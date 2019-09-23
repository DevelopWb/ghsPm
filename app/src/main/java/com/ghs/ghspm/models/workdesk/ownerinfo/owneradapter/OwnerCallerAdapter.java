package com.ghs.ghspm.models.workdesk.ownerinfo.owneradapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class OwnerCallerAdapter extends BaseQuickAdapter<String,BaseViewHolder> {
    public OwnerCallerAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

    }
}
