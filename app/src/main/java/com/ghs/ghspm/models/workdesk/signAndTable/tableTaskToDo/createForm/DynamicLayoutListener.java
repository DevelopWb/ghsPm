package com.ghs.ghspm.models.workdesk.signAndTable.tableTaskToDo.createForm;

import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.TextView;

import com.ghs.ghspm.bean.DynamicLayoutBean;

/**
 * Author:wang_sir
 * Time:2019/1/4 19:26
 * Description:This is DynamicLayoutListener
 */
public interface DynamicLayoutListener {


    void getSelectPhotosData(String value, RecyclerView recyclerView);

    void edittextOnTouchListen(DynamicLayoutBean dynamicLayoutBean);

    void edittextFocusChangedListen(EditText editText, boolean hasFocus);

    void getTowerView(TextView textView);

    void getCellView(TextView textView);

    void getRoomView(TextView textView);

}
