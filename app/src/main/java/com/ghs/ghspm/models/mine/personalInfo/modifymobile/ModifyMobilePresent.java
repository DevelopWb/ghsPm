package com.ghs.ghspm.models.mine.personalInfo.modifymobile;

import android.text.TextUtils;

import com.ghs.ghspm.base.BasePresent;
import com.ghs.ghspm.base.network.RequestStatus;
import com.ghs.ghspm.base.network.networkProxy.HttpProxy;
import com.ghs.ghspm.base.network.okgo.NetCallBackInterface;
import com.ghs.ghspm.models.login.LoginModel;
import com.ghs.ghspm.models.login.sendcode.ISendCode;
import com.ghs.ghspm.models.login.sendcode.SendCodeModel;
import com.ghs.ghspm.tools.Contract;
import com.ghs.ghspm.tools.PubUtil;

/**
 * Author:wang_sir
 * Time:2018/7/16 18:54
 * Description:This is ModifyMobilePresent
 */
public class ModifyMobilePresent extends BasePresent<ModifyMobileContract.IModifyMobileView> implements ModifyMobileContract.IModifyMobilePresent, ISendCode.IUpdateView, RequestStatus {

    private final SendCodeModel sendCodeModel;

    public ModifyMobilePresent() {
        sendCodeModel = new SendCodeModel(this);
    }



    /**
     * 检查手机号的格式
     */
    private boolean checkMobile(String mobile) {
        if (mobile == null || TextUtils.isEmpty(mobile)) {
            if (getView() != null) {
                getView().checkFormatError("请输入手机号");
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
    public void sendCheckCode(String mobile) {
        if (checkMobile(mobile)) {
            sendCodeModel.initGetTestCodeButtonStatus();
            new LoginModel().getCheckCode(mobile, this, Contract.SMS_CODE_TYPE_MODIFY_MOBILE);
        }

    }


    @Override
    public void checkCodeReceived() {
        sendCodeModel.checkCodeReceived();
    }

    @Override
    public void commitModify(int userId, String mobile, String smsCode) {
        if (checkMobile(mobile)) {
            if (smsCode != null && !TextUtils.isEmpty(smsCode)) {
                HttpProxy.getInstance()
                        .params("userId",userId)
                        .params("mobile", mobile)
                        .params("smsCode", smsCode)
                        .postToNetwork(Contract.MODIFY_USER_MOBILE,  new NetCallBackInterface() {
                            @Override
                            public void onSuccess(String content) {
                                if (getView() != null) {
                                    getView().updateView(PubUtil.getServerData(content), ModifyMobileContract.MODIFY_MOBILE);
                                }
                            }

                            @Override
                            public void onError(String str) {
                                if (getView() != null) {
                                    getView().onError(str);

                                }
                            }
                        });


            } else {
                if (getView() != null) {
                    getView().checkFormatError("验证码为空");
                }

            }

        }
    }

    @Override
    public void onStart(String tag) {

    }

    @Override
    public void onSuccess(Object o, String tag) {
        String ob = (String) o;
        if (getView() != null) {
            getView().updateView(ob, tag);
        }
    }

    @Override
    public void onError(String tag) {
        if (getView() != null) {
            getView().onError(tag);
        }
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
