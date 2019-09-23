package com.ghs.ghspm.bean;

import java.util.List;

public class OwnerWaterBillBean {


    /**
     * code : 1000
     * message : 成功
     * data : [{"year":2019,"billDOList":[{"id":57,"type":"water","villageId":27,"orderNum":"2019072511484964896909","channelOrderNum":null,"roomId":86128,"ownerId":null,"title":"2017年水费","fee":123,"payMoney":123,"couponUserId":null,"payState":2,"payCreateTime":null,"payTime":null,"payType":null,"payUser":null,"createTime":"2019-07-25 11:48:49","updateTime":"2019-07-25 11:49:05","propertyId":30,"propertyName":"长春大禹物业公司","villageMsg":"一期-1号楼-1单元-101","villageName":"褐石公园小区（测试）","portionName":"一期","towerNumber":"1号楼","cellName":"1单元","roomNumber":"101","payUserName":null,"ownerName":"测试名字test"},{"id":53,"type":"water","villageId":27,"orderNum":"2019072511401364653731","channelOrderNum":null,"roomId":86128,"ownerId":null,"title":"2017年水费","fee":123,"payMoney":123,"couponUserId":null,"payState":2,"payCreateTime":null,"payTime":null,"payType":null,"payUser":null,"createTime":"2019-07-25 11:40:13","updateTime":"2019-07-25 11:49:14","propertyId":30,"propertyName":"长春大禹物业公司","villageMsg":"一期-1号楼-1单元-101","villageName":"褐石公园小区（测试）","portionName":"一期","towerNumber":"1号楼","cellName":"1单元","roomNumber":"101","payUserName":null,"ownerName":"测试名字test"}]},{"year":2018,"billDOList":[]}]
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
         * year : 2019
         * billDOList : [{"id":57,"type":"water","villageId":27,"orderNum":"2019072511484964896909","channelOrderNum":null,"roomId":86128,"ownerId":null,"title":"2017年水费","fee":123,"payMoney":123,"couponUserId":null,"payState":2,"payCreateTime":null,"payTime":null,"payType":null,"payUser":null,"createTime":"2019-07-25 11:48:49","updateTime":"2019-07-25 11:49:05","propertyId":30,"propertyName":"长春大禹物业公司","villageMsg":"一期-1号楼-1单元-101","villageName":"褐石公园小区（测试）","portionName":"一期","towerNumber":"1号楼","cellName":"1单元","roomNumber":"101","payUserName":null,"ownerName":"测试名字test"},{"id":53,"type":"water","villageId":27,"orderNum":"2019072511401364653731","channelOrderNum":null,"roomId":86128,"ownerId":null,"title":"2017年水费","fee":123,"payMoney":123,"couponUserId":null,"payState":2,"payCreateTime":null,"payTime":null,"payType":null,"payUser":null,"createTime":"2019-07-25 11:40:13","updateTime":"2019-07-25 11:49:14","propertyId":30,"propertyName":"长春大禹物业公司","villageMsg":"一期-1号楼-1单元-101","villageName":"褐石公园小区（测试）","portionName":"一期","towerNumber":"1号楼","cellName":"1单元","roomNumber":"101","payUserName":null,"ownerName":"测试名字test"}]
         */

        private int year;
        private List<BillDOListBean> billDOList;

