package com.ghs.ghspm.base;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ghs.ghspm.R;
import com.ghs.ghspm.base.displayPhotos.DisplayPhotosActivity;
import com.ghs.ghspm.base.network.NetWorkUtil;
import com.ghs.ghspm.base.promission.CheckPermListener;
import com.ghs.ghspm.base.promission.PromissionManagerActivity;
import com.ghs.ghspm.bean.UsersFromRoleBean;
import com.ghs.ghspm.customView.LoadingDialog;
import com.ghs.ghspm.models.login.LoginActivity;
import com.ghs.ghspm.models.main.MainActivity;
import com.ghs.ghspm.models.mine.set.useguide.UserGuideDisplayActivity;
import com.ghs.ghspm.tools.ActivityManagerTool;
import com.ghs.ghspm.tools.ActivityResultManager;
import com.ghs.ghspm.tools.Contract;
import com.ghs.ghspm.tools.DividerItemDecoration;
import com.ghs.ghspm.tools.EventBusProperty;
import com.ghs.ghspm.tools.EventManager;
import com.ghs.ghspm.tools.MaterialAlterDialogManager;
import com.ghs.ghspm.tools.PubUtil;
import com.ghs.ghspm.tools.ScreenUtils;
import com.ghs.ghspm.tools.StrUtils;
import com.ghs.ghspm.tools.ToastUtil;
import com.ghs.ghspm.tools.UserInfoUtil;
import com.ghs.ghspm.tools.glide.GlideCircleTransform;
import com.gyf.barlibrary.ImmersionBar;
import com.umeng.analytics.MobclickAgent;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;


/**
 * Author:wang_sir
 * Time:2018/5/13 14:37
 * Description:This is BaseActivity
 * 泛型V代表View
 */
public abstract class BaseActivity<V, T extends BasePresent<V>> extends PromissionManagerActivity {
    public String TAG = getClass().getSimpleName() + "";
    public SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS", Locale.CHINA);
    protected ImmersionBar mImmersionBar;
    private T mPresenter;
    public ImageView mHeaderLeftIv;
    public String[] arrs = {"1", "2", "3", "4", "5"};
    /**
     * 设置
     */
    private TextView mHeaderTitleTv;
    private ConnectivityManager manager;
    private TextView activityNoDataTv;
    //    public DaoUtils mDaoUtil;
//    private MaterialDialog materialProgressDialog;
    private boolean noNetWorkFirstChanged = true;//网络状态第一次改变了

