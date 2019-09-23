package com.ghs.ghspm.models.workdesk.signAndTable;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ghs.ghspm.R;
import com.ghs.ghspm.base.BaseActivity;
import com.ghs.ghspm.bean.TableTaskOutLineBean;
import com.ghs.ghspm.models.workdesk.signAndTable.formTaskRecordRelatedToMe.RecordOfMineActivity;
import com.ghs.ghspm.models.workdesk.signAndTable.tableTaskToDo.tableTaskDescribe.TableTaskDesActivity;
import com.ghs.ghspm.tools.ActivityResultManager;
import com.ghs.ghspm.tools.PubUtil;
import com.ghs.ghspm.tools.UserInfoUtil;

import java.util.List;

/**
 * created by tobato
 * created date 2019/1/2 10:38.
 * application   日常记录类表单
 */
public class SignAndTableActivity extends BaseActivity<SignTableContrct.ISignTableView, SignTablePresent> implements View.OnClickListener, SignTableContrct.ISignTableView {

    private RecyclerView mSignTableRv;
    private LinearLayout mTableSignedOfMineLl;
    private LinearLayout mTableCommitOfMineLl;
    private LinearLayout mTableCopyedToMeLl;
    private SignAndTableAdapter adapter;
    private SwipeRefreshLayout mSwipeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setLayout() {
        setContentView(R.layout.activity_sign_and_table);

    }

    @Override
    public void initLayoutView() {
        initView();
        initActionBar("签批与表单", null);
    }

    @Override
    public SignTablePresent creatPresenter() {
        return new SignTablePresent();
    }

    @Override
    public void getDate() {
        getPresenter().getTableTasks(UserInfoUtil.getInstance().getUserId(), SignTableContrct.GET_TABLE_TASKS);

    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        getPresenter().getTableTasks(UserInfoUtil.getInstance().getUserId(), SignTableContrct.GET_TABLE_TASKS);

    }

    @Override
    public void actionBarRightTvOnClick() {

    }

    private void initView() {
        mSignTableRv = (RecyclerView) findViewById(R.id.sign_table_rv);
        mTableSignedOfMineLl = (LinearLayout) findViewById(R.id.table_signed_of_mine_ll);
        mTableSignedOfMineLl.setOnClickListener(this);
        mTableCommitOfMineLl = (LinearLayout) findViewById(R.id.table_commit_of_mine_ll);
        mTableCommitOfMineLl.setOnClickListener(this);
        mTableCopyedToMeLl = (LinearLayout) findViewById(R.id.table_copyed_to_me_ll);
        mSwipeLayout = (SwipeRefreshLayout) findViewById(R.id.swipeLayout);
        mSwipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSwipeLayout.setRefreshing(true);
                getPresenter().getTableTasks(UserInfoUtil.getInstance().getUserId(), SignTableContrct.GET_TABLE_TASKS);

            }
        });
        mTableCopyedToMeLl.setOnClickListener(this);
        adapter = new SignAndTableAdapter(R.layout.notice_item);
        adapter.setEmptyView(getAdapterEmptyView("很干净,一条任务也没有"));
        initRecyclerview(mSignTableRv, adapter, LinearLayoutManager.VERTICAL, false);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                TableTaskOutLineBean.DataBean dataBean = (TableTaskOutLineBean.DataBean) adapter.getData().get(position);
                Intent intent = new Intent(SignAndTableActivity.this, TableTaskDesActivity.class);
                PubUtil.TableTaskDesActivityEntry = 1;
                intent.putExtra(ActivityResultManager.TABLE_TASK_DETAIL_ID, dataBean.getId());
                intent.putExtra(ActivityResultManager.TABLE_TASK_DETAIL_TYPE, dataBean.getFormType());
                intent.putExtra(ActivityResultManager.TABLE_TASK_DETAIL_STATUS, dataBean.getStatus());
                startActivityForResult(intent, ActivityResultManager.TABLE_TASK_DETAIL);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (ActivityResultManager.TABLE_TASK_DETAIL==resultCode) {
            getPresenter().getTableTasks(UserInfoUtil.getInstance().getUserId(), SignTableContrct.GET_TABLE_TASKS);

        }else{
            super.onActivityResult(requestCode, resultCode, data);

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.table_signed_of_mine_ll:
                PubUtil.SIGN_TABLE_TYPE = 0;
                startActivity(new Intent(this, RecordOfMineActivity.class));
                break;
            case R.id.table_commit_of_mine_ll:
                PubUtil.SIGN_TABLE_TYPE = 1;
                startActivity(new Intent(this, RecordOfMineActivity.class));

                break;
            case R.id.table_copyed_to_me_ll:
                PubUtil.SIGN_TABLE_TYPE = 2;
                startActivity(new Intent(this, RecordOfMineActivity.class));

                break;
        }
    }

    @Override
    public void startLoading(String tag) {

    }

    @Override
    public void stopLoading(String tag) {

    }

    @Override
    public void updateView(Object o, String tag) {
        mSwipeLayout.setRefreshing(false);

        if (SignTableContrct.GET_TABLE_TASKS.equals(tag)) {//表单任务
            TableTaskOutLineBean tableTaskBean = (TableTaskOutLineBean) o;
            if (tableTaskBean != null) {
                if (tableTaskBean.getData() != null) {
                    Log.i("TAG",tableTaskBean.getData().size()+"----");
                    if (tableTaskBean.getData().size() >= 0) {
                        List<TableTaskOutLineBean.DataBean> dataBeans = tableTaskBean.getData();
                        adapter.setNewData(dataBeans);
                    }
                }
            }
        }
    }

    @Override
    public void onError(String tag) {
        mSwipeLayout.setRefreshing(false);
        showToast(tag);

    }
}
