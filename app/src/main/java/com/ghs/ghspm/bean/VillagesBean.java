package com.ghs.ghspm.bean;

import java.util.List;

/**
 * Author:wang_sir
 * Time:2019/4/12 11:55
 * Description:This is VillagesBean  小区实体类
 */
public class VillagesBean {

    /**
     * code : 1000
     * message : 成功
     * data : [{"id":28,"propertyId":null,"villageName":"测试小区","communityId":null,"provinceId":null,"cityId":null,"districtId":null,"address":null,"commId":null,"updateTime":null,"propertyName":null,"communityName":null,"provinceName":null,"cityName":null,"districtName":null,"deleteFlag":null,"createTime":null},{"id":31,"propertyId":null,"villageName":"wjf小区","communityId":null,"provinceId":null,"cityId":null,"districtId":null,"address":null,"commId":null,"updateTime":null,"propertyName":null,"communityName":null,"provinceName":null,"cityName":null,"districtName":null,"deleteFlag":null,"createTime":null},{"id":27,"propertyId":null,"villageName":"褐石公园小区（测试）","communityId":null,"provinceId":null,"cityId":null,"districtId":null,"address":null,"commId":null,"updateTime":null,"propertyName":null,"communityName":null,"provinceName":null,"cityName":null,"districtName":null,"deleteFlag":null,"createTime":null}]
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
         * id : 28
         * propertyId : null
         * villageName : 测试小区
         * communityId : null
         * provinceId : null
         * cityId : null
         * districtId : null
         * address : null
         * commId : null
         * updateTime : null
         * propertyName : null
         * communityName : null
         * provinceName : null
         * cityName : null
         * districtName : null
         * deleteFlag : null
         * createTime : null
         */

        private int id;
        private int propertyId;
        private String villageName;
        private int communityId;
        private int provinceId;
        private int cityId;
        private int districtId;
        private String address;
        private String commId;
        private String updateTime;
        private String propertyName;
        private String communityName;
        private String provinceName;
        private String cityName;
        private String districtName;
        private int deleteFlag;
        private String createTime;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getPropertyId() {
            return propertyId;
        }

        public void setPropertyId(int propertyId) {
            this.propertyId = propertyId;
        }

        public String getVillageName() {
            return villageName == null ? "" : villageName;
        }

        public void setVillageName(String villageName) {
            this.villageName = villageName;
        }

        public int getCommunityId() {
            return communityId;
        }

        public void setCommunityId(int communityId) {
            this.communityId = communityId;
        }

        public int getProvinceId() {
            return provinceId;
        }

        public void setProvinceId(int provinceId) {
            this.provinceId = provinceId;
        }

        public int getCityId() {
            return cityId;
        }

        public void setCityId(int cityId) {
            this.cityId = cityId;
        }

        public int getDistrictId() {
            return districtId;
        }

        public void setDistrictId(int districtId) {
            this.districtId = districtId;
        }

        public String getAddress() {
            return address == null ? "" : address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getCommId() {
            return commId == null ? "" : commId;
        }

        public void setCommId(String commId) {
            this.commId = commId;
        }

        public String getUpdateTime() {
            return updateTime == null ? "" : updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public String getPropertyName() {
            return propertyName == null ? "" : propertyName;
        }

        public void setPropertyName(String propertyName) {
            this.propertyName = propertyName;
        }

        public String getCommunityName() {
            return communityName == null ? "" : communityName;
        }

        public void setCommunityName(String communityName) {
            this.communityName = communityName;
        }

        public String getProvinceName() {
            return provinceName == null ? "" : provinceName;
        }

        public void setProvinceName(String provinceName) {
            this.provinceName = provinceName;
        }

        public String getCityName() {
            return cityName == null ? "" : cityName;
        }

        public void setCityName(String cityName) {
            this.cityName = cityName;
        }

        public String getDistrictName() {
            return districtName == null ? "" : districtName;
        }

        public void setDistrictName(String districtName) {
            this.districtName = districtName;
        }

        public int getDeleteFlag() {
            return deleteFlag;
        }

        public void setDeleteFlag(int deleteFlag) {
            this.deleteFlag = deleteFlag;
        }

        public String getCreateTime() {
            return createTime == null ? "" : createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }
    }
}
