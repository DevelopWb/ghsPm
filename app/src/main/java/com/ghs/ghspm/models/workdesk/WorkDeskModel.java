package com.ghs.ghspm.models.workdesk;

import com.ghs.ghspm.base.network.RequestStatus;

/**
 * Author:wang_sir
 * Time:2018/8/7 14:17
 * Description:This is WorkDeskModel
 */
public class WorkDeskModel implements WorkDeskContract.IHomeModel {

    @Override
    public void clickRedPoint(int villageId, int roomId, int userId, int module, final RequestStatus requestStatus,final  String tag) {
    }

    @Override
    public void getUserAndRoomInfo(int userId, int userRoomId, final RequestStatus requestStatus, final String tag) {
}}
