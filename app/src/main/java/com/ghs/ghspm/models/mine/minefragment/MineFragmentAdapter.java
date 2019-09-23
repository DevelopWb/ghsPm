package com.ghs.ghspm.models.mine.minefragment;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ghs.ghspm.R;
import com.ghs.ghspm.bean.MineMenuBean;

/**
 * Author:wang_sir
 * Time:2019/2/1 14:34
 * Description:This is SystemNoticeAdapter
 */
public class MineFragmentAdapter extends BaseQuickAdapter<MineMenuBean, BaseViewHolder> {
    public MineFragmentAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, MineMenuBean item) {
        helper.setText(R.id.menu_title_tv,item.getTitle());
        helper.setBackgroundRes(R.id.patrol_arrow_right_tv,item.getDrawbleId());
        int unReadPointAmount = item.getUnReadAmount();
        if (unReadPointAmount>0) {
            helper.setVisible(R.id.menu_unread_tag_tv,true);
            helper.setText(R.id.menu_unread_tag_tv,String.valueOf(unReadPointAmount));
        }else{
            helper.setVisible(R.id.menu_unread_tag_tv,false);

        }

    }
}
