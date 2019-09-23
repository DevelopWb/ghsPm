package com.ghs.ghspm.models.workdesk.innerrepair;

import android.graphics.Color;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ghs.ghspm.R;
import com.ghs.ghspm.bean.InnerRepairTagBean;

/**
 * Author:wang_sir
 * Time:2018/11/1 19:01
 * Description:This is InnerRepairMenuAdapter
 */
public class InnerRepairTagsAdapter extends BaseQuickAdapter<InnerRepairTagBean,BaseViewHolder> {
    public InnerRepairTagsAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, InnerRepairTagBean item) {
        helper.setText(R.id.inner_repair_tag_name_tv, item.getName());
        if (item.isSelected()) {
            helper.setBackgroundRes(R.id.inner_repair_tag_name_tv,R.drawable.sp_default_color);
            helper.setTextColor(R.id.inner_repair_tag_name_tv, Color.parseColor("#ffffff"));
        } else {
            helper.setBackgroundRes(R.id.inner_repair_tag_name_tv,R.drawable.rv_white_shadow_shape);
            helper.setTextColor(R.id.inner_repair_tag_name_tv, Color.parseColor("#000000"));

        }
    }
}
