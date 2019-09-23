package com.ghs.ghspm.models.workdesk;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ghs.ghspm.R;
import com.ghs.ghspm.base.BaseFragment;
import com.ghs.ghspm.base.network.RequestStatus;
import com.ghs.ghspm.bean.MenuBean;
import com.ghs.ghspm.bean.MultiWorkDeskMenuBean;
import com.ghs.ghspm.bean.ToolFormBean;
import com.ghs.ghspm.models.login.LoginContract;
import com.ghs.ghspm.models.login.LoginModel;
import com.ghs.ghspm.models.main.MainContact;
import com.ghs.ghspm.models.workdesk.arrangeShift.ArrangeShiftActivity;
import com.ghs.ghspm.models.workdesk.carnum.CarNumActivity;
import com.ghs.ghspm.models.workdesk.innerrepair.InnerRepairActivity;
import com.ghs.ghspm.models.workdesk.moretools.MoreToolsActivity;
import com.ghs.ghspm.models.workdesk.organizeframe.OrganizeFrameActivity;
import com.ghs.ghspm.models.workdesk.ownerinfo.OwnerInfoeActivity;
import com.ghs.ghspm.models.workdesk.patrolForm.PatrolCheckUpdateActivity;
import com.ghs.ghspm.models.workdesk.checkVisitor.CheckVisitorListActivity;
import com.ghs.ghspm.models.workdesk.publicfuction.notice.NoticeActivity;
import com.ghs.ghspm.models.workdesk.publicfuction.signIn.SignInActivity;
import com.ghs.ghspm.models.workdesk.publicfuction.universalkey.UniversalKeyActivity;
import com.ghs.ghspm.models.workdesk.signAndTable.SignAndTableActivity;
import com.ghs.ghspm.models.workdesk.signAndTable.tableTaskToDo.createForm.CreateFormActivity;
import com.ghs.ghspm.models.workdesk.usualtasksign.UsualTaskSignActivity;
import com.ghs.ghspm.models.workdesk.waterrecord.WaterRecordActivity;
import com.ghs.ghspm.tools.ActivityResultManager;
import com.ghs.ghspm.tools.HawkProperty;
import com.ghs.ghspm.tools.PubUtil;
import com.orhanobut.hawk.Hawk;

import java.util.LinkedList;
import java.util.List;

/**
 * created by tobato
 * created date 2019/7/10 14:53.
 * application   工作台
 */
public class WorkDeskFragment extends BaseFragment<WorkDeskContract.IHomeView, WorkDeskPresent> implements WorkDeskContract.IHomeView, RequestStatus, View.OnClickListener {


    private View view;
    private RecyclerView mWorkContentRv;
    private LinearLayout mWorkSignLl;
    private LinearLayout mWorkKeyLl;
    private LinearLayout mWorkNoticeLl;
    private WorkDeskAdapter workAdapter;
    private int GPS_REQUEST_CODE = 10;

    /**
     * 工作台
     */
    private TextView mWorkdeskTitleTv;



    /**
     * 加载适配器数据
     */
    private void initAdapterData() {
        List<MultiWorkDeskMenuBean> arrays = Hawk.get(HawkProperty.WORK_DESK_MENU);
        if (PubUtil.toolForm.size() > 0) {
            Log.d(TAG, PubUtil.toolForm.toString());
//            arrays.addAll(PubUtil.toolForm);
        }
        workAdapter.setNewData(arrays);

    }

    @Override
    protected void villageChanged() {
        mWorkdeskTitleTv.setText(mUserInfoUtil.getVillageName() + "服务中心");
        new LoginModel().getToolFormList(mUserInfoUtil.getPropertyId(), mUserInfoUtil.getVillageId(), mUserInfoUtil.getUserId(), LoginContract.GET_TOOL_FORM_LIST, this);
        super.villageChanged();
    }

