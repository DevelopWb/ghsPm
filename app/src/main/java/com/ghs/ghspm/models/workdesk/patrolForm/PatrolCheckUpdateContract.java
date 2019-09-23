package com.ghs.ghspm.models.workdesk.patrolForm;

import com.ghs.ghspm.base.BaseViewInterface;

import java.util.Map;

/**
 * Author:wang_sir
 * Time:2019/4/23 14:12
 * Description:This is PatrolCheckUpdateContract
 */
public interface PatrolCheckUpdateContract {
    String PATROL_FORM_TASK_DEAL_BEAN = "PatrolTaskFormListBean.DataBean";//taskId
    String FORMFILLING = "form_filling";//正在填写的表单
    String FORM_SUBUMIT = "form_submits";//已经填写的表单
    String FORM_SUBMIT_SUCCEEF = "form_submit";//上交表单
    String FORM_CONTINUE = "form_continue";//继续编辑表单
    String FORM_UPDATA ="form_updata";

    interface IPatrolCheckUpdateView extends BaseViewInterface {
    }

    interface IPatrolCheckUpdatePresent {
        void getPatrolFormList(int propertyId, int villageId, int userId, String tag);

        void savePatrolFormTask(Map<String, String> map, String tag);

        void getFormFillingList(int villageId, int formId, String tag);

        void getPatrolFormTaskRecordList(int taskId, String tag);

        void getFormSubmitList(int villageId, int formId, String tag);



        //提交表单
        void submitForm(int taskId,int handlerId ,String tag);

        //继续填写表单
        void continueForm(int taskId,int handler, String tag);
        //修改表单信息
        void updataForm(int id,String taskName,int formId,String showDateValue,String headerTitle1Value,String headerTitle2Value,String headerTitle3Value,String headerTitle4Value,String footerTitle1Value ,String footerTitle2Value,String footerTitle3Value,String footerTitle4Value,String updateUserId,String tag );

    }
}
