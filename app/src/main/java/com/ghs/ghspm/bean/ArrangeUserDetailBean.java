package com.ghs.ghspm.bean;

/**
 * Author:wang_sir
 * Time:2019/2/13 16:06
 * Description:This is ArrangeUserDetailBean
 */
public class ArrangeUserDetailBean {
    /**
     * code : 1000
     * message : 成功
     * data : {"arrange":1,"arrangeUserId":409,"day":"2019-02-13","rest":0,"duty":1,"shiftMsg":"早班 07:00-12:00 晚班 16:00-06:00 ","clockPlace":"北京德兴久 "}
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
         * arrange : 1
         * arrangeUserId : 409
         * day : 2019-02-13
         * rest : 0
         * duty : 1
         * shiftMsg : 早班 07:00-12:00 晚班 16:00-06:00
         * clockPlace : 北京德兴久
         */

        private int arrange;
        private int arrangeUserId;
        private String day;
        private int rest;
        private int duty;
        private String shiftMsg;
        private String clockPlace;

        public int getArrange() {
            return arrange;
        }

        public void setArrange(int arrange) {
            this.arrange = arrange;
        }

        public int getArrangeUserId() {
            return arrangeUserId;
        }

        public void setArrangeUserId(int arrangeUserId) {
            this.arrangeUserId = arrangeUserId;
        }

        public String getDay() {
            return day == null ? "" : day;
        }

        public void setDay(String day) {
            this.day = day;
        }

        public int getRest() {
            return rest;
        }

        public void setRest(int rest) {
            this.rest = rest;
        }

        public int getDuty() {
            return duty;
        }

        public void setDuty(int duty) {
            this.duty = duty;
        }

        public String getShiftMsg() {
            return shiftMsg == null ? "" : shiftMsg;
        }

        public void setShiftMsg(String shiftMsg) {
            this.shiftMsg = shiftMsg;
        }

        public String getClockPlace() {
            return clockPlace == null ? "" : clockPlace;
        }

        public void setClockPlace(String clockPlace) {
            this.clockPlace = clockPlace;
        }
    }
}
