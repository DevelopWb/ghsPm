package com.ghs.ghspm.bean;

import java.util.List;

/**
 * Author:wang_sir
 * Time:2018/10/11 11:07
 * Description:This is OrganizeBean
 */
public class OrganizeBean {

    /**
     * code : 1000
     * message : 成功
     * data : {"deptId":58,"deptName":"长春大禹物业","allDeptIds":"58","allDeptNames":"公司架构","userNum":19,"nextLevelDeptList":[{"deptId":69,"deptName":"产品","userNum":4},{"deptId":59,"deptName":"技术部(王)","userNum":6},{"deptId":61,"deptName":"财务部","userNum":1},{"deptId":64,"deptName":"保安部","userNum":5}],"userList":[{"userId":45,"name":"季鹏坤","position":"管理员","duty":0,"workState":"","headImage":null,"mobile":"13810962243","userConfigList":[{"title":"邮箱","value":"java_do@qq.com"}]},{"userId":33,"name":"王总","position":"s","duty":0,"workState":"","headImage":null,"mobile":"18640271772","userConfigList":[{"title":"邮箱","value":"sdfsdn@sina.com"}]},{"userId":32,"name":"王经理","position":"s","duty":0,"workState":"","headImage":null,"mobile":"18640271771","userConfigList":[{"title":"邮箱","value":"sdfsdn@sina.com"}]}]}
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
         * deptId : 58
         * deptName : 长春大禹物业
         * allDeptIds : 58
         * allDeptNames : 公司架构
         * userNum : 19
         * nextLevelDeptList : [{"deptId":69,"deptName":"产品","userNum":4},{"deptId":59,"deptName":"技术部(王)","userNum":6},{"deptId":61,"deptName":"财务部","userNum":1},{"deptId":64,"deptName":"保安部","userNum":5}]
         * userList : [{"userId":45,"name":"季鹏坤","position":"管理员","duty":0,"workState":"","headImage":null,"mobile":"13810962243","userConfigList":[{"title":"邮箱","value":"java_do@qq.com"}]},{"userId":33,"name":"王总","position":"s","duty":0,"workState":"","headImage":null,"mobile":"18640271772","userConfigList":[{"title":"邮箱","value":"sdfsdn@sina.com"}]},{"userId":32,"name":"王经理","position":"s","duty":0,"workState":"","headImage":null,"mobile":"18640271771","userConfigList":[{"title":"邮箱","value":"sdfsdn@sina.com"}]}]
         */

        private int deptId;
        private String deptName;
        private String allDeptIds;
        private String allDeptNames;
        private int userNum;
        private List<NextLevelDeptListBean> nextLevelDeptList;
        private List<UserListBean> userList;

        public int getDeptId() {
            return deptId;
        }

        public void setDeptId(int deptId) {
            this.deptId = deptId;
        }

        public String getDeptName() {
            return deptName;
        }

        public void setDeptName(String deptName) {
            this.deptName = deptName;
        }

        public String getAllDeptIds() {
            return allDeptIds;
        }

        public void setAllDeptIds(String allDeptIds) {
            this.allDeptIds = allDeptIds;
        }

        public String getAllDeptNames() {
            return allDeptNames;
        }

        public void setAllDeptNames(String allDeptNames) {
            this.allDeptNames = allDeptNames;
        }

        public int getUserNum() {
            return userNum;
        }

        public void setUserNum(int userNum) {
            this.userNum = userNum;
        }

        public List<NextLevelDeptListBean> getNextLevelDeptList() {
            return nextLevelDeptList;
        }

        public void setNextLevelDeptList(List<NextLevelDeptListBean> nextLevelDeptList) {
            this.nextLevelDeptList = nextLevelDeptList;
        }

        public List<UserListBean> getUserList() {
            return userList;
        }

        public void setUserList(List<UserListBean> userList) {
            this.userList = userList;
        }

        public static class NextLevelDeptListBean {
            /**
             * deptId : 69
             * deptName : 产品
             * userNum : 4
             */

            private int deptId;
            private String deptName;
            private int userNum;

            public int getDeptId() {
                return deptId;
            }

            public void setDeptId(int deptId) {
                this.deptId = deptId;
            }

            public String getDeptName() {
                return deptName;
            }

            public void setDeptName(String deptName) {
                this.deptName = deptName;
            }

            public int getUserNum() {
                return userNum;
            }

            public void setUserNum(int userNum) {
                this.userNum = userNum;
            }
        }

        public static class UserListBean {
            /**
             * userId : 45
             * name : 季鹏坤
             * position : 管理员
             * duty : 0
             * workState :
             * headImage : null
             * mobile : 13810962243
             * userConfigList : [{"title":"邮箱","value":"java_do@qq.com"}]
             */

            private int userId;
            private String name;
            private String position;
            private int duty;
            private String workState;
            private String headImage;
            private String mobile;
            private List<UserConfigListBean> userConfigList;
            private boolean selected;

            public boolean isSelected() {
                return selected;
            }

            public void setSelected(boolean selected) {
                this.selected = selected;
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
                return headImage == null ? "" : headImage;
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
                 * value : java_do@qq.com
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
}
