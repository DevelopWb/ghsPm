package com.ghs.ghspm.bean;

public class PrepareFillCardBean {

    /**
     * code : 1000
     * data : {"alreadyReplaceNum":0,"clockDay":"2019.2.18","clockId":1348,"leaveReplaceNum":8,"replaceTimeRange":"2019-02-18 13:00#2019-02-19 09:00","shiftTime":"上班时间16:00"}
     * message : 成功
     */

    private int code;
    private DataBean data;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class DataBean {
        /**
         * alreadyReplaceNum : 0
         * clockDay : 2019.2.18
         * clockId : 1348
         * leaveReplaceNum : 8
         * replaceTimeRange : 2019-02-18 13:00#2019-02-19 09:00
         * shiftTime : 上班时间16:00
         */

        private int alreadyReplaceNum;
        private String clockDay;
        private int clockId;
        private int leaveReplaceNum;
        private String replaceTimeRange;
        private String shiftTime;

        public int getAlreadyReplaceNum() {
            return alreadyReplaceNum;
        }

        public void setAlreadyReplaceNum(int alreadyReplaceNum) {
            this.alreadyReplaceNum = alreadyReplaceNum;
        }

        public String getClockDay() {
            return clockDay;
        }

        public void setClockDay(String clockDay) {
            this.clockDay = clockDay;
        }

        public int getClockId() {
            return clockId;
        }

        public void setClockId(int clockId) {
            this.clockId = clockId;
        }

        public int getLeaveReplaceNum() {
            return leaveReplaceNum;
        }

        public void setLeaveReplaceNum(int leaveReplaceNum) {
            this.leaveReplaceNum = leaveReplaceNum;
        }

        public String getReplaceTimeRange() {
            return replaceTimeRange;
        }

        public void setReplaceTimeRange(String replaceTimeRange) {
            this.replaceTimeRange = replaceTimeRange;
        }

        public String getShiftTime() {
            return shiftTime;
        }

        public void setShiftTime(String shiftTime) {
            this.shiftTime = shiftTime;
        }
    }
}
