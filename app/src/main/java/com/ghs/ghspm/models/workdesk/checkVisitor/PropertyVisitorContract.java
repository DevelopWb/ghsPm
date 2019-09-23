package com.ghs.ghspm.models.workdesk.checkVisitor;

import com.ghs.ghspm.base.BaseViewInterface;

/**
 * created by guohainan
 * created date 2019/8/23
 * application
 * package name com.ghs.ghspm.models.workdesk.propertyrecord
 */
public interface PropertyVisitorContract {
    public static String PROPERTY_RECORD_LIST = "visitorList";//访客列表
    public static String PROPERTY_RECORD_CHECKAGREE = "checkAgree";//申请通过
    public static String PROPERTY_RECORD_CHECKREJECT = "checkReject";//驳回
    public static String PROPERTY_DEATILS="property_deatils";//详情

    interface PropertyRecordView extends BaseViewInterface {
    }

    interface ProperRecordPresent {
        void getPropertyVisitorRecodList(int villageId, int offset, int limit, int state, String tag);

        void getCheckAgree(int visitorId, int pmUserId, String tag);

        void getCheckReject(int visitorId, int pmUserId, String tag);

    }


}