        public int getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }

        public List<BillDOListBean> getBillDOList() {
            return billDOList;
        }

        public void setBillDOList(List<BillDOListBean> billDOList) {
            this.billDOList = billDOList;
        }

        public static class BillDOListBean {
            /**
             * id : 57
             * type : water
             * villageId : 27
             * orderNum : 2019072511484964896909
             * channelOrderNum : null
             * roomId : 86128
             * ownerId : null
             * title : 2017年水费
             * fee : 123.0
             * payMoney : 123.0
             * couponUserId : null
             * payState : 2
             * payCreateTime : null
             * payTime : null
             * payType : null
             * payUser : null
             * createTime : 2019-07-25 11:48:49
             * updateTime : 2019-07-25 11:49:05
             * propertyId : 30
             * propertyName : 长春大禹物业公司
             * villageMsg : 一期-1号楼-1单元-101
             * villageName : 褐石公园小区（测试）
             * portionName : 一期
             * towerNumber : 1号楼
             * cellName : 1单元
             * roomNumber : 101
             * payUserName : null
             * ownerName : 测试名字test
             */

            private int id;
            private String type;
            private int villageId;
            private String orderNum;
            private Object channelOrderNum;
            private int roomId;
            private Object ownerId;
            private String title;
            private double fee;
            private double payMoney;
            private Object couponUserId;
            private int payState;
            private Object payCreateTime;
            private Object payTime;
            private Object payType;
            private Object payUser;
            private String createTime;
            private String updateTime;
            private int propertyId;
            private String propertyName;
            private String villageMsg;
            private String villageName;
            private String portionName;
            private String towerNumber;
            private String cellName;
            private String roomNumber;
            private Object payUserName;
            private String ownerName;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public int getVillageId() {
                return villageId;
            }

            public void setVillageId(int villageId) {
                this.villageId = villageId;
            }

            public String getOrderNum() {
                return orderNum;
            }

            public void setOrderNum(String orderNum) {
                this.orderNum = orderNum;
            }

            public Object getChannelOrderNum() {
                return channelOrderNum;
            }

            public void setChannelOrderNum(Object channelOrderNum) {
                this.channelOrderNum = channelOrderNum;
            }

            public int getRoomId() {
                return roomId;
            }

            public void setRoomId(int roomId) {
                this.roomId = roomId;
            }

            public Object getOwnerId() {
                return ownerId;
            }

            public void setOwnerId(Object ownerId) {
                this.ownerId = ownerId;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public double getFee() {
                return fee;
            }

            public void setFee(double fee) {
                this.fee = fee;
            }

            public double getPayMoney() {
                return payMoney;
            }

            public void setPayMoney(double payMoney) {
                this.payMoney = payMoney;
            }

            public Object getCouponUserId() {
                return couponUserId;
            }

            public void setCouponUserId(Object couponUserId) {
                this.couponUserId = couponUserId;
            }

            public int getPayState() {
                return payState;
            }

            public void setPayState(int payState) {
                this.payState = payState;
            }

            public Object getPayCreateTime() {
                return payCreateTime;
            }

            public void setPayCreateTime(Object payCreateTime) {
                this.payCreateTime = payCreateTime;
            }

            public Object getPayTime() {
                return payTime;
            }

            public void setPayTime(Object payTime) {
                this.payTime = payTime;
            }

            public Object getPayType() {
                return payType;
            }

            public void setPayType(Object payType) {
                this.payType = payType;
            }

            public Object getPayUser() {
                return payUser;
            }

            public void setPayUser(Object payUser) {
                this.payUser = payUser;
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

            public String getPortionName() {
                return portionName;
            }

            public void setPortionName(String portionName) {
                this.portionName = portionName;
            }

            public String getTowerNumber() {
                return towerNumber;
            }

            public void setTowerNumber(String towerNumber) {
                this.towerNumber = towerNumber;
            }

            public String getCellName() {
                return cellName;
            }

            public void setCellName(String cellName) {
                this.cellName = cellName;
            }

            public String getRoomNumber() {
                return roomNumber;
            }

            public void setRoomNumber(String roomNumber) {
                this.roomNumber = roomNumber;
            }

            public Object getPayUserName() {
                return payUserName;
            }

            public void setPayUserName(Object payUserName) {
                this.payUserName = payUserName;
            }

            public String getOwnerName() {
                return ownerName;
            }

            public void setOwnerName(String ownerName) {
                this.ownerName = ownerName;
            }
        }
    }
}
