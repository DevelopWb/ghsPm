package com.ghs.ghspm.models.push;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.alibaba.sdk.android.push.CloudPushService;
import com.alibaba.sdk.android.push.CommonCallback;
import com.alibaba.sdk.android.push.noonesdk.PushServiceFactory;
import com.ghs.ghspm.R;

public class AliPushManager {

    private static volatile AliPushManager instance = null;
    private CloudPushService pushService;
    private String TAG = "PUSHAL";
    private static String PUSH_PM_USER = "pm_user_";//账号       别名
    private static String PUSH_PM_DEPT = "pm_dept_";//物管部门   别名
    public static String PUSH_NOTICE_TYPE = "pm_notice_type";//物管通知类型



    /**
     * 物管消息类型  对应notification的channelId
     */
    public static String PUSH_PM_INNER_NOTICE = "pm_notice";//物管发布内部通知
    public static String PUSH_FORM_TASK = "pm_form_task";//表单任务
    public static String PUSH_ARRANGE_NOTICE = "pm_arrange_work";//物管排班
    public static String PUSH_WORK_ORDER_NOTICE = "service_work";//工单

    private AliPushManager() {

    }

    public static AliPushManager getInstance() {
        if (instance == null) {
            synchronized (AliPushManager.class) {
                if (instance == null) {
                    instance = new AliPushManager();
                }
            }
        }
        return instance;
    }

    /**
     * 初始化推送对象
     */
    public void initPush(Context context) {
        PushServiceFactory.init(context);
        pushService = PushServiceFactory.getCloudPushService();
        pushService.setPushIntentService(MyMessageIntentService.class);
        pushRegister(context);
        initConfig(context);

// 设置推送的图标       pushService.setNotificationSmallIcon(R.mipmap.group);

    }

    /**
     *
     */
    private void initConfig(Context context) {
        //设置通知栏图标
        pushService.setNotificationLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.mipmap.ghpm_icon));
        //设置状态栏图标  图标不能太大  图片背景透明 要不不显示
        pushService.setNotificationSmallIcon(R.mipmap.ghpm_small_icon);

    }

    /**
     * 注册
     */
    private void pushRegister(Context context) {

        pushService.register(context, new CommonCallback() {
            @Override
            public void onSuccess(String s) {
                Log.i(TAG, s+"=============阿里推送 注册成功");
            }

            @Override
            public void onFailed(String s, String s1) {
                Log.i(TAG, s);
            }
        });


    }

    /**
     * 添加别名
     * userId
     *
     * @param userId
     */
    private void addAliasUserId(final int userId) {

        pushService.addAlias(PUSH_PM_USER + userId, new CommonCallback() {
            @Override
            public void onSuccess(String s) {
                Log.i(TAG, userId+"添加别名！！！！！！！！！！！");
            }

            @Override
            public void onFailed(String s, String s1) {
                Log.i(TAG, s);
            }
        });

    }


    /**
     * 删除别名alias 别名（alias = null or alias.length = 0时，删除设备全部别名）
     */
    public void removeAllAlias() {

        pushService.removeAlias(null, new CommonCallback() {
            @Override
            public void onSuccess(String s) {
                Log.i(TAG, "删除所有的别名成功----------------"+s);
            }

            @Override
            public void onFailed(String s, String s1) {
                Log.i(TAG, s);
            }
        });

    }



    /**
     * @param userid 用户别名
     */
    public void bindAll(int userid) {
        removeAllAlias();
        addAliasUserId(userid);
    }

    /**
     * 查询别名
     */

    public void seachBoundAlias() {

        if (pushService != null) {
            pushService.listAliases(new CommonCallback() {
                @Override
                public void onSuccess(String s) {
                    Log.i(TAG, "绑定的别名-----------" + s);
                }

                @Override
                public void onFailed(String s, String s1) {
                    Log.i(TAG, "alias-error-----------" + s);
                }
            });
        }

    }


    /**
     * 查询绑定的标签
     */

    public void seachBoundTags() {
        if (pushService != null) {
            pushService.listTags(1, new CommonCallback() {
                @Override
                public void onSuccess(String s) {
                    Log.i(TAG, "绑定的tag-----------" + s);
                }

                @Override
                public void onFailed(String s, String s1) {
                    Log.i(TAG, "tag-----------" + s);
                }
            });

        }
    }

}
