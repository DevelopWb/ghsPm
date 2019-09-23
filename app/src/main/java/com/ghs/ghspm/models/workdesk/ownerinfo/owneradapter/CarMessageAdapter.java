package com.ghs.ghspm.models.workdesk.ownerinfo.owneradapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ghs.ghspm.R;
import com.ghs.ghspm.bean.CarMessageBean;
import com.ghs.ghspm.models.workdesk.ownerinfo.illegally.IllegallyActivity;
import com.ghs.ghspm.tools.StrUtils;

public class CarMessageAdapter extends BaseQuickAdapter<CarMessageBean.DataBean, BaseViewHolder> {
    public static final String CAR_NUM = "CAR_NUM";
    Context context;

    public CarMessageAdapter(int layoutResId, Context context) {
        super(layoutResId);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, final CarMessageBean.DataBean item) {
        helper.setText(R.id.car_message_carId, item.getCarNum() == null ? "暂无" : StrUtils.stringSecond(item.getCarNum()));
        helper.setText(R.id.car_message_carOwner, item.getUserName() == null ? "暂无" : item.getUserName());
        helper.setText(R.id.car_message_mobile_phone, item.getMobile() == null ? "暂无" : item.getMobile());
        TextView car_message_illegally = helper.getView(R.id.car_message_illegally);
        car_message_illegally.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, IllegallyActivity.class);
                intent.putExtra(CAR_NUM, item.getCarNum());
                context.startActivity(intent);

            }
        });


    }


}
