package com.ghs.ghspm.models.workdesk.publicfuction.notice;

import com.ghs.ghspm.base.BaseViewInterface;

/**
 * Author:wang_sir
 * Time:2018/9/29 11:09
 * Description:This is NoticeContract
 */
public interface NoticeContract {


    String NORMAL_NOTICE_LIST = "normal_notice_list";
    String INSTITUTION_LIST = "institution_list";
    String VILLIAGE_NOTICE_LIST = "villiage_notice_list";
    String NOTICE_DETAIL_INFO = "notice_detail_info";
    String PULISH_NOTICE = "publish_notice";//发送消息

    interface INoticeView extends BaseViewInterface {
    }

    interface INoticePresent {
        void getNoticeList(int offset, int limit,int type, int villageId, int pmUserId, String tag);

        void getNoticeInfo(int pmNoticeId, String tag);

        void publishNotice(int villageId, int pmUserId, String title, String content, String imageUrl, String deptIds, String tag);
        void getRoleList(String tag);
    }
}