    @Override
    protected WorkDeskPresent createPresenter() {
        return new WorkDeskPresent();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.work_fragment_layout, container, false);
        initView(view);
        initRecycleView();
        return view;
    }

    @Override
    protected void lazyLoad() {
        Log.d("lazyLoad","lazyLoad---------------"+TAG);
        if (mWorkdeskTitleTv != null) {
            mWorkdeskTitleTv.setText(mUserInfoUtil.getVillageName() + "服务中心");
        }
        initAdapterData();

    }

    private void initView(View view) {
        mWorkContentRv = view.findViewById(R.id.work_content_rv);
        workAdapter = new WorkDeskAdapter(null);
        GridLayoutManager managere = new GridLayoutManager(getContext(), 3);
        mWorkContentRv.setLayoutManager(managere);
        mWorkContentRv.setAdapter(workAdapter);
        mWorkSignLl = view.findViewById(R.id.work_sign_ll);
        mWorkSignLl.setOnClickListener(this);
        mWorkKeyLl = view.findViewById(R.id.work_key_ll);
        mWorkKeyLl.setOnClickListener(this);
        mWorkNoticeLl = view.findViewById(R.id.work_notice_ll);
        mWorkNoticeLl.setOnClickListener(this);
        workAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                MultiWorkDeskMenuBean multiWorkDeskMenuBean = (MultiWorkDeskMenuBean) adapter.getData().get(position);
                switch (multiWorkDeskMenuBean.getItemType()) {
                    case MultiWorkDeskMenuBean.FIXED_MENU:
                        String name = (String) multiWorkDeskMenuBean.getObject();
                        if ("组织架构".equals(name)) {
                            name = "通讯录";
                        }
                        switch (name) {
                            case "任务打卡":
                                startActivity(new Intent(getContext(), UsualTaskSignActivity.class));
                                break;
                            case "内部报修":
                                startActivity(new Intent(getContext(), InnerRepairActivity.class));

                                break;
                            case "通讯录":
                                startActivity(new Intent(getContext(), OrganizeFrameActivity.class));

                                break;
                            case "业主信息":
                                startActivity(new Intent(getContext(), OwnerInfoeActivity.class));

                                break;
                            case "车辆信息":
                                startActivity(new Intent(getContext(), CarNumActivity.class));
//                        startActivity(new Intent(getContext(), UploadCarMsgActivity.class));

                                break;
                            case "水电表抄录":
                                startActivity(new Intent(getContext(), WaterRecordActivity.class));


                                break;
                            case "签批与表单":
                                startActivity(new Intent(getContext(), SignAndTableActivity.class));


                                break;
                            case "账单管理":
                                break;
                            case "资金管理":
                                break;
                            case "任务打卡统计":
                                break;
                            case "排班":
                                startActivity(new Intent(getContext(), ArrangeShiftActivity.class));
                                break;
                            case "巡查巡检":
                                startActivity(new Intent(getContext(), PatrolCheckUpdateActivity.class));
                                break;
                            case "更多应用":
                                startActivity(new Intent(getContext(), MoreToolsActivity.class));
                                break;
                            case "访客审核":
                                startActivity(new Intent(getContext(), CheckVisitorListActivity.class));
                                break;
                            default:
                                break;
                        }
                        break;
                    case MultiWorkDeskMenuBean.DYNAMIC_MENU:
                        PubUtil.DailyRecordActivityEntry = 0;
                        ToolFormBean.DataBean dataBean = (ToolFormBean.DataBean) multiWorkDeskMenuBean.getObject();
                        Intent intent = new Intent(getContext(), CreateFormActivity.class);
                        intent.putExtra(ActivityResultManager.TABLE_TASK_DAILY_RECORD, dataBean);
                        startActivity(intent);
                        break;
                    default:
                        break;
                }

            }
        });
        mWorkdeskTitleTv = (TextView) view.findViewById(R.id.workdesk_title_tv);
        mWorkdeskTitleTv.setText(mUserInfoUtil.getVillageName() + "服务中心");
    }

    /**
     * 初始化recyclerview
     */
    private void initRecycleView() {

    }

    @Override
    protected void initFragmentData() {
    }

    @Override
    public void onResume() {
        super.onResume();

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


    }

    @Override
    public void onError(String tag) {
        getBaseActivity().showNormalToast(tag);
    }

    @Override
    public void onStart(String tag) {

    }

    @Override
    public void onSuccess(Object o, String tag) {
        if (LoginContract.GET_TOOL_FORM_LIST.equals(tag)) {
            ToolFormBean toolFormBean = (ToolFormBean) o;
            if (toolFormBean != null) {
                List<ToolFormBean.DataBean> dataBeanList = toolFormBean.getData();
                if (dataBeanList != null) {
                    PubUtil.toolForm.clear();
                    if (dataBeanList.size() > 0) {
                        for (ToolFormBean.DataBean dataBean : dataBeanList) {
                            PubUtil.toolForm.add(new MultiWorkDeskMenuBean(MultiWorkDeskMenuBean.DYNAMIC_MENU, dataBean));
                        }
                    }
                }
            }
            new LoginModel().getUserMenu(mUserInfoUtil.getPropertyId(), mUserInfoUtil.getVillageId(), mUserInfoUtil.getUserId(), MainContact.USER_MENU, this);

        } else {
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
                            }

                        }
                        Hawk.put(HawkProperty.WORK_DESK_MENU, adapterData);
                    } else {
                        Hawk.put(HawkProperty.WORK_DESK_MENU, adapterData);

                    }
                }

            }
            initAdapterData();

        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.work_sign_ll://签到
                startActivity(new Intent(getContext(), SignInActivity.class));
//                startActivity(new Intent(getContext(), ApplyReCardActivity.class));
                break;
            case R.id.work_key_ll://万能钥匙
                startActivity(new Intent(getContext(), UniversalKeyActivity.class));

                break;
            case R.id.work_notice_ll://公告
                startActivity(new Intent(getContext(), NoticeActivity.class));

                break;
        }
    }



}
