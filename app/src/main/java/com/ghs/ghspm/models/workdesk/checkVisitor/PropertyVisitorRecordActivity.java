package com.ghs.ghspm.models.workdesk.checkVisitor;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ghs.ghspm.R;
import com.ghs.ghspm.base.BaseActivity;
import com.ghs.ghspm.base.network.RequestStatus;
import com.ghs.ghspm.bean.PropertyVisitorListBean;
import com.ghs.ghspm.models.workdesk.checkVisitor.adapter.PropertyRecordAdapter;
import com.ghs.ghspm.tools.IntentUtil;
import com.ghs.ghspm.tools.UserInfoUtil;

import java.util.List;

/**
 * created by
 * created date 2019/8/26 11:26.
 * application   物业访客记录
 */
public class PropertyVisitorRecordActivity extends BaseActivity<PropertyVisitorContract.PropertyRecordView, PropertyVisitorPresent> implements PropertyVisitorContract.PropertyRecordView {

    private RecyclerView mPropertyRecordRv;
    private SwipeRefreshLayout mPropertyRecordSwipefreshlayout;
    private PropertyRecordAdapter propertyRecordAdapter;
    private int offset = 0;
    private int limit =10;
    @Override
    public PropertyVisitorPresent creatPresenter() {
        return new PropertyVisitorPresent();
    }

    @Override
    public void setLayout() {
        setContentView(R.layout.activity_property_visitor_record);
        initView();
        initActionBar("物业访客记录", "",R.drawable.bg_gray_only_bottom_deeper_gray_shape_1px);

    }

    @Override
    public void initLayoutView() {


    }

    @Override
    public void getDate() {
        offset = 0;
        getPresenter().getPropertyVisitorRecodList(UserInfoUtil.getInstance().getVillageId(), offset, limit, 2, RequestStatus.REFRESH);

    }

    @Override
    public void actionBarRightTvOnClick() {

    }

    private void initView() {
        mPropertyRecordRv = (RecyclerView) findViewById(R.id.property_record_rv);
        mPropertyRecordSwipefreshlayout = (SwipeRefreshLayout) findViewById(R.id.property_record_swipefreshlayout);
        mPropertyRecordSwipefreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                offset = 0;
                propertyRecordAdapter.setEnableLoadMore(false);//这里的作用是防止下拉刷新的时候还可以上拉加载
                mPropertyRecordSwipefreshlayout.setRefreshing(true);
                getPresenter().getPropertyVisitorRecodList(UserInfoUtil.getInstance().getVillageId(), offset, limit, 2, RequestStatus.REFRESH);

            }
        });
        propertyRecordAdapter = new PropertyRecordAdapter(R.layout.property_record_item_layout, null);
        propertyRecordAdapter.setEmptyView(getAdapterEmptyView("暂无内容"));
        initRecyclerview(mPropertyRecordRv, propertyRecordAdapter, LinearLayoutManager.VERTICAL, false);
        propertyRecordAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                PropertyVisitorListBean.DataBean data = propertyRecordAdapter.getData().get(position);
                IntentUtil.getInstance().startActivityWithParcelableData(data, PropertyVisitorRecordActivity.this, DealVisitorRequestActivity.class);

            }
        });
        propertyRecordAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                getPresenter().getPropertyVisitorRecodList(UserInfoUtil.getInstance().getVillageId(), offset, limit, 2, PropertyVisitorContract.PROPERTY_RECORD_LIST);
            }
        }, mPropertyRecordRv);
    }

    @Override
    public void startLoading(String tag) {

    }

    @Override
    public void stopLoading(String tag) {

    }

    @Override
    public void updateView(Object o, String tag) {
        mPropertyRecordSwipefreshlayout.setRefreshing(false);
        PropertyVisitorListBean propertyRecordBean = (PropertyVisitorListBean) o;
        boolean isFresh = RequestStatus.REFRESH.equals(tag) ? true : false;
        if (propertyRecordBean != null) {
            List<PropertyVisitorListBean.DataBean> arrays = propertyRecordBean.getData();
            if (arrays != null) {
                offset += arrays.size();
                if (isFresh) {
                    propertyRecordAdapter.setNewData(arrays);
                } else {
                    propertyRecordAdapter.addData(arrays);
                }
                if (arrays.size() < limit) {
                    //第一页如果不够一页就不显示没有更多数据布局
                    propertyRecordAdapter.loadMoreEnd(isFresh);
                } else {
                    propertyRecordAdapter.loadMoreComplete();
                }
            }
        }
    }

    @Override
    public void onError(String tag) {

    }
}
