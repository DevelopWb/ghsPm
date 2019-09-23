package com.ghs.ghspm.models.mine.set.useguide;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ghs.ghspm.R;

/**
 * Author:wang_sir
 * Time:2018/10/24 19:25
 * Description:This is UserGuideAdapter
 */
public class UserGuideAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public UserGuideAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.mine_bar_left, item);
    }
}
