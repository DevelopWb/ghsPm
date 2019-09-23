package com.ghs.ghspm.bean;

import com.contrarywind.interfaces.IPickerViewData;

import java.util.List;

/**
 * Author:wang_sir
 * Time:2018/9/28 10:52
 * Description:This is TowerBean
 */
public class TowerBean   {
    /**
     * code : 1000
     * message : 成功
     * data : [{"id":279,"portionId":8,"towerNumber":"1号楼","updateTime":"2018-06-21 17:01:12","portionName":"一期","villageName":"褐石公园小区","villageId":24},{"id":267,"portionId":8,"towerNumber":"2号楼","updateTime":"2018-05-29 14:42:48","portionName":"一期","villageName":"褐石公园小区","villageId":24},{"id":266,"portionId":8,"towerNumber":"3号楼","updateTime":"2018-05-29 14:42:43","portionName":"一期","villageName":"褐石公园小区","villageId":24},{"id":265,"portionId":8,"towerNumber":"4号楼","updateTime":"2018-05-29 14:42:38","portionName":"一期","villageName":"褐石公园小区","villageId":24}]
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


    public static class DataBean implements IPickerViewData {
        /**
         * id : 279
         * portionId : 8
         * towerNumber : 1号楼
         * updateTime : 2018-06-21 17:01:12
         * portionName : 一期
         * villageName : 褐石公园小区
         * villageId : 24
         */

        private int id;
        private int portionId;
        private String towerNumber;
        private String updateTime;
        private String portionName;
        private String villageName;
        private int villageId;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getPortionId() {
            return portionId;
        }

        public void setPortionId(int portionId) {
            this.portionId = portionId;
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

        public String getPortionName() {
            return portionName;
        }

        public void setPortionName(String portionName) {
            this.portionName = portionName;
        }

        public String getVillageName() {
            return villageName;
        }

        public void setVillageName(String villageName) {
            this.villageName = villageName;
        }

        public int getVillageId() {
            return villageId;
        }

        public void setVillageId(int villageId) {
            this.villageId = villageId;
        }

        @Override
        public String getPickerViewText() {
            return portionName+towerNumber;
        }
    }
}
