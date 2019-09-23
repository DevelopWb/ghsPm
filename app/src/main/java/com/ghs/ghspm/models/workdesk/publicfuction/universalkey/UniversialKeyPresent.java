package com.ghs.ghspm.models.workdesk.publicfuction.universalkey;

import com.ghs.ghspm.base.BasePresent;
import com.ghs.ghspm.base.network.RequestStatus;

/**
 * Author:wang_sir
 * Time:2018/9/27 16:03
 * Description:This is UniversialKeyPresent
 */
public class UniversialKeyPresent extends BasePresent<UniversialKeyContract.IUniversialKeyView> implements UniversialKeyContract.IUniversialKeyPresent, RequestStatus {

    private final UniversialKeyModel universialKeyModel;

    public UniversialKeyPresent() {
        universialKeyModel = new UniversialKeyModel();
    }

    @Override
    public void getTowerNo(int villageId, String tag) {
        universialKeyModel.getTowerNo(villageId, this, tag);
    }

    @Override
    public void getCellNo(int villageId, int towerId, String tag) {
        universialKeyModel.getCellNo(villageId, towerId, this, tag);
    }

    @Override
    public void getDoorNo(String villageId, String tag) {
        universialKeyModel.getDoorNo(villageId,this,tag);
    }

    @Override
    public void openByCell(String cellId, String pmUserId, String tag) {
        universialKeyModel.openByCell(cellId,pmUserId,this,tag);
    }

    @Override
    public void openByDeviceId(String deviceId, String pmUserId, String tag) {
        universialKeyModel.openByDeviceId(deviceId,pmUserId,this,tag);
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
