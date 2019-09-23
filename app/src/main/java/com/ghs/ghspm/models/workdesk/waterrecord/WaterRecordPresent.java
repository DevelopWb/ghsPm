package com.ghs.ghspm.models.workdesk.waterrecord;

import com.ghs.ghspm.base.BasePresent;
import com.ghs.ghspm.base.network.networkProxy.HttpProxy;
import com.ghs.ghspm.base.network.okgo.NetCallBackInterface;
import com.ghs.ghspm.bean.WaterRecordBean;
import com.ghs.ghspm.tools.Contract;
import com.ghs.ghspm.tools.GsonManager;
import com.ghs.ghspm.tools.PubUtil;

/**
 * Author:wang_sir
 * Time:2018/10/24 11:05
 * Description:This is WaterRecordPresent
 */
public class WaterRecordPresent extends BasePresent<WaterRecordContract.IWaterRecordView> implements WaterRecordContract.IWaterRecordPresent {
    @Override
    public void getRoomRecordList(int propertyId, String feeType, int cellId, final String tag) {
        HttpProxy.getInstance()
                .params("propertyId", propertyId)
                .params("feeType", feeType)
                .params("cellId", cellId)
                .postToNetwork(Contract.GET_EOOM_RECORD_LIST,  new NetCallBackInterface() {
                    @Override
                    public void onSuccess(String content) {
                        if (getView() != null) {
                            getView().updateView(GsonManager.getInstance().parseJsonToBean(content, WaterRecordBean.class), tag);
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
    public void getRoomRecordDetail(String feeType, int roomId,final  String tag) {
        HttpProxy.getInstance()
                .params("feeType", feeType)
                .params("roomId", roomId)
                .postToNetwork(Contract.GET_EOOM_RECORD,  new NetCallBackInterface() {
                    @Override
                    public void onSuccess(String content) {
                        if (getView() != null) {
                            getView().updateView(GsonManager.getInstance().parseJsonToBean(PubUtil.getServerData(content), WaterRecordBean.DataBean.class), tag);

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
    public void saveWaterRecord(int orderId, String feeType, int userId, int roomId, double watchNumber, String remark, final String tag) {
        HttpProxy.getInstance()
                .params("orderId", orderId)
                .params("feeType", feeType)
                .params("userId", userId)
                .params("roomId", roomId)
                .params("watchNumber", watchNumber)
                .params("remark", remark)
                .postToNetwork(Contract.SAVE_WATER_RECORD,  new NetCallBackInterface() {
                    @Override
                    public void onSuccess(String content) {
                        if (getView() != null) {
                            getView().updateView("提交成功", tag);
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
