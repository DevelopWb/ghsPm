package com.ghs.ghspm.models.workOrder;

import android.util.Log;

import com.ghs.ghspm.base.BasePresent;
import com.ghs.ghspm.base.network.RequestStatus;
import com.ghs.ghspm.base.network.networkProxy.HttpProxy;
import com.ghs.ghspm.base.network.okgo.NetCallBackInterface;
import com.ghs.ghspm.bean.RoomSearchedBean;
import com.ghs.ghspm.bean.WorkOrderBean;
import com.ghs.ghspm.bean.WorkOrderDetailBean;
import com.ghs.ghspm.bean.WorkOrderUnreadBean;
import com.ghs.ghspm.tools.Contract;
import com.ghs.ghspm.tools.GsonManager;
import com.ghs.ghspm.tools.PubUtil;

import java.util.Map;

/**
 * Author:wang_sir
 * Time:2019/7/8 15:20
 * Description:This is WorkOrderPresent
 */
public class WorkOrderPresent  extends BasePresent<WorkOrderContract.IWorkOrderView> implements WorkOrderContract.IWorkOrderPresent {
    @Override
    public void getWaitCheckOrders(int userId, int villageId, int offset, int limit, String tag) {
        requestOrderList(Contract.WAIT_CHECK_ORDER,userId, villageId, offset, limit, tag);

    }

    @Override
    public void getFinishedOrders(int userId, int villageId, int offset, int limit, String tag) {
        requestOrderList(Contract.FINISHED_ORDERS,userId, villageId, offset, limit, tag);
    }

    @Override
    public void getUnAssignedOrders(int villageId, int offset, int limit, String tag) {
        if (0==offset) {
            tag = RequestStatus.REFRESH;
        }
        final  String tagA = tag;
        HttpProxy.getInstance()
                .params("villageId", villageId)
                .params("offset", offset)
                .params("limit", limit)
                .postToNetwork(Contract.UNASSIGN_ORDER, new NetCallBackInterface() {
                    @Override
                    public void onSuccess(String content) {
                        if (getView() != null) {
                            getView().updateView(GsonManager.getInstance().parseJsonToBean(content, WorkOrderBean.class), tagA);
                        }
                    }

                    @Override
                    public void onError(String str) {
                        if (getView() != null) {
                            getView().onError(str);
                        }
                    }
                });
    }

    @Override
    public void getCopyedOrders(int userId, int villageId, int offset, int limit, String tag) {
        requestOrderList(Contract.COPYED_TO_ME_ORDER,userId, villageId, offset, limit, tag);

    }

    @Override
    public void getICreatedOrders(int userId, int villageId, int offset, int limit, String tag) {
        requestOrderList(Contract.I_CREATED_ORDER,userId, villageId, offset, limit, tag);

    }

    @Override
    public void getWaitFollowedOrders(int userId, int villageId, int offset, int limit,  String tag) {
        requestOrderList(Contract.WAIT_FOLLOW_ORDER,userId, villageId, offset, limit, tag);

    }

    @Override
    public void createOrder(int villageId, int userId, Map<String, String> map, final String tag) {


        HttpProxy.getInstance()
                .params("userId", userId)
                .params("villageId", villageId)
                .params(map)
                .postToNetwork(Contract.CREATE_ORDER, new NetCallBackInterface() {
                    @Override
                    public void onSuccess(String content) {
                        if (getView() != null) {
                            getView().updateView("", tag);
                        }
                    }

                    @Override
                    public void onError(String str) {
                        if (getView() != null) {
                            getView().onError(str);
                        }
                    }
                });




    }




    /**
     * 请求工单列表
     * @param userId
     * @param villageId
     * @param offset
     * @param limit
     * @param tag
     */
    private void requestOrderList(String  url,int userId, int villageId, int offset, int limit, String tag) {
        if (0==offset) {
            tag = RequestStatus.REFRESH;
        }
        final  String tagA = tag;
        HttpProxy.getInstance()
                .params("userId", userId)
                .params("villageId", villageId)
                .params("offset", offset)
                .params("limit", limit)
                .postToNetwork(url, new NetCallBackInterface() {
                    @Override
                    public void onSuccess(String content) {
                        if (getView() != null) {
                            getView().updateView(GsonManager.getInstance().parseJsonToBean(content, WorkOrderBean.class), tagA);
                        }
                    }

                    @Override
                    public void onError(String str) {
                        if (getView() != null) {
                            getView().onError(str);
                        }
                    }
                });
    }


