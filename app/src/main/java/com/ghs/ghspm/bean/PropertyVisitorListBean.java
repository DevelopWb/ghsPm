package com.ghs.ghspm.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * created by guohainan
 * created date 2019/8/23
 * application   物业访客列表
 * package name com.ghs.ghspm.bean
 */
public class PropertyVisitorListBean {


    /**
     * code : 0
     * data : [{"auditGhsUserId":0,"auditPmUserId":0,"auditTime":"","carNum":"","createTime":"","disabled":0,"ghsUserId":0,"ghsUserMobile":"","id":0,"lockPassword":"","passworkEndTime":"","passworkStartTime":"","qrCode":"","reason":"","roomFullName":"","roomId":0,"source":0,"state":0,"type":0,"updateTime":"","villageId":0,"villageName":"","visitDay":"","visitorMobile":"","visitorName":"","visitorUnionId":""}]
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

    public static class DataBean implements Parcelable {
        /**
         * auditGhsUserId : 0
         * auditPmUserId : 0
         * auditTime :
         * carNum :
         * createTime :
         * disabled : 0
         * ghsUserId : 0
         * ghsUserMobile :
         * id : 0
         * lockPassword :
         * passworkEndTime :
         * passworkStartTime :
         * qrCode :
         * reason :
         * roomFullName :
         * roomId : 0
         * source : 0
         * state : 0
         * type : 0
         * updateTime :
         * villageId : 0
         * villageName :
         * visitDay :
         * visitorMobile :
         * visitorName :
         * visitorUnionId :
         */

        private int auditGhsUserId;
        private int auditPmUserId;
        private String auditTime;
        private String carNum;
        private String createTime;
        private int disabled;
        private int ghsUserId;
        private String ghsUserMobile;
        private int id;
        private String lockPassword;
        private String passworkEndTime;
        private String passworkStartTime;
        private String qrCode;
        private String reason;
        private String roomFullName;
        private int roomId;
        private int source;
        private int state;
        private int type;
        private String updateTime;
        private int villageId;
        private String villageName;
        private String visitDay;
        private String visitorMobile;
        private String visitorName;
        private String visitorUnionId;

        public int getAuditGhsUserId() {
            return auditGhsUserId;
        }

        public void setAuditGhsUserId(int auditGhsUserId) {
            this.auditGhsUserId = auditGhsUserId;
        }

        public int getAuditPmUserId() {
            return auditPmUserId;
        }

        public void setAuditPmUserId(int auditPmUserId) {
            this.auditPmUserId = auditPmUserId;
        }

        public String getAuditTime() {
            return auditTime == null ? "" : auditTime;
        }

        public void setAuditTime(String auditTime) {
            this.auditTime = auditTime;
        }

        public String getCarNum() {
            return carNum == null ? "" : carNum;
        }

        public void setCarNum(String carNum) {
            this.carNum = carNum;
        }

