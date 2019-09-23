package com.ghs.ghspm.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Author:wang_sir
 * Time:2018/10/17 16:06
 * Description:This is TaskInfoBean
 */
public class TaskInfoBean  {
    /**
     * code : 1000
     * message : 成功
     * data : {"task":{"id":46,"content":"有地址的测试","picture":"2018102211392251857142.jpeg","startTime":null,"endTime":null,"towerId":448,"cellId":591,"status":2,"checkUserId":20,"propertyId":30,"createTime":"2018-10-22 11:39:23","createUserId":20,"createUserName":"王斌","updateTime":"2018-10-22 11:39:23","updateUserId":20,"portionName":"三期","towerNumber":"1号楼","cellName":"1单元","handlerUsersId":null,"ccUsersId":null,"handlerUsersName":"王斌","ccUsersName":"保安人员","checkUserName":"王斌","opertionType":1},"recordList":[{"id":171,"taskId":46,"userId":20,"opertionType":1,"content":"王斌 发起任务","opertionTime":"2018-10-22 11:39:23"}]}
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

    public static class DataBean {
        /**
         * task : {"id":46,"content":"有地址的测试","picture":"2018102211392251857142.jpeg","startTime":null,"endTime":null,"towerId":448,"cellId":591,"status":2,"checkUserId":20,"propertyId":30,"createTime":"2018-10-22 11:39:23","createUserId":20,"createUserName":"王斌","updateTime":"2018-10-22 11:39:23","updateUserId":20,"portionName":"三期","towerNumber":"1号楼","cellName":"1单元","handlerUsersId":null,"ccUsersId":null,"handlerUsersName":"王斌","ccUsersName":"保安人员","checkUserName":"王斌","opertionType":1}
         * recordList : [{"id":171,"taskId":46,"userId":20,"opertionType":1,"content":"王斌 发起任务","opertionTime":"2018-10-22 11:39:23"}]
         */

        private TaskBean task;
        private List<RecordListBean> recordList;

        public TaskBean getTask() {
            return task;
        }

        public void setTask(TaskBean task) {
            this.task = task;
        }

        public List<RecordListBean> getRecordList() {
            return recordList;
        }

        public void setRecordList(List<RecordListBean> recordList) {
            this.recordList = recordList;
        }

        public static class TaskBean  implements Serializable {
            /**
             * id : 46
             * content : 有地址的测试
             * picture : 2018102211392251857142.jpeg
             * startTime : null
             * endTime : null
             * towerId : 448
             * cellId : 591
             * status : 2
             * checkUserId : 20
             * propertyId : 30
             * createTime : 2018-10-22 11:39:23
             * createUserId : 20
             * createUserName : 王斌
             * updateTime : 2018-10-22 11:39:23
             * updateUserId : 20
             * portionName : 三期
             * towerNumber : 1号楼
             * cellName : 1单元
             * handlerUsersId : null
             * ccUsersId : null
             * handlerUsersName : 王斌
             * ccUsersName : 保安人员
             * checkUserName : 王斌
             * opertionType : 1
             */

            private int id;
            private String content;
            private String picture;
            private String startTime;
            private String endTime;
            private int towerId;
            private int cellId;
            private int status;
            private String checkUserId;
            private int propertyId;
            private String createTime;
            private int createUserId;
            private String createUserName;
            private String updateTime;
            private int updateUserId;
            private String portionName;
            private String towerNumber;
            private String cellName;
            private String handlerUsersId;
            private String ccUsersId;
            private String handlerUsersName;
            private String ccUsersName;
            private String checkUserName;
            private int opertionType;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getPicture() {
                return picture;
            }

            public void setPicture(String picture) {
                this.picture = picture;
            }



            public int getTowerId() {
                return towerId;
            }

            public void setTowerId(int towerId) {
                this.towerId = towerId;
            }

            public int getCellId() {
                return cellId;
            }

            public void setCellId(int cellId) {
                this.cellId = cellId;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getCheckUserId() {
                return checkUserId;
            }

            public void setCheckUserId(String checkUserId) {
                this.checkUserId = checkUserId;
            }

            public int getPropertyId() {
                return propertyId;
            }

            public void setPropertyId(int propertyId) {
                this.propertyId = propertyId;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public int getCreateUserId() {
                return createUserId;
            }

            public void setCreateUserId(int createUserId) {
                this.createUserId = createUserId;
            }

            public String getCreateUserName() {
                return createUserName;
            }

            public void setCreateUserName(String createUserName) {
                this.createUserName = createUserName;
            }

            public String getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(String updateTime) {
                this.updateTime = updateTime;
            }

            public int getUpdateUserId() {
                return updateUserId;
            }

            public void setUpdateUserId(int updateUserId) {
                this.updateUserId = updateUserId;
            }

            public String getPortionName() {
                return portionName;
            }

            public void setPortionName(String portionName) {
                this.portionName = portionName;
            }

            public String getTowerNumber() {
                return towerNumber;
            }

            public void setTowerNumber(String towerNumber) {
                this.towerNumber = towerNumber;
            }

            public String getCellName() {
                return cellName;
            }

            public void setCellName(String cellName) {
                this.cellName = cellName;
            }

            public String getStartTime() {
                return startTime;
            }

            public void setStartTime(String startTime) {
                this.startTime = startTime;
            }

            public String getEndTime() {
                return endTime;
            }

            public void setEndTime(String endTime) {
                this.endTime = endTime;
            }

            public String getHandlerUsersId() {
                return handlerUsersId;
            }

            public void setHandlerUsersId(String handlerUsersId) {
                this.handlerUsersId = handlerUsersId;
            }

            public String getCcUsersId() {
                return ccUsersId;
            }

            public void setCcUsersId(String ccUsersId) {
                this.ccUsersId = ccUsersId;
            }

            public String getHandlerUsersName() {
                return handlerUsersName;
            }

            public void setHandlerUsersName(String handlerUsersName) {
                this.handlerUsersName = handlerUsersName;
            }

            public String getCcUsersName() {
                return ccUsersName;
            }

            public void setCcUsersName(String ccUsersName) {
                this.ccUsersName = ccUsersName;
            }

            public String getCheckUserName() {
                return checkUserName;
            }

            public void setCheckUserName(String checkUserName) {
                this.checkUserName = checkUserName;
            }

            public int getOpertionType() {
                return opertionType;
            }

            public void setOpertionType(int opertionType) {
                this.opertionType = opertionType;
            }
        }

        public static class RecordListBean {
            /**
             * id : 171
             * taskId : 46
             * userId : 20
             * opertionType : 1
             * content : 王斌 发起任务
             * opertionTime : 2018-10-22 11:39:23
             */

            private int id;
            private int taskId;
            private int userId;
            private int opertionType;
            private String content;
            private String remark;
            private String picture;

            private String opertionTime;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getTaskId() {
                return taskId;
            }

            public void setTaskId(int taskId) {
                this.taskId = taskId;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public int getOpertionType() {
                return opertionType;
            }

            public void setOpertionType(int opertionType) {
                this.opertionType = opertionType;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getOpertionTime() {
                return opertionTime;
            }

            public String getRemark() {
                return remark == null ? "" : remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public String getPicture() {
                return picture == null ? "" : picture;
            }

            public void setPicture(String picture) {
                this.picture = picture;
            }

            public void setOpertionTime(String opertionTime) {
                this.opertionTime = opertionTime;
            }
        }
    }
}
