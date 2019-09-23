package com.ghs.ghspm.bean;

import java.util.List;

/**
 * Author:wang_sir
 * Time:2018/10/16 14:52
 * Description:This is TasksBean
 */
public class TasksBean {

    /**
     * code : 1000
     * message : 成功
     * data : [{"id":30,"content":"给明申的任务","picture":null,"startTime":null,"endTime":null,"towerId":null,"cellId":null,"status":3,"checkUserId":20,"propertyId":30,"createTime":"2018-10-17 17:38:40","createUserId":20,"createUserName":"王斌","updateTime":"2018-10-18 14:49:15","updateUserId":20,"towerNumber":null,"cellName":null,"handlerUsersId":null,"ccUsersId":null,"handlerUsersName":null,"ccUsersName":null,"checkUserName":null,"opertionType":null},{"id":29,"content":"给明申的任务","picture":null,"startTime":null,"endTime":null,"towerId":null,"cellId":null,"status":2,"checkUserId":20,"propertyId":30,"createTime":"2018-10-17 17:38:35","createUserId":20,"createUserName":"王斌","updateTime":"2018-10-17 17:38:35","updateUserId":20,"towerNumber":null,"cellName":null,"handlerUsersId":null,"ccUsersId":null,"handlerUsersName":null,"ccUsersName":null,"checkUserName":null,"opertionType":null},{"id":28,"content":"给明申的任务","picture":null,"startTime":null,"endTime":null,"towerId":null,"cellId":null,"status":2,"checkUserId":20,"propertyId":30,"createTime":"2018-10-17 17:38:32","createUserId":20,"createUserName":"王斌","updateTime":"2018-10-17 17:38:32","updateUserId":20,"towerNumber":null,"cellName":null,"handlerUsersId":null,"ccUsersId":null,"handlerUsersName":null,"ccUsersName":null,"checkUserName":null,"opertionType":null},{"id":27,"content":"给明申的任务","picture":null,"startTime":null,"endTime":null,"towerId":null,"cellId":null,"status":2,"checkUserId":20,"propertyId":30,"createTime":"2018-10-17 17:38:27","createUserId":20,"createUserName":"王斌","updateTime":"2018-10-17 17:38:27","updateUserId":20,"towerNumber":null,"cellName":null,"handlerUsersId":null,"ccUsersId":null,"handlerUsersName":null,"ccUsersName":null,"checkUserName":null,"opertionType":null}]
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
         * id : 30
         * content : 给明申的任务
         * picture : null
         * startTime : null
         * endTime : null
         * towerId : null
         * cellId : null
         * status : 3
         * checkUserId : 20
         * propertyId : 30
         * createTime : 2018-10-17 17:38:40
         * createUserId : 20
         * createUserName : 王斌
         * updateTime : 2018-10-18 14:49:15
         * updateUserId : 20
         * towerNumber : null
         * cellName : null
         * handlerUsersId : null
         * ccUsersId : null
         * handlerUsersName : null
         * ccUsersName : null
         * checkUserName : null
         * opertionType : null
         */

        private int id;
        private String content;
        private String picture;
        private String startTime;
        private String endTime;
        private int towerId;
        private int cellId;
        private int status;
        private int checkUserId;
        private int propertyId;
        private String createTime;
        private int createUserId;
        private String createUserName;
        private String updateTime;
        private int updateUserId;
        private int towerNumber;
        private int cellName;
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

        public int getCheckUserId() {
            return checkUserId;
        }

        public void setCheckUserId(int checkUserId) {
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

        public int getTowerNumber() {
            return towerNumber;
        }

        public void setTowerNumber(int towerNumber) {
            this.towerNumber = towerNumber;
        }

        public int getCellName() {
            return cellName;
        }

        public void setCellName(int cellName) {
            this.cellName = cellName;
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
}
