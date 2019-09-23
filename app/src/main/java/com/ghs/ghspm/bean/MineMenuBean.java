package com.ghs.ghspm.bean;

/**
 * Author:wang_sir
 * Time:2019/2/12 14:19
 * Description:This is MineMenuBean
 */
public class MineMenuBean {
    private String title;
    private int unReadAmount;
    private int drawbleId;

    public MineMenuBean(String title, int unReadAmount, int drawbleId) {
        this.title = title;
        this.unReadAmount = unReadAmount;
        this.drawbleId = drawbleId;
    }

    public String getTitle() {
        return title == null ? "" : title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getUnReadAmount() {
        return unReadAmount;
    }

    public void setUnReadAmount(int unReadAmount) {
        this.unReadAmount = unReadAmount;
    }

    public int getDrawbleId() {
        return drawbleId;
    }

    public void setDrawbleId(int drawbleId) {
        this.drawbleId = drawbleId;
    }
}
