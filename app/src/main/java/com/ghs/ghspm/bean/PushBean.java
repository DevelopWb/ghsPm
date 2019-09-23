package com.ghs.ghspm.bean;

public class PushBean {


    /**
     * _ALIYUN_NOTIFICATION_ID_ : 25119
     * noticeType : notice
     * _ALIYUN_NOTIFICATION_PRIORITY_ : 1
     */

    private String _ALIYUN_NOTIFICATION_ID_;
    private String noticeType;
    private String recordId;
    private String _ALIYUN_NOTIFICATION_PRIORITY_;

    public String getRecordId() {
        return recordId == null ? "" : recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public String get_ALIYUN_NOTIFICATION_ID_() {
        return _ALIYUN_NOTIFICATION_ID_;
    }

    public void set_ALIYUN_NOTIFICATION_ID_(String _ALIYUN_NOTIFICATION_ID_) {
        this._ALIYUN_NOTIFICATION_ID_ = _ALIYUN_NOTIFICATION_ID_;
    }

    public String getNoticeType() {
        return noticeType;
    }

    public void setNoticeType(String noticeType) {
        this.noticeType = noticeType;
    }

    public String get_ALIYUN_NOTIFICATION_PRIORITY_() {
        return _ALIYUN_NOTIFICATION_PRIORITY_;
    }

    public void set_ALIYUN_NOTIFICATION_PRIORITY_(String _ALIYUN_NOTIFICATION_PRIORITY_) {
        this._ALIYUN_NOTIFICATION_PRIORITY_ = _ALIYUN_NOTIFICATION_PRIORITY_;
    }
}
