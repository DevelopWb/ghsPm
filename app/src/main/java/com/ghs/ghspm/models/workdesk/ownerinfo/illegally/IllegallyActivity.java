package com.ghs.ghspm.models.workdesk.ownerinfo.illegally;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ghs.ghspm.R;
import com.ghs.ghspm.base.BaseActivity;
import com.ghs.ghspm.bean.IllegallyMessageBean;
import com.ghs.ghspm.models.workdesk.ownerinfo.owneradapter.CarMessageAdapter;
import com.ghs.ghspm.tools.DividerItemDecoration;
import com.ghs.ghspm.tools.StrUtils;
import com.ghs.ghspm.tools.UserInfoUtil;

import java.util.List;

public class IllegallyActivity extends BaseActivity<IllegallyContract.IllegallyView, IllegallyPresenter> implements IllegallyContract.IllegallyView {

    private RecyclerView illegally_recylerview;
    private IlleagllyAdapter illeagllyAdapter;
    private TextView illegally_title;
    private SwipeRefreshLayout swipeLayout;
    private String carNum;
    int AdapterSize = 0;

    @Override
    public void getDate() {
        Intent intent = getIntent();
        //车牌
        carNum = intent.getStringExtra(CarMessageAdapter.CAR_NUM);
        if (StrUtils.isStringValueOk(carNum)) {
            String carNums = StrUtils.stringSecond(carNum);
            illegally_title.setText(carNums + "的违停记录");
            getPresenter().getIllegallyMessage(carNum, UserInfoUtil.getInstance().getVillageId() + "", IllegallyContract.REFRESH);

        }
        //刷新
        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //关闭加载更多
                illeagllyAdapter.setEnableLoadMore(false);
                swipeLayout.setRefreshing(true);
                getPresenter().getIllegallyMessage(carNum, UserInfoUtil.getInstance().getVillageId() + "", IllegallyContract.REFRESH);

            }
        });

    }

    @Override
    public void initLayoutView() {
        initActionBar("违停记录", "");
        initView();
    }

    public void initView() {
        illegally_recylerview = findViewById(R.id.illegally_recylerview);
        illegally_recylerview.setLayoutManager(new LinearLayoutManager(this));
        illeagllyAdapter = new IlleagllyAdapter(R.layout.illegally_item_layout, this);
        illegally_recylerview.setAdapter(illeagllyAdapter);
        illegally_recylerview.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        illeagllyAdapter.setEmptyView(getAdapterEmptyView("很干净，一条数据都没有"));
        illeagllyAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                illeagllyAdapter.loadMoreEnd();
            }
        }, illegally_recylerview);
        illegally_title = findViewById(R.id.illegally_title);
        swipeLayout = findViewById(R.id.swipeLayout);

    }

    @Override
    public IllegallyPresenter creatPresenter() {
        return new IllegallyPresenter();
    }

    @Override
    public void setLayout() {
        setContentView(R.layout.activity_illegally);
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
        swipeLayout.setRefreshing(false);
        switch (tag) {
            case IllegallyContract.ILLEGA_MESSAGE:
                break;
            case IllegallyContract.REFRESH:
                IllegallyMessageBean illegallyMessageBean = (IllegallyMessageBean) o;
                if (illegallyMessageBean != null) {
                    List<IllegallyMessageBean.DataBean> data = illegallyMessageBean.getData();
                    Log.i("TAG",data.size()+"-----");
                    illeagllyAdapter.setNewData(data);
                }

                break;
        }


    }

    @Override
    public void onError(String tag) {
        swipeLayout.setRefreshing(false);
        showToast(tag);
    }


    public View getView() {
        return View.inflate(this, R.layout.illegally_foot_layout, null);
    }

}
