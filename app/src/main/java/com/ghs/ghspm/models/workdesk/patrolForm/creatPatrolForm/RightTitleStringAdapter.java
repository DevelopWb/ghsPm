package com.ghs.ghspm.models.workdesk.patrolForm.creatPatrolForm;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ghs.ghspm.R;

/**
 * Author:wang_sir
 * Time:2018/12/25 17:56
 * Description:This is LeftMenuAdapter
 */
public class RightTitleStringAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public RightTitleStringAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper,String item) {
        helper.setText(R.id.tv_table_content_item_left, item);
        helper.setBackgroundRes(R.id.tv_table_content_item_ll,R.drawable.bg_patrol_form_title);

    }
}
