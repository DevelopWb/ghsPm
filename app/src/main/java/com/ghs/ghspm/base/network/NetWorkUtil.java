package com.ghs.ghspm.base.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.ghs.ghspm.MyApplication;

/**
 * Author:wang_sir
 * Time:2018/8/20 10:42
 * Description:This is NetWorkUtil
 */
public class NetWorkUtil {


    //判断当前网络是否可用
    public static boolean isNetworkAvailable() {
        ConnectivityManager manager = (ConnectivityManager) MyApplication.getAppContext().getSystemService(
                Context.CONNECTIVITY_SERVICE);
        if (manager == null) {
            return false;
        }
        NetworkInfo info = manager.getActiveNetworkInfo();
        if (null != info && info.isConnected()) {
            if (info.getState() == NetworkInfo.State.CONNECTED) {
                return true;
            }
        }
        return false;
    }
}
