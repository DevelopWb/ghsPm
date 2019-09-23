package com.ghs.ghspm.models.task;

import com.ghs.ghspm.base.BaseViewInterface;

import java.util.Map;

/**
 * Author:wang_sir
 * Time:2018/6/29 10:10
 * Description:This is WorkDeskContract
 */
public interface TaskContract {
    String WAIT_TASK = "wait_task";//待处理任务
    String CREATED_TASK = "created_task";//我创建的任务
    String RELATED_TASK = "related_task";//与我相关任务
    String COMPLATED_TASK = "complated_task";//已完成任务
    String PUBLISH_TASK = "publish_task";
    String TASK_DETAIL_INFO = "task_detail_info";
    String GIVE_UP_TASK = "give_up_task";
    String COMPLATE_TASK = "complate_task";
    String AGREE_TASK = "agree_task";
    String REJECT_TASK = "reject_task";
    String TRANSFER_TASK = "transfer_task";//转发任务
    String REFRESH = "refresh";//刷新
    String UPDATA_TASK = "updata_task";//修改任务

    interface ITaskView extends BaseViewInterface {
    }

    interface ITaskPresent {
        void getTasks(String taskType, int userId, int offset, int limit, String tag);

        void publishTask(String content, String checkUserId, int propertyId, int createUserId, String createUserName, String handlerUsersId, String ccUsersId, Map<String, String> map, String tag);

        void getTaskDetailInfo(int taskId, int userId, String tag);

        /**
         * 放弃任务或者完成任务调用的接口
         *
         * @param taskId
         * @param userId
         * @param tag
         */
        void dealTask(String urlPath, int taskId, int userId, String remark, String picture, String tag);

        void transferTask(int taskId, int userId, int toUserId, String remark, String picture, String tag);

        /**
         *
         * @param taskId  临时任务id
         * @param updateUserId 修改人id
         * @param checkUserId  负责人id
         * @param handlerUsersId 执行人id
         * @param ccUsersId 抄送人id
         */
        void updataTask(int taskId, int updateUserId,Long checkUserId,String handlerUsersId,String ccUsersId,String tag);

    }


}
