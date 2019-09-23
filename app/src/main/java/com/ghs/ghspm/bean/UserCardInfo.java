package com.ghs.ghspm.bean;

import java.util.List;

/**
 * Author:wang_sir
 * Time:2018/10/19 15:23
 * Description:This is UserCardInfo  用户打卡信息
 */
public class UserCardInfo {

    /**
     * code : 1000
     * message : 成功
     * data : {"rulesName":"保安部","type":1,"clock":2,"reason":null,"attendanceTime":"早班07:00-12:00  晚班16:00-次日06:00  ","clockRecordList":[{"clockDay":"2019-02-18","clockType":1,"permitStartClockTime":"2019-02-18 04:00","permitEndClockTime":null,"updateBut":0,"lastUpdateClockTime":null,"shiftId":4,"shiftDay":"2019.2.18","shiftTime":"07:00","clockPlaceList":[{"id":2,"propertyId":30,"mode":1,"place":"朝阳医院","effectiveDistance":200,"longitude":"116.453804","latitude":"39.925774","defaultFlag":0,"createUserId":null,"createTime":"2019-01-28 17:07:39","updateUserId":null,"updateTime":"2019-01-28 17:07:41"},{"id":1,"propertyId":30,"mode":1,"place":"万通中心","effectiveDistance":200,"longitude":"116.456014","latitude":"39.919856","defaultFlag":1,"createUserId":null,"createTime":"2019-01-28 17:07:05","updateUserId":null,"updateTime":"2019-01-28 17:07:09"}],"replaceClockId":null,"clockId":1346,"clockTime":"10:42","clockPlace":"万通中心","result":0,"errorCode":3},{"clockDay":"2019-02-18","clockType":2,"permitStartClockTime":null,"permitEndClockTime":"2019-02-18 15:00","updateBut":0,"lastUpdateClockTime":"2019-02-18 15:00","shiftId":4,"shiftDay":"2019.2.18","shiftTime":"12:00","clockPlaceList":[{"id":2,"propertyId":30,"mode":1,"place":"朝阳医院","effectiveDistance":200,"longitude":"116.453804","latitude":"39.925774","defaultFlag":0,"createUserId":null,"createTime":"2019-01-28 17:07:39","updateUserId":null,"updateTime":"2019-01-28 17:07:41"},{"id":1,"propertyId":30,"mode":1,"place":"万通中心","effectiveDistance":200,"longitude":"116.456014","latitude":"39.919856","defaultFlag":1,"createUserId":null,"createTime":"2019-01-28 17:07:05","updateUserId":null,"updateTime":"2019-01-28 17:07:09"}],"replaceClockId":null,"clockId":1347,"clockTime":"15:13","clockPlace":"万通中心","result":1,"errorCode":6},{"clockDay":"2019-02-18","clockType":1,"permitStartClockTime":"2019-02-18 13:00","permitEndClockTime":null,"updateBut":0,"lastUpdateClockTime":null,"shiftId":7,"shiftDay":"2019.2.18","shiftTime":"16:00","clockPlaceList":[{"id":2,"propertyId":30,"mode":1,"place":"朝阳医院","effectiveDistance":200,"longitude":"116.453804","latitude":"39.925774","defaultFlag":0,"createUserId":null,"createTime":"2019-01-28 17:07:39","updateUserId":null,"updateTime":"2019-01-28 17:07:41"},{"id":1,"propertyId":30,"mode":1,"place":"万通中心","effectiveDistance":200,"longitude":"116.456014","latitude":"39.919856","defaultFlag":1,"createUserId":null,"createTime":"2019-01-28 17:07:05","updateUserId":null,"updateTime":"2019-01-28 17:07:09"}],"replaceClockId":null,"clockId":1348,"clockTime":"13:54","clockPlace":"万通中心","result":1,"errorCode":null}]}
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
         * rulesName : 保安部
         * type : 1
         * clock : 2
         * reason : null
         * attendanceTime : 早班07:00-12:00  晚班16:00-次日06:00
         * clockRecordList : [{"clockDay":"2019-02-18","clockType":1,"permitStartClockTime":"2019-02-18 04:00","permitEndClockTime":null,"updateBut":0,"lastUpdateClockTime":null,"shiftId":4,"shiftDay":"2019.2.18","shiftTime":"07:00","clockPlaceList":[{"id":2,"propertyId":30,"mode":1,"place":"朝阳医院","effectiveDistance":200,"longitude":"116.453804","latitude":"39.925774","defaultFlag":0,"createUserId":null,"createTime":"2019-01-28 17:07:39","updateUserId":null,"updateTime":"2019-01-28 17:07:41"},{"id":1,"propertyId":30,"mode":1,"place":"万通中心","effectiveDistance":200,"longitude":"116.456014","latitude":"39.919856","defaultFlag":1,"createUserId":null,"createTime":"2019-01-28 17:07:05","updateUserId":null,"updateTime":"2019-01-28 17:07:09"}],"replaceClockId":null,"clockId":1346,"clockTime":"10:42","clockPlace":"万通中心","result":0,"errorCode":3},{"clockDay":"2019-02-18","clockType":2,"permitStartClockTime":null,"permitEndClockTime":"2019-02-18 15:00","updateBut":0,"lastUpdateClockTime":"2019-02-18 15:00","shiftId":4,"shiftDay":"2019.2.18","shiftTime":"12:00","clockPlaceList":[{"id":2,"propertyId":30,"mode":1,"place":"朝阳医院","effectiveDistance":200,"longitude":"116.453804","latitude":"39.925774","defaultFlag":0,"createUserId":null,"createTime":"2019-01-28 17:07:39","updateUserId":null,"updateTime":"2019-01-28 17:07:41"},{"id":1,"propertyId":30,"mode":1,"place":"万通中心","effectiveDistance":200,"longitude":"116.456014","latitude":"39.919856","defaultFlag":1,"createUserId":null,"createTime":"2019-01-28 17:07:05","updateUserId":null,"updateTime":"2019-01-28 17:07:09"}],"replaceClockId":null,"clockId":1347,"clockTime":"15:13","clockPlace":"万通中心","result":1,"errorCode":6},{"clockDay":"2019-02-18","clockType":1,"permitStartClockTime":"2019-02-18 13:00","permitEndClockTime":null,"updateBut":0,"lastUpdateClockTime":null,"shiftId":7,"shiftDay":"2019.2.18","shiftTime":"16:00","clockPlaceList":[{"id":2,"propertyId":30,"mode":1,"place":"朝阳医院","effectiveDistance":200,"longitude":"116.453804","latitude":"39.925774","defaultFlag":0,"createUserId":null,"createTime":"2019-01-28 17:07:39","updateUserId":null,"updateTime":"2019-01-28 17:07:41"},{"id":1,"propertyId":30,"mode":1,"place":"万通中心","effectiveDistance":200,"longitude":"116.456014","latitude":"39.919856","defaultFlag":1,"createUserId":null,"createTime":"2019-01-28 17:07:05","updateUserId":null,"updateTime":"2019-01-28 17:07:09"}],"replaceClockId":null,"clockId":1348,"clockTime":"13:54","clockPlace":"万通中心","result":1,"errorCode":null}]
         */

