package com.ghs.ghspm.models.main;

import com.ghs.ghspm.base.BaseViewInterface;
import com.ghs.ghspm.base.network.RequestStatus;

/**
 * Author:wang_sir
 * Time:2018/7/10 18:54
 * Description:This is MainContact
 */
public interface MainContact {
    String USER_MENU = "user_menu";
    String GET_USER_PROPERTY_RELATION = "/userCenter/getUserDeptInfo";
    String GET_VILLAGE_LIST = "/userCenter/villageList";
    String CHANGE_VILLAGE = "changeVillage";
    String BLUE_MAC_ADDR = "blue_mac_addr";//蓝牙地址列表
    String OPEN_BY_NET = "open_by_net";//网络开门


    interface IMainView extends BaseViewInterface {
        void onError(String tag, String errorCode);
    }


    interface IMainPresent {

        void getVillagesList(int userId, int propertyId, String tag);//获取小区列表

        void getUserPropertyRelation(int userId, int propertyId, int villageId, String tag);//选择小区后 获取关系表



    }

    interface IMainModel {
        /**
         * 获取蓝牙列表
         *
         * @param villageId
         * @param cellId
         */
        void getBlueMacList(int villageId, int cellId, RequestStatus requestStatus, String tag);


        /**
         * 网络开门
         *
         * @param deviceId
         * @param userRoomId
         */
        void openDoorByNet(int deviceId, int userRoomId, RequestStatus requestStatus);

    }

    interface DrawerLayoutCallBack {
        void openDrawerLayout();
    }

}
