package com.ghs.ghspm.models.workdesk.organizeframe;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ghs.ghspm.R;
import com.ghs.ghspm.bean.section.OrganizeMenuBean;

/**
 * Author:wang_sir
 * Time:2018/10/11 11:48
 * Description:This is OrganizeMenuAdapter
 */
public class OrganizeMenuAdapter extends BaseQuickAdapter<OrganizeMenuBean, BaseViewHolder> {
    public OrganizeMenuAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, OrganizeMenuBean item) {
        int position = helper.getAdapterPosition();
        String name = "";
        if (0 == position) {
            name = item.getDepName();
        } else {
            name = ">  " + item.getDepName();
        }
        helper.setText(R.id.organaze_menu_name_tv, name);

    }
}