    @Override
    public void getWorkOrderUnreadNum(int userId, int villageId, final String tag) {
        HttpProxy.getInstance()
                .params("userId", userId)
                .params("villageId", villageId)
                .postToNetwork(Contract.GET_WORK_ORDER_UNREAD_NUM,  new NetCallBackInterface() {
                    @Override
                    public void onSuccess(String content) {
                        Log.i("TAG",content);
                        if (getView() != null) {
                            getView().updateView(GsonManager.getInstance().parseJsonToBean(content, WorkOrderUnreadBean.class), tag);
                        }
                    }

                    @Override
                    public void onError(String str) {
                        if (getView() != null) {
                            getView().onError(str);
                        }
                    }
                });

    }

    /**
     * 工单未读通知类型，1:跟进 2:验收 3:抄送
     * @param userId
     * @param villageId
     * @param type      工单未读通知类型，1:跟进 2:验收 3:抄送
     * @param tag
     */
    @Override
    public void delWorkOrderUnreadNum(int userId, int villageId, int type,  final String tag) {
        HttpProxy.getInstance()
                .params("userId", userId)
                .params("villageId", villageId)
                .params("type", type)
                .postToNetwork(Contract.DEL_WORK_ORDER_UNREAD_NUM,  new NetCallBackInterface() {
                    @Override
                    public void onSuccess(String content) {
                        if (getView() != null) {
                            getView().updateView(GsonManager.getInstance().parseJsonToBean(content, WorkOrderUnreadBean.class), tag);
                        }
                    }

                    @Override
                    public void onError(String str) {
                        if (getView() != null) {
                            getView().onError(str);
                        }
                    }
                });
    }

    @Override
    public void uploadSuggestion(int userId, int villageId, String content, String imageUrl1, String imageUrl2, String imageUrl3, final String tag) {
        HttpProxy.getInstance()
                .params("userId",userId )
                .params("villageId",villageId )
                .params("content", content)
                .params("imageUrl1", imageUrl1)
                .params("imageUrl2", imageUrl2)
                .params("imageUrl3", imageUrl3)
                .postToNetwork(Contract.MINE_SUGGUEST,  new NetCallBackInterface() {
                    @Override
                    public void onSuccess(String content) {
                        if (getView() != null) {
                            getView().updateView(PubUtil.getServerCode(content), tag);
                        }
                    }

                    @Override
                    public void onError(String str) {
                        if (getView() != null) {
                            getView().onError(str);
                        }
                    }
                });
    }

    @Override
    public void getWorkOrderDetail(int userId, int serviceWorkId, final String tag) {
        HttpProxy.getInstance()
                .params("userId",userId )
                .params("serviceWorkId",serviceWorkId )
                .postToNetwork(Contract.WORK_ORDER_DETAIL,  new NetCallBackInterface() {
                    @Override
                    public void onSuccess(String content) {
                        if (getView() != null) {
                            getView().updateView(GsonManager.getInstance().parseJsonToBean(content, WorkOrderDetailBean.class),tag);
                        }
                    }

                    @Override
                    public void onError(String str) {
                        if (getView() != null) {
                            getView().onError(str);
                        }
                    }
                });
    }

    @Override
    public void finishWorkOrder(int userId, String userName, int serviceWorkId,final String tag) {
        HttpProxy.getInstance()
                .params("userId",userId )
                .params("userName",userName )
                .params("serviceWorkId",serviceWorkId )
                .postToNetwork(Contract.WORK_ORDER_DETAIL_FINISH,  new NetCallBackInterface() {
                    @Override
                    public void onSuccess(String content) {
                        if (getView() != null) {
                            getView().updateView("",tag);
                        }
                    }

                    @Override
                    public void onError(String str) {
                        if (getView() != null) {
                            getView().onError(str);
                        }
                    }
                });
    }

    @Override
    public void agreeWorkOrder(int userId, String userName, int serviceWorkId, final String tag) {
        HttpProxy.getInstance()
                .params("userId",userId )
                .params("userName",userName )
                .params("serviceWorkId",serviceWorkId )
                .postToNetwork(Contract.WORK_ORDER_DETAIL_AGREE,  new NetCallBackInterface() {
                    @Override
                    public void onSuccess(String content) {
                        if (getView() != null) {
                            getView().updateView("",tag);
                        }
                    }

                    @Override
                    public void onError(String str) {
                        if (getView() != null) {
                            getView().onError(str);
                        }
                    }
                });
    }

