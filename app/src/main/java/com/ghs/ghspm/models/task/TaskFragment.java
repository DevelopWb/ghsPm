package com.ghs.ghspm.models.task;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ghs.ghspm.R;
import com.ghs.ghspm.base.BaseFragment;
import com.ghs.ghspm.base.FragmentAdapter;
import com.ghs.ghspm.base.FragmentFactory;
import com.ghs.ghspm.base.network.RequestStatus;
import com.ghs.ghspm.models.main.MainContact;
import com.ghs.ghspm.tools.ActivityResultManager;
import com.ghs.ghspm.tools.EventBusProperty;
import com.ghs.ghspm.tools.EventManager;
import com.ghs.ghspm.tools.PubUtil;
import com.ghs.ghspm.tools.UserInfoUtil;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * created by tobato
 * created date 2019/7/5 11:01.
 * application   工单模块
 */

public class TaskFragment extends BaseFragment<TaskContract.ITaskView, TaskPresent> implements TaskContract.ITaskView, RequestStatus, View.OnClickListener {

    private TabLayout mHomeTab;
    private ViewPager mHomeVp;
    private MainContact.DrawerLayoutCallBack drawerLayoutCallBack;
    private View layoutview;
    private String[] tabs = {"待处理", "我发起的", "与我相关", "已完成"};
    private View view;
    /**
     * 测试小区的长度是多少
     */
    private TextView mVilliageNameTv;

    public static TaskFragment getInstance() {
        return FirstFragmentHolder.instatce;
    }



    @Override
    protected TaskPresent createPresenter() {
        return new TaskPresent();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (ActivityResultManager.PUBLISH_TASK == resultCode) {
            super.onActivityResult(requestCode, resultCode, data);
        }

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dealTaskRedPoint();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        layoutview = inflater.inflate(R.layout.home_fragment_layout, container, false);
        initView(layoutview);
        return layoutview;
    }

    @Override
    protected void lazyLoad() {
        dealTaskRedPoint();

    }

    private void initView(View view) {
        mHomeTab = (TabLayout) view.findViewById(R.id.home_tab);
        mHomeVp = (ViewPager) view.findViewById(R.id.home_vp);
        initViewPageWithTabLayout();
        mVilliageNameTv = (TextView) layoutview.findViewById(R.id.villiage_name_tv);
        mVilliageNameTv.setText(UserInfoUtil.getInstance().getVillageName());
        mVilliageNameTv.setOnClickListener(this);
    }

    /**
     * viewpage和tablayout绑定数据
     */
    private void initViewPageWithTabLayout() {

        FragmentManager fragmentManager = getChildFragmentManager();//外面如果还有一层fragment时，使用这个方法
//        FragmentManager fragmentManager = getSupportFragmentManager();//外面不是fragment包裹时，使用这个方法
        FragmentAdapter fragmentAdapter = new FragmentAdapter(fragmentManager, FragmentFactory.getmFragmentsOfHomePageOfTask(), getContext());
        mHomeVp.setAdapter(fragmentAdapter);
        mHomeTab.setupWithViewPager(mHomeVp);

        for (int i = 0; i < tabs.length; i++) {
            TabLayout.Tab tab = mHomeTab.getTabAt(i);
            if (tab != null) {
                tab.setCustomView(fragmentAdapter.getTabView(i, tabs));//自定义View

            }
        }
        //tab的字体选择器,默认黑色,选择时红色
        mHomeTab.setTabTextColors(ContextCompat.getColor(getContext(),R.color.app_black), ContextCompat.getColor(getContext(),R.color.app_default_blue));
        //tab的下划线颜色,默认是粉红色
        mHomeTab.setSelectedTabIndicatorColor(ContextCompat.getColor(getContext(),R.color.app_default_blue));
//        //更改tab下划线的宽度
        PubUtil.setIndicator(mHomeTab, 15, 15);
        //将tab下划线隐藏，也可以在xml里面配置高的值为0dp来实现隐藏
//        mHomeTab.setSelectedTabIndicatorHeight(0);

    }

    @Override
    protected void initFragmentData() {

    }

    @Override
    public void onResume() {
        super.onResume();


    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mHomeTab = null;
    }

    @Override
    protected void initFragmentView(View view) {
    }

    @Override
    protected View initFragmentViewLayout(LayoutInflater inflater, ViewGroup container) {
        return null;
    }

    /**
     * 处理任务模块红点的问题
     */
    private void dealTaskRedPoint() {
        if (mHomeTab != null) {
            int position = mHomeTab.getSelectedTabPosition();
            if (0 == position) {
                //清除tab小红点
                setTabOneUnreadTagStatus(false);
                //清除MainActivity中导航栏中的小红点
                Log.i(PubUtil.RED_POINT_TAG, "告诉main，清除MainActivity中导航栏中的小红点--------------");

                EventManager.sendStringMsg(EventBusProperty.WAIT_TO_DEAL_TASK_RED_POINT);
//                //刷新数据
//                EventManager.sendStringMsg(EventBusProperty.WAIT_TO_DEAL_TASK_REFRESH);
                Log.i(PubUtil.RED_POINT_TAG, "告诉待处理fragment，刷新界面--------------");

            } else {
                Log.i(PubUtil.RED_POINT_TAG, "校验下，是否需要显示tab小红点--------------");

                if (PubUtil.showTabRedPoint) {
                    //显示tab小红点
                    setTabOneUnreadTagStatus(true);
                } else {
                    setTabOneUnreadTagStatus(false);
                }


            }
        }
    }


    /**
     * 改变tab1中的未读标识的状态
     */
    private void setTabOneUnreadTagStatus(boolean allowedShow) {
        for (int i = 0; i < tabs.length; i++) {
            TabLayout.Tab tab = mHomeTab.getTabAt(i);
            if (tab != null) {
                View view = tab.getCustomView();
                TextView unReadTag = (TextView) view.findViewById(R.id.task_tab_item_unread_tag_tv);
                if (0 == i) {
                    if (allowedShow) {
                        unReadTag.setVisibility(View.VISIBLE);
                    } else {
                        unReadTag.setVisibility(View.GONE);
                    }
                }
            }

        }
    }

    /**
     * 判断tab0是否显示小红点
     *
     * @param str
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void receiveFromMainActivityToDealTabOneTagStatus(String str) {
        if (EventBusProperty.CHANGE_TAB_TASK_RED_POINT.equals(str)) {
            dealTaskRedPoint();
        } else if (EventBusProperty.HIND_TASK_TAB_RED_POINT.equals(str)) {
            //清除tab小红点
            setTabOneUnreadTagStatus(false);
            Log.i(PubUtil.RED_POINT_TAG, "隐藏tab小红点--------------");

        }else if (MainContact.CHANGE_VILLAGE.equals(str)) {
            mVilliageNameTv.setText(UserInfoUtil.getInstance().getVillageName());

        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.drawerLayoutCallBack = (MainContact.DrawerLayoutCallBack) context;
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

    }

    @Override
    public void onError(String tag) {

    }

    @Override
    public void onStart(String tag) {

    }

    @Override
    public void onSuccess(Object o, String tag) {
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.villiage_name_tv://点击告诉MainActivity弹出左侧窗口
                if (drawerLayoutCallBack != null) {
                    drawerLayoutCallBack.openDrawerLayout();
                }
                break;
        }
    }

    private static class FirstFragmentHolder {
        private static TaskFragment instatce = new TaskFragment();
    }
}
