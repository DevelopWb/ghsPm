package com.ghs.ghspm.models.workdesk.ownerinfo.illegally;

import com.ghs.ghspm.base.BaseViewInterface;
import com.ghs.ghspm.base.network.RequestStatus;

 interface IllegallyContract {
    //车辆违停信息
     String ILLEGA_MESSAGE = "illegally_message";
    String REFRESH = "refresh";//刷新
    /*
     *m层接口
     */
    //违停记录
     interface illegallyMoudel {

        void getIllegallyMessage(String carNum, String villageId, RequestStatus requestStatus, String tag);

    }

    /*
     *  p层接口
     */
    //违停记录
     interface IllegfllyPresenter {
        void getIllegallyMessage(String carNum, String villageId, String tag);
    }

     interface IllegallyView extends BaseViewInterface {
    }
}
