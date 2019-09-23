package com.ghs.ghspm.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Author:wang_sir
 * Time:2019/7/10 16:03
 * Description:This is WorkOrderBean  工单
 */
public class WorkOrderBean {

    /**
     * code : 1000
     * message : 成功
     * data : [{"id":217,"source":1,"propertyId":30,"villageId":28,"roomId":null,"serviceClass":null,"serviceType":6,"serviceLabel":null,"sysUserId":38,"ownerName":null,"mobile":null,"ghsUserId":null,"state":1,"content":"任务内容:11;起止时间:空","imageUrl":null,"hopeGotoTime":null,"temporaryTaskId":576,"createTime":"2019-05-16 10:43:41","updateTime":"2019-05-16 10:43:41","startCreateTime":null,"endCreateTime":null,"sysUserName":"王斌(2)","ghsUserName":null,"repairUserId":null,"repairUserName":"王斌(2)","repairUserMobile":null,"checkUserId":null,"checkUserName":null,"ccUserId":null,"ccUserName":null,"villageMsg":"测试小区","roomNumber":null,"villageName":"测试小区","portionName":null,"towerNumber":null,"cellName":null,"portionId":null,"towerId":null,"cellId":null,"distributeTime":null,"receiveTime":null,"arriveTime":null,"repairMsg":null,"useMaterial":null,"remark":null,"finishJobTime":null,"repairHour":null,"repairMinute":null,"engineeringManager":null,"evaluateArrive":null,"evaluateAttitude":null,"evaluateQuality":null,"paid":null,"repairMoney":null,"suggest":null},{"id":216,"source":1,"propertyId":30,"villageId":28,"roomId":null,"serviceClass":null,"serviceType":6,"serviceLabel":null,"sysUserId":38,"ownerName":null,"mobile":null,"ghsUserId":null,"state":1,"content":"任务内容:99;起止时间:空","imageUrl":null,"hopeGotoTime":null,"temporaryTaskId":575,"createTime":"2019-05-15 15:56:21","updateTime":"2019-05-15 15:56:21","startCreateTime":null,"endCreateTime":null,"sysUserName":"王斌(2)","ghsUserName":null,"repairUserId":null,"repairUserName":"王斌(2)","repairUserMobile":null,"checkUserId":null,"checkUserName":null,"ccUserId":null,"ccUserName":null,"villageMsg":"测试小区","roomNumber":null,"villageName":"测试小区","portionName":null,"towerNumber":null,"cellName":null,"portionId":null,"towerId":null,"cellId":null,"distributeTime":null,"receiveTime":null,"arriveTime":null,"repairMsg":null,"useMaterial":null,"remark":null,"finishJobTime":null,"repairHour":null,"repairMinute":null,"engineeringManager":null,"evaluateArrive":null,"evaluateAttitude":null,"evaluateQuality":null,"paid":null,"repairMoney":null,"suggest":null},{"id":215,"source":1,"propertyId":30,"villageId":28,"roomId":null,"serviceClass":null,"serviceType":6,"serviceLabel":null,"sysUserId":38,"ownerName":null,"mobile":null,"ghsUserId":null,"state":1,"content":"任务内容:99;起止时间:空","imageUrl":null,"hopeGotoTime":null,"temporaryTaskId":574,"createTime":"2019-05-15 15:55:28","updateTime":"2019-05-15 15:55:28","startCreateTime":null,"endCreateTime":null,"sysUserName":"王斌(2)","ghsUserName":null,"repairUserId":null,"repairUserName":"王斌(2)","repairUserMobile":null,"checkUserId":null,"checkUserName":null,"ccUserId":null,"ccUserName":null,"villageMsg":"测试小区","roomNumber":null,"villageName":"测试小区","portionName":null,"towerNumber":null,"cellName":null,"portionId":null,"towerId":null,"cellId":null,"distributeTime":null,"receiveTime":null,"arriveTime":null,"repairMsg":null,"useMaterial":null,"remark":null,"finishJobTime":null,"repairHour":null,"repairMinute":null,"engineeringManager":null,"evaluateArrive":null,"evaluateAttitude":null,"evaluateQuality":null,"paid":null,"repairMoney":null,"suggest":null},{"id":214,"source":1,"propertyId":30,"villageId":28,"roomId":null,"serviceClass":null,"serviceType":6,"serviceLabel":null,"sysUserId":38,"ownerName":null,"mobile":null,"ghsUserId":null,"state":1,"content":"任务内容:456;起止时间:空","imageUrl":null,"hopeGotoTime":null,"temporaryTaskId":573,"createTime":"2019-05-15 15:54:55","updateTime":"2019-05-15 15:54:55","startCreateTime":null,"endCreateTime":null,"sysUserName":"王斌(2)","ghsUserName":null,"repairUserId":null,"repairUserName":"王斌(2)","repairUserMobile":null,"checkUserId":null,"checkUserName":null,"ccUserId":null,"ccUserName":null,"villageMsg":"测试小区","roomNumber":null,"villageName":"测试小区","portionName":null,"towerNumber":null,"cellName":null,"portionId":null,"towerId":null,"cellId":null,"distributeTime":null,"receiveTime":null,"arriveTime":null,"repairMsg":null,"useMaterial":null,"remark":null,"finishJobTime":null,"repairHour":null,"repairMinute":null,"engineeringManager":null,"evaluateArrive":null,"evaluateAttitude":null,"evaluateQuality":null,"paid":null,"repairMoney":null,"suggest":null},{"id":213,"source":1,"propertyId":30,"villageId":28,"roomId":null,"serviceClass":null,"serviceType":6,"serviceLabel":null,"sysUserId":38,"ownerName":null,"mobile":null,"ghsUserId":null,"state":1,"content":"任务内容:556;起止时间:空","imageUrl":null,"hopeGotoTime":null,"temporaryTaskId":572,"createTime":"2019-05-15 15:54:25","updateTime":"2019-05-15 15:54:25","startCreateTime":null,"endCreateTime":null,"sysUserName":"王斌(2)","ghsUserName":null,"repairUserId":null,"repairUserName":"王斌(2)","repairUserMobile":null,"checkUserId":null,"checkUserName":null,"ccUserId":null,"ccUserName":null,"villageMsg":"测试小区","roomNumber":null,"villageName":"测试小区","portionName":null,"towerNumber":null,"cellName":null,"portionId":null,"towerId":null,"cellId":null,"distributeTime":null,"receiveTime":null,"arriveTime":null,"repairMsg":null,"useMaterial":null,"remark":null,"finishJobTime":null,"repairHour":null,"repairMinute":null,"engineeringManager":null,"evaluateArrive":null,"evaluateAttitude":null,"evaluateQuality":null,"paid":null,"repairMoney":null,"suggest":null}]
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

    public static class DataBean implements Parcelable {
        /**
         * id : 217
         * source : 1
         * propertyId : 30
         * villageId : 28
         * roomId : null
         * serviceClass : null
         * serviceType : 6
         * serviceLabel : null
         * sysUserId : 38
         * ownerName : null
         * mobile : null
         * ghsUserId : null
         * state : 1
         * content : 任务内容:11;起止时间:空
         * imageUrl : null
         * hopeGotoTime : null
         * temporaryTaskId : 576
         * createTime : 2019-05-16 10:43:41
         * updateTime : 2019-05-16 10:43:41
         * startCreateTime : null
         * endCreateTime : null
         * sysUserName : 王斌(2)
         * ghsUserName : null
         * repairUserId : null
         * repairUserName : 王斌(2)
         * repairUserMobile : null
         * checkUserId : null
         * checkUserName : null
         * ccUserId : null
         * ccUserName : null
         * villageMsg : 测试小区
         * roomNumber : null
         * villageName : 测试小区
         * portionName : null
         * towerNumber : null
         * cellName : null
         * portionId : null
         * towerId : null
         * cellId : null
         * distributeTime : null
         * receiveTime : null
         * arriveTime : null
         * repairMsg : null
         * useMaterial : null
         * remark : null
         * finishJobTime : null
         * repairHour : null
         * repairMinute : null
         * engineeringManager : null
         * evaluateArrive : null
         * evaluateAttitude : null
         * evaluateQuality : null
         * paid : null
         * repairMoney : null
         * suggest : null
         */

