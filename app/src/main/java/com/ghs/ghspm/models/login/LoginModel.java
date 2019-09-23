package com.ghs.ghspm.models.login;


import com.ghs.ghspm.base.network.RequestStatus;
import com.ghs.ghspm.base.network.networkProxy.HttpProxy;
import com.ghs.ghspm.base.network.okgo.NetCallBackInterface;
import com.ghs.ghspm.bean.LoginBean;
import com.ghs.ghspm.bean.MenuBean;
import com.ghs.ghspm.bean.ToolFormBean;
import com.ghs.ghspm.tools.Contract;
import com.ghs.ghspm.tools.GsonManager;

/**
 * Author:wang_sir
 * Time:2018/7/9 17:19
 * Description:This is LoginModel
 */
public class LoginModel implements LoginContract.ILoginModel {
    @Override
    public void getCheckCode(String mobile, final RequestStatus requestStatus, String smsTYpe) {

        HttpProxy.getInstance()
                .params("mobile", mobile)
                .params("codeType", smsTYpe)
                .postToNetwork(Contract.GEI_SMS_CODE,   new NetCallBackInterface() {
                    @Override
                    public void onSuccess(String content) {
                        if (requestStatus != null) {
                            requestStatus.onSuccess("", LoginContract.CHECK_CODE);
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
    public void loginByTelNo(String mobile, String smsCode, final RequestStatus requestStatus) {
        if (requestStatus != null) {
            requestStatus.onStart(LoginContract.CHECK_CODE);
        }

        HttpProxy.getInstance()
                .params("mobile", mobile)
                .params("smsCode", smsCode)
                .postToNetwork(Contract.LOGIN_BY_MOBILE,  new NetCallBackInterface() {
                    @Override
                    public void onSuccess(String content) {
                        if (requestStatus != null) {
                            requestStatus.onSuccess(GsonManager.getInstance().parseJsonToBean(content, LoginBean.class), LoginContract.LOGIN_MOBILE);
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
    public void getUserMenu(int propertyId,int villageId,int userId, final String tag, final RequestStatus requestStatus) {
        HttpProxy.getInstance()
                .params("propertyId", propertyId)
                .params("userId", userId)
                .params("villageId", villageId)
                .postToNetwork(Contract.USER_MENU,  new NetCallBackInterface() {
                    @Override
                    public void onSuccess(String content) {
                        if (requestStatus != null) {
                            requestStatus.onSuccess(GsonManager.getInstance().parseJsonToBean(content, MenuBean.class), tag);
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
    public void getToolFormList(int propertyId,int villageId,int userId,final String tag, final RequestStatus requestStatus) {
        HttpProxy.getInstance()
                .params("propertyId", propertyId)
                .params("userId", userId)
                .params("villageId", villageId)
                .postToNetwork(Contract.GET_TOOL_FORM_LIST,   new NetCallBackInterface() {
                    @Override
                    public void onSuccess(String content) {
                        if (requestStatus != null) {
                            requestStatus.onSuccess(GsonManager.getInstance().parseJsonToBean(content, ToolFormBean.class), tag);
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
