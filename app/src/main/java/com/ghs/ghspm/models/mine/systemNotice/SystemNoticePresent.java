package com.ghs.ghspm.models.mine.systemNotice;

import com.ghs.ghspm.base.BasePresent;
import com.ghs.ghspm.base.network.networkProxy.HttpProxy;
import com.ghs.ghspm.base.network.okgo.NetCallBackInterface;
import com.ghs.ghspm.bean.SysNoticeBean;
import com.ghs.ghspm.tools.Contract;
import com.ghs.ghspm.tools.GsonManager;

/**
 * Author:wang_sir
 * Time:2019/2/21 15:01
 * Description:This is SystemNoticePresent
 */
public class SystemNoticePresent extends BasePresent<SystemNoticeContract.ISystemNoticeView> implements SystemNoticeContract.ISystemNoticeP {



    @Override
    public void getNoticeList(int userId, final String tag) {

        HttpProxy.getInstance()
                .params("userId",userId)

                .postToNetwork(Contract.GET_SYS_NOTICE,  new NetCallBackInterface() {
                    @Override
                    public void onSuccess(String content) {
                        if (getView() != null) {
                            getView().updateView(GsonManager.getInstance().parseJsonToBean(content, SysNoticeBean.class),tag);
                        }
                    }

                    @Override
                    public void onError(String str) {
                        if (getView() != null) {
                            getView().onError(str);
                        }
                    }
                });
        
        
    }

}
