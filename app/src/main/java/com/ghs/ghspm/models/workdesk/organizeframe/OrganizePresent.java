package com.ghs.ghspm.models.workdesk.organizeframe;

import com.ghs.ghspm.base.BasePresent;
import com.ghs.ghspm.base.network.networkProxy.HttpProxy;
import com.ghs.ghspm.base.network.okgo.NetCallBackInterface;
import com.ghs.ghspm.bean.RoleBean;
import com.ghs.ghspm.bean.UsersFromRoleBean;
import com.ghs.ghspm.tools.Contract;
import com.ghs.ghspm.tools.GsonManager;

/**
 * Author:wang_sir
 * Time:2018/10/11 10:59
 * Description:This is OrganizePresent
 */
public class OrganizePresent extends BasePresent<OrganizeContact.IOrganizeView> implements OrganizeContact.IOrganizePresent {


    @Override
    public void getRoleList(int villageId, final String tag) {
        HttpProxy.getInstance()
                .params("villageId", villageId)
                .postToNetwork(Contract.GET_ROLE_LIST,  new NetCallBackInterface() {
                    @Override
                    public void onSuccess(String content) {
                        if (getView() != null) {
                            getView().updateView(GsonManager.getInstance().parseJsonToBean(content, RoleBean.class), tag);
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
    public void getUsersFromRoleList(int roleId,final  String tag) {
        HttpProxy.getInstance()
                .params("roleId", roleId)
                .postToNetwork(Contract.GET_USERS_FROM_ROLE_LIST,  new NetCallBackInterface() {
                    @Override
                    public void onSuccess(String content) {
                        if (getView() != null) {
                            getView().updateView(GsonManager.getInstance().parseJsonToBean(content, UsersFromRoleBean.class), tag);
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
    public void searchUsersFromRoleList(int villageId, String searchKey, final String tag) {
        HttpProxy.getInstance()
                .params("villageId", villageId)
                .params("searchKey", searchKey)
                .postToNetwork(Contract.SEARCH_USERS_FROM_ROLE_LIST,  new NetCallBackInterface() {
                    @Override
                    public void onSuccess(String content) {
                        if (getView() != null) {
                            getView().updateView(GsonManager.getInstance().parseJsonToBean(content, UsersFromRoleBean.class), tag);
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