        private int id;
        private int source;
        private int propertyId;
        private int villageId;
        private int roomId;
        private int serviceClass;
        private int serviceType;
        private String serviceLabel;
        private int sysUserId;
        private String ownerName;
        private String mobile;
        private int ghsUserId;
        private int state;
        private String content;
        private String imageUrl;
        private String hopeGotoTime;
        private int temporaryTaskId;
        private String createTime;
        private String updateTime;
        private String startCreateTime;
        private String endCreateTime;
        private String sysUserName;
        private String ghsUserName;
        private String repairUserId;
        private String repairUserName;
        private String repairUserMobile;
        private int checkUserId;
        private String checkUserName;
        private String ccUserId;
        private String ccUserName;
        private String villageMsg;
        private String roomNumber;
        private String villageName;
        private String portionName;
        private String towerNumber;
        private String cellName;
        private int portionId;
        private int towerId;
        private int cellId;
        private String distributeTime;
        private String receiveTime;
        private String arriveTime;
        private String repairMsg;
        private String useMaterial;
        private String remark;
        private String finishJobTime;
        private int repairHour;
        private int repairMinute;
        private String engineeringManager;
        private int evaluateArrive;
        private int evaluateAttitude;
        private int evaluateQuality;
        private int paid;
        private double repairMoney;
        private String suggest;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getSource() {
            return source;
        }

