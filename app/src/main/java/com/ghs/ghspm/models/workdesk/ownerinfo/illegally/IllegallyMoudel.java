package com.ghs.ghspm.models.workdesk.ownerinfo.illegally;

import com.ghs.ghspm.base.network.RequestStatus;
import com.ghs.ghspm.base.network.networkProxy.HttpProxy;
import com.ghs.ghspm.base.network.okgo.NetCallBackInterface;
import com.ghs.ghspm.bean.IllegallyMessageBean;
import com.ghs.ghspm.tools.Contract;
import com.ghs.ghspm.tools.GsonManager;

public class IllegallyMoudel implements IllegallyContract.illegallyMoudel {
    @Override
    public void getIllegallyMessage(String carNum, String villageId, final RequestStatus requestStatus, final String tag) {

        HttpProxy.getInstance()
                .params("villageId", villageId)
                .params("carNum", carNum)
                .postToNetwork(Contract.CAR_ILLEGALLY,  new NetCallBackInterface() {
                    @Override
                    public void onSuccess(String content) {
                        if (requestStatus != null) {
                            requestStatus.onSuccess(GsonManager.getInstance().parseJsonToBean(content, IllegallyMessageBean.class), tag);
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
