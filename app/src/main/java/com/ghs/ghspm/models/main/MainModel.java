package com.ghs.ghspm.models.main;

import com.ghs.ghspm.base.network.RequestStatus;
import com.ghs.ghspm.base.network.networkProxy.HttpProxy;
import com.ghs.ghspm.base.network.okgo.NetCallBackInterface;
import com.ghs.ghspm.bean.WorkOrderUnreadBean;
import com.ghs.ghspm.tools.Contract;
import com.ghs.ghspm.tools.GsonManager;

/**
 * Author:wang_sir
 * Time:2018/7/10 19:39
 * Description:This is MainModel
 */
public class MainModel implements MainContact.IMainModel {
    @Override
    public void getBlueMacList(int villageId, int cellId, final RequestStatus requestStatus,final String tag) {

    }


    @Override
    public void openDoorByNet(int deviceId, int userRoomId, final RequestStatus requestStatus) {
}

}
