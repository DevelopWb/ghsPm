package com.ghs.ghspm.models.workdesk.signAndTable.tableTaskToDo.formTaskSign.autoForm;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ghs.ghspm.R;
import com.ghs.ghspm.base.BaseActivity;
import com.ghs.ghspm.bean.AutoFormBean;
import com.ghs.ghspm.bean.TableTaskDetailBean;
import com.ghs.ghspm.customView.CustomLoadMoreView;
import com.ghs.ghspm.customView.SyncHorizontalScrollView;
import com.ghs.ghspm.models.workdesk.signAndTable.SignTableContrct;
import com.ghs.ghspm.models.workdesk.signAndTable.SignTablePresent;
import com.ghs.ghspm.tools.ActivityResultManager;
import com.ghs.ghspm.tools.CalendarUtil;
import com.ghs.ghspm.tools.PubUtil;
import com.ghs.ghspm.tools.StrUtils;
import com.ghs.ghspm.tools.UserInfoUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * created by tobato
 * created date 2019/1/2 10:44.
 * application   自动报表
 * <p>
 * 因为使用scrollview嵌套recyclerview  滑动使用的是scrollview的滑动，所以item得不到复用，当数据量大的时候 容易出现oom，解决办法，监听
 * scrollview的滑动，滑动时加载10条数据，第一次进入页面的时候 加载15条数据 保证数据填充整个屏幕
 */
public class AutoFormActivity extends BaseActivity<SignTableContrct.ISignTableView, SignTablePresent> implements View.OnClickListener, SignTableContrct.ISignTableView {

    private RecyclerView mShowTableRecordRv;
    /**
     * 总标题
     */
    private TextView mTvTableTitleLeft;
    private SyncHorizontalScrollView mTitleHorsv;
    private SyncHorizontalScrollView mContentHorsv;
    private NestedScrollView mPullRefreshScroll;
    private RecyclerView mLeftContainerRv;
    private RecyclerView mRightTitleRv;
    private RecyclerView mRightContainerRv;
    private TextView mTableTaskNameTv;
    private TextView mTableTaskDateTv;
    private TextView mTableTaskDealTv;
    private LeftMenuAdapter leftAdapter;
    private RightTitleAdapter rightTitleAdapter;
    private RightPresentAdapter rightContentAdapter;

    private List<AutoFormBean.DataBean> leftMenuData = new ArrayList<>();
    private List<AutoFormBean.DataBean> rightTitleData = new ArrayList<>();
    private List<List<AutoFormBean.DataBean>> rightContent = new ArrayList<>();
    private TableTaskDetailBean.DataBean dataBean;
    private int scrollViewLimit = 15;//scrollview 滑动一次需要加载的数据的条数
    private List<List<AutoFormBean.DataBean>> scrollViewList;//scrollview 滑动一次需要加载的数据
    private int loadLimit = 8;//展示数据时 一次展示的数据

    // 次加载15条数据
    private int scrollOffset = 0;//滑动时加载数据的起点
    private int displayOffset = 0;//展示时的起点
    private List<List<AutoFormBean.DataBean>> lists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public SignTablePresent creatPresenter() {
        return new SignTablePresent();
    }

    @Override
    public void setLayout() {
        setContentView(R.layout.activity_show_table_record);

    }

    @Override
    public void initLayoutView() {
        initView();

    }

    @Override
    public void getDate() {
        if (getIntent() != null) {
            dataBean = getIntent().getParcelableExtra(ActivityResultManager.AUTO_FORM_SIGN_ACTIVITY);
            if (dataBean != null) {
                initTitleAndSIgnButtomStatus();
                getPresenter().getTaskListOfAutoForm(dataBean.getId(), SignTableContrct.GET_AUTO_FORM_LIST);
                mTableTaskNameTv.setText(dataBean.getTaskName());
                String startTime = StrUtils.isStringValueOk(dataBean.getTaskStartTime()) ? CalendarUtil.getTimeFromStringTime("yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", dataBean.getTaskStartTime()) : "";
                String endTime = StrUtils.isStringValueOk(dataBean.getTaskEndTime()) ? CalendarUtil.getTimeFromStringTime("yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", dataBean.getTaskEndTime()) : "";
                mTableTaskDateTv.setText("报表日期：" + startTime + "至" + endTime);
                initActionBar(dataBean.getFormName(), null);

            }
        }
    }

