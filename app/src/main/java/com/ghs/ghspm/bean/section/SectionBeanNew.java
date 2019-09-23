package com.ghs.ghspm.bean.section;

import java.util.ArrayList;
import java.util.List;

/**
 * Author:wang_sir
 * Time:2019/4/16 10:14
 * Description:This is SectionBeanNew
 */
public class SectionBeanNew {
    /**
     * roleId : 52
     * roleName : 新增角色
     * userNum : 4
     */

    private int roleId;
    private String roleName;
    private int parentId;
    private int userNum;
    private boolean selected;
    private List<SectionBeanNew> children;

    public SectionBeanNew(int roleId, String roleName, int parentId, int userNum, boolean selected, List<SectionBeanNew> children) {
        this.roleId = roleId;
        this.roleName = roleName;
        this.parentId = parentId;
        this.userNum = userNum;
        this.selected = selected;
        this.children = children;
    }

    public int getUserNum() {
        return userNum;
    }

    public void setUserNum(int userNum) {
        this.userNum = userNum;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getRoleName() {
        return roleName == null ? "" : roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public List<SectionBeanNew> getChildren() {
        if (children == null) {
            return new ArrayList<>();
        }
        return children;
    }

    public void setChildren(List<SectionBeanNew> children) {
        this.children = children;
    }
}
