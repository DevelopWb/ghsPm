package com.ghs.ghspm.models.workOrder;

import android.support.v4.content.ContextCompat;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ghs.ghspm.R;
import com.ghs.ghspm.bean.WorkOrderTypeBean;

/**
 * Author:wang_sir
 * Time:2019/7/5 16:43
 * Description:This is WorkOrderTypeAdapter
 */
public class WorkOrderTypeAdapter extends BaseQuickAdapter<WorkOrderTypeBean, BaseViewHolder> {
    public WorkOrderTypeAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, WorkOrderTypeBean item) {
        int position = helper.getAdapterPosition();
        int  unreadAmount = item.getUnReadAmount();
        if (unreadAmount>0) {
            helper.setVisible(R.id.order_type_unread_amount_tv,true);
            helper.setText(R.id.order_type_unread_amount_tv,String.valueOf(unreadAmount));
        }else{
            helper.setVisible(R.id.order_type_unread_amount_tv,false);

        }
        if (position==mData.size()-1) {
            helper.setGone(R.id.order_type_selected_divider_v,false);
        }else{
            helper.setGone(R.id.order_type_selected_divider_v,true);
        }
        helper.setText(R.id.order_type_name_tv, item.getContent());
        if (item.isSelected()) {
            helper.setGone(R.id.order_type_selected_tag_tv,true);
            helper.setTextColor(R.id.order_type_name_tv, ContextCompat.getColor(mContext,R.color.app_default_blue));
        }else{
            helper.setGone(R.id.order_type_selected_tag_tv,false);
            helper.setTextColor(R.id.order_type_name_tv, ContextCompat.getColor(mContext,R.color.app_black));

        }
    }
}
