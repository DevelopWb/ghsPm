package com.ghs.ghspm.models.login;

import android.text.TextUtils;

import com.ghs.ghspm.base.BasePresent;
import com.ghs.ghspm.base.network.RequestStatus;
import com.ghs.ghspm.base.network.networkProxy.HttpProxy;
import com.ghs.ghspm.base.network.okgo.NetCallBackInterface;
import com.ghs.ghspm.bean.LoginBean;
import com.ghs.ghspm.bean.MenuBean;
import com.ghs.ghspm.bean.PropertiesBean;
import com.ghs.ghspm.models.login.sendcode.ISendCode;
import com.ghs.ghspm.models.login.sendcode.SendCodeModel;
import com.ghs.ghspm.tools.Contract;
import com.ghs.ghspm.tools.GsonManager;
import com.ghs.ghspm.tools.PubUtil;


/**
 * Author:wang_sir
 * Time:2018/7/9 17:18
 * Description:This is LoginPresent
 */
public class LoginPresent extends BasePresent<LoginContract.ILoginView> implements LoginContract.ILoginPresent, RequestStatus, ISendCode.IUpdateView {

    private final LoginModel model;
    private final SendCodeModel sendCodeModel;
    private boolean run = false;

    public LoginPresent() {
        model = new LoginModel();
        sendCodeModel = new SendCodeModel(this);
    }

    @Override
    public void onStart(String tag) {
        if (getView() != null) {
            getView().startLoading(tag);
        }
    }

    @Override
    public void onSuccess(Object o, String tag) {
        if (getView() != null) {
            getView().updateView(o, tag);
        }

    }

    @Override
    public void onError(String tag) {
        if (getView() != null) {
            getView().onError(tag);
        }

    }

    @Override
    public void sendCheckCode(String mobile) {

        if (checkMobile(mobile)) {
            sendCodeModel.initGetTestCodeButtonStatus();
            model.getCheckCode(mobile, this, Contract.SMS_CODE_TYPE_LOGIN);
        }
    }

    @Override
    public void loginByTelNo(String mobile, String code) {
        if (checkMobile(mobile)) {
            if (code != null && !TextUtils.isEmpty(code)) {
                model.loginByTelNo(mobile, code, this);
            } else {
                if (getView() != null) {
                    getView().checkFormatError("验证码为空");
                }
            }
        }

    }

    @Override
    public void checkCodeReceived() {
        sendCodeModel.checkCodeReceived();
    }

    @Override
    public void getUserMenu(int propertyId,int villageId,int userId, final String tag) {
        HttpProxy.getInstance()
                .params("propertyId", propertyId)
                .params("userId", userId)
                .params("villageId", villageId)
                .postToNetwork(Contract.USER_MENU,  new NetCallBackInterface() {
                    @Override
                    public void onSuccess(String content) {
                        if (getView() != null) {
                            getView().updateView(GsonManager.getInstance().parseJsonToBean(content, MenuBean.class), tag);
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
    public void getToolFormList(String tag) {

    }

    @Override
    public void getPropertyList(int userId, final String tag) {
        HttpProxy.getInstance()
                .params("userId", userId)
                .postToNetwork(Contract.GET_PROPERTY_LIST,  new NetCallBackInterface() {
                    @Override
                    public void onSuccess(String content) {
                        if (getView() != null) {
                            getView().updateView(GsonManager.getInstance().parseJsonToBean(content, PropertiesBean.class), tag);
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
    public void selectProperty(int userId, int propertyId, final String tag) {
        HttpProxy.getInstance()
                .params("userId", userId)
                .params("propertyId", propertyId)
                .postToNetwork(Contract.UPLOAD_SELECTED_PROPERTY,  new NetCallBackInterface() {
                    @Override
                    public void onSuccess(String content) {
                        if (getView() != null) {
                            getView().updateView(GsonManager.getInstance().parseJsonToBean(content, LoginBean.class), tag);
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

    /**
     * 检查手机号的格式
     */
    private boolean checkMobile(String mobile) {
        if (mobile == null || TextUtils.isEmpty(mobile)) {
            if (getView() != null) {
                getView().checkFormatError("手机号没有填写");
            }

            return false;
        }
        if (!PubUtil.isMobileNO(mobile)) {
            if (getView() != null) {
                getView().checkFormatError("手机号格式错误");

            }
            return false;
        }
        return true;

    }


    @Override
    public void startTiming(long value) {
        if (getView() != null) {
            getView().updateSendCheckCodeViewStatus(value);
        }

    }

    @Override
    public void endTiming(long value) {
        if (getView() != null) {
            getView().updateSendCheckCodeViewStatus(value);
        }

    }
}
