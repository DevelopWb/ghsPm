package com.ghs.ghspm.models.workOrder.workOrderDetail;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.allen.library.SuperTextView;
import com.ghs.ghspm.R;
import com.ghs.ghspm.base.BaseActivity;
import com.ghs.ghspm.base.displayPhotos.DisplayPhotosActivity;
import com.ghs.ghspm.bean.CreateOrderBean;
import com.ghs.ghspm.bean.KeyValueBean;
import com.ghs.ghspm.bean.WorkOrderBean;
import com.ghs.ghspm.bean.WorkOrderDetailBean;
import com.ghs.ghspm.models.push.AliPushManager;
import com.ghs.ghspm.models.workOrder.WorkOrderContract;
import com.ghs.ghspm.models.workOrder.WorkOrderPresent;
import com.ghs.ghspm.models.workOrder.createOrder.CreateOrderActivity;
import com.ghs.ghspm.tools.ActivityResultManager;
import com.ghs.ghspm.tools.MaterialAlterDialogManager;
import com.ghs.ghspm.tools.PubUtil;
import com.ghs.ghspm.tools.StrUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * created by tobato
 * created date 2019/7/8 14:48.
 * application   工单详情
 */
public class WorkOrderDetailActivity extends BaseActivity<WorkOrderContract.IWorkOrderView, WorkOrderPresent> implements WorkOrderContract.IWorkOrderView, View.OnClickListener {

    private ImageView mHeaderLeftIv;
    /**
     * 设置
     */
    private TextView mHeaderTitleTv;
    private ImageView mWorkOrderMoreMsgIv;
    private ImageView mWorkOrderMoreOperateIv;
    /**
     * 工单内容
     */
    private TextView mWorkOrderContentTv;
    private RecyclerView mWorkOrderDetailRv;
    /**
     * 工单回执
     */
    private TextView mWorkOrderReceiptTv;
    private RecyclerView mWorkOrderDetailStatusRv;
    private BottomSheetDialog bottomSheetDialog;
    private WorkOrderDetailAdapter workOrderDetailAdapter;
    private SuperTextView mWorkOrderFinishStv;
    private SuperTextView mWorkOrderWriteProgressStv;
    /**
     * 查看
     */
    private TextView mWorkOlderLookPicsTv;
    private SuperTextView mWorkOrderAgreeStv;
    private SuperTextView mWorkOrderRejectStv;
    private OrderOprateLogAdapter orderOprateLogAdapter;
    private WorkOrderDetailBean.DataBean.ServiceWorkDOBean serviceWorkBean;
    private View mWorkOrderReceiptDividerV;
    private ConstraintLayout mWorkDetailRootViewCl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public WorkOrderPresent creatPresenter() {
        return new WorkOrderPresent();

    }

    @Override
    public void setLayout() {

    }

    @Override
    public void initLayoutView() {
        setContentView(R.layout.activity_work_order_detail);
        initView();
    }

    @Override
    public void getDate() {
        Intent intent = getIntent();
        showMaterialProgressDialog();
        if (intent != null) {
            String orderId = intent.getStringExtra(AliPushManager.PUSH_NOTICE_TYPE);
            if (orderId != null) {
                getPresenter().getWorkOrderDetail(mUserInfoUtil.getUserId(), Integer.parseInt(orderId), WorkOrderContract.WORK_ORDER_DETAIL);

            } else {
                WorkOrderBean.DataBean dataBean = (WorkOrderBean.DataBean) getIntentParcelableData();
                if (dataBean != null) {
                    getPresenter().getWorkOrderDetail(mUserInfoUtil.getUserId(), dataBean.getId(), WorkOrderContract.WORK_ORDER_DETAIL);
                }
            }
        }
    }

    @Override
    public void actionBarRightTvOnClick() {

    }


    @Override
    public void startLoading(String tag) {

    }

    @Override
    public void stopLoading(String tag) {

    }

