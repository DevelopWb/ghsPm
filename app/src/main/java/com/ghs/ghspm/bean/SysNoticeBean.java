package com.ghs.ghspm.bean;

import java.util.List;

/**
 * Author:wang_sir
 * Time:2019/2/21 15:08
 * Description:This is SysNoticeBean  系统消息
 */
public class SysNoticeBean {

    /**
     * code : 1000
     * message : 成功
     * data : [{"id":22,"type":1,"title":"排班消息","content":"您好，陶龙洋发布了#技术部(王)2019-03的排班#请前往\u201c排班\u201d工具中查看具体排班情况！","receiveUserId":38,"createTime":null},{"id":17,"type":1,"title":"排班消息","content":"您好，您在#技术部(王)2019-02的排班有变动#请注意上班时间。具体变动请前往\u201c排班\u201d应用中查看！","receiveUserId":38,"createTime":null},{"id":14,"type":1,"title":"排班消息","content":"您好，您在#技术部(王)2019-02的排班有变动#请注意上班时间。具体变动请前往\u201c排班\u201d应用中查看！","receiveUserId":38,"createTime":null},{"id":11,"type":1,"title":"排班消息","content":"您好，您在#技术部(王)2019-02的排班有变动#请注意上班时间。具体变动请前往\u201c排班\u201d应用中查看！","receiveUserId":38,"createTime":null},{"id":8,"type":1,"title":"排班消息","content":"您好，您在#技术部(王)2019-02的排班有变动#请注意上班时间。具体变动请前往\u201c排班\u201d应用中查看！","receiveUserId":38,"createTime":null},{"id":4,"type":1,"title":"排班消息","content":"您好，您在#技术部(王)2019-02的排班有变动#请注意上班时间。具体变动请前往\u201c排班\u201d应用中查看！","receiveUserId":38,"createTime":null}]
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
         * id : 22
         * type : 1
         * title : 排班消息
         * content : 您好，陶龙洋发布了#技术部(王)2019-03的排班#请前往“排班”工具中查看具体排班情况！
         * receiveUserId : 38
         * createTime : null
         */

        private int id;
        private int type;
        private String title;
        private String content;
        private int receiveUserId;
        private String createTime;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getTitle() {
            return title == null ? "" : title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content == null ? "" : content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getReceiveUserId() {
            return receiveUserId;
        }

        public void setReceiveUserId(int receiveUserId) {
            this.receiveUserId = receiveUserId;
        }

        public String getCreateTime() {
            return createTime == null ? "" : createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }
    }
}
