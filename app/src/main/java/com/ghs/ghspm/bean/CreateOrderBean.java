package com.ghs.ghspm.bean;

import com.ghs.ghspm.tools.StrUtils;

import java.io.Serializable;

/**
 * created by guohainan
 * created date 2019/7/8
 * application
 * package name com.ghs.ghspm.bean
 */
public class CreateOrderBean implements Serializable {
    private String orderContent;//工单内容
    private String orderImage;//图片
    private String orderType;//工单类型
    //报修服务单
    private String repairsType;//报修类型
    private String repairsLabel;//报修标签
    private String orderName;//联系人姓名
    private String orderMobile;//联系人电话
    private String orderRoom;//房间号
    private String orderRoomId;
    private String orderHopeGotoTime;//期望上门时间
    // 综合服务单
    private String serveType;//服务单类型
    private String followPeople;//跟进人
    private String followPeopleId;
    private String checkPeople;//验收人
    private String checkPeopleId;
    private String copyPeople;//抄送人
    private String copyPeopleId;
    private boolean updateOrderState;//是否编辑
    private String orderId;

    public String getCopyPeopleId() {
        return copyPeopleId;
    }

    public void setCopyPeopleId(String copyPeopleId) {
        this.copyPeopleId = copyPeopleId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public boolean isUpdateOrderState() {
        return updateOrderState;
    }

    public void setUpdateOrderState(boolean updateOrderState) {
        this.updateOrderState = updateOrderState;
    }

    public String getOrderRoomId() {
        return orderRoomId;
    }

    public void setOrderRoomId(String orderRoomId) {
        this.orderRoomId = orderRoomId;
    }

    public String getFollowPeopleId() {
        return followPeopleId;
    }

    public void setFollowPeopleId(String followPeopleId) {
        this.followPeopleId = followPeopleId;
    }

    public String getCheckPeopleId() {
        return checkPeopleId;
    }

    public void setCheckPeopleId(String checkPeopleId) {
        this.checkPeopleId = checkPeopleId;
    }

    public String getOrderHopeGotoTime() {
        return StrUtils.isStringValueOk(orderHopeGotoTime) ? orderHopeGotoTime : "尽快上门";
    }

    public void setOrderHopeGotoTime(String orderHopeGotoTime) {
        this.orderHopeGotoTime = orderHopeGotoTime;
    }

    public String getFollowPeople() {
        return StrUtils.isStringValueOk(followPeople)?followPeople:"请选择";
    }

    public void setFollowPeople(String followPeople) {
        this.followPeople = followPeople;
    }

    public String getCheckPeople() {
        return StrUtils.isStringValueOk(checkPeople)?checkPeople:"请选择";
    }

    public void setCheckPeople(String checkPeople) {
        this.checkPeople = checkPeople;
    }

    public String getCopyPeople() {
        return  StrUtils.isStringValueOk(copyPeople)?copyPeople:"请选择";
    }

    public void setCopyPeople(String copyPeople) {
        this.copyPeople = copyPeople;
    }

    public CreateOrderBean(String orderContent, String orderImage, String orderType, String repairsType, String repairsLabel, String orderName, String orderMobile, String orderRoom, String serveType) {
        this.orderContent = orderContent;
        this.orderImage = orderImage;
        this.orderType = orderType;
        this.repairsType = repairsType;
        this.repairsLabel = repairsLabel;
        this.orderName = orderName;
        this.orderMobile = orderMobile;
        this.orderRoom = orderRoom;
        this.serveType = serveType;
    }

    public CreateOrderBean() {
    }

    public String getOrderContent() {
        return orderContent;
    }

    public void setOrderContent(String orderContent) {
        this.orderContent = orderContent;
    }

    public String getOrderImage() {
        return orderImage;
    }

    public void setOrderImage(String orderImage) {
        this.orderImage = orderImage;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getRepairsType() {
        return repairsType;
    }

    public void setRepairsType(String repairsType) {
        this.repairsType = repairsType;
    }

    public String getRepairsLabel() {
        return repairsLabel;
    }

    public void setRepairsLabel(String repairsLabel) {
        this.repairsLabel = repairsLabel;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getOrderMobile() {
        return orderMobile;
    }

    public void setOrderMobile(String orderMobile) {
        this.orderMobile = orderMobile;
    }

    public String getOrderRoom() {
        return StrUtils.isStringValueOk(orderRoom)? orderRoom: "请选择";
    }

    public void setOrderRoom(String orderRoom) {
        this.orderRoom = orderRoom;
    }

    public String getServeType() {
        return serveType;
    }

    public void setServeType(String serveType) {
        this.serveType = serveType;
    }
}
