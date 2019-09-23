package com.ghs.ghspm.models.workdesk.publicfuction.universalkey.adapter;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ghs.ghspm.R;

public class RecentlyAdapter extends BaseQuickAdapter<String,BaseViewHolder> {
    public RecentlyAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.recently_item_text,item);
        helper.addOnClickListener(R.id.recently_item_text);
    }

}


