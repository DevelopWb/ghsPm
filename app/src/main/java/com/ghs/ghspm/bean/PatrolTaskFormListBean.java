package com.ghs.ghspm.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Author:wang_sir
 * Time:2019/4/25 18:47
 * Description:This is PatrolTaskFormListBean
 *
 * 巡检任务表单列表  应用于正在提交的表单数据  已经提交的表单数据
 */
public class PatrolTaskFormListBean {

    /**
     * code : 1000
     * message : 成功
     * data : [{"id":89,"villageId":27,"taskName":"1642","formId":74,"status":1,"submitUserId":null,"submitTime":null,"createUserId":38,"createTime":"2019-05-05 16:42:10","updateUserId":38,"updateTime":"2019-05-05 16:42:10","showDateValue":null,"headerTitle1Value":null,"headerTitle2Value":null,"headerTitle3Value":null,"headerTitle4Value":null,"footerTitle1Value":null,"footerTitle2Value":null,"footerTitle3Value":null,"footerTitle4Value":null,"submitUserName":null,"formName":"测试表单"},{"id":88,"villageId":27,"taskName":"1641","formId":74,"status":1,"submitUserId":null,"submitTime":null,"createUserId":38,"createTime":"2019-05-05 16:41:31","updateUserId":38,"updateTime":"2019-05-05 16:41:31","showDateValue":null,"headerTitle1Value":null,"headerTitle2Value":null,"headerTitle3Value":null,"headerTitle4Value":null,"footerTitle1Value":null,"footerTitle2Value":null,"footerTitle3Value":null,"footerTitle4Value":null,"submitUserName":null,"formName":"测试表单"},{"id":87,"villageId":27,"taskName":"201905051636","formId":74,"status":1,"submitUserId":null,"submitTime":null,"createUserId":38,"createTime":"2019-05-05 16:36:44","updateUserId":38,"updateTime":"2019-05-05 16:36:44","showDateValue":"2019年06月","headerTitle1Value":null,"headerTitle2Value":null,"headerTitle3Value":null,"headerTitle4Value":null,"footerTitle1Value":null,"footerTitle2Value":null,"footerTitle3Value":null,"footerTitle4Value":null,"submitUserName":null,"formName":"测试表单"},{"id":86,"villageId":27,"taskName":"20190505","formId":74,"status":1,"submitUserId":null,"submitTime":null,"createUserId":38,"createTime":"2019-05-05 16:34:01","updateUserId":38,"updateTime":"2019-05-05 16:34:01","showDateValue":"2019年01月","headerTitle1Value":"122","headerTitle2Value":"233","headerTitle3Value":null,"headerTitle4Value":null,"footerTitle1Value":"王彬","footerTitle2Value":null,"footerTitle3Value":null,"footerTitle4Value":null,"submitUserName":null,"formName":"测试表单"},{"id":73,"villageId":27,"taskName":"五百块钱","formId":74,"status":1,"submitUserId":null,"submitTime":null,"createUserId":40,"createTime":"2019-04-30 16:49:44","updateUserId":40,"updateTime":"2019-05-05 11:19:48","showDateValue":"2019年06月","headerTitle1Value":null,"headerTitle2Value":null,"headerTitle3Value":null,"headerTitle4Value":null,"footerTitle1Value":null,"footerTitle2Value":null,"footerTitle3Value":null,"footerTitle4Value":null,"submitUserName":null,"formName":"测试表单"},{"id":72,"villageId":27,"taskName":"我的表格","formId":74,"status":1,"submitUserId":null,"submitTime":null,"createUserId":49,"createTime":"2019-04-30 16:48:01","updateUserId":49,"updateTime":"2019-04-30 16:48:01","showDateValue":"2019年04月","headerTitle1Value":"我","headerTitle2Value":"我","headerTitle3Value":null,"headerTitle4Value":null,"footerTitle1Value":"陈佩","footerTitle2Value":"陈佩","footerTitle3Value":null,"footerTitle4Value":null,"submitUserName":null,"formName":"测试表单"},{"id":71,"villageId":27,"taskName":"新表单","formId":74,"status":1,"submitUserId":null,"submitTime":null,"createUserId":40,"createTime":"2019-04-30 16:47:39","updateUserId":40,"updateTime":"2019-04-30 16:47:39","showDateValue":"2019年07月","headerTitle1Value":null,"headerTitle2Value":null,"headerTitle3Value":null,"headerTitle4Value":null,"footerTitle1Value":null,"footerTitle2Value":null,"footerTitle3Value":null,"footerTitle4Value":null,"submitUserName":null,"formName":"测试表单"},{"id":70,"villageId":27,"taskName":"提取新表单","formId":74,"status":1,"submitUserId":null,"submitTime":null,"createUserId":40,"createTime":"2019-04-30 16:25:51","updateUserId":40,"updateTime":"2019-04-30 16:31:06","showDateValue":"2019年05月","headerTitle1Value":"66","headerTitle2Value":"","headerTitle3Value":"","headerTitle4Value":"","footerTitle1Value":"确认","footerTitle2Value":"确认","footerTitle3Value":"确认","footerTitle4Value":"确认","submitUserName":null,"formName":"测试表单"},{"id":69,"villageId":27,"taskName":"QWERTY ","formId":74,"status":1,"submitUserId":null,"submitTime":null,"createUserId":40,"createTime":"2019-04-30 16:20:03","updateUserId":40,"updateTime":"2019-04-30 16:21:11","showDateValue":"2019年08月","headerTitle1Value":"","headerTitle2Value":"","headerTitle3Value":"","headerTitle4Value":"","footerTitle1Value":"","footerTitle2Value":"","footerTitle3Value":"","footerTitle4Value":"","submitUserName":null,"formName":"测试表单"},{"id":68,"villageId":27,"taskName":"啊啊啊","formId":74,"status":1,"submitUserId":null,"submitTime":null,"createUserId":40,"createTime":"2019-04-30 16:15:08","updateUserId":40,"updateTime":"2019-04-30 16:15:08","showDateValue":"2019年07月","headerTitle1Value":null,"headerTitle2Value":null,"headerTitle3Value":null,"headerTitle4Value":null,"footerTitle1Value":null,"footerTitle2Value":null,"footerTitle3Value":null,"footerTitle4Value":null,"submitUserName":null,"formName":"测试表单"},{"id":67,"villageId":27,"taskName":"1111","formId":74,"status":1,"submitUserId":null,"submitTime":null,"createUserId":40,"createTime":"2019-04-30 15:55:03","updateUserId":40,"updateTime":"2019-04-30 15:55:03","showDateValue":null,"headerTitle1Value":null,"headerTitle2Value":null,"headerTitle3Value":null,"headerTitle4Value":null,"footerTitle1Value":null,"footerTitle2Value":null,"footerTitle3Value":null,"footerTitle4Value":null,"submitUserName":null,"formName":"测试表单"},{"id":66,"villageId":27,"taskName":"应用","formId":74,"status":1,"submitUserId":null,"submitTime":null,"createUserId":40,"createTime":"2019-04-30 15:46:39","updateUserId":40,"updateTime":"2019-04-30 15:46:39","showDateValue":"2019年04月","headerTitle1Value":"FDF","headerTitle2Value":null,"headerTitle3Value":null,"headerTitle4Value":null,"footerTitle1Value":null,"footerTitle2Value":null,"footerTitle3Value":null,"footerTitle4Value":null,"submitUserName":null,"formName":"测试表单"},{"id":65,"villageId":27,"taskName":"测试","formId":74,"status":1,"submitUserId":null,"submitTime":null,"createUserId":40,"createTime":"2019-04-30 15:01:45","updateUserId":38,"updateTime":"2019-05-05 10:24:47","showDateValue":"","headerTitle1Value":"","headerTitle2Value":"","headerTitle3Value":"","headerTitle4Value":"","footerTitle1Value":"","footerTitle2Value":"","footerTitle3Value":"","footerTitle4Value":"","submitUserName":null,"formName":"测试表单"},{"id":64,"villageId":27,"taskName":"测试6","formId":74,"status":1,"submitUserId":null,"submitTime":null,"createUserId":40,"createTime":"2019-04-30 14:31:01","updateUserId":40,"updateTime":"2019-04-30 15:43:13","showDateValue":"2019年04月","headerTitle1Value":"啊啊啊啊","headerTitle2Value":"","headerTitle3Value":"","headerTitle4Value":"","footerTitle1Value":"","footerTitle2Value":"","footerTitle3Value":"","footerTitle4Value":"","submitUserName":null,"formName":"测试表单"},{"id":63,"villageId":27,"taskName":"测试666","formId":74,"status":1,"submitUserId":null,"submitTime":null,"createUserId":40,"createTime":"2019-04-30 14:28:23","updateUserId":40,"updateTime":"2019-04-30 14:28:23","showDateValue":"2019年04月","headerTitle1Value":null,"headerTitle2Value":null,"headerTitle3Value":null,"headerTitle4Value":null,"footerTitle1Value":null,"footerTitle2Value":null,"footerTitle3Value":null,"footerTitle4Value":null,"submitUserName":null,"formName":"测试表单"},{"id":51,"villageId":27,"taskName":"这","formId":74,"status":1,"submitUserId":null,"submitTime":null,"createUserId":49,"createTime":"2019-04-30 11:02:14","updateUserId":19,"updateTime":"2019-04-30 12:43:13","showDateValue":"2019年04月","headerTitle1Value":"1","headerTitle2Value":"1","headerTitle3Value":null,"headerTitle4Value":null,"footerTitle1Value":"1","footerTitle2Value":"1","footerTitle3Value":null,"footerTitle4Value":null,"submitUserName":null,"formName":"测试表单"},{"id":50,"villageId":27,"taskName":"测试表单57788","formId":74,"status":1,"submitUserId":null,"submitTime":null,"createUserId":40,"createTime":"2019-04-30 10:53:22","updateUserId":40,"updateTime":"2019-04-30 14:15:38","showDateValue":"2019年07月","headerTitle1Value":"三国无双","headerTitle2Value":"认识一下","headerTitle3Value":"","headerTitle4Value":"","footerTitle1Value":"确认人","footerTitle2Value":"确认人","footerTitle3Value":"确认人","footerTitle4Value":"确认人","submitUserName":null,"formName":"测试表单"},{"id":49,"villageId":27,"taskName":"ios6","formId":74,"status":1,"submitUserId":null,"submitTime":null,"createUserId":19,"createTime":"2019-04-30 10:52:28","updateUserId":19,"updateTime":"2019-04-30 10:52:28","showDateValue":null,"headerTitle1Value":null,"headerTitle2Value":null,"headerTitle3Value":null,"headerTitle4Value":null,"footerTitle1Value":null,"footerTitle2Value":null,"footerTitle3Value":null,"footerTitle4Value":null,"submitUserName":null,"formName":"测试表单"},{"id":47,"villageId":27,"taskName":"ios5","formId":74,"status":1,"submitUserId":null,"submitTime":null,"createUserId":19,"createTime":"2019-04-30 10:38:01","updateUserId":19,"updateTime":"2019-04-30 10:38:01","showDateValue":null,"headerTitle1Value":null,"headerTitle2Value":null,"headerTitle3Value":null,"headerTitle4Value":null,"footerTitle1Value":null,"footerTitle2Value":null,"footerTitle3Value":null,"footerTitle4Value":null,"submitUserName":null,"formName":"测试表单"},{"id":46,"villageId":27,"taskName":"iOS3","formId":74,"status":1,"submitUserId":null,"submitTime":null,"createUserId":19,"createTime":"2019-04-30 10:33:09","updateUserId":19,"updateTime":"2019-04-30 10:33:09","showDateValue":null,"headerTitle1Value":null,"headerTitle2Value":null,"headerTitle3Value":null,"headerTitle4Value":null,"footerTitle1Value":null,"footerTitle2Value":null,"footerTitle3Value":null,"footerTitle4Value":null,"submitUserName":null,"formName":"测试表单"},{"id":45,"villageId":27,"taskName":"iOS2","formId":74,"status":1,"submitUserId":null,"submitTime":null,"createUserId":19,"createTime":"2019-04-30 10:28:41","updateUserId":19,"updateTime":"2019-04-30 10:28:41","showDateValue":null,"headerTitle1Value":null,"headerTitle2Value":null,"headerTitle3Value":null,"headerTitle4Value":null,"footerTitle1Value":null,"footerTitle2Value":null,"footerTitle3Value":null,"footerTitle4Value":null,"submitUserName":null,"formName":"测试表单"},{"id":44,"villageId":27,"taskName":"iOS1","formId":74,"status":1,"submitUserId":null,"submitTime":null,"createUserId":19,"createTime":"2019-04-30 10:27:39","updateUserId":19,"updateTime":"2019-04-30 10:27:39","showDateValue":null,"headerTitle1Value":null,"headerTitle2Value":null,"headerTitle3Value":null,"headerTitle4Value":null,"footerTitle1Value":null,"footerTitle2Value":null,"footerTitle3Value":null,"footerTitle4Value":null,"submitUserName":null,"formName":"测试表单"},{"id":43,"villageId":27,"taskName":"测试表单102","formId":74,"status":1,"submitUserId":null,"submitTime":null,"createUserId":40,"createTime":"2019-04-30 10:23:38","updateUserId":40,"updateTime":"2019-04-30 10:23:38","showDateValue":"2019年05月","headerTitle1Value":null,"headerTitle2Value":null,"headerTitle3Value":null,"headerTitle4Value":null,"footerTitle1Value":null,"footerTitle2Value":null,"footerTitle3Value":null,"footerTitle4Value":null,"submitUserName":null,"formName":"测试表单"},{"id":42,"villageId":27,"taskName":"三国演义","formId":74,"status":1,"submitUserId":null,"submitTime":null,"createUserId":40,"createTime":"2019-04-30 10:19:08","updateUserId":40,"updateTime":"2019-04-30 10:19:08","showDateValue":"2019年01月","headerTitle1Value":"国家","headerTitle2Value":null,"headerTitle3Value":null,"headerTitle4Value":null,"footerTitle1Value":null,"footerTitle2Value":null,"footerTitle3Value":null,"footerTitle4Value":null,"submitUserName":null,"formName":"测试表单"},{"id":41,"villageId":27,"taskName":"测试表单101","formId":74,"status":1,"submitUserId":null,"submitTime":null,"createUserId":40,"createTime":"2019-04-30 10:01:15","updateUserId":40,"updateTime":"2019-04-30 10:18:16","showDateValue":"2019年-04月","headerTitle1Value":"123486","headerTitle2Value":"366","headerTitle3Value":null,"headerTitle4Value":null,"footerTitle1Value":null,"footerTitle2Value":null,"footerTitle3Value":null,"footerTitle4Value":null,"submitUserName":null,"formName":"测试表单"},{"id":40,"villageId":27,"taskName":"测试一下1","formId":74,"status":1,"submitUserId":null,"submitTime":null,"createUserId":31,"createTime":"2019-04-29 20:29:08","updateUserId":40,"updateTime":"2019-04-30 13:22:21","showDateValue":"2019年-04月","headerTitle1Value":"记录号","headerTitle2Value":"口号","headerTitle3Value":"","headerTitle4Value":"","footerTitle1Value":"确认人","footerTitle2Value":"确认人","footerTitle3Value":"确认人","footerTitle4Value":"确认人","submitUserName":null,"formName":"测试表单"},{"id":39,"villageId":27,"taskName":"测试一下","formId":74,"status":1,"submitUserId":null,"submitTime":null,"createUserId":40,"createTime":"2019-04-29 20:24:30","updateUserId":40,"updateTime":"2019-04-29 20:24:30","showDateValue":"2019年-04月","headerTitle1Value":null,"headerTitle2Value":null,"headerTitle3Value":null,"headerTitle4Value":null,"footerTitle1Value":null,"footerTitle2Value":null,"footerTitle3Value":null,"footerTitle4Value":null,"submitUserName":null,"formName":"测试表单"},{"id":38,"villageId":27,"taskName":"666","formId":74,"status":1,"submitUserId":null,"submitTime":null,"createUserId":40,"createTime":"2019-04-29 20:21:04","updateUserId":40,"updateTime":"2019-04-29 20:21:04","showDateValue":null,"headerTitle1Value":null,"headerTitle2Value":null,"headerTitle3Value":null,"headerTitle4Value":null,"footerTitle1Value":null,"footerTitle2Value":null,"footerTitle3Value":null,"footerTitle4Value":null,"submitUserName":null,"formName":"测试表单"},{"id":37,"villageId":27,"taskName":"666","formId":74,"status":1,"submitUserId":null,"submitTime":null,"createUserId":40,"createTime":"2019-04-29 20:19:03","updateUserId":40,"updateTime":"2019-04-29 20:19:03","showDateValue":null,"headerTitle1Value":null,"headerTitle2Value":null,"headerTitle3Value":null,"headerTitle4Value":null,"footerTitle1Value":null,"footerTitle2Value":null,"footerTitle3Value":null,"footerTitle4Value":null,"submitUserName":null,"formName":"测试表单"},{"id":36,"villageId":27,"taskName":"莫得了","formId":74,"status":1,"submitUserId":null,"submitTime":null,"createUserId":40,"createTime":"2019-04-29 20:18:33","updateUserId":40,"updateTime":"2019-04-29 20:18:33","showDateValue":"2019年-04月","headerTitle1Value":null,"headerTitle2Value":null,"headerTitle3Value":null,"headerTitle4Value":null,"footerTitle1Value":null,"footerTitle2Value":null,"footerTitle3Value":null,"footerTitle4Value":null,"submitUserName":null,"formName":"测试表单"},{"id":35,"villageId":27,"taskName":"F默默哦DF","formId":74,"status":1,"submitUserId":null,"submitTime":null,"createUserId":31,"createTime":"2019-04-29 19:46:37","updateUserId":31,"updateTime":"2019-04-29 19:46:37","showDateValue":"2019年-04月","headerTitle1Value":null,"headerTitle2Value":null,"headerTitle3Value":null,"headerTitle4Value":null,"footerTitle1Value":null,"footerTitle2Value":null,"footerTitle3Value":null,"footerTitle4Value":null,"submitUserName":null,"formName":"测试表单"},{"id":34,"villageId":27,"taskName":"1","formId":74,"status":1,"submitUserId":null,"submitTime":null,"createUserId":31,"createTime":"2019-04-29 19:46:06","updateUserId":31,"updateTime":"2019-04-29 19:46:06","showDateValue":null,"headerTitle1Value":null,"headerTitle2Value":null,"headerTitle3Value":null,"headerTitle4Value":null,"footerTitle1Value":null,"footerTitle2Value":null,"footerTitle3Value":null,"footerTitle4Value":null,"submitUserName":null,"formName":"测试表单"},{"id":33,"villageId":27,"taskName":"FDF","formId":74,"status":1,"submitUserId":null,"submitTime":null,"createUserId":40,"createTime":"2019-04-29 19:45:04","updateUserId":40,"updateTime":"2019-04-29 19:45:04","showDateValue":"2019年-04月","headerTitle1Value":null,"headerTitle2Value":null,"headerTitle3Value":null,"headerTitle4Value":null,"footerTitle1Value":null,"footerTitle2Value":null,"footerTitle3Value":null,"footerTitle4Value":null,"submitUserName":null,"formName":"测试表单"},{"id":32,"villageId":27,"taskName":"wjf","formId":74,"status":1,"submitUserId":null,"submitTime":null,"createUserId":31,"createTime":"2019-04-29 19:37:02","updateUserId":31,"updateTime":"2019-04-29 19:37:02","showDateValue":"2019年04月","headerTitle1Value":"ggfxchh","headerTitle2Value":"老老实实","headerTitle3Value":null,"headerTitle4Value":null,"footerTitle1Value":null,"footerTitle2Value":null,"footerTitle3Value":null,"footerTitle4Value":null,"submitUserName":null,"formName":"测试表单"},{"id":23,"villageId":27,"taskName":"111","formId":74,"status":1,"submitUserId":null,"submitTime":null,"createUserId":40,"createTime":"2019-04-29 19:01:14","updateUserId":40,"updateTime":"2019-04-29 19:01:14","showDateValue":null,"headerTitle1Value":null,"headerTitle2Value":null,"headerTitle3Value":null,"headerTitle4Value":null,"footerTitle1Value":null,"footerTitle2Value":null,"footerTitle3Value":null,"footerTitle4Value":null,"submitUserName":null,"formName":"测试表单"},{"id":10,"villageId":27,"taskName":"iOS测试勿修改","formId":74,"status":1,"submitUserId":null,"submitTime":null,"createUserId":40,"createTime":"2019-04-29 17:29:27","updateUserId":19,"updateTime":"2019-04-29 18:54:06","showDateValue":null,"headerTitle1Value":null,"headerTitle2Value":null,"headerTitle3Value":null,"headerTitle4Value":null,"footerTitle1Value":null,"footerTitle2Value":null,"footerTitle3Value":null,"footerTitle4Value":null,"submitUserName":null,"formName":"测试表单"},{"id":9,"villageId":27,"taskName":"FDF","formId":74,"status":1,"submitUserId":null,"submitTime":null,"createUserId":40,"createTime":"2019-04-29 17:25:38","updateUserId":40,"updateTime":"2019-04-29 17:25:40","showDateValue":null,"headerTitle1Value":null,"headerTitle2Value":null,"headerTitle3Value":null,"headerTitle4Value":null,"footerTitle1Value":null,"footerTitle2Value":null,"footerTitle3Value":null,"footerTitle4Value":null,"submitUserName":null,"formName":"测试表单"},{"id":8,"villageId":27,"taskName":"FDF","formId":74,"status":1,"submitUserId":null,"submitTime":null,"createUserId":40,"createTime":"2019-04-29 17:25:07","updateUserId":40,"updateTime":"2019-04-30 11:25:02","showDateValue":"","headerTitle1Value":"记录号","headerTitle2Value":"口号","headerTitle3Value":"表头3","headerTitle4Value":"表头4","footerTitle1Value":"确认人","footerTitle2Value":"签批人","footerTitle3Value":"表尾3","footerTitle4Value":"签批人","submitUserName":null,"formName":"测试表单"},{"id":6,"villageId":27,"taskName":"测试5","formId":74,"status":1,"submitUserId":null,"submitTime":null,"createUserId":40,"createTime":"2019-04-29 16:34:25","updateUserId":38,"updateTime":"2019-05-05 10:48:42","showDateValue":"2019年-04月","headerTitle1Value":"","headerTitle2Value":"","headerTitle3Value":"","headerTitle4Value":"","footerTitle1Value":"","footerTitle2Value":"","footerTitle3Value":"","footerTitle4Value":"","submitUserName":null,"formName":"测试表单"},{"id":5,"villageId":27,"taskName":"测试","formId":74,"status":1,"submitUserId":null,"submitTime":null,"createUserId":40,"createTime":"2019-04-29 16:26:51","updateUserId":40,"updateTime":"2019-04-29 16:28:39","showDateValue":"2019年-04月","headerTitle1Value":"噢噢噢哦哦噢噢噢噢噢噢哦哦噢噢噢","headerTitle2Value":null,"headerTitle3Value":null,"headerTitle4Value":null,"footerTitle1Value":null,"footerTitle2Value":null,"footerTitle3Value":null,"footerTitle4Value":null,"submitUserName":null,"formName":"测试表单"},{"id":3,"villageId":27,"taskName":"检查卫生","formId":74,"status":1,"submitUserId":null,"submitTime":null,"createUserId":19,"createTime":"2019-04-29 14:52:34","updateUserId":19,"updateTime":"2019-04-29 16:18:36","showDateValue":"2019年04月","headerTitle1Value":"98708","headerTitle2Value":"我们是冠军","headerTitle3Value":null,"headerTitle4Value":null,"footerTitle1Value":"小马哥","footerTitle2Value":"马云","footerTitle3Value":null,"footerTitle4Value":null,"submitUserName":null,"formName":"测试表单"}]
     */