    @Override
    public void updateView(Object o, String tag) {
        stopMaterialProgressDialog();
        switch (tag) {
            case WorkOrderContract.WORK_ORDER_DETAIL:
                //工单详情
                WorkOrderDetailBean workOrderDetailBean = (WorkOrderDetailBean) o;
                if (workOrderDetailBean != null) {
                    WorkOrderDetailBean.DataBean dataBean = workOrderDetailBean.getData();
                    if (dataBean != null) {
                        //工单基本信息
                        serviceWorkBean = dataBean.getServiceWorkDO();
                        if (serviceWorkBean != null) {
                            mWorkOrderContentTv.setText(serviceWorkBean.getContent());
                            workOrderDetailAdapter.setNewData(getWorkOrderDetailData(serviceWorkBean));
                            String images = serviceWorkBean.getImageUrl();
                            if (StrUtils.isStringValueOk(images)) {
                                mWorkOlderLookPicsTv.setVisibility(View.VISIBLE);
                            } else {
                                mWorkOlderLookPicsTv.setVisibility(View.GONE);
                            }
                        }
                        //工单种类：1:物业报修单 2:综合服务单  只有“物业报修工单”详情才有工单回执
                        int serviceClass = serviceWorkBean.getServiceClass();
                        if (1 == serviceClass) {
                            setViewsVisible(mWorkOrderReceiptTv, mWorkOrderReceiptDividerV);
                        } else {
                            setViewsInvisible(true, mWorkOrderReceiptTv, mWorkOrderReceiptDividerV);
                        }
                        //最下面的按钮
                        List<String> arrays = dataBean.getRoleButtonList();
                        mWorkOrderAgreeStv.setVisibility(View.GONE);
                        mWorkOrderRejectStv.setVisibility(View.GONE);
                        mWorkOrderWriteProgressStv.setVisibility(View.GONE);
                        if (arrays != null) {
                            if (arrays.size() > 0) {
                                if (arrays.contains("完成")) {
                                    mWorkOrderAgreeStv.setVisibility(View.VISIBLE);
                                    mWorkOrderAgreeStv.setCenterString("完成");
                                }
                                if (arrays.contains("写进展")) {
                                    mWorkOrderWriteProgressStv.setVisibility(View.VISIBLE);
                                }
                                if (arrays.contains("通过")) {
                                    mWorkOrderAgreeStv.setVisibility(View.VISIBLE);
                                    mWorkOrderRejectStv.setVisibility(View.VISIBLE);
                                    mWorkOrderAgreeStv.setCenterString("通过");
                                    mWorkOrderRejectStv.setCenterString("不通过");
                                }
                            }
                        }
                        //操作日志
                        List<WorkOrderDetailBean.DataBean.OpertionLogListBean> logListBeanList = dataBean.getOpertionLogList();
                        if (logListBeanList != null) {
                            orderOprateLogAdapter.setNewData(logListBeanList);
                        }
                    }
                }

                break;
            case WorkOrderContract.WORK_ORDER_DETAIL_FINISH:
                showToast("已完成");
                onBackPressed();
                break;
            case WorkOrderContract.WORK_ORDER_DETAIL_AGREE:
                showToast("已同意");
                onBackPressed();
                break;
            case WorkOrderContract.WORK_ORDER_DETAIL_REJECT:
                showToast("已拒绝");
                onBackPressed();
                break;
            case WorkOrderContract.DELETE_WORK_ORDER:
                showToast("已删除");
                onBackPressed();
                break;
            case WorkOrderContract.ACTIVITE_WORK_ORDER:
                showToast("已激活");
                //更新下订单
                getPresenter().getWorkOrderDetail(mUserInfoUtil.getUserId(), serviceWorkBean.getId(), WorkOrderContract.WORK_ORDER_DETAIL);

                break;
            default:
                break;
        }
    }

    @Override
    public void onError(String tag) {

    }