        private String rulesName;
        private int type;
        private int clock;
        private String reason;
        private String attendanceTime;
        private List<ClockRecordListBean> clockRecordList;

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

        public int getClock() {
            return clock;
        }

        public void setClock(int clock) {
            this.clock = clock;
        }

        public String getReason() {
            return reason == null ? "" : reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }

        public String getAttendanceTime() {
            return attendanceTime;
        }

        public void setAttendanceTime(String attendanceTime) {
            this.attendanceTime = attendanceTime;
        }

        public List<ClockRecordListBean> getClockRecordList() {
            return clockRecordList;
        }

        public void setClockRecordList(List<ClockRecordListBean> clockRecordList) {
            this.clockRecordList = clockRecordList;
        }

        public static class ClockRecordListBean {
            /**
             * clockDay : 2019-02-18
             * clockType : 1
             * permitStartClockTime : 2019-02-18 04:00
             * permitEndClockTime : null
             * updateBut : 0
             * lastUpdateClockTime : null
             * shiftId : 4
             * shiftDay : 2019.2.18
             * shiftTime : 07:00
             * clockPlaceList : [{"id":2,"propertyId":30,"mode":1,"place":"朝阳医院","effectiveDistance":200,"longitude":"116.453804","latitude":"39.925774","defaultFlag":0,"createUserId":null,"createTime":"2019-01-28 17:07:39","updateUserId":null,"updateTime":"2019-01-28 17:07:41"},{"id":1,"propertyId":30,"mode":1,"place":"万通中心","effectiveDistance":200,"longitude":"116.456014","latitude":"39.919856","defaultFlag":1,"createUserId":null,"createTime":"2019-01-28 17:07:05","updateUserId":null,"updateTime":"2019-01-28 17:07:09"}]
             * replaceClockId : null
             * clockId : 1346
             * clockTime : 10:42
             * clockPlace : 万通中心
             * result : 0
             * errorCode : 3
             */

            private String clockDay;
            private int clockType;
            private String permitStartClockTime;
            private String permitEndClockTime;
            private int updateBut;
            private String lastUpdateClockTime;
            private int shiftId;
            private String shiftDay;
            private String shiftTime;
            private int replaceClockId;
            private int clockId;
            private String clockTime;
            private String clockPlace;
            private int result;
            private int errorCode;
            private List<ClockPlaceListBean> clockPlaceList;
            private boolean timeTagLight;