    private int code;
    private String message;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Parcelable {
        /**
         * id : 89
         * villageId : 27
         * taskName : 1642
         * formId : 74
         * status : 1
         * submitUserId : null
         * submitTime : null
         * createUserId : 38
         * createTime : 2019-05-05 16:42:10
         * updateUserId : 38
         * updateTime : 2019-05-05 16:42:10
         * showDateValue : null
         * headerTitle1Value : null
         * headerTitle2Value : null
         * headerTitle3Value : null
         * headerTitle4Value : null
         * footerTitle1Value : null
         * footerTitle2Value : null
         * footerTitle3Value : null
         * footerTitle4Value : null
         * submitUserName : null
         * formName : 测试表单
         */

        private int id;
        private int villageId;
        private String taskName;
        private int formId;
        private int status;
        private int submitUserId;
        private String submitTime;
        private int createUserId;
        private String createTime;
        private int updateUserId;
        private String updateTime;
        private String showDateValue;
        private String headerTitle1Value;
        private String headerTitle2Value;
        private String headerTitle3Value;
        private String headerTitle4Value;
        private String footerTitle1Value;
        private String footerTitle2Value;
        private String footerTitle3Value;
        private String footerTitle4Value;
        private String submitUserName;
        private String formName;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getVillageId() {
            return villageId;
        }

        public void setVillageId(int villageId) {
            this.villageId = villageId;
        }

        public String getTaskName() {
            return taskName == null ? "" : taskName;
        }

        public void setTaskName(String taskName) {
            this.taskName = taskName;
        }

        public int getFormId() {
            return formId;
        }

        public void setFormId(int formId) {
            this.formId = formId;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getSubmitUserId() {
            return submitUserId;
        }

        public void setSubmitUserId(int submitUserId) {
            this.submitUserId = submitUserId;
        }

        public String getSubmitTime() {
            return submitTime == null ? "" : submitTime;
        }

        public void setSubmitTime(String submitTime) {
            this.submitTime = submitTime;
        }

        public int getCreateUserId() {
            return createUserId;
        }

        public void setCreateUserId(int createUserId) {
            this.createUserId = createUserId;
        }

        public String getCreateTime() {
            return createTime == null ? "" : createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public int getUpdateUserId() {
            return updateUserId;
        }

        public void setUpdateUserId(int updateUserId) {
            this.updateUserId = updateUserId;
        }

        public String getUpdateTime() {
            return updateTime == null ? "" : updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public String getShowDateValue() {
            return showDateValue == null ? "" : showDateValue;
        }

        public void setShowDateValue(String showDateValue) {
            this.showDateValue = showDateValue;
        }

        public String getHeaderTitle1Value() {
            return headerTitle1Value == null ? "" : headerTitle1Value;
        }

        public void setHeaderTitle1Value(String headerTitle1Value) {
            this.headerTitle1Value = headerTitle1Value;
        }

        public String getHeaderTitle2Value() {
            return headerTitle2Value == null ? "" : headerTitle2Value;
        }

        public void setHeaderTitle2Value(String headerTitle2Value) {
            this.headerTitle2Value = headerTitle2Value;
        }

        public String getHeaderTitle3Value() {
            return headerTitle3Value == null ? "" : headerTitle3Value;
        }

        public void setHeaderTitle3Value(String headerTitle3Value) {
            this.headerTitle3Value = headerTitle3Value;
        }

        public String getHeaderTitle4Value() {
            return headerTitle4Value == null ? "" : headerTitle4Value;
        }

        public void setHeaderTitle4Value(String headerTitle4Value) {
            this.headerTitle4Value = headerTitle4Value;
        }

        public String getFooterTitle1Value() {
            return footerTitle1Value == null ? "" : footerTitle1Value;
        }

        public void setFooterTitle1Value(String footerTitle1Value) {
            this.footerTitle1Value = footerTitle1Value;
        }

        public String getFooterTitle2Value() {
            return footerTitle2Value == null ? "" : footerTitle2Value;
        }

        public void setFooterTitle2Value(String footerTitle2Value) {
            this.footerTitle2Value = footerTitle2Value;
        }

        public String getFooterTitle3Value() {
            return footerTitle3Value == null ? "" : footerTitle3Value;
        }

        public void setFooterTitle3Value(String footerTitle3Value) {
            this.footerTitle3Value = footerTitle3Value;
        }

        public String getFooterTitle4Value() {
            return footerTitle4Value == null ? "" : footerTitle4Value;
        }

        public void setFooterTitle4Value(String footerTitle4Value) {
            this.footerTitle4Value = footerTitle4Value;
        }

        public String getSubmitUserName() {
            return submitUserName == null ? "" : submitUserName;
        }

        public void setSubmitUserName(String submitUserName) {
            this.submitUserName = submitUserName;
        }

        public String getFormName() {
            return formName == null ? "" : formName;
        }

        public void setFormName(String formName) {
            this.formName = formName;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.id);
            dest.writeInt(this.villageId);
            dest.writeString(this.taskName);
            dest.writeInt(this.formId);
            dest.writeInt(this.status);
            dest.writeInt(this.submitUserId);
            dest.writeString(this.submitTime);
            dest.writeInt(this.createUserId);
            dest.writeString(this.createTime);
            dest.writeInt(this.updateUserId);
            dest.writeString(this.updateTime);
            dest.writeString(this.showDateValue);
            dest.writeString(this.headerTitle1Value);
            dest.writeString(this.headerTitle2Value);
            dest.writeString(this.headerTitle3Value);
            dest.writeString(this.headerTitle4Value);
            dest.writeString(this.footerTitle1Value);
            dest.writeString(this.footerTitle2Value);
            dest.writeString(this.footerTitle3Value);
            dest.writeString(this.footerTitle4Value);
            dest.writeString(this.submitUserName);
            dest.writeString(this.formName);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.id = in.readInt();
            this.villageId = in.readInt();
            this.taskName = in.readString();
            this.formId = in.readInt();
            this.status = in.readInt();
            this.submitUserId = in.readInt();
            this.submitTime = in.readString();
            this.createUserId = in.readInt();
            this.createTime = in.readString();
            this.updateUserId = in.readInt();
            this.updateTime = in.readString();
            this.showDateValue = in.readString();
            this.headerTitle1Value = in.readString();
            this.headerTitle2Value = in.readString();
            this.headerTitle3Value = in.readString();
            this.headerTitle4Value = in.readString();
            this.footerTitle1Value = in.readString();
            this.footerTitle2Value = in.readString();
            this.footerTitle3Value = in.readString();
            this.footerTitle4Value = in.readString();
            this.submitUserName = in.readString();
            this.formName = in.readString();
        }

        public static final Parcelable.Creator<DataBean> CREATOR = new Parcelable.Creator<DataBean>() {
            @Override
            public DataBean createFromParcel(Parcel source) {
                return new DataBean(source);
            }

            @Override
            public DataBean[] newArray(int size) {
                return new DataBean[size];
            }
        };
    }
}
