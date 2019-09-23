package com.ghs.ghspm.models.workOrder.workOrderDetail;

import android.support.v4.content.ContextCompat;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ghs.ghspm.R;
import com.ghs.ghspm.bean.KeyValueBean;
import com.ghs.ghspm.tools.StrUtils;


/**
 * Author:wang_sir
 * Time:2018/6/29 11:16
 * Description:This is   未支付订单里面的rv
 */

public class WorkOrderDetailAdapter extends BaseQuickAdapter<KeyValueBean, BaseViewHolder> {

    private boolean isMoreMsg;//更多信息的适配器

    public WorkOrderDetailAdapter(int layoutResId, boolean isMoreMsg) {
        super(layoutResId);
        this.isMoreMsg = isMoreMsg;
    }

    @Override
    protected void convert(BaseViewHolder helper, KeyValueBean item) {
        helper.setText(R.id.display_key_tv, item.getKey());
        String value = item.getValue();
        value = StrUtils.isStringValueOk(value) ? value : "暂无";
        helper.setText(R.id.display_value_tv, value);
        int position = helper.getAdapterPosition();
        if (position == mData.size() - 1) {
            helper.setBackgroundColor(R.id.display_group_cl, ContextCompat.getColor(mContext, R.color.app_white));
        } else {
            helper.setBackgroundRes(R.id.display_group_cl, R.drawable.bg_white_only_bottom_gray_shape_1px);
        }
        if (isMoreMsg) {
            if (position==1) {
                helper.setGone(R.id.display_divider_10dp_v, true);
                helper.setBackgroundColor(R.id.display_group_cl, ContextCompat.getColor(mContext, R.color.app_white));
            }else{
                helper.setGone(R.id.display_divider_10dp_v, false);
            }
        }else{
            helper.setGone(R.id.display_divider_10dp_v, false);
        }
    }
}

