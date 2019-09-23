package com.ghs.ghspm.models.workdesk.signAndTable.tableTaskToDo.formTaskSign.autoForm;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ghs.ghspm.R;
import com.ghs.ghspm.bean.AutoFormBean;

/**
 * Author:wang_sir
 * Time:2018/12/25 17:56
 * Description:This is LeftMenuAdapter
 */
public class RightTitleAdapter extends BaseQuickAdapter<AutoFormBean.DataBean, BaseViewHolder> {

    public RightTitleAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, AutoFormBean.DataBean item) {
        helper.setText(R.id.tv_table_content_item_left, item.getTitle());
    }
}
