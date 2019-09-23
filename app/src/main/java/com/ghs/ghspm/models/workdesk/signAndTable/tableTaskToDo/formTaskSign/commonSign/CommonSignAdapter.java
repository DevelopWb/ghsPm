package com.ghs.ghspm.models.workdesk.signAndTable.tableTaskToDo.formTaskSign.commonSign;

import android.support.v4.content.ContextCompat;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ghs.ghspm.R;
import com.ghs.ghspm.bean.DailyTaskJsonBean;
import com.ghs.ghspm.tools.StrUtils;

/**
 * Author:wang_sir
 * Time:2018/12/21 15:14
 * Description:This is   签字或者处理临时任务类表单的适配器
 */
public class CommonSignAdapter extends BaseQuickAdapter<DailyTaskJsonBean, BaseViewHolder> {
    public CommonSignAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, DailyTaskJsonBean dailyTaskJsonBean) {
        helper.setText(R.id.sign_or_deal_key_tv, dailyTaskJsonBean.getTitle());

        String cssClass = dailyTaskJsonBean.getCssClass();
        String value = StrUtils.isStringValueOk(dailyTaskJsonBean.getPrevalue()) ? dailyTaskJsonBean.getPrevalue() : "无";
        if ("image".equals(cssClass)) {
            if ("无".equals(value)) {
                helper.setText(R.id.sign_or_deal_value_tv, value);
                helper.setTextColor(R.id.sign_or_deal_value_tv, ContextCompat.getColor(mContext,R.color.app_black));

            } else {
                helper.setText(R.id.sign_or_deal_value_tv, "查看");
                helper.setBackgroundRes(R.id.sign_or_deal_value_tv, R.drawable.bg_white_bottom_blue_shape);
                helper.setTextColor(R.id.sign_or_deal_value_tv, ContextCompat.getColor(mContext,R.color.allot_key_yellow));
            }

        } else {
            helper.setBackgroundColor(R.id.sign_or_deal_value_tv, ContextCompat.getColor(mContext,R.color.app_white));
            helper.setTextColor(R.id.sign_or_deal_value_tv, ContextCompat.getColor(mContext,R.color.app_black));
            if ("money".equals(cssClass)) {
                if ("无".equals(value)) {
                    helper.setText(R.id.sign_or_deal_value_tv, value);
                } else {
                    helper.setText(R.id.sign_or_deal_value_tv, value + "元");

                }
            } else {
                helper.setText(R.id.sign_or_deal_value_tv, value);
            }

        }


    }
}
