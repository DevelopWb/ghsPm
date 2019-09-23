package com.ghs.ghspm.tools;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.Uri;
import android.provider.Settings;
import android.support.design.widget.TabLayout;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.ghs.ghspm.bean.MultiWorkDeskMenuBean;
import com.ghs.ghspm.bean.PatrolFormListBean;
import com.ghs.ghspm.bean.UsersFromRoleBean;
import com.ghs.ghspm.bean.section.OrganizeMenuBean;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Author:wang_sir
 * Time:2018/7/3 10:28
 * Description:This is PubUtil
 */
public class PubUtil {
    public static   String[] promissions = {Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.WRITE_EXTERNAL_STORAGE
            , Manifest.permission.CALL_PHONE, Manifest.permission.CAMERA};
    public static int LOGIN_ENTER = 1;//进入登录界面的入口。1为homefragment 2为设置中退出,3为token过期时的跳转,4,其他
    //发起任务时选择相关人。0代表执行人支持多选||4.代表日常表单任务中选择人员，1代表负责人,只能单选，2代表抄送人支持多选,3代表选择转发的联系人,
    public static int publish_task_select_people = -1;//1或者3代表单选，其他多选。0代表执行人，1代表负责人，2代表抄送人，3代表转发人，4代表签批人。
    //EventBusMsg
    public static String EVENTBUS_TOCKEN_EXPIRED = "eventbus_tocken_expired";//token过期
    public static boolean tokenExpiredFirstNotice = true;//token过期第一次提醒

    public static String INFO_MODIFY_SUCESS = "info_modify_success";//个人资料修改完毕

    public static int NOTICE_TYPE = -1;//公告中制度和小区公告的类型 0代表制度，1代表小区公告
    public static int SHIFT_TYPE = 1;//1：固定班次；2：自由工时 3:排班
    public static int SIGN_TABLE_TYPE = -1;//签批与表单中  0代表我签批的 1代表我提交的 2 代表抄送我的
    public static int ScreenHeight;//屏幕高度
    public static int ScreenWidth;//屏幕宽度
    public static int LAYOUT_TYPE = 0;//布局类型，0是投诉建议，1是写进展，
    public static LinkedHashMap<Integer, UsersFromRoleBean.DataBean> selectedUsersMap = new LinkedHashMap<>();//发起任务时，选中的用户（执行人或者负责人，或者抄送人）
    public static int clickedTaskID;//任务模块中，点击任务的实体的任务id
    public static int clickedTaskType = -1;//任务模块中，点击任务进入任务详情的入口:0代表待处理任务，1代表我发起的任务。。。
    public static boolean taskFragmentActivityFinished = false;//活动关闭了
    public static boolean showTabRedPoint = false;//展示tab小红点
    public static String RED_POINT_TAG = "red_point_tag";
    public static int DailyRecordActivityEntry = -1;//日常记录类的入口  0是工作台上的  1是待处理里面进入的 2是巡检表单中的填写  3是巡检表单中的修改
    public static int MODIFY_PATROL_FORM_INFO = -1;//修改表单的头信息或者尾部信息，0代表提取新表单，1代表编辑表单
    public static int TableTaskDesActivityEntry = -1;//表单任务详情类的入口  0是记录类的入口recordofmineactivity  1是其他类的入口
    public static List<MultiWorkDeskMenuBean> toolForm = new ArrayList<>();//工作台上的工具类服务单

    public static boolean CAN_PUBLISH_TASK = false;//可以发布任务
    public static boolean CAN_PUBLISH_NOTICE = false;//可以发布公告
    public static boolean CHANGE_PROPERTY = false;//切换空间
    public static boolean TASKMODULE = true;//点击任务模块
    public static List<String> CURRENT_SELECTED_VALUE_LIST = new LinkedList<>();//巡检表单任务记录中 ，当前选中的item的值
    public static List<String> PATROL_FORM_TASK_ROW_TITLE = new ArrayList<>();//巡检表单任务记录中 ，当前选中的item的值
    public static List<String> ORDER_OPERATE_PROMISSION = new ArrayList<>();//工单操作权限按钮
    public static String patrolFormSelectedTowerId = "";//巡检表单中 已经选中的楼号id
    public static String patrolFormSelectedCellId = "";//巡检表单中 已经选中的单元id
    public static PatrolFormListBean.DataBean PATROL_FORM_LIST_DATABEAN;//巡检巡查表单列表里的item


    public  static String  NOTIFICATION_CHANNEL_ACTIVITY_ID = "ghspm_activity_id";//小区活动  系统通知
    public  static String  NOTIFICATION_CHANNEL_ACTIVITY_NAME = "活动通知";//小区活动  系统通知
    public  static String  NOTIFICATION_CHANNEL_ACTIVITY_DES = "小区活动 系统通知";//小区活动  系统通知
    public static String  UPADAT_ORDER="updata_order_data";//编辑工内容

