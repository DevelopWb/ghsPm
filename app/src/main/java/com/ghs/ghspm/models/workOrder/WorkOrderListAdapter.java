package com.ghs.ghspm.models.workOrder;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ghs.ghspm.R;
import com.ghs.ghspm.bean.WorkOrderBean;
import com.ghs.ghspm.tools.StrUtils;

/**
 * Author:wang_sir
 * Time:2019/5/23 15:58
 * Description:This is WorkOrderListAdapter
 */
public class WorkOrderListAdapter extends BaseQuickAdapter<WorkOrderBean.DataBean, BaseViewHolder> {
    private boolean dividerTopShow;

    public WorkOrderListAdapter(int layoutResId,boolean dividerTopShow) {
        super(layoutResId);
        this.dividerTopShow = dividerTopShow;
    }

    @Override
    protected void convert(BaseViewHolder helper, WorkOrderBean.DataBean item) {
        //服务类型 5:报事、1:报修、2:投诉、3:建议、6:其他
        int type = item.getServiceType();
        int position = helper.getAdapterPosition();
        if (!dividerTopShow) {
            //一键呼叫
            if (position == 0) {
                helper.setGone(R.id.work_order_divider_v, false);
            } else {
                helper.setGone(R.id.work_order_divider_v, true);
            }
        } else {
            //我的工单中 顶部的分割线需要显示
            helper.setGone(R.id.work_order_divider_v, true);
        }

        String orderName =getOrderTypeName(type);
        helper.setText(R.id.order_type_tv, orderName);
        helper.setText(R.id.feedback_time_tv, item.getCreateTime());
        helper.setText(R.id.order_content_tv, item.getContent());
        String repairPeople = item.getRepairUserName();
        repairPeople = StrUtils.isStringValueOk(repairPeople) ? "跟进人：" + repairPeople : "跟进人：暂无跟进人";
        helper.setText(R.id.processing_person_tv, repairPeople);
        helper.setText(R.id.order_type_value_tv, getStatus(item.getState()));

    }

    /**
     * 获取工单类型
     * @param type
     * @return        //报修类型 1:家庭报修、4:公共报修、2:投诉、3:建议、5:报事、6:其他
     */
    private String getOrderTypeName(int type) {
        String  name = "";
        switch (type) {
            case 1:
                name = "家庭报修工单";
                break;
            case 2:
                name = "投诉工单";
                break;
            case 3:
                name = "建议工单";
                break;
            case 4:
                name = "公共报修工单";
                break;
            case 5:
                name = "报事工单";
                break;
            case 6:
                name = "其他工单";
                break;
            default:
                name = "工单";
                break;
        }
        return name;
    }

    /**
     * 获取状态名
     *
     * @param status  状态1.跟进中2.已完成 3:待跟进 4:待验收
     * @return
     */
    private String getStatus(int status) {
        String statusName = "";
        switch (status) {
            case 1:
                statusName = "跟进中";
                break;
            case 2:
                statusName = "已完成";
                break;
            case 3:
                statusName = "待跟进";
                break;
            case 4:
                statusName = "待验收";
                break;
            default:
                break;
        }
        return statusName;
    }
}
