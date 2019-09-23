package com.ghs.ghspm.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Author:wang_sir
 * Time:2018/10/11 15:19
 * Description:This is OrganizeItem
 */
public class OrganizeItem implements MultiItemEntity {

    public static final int DEPS = 1;
    public static final int USERS = 2;
    public static final int USER = 3;
    private int itemType;
    private Object object;

    public OrganizeItem(int itemType, Object object) {
        this.itemType = itemType;
        this.object = object;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    @Override
    public int getItemType() {
        return itemType;
    }
}
