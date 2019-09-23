package com.ghs.ghspm.models.task.pushedTaskShow;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ghs.ghspm.R;
import com.ghs.ghspm.base.BaseFragment;
import com.ghs.ghspm.base.network.RequestStatus;
import com.ghs.ghspm.bean.TableTaskOutLineBean;
import com.ghs.ghspm.bean.TaskMultipleItem;
import com.ghs.ghspm.bean.TasksBean;
import com.ghs.ghspm.models.task.TaskAdapter;
import com.ghs.ghspm.models.task.TaskContract;
import com.ghs.ghspm.models.task.TaskDetailInfoActivity;
import com.ghs.ghspm.models.task.TaskPresent;
import com.ghs.ghspm.models.task.publishtask.PublishTaskActivity;
import com.ghs.ghspm.models.workdesk.signAndTable.SignTableContrct;
import com.ghs.ghspm.models.workdesk.signAndTable.SignTableModel;
import com.ghs.ghspm.models.workdesk.signAndTable.tableTaskToDo.tableTaskDescribe.TableTaskDesActivity;
import com.ghs.ghspm.tools.ActivityResultManager;
import com.ghs.ghspm.tools.Contract;
import com.ghs.ghspm.tools.PubUtil;
import com.ghs.ghspm.tools.UserInfoUtil;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;


public class WaitForDealTaskFromPushFragment extends BaseFragment<TaskContract.ITaskView, TaskPresent> implements TaskContract.ITaskView, RequestStatus, View.OnClickListener {


    private View view;
    private RecyclerView mNormalNoticeRv;
    private ImageView mNormalNoticePublishIv;
    private TaskAdapter adapter;
    private int offset = 0;
    private int limit = 10;
    private SwipeRefreshLayout mSwipeLayout;
    private RecyclerView mNormalTableTaskRv;
    private TaskAdapter tableTaskAdapter;

