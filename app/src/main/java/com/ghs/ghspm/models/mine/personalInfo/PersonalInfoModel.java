package com.ghs.ghspm.models.mine.personalInfo;

import android.util.Log;

import com.ghs.ghspm.base.network.RequestStatus;
import com.ghs.ghspm.base.network.networkProxy.HttpProxy;
import com.ghs.ghspm.base.network.okgo.NetCallBackInterface;
import com.ghs.ghspm.bean.UserInfoBean;
import com.ghs.ghspm.tools.Contract;
import com.ghs.ghspm.tools.GsonManager;

/**
 * Author:wang_sir
 * Time:2018/10/13 15:26
 * Description:This is PersonalInfoModel
 */
public class PersonalInfoModel implements PersonalInfoContract.IUserInfoModel {
    @Override
    public void getUserInfoDetail(int propertyId,int villageId,int userId, final String tag, final RequestStatus requestStatus) {

        HttpProxy.getInstance()
                .params("propertyId", propertyId)
                .params("userId", userId)
                .params("villageId", villageId)
                .postToNetwork(Contract.GET_USER_INFO_DETAIL,  new NetCallBackInterface() {
                    @Override
                    public void onSuccess(String content) {
                        Log.i("TCG",content);
                        if (requestStatus != null) {
                            requestStatus.onSuccess(GsonManager.getInstance().parseJsonToBean(content, UserInfoBean.class),tag);
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