    public String INTENT_KEY = "intent_key";//intent跳转的key
    protected UserInfoUtil mUserInfoUtil;
    private RelativeLayout mHeaderLayoutRl;
    public TextView mHeaderRightTv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //创建presenter
        mPresenter = creatPresenter();
        if (mPresenter != null) {
            mPresenter.onAttachView((V) this);
        }
        mUserInfoUtil = UserInfoUtil.getInstance();
        setLayout();
        initLayoutView();
        ActivityManagerTool.getInstance().addActivity(this);
        EventManager.getLibraryEvent().register(this);//注册
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);// 锁定竖屏

        initWidows();

        mImmersionBar = ImmersionBar.with(this);
        if (this instanceof MainActivity) {
        } else if (this instanceof DisplayPhotosActivity) {
            mImmersionBar.statusBarDarkFont(false).init();
        } else {
            mImmersionBar
                    .statusBarDarkFont(true, 0.2f) //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
                    .init();
        }
        // where this is an Activity or Fragment instance
        getDate();
    }

    /**
     * 创建presenter
     */
    public abstract T creatPresenter();

    /**
     * 初始化窗口 在界面为初始化之前调用
     */
    protected void initWidows() {
        //设置屏幕适配 360为设计图尺寸px/2
        ScreenUtils screenUtils = ScreenUtils.getInstance(getApplicationContext());
        if (screenUtils.isPortrait()) {
            screenUtils.adaptScreen4VerticalSlide(this, 360);
        } else {
            screenUtils.adaptScreen4HorizontalSlide(this, 360);
        }

    }

    /**
     * 初始化布局中的view
     */
    public abstract void setLayout();

    ;

    /**
     * 初始化布局中的view
     */
    public abstract void initLayoutView();

    ;

    /**
     * 获取数据
     */
    public abstract void getDate();

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    /**
     * 获取Textview的数据
     *
     * @param textView
     * @return
     */
    public String getTextViewValue(TextView textView) {
        if (textView != null) {
            return textView.getText().toString().trim();
        }
        return "";
    }

    /**
     * 隐藏控件  Invisible  gone
     *
     * @param isGone gone
     * @param views
     */
    protected void setViewsInvisible(boolean isGone, View... views) {
        if (views != null && views.length > 0) {
            for (View view : views) {
                if (view != null) {
                    if (isGone) {
                        view.setVisibility(View.GONE);
                    } else {
                        view.setVisibility(View.INVISIBLE);
                    }
                }
            }
        }
    }

    /**
     * 显示控件  Invisible  gone
     *
     * @param views
     */
    protected void setViewsVisible(View... views) {
        if (views != null && views.length > 0) {
            for (View view : views) {
                if (view != null) {
                    view.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityManagerTool.getInstance().removeActivity(this);
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        stopMaterialProgressDialog();
        EventManager.getLibraryEvent().unregister(this);
        if (mImmersionBar != null) {
            mImmersionBar.destroy();  //必须调用该方法，防止内存泄漏，不调用该方法，如果界面bar发生改变，在不关闭app的情况下，退出此界面再进入将记忆最后一次bar改变的状态
        }

    }

    /**
     *
     */
    public void stopMaterialProgressDialog() {
        LoadingDialog.getInstance().dismissProgress();

    }
    /**
     * 检测网络连接状态
     * @return
     */
    protected boolean isNetWorkConnected() {
        if (!NetWorkUtil.isNetworkAvailable()) {
            showToast("网络异常，请检查网络");
            return false;
        }
        return true;
    }


    public void userGuidDisplay(String tag) {
        Intent intent = new Intent(this, UserGuideDisplayActivity.class);
        intent.putExtra(ActivityResultManager.USER_GUID_TAG, tag);
        startActivity(intent);
    }

    /**
     * 获取present实例
     *
     * @return
     */
    public T getPresenter() {
        return mPresenter;
    }

    /**
     * 展示进度框
     */
    public void showMaterialProgressDialog(String title, String content) {
       LoadingDialog.getInstance().showProgress(this);
    }

    /**
     * 展示进度框
     */
    public void showMaterialProgressDialog() {
        LoadingDialog.getInstance().showProgress(this);

    }


    /**
     * 初始化actionBar
     */
    public void initActionBar(String titleContent, String rightTvContent) {

        if (this instanceof MainActivity) {

        } else {
            mHeaderLeftIv = (ImageView) findViewById(R.id.header_left_iv);
            mHeaderTitleTv = (TextView) findViewById(R.id.header_title_tv);
            mHeaderTitleTv.setText(titleContent);
            mHeaderRightTv = (TextView) findViewById(R.id.header_right_tv);
            if (rightTvContent != null) {
                mHeaderRightTv.setText(rightTvContent);
            } else {
                mHeaderRightTv.setVisibility(View.INVISIBLE);
            }
            mHeaderLeftIv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onBackPressed();
                }
            });
            mHeaderRightTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    actionBarRightTvOnClick();
                }
            });
        }

    }

    /**
     * 初始化actionBar
     *
     * @param titleContent
     * @param rightTvContent
     * @param bgRes          背景色
     */
    public void initActionBar(String titleContent, String rightTvContent, int bgRes) {

        if (this instanceof MainActivity) {

        } else {
            mHeaderLeftIv = (ImageView) findViewById(R.id.header_left_iv);
            mHeaderTitleTv = (TextView) findViewById(R.id.header_title_tv);
            mHeaderLayoutRl = (RelativeLayout) findViewById(R.id.top_layout_rl);
            mHeaderLayoutRl.setBackgroundResource(bgRes);
            mHeaderTitleTv.setText(titleContent);
            mHeaderRightTv = (TextView) findViewById(R.id.header_right_tv);
            if (StrUtils.isStringValueOk(rightTvContent)) {
                mHeaderRightTv.setText(rightTvContent);
            } else {
                mHeaderRightTv.setVisibility(View.INVISIBLE);
            }
            mHeaderLeftIv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onBackPressed();
                }
            });
            mHeaderRightTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    actionBarRightTvOnClick();
                }
            });
        }

    }

    /**
     * 头布局中rightTextView的点击事件
     */
    public abstract void actionBarRightTvOnClick();

    @Override
    protected void onPause() {
        super.onPause();
        if (this instanceof MainActivity) {

        } else {
            MobclickAgent.onPageEnd(this.getClass().getName());
        }
        MobclickAgent.onPause(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (this instanceof MainActivity) {

        } else {
            MobclickAgent.onPageStart(this.getClass().getName());//统计页面
        }
        MobclickAgent.onResume(this);//统计时长
    }

    /**
     * 获取空布局
     *
     * @param text
     * @return
     */
    public View getAdapterEmptyView(String text) {
        View view = LayoutInflater.from(this).inflate(R.layout.empty_view, null);
        TextView noticeTv = view.findViewById(R.id.empty_view_tv);
        noticeTv.setText(text);
        return view;
    }

    /**
     * 弹出Toast
     *
     * @param text
     */
    public void showToast(String text) {
        ToastUtil.showNormalToast(this, Toast.LENGTH_SHORT, text);
//        ToastUtil.showToast(this, text);
    }

    /**
     * 弹出Toast
     *
     * @param text
     */
    public void showNormalToast(String text) {
        ToastUtil.showNormalToast(this, Toast.LENGTH_SHORT, text);
    }

    /**
     * 获取屏幕宽度(px)
     *
     * @param
     * @return
     */
    public int getScreenWidth() {
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }

    /**
     * 获取屏幕高度(px)
     *
     * @param
     * @return
     */
    public int getScreenHeight() {
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.heightPixels;
    }



    /**
     * 初始化recyclerview LinearLayoutManager
     */
    public void initRecyclerview(RecyclerView recyclerView, BaseQuickAdapter baseQuickAdapter, @RecyclerView.Orientation int orientation, boolean reverseLayout) {
        LinearLayoutManager managere = new LinearLayoutManager(this, orientation, reverseLayout);
        recyclerView.setLayoutManager(managere);
        recyclerView.setAdapter(baseQuickAdapter);
    }

    /**
     * 初始化recyclerview LinearLayoutManager
     */
    public void initRecyclerviewGridLayout(RecyclerView recyclerView, BaseQuickAdapter baseQuickAdapter, int spanCount, @RecyclerView.Orientation int orientation, boolean reverseLayout, boolean noSlide) {
        GridLayoutManager gridLayoutManager = null;
        if (!noSlide) {
            gridLayoutManager = new GridLayoutManager(this, spanCount, orientation, false);
        } else {
            gridLayoutManager = new GridLayoutManager(this, spanCount, orientation, false) {
                @Override
                public boolean canScrollVertically() {
                    return false;
                }
            };
        }
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(baseQuickAdapter);
    }

    /**
     * 初始化recyclerview LinearLayoutManager
     */
    public void initRecyclerview(RecyclerView recyclerView, BaseQuickAdapter baseQuickAdapter, @RecyclerView.Orientation int orientation, boolean reverseLayout, boolean noSlide) {
        LinearLayoutManager managere = new LinearLayoutManager(this, orientation, reverseLayout) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        recyclerView.setLayoutManager(managere);
        recyclerView.setAdapter(baseQuickAdapter);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void stringMsgReceived(String str) {
        if (PubUtil.EVENTBUS_TOCKEN_EXPIRED.equals(str)) {//token过期
            if (PubUtil.tokenExpiredFirstNotice) {
                PubUtil.LOGIN_ENTER = 3;
                PubUtil.tokenExpiredFirstNotice = false;
                Intent intent = new Intent(this, LoginActivity.class);
                if (this instanceof MainActivity) {
                    intent.putExtra(ActivityResultManager.TOKEN_EXPIRED_ACTIVITY_TAG, ActivityResultManager.MAINATIVITY_TAG);
                } else {
                    intent.putExtra(ActivityResultManager.TOKEN_EXPIRED_ACTIVITY_TAG, ActivityResultManager.EXCEPT_MAINATIVITY_TAG);

                }
                startActivityForResult(intent, ActivityResultManager.QUIT_CURRENT_ACCOUNT_FOR_TOKEN_MAINACTIVITY);
            }
        }else if (EventBusProperty.NET_WORK_UNCONNECT.equals(str)) {
            showToast("网络异常，请检查网络");
            stopMaterialProgressDialog();
        }
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
            dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST, R.drawable.divider_hor_line_sp);
        } else {
            dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL_LIST);
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
     * 添加分割线
     *
     * @param recyclerView
     * @param haveTopLine
     * @param isHorizontalDivider 水平分割线
     * @param haveEndLine         最后一个item下是否划线
     */
    public void addDivider(boolean isHorizontalDivider, RecyclerView recyclerView, boolean haveTopLine, boolean haveEndLine, int drawableId) {
        DividerItemDecoration dividerItemDecoration;
        if (isHorizontalDivider) {
            dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST, drawableId);
        } else {
            dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL_LIST);
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
     * 工作台中的菜单项
     *
     * @return
     */
    public List<String> getWorkDeskMenueNames() {
        List<String> menus = new ArrayList<>();
        menus.add("任务打卡");
        menus.add("内部报修");
        menus.add("组织架构");
        menus.add("业主信息");
        menus.add("车辆信息");
        menus.add("水电表抄录");
        menus.add("签批与表单");
        menus.add("巡查巡检");
        menus.add("更多应用");
        menus.add("访客审核");
        menus.add("排班");
        return menus;
    }

    /**
     * 描述：dip转换为px.
     *
     * @param context the context
     * @return px值
     */
    public int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 获取当年是哪年
     *
     * @return
     */
    public int getCurrentYear() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.YEAR);
    }


    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha 屏幕透明度0.0-1.0 1表示完全不透明
     */
    public void setBackgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow()
                .getAttributes();
        lp.alpha = bgAlpha;
        getWindow().setAttributes(lp);
    }

    /**
     * 提示修改
     *
     * @param msg
     */
    public void modifyNotice(String msg) {
        new AlertDialog.Builder(this)
                .setTitle("提示")
                .setMessage(msg)
                .setNegativeButton("是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .setPositiveButton("否", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        finish();
                    }
                }).show();

    }

    /**
     * 关闭dialog
     */
    public void dismissDialog(Dialog dialog) {
        if (dialog != null) {
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
        }
    }


    /**
     * 获取intent传入的值
     *
     * @return
     */
    public String getIntentStringData(String key) {
        String json = "";
        if (getIntent() != null) {
            json = getIntent().getStringExtra(key);
        }
        return json;
    }

    /**
     * 初始化已选择的执行人 或负责人，或则抄送人
     *
     * @param sb
     * @param tv
     */
    public void initSelectedPeoples(StringBuffer sb, TextView tv) {
        List<String> arrays = new ArrayList<>();
        for (Map.Entry<Integer, UsersFromRoleBean.DataBean> entry : PubUtil.selectedUsersMap.entrySet()) {
            int key = entry.getKey();
            sb.append(String.valueOf(key));
            sb.append(",");
            UsersFromRoleBean.DataBean userListBean = entry.getValue();
            arrays.add(userListBean.getName());
        }

        if (arrays.size() > 0) {
            String name = "";
            for (int i = 0; i < arrays.size(); i++) {
                name = arrays.get(0);
                break;
            }
            if (arrays.size() > 1) {
                tv.setText(name + "等" + arrays.size() + "人");
            } else {
                tv.setText(name);
            }
        } else {
            tv.setText("");
        }

    }

    /**
     * 初始化已选择的执行人 或负责人，或则抄送人
     *
     * @param tv
     */
    public void initSelectedPeoples(StringBuffer sbid, StringBuffer sbname, TextView tv) {
        List<String> arrays = new ArrayList<>();
        for (Map.Entry<Integer, UsersFromRoleBean.DataBean> entry : PubUtil.selectedUsersMap.entrySet()) {
            int key = entry.getKey();
            sbid.append(String.valueOf(key));
            sbid.append(",");

            UsersFromRoleBean.DataBean userListBean = entry.getValue();
            arrays.add(userListBean.getName());
            sbname.append(userListBean.getName());
            sbname.append(",");

        }

        if (arrays.size() > 0) {
            String name = "";
            for (int i = 0; i < arrays.size(); i++) {
                name = arrays.get(0);
                break;
            }
            if (arrays.size() > 1) {
                tv.setText(name + "等" + arrays.size() + "人");

            } else {
                tv.setText(name);
            }
        } else {
            tv.setText("");
        }
    }

    /**
     * 清空stringbuffer
     *
     * @param sb
     */
    public void clearStringBuffer(StringBuffer sb) {
        if (sb != null) {
            if (sb.length() > 0) {
                sb.delete(0, sb.length());
            }
        }

    }


    /**
     * 隐藏软键盘  view 可以是当前点击的view 没必要全是edittext
     */
    public void hideKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    /**
     * 显示软键盘
     *
     * @param view
     */
    public void openKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.showSoftInput(view, InputMethodManager.RESULT_UNCHANGED_SHOWN);
            imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
        }

    }

    /**
     * 在activity中调用 监听非EditText的onTouich事件
     *
     * @param activity
     * @param rootView
     */
    public void setOnTouchListenerOfViews(Activity activity, final View rootView) {
        //Set up touch listener for non-text box views to hide keyboard.
        if (!(rootView instanceof EditText)) {
            rootView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    hideKeyboard(rootView);  //Main.this是我的activity名
                    return false;
                }
            });
        }

        //If a layout container, iterate over children and seed recursion.
        if (rootView instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) rootView).getChildCount(); i++) {
                View innerView = ((ViewGroup) rootView).getChildAt(i);
                setOnTouchListenerOfViews(activity, innerView);
            }
        }
    }

    /**
     * 启动activity
     *
     * @param value
     * @param cls
     */
    public void startActivityWithParcelableData(Parcelable value, Class<?> cls) {

        Intent intent = new Intent(this, cls);
        intent.putExtra(INTENT_KEY, value);
        startActivity(intent);

    }

    /**
     * 获取传过来的Parcelable实体类
     *
     * @return
     */
    public Parcelable getIntentParcelableData() {
        Parcelable value = null;
        Intent intent = getIntent();
        if (intent != null) {
            value = intent.getParcelableExtra(INTENT_KEY);
        }
        return value;
    }

    /**
     * 拨打电话
     */
    public void makeAPhoneCall(String telNum) {
        View view = getLayoutInflater().inflate(R.layout.call_tel_layout, null);
        final AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setView(view)
                .create();
        alertDialog.show();
        MaterialAlterDialogManager.getInstance().setAlertDialogHeightWidth(alertDialog, -1, 0);

        final TextView phone = view.findViewById(R.id.property_phone_no_tv);
        phone.setText(telNum);
        view.findViewById(R.id.call_property_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                checkAppPermissions(new CheckPermListener() {
                    @Override
                    public void agreeAllPermission() {
                        alertDialog.dismiss();
                        PubUtil.callPhone(BaseActivity.this, phone.getText().toString().trim());
                    }

                    @Override
                    public void selectedAllPermission() {
                    }
                }, R.string.perm_call, PubUtil.promissions[2]);


            }
        });
        view.findViewById(R.id.cancel_call_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });
    }


    /**
     * 关闭正在展开的底部弹框
     */
    public void dismissBottomSheetDialog(BottomSheetDialog bottomSheetDialog) {
        if (bottomSheetDialog != null) {
            if (bottomSheetDialog.isShowing()) {
                bottomSheetDialog.dismiss();
            }
        }
    }

    /**
     * 配置view的margin属性
     */
    protected void setMargin(View view, int left, int top, int right, int bottom) {
        left = PubUtil.dip2px(this, left);
        top = PubUtil.dip2px(this, top);
        right = PubUtil.dip2px(this, right);
        bottom = PubUtil.dip2px(this, bottom);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(view.getLayoutParams());
        layoutParams.setMargins(left, top, right, bottom);
        view.setLayoutParams(layoutParams);
    }


    /**
     * 头像
     *
     * @param imageView
     * @param headImage
     * @param isGone  TextView是否隐藏
     */
    public void setHeadPicBgResource(ImageView imageView, TextView textView, String  headName,String headImage,String headBgColor, boolean isGone) {
        if (StrUtils.isStringValueOk(headImage) && !"defaultHeadImage.jpg".equals(headImage)) {
            //已上传头像
            setViewsVisible(imageView);
            if (isGone) {
                setViewsInvisible(true, textView);
            } else {
                setViewsInvisible(false, textView);
            }
            if (isValidContextForGlide(this)) {
                Glide.with(this).load(Contract.ImageBasePath + headImage)
                        .skipMemoryCache(false)
                        .placeholder(R.mipmap.default_user_head_icon)
                        // 设置占位图
                        .transform(new GlideCircleTransform(this))//圆角
                        .into(imageView);
            }

        } else {
            setViewsVisible(textView);
            if (isGone) {
                setViewsInvisible(true, imageView);
            } else {
                setViewsInvisible(false, imageView);
            }
            if (StrUtils.isStringValueOk(headName)) {
                headName = headName.substring(headName.length() - 1, headName.length());
            }
            textView.setText(headName);
            headBgColor = StrUtils.isStringValueOk(headBgColor) ? headBgColor : "#ffffff";
            if (headBgColor.contains("53C68C")) {
                textView.setBackgroundResource(R.drawable.head_pic_circle_bg_green_shape);
            } else if (headBgColor.contains("2781FC")) {
                textView.setBackgroundResource(R.drawable.head_pic_circle_bg_blue_shape);
            } else if (headBgColor.contains("FFB243")) {
                textView.setBackgroundResource(R.drawable.head_pic_circle_bg_orange_shape);
            }else{
                textView.setBackgroundResource(R.drawable.head_pic_circle_bg_white_shape);
            }
        }
    }

    /**
     * 检测glide加载环境是否ok
     * @param context
     * @return
     */
    public static boolean isValidContextForGlide(final Context context) {
        if (context == null) {
            return false;
        }
        if (context instanceof Activity) {
            final Activity activity = (Activity) context;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                if (activity.isDestroyed() || activity.isFinishing()) {
                    return false;
                }
            } else {
                if (activity.isFinishing()) {
                    return false;
                }
            }
        }
        return true;
    }

}
