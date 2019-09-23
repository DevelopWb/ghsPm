package com.ghs.ghspm.models.mine.personalInfo;

import com.ghs.ghspm.base.BaseViewInterface;
import com.ghs.ghspm.base.network.RequestStatus;

/**
 * Author:wang_sir
 * Time:2018/10/13 15:11
 * Description:This is PersonalInfoContract
 */
public interface PersonalInfoContract {
    String MODIFY_HEAD_PIC = "modify_head_pic";
    String GET_USER_INFO_DETAIL = "get_user_info_detail";

    interface IUserInfoView extends BaseViewInterface {
    }

    interface IUserInfoPresent {
        void getUserInfoDetail(int userId, String tag);

        void modifyUserHeadPic(int userId, String headImage, String tag);

    }

    interface IUserInfoModel {
        void getUserInfoDetail(int propertyId,int villageId,int userId, String tag, RequestStatus requestStatus);

    }
}
