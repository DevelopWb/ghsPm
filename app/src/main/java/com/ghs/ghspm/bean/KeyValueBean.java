package com.ghs.ghspm.bean;

/**
 * Author:wang_sir
 * Time:2019/1/2 19:00
 * Description:This is KeyValueBean
 */
public class KeyValueBean {
    public KeyValueBean(String key, String value) {
        this.key = key;
        this.value = value;
    }

    private String key;
    private String value;

    public String getKey() {
        return key == null ? "" : key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value == null ? "" : value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