    /**
     * 初始化标题和签批按钮的状态
     */
    private void initTitleAndSIgnButtomStatus() {
        if (3 == dataBean.getStatus()) {
            mTableTaskDealTv.setVisibility(View.GONE);
        } else {
            if (0 == PubUtil.TableTaskDesActivityEntry) {
                mTableTaskDealTv.setVisibility(View.GONE);
            } else {
                mTableTaskDealTv.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public void actionBarRightTvOnClick() {

    }

    private void initView() {
        mTvTableTitleLeft = (TextView) findViewById(R.id.tv_table_title_left);
        mTitleHorsv = (SyncHorizontalScrollView) findViewById(R.id.title_horsv);
        mContentHorsv = (SyncHorizontalScrollView) findViewById(R.id.content_horsv);
        mPullRefreshScroll = (NestedScrollView) findViewById(R.id.pull_refresh_scroll);
        // 设置两个水平控件的联动
        mTitleHorsv.setScrollView(mContentHorsv);
        mContentHorsv.setScrollView(mTitleHorsv);
        mLeftContainerRv = (RecyclerView) findViewById(R.id.left_container_Rv);
        mRightTitleRv = (RecyclerView) findViewById(R.id.right_title_rv);
        mRightContainerRv = (RecyclerView) findViewById(R.id.right_container_Rv);
        mPullRefreshScroll.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY > oldScrollY) {
                    Log.i(TAG, "Scroll DOWN");
                }
                if (scrollY < oldScrollY) {
                    Log.i(TAG, "Scroll UP");
                }

                if (scrollY == 0) {
                    Log.i(TAG, "TOP SCROLL");
                }

                if (scrollY == (v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight())) {
                    Log.i(TAG, "BOTTOM SCROLL");
//每滑动一次，加载5条数据
                    scrollViewList = getToShowData(lists, scrollOffset, loadLimit, 0);
                    if (scrollViewList.size() > 0) {
                        rightContentAdapter.loadMoreComplete();
                        leftAdapter.loadMoreComplete();
                    }


                }
            }
        });
        //左侧菜单
        leftAdapter = new LeftMenuAdapter(R.layout.test_text);
        leftAdapter.setLoadMoreView(new CustomLoadMoreView());
        addDivider(true,mLeftContainerRv,false,true);

