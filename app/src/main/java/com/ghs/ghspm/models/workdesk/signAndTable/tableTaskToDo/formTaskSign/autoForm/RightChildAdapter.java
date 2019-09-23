package com.ghs.ghspm.models.workdesk.signAndTable.tableTaskToDo.formTaskSign.autoForm;

import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ghs.ghspm.R;
import com.ghs.ghspm.bean.AutoFormBean;
import com.ghs.ghspm.tools.StrUtils;

/**
 * Author:wang_sir
 * Time:2018/12/25 17:56
 * Description:This is LeftMenuAdapter
 */
public class RightChildAdapter extends BaseQuickAdapter<AutoFormBean.DataBean, BaseViewHolder> {

    public RightChildAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, AutoFormBean.DataBean item) {
        Log.d("LEFTMENUE", "----------" + String.valueOf(helper.getAdapterPosition()));
        String emptyTag = item.getEmptyDataTag();
        String key = item.getCssClass();
        String value = item.getPrevalue();
        boolean  selected = item.isSelected();
        if (selected) {
            helper.setBackgroundRes(R.id.tv_table_content_item_ll,R.drawable.patrol_form_record_selected_right_sp);
        }else{
            helper.setBackgroundRes(R.id.tv_table_content_item_ll,R.drawable.bg_patrol_form_right_content);

        }
        if ("空".equals(emptyTag)) {
            helper.setText(R.id.tv_table_content_item_left, "");
        }else{
            if (!StrUtils.isStringValueOk(value)) {
                helper.setText(R.id.tv_table_content_item_left, "无");
                if (selected) {
                    helper.setBackgroundRes(R.id.tv_table_content_item_left, R.color.patrol_form_selected_color);
                }else{
                    helper.setBackgroundRes(R.id.tv_table_content_item_left, R.color.app_white);
                }
                helper.setTextColor(R.id.tv_table_content_item_left, ContextCompat.getColor(mContext,R.color.app_black));

            } else {
                if ("image".equals(key)) {
                    helper.setText(R.id.tv_table_content_item_left, "查看");
                    if (selected) {
                        helper.setBackgroundRes(R.id.tv_table_content_item_left, R.drawable.bg_patrol_form_image_select);
                    }else{
                        helper.setBackgroundRes(R.id.tv_table_content_item_left, R.drawable.bg_white_bottom_blue_shape);
                    }
                    helper.setTextColor(R.id.tv_table_content_item_left, ContextCompat.getColor(mContext,R.color.blue));
                } else {
                    helper.setText(R.id.tv_table_content_item_left, value);

                }

            }
        }

    }
}
