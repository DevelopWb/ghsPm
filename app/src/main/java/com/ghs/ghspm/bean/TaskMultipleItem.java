package com.ghs.ghspm.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Author:wang_sir
 * Time:2018/12/20 16:38
 * Description:This is TaskMultipleItem
 */
public class TaskMultipleItem implements MultiItemEntity {
    public static final int TEMP_TASK = 1;//临时任务
    public static final int TABLE_TASK = 2;//表单任务
    private int itemType;
    private Object object;

    public TaskMultipleItem(int itemType, Object object) {
        this.itemType = itemType;
        this.object = object;
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
    public int getItemType() {
        return itemType;
    }
}
