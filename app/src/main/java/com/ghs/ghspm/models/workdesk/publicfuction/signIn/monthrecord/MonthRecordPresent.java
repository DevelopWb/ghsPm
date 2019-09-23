package com.ghs.ghspm.models.workdesk.publicfuction.signIn.monthrecord;

import com.ghs.ghspm.base.BasePresent;
import com.ghs.ghspm.base.network.networkProxy.HttpProxy;
import com.ghs.ghspm.base.network.okgo.NetCallBackInterface;
import com.ghs.ghspm.bean.ShiftRecordBean;
import com.ghs.ghspm.bean.SignStaticsBean;
import com.ghs.ghspm.tools.Contract;
import com.ghs.ghspm.tools.GsonManager;
import com.ghs.ghspm.tools.UserInfoUtil;

/**
 * Author:wang_sir
 * Time:2018/10/20 18:19
 * Description:This is MonthRecordPresent
 */
public class MonthRecordPresent extends BasePresent<MonthRecordContract.IMonthRecordView> implements MonthRecordContract.IMonthRecordPresent {

    @Override
    public void signStatics(int propertyId, int userId, int year, int month, final String tag) {
        HttpProxy.getInstance()
                .params("propertyId", propertyId)
                .params("userId", userId)
                .params("year", year)
                .params("month", month)
                .params("villageId", UserInfoUtil.getInstance().getVillageId())

                .postToNetwork(Contract.CHECK_WORK_SIGN_STATICS,  new NetCallBackInterface() {
                    @Override
                    public void onSuccess(String content) {
                        if (getView() != null) {
                            getView().updateView(GsonManager.getInstance().parseJsonToBean(content, SignStaticsBean.class), tag);
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
    public void shiftRecordEveryDay(int propertyId, int userId, String clockDay, final String tag) {
        HttpProxy.getInstance()
                .params("propertyId", propertyId)
                .params("userId", userId)
                .params("clockDay", clockDay)
                .params("villageId", UserInfoUtil.getInstance().getVillageId())

                .postToNetwork(Contract.SHIFT_RECORD_EVERYDAY,  new NetCallBackInterface() {
                    @Override
                    public void onSuccess(String content) {
                        if (getView() != null) {
                            getView().updateView(GsonManager.getInstance().parseJsonToBean(content, ShiftRecordBean.class), tag);
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
    public void signReplace(int ruleId, int userId, int clockId, int controlUser, String reason,final String tag) {
        HttpProxy.getInstance()
                .params("ruleId", ruleId)
                .params("userId", userId)
                .params("clockId", clockId)
                .params("controlUser", controlUser)
                .params("reason", reason)
                .postToNetwork(Contract.CHECK_WORK_SIGN_REPLACE,  new NetCallBackInterface() {
                    @Override
                    public void onSuccess(String content) {
                        if (getView() != null) {
                            getView().updateView("补卡申请已经提交",tag);
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
