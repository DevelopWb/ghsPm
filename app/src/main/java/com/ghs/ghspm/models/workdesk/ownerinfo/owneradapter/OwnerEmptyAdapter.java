package com.ghs.ghspm.models.workdesk.ownerinfo.owneradapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ghs.ghspm.bean.OwnerMessageBean;

public class OwnerEmptyAdapter extends BaseQuickAdapter<OwnerMessageBean.DataBean,BaseViewHolder> {

    public OwnerEmptyAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, OwnerMessageBean.DataBean item) {

    }
}
