package com.ghs.ghspm.bean;

import java.util.List;

/**
 * created by tobato
 * created date 2019/4/25 16:30.
 * application   编辑表单的实体类
 */
public class PatrolFormTaskBean {

    /**
     * code : 1000
     * message : 成功
     * data : [{"id":2046,"villageId":27,"formId":74,"taskId":26,"contentJson":"[{\"title\":\"单文本输入框\",\"cssClass\":\"text\",\"prevalue\":\"我\"},{\"title\":\"多文本输入框\",\"cssClass\":\"textarea\",\"prevalue\":\"\"},{\"title\":\"数字输入框\",\"cssClass\":\"number\",\"prevalue\":\"123\"},{\"title\":\"多项选择框\",\"cssClass\":\"multiple_select\",\"prevalue\":\"选项一\"},{\"title\":\"金额输入框\",\"cssClass\":\"money\",\"prevalue\":\"999\"},{\"title\":\"人员选择框\",\"cssClass\":\"user\",\"prevalue\":\"王明申\"},{\"title\":\"图片现在框\",\"cssClass\":\"image\",\"prevalue\":\"2019042517160888310022.jpeg\"},{\"title\":\"日期选择框\",\"cssClass\":\"date\",\"prevalue\":\"2019-04-25\"},{\"title\":\"楼栋选择框\",\"cssClass\":\"tower\",\"prevalue\":\"一期1号楼\"},{\"title\":\"单元选择框\",\"cssClass\":\"cell\",\"prevalue\":\"1单元\"},{\"title\":\"室号单选框\",\"cssClass\":\"room\",\"prevalue\":\"1\"}]","status":null,"handleTime":null,"checkerTime":null,"createTime":"2019-04-25 17:16:09","updateTime":"2019-04-25 17:16:09","formName":"测试表单","introduction":"测试专用\r\n","usage":"表单必须认真填写，否则不予审批","formType":1,"fieldJson":"[{\"min\":\"1\",\"cssClass\":\"text\",\"max\":\"500\",\"fieldesc\":\"请输入\",\"title\":\"单文本输入框\",\"required\":\"required\"},{\"min\":\"1\",\"cssClass\":\"textarea\",\"max\":\"3000\",\"fieldesc\":\"请输入\",\"title\":\"多文本输入框\",\"required\":\"\"},{\"min\":\"0\",\"cssClass\":\"number\",\"max\":\"1000000000\",\"fieldesc\":\"请输入数字\",\"title\":\"数字输入框\",\"required\":\"required\"},{\"min\":\"\",\"cssClass\":\"multiple_select\",\"max\":\"\",\"children\":[\"选项一\",\"选项二\",\"选项三\"],\"fieldesc\":\"请选择\",\"title\":\"多项选择框\",\"required\":\"required\"},{\"min\":\"0\",\"cssClass\":\"money\",\"max\":\"1000000000\",\"fieldesc\":\"请输入金额\",\"title\":\"金额输入框\",\"required\":\"required\"},{\"min\":\"\",\"cssClass\":\"user\",\"max\":\"\",\"fieldesc\":\"请选择人员\",\"title\":\"人员选择框\",\"required\":\"required\"},{\"min\":\"\",\"cssClass\":\"image\",\"max\":\"9\",\"fieldesc\":\"请选择图片\",\"title\":\"图片现在框\",\"required\":\"required\"},{\"min\":\"\",\"cssClass\":\"date\",\"max\":\"\",\"format\":\"yyyy-MM-dd\",\"fieldesc\":\"请选择日期\",\"title\":\"日期选择框\",\"required\":\"required\"},{\"min\":\"\",\"cssClass\":\"tower\",\"max\":\"\",\"fieldesc\":\"请选择楼栋\",\"title\":\"楼栋选择框\",\"required\":\"required\"},{\"min\":\"\",\"cssClass\":\"cell\",\"max\":\"\",\"fieldesc\":\"请选择单元\",\"title\":\"单元选择框\",\"required\":\"required\"},{\"min\":\"\",\"cssClass\":\"room\",\"max\":\"\",\"fieldesc\":\"请选择室号\",\"title\":\"室号单选框\",\"required\":\"required\"}]","auto":2,"taskName":null,"taskDescription":null,"taskUpdateUserId":null,"taskUpdateUserName":null,"handlerName":null,"checkerName":null,"ccName":null,"checkerId":null},{"id":2045,"villageId":27,"formId":74,"taskId":26,"contentJson":"[{\"title\":\"单文本输入框\",\"cssClass\":\"text\",\"prevalue\":\"我是苹果\"},{\"title\":\"多文本输入框\",\"cssClass\":\"textarea\",\"prevalue\":\"哈哈\"},{\"title\":\"数字输入框\",\"cssClass\":\"number\",\"prevalue\":\"666\"},{\"title\":\"多项选择框\",\"cssClass\":\"multiple_select\",\"prevalue\":\"选项一\"},{\"title\":\"金额输入框\",\"cssClass\":\"money\",\"prevalue\":\"999\"},{\"title\":\"人员选择框\",\"cssClass\":\"user\",\"prevalue\":\"王明申\"},{\"title\":\"图片现在框\",\"cssClass\":\"image\",\"prevalue\":\"2019042517134411710011.jpeg\"},{\"title\":\"日期选择框\",\"cssClass\":\"date\",\"prevalue\":\"2019-04-25\"},{\"title\":\"楼栋选择框\",\"cssClass\":\"tower\",\"prevalue\":\"一期1号楼\"},{\"title\":\"单元选择框\",\"cssClass\":\"cell\",\"prevalue\":\"1单元\"},{\"title\":\"室号单选框\",\"cssClass\":\"room\",\"prevalue\":\"1\"}]","status":null,"handleTime":null,"checkerTime":null,"createTime":"2019-04-25 17:13:44","updateTime":"2019-04-25 17:13:44","formName":"测试表单","introduction":"测试专用\r\n","usage":"表单必须认真填写，否则不予审批","formType":1,"fieldJson":"[{\"min\":\"1\",\"cssClass\":\"text\",\"max\":\"500\",\"fieldesc\":\"请输入\",\"title\":\"单文本输入框\",\"required\":\"required\"},{\"min\":\"1\",\"cssClass\":\"textarea\",\"max\":\"3000\",\"fieldesc\":\"请输入\",\"title\":\"多文本输入框\",\"required\":\"\"},{\"min\":\"0\",\"cssClass\":\"number\",\"max\":\"1000000000\",\"fieldesc\":\"请输入数字\",\"title\":\"数字输入框\",\"required\":\"required\"},{\"min\":\"\",\"cssClass\":\"multiple_select\",\"max\":\"\",\"children\":[\"选项一\",\"选项二\",\"选项三\"],\"fieldesc\":\"请选择\",\"title\":\"多项选择框\",\"required\":\"required\"},{\"min\":\"0\",\"cssClass\":\"money\",\"max\":\"1000000000\",\"fieldesc\":\"请输入金额\",\"title\":\"金额输入框\",\"required\":\"required\"},{\"min\":\"\",\"cssClass\":\"user\",\"max\":\"\",\"fieldesc\":\"请选择人员\",\"title\":\"人员选择框\",\"required\":\"required\"},{\"min\":\"\",\"cssClass\":\"image\",\"max\":\"9\",\"fieldesc\":\"请选择图片\",\"title\":\"图片现在框\",\"required\":\"required\"},{\"min\":\"\",\"cssClass\":\"date\",\"max\":\"\",\"format\":\"yyyy-MM-dd\",\"fieldesc\":\"请选择日期\",\"title\":\"日期选择框\",\"required\":\"required\"},{\"min\":\"\",\"cssClass\":\"tower\",\"max\":\"\",\"fieldesc\":\"请选择楼栋\",\"title\":\"楼栋选择框\",\"required\":\"required\"},{\"min\":\"\",\"cssClass\":\"cell\",\"max\":\"\",\"fieldesc\":\"请选择单元\",\"title\":\"单元选择框\",\"required\":\"required\"},{\"min\":\"\",\"cssClass\":\"room\",\"max\":\"\",\"fieldesc\":\"请选择室号\",\"title\":\"室号单选框\",\"required\":\"required\"}]","auto":2,"taskName":null,"taskDescription":null,"taskUpdateUserId":null,"taskUpdateUserName":null,"handlerName":null,"checkerName":null,"ccName":null,"checkerId":null}]
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

    public static class DataBean {

        /**
         * id : 2046
         * villageId : 27
         * formId : 74
         * taskId : 26
         * contentJson : [{"title":"单文本输入框","cssClass":"text","prevalue":"我"},{"title":"多文本输入框","cssClass":"textarea","prevalue":""},{"title":"数字输入框","cssClass":"number","prevalue":"123"},{"title":"多项选择框","cssClass":"multiple_select","prevalue":"选项一"},{"title":"金额输入框","cssClass":"money","prevalue":"999"},{"title":"人员选择框","cssClass":"user","prevalue":"王明申"},{"title":"图片现在框","cssClass":"image","prevalue":"2019042517160888310022.jpeg"},{"title":"日期选择框","cssClass":"date","prevalue":"2019-04-25"},{"title":"楼栋选择框","cssClass":"tower","prevalue":"一期1号楼"},{"title":"单元选择框","cssClass":"cell","prevalue":"1单元"},{"title":"室号单选框","cssClass":"room","prevalue":"1"}]
         * status : null
         * handleTime : null
         * checkerTime : null
         * createTime : 2019-04-25 17:16:09
         * updateTime : 2019-04-25 17:16:09
         * formName : 测试表单
         * introduction : 测试专用
         * usage : 表单必须认真填写，否则不予审批
         * formType : 1
         * fieldJson : [{"min":"1","cssClass":"text","max":"500","fieldesc":"请输入","title":"单文本输入框","required":"required"},{"min":"1","cssClass":"textarea","max":"3000","fieldesc":"请输入","title":"多文本输入框","required":""},{"min":"0","cssClass":"number","max":"1000000000","fieldesc":"请输入数字","title":"数字输入框","required":"required"},{"min":"","cssClass":"multiple_select","max":"","children":["选项一","选项二","选项三"],"fieldesc":"请选择","title":"多项选择框","required":"required"},{"min":"0","cssClass":"money","max":"1000000000","fieldesc":"请输入金额","title":"金额输入框","required":"required"},{"min":"","cssClass":"user","max":"","fieldesc":"请选择人员","title":"人员选择框","required":"required"},{"min":"","cssClass":"image","max":"9","fieldesc":"请选择图片","title":"图片现在框","required":"required"},{"min":"","cssClass":"date","max":"","format":"yyyy-MM-dd","fieldesc":"请选择日期","title":"日期选择框","required":"required"},{"min":"","cssClass":"tower","max":"","fieldesc":"请选择楼栋","title":"楼栋选择框","required":"required"},{"min":"","cssClass":"cell","max":"","fieldesc":"请选择单元","title":"单元选择框","required":"required"},{"min":"","cssClass":"room","max":"","fieldesc":"请选择室号","title":"室号单选框","required":"required"}]
         * auto : 2
         * taskName : null
         * taskDescription : null
         * taskUpdateUserId : null
         * taskUpdateUserName : null
         * handlerName : null
         * checkerName : null
         * ccName : null
         * checkerId : null
         */

