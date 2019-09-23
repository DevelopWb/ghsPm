package com.ghs.ghspm.models.workdesk.ownerinfo;

import com.ghs.ghspm.base.BasePresent;
import com.ghs.ghspm.base.network.RequestStatus;

public class OwnerInfoPresenter extends BasePresent<OwnerInfoContract.OwnerInfoView> implements OwnerInfoContract.OwnerInfoPresenter, RequestStatus {
    private OwnerInfoMoudel ownerInfoMoudel;

    public OwnerInfoPresenter() {
        ownerInfoMoudel = new OwnerInfoMoudel();
    }


    @Override
    public void getTower(String villageId, String tag) {
        ownerInfoMoudel.getTower(villageId, this, tag);
    }

    @Override
    public void getRation(String villageId, String rationId, String tag) {
        ownerInfoMoudel.getRation(villageId, rationId, this, tag);
    }

    @Override
    public void getRoom(String villageId, String cellid, String tag) {
        ownerInfoMoudel.getRoom(villageId, cellid, this, tag);
    }

    @Override
    public void getOwnerMessage(String propertyId, String roomId, String roleType, String tag) {

        ownerInfoMoudel.getOwnerMessage(propertyId, roomId, roleType, this, tag);
    }

    @Override
    public void getWaterBill(String villageId, String roomId, String feeType, String startMonth, String endMonth, String tag) {

        ownerInfoMoudel.getWaterBill(villageId, roomId, feeType, startMonth, endMonth, this, tag);

    }

    @Override
    public void getProPrety(String villageId, String roomId, String tag) {

        ownerInfoMoudel.getProPrety(villageId, roomId, this, tag);
    }

    //车辆信息
    @Override
    public void getCarMessage(String villagerId, String roomId, String tag) {
        if (ownerInfoMoudel != null) {

            ownerInfoMoudel.getCarMessage(villagerId, roomId, this, tag);

        }
    }


    @Override
    public void onStart(String tag) {
        if (getView() != null) {
            getView().startLoading(tag);
        }
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

}