    private void initView() {
        mHeaderLeftIv = (ImageView) findViewById(R.id.header_left_iv);
        mHeaderLeftIv.setOnClickListener(this);
        mHeaderTitleTv = (TextView) findViewById(R.id.header_title_tv);
        mWorkOrderMoreMsgIv = (ImageView) findViewById(R.id.work_order_more_msg_iv);
        mWorkOrderMoreMsgIv.setOnClickListener(this);
        mWorkOrderMoreOperateIv = (ImageView) findViewById(R.id.work_order_more_operate_iv);
        mWorkOrderMoreOperateIv.setOnClickListener(this);
        mWorkOrderContentTv = (TextView) findViewById(R.id.work_order_content_tv);
        mWorkOrderDetailRv = (RecyclerView) findViewById(R.id.work_order_detail_rv);
        workOrderDetailAdapter = new WorkOrderDetailAdapter(R.layout.work_order_detail_item, false);
        workOrderDetailAdapter.setEmptyView(getAdapterEmptyView("暂无数据"));
        initRecyclerview(mWorkOrderDetailRv, workOrderDetailAdapter, LinearLayoutManager.VERTICAL, false, true);
        mWorkOrderReceiptTv = (TextView) findViewById(R.id.work_order_receipt_tv);
        mWorkOrderReceiptTv.setOnClickListener(this);
        mWorkOrderDetailStatusRv = (RecyclerView) findViewById(R.id.work_order_detail_status_rv);
        orderOprateLogAdapter = new OrderOprateLogAdapter(R.layout.order_operate_log_item);
        initRecyclerview(mWorkOrderDetailStatusRv, orderOprateLogAdapter, LinearLayoutManager.VERTICAL, false, true);
        orderOprateLogAdapter.setEmptyView(getAdapterEmptyView("暂无进展"));
        mWorkOrderFinishStv = (SuperTextView) findViewById(R.id.work_order_agree_stv);
        mWorkOrderFinishStv.setOnClickListener(this);
        mWorkOrderWriteProgressStv = (SuperTextView) findViewById(R.id.work_order_write_progress_stv);
        mWorkOrderWriteProgressStv.setOnClickListener(this);
        mWorkOlderLookPicsTv = (TextView) findViewById(R.id.work_older_look_pics_tv);
        mWorkOlderLookPicsTv.setOnClickListener(this);
        mWorkOrderAgreeStv = (SuperTextView) findViewById(R.id.work_order_agree_stv);
        mWorkOrderAgreeStv.setOnClickListener(this);
        mWorkOrderRejectStv = (SuperTextView) findViewById(R.id.work_order_reject_stv);
        mWorkOrderRejectStv.setOnClickListener(this);
        mWorkOrderReceiptDividerV = (View) findViewById(R.id.work_order_receipt_divider_v);
        mWorkDetailRootViewCl = (ConstraintLayout) findViewById(R.id.work_detail_root_view_cl);
    }