        private int id;
        private int villageId;
        private int formId;
        private int taskId;
        private String contentJson;
        private String status;
        private String handleTime;
        private String checkerTime;
        private String createTime;
        private String updateTime;
        private String formName;
        private String introduction;
        private String usage;
        private int formType;
        private String fieldJson;
        private int auto;
        private String taskName;
        private String taskDescription;
        private int taskUpdateUserId;
        private String taskUpdateUserName;
        private String handlerName;
        private String checkerName;
        private String ccName;
        private int checkerId;

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

        public int getFormId() {
            return formId;
        }

        public void setFormId(int formId) {
            this.formId = formId;
        }

        public int getTaskId() {
            return taskId;
        }

        public void setTaskId(int taskId) {
            this.taskId = taskId;
        }

        public String getContentJson() {
            return contentJson == null ? "" : contentJson;
        }

        public void setContentJson(String contentJson) {
            this.contentJson = contentJson;
        }

        public String getStatus() {
            return status == null ? "" : status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getHandleTime() {
            return handleTime == null ? "" : handleTime;
        }

        public void setHandleTime(String handleTime) {
            this.handleTime = handleTime;
        }

        public String getCheckerTime() {
            return checkerTime == null ? "" : checkerTime;
        }

        public void setCheckerTime(String checkerTime) {
            this.checkerTime = checkerTime;
        }

        public String getCreateTime() {
            return createTime == null ? "" : createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getUpdateTime() {
            return updateTime == null ? "" : updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public String getFormName() {
            return formName == null ? "" : formName;
        }

        public void setFormName(String formName) {
            this.formName = formName;
        }

        public String getIntroduction() {
            return introduction == null ? "" : introduction;
        }

        public void setIntroduction(String introduction) {
            this.introduction = introduction;
        }

        public String getUsage() {
            return usage == null ? "" : usage;
        }

        public void setUsage(String usage) {
            this.usage = usage;
        }

        public int getFormType() {
            return formType;
        }

        public void setFormType(int formType) {
            this.formType = formType;
        }

        public String getFieldJson() {
            return fieldJson == null ? "" : fieldJson;
        }

        public void setFieldJson(String fieldJson) {
            this.fieldJson = fieldJson;
        }

        public int getAuto() {
            return auto;
        }

        public void setAuto(int auto) {
            this.auto = auto;
        }

        public String getTaskName() {
            return taskName == null ? "" : taskName;
        }

        public void setTaskName(String taskName) {
            this.taskName = taskName;
        }

        public String getTaskDescription() {
            return taskDescription == null ? "" : taskDescription;
        }

        public void setTaskDescription(String taskDescription) {
            this.taskDescription = taskDescription;
        }

        public int getTaskUpdateUserId() {
            return taskUpdateUserId;
        }

        public void setTaskUpdateUserId(int taskUpdateUserId) {
            this.taskUpdateUserId = taskUpdateUserId;
        }

        public String getTaskUpdateUserName() {
            return taskUpdateUserName == null ? "" : taskUpdateUserName;
        }

        public void setTaskUpdateUserName(String taskUpdateUserName) {
            this.taskUpdateUserName = taskUpdateUserName;
        }

        public String getHandlerName() {
            return handlerName == null ? "" : handlerName;
        }

        public void setHandlerName(String handlerName) {
            this.handlerName = handlerName;
        }

        public String getCheckerName() {
            return checkerName == null ? "" : checkerName;
        }

        public void setCheckerName(String checkerName) {
            this.checkerName = checkerName;
        }

        public String getCcName() {
            return ccName == null ? "" : ccName;
        }

        public void setCcName(String ccName) {
            this.ccName = ccName;
        }

        public int getCheckerId() {
            return checkerId;
        }

        public void setCheckerId(int checkerId) {
            this.checkerId = checkerId;
        }
    }
}
