package com.ghs.ghspm.bean;

/**
 * Author:wang_sir
 * Time:2019/7/12 14:31
 * Description:This is WorkOrderUnreadBean
 */
public class WorkOrderUnreadBean {
    /**
     * code : 1000
     * message : 成功
     * data : {"userId":38,"villageId":27,"unreadNumTotal":0,"unreadNumIng":0,"unreadNumCc":0,"unreadNumCheck":0,"updateTime":null}
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
         * userId : 38
         * villageId : 27
         * unreadNumTotal : 0
         * unreadNumIng : 0
         * unreadNumCc : 0
         * unreadNumCheck : 0
         * updateTime : null
         */

        private int userId;
        private int villageId;
        private int unreadNumTotal;
        private int unreadNumIng;
        private int unreadNumCc;
        private int unreadNumCheck;
        private String updateTime;

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getVillageId() {
            return villageId;
        }

        public void setVillageId(int villageId) {
            this.villageId = villageId;
        }

        public int getUnreadNumTotal() {
            return unreadNumTotal;
        }

        public void setUnreadNumTotal(int unreadNumTotal) {
            this.unreadNumTotal = unreadNumTotal;
        }

        public int getUnreadNumIng() {
            return unreadNumIng;
        }

        public void setUnreadNumIng(int unreadNumIng) {
            this.unreadNumIng = unreadNumIng;
        }

        public int getUnreadNumCc() {
            return unreadNumCc;
        }

        public void setUnreadNumCc(int unreadNumCc) {
            this.unreadNumCc = unreadNumCc;
        }

        public int getUnreadNumCheck() {
            return unreadNumCheck;
        }

        public void setUnreadNumCheck(int unreadNumCheck) {
            this.unreadNumCheck = unreadNumCheck;
        }

        public String getUpdateTime() {
            return updateTime == null ? "" : updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }
    }
}