        public void setSource(int source) {
            this.source = source;
        }

        public int getPropertyId() {
            return propertyId;
        }

        public void setPropertyId(int propertyId) {
            this.propertyId = propertyId;
        }

        public int getVillageId() {
            return villageId;
        }

        public void setVillageId(int villageId) {
            this.villageId = villageId;
        }

        public int getRoomId() {
            return roomId;
        }

        public void setRoomId(int roomId) {
            this.roomId = roomId;
        }

        public int getServiceClass() {
            return serviceClass;
        }

        public void setServiceClass(int serviceClass) {
            this.serviceClass = serviceClass;
        }

        public int getServiceType() {
            return serviceType;
        }

        public void setServiceType(int serviceType) {
            this.serviceType = serviceType;
        }

        public String getServiceLabel() {
            return serviceLabel == null ? "" : serviceLabel;
        }

        public void setServiceLabel(String serviceLabel) {
            this.serviceLabel = serviceLabel;
        }

        public int getSysUserId() {
            return sysUserId;
        }

        public void setSysUserId(int sysUserId) {
            this.sysUserId = sysUserId;
        }

        public String getOwnerName() {
            return ownerName == null ? "" : ownerName;
        }

        public void setOwnerName(String ownerName) {
            this.ownerName = ownerName;
        }

