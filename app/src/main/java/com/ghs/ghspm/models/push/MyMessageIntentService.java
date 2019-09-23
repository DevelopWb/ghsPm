package com.ghs.ghspm.models.push;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.alibaba.sdk.android.push.AliyunMessageIntentService;
import com.alibaba.sdk.android.push.notification.CPushMessage;
import com.ghs.ghspm.bean.PushBean;
import com.ghs.ghspm.models.main.SplashActivity;
import com.ghs.ghspm.models.mine.systemNotice.SystemNoticeActivity;
import com.ghs.ghspm.models.workOrder.workOrderDetail.WorkOrderDetailActivity;
import com.ghs.ghspm.models.workdesk.publicfuction.notice.NoticeActivity;
import com.ghs.ghspm.models.workdesk.signAndTable.SignAndTableActivity;
import com.ghs.ghspm.tools.EventBusProperty;
import com.ghs.ghspm.tools.EventManager;
import com.ghs.ghspm.tools.PubUtil;
import com.google.gson.Gson;

import java.util.List;
import java.util.Map;


/**
 * Created by liyazhou on 17/8/22.
 * 为避免推送广播被系统拦截的小概率事件,我们推荐用户通过IntentService处理消息互调,接入步骤:
 * 1. 创建IntentService并继承AliyunMessageIntentService
 * 2. 覆写相关方法,并在Manifest的注册该Service
 * 3. 调用接口CloudPushService.setPushIntentService
 * 详细用户可参考:https://help.aliyun.com/document_detail/30066.html#h2-2-messagereceiver-aliyunmessageintentservice
 */

public class MyMessageIntentService extends AliyunMessageIntentService {

    private static final String TAG = "MyMessageIntentService";


    /**
     * 推送通知的回调方法
     *
     * @param context
     * @param title
     * @param summary
     * @param extraMap
     */
    @Override
    protected void onNotification(Context context, String title, String summary, Map<String, String> extraMap) {
        Log.i(TAG, "收到一条推送通知 ： " + title + ", summary:" + summary);
        //来电亮屏
        ScreensUtils.isScree(context);
        if ("工单".equals(title)) {
            EventManager.sendStringMsg(EventBusProperty.RECEIVE_WORK_ORDER_NOTICE);
        }

        Log.i(PubUtil.RED_POINT_TAG, "收到一条通知，开始发送--------------");

//

    }

    /**
     * 推送消息的回调方法
     *
     * @param context
     * @param cPushMessage
     */
    @Override
    protected void onMessage(Context context, CPushMessage cPushMessage) {
        Log.i(TAG, "收到一条推送消息 ： " + cPushMessage.getTitle() + ", content:" + cPushMessage.getContent());


    }

    /**
     * 从通知栏打开通知的扩展处理
     *
     * @param context
     * @param title
     * @param summary
     * @param extraMap
     */
    @Override
    protected void onNotificationOpened(Context context, String title, String summary, String extraMap) {
        Log.i(TAG, "onNotificationOpened ： " + " : " + title + " : " + summary + " : " + extraMap);
        Log.i(TAG, "summary" + summary + extraMap + "extramap");

        Gson gson = new Gson();
        PushBean pushBean = gson.fromJson(extraMap, PushBean.class);

        if (pushBean != null) {
            String noticeType = pushBean.getNoticeType();
            if (isAppRunning(context, context.getPackageName())) {

                if (AliPushManager.PUSH_PM_INNER_NOTICE.equals(noticeType)) {
                    //跳转到公告页面
                    Log.d("MiPushRegister", "公告");

                    Intent intent = new Intent(context, NoticeActivity.class);
                    intent.putExtra(AliPushManager.PUSH_NOTICE_TYPE, noticeType);
                    startActivity(intent);
                } else  if (AliPushManager.PUSH_FORM_TASK.equals(noticeType)) {
                    Intent intent = new Intent(context, SignAndTableActivity.class);
                    intent.putExtra(AliPushManager.PUSH_NOTICE_TYPE, noticeType);
                    startActivity(intent);
                } else if (AliPushManager.PUSH_ARRANGE_NOTICE.equals(noticeType)) {
                    Intent intent = new Intent(context, SystemNoticeActivity.class);
                    intent.putExtra(AliPushManager.PUSH_NOTICE_TYPE, noticeType);
                    startActivity(intent);
                } else if (AliPushManager.PUSH_WORK_ORDER_NOTICE.equals(noticeType)) {
                    Intent intent = new Intent(context, WorkOrderDetailActivity.class);
                    intent.putExtra(AliPushManager.PUSH_NOTICE_TYPE, pushBean.getRecordId());
                    startActivity(intent);
                }
            } else {
                Log.d("MiPushRegister", "main类");
                Intent intent = new Intent(context, SplashActivity.class);
                intent.putExtra(AliPushManager.PUSH_NOTICE_TYPE, noticeType);
                startActivity(intent);
            }

        }

    }

    /**
     * 无动作通知点击回调。当在后台或阿里云控制台指定的通知动作为无逻辑跳转时,通知点击回调为onNotificationClickedWithNoAction而不是onNotificationOpened
     *
     * @param context
     * @param title
     * @param summary
     * @param extraMap
     */
    @Override
    protected void onNotificationClickedWithNoAction(Context context, String title, String summary, String extraMap) {
        Log.i(TAG, "onNotificationClickedWithNoAction ： " + " : " + title + " : " + summary + " : " + extraMap);
    }

    /**
     * 通知删除回调
     *
     * @param context
     * @param messageId
     */
    @Override
    protected void onNotificationRemoved(Context context, String messageId) {
        Log.i(TAG, "onNotificationRemoved ： " + messageId);
    }

    /**
     * 应用处于前台时通知到达回调。注意:该方法仅对自定义样式通知有效,相关详情请参考https://help.aliyun.com/document_detail/30066.html#h3-3-4-basiccustompushnotification-api
     *
     * @param context
     * @param title
     * @param summary
     * @param extraMap
     * @param openType
     * @param openActivity
     * @param openUrl
     */
    @Override
    protected void onNotificationReceivedInApp(Context context, String title, String summary, Map<String, String> extraMap, int openType, String openActivity, String openUrl) {
        Log.i(TAG, "onNotificationReceivedInApp ： " + " : " + title + " : " + summary + "  " + extraMap + " : " + openType + " : " + openActivity + " : " + openUrl);
    }

    public boolean isAppRunning(Context context, String packageName) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        if (am != null) {
            List<ActivityManager.RunningTaskInfo> list = am.getRunningTasks(100);
            if (list.size() <= 0) {
                return false;
            }
            for (ActivityManager.RunningTaskInfo info : list) {
                if (info.baseActivity.getPackageName().equals(packageName)) {
                    return true;

                }
            }
        }

        return false;
    }


}
