package com.ghs.ghspm.bean;

/**
 * Author:wang_sir
 * Time:2019/1/21 9:55
 * Description:This is DynamicSelectedPicBean
 */
public class DynamicSelectedPicBean {

    private String   dynamicLayoutTitle;//动态布局 标题
    private String  imagePath;//图片路径

    public DynamicSelectedPicBean(String dynamicLayoutTitle, String imagePath) {
        this.dynamicLayoutTitle = dynamicLayoutTitle;
        this.imagePath = imagePath;
    }

    public String getDynamicLayoutTitle() {
        return dynamicLayoutTitle == null ? "" : dynamicLayoutTitle;
    }

    public void setDynamicLayoutTitle(String dynamicLayoutTitle) {
        this.dynamicLayoutTitle = dynamicLayoutTitle;
    }

    public String getImagePath() {
        return imagePath == null ? "" : imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
