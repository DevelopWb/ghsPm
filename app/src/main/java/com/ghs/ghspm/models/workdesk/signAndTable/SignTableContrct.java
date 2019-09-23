package com.ghs.ghspm.models.workdesk.signAndTable;

import com.ghs.ghspm.base.BaseViewInterface;
import com.ghs.ghspm.base.network.RequestStatus;
import com.ghs.ghspm.bean.DynamicLayoutBean;
import com.ghs.ghspm.bean.TableMultipleItem;

import java.util.List;

/**
 * Author:wang_sir
 * Time:2019/1/2 11:02
 * Description:This is SignTableContrct
 */
public interface SignTableContrct {

    String GET_TABLE_TASKS = "get_table_tasks";//获取表单任务
    String GET_TABLE_TASK_DETAIL = "get_table_task_detail";//获取表单任务
    String SAVE_DAILY_FORM_TASK = "save_daily_form_task";//保存日常记录的任务
    String SAVE_TOOL_FORM_TASK = "save_tool_form_task";//保存日常记录的任务
    String FORM_TASK_COPY_TO_ME = "form_task_copy";//抄送我的
    String FORM_TASK_SINGED_BY_ME = "form_task_signed";//我签批的
    String FORM_TASK_COMMIT_BY_ME = "form_task_commit";//我提交的
    String GET_AUTO_FORM_LIST = "get_auto_form_list";//获取自动表单列表
    String SAVE_PATROL_FORM_RECORD = "save_patrol_form_record";//保存巡检表单记录

    interface ISignTableView extends BaseViewInterface {
    }

    interface ISignTablePresent {
        /**
         * 获取表单任务
         * @param userId
         */
        void getTableTasks(int userId, String tag);
        /**
         * 获取表单任务详情
         */
        void getTableTaskDetail(int recordId, int formType,int status,String tag);
        /**
         * 保存日常表单任务
         */
        void saveCommonFormTask(int recordId, String  contentJson,int handler,String tag);
        /**
         * 保存巡检表单任务记录
         */
        void savePatrolFormTaskRecord(int villageId,int formId,int taskId,String contentJson,int handler,String tag);
        /**
        /**
         * 编辑巡检表单任务记录
         */
        void editPatrolFormTaskRecord(int recordId,String contentJson,int handler,String tag);
        /**
         * 保存工具类表单
         */
        void saveToolFormTask(int villageId,int formId ,int handler,int checkUser,String ccUser, String  contentJson,String tag);

        /**
         * 获取动态布局的类型
         * @param cssClass
         */
        int getLayoutType(String cssClass);

        /**
         * 获取动态布局的列表数据
         * @param arrays
         * @param formTitle
         * @return
         */
        List<TableMultipleItem> loadDynamicLayoutData(List<DynamicLayoutBean> arrays,String  formTitle);

        /**
         * 签批日常或者工具类表单
         * @param recordId
         * @param checker
         * @param tag
         */
        void signCommonFormTask(int recordId,int checker,String tag);

        /**
         * 抄送我的
         * @param userId
         * @param offset
         * @param limit
         */
        void  formTaskCopyedToMe(int userId,int offset,int limit,String tag);
        /**
         * 我签批的
         * @param userId
         * @param offset
         * @param limit
         */
        void  formTaskSignedToMe(int userId,int offset,int limit,String tag);
        /**
         * 我提交的
         * @param userId
         * @param offset
         * @param limit
         */
        void  formTaskCommitOfMine(int userId,int offset,int limit,String tag);

        /**
         * 获取自动报表
         * @param autoCheckId
         * @param tag
         */
        void getTaskListOfAutoForm(int autoCheckId,String  tag);
        /**
         * 签批自动报表
         * @param autoCheckId
         * @param tag
         */
        void signAutoForm(int autoCheckId,int checkUser,String  tag);

    }

    interface ISignTableModel {
        /**
         * 获取表单任务
         * @param userId
         */
        void getTableTasks(int userId, String tag, RequestStatus requestStatus);

    }
}
