package com.ghs.ghspm.models.workdesk.publicfuction.signIn;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Vibrator;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMapUtils;
import com.amap.api.maps.model.LatLng;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ghs.ghspm.R;
import com.ghs.ghspm.base.BaseActivity;
import com.ghs.ghspm.base.promission.CheckPermListener;
import com.ghs.ghspm.base.promission.EasyPermissions;
import com.ghs.ghspm.bean.UserCardInfo;
import com.ghs.ghspm.models.workdesk.publicfuction.signIn.applyReCard.ApplyReCardActivity;
import com.ghs.ghspm.models.workdesk.publicfuction.signIn.attendanceRule.AttendanceRuleActivity;
import com.ghs.ghspm.models.workdesk.publicfuction.signIn.monthrecord.MonthRecordActivity;
import com.ghs.ghspm.tools.ActivityResultManager;
import com.ghs.ghspm.tools.CalendarUtil;
import com.ghs.ghspm.tools.Contract;
import com.ghs.ghspm.tools.glide.GlideCircleTransform;
import com.ghs.ghspm.tools.MaterialAlterDialogManager;
import com.ghs.ghspm.base.network.NetWorkUtil;
import com.ghs.ghspm.tools.PubUtil;
import com.ghs.ghspm.tools.StrUtils;
import com.ghs.ghspm.tools.UserInfoUtil;

import java.util.Date;
import java.util.List;

/**
 * created by 8级大的狂风
 * created date 2018/9/21 9:43.
 * application  考勤签到
 */
public class SignInActivity extends BaseActivity<SignInContract.ISignInView, SignInPresent> implements View.OnClickListener, SignInContract.ISignInView {

    private ImageView mSignUserHeadIv;
    /**
     * 王二
     */
    private TextView mSignUserNameTv;
    /**
     * 王二
     */
    private TextView mSignTypeTv;
    private LinearLayout mSignMonthDetailLl;
    private LinearLayout mSignLl;
    /**
     * 工作时间：8:00-18:00
     */
    private TextView mSignWorkTimeTv;
    /**
     * 上班签到\n9:00:00
     */
    private TextView mShiftName;
    private RecyclerView mSignInfoRv;
    private TextClock mSignTimeTv;
    /**
     * 您已进入考勤范围
     */
    private TextView mSignRangeTv;
    private SignInAdapter signInAdapter;
    private AMapLocationClient locationClient;
    //    private LatLng mLatLngForSign = null;//打卡点
    private int rangeValue = 0;//打卡范围
    //    private UserCardInfo.DataBean.PmAttendanceRulesDOBean pmAttendanceRulesDOBean;
    private int GPS_REQUEST_CODE = 10;


    private int distanceOfClockLately = 999999999;//距离打卡点的距离
    private int effectiveDistance = 200;//打卡范围的半径

    private Vibrator vibrator;
    /**
     * 今天
     */
    private TextView mSignCurrentDateTv;
    /**
     * 查看考勤规则
     */
    private TextView mSignAttendanceRulesTv;
    private TextView mOffDutyNoticeTv;

    private int outsideId;//是否超出打卡范围  1:超出; 2:未超出
    private String clockPlace = "";//打卡地点
    private String currentAddr;//当前的位置
    private LatLng currentLatLng;//当前的位置

    private boolean  hasClockAddr = true;//后台设置打卡地点
    /**
     * 定位监听
     */
    AMapLocationListener locationListener = new AMapLocationListener() {

        @Override
        public void onLocationChanged(AMapLocation loc) {

            if (null != loc) {
                //解析定位结果
                double lat = loc.getLatitude();
                Log.d(TAG, lat + "当前纬度----------------");
                currentAddr = loc.getAddress();
                String erro = loc.getErrorInfo();
//                if ("缺少定位权限".equals(erro)) {
//                    promissionMiss = false;
//                }
//                else{
//                    mShiftName.setText("正在定位");
//                }
                double lng = loc.getLongitude();
                Log.d(TAG, lng + "当前经度----------------");

                if (0 == lat || lng == 0) {
                    return;
                }
//                promissionMiss = true;
                currentLatLng = new LatLng(lat, lng);
                //获取位置最近的打卡点
                UserCardInfo.DataBean.ClockRecordListBean clockRecordListBean = getCurrentShiftToClock(false);
                prepareForClock(clockRecordListBean, true);


            }

        }
    };
    private int clickedPosition = -1;//点击的item位置
    private LinearLayout mSignLayoutLl;
    private LinearLayout mOffDutyLl;
    private AMapLocationClientOption mapLocationClientOption;

