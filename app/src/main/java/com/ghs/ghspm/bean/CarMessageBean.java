package com.ghs.ghspm.bean;

import java.util.List;

public class CarMessageBean {


    /**
     * code : 0
     * data : [{"carNum":"","createTime":"","createUserId":0,"ghsUserId":0,"id":0,"mobile":"","qrCode":"","roomFullName":"","roomId":0,"source":0,"type":0,"updateTime":"","updateUserId":0,"userName":"","villageId":0}]
     * message :
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
         * carNum :
         * createTime :
         * createUserId : 0
         * ghsUserId : 0
         * id : 0
         * mobile :
         * qrCode :
         * roomFullName :
         * roomId : 0
         * source : 0
         * type : 0
         * updateTime :
         * updateUserId : 0
         * userName :
         * villageId : 0
         */

        private String carNum;
        private String createTime;
        private int createUserId;
        private int ghsUserId;
        private int id;
        private String mobile;
        private String qrCode;
        private String roomFullName;
        private int roomId;
        private int source;
        private int type;
        private String updateTime;
        private int updateUserId;
        private String userName;
        private int villageId;

        public String getCarNum() {
            return carNum;
        }

        public void setCarNum(String carNum) {
            this.carNum = carNum;
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

        public int getGhsUserId() {
            return ghsUserId;
        }

        public void setGhsUserId(int ghsUserId) {
            this.ghsUserId = ghsUserId;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getQrCode() {
            return qrCode;
        }

        public void setQrCode(String qrCode) {
            this.qrCode = qrCode;
        }

        public String getRoomFullName() {
            return roomFullName;
        }

        public void setRoomFullName(String roomFullName) {
            this.roomFullName = roomFullName;
        }

        public int getRoomId() {
            return roomId;
        }

        public void setRoomId(int roomId) {
            this.roomId = roomId;
        }

        public int getSource() {
            return source;
        }

        public void setSource(int source) {
            this.source = source;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
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

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public int getVillageId() {
            return villageId;
        }

        public void setVillageId(int villageId) {
            this.villageId = villageId;
        }
    }
}
