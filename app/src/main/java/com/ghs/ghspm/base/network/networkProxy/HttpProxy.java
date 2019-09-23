package com.ghs.ghspm.base.network.networkProxy;

import android.util.ArrayMap;

import com.ghs.ghspm.base.network.NetWorkUtil;
import com.ghs.ghspm.base.network.okgo.NetCallBackInterface;
import com.ghs.ghspm.tools.EventBusProperty;
import com.ghs.ghspm.tools.EventManager;

import java.util.Map;


/**
 * Author:wang_sir
 * Time:2019/4/3 16:50
 * Description:This is HttpProxy
 */
public class HttpProxy<H extends HttpProxy> {
    private static Map requestMap = new ArrayMap();

    private HttpStaticProxyInterface httpProxy = null;


    public static HttpProxy getInstance() {
        requestMap.clear();
        return HttpStaticProxyHolder.httpStaticProxy;
    }

    public void initNetProxyType(HttpStaticProxyInterface httpProxy) {
        this.httpProxy = httpProxy;
    }

    /**
     * 添加参数
     *
     * @param key
     * @param value
     * @return
     */
    public H params(String key, String value) {
        requestMap.put(key, value);
        return (H) this;
    }

    /**
     * 添加参数
     *
     * @param key
     * @param value
     * @return
     */
    public H params(String key, int value) {
        if (-1 == value) {
            return (H) this;
        }
        String valueStr = String.valueOf(value);
        requestMap.put(key, valueStr);
        return (H) this;
    }

    /**
     * 添加参数
     *
     * @param key
     * @param value
     * @return
     */
    public H params(String key, double value) {
        if (-1 == value) {
            return (H) this;
        }
        String valueStr = String.valueOf(value);
        requestMap.put(key, valueStr);
        return (H) this;
    }


    public H params(String key, Long value) {

        if (value != null) {
            String valueStr = String.valueOf(value);
            requestMap.put(key, valueStr);
        } else {
            requestMap.put(key, null);
        }

        return (H) this;
    }


    /**
     * 添加参数
     *
     * @param map
     * @return
     */
    public H params(Map<String, String> map) {
        requestMap.putAll(map);
        return (H) this;
    }

    public void postToNetwork(String urlPath, NetCallBackInterface netCallBackInterface) {
        if (NetWorkUtil.isNetworkAvailable()) {
            if (httpProxy != null) {
                httpProxy.postToNetwork(urlPath, requestMap, netCallBackInterface);
            }
        }else{
            //通知网络变化
            EventManager.sendStringMsg(EventBusProperty.NET_WORK_UNCONNECT);
        }

    }

    public void getToNetwork(String urlPath, NetCallBackInterface netCallBackInterface) {
        if (NetWorkUtil.isNetworkAvailable()) {
            if (httpProxy != null) {
                httpProxy.getToNetwork(urlPath, requestMap, netCallBackInterface);
            }
        }else{
            //通知网络变化
            EventManager.sendStringMsg(EventBusProperty.NET_WORK_UNCONNECT);
        }
    }

    public void postToNetwork(String urlPath, boolean returnErrorCode, NetCallBackInterface netCallBackInterface) {
        if (NetWorkUtil.isNetworkAvailable()) {
            if (httpProxy != null) {
                httpProxy.postToNetwork(urlPath, requestMap, netCallBackInterface, returnErrorCode);
            }
        }else{
            //通知网络变化
            EventManager.sendStringMsg(EventBusProperty.NET_WORK_UNCONNECT);
        }
    }

    public void getToNetwork(String urlPath, boolean returnErrorCode, NetCallBackInterface netCallBackInterface) {
        if (NetWorkUtil.isNetworkAvailable()) {
            if (httpProxy != null) {
                httpProxy.getToNetwork(urlPath, requestMap, netCallBackInterface, returnErrorCode);
            }
        }else{
            //通知网络变化
            EventManager.sendStringMsg(EventBusProperty.NET_WORK_UNCONNECT);
        }

    }

    private static class HttpStaticProxyHolder {
        private static HttpProxy httpStaticProxy = new HttpProxy();
    }


}
