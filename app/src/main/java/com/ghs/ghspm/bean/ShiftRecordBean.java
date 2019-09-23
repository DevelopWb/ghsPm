package com.ghs.ghspm.bean;

import java.util.List;

/**
 * Author:wang_sir
 * Time:2018/10/20 19:42
 * Description:This is ShiftRecordBean
 */
public class ShiftRecordBean {

    /**
     * code : 1000
     * message : 成功
     * data : {"workTime":"早班07:00-12:00  中班13:00-19:00  ","pmAttendanceClockDOList":[{"id":1498,"propertyId":30,"attendanceType":3,"userId":38,"shiftId":4,"shiftTime":"2019.2.19 07:00","clockDay":"2019-02-19 00:00:00","clockTime":null,"clockType":1,"clockPlace":null,"result":0,"errorCode":1,"replaceTimeRange":"2019-02-19 04:00#2019-02-19 15:00","replaceClockId":null,"createTime":"2019-02-19 16:11:02","updateTime":"2019-02-19 16:11:02"},{"id":1499,"propertyId":30,"attendanceType":3,"userId":38,"shiftId":4,"shiftTime":"2019.2.19 12:00","clockDay":"2019-02-19 00:00:00","clockTime":null,"clockType":2,"clockPlace":null,"result":0,"errorCode":1,"replaceTimeRange":"2019-02-19 04:00#2019-02-19 15:00","replaceClockId":null,"createTime":"2019-02-19 16:11:02","updateTime":"2019-02-19 16:11:02"},{"id":1509,"propertyId":30,"attendanceType":3,"userId":38,"shiftId":5,"shiftTime":"2019.2.19 13:00","clockDay":"2019-02-19 00:00:00","clockTime":null,"clockType":1,"clockPlace":null,"result":0,"errorCode":1,"replaceTimeRange":"2019-02-19 12:35#2019-02-19 19:30","replaceClockId":null,"createTime":"2019-02-19 19:00:00","updateTime":"2019-02-19 19:00:00"},{"id":1511,"propertyId":30,"attendanceType":3,"userId":38,"shiftId":5,"shiftTime":"2019.2.19 19:00","clockDay":"2019-02-19 00:00:00","clockTime":null,"clockType":2,"clockPlace":null,"result":0,"errorCode":1,"replaceTimeRange":"2019-02-19 12:35#2019-02-19 19:30","replaceClockId":null,"createTime":"2019-02-19 19:30:00","updateTime":"2019-02-19 19:30:00"}]}
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
         * workTime : 早班07:00-12:00  中班13:00-19:00
         * pmAttendanceClockDOList : [{"id":1498,"propertyId":30,"attendanceType":3,"userId":38,"shiftId":4,"shiftTime":"2019.2.19 07:00","clockDay":"2019-02-19 00:00:00","clockTime":null,"clockType":1,"clockPlace":null,"result":0,"errorCode":1,"replaceTimeRange":"2019-02-19 04:00#2019-02-19 15:00","replaceClockId":null,"createTime":"2019-02-19 16:11:02","updateTime":"2019-02-19 16:11:02"},{"id":1499,"propertyId":30,"attendanceType":3,"userId":38,"shiftId":4,"shiftTime":"2019.2.19 12:00","clockDay":"2019-02-19 00:00:00","clockTime":null,"clockType":2,"clockPlace":null,"result":0,"errorCode":1,"replaceTimeRange":"2019-02-19 04:00#2019-02-19 15:00","replaceClockId":null,"createTime":"2019-02-19 16:11:02","updateTime":"2019-02-19 16:11:02"},{"id":1509,"propertyId":30,"attendanceType":3,"userId":38,"shiftId":5,"shiftTime":"2019.2.19 13:00","clockDay":"2019-02-19 00:00:00","clockTime":null,"clockType":1,"clockPlace":null,"result":0,"errorCode":1,"replaceTimeRange":"2019-02-19 12:35#2019-02-19 19:30","replaceClockId":null,"createTime":"2019-02-19 19:00:00","updateTime":"2019-02-19 19:00:00"},{"id":1511,"propertyId":30,"attendanceType":3,"userId":38,"shiftId":5,"shiftTime":"2019.2.19 19:00","clockDay":"2019-02-19 00:00:00","clockTime":null,"clockType":2,"clockPlace":null,"result":0,"errorCode":1,"replaceTimeRange":"2019-02-19 12:35#2019-02-19 19:30","replaceClockId":null,"createTime":"2019-02-19 19:30:00","updateTime":"2019-02-19 19:30:00"}]
         */

