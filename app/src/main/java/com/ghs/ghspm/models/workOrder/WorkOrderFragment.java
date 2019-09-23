package com.ghs.ghspm.models.workOrder;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ghs.ghspm.R;
import com.ghs.ghspm.base.BaseFragment;
import com.ghs.ghspm.base.network.RequestStatus;
import com.ghs.ghspm.bean.MenuBean;
import com.ghs.ghspm.bean.MultiWorkDeskMenuBean;
import com.ghs.ghspm.bean.WorkOrderBean;
import com.ghs.ghspm.bean.WorkOrderTypeBean;
import com.ghs.ghspm.bean.WorkOrderUnreadBean;
import com.ghs.ghspm.models.login.LoginModel;
import com.ghs.ghspm.models.main.MainActivity;
import com.ghs.ghspm.models.main.MainContact;
import com.ghs.ghspm.models.workOrder.createOrder.CreateOrderActivity;
import com.ghs.ghspm.models.workOrder.workOrderDetail.WorkOrderDetailActivity;
import com.ghs.ghspm.tools.ActivityResultManager;
import com.ghs.ghspm.tools.HawkProperty;
import com.ghs.ghspm.tools.PubUtil;
import com.ghs.ghspm.tools.UserInfoUtil;
import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * created by tobato
 * created date 2019/7/5 11:01.
 * application   工单模块
 * <p>
 * 小红点的逻辑
 * 后台切换前台的时候 请求小红点数，如果当前工单的类型和未读小红点工单的类型一致，不显示小红点，刷新数据。如果不一致，显示底部小红点。
 * 收到推送的消息的时候，请求小红点数，
 * if（工单fragment显示状态){WORK_ORDER_DETAIL
 * if(当前工单的类型和未读小红点工单的类型一致){
 * 不显示小红点，刷新数据
 * }else{
 * 显示底部小红点
 * }
 * }else if（工单fragment隐藏状态）{
 * 显示底部小红点
 * }
 */

public class WorkOrderFragment extends BaseFragment<WorkOrderContract.IWorkOrderView, WorkOrderPresent> implements WorkOrderContract.IWorkOrderView, RequestStatus, View.OnClickListener {

    private MainContact.DrawerLayoutCallBack drawerLayoutCallBack;
    private PopupWindow popupWindow;
    /**
     * 测试小区的长度是多少
     */
    private TextView mVilliageNameTv;
    private View view;
    /**
     * 带我跟进
     */
    private TextView mWorkOrderTypeTv;
    private RecyclerView mWorkOrderRv;
    private ImageView mCreatOrderIv;
    private ConstraintLayout mWorkOrderTypeCl;
    public static String REPAIRS = "repairs";//报修服务
    public static String SERVE = "serve";//综合服务
    public static String REDACT = "redact";//编辑
    private ConstraintLayout mVillageMsgCl;
    private int offset = 0;
    private int limit = 5;
    private WorkOrderListAdapter adapter;
    private SwipeRefreshLayout mWorkOlderRf;
    private int unreadNumCc = 0;//抄送我的  未读数
    private int unreadNumCheck = 0;//待验收的  未读数
    private int unreadNumIng = 0;//待跟进的 未读数
    private int unreadNumTotal = 0;//总共的  未读数

    private boolean isFirstLoad = true;//第一次进入



    @Override
    protected WorkOrderPresent createPresenter() {
        return new WorkOrderPresent();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (ActivityResultManager.PUBLISH_TASK == resultCode) {
            super.onActivityResult(requestCode, resultCode, data);
        }
        if (ActivityResultManager.WORK_ORDER_DETAIL == resultCode) {
            lazyLoad();
        }

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layoutview = inflater.inflate(R.layout.work_order_fragment, container, false);
        initView(layoutview);
        return layoutview;
    }

