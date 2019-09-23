package com.ghs.ghspm.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Author:wang_sir
 * Time:2019/1/2 18:09
 * Description:This is TableTaskDetailBean
 */
public class TableTaskDetailBean {

    /**
     * code : 1000
     * message : 成功
     * data : {"id":1,"formName":"打扫楼道（非自动）","formUsage":"打扫完成必须认真填写表格","fieldJson":"[{\"min\":\"1\",\"cssClass\":\"text\",\"max\":\"500\",\"fieldesc\":\"必须输入\",\"title\":\"姓名\",\"required\":\"required\"},{\"min\":\"1\",\"cssClass\":\"textarea\",\"max\":\"3000\",\"fieldesc\":\"多行文本\",\"title\":\"状况描述\",\"required\":\"\"},{\"min\":\"\",\"cssClass\":\"select\",\"max\":\"\",\"children\":[\"男\",\"女\"],\"fieldesc\":\"请选择\",\"title\":\"性别\",\"required\":\"required\"},{\"min\":\"\",\"cssClass\":\"multiple_select\",\"max\":\"\",\"fieldesc\":\"多项选择框\",\"title\":\"打扫位置\",\"required\":\"required\"},{\"min\":\"0\",\"cssClass\":\"number\",\"max\":\"1000000000\",\"fieldesc\":\"请输入\",\"title\":\"数字\",\"required\":\"\"},{\"min\":\"0\",\"cssClass\":\"money\",\"max\":\"1000000000\",\"fieldesc\":\"金额\",\"title\":\"金额\",\"required\":\"\"},{\"min\":\"\",\"cssClass\":\"user\",\"max\":\"\",\"fieldesc\":\"人员\",\"title\":\"人员\",\"required\":\"\"},{\"min\":\"\",\"cssClass\":\"image\",\"max\":\"9\",\"fieldesc\":\"图片\",\"title\":\"图片\",\"required\":\"\"},{\"min\":\"\",\"cssClass\":\"date\",\"max\":\"\",\"format\":\"yyyy-MM-dd\",\"fieldesc\":\"日期\",\"title\":\"日期\",\"required\":\"\"},{\"min\":\"\",\"cssClass\":\"tower\",\"max\":\"\",\"fieldesc\":\"楼栋单选框\",\"title\":\"楼栋\",\"required\":\"\"},{\"min\":\"\",\"cssClass\":\"cell\",\"max\":\"\",\"fieldesc\":\"单元\",\"title\":\"单元\",\"required\":\"\"},{\"min\":\"\",\"cssClass\":\"room\",\"max\":\"\",\"fieldesc\":\"室号单选框\",\"title\":\"室号\",\"required\":\"\"}]","contentJson":"[{\n\t\"cssClass\": \"text\",\n\t\"title\": \"姓名\",\n  \t\"prevalue\": \"陶\",\n},\n{\n\t\"cssClass\": \"text\",\n\t\"title\": \"提交人\",\n  \t\"prevalue\": \"陶\",\n}]","taskName":"打扫卫生","taskDescription":"认真打扫","taskUpdateUserName":"陶龙洋","handlerName":"王斌(2)","handleTime":null,"checkerName":"王明申","checkerTime":null,"ccName":"陶龙洋","taskStartTime":null,"taskEndTime":null,"formType":1,"status":1}
     */

