package com.ghs.ghspm.bean;

import java.util.List;

/**
 * Author:wang_sir
 * Time:2018/11/1 18:07
 * Description:This is InnerRepairBean
 */
public class InnerRepairBean {
    /**
     * code : 1000
     * message : 成功
     * data : [{"id":1,"propertyId":30,"kind":"路灯","label":"灯泡不亮，灯泡丢失，灯柱损坏，其他问题","orderNum":1,"userId":37,"createTime":"2018-11-01 11:54:54","updateTime":"2018-11-01 14:10:25","userName":"陶龙洋"},{"id":2,"propertyId":30,"kind":"井盖","label":"其他问题","orderNum":2,"userId":37,"createTime":"2018-11-01 12:02:41","updateTime":"2018-11-01 14:18:37","userName":"陶龙洋"},{"id":3,"propertyId":30,"kind":"路面","label":"其他问题","orderNum":3,"userId":37,"createTime":"2018-11-01 12:02:54","updateTime":"2018-11-01 14:18:54","userName":"陶龙洋"},{"id":4,"propertyId":30,"kind":"车位","label":"其他问题","orderNum":4,"userId":37,"createTime":"2018-11-01 12:02:58","updateTime":"2018-11-01 14:19:04","userName":"陶龙洋"},{"id":5,"propertyId":30,"kind":"植被","label":"其他问题","orderNum":5,"userId":37,"createTime":"2018-11-01 12:03:10","updateTime":"2018-11-01 14:19:12","userName":"陶龙洋"},{"id":6,"propertyId":30,"kind":"水管","label":"其他问题","orderNum":7,"userId":37,"createTime":"2018-11-01 12:03:14","updateTime":"2018-11-01 14:19:19","userName":"陶龙洋"},{"id":7,"propertyId":30,"kind":"宣传牌","label":"其他问题","orderNum":8,"userId":37,"createTime":"2018-11-01 12:03:19","updateTime":"2018-11-01 14:19:31","userName":"陶龙洋"},{"id":9,"propertyId":30,"kind":"门禁","label":"其他问题","orderNum":9,"userId":37,"createTime":"2018-11-01 14:19:43","updateTime":"2018-11-01 14:19:43","userName":"陶龙洋"}]
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
         * id : 1
         * propertyId : 30
         * kind : 路灯
         * label : 灯泡不亮，灯泡丢失，灯柱损坏，其他问题
         * orderNum : 1
         * userId : 37
         * createTime : 2018-11-01 11:54:54
         * updateTime : 2018-11-01 14:10:25
         * userName : 陶龙洋
         */

        private int id;
        private int propertyId;
        private String kind;
        private String label;
        private int orderNum;
        private int userId;
        private String createTime;
        private String updateTime;
        private String userName;
        private boolean  selected;

        public boolean isSelected() {
            return selected;
        }

        public void setSelected(boolean selected) {
            this.selected = selected;
        }

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

        public String getKind() {
            return kind;
        }

        public void setKind(String kind) {
            this.kind = kind;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public int getOrderNum() {
            return orderNum;
        }

        public void setOrderNum(int orderNum) {
            this.orderNum = orderNum;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
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

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }
    }
}
