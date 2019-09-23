package com.ghs.ghspm.models.workOrder.createOrder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.SearchView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ghs.ghspm.R;
import com.ghs.ghspm.base.BaseActivity;
import com.ghs.ghspm.bean.RoomSearchedBean;
import com.ghs.ghspm.models.workOrder.WorkOrderContract;
import com.ghs.ghspm.models.workOrder.WorkOrderPresent;
import com.ghs.ghspm.tools.ActivityResultManager;

import java.util.List;

/**
 * created by tobato
 * created date 2019/7/19 11:41.
 * application   选择房间号
 */
public class SearchRoomActivity extends BaseActivity<WorkOrderContract.IWorkOrderView, WorkOrderPresent> implements WorkOrderContract.IWorkOrderView {

    private SearchView mMainSearchview;
    private RecyclerView mSearchRoomRv;
    private SearchRommAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public WorkOrderPresent creatPresenter() {
        return new WorkOrderPresent();
    }

    @Override
    public void setLayout() {
        setContentView(R.layout.activity_search_room);
        initView();
        initActionBar("选择房间号", "",R.drawable.bg_gray_only_bottom_deeper_gray_shape_1px);
    }

    @Override
    public void initLayoutView() {

    }

    @Override
    public void getDate() {
    }

    @Override
    public void actionBarRightTvOnClick() {

    }


    private void initView() {

        mMainSearchview = (SearchView) findViewById(R.id.search_room_sv);
        int id = mMainSearchview.getContext().getResources().getIdentifier("android:id/search_src_text", null, null);
        EditText textView = (EditText) mMainSearchview.findViewById(id);
        if (textView != null) {
            textView.setTextSize(14);//字体、提示字体大小
        }
        mSearchRoomRv = (RecyclerView) findViewById(R.id.search_room_rv);
        adapter = new SearchRommAdapter(R.layout.search_room_item);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                RoomSearchedBean.DataBean dataBean = (RoomSearchedBean.DataBean) adapter.getData().get(position);
                Intent intent = new Intent();
                intent.putExtra(INTENT_KEY, dataBean);
                setResult(ActivityResultManager.CREATE_ORDER_SEACHROOM, intent);
                finish();
            }
        });
        initRecyclerview(mSearchRoomRv, adapter, LinearLayoutManager.VERTICAL,false);
        mMainSearchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (!TextUtils.isEmpty(query)) {
                    getPresenter().searchRoomNum(mUserInfoUtil.getVillageId(), query, WorkOrderContract.SEARCH_ROOM_NUM);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
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
        RoomSearchedBean roomSearchedBean = (RoomSearchedBean) o;
        if (roomSearchedBean != null) {
            List<RoomSearchedBean.DataBean> dataBean = roomSearchedBean.getData();
            if (dataBean != null) {
                adapter.setNewData(dataBean);
                if (dataBean.size()==0) {
                    showToast("没有搜索到相关联的房间");
                }
            }
        }

    }

    @Override
    public void onError(String tag) {

    }

}
