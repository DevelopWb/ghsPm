package com.ghs.ghspm.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Author:wang_sir
 * Time:2019/7/16 10:11
 * Description:This is WorkOrderDetailBean  订单详情
 */
public class WorkOrderDetailBean {


    /**
     * code : 1000
     * message : 成功
     * data : {"serviceWorkDO":{"id":186,"source":1,"villageId":27,"roomId":95994,"serviceClass":2,"serviceType":5,"serviceLabel":null,"sysUserId":42,"ownerName":"郭","mobile":"13580890533","ghsUserId":null,"state":1,"content":"zhuangfa 位置:一期1号楼20单元1201","imageUrl":null,"hopeGotoTime":null,"createTime":"2019-05-07 14:07:01","updateTime":"2019-05-07 14:07:36","startCreateTime":null,"endCreateTime":null,"sysUserName":"龙洋","ghsUserName":null,"updateUserId":null,"updateUserName":null,"repairUserId":"38","repairUserName":"王斌(2)","repairUserMobile":"17568086930","checkUserId":null,"checkUserName":null,"ccUserId":null,"ccUserName":null,"villageMsg":"一期1号楼20单元1201","roomNumber":"1201","villageName":"褐石公园小区（测试）","portionName":"一期","towerNumber":"1号楼","cellName":"20单元","portionId":null,"towerId":517,"cellId":767,"arriveTime":null,"repairMsg":null,"useMaterial":null,"remark":null,"finishJobTime":null,"repairUseTime":null,"engineeringManager":null,"evaluateArrive":null,"evaluateAttitude":null,"evaluateQuality":null,"paid":null,"repairMoney":null,"suggest":null,"ownerSignUrl":null},"statusRecordList":null,"opertionLogList":[{"id":88,"serviceWorkId":186,"pmUserId":null,"opertionType":1,"content":"郭海楠 发起任务","remark":null,"imageUrl":null,"createTime":"2019-05-07 14:07:00","pmUserName":null,"headImage":null,"headImageBackGroudColor":null}],"roleButtonList":["写进展","完成"]}
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
         * serviceWorkDO : {"id":186,"source":1,"villageId":27,"roomId":95994,"serviceClass":2,"serviceType":5,"serviceLabel":null,"sysUserId":42,"ownerName":"郭","mobile":"13580890533","ghsUserId":null,"state":1,"content":"zhuangfa 位置:一期1号楼20单元1201","imageUrl":null,"hopeGotoTime":null,"createTime":"2019-05-07 14:07:01","updateTime":"2019-05-07 14:07:36","startCreateTime":null,"endCreateTime":null,"sysUserName":"龙洋","ghsUserName":null,"updateUserId":null,"updateUserName":null,"repairUserId":"38","repairUserName":"王斌(2)","repairUserMobile":"17568086930","checkUserId":null,"checkUserName":null,"ccUserId":null,"ccUserName":null,"villageMsg":"一期1号楼20单元1201","roomNumber":"1201","villageName":"褐石公园小区（测试）","portionName":"一期","towerNumber":"1号楼","cellName":"20单元","portionId":null,"towerId":517,"cellId":767,"arriveTime":null,"repairMsg":null,"useMaterial":null,"remark":null,"finishJobTime":null,"repairUseTime":null,"engineeringManager":null,"evaluateArrive":null,"evaluateAttitude":null,"evaluateQuality":null,"paid":null,"repairMoney":null,"suggest":null,"ownerSignUrl":null}
         * statusRecordList : null
         * opertionLogList : [{"id":88,"serviceWorkId":186,"pmUserId":null,"opertionType":1,"content":"郭海楠 发起任务","remark":null,"imageUrl":null,"createTime":"2019-05-07 14:07:00","pmUserName":null,"headImage":null,"headImageBackGroudColor":null}]
         * roleButtonList : ["写进展","完成"]
         */

        private ServiceWorkDOBean serviceWorkDO;
        private StatusRecordListBean statusRecordList;
        private List<OpertionLogListBean> opertionLogList;
        private List<String> roleButtonList;

        public ServiceWorkDOBean getServiceWorkDO() {
            return serviceWorkDO;
        }

        public void setServiceWorkDO(ServiceWorkDOBean serviceWorkDO) {
            this.serviceWorkDO = serviceWorkDO;
        }

        public StatusRecordListBean getStatusRecordList() {
            return statusRecordList;
        }

        public void setStatusRecordList(StatusRecordListBean statusRecordList) {
            this.statusRecordList = statusRecordList;
        }

        public List<OpertionLogListBean> getOpertionLogList() {
            return opertionLogList;
        }

        public void setOpertionLogList(List<OpertionLogListBean> opertionLogList) {
            this.opertionLogList = opertionLogList;
        }

        public List<String> getRoleButtonList() {
            return roleButtonList;
        }

        public void setRoleButtonList(List<String> roleButtonList) {
            this.roleButtonList = roleButtonList;
        }

