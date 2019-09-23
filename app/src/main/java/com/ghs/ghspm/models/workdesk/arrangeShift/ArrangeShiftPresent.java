package com.ghs.ghspm.models.workdesk.arrangeShift;

import com.ghs.ghspm.base.BasePresent;
import com.ghs.ghspm.base.network.networkProxy.HttpProxy;
import com.ghs.ghspm.base.network.okgo.NetCallBackInterface;
import com.ghs.ghspm.bean.ArrangeShiftBean;
import com.ghs.ghspm.bean.ArrangeUserDetailBean;
import com.ghs.ghspm.tools.Contract;
import com.ghs.ghspm.tools.GsonManager;
import com.ghs.ghspm.tools.UserInfoUtil;

/**
 * Author:wang_sir
 * Time:2019/2/11 14:12
 * Description:This is ArrangeShiftPresent
 */
public class ArrangeShiftPresent  extends BasePresent<ArrangeShiftContact.IArrangeShiftView> implements ArrangeShiftContact.IArrangeShiftPresent {

    @Override
    public void getArrangeShiftCalendarData(int propertyId, int userId, int year, int month, final String tag) {
        HttpProxy.getInstance()
                .params("propertyId", propertyId)
                .params("userId", userId)
                .params("year", year)
                .params("month", month)
                .params("villageId", UserInfoUtil.getInstance().getVillageId())

                .postToNetwork(Contract.ARRANGE_CALENDAR,  new NetCallBackInterface() {
                    @Override
                    public void onSuccess(String content) {
                        if (getView() != null) {
                            getView().updateView(GsonManager.getInstance().parseJsonToBean(content, ArrangeShiftBean.class), tag);
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
    public void getArrangeUserDetail(int propertyId, int userId, String clockDay, final String tag) {

        HttpProxy.getInstance()
                .params("propertyId", propertyId)
                .params("userId", userId)
                .params("clockDay", clockDay)
                .params("villageId", UserInfoUtil.getInstance().getVillageId())

                .postToNetwork(Contract.ARRANGE_USER_DETAIL,  new NetCallBackInterface() {
                    @Override
                    public void onSuccess(String content) {
                        if (getView() != null) {
                            getView().updateView(GsonManager.getInstance().parseJsonToBean(content, ArrangeUserDetailBean.class), tag);
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
