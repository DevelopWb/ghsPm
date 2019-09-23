package com.ghs.ghspm.models.push;

import android.content.Context;
import android.os.PowerManager;

public class ScreensUtils {

    // 亮屏操作
    private static void wakeUpAndUnlock(Context context) {
        // 获取电源管理器对象
        PowerManager pm = (PowerManager) context
                .getSystemService(Context.POWER_SERVICE);
        if (pm != null) {
            boolean screenOn = pm.isScreenOn();
            if (!screenOn) {
                // 获取PowerManager.WakeLock对象,后面的参数|表示同时传入两个值,最后的是LogCat里用的Tag
                PowerManager.WakeLock wl = pm.newWakeLock(
                        PowerManager.ACQUIRE_CAUSES_WAKEUP |
                                PowerManager.SCREEN_BRIGHT_WAKE_LOCK, "bright");
                wl.acquire(10000); // 点亮屏幕
                wl.release(); // 释放
            }
        }

    }

    //判断屏幕状态
    public static void isScree(Context context) {
        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        if (pm != null) {
            boolean isScreenOn = pm.isScreenOn();
            if (!isScreenOn) {
                wakeUpAndUnlock(context);
            }
        }


    }


}
