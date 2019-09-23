package com.ghs.ghspm.models.workdesk.publicfuction.signIn.monthrecord;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ghs.ghspm.R;
import com.ghs.ghspm.base.BaseActivity;
import com.ghs.ghspm.bean.ShiftRecordBean;
import com.ghs.ghspm.bean.SignStaticsBean;
import com.ghs.ghspm.models.workdesk.publicfuction.signIn.applyReCard.ApplyReCardActivity;
import com.ghs.ghspm.tools.ActivityResultManager;
import com.ghs.ghspm.tools.CalendarUtil;
import com.ghs.ghspm.tools.StrUtils;
import com.ghs.ghspm.tools.PickerManager;
import com.ghs.ghspm.tools.UserInfoUtil;
import com.haibin.calendarview.CalendarView;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MonthRecordActivity extends BaseActivity<MonthRecordContract.IMonthRecordView, MonthRecordPresent> implements View.OnClickListener, MonthRecordContract.IMonthRecordView {

    /**
     * 9月
     */
    private TextView mMonthRecordSelectMonthTv;
    private CalendarView mMonthRecordCalendarView;
    /**
     * 工作时间：8:00-18:00
     */
    private TextView mMonthRecordWorkTimeTv;
    private RecyclerView mMonthRecordRv;
    private SignRecordAdapter signRecordAdapter;
    private int selectedYear;//已选择年份
    private int selectedMonth;//已选择月份
    private LinearLayout mHasClockRecordLl;
    /**
     * 今日未排班
     */
    private TextView mOffDutyNoticeTv;
    private LinearLayout mOffDutyNoticeLl;

    /**
     * 启动activity
     */
    public static void startActivityOneself(Context context, Class<?> cls) {
        Intent intent = new Intent(context, cls);
        context.startActivity(intent);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public MonthRecordPresent creatPresenter() {
        return new MonthRecordPresent();
    }

    @Override
    public void setLayout() {
        setContentView(R.layout.activity_month_record);

    }

    @Override
    public void initLayoutView() {
        initView();

        initActionBar("打卡月历", null);



    }

    @Override
    public void getDate() {
        selectedYear = Integer.parseInt(CalendarUtil.getTimeFromDate("yyyy", new Date()));
        selectedMonth = Integer.parseInt(CalendarUtil.getTimeFromDate("MM", new Date()));
        //获取考勤记录
        getPresenter().shiftRecordEveryDay(UserInfoUtil.getInstance().getPropertyId(), UserInfoUtil.getInstance().getUserId(), CalendarUtil.getCurrentTimeNormal("yyyy-MM-dd"), MonthRecordContract.SIGN_RECORD);
        //获取考勤统计
        getPresenter().signStatics(UserInfoUtil.getInstance().getPropertyId(), UserInfoUtil.getInstance().getUserId(), selectedYear, selectedMonth, MonthRecordContract.SIGN_STATICS);
    }

    @Override
    public void actionBarRightTvOnClick() {

    }

    private void initView() {
        mMonthRecordSelectMonthTv = (TextView) findViewById(R.id.month_record_select_month_tv);
        mMonthRecordSelectMonthTv.setText(CalendarUtil.getCurrentMonth() + "月");
        mMonthRecordSelectMonthTv.setOnClickListener(this);
        mMonthRecordCalendarView = (CalendarView) findViewById(R.id.month_record_calendarView);
        mMonthRecordCalendarView.setOnCalendarSelectListener(new CalendarView.OnCalendarSelectListener() {
            @Override
            public void onCalendarOutOfRange(com.haibin.calendarview.Calendar calendar) {

            }

            @Override
            public void onCalendarSelect(com.haibin.calendarview.Calendar calendar, boolean isClick) {
                if (isClick) {
                    //查看打卡记录
                    String time = calendar.getYear() + "-" + calendar.getMonth() + "-" + calendar.getDay();
                    getPresenter().shiftRecordEveryDay(UserInfoUtil.getInstance().getPropertyId(), UserInfoUtil.getInstance().getUserId(), time, MonthRecordContract.SIGN_RECORD);

                }

            }
        });
        mMonthRecordWorkTimeTv = (TextView) findViewById(R.id.month_record_work_time_tv);
        mMonthRecordRv = (RecyclerView) findViewById(R.id.month_record_rv);
        signRecordAdapter = new SignRecordAdapter(R.layout.sign_record_item);
        initRecyclerview(mMonthRecordRv, signRecordAdapter, LinearLayoutManager.VERTICAL, false,true);
        signRecordAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                final ShiftRecordBean.DataBean.PmAttendanceClockDOListBean bean = (ShiftRecordBean.DataBean.PmAttendanceClockDOListBean) adapter.getData().get(position);
                int clockId = bean.getId();
                Intent intent = new Intent(MonthRecordActivity.this, ApplyReCardActivity.class);
                intent.putExtra("clockId", clockId);
                startActivityForResult(intent, ActivityResultManager.RECARD_SUCCESS);
            }
        });

        mHasClockRecordLl = (LinearLayout) findViewById(R.id.has_clock_record_ll);
        mOffDutyNoticeTv = (TextView) findViewById(R.id.off_duty_notice_tv);
        mOffDutyNoticeLl = (LinearLayout) findViewById(R.id.off_duty_notice_ll);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (ActivityResultManager.RECARD_SUCCESS == resultCode) {
            com.haibin.calendarview.Calendar calendar = mMonthRecordCalendarView.getSelectedCalendar();
            String time = calendar.getYear() + "-" + calendar.getMonth() + "-" + calendar.getDay();
            getPresenter().shiftRecordEveryDay(UserInfoUtil.getInstance().getPropertyId(), UserInfoUtil.getInstance().getUserId(), time, MonthRecordContract.SIGN_RECORD);

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
        switch (tag) {
            case MonthRecordContract.SIGN_RECORD:
                ShiftRecordBean signRecordBean = (ShiftRecordBean) o;

                if (signRecordBean != null) {
                    ShiftRecordBean.DataBean dataBean = signRecordBean.getData();
                    if (dataBean != null) {

                        List<ShiftRecordBean.DataBean.PmAttendanceClockDOListBean> arrays = dataBean.getPmAttendanceClockDOList();
                        if (arrays != null && arrays.size() > 0) {
                            signRecordAdapter.setNewData(arrays);
                            String workTime = dataBean.getWorkTime();
                            mMonthRecordWorkTimeTv.setText(workTime);
                            hasClockRecord(true, "");
                        } else {
                            hasClockRecord(false, "当日无打卡记录");

                        }
                    }
                }
                break;
            case MonthRecordContract.SIGN_STATICS:
                SignStaticsBean signStaticsBean = (SignStaticsBean) o;
                if (signStaticsBean != null) {
                    List<SignStaticsBean.DataBean> lists = signStaticsBean.getData();
                    if (lists != null) {
                        if (lists.size() > 0) {
                            Map<String, com.haibin.calendarview.Calendar> map = new HashMap<>();
                            for (SignStaticsBean.DataBean list : lists) {
                                String time = list.getDay();
                                int result = list.getResult();
                                if (StrUtils.isStringValueOk(time)) {
                                    if (time.contains("-")) {
                                        String[] date = time.split("-");
                                        switch (result) {
                                            case 1://无状态

                                                break;
                                            case 2://正常
                                                map.put(getSchemeCalendar(Integer.parseInt(date[0]), Integer.parseInt(date[1]), Integer.parseInt(date[2]), Color.parseColor("#2AA146"), "").toString(),
                                                        getSchemeCalendar(Integer.parseInt(date[0]), Integer.parseInt(date[1]), Integer.parseInt(date[2]), Color.parseColor("#2AA146"), ""));
                                                break;
                                            case 3://异常
                                                map.put(getSchemeCalendar(Integer.parseInt(date[0]), Integer.parseInt(date[1]), Integer.parseInt(date[2]), Color.parseColor("#F7805C"), "").toString(),
                                                        getSchemeCalendar(Integer.parseInt(date[0]), Integer.parseInt(date[1]), Integer.parseInt(date[2]), Color.parseColor("#F7805C"), ""));
                                                break;
                                            default:
                                                break;
                                        }
                                    }
                                }
                            }
                            //此方法在巨大的数据量上不影响遍历性能，推荐使用
                            mMonthRecordCalendarView.setSchemeDate(map);
                        }
                    }
                }
                break;
            case MonthRecordContract.SIGN_REPLACE:
                showNormalToast("补卡成功");
//                //获取考勤记录
                getPresenter().shiftRecordEveryDay(UserInfoUtil.getInstance().getPropertyId(), UserInfoUtil.getInstance().getUserId(), CalendarUtil.getCurrentTimeNormal("yyyy-MM-dd"), MonthRecordContract.SIGN_RECORD);

                break;
            default:
                break;
        }
    }

    /**
     * 存在打卡记录时的状态
     */
    private void hasClockRecord(boolean hasRecord, String notice) {
        if (hasRecord) {
            mHasClockRecordLl.setVisibility(View.VISIBLE);
            mOffDutyNoticeLl.setVisibility(View.GONE);
        } else {
            mHasClockRecordLl.setVisibility(View.GONE);
            mOffDutyNoticeLl.setVisibility(View.VISIBLE);
            mOffDutyNoticeTv.setText(notice);

        }
    }

    private com.haibin.calendarview.Calendar getSchemeCalendar(int year, int month, int day, int color, String text) {
        com.haibin.calendarview.Calendar calendar = new com.haibin.calendarview.Calendar();
        calendar.setYear(year);
        calendar.setMonth(month);
        calendar.setDay(day);
        calendar.setSchemeColor(color);//如果单独标记颜色、则会使用这个颜色
        calendar.setScheme(text);
        return calendar;
    }

    @Override
    public void onError(String tag) {
        stopMaterialProgressDialog();
        showToast(tag);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.month_record_select_month_tv:
                Calendar selectedDate = Calendar.getInstance();
                String startYear = CalendarUtil.getLastDateOfMonth(-11, "yyyy");
                String startMonth = CalendarUtil.getLastDateOfMonth(-11, "MM");
                String endYear = CalendarUtil.getLastDateOfMonth(0, "yyyy");
                String endMonth = CalendarUtil.getLastDateOfMonth(0, "MM");
                Calendar startDate = Calendar.getInstance();
                Calendar endDate = Calendar.getInstance();
                //正确设置方式 原因：注意事项有说明
                startDate.set(Integer.parseInt(startYear), Integer.parseInt(startMonth), 1);
                endDate.set(Integer.parseInt(endYear), Integer.parseInt(endMonth), 1);
                //时间选择器
                PickerManager.getInstance().showTimePickerView(this, new boolean[]{true, true, false, false, false, false}, null, new PickerManager.OnTimePickerTimeSelectedListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        selectedYear = Integer.parseInt(CalendarUtil.getTimeFromDate("yyyy", date));
                        selectedMonth = Integer.parseInt(CalendarUtil.getTimeFromDate("MM", date));
                        mMonthRecordSelectMonthTv.setText(selectedMonth + "月");
                        if (CalendarUtil.getTimeFromDate("yyyy-MM", date).equals(CalendarUtil.getTimeFromDate("yyyy-MM", new Date()))) {//当月界面
                            //滚动到指定日期的日历
                            mMonthRecordCalendarView.scrollToCalendar(selectedYear, selectedMonth, CalendarUtil.getCurrentDay());
                            //获取考勤记录
                            getPresenter().shiftRecordEveryDay(UserInfoUtil.getInstance().getPropertyId(), UserInfoUtil.getInstance().getUserId(), selectedYear + "-" + selectedMonth + "-" + CalendarUtil.getCurrentDay(), MonthRecordContract.SIGN_RECORD);

                        } else {
                            //滚动到指定日期的日历
                            mMonthRecordCalendarView.scrollToCalendar(selectedYear, selectedMonth, 1);
                            //获取考勤记录
                            getPresenter().shiftRecordEveryDay(UserInfoUtil.getInstance().getPropertyId(), UserInfoUtil.getInstance().getUserId(), selectedYear + "-" + selectedMonth + "-1", MonthRecordContract.SIGN_RECORD);

                        }

                        //获取考勤统计
                        getPresenter().signStatics(UserInfoUtil.getInstance().getPropertyId(), UserInfoUtil.getInstance().getUserId(), selectedYear, selectedMonth, MonthRecordContract.SIGN_STATICS);
                    }
                });
                break;
        }
    }


}
