package com.ghs.ghspm.bean;

import java.util.List;

/**
 * Author:wang_sir
 * Time:2019/2/11 14:34
 * Description:This is ArrangeShiftBean
 * 排班月历实体类
 */
public class ArrangeShiftBean {

    /**
     * code : 1000
     * message : 成功
     * data : [{"arrange":0,"arrangeUserId":null,"day":"2019-02-01","rest":null,"duty":null,"shiftMsg":null,"clockPlace":null},{"arrange":0,"arrangeUserId":null,"day":"2019-02-02","rest":null,"duty":null,"shiftMsg":null,"clockPlace":null},{"arrange":0,"arrangeUserId":null,"day":"2019-02-03","rest":null,"duty":null,"shiftMsg":null,"clockPlace":null},{"arrange":0,"arrangeUserId":null,"day":"2019-02-04","rest":null,"duty":null,"shiftMsg":null,"clockPlace":null},{"arrange":0,"arrangeUserId":null,"day":"2019-02-05","rest":null,"duty":null,"shiftMsg":null,"clockPlace":null},{"arrange":0,"arrangeUserId":null,"day":"2019-02-06","rest":null,"duty":null,"shiftMsg":null,"clockPlace":null},{"arrange":0,"arrangeUserId":null,"day":"2019-02-07","rest":null,"duty":null,"shiftMsg":null,"clockPlace":null},{"arrange":0,"arrangeUserId":null,"day":"2019-02-08","rest":null,"duty":null,"shiftMsg":null,"clockPlace":null},{"arrange":0,"arrangeUserId":null,"day":"2019-02-09","rest":null,"duty":null,"shiftMsg":null,"clockPlace":null},{"arrange":0,"arrangeUserId":null,"day":"2019-02-10","rest":null,"duty":null,"shiftMsg":null,"clockPlace":null},{"arrange":0,"arrangeUserId":null,"day":"2019-02-11","rest":null,"duty":null,"shiftMsg":null,"clockPlace":null},{"arrange":0,"arrangeUserId":null,"day":"2019-02-12","rest":null,"duty":null,"shiftMsg":null,"clockPlace":null},{"arrange":0,"arrangeUserId":null,"day":"2019-02-13","rest":null,"duty":null,"shiftMsg":null,"clockPlace":null},{"arrange":0,"arrangeUserId":null,"day":"2019-02-14","rest":null,"duty":null,"shiftMsg":null,"clockPlace":null},{"arrange":0,"arrangeUserId":null,"day":"2019-02-15","rest":null,"duty":null,"shiftMsg":null,"clockPlace":null},{"arrange":0,"arrangeUserId":null,"day":"2019-02-16","rest":null,"duty":null,"shiftMsg":null,"clockPlace":null},{"arrange":0,"arrangeUserId":null,"day":"2019-02-17","rest":null,"duty":null,"shiftMsg":null,"clockPlace":null},{"arrange":0,"arrangeUserId":null,"day":"2019-02-18","rest":null,"duty":null,"shiftMsg":null,"clockPlace":null},{"arrange":0,"arrangeUserId":null,"day":"2019-02-19","rest":null,"duty":null,"shiftMsg":null,"clockPlace":null},{"arrange":0,"arrangeUserId":null,"day":"2019-02-20","rest":null,"duty":null,"shiftMsg":null,"clockPlace":null},{"arrange":0,"arrangeUserId":null,"day":"2019-02-21","rest":null,"duty":null,"shiftMsg":null,"clockPlace":null},{"arrange":0,"arrangeUserId":null,"day":"2019-02-22","rest":null,"duty":null,"shiftMsg":null,"clockPlace":null},{"arrange":0,"arrangeUserId":null,"day":"2019-02-23","rest":null,"duty":null,"shiftMsg":null,"clockPlace":null},{"arrange":0,"arrangeUserId":null,"day":"2019-02-24","rest":null,"duty":null,"shiftMsg":null,"clockPlace":null},{"arrange":0,"arrangeUserId":null,"day":"2019-02-25","rest":null,"duty":null,"shiftMsg":null,"clockPlace":null},{"arrange":0,"arrangeUserId":null,"day":"2019-02-26","rest":null,"duty":null,"shiftMsg":null,"clockPlace":null},{"arrange":0,"arrangeUserId":null,"day":"2019-02-27","rest":null,"duty":null,"shiftMsg":null,"clockPlace":null},{"arrange":0,"arrangeUserId":null,"day":"2019-02-28","rest":null,"duty":null,"shiftMsg":null,"clockPlace":null}]
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
         * arrange : 0
         * arrangeUserId : null
         * day : 2019-02-01
         * rest : null
         * duty : null
         * shiftMsg : null
         * clockPlace : null
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
