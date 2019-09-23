package com.ghs.ghspm.models.workdesk.publicfuction.signIn;

import com.ghs.ghspm.base.BasePresent;
import com.ghs.ghspm.base.network.networkProxy.HttpProxy;
import com.ghs.ghspm.base.network.okgo.NetCallBackInterface;
import com.ghs.ghspm.bean.AttendanceRuleBean;
import com.ghs.ghspm.bean.PrepareFillCardBean;
import com.ghs.ghspm.bean.UserCardInfo;
import com.ghs.ghspm.tools.Contract;
import com.ghs.ghspm.tools.GsonManager;
import com.ghs.ghspm.tools.PubUtil;
import com.ghs.ghspm.tools.UserInfoUtil;

/**
 * Author:wang_sir
 * Time:2018/10/19 14:53
 * Description:This is SignInPresent
 */
public class SignInPresent extends BasePresent<SignInContract.ISignInView> implements SignInContract.ISignInPresent {
    @Override
    public void clock(int propertyId, int userId, int outside, final String clockPlace, final  int clockType, final String tag) {
        HttpProxy.getInstance()
                .params("propertyId", propertyId)
                .params("userId", userId)
                .params("outside", outside)
                .params("clockPlace", clockPlace)
                .params("villageId", UserInfoUtil.getInstance().getVillageId())

                .postToNetwork(Contract.CHECK_WORK_SIGN,  new NetCallBackInterface() {
                    @Override
                    public void onSuccess(String content) {
                        if (getView() != null) {
                            getView().updateView(PubUtil.getServerData(content)+","+clockType, tag);

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
    public void checkWorkGetUserInfo(int propertyId, int userId, final String tag) {

        HttpProxy.getInstance()
                .params("propertyId", propertyId)
                .params("userId", userId)
                .params("villageId", UserInfoUtil.getInstance().getVillageId())

                .postToNetwork(Contract.CHECK_WORK_GET_USER_INFO,  new NetCallBackInterface() {
                    @Override
                    public void onSuccess(String content) {
                        if (getView() != null) {
                            getView().updateView(GsonManager.getInstance().parseJsonToBean(content, UserCardInfo.class), tag);
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
    public void updateClock(int outside, String clockPlace, int clockId, final String tag) {
        HttpProxy.getInstance()
                .params("outside", outside)
                .params("clockPlace", clockPlace)
                .params("clockId", clockId)
                .postToNetwork(Contract.UPDATE_CLOCK,  new NetCallBackInterface() {
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

    @Override
    public void signReplace(int ruleId, int userId, int clockId, int controlUser, String reason, final String tag) {
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
                            getView().updateView("补卡申请已经提交", tag);
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
    public void getAttendanceRules(int propertyId, int userId, final String tag) {
        HttpProxy.getInstance()
                .params("propertyId", propertyId)
                .params("userId", userId)
                .params("villageId", UserInfoUtil.getInstance().getVillageId())

                .postToNetwork(Contract.ATTENDANCE_RULES,  new NetCallBackInterface() {
                    @Override
                    public void onSuccess(String content) {
                        if (getView() != null) {
                            getView().updateView(GsonManager.getInstance().parseJsonToBean(content, AttendanceRuleBean.class), tag);
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
    public void getReplaceClock(int propertyId, int userId, int clockId, final String tag) {
        HttpProxy.getInstance()
                .params("propertyId", propertyId)
                .params("userId", userId)
                .params("clockId", clockId)
                .params("villageId", UserInfoUtil.getInstance().getVillageId())

                .postToNetwork(Contract.READY_FILLCARD,  new NetCallBackInterface() {
                    @Override
                    public void onSuccess(String content) {
                        if (getView() != null) {
                            getView().updateView(GsonManager.getInstance().parseJsonToBean(content, PrepareFillCardBean.class), tag);
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
    public void getReplaceClocked(int propertyId, int userId, int clockId, String replaceTime, String reason, final String tag) {
        HttpProxy.getInstance()
                .params("propertyId", propertyId)
                .params("userId", userId)
                .params("clockId", clockId)
                .params("replaceTime", replaceTime)
                .params("reason", reason)
                .params("villageId", UserInfoUtil.getInstance().getVillageId())

                .postToNetwork(Contract.REPLACE_CLOCK,  new NetCallBackInterface() {
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
