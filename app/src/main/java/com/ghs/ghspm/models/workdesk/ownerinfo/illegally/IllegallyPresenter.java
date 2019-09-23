package com.ghs.ghspm.models.workdesk.ownerinfo.illegally;

import com.ghs.ghspm.base.BasePresent;
import com.ghs.ghspm.base.network.RequestStatus;

public class IllegallyPresenter extends BasePresent<IllegallyContract.IllegallyView> implements IllegallyContract.IllegfllyPresenter, RequestStatus {
    private IllegallyMoudel illegallyMoudel;

    public IllegallyPresenter() {
        this.illegallyMoudel = new IllegallyMoudel();
    }

    @Override
    public void onStart(String tag) {

    }

    @Override
    public void onSuccess(Object o, String tag) {
        if (getView() != null) {
            getView().updateView(o, tag);
        }
    }

    @Override
    public void onError(String tag) {
        if (getView() != null) {
            getView().onError(tag);
        }
    }

    @Override
    public void getIllegallyMessage(String carNum, String villageId, String tag) {
        illegallyMoudel.getIllegallyMessage(carNum, villageId, this, tag);
    }
}
