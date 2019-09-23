package com.ghs.ghspm.models.workdesk.carnum;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ghs.ghspm.R;
import com.ghs.ghspm.tools.StrUtils;

/**
 * Author:wang_sir
 * Time:2018/9/26 16:39
 * Description:This is CarNumAdapter
 */
public class CarNumAdapter  extends BaseQuickAdapter<String, BaseViewHolder> {
    public CarNumAdapter(int layoutResId) {
        super(layoutResId);
    }



    @Override
    protected void convert(BaseViewHolder helper, String text) {
        if (StrUtils.isStringValueOk(text)) {
            helper.setBackgroundRes(R.id.car_info_item_bt,R.drawable.bt_selecter_select_car_no);
        }else{
            helper.setBackgroundRes(R.id.car_info_item_bt,0);
        }
        helper.setText(R.id.car_info_item_bt,text);
        helper.addOnClickListener(R.id.car_info_item_bt);
    }

}
