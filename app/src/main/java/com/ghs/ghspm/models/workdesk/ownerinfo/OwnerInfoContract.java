package com.ghs.ghspm.models.workdesk.ownerinfo;

import android.view.View;

import com.ghs.ghspm.base.BaseViewInterface;
import com.ghs.ghspm.base.network.RequestStatus;

 public interface OwnerInfoContract {

     String GET_TOWER_NAME = "get_tower_name";
     String GET_UNIT_NAME = "get_unit_name";
     String GET_ROOM_NAME = "get_room_name";
     String GET_WATER_BILL = "get_water_bill";
     String GET_PROPRETY = "get_proprety";
     String OWNER_MESSAGE = "owner_message";
     String OWNER_USER = "owner_user";
     String OWNER_HOME = "owner_home";
     String OWNER_TENANT = "owner_tenant";
     String OWNER_CAR = "owner_car";
     String OWNER_EMPTY = "owner_empty";
     String OWNET_TYPE_1 = "#1";//业主
     String OWNET_TYPE_2 = "#2";//家人
     String OWNET_TYPE_3 = "#3";//租客

     interface OwnerInfoModel {
        void getTower(String villageId, RequestStatus requestStatus, String tag);

        void getRation(String villageId, String rationId, RequestStatus requestStatus, String tag);

        void getRoom(String villageId, String cellid, RequestStatus requestStatus, String tag);

        void getOwnerMessage(String propertyId, String roomId, String roleType, RequestStatus requestStatus, String tag);

        void getWaterBill(String villageId, String roomId, String feeType, String startMonth, String endMonth, RequestStatus requestStatus, String tag);

        void getProPrety(String villageId, String roomId, RequestStatus requestStatus, String tag);

        //获取车辆信息
        void getCarMessage(String villagerId, String roomId, RequestStatus requestStatus,String tag);
    }

     interface OwnerInfoPresenter {
        void getTower(String villageId, String tag);

        void getRation(String villageId, String rationId, String tag);

        void getRoom(String villageId, String cellid, String tag);

        void getOwnerMessage(String propertyId, String roomId, String roleType, String tag);

        void getWaterBill(String villageId, String roomId, String feeType, String startMonth, String endMonth, String tag);

        void getProPrety(String villageId, String roomId, String tag);

        void getCarMessage(String villagerId,  String roomId,String tag);
    }


     interface OwnerInfoView extends BaseViewInterface {

    }

    //  侧栏点击事件
     interface LeftOnClickListenter {

        void OnClick(View view, int prostion);

    }


}
