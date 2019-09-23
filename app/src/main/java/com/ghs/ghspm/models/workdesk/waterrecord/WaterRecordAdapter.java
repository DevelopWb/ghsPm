package com.ghs.ghspm.models.workdesk.waterrecord;

import android.graphics.Color;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ghs.ghspm.R;
import com.ghs.ghspm.bean.WaterRecordBean;
import com.ghs.ghspm.tools.StrUtils;

/**
 * Author:wang_sir
 * Time:2018/10/24 11:24
 * Description:This is WaterRecordAdapter
 */
public class WaterRecordAdapter extends BaseQuickAdapter<WaterRecordBean.DataBean, BaseViewHolder> {
    public WaterRecordAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, WaterRecordBean.DataBean item) {
        helper.setText(R.id.water_record_room_name_tv, item.getRoomNumber());
        if (item.getWatchNumber() > 0) {
            helper.setText(R.id.water_record_room_value_tv, String.valueOf(item.getWatchNumber()) + " 吨");
        } else {
            helper.setText(R.id.water_record_room_value_tv, item.getRemark());
        }
        if (item.getWatchNumber()>0|| StrUtils.isStringValueOk(item.getRemark())) {
          helper.setTextColor(R.id.water_record_room_name_tv, Color.parseColor("#9B9B9B"));
          helper.setTextColor(R.id.water_record_room_value_tv, Color.parseColor("#9B9B9B"));
        }else{
            helper.setTextColor(R.id.water_record_room_name_tv, Color.parseColor("#000000"));
            helper.setTextColor(R.id.water_record_room_value_tv, Color.parseColor("#000000"));

        }
    }


}
