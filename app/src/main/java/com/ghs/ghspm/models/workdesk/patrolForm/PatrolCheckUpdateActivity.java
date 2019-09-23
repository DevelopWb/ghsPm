package com.ghs.ghspm.models.workdesk.patrolForm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ghs.ghspm.R;
import com.ghs.ghspm.base.BaseActivity;
import com.ghs.ghspm.bean.PatrolFormListBean;
import com.ghs.ghspm.models.workdesk.patrolForm.formStatusInfo.PatrolFormPreviewActivity;
import com.ghs.ghspm.tools.PubUtil;
import com.ghs.ghspm.tools.UserInfoUtil;

import java.util.List;

/**
 * created by tobato
 * created date 2019/4/23 14:09.
 * application   巡查巡检表单列表
 */
public class PatrolCheckUpdateActivity extends BaseActivity<PatrolCheckUpdateContract.IPatrolCheckUpdateView, PatrolCheckUpdatePresent> implements PatrolCheckUpdateContract.IPatrolCheckUpdateView {

    private RecyclerView mPatrolCheckUpdateRv;
    private SwipeRefreshLayout mPatrolCheckUpdateRefresh;
    private PatrolCheckUpdateAdapter adapter;
    private UserInfoUtil userInfoUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public PatrolCheckUpdatePresent creatPresenter() {
        return new PatrolCheckUpdatePresent();
    }

    @Override
    public void setLayout() {
        setContentView(R.layout.activity_patrol_check_update);

    }

    @Override
    public void initLayoutView() {
        initView();
        initActionBar("巡查巡检", null);
    }

    @Override
    public void getDate() {
        userInfoUtil = new UserInfoUtil();
        getPresenter().getPatrolFormList(userInfoUtil.getPropertyId(), userInfoUtil.getVillageId(), userInfoUtil.getUserId(), "");
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
        mPatrolCheckUpdateRefresh.setRefreshing(false);
        PatrolFormListBean patralFormBean = (PatrolFormListBean) o;
        if (patralFormBean != null) {
            List<PatrolFormListBean.DataBean> dataBeans = patralFormBean.getData();
            if (dataBeans != null && dataBeans.size() > 0) {
                adapter.setNewData(dataBeans);
            }
        }

    }

    @Override
    public void onError(String tag) {

    }

    private void initView() {
        mPatrolCheckUpdateRv = (RecyclerView) findViewById(R.id.patrol_check_update_rv);
        adapter = new PatrolCheckUpdateAdapter(R.layout.patrol_check_update_item);
        adapter.setEmptyView(getAdapterEmptyView("很干净，一个表单也没有"));
        initRecyclerview(mPatrolCheckUpdateRv, adapter, LinearLayoutManager.VERTICAL, false);
        addDivider(true, mPatrolCheckUpdateRv, true, true);
        mPatrolCheckUpdateRefresh = (SwipeRefreshLayout) findViewById(R.id.patrol_check_update_refresh);
        mPatrolCheckUpdateRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getPresenter().getPatrolFormList(userInfoUtil.getPropertyId(), userInfoUtil.getVillageId(), userInfoUtil.getUserId(), "");

            }
        });
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                PatrolFormListBean.DataBean dataBean = (PatrolFormListBean.DataBean) adapter.getData().get(position);
                PubUtil.PATROL_FORM_LIST_DATABEAN = dataBean;
                startActivity(new Intent(PatrolCheckUpdateActivity.this, PatrolFormPreviewActivity.class));
            }
        });
    }
}
