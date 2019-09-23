package com.ghs.ghspm.bean;

import java.util.List;

/**
 * Author:wang_sir
 * Time:2019/4/11 19:34
 * Description:This is PropertiesBean 企业列表的实体类
 */
public class PropertiesBean {


    /**
     * code : 1000
     * message : 成功
     * data : [{"id":30,"propertyName":"长春大禹物业","principal":null,"contactWay":null,"address":null,"propertyMobile":null,"remark":null,"updateTime":null,"subsystem":null,"provinceId":null,"cityId":null,"provinceName":null,"cityName":null,"createTime":null,"deleteFlag":null,"headImage":null},{"id":38,"propertyName":"wjf","principal":null,"contactWay":null,"address":null,"propertyMobile":null,"remark":null,"updateTime":null,"subsystem":null,"provinceId":null,"cityId":null,"provinceName":null,"cityName":null,"createTime":null,"deleteFlag":null,"headImage":null}]
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
         * id : 30
         * propertyName : 长春大禹物业
         * principal : null
         * contactWay : null
         * address : null
         * propertyMobile : null
         * remark : null
         * updateTime : null
         * subsystem : null
         * provinceId : null
         * cityId : null
         * provinceName : null
         * cityName : null
         * createTime : null
         * deleteFlag : null
         * headImage : null
         */

        private int id;
        private String propertyName;
        private String principal;
        private String contactWay;
        private String address;
        private String propertyMobile;
        private String remark;
        private String updateTime;
        private String subsystem;
        private int provinceId;
        private int cityId;
        private String provinceName;
        private String cityName;
        private String createTime;
        private int deleteFlag;
        private String headImage;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPropertyName() {
            return propertyName == null ? "" : propertyName;
        }

        public void setPropertyName(String propertyName) {
            this.propertyName = propertyName;
        }

        public String getPrincipal() {
            return principal == null ? "" : principal;
        }

        public void setPrincipal(String principal) {
            this.principal = principal;
        }

        public String getContactWay() {
            return contactWay == null ? "" : contactWay;
        }

        public void setContactWay(String contactWay) {
            this.contactWay = contactWay;
        }

        public String getAddress() {
            return address == null ? "" : address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getPropertyMobile() {
            return propertyMobile == null ? "" : propertyMobile;
        }

        public void setPropertyMobile(String propertyMobile) {
            this.propertyMobile = propertyMobile;
        }

        public String getRemark() {
            return remark == null ? "" : remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getUpdateTime() {
            return updateTime == null ? "" : updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public String getSubsystem() {
            return subsystem == null ? "" : subsystem;
        }

        public void setSubsystem(String subsystem) {
            this.subsystem = subsystem;
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

        public String getCreateTime() {
            return createTime == null ? "" : createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public int getDeleteFlag() {
            return deleteFlag;
        }

        public void setDeleteFlag(int deleteFlag) {
            this.deleteFlag = deleteFlag;
        }

        public String getHeadImage() {
            return headImage == null ? "" : headImage;
        }

        public void setHeadImage(String headImage) {
            this.headImage = headImage;
        }
    }
}
