package com.ghs.ghspm.models.workdesk.organizeframe;

import com.ghs.ghspm.base.BaseViewInterface;

/**
 * Author:wang_sir
 * Time:2018/10/11 10:54
 * Description:This is OrganizeContact
 */
public interface OrganizeContact {
    String GET_ORGANIZE_MSG = "get_organize_msg";
    String GET_ROLE_LIST = "/dept/getRoleByVillage";
    String GET_USERS_FROM_ROLE_LIST = "/dept/getUserByRole";
    interface IOrganizeView extends BaseViewInterface {
    }

    interface IOrganizePresent {

        void getRoleList(int villageId,String tag);
        void getUsersFromRoleList(int roleId,String tag);
        void searchUsersFromRoleList(int villageId,String searchKey,String tag);
    }

}