    @Override
    public void rejectWorkOrder(int userId, String userName, int serviceWorkId, final String tag) {
        HttpProxy.getInstance()
                .params("userId",userId )
                .params("userName",userName )
                .params("serviceWorkId",serviceWorkId )
                .postToNetwork(Contract.WORK_ORDER_DETAIL_REJECT,  new NetCallBackInterface() {
                    @Override
                    public void onSuccess(String content) {
                        if (getView() != null) {
                            getView().updateView("",tag);
                        }
                    }

                    @Override
                    public void onError(String str) {
                        if (getView() != null) {
                            getView().onError(str);
                        }
                    }
                });
    }

    @Override
    public void writeProgress(int userId, String userName, int serviceWorkId, String remark, String imageUrl,final  String tag) {
        HttpProxy.getInstance()
                .params("userId",userId )
                .params("userName",userName )
                .params("serviceWorkId",serviceWorkId )
                .params("remark",remark )
                .params("imageUrl",imageUrl )
                .postToNetwork(Contract.WORK_ORDER_WRITE_PROGRESS,  new NetCallBackInterface() {
                    @Override
                    public void onSuccess(String content) {
                        if (getView() != null) {
                            getView().updateView("",tag);
                        }
                    }

                    @Override
                    public void onError(String str) {
                        if (getView() != null) {
                            getView().onError(str);
                        }
                    }
                });
    }

    @Override
    public void updateReceipt(int userId, int serviceWorkId, Map<String, String> map, final String tag) {
        HttpProxy.getInstance()
                .params("userId", userId)
                .params("serviceWorkId", serviceWorkId)
                .params(map)
                .postToNetwork(Contract.UPDATA_RECEIPT,  new NetCallBackInterface() {
                    @Override
                    public void onSuccess(String content) {
                        if (getView() != null) {
                            getView().updateView("", tag);
                        }
                    }

                    @Override
                    public void onError(String str) {
                        if (getView() != null) {
                            getView().onError(str);
                        }
                    }
                });
    }

    @Override
    public void activateWorkOrder(int userId, String userName, int serviceWorkId, final String tag) {
        HttpProxy.getInstance()
                .params("userId", userId)
                .params("userName", userName)
                .params("serviceWorkId", serviceWorkId)
                .postToNetwork(Contract.ACTIVITE_WORK_ORDER,  new NetCallBackInterface() {
                    @Override
                    public void onSuccess(String content) {
                        if (getView() != null) {
                            getView().updateView("", tag);
                        }
                    }

                    @Override
                    public void onError(String str) {
                        if (getView() != null) {
                            getView().onError(str);
                        }
                    }
                });
    }

    @Override
    public void deleteWorkOrder(int serviceWorkId,final  String tag) {
        HttpProxy.getInstance()
                .params("serviceWorkId", serviceWorkId)
                .postToNetwork(Contract.DELETE_WORK_ORDER,  new NetCallBackInterface() {
                    @Override
                    public void onSuccess(String content) {
                        if (getView() != null) {
                            getView().updateView("", tag);
                        }
                    }

                    @Override
                    public void onError(String str) {
                        if (getView() != null) {
                            getView().onError(str);
                        }
                    }
                });
    }

    @Override
    public void searchRoomNum(int villageId, String roomNumber,final  String tag) {
        HttpProxy.getInstance()
                .params("villageId", villageId)
                .params("roomNumber", roomNumber)
                .postToNetwork(Contract.SEARCH_ROOM_NUM,  new NetCallBackInterface() {
                    @Override
                    public void onSuccess(String content) {
                        if (getView() != null) {
                            getView().updateView(GsonManager.getInstance().parseJsonToBean(content, RoomSearchedBean.class),tag);

                        }
                    }

                    @Override
                    public void onError(String str) {
                        if (getView() != null) {
                            getView().onError(str);
                        }
                    }
                });
    }

    /**
     * 修改工单
     * @param serviceWorkId
     * @param userId
     * @param map
     * @param tag
     */
    @Override
    public void updateOrder(int serviceWorkId, int userId, Map<String, String> map, final String tag) {
        HttpProxy.getInstance()
                .params("userId", userId)
                .params("serviceWorkId", serviceWorkId)
                .params(map)
                .postToNetwork(Contract.UPDATE_ORDER,  new NetCallBackInterface() {
                    @Override
                    public void onSuccess(String content) {
                        if (getView() != null) {
                            getView().updateView(GsonManager.getInstance().parseJsonToBean(content, WorkOrderUnreadBean.class), tag);
                        }
                    }

                    @Override
                    public void onError(String str) {
                        if (getView() != null) {
                            getView().onError(str);
                        }
                    }
                });


    }
}
