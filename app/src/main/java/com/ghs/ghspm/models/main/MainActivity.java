package com.ghs.ghspm.models.main;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ghs.ghspm.R;
import com.ghs.ghspm.base.BaseActivity;
import com.ghs.ghspm.base.network.RequestStatus;
import com.ghs.ghspm.bean.LoginBean;
import com.ghs.ghspm.bean.MultiWorkDeskMenuBean;
import com.ghs.ghspm.bean.ToolFormBean;
import com.ghs.ghspm.bean.VillagesBean;
import com.ghs.ghspm.models.login.LoginActivity;
import com.ghs.ghspm.models.login.LoginContract;
import com.ghs.ghspm.models.login.LoginModel;
import com.ghs.ghspm.models.login.selectProperty.SelectPropertyActivity;
import com.ghs.ghspm.models.mine.minefragment.MineFragment;
import com.ghs.ghspm.models.mine.systemNotice.SystemNoticeActivity;
import com.ghs.ghspm.models.push.AliPushManager;
import com.ghs.ghspm.models.unread.UnReadContract;
import com.ghs.ghspm.models.unread.UnReadTagModel;
import com.ghs.ghspm.models.workOrder.WorkOrderFragment;
import com.ghs.ghspm.models.workdesk.WorkDeskFragment;
import com.ghs.ghspm.models.workdesk.publicfuction.notice.NoticeActivity;
import com.ghs.ghspm.models.workdesk.publicfuction.universalkey.UniversalKeyActivity;
import com.ghs.ghspm.models.workdesk.signAndTable.SignAndTableActivity;
import com.ghs.ghspm.tools.ActivityManagerTool;
import com.ghs.ghspm.tools.ActivityResultManager;
import com.ghs.ghspm.tools.CalendarUtil;
import com.ghs.ghspm.tools.EventBusProperty;
import com.ghs.ghspm.tools.EventManager;
import com.ghs.ghspm.tools.HawkProperty;
import com.ghs.ghspm.tools.MaterialAlterDialogManager;
import com.ghs.ghspm.tools.MobileInfoUtils;
import com.ghs.ghspm.tools.PubUtil;
import com.ghs.ghspm.tools.StrUtils;
import com.ghs.ghspm.tools.ToastUtil;
import com.orhanobut.hawk.Hawk;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Date;
import java.util.List;


public class MainActivity extends BaseActivity<MainContact.IMainView, MainPresent> implements MainContact.IMainView, RequestStatus, View.OnClickListener, MainContact.DrawerLayoutCallBack {

    private BluetoothAdapter myBluetoothAdapter;
    private FrameLayout mContentFl;
    private ImageView mNavigationHomeIv;
    /**
     * 首页
     */
    private TextView mNavigationHomeTv;
    private ConstraintLayout mNavigationHomeCl;
    private ImageView mNavigationMineIv;
    /**
     * 我的
     */
    private TextView mNavigationMineTv;
    private LinearLayout mNavigationMineLl;
    private LinearLayout mNavigationBottom;
    private ImageView mNavigationWorkIv;
    /**
     * 我的工作
     */
    private TextView mNavigationWorkTv;
    public TextView mUnreadAmountTv;
    private LinearLayout mNavigationWorkLl;
    private boolean isExit = false;//确定退出程序
    private int currentPostion = 0;//导航栏 当前的位置
    private NavigationView mMainNv;
    private DrawerLayout mMainDrawerlayout;
    private ImageView mDrawerbleLayoutUserHeadIv;
    private TextView mDrawerbleLayoutUserNameTv;
    /**
     * 切换空间
     */
    private TextView mDrawerbleLayoutChangeOrganizeTv;
    private TextView mDrawerbleLayoutOrganizeNameTv;
    /**
     * 小区
     */
    private LinearLayout mDrawerbleLayoutVillagesLl;
    private RecyclerView mDrawerbleLayoutVillagesRv;
    private VillagesAdapter villagesAdapter;
    //    private TextView mUnreadAmountMineTv;
    private String WORK_ORDER_KEY = "workOrderFragment";//工单模块
    private String WORK_DESK_KEY = "workDeskFragment";//工作台模块
    private String MINE_KEY = "mineFragment";//我的 模块
    private WorkOrderFragment workOrderFragment;
    private WorkDeskFragment workDeskFragment;
    private MineFragment mineFragment;
    private TextView mDrawerbleLayoutUserHeadTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            workOrderFragment = (WorkOrderFragment) getSupportFragmentManager().findFragmentByTag(WORK_ORDER_KEY);
            workDeskFragment = (WorkDeskFragment) getSupportFragmentManager().findFragmentByTag(WORK_DESK_KEY);
            mineFragment = (MineFragment) getSupportFragmentManager().findFragmentByTag(MINE_KEY);
        } else {
            initFragments();
        }
        initBottomViewStatus(0);
        new LoginModel().getToolFormList(mUserInfoUtil.getPropertyId(), mUserInfoUtil.getVillageId(), mUserInfoUtil.getUserId(), LoginContract.GET_TOOL_FORM_LIST, this);
        new UnReadTagModel().clearUnReadAmount(mUserInfoUtil.getUserId(), this, UnReadContract.CLEAR_UNREAD_AMOUNT);
        //如果保存了已选择的小区
