package com.ghs.ghspm.bean.section;

/**
 * Author:wang_sir
 * Time:2018/10/11 11:30
 * Description:This is 通讯录横向菜单
 */
public class OrganizeMenuBean {

    private int depId;
    private String depName;
    private  String  uniqueTag;

    public OrganizeMenuBean(int depId, String depName, String uniqueTag) {
        this.depId = depId;
        this.depName = depName;
        this.uniqueTag = uniqueTag;
    }

    public String getUniqueTag() {
        return uniqueTag == null ? "" : uniqueTag;
    }

    public void setUniqueTag(String uniqueTag) {
        this.uniqueTag = uniqueTag;
    }

    public int getDepId() {
        return depId;
    }

    public void setDepId(int depId) {
        this.depId = depId;
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }
}
