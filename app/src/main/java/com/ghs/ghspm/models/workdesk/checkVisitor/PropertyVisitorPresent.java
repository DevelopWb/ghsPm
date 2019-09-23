package com.ghs.ghspm.models.workdesk.checkVisitor;

import com.ghs.ghspm.base.BasePresent;
import com.ghs.ghspm.base.network.networkProxy.HttpProxy;
import com.ghs.ghspm.base.network.okgo.NetCallBackInterface;
import com.ghs.ghspm.bean.PropertyVisitorListBean;
import com.ghs.ghspm.tools.Contract;
import com.ghs.ghspm.tools.GsonManager;
import com.ghs.ghspm.tools.PubUtil;

/**
 * created by guohainan
 * created date 2019/8/23
 * application
 * package name com.ghs.ghspm.models.workdesk.propertyrecord
 */
public class PropertyVisitorPresent extends BasePresent<PropertyVisitorContract.PropertyRecordView> implements PropertyVisitorContract.ProperRecordPresent {


    @Override
    public void getPropertyVisitorRecodList(int villageId, int offset, int limit, int state, String tag) {
        HttpProxy.getInstance()
                .params("villageId", villageId)
                .params("offset", offset)
                .params("limit", limit)
                .params("state", state)
                .postToNetwork(Contract.PROPERTY_RECORD_LIST, new NetCallBackInterface() {
                    @Override
                    public void onSuccess(String content) {
                        if (getView() != null) {
                            getView().updateView(GsonManager.getInstance().parseJsonToBean(content, PropertyVisitorListBean.class), tag);
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
    public void getCheckAgree(int visitorId, int pmUserId, String tag) {

        HttpProxy.getInstance()
                .params("visitorId", visitorId)
                .params("pmUserId", pmUserId)
                .postToNetwork(Contract.PROPERTY_RECORD_CHECKAGREE, new NetCallBackInterface() {
                    @Override
                    public void onSuccess(String content) {
                        if (getView() != null) {
                            getView().updateView(GsonManager.getInstance().parseJsonToBean(PubUtil.getServerData(content), PropertyVisitorListBean.DataBean.class), tag);
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
    public void getCheckReject(int visitorId, int pmUserId, String tag) {


        HttpProxy.getInstance()
                .params("visitorId", visitorId)
                .params("pmUserId", pmUserId)
                .postToNetwork(Contract.PROPERTY_RECORD_CHECKREJECT, new NetCallBackInterface() {
                    @Override
                    public void onSuccess(String content) {
                        if (getView() != null) {
                            getView().updateView(GsonManager.getInstance().parseJsonToBean(PubUtil.getServerData(content), PropertyVisitorListBean.DataBean.class), tag);
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