    public static WaitForDealTaskFromPushFragment getInstance() {
        return FirstFragmentHolder.instatce;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.normal_notice_fragment_layout, container, false);
        initView(view);
        return view;
    }

    @Override
    protected void lazyLoad() {
        offset = 0;
        new SignTableModel().getTableTasks(UserInfoUtil.getInstance().getUserId(), SignTableContrct.GET_TABLE_TASKS, this);

        getPresenter().getTasks(Contract.WAIT_TO_DEAL_TASK, UserInfoUtil.getInstance().getUserId(), offset, limit, TaskContract.REFRESH);

    }

    private void initView(View view) {
        mNormalNoticeRv = (RecyclerView) view.findViewById(R.id.normal_notice_rv);
        mNormalNoticePublishIv = (ImageView) view.findViewById(R.id.normal_notice_publish_iv);

        mNormalNoticePublishIv.setOnClickListener(this);
        adapter = new TaskAdapter(null);
        adapter.setHeaderView(getHeadLayout());
        adapter.setHeaderAndEmpty(true);

        initRecyclerview(mNormalNoticeRv, adapter, LinearLayoutManager.VERTICAL, false);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                TaskMultipleItem taskMultipleItem = (TaskMultipleItem) adapter.getData().get(position);
                switch (taskMultipleItem.getItemType()) {
                    case TaskMultipleItem.TEMP_TASK:
                        TasksBean.DataBean dataBean = (TasksBean.DataBean) taskMultipleItem.getObject();
                        PubUtil.clickedTaskID = dataBean.getId();
                        PubUtil.clickedTaskType = 0;
                        startActivityForResult(new Intent(getContext(), TaskDetailInfoActivity.class), ActivityResultManager.DEAL_TASK_DETAIL);

                        break;
                    default:
                        break;
                }
            }
        });
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                getPresenter().getTasks(Contract.WAIT_TO_DEAL_TASK, UserInfoUtil.getInstance().getUserId(), offset, limit, "");
            }
        }, mNormalNoticeRv);
        mSwipeLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeLayout);
        mSwipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                offset = 0;
                adapter.setEnableLoadMore(false);//这里的作用是防止下拉刷新的时候还可以上拉加载
                mSwipeLayout.setRefreshing(true);
                new SignTableModel().getTableTasks(UserInfoUtil.getInstance().getUserId(), SignTableContrct.GET_TABLE_TASKS, WaitForDealTaskFromPushFragment.this);

                getPresenter().getTasks(Contract.WAIT_TO_DEAL_TASK, UserInfoUtil.getInstance().getUserId(), offset, limit, TaskContract.REFRESH);

            }
        });

    }

    /**
     * 获取头布局
     *
     * @return
     */
    private View getHeadLayout() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.table_task_head_layout, null);
        mNormalTableTaskRv = (RecyclerView) view.findViewById(R.id.normal_notice_table_task_rv);
        tableTaskAdapter = new TaskAdapter(null);
        initRecyclerview(mNormalTableTaskRv, tableTaskAdapter, LinearLayoutManager.VERTICAL, false, true);

        tableTaskAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                TaskMultipleItem taskMultipleItem = (TaskMultipleItem) adapter.getData().get(position);
                TableTaskOutLineBean.DataBean dataBean = (TableTaskOutLineBean.DataBean) taskMultipleItem.getObject();
                if (dataBean != null) {
                    Intent intent = new Intent(getContext(), TableTaskDesActivity.class);
                    PubUtil.TableTaskDesActivityEntry = 1;
                    intent.putExtra(ActivityResultManager.TABLE_TASK_DETAIL_ID, dataBean.getId());
                    intent.putExtra(ActivityResultManager.TABLE_TASK_DETAIL_TYPE, dataBean.getFormType());
                    intent.putExtra(ActivityResultManager.TABLE_TASK_DETAIL_STATUS, dataBean.getStatus());
                    startActivityForResult(intent, ActivityResultManager.TABLE_TASK_DETAIL);
                }

            }
        });
        return view;
    }

    @Override
    protected View initFragmentViewLayout(LayoutInflater inflater, ViewGroup container) {
        return null;
    }

    @Override
    protected void initFragmentView(View view) {
    }

    @Override
    protected void initFragmentData() {
    }

    @Override
    protected TaskPresent createPresenter() {
        return new TaskPresent();
    }

    @Override
    public void onResume() {
        super.onResume();


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (ActivityResultManager.PUBLISH_TASK == resultCode || ActivityResultManager.DEAL_TASK_DETAIL == resultCode || ActivityResultManager.TABLE_TASK_DETAIL == resultCode) {
            if (getPresenter() != null) {
                offset = 0;
                new SignTableModel().getTableTasks(UserInfoUtil.getInstance().getUserId(), SignTableContrct.GET_TABLE_TASKS, this);

                getPresenter().getTasks(Contract.WAIT_TO_DEAL_TASK, UserInfoUtil.getInstance().getUserId(), offset, limit, TaskContract.REFRESH);
            }

        }

    }

    @Override
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void stringMsgReceived(String str) {
        if (ActivityResultManager.TASK_FRAGMENT_REFRESH.equals(str)) {//
            offset = 0;
            new SignTableModel().getTableTasks(UserInfoUtil.getInstance().getUserId(), SignTableContrct.GET_TABLE_TASKS, this);

            getPresenter().getTasks(Contract.WAIT_TO_DEAL_TASK, UserInfoUtil.getInstance().getUserId(), offset, limit, TaskContract.REFRESH);

        }
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
        mSwipeLayout.setRefreshing(false);
        TasksBean taskBean = (TasksBean) o;
        boolean isFresh = TaskContract.REFRESH.equals(tag) ? true : false;
        if (taskBean != null) {
            if (PubUtil.CAN_PUBLISH_TASK) {
                mNormalNoticePublishIv.setVisibility(View.VISIBLE);
            } else {
                mNormalNoticePublishIv.setVisibility(View.GONE);

            }
            if (taskBean.getData() != null) {
                List<TasksBean.DataBean> dataBeanList = taskBean.getData();
                List<TaskMultipleItem> arrays = new ArrayList<>();
                for (TasksBean.DataBean dataBean : dataBeanList) {
                    arrays.add(new TaskMultipleItem(TaskMultipleItem.TEMP_TASK, dataBean));
                }
                offset += dataBeanList.size();
                if (isFresh) {
                    adapter.setNewData(arrays);
                } else {
                    adapter.addData(arrays);
                }
                if (dataBeanList.size() < limit) {
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

    @Override
    public void onStart(String tag) {

    }

    @Override
    public void onSuccess(Object o, String tag) {

        if (SignTableContrct.GET_TABLE_TASKS.equals(tag)) {//表单任务
            TableTaskOutLineBean tableTaskBean = (TableTaskOutLineBean) o;
            List<TaskMultipleItem> arrays = new ArrayList<>();
            if (tableTaskBean != null) {
                if (tableTaskBean.getData() != null) {
                    if (tableTaskBean.getData().size() > 0) {
                        List<TableTaskOutLineBean.DataBean> dataBeans = tableTaskBean.getData();
                        for (TableTaskOutLineBean.DataBean dataBean : dataBeans) {
                            arrays.add(new TaskMultipleItem(TaskMultipleItem.TABLE_TASK, dataBean));

                        }
                    }
                }
            }
            tableTaskAdapter.setNewData(arrays);
            if (arrays.size() > 0) {
                adapter.setEmptyView(getAdapterEmptyView(""));
            } else {
                adapter.setEmptyView(getAdapterEmptyView("很干净，一条任务也没有"));


            }
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.normal_notice_publish_iv:
                startActivityForResult(new Intent(getContext(), PublishTaskActivity.class), ActivityResultManager.PUBLISH_TASK);


                break;
            default:
                break;
        }
    }

    private static class FirstFragmentHolder {
        private static WaitForDealTaskFromPushFragment instatce = new WaitForDealTaskFromPushFragment();
    }


}
