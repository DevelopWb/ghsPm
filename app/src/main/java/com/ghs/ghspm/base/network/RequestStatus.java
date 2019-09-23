package com.ghs.ghspm.base.network;

/**
 * Author:wang_sir
 * Time:2018/6/27 17:33
 * Description:This is RequestStatus
 */
public interface RequestStatus<T> {
    String REFRESH = "refresh";
    String UPDATE = "update";

    void onStart(String tag);

    void onSuccess(T t, String tag);//refresh 刷新  update 更新

    void onError(String tag);
}
