package com.ghs.ghspm.models.workOrder.workOrderDetail;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ghs.ghspm.R;
import com.ghs.ghspm.base.displayPhotos.DisplayPhotosActivity;
import com.ghs.ghspm.bean.WorkOrderDetailBean;
import com.ghs.ghspm.models.workdesk.publicfuction.notice.normalnotice.NoticeDetailPicDisplayAdapter;
import com.ghs.ghspm.tools.CalendarUtil;
import com.ghs.ghspm.tools.StrUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Author:wang_sir
 * Time:2018/10/17 16:14
 * Description:This i  工单操作记录
 */
public class OrderOprateLogAdapter extends BaseQuickAdapter<WorkOrderDetailBean.DataBean.OpertionLogListBean, BaseViewHolder> {
    public OrderOprateLogAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, WorkOrderDetailBean.DataBean.OpertionLogListBean item) {
        int position = helper.getAdapterPosition();
        helper.setTextColor(R.id.order_operate_creat_time_tv, ContextCompat.getColor(mContext, R.color.app_gray));
        helper.setTextColor(R.id.order_operate_action_tv, ContextCompat.getColor(mContext, R.color.app_gray));
        ((WorkOrderDetailActivity)mContext).setHeadPicBgResource((ImageView) helper.getView(R.id.order_operate_head_pic_iv),(TextView) helper.getView(R.id.order_operate_head_pic_tv), item.getPmUserName(), item.getHeadImage(),item.getHeadImageBackGroudColor(),true);
        String time = item.getCreateTime();
        if (StrUtils.isStringValueOk(time)) {
            time = CalendarUtil.getTimeFromStringTime("MM月dd日 HH:mm", "yyyy-MM-dd HH:mm:ss", time);
            helper.setText(R.id.order_operate_creat_time_tv, time);
        } else {
            helper.setText(R.id.order_operate_creat_time_tv, "");
        }

        helper.setText(R.id.order_operate_action_tv, item.getContent());
        String remark = item.getRemark();
        if (StrUtils.isStringValueOk(remark)) {
            helper.setGone(R.id.order_operate_remark_tv, true);
            helper.setText(R.id.order_operate_remark_tv, item.getRemark());
        } else {
            helper.setGone(R.id.order_operate_remark_tv, false);
        }
        initRecyclerview(helper, item);
    }



    private void initRecyclerview(BaseViewHolder helper, WorkOrderDetailBean.DataBean.OpertionLogListBean item) {
        RecyclerView recyclerView = helper.getView(R.id.order_operate_item_rv);

        final String imageUrl = item.getImageUrl();
        if (StrUtils.isStringValueOk(imageUrl)) {
            helper.setGone(R.id.order_operate_item_rv, true);
            GridLayoutManager manager = new GridLayoutManager(mContext, 3) {
                @Override
                public boolean canScrollVertically() {
                    return false;
                }
            };
            NoticeDetailPicDisplayAdapter adapter = new NoticeDetailPicDisplayAdapter(R.layout.notice_detail_pics_display_item);
            adapter.setOnItemChildClickListener(new OnItemChildClickListener() {
                @Override
                public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                    new DisplayPhotosActivity().startDisplayPhotosActivity(mContext, imageUrl, position);
                }
            });
            recyclerView.setLayoutManager(manager);
            recyclerView.setAdapter(adapter);
            if (imageUrl.contains(",")) {
                String[] pics = imageUrl.split(",");
                adapter.setNewData(Arrays.asList(pics));
            } else {
                List<String> arrays = new ArrayList<>();
                arrays.add(imageUrl);
                adapter.setNewData(arrays);
            }

        } else {
            helper.setGone(R.id.order_operate_item_rv, false);

        }
    }
}
