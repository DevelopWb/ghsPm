package com.ghs.ghspm.models.main;

import android.support.v4.content.ContextCompat;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ghs.ghspm.R;
import com.ghs.ghspm.bean.VillagesBean;
import com.ghs.ghspm.tools.UserInfoUtil;

/**
 * Author:wang_sir
 * Time:2019/4/12 14:11
 * Description:This is VillagesAdapter
 */
public class VillagesAdapter extends BaseQuickAdapter<VillagesBean.DataBean, BaseViewHolder> {
    public VillagesAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, VillagesBean.DataBean item) {
        helper.setText(R.id.village_name_tv, item.getVillageName());
        int villageId = UserInfoUtil.getInstance().getVillageId();
        if (villageId == item.getId()) {
            helper.setGone(R.id.village_selected_tag_tv, true);
            helper.setTextColor(R.id.village_name_tv, ContextCompat.getColor(mContext,R.color.app_default_blue));
        } else {
            helper.setGone(R.id.village_selected_tag_tv, false);
            helper.setTextColor(R.id.village_name_tv, ContextCompat.getColor(mContext,R.color.app_black));

        }
    }
}
