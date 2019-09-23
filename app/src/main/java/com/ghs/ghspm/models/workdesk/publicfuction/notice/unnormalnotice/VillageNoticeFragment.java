package com.ghs.ghspm.models.workdesk.publicfuction.notice.unnormalnotice;

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

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ghs.ghspm.R;
import com.ghs.ghspm.base.BaseFragment;
import com.ghs.ghspm.base.network.RequestStatus;
import com.ghs.ghspm.bean.NoticeBean;
import com.ghs.ghspm.models.workdesk.publicfuction.notice.NoticeAdapter;
import com.ghs.ghspm.models.workdesk.publicfuction.notice.NoticeContract;
import com.ghs.ghspm.models.workdesk.publicfuction.notice.NoticePresent;
import com.ghs.ghspm.tools.ActivityResultManager;
import com.ghs.ghspm.tools.PubUtil;
import com.ghs.ghspm.tools.StrUtils;
import com.ghs.ghspm.tools.UserInfoUtil;

import java.util.List;


public class VillageNoticeFragment extends BaseFragment<NoticeContract.INoticeView, NoticePresent> implements  NoticeContract.INoticeView{

        private View view;
    private RecyclerView mUnNormalNoticeRv;
    private NoticeAdapter noticeAdapter;
    private int offset = 0;
    private int limit = 10;
    private SwipeRefreshLayout mSwipeLayout;
    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public void onStop() {
        super.onStop();

    }

    @Override
    public void onResume() {
        super.onResume();


    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {


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
        boolean isFresh =  RequestStatus.REFRESH.equals(tag)?true:false;
        NoticeBean noticeBean = (NoticeBean) o;
        if (noticeBean != null) {
            if (noticeBean.getData() != null) {
                NoticeBean.DataBean dataBean = noticeBean.getData();
                if (dataBean.getRows()!=null) {
                    List<NoticeBean.DataBean.RowsBean> rowsBeanList = dataBean.getRows();
                    if (rowsBeanList.size() > 0) {
                        offset+=rowsBeanList.size();
                        if (isFresh) {
                            noticeAdapter.setNewData(rowsBeanList);
                        }else{
                            noticeAdapter.addData(rowsBeanList);
                        }
                    }
                    if (rowsBeanList.size() < limit) {
                        //第一页如果不够一页就不显示没有更多数据布局
                        noticeAdapter.loadMoreEnd(isFresh);
                    } else {
                        noticeAdapter.loadMoreComplete();
                    }
                }

            }
        }
    }



    @Override
    public void onError(String tag) {

    }


    private void initView(View view) {
        mUnNormalNoticeRv = (RecyclerView) view.findViewById(R.id.un_normal_notice_rv);
        noticeAdapter = new NoticeAdapter(R.layout.notice_item);
        initRecyclerview(mUnNormalNoticeRv, noticeAdapter, LinearLayoutManager.VERTICAL,false);
        noticeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                NoticeBean.DataBean.RowsBean dataBean = (NoticeBean.DataBean.RowsBean) adapter.getData().get(position);
                String url = dataBean.getUrl();
                Intent intent = new Intent(getContext(), UnNormalNoticeDetailActivity.class);
                if (StrUtils.isStringValueOk(url)) {
                    PubUtil.NOTICE_TYPE = 1;
                    intent.putExtra(ActivityResultManager.INSTITUTION_URL, url);
                }
                startActivity(intent);
            }
        });
        noticeAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                getPresenter().getNoticeList(offset,limit,3, UserInfoUtil.getInstance().getVillageId(), UserInfoUtil.getInstance().getUserId(), "");

            }
        }, mUnNormalNoticeRv);
        mSwipeLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeLayout);
        mSwipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                offset = 0;
                noticeAdapter.setEnableLoadMore(false);//这里的作用是防止下拉刷新的时候还可以上拉加载
                mSwipeLayout.setRefreshing(true);
                getPresenter().getNoticeList(offset,limit,3, UserInfoUtil.getInstance().getVillageId(), UserInfoUtil.getInstance().getUserId(), RequestStatus.REFRESH);


            }
        });
        noticeAdapter.setEmptyView(getAdapterEmptyView("很干净，一条小区公告也没有"));

    }




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.unnormal_notice_fragment_layout, container, false);
        initView(view);
        return view;
    }

    @Override
    protected void lazyLoad() {
        offset = 0;
        getPresenter().getNoticeList(offset,limit,3, UserInfoUtil.getInstance().getVillageId(), UserInfoUtil.getInstance().getUserId(), RequestStatus.REFRESH);

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
    protected NoticePresent createPresenter() {
        return new NoticePresent();
    }


}
