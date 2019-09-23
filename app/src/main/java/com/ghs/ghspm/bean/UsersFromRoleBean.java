package com.ghs.ghspm.bean;

import java.util.List;

/**
 * Author:wang_sir
 * Time:2019/4/15 11:27
 * Description:This is UsersFromRoleBean
 */
public class UsersFromRoleBean {

    /**
     * code : 1000
     * message : 成功
     * data : [{"userId":42,"name":"tly","position":"保安队长","duty":0,"workState":"","headImage":"2019022217540469110051.jpeg","mobile":"18500975534","userConfigList":[{"title":"邮箱","value":"11@qq.com"}]},{"userId":31,"name":"吴敬丰","position":"保安队长","duty":0,"workState":"","headImage":"defaultHeadImage.jpg","mobile":"18640271776","userConfigList":[{"title":"邮箱","value":"sdfsdn@sina.com"}]},{"userId":38,"name":"王斌(2)","position":"队长","duty":0,"workState":"","headImage":"2019022118421708190200.jpeg","mobile":"17568086930","userConfigList":[{"title":"邮箱","value":"11@qq.com"}]},{"userId":19,"name":"王明申","position":"保洁组长","duty":0,"workState":"","headImage":"2018102916530060810085.jpeg","mobile":"15890086993","userConfigList":[{"title":"邮箱","value":"11@qq.com"}]}]
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
         * userId : 42
         * name : tly
         * position : 保安队长
         * duty : 0
         * workState :
         * headImage : 2019022217540469110051.jpeg
         * mobile : 18500975534
         * userConfigList : [{"title":"邮箱","value":"11@qq.com"}]
         */

        private int userId;
        private String name;
        private String position;
        private int duty;
        private String workState;
        private String headImage;
        private String headImageBackGroudColor;
        private String mobile;
        private List<UserConfigListBean> userConfigList;
        private boolean isSelected;

        public String getHeadImageBackGroudColor() {
            return headImageBackGroudColor == null ? "" : headImageBackGroudColor;
        }

        public void setHeadImageBackGroudColor(String headImageBackGroudColor) {
            this.headImageBackGroudColor = headImageBackGroudColor;
        }

        public boolean isSelected() {
            return isSelected;
        }

        public void setSelected(boolean selected) {
            isSelected = selected;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
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

        public int getDuty() {
            return duty;
        }

        public void setDuty(int duty) {
            this.duty = duty;
        }

        public String getWorkState() {
            return workState;
        }

        public void setWorkState(String workState) {
            this.workState = workState;
        }

        public String getHeadImage() {
            return headImage;
        }

        public void setHeadImage(String headImage) {
            this.headImage = headImage;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public List<UserConfigListBean> getUserConfigList() {
            return userConfigList;
        }

        public void setUserConfigList(List<UserConfigListBean> userConfigList) {
            this.userConfigList = userConfigList;
        }

        public static class UserConfigListBean {
            /**
             * title : 邮箱
             * value : 11@qq.com
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
