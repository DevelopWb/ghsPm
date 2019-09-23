package com.ghs.ghspm.models.workdesk.ownerinfo;

import android.util.Log;

import com.ghs.ghspm.base.network.RequestStatus;
import com.ghs.ghspm.base.network.networkProxy.HttpProxy;
import com.ghs.ghspm.base.network.okgo.NetCallBackInterface;
import com.ghs.ghspm.bean.CarMessageBean;
import com.ghs.ghspm.bean.CellBean;
import com.ghs.ghspm.bean.OwnerMessageBean;
import com.ghs.ghspm.bean.OwnerPropretyBean;
import com.ghs.ghspm.bean.OwnerWaterBillBean;
import com.ghs.ghspm.bean.RoomBean;
import com.ghs.ghspm.bean.TowerBean;
import com.ghs.ghspm.tools.Contract;
import com.ghs.ghspm.tools.GsonManager;

public class OwnerInfoMoudel implements OwnerInfoContract.OwnerInfoModel {


    @Override
    public void getTower(String villageId, final RequestStatus requestStatus, final String tag) {

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
    public void getRation(String villageId, String rationId, final RequestStatus requestStatus, final String tag) {

        HttpProxy.getInstance()
                .params("villageId", villageId)
                .params("towerId", rationId)
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
    public void getRoom(String villageId, final String cellid, final RequestStatus requestStatus, final String tag) {
        HttpProxy.getInstance()
                .params("villageId", villageId)
                .params("cellId", cellid)
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

    //   获取业主信息
    @Override
    public void getOwnerMessage(String propertyId, String roomId, final String roleType, final RequestStatus requestStatus, final String tag) {
        HttpProxy.getInstance()
                .params("villageId", propertyId)
                .params("roomId", roomId)
                .params("roleType", roleType)
                .postToNetwork(Contract.OWNERME_MESSAGE,  new NetCallBackInterface() {
                    @Override
                    public void onSuccess(String content) {
                        if (requestStatus != null) {
                            requestStatus.onSuccess(GsonManager.getInstance().parseJsonToBean(content, OwnerMessageBean.class), OwnerInfoContract.OWNER_MESSAGE+"#"+roleType);
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
    //获取水费列表
    @Override
    public void getWaterBill(String villageId, String roomId, String feeType, String startMonth, String endMonth, final RequestStatus requestStatus, String tag) {
        HttpProxy.getInstance()
                .params("villageId", villageId)
                .params("roomId", roomId)
                .params("feeType", feeType)
                .postToNetwork(Contract.WATER_BILL_LiST,  new NetCallBackInterface() {
                    @Override
                    public void onSuccess(String content) {
                        Log.i("TAG",content);
                        if (requestStatus != null) {
                            requestStatus.onSuccess(GsonManager.getInstance().parseJsonToBean(content, OwnerWaterBillBean.class), OwnerInfoContract.GET_WATER_BILL);
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
    public void getProPrety(String villageId, String roomId, final RequestStatus requestStatus, String tag) {

        HttpProxy.getInstance()
                .params("villageId", villageId)
                .params("roomId", roomId)
                .postToNetwork(Contract.PROPRET_LIST,  new NetCallBackInterface() {
                    @Override
                    public void onSuccess(String content) {

                        if (requestStatus != null) {
                            requestStatus.onSuccess(GsonManager.getInstance().parseJsonToBean(content, OwnerPropretyBean.class),  OwnerInfoContract.GET_PROPRETY);
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
    public void getCarMessage(String villagerId, String roomId, final RequestStatus requestStatus, String tag) {
        HttpProxy.getInstance()
                .params("villageId", villagerId)
                .params("roomId", roomId)
                .postToNetwork(Contract.CAR_MESSAGE,  new NetCallBackInterface() {
                    @Override
                    public void onSuccess(String content) {
                        if (requestStatus != null) {
                            requestStatus.onSuccess(GsonManager.getInstance().parseJsonToBean(content, CarMessageBean.class), OwnerInfoContract.OWNER_CAR);
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
