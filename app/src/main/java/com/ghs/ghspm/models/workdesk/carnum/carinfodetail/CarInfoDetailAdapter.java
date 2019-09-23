package com.ghs.ghspm.models.workdesk.carnum.carinfodetail;

import android.support.v4.content.ContextCompat;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ghs.ghspm.R;

/**
 * Author:wang_sir
 * Time:2018/9/28 19:56
 * Description:This is CarInfoDetailAdapter
 */
public class CarInfoDetailAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    private String[] names = {"车主信息", "预留电话", "入场时间", "出场时间", "停留时长", "停车费用"};

    public CarInfoDetailAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        int position = helper.getAdapterPosition();
        helper.setBackgroundColor(R.id.car_info_detail_item_value_tv, ContextCompat.getColor(mContext,R.color.app_white));
        helper.setTextColor(R.id.car_info_detail_item_value_tv, ContextCompat.getColor(mContext,R.color.app_black));
        if (1==position) {
            if (!"暂无信息".equals(item)) {
                helper.setBackgroundRes(R.id.car_info_detail_item_value_tv, R.drawable.bg_white_bottom_blue_shape);
                helper.setTextColor(R.id.car_info_detail_item_value_tv, ContextCompat.getColor(mContext,R.color.blue));
            }

        }
        helper.setText(R.id.car_info_detail_item_key_tv, names[position]);
        helper.setText(R.id.car_info_detail_item_value_tv, item);
    }
}
