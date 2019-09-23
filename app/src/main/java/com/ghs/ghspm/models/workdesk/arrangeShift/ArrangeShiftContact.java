package com.ghs.ghspm.models.workdesk.arrangeShift;

import com.ghs.ghspm.base.BaseViewInterface;

/**
 * Author:wang_sir
 * Time:2019/2/11 14:12
 * Description:This is ArrangeShiftContact
 */
public interface ArrangeShiftContact {

    String ARRANGE_CAKENDAR = "arrange_calendar";//排班月历
    String ARRANGE_USER_DETAIL = "arrange_user_detauk";//xiagnq

    interface  IArrangeShiftView extends BaseViewInterface{}

    interface IArrangeShiftPresent {
        void  getArrangeShiftCalendarData(int propertyId,int userId,int year,int month,String tag);

        /**
         * 获取排班详情
         * @param tag
         */
        void  getArrangeUserDetail(int propertyId,int userId,String clockDay, String tag);

    }
}
