package com.ghs.ghspm.base.network.networkProxy;

import com.ghs.ghspm.base.network.okgo.NetCallBackInterface;

import java.util.Map;

/**
 * Author:wang_sir
 * Time:2019/4/3 17:00
 * Description:This is HttpStaticProxyInterface
 */
public interface HttpStaticProxyInterface<T> {

    void   postToNetwork(String urlPath, Map<String,String> map,  NetCallBackInterface netCallBackInterface);
    void   postToNetwork(String urlPath, Map<String,String> map,  NetCallBackInterface netCallBackInterface,boolean returnErrorCode);
    void   getToNetwork(String urlPath, Map<String,String> map,  NetCallBackInterface netCallBackInterface);
    void   getToNetwork(String urlPath, Map<String,String> map,  NetCallBackInterface netCallBackInterface,boolean returnErrorCode);

}
