package com.ghs.ghspm.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Author:wang_sir
 * Time:2019/1/8 14:12
 * Description:This is FormTaskRecordBean
 */
public class FormTaskRecordBean {
    /**
     * code : 1000
     * message : 成功
     * data : [{"id":7,"title":"222222","userName":"王斌(2)","createTime":"2019-01-08 11:47:44","time":"2019-01-08 11:44:26","formType":1,"role":2,"status":3},{"id":6,"title":"11111","userName":"王斌(2)","createTime":"2019-01-08 11:47:34","time":"2019-01-08 11:40:20","formType":1,"role":2,"status":3},{"id":4,"title":"测试标题","userName":"王斌(2)","createTime":"2019-01-08 11:38:51","time":"2019-01-08 11:37:31","formType":1,"role":2,"status":3},{"id":5,"title":"打扫楼道","userName":"王斌(2)","createTime":"2019-01-08 11:33:00","time":"2019-01-08 11:31:39","formType":1,"role":2,"status":3},{"id":3,"title":"测试标题","userName":"王斌(2)","createTime":"2019-01-08 11:26:43","time":"2019-01-08 11:00:02","formType":1,"role":2,"status":3}]
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
         * id : 7
         * title : 222222
         * userName : 王斌(2)
         * createTime : 2019-01-08 11:47:44
         * time : 2019-01-08 11:44:26
         * formType : 1
         * role : 2
         * status : 3
         */

        private int id;
        private String title;
        private String userName;
        private String createTime;
        private String time;
        private int formType;
        private int role;
        private int status;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title == null ? "" : title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUserName() {
            return userName == null ? "" : userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getCreateTime() {
            return createTime == null ? "" : createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getTime() {
            return time == null ? "" : time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public int getFormType() {
            return formType;
        }

        public void setFormType(int formType) {
            this.formType = formType;
        }

        public int getRole() {
            return role;
        }

        public void setRole(int role) {
            this.role = role;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.id);
            dest.writeString(this.title);
            dest.writeString(this.userName);
            dest.writeString(this.createTime);
            dest.writeString(this.time);
            dest.writeInt(this.formType);
            dest.writeInt(this.role);
            dest.writeInt(this.status);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.id = in.readInt();
            this.title = in.readString();
            this.userName = in.readString();
            this.createTime = in.readString();
            this.time = in.readString();
            this.formType = in.readInt();
            this.role = in.readInt();
            this.status = in.readInt();
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
