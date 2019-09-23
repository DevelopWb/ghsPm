package com.ghs.ghspm.bean;

import java.util.List;

public class OwnerMessageBean {


    /**
     * code : 0
     * data : [{"address":"","age":0,"fullName":"","headImage":"","mobile":"","roomSize":""}]
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
         * address :
         * age : 0
         * fullName :
         * headImage :
         * mobile :
         * roomSize :
         */

        private String address;
        private String age;
        private String fullName;
        private String headImage;
        private String mobile;
        private String roomSize;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
        }

        public String getHeadImage() {
            return headImage;
        }

        public void setHeadImage(String headImage) {
            this.headImage = headImage;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getRoomSize() {
            return roomSize;
        }

        public void setRoomSize(String roomSize) {
            this.roomSize = roomSize;
        }
    }
}
