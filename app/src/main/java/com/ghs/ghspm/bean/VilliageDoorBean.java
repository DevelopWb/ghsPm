package com.ghs.ghspm.bean;

import java.util.List;

/**
 * Author:wang_sir
 * Time:2018/9/27 19:08
 * Description:This is VilliageDoorBean
 */
public class VilliageDoorBean {
    /**
     * code : 1000
     * message : 成功
     * data : [{"id":8,"villageId":24,"cellId":null,"deviceType":1,"deviceName":"东门","macAddress":"ec:3d:fd:77:05:74","deviceState":1,"createTime":"2018-07-05 14:51:44","updateTime":"2018-07-05 14:51:40","apkManagerId":0,"apkUpdateTime":null,"version":null,"remark":null,"villageMsg":null,"villageName":null,"portionId":null,"portionName":null,"towerId":null,"towerNumber":null,"cellName":null},{"id":3,"villageId":24,"cellId":null,"deviceType":1,"deviceName":"大门","macAddress":"8c:18:d9:e5:98:a6","deviceState":2,"createTime":null,"updateTime":"2018-07-25 14:22:36","apkManagerId":0,"apkUpdateTime":null,"version":null,"remark":null,"villageMsg":null,"villageName":null,"portionId":null,"portionName":null,"towerId":null,"towerNumber":null,"cellName":null}]
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
         * id : 8
         * villageId : 24
         * cellId : null
         * deviceType : 1
         * deviceName : 东门
         * macAddress : ec:3d:fd:77:05:74
         * deviceState : 1
         * createTime : 2018-07-05 14:51:44
         * updateTime : 2018-07-05 14:51:40
         * apkManagerId : 0
         * apkUpdateTime : null
         * version : null
         * remark : null
         * villageMsg : null
         * villageName : null
         * portionId : null
         * portionName : null
         * towerId : null
         * towerNumber : null
         * cellName : null
         */

        private int id;
        private int villageId;
        private Object cellId;
        private int deviceType;
        private String deviceName;
        private String macAddress;
        private int deviceState;
        private String createTime;
        private String updateTime;
        private int apkManagerId;
        private Object apkUpdateTime;
        private Object version;
        private Object remark;
        private Object villageMsg;
        private Object villageName;
        private Object portionId;
        private Object portionName;
        private Object towerId;
        private Object towerNumber;
        private Object cellName;

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

        public Object getCellId() {
            return cellId;
        }

        public void setCellId(Object cellId) {
            this.cellId = cellId;
        }

        public int getDeviceType() {
            return deviceType;
        }

        public void setDeviceType(int deviceType) {
            this.deviceType = deviceType;
        }

        public String getDeviceName() {
            return deviceName;
        }

        public void setDeviceName(String deviceName) {
            this.deviceName = deviceName;
        }

        public String getMacAddress() {
            return macAddress;
        }

        public void setMacAddress(String macAddress) {
            this.macAddress = macAddress;
        }

        public int getDeviceState() {
            return deviceState;
        }

        public void setDeviceState(int deviceState) {
            this.deviceState = deviceState;
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

        public int getApkManagerId() {
            return apkManagerId;
        }

        public void setApkManagerId(int apkManagerId) {
            this.apkManagerId = apkManagerId;
        }

        public Object getApkUpdateTime() {
            return apkUpdateTime;
        }

        public void setApkUpdateTime(Object apkUpdateTime) {
            this.apkUpdateTime = apkUpdateTime;
        }

        public Object getVersion() {
            return version;
        }

        public void setVersion(Object version) {
            this.version = version;
        }

        public Object getRemark() {
            return remark;
        }

        public void setRemark(Object remark) {
            this.remark = remark;
        }

        public Object getVillageMsg() {
            return villageMsg;
        }

        public void setVillageMsg(Object villageMsg) {
            this.villageMsg = villageMsg;
        }

        public Object getVillageName() {
            return villageName;
        }

        public void setVillageName(Object villageName) {
            this.villageName = villageName;
        }

        public Object getPortionId() {
            return portionId;
        }

        public void setPortionId(Object portionId) {
            this.portionId = portionId;
        }

        public Object getPortionName() {
            return portionName;
        }

        public void setPortionName(Object portionName) {
            this.portionName = portionName;
        }

        public Object getTowerId() {
            return towerId;
        }

        public void setTowerId(Object towerId) {
            this.towerId = towerId;
        }

        public Object getTowerNumber() {
            return towerNumber;
        }

        public void setTowerNumber(Object towerNumber) {
            this.towerNumber = towerNumber;
        }

        public Object getCellName() {
            return cellName;
        }

        public void setCellName(Object cellName) {
            this.cellName = cellName;
        }
    }
}
