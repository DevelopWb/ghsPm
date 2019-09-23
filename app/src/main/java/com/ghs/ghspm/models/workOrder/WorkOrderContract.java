package com.ghs.ghspm.models.workOrder;

import com.ghs.ghspm.base.BaseViewInterface;

import java.util.Map;

/**
 * Author:wang_sir
 * Time:2019/7/8 15:18
 * Description:This is WorkOrderContract
 */
public interface WorkOrderContract {
    //待验收工单
    String WAIT_CHECK_ORDER = "/serviceWork/checkServiceWorkList";
    //已完成工单
    String FINISHED_ORDERS = "/serviceWork/completeServiceWorkList";
    //未指派工单
    String UNASSIGN_ORDER = "/serviceWork/notAssignedServiceWorkList";
    //抄送我的
    String COPYED_TO_ME_ORDER = "/serviceWork/relationServiceWorkList";
    //我创建的
    String I_CREATED_ORDER = "/serviceWork/startServiceWorkList";
    //待跟进
    String WAIT_FOLLOW_ORDER = "/serviceWork/todoServiceWorkList";
    //获取工单未读数
    String GET_WORK_ORDER_UNREAD_NUM = "/serviceWork/getUnreadNum";
    //移除小红点
    String DEL_WORK_ORDER_UNREAD_NUM = "/serviceWork/removeUnreadNum";
    //工单详情
    String WORK_ORDER_DETAIL = "/serviceWork/detail";
    //审核通过
    String WORK_ORDER_DETAIL_AGREE = "/serviceWork/checkAgree";
    //审核不通过
    String WORK_ORDER_DETAIL_REJECT = "/serviceWork/checkReject";
    //工单详情完成
    String WORK_ORDER_DETAIL_FINISH = "/serviceWork/finish";
    //写进展
    String WORK_ORDER_WRITE_PROGRESS = "/serviceWork/progress";

    //新建工单
    String CREATE_ORDER = "create_order";
    //修改工单内容
    String UPDATE_ORDER_CONTENT = "update_order_content";
    //修改工单回执
    String UPDATE_RECEIPT = "update_receipt";

    //激活工单
    String ACTIVITE_WORK_ORDER="/serviceWork/activate";
    //删除工单
    String DELETE_WORK_ORDER="/serviceWork/remove";

    //根据房间号查询房间list（全匹配查询）
     String SEARCH_ROOM_NUM = "/roomUtils/getRoomListByRoomNumber";
    interface IWorkOrderView extends BaseViewInterface {
    }

    interface IWorkOrderPresent {
        /**
         * 获取待检测的工单
         * @param userId
         * @param villageId
         * @param offset
         * @param limit
         * @param tag
         */
        void getWaitCheckOrders(int userId, int villageId, int offset, int limit, String tag);

        /**
         * 获取已完成的工单
         * @param userId
         * @param villageId
         * @param offset
         * @param limit
         * @param tag
         */
        void getFinishedOrders(int userId, int villageId, int offset, int limit, String tag);

        /**
         * 未指定的工单
         * @param villageId
         * @param offset
         * @param limit
         * @param tag
         */
        void getUnAssignedOrders(int villageId, int offset, int limit, String tag);

        /**
         * 抄送我的
         * @param userId
         * @param villageId
         * @param offset
         * @param limit
         * @param tag
         */
        void getCopyedOrders(int userId, int villageId, int offset, int limit, String tag);

        /**
         * 我创建的工单
         * @param userId
         * @param villageId
         * @param offset
         * @param limit
         * @param tag
         */
        void getICreatedOrders(int userId, int villageId, int offset, int limit, String tag);

        /**
         * 待跟进
         * @param userId
         * @param villageId
         * @param offset
         * @param limit
         * @param tag
         */
        void getWaitFollowedOrders(int userId, int villageId, int offset, int limit, String tag);

        /**
         * 新建工单
         * @param villageId
         * @param userId
         * @param map
         * @param tag
         */
        void createOrder(int villageId, int userId, Map<String, String> map, String tag);

        /**
         * 更新工单内容
         * @param serviceWorkId
         * @param userId
         * @param map
         * @param tag
         */
        void updateOrder(int serviceWorkId, int userId, Map<String, String> map, String tag);

        /**
         * 获取工单未读数
         * @param userId
         * @param villageId
         * @param tag
         */
        void getWorkOrderUnreadNum(int userId, int villageId, String tag);

        /**
         * @param userId
         * @param villageId
         * @param type      工单未读通知类型，1:跟进 2:验收 3:抄送
         * @param tag
         */
        void delWorkOrderUnreadNum(int userId, int villageId, int type, String tag);

        /**
         * 上传建议
         * @param userId
         * @param villageId
         * @param content
         * @param imageUrl1
         * @param imageUrl2
         * @param imageUrl3
         * @param tag
         */
        void uploadSuggestion(int userId, int villageId, String content, String imageUrl1, String imageUrl2, String imageUrl3, String tag);

        /**
         * 获取工单详情
         * @param userId
         * @param serviceWorkId
         * @param tag
         */
        void getWorkOrderDetail(int userId, int serviceWorkId, String tag);

        /**
         * 完成
         * @param userId
         * @param userName
         * @param serviceWorkId
         * @param tag
         */
        void finishWorkOrder(int userId, String userName, int serviceWorkId, String tag);

        /**
         * 通过
         * @param userId
         * @param userName
         * @param serviceWorkId
         * @param tag
         */
        void agreeWorkOrder(int userId, String userName, int serviceWorkId, String tag);

        /**
         * 不通过
         * @param userId
         * @param userName
         * @param serviceWorkId
         * @param tag
         */
        void rejectWorkOrder(int userId, String userName, int serviceWorkId, String tag);

        /**
         * 写进展
         * @param userId
         * @param userName
         * @param serviceWorkId
         * @param remark
         * @param imageUrl
         * @param tag
         */
        void writeProgress(int userId, String userName, int serviceWorkId, String remark, String imageUrl, String tag);

        /**
         * 更新工单回执
         */
        void updateReceipt(int userId,int serviceWorkId, Map<String, String> map, String tag);

        /**
         * 激活工单
         * @param userId
         * @param userName
         * @param serviceWorkId
         * @param tag
         */
        void activateWorkOrder(int userId, String userName, int serviceWorkId, String tag);

        /**
         * 删除工单
         * @param serviceWorkId
         * @param tag
         */
        void deleteWorkOrder(int serviceWorkId, String tag);
        /**
         * 搜索房间号
         * @param villageId
         * @param roomNumber
         * @param tag
         */
        void searchRoomNum(int villageId,String roomNumber,String tag);

    }



}
