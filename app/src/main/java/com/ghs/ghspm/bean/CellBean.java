package com.ghs.ghspm.bean;

import com.contrarywind.interfaces.IPickerViewData;

import java.util.List;

/**
 * Author:wang_sir
 * Time:2018/9/28 14:00
 * Description:This is CellBean
 */
public class CellBean {
    /**
     * code : 1000
     * message : 成功
     * data : [{"id":385,"towerId":267,"cellName":"1单元","updateTime":"2018-05-29 14:42:48","towerNumber":"2号楼","portionId":8,"portionName":"一期","villageId":24,"villageName":"褐石公园小区"},{"id":384,"towerId":267,"cellName":"2单元","updateTime":"2018-05-29 14:42:48","towerNumber":"2号楼","portionId":8,"portionName":"一期","villageId":24,"villageName":"褐石公园小区"},{"id":383,"towerId":267,"cellName":"3单元","updateTime":"2018-05-29 14:42:48","towerNumber":"2号楼","portionId":8,"portionName":"一期","villageId":24,"villageName":"褐石公园小区"}]
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
         * id : 385
         * towerId : 267
         * cellName : 1单元
         * updateTime : 2018-05-29 14:42:48
         * towerNumber : 2号楼
         * portionId : 8
         * portionName : 一期
         * villageId : 24
         * villageName : 褐石公园小区
         */

        private int id;
        private int towerId;
        private String cellName;
        private String updateTime;
        private String towerNumber;
        private int portionId;
        private String portionName;
        private int villageId;
        private String villageName;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getTowerId() {
            return towerId;
        }

        public void setTowerId(int towerId) {
            this.towerId = towerId;
        }

        public String getCellName() {
            return cellName;
        }

        public void setCellName(String cellName) {
            this.cellName = cellName;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public String getTowerNumber() {
            return towerNumber;
        }

        public void setTowerNumber(String towerNumber) {
            this.towerNumber = towerNumber;
        }

        public int getPortionId() {
            return portionId;
        }

        public void setPortionId(int portionId) {
            this.portionId = portionId;
        }

        public String getPortionName() {
            return portionName;
        }

        public void setPortionName(String portionName) {
            this.portionName = portionName;
        }

        public int getVillageId() {
            return villageId;
        }

        public void setVillageId(int villageId) {
            this.villageId = villageId;
        }

        public String getVillageName() {
            return villageName;
        }

        public void setVillageName(String villageName) {
            this.villageName = villageName;
        }

        @Override
        public String getPickerViewText() {
            return cellName;
        }
    }
}
