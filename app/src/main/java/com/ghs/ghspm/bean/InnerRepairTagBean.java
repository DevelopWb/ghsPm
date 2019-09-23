package com.ghs.ghspm.bean;

/**
 * Author:wang_sir
 * Time:2018/11/2 14:09
 * Description:This is InnerRepairTagBean
 */
public class InnerRepairTagBean {

    private String name ;
    private boolean selected;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public InnerRepairTagBean(String name, boolean selected) {
        this.name = name;
        this.selected = selected;
    }
}
