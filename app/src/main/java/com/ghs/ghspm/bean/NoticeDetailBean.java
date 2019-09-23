package com.ghs.ghspm.bean;

/**
 * Author:wang_sir
 * Time:2018/9/29 18:09
 * Description:This is NoticeDetailBean
 */
public class NoticeDetailBean {
    /**
     * code : 1000
     * message : 成功
     * data : {"id":29,"villageId":24,"createUserId":19,"title":"国庆七天乐","content":"nba","deptIds":"58","deptNames":"长春大禹物业管理有限公司（测试）,","receiveUserId":null,"type":1,"imageUrl":"(null)2018092917233343610069.jpeg","createTime":"2018-09-29 17:23:33","updateTime":"2018-09-29 17:23:33","createUserName":"王明申"}
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
         * id : 29
         * villageId : 24
         * createUserId : 19
         * title : 国庆七天乐
         * content : nba
         * deptIds : 58
         * deptNames : 长春大禹物业管理有限公司（测试）,
         * receiveUserId : null
         * type : 1
         * imageUrl : (null)2018092917233343610069.jpeg
         * createTime : 2018-09-29 17:23:33
         * updateTime : 2018-09-29 17:23:33
         * createUserName : 王明申
         */

        private int id;
        private int villageId;
        private int createUserId;
        private String title;
        private String content;
        private String deptIds;
        private String deptNames;
        private Object receiveUserId;
        private int type;
        private String imageUrl;
        private String createTime;
        private String updateTime;
        private String createUserName;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getVillageId() {
            return villageId;
        }

        public void setVillageId(int villageId) {
            this.villageId = villageId;
        }

        public int getCreateUserId() {
            return createUserId;
        }

        public void setCreateUserId(int createUserId) {
            this.createUserId = createUserId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getDeptIds() {
            return deptIds;
        }

        public void setDeptIds(String deptIds) {
            this.deptIds = deptIds;
        }

        public String getDeptNames() {
            return deptNames;
        }

        public void setDeptNames(String deptNames) {
            this.deptNames = deptNames;
        }

        public Object getReceiveUserId() {
            return receiveUserId;
        }

        public void setReceiveUserId(Object receiveUserId) {
            this.receiveUserId = receiveUserId;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public String getCreateUserName() {
            return createUserName;
        }

        public void setCreateUserName(String createUserName) {
            this.createUserName = createUserName;
        }
    }
}
