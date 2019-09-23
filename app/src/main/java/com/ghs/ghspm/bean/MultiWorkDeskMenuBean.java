package com.ghs.ghspm.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Author:wang_sir
 * Time:2019/1/9 14:19
 * Description:This is MultiWorkDeskMenuBean
 */
public class MultiWorkDeskMenuBean implements MultiItemEntity {
    public static final int FIXED_MENU = 1;//固定菜单类
    public static final int DYNAMIC_MENU = 2;//动态菜单类
    private int itemType;
    private Object object;

    public MultiWorkDeskMenuBean(int itemType, Object object) {
        this.itemType = itemType;
        this.object = object;
    }

    @Override
    public int getItemType() {
        return itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    @Override
    public String toString() {
        return "MultiWorkDeskMenuBean{" +
                "itemType=" + itemType +
                ", object=" + object +
                '}';
    }
}
