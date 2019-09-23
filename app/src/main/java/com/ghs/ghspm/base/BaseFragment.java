package com.ghs.ghspm.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ghs.ghspm.MyApplication;
import com.ghs.ghspm.R;
import com.ghs.ghspm.models.main.MainContact;
import com.ghs.ghspm.tools.DividerItemDecoration;
import com.ghs.ghspm.tools.EventBusProperty;
import com.ghs.ghspm.tools.EventManager;
import com.ghs.ghspm.tools.ScreenUtils;
import com.ghs.ghspm.tools.StrUtils;
import com.ghs.ghspm.tools.UserInfoUtil;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;


/**
 * created by tobato
 * created date 2019/7/12 11:03.
 * application   fragment 基类
 */
public abstract class BaseFragment<V, T extends BasePresent<V>> extends Fragment {
    private static final String STATE_SAVE_IS_HIDDEN = "STATE_SAVE_IS_HIDDEN";
    public String TAG = getClass().getSimpleName() + "";
    protected T mPresenter;
    //定义一个View用来保存Fragment创建的时候使用打气筒工具进行的布局获取对象的存储
    protected View view;

    private TextView activityNoDataTv;
    private ImageView mHeaderLeftIv;
    /**
     * 设置
     */
    private TextView mHeaderTitleTv;
    /**
     * 设置
     */
    private TextView mHeaderRightTv;
    private View mRoot;

    /**
     * 是否创建了View
     */
//    private boolean isCreateView;

    /**
     * 当从另一个activity回到fragment所在的activity
     * 当fragment回调onResume方法的时候，可以通过这个变量判断fragment是否可见，来决定是否要刷新数据
     */
    protected UserInfoUtil mUserInfoUtil;
    private boolean isHidden;//fragment是否隐藏