    /**
     * 准备打卡前得数据
     *
     * @param clockRecordListBean
     */
    private void prepareForClock(UserCardInfo.DataBean.ClockRecordListBean clockRecordListBean, boolean visible) {
        UserCardInfo.DataBean.ClockRecordListBean.ClockPlaceListBean clockPlaceLately = null;//距离最近的打卡点

        if (clockRecordListBean != null) {
            List<UserCardInfo.DataBean.ClockRecordListBean.ClockPlaceListBean> clockPlaces = clockRecordListBean.getClockPlaceList();
            if (clockPlaces == null || clockPlaces.size() < 1) {//未分配打卡地点
                hasClockAddr = false;
                mSignRangeTv.setText("未分配打卡地点，无法打卡");
                mSignRangeTv.setTextColor(Color.RED);
            } else {
                hasClockAddr = true;
                distanceOfClockLately = 999999999;
                for (UserCardInfo.DataBean.ClockRecordListBean.ClockPlaceListBean clockPlace : clockPlaces) {
                    String clockPlaceLatitude = clockPlace.getLatitude();
                    String clockPlaceLongitude = clockPlace.getLongitude();
                    double clockPlaceLatitudeDouble = Double.parseDouble(clockPlaceLatitude);
                    double clockPlaceLongitudeDouble = Double.parseDouble(clockPlaceLongitude);
                    int radius = clockPlace.getEffectiveDistance();
                    LatLng clockPlaceLatlng = new LatLng(clockPlaceLatitudeDouble, clockPlaceLongitudeDouble);
                    int distance = Math.round(AMapUtils.calculateLineDistance(currentLatLng,
                            clockPlaceLatlng));
                    Log.d(TAG, distance + "----------------");
                    if (distance < distanceOfClockLately) {
                        distanceOfClockLately = distance;
                        clockPlaceLately = clockPlace;
                        effectiveDistance = radius;
                    }
                }
                if (distanceOfClockLately > effectiveDistance) {//打卡范围外
                    outsideId = 1;
                    if (visible) {
                        mSignRangeTv.setText("未进入工作范围");
                        mSignRangeTv.setTextColor(Color.RED);
                    }
                    clockPlace = currentAddr;

                } else {
                    outsideId = 2;
                    if (visible) {
                        mSignRangeTv.setText("已进入工作范围");
                        mSignRangeTv.setTextColor(Color.parseColor("#9B9B9B"));
                    }
                    clockPlace = clockPlaceLately.getPlace();
                }

            }

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        checkLocationPromission();
    }

    /**
     * 检查定位权限
     */
    private void checkLocationPromission() {
        checkAppPermissions(new CheckPermListener() {
            @Override
            public void agreeAllPermission() {
                initLocation();
            }

            @Override
            public void selectedAllPermission() {

            }
        }, R.string.perm_location, PubUtil.promissions[0]);
    }


    @Override
    public SignInPresent creatPresenter() {
        return new SignInPresent();
    }

    @Override
    public void setLayout() {
        setContentView(R.layout.activity_sign);

    }

    @Override
    public void initLayoutView() {
        initView();
        initActionBar("签到", null);
    }

    @Override
    public void getDate() {
            getPresenter().checkWorkGetUserInfo(UserInfoUtil.getInstance().getPropertyId(), UserInfoUtil.getInstance().getUserId(), SignInContract.CHECK_WORK_GET_USER_INFO);
    }

    @Override
    protected void onDestroy() {
        stopLocation();
        super.onDestroy();
    }

    /**
     * 停止定位
     *
     * @author hongming.wang
     * @since 2.8.0
     */
    private void stopLocation() {
        if (null != locationClient) {
            /**
             * 如果AMapLocationClient是在当前Activity实例化的，
             * 在Activity的onDestroy中一定要执行AMapLocationClient的onDestroy
             */
            locationClient.onDestroy();
            locationClient = null;
            mapLocationClientOption= null;
        }
    }

    @Override
    public void actionBarRightTvOnClick() {

    }

    private void initView() {
        mSignUserHeadIv = (ImageView) findViewById(R.id.sign_user_head_iv);
        Glide.with(this).load(Contract.ImageBasePath + UserInfoUtil.getInstance().getUserHeadImage())
                .skipMemoryCache(false)
                // 加载图片
//                        .error(errorimg)
//                        // 设置错误图片
//                        .crossFade()
//                        // 设置淡入淡出效果，默认300ms，可以传参
                .placeholder(R.mipmap.default_user_head_icon)
                // 设置占位图
                .transform(new GlideCircleTransform(this))//圆角
                .into(mSignUserHeadIv);
        mSignUserNameTv = (TextView) findViewById(R.id.sign_user_name_tv);
        mSignUserNameTv.setText(UserInfoUtil.getInstance().getUserName());
        mSignTypeTv = (TextView) findViewById(R.id.sign_type_tv);
        mSignMonthDetailLl = (LinearLayout) findViewById(R.id.sign_month_detail_ll);
        mSignLl = (LinearLayout) findViewById(R.id.sign_ll);
        mSignMonthDetailLl.setOnClickListener(this);
        mSignLl.setOnClickListener(this);
        mSignWorkTimeTv = (TextView) findViewById(R.id.sign_work_time_tv);
        mShiftName = (TextView) findViewById(R.id.sign_name_tv);
        mSignInfoRv = (RecyclerView) findViewById(R.id.sign_info_rv);
        signInAdapter = new SignInAdapter(R.layout.check_sign_info_item);
        initRecyclerview(mSignInfoRv, signInAdapter, LinearLayoutManager.VERTICAL, false, true);
        mSignTimeTv = (TextClock) findViewById(R.id.sign_time_tv);
        mSignRangeTv = (TextView) findViewById(R.id.sign_range_tv);
        mSignCurrentDateTv = (TextView) findViewById(R.id.sign_current_date_tv);
        mSignCurrentDateTv.setText("今天 " + CalendarUtil.getTimeFromDate("yyyy.MM.dd", new Date()));
        mSignAttendanceRulesTv = (TextView) findViewById(R.id.sign_Attendance_rules_tv);
        mSignAttendanceRulesTv.setOnClickListener(this);
        signInAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                clickedPosition = position;
                final UserCardInfo.DataBean.ClockRecordListBean bean = (UserCardInfo.DataBean.ClockRecordListBean) adapter.getData().get(position);
                TextView updateClock = (TextView) adapter.getViewByPosition(mSignInfoRv, position, R.id.sign_update_tv);
                String updateClockText = updateClock.getText().toString().trim();
                switch (updateClockText) {
                    case "申请补卡":
                        int clockId = bean.getClockId();
                        Intent intent = new Intent(SignInActivity.this, ApplyReCardActivity.class);
                        intent.putExtra("clockId", clockId);
                        startActivityForResult(intent, ActivityResultManager.RECARD_SUCCESS);
                        break;
                    case "更新打卡":
                        if (!hasClockAddr) {
                            showToast("未分配打卡地点，无法打卡");
                            return;
                        }
                            prepareForClock(bean, false);
                            MaterialAlterDialogManager.getInstance().showMaterialAlterDialog(SignInActivity.this,"确定要更新此次打卡记录吗？","确定","取消", new MaterialAlterDialogManager.OnAlterDialogCallBack() {
                                @Override
                                public void leftBtClicked() {
                                    if (1 == outsideId) {//超出范围 不能打卡
                                        MaterialAlterDialogManager.getInstance().showMaterialAlterdialogOnlyOneBtToClose(SignInActivity.this,"已超出工作范围，不能打卡", "知道了");
                                    } else {
                                        showMaterialProgressDialog();
                                        getPresenter().updateClock(outsideId, getClockPlace(bean), bean.getClockId(), SignInContract.UPDATE_CLOCK);

                                    }
                                }

                                @Override
                                public void rightBtClicked() {

                                }
                            });

                        break;
                    default:
                        break;
                }


            }
        });
        mOffDutyNoticeTv = (TextView) findViewById(R.id.off_duty_notice_tv);
        mSignLayoutLl = (LinearLayout) findViewById(R.id.sign_layout_ll);
        mOffDutyLl = (LinearLayout) findViewById(R.id.off_duty_ll);
    }

    /**
     * 初始化定位
     *
     * @author hongming.wang
     * @since 2.8.0
     */
    private void initLocation() {
        //初始化client
        locationClient = new AMapLocationClient(getApplicationContext());
        // 设置定位监听
        locationClient.setLocationListener(locationListener);
        startLocation();
    }

    /**
     * \
     * 获取打卡的时间
     *
     * @return
     */
    private boolean isClockTime(final UserCardInfo.DataBean.ClockRecordListBean bean) {
        boolean isOk = false;
        if (2 == PubUtil.SHIFT_TYPE) {//自由工时，上下班时间点没有限制
            return true;
        }
        if (bean != null) {
            int clockType = bean.getClockType();
            switch (clockType) {
                case 1://上班卡
                    String startTime = bean.getPermitStartClockTime();
                    if (StrUtils.isStringValueOk(startTime)) {
                        if (CalendarUtil.compareDateSize("yyyy-MM-dd HH:mm", startTime, CalendarUtil.getCurrentTimeNormal("yyyy-MM-dd HH:mm"))) {//打卡提前了
                            isOk = false;
                            MaterialAlterDialogManager.getInstance().showMaterialAlterdialogOnlyOneBtToClose(SignInActivity.this,"未到打卡时间", "知道了");

                        } else {
                            isOk = true;
                        }
                    } else {
                        isOk = false;
                    }

                    break;
                case 2://下班卡
                    String shiftTime = bean.getShiftDay() +" "+ bean.getShiftTime();
                    shiftTime = CalendarUtil.getTimeFromStringTime("yyyy-MM-dd HH:mm", "yyyy.MM.dd HH:mm", shiftTime);
                    if (StrUtils.isStringValueOk(shiftTime)) {
                        if (CalendarUtil.compareDateSize("yyyy-MM-dd HH:mm", shiftTime, CalendarUtil.getCurrentTimeNormal("yyyy-MM-dd HH:mm"))) {//早退
                            isOk = false;
                            MaterialAlterDialogManager.getInstance().showMaterialAlterDialog(SignInActivity.this,"还未到下班时间,提前打卡会视为早退","确定打卡","取消", new MaterialAlterDialogManager.OnAlterDialogCallBack() {
                                @Override
                                public void leftBtClicked() {
                                        if (1 == outsideId) {//超出范围 不能打卡
                                            MaterialAlterDialogManager.getInstance().showMaterialAlterdialogOnlyOneBtToClose(SignInActivity.this,"已超出工作范围，不能打卡", "知道了");
                                        } else {
                                            showMaterialProgressDialog();
                                            getPresenter().clock(UserInfoUtil.getInstance().getPropertyId(),
                                                    UserInfoUtil.getInstance().getUserId(), outsideId, clockPlace, bean.getClockType(), SignInContract.CHECK_WORK_SIGN);

                                        }
                                }

                                @Override
                                public void rightBtClicked() {

                                }
                            });
                        } else {
                            isOk = true;
                        }
                    } else {
                        isOk = false;
                    }
                    break;
                default:
                    break;
            }

        } else {
            isOk = false;
        }
        return isOk;
    }

    /**
     * 获取打卡地点（离自己最近的位置）
     * <p>
     * 需要返回打卡点
     */
    private String getClockPlace(UserCardInfo.DataBean.ClockRecordListBean clockRecordListBean) {
        UserCardInfo.DataBean.ClockRecordListBean.ClockPlaceListBean clockPlaceLately = null;//距离最近的打卡点
        List<UserCardInfo.DataBean.ClockRecordListBean.ClockPlaceListBean> clockPlaces = clockRecordListBean.getClockPlaceList();
        if (clockPlaces == null || clockPlaces.size() < 1) {//未分配打卡地点
            return currentAddr;
        } else {
            distanceOfClockLately = 999999999;
            for (UserCardInfo.DataBean.ClockRecordListBean.ClockPlaceListBean clockPlace : clockPlaces) {
                String clockPlaceLatitude = clockPlace.getLatitude();
                String clockPlaceLongitude = clockPlace.getLongitude();
                double clockPlaceLatitudeDouble = Double.parseDouble(clockPlaceLatitude);
                double clockPlaceLongitudeDouble = Double.parseDouble(clockPlaceLongitude);
                int radius = clockPlace.getEffectiveDistance();
                LatLng clockPlaceLatlng = new LatLng(clockPlaceLatitudeDouble, clockPlaceLongitudeDouble);
                int distance = Math.round(AMapUtils.calculateLineDistance(currentLatLng,
                        clockPlaceLatlng));
                if (distance < distanceOfClockLately) {
                    distanceOfClockLately = distance;
                    clockPlaceLately = clockPlace;
                    effectiveDistance = radius;
                }
            }
            if (distanceOfClockLately > effectiveDistance) {//打卡范围外
                return currentAddr;
            } else {
                return clockPlaceLately.getPlace();
            }

        }
    }

    /**
     * 开始定位
     *
     * @author hongming.wang
     * @since 2.8.0
     */
    private void startLocation() {
//		//根据控件的选择，重新设置定位参数
//		resetOption();
        // 设置定位参数
        mapLocationClientOption = getDefaultOption();
        locationClient.setLocationOption(mapLocationClientOption);
        // 启动定位
        locationClient.startLocation();
    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (requestCode == GPS_REQUEST_CODE) {
//            //做需要做的事情，比如再次检测是否打开GPS了 或者定位
//            openGPSSettings();
//        }
//
//    }

    /**
     * 默认的定位参数
     *
     * @author hongming.wang
     * @since 2.8.0
     */
    private AMapLocationClientOption getDefaultOption() {
        AMapLocationClientOption mOption = new AMapLocationClientOption();
        mOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);//可选，设置定位模式，可选的模式有高精度、仅设备、仅网络。默认为高精度模式
        mOption.setGpsFirst(false);//可选，设置是否gps优先，只在高精度模式下有效。默认关闭
        mOption.setHttpTimeOut(30000);//可选，设置网络请求超时时间。默认为30秒。在仅设备模式下无效
        mOption.setInterval(2000);//可选，设置定位间隔。默认为2秒
        mOption.setNeedAddress(true);//可选，设置是否返回逆地理地址信息。默认是true
        mOption.setOnceLocation(false);//可选，设置是否单次定位。默认是false
//		mOption.setOnceLocationLatest(false);//可选，设置是否等待wifi刷新，默认为false.如果设置为true,会自动变为单次定位，持续定位时不要使用
//		AMapLocationClientOption.setLocationProtocol(AMapLocationProtocol.HTTP);//可选， 设置网络请求的协议。可选HTTP或者HTTPS。默认为HTTP
//		mOption.setSensorEnable(false);//可选，设置是否使用传感器。默认是false
        return mOption;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == EasyPermissions.SETTINGS_REQ_CODE) {
            //配置权限，设置返回
            checkLocationPromission();
        }
        if (ActivityResultManager.RECARD_SUCCESS == resultCode) {
            getPresenter().checkWorkGetUserInfo(UserInfoUtil.getInstance().getPropertyId(), UserInfoUtil.getInstance().getUserId(), SignInContract.CHECK_WORK_GET_USER_INFO);
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
            case SignInContract.CHECK_WORK_GET_USER_INFO:
                stopMaterialProgressDialog();
                UserCardInfo checkWorkUserBean = (UserCardInfo) o;
                if (checkWorkUserBean != null) {
                    if (checkWorkUserBean.getData() != null) {
                        UserCardInfo.DataBean dataBean = checkWorkUserBean.getData();
                        if (dataBean != null) {
                            String attendanceTime = dataBean.getAttendanceTime();
                            int clockAble = dataBean.getClock();//	是否需要打卡 1:否 2:是
                            String ruleName = dataBean.getRulesName();
                            int type = dataBean.getType();
                            PubUtil.SHIFT_TYPE = type;
                            if (2 == type) {//自由工时
                                mSignTypeTv.setVisibility(View.GONE);
                                mSignTypeTv.setVisibility(View.VISIBLE);
                                mSignTypeTv.setText(ruleName);

                            } else if (1 == type || 3 == type) {//1：固定班次；2：自由工时 3:排班
                                if (StrUtils.isStringValueOk(attendanceTime)) {
                                    mSignWorkTimeTv.setVisibility(View.VISIBLE);
                                    mSignWorkTimeTv.setText(attendanceTime);
                                }else{
                                    mSignWorkTimeTv.setVisibility(View.GONE);
                                }

                                mSignTypeTv.setVisibility(View.VISIBLE);
                                mSignTypeTv.setText(ruleName);

                            } else {//没有分配考勤规则
                                mSignWorkTimeTv.setVisibility(View.GONE);
                                mSignTypeTv.setVisibility(View.GONE);
                            }

                            if (clockAble == 1) {
                                if (0 == type) {
                                    todyIsOnDuty(false, dataBean.getReason());
                                } else {
                                    todyIsOnDuty(false, "今日无需打卡");
                                }
                            } else {
                                todyIsOnDuty(true, null);
                            }
                            List<UserCardInfo.DataBean.ClockRecordListBean> clockList = dataBean.getClockRecordList();
                            if (clockList != null && clockList.size() > 0) {
                                signInAdapter.setNewData(clockList);
                                if (clickedPosition != -1) {//item被点击了
                                    mSignInfoRv.scrollToPosition(clickedPosition);
                                } else {
                                    int position = getPositionForRvToScroll();
                                    position = position != -1 ? position : clockList.size() - 1;
                                    mSignInfoRv.scrollToPosition(position);
                                }

                            }

                            initClockShiftName();

                        }
                    }
                }
                break;
            case SignInContract.CHECK_WORK_SIGN://签到成功
                String data = (String) o;
                cardSucceed(data);
                    getPresenter().checkWorkGetUserInfo(UserInfoUtil.getInstance().getPropertyId(), UserInfoUtil.getInstance().getUserId(), SignInContract.CHECK_WORK_GET_USER_INFO);

                break;
            case SignInContract.UPDATE_CLOCK://更新打卡
                showToast("更新打卡成功");
                vibrator.vibrate(300);
                getPresenter().checkWorkGetUserInfo(UserInfoUtil.getInstance().getPropertyId(), UserInfoUtil.getInstance().getUserId(), SignInContract.CHECK_WORK_GET_USER_INFO);

                break;
            default:
                break;
        }

    }

    /**
     * 今天是否当值
     */
    private void todyIsOnDuty(boolean isOnDuty, String notice) {

        if (isOnDuty) {
            mSignLayoutLl.setVisibility(View.VISIBLE);
            mSignInfoRv.setVisibility(View.VISIBLE);
            mOffDutyLl.setVisibility(View.GONE);
        } else {
            mSignLayoutLl.setVisibility(View.GONE);
            mSignInfoRv.setVisibility(View.GONE);
            mOffDutyLl.setVisibility(View.VISIBLE);
            if (notice != null) {
                mOffDutyNoticeTv.setText(notice);
            }
        }
    }

    /**
     * recyclerview滚动的位置
     */
    private int getPositionForRvToScroll() {
        UserCardInfo.DataBean.ClockRecordListBean bean = null;
        int position = -1;
        List<UserCardInfo.DataBean.ClockRecordListBean> clockList = signInAdapter.getData();
        for (int i = 0; i < clockList.size(); i++) {
            UserCardInfo.DataBean.ClockRecordListBean clockRecordListBean = clockList.get(i);
            int result = clockRecordListBean.getResult();
            if (-1 == result) {
                bean = clockRecordListBean;
                position = i;
                break;
            }
        }
        return position;
    }

    /**
     * 初始化打卡班次
     */
    private void initClockShiftName() {
        UserCardInfo.DataBean.ClockRecordListBean bean = getCurrentShiftToClock(true);
        if (bean != null) {
            int clockType = bean.getClockType();
            if (1 == clockType) {
                mShiftName.setText("上班打卡");
            } else {
                mShiftName.setText("下班打卡");
            }

        } else {
            mShiftName.setText("不需要打卡");
        }
    }

    /**
     * 打卡成功  1是上班卡  2是下班卡
     */
    private void cardSucceed(String data) {
        vibrator.vibrate(300);
//弹出打卡成功的对话框
        View view = LayoutInflater.from(this).inflate(R.layout.card_success_dialog, null);
        final AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setView(view)
                .setCancelable(false)
                .show();
        TextView workType = view.findViewById(R.id.card_success_type_tv);
        TextView clockTimeTv = view.findViewById(R.id.card_success_clock_time_tv);
        if (StrUtils.isStringValueOk(data)) {
            if (data.contains(",")) {
                String[] datas = data.split(",");
                String clockTime = datas[0];
                clockTimeTv.setText(clockTime);
                int clockType = Integer.parseInt(datas[1]);
                if (1 == clockType) {
                    workType.setText("上班打卡成功");
                } else {
                    workType.setText("下班打卡成功");

                }
            }
        }

        TextView know = view.findViewById(R.id.card_success_knowed_tv);
        know.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        MaterialAlterDialogManager.getInstance().setAlertDialogHeightWidth(alertDialog, dip2px(this, 250), dip2px(this, 320));
    }

    /**
     * 获取当前需要打卡的班次
     */
    private UserCardInfo.DataBean.ClockRecordListBean getCurrentShiftToClock(boolean refreshAdapter) {
        UserCardInfo.DataBean.ClockRecordListBean bean = null;
        int position = -1;
        List<UserCardInfo.DataBean.ClockRecordListBean> clockList = signInAdapter.getData();
        for (int i = 0; i < clockList.size(); i++) {
            UserCardInfo.DataBean.ClockRecordListBean clockRecordListBean = clockList.get(i);
            int result = clockRecordListBean.getResult();
            if (-1 == result) {
                bean = clockRecordListBean;
                position = i;
                break;
            }
        }
        if (refreshAdapter) {
            for (int i = 0; i < clockList.size(); i++) {
                UserCardInfo.DataBean.ClockRecordListBean clockRecordListBean = clockList.get(i);
                if (position == i) {
                    clockRecordListBean.setTimeTagLight(true);
                } else {
                    clockRecordListBean.setTimeTagLight(false);
                }
            }
            signInAdapter.notifyDataSetChanged();
        }
        return bean;
    }

    @Override
    public void onError(String tag) {
        stopMaterialProgressDialog();
        switch (tag) {
            case SignInContract.UPDATE_CLOCK://更新打卡
                showToast("更新打卡失败");
            default:
                showToast(tag);
                break;
        }

    }


    /**
     * 判断GPS是否开启，GPS或者AGPS开启一个就认为是开启的
     *
     * @param context
     * @return true 表示开启
     */
    public static boolean checkGpsIsEnable(Context context) {
		/* 用Setting.System来读取也可以，只是这是更旧的用法
		String str = Settings.System.getString(getContentResolver(),
				Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
		*/
        String str = Settings.Secure.getString(context.getContentResolver(),
                Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
        Log.v("GPS", str);
        return str != null && str.contains("gps");


    }


    /**
     * 跳转GPS设置
     */
    private void openGPSSettings() {
        if (checkGpsIsEnable(this)) {
//            initLocation(); //自己写的定位方法
        } else {
            //没有打开则弹出对话框
            AlertDialog alertDialog = new AlertDialog.Builder(this)
                    .setTitle(R.string.notifyTitle)
                    .setMessage(R.string.gpsNotifyMsg)
                    // 拒绝, 退出应用
                    .setNegativeButton(R.string.cancel,
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
//                                    finish();
                                }
                            })

                    .setPositiveButton(R.string.setting,
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    //跳转GPS设置界面
                                    Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                                    startActivityForResult(intent, GPS_REQUEST_CODE);
                                }
                            })

                    .setCancelable(false)
                    .show();
            MaterialAlterDialogManager.getInstance().setAlertDialogHeightWidth(alertDialog, -1, 0);

        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.sign_month_detail_ll:
//                int ruleId = pmAttendanceRulesDOBean == null ? -1 : pmAttendanceRulesDOBean.getId();
//                intent.putExtra(ActivityResultManager.RULEID, ruleId);
                MonthRecordActivity.startActivityOneself(this, MonthRecordActivity.class);
                break;
            case R.id.sign_ll://签到
                if (!hasClockAddr) {
                    showToast("未分配打卡地点，无法打卡");
                    return;
                }

                checkAppPermissions(new CheckPermListener() {
                    @Override
                    public void agreeAllPermission() {
                        if (!checkGpsIsEnable(SignInActivity.this)) {
                            openGPSSettings();
                            return;
                        }

                        UserCardInfo.DataBean.ClockRecordListBean bean = getCurrentShiftToClock(false);
                        //获取位置最近的打卡点
                        UserCardInfo.DataBean.ClockRecordListBean clockRecordListBean = getCurrentShiftToClock(false);
                        if (clockRecordListBean == null) {//没有需要打卡的班次了
                            showToast("无需打卡");
                            return;
                        } else {
                            String text = mSignRangeTv.getText().toString().trim();
                            if (!StrUtils.isStringValueOk(text)) {
                                Toast.makeText(getApplicationContext(), "正在获取位置，请稍后...", Toast.LENGTH_LONG).show();
                                return;
                            }
                        }
                        if (!isClockTime(bean)) {
                            return;
                        }

                            clickedPosition = -1;
                            int clockType = bean == null ? 1 : bean.getClockType();
                            if (1 == outsideId) {//超出范围 不能打卡
                                MaterialAlterDialogManager.getInstance().showMaterialAlterdialogOnlyOneBtToClose(SignInActivity.this,"已超出工作范围，不能打卡", "知道了");
                            } else {
                                showMaterialProgressDialog();
                                getPresenter().clock(UserInfoUtil.getInstance().getPropertyId(), UserInfoUtil.getInstance().getUserId(), outsideId, clockPlace, clockType, SignInContract.CHECK_WORK_SIGN);
                            }

                    }

                    @Override
                    public void selectedAllPermission() {

                    }
                }, R.string.perm_location, PubUtil.promissions[0]);

                break;
            case R.id.sign_Attendance_rules_tv:
                startActivity(new Intent(SignInActivity.this, AttendanceRuleActivity.class));
                break;
        }
    }


}
