package com.ghs.ghspm.models.workdesk.publicfuction.universalkey;

import com.ghs.ghspm.base.BaseViewInterface;
import com.ghs.ghspm.base.network.RequestStatus;

/**
 * Author:wang_sir
 * Time:2018/9/27 14:43
 * Description:This is UniversialKeyContract
 */
public interface UniversialKeyContract {

    String GET_TOWER_NO = "get_tower_no";
    String GET_CELL_NO = "get_cell_no";
    String GET_ROOM_NO = "get_room_no";
    String GET_DOOR_NO = "get_door_no";
    String OPEN_BY_CELL = "open_by_cell";
    String OPEN_BY_DEVICE_NO = "open_by_device_no";
    interface IUniversialKeyView extends BaseViewInterface{}

    interface IUniversialKeyPresent {
        void getTowerNo(int villageId,String tag);
        void getCellNo(int villageId,int towerId,String tag);
        void getDoorNo(String villageId,String tag);
        void openByCell(String cellId,String pmUserId,String tag);
        void openByDeviceId(String deviceId,String pmUserId,String tag);
    }

    interface IUniversialKeyModel{
        void getTowerNo(int villageId, RequestStatus requestStatus,String tag);
        void getCellNo(int villageId,int towerId, RequestStatus requestStatus,String tag);
        void getRoomNo(int villageId,int cellId, RequestStatus requestStatus,String tag);
        void getDoorNo(String villageId, RequestStatus requestStatus,String tag);
        void openByCell(String cellId,String pmUserId, RequestStatus requestStatus,String tag);
        void openByDeviceId(String deviceId,String pmUserId, RequestStatus requestStatus,String tag);
    }
}
