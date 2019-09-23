package com.ghs.ghspm.models.workdesk.publicfuction.universalkey;

import com.ghs.ghspm.base.network.RequestStatus;
import com.ghs.ghspm.base.network.networkProxy.HttpProxy;
import com.ghs.ghspm.base.network.okgo.NetCallBackInterface;
import com.ghs.ghspm.bean.CellBean;
import com.ghs.ghspm.bean.RoomBean;
import com.ghs.ghspm.bean.TowerBean;
import com.ghs.ghspm.bean.VilliageDoorBean;
import com.ghs.ghspm.tools.Contract;
import com.ghs.ghspm.tools.GsonManager;
import com.ghs.ghspm.tools.PubUtil;

/**
 * Author:wang_sir
 * Time:2018/9/27 16:04
 * Description:This is UniversialKeyModel
 */
public class UniversialKeyModel implements UniversialKeyContract.IUniversialKeyModel {
    @Override
    public void getTowerNo(int villageId, final RequestStatus requestStatus, final String tag) {
        HttpProxy.getInstance()
                .params("villageId", villageId)
                .postToNetwork(Contract.UNIVERSIAL_KEY_GET_TOWER_NO,  new NetCallBackInterface() {
                    @Override
                    public void onSuccess(String content) {
                        if (requestStatus != null) {
                            requestStatus.onSuccess(GsonManager.getInstance().parseJsonToBean(content, TowerBean.class), tag);
                        }
                    }

                    @Override
                    public void onError(String str) {
                        if (requestStatus != null) {
                            requestStatus.onError(str);
                        }
                    }
                });
    }

    @Override
    public void getCellNo(int villageId, int towerId, final RequestStatus requestStatus, final String tag) {
        HttpProxy.getInstance()
                .params("villageId", villageId)
                .params("towerId", towerId)
                .postToNetwork(Contract.UNIVERSIAL_KEY_GET_CELL_NO,  new NetCallBackInterface() {
                    @Override
                    public void onSuccess(String content) {
                        if (requestStatus != null) {
                            requestStatus.onSuccess(GsonManager.getInstance().parseJsonToBean(content, CellBean.class), tag);
                        }
                    }

                    @Override
                    public void onError(String str) {
                        if (requestStatus != null) {
                            requestStatus.onError(str);
                        }
                    }
                });
    }

    @Override
    public void getRoomNo(int villageId, int cellId, final RequestStatus requestStatus, final String tag) {
        HttpProxy.getInstance()
                .params("villageId", villageId)
                .params("cellId", cellId)
                .postToNetwork(Contract.UNIVERSIAL_GTEROOM,  new NetCallBackInterface() {
                    @Override
                    public void onSuccess(String content) {
                        if (requestStatus != null) {
                            requestStatus.onSuccess(GsonManager.getInstance().parseJsonToBean(content, RoomBean.class), tag);
                        }
                    }

                    @Override
                    public void onError(String str) {
                        if (requestStatus != null) {
                            requestStatus.onError(str);
                        }
                    }
                });
    }

    @Override
    public void getDoorNo(final String villageId, final RequestStatus requestStatus, final String tag) {

        HttpProxy.getInstance()
                .params("villageId", villageId)
                .postToNetwork(Contract.UNIVERSIAL_KEY_GET_DOOR_NO,  new NetCallBackInterface() {
                    @Override
                    public void onSuccess(String content) {
                        if (requestStatus != null) {
                            requestStatus.onSuccess(GsonManager.getInstance().parseJsonToBean(content, VilliageDoorBean.class), tag);
                        }
                    }

                    @Override
                    public void onError(String str) {
                        if (requestStatus != null) {
                            requestStatus.onError(str);
                        }
                    }
                });

    }

    @Override
    public void openByCell(String cellId, String pmUserId, final RequestStatus requestStatus, final String tag) {
        HttpProxy.getInstance()
                .params("cellId", cellId)
                .params("pmUserId", pmUserId)
                .postToNetwork(Contract.UNIVERSIAL_KEY_OPEN_BY_CELL,  new NetCallBackInterface() {
                    @Override
                    public void onSuccess(String content) {
                        if (requestStatus != null) {
                            requestStatus.onSuccess(PubUtil.getServerCode(content), tag);

                        }
                    }

                    @Override
                    public void onError(String str) {
                        if (requestStatus != null) {
                            requestStatus.onError(str);
                        }
                    }
                });
    }

    @Override
    public void openByDeviceId(String deviceId, String pmUserId, final RequestStatus requestStatus, final String tag) {
        HttpProxy.getInstance()
                .params("deviceId", deviceId)
                .params("pmUserId", pmUserId)
                .postToNetwork(Contract.UNIVERSIAL_KEY_OPEN_BY_DEVICE_NO,  new NetCallBackInterface() {
                    @Override
                    public void onSuccess(String content) {
                        if (requestStatus != null) {
                            requestStatus.onSuccess(PubUtil.getServerCode(content), tag);

                        }
                    }

                    @Override
                    public void onError(String str) {
                        if (requestStatus != null) {
                            requestStatus.onError(str);
                        }
                    }
                });
    }
}
