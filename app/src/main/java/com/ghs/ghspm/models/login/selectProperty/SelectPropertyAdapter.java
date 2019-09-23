package com.ghs.ghspm.models.login.selectProperty;

import android.support.v4.content.ContextCompat;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ghs.ghspm.R;
import com.ghs.ghspm.bean.PropertiesBean;
import com.ghs.ghspm.tools.UserInfoUtil;

/**
 * Author:wang_sir
 * Time:2019/3/19 15:19
 * Description:This is SelectPropertyAdapter
 */
public class SelectPropertyAdapter extends BaseQuickAdapter<PropertiesBean.DataBean, BaseViewHolder> {
    public SelectPropertyAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, PropertiesBean.DataBean item) {
        helper.setText(R.id.property_name_tv, item.getPropertyName());
        int propertyId = UserInfoUtil.getInstance().getPropertyId();
        if (propertyId == item.getId()) {
            helper.setGone(R.id.property_selected_tag_tv, true);
            helper.setTextColor(R.id.property_name_tv, ContextCompat.getColor(mContext,R.color.app_default_blue));
        } else {
            helper.setGone(R.id.property_selected_tag_tv, false);
            helper.setTextColor(R.id.property_name_tv, ContextCompat.getColor(mContext,R.color.app_black));

        }
    }
}
