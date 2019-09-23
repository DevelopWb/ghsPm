package com.ghs.ghspm.bean;

/**
 * Author:wang_sir
 * Time:2018/10/23 15:34
 * Description:This is CarInfoBean
 */
public class CarInfoBean {

    /**
     * code : 1000
     * message : 成功
     * data : {"fullName":"测试7","mobile":"13051862093","enterTime":"","outTime":"","stayTime":"","carFee":""}
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
         * fullName : 测试7
         * mobile : 13051862093
         * enterTime :
         * outTime :
         * stayTime :
         * carFee :
         */

        private String fullName;
        private String mobile;
        private String enterTime;
        private String outTime;
        private String stayTime;
        private String carFee;

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getEnterTime() {
            return enterTime;
        }

        public void setEnterTime(String enterTime) {
            this.enterTime = enterTime;
        }

        public String getOutTime() {
            return outTime;
        }

        public void setOutTime(String outTime) {
            this.outTime = outTime;
        }

        public String getStayTime() {
            return stayTime;
        }

        public void setStayTime(String stayTime) {
            this.stayTime = stayTime;
        }

        public String getCarFee() {
            return carFee;
        }

        public void setCarFee(String carFee) {
            this.carFee = carFee;
        }
    }
}