    /**
     * 获取适配器数据
     *
     * @return
     */
    private List<KeyValueBean> getWorkOrderDetailData(WorkOrderDetailBean.DataBean.ServiceWorkDOBean dataBean) {
        int serviceType = dataBean.getServiceType();
        List<KeyValueBean> arrays = new ArrayList<>();
        arrays.add(new KeyValueBean("跟进人", dataBean.getRepairUserName()));
        arrays.add(new KeyValueBean("联系人姓名", dataBean.getOwnerName()));
        arrays.add(new KeyValueBean("联系电话", dataBean.getMobile()));
        arrays.add(new KeyValueBean("房间号", dataBean.getPortionName()+dataBean.getTowerNumber()+dataBean.getCellName()+dataBean.getRoomNumber()));
        if (1 == serviceType) {
            //家庭报修工单有期望上门时间
            String hopeTime = dataBean.getHopeGotoTime();
            if (!"尽快上门".equals(hopeTime)) {
                hopeTime = hopeTime.substring(5, hopeTime.length());
            }
            arrays.add(new KeyValueBean("期望上门时间", hopeTime));
        }
        return arrays;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.header_left_iv:
                onBackPressed();
                break;
            case R.id.work_order_more_msg_iv:
                //更多信息
                if (serviceWorkBean != null) {
                    startActivityWithParcelableData(serviceWorkBean, MoreOrderMsgActivity.class);
                }
                break;
            case R.id.work_order_more_operate_iv:
                //更多操作
                bottomSheetDialog = new BottomSheetDialog(this);
                bottomSheetDialog.setContentView(R.layout.operate_work_order_layout);
                bottomSheetDialog.findViewById(R.id.activate_order_tv).setOnClickListener(this);
                bottomSheetDialog.findViewById(R.id.operate_order_edit_tv).setOnClickListener(this);
                bottomSheetDialog.findViewById(R.id.operate_order_delete_tv).setOnClickListener(this);
                bottomSheetDialog.findViewById(R.id.operate_order_cancel_tv).setOnClickListener(this);

                // 2. 编辑：已完成和待验收状态不可编辑并且拥有此按钮权限
                //3.激活工单：完成状态下可激活工单并且拥有此按钮权限
                //状态1.跟进中2.已完成 3:待跟进 4:待验收
                if (!PubUtil.ORDER_OPERATE_PROMISSION.contains("激活工单")) {
                    bottomSheetDialog.findViewById(R.id.activate_order_tv).setVisibility(View.GONE);
                } else {
                    if (2 != serviceWorkBean.getState()) {
                        bottomSheetDialog.findViewById(R.id.activate_order_tv).setVisibility(View.GONE);
                    }
                }
                if (!PubUtil.ORDER_OPERATE_PROMISSION.contains("编辑工单")) {
                    bottomSheetDialog.findViewById(R.id.operate_order_edit_tv).setVisibility(View.GONE);
                } else {
                    if (2 == serviceWorkBean.getState() || 4 == serviceWorkBean.getState()) {
                        bottomSheetDialog.findViewById(R.id.operate_order_edit_tv).setVisibility(View.GONE);
                    }
                }
                if (!PubUtil.ORDER_OPERATE_PROMISSION.contains("删除工单")) {
                    bottomSheetDialog.findViewById(R.id.operate_order_delete_tv).setVisibility(View.GONE);
                }
                bottomSheetDialog.setCanceledOnTouchOutside(false);

                bottomSheetDialog.show();
                break;
            case R.id.work_order_receipt_tv:
                //工单回执
                if (!PubUtil.ORDER_OPERATE_PROMISSION.contains("工单回执")) {
                    showToast("暂无权限");
                    return;
                }
                Intent intent_receipt = new Intent(this, WorkOrderReceiptActivity.class);
                intent_receipt.putExtra(INTENT_KEY, serviceWorkBean);
                startActivityForResult(intent_receipt, ActivityResultManager.WORK_ORDER_DETAIL_RECEIPT);

                break;
            case R.id.activate_order_tv:
                //激活工单

                //激活工单：完成状态下可激活工单并且拥有此按钮权限
                    dismissDialog(bottomSheetDialog);
                    showMaterialProgressDialog();
                    getPresenter().activateWorkOrder(mUserInfoUtil.getUserId(), mUserInfoUtil.getUserName(), serviceWorkBean.getId(), WorkOrderContract.ACTIVITE_WORK_ORDER);
                break;
            case R.id.operate_order_edit_tv:
                //编辑工单     编辑：已完成和待验收状态不可编辑并且拥有此按钮权限
                updateOrder();


                break;
            case R.id.operate_order_delete_tv:
                TextView textView = (TextView) v;

                //删除工单
                MaterialAlterDialogManager.getInstance().showMaterialAlterDialog(this, "确定要删除此工单吗？", "确定", "取消", new MaterialAlterDialogManager.OnAlterDialogCallBack() {
                    @Override
                    public void leftBtClicked() {
//删除工单
                            showMaterialProgressDialog();
                            getPresenter().deleteWorkOrder(serviceWorkBean.getId(), WorkOrderContract.DELETE_WORK_ORDER);
                    }

                    @Override
                    public void rightBtClicked() {

                    }
                });
                break;
            case R.id.operate_order_cancel_tv:
                //取消
                dismissBottomSheetDialog(bottomSheetDialog);
                break;
            case R.id.work_order_agree_stv:
                //完成或者 通过
                String text = mWorkOrderAgreeStv.getCenterString();
                if ("完成".equals(text)) {
                    getPresenter().finishWorkOrder(mUserInfoUtil.getUserId(), mUserInfoUtil.getUserName(), serviceWorkBean.getId(), WorkOrderContract.WORK_ORDER_DETAIL_FINISH);
                } else {
                    getPresenter().agreeWorkOrder(mUserInfoUtil.getUserId(), mUserInfoUtil.getUserName(), serviceWorkBean.getId(), WorkOrderContract.WORK_ORDER_DETAIL_AGREE);

                }
                break;
            case R.id.work_order_write_progress_stv:
                //写进展
                PubUtil.LAYOUT_TYPE = 1;
                Intent intent_progress = new Intent(this, WriteProgressActivity.class);
                intent_progress.putExtra(INTENT_KEY, serviceWorkBean);
                startActivityForResult(intent_progress, ActivityResultManager.WORK_ORDER_DETAIL_WRITE_PROGRESS);
                break;
            case R.id.work_older_look_pics_tv:
                //查看图片
                if (serviceWorkBean != null) {
                    new DisplayPhotosActivity().startDisplayPhotosActivity(WorkOrderDetailActivity.this, serviceWorkBean.getImageUrl(), 0);
                }
                break;
            case R.id.work_order_reject_stv:
                //不通过
                getPresenter().rejectWorkOrder(mUserInfoUtil.getUserId(), mUserInfoUtil.getUserName(), serviceWorkBean.getId(), WorkOrderContract.WORK_ORDER_DETAIL_REJECT);
                break;
        }
    }


    /**
     * 编辑工单
     */
    private void updateOrder() {

        Intent intent = new Intent(WorkOrderDetailActivity.this, CreateOrderActivity.class);
        CreateOrderBean createOrderBean = new CreateOrderBean();
        //编辑
        createOrderBean.setUpdateOrderState(true);

        if (serviceWorkBean != null) {

            switch (serviceWorkBean.getServiceClass()) {

                case 1:
                    //物业报修单
                    createOrderBean.setOrderType("物业报修单");
                    //物业报修类型
                    createOrderBean.setRepairsType(selectType(serviceWorkBean.getServiceType()));
                    //物业报修标签
                    createOrderBean.setRepairsLabel(serviceWorkBean.getServiceLabel());

                    break;

                case 2:
                    //综合服务单
                    createOrderBean.setOrderType("综合服务单");
                    //综合服务类型
                    createOrderBean.setServeType(selectType(serviceWorkBean.getServiceType()));
                    //物业报修标签
                    createOrderBean.setRepairsLabel(serviceWorkBean.getServiceLabel());


                    break;

            }
            //反馈内容
            createOrderBean.setOrderContent(serviceWorkBean.getContent());
            //反馈图片
            createOrderBean.setOrderImage(serviceWorkBean.getImageUrl());
            //联系人姓名
            createOrderBean.setOrderName(serviceWorkBean.getOwnerName());
            //联系人电话
            createOrderBean.setOrderMobile(serviceWorkBean.getMobile());
            //房间号
            createOrderBean.setOrderRoom(serviceWorkBean.getRoomNumber());
            //房间id
            createOrderBean.setOrderRoomId(serviceWorkBean.getRoomId() + "");
            //跟进人
            createOrderBean.setFollowPeople(serviceWorkBean.getRepairUserName());
            createOrderBean.setFollowPeopleId(serviceWorkBean.getRepairUserId());
            //负责人
            createOrderBean.setCheckPeople(serviceWorkBean.getCheckUserName());
            createOrderBean.setCheckPeopleId(serviceWorkBean.getCheckUserId() + "");
            //抄送人
            createOrderBean.setCopyPeople(serviceWorkBean.getCcUserName());
            createOrderBean.setCopyPeopleId(serviceWorkBean.getCcUserId() + "");
            //上门时间
            createOrderBean.setOrderHopeGotoTime(serviceWorkBean.getHopeGotoTime());
            //工单id
            createOrderBean.setOrderId(serviceWorkBean.getId() + "");
        }
        intent.putExtra(PubUtil.UPADAT_ORDER, createOrderBean);
        startActivityForResult(intent, ActivityResultManager.UPDATE_ORDER_COMPLETE);
        bottomSheetDialog.dismiss();

    }

    /**
     * 报修类型 1:家庭报修、4:公共报修、2:投诉、3:建议、5:报事、6:其他
     *
     * @param type
     * @return
     */
    public String selectType(int type) {
        String i = "";

        switch (type) {
            case 1:
                i = "家庭报修";
                break;
            case 4:
                i = "公共报修";
                break;

            case 2:
                i = "投诉";
                break;
            case 3:
                i = "建议";
                break;

            case 5:
                i = "报事";
                break;

            case 6:
                i = "其他";
                break;

        }

        return i;

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ActivityResultManager.WORK_ORDER_DETAIL_WRITE_PROGRESS || requestCode == ActivityResultManager.WORK_ORDER_DETAIL_RECEIPT || resultCode == ActivityResultManager.UPDATE_ORDER_COMPLETE) {
            if (serviceWorkBean != null) {
                getPresenter().getWorkOrderDetail(mUserInfoUtil.getUserId(), serviceWorkBean.getId(), WorkOrderContract.WORK_ORDER_DETAIL);
            }
        }
    }

    @Override
    public void onBackPressed() {
        setResult(ActivityResultManager.WORK_ORDER_DETAIL);
        super.onBackPressed();
    }
}
