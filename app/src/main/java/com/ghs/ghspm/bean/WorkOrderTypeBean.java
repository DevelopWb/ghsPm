package com.ghs.ghspm.bean;

/**
 * Author:wang_sir
 * Time:2019/7/5 16:49
 * Description:This is WorkOrderTypeBean
 */
public class WorkOrderTypeBean {

    private String content;
    private boolean isSelected;
    private int unReadAmount;//未读数量

    public int getUnReadAmount() {
        return unReadAmount;
    }

    public void setUnReadAmount(int unReadAmount) {
        this.unReadAmount = unReadAmount;
    }

    public WorkOrderTypeBean(String content, boolean isSelected, int unReadAmount) {
        this.content = content;
        this.isSelected = isSelected;
        this.unReadAmount = unReadAmount;
    }

    public String getContent() {
        return content == null ? "" : content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
