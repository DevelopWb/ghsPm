package com.ghs.ghspm.models.workdesk.ownerinfo.owneradapter;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ghs.ghspm.R;
import com.ghs.ghspm.bean.OwnerWaterBillBean;

import java.util.List;

public class WaterbillAdapter extends BaseQuickAdapter<OwnerWaterBillBean.DataBean, BaseViewHolder> {

    private WaterBillListAdapter listAdapter;

    public WaterbillAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, OwnerWaterBillBean.DataBean item) {
        helper.setText(R.id.owner_waterbill_item1_year, item.getYear() + "年");
        RecyclerView owner_waterbill_item1_recylerview = helper.getView(R.id.owner_waterbill_item1_recylerview);
        owner_waterbill_item1_recylerview.setLayoutManager(new LinearLayoutManager(mContext));
        listAdapter = new WaterBillListAdapter(R.layout.owner_waterbill_item_layout);
        List<OwnerWaterBillBean.DataBean.BillDOListBean> orderDetailBeanList = item.getBillDOList();
//        List<OwnerWaterBillBean.DataBean.OrderDetailBeanListBean> orderDetailBeanList = item.getOrderDetailBeanList();
        if(orderDetailBeanList.size()== 0){
            TextView view = helper.getView(R.id.owner_waterbill_item1_year);
            view.setVisibility(View.GONE);
        }else {
            listAdapter.setNewData(orderDetailBeanList);
            owner_waterbill_item1_recylerview.setAdapter(listAdapter);

        }
    }


    public View getAdaoterEmptyView(String text) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.empty_view_list_layout, null);
        TextView noticeTv = view.findViewById(R.id.empty_view1_tv);
        noticeTv.setText(text);
        return view;
    }
     //最新一个item不显示下划线
    public void addDivider(RecyclerView recyclerView, boolean isVertical, boolean haveEndLine) {
        com.ghs.ghspm.tools.DividerItemDecoration dividerItemDecoration;

        if (!isVertical) {
            dividerItemDecoration = new com.ghs.ghspm.tools.DividerItemDecoration(mContext, com.ghs.ghspm.tools.DividerItemDecoration.VERTICAL_LIST);
        } else {
            dividerItemDecoration = new com.ghs.ghspm.tools.DividerItemDecoration(mContext, com.ghs.ghspm.tools.DividerItemDecoration.HORIZONTAL_LIST);
        }
        if (haveEndLine) {
            //最后一个item下面是否画线
            dividerItemDecoration.setDividerMode(com.ghs.ghspm.tools.DividerItemDecoration.END);
        }
        recyclerView.addItemDecoration(dividerItemDecoration);
    }

}