        public static class ServiceWorkDOBean implements Parcelable {
            /**
             * id : 186
             * source : 1
             * villageId : 27
             * roomId : 95994
             * serviceClass : 2
             * serviceType : 5
             * serviceLabel : null
             * sysUserId : 42
             * ownerName : 郭
             * mobile : 13580890533
             * ghsUserId : null
             * state : 1
             * content : zhuangfa 位置:一期1号楼20单元1201
             * imageUrl : null
             * hopeGotoTime : null
             * createTime : 2019-05-07 14:07:01
             * updateTime : 2019-05-07 14:07:36
             * startCreateTime : null
             * endCreateTime : null
             * sysUserName : 龙洋
             * ghsUserName : null
             * updateUserId : null
             * updateUserName : null
             * repairUserId : 38
             * repairUserName : 王斌(2)
             * repairUserMobile : 17568086930
             * checkUserId : null
             * checkUserName : null
             * ccUserId : null
             * ccUserName : null
             * villageMsg : 一期1号楼20单元1201
             * roomNumber : 1201
             * villageName : 褐石公园小区（测试）
             * portionName : 一期
             * towerNumber : 1号楼
             * cellName : 20单元
             * portionId : null
             * towerId : 517
             * cellId : 767
             * arriveTime : null
             * repairMsg : null
             * useMaterial : null
             * remark : null
             * finishJobTime : null
             * repairUseTime : null
             * engineeringManager : null
             * evaluateArrive : null
             * evaluateAttitude : null
             * evaluateQuality : null
             * paid : null
             * repairMoney : null
             * suggest : null
             * ownerSignUrl : null
             */

            private int id;
            private int source;
            private int villageId;
            private int roomId;
            private int serviceClass;
            private int serviceType;
            private String serviceLabel;
            private int sysUserId;
            private String ownerName;
            private String mobile;
            private String ghsUserId;//光合家用户ID
            private int state;
            private String content;
            private String imageUrl;//图片
            private String hopeGotoTime;//期望上门时间
            private String createTime;
            private String updateTime;
            private String startCreateTime;
            private String endCreateTime;
            private String sysUserName;
            private String ghsUserName;
            private int updateUserId;
            private String updateUserName;
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
            private String arriveTime;
            private String repairMsg;
            private String useMaterial;
            private String remark;
            private String finishJobTime;
            private String repairUseTime;
            private String engineeringManager;
            private int evaluateArrive;
            private int evaluateAttitude;
            private int evaluateQuality;
            private int paid;
            private double repairMoney;
            private String suggest;
            private String ownerSignUrl;

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

            public String getGhsUserId() {
                return ghsUserId == null ? "" : ghsUserId;
            }