    /**
     * 获取随机4位数
     */
    public static String getRandomData() {
        StringBuilder strRand = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            strRand.append(String.valueOf((int) (Math.random() * 10)));
        }
        return strRand.toString();
    }

    /**
     * 是否开启gps
     *
     * @param context
     * @return
     */
    public static boolean checkGpsIsEnable(Context context) {
		/* 用Setting.System来读取也可以，只是这是更旧的用法
		String str = Settings.System.getString(getContentResolver(),
				Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
		*/
        String str = Settings.Secure.getString(context.getContentResolver(),
                Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
        Log.v("GPS", str);
        return str != null && str.contains("gps");


    }

    /**
     * 验证手机格式
     */
    public static boolean isMobileNO(String mobiles) {
        /*
         * 移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
         * 联通：130、131、132、152、155、156、185、186 电信：133、153、180、189、（1349卫通）
         * 总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9
         */
        String telRegex = "[1][23456789]\\d{9}";// "[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        return !TextUtils.isEmpty(mobiles) && mobiles.matches(telRegex);
    }

    /**
     * 设置控件的margin
     *
     * @param v
     * @param l
     * @param t
     * @param r
     * @param b
     */
    public static void margin(View v, int l, int t, int r, int b) {
        if (v.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
            p.setMargins(l, t, r, b);
            v.requestLayout();
        }
    }

    /**
     * 拨打电话（直接拨打电话）
     *
     * @param phoneNum 电话号码
     */
    public static void callPhone(Context context, String phoneNum) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:" + phoneNum);
        intent.setData(data);
        context.startActivity(intent);
    }


    /**
     * 检测服务端返回数据的状态
     *
     * @param content
     * @return
     */
    public static boolean initContent(String content) {
        boolean status = false;
        if (StrUtils.isStringValueOk(content)) {
            try {
                JSONObject object = new JSONObject(content);
                int code = object.getInt("code");
                if (code == 1000) {
                    status = true;
                } else {
                    status = false;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        return status;
    }

    /**
     * 获取服务端返回的message信息
     *
     * @param content
     * @return
     */
    public static String getServerMessage(String content) {
        String message = "";
        if (StrUtils.isStringValueOk(content)) {
            try {
                JSONObject object = new JSONObject(content);
                message = object.getString("message");
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        return message;
    }

    /**
     * 获取服务端返回的data信息
     *
     * @param content
     * @return
     */
    public static String getServerData(String content) {
        String data = "";
        if (StrUtils.isStringValueOk(content)) {
            try {
                JSONObject object = new JSONObject(content);
                data = object.getString("data");
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        return data;
    }

    /**
     * code
     *
     * @param content
     * @return
     */
    public static int getServerCode(String content) {
        int code = -1;
        if (StrUtils.isStringValueOk(content)) {
            try {
                JSONObject object = new JSONObject(content);
                code = object.getInt("code");
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        return code;
    }

    /**
     * 判定微信是否安装
     *
     * @param context
     * @return
     */
    public static boolean isWeixinAvilible(Context context) {
        final PackageManager packageManager = context.getPackageManager();// 获取packagemanager
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);// 获取所有已安装程序的包信息
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                if (pn.equals("com.tencent.mm")) {
                    return true;
                }
            }
        }
        return false;
    }


    /**
     * 获取软件版本号
     */
    public static String getAPPVersion(Context context) {
        PackageManager pm = context.getPackageManager();//得到PackageManager对象
        String version_app = "0.0.0";
        try {
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);//得到PackageInfo对象，封装了一些软件包的信息在里面
            version_app = pi.versionName;//获取清单文件中versionCode节点的值
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return version_app;
    }

    /**
     * 将像素转换成dp
     *
     * @param context
     * @param pxValue
     * @return
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }





    /**
     * view获取焦点
     */
    public static void getViewFocus(View view) {
        view.setFocusable(true);
        view.setFocusableInTouchMode(true);
        view.requestFocus();
    }

    /**
     * 设置tablayout底部导航条的宽度
     *
     * @param tabs
     * @param leftDip  距离左边的边距
     * @param rightDip 距离右边的边距
     */
    public static void setIndicator(TabLayout tabs, int leftDip, int rightDip) {
        try {
            Field tabStrip = tabs.getClass().getDeclaredField("mTabStrip");
            tabStrip.setAccessible(true);
            LinearLayout llTab = null;
            llTab = (LinearLayout) tabStrip.get(tabs);
            int left = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, leftDip, Resources.getSystem().getDisplayMetrics());
            int right = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, rightDip, Resources.getSystem().getDisplayMetrics());

            for (int i = 0; i < llTab.getChildCount(); i++) {
                View child = llTab.getChildAt(i);
                child.setPadding(0, 0, 0, 0);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
                params.leftMargin = left;
                params.rightMargin = right;
                child.setLayoutParams(params);
                child.invalidate();
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }


    //通过HashSet删除list中的重复数据
//    此方法的思想是:
//    两层循环,外层循环从第一个元素想最后一个元素循环,
//    内层循环是从最后一个元素向外层循环元素的当前元素循环
//            比较两个元素是否相等,如果相等,移除靠后的元素来进行去重
//    这种方法时间复杂度大于O(n),小于O(N²)
    public static List removeDuplicateDataOfList(List<OrganizeMenuBean> list) {
        for (int i = 0; i < list.size(); i++)  //外循环是循环的次数
        {
            for (int j = list.size() - 1; j > i; j--)  //内循环是 外循环一次比较的次数
            {
                if (list.get(i).getUniqueTag().equals(list.get(j).getUniqueTag())) {
                    list.remove(list.get(j));
                }
            }
        }
        return list;
    }
    /**
     * 获取activity
     * @param context
     * @return
     */
    public static Activity getActivity(Context context) {
        while (!(context instanceof Activity) && context instanceof ContextWrapper) {
            context = ((ContextWrapper) context).getBaseContext();
        }
        if (context instanceof Activity) {
            return (Activity) context;
        }else {
            return null;
        }
    }


}
