package com.ghs.ghspm.models.workdesk;

import com.ghs.ghspm.base.BasePresent;
import com.ghs.ghspm.base.network.RequestStatus;

/**
 * Author:wang_sir
 * Time:2018/6/29 10:17
 * Description:This is WorkDeskPresent
 */
public class WorkDeskPresent extends BasePresent<WorkDeskContract.IHomeView> implements WorkDeskContract.IHomePresent ,RequestStatus{




    @Override
    public void onStart(String tag) {

    }

    @Override
    public void onSuccess(Object o, String tag) {
        if (getView() != null) {
            getView().updateView(o,tag);
        }
    }

    @Override
    public void onError(String tag) {

    }

}
