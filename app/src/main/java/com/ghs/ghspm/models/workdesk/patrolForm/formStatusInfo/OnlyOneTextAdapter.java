package com.ghs.ghspm.models.workdesk.patrolForm.formStatusInfo;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ghs.ghspm.R;

/**
 * Author:wang_sir
 * Time:2019/4/23 17:54
 * Description:This is OnlyOneTextAdapter
 */
public class OnlyOneTextAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public OnlyOneTextAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.text_gravity_left_tv, item);
    }
}
