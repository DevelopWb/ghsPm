package com.ghs.ghspm.bean;

/**
 * Author:wang_sir
 * Time:2018/9/27 17:32
 * Description:This is LoginBean
 */
public class LoginBean {

    /**
     * code : 1000
     * message : 成功
     * data : {"id":null,"userId":31,"deptId":null,"propertyId":null,"userType":null,"email":null,"position":null,"status":null,"createUserId":null,"createTime":null,"updateUserId":null,"updateTime":null,"name":"吴敬丰","password":null,"mobile":"18640271776","headImage":"defaultHeadImage.jpg","deptName":null,"propertyName":null,"villageId":null,"villageName":null,"token":"eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOiIzMSIsInVzZXJOYW1lIjoi5ZC05pWs5LiwIiwicHJvcGVydHlJZCI6IiIsImlhdCI6MTU1NDk3MDM5MSwiZXhwIjoxNTU3NTYyMzkxfQ.k9kaVlsQJFuIPguopYgaMA03LB4a5ZDl_fjj3I4xcSw","propertyNum":null}
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
         * id : null
         * userId : 31
         * deptId : null
         * propertyId : null
         * userType : null
         * email : null
         * position : null
         * status : null
         * createUserId : null
         * createTime : null
         * updateUserId : null
         * updateTime : null
         * name : 吴敬丰
         * password : null
         * mobile : 18640271776
         * headImage : defaultHeadImage.jpg
         * deptName : null
         * propertyName : null
         * villageId : null
         * villageName : null
         * token : eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOiIzMSIsInVzZXJOYW1lIjoi5ZC05pWs5LiwIiwicHJvcGVydHlJZCI6IiIsImlhdCI6MTU1NDk3MDM5MSwiZXhwIjoxNTU3NTYyMzkxfQ.k9kaVlsQJFuIPguopYgaMA03LB4a5ZDl_fjj3I4xcSw
         * propertyNum : null
         */

        private int id;
        private int userId;
        private int deptId;
        private int propertyId;
        private int userType;
        private String email;
        private String position;
        private int status;
        private int createUserId;
        private String createTime;
        private int updateUserId;
        private String updateTime;
        private String name;
        private String password;
        private String mobile;
        private String headImage;
        private String deptName;
        private String headImageBackGroudColor;
        private String propertyName;
        private int villageId;
        private String villageName;
        private String token;
        private int propertyNum;

        public String getHeadImageBackGroudColor() {
            return headImageBackGroudColor == null ? "" : headImageBackGroudColor;
        }

        public void setHeadImageBackGroudColor(String headImageBackGroudColor) {
            this.headImageBackGroudColor = headImageBackGroudColor;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getDeptId() {
            return deptId;
        }

        public void setDeptId(int deptId) {
            this.deptId = deptId;
        }

        public int getPropertyId() {
            return propertyId;
        }

        public void setPropertyId(int propertyId) {
            this.propertyId = propertyId;
        }

        public int getUserType() {
            return userType;
        }

        public void setUserType(int userType) {
            this.userType = userType;
        }

        public String getEmail() {
            return email == null ? "" : email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPosition() {
            return position == null ? "" : position;
        }

        public void setPosition(String position) {
            this.position = position;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getCreateUserId() {
            return createUserId;
        }

        public void setCreateUserId(int createUserId) {
            this.createUserId = createUserId;
        }

        public String getCreateTime() {
            return createTime == null ? "" : createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public int getUpdateUserId() {
            return updateUserId;
        }

        public void setUpdateUserId(int updateUserId) {
            this.updateUserId = updateUserId;
        }

        public String getUpdateTime() {
            return updateTime == null ? "" : updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public String getName() {
            return name == null ? "" : name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPassword() {
            return password == null ? "" : password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getMobile() {
            return mobile == null ? "" : mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getHeadImage() {
            return headImage == null ? "" : headImage;
        }

        public void setHeadImage(String headImage) {
            this.headImage = headImage;
        }

        public String getDeptName() {
            return deptName == null ? "" : deptName;
        }

        public void setDeptName(String deptName) {
            this.deptName = deptName;
        }

        public String getPropertyName() {
            return propertyName == null ? "" : propertyName;
        }

        public void setPropertyName(String propertyName) {
            this.propertyName = propertyName;
        }

        public int getVillageId() {
            return villageId;
        }

        public void setVillageId(int villageId) {
            this.villageId = villageId;
        }

        public String getVillageName() {
            return villageName == null ? "" : villageName;
        }

        public void setVillageName(String villageName) {
            this.villageName = villageName;
        }

        public String getToken() {
            return token == null ? "" : token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public int getPropertyNum() {
            return propertyNum;
        }

        public void setPropertyNum(int propertyNum) {
            this.propertyNum = propertyNum;
        }
    }
}