            public String getClockDay() {
                return clockDay;
            }

            public boolean isTimeTagLight() {
                return timeTagLight;
            }

            public void setTimeTagLight(boolean timeTagLight) {
                this.timeTagLight = timeTagLight;
            }

            public void setClockDay(String clockDay) {
                this.clockDay = clockDay;
            }

            public int getClockType() {
                return clockType;
            }

            public void setClockType(int clockType) {
                this.clockType = clockType;
            }

            public String getPermitStartClockTime() {
                return permitStartClockTime;
            }

            public void setPermitStartClockTime(String permitStartClockTime) {
                this.permitStartClockTime = permitStartClockTime;
            }


            public int getUpdateBut() {
                return updateBut;
            }

            public void setUpdateBut(int updateBut) {
                this.updateBut = updateBut;
            }


            public int getShiftId() {
                return shiftId;
            }

            public void setShiftId(int shiftId) {
                this.shiftId = shiftId;
            }

            public String getShiftDay() {
                return shiftDay;
            }

            public void setShiftDay(String shiftDay) {
                this.shiftDay = shiftDay;
            }

            public String getShiftTime() {
                return shiftTime;
            }

            public void setShiftTime(String shiftTime) {
                this.shiftTime = shiftTime;
            }


            public int getClockId() {
                return clockId;
            }

            public void setClockId(int clockId) {
                this.clockId = clockId;
            }

            public String getClockTime() {
                return clockTime;
            }

            public void setClockTime(String clockTime) {
                this.clockTime = clockTime;
            }

            public String getClockPlace() {
                return clockPlace;
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

            public List<ClockPlaceListBean> getClockPlaceList() {
                return clockPlaceList;
            }

            public void setClockPlaceList(List<ClockPlaceListBean> clockPlaceList) {
                this.clockPlaceList = clockPlaceList;
            }

            public String getPermitEndClockTime() {
                return permitEndClockTime == null ? "" : permitEndClockTime;
            }

            public void setPermitEndClockTime(String permitEndClockTime) {
                this.permitEndClockTime = permitEndClockTime;
            }

            public String getLastUpdateClockTime() {
                return lastUpdateClockTime == null ? "" : lastUpdateClockTime;
            }

            public void setLastUpdateClockTime(String lastUpdateClockTime) {
                this.lastUpdateClockTime = lastUpdateClockTime;
            }

            public int getReplaceClockId() {
                return replaceClockId;
            }

            public void setReplaceClockId(int replaceClockId) {
                this.replaceClockId = replaceClockId;
            }

            public static class ClockPlaceListBean {
                /**
                 * id : 2
                 * propertyId : 30
                 * mode : 1
                 * place : 朝阳医院
                 * effectiveDistance : 200
                 * longitude : 116.453804
                 * latitude : 39.925774
                 * defaultFlag : 0
                 * createUserId : null
                 * createTime : 2019-01-28 17:07:39
                 * updateUserId : null
                 * updateTime : 2019-01-28 17:07:41
                 */

                private int id;
                private int propertyId;
                private int mode;
                private String place;
                private int effectiveDistance;
                private String longitude;
                private String latitude;
                private int defaultFlag;
                private int createUserId;
                private String createTime;
                private int updateUserId;
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

                public int getMode() {
                    return mode;
                }

                public void setMode(int mode) {
                    this.mode = mode;
                }

                public String getPlace() {
                    return place == null ? "" : place;
                }

                public void setPlace(String place) {
                    this.place = place;
                }

                public int getEffectiveDistance() {
                    return effectiveDistance;
                }

                public void setEffectiveDistance(int effectiveDistance) {
                    this.effectiveDistance = effectiveDistance;
                }

                public String getLongitude() {
                    return longitude == null ? "" : longitude;
                }

                public void setLongitude(String longitude) {
                    this.longitude = longitude;
                }

                public String getLatitude() {
                    return latitude == null ? "" : latitude;
                }

                public void setLatitude(String latitude) {
                    this.latitude = latitude;
                }

                public int getDefaultFlag() {
                    return defaultFlag;
                }

                public void setDefaultFlag(int defaultFlag) {
                    this.defaultFlag = defaultFlag;
                }

                public int getCreateUserId() {
                    return createUserId;
                }

                public void setCreateUserId(int createUserId) {
                    this.createUserId = createUserId;
                }

                public String getCreateTime() {
                    return createTime == null ? "" : createTime;
                }

                public void setCreateTime(String createTime) {
                    this.createTime = createTime;
                }

                public int getUpdateUserId() {
                    return updateUserId;
                }

                public void setUpdateUserId(int updateUserId) {
                    this.updateUserId = updateUserId;
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
}
