package com.ghs.ghspm.models.workdesk.publicfuction.universalkey.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ghs.ghspm.R;
import com.ghs.ghspm.bean.VilliageDoorBean;

/**
 * Author:wang_sir
 * Time:2018/9/21 16:24
 * Description:This is UniversalKeyAdapter
 */
public class UniversalKeyAdapter extends BaseQuickAdapter<VilliageDoorBean.DataBean, BaseViewHolder> {
    public UniversalKeyAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, VilliageDoorBean.DataBean item) {
        helper.setText(R.id.universal_key_door_name_tv, item.getDeviceName());
    }
}
