package com.ghs.ghspm.models.workdesk.publicfuction.signIn.monthrecord;

import com.ghs.ghspm.base.BaseViewInterface;

/**
 * Author:wang_sir
 * Time:2018/10/20 18:08
 * Description:This is MonthRecordContract
 */
public interface MonthRecordContract {


    String SIGN_RECORD = "sign_record";
    String SIGN_STATICS = "sign_statics";
    String SIGN_REPLACE = "sign_replace";

    interface IMonthRecordView extends BaseViewInterface {
    }

    interface IMonthRecordPresent {
        void signStatics(int propertyId, int userId, int year, int month, String tag);
        void shiftRecordEveryDay(int propertyId, int userId,String clockDay, String tag);
        void signReplace(int ruleId, int userId, int clockId,int controlUser,String reason, String tag);
    }
}
