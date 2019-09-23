package com.ghs.ghspm.models.task.pushedTaskShow;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;

import com.ghs.ghspm.R;
import com.ghs.ghspm.base.BaseActivity;
import com.ghs.ghspm.base.BasePresent;
import com.ghs.ghspm.base.FragmentAdapter;
import com.ghs.ghspm.base.FragmentFactory;
import com.ghs.ghspm.tools.EventBusProperty;
import com.ghs.ghspm.tools.EventManager;
import com.ghs.ghspm.tools.PubUtil;


/**
 * created by tobato
 * created date 2018/11/15 10:37.
 * application   用于展示推送的临时消息的跳转
 */

public class TaskFragmentActivity extends BaseActivity {


    private TabLayout mHomeTab;
    private ViewPager mHomeVp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PubUtil.taskFragmentActivityFinished = false;
    }

    @Override
    public void getDate() {

    }

    @Override
    public void initLayoutView() {
        initView();
        initActionBar("任务","");
    }


    @Override
    public void setLayout() {
        setContentView(R.layout.activity_task_fragment);

    }
    private void initView() {
        mHomeTab = (TabLayout) findViewById(R.id.home_tab);
        mHomeVp = (ViewPager) findViewById(R.id.home_vp);
        initViewPageWithTabLayout();

    }

    @Override
    public void actionBarRightTvOnClick() {
    }
    /**
     * 初始化viewpage和tablayout
     */
    private void initViewPageWithTabLayout() {

        FragmentManager fragmentManager = getSupportFragmentManager();
        mHomeVp.setAdapter(new FragmentAdapter(fragmentManager, FragmentFactory.getmFragmentsOfPushedTask()));
        mHomeTab.setupWithViewPager(mHomeVp);
        String[] tabs = {"待处理", "我发起的", "与我相关", "已完成"};
        for (int i = 0; i < tabs.length; i++) {
            if (mHomeTab != null) {
                TabLayout.Tab tab = mHomeTab.getTabAt(i);
                tab.setText(tabs[i]);
            }

        }
        //tab的字体选择器,默认黑色,选择时红色
        mHomeTab.setTabTextColors(ContextCompat.getColor(this,R.color.app_black), ContextCompat.getColor(this,R.color.app_default_blue));
        //tab的下划线颜色,默认是粉红色
        mHomeTab.setSelectedTabIndicatorColor(ContextCompat.getColor(this,R.color.app_default_blue));
        PubUtil.setIndicator(mHomeTab, 15, 15);

    }
    @Override
    public BasePresent creatPresenter() {
        return null;
    }

    @Override
    public void onBackPressed() {
        PubUtil.taskFragmentActivityFinished = true;
        EventManager.sendStringMsg(EventBusProperty.WAIT_TO_DEAL_TASK_RED_POINT);
        super.onBackPressed();
    }
}
