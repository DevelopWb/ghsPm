package com.ghs.ghspm.models.workdesk.signAndTable.formTaskRecordRelatedToMe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ghs.ghspm.R;
import com.ghs.ghspm.base.BaseActivity;
import com.ghs.ghspm.base.network.RequestStatus;
import com.ghs.ghspm.bean.FormTaskRecordBean;
import com.ghs.ghspm.models.workdesk.signAndTable.SignTableContrct;
import com.ghs.ghspm.models.workdesk.signAndTable.SignTablePresent;
import com.ghs.ghspm.models.workdesk.signAndTable.tableTaskToDo.tableTaskDescribe.TableTaskDesActivity;
import com.ghs.ghspm.tools.ActivityResultManager;
import com.ghs.ghspm.tools.PubUtil;
import com.ghs.ghspm.tools.UserInfoUtil;

import java.util.List;

/**
 * created by tobato
 * created date 2018/12/21 14:51.
 * application   我签批的  我提交的  抄送我的
 */
public class RecordOfMineActivity extends BaseActivity<SignTableContrct.ISignTableView, SignTablePresent> implements SignTableContrct.ISignTableView {

    private RecyclerView mRecordOfMineRv;
    private RecordOfMineAdapter adapter;
    private int offset = 0;
    private int limit = 10;
    private SwipeRefreshLayout mSwipeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setLayout() {
        setContentView(R.layout.activity_record_of_mine);

    }

    /**
     * 请求网络
     */
    private void  requestToNetWork(boolean  refresh){
        if (refresh) {
            offset = 0;
            switch (PubUtil.SIGN_TABLE_TYPE) {
                case 0://我签批的
                    getPresenter().formTaskSignedToMe(UserInfoUtil.getInstance().getUserId(), offset, limit, RequestStatus.REFRESH);
                    break;
                case 1://我提交的
                    getPresenter().formTaskCommitOfMine(UserInfoUtil.getInstance().getUserId(), offset, limit, RequestStatus.REFRESH);

                    break;
                case 2://抄送我的
                    getPresenter().formTaskCopyedToMe(UserInfoUtil.getInstance().getUserId(), offset, limit, RequestStatus.REFRESH);

                    break;
                default:
                    break;
            }
        }else{
            switch (PubUtil.SIGN_TABLE_TYPE) {
                case 0://我签批的
                    getPresenter().formTaskSignedToMe(UserInfoUtil.getInstance().getUserId(), offset, limit, SignTableContrct.FORM_TASK_SINGED_BY_ME);
                    break;
                case 1://我提交的
                    getPresenter().formTaskCommitOfMine(UserInfoUtil.getInstance().getUserId(), offset, limit, SignTableContrct.FORM_TASK_COMMIT_BY_ME);

                    break;
                case 2://抄送我的
                    getPresenter().formTaskCopyedToMe(UserInfoUtil.getInstance().getUserId(), offset, limit, SignTableContrct.FORM_TASK_COPY_TO_ME);

                    break;
                default:
                    break;
            }
        }


    }

    @Override
    public void initLayoutView() {
        initView();
        switch (PubUtil.SIGN_TABLE_TYPE) {
            case 0:
                initActionBar("我签批的", null);
                break;
            case 1:
                initActionBar("我提交的", null);

                break;
            case 2:
                initActionBar("抄送我的", null);

                break;
            default:
                break;
        }
    }

    @Override
    public SignTablePresent creatPresenter() {
        return new SignTablePresent();
    }

    @Override
    public void getDate() {
        requestToNetWork(true);
    }

    @Override
    public void actionBarRightTvOnClick() {

    }

    private void initView() {
        mRecordOfMineRv = (RecyclerView) findViewById(R.id.record_of_mine_rv);
        adapter = new RecordOfMineAdapter(R.layout.notice_item);
        adapter.setEmptyView(getAdapterEmptyView("一条数据也没有"));
        initRecyclerview(mRecordOfMineRv, adapter, LinearLayoutManager.VERTICAL, false);
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                requestToNetWork(false);


            }
        }, mRecordOfMineRv);
        mSwipeLayout = (SwipeRefreshLayout) findViewById(R.id.swipeLayout);
        mSwipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                adapter.setEnableLoadMore(false);//这里的作用是防止下拉刷新的时候还可以上拉加载
                mSwipeLayout.setRefreshing(true);
                requestToNetWork(true);

            }
        });
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                FormTaskRecordBean.DataBean dataBean = (FormTaskRecordBean.DataBean) adapter.getData().get(position);
                PubUtil.TableTaskDesActivityEntry = 0;
                Intent intent = new Intent(RecordOfMineActivity.this, TableTaskDesActivity.class);
                intent.putExtra(ActivityResultManager.TABLE_TASK_DETAIL_ID, dataBean.getId());
                intent.putExtra(ActivityResultManager.TABLE_TASK_DETAIL_TYPE, dataBean.getFormType());
                intent.putExtra(ActivityResultManager.TABLE_TASK_DETAIL_STATUS, dataBean.getStatus());
                startActivityForResult(intent, ActivityResultManager.TABLE_TASK_DETAIL);

            }
        });
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

        FormTaskRecordBean formTaskRecordBean = (FormTaskRecordBean) o;
        boolean isFresh = RequestStatus.REFRESH.equals(tag)?true:false;
        if (formTaskRecordBean != null) {
            List<FormTaskRecordBean.DataBean> arrays = formTaskRecordBean.getData();
            if (arrays != null) {
                offset+=arrays.size();
                if (isFresh) {
                    adapter.setNewData(arrays);
                }else{
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
    }

    @Override
    public void onError(String tag) {
        mSwipeLayout.setRefreshing(false);
    }
}
