package com.ghs.ghspm.models.workdesk.innerrepair;

import com.ghs.ghspm.base.BaseViewInterface;

/**
 * Author:wang_sir
 * Time:2018/11/1 17:54
 * Description:This is InnerRepairContract
 */
public interface InnerRepairContract {


    String REPAIR_MENU = "repair_menu";
    String SAVE_REPAIR = "save_repair";
    interface IInnerRepairView extends BaseViewInterface {
    }

    interface iInnerRepairPresent {
        void getInnerRepairMenu(int propertyId,String tag);
        void saveInnerRepairInfo(int propertyId,int userId,String kind,String label,String position,String content,String imageUrl,String tag);

    }
}
