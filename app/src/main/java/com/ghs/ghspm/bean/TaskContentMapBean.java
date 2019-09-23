package com.ghs.ghspm.bean;

public class TaskContentMapBean {


    /**
     * title : 工单类型
     * value : 报事
     */

    private String title;
    private String value;

    public TaskContentMapBean(String title, String value) {
        this.title = title;
        this.value = value;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
