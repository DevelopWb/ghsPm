package com.ghs.ghspm.models.workdesk.moretools;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ghs.ghspm.R;
import com.ghs.ghspm.base.BaseActivity;
import com.ghs.ghspm.base.network.RequestStatus;
import com.ghs.ghspm.bean.MultiWorkDeskMenuBean;
import com.ghs.ghspm.bean.ToolFormBean;
import com.ghs.ghspm.models.login.LoginContract;
import com.ghs.ghspm.models.login.LoginModel;
import com.ghs.ghspm.models.login.LoginPresent;
import com.ghs.ghspm.models.workdesk.WorkDeskAdapter;
import com.ghs.ghspm.models.workdesk.signAndTable.tableTaskToDo.createForm.CreateFormActivity;
import com.ghs.ghspm.tools.ActivityResultManager;
import com.ghs.ghspm.tools.PubUtil;
import com.ghs.ghspm.tools.UserInfoUtil;

import java.util.List;

public class MoreToolsActivity extends BaseActivity<LoginContract.ILoginView, LoginPresent> implements LoginContract.ILoginView, RequestStatus {


    private WorkDeskAdapter workAdapter;
    private RecyclerView moreapp_rececylerview;
    private MoreToolsAdapter moreToolsAdapter;
    private SwipeRefreshLayout form_filling_refresh;
    private ToolFormBean toolFormBean;

    @Override
    public LoginPresent creatPresenter() {
        return new LoginPresent();
    }

    @Override
    public void setLayout() {
        setContentView(R.layout.activity_more_applications);
    }

    @Override
    public void initLayoutView() {
        initView();
        initActionBar("更多应用", null);
    }

    @Override
    public void getDate() {
        new LoginModel().getToolFormList(UserInfoUtil.getInstance().getPropertyId(), UserInfoUtil.getInstance().getVillageId(), UserInfoUtil.getInstance().getUserId(), LoginContract.GET_TOOL_FORM_LIST, this);
    }

    @Override
    public void actionBarRightTvOnClick() {

    }

    @Override
    public void updateSendCheckCodeViewStatus(long second) {

    }

    @Override
    public void checkFormatError(String error) {

    }

    @Override
    public void startLoading(String tag) {

    }

    @Override
    public void stopLoading(String tag) {

    }

    @Override
    public void updateView(Object o, String tag) {
        showToast(tag + "updateview");

    }

    @Override
    public void onStart(String tag) {

    }

    @Override
    public void onSuccess(Object o, String tag) {
        form_filling_refresh.setRefreshing(false);
        PubUtil.DailyRecordActivityEntry = 0;
        PubUtil.toolForm.clear();
        toolFormBean = (ToolFormBean) o;
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
            moreToolsAdapter.setNewData(PubUtil.toolForm);

        }


    }

    @Override
    public void onError(String tag) {
        form_filling_refresh.setRefreshing(false);
        showToast(tag);
    }

    public void initView() {
        moreapp_rececylerview = findViewById(R.id.moreapp_rececylerview);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        moreapp_rececylerview.setLayoutManager(layoutManager);
//        workAdapter = new WorkDeskAdapter(null);

        moreToolsAdapter = new MoreToolsAdapter(R.layout.more_tools_item_layout, null);
        moreToolsAdapter.setEmptyView(getAdapterEmptyView("很干净，一条数据都没有"));
        moreapp_rececylerview.setAdapter(moreToolsAdapter);
        moreToolsAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                MultiWorkDeskMenuBean multiWorkDeskMenuBean = (MultiWorkDeskMenuBean) adapter.getData().get(position);
                ToolFormBean.DataBean dataBean = (ToolFormBean.DataBean) multiWorkDeskMenuBean.getObject();
                Intent intent = new Intent(MoreToolsActivity.this, CreateFormActivity.class);
                intent.putExtra(ActivityResultManager.TABLE_TASK_DAILY_RECORD, dataBean);
                startActivity(intent);
            }
        });

        form_filling_refresh = findViewById(R.id.form_filling_refresh);
        form_filling_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                if (toolFormBean != null) {
                    new LoginModel().getToolFormList(UserInfoUtil.getInstance().getPropertyId(), UserInfoUtil.getInstance().getVillageId(), UserInfoUtil.getInstance().getUserId(), LoginContract.GET_TOOL_FORM_LIST, MoreToolsActivity.this);

                }

            }
        });
    }
}
