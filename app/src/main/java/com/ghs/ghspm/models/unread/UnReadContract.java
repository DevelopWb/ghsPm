package com.ghs.ghspm.models.unread;

import com.ghs.ghspm.base.network.RequestStatus;

/**
 * Author:wang_sir
 * Time:2018/12/13 15:28
 * Description:This is UnReadContract
 */
public interface UnReadContract {

    String  GET_UNREAD_AMOUNT = "unread_amount";
    String  CLEAR_UNREAD_AMOUNT = "clear_unread_amount";
    /**
     * 获取未读数量
     * @param userId
     * @param requestStatus
     * @param tag
     */
    void  getUnReadAmount(int userId, RequestStatus requestStatus,String tag);
    /**
     * 清除未读数量
     * @param userId
     * @param requestStatus
     * @param tag
     */
    void  clearUnReadAmount(int userId, RequestStatus requestStatus,String tag);


    void getUnreadSysNoticeAmount(int userId, RequestStatus requestStatus, String tag);

    void rvUnreadSysNoticeAmount(int userId, RequestStatus requestStatus, String tag);
}
