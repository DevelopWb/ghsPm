package com.ghs.ghspm.models.workdesk.ownerinfo.owneradapter;

import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ghs.ghspm.R;
import com.ghs.ghspm.bean.OwnerPropretyBean;
import com.ghs.ghspm.tools.StrUtils;

public class OwnerPropretyAdapter extends BaseQuickAdapter<OwnerPropretyBean.DataBean,BaseViewHolder> {
    public OwnerPropretyAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, OwnerPropretyBean.DataBean item) {

          helper.setText(R.id.owner_proprety_item_title,item.getTitle());
//          helper.setText(R.id.owner_proprety_item_data,item.getPayTime()+"");
          helper.setText(R.id.owner_proprety_item_pay, StrUtils.formatDoubleData(item.getPayMoney()));
        TextView owner_waterbill_item_paystate = helper.getView(R.id.owner_proprety_item_paystate);

        if(!"已支付".equals(getState(item.getPayState()))){
            helper.setText(R.id.owner_proprety_item_paystate,getState(item.getPayState()));
//            TextView owner_waterbill_item_data = helper.getView(R.id.owner_proprety_item_data);
//            owner_waterbill_item_data.setVisibility(View.GONE);
            owner_waterbill_item_paystate.setVisibility(View.VISIBLE);
        }else {
            owner_waterbill_item_paystate.setVisibility(View.GONE);
            helper.setText(R.id.owner_proprety_item_paystate,getState(item.getPayState()));

        }

    }

    public String getState(int state) {
        String Falg = null;
        switch (state) {
            case 1:
                Falg = "未支付";
                break;

            case 2:
                Falg = "未支付";
                break;
            case 3:
                Falg = "未支付";

                break;


            case 4:

                Falg = "已支付";

                break;
            case 5:
                Falg = "未支付";


                break;

            case 6:
                Falg = "未支付";


                break;


        }

        return Falg;
    }

}
