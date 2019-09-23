package com.ghs.ghspm.models.workdesk.innerrepair;

import android.graphics.Color;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ghs.ghspm.R;
import com.ghs.ghspm.bean.InnerRepairBean;

/**
 * Author:wang_sir
 * Time:2018/11/1 19:01
 * Description:This is InnerRepairMenuAdapter
 */
public class InnerRepairMenuAdapter extends BaseQuickAdapter<InnerRepairBean.DataBean, BaseViewHolder> {
    public InnerRepairMenuAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, InnerRepairBean.DataBean item) {
        helper.setText(R.id.inner_repair_menu_name_tv, item.getKind());
        if (item.isSelected()) {
            helper.setBackgroundColor(R.id.inner_repair_menu_name_tv, Color.parseColor("#48CBC3"));
            helper.setTextColor(R.id.inner_repair_menu_name_tv, Color.parseColor("#ffffff"));
        } else {
            helper.setBackgroundColor(R.id.inner_repair_menu_name_tv, Color.parseColor("#ffffff"));
            helper.setTextColor(R.id.inner_repair_menu_name_tv, Color.parseColor("#000000"));

        }
    }
}
