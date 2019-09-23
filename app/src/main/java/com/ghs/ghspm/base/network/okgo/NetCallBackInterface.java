package com.ghs.ghspm.base.network.okgo;

/**
 * Author:wang_sir
 * Time:2019/4/3 11:42
 * Description:This is NetCallBackInterface
 */
public interface NetCallBackInterface  {


    void  onSuccess(String  content);
    void  onError(String  str);
}
