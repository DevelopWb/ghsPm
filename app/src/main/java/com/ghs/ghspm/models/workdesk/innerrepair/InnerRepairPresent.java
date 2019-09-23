package com.ghs.ghspm.models.workdesk.innerrepair;

import com.ghs.ghspm.base.BasePresent;
import com.ghs.ghspm.base.network.networkProxy.HttpProxy;
import com.ghs.ghspm.base.network.okgo.NetCallBackInterface;
import com.ghs.ghspm.bean.InnerRepairBean;
import com.ghs.ghspm.tools.Contract;
import com.ghs.ghspm.tools.GsonManager;
import com.ghs.ghspm.tools.UserInfoUtil;

/**
 * Author:wang_sir
 * Time:2018/11/1 17:58
 * Description:This is InnerRepairPresent
 */
public class InnerRepairPresent extends BasePresent<InnerRepairContract.IInnerRepairView> implements InnerRepairContract.iInnerRepairPresent {
    @Override
    public void getInnerRepairMenu(int propertyId, final String tag) {
        HttpProxy.getInstance()
                .params("propertyId",propertyId)
                .params("villageId", UserInfoUtil.getInstance().getVillageId())

                .postToNetwork(Contract.INNER_REPAIR_MENU,  new NetCallBackInterface() {
                    @Override
                    public void onSuccess(String content) {
                        if (getView() != null) {
                            getView().updateView(GsonManager.getInstance().parseJsonToBean(content, InnerRepairBean.class), tag);
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

    @Override
    public void saveInnerRepairInfo(int propertyId, int userId, String kind, String label, String position, String content, String imageUrl, final String tag) {

        HttpProxy.getInstance()
                .params("propertyId",propertyId)
                .params("userId",userId)
                .params("kind",kind)
                .params("label",label)
                .params("position",position)
                .params("content",content)
                .params("imageUrl",imageUrl)
                .params("villageId", UserInfoUtil.getInstance().getVillageId())

                .postToNetwork(Contract.SAVE_INNER_REPAIR,  new NetCallBackInterface() {
                    @Override
                    public void onSuccess(String content) {
                        if (getView() != null) {
                            getView().updateView("", tag);
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