    /*
     * 此方法在viewpager嵌套fragment时会回调
     * 查看FragmentPagerAdapter源码中instantiateItem和setPrimaryItem会调用此方法
     * 在所有生命周期方法前调用
     * 这个基类适用于在viewpager嵌套少量的fragment页面
     * 该方法是第一个回调，可以将数据放在这里处理（viewpager默认会预加载一个页面）
     * 只在fragment可见时加载数据，加快响应速度
     * */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            mPresenter = createPresenter();//创建presenter
            mUserInfoUtil = UserInfoUtil.getInstance();
            if (null != mPresenter) {
                mPresenter.onAttachView((V) this);
            }
            lazyLoad();
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        isHidden = hidden;
        if (!hidden) {
            lazyLoad();
        }
        super.onHiddenChanged(hidden);
    }

    /**
     * 初始化actionBar
     */
    public void initActionBar(View view, String titleContent, String rightTvContent) {

        mHeaderLeftIv = (ImageView) view.findViewById(R.id.header_left_iv);
        mHeaderTitleTv = (TextView) view.findViewById(R.id.header_title_tv);
        mHeaderTitleTv.setText(titleContent);
        mHeaderRightTv = (TextView) view.findViewById(R.id.header_right_tv);
        if (StrUtils.isStringValueOk(rightTvContent)) {
            mHeaderRightTv.setText(rightTvContent);
        } else {
            mHeaderRightTv.setVisibility(View.INVISIBLE);
        }

    }

    /**
     * 工作台中的菜单项
     *
     * @return
     */
    public List<String> getWorkDeskMenueNames() {
        return getBaseActivity().getWorkDeskMenueNames();
    }


    /**
     * 获取present实例
     *
     * @return
     */
    public T getPresenter() {
        return mPresenter;
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void stringMsgReceived(String str) {
        if (MainContact.CHANGE_VILLAGE.equals(str)) {//
                villageChanged();
        } else if (EventBusProperty.RECEIVE_WORK_ORDER_NOTICE.equals(str)) {
            //收到工单消息
            receivedOrderMsg();

        }
    }

    /**
     * 切换小区
     */
    protected void villageChanged() {
    }

    /**
     * 收到工单消息
     */
    protected void receivedOrderMsg() {
    }

    /**
     * 创建Presenter对象
     */
    protected abstract T createPresenter();

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 当Fragment进行创建的时候执行的方法
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initWidows();

    }
    @Override
    public void onStart() {
        super.onStart();
        EventManager.getLibraryEvent().register(this);//注册

    }

    @Override
    public void onStop() {
        super.onStop();
        EventManager.getLibraryEvent().unregister(this);
    }
    @Override
    public void onAttach(Context context) {
        if (mUserInfoUtil == null) {
            mUserInfoUtil = UserInfoUtil.getInstance();
        }
        if (mPresenter == null) {
            mPresenter = createPresenter();//创建presenter
        }
        if (null != mPresenter) {
            mPresenter.onAttachView((V) this);
        }
        super.onAttach(context);
    }

    /**
     * 初始化窗口 在界面为初始化之前调用
     */
    protected void initWidows() {
        //设置屏幕适配 360为设计图尺寸px/2
        ScreenUtils screenUtils = ScreenUtils.getInstance(getContext().getApplicationContext());
        if (screenUtils.isPortrait()) {

            screenUtils.adaptScreen4VerticalSlide(getActivity(), 360);
        } else {
            screenUtils.adaptScreen4HorizontalSlide(getActivity(), 360);
        }

    }

    /**
     * 这个方法是关于Fragment完成创建的过程中，进行界面填充的方法,该方法返回的是一个view对象
     * 在这个对象中封装的就是Fragment对应的布局
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        if(mRoot == null){
        mRoot = initFragmentViewLayout(inflater, container);
//            isCreateView = true;
        initFragmentView(mRoot);
//            onVisible();
//        }
        return mRoot;
    }


    /**
     * 这个方法当onCreateView方法中的view创建完成之后，执行
     * 在inflate完成view的创建之后，可以将对应view中的各个控件进行查找findViewById
     */
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        initFragmentView(view);
    }

    /**
     * fragment第一次可见的时候回调此方法
     */
    protected abstract void lazyLoad();


    /**
     * 这个方法是在Fragment完成创建操作之后，进行数据填充操作的时候执行的方法
     */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initFragmentData();
    }

    /**
     * 网络数据填充的操作
     */
    protected abstract void initFragmentData();

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putBoolean(STATE_SAVE_IS_HIDDEN, isHidden());
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (null != mPresenter) {
            mPresenter.detachView();
        }
        if (MyApplication.OpenRefWatcher) {
            MyApplication.getRefWatcher(getBaseActivity()).watch(this);
        }
    }

    /**
     * 进行findViewById的操作
     *
     * @param view 打气筒生成的View对象
     */
    protected abstract void initFragmentView(View view);

    /**
     * 完成打气筒操作
     */
    protected abstract View initFragmentViewLayout(LayoutInflater inflater, ViewGroup container);


    public View getAdapterEmptyView(String text) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.empty_view, null);
        TextView noticeTv = view.findViewById(R.id.empty_view_tv);
        noticeTv.setText(text);
        return view;
    }


    /**
     * 初始化recyclerview LinearLayoutManager
     */
    public void initRecyclerview(RecyclerView recyclerView, BaseQuickAdapter baseQuickAdapter, @RecyclerView.Orientation int orientation, boolean reverseLayout) {
        LinearLayoutManager managere = new LinearLayoutManager(getContext(), orientation, reverseLayout);
        recyclerView.setLayoutManager(managere);
        recyclerView.setAdapter(baseQuickAdapter);
    }

    /**
     * 初始化recyclerview LinearLayoutManager
     */
    public void initRecyclerview(RecyclerView recyclerView, BaseQuickAdapter baseQuickAdapter, @RecyclerView.Orientation int orientation, boolean reverseLayout, boolean noSlide) {
        LinearLayoutManager managere = new LinearLayoutManager(getContext(), orientation, reverseLayout) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        recyclerView.setLayoutManager(managere);
        recyclerView.setAdapter(baseQuickAdapter);
    }


    /**
     * 添加分割线
     *
     * @param recyclerView
     * @param haveTopLine
     * @param isHorizontalDivider 水平分割线
     * @param haveEndLine         最后一个item下是否划线
     */
    public void addDivider(boolean isHorizontalDivider, RecyclerView recyclerView, boolean haveTopLine, boolean haveEndLine) {
        DividerItemDecoration dividerItemDecoration;
        if (isHorizontalDivider) {
            dividerItemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL_LIST, R.drawable.divider_hor_line_sp);
        } else {
            dividerItemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.HORIZONTAL_LIST);
        }
        if (haveTopLine) {
            if (haveEndLine) {
                dividerItemDecoration.setDividerMode(DividerItemDecoration.ALL);
            } else {
                dividerItemDecoration.setDividerMode(DividerItemDecoration.START);
            }
        } else {
            if (haveEndLine) {
                dividerItemDecoration.setDividerMode(DividerItemDecoration.END);
            } else {
                dividerItemDecoration.setDividerMode(DividerItemDecoration.INSIDE);

            }
        }
        recyclerView.addItemDecoration(dividerItemDecoration);
    }


    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha 屏幕透明度0.0-1.0 1表示完全不透明
     */
    public void setBackgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getActivity().getWindow()
                .getAttributes();
        lp.alpha = bgAlpha;
        getActivity().getWindow().setAttributes(lp);
    }

    /**
     * 获取BaseActivity对象
     *
     * @return
     */
    protected BaseActivity getBaseActivity() {
        return (BaseActivity) getActivity();
    }
}