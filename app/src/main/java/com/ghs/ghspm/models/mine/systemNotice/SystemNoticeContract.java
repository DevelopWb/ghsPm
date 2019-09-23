package com.ghs.ghspm.models.mine.systemNotice;

import com.ghs.ghspm.base.BaseViewInterface;

/**
 * Author:wang_sir
 * Time:2019/2/21 14:46
 * Description:This is SystemNoticeContract
 */
public interface SystemNoticeContract {
    String GET_SYS_NOTICE = "/systemMsg/msgList";
    String GET_SYS_NOTICE_UNREAD = "/systemMsg/getUnreadNum";
    String RV_SYS_NOTICE_UNREAD = "/systemMsg/removeUnreadNum";

    interface ISystemNoticeView extends BaseViewInterface {
    }

    interface ISystemNoticeP {
        void getNoticeList(int userId, String tag);

    }

}
