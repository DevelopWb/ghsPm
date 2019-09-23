package com.ghs.ghspm.models.mine.personalInfo.modifymobile;


import com.ghs.ghspm.base.BaseViewInterface;

/**
 * Author:wang_sir
 * Time:2018/7/16 18:47
 * Description:This is ModifyMobileContract
 */
public interface ModifyMobileContract {
    String MODIFY_MOBILE = "modify_mobile";//修改手机成功
    String MODIFY_MOBILE_FAILED = "modify_mobile_failed";//修改手机失败
    interface IModifyMobileView extends BaseViewInterface {
        void updateSendCheckCodeViewStatus(long second);
        void checkFormatError(String error);
    }

    interface IModifyMobilePresent {
        void sendCheckCode(String mobile);

        void checkCodeReceived();

        void commitModify(int userId, String mobile, String smsCode);
    }


}
