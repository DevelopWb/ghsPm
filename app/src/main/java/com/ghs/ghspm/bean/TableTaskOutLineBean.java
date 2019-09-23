package com.ghs.ghspm.bean;

import java.util.List;

/**
 * Author:wang_sir
 * Time:2019/1/2 11:48
 * Description:This is TableTaskOutLineBean
 */
public class TableTaskOutLineBean {
    /**
     * code : 1000
     * message : 成功
     * data : [{"id":1,"title":"打扫卫生","userName":"陶龙洋","createTime":"2019-01-02 10:49:49","formType":1,"role":1,"status":1}]
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
         * id : 1
         * title : 打扫卫生
         * userName : 陶龙洋
         * createTime : 2019-01-02 10:49:49
         * formType : 1
         * role : 1
         * status : 1
         */

        private int id;
        private String title;
        private String userName;
        private String createTime;
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
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
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
    }
}
