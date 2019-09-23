package com.ghs.ghspm.models.workOrder.createOrder;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ghs.ghspm.R;
import com.ghs.ghspm.bean.RoomSearchedBean;

/**
 * Author:wang_sir
 * Time:2019/7/19 16:33
 * Description:This is SearchRommAdapter
 */
public class SearchRommAdapter extends BaseQuickAdapter<RoomSearchedBean.DataBean, BaseViewHolder> {
    public SearchRommAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, RoomSearchedBean.DataBean dataBean) {
        helper.setText(R.id.searched_room_num_tv, dataBean.getPortionName() + "-" + dataBean.getTowerNumber() + "-" + dataBean.getCellName() + "-" + dataBean.getRoomNumber());
    }
}
