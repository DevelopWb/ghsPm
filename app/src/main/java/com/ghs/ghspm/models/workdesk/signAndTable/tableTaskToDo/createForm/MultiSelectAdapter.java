package com.ghs.ghspm.models.workdesk.signAndTable.tableTaskToDo.createForm;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ghs.ghspm.R;

/**
 * Author:wang_sir
 * Time:2018/12/21 15:14
 * Description:This is 多选 适配器
 */
public class MultiSelectAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public MultiSelectAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.multi_select_content_tv, item);
    }
}
