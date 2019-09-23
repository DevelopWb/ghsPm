package com.ghs.ghspm.models.oss;

import com.ghs.ghspm.base.network.RequestStatus;
import com.ghs.ghspm.base.network.networkProxy.HttpProxy;
import com.ghs.ghspm.base.network.okgo.NetCallBackInterface;
import com.ghs.ghspm.bean.OssTokenBean;
import com.ghs.ghspm.tools.Contract;
import com.ghs.ghspm.tools.GsonManager;

/**
 * Author:wang_sir
 * Time:2018/8/10 19:18
 * Description:This is OssTokenModel
 */
public class OssTokenModel implements OssInterface {

    @Override
    public void getOssToken(final RequestStatus requestStatus, final String tag) {

        HttpProxy.getInstance()
                .postToNetwork(Contract.GET_OSS_TOKEN,  new NetCallBackInterface() {
                    @Override
                    public void onSuccess(String content) {
                        if (requestStatus != null) {
                            requestStatus.onSuccess(GsonManager.getInstance().parseJsonToBean(content, OssTokenBean.class), tag);
                        }
                    }

                    @Override
                    public void onError(String str) {
                        if (requestStatus != null) {
                            requestStatus.onError(str);
                        }
                    }
                });
    }

}