        public String getMobile() {
            return mobile == null ? "" : mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public int getGhsUserId() {
            return ghsUserId;
        }

        public void setGhsUserId(int ghsUserId) {
            this.ghsUserId = ghsUserId;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public String getContent() {
            return content == null ? "" : content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getImageUrl() {
            return imageUrl == null ? "" : imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getHopeGotoTime() {
            return hopeGotoTime == null ? "" : hopeGotoTime;
        }

        public void setHopeGotoTime(String hopeGotoTime) {
            this.hopeGotoTime = hopeGotoTime;
        }

        public int getTemporaryTaskId() {
            return temporaryTaskId;
        }

        public void setTemporaryTaskId(int temporaryTaskId) {
            this.temporaryTaskId = temporaryTaskId;
        }

        public String getCreateTime() {
            return createTime == null ? "" : createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getUpdateTime() {
            return updateTime == null ? "" : updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public String getStartCreateTime() {
            return startCreateTime == null ? "" : startCreateTime;
        }

        public void setStartCreateTime(String startCreateTime) {
            this.startCreateTime = startCreateTime;
        }

        public String getEndCreateTime() {
            return endCreateTime == null ? "" : endCreateTime;
        }

        public void setEndCreateTime(String endCreateTime) {
            this.endCreateTime = endCreateTime;
        }

        public String getSysUserName() {
            return sysUserName == null ? "" : sysUserName;
        }

        public void setSysUserName(String sysUserName) {
            this.sysUserName = sysUserName;
        }

        public String getGhsUserName() {
            return ghsUserName == null ? "" : ghsUserName;
        }

        public void setGhsUserName(String ghsUserName) {
            this.ghsUserName = ghsUserName;
        }

        public String getRepairUserId() {
            return repairUserId == null ? "" : repairUserId;
        }

        public void setRepairUserId(String repairUserId) {
            this.repairUserId = repairUserId;
        }

        public String getRepairUserName() {
            return repairUserName == null ? "" : repairUserName;
        }

        public void setRepairUserName(String repairUserName) {
            this.repairUserName = repairUserName;
        }

        public String getRepairUserMobile() {
            return repairUserMobile == null ? "" : repairUserMobile;
        }

        public void setRepairUserMobile(String repairUserMobile) {
            this.repairUserMobile = repairUserMobile;
        }

        public int getCheckUserId() {
            return checkUserId;
        }

        public void setCheckUserId(int checkUserId) {
            this.checkUserId = checkUserId;
        }

        public String getCheckUserName() {
            return checkUserName == null ? "" : checkUserName;
        }

        public void setCheckUserName(String checkUserName) {
            this.checkUserName = checkUserName;
        }

        public String getCcUserId() {
            return ccUserId == null ? "" : ccUserId;
        }

        public void setCcUserId(String ccUserId) {
            this.ccUserId = ccUserId;
        }

        public String getCcUserName() {
            return ccUserName == null ? "" : ccUserName;
        }

        public void setCcUserName(String ccUserName) {
            this.ccUserName = ccUserName;
        }

        public String getVillageMsg() {
            return villageMsg == null ? "" : villageMsg;
        }

        public void setVillageMsg(String villageMsg) {
            this.villageMsg = villageMsg;
        }

        public String getRoomNumber() {
            return roomNumber == null ? "" : roomNumber;
        }

        public void setRoomNumber(String roomNumber) {
            this.roomNumber = roomNumber;
        }

        public String getVillageName() {
            return villageName == null ? "" : villageName;
        }

        public void setVillageName(String villageName) {
            this.villageName = villageName;
        }

        public String getPortionName() {
            return portionName == null ? "" : portionName;
        }

        public void setPortionName(String portionName) {
            this.portionName = portionName;
        }

        public String getTowerNumber() {
            return towerNumber == null ? "" : towerNumber;
        }

        public void setTowerNumber(String towerNumber) {
            this.towerNumber = towerNumber;
        }

        public String getCellName() {
            return cellName == null ? "" : cellName;
        }

        public void setCellName(String cellName) {
            this.cellName = cellName;
        }

        public int getPortionId() {
            return portionId;
        }

        public void setPortionId(int portionId) {
            this.portionId = portionId;
        }

        public int getTowerId() {
            return towerId;
        }

        public void setTowerId(int towerId) {
            this.towerId = towerId;
        }

        public int getCellId() {
            return cellId;
        }

        public void setCellId(int cellId) {
            this.cellId = cellId;
        }

        public String getDistributeTime() {
            return distributeTime == null ? "" : distributeTime;
        }

        public void setDistributeTime(String distributeTime) {
            this.distributeTime = distributeTime;
        }

        public String getReceiveTime() {
            return receiveTime == null ? "" : receiveTime;
        }

        public void setReceiveTime(String receiveTime) {
            this.receiveTime = receiveTime;
        }

        public String getArriveTime() {
            return arriveTime == null ? "" : arriveTime;
        }

        public void setArriveTime(String arriveTime) {
            this.arriveTime = arriveTime;
        }

        public String getRepairMsg() {
            return repairMsg == null ? "" : repairMsg;
        }

        public void setRepairMsg(String repairMsg) {
            this.repairMsg = repairMsg;
        }

        public String getUseMaterial() {
            return useMaterial == null ? "" : useMaterial;
        }

        public void setUseMaterial(String useMaterial) {
            this.useMaterial = useMaterial;
        }

        public String getRemark() {
            return remark == null ? "" : remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getFinishJobTime() {
            return finishJobTime == null ? "" : finishJobTime;
        }

        public void setFinishJobTime(String finishJobTime) {
            this.finishJobTime = finishJobTime;
        }

        public int getRepairHour() {
            return repairHour;
        }

        public void setRepairHour(int repairHour) {
            this.repairHour = repairHour;
        }

        public int getRepairMinute() {
            return repairMinute;
        }

        public void setRepairMinute(int repairMinute) {
            this.repairMinute = repairMinute;
        }

        public String getEngineeringManager() {
            return engineeringManager == null ? "" : engineeringManager;
        }

        public void setEngineeringManager(String engineeringManager) {
            this.engineeringManager = engineeringManager;
        }

        public int getEvaluateArrive() {
            return evaluateArrive;
        }

        public void setEvaluateArrive(int evaluateArrive) {
            this.evaluateArrive = evaluateArrive;
        }

        public int getEvaluateAttitude() {
            return evaluateAttitude;
        }

        public void setEvaluateAttitude(int evaluateAttitude) {
            this.evaluateAttitude = evaluateAttitude;
        }

        public int getEvaluateQuality() {
            return evaluateQuality;
        }

        public void setEvaluateQuality(int evaluateQuality) {
            this.evaluateQuality = evaluateQuality;
        }

        public int getPaid() {
            return paid;
        }

        public void setPaid(int paid) {
            this.paid = paid;
        }

        public double getRepairMoney() {
            return repairMoney;
        }

        public void setRepairMoney(double repairMoney) {
            this.repairMoney = repairMoney;
        }

        public String getSuggest() {
            return suggest == null ? "" : suggest;
        }

        public void setSuggest(String suggest) {
            this.suggest = suggest;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.id);
            dest.writeInt(this.source);
            dest.writeInt(this.propertyId);
            dest.writeInt(this.villageId);
            dest.writeInt(this.roomId);
            dest.writeInt(this.serviceClass);
            dest.writeInt(this.serviceType);
            dest.writeString(this.serviceLabel);
            dest.writeInt(this.sysUserId);
            dest.writeString(this.ownerName);
            dest.writeString(this.mobile);
            dest.writeInt(this.ghsUserId);
            dest.writeInt(this.state);
            dest.writeString(this.content);
            dest.writeString(this.imageUrl);
            dest.writeString(this.hopeGotoTime);
            dest.writeInt(this.temporaryTaskId);
            dest.writeString(this.createTime);
            dest.writeString(this.updateTime);
            dest.writeString(this.startCreateTime);
            dest.writeString(this.endCreateTime);
            dest.writeString(this.sysUserName);
            dest.writeString(this.ghsUserName);
            dest.writeString(this.repairUserId);
            dest.writeString(this.repairUserName);
            dest.writeString(this.repairUserMobile);
            dest.writeInt(this.checkUserId);
            dest.writeString(this.checkUserName);
            dest.writeString(this.ccUserId);
            dest.writeString(this.ccUserName);
            dest.writeString(this.villageMsg);
            dest.writeString(this.roomNumber);
            dest.writeString(this.villageName);
            dest.writeString(this.portionName);
            dest.writeString(this.towerNumber);
            dest.writeString(this.cellName);
            dest.writeInt(this.portionId);
            dest.writeInt(this.towerId);
            dest.writeInt(this.cellId);
            dest.writeString(this.distributeTime);
            dest.writeString(this.receiveTime);
            dest.writeString(this.arriveTime);
            dest.writeString(this.repairMsg);
            dest.writeString(this.useMaterial);
            dest.writeString(this.remark);
            dest.writeString(this.finishJobTime);
            dest.writeInt(this.repairHour);
            dest.writeInt(this.repairMinute);
            dest.writeString(this.engineeringManager);
            dest.writeInt(this.evaluateArrive);
            dest.writeInt(this.evaluateAttitude);
            dest.writeInt(this.evaluateQuality);
            dest.writeInt(this.paid);
            dest.writeDouble(this.repairMoney);
            dest.writeString(this.suggest);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.id = in.readInt();
            this.source = in.readInt();
            this.propertyId = in.readInt();
            this.villageId = in.readInt();
            this.roomId = in.readInt();
            this.serviceClass = in.readInt();
            this.serviceType = in.readInt();
            this.serviceLabel = in.readString();
            this.sysUserId = in.readInt();
            this.ownerName = in.readString();
            this.mobile = in.readString();
            this.ghsUserId = in.readInt();
            this.state = in.readInt();
            this.content = in.readString();
            this.imageUrl = in.readString();
            this.hopeGotoTime = in.readString();
            this.temporaryTaskId = in.readInt();
            this.createTime = in.readString();
            this.updateTime = in.readString();
            this.startCreateTime = in.readString();
            this.endCreateTime = in.readString();
            this.sysUserName = in.readString();
            this.ghsUserName = in.readString();
            this.repairUserId = in.readString();
            this.repairUserName = in.readString();
            this.repairUserMobile = in.readString();
            this.checkUserId = in.readInt();
            this.checkUserName = in.readString();
            this.ccUserId = in.readString();
            this.ccUserName = in.readString();
            this.villageMsg = in.readString();
            this.roomNumber = in.readString();
            this.villageName = in.readString();
            this.portionName = in.readString();
            this.towerNumber = in.readString();
            this.cellName = in.readString();
            this.portionId = in.readInt();
            this.towerId = in.readInt();
            this.cellId = in.readInt();
            this.distributeTime = in.readString();
            this.receiveTime = in.readString();
            this.arriveTime = in.readString();
            this.repairMsg = in.readString();
            this.useMaterial = in.readString();
            this.remark = in.readString();
            this.finishJobTime = in.readString();
            this.repairHour = in.readInt();
            this.repairMinute = in.readInt();
            this.engineeringManager = in.readString();
            this.evaluateArrive = in.readInt();
            this.evaluateAttitude = in.readInt();
            this.evaluateQuality = in.readInt();
            this.paid = in.readInt();
            this.repairMoney = in.readDouble();
            this.suggest = in.readString();
        }

        public static final Parcelable.Creator<DataBean> CREATOR = new Parcelable.Creator<DataBean>() {
            @Override
            public DataBean createFromParcel(Parcel source) {
                return new DataBean(source);
            }

            @Override
            public DataBean[] newArray(int size) {
                return new DataBean[size];
            }
        };
    }
}
