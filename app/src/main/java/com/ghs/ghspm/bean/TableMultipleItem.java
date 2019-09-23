package com.ghs.ghspm.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Author:wang_sir
 * Time:2018/12/20 16:38
 * Description:This is 表单
 */
public class TableMultipleItem implements MultiItemEntity {
    public static final int TABLE_TEXT_SHOW = 0;//纯文本显示
    public static final int TABLE_TEXT = 1;//单行文本
    public static final int TABLE_AREA = 2;//多行文本
    public static final int TABLE_RADIO = 3;//单项选择框
    public static final int TABLE_CHECKBOX = 4;//多项选择框
    public static final int TABLE_NUMBER = 5;//数字输入框
    public static final int TABLE_MONEY = 6;//金额输入框
    public static final int TABLE_USER = 7;//选择人员，可多选
    public static final int TABLE_COPY_USER = 8;//选择人员，抄送人
    public static final int TABLE_CHECK_USER = 9;//选择人员,签批人
    public static final int TABLE_IMAGE = 10;//图片
    public static final int TABLE_DATE = 11;//日期
    public static final int TABLE_DEVICE = 12;//设备单选框
    public static final int TABLE_TOWER = 13;//楼栋单选框
    public static final int TABLE_CELL = 14;//单元号单选框
    public static final int TABLE_ROOM = 15;//房间号单选框
    private int itemType;
    private Object object;

    public TableMultipleItem(int itemType, Object object) {
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
