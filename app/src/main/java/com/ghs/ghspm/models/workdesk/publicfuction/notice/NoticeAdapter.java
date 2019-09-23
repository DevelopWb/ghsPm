package com.ghs.ghspm.models.workdesk.publicfuction.notice;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ghs.ghspm.R;
import com.ghs.ghspm.bean.NoticeBean;

/**
 * Author:wang_sir
 * Time:2018/9/29 10:59
 * Description:This is TaskAdapter
 */
public class NoticeAdapter extends BaseQuickAdapter<NoticeBean.DataBean.RowsBean, BaseViewHolder> {
    public NoticeAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, NoticeBean.DataBean.RowsBean item) {
        boolean toShow = item.getReadType()==2?true:false;
        if (toShow) {
            helper.setGone(R.id.notice_item_red_hot_iv,true);
        }else{
            helper.setGone(R.id.notice_item_red_hot_iv,false);

        }
//        String  creatName = StrUtils.isStringValueOk(item.getCreateUserName())?item.getCreateUserName():"";
        helper.setText(R.id.notice_item_title_tv, item.getTitle());
        helper.setText(R.id.notice_item_creater_tv, item.getCreateUserName());
        helper.setText(R.id.notice_item_time_tv, item.getCreateTime());
        helper.setVisible(R.id.notice_item_satsus_tv,false);
    }
}