    private int code;
    private String message;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Parcelable {
        /**
         * id : 1
         * formName : 打扫楼道（非自动）
         * formUsage : 打扫完成必须认真填写表格
         * fieldJson : [{"min":"1","cssClass":"text","max":"500","fieldesc":"必须输入","title":"姓名","required":"required"},{"min":"1","cssClass":"textarea","max":"3000","fieldesc":"多行文本","title":"状况描述","required":""},{"min":"","cssClass":"select","max":"","children":["男","女"],"fieldesc":"请选择","title":"性别","required":"required"},{"min":"","cssClass":"multiple_select","max":"","fieldesc":"多项选择框","title":"打扫位置","required":"required"},{"min":"0","cssClass":"number","max":"1000000000","fieldesc":"请输入","title":"数字","required":""},{"min":"0","cssClass":"money","max":"1000000000","fieldesc":"金额","title":"金额","required":""},{"min":"","cssClass":"user","max":"","fieldesc":"人员","title":"人员","required":""},{"min":"","cssClass":"image","max":"9","fieldesc":"图片","title":"图片","required":""},{"min":"","cssClass":"date","max":"","format":"yyyy-MM-dd","fieldesc":"日期","title":"日期","required":""},{"min":"","cssClass":"tower","max":"","fieldesc":"楼栋单选框","title":"楼栋","required":""},{"min":"","cssClass":"cell","max":"","fieldesc":"单元","title":"单元","required":""},{"min":"","cssClass":"room","max":"","fieldesc":"室号单选框","title":"室号","required":""}]
         * contentJson : [{
         "cssClass": "text",
         "title": "姓名",
         "prevalue": "陶",
         },
         {
         "cssClass": "text",
         "title": "提交人",
         "prevalue": "陶",
         }]
         * taskName : 打扫卫生
         * taskDescription : 认真打扫
         * taskUpdateUserName : 陶龙洋
         * handlerName : 王斌(2)
         * handleTime : null
         * checkerName : 王明申
         * checkerTime : null
         * ccName : 陶龙洋
         * taskStartTime : null
         * taskEndTime : null
         * formType : 1
         * status : 1
         */

        private int id;
        private String formName;
        private String usage;//表单说明，动态表单的说明，（简述）
        private String fieldJson;
        private String contentJson;
        private String taskName;
        private String description;
        private String taskUpdateUserName;
        private String handlerName;
        private String handleTime;
        private String checkerName;
        private String checkerTime;
        private int taskId;
        private String ccName;
        private String taskStartTime;
        private String taskEndTime;
        private int formType;
        private int status;
        private int recordId;

        public int getRecordId() {
            return recordId;
        }

        public void setRecordId(int recordId) {
            this.recordId = recordId;
        }

        public int getTaskId() {
            return taskId;
        }

        public void setTaskId(int taskId) {
            this.taskId = taskId;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getFormName() {
            return formName == null ? "" : formName;
        }

        public void setFormName(String formName) {
            this.formName = formName;
        }

        public String getUsage() {
            return usage == null ? "" : usage;
        }

        public void setUsage(String usage) {
            this.usage = usage;
        }

        public String getFieldJson() {
            return fieldJson == null ? "" : fieldJson;
        }

        public void setFieldJson(String fieldJson) {
            this.fieldJson = fieldJson;
        }

        public String getContentJson() {
            return contentJson == null ? "" : contentJson;
        }

        public void setContentJson(String contentJson) {
            this.contentJson = contentJson;
        }

        public String getTaskName() {
            return taskName == null ? "" : taskName;
        }

        public void setTaskName(String taskName) {
            this.taskName = taskName;
        }

        public String getDescription() {
            return description == null ? "" : description;
        }

        public void setDescription(String description) {
            this.description = description;
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

        public String getHandleTime() {
            return handleTime == null ? "" : handleTime;
        }

        public void setHandleTime(String handleTime) {
            this.handleTime = handleTime;
        }

        public String getCheckerName() {
            return checkerName == null ? "" : checkerName;
        }

        public void setCheckerName(String checkerName) {
            this.checkerName = checkerName;
        }

        public String getCheckerTime() {
            return checkerTime == null ? "" : checkerTime;
        }

        public void setCheckerTime(String checkerTime) {
            this.checkerTime = checkerTime;
        }

        public String getCcName() {
            return ccName == null ? "" : ccName;
        }

        public void setCcName(String ccName) {
            this.ccName = ccName;
        }

        public String getTaskStartTime() {
            return taskStartTime == null ? "" : taskStartTime;
        }

        public void setTaskStartTime(String taskStartTime) {
            this.taskStartTime = taskStartTime;
        }

        public String getTaskEndTime() {
            return taskEndTime == null ? "" : taskEndTime;
        }

        public void setTaskEndTime(String taskEndTime) {
            this.taskEndTime = taskEndTime;
        }

        public int getFormType() {
            return formType;
        }

        public void setFormType(int formType) {
            this.formType = formType;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public DataBean() {
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.id);
            dest.writeString(this.formName);
            dest.writeString(this.usage);
            dest.writeString(this.fieldJson);
            dest.writeString(this.contentJson);
            dest.writeString(this.taskName);
            dest.writeString(this.description);
            dest.writeString(this.taskUpdateUserName);
            dest.writeString(this.handlerName);
            dest.writeString(this.handleTime);
            dest.writeString(this.checkerName);
            dest.writeString(this.checkerTime);
            dest.writeInt(this.taskId);
            dest.writeString(this.ccName);
            dest.writeString(this.taskStartTime);
            dest.writeString(this.taskEndTime);
            dest.writeInt(this.formType);
            dest.writeInt(this.status);
            dest.writeInt(this.recordId);
        }

        protected DataBean(Parcel in) {
            this.id = in.readInt();
            this.formName = in.readString();
            this.usage = in.readString();
            this.fieldJson = in.readString();
            this.contentJson = in.readString();
            this.taskName = in.readString();
            this.description = in.readString();
            this.taskUpdateUserName = in.readString();
            this.handlerName = in.readString();
            this.handleTime = in.readString();
            this.checkerName = in.readString();
            this.checkerTime = in.readString();
            this.taskId = in.readInt();
            this.ccName = in.readString();
            this.taskStartTime = in.readString();
            this.taskEndTime = in.readString();
            this.formType = in.readInt();
            this.status = in.readInt();
            this.recordId = in.readInt();
        }

        public static final Creator<DataBean> CREATOR = new Creator<DataBean>() {
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
