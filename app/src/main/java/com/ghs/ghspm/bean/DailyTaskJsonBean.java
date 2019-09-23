package com.ghs.ghspm.bean;

/**
 * Author:wang_sir
 * Time:2019/1/4 11:38
 * Description:This is DailyTaskJsonBean
 */
public class DailyTaskJsonBean {
    /**
     * title : 姓名
     * cssClass : text
     * prevalue : 马云
     */

    private String title;
    private String cssClass;
    private String prevalue;
    private String  preId;

    public DailyTaskJsonBean(String title, String cssClass, String prevalue, String preId) {
        this.title = title;
        this.cssClass = cssClass;
        this.prevalue = prevalue;
        this.preId = preId;
    }


    public String getTitle() {
        return title == null ? "" : title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCssClass() {
        return cssClass == null ? "" : cssClass;
    }

    public void setCssClass(String cssClass) {
        this.cssClass = cssClass;
    }

    public String getPrevalue() {
        return prevalue == null ? "" : prevalue;
    }

    public void setPrevalue(String prevalue) {
        this.prevalue = prevalue;
    }
}
