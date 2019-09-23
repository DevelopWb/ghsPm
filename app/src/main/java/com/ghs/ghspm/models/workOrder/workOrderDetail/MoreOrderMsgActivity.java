package com.ghs.ghspm.models.workOrder.workOrderDetail;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ghs.ghspm.R;
import com.ghs.ghspm.base.BaseActivity;
import com.ghs.ghspm.bean.KeyValueBean;
import com.ghs.ghspm.bean.WorkOrderDetailBean;
import com.ghs.ghspm.models.workOrder.WorkOrderContract;
import com.ghs.ghspm.models.workOrder.WorkOrderPresent;

import java.util.ArrayList;
import java.util.List;

/**
 * created by tobato
 * created date 2019/7/8 18:29.
 * application  更多工单信息
 */
public class MoreOrderMsgActivity extends BaseActivity<WorkOrderContract.IWorkOrderView, WorkOrderPresent> implements WorkOrderContract.IWorkOrderView, View.OnClickListener {

    private RecyclerView mMoreOrderMsgRv;
    private WorkOrderDetailAdapter workOrderDetailAdapter;


    @Override
    public WorkOrderPresent creatPresenter() {
        return new WorkOrderPresent();

    }

    @Override
    public void setLayout() {
        setContentView(R.layout.activity_more_order_msg);
        initView();
        initActionBar("更多信息", null,R.drawable.bg_gray_only_bottom_deeper_gray_shape_1px);
    }

    @Override
    public void initLayoutView() {

    }

    @Override
    public void getDate() {
        WorkOrderDetailBean.DataBean.ServiceWorkDOBean dataBean = (WorkOrderDetailBean.DataBean.ServiceWorkDOBean) getIntentParcelableData();
        if (dataBean != null) {
            workOrderDetailAdapter.setNewData(getWorkOrderDetailData(dataBean));
        }
    }

    @Override
    public void actionBarRightTvOnClick() {

    }

    private void initView() {
        mMoreOrderMsgRv = (RecyclerView) findViewById(R.id.more_order_msg_rv);
        workOrderDetailAdapter = new WorkOrderDetailAdapter(R.layout.work_order_detail_item, true);
        initRecyclerview(mMoreOrderMsgRv, workOrderDetailAdapter, LinearLayoutManager.VERTICAL, false);
    }

    private List<KeyValueBean> getWorkOrderDetailData(WorkOrderDetailBean.DataBean.ServiceWorkDOBean dataBean) {
        List<KeyValueBean> arrays = new ArrayList<>();
        arrays.add(new KeyValueBean("验收人", dataBean.getCheckUserName()));
        arrays.add(new KeyValueBean("抄送人", dataBean.getCcUserName()));
        arrays.add(new KeyValueBean("报修类型", getOrderTypeName(dataBean.getServiceType())));
        arrays.add(new KeyValueBean("报修标签", dataBean.getServiceLabel()));
        return arrays;
    }
    /**
     * 获取工单类型
     * @param type
     * @return        报修类型 1:家庭报修、4:公共报修、2:投诉、3:建议、5:报事、6:其他
     */
    private String getOrderTypeName(int type) {
        String  name = "";
        switch (type) {
            case 1:
                name = "家庭报修";
                break;
            case 2:
                name = "投诉工单";
                break;
            case 3:
                name = "建议工单";
                break;
            case 4:
                name = "公共报修";
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
    @Override
    public void onClick(View v) {

    }

    @Override
    public void startLoading(String tag) {

    }

    @Override
    public void stopLoading(String tag) {

    }

    @Override
    public void updateView(Object o, String tag) {

    }

    @Override
    public void onError(String tag) {

    }
}
