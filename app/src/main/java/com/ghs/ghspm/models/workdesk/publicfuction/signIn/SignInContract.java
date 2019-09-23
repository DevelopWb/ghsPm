package com.ghs.ghspm.models.workdesk.publicfuction.signIn;

import com.ghs.ghspm.base.BaseViewInterface;

/**
 * Author:wang_sir
 * Time:2018/10/19 14:49
 * Description:This is SignInContract
 */
public interface SignInContract {
    String CHECK_WORK_SIGN = "check_work_sign";
    String CHECK_WORK_GET_USER_INFO = "check_work_get_user_info";
    String UPDATE_CLOCK = "update_clock";
    String REPLACE_CLOCK = "replace_clock";//补卡
    String READlY_REPLACE_CLOCK = "readly_replace_clock";
    String RULE = "rule";//考勤规则


    interface ISignInView extends BaseViewInterface {
    }

    interface ISignInPresent {
        void clock(int propertyId, int userId, int outside, String clockPlace, int clockType ,String tag);

        void checkWorkGetUserInfo(int propertyId, int userId, String tag);

        void updateClock(int outside, String clockPlace, int clockId, String tag);

        void signReplace(int ruleId, int userId, int clockId, int controlUser, String reason, String tag);

        /**
         * 获取考勤规则
         *
         * @param propertyId
         * @param userId
         * @param tag
         */
        void getAttendanceRules(int propertyId, int userId, String tag);

        /**
         * 准备补卡信息
         */

        void getReplaceClock(int propertyId, int userId, int clockId, String tag);

        /**
         * 补卡
         */
        void getReplaceClocked(int propertyId, int userId, int clockId, String replaceTime, String reason, String tag);
    }
}
