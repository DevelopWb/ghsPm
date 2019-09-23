package com.ghs.ghspm.bean;

import java.util.List;

/**
 * Author:wang_sir
 * Time:2019/4/15 10:40
 * Description:This is RoleBean
 */
public class RoleBean {

    /**
     * code : 1000
     * message : 成功
     * data : [{"roleId":52,"roleName":"新增角色","userNum":4},{"roleId":58,"roleName":"过","userNum":0}]
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
         * roleId : 52
         * roleName : 新增角色
         * userNum : 4
         */

        private int roleId;
        private String roleName;
        private int userNum;

        public int getRoleId() {
            return roleId;
        }

        public void setRoleId(int roleId) {
            this.roleId = roleId;
        }

        public String getRoleName() {
            return roleName;
        }

        public void setRoleName(String roleName) {
            this.roleName = roleName;
        }

        public int getUserNum() {
            return userNum;
        }

        public void setUserNum(int userNum) {
            this.userNum = userNum;
        }
    }
}
