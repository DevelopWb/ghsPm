package com.ghs.ghspm.models.workdesk.waterrecord;

import com.ghs.ghspm.base.BaseViewInterface;

/**
 * Author:wang_sir
 * Time:2018/10/24 10:55
 * Description:This is WaterRecordContract
 */
public interface WaterRecordContract {

    String  GET_EOOM_RECORD_LIST= "get_room_record_list";
    String  GET_EOOM_RECORD= "get_room_record";
    String  SAVE_WATER_RECORD= "save_water_record";
    interface  IWaterRecordView extends BaseViewInterface{}

    interface IWaterRecordPresent {

        void getRoomRecordList(int propertyId,String feeType,int cellId,String tag);
        void getRoomRecordDetail(String feeType,int roomId,String tag);
        void saveWaterRecord(int orderId,String feeType,int userId,int roomId,double watchNumber,String remark,String tag);

    }
}