        initRecyclerview(mLeftContainerRv, leftAdapter, LinearLayoutManager.VERTICAL, false, true);
//右侧标题
        rightTitleAdapter = new RightTitleAdapter(R.layout.test_text);
        initRecyclerview(mRightTitleRv, rightTitleAdapter, LinearLayoutManager.HORIZONTAL, false, true);
//右侧内容
        rightContentAdapter = new RightPresentAdapter(R.layout.test_right_text);
        rightContentAdapter.setLoadMoreView(new CustomLoadMoreView());
        rightContentAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                loadMoreRequested();

            }
        }, mRightContainerRv);
        leftAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
            }
        }, mLeftContainerRv);
        initRecyclerview(mRightContainerRv, rightContentAdapter, LinearLayoutManager.VERTICAL, false, true);
        addDivider(true,mRightContainerRv,false,true);

        mTableTaskNameTv = (TextView) findViewById(R.id.table_task_name_tv);
        mTableTaskDateTv = (TextView) findViewById(R.id.table_task_form_head_info_tv);
        mTableTaskDealTv = (TextView) findViewById(R.id.table_task_deal_tv);
        mTableTaskDealTv.setOnClickListener(this);

    }

    /**
     * 截取需要展示的数据
     *
     * @param lists
     * @param offset
     * @param limit
     * @param type   0代表滑动时加载的数据 15条，1代表展示时每次展示的数据 2条
     * @return
     */
    private List<List<AutoFormBean.DataBean>> getToShowData(List<List<AutoFormBean.DataBean>> lists, int offset, int limit, int type) {
        List<List<AutoFormBean.DataBean>> arrasy = new ArrayList<>(lists.size());
        for (int i = 0; i < lists.size(); i++) {
            if (i > offset - 1 && i < limit + offset) {
                arrasy.add(lists.get(i));
            }
        }
        if (0 == type) {
            this.scrollOffset += arrasy.size();

        } else {
            this.displayOffset += arrasy.size();

        }
        return arrasy;
    }

    /**
     * 加载更多数据
     */
    private void loadMoreRequested() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //每次展示5条数据
                getAdapterData(getToShowData(scrollViewList, displayOffset, loadLimit, 1));
            }
        }, 500);
    }

    /**
     * 获取数据
     *
     * @param lists
     */
    private void getAdapterData(List<List<AutoFormBean.DataBean>> lists) {
        if (lists != null) {
            if (lists.size() > 0) {
                rightTitleData.clear();
                leftMenuData.clear();
                rightContent.clear();
                for (int i = 0; i < lists.size(); i++) {
                    List<AutoFormBean.DataBean> arrays = lists.get(i);
                    List<AutoFormBean.DataBean> rightContentItem = new ArrayList<>();

                    for (int x = 0; x < arrays.size(); x++) {
                        AutoFormBean.DataBean dataBean = arrays.get(x);
                        if (0 == i) {//获取标题
                            if (0 != x) {
                                rightTitleData.add(dataBean);
                            } else {
                                mTvTableTitleLeft.setText(dataBean.getTitle());
                            }
                        }
                        if (0 == x) {//获取左侧数据
                            leftMenuData.add(dataBean);
                        } else {
                            rightContentItem.add(dataBean);
                        }


                    }
                    rightContent.add(rightContentItem);

                }
                rightTitleAdapter.addData(rightTitleData);
                leftAdapter.addData(leftMenuData);
                rightContentAdapter.addData(rightContent);
                if (lists.size() < loadLimit) {
                    displayDataComplete();

                } else {
                    rightContentAdapter.loadMoreComplete();
                    leftAdapter.loadMoreComplete();
                }
            } else {
                displayDataComplete();
            }
        }
    }

    /**
     * 数据展示完毕
     */
    private void displayDataComplete() {
        //第一页如果不够一页就不显示没有更多数据布局
        rightContentAdapter.loadMoreEnd(false);
        leftAdapter.loadMoreEnd(false);
        displayOffset = 0;
        if (leftAdapter.getData().size() == lists.size()) {
            rightContentAdapter.loadMoreEnd(true);
            leftAdapter.loadMoreEnd(true);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.table_task_deal_tv:
                    if (dataBean != null) {
                        getPresenter().signAutoForm(dataBean.getId(), UserInfoUtil.getInstance().getUserId(), "");
                }
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
        if (SignTableContrct.GET_AUTO_FORM_LIST.equals(tag)) {
            AutoFormBean autoFormBean = (AutoFormBean) o;
            if (autoFormBean != null) {
                lists = autoFormBean.getData();
                //第一次 截取15条待展示的数据
                scrollViewList = getToShowData(lists, scrollOffset, scrollViewLimit, 0);
                //每次展示5条数据 分阶段展示
                getAdapterData(getToShowData(scrollViewList, displayOffset, loadLimit, 1));
            }
        } else {//签字
            stopMaterialProgressDialog();
            showToast("已签批");
            setResult(ActivityResultManager.FINISH_FORM_TASK_DETAIL_ACTIVITY);
            finish();
        }

    }

    @Override
    public void onError(String tag) {
        showToast(tag);

    }
}
