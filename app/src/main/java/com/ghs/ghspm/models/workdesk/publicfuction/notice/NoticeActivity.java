package com.ghs.ghspm.models.workdesk.publicfuction.notice;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.ghs.ghspm.R;
import com.ghs.ghspm.base.BaseActivity;
import com.ghs.ghspm.base.BasePresent;
import com.ghs.ghspm.base.FragmentAdapter;
import com.ghs.ghspm.base.FragmentFactory;
import com.ghs.ghspm.tools.ActivityResultManager;
import com.ghs.ghspm.tools.PubUtil;

/**
 * created by 8级大的狂风
 * created date 2018/9/21 14:43.
 * application  公告
 */
public class NoticeActivity extends BaseActivity implements View.OnClickListener {

    private TabLayout mNoticeTab;
    private ViewPager mNoticeVp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void getDate() {

    }

    @Override
    public void initLayoutView() {
        initView();
        initActionBar("公告",null);

    }

    @Override
    public void setLayout() {
        setContentView(R.layout.activity_notice);

    }

    @Override
    public void actionBarRightTvOnClick() {

    }

    @Override
    public BasePresent creatPresenter() {
        return null;
    }

    private void initView() {
        mNoticeTab = (TabLayout) findViewById(R.id.notice_tab);
        mNoticeVp = (ViewPager) findViewById(R.id.notice_vp);
        initViewPageWithTabLayout();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
        }
    }
    /**
     * 初始化viewpage和tablayout
     */
    private void initViewPageWithTabLayout() {

        FragmentManager fragmentManager = getSupportFragmentManager();
        mNoticeVp.setAdapter(new FragmentAdapter(fragmentManager, FragmentFactory.getmFragmentsOfNotice()));
        mNoticeTab.setupWithViewPager(mNoticeVp);
        String[] tabs = {"通知", "制度","小区公告"};
        for (int i = 0; i < tabs.length; i++) {
            TabLayout.Tab tab = mNoticeTab.getTabAt(i);
            if (tab != null) {
                tab.setText(tabs[i]);

            }
        }
        //tab的字体选择器,默认黑色,选择时红色
        mNoticeTab.setTabTextColors(ContextCompat.getColor(this,R.color.app_black),ContextCompat.getColor(this,R.color.app_default_blue));
        //tab的下划线颜色,默认是粉红色
        mNoticeTab.setSelectedTabIndicatorColor(ContextCompat.getColor(this,R.color.app_default_blue));
        PubUtil.setIndicator(mNoticeTab, 30, 30);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (ActivityResultManager.PULISH_NOTICE==resultCode||ActivityResultManager.NOEMAL_NOTICE_DETAIL==resultCode) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
