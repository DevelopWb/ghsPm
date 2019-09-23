package com.ghs.ghspm.bean;

import java.util.List;

public class OwnerPropretyBean {

    /**
     * code : 1000
     * data : [{"cellName":"1单元","createTime":"2019-07-26 17:59:49","fee":1542,"id":58,"orderNum":"2019072617594927150915","ownerName":"测试名字test","payMoney":1542,"payState":2,"portionName":"一期","propertyId":30,"propertyName":"长春大禹物业公司","roomId":86128,"roomNumber":"101","title":"7月-9月物业管理费","towerNumber":"1号楼","type":"property","updateTime":"2019-07-26 18:04:07","villageId":27,"villageMsg":"一期-1号楼-1单元-101","villageName":"褐石公园小区（测试）"}]
     * message : 成功
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
         * cellName : 1单元
         * createTime : 2019-07-26 17:59:49
         * fee : 1542.0
         * id : 58
         * orderNum : 2019072617594927150915
         * ownerName : 测试名字test
         * payMoney : 1542.0
         * payState : 2
         * portionName : 一期
         * propertyId : 30
         * propertyName : 长春大禹物业公司
         * roomId : 86128
         * roomNumber : 101
         * title : 7月-9月物业管理费
         * towerNumber : 1号楼
         * type : property
         * updateTime : 2019-07-26 18:04:07
         * villageId : 27
         * villageMsg : 一期-1号楼-1单元-101
         * villageName : 褐石公园小区（测试）
         */

        private String cellName;
        private String createTime;
        private double fee;
        private int id;
        private String orderNum;
        private String ownerName;
        private double payMoney;
        private int payState;
        private String portionName;
        private int propertyId;
        private String propertyName;
        private int roomId;
        private String roomNumber;
        private String title;
        private String towerNumber;
        private String type;
        private String updateTime;
        private int villageId;
        private String villageMsg;
        private String villageName;

        public String getCellName() {
            return cellName;
        }

        public void setCellName(String cellName) {
            this.cellName = cellName;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public double getFee() {
            return fee;
        }

        public void setFee(double fee) {
            this.fee = fee;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getOrderNum() {
            return orderNum;
        }

        public void setOrderNum(String orderNum) {
            this.orderNum = orderNum;
        }

        public String getOwnerName() {
            return ownerName;
        }

        public void setOwnerName(String ownerName) {
            this.ownerName = ownerName;
        }

        public double getPayMoney() {
            return payMoney;
        }

        public void setPayMoney(double payMoney) {
            this.payMoney = payMoney;
        }

        public int getPayState() {
            return payState;
        }

        public void setPayState(int payState) {
            this.payState = payState;
        }

        public String getPortionName() {
            return portionName;
        }

        public void setPortionName(String portionName) {
            this.portionName = portionName;
        }

        public int getPropertyId() {
            return propertyId;
        }

        public void setPropertyId(int propertyId) {
            this.propertyId = propertyId;
        }

        public String getPropertyName() {
            return propertyName;
        }

        public void setPropertyName(String propertyName) {
            this.propertyName = propertyName;
        }

        public int getRoomId() {
            return roomId;
        }

        public void setRoomId(int roomId) {
            this.roomId = roomId;
        }

        public String getRoomNumber() {
            return roomNumber;
        }

        public void setRoomNumber(String roomNumber) {
            this.roomNumber = roomNumber;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTowerNumber() {
            return towerNumber;
        }

        public void setTowerNumber(String towerNumber) {
            this.towerNumber = towerNumber;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public int getVillageId() {
            return villageId;
        }

        public void setVillageId(int villageId) {
            this.villageId = villageId;
        }

        public String getVillageMsg() {
            return villageMsg;
        }

        public void setVillageMsg(String villageMsg) {
            this.villageMsg = villageMsg;
        }

        public String getVillageName() {
            return villageName;
        }

        public void setVillageName(String villageName) {
            this.villageName = villageName;
        }
    }
}