        public String getCreateTime() {
            return createTime == null ? "" : createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public int getDisabled() {
            return disabled;
        }

        public void setDisabled(int disabled) {
            this.disabled = disabled;
        }

        public int getGhsUserId() {
            return ghsUserId;
        }

        public void setGhsUserId(int ghsUserId) {
            this.ghsUserId = ghsUserId;
        }

        public String getGhsUserMobile() {
            return ghsUserMobile == null ? "" : ghsUserMobile;
        }

        public void setGhsUserMobile(String ghsUserMobile) {
            this.ghsUserMobile = ghsUserMobile;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getLockPassword() {
            return lockPassword == null ? "" : lockPassword;
        }

        public void setLockPassword(String lockPassword) {
            this.lockPassword = lockPassword;
        }

        public String getPassworkEndTime() {
            return passworkEndTime == null ? "" : passworkEndTime;
        }

        public void setPassworkEndTime(String passworkEndTime) {
            this.passworkEndTime = passworkEndTime;
        }

        public String getPassworkStartTime() {
            return passworkStartTime == null ? "" : passworkStartTime;
        }

        public void setPassworkStartTime(String passworkStartTime) {
            this.passworkStartTime = passworkStartTime;
        }

        public String getQrCode() {
            return qrCode == null ? "" : qrCode;
        }

        public void setQrCode(String qrCode) {
            this.qrCode = qrCode;
        }

        public String getReason() {
            return reason == null ? "" : reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }

        public String getRoomFullName() {
            return roomFullName == null ? "" : roomFullName;
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

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getUpdateTime() {
            return updateTime == null ? "" : updateTime;
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

        public String getVillageName() {
            return villageName == null ? "" : villageName;
        }

        public void setVillageName(String villageName) {
            this.villageName = villageName;
        }

        public String getVisitDay() {
            return visitDay == null ? "" : visitDay;
        }

        public void setVisitDay(String visitDay) {
            this.visitDay = visitDay;
        }

        public String getVisitorMobile() {
            return visitorMobile == null ? "" : visitorMobile;
        }

        public void setVisitorMobile(String visitorMobile) {
            this.visitorMobile = visitorMobile;
        }

        public String getVisitorName() {
            return visitorName == null ? "" : visitorName;
        }

        public void setVisitorName(String visitorName) {
            this.visitorName = visitorName;
        }

        public String getVisitorUnionId() {
            return visitorUnionId == null ? "" : visitorUnionId;
        }

        public void setVisitorUnionId(String visitorUnionId) {
            this.visitorUnionId = visitorUnionId;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.auditGhsUserId);
            dest.writeInt(this.auditPmUserId);
            dest.writeString(this.auditTime);
            dest.writeString(this.carNum);
            dest.writeString(this.createTime);
            dest.writeInt(this.disabled);
            dest.writeInt(this.ghsUserId);
            dest.writeString(this.ghsUserMobile);
            dest.writeInt(this.id);
            dest.writeString(this.lockPassword);
            dest.writeString(this.passworkEndTime);
            dest.writeString(this.passworkStartTime);
            dest.writeString(this.qrCode);
            dest.writeString(this.reason);
            dest.writeString(this.roomFullName);
            dest.writeInt(this.roomId);
            dest.writeInt(this.source);
            dest.writeInt(this.state);
            dest.writeInt(this.type);
            dest.writeString(this.updateTime);
            dest.writeInt(this.villageId);
            dest.writeString(this.villageName);
            dest.writeString(this.visitDay);
            dest.writeString(this.visitorMobile);
            dest.writeString(this.visitorName);
            dest.writeString(this.visitorUnionId);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.auditGhsUserId = in.readInt();
            this.auditPmUserId = in.readInt();
            this.auditTime = in.readString();
            this.carNum = in.readString();
            this.createTime = in.readString();
            this.disabled = in.readInt();
            this.ghsUserId = in.readInt();
            this.ghsUserMobile = in.readString();
            this.id = in.readInt();
            this.lockPassword = in.readString();
            this.passworkEndTime = in.readString();
            this.passworkStartTime = in.readString();
            this.qrCode = in.readString();
            this.reason = in.readString();
            this.roomFullName = in.readString();
            this.roomId = in.readInt();
            this.source = in.readInt();
            this.state = in.readInt();
            this.type = in.readInt();
            this.updateTime = in.readString();
            this.villageId = in.readInt();
            this.villageName = in.readString();
            this.visitDay = in.readString();
            this.visitorMobile = in.readString();
            this.visitorName = in.readString();
            this.visitorUnionId = in.readString();
        }

        public static final Parcelable.Creator<DataBean> CREATOR = new Parcelable.Creator<DataBean>() {
            @Override
            public DataBean createFromParcel(Parcel source) {
                return new DataBean(source);
            }

            @Override
            public DataBean[] newArray(int size) {
                return new DataBean[size];
            }
        };
    }
}
