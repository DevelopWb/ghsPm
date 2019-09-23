package com.ghs.ghspm.bean;

import java.util.List;

/**
 * Author:wang_sir
 * Time:2018/10/22 18:43
 * Description:This is MenuBean
 */
public class MenuBean {

    /**
     * code : 1000
     * message : 成功
     * data : [{"menuId":292,"name":"水电表抄录","applicationType":2,"nextLevelMenuList":[]},{"menuId":256,"name":"发布通知","applicationType":2,"nextLevelMenuList":[]},{"menuId":293,"name":"发布任务","applicationType":2,"nextLevelMenuList":[]},{"menuId":295,"name":"组织架构","applicationType":2,"nextLevelMenuList":[]},{"menuId":296,"name":"车辆信息","applicationType":2,"nextLevelMenuList":[]},{"menuId":304,"name":"业主信息","applicationType":2,"nextLevelMenuList":[]},{"menuId":305,"name":"内部报修","applicationType":2,"nextLevelMenuList":[]},{"menuId":368,"name":"签批与表单","applicationType":2,"nextLevelMenuList":[]},{"menuId":379,"name":"排班","applicationType":2,"nextLevelMenuList":[]}]
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
         * menuId : 292
         * name : 水电表抄录
         * applicationType : 2
         * nextLevelMenuList : []
         */

        private int menuId;
        private String name;
        private int applicationType;
        private List<DataBean> nextLevelMenuList;

        public int getMenuId() {
            return menuId;
        }

        public void setMenuId(int menuId) {
            this.menuId = menuId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getApplicationType() {
            return applicationType;
        }

        public void setApplicationType(int applicationType) {
            this.applicationType = applicationType;
        }

        public List<DataBean> getNextLevelMenuList() {
            return nextLevelMenuList;
        }

        public void setNextLevelMenuList(List<DataBean> nextLevelMenuList) {
            this.nextLevelMenuList = nextLevelMenuList;
        }
    }
}
