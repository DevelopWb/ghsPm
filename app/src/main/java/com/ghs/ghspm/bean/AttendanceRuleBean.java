package com.ghs.ghspm.bean;

public class AttendanceRuleBean {


    /**
     * code : 0
     * data : {"attendanceTime":"","lateDescription":"","replaceDescription":"","rulesName":"","type":0,"weekDay":""}
     * message :
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
         * attendanceTime :
         * lateDescription :
         * replaceDescription :
         * rulesName :
         * type : 0
         * weekDay :
         */

        private String attendanceTime;
        private String lateDescription;
        private String replaceDescription;
        private String rulesName;
        private int type;
        private String weekDay;

        public String getAttendanceTime() {
            return attendanceTime;
        }

        public void setAttendanceTime(String attendanceTime) {
            this.attendanceTime = attendanceTime;
        }

        public String getLateDescription() {
            return lateDescription;
        }

        public void setLateDescription(String lateDescription) {
            this.lateDescription = lateDescription;
        }

        public String getReplaceDescription() {
            return replaceDescription;
        }

        public void setReplaceDescription(String replaceDescription) {
            this.replaceDescription = replaceDescription;
        }

        public String getRulesName() {
            return rulesName;
        }

        public void setRulesName(String rulesName) {
            this.rulesName = rulesName;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getWeekDay() {
            return weekDay;
        }

        public void setWeekDay(String weekDay) {
            this.weekDay = weekDay;
        }
    }
}
