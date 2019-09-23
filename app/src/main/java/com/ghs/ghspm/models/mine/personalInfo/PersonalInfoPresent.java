package com.ghs.ghspm.models.mine.personalInfo;

import com.ghs.ghspm.base.BasePresent;
import com.ghs.ghspm.base.network.RequestStatus;
import com.ghs.ghspm.base.network.networkProxy.HttpProxy;
import com.ghs.ghspm.base.network.okgo.NetCallBackInterface;
import com.ghs.ghspm.tools.Contract;
import com.ghs.ghspm.tools.UserInfoUtil;

/**
 * Author:wang_sir
 * Time:2018/10/13 15:15
 * Description:This is PersonalInfoPresent
 */
public class PersonalInfoPresent extends BasePresent<PersonalInfoContract.IUserInfoView> implements PersonalInfoContract.IUserInfoPresent ,RequestStatus{

    private final PersonalInfoModel userInfoModel;

    public PersonalInfoPresent() {

        userInfoModel = new PersonalInfoModel();
    }

    @Override
    public void getUserInfoDetail(int userId, String tag) {
        userInfoModel.getUserInfoDetail(UserInfoUtil.getInstance().getPropertyId(),UserInfoUtil.getInstance().getVillageId(),userId,tag,this);
    }

    @Override
    public void modifyUserHeadPic(int userId, String headImage, final String tag) {

        HttpProxy.getInstance()
                .params("userId", userId)
                .params("headImage", headImage)
                .postToNetwork(Contract.MODIFY_USER_PIC,  new NetCallBackInterface() {
                    @Override
                    public void onSuccess(String content) {
                        if (getView() != null) {
                            getView().updateView("更改成功",tag);
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
    public void onStart(String tag) {

    }

    @Override
    public void onSuccess(Object o, String tag) {
        if (getView() != null) {
            getView().updateView(o,tag);
        }
    }

    @Override
    public void onError(String tag) {
        if (getView() != null) {
            getView().onError(tag);
        }
    }
}
