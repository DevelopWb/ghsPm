package com.ghs.ghspm.models.workdesk.signAndTable.tableTaskToDo.tableTaskDescribe;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ghs.ghspm.R;
import com.ghs.ghspm.bean.KeyValueBean;
import com.ghs.ghspm.tools.StrUtils;

/**
 * Author:wang_sir
 * Time:2018/12/21 15:14
 * Description:This is   签字或者处理临时任务类表单的适配器
 */
public class TableTaskDesAdapter extends BaseQuickAdapter<KeyValueBean, BaseViewHolder> {
    public TableTaskDesAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, KeyValueBean keyValueBean) {
        String value = StrUtils.isStringValueOk(keyValueBean.getValue()) ? keyValueBean.getValue() : "无";
        helper.setText(R.id.sign_or_deal_key_tv, keyValueBean.getKey());
        helper.setText(R.id.sign_or_deal_value_tv, value);

    }
}
