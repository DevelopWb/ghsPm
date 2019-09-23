package com.ghs.ghspm.bean;

import com.contrarywind.interfaces.IPickerViewData;

import java.util.List;

/**
 * created by guohainan
 * created date 2019/7/8
 * application
 * package name com.ghs.ghspm.bean
 */
public class CreateOrderTimeBean implements IPickerViewData {

    private String name;
    private List<String> time;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getTime() {
        return time;
    }

    public void setTime(List<String> time) {
        this.time = time;
    }

    @Override
    public String getPickerViewText() {
        return this.name;
    }


}
