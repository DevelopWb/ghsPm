package com.ghs.ghspm.models.main;

import com.ghs.ghspm.base.BasePresent;
import com.ghs.ghspm.base.network.RequestStatus;
import com.ghs.ghspm.base.network.networkProxy.HttpProxy;
import com.ghs.ghspm.base.network.okgo.NetCallBackInterface;
import com.ghs.ghspm.bean.LoginBean;
import com.ghs.ghspm.bean.VillagesBean;
import com.ghs.ghspm.tools.Contract;
import com.ghs.ghspm.tools.GsonManager;

/**
 * Author:wang_sir
 * Time:2018/7/10 19:40
 * Description:This is MainPresent
 */
public  class MainPresent extends BasePresent<MainContact.IMainView> implements RequestStatus, MainContact.IMainPresent {

    private final MainModel mainModel;

    public MainPresent() {
        mainModel = new MainModel();
    }



    @Override
    public void onStart(String tag) {

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
    public void getVillagesList(int userId, int propertyId, final String tag) {
        HttpProxy.getInstance()
                .params("propertyId", propertyId)
                .params("userId", userId)
                .postToNetwork(Contract.GET_VILLAGE_LIST,  new NetCallBackInterface() {
                    @Override
                    public void onSuccess(String content) {
                        if (getView() != null) {
                            getView().updateView(GsonManager.getInstance().parseJsonToBean(content, VillagesBean.class), tag);
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
    public void getUserPropertyRelation(int userId, int propertyId, int villageId,final  String tag) {
        HttpProxy.getInstance()
                .params("propertyId", propertyId)
                .params("userId", userId)
                .params("villageId", villageId)
                .postToNetwork(Contract.GET_USER_PROPERTY_RELATION, true, new NetCallBackInterface() {
                    @Override
                    public void onSuccess(String content) {
                        if (getView() != null) {
                            getView().updateView(GsonManager.getInstance().parseJsonToBean(content, LoginBean.class), tag);
                        }
                    }

                    @Override
                    public void onError(String str) {
                        if (getView() != null) {
                            getView().onError(tag,str);
                        }
                    }
                });
    }


}