    @Override
    protected void lazyLoad() {
        Log.d("lazyLoad", "lazyLoad---------------" + TAG);
        offset = 0;
        if (mVilliageNameTv != null) {
            mVilliageNameTv.setText(UserInfoUtil.getInstance().getVillageName());
        }
        String text = mWorkOrderTypeTv != null ? mWorkOrderTypeTv.getText().toString().trim() : "待我跟进";
        getOrdersData(text);
        new LoginModel().getUserMenu(UserInfoUtil.getInstance().getPropertyId(), UserInfoUtil.getInstance().getVillageId(), UserInfoUtil.getInstance().getUserId(), MainContact.USER_MENU, this);
        getPresenter().getWorkOrderUnreadNum(mUserInfoUtil.getUserId(), mUserInfoUtil.getVillageId(), WorkOrderContract.GET_WORK_ORDER_UNREAD_NUM);
    }


    private void initView(View layoutview) {
        mVilliageNameTv = (TextView) layoutview.findViewById(R.id.villiage_name_tv);
        mVilliageNameTv.setText(UserInfoUtil.getInstance().getVillageName());
        mWorkOrderTypeTv = (TextView) layoutview.findViewById(R.id.work_order_type_tv);
        mWorkOrderRv = (RecyclerView) layoutview.findViewById(R.id.work_order_rv);
        adapter = new WorkOrderListAdapter(R.layout.work_order_list_item, true);
        initRecyclerview(mWorkOrderRv, adapter, LinearLayoutManager.VERTICAL, false);
        mWorkOlderRf = (SwipeRefreshLayout) layoutview.findViewById(R.id.work_older_rf);
        adapter.setEmptyView(getAdapterEmptyView("暂无数据"));
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                WorkOrderBean.DataBean dataBean = (WorkOrderBean.DataBean) adapter.getData().get(position);
                Intent intent = new Intent(getContext(), WorkOrderDetailActivity.class);
                intent.putExtra(getBaseActivity().INTENT_KEY, dataBean);
                startActivityForResult(intent, ActivityResultManager.WORK_ORDER_DETAIL);
            }
        });
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                getOrdersData(mWorkOrderTypeTv.getText().toString().trim());
            }
        }, mWorkOrderRv);
        mWorkOlderRf.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                offset = 0;
                adapter.setEnableLoadMore(false);//这里的作用是防止下拉刷新的时候还可以上拉加载
                mWorkOlderRf.setRefreshing(true);
                getOrdersData(mWorkOrderTypeTv.getText().toString().trim());

            }
        });
        mCreatOrderIv = (ImageView) layoutview.findViewById(R.id.creat_order_iv);
        mCreatOrderIv.setOnClickListener(this);
        if (PubUtil.CAN_PUBLISH_TASK) {
            mCreatOrderIv.setVisibility(View.VISIBLE);
        } else {
            mCreatOrderIv.setVisibility(View.GONE);
        }
        mWorkOrderTypeCl = (ConstraintLayout) layoutview.findViewById(R.id.work_order_type_cl);
        mWorkOrderTypeCl.setOnClickListener(this);
        mVillageMsgCl = (ConstraintLayout) layoutview.findViewById(R.id.village_msg_cl);
        mVillageMsgCl.setOnClickListener(this);
    }

    @Override
    protected void villageChanged() {
        lazyLoad();
        super.villageChanged();
    }

    @Override
    protected void receivedOrderMsg() {
        lazyLoad();
        super.receivedOrderMsg();
    }

    @Override
    protected void initFragmentData() {

    }

    @Override
    public void onResume() {
        super.onResume();
        if (!isFirstLoad) {
            new LoginModel().getUserMenu(UserInfoUtil.getInstance().getPropertyId(), UserInfoUtil.getInstance().getVillageId(), UserInfoUtil.getInstance().getUserId(), MainContact.USER_MENU, this);
            getPresenter().getWorkOrderUnreadNum(mUserInfoUtil.getUserId(), mUserInfoUtil.getVillageId(), WorkOrderContract.GET_WORK_ORDER_UNREAD_NUM);
        } else {
            isFirstLoad = false;
        }

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void initFragmentView(View view) {
    }

    @Override
    protected View initFragmentViewLayout(LayoutInflater inflater, ViewGroup container) {
        return null;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.drawerLayoutCallBack = (MainContact.DrawerLayoutCallBack) context;
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onStop() {
        super.onStop();

    }

    @Override
    public void startLoading(String tag) {

    }

    @Override
    public void stopLoading(String tag) {


    }

    @Override
    public void updateView(Object o, String tag) {
        switch (tag) {
            case WorkOrderContract.GET_WORK_ORDER_UNREAD_NUM:
                dealUnReadWorkOrder((WorkOrderUnreadBean) o);
                break;
            case WorkOrderContract.DEL_WORK_ORDER_UNREAD_NUM:
                dealUnReadWorkOrder((WorkOrderUnreadBean) o);
                break;
            default:
                mWorkOlderRf.setRefreshing(false);
                WorkOrderBean workOrderBean = (WorkOrderBean) o;
                boolean isFresh = RequestStatus.REFRESH.equals(tag) ? true : false;
                if (workOrderBean != null) {
                    List<WorkOrderBean.DataBean> arrays = workOrderBean.getData();
                    if (arrays != null) {
                        offset += arrays.size();
                        if (isFresh) {
                            adapter.setNewData(arrays);
                        } else {
                            adapter.addData(arrays);
                        }
                        if (arrays.size() < limit) {
                            //第一页如果不够一页就不显示没有更多数据布局
                            adapter.loadMoreEnd(isFresh);
                        } else {
                            adapter.loadMoreComplete();
                        }
                    }
                }
                break;
        }

    }

    /**
     * 处理未读工单数  获取或者删除
     *
     * @param o
     */
    private void dealUnReadWorkOrder(WorkOrderUnreadBean o) {
        //工单未读数
        WorkOrderUnreadBean workOrderUnreadBean = o;
        if (workOrderUnreadBean != null) {
            WorkOrderUnreadBean.DataBean dataBean = workOrderUnreadBean.getData();
            if (dataBean != null) {
                unreadNumCc = dataBean.getUnreadNumCc();
                unreadNumCheck = dataBean.getUnreadNumCheck();
                unreadNumIng = dataBean.getUnreadNumIng();
                unreadNumTotal = dataBean.getUnreadNumTotal();
            }

            String typeName = mWorkOrderTypeTv.getText().toString().trim();
            if (unreadNumCc > 0) {
                if ("抄送我的".equals(typeName)) {
                    if (!isHidden()) {
                        getPresenter().delWorkOrderUnreadNum(mUserInfoUtil.getUserId(), mUserInfoUtil.getVillageId(), 3, WorkOrderContract.DEL_WORK_ORDER_UNREAD_NUM);
                    }
                }
            }
            if (unreadNumCheck > 0) {
                if ("要我验收".equals(typeName)) {
                    if (!isHidden()) {
                        getPresenter().delWorkOrderUnreadNum(mUserInfoUtil.getUserId(), mUserInfoUtil.getVillageId(), 2, WorkOrderContract.DEL_WORK_ORDER_UNREAD_NUM);
                    }
                }
            }
            if (unreadNumIng > 0) {
                if ("待我跟进".equals(typeName)) {
                    if (!isHidden()) {
                        getPresenter().delWorkOrderUnreadNum(mUserInfoUtil.getUserId(), mUserInfoUtil.getVillageId(), 1, WorkOrderContract.DEL_WORK_ORDER_UNREAD_NUM);
                    }
                }
            }
            if (unreadNumTotal > 0) {
                ((MainActivity) getActivity()).mUnreadAmountTv.setVisibility(View.VISIBLE);
                ((MainActivity) getActivity()).mUnreadAmountTv.setText(String.valueOf(unreadNumTotal));
            } else {
                ((MainActivity) getActivity()).mUnreadAmountTv.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void onError(String tag) {

        getBaseActivity().showNormalToast(tag);
        mWorkOlderRf.setRefreshing(false);

    }

    @Override
    public void onStart(String tag) {

    }

    @Override
    public void onSuccess(Object o, String tag) {
        MenuBean menuBean = (MenuBean) o;
        if (menuBean != null) {
            List<MenuBean.DataBean> dataBean = menuBean.getData();
            PubUtil.CAN_PUBLISH_TASK = false;
            PubUtil.CAN_PUBLISH_NOTICE = false;
            if (dataBean != null) {
                List<MultiWorkDeskMenuBean> adapterData = new LinkedList<>();
                if (dataBean.size() > 0) {
                    for (MenuBean.DataBean bean : dataBean) {
                        String name = bean.getName();
                        if (2 == bean.getApplicationType()) {
                            if (getWorkDeskMenueNames().contains(name)) {
                                adapterData.add(new MultiWorkDeskMenuBean(MultiWorkDeskMenuBean.FIXED_MENU, name));
                            }
                            if ("发布通知".equals(name)) {
                                PubUtil.CAN_PUBLISH_NOTICE = true;
                            }
                            if ("发布任务".equals(name)) {
                                PubUtil.CAN_PUBLISH_TASK = true;
                            }
                            if ("工单中心".equals(name)) {
                                List<MenuBean.DataBean> orderDatas = bean.getNextLevelMenuList();
                                if (orderDatas != null) {
                                    if (orderDatas.size() > 0) {
                                        PubUtil.ORDER_OPERATE_PROMISSION.clear();
                                        for (MenuBean.DataBean orderData : orderDatas) {
                                            String orderOperateName = orderData.getName();
                                            PubUtil.ORDER_OPERATE_PROMISSION.add(orderOperateName);
                                        }
                                    }
                                }
                            }
                        }
                    }
                    Hawk.put(HawkProperty.WORK_DESK_MENU, adapterData);
                } else {
                    Hawk.put(HawkProperty.WORK_DESK_MENU, adapterData);

                }
            }
        }
        if (PubUtil.CAN_PUBLISH_TASK) {
            mCreatOrderIv.setVisibility(View.VISIBLE);
        } else {
            mCreatOrderIv.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.work_order_status_shadow_v:
                if (popupWindow.isShowing()) {
                    popupWindow.dismiss();
                }
                break;
            case R.id.creat_order_iv:
                //创建工单
                createOrderType();
                break;
            case R.id.work_order_type_cl:
                popupWindow = showOrderTypes();
                popupWindow.showAsDropDown(v);
                break;
            case R.id.village_msg_cl:
                //点击告诉MainActivity弹出左侧窗口
                if (drawerLayoutCallBack != null) {
                    drawerLayoutCallBack.openDrawerLayout();
                }
                break;
        }
    }

    //创建
    public void createOrderType() {

        View view = View.inflate(getActivity(), R.layout.create_order_popuwindow_layout, null);
        LinearLayout mLine = view.findViewById(R.id.mLine);
        //报修服务
        LinearLayout order_type_repairs = view.findViewById(R.id.create_order_repairs_ly);
        //综合服务
        LinearLayout create_order_serve_ly = view.findViewById(R.id.create_order_serve_ly);
        //返回
        ImageView create_order_back = view.findViewById(R.id.create_order_back);

        final PopupWindow mPopu = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        mPopu.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
//        setBackgroundAlpha(0.3f);


        order_type_repairs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(getActivity(), CreateOrderActivity.class);
                intent.putExtra(ActivityResultManager.CREATE_ORDER, REPAIRS);
                startActivity(intent);
                mPopu.dismiss();
            }
        });

        create_order_serve_ly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), CreateOrderActivity.class);
                intent.putExtra(ActivityResultManager.CREATE_ORDER, SERVE);
                startActivity(intent);
                mPopu.dismiss();

            }
        });
        create_order_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mPopu.dismiss();

            }
        });


        mLine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPopu.dismiss();
            }
        });

        mPopu.setOutsideTouchable(true);//判断在外面点击是否有效
        mPopu.setFocusable(true);
        mPopu.showAsDropDown(view);
        mPopu.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                setBackgroundAlpha(1.0f);
            }
        });
    }




    /**
     * 展示和我相关的工单类型
     *
     * @return
     */
    private PopupWindow showOrderTypes() {

        View view = LayoutInflater.from(getContext()).inflate(R.layout.work_order_status, null);
        View shadowV = view.findViewById(R.id.work_order_status_shadow_v);
        shadowV.setOnClickListener(this);
        RecyclerView recyclerView = view.findViewById(R.id.work_order_status_rv);
        WorkOrderTypeAdapter adapter = new WorkOrderTypeAdapter(R.layout.work_order_type_item);
        initRecyclerview(recyclerView, adapter, LinearLayoutManager.VERTICAL, false);
        adapter.setNewData(getWorkOrderTypes());
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                WorkOrderTypeBean stringSelectedBean = (WorkOrderTypeBean) adapter.getData().get(position);
                mWorkOrderTypeTv.setText(stringSelectedBean.getContent());
                if (popupWindow.isShowing()) {
                    popupWindow.dismiss();
                }
                offset = 0;
                adapter.setEnableLoadMore(false);//这里的作用是防止下拉刷新的时候还可以上拉加载
                mWorkOlderRf.setRefreshing(true);
                String orderName = stringSelectedBean.getContent();
                getOrdersData(orderName);
            }
        });
        final PopupWindow pop = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        pop.setOutsideTouchable(true);
        pop.setFocusable(true);
        return pop;
    }

    /**
     * 获取工单数据
     *
     * @param orderName
     */
    private void getOrdersData(String orderName) {
        if (isHidden()) {
            return;
        }
        switch (orderName) {
            case "待我跟进":
                if (unreadNumIng > 0) {
                    getPresenter().delWorkOrderUnreadNum(mUserInfoUtil.getUserId(), mUserInfoUtil.getVillageId(), 1, WorkOrderContract.DEL_WORK_ORDER_UNREAD_NUM);

                }
                getPresenter().getWaitFollowedOrders(mUserInfoUtil.getUserId(), mUserInfoUtil.getVillageId(), offset, limit, WorkOrderContract.WAIT_FOLLOW_ORDER);
                break;
            case "我创建的":
                getPresenter().getICreatedOrders(mUserInfoUtil.getUserId(), mUserInfoUtil.getVillageId(), offset, limit, WorkOrderContract.I_CREATED_ORDER);
                break;
            case "抄送我的":
                if (unreadNumCc > 0) {
                    getPresenter().delWorkOrderUnreadNum(mUserInfoUtil.getUserId(), mUserInfoUtil.getVillageId(), 3, WorkOrderContract.DEL_WORK_ORDER_UNREAD_NUM);

                }
                getPresenter().getCopyedOrders(mUserInfoUtil.getUserId(), mUserInfoUtil.getVillageId(), offset, limit, WorkOrderContract.COPYED_TO_ME_ORDER);
                break;
            case "要我验收":
                if (unreadNumCheck > 0) {
                    getPresenter().delWorkOrderUnreadNum(mUserInfoUtil.getUserId(), mUserInfoUtil.getVillageId(), 2, WorkOrderContract.DEL_WORK_ORDER_UNREAD_NUM);

                }
                getPresenter().getWaitCheckOrders(mUserInfoUtil.getUserId(), mUserInfoUtil.getVillageId(), offset, limit, WorkOrderContract.WAIT_CHECK_ORDER);
                break;
            case "未指派的":
                getPresenter().getUnAssignedOrders(mUserInfoUtil.getVillageId(), offset, limit, WorkOrderContract.UNASSIGN_ORDER);
                break;
            case "已完成的":
                getPresenter().getFinishedOrders(mUserInfoUtil.getUserId(), mUserInfoUtil.getVillageId(), offset, limit, WorkOrderContract.FINISHED_ORDERS);
                break;
            default:
                break;
        }
    }

    /**
     * 获取和我相关的工单
     *
     * @return
     */
    private List<WorkOrderTypeBean> getWorkOrderTypes() {
        String selectedType = mWorkOrderTypeTv.getText().toString().trim();
        List<WorkOrderTypeBean> arrays = new ArrayList<>();
        String[] names = {"待我跟进", "我创建的", "抄送我的", "要我验收", "未指派的", "已完成的"};

        for (String name : names) {
            if (name.equals(selectedType)) {
                arrays.add(new WorkOrderTypeBean(name, true, 0));
            } else {
                arrays.add(new WorkOrderTypeBean(name, false, 0));
            }
        }
        for (WorkOrderTypeBean array : arrays) {
            String name = array.getContent();
            if ("待我跟进".equals(name)) {
                array.setUnReadAmount(unreadNumIng);
            }
            if ("抄送我的".equals(name)) {
                array.setUnReadAmount(unreadNumCc);
            }
            if ("要我验收".equals(name)) {
                array.setUnReadAmount(unreadNumCheck);
            }
        }
        return arrays;
    }
}
