package com.ghs.ghspm.models.workdesk;

import com.ghs.ghspm.base.BaseViewInterface;
import com.ghs.ghspm.base.network.RequestStatus;

/**
 * Author:wang_sir
 * Time:2018/6/29 10:10
 * Description:This is WorkDeskContract
 */
public interface WorkDeskContract {

    interface IHomeView extends BaseViewInterface {
    }

    interface IHomePresent {


    }

    interface IHomeModel {
        /**
         * 查看提醒状态
         *
         * @param villageId
         * @param roomId
         * @param userId
         * @param tag
         */
        void clickRedPoint(int villageId, int roomId, int userId, int module, RequestStatus requestStatus, String tag);

        void getUserAndRoomInfo(int userId, int userRoomId, RequestStatus requestStatus, String tag);

    }
}