//        if (Hawk.contains(HawkProperty.SELECTED_VILLAGE_ID + mUserInfoUtil.getUserId())) {
//            int villageId = Hawk.get(HawkProperty.SELECTED_VILLAGE_ID + mUserInfoUtil.getUserId());
//            new LoginModel().getUserMenu(mUserInfoUtil.getPropertyId(), villageId, mUserInfoUtil.getUserId(), MainContact.USER_MENU, this);
//        } else {
//            //请求权限接口
//            new LoginModel().getUserMenu(mUserInfoUtil.getPropertyId(), mUserInfoUtil.getVillageId(), mUserInfoUtil.getUserId(), MainContact.USER_MENU, this);
//
//        }
        PubUtil.ScreenWidth = getScreenWidth();
        PubUtil.ScreenHeight = getScreenHeight();
        myBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        //状态栏字体颜色是否为深色
        mImmersionBar.statusBarDarkFont(true, 0.2f).init();
        if (getIntent() != null) {
            String noticeType = getIntent().getStringExtra(AliPushManager.PUSH_NOTICE_TYPE);
            if (AliPushManager.PUSH_PM_INNER_NOTICE.equals(noticeType)) {
                startActivity(new Intent(this, NoticeActivity.class));
            } else if (AliPushManager.PUSH_FORM_TASK.equals(noticeType)) {
                startActivity(new Intent(this, SignAndTableActivity.class));
            } else if (AliPushManager.PUSH_FORM_TASK.equals(noticeType)) {
                startActivity(new Intent(this, SystemNoticeActivity.class));
            }
        }

        checkNoticePromission();

    }

    /**
     * 初始化所以得fragment
     */
    private void initFragments() {
        if (workOrderFragment == null) {
            workOrderFragment = new WorkOrderFragment();
        }
        if (workDeskFragment == null) {
            workDeskFragment = new WorkDeskFragment();
        }
        if (mineFragment == null) {
            mineFragment =new  MineFragment();
        }
    }

    /**
     * 初始化fragment
     *
     * @param i
     */
    private void initFragmentSelected(int i) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        hindFragments(fragmentTransaction);
        initFragments();
        switch (i) {
            case 0:
                if (!workOrderFragment.isAdded()) {
                    fragmentTransaction.add(R.id.content_fl, workOrderFragment, WORK_ORDER_KEY);
                }
                fragmentTransaction.show(workOrderFragment);
                break;
            case 1:
                if (!workDeskFragment.isAdded()) {
                    fragmentTransaction.add(R.id.content_fl, workDeskFragment, WORK_DESK_KEY);
                }
                fragmentTransaction.show(workDeskFragment);
                break;
            case 2:
                if (!mineFragment.isAdded()) {
                    fragmentTransaction.add(R.id.content_fl, mineFragment, MINE_KEY);
                }
                fragmentTransaction.show(mineFragment);
                break;
            default:
                break;
        }
        fragmentTransaction.commitAllowingStateLoss();
    }

    /**
     * 隐藏所有的fragment
     *
     * @param fragmentTransaction
     */
    private void hindFragments(FragmentTransaction fragmentTransaction) {
        if (workOrderFragment != null) {
            fragmentTransaction.hide(workOrderFragment);
        }
        if (workDeskFragment != null) {
            fragmentTransaction.hide(workDeskFragment);
        }
        if (mineFragment != null) {
            fragmentTransaction.hide(mineFragment);
        }
    }

    @Override
    public MainPresent creatPresenter() {
        return new MainPresent();
    }

    @Override
    public void setLayout() {
        setContentView(R.layout.activity_main);
    }

    @Override
    public void initLayoutView() {
        initView();
    }

    @Override
    public void getDate() {

    }

    @Override
    protected void onDestroy() {
        PubUtil.LOGIN_ENTER = 1;
        PubUtil.tokenExpiredFirstNotice = true;

        super.onDestroy();
    }

    @Override
    public void actionBarRightTvOnClick() {

    }

    @Override
    public void onPause() {
        super.onPause();

    }

    @Override
    protected void onResume() {
        super.onResume();
        AliPushManager.getInstance().seachBoundAlias();
        //如果保存了已选择的小区
        if (Hawk.contains(HawkProperty.SELECTED_VILLAGE_ID + mUserInfoUtil.getUserId())) {
            getPresenter().getUserPropertyRelation(mUserInfoUtil.getUserId(), mUserInfoUtil.getPropertyId(), mUserInfoUtil.getVillageId(), MainContact.GET_USER_PROPERTY_RELATION);
        }

    }

    private void initView() {
        mContentFl = (FrameLayout) findViewById(R.id.content_fl);
        mNavigationHomeIv = (ImageView) findViewById(R.id.navigation_home_iv);
        mNavigationHomeTv = (TextView) findViewById(R.id.navigation_home_tv);
        mNavigationHomeCl = (ConstraintLayout) findViewById(R.id.navigation_task_cl);
        mNavigationHomeCl.setOnClickListener(this);
        mNavigationMineIv = (ImageView) findViewById(R.id.navigation_mine_iv);
        mNavigationMineTv = (TextView) findViewById(R.id.navigation_mine_tv);
        mNavigationMineLl = (LinearLayout) findViewById(R.id.navigation_mine_ll);
        mNavigationMineLl.setOnClickListener(this);
        mNavigationBottom = (LinearLayout) findViewById(R.id.navigation_bottom);
        mNavigationWorkIv = (ImageView) findViewById(R.id.navigation_work_iv);
        mNavigationWorkTv = (TextView) findViewById(R.id.navigation_work_tv);
        mUnreadAmountTv = (TextView) findViewById(R.id.unread_amount_task_tv);
        mNavigationWorkLl = (LinearLayout) findViewById(R.id.navigation_work_desk_ll);
        mNavigationWorkLl.setOnClickListener(this);
        drawerlayotLogic();

    }

    /**
     * 抽屉布局里面的逻辑
     */
    private void drawerlayotLogic() {

        mDrawerbleLayoutUserHeadIv = (ImageView) findViewById(R.id.drawerbleLayout_user_head_iv);
        mDrawerbleLayoutUserHeadTv = (TextView) findViewById(R.id.drawerbleLayout_user_head_tv);
        mDrawerbleLayoutUserHeadIv.setOnClickListener(this);
        initHeadPic();
        mDrawerbleLayoutUserNameTv = (TextView) findViewById(R.id.drawerbleLayout_userName_tv);
        mDrawerbleLayoutChangeOrganizeTv = (TextView) findViewById(R.id.drawerbleLayout_change_organize_tv);
        mDrawerbleLayoutChangeOrganizeTv.setOnClickListener(this);
        mDrawerbleLayoutOrganizeNameTv = (TextView) findViewById(R.id.drawerbleLayout_organize_name_tv);
        mDrawerbleLayoutVillagesLl = (LinearLayout) findViewById(R.id.drawerbleLayout_villages_ll);
        mDrawerbleLayoutVillagesRv = (RecyclerView) findViewById(R.id.drawerbleLayout_villages_rv);
        villagesAdapter = new VillagesAdapter(R.layout.village_item);

        initRecyclerview(mDrawerbleLayoutVillagesRv, villagesAdapter, LinearLayoutManager.VERTICAL, false);
        villagesAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                mMainDrawerlayout.closeDrawers();
                VillagesBean.DataBean dataBean = (VillagesBean.DataBean) adapter.getData().get(position);
                showMaterialProgressDialog();
                AliPushManager.getInstance().bindAll(mUserInfoUtil.getUserId());
                getPresenter().getUserPropertyRelation(mUserInfoUtil.getUserId(), mUserInfoUtil.getPropertyId(), dataBean.getId(), MainContact.CHANGE_VILLAGE);
            }
        });
        mMainDrawerlayout = (DrawerLayout) findViewById(R.id.main_drawerlayout);
        mMainDrawerlayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {
//请求网络 获取小区列表
                drawerView.setClickable(true);
                initHeadPic();
                mDrawerbleLayoutOrganizeNameTv.setText(mUserInfoUtil.getPropertyName());
                mDrawerbleLayoutUserNameTv.setText(mUserInfoUtil.getUserName());
                getPresenter().getVillagesList(mUserInfoUtil.getUserId(), mUserInfoUtil.getPropertyId(), MainContact.GET_VILLAGE_LIST);
            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {
                if (PubUtil.CHANGE_PROPERTY) {
                    startActivity(new Intent(MainActivity.this, SelectPropertyActivity.class));
                }
            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
    }

    /**
     * 初始化头图片
     */
    private void initHeadPic() {
        String picPath = mUserInfoUtil.getUserHeadImage();
        if (StrUtils.isStringValueOk(picPath)) {
            setHeadPicBgResource(mDrawerbleLayoutUserHeadIv, mDrawerbleLayoutUserHeadTv, mUserInfoUtil.getUserName(), picPath, mUserInfoUtil.getHeadDefaultBgColor(), true);
        }
    }


    /**
     * 检测通知相关权限，首先判断通知权限是否开启，其次是自启动权限是否开启，通知权限如果没有开启，一天提醒一次，自启动权限
     * 如果没有开启，一天提醒一次 可以设置不再提醒，因为监听不到是否开启自
     */
    private void checkNoticePromission() {
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        boolean areNotificationsEnabled = notificationManagerCompat.areNotificationsEnabled();
        //未开启允许通知权限
        if (!areNotificationsEnabled) {
            String savedDate = Hawk.get(HawkProperty.NOTICE_ALLOWED);
            if (StrUtils.isStringValueOk(savedDate)) {
                if (!CalendarUtil.compareDateSize("yyyy-MM-dd", CalendarUtil.getTimeFromDate("yyyy-MM-dd", new Date()), savedDate)) {
                    return;
                }
            }

            MaterialAlterDialogManager.getInstance().showMaterialAlterDialog(this, "通知提醒", R.mipmap.ghpm_icon,
                    "为了便于您收到通知，建议您开启通知\n\n注：不开启通知就不能收到软件发出的通知提醒",
                    "前往设置", "下次再说", new MaterialAlterDialogManager.OnAlterDialogCallBack() {
                        @Override
                        public void leftBtClicked() {
                            MobileInfoUtils.jumpToAllowNotice(MainActivity.this);
                            Hawk.put(HawkProperty.NOTICE_ALLOWED, CalendarUtil.getNextDayTime(1, "yyyy-MM-dd"));
                        }

                        @Override
                        public void rightBtClicked() {
                            Hawk.put(HawkProperty.NOTICE_ALLOWED, CalendarUtil.getNextDayTime(1, "yyyy-MM-dd"));

                        }
                    });

            return;
        }

        boolean autorun = Hawk.get(HawkProperty.AUTORUN_NOTICE, true);//自启动权限选择提醒,默认提醒，一天一次
        if (autorun) {
            MaterialAlterDialogManager.getInstance().showMaterialAlterDialog(this, "温馨提示", R.mipmap.ghpm_icon,
                    "如果您想在软件被后台清理掉后也能收到消息提醒(类似微信)\n建议您开启自启动权限，能够有效的接收消息",
                    "前往设置", "不再提醒", new MaterialAlterDialogManager.OnAlterDialogCallBack() {
                        @Override
                        public void leftBtClicked() {
                            Hawk.put(HawkProperty.AUTORUN_NOTICE, false);
                            MobileInfoUtils.jumpStartInterface(MainActivity.this);
                        }

                        @Override
                        public void rightBtClicked() {
                            Hawk.put(HawkProperty.AUTORUN_NOTICE, false);
                        }
                    });
        }
    }

    /**
     * 初始化底部控件的状态
     *
     * @param i
     */
    private void initBottomViewStatus(int i) {
        mNavigationHomeIv.setImageResource(R.mipmap.home_press_normal);
        mNavigationMineIv.setImageResource(R.mipmap.mine_icon_normal);
        mNavigationWorkIv.setImageResource(R.mipmap.my_work_icon_unselected);
        mNavigationHomeTv.setTextColor(ContextCompat.getColor(this, R.color.app_black));
        mNavigationWorkTv.setTextColor(ContextCompat.getColor(this, R.color.app_black));
        mNavigationMineTv.setTextColor(ContextCompat.getColor(this, R.color.app_black));

        switch (i) {
            case 0:
                initFragmentSelected(0);
                PubUtil.TASKMODULE = true;
                //状态栏字体颜色是否为深色
                mImmersionBar.statusBarDarkFont(true, 0.2f).init();
                mNavigationHomeIv.setImageResource(R.mipmap.home_press_icon);
                mNavigationHomeTv.setTextColor(ContextCompat.getColor(this, R.color.app_default_blue));
                break;
            case 1:
                initFragmentSelected(1);
                PubUtil.TASKMODULE = false;
                mImmersionBar.statusBarDarkFont(false).init();
                mNavigationWorkIv.setImageResource(R.mipmap.my_work_icon);
                mNavigationWorkTv.setTextColor(ContextCompat.getColor(this, R.color.app_default_blue));
                break;
            case 2:
                initFragmentSelected(2);
                PubUtil.TASKMODULE = false;
                mImmersionBar.statusBarDarkFont(true, 0.2f).init();
                mNavigationMineIv.setImageResource(R.mipmap.mine_press_icon);
                mNavigationMineTv.setTextColor(ContextCompat.getColor(this, R.color.app_default_blue));
                break;
            default:
                break;
        }
    }

    /**
     * 收到任务模块关于小红点的问题
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void receivedMsgAboutRedPointOfTask(String str) {
        if (EventBusProperty.WAIT_TO_DEAL_TASK_RED_POINT.equals(str)) {
            Log.i(PubUtil.RED_POINT_TAG, "清除MainActivity中导航栏中的小红点--------------");

            if (PubUtil.taskFragmentActivityFinished) {
                PubUtil.taskFragmentActivityFinished = false;
                clearRedPointEngin();
                return;
            }
            if (0 == currentPostion) {
                clearRedPointEngin();
            }

        }
    }

    /**
     * 清理任务模块中的小红点的逻辑
     */
    private void clearRedPointEngin() {
        mUnreadAmountTv.setVisibility(View.GONE);
        String amount = mUnreadAmountTv.getText().toString().trim();
        EventManager.sendStringMsg(EventBusProperty.HIND_TASK_TAB_RED_POINT);
        if (StrUtils.isStringValueOk(amount)) {
            mUnreadAmountTv.setText("");
            int amountInt = Integer.parseInt(amount);
            if (amountInt > 0) {
                new UnReadTagModel().clearUnReadAmount(mUserInfoUtil.getUserId(), this, UnReadContract.CLEAR_UNREAD_AMOUNT);
            }
        }
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        //通知相关接口 重新刷新
        EventManager.sendStringMsg(MainContact.CHANGE_VILLAGE);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == ActivityResultManager.MODIFY_USER_INFO || resultCode == ActivityResultManager.MINE_SET_QUIT_APP
                || resultCode == ActivityResultManager.QUIT_CURRENT_ACCOUNT_FOR_LOGIN_CANCEL

                || resultCode == ActivityResultManager.TABLE_TASK_DETAIL || ActivityResultManager.WORK_ORDER_DETAIL == resultCode
                || ActivityResultManager.PUBLISH_TASK == resultCode || ActivityResultManager.DEAL_TASK_DETAIL == resultCode) {
            super.onActivityResult(requestCode, resultCode, data);
        } else if (resultCode == ActivityResultManager.QUIT_CURRENT_ACCOUNT_FOR_LOGIN_OTHER) {
//            new LoginModel().getUserMenu(mUserInfoUtil.getPropertyId(), mUserInfoUtil.getVillageId(), mUserInfoUtil.getUserId(), MainContact.USER_MENU, this);
            initBottomViewStatus(0);
        } else if (resultCode == ActivityResultManager.QUIT_CURRENT_ACCOUNT_FOR_TOKEN_MAINACTIVITY) {
            EventManager.sendStringMsg(ActivityResultManager.TASK_FRAGMENT_REFRESH);
        } else if (resultCode == ActivityResultManager.MINE_SYSTEM_NOTICE_BACK) {
            //将底部导航栏 我的中的红点状态消除
//            mUnreadAmountMineTv.setVisibility(View.GONE);
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onBackPressed() {
        //抽屉布局打开中
        if (mMainDrawerlayout.isDrawerOpen(GravityCompat.START)) {
            mMainDrawerlayout.closeDrawers();
            return;
        }
        if (isExit == false) {
            isExit = true;
            ToastUtil.showNormalToast(this, Toast.LENGTH_SHORT, "再按一次退出程序");
            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    isExit = false;
                }

            }, 2000);
        } else {
            ActivityManagerTool.getInstance().finishApp();
            super.onBackPressed();
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
        stopMaterialProgressDialog();
        switch (tag) {
            case MainContact.GET_VILLAGE_LIST://小区列表
                VillagesBean villagesBean = (VillagesBean) o;
                if (villagesBean != null) {
                    List<VillagesBean.DataBean> arrays = villagesBean.getData();
                    if (arrays != null && arrays.size() > 0) {
                        villagesAdapter.setNewData(arrays);
                    }
                }

                break;
            case MainContact.GET_USER_PROPERTY_RELATION://关系表
                LoginBean loginBean = (LoginBean) o;
                if (loginBean != null) {
                    if (loginBean.getData() != null) {
                        Hawk.put(HawkProperty.LOGIN_BEAN , loginBean);
                    }
                }
                break;
            case MainContact.CHANGE_VILLAGE://关系表  切换小区
                LoginBean loginBean1 = (LoginBean) o;
                if (loginBean1 != null) {
                    if (loginBean1.getData() != null) {
                        int propertyId = loginBean1.getData().getPropertyId();
                        int villageId = loginBean1.getData().getVillageId();
                        String villageName = loginBean1.getData().getVillageName();

                        Hawk.put(HawkProperty.LOGIN_BEAN , loginBean1);
                        Hawk.put(HawkProperty.SELECTED_PROPERTY_ID + mUserInfoUtil.getUserId(), propertyId);
                        Hawk.put(HawkProperty.SELECTED_VILLAGE_ID + mUserInfoUtil.getUserId(), villageId);
                        Hawk.put(HawkProperty.SELECTED_VILLAGE_NAME + mUserInfoUtil.getUserId(), villageName);
                        //通知相关接口 重新刷新
                        EventManager.sendStringMsg(MainContact.CHANGE_VILLAGE);
                    }
                }
                break;

            default:
                break;
        }
    }

    @Override
    public void onError(String tag) {
        stopMaterialProgressDialog();
        showNormalToast(tag);

    }

    @Override
    public void onStart(String tag) {

    }

    @Override
    public void onSuccess(Object o, String tag) {
        if (UnReadContract.CLEAR_UNREAD_AMOUNT.equals(tag)) {

        } else if (LoginContract.GET_TOOL_FORM_LIST.equals(tag)) {

            ToolFormBean toolFormBean = (ToolFormBean) o;
            if (toolFormBean != null) {
                List<ToolFormBean.DataBean> dataBeanList = toolFormBean.getData();
                if (dataBeanList != null) {
                    PubUtil.toolForm.clear();
                    if (dataBeanList.size() > 0) {
                        for (ToolFormBean.DataBean dataBean : dataBeanList) {
                            PubUtil.toolForm.add(new MultiWorkDeskMenuBean(MultiWorkDeskMenuBean.DYNAMIC_MENU, dataBean));
                        }
                    }
                }
            }
        }

    }


    @Override
    public void onClick(View v) {
        boolean isLogin = Hawk.contains(HawkProperty.LOGIN_BEAN );
        switch (v.getId()) {
            default:
                break;
            case R.id.navigation_task_cl:
                currentPostion = 0;
                String amount = mUnreadAmountTv.getText().toString().trim();
                if (StrUtils.isStringValueOk(amount)) {
                    PubUtil.showTabRedPoint = true;
                } else {
                    PubUtil.showTabRedPoint = false;

                }
                if (!isLogin) {
                    PubUtil.LOGIN_ENTER = 4;

                    //跳转到登录界面
                    startActivityForResult(new Intent(this, LoginActivity.class), ActivityResultManager.QUIT_CURRENT_ACCOUNT_FOR_LOGIN_OTHER);
                    return;
                }
                initBottomViewStatus(0);
                break;
            case R.id.navigation_work_desk_ll:
                currentPostion = 1;

                if (!isLogin) {
                    PubUtil.LOGIN_ENTER = 4;

                    //跳转到登录界面
                    startActivityForResult(new Intent(this, LoginActivity.class), ActivityResultManager.QUIT_CURRENT_ACCOUNT_FOR_LOGIN_OTHER);
                    return;
                }
                initBottomViewStatus(1);
                break;
            case R.id.navigation_mine_ll:
                currentPostion = 2;

                initBottomViewStatus(2);


                break;
            case R.id.head_pic_pv:

                break;
            case R.id.drawerbleLayout_user_head_iv:

//                //抽屉布局打开中
//                if (mMainDrawerlayout.isDrawerOpen(GravityCompat.START)) {
//                    mMainDrawerlayout.closeDrawers();
//                }
                break;
            case R.id.drawerbleLayout_change_organize_tv://切换小区
                PubUtil.CHANGE_PROPERTY = true;
                mMainDrawerlayout.closeDrawers();

                break;
        }
    }


    @Override
    public void openDrawerLayout() {
        mMainDrawerlayout.openDrawer(GravityCompat.START);

    }

    @Override
    public void onError(String tag, String errorCode) {
        if (StrUtils.isStringValueOk(errorCode)) {
            if ("1001".equals(errorCode)) {//权限发生变更，请重新登录
                //删除最近开门记录
                Hawk.delete(UniversalKeyActivity.MAPKEY);
                Hawk.delete(UniversalKeyActivity.LISTKEY);
                startActivity(new Intent(this, LoginActivity.class));
            }
        }
    }

}
