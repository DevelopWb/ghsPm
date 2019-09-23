package com.ghs.ghspm.bean;

import com.contrarywind.interfaces.IPickerViewData;

import java.util.List;

public class RoomBean {


    /**
     * code : 0
     * data : [{"ammeterNumber":0,"ammeterPrice":0,"cellId":0,"cellName":"","id":0,"ownerName":"","portionId":0,"portionName":"","propertyId":0,"roomNumber":"","roomType":0,"size":0,"towerId":0,"towerNumber":"","updateTime":"","villageId":0,"villageMsg":"","villageName":"","waterNumber":0,"waterPrice":0}]
     * message :
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

    public static class DataBean  implements IPickerViewData {
        /**
         * ammeterNumber : 0
         * ammeterPrice : 0
         * cellId : 0
         * cellName :
         * id : 0
         * ownerName :
         * portionId : 0
         * portionName :
         * propertyId : 0
         * roomNumber :
         * roomType : 0
         * size : 0
         * towerId : 0
         * towerNumber :
         * updateTime :
         * villageId : 0
         * villageMsg :
         * villageName :
         * waterNumber : 0
         * waterPrice : 0
         */

        private double ammeterNumber;
        private double ammeterPrice;
        private long cellId;
        private String cellName;
        private long id;
        private String ownerName;
        private long portionId;
        private String portionName;
        private long propertyId;
        private String roomNumber;
        private long roomType;
        private double size;
        private long towerId;
        private String towerNumber;
        private String updateTime;
        private long villageId;
        private String villageMsg;
        private String villageName;
        private double waterNumber;
        private double waterPrice;

        public double getAmmeterNumber() {
            return ammeterNumber;
        }

        public void setAmmeterNumber(long ammeterNumber) {
            this.ammeterNumber = ammeterNumber;
        }

        public double getAmmeterPrice() {
            return ammeterPrice;
        }

        public void setAmmeterPrice(long ammeterPrice) {
            this.ammeterPrice = ammeterPrice;
        }

        public long getCellId() {
            return cellId;
        }

        public void setCellId(long cellId) {
            this.cellId = cellId;
        }

        public String getCellName() {
            return cellName;
        }

        public void setCellName(String cellName) {
            this.cellName = cellName;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getOwnerName() {
            return ownerName;
        }

        public void setOwnerName(String ownerName) {
            this.ownerName = ownerName;
        }

        public long getPortionId() {
            return portionId;
        }

        public void setPortionId(long portionId) {
            this.portionId = portionId;
        }

        public String getPortionName() {
            return portionName;
        }

        public void setPortionName(String portionName) {
            this.portionName = portionName;
        }

        public long getPropertyId() {
            return propertyId;
        }

        public void setPropertyId(long propertyId) {
            this.propertyId = propertyId;
        }

        public String getRoomNumber() {
            return roomNumber;
        }

        public void setRoomNumber(String roomNumber) {
            this.roomNumber = roomNumber;
        }

        public long getRoomType() {
            return roomType;
        }

        public void setRoomType(long roomType) {
            this.roomType = roomType;
        }

        public double getSize() {
            return size;
        }

        public void setSize(long size) {
            this.size = size;
        }

        public long getTowerId() {
            return towerId;
        }

        public void setTowerId(long towerId) {
            this.towerId = towerId;
        }

        public String getTowerNumber() {
            return towerNumber;
        }

        public void setTowerNumber(String towerNumber) {
            this.towerNumber = towerNumber;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public long getVillageId() {
            return villageId;
        }

        public void setVillageId(long villageId) {
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

        public double getWaterNumber() {
            return waterNumber;
        }

        public void setWaterNumber(long waterNumber) {
            this.waterNumber = waterNumber;
        }

        public double getWaterPrice() {
            return waterPrice;
        }

        public void setWaterPrice(long waterPrice) {
            this.waterPrice = waterPrice;
        }

        @Override
        public String getPickerViewText() {
            return roomNumber;
        }
    }
}
