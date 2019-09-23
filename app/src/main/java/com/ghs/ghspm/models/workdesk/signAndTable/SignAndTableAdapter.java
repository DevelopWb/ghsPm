package com.ghs.ghspm.models.workdesk.signAndTable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ghs.ghspm.R;
import com.ghs.ghspm.bean.TableTaskOutLineBean;
import com.ghs.ghspm.tools.CalendarUtil;

/**
 * Author:wang_sir
 * Time:2018/12/21 15:14
 * Description:This is RecordOfMineAdapter
 */
public class SignAndTableAdapter extends BaseQuickAdapter<TableTaskOutLineBean.DataBean, BaseViewHolder> {
    public SignAndTableAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, TableTaskOutLineBean.DataBean tableTaskBean) {
        helper.setVisible(R.id.notice_item_title_tag_tv,true);
        helper.setText(R.id.notice_item_title_tv, tableTaskBean.getTitle());
        helper.setText(R.id.notice_item_creater_tv, tableTaskBean.getUserName());
        helper.setText(R.id.notice_item_time_tv, "时间:" + CalendarUtil.getTimeFromStringTime("yyyy-MM-dd HH:mm", "yyyy-MM-dd HH:mm:ss", tableTaskBean.getCreateTime()));
        int tableTaskStatus = tableTaskBean.getStatus();
        //1已完成，2待处理，3完成待审核，4转发待审核，5放弃待审核，6已放弃
        switch (tableTaskStatus) {
            case 1:
                helper.setText(R.id.notice_item_satsus_tv, "待处理");
                break;
            case 2:
                helper.setText(R.id.notice_item_satsus_tv, "待签批");

                break;
            case 3:
                helper.setText(R.id.notice_item_satsus_tv, "已完成");

                break;
            default:
                break;
        }
    }
}
