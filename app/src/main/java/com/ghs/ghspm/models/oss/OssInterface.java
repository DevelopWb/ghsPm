package com.ghs.ghspm.models.oss;

import com.ghs.ghspm.base.network.RequestStatus;

/**
 * Author:wang_sir
 * Time:2018/10/9 16:30
 * Description:This is OssInterface
 */
public interface OssInterface {

    String OSS_TOKEN = "oss_token";

    /**
     * 获取ossTOken
     */
    void getOssToken(RequestStatus requestStatus, String tag);

}
