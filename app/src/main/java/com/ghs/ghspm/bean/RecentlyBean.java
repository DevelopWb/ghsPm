package com.ghs.ghspm.bean;

public class RecentlyBean {
    private String cellid;
    private String doorName;

    public RecentlyBean(String cellid, String doorName) {
        this.cellid = cellid;
        this.doorName = doorName;
    }

    public RecentlyBean() {
    }

    public String getCellid() {
        return cellid;
    }

    public void setCellid(String cellid) {
        this.cellid = cellid;
    }

    public String getDoorName() {
        return doorName;
    }

    public void setDoorName(String doorName) {
        this.doorName = doorName;
    }
}