        private String workTime;
        private List<PmAttendanceClockDOListBean> pmAttendanceClockDOList;

        public String getWorkTime() {
            return workTime;
        }

        public void setWorkTime(String workTime) {
            this.workTime = workTime;
        }

        public List<PmAttendanceClockDOListBean> getPmAttendanceClockDOList() {
            return pmAttendanceClockDOList;
        }

        public void setPmAttendanceClockDOList(List<PmAttendanceClockDOListBean> pmAttendanceClockDOList) {
            this.pmAttendanceClockDOList = pmAttendanceClockDOList;
        }

        public static class PmAttendanceClockDOListBean {
            /**
             * id : 1498
             * propertyId : 30
             * attendanceType : 3
             * userId : 38
             * shiftId : 4
             * shiftTime : 2019.2.19 07:00
             * clockDay : 2019-02-19 00:00:00
             * clockTime : null
             * clockType : 1
             * clockPlace : null
             * result : 0
             * errorCode : 1
             * replaceTimeRange : 2019-02-19 04:00#2019-02-19 15:00
             * replaceClockId : null
             * createTime : 2019-02-19 16:11:02
             * updateTime : 2019-02-19 16:11:02
             */

            private int id;
            private int propertyId;
            private int attendanceType;
            private int userId;
            private int shiftId;
            private String shiftTime;
            private String clockDay;
            private String clockTime;
            private int clockType;
            private String clockPlace;
            private int result;
            private int errorCode;
            private String replaceTimeRange;
            private int replaceClockId;
            private String createTime;
            private String updateTime;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getPropertyId() {
                return propertyId;
            }

            public void setPropertyId(int propertyId) {
                this.propertyId = propertyId;
            }

            public int getAttendanceType() {
                return attendanceType;
            }

            public void setAttendanceType(int attendanceType) {
                this.attendanceType = attendanceType;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public int getShiftId() {
                return shiftId;
            }

            public void setShiftId(int shiftId) {
                this.shiftId = shiftId;
            }

            public String getShiftTime() {
                return shiftTime == null ? "" : shiftTime;
            }

            public void setShiftTime(String shiftTime) {
                this.shiftTime = shiftTime;
            }

            public String getClockDay() {
                return clockDay == null ? "" : clockDay;
            }

            public void setClockDay(String clockDay) {
                this.clockDay = clockDay;
            }

            public String getClockTime() {
                return clockTime == null ? "" : clockTime;
            }

            public void setClockTime(String clockTime) {
                this.clockTime = clockTime;
            }

            public int getClockType() {
                return clockType;
            }

            public void setClockType(int clockType) {
                this.clockType = clockType;
            }

            public String getClockPlace() {
                return clockPlace == null ? "" : clockPlace;
            }

            public void setClockPlace(String clockPlace) {
                this.clockPlace = clockPlace;
            }

            public int getResult() {
                return result;
            }

            public void setResult(int result) {
                this.result = result;
            }

            public int getErrorCode() {
                return errorCode;
            }

            public void setErrorCode(int errorCode) {
                this.errorCode = errorCode;
            }

            public String getReplaceTimeRange() {
                return replaceTimeRange == null ? "" : replaceTimeRange;
            }

            public void setReplaceTimeRange(String replaceTimeRange) {
                this.replaceTimeRange = replaceTimeRange;
            }

            public int getReplaceClockId() {
                return replaceClockId;
            }

            public void setReplaceClockId(int replaceClockId) {
                this.replaceClockId = replaceClockId;
            }

            public String getCreateTime() {
                return createTime == null ? "" : createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getUpdateTime() {
                return updateTime == null ? "" : updateTime;
            }

            public void setUpdateTime(String updateTime) {
                this.updateTime = updateTime;
            }
        }
    }
}