            public void setGhsUserId(String ghsUserId) {
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

            public int getUpdateUserId() {
                return updateUserId;
            }

            public void setUpdateUserId(int updateUserId) {
                this.updateUserId = updateUserId;
            }

            public String getUpdateUserName() {
                return updateUserName == null ? "" : updateUserName;
            }

            public void setUpdateUserName(String updateUserName) {
                this.updateUserName = updateUserName;
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

            public String getRepairUseTime() {
                return repairUseTime == null ? "" : repairUseTime;
            }

            public void setRepairUseTime(String repairUseTime) {
                this.repairUseTime = repairUseTime;
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

            public String getOwnerSignUrl() {
                return ownerSignUrl == null ? "" : ownerSignUrl;
            }

            public void setOwnerSignUrl(String ownerSignUrl) {
                this.ownerSignUrl = ownerSignUrl;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeInt(this.id);
                dest.writeInt(this.source);
                dest.writeInt(this.villageId);
                dest.writeInt(this.roomId);
                dest.writeInt(this.serviceClass);
                dest.writeInt(this.serviceType);
                dest.writeString(this.serviceLabel);
                dest.writeInt(this.sysUserId);
                dest.writeString(this.ownerName);
                dest.writeString(this.mobile);
                dest.writeString(this.ghsUserId);
                dest.writeInt(this.state);
                dest.writeString(this.content);
                dest.writeString(this.imageUrl);
                dest.writeString(this.hopeGotoTime);
                dest.writeString(this.createTime);
                dest.writeString(this.updateTime);
                dest.writeString(this.startCreateTime);
                dest.writeString(this.endCreateTime);
                dest.writeString(this.sysUserName);
                dest.writeString(this.ghsUserName);
                dest.writeInt(this.updateUserId);
                dest.writeString(this.updateUserName);
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
                dest.writeString(this.arriveTime);
                dest.writeString(this.repairMsg);
                dest.writeString(this.useMaterial);
                dest.writeString(this.remark);
                dest.writeString(this.finishJobTime);
                dest.writeString(this.repairUseTime);
                dest.writeString(this.engineeringManager);
                dest.writeInt(this.evaluateArrive);
                dest.writeInt(this.evaluateAttitude);
                dest.writeInt(this.evaluateQuality);
                dest.writeInt(this.paid);
                dest.writeDouble(this.repairMoney);
                dest.writeString(this.suggest);
                dest.writeString(this.ownerSignUrl);
            }

            public ServiceWorkDOBean() {
            }

            protected ServiceWorkDOBean(Parcel in) {
                this.id = in.readInt();
                this.source = in.readInt();
                this.villageId = in.readInt();
                this.roomId = in.readInt();
                this.serviceClass = in.readInt();
                this.serviceType = in.readInt();
                this.serviceLabel = in.readString();
                this.sysUserId = in.readInt();
                this.ownerName = in.readString();
                this.mobile = in.readString();
                this.ghsUserId = in.readString();
                this.state = in.readInt();
                this.content = in.readString();
                this.imageUrl = in.readString();
                this.hopeGotoTime = in.readString();
                this.createTime = in.readString();
                this.updateTime = in.readString();
                this.startCreateTime = in.readString();
                this.endCreateTime = in.readString();
                this.sysUserName = in.readString();
                this.ghsUserName = in.readString();
                this.updateUserId = in.readInt();
                this.updateUserName = in.readString();
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
                this.arriveTime = in.readString();
                this.repairMsg = in.readString();
                this.useMaterial = in.readString();
                this.remark = in.readString();
                this.finishJobTime = in.readString();
                this.repairUseTime = in.readString();
                this.engineeringManager = in.readString();
                this.evaluateArrive = in.readInt();
                this.evaluateAttitude = in.readInt();
                this.evaluateQuality = in.readInt();
                this.paid = in.readInt();
                this.repairMoney = in.readDouble();
                this.suggest = in.readString();
                this.ownerSignUrl = in.readString();
            }

            public static final Parcelable.Creator<ServiceWorkDOBean> CREATOR = new Parcelable.Creator<ServiceWorkDOBean>() {
                @Override
                public ServiceWorkDOBean createFromParcel(Parcel source) {
                    return new ServiceWorkDOBean(source);
                }

                @Override
                public ServiceWorkDOBean[] newArray(int size) {
                    return new ServiceWorkDOBean[size];
                }
            };
        }

        public static class OpertionLogListBean {
            /**
             * id : 88
             * serviceWorkId : 186
             * pmUserId : null
             * opertionType : 1
             * content : 郭海楠 发起任务
             * remark : null
             * imageUrl : null
             * createTime : 2019-05-07 14:07:00
             * pmUserName : null
             * headImage : null
             * headImageBackGroudColor : null
             */

            private int id;
            private int serviceWorkId;
            private int pmUserId;
            private int opertionType;
            private String content;
            private String remark;
            private String imageUrl;
            private String createTime;
            private String   pmUserName;
            private String headImage;
            private String headImageBackGroudColor;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getServiceWorkId() {
                return serviceWorkId;
            }

            public void setServiceWorkId(int serviceWorkId) {
                this.serviceWorkId = serviceWorkId;
            }

            public int getPmUserId() {
                return pmUserId;
            }

            public void setPmUserId(int pmUserId) {
                this.pmUserId = pmUserId;
            }

            public int getOpertionType() {
                return opertionType;
            }

            public void setOpertionType(int opertionType) {
                this.opertionType = opertionType;
            }

            public String getContent() {
                return content == null ? "" : content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getRemark() {
                return remark == null ? "" : remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public String getImageUrl() {
                return imageUrl == null ? "" : imageUrl;
            }

            public void setImageUrl(String imageUrl) {
                this.imageUrl = imageUrl;
            }

            public String getCreateTime() {
                return createTime == null ? "" : createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getPmUserName() {
                return pmUserName == null ? "" : pmUserName;
            }

            public void setPmUserName(String pmUserName) {
                this.pmUserName = pmUserName;
            }

            public String getHeadImage() {
                return headImage == null ? "" : headImage;
            }

            public void setHeadImage(String headImage) {
                this.headImage = headImage;
            }

            public String getHeadImageBackGroudColor() {
                return headImageBackGroudColor == null ? "" : headImageBackGroudColor;
            }

            public void setHeadImageBackGroudColor(String headImageBackGroudColor) {
                this.headImageBackGroudColor = headImageBackGroudColor;
            }
        }
        public static class StatusRecordListBean {

            private int id;
            private int serviceWorkId;
            private String content;
            private String createTime;
            private String imageUrl;
            private String remark;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getServiceWorkId() {
                return serviceWorkId;
            }

            public void setServiceWorkId(int serviceWorkId) {
                this.serviceWorkId = serviceWorkId;
            }

            public String getContent() {
                return content == null ? "" : content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getCreateTime() {
                return createTime == null ? "" : createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getImageUrl() {
                return imageUrl == null ? "" : imageUrl;
            }

            public void setImageUrl(String imageUrl) {
                this.imageUrl = imageUrl;
            }

            public String getRemark() {
                return remark == null ? "" : remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }
        }
    }
}
