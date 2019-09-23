package com.ghs.ghspm.models.mine.personalInfo;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ghs.ghspm.R;
import com.ghs.ghspm.bean.UserInfoBean;

/**
 * Author:wang_sir
 * Time:2018/10/13 15:20
 * Description:This is PersonalInfoAdapter
 */
public class PersonalInfoAdapter extends BaseQuickAdapter<UserInfoBean.DataBean.UserConfigListBean, BaseViewHolder> {
    public PersonalInfoAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, UserInfoBean.DataBean.UserConfigListBean item) {
        helper.setText(R.id.user_info_item_key_tv, item.getTitle());
        helper.setText(R.id.user_info_item_value_tv, item.getValue());
    }
}
