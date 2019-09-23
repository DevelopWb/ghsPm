package com.ghs.ghspm.models.login;


import com.ghs.ghspm.base.BaseViewInterface;
import com.ghs.ghspm.base.network.RequestStatus;

/**
 * Author:wang_sir
 * Time:2018/7/9 17:18
 * Description:This is LoginContract
 */
public interface LoginContract {
    String  GET_PROPERTY_LIST ="get_property_list";
    String  UPLOAD_SELECTED_PROPERTY = "/userCenter/setProperty";
    String CHECK_CODE = "check_code";//验证码
    String LOGIN_MOBILE = "login_mobile";//手机登录
    String GET_TOOL_FORM_LIST = "get_tool_form_list";//手机登录

    interface ILoginView extends BaseViewInterface {
        void updateSendCheckCodeViewStatus(long second);

        void checkFormatError(String error);
    }

    interface ILoginPresent {
        void sendCheckCode(String mobile);

        void loginByTelNo(String mobile, String smsCode);

        void checkCodeReceived();
        void getUserMenu(int propertyId,int villageId,int userId,  String tag);
        void getToolFormList(String tag);
        void  getPropertyList(int userId,String tag);
        void  selectProperty(int userId,int propertyId,String tag);//选择物业
    }

    interface ILoginModel {
        void getCheckCode(String mobile, RequestStatus requestStatus,String smsType);

        void loginByTelNo(String mobile, String smsCode, RequestStatus requestStatus);
        void getUserMenu(int propertyId,int villageId,int userId,  String tag,RequestStatus requestStatus);
        void getToolFormList(int propertyId,int villageId,int userId,String tag,RequestStatus requestStatus);

    }


}
