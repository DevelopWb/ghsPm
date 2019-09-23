package com.ghs.ghspm.models.unread;

import com.ghs.ghspm.base.network.RequestStatus;
import com.ghs.ghspm.base.network.networkProxy.HttpProxy;
import com.ghs.ghspm.base.network.okgo.NetCallBackInterface;
import com.ghs.ghspm.bean.UnReadBean;
import com.ghs.ghspm.tools.Contract;
import com.ghs.ghspm.tools.GsonManager;
import com.ghs.ghspm.tools.PubUtil;
import com.ghs.ghspm.tools.UserInfoUtil;

/**
 * Author:wang_sir
 * Time:2018/12/13 15:27
 * Description:This is UnReadTagModel
 */
public class UnReadTagModel implements UnReadContract {
    @Override
    public void getUnReadAmount(int userId, final RequestStatus requestStatus, final String tag) {

        HttpProxy.getInstance()
                .params("userId", userId)
                .params("villageId", UserInfoUtil.getInstance().getVillageId())
                .getToNetwork(Contract.GET_UNREAD_AMOUNT,  new NetCallBackInterface() {
                    @Override
                    public void onSuccess(String content) {
                        if (requestStatus != null) {
                            requestStatus.onSuccess(GsonManager.getInstance().parseJsonToBean(content, UnReadBean.class), tag);
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

    @Override
    public void clearUnReadAmount(int userId, final RequestStatus requestStatus, final String tag) {

        HttpProxy.getInstance()
                .params("userId", userId)
                .params("villageId", UserInfoUtil.getInstance().getVillageId())
                .getToNetwork(Contract.CLEAR_UNREAD_AMOUNT,  new NetCallBackInterface() {
                    @Override
                    public void onSuccess(String content) {
                        if (requestStatus != null) {
                            requestStatus.onSuccess("", tag);
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

    @Override
    public void getUnreadSysNoticeAmount(int userId,final RequestStatus requestStatus, final String tag) {
        HttpProxy.getInstance()
                .params("userId", userId)

                .getToNetwork(Contract.GET_SYS_NOTICE_UNREAD,  new NetCallBackInterface() {
                    @Override
                    public void onSuccess(String content) {
                        if (requestStatus != null) {
                            requestStatus.onSuccess(PubUtil.getServerData(content), tag);

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

    @Override
    public void rvUnreadSysNoticeAmount(int userId, final RequestStatus requestStatus, final String tag) {


        HttpProxy.getInstance()
                .params("userId", userId)

                .getToNetwork(Contract.RV_SYS_NOTICE_UNREAD,  new NetCallBackInterface() {
                    @Override
                    public void onSuccess(String content) {
                        if (requestStatus != null) {
                            requestStatus.onSuccess("", tag);

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
