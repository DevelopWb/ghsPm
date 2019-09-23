package com.ghs.ghspm.bean;

import java.util.List;

public class IllegallyMessageBean {


    /**
     * code : 0
     * data : [{"carNum":"","content":"","createTime":"","createUserId":0,"createUserName":"","fullName":"","ghsUserId":0,"id":0,"imageUrl":"","imageUrl1":"","imageUrl2":"","imageUrl3":"","imageUrl4":"","imageUrl5":"","imageUrl6":"","mobile":"","propertyId":0,"updateTime":"","villageId":0}]
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
         * content :
         * createTime :
         * createUserId : 0
         * createUserName :
         * fullName :
         * ghsUserId : 0
         * id : 0
         * imageUrl :
         * imageUrl1 :
         * imageUrl2 :
         * imageUrl3 :
         * imageUrl4 :
         * imageUrl5 :
         * imageUrl6 :
         * mobile :
         * propertyId : 0
         * updateTime :
         * villageId : 0
         */

        private String carNum;
        private String content;
        private String createTime;
        private int createUserId;
        private String createUserName;
        private String fullName;
        private int ghsUserId;
        private int id;
        private String imageUrl;
        private String imageUrl1;
        private String imageUrl2;
        private String imageUrl3;
        private String imageUrl4;
        private String imageUrl5;
        private String imageUrl6;
        private String mobile;
        private int propertyId;
        private String updateTime;
        private int villageId;

        public String getCarNum() {
            return carNum;
        }

        public void setCarNum(String carNum) {
            this.carNum = carNum;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
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

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
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

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getImageUrl1() {
            return imageUrl1;
        }

        public void setImageUrl1(String imageUrl1) {
            this.imageUrl1 = imageUrl1;
        }

        public String getImageUrl2() {
            return imageUrl2;
        }

        public void setImageUrl2(String imageUrl2) {
            this.imageUrl2 = imageUrl2;
        }

        public String getImageUrl3() {
            return imageUrl3;
        }

        public void setImageUrl3(String imageUrl3) {
            this.imageUrl3 = imageUrl3;
        }

        public String getImageUrl4() {
            return imageUrl4;
        }

        public void setImageUrl4(String imageUrl4) {
            this.imageUrl4 = imageUrl4;
        }

        public String getImageUrl5() {
            return imageUrl5;
        }

        public void setImageUrl5(String imageUrl5) {
            this.imageUrl5 = imageUrl5;
        }

        public String getImageUrl6() {
            return imageUrl6;
        }

        public void setImageUrl6(String imageUrl6) {
            this.imageUrl6 = imageUrl6;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public int getPropertyId() {
            return propertyId;
        }

        public void setPropertyId(int propertyId) {
            this.propertyId = propertyId;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public int getVillageId() {
            return villageId;
        }

        public void setVillageId(int villageId) {
            this.villageId = villageId;
        }
    }
}
