package com.ghs.ghspm.bean;

import java.util.List;

/**
 * Author:wang_sir
 * Time:2018/10/13 15:21
 * Description:This is UserInfoBean
 */
public class UserInfoBean {

    /**
     * code : 0
     * data : {"headImage":"","mobile":"","name":"","position":"","userConfigList":[{"title":"","value":""}],"userId":0,"workState":""}
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
         * headImage :
         * mobile :
         * name :
         * position :
         * userConfigList : [{"title":"","value":""}]
         * userId : 0
         * workState :
         */

        private String headImage;
        private String mobile;
        private String name;
        private String position;
        private String headImageBackGroudColor;
        private int userId;
        private String workState;
        private List<UserConfigListBean> userConfigList;

        public String getHeadImage() {
            return headImage;
        }

        public void setHeadImage(String headImage) {
            this.headImage = headImage;
        }

        public String getMobile() {
            return mobile;
        }

        public String getHeadImageBackGroudColor() {
            return headImageBackGroudColor == null ? "" : headImageBackGroudColor;
        }

        public void setHeadImageBackGroudColor(String headImageBackGroudColor) {
            this.headImageBackGroudColor = headImageBackGroudColor;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPosition() {
            return position;
        }

        public void setPosition(String position) {
            this.position = position;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getWorkState() {
            return workState;
        }

        public void setWorkState(String workState) {
            this.workState = workState;
        }

        public List<UserConfigListBean> getUserConfigList() {
            return userConfigList;
        }

        public void setUserConfigList(List<UserConfigListBean> userConfigList) {
            this.userConfigList = userConfigList;
        }

        public static class UserConfigListBean {
            public UserConfigListBean(String title, String value) {
                this.title = title;
                this.value = value;
            }

            /**
             * title :
             * value :
             */

            private String title;
            private String value;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }
        }
    }
}
