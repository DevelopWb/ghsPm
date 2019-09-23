package com.ghs.ghspm.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Author:wang_sir
 * Time:2019/1/3 11:43
 * Description:This is DynamicLayoutBean
 */
public class DynamicLayoutBean {
    /**
     * min : 1
     * cssClass : text
     * max : 500
     * fieldesc : 必须输入
     * title : 姓名
     * required : required
     * children : ["男","女"]
     * format : yyyy-MM-dd
     */

    private String min;
    private String cssClass;
    private String max;
    private String fieldesc;
    private String title;
    private String required;
    private List<String> children;
    private String format;
    private String preId;
    private String value;

    public DynamicLayoutBean(String cssClass, String fieldesc, String title, String required,String value) {
        this.cssClass = cssClass;
        this.fieldesc = fieldesc;
        this.title = title;
        this.required = required;
        this.value = value;
    }

    public String getPreId() {
        return preId == null ? "" : preId;
    }

    public void setPreId(String preId) {
        this.preId = preId;
    }

    public String getValue() {
        return value == null ? "" : value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getMin() {
        return min == null ? "" : min;
    }

    public void setMin(String min) {
        this.min = min;
    }

    public String getCssClass() {
        return cssClass == null ? "" : cssClass;
    }

    public void setCssClass(String cssClass) {
        this.cssClass = cssClass;
    }

    public String getMax() {
        return max == null ? "" : max;
    }

    public void setMax(String max) {
        this.max = max;
    }

    public String getFieldesc() {
        return fieldesc == null ? "" : fieldesc;
    }

    public void setFieldesc(String fieldesc) {
        this.fieldesc = fieldesc;
    }

    public String getTitle() {
        return title == null ? "" : title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRequired() {
        return required == null ? "" : required;
    }

    public void setRequired(String required) {
        this.required = required;
    }

    public String getFormat() {
        return format == null ? "" : format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public List<String> getChildren() {
        if (children == null) {
            return new ArrayList<>();
        }
        return children;
    }

    public void setChildren(List<String> children) {
        this.children = children;
    }
}
