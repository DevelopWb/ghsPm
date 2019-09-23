package com.ghs.ghspm.tools;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;

public class MobileInfoUtils {

    //跳转至授权页面  自启动
    public static void jumpStartInterface(Context context) {
        int sdk = getMobileVersionCode();
        String mobileType = getMobileType();
        Intent intent = new Intent();
        try {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            Log.e("HLQ_Struggle", "******************当前手机型号为：" + mobileType);
            Log.e("HLQ_Struggle", "******************当前手机SDK为：" + getMobileVersionCode());
            ComponentName componentName = null;
            if (mobileType.equals("Xiaomi")) { // 红米Note4测试通过
                componentName = new ComponentName("com.miui.securitycenter", "com.miui.permcenter.autostart.AutoStartManagementActivity");

            } else if (mobileType.equals("Letv")) { // 乐视2测试通过
                intent.setAction("com.letv.android.permissionautoboot");
            } else if (mobileType.equals("samsung")) { // 三星Note5测试通过
                if (sdk > 24) {//7.0以上
                    componentName = new ComponentName("com.samsung.android.sm_cn", "com.samsung.android.sm.ui.ram.AutoRunActivity");
                } else {
                    componentName = ComponentName.unflattenFromString("com.samsung.android.sm/.app.dashboard.SmartManagerDashBoardActivity");

                }
                //
                //componentName = ComponentName.unflattenFromString("com.samsung.android.sm/.ui.ram.RamActivity");// Permission Denial not exported from uid 1000，不允许被其他程序调用
            } else if (mobileType.equals("HUAWEI") || "honor".equals(mobileType)) { // 华为测试通过
                if (sdk > 25) {//8.0以及以上的系统
                    componentName = ComponentName.unflattenFromString("com.huawei.systemmanager/.appcontrol.activity.StartupAppControlActivity");//跳自启动管理
                } else if (sdk > 22 && sdk < 26) {//6.0到7.0
                    componentName = ComponentName.unflattenFromString("com.huawei.systemmanager/.startupmgr.ui.StartupNormalAppListActivity");//跳自启动管理
                } else {
                    componentName = ComponentName.unflattenFromString("com.huawei.systemmanager/.optimize.bootstart.BootStartActivity");//跳自启动管理
                }
                //SettingOverlayView.show(context);
            } else if (mobileType.equals("vivo")) { // VIVO测试通过
                if (Build.VERSION.SDK_INT >= 26) {
                    componentName = new ComponentName("com.vivo.permissionmanager", "com.vivo.permissionmanager.activity.PurviewTabActivity");
                } else {
                    componentName = new ComponentName("com.iqoo.secure", "com.iqoo.secure.ui.phoneoptimize.SoftwareManagerActivity");
                }

            } else if (mobileType.equals("Meizu")) { //万恶的魅族
                //componentName = ComponentName.unflattenFromString("com.meizu.safe/.permission.PermissionMainActivity");//跳转到手机管家
                componentName = ComponentName.unflattenFromString("com.meizu.safe/.permission.SmartBGActivity");//跳转到后台管理页面
            } else if (mobileType.equals("OPPO")) { // OPPO R8205测试通过

                componentName = ComponentName
                        .unflattenFromString("com.coloros.safecenter/.startupapp.StartupAppListActivity");

            } else if (mobileType.equals("ulong")) { // 360手机 未测试
                componentName = new ComponentName("com.yulong.android.coolsafe", ".ui.activity.autorun.AutoRunListActivity");
            } else {
                // 将用户引导到系统设置页面
                if (Build.VERSION.SDK_INT >= 9) {
                    Log.e("HLQ_Struggle", "APPLICATION_DETAILS_SETTINGS");
                    intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
                    intent.setData(Uri.fromParts("package", context.getPackageName(), null));
                } else if (Build.VERSION.SDK_INT <= 8) {
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails");
                    intent.putExtra("com.android.settings.ApplicationPkgName", context.getPackageName());
                }
            }
            intent.setComponent(componentName);
            context.startActivity(intent);

        } catch (Exception e) {//抛出异常就直接打开设置页面
            Log.e("HLQ_Struggle", e.getLocalizedMessage());
            intent = new Intent(Settings.ACTION_SETTINGS);
            context.startActivity(intent);
        }
    }

    //获取手机系统sdk 版本
    private static int getMobileVersionCode() {
        return Build.VERSION.SDK_INT;
    }

    //    private SettingDialogPermision dialog_per;
    //获取手机类型
    private static String getMobileType() {
        return Build.MANUFACTURER;
    }

    /**
     * 跳转到允许通知界面
     */
    public static void jumpToAllowNotice(Context context) {

        // 出现异常则跳转到应用设置界面：锤子坚果3——OC105 API25
        Intent intent = new Intent();

        //下面这种方案是直接跳转到当前应用的设置界面。
        //https://blog.csdn.net/ysy950803/article/details/71910806
        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", context.getPackageName(), null);
        intent.setData(uri);
        context.startActivity(intent);
//        try {
//            // 根据isOpened结果，判断是否需要提醒用户跳转AppInfo页面，去打开App通知权限
//            Intent intent = new Intent();
//            intent.setAction(Settings.ACTION_APP_NOTIFICATION_SETTINGS);
//            //这种方案适用于 API 26, 即8.0（含8.0）以上可以用
////            intent.putExtra(EXTRA_APP_PACKAGE, getPackageName());
////            intent.putExtra(EXTRA_CHANNEL_ID, getApplicationInfo().uid);
////
////            //这种方案适用于 API21——25，即 5.0——7.1 之间的版本可以使用
//            intent.putExtra("app_package", context.getPackageName());
//            intent.putExtra("app_uid", context.getApplicationInfo().uid);
//
//            // 小米6 -MIUI9.6-8.0.0系统，是个特例，通知设置界面只能控制"允许使用通知圆点"——然而这个玩意并没有卵用，我想对雷布斯说：I'm not ok!!!
//            if ("MI 6".equals(Build.MODEL)) {
//                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
//                Uri uri = Uri.fromParts("package", context.getPackageName(), null);
//                intent.setData(uri);
//                intent.setAction("com.android.settings/.SubSettings");
//            }
//            context.startActivity(intent);
//        } catch (Exception e) {
//            e.printStackTrace();
//            // 出现异常则跳转到应用设置界面：锤子坚果3——OC105 API25
//            Intent intent = new Intent();
//
//            //下面这种方案是直接跳转到当前应用的设置界面。
//            //https://blog.csdn.net/ysy950803/article/details/71910806
//            intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
//            Uri uri = Uri.fromParts("package", context.getPackageName(), null);
//            intent.setData(uri);
//            context.startActivity(intent);
//        }
    }
}

