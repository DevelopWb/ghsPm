package com.ghs.ghspm.models.workdesk.carnum;

import com.ghs.ghspm.base.BaseViewInterface;

/**
 * Author:wang_sir
 * Time:2018/10/23 15:04
 * Description:This is CarContract
 */
public interface CarContract {
    String CAR_INFO = "car_info";
    String SAVE_CAR_ERRO_INFO = "save_car_erro_info";

    interface ICarView extends BaseViewInterface{}

    interface ICarPresent {
        void getCarInfo(int propertyId,int villageId,String carNum,String tag);
        void saveCarErrorInfo(int villageId,int propertyId,int pmUserId,String carNum,String content,String imageUrl,String tag);
    }
}
