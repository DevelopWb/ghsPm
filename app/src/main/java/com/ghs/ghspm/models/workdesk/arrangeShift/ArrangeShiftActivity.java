package com.ghs.ghspm.models.workdesk.arrangeShift;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ghs.ghspm.R;
import com.ghs.ghspm.base.BaseActivity;
import com.ghs.ghspm.bean.ArrangeShiftBean;
import com.ghs.ghspm.bean.ArrangeUserDetailBean;
import com.ghs.ghspm.tools.CalendarUtil;
import com.ghs.ghspm.tools.PickerManager;
import com.ghs.ghspm.tools.StrUtils;
import com.ghs.ghspm.tools.UserInfoUtil;
import com.haibin.calendarview.CalendarView;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * created by tobato
 * created date 2019/1/31 15:03.
 * application   工作台   排班
 */
public class ArrangeShiftActivity extends BaseActivity<ArrangeShiftContact.IArrangeShiftView, ArrangeShiftPresent> implements View.OnClickListener, ArrangeShiftContact.IArrangeShiftView {

    private TextView mMonthRecordSelectMonthTv;
    private CalendarView mMonthRecordCalendarView;
    /**
     * 工作时间：8:00-18:00
     */
    private TextView mMonthRecordWorkTimeTv;
    /**
     * 当日未排班
     */
    private TextView mOffDutyNoticeTv;
    private LinearLayout mOffDutyNoticeLl;
    /**
     * 考勤地点
     */
    private int selectedYear;//已选择年份
    private int selectedMonth;//已选择月份
    private UserInfoUtil userInfoUtil;
    /**
     * 当日
     */
    private TextView mMonthRecordWorkStatusTv;
    /**
     * 当日排班
     */
    private TextView mCurrentDayShiftTv;
    /**
     * 考勤地点
     */
    private TextView mCardAddrTv;
    private LinearLayout mShiftInfoLl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public ArrangeShiftPresent creatPresenter() {
        return new ArrangeShiftPresent();
    }

    @Override
    public void setLayout() {
        setContentView(R.layout.activity_arrange_shift);

    }

    @Override
    public void initLayoutView() {
        initView();
        initActionBar("我的排班", null);

    }

    @Override
    public void getDate() {
        userInfoUtil = UserInfoUtil.getInstance();
        getPresenter().getArrangeShiftCalendarData(userInfoUtil.getPropertyId(), userInfoUtil.getUserId(), CalendarUtil.getCurrentYear(), CalendarUtil.getCurrentMonth(), ArrangeShiftContact.ARRANGE_CAKENDAR);
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
                    String workTime = calendar.getYear()
                            + "年" + calendar.getMonth() + "月" + calendar.getDay() + "日";
                    mMonthRecordWorkTimeTv.setText(workTime);
                    String arrangeTag = calendar.getScheme();
                    String time = calendar.getYear()
                            + "-" + calendar.getMonth() + "-" + calendar.getDay();
                    initArrangeShiftStatus(arrangeTag, time);

                }

            }
        });
        mMonthRecordWorkTimeTv = (TextView) findViewById(R.id.month_record_work_time_tv);
        mMonthRecordWorkTimeTv.setText(CalendarUtil.getTimeFromDate("yyyy年MM月dd日", new Date()));
        mOffDutyNoticeTv = (TextView) findViewById(R.id.off_duty_notice_tv);
        mOffDutyNoticeLl = (LinearLayout) findViewById(R.id.off_duty_notice_ll);
        mMonthRecordWorkStatusTv = (TextView) findViewById(R.id.month_record_work_status_tv);
        mCurrentDayShiftTv = (TextView) findViewById(R.id.current_day_shift_tv);
        mCardAddrTv = (TextView) findViewById(R.id.card_addr_tv);
        mShiftInfoLl = (LinearLayout) findViewById(R.id.shift_info_ll);
    }

    /**
     * 初始化排班详情
     *
     * @param arrangeTag
     */
    private void initArrangeShiftStatus(String arrangeTag, String day) {
        if (StrUtils.isStringValueOk(arrangeTag)) {
            if ("休".equals(arrangeTag)) {
                todyIsOnDuty(false, "当日休息");
                mMonthRecordWorkStatusTv.setTextColor(Color.parseColor("#F56437"));
                mMonthRecordWorkStatusTv.setText("当日休息");

            } else if ("值".equals(arrangeTag)) {
                todyIsOnDuty(true, "");
                mMonthRecordWorkStatusTv.setTextColor(Color.parseColor("#00ACA6"));
                mMonthRecordWorkStatusTv.setText("当日值班");
            } else {
                todyIsOnDuty(true, "");
                mMonthRecordWorkStatusTv.setVisibility(View.INVISIBLE);

            }
            // 请求考勤信息
            getPresenter().getArrangeUserDetail(userInfoUtil.getPropertyId(), userInfoUtil.getUserId(), day, ArrangeShiftContact.ARRANGE_USER_DETAIL);

        } else {
            todyIsOnDuty(false, "当日未排班");
            mMonthRecordWorkStatusTv.setText("");

        }
    }

    /**
     * 今天是否当值
     */
    private void todyIsOnDuty(boolean isOnDuty, String notice) {
        mMonthRecordWorkStatusTv.setVisibility(View.VISIBLE);
        if (isOnDuty) {
            mShiftInfoLl.setVisibility(View.VISIBLE);
            mOffDutyNoticeLl.setVisibility(View.GONE);
        } else {
            mShiftInfoLl.setVisibility(View.GONE);
            mOffDutyNoticeLl.setVisibility(View.VISIBLE);
            if (notice != null) {
                mOffDutyNoticeTv.setText(notice);
            }

        }
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
                            mMonthRecordWorkTimeTv.setText(selectedYear + "年" + selectedMonth + "月" + CalendarUtil.getCurrentDay() + "日");

                        } else {
                            //滚动到指定日期的日历
                            mMonthRecordCalendarView.scrollToCalendar(selectedYear, selectedMonth, 1);
                            mMonthRecordWorkTimeTv.setText(selectedYear + "年" + selectedMonth + "月" + "1日");
                        }
                        getPresenter().getArrangeShiftCalendarData(userInfoUtil.getPropertyId(), userInfoUtil.getUserId(), selectedYear, selectedMonth, ArrangeShiftContact.ARRANGE_CAKENDAR);

                    }
                });
                break;
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
            case ArrangeShiftContact.ARRANGE_CAKENDAR:
                ArrangeShiftBean arrangeShiftBean = (ArrangeShiftBean) o;
                if (arrangeShiftBean != null) {
                    List<ArrangeShiftBean.DataBean> arrays = arrangeShiftBean.getData();
                    if (arrays != null && arrays.size() > 0) {
                        Map<String, com.haibin.calendarview.Calendar> map = new HashMap<>();

                        for (ArrangeShiftBean.DataBean array : arrays) {
                            String time = array.getDay();

                            int arranged = array.getArrange();
                            int duty = array.getDuty();
                            int rest = array.getRest();
                            if (StrUtils.isStringValueOk(time)) {
                                initSelectedDayArrangeShiftStatus(time, arranged, duty, rest,time);
                                if (time.contains("-")) {
                                    String[] date = time.split("-");
                                    if (1 == arranged) {//排班了
                                        if (0 == duty && 0 == rest) {
                                            map.put(getSchemeCalendar(Integer.parseInt(date[0]), Integer.parseInt(date[1]), Integer.parseInt(date[2]), Color.parseColor("#ffffff"), "空").toString(),
                                                    getSchemeCalendar(Integer.parseInt(date[0]), Integer.parseInt(date[1]), Integer.parseInt(date[2]), Color.parseColor("#ffffff"), "空"));
                                        } else {
                                            if (1 == duty) {//值班  绿色
                                                map.put(getSchemeCalendar(Integer.parseInt(date[0]), Integer.parseInt(date[1]), Integer.parseInt(date[2]), Color.parseColor("#00ACA6"), "值").toString(),
                                                        getSchemeCalendar(Integer.parseInt(date[0]), Integer.parseInt(date[1]), Integer.parseInt(date[2]), Color.parseColor("#00ACA6"), "值"));
                                            }
                                            if (1 == rest) {//休息  红色
                                                map.put(getSchemeCalendar(Integer.parseInt(date[0]), Integer.parseInt(date[1]), Integer.parseInt(date[2]), Color.parseColor("#F56437"), "休").toString(),
                                                        getSchemeCalendar(Integer.parseInt(date[0]), Integer.parseInt(date[1]), Integer.parseInt(date[2]), Color.parseColor("#F56437"), "休"));
                                            }
                                        }


                                    }
                                }
                            }

                        }

                        //此方法在巨大的数据量上不影响遍历性能，推荐使用
                        mMonthRecordCalendarView.setSchemeDate(map);
                    } else {
                        initSelectedDayArrangeShiftStatus("", 0, 0, 0,"");
                    }
                }
                break;
            case ArrangeShiftContact.ARRANGE_USER_DETAIL:
                ArrangeUserDetailBean detailBean = (ArrangeUserDetailBean) o;
                if (detailBean != null) {
                    ArrangeUserDetailBean.DataBean dataBean = detailBean.getData();
                    if (dataBean != null) {
                        mCurrentDayShiftTv.setText("当日排班：" + dataBean.getShiftMsg());
                        mCardAddrTv.setText("考勤地点：" + dataBean.getClockPlace());
                    }
                }
                break;
            default:
                break;
        }
    }

    /**
     * 初始化被选中的当天排班详情
     *
     * @param time
     * @param arranged
     * @param duty
     */
    private void initSelectedDayArrangeShiftStatus(String time, int arranged, int duty, int rest, String day) {
        com.haibin.calendarview.Calendar calendar = mMonthRecordCalendarView.getSelectedCalendar();
        String selectedDay = calendar.toString();
        selectedDay = CalendarUtil.getTimeFromStringTime("yyyy-MM-dd", "yyyyMMdd", selectedDay);
        if (selectedDay.equals(time)) {
            if (0 == arranged) {
                initArrangeShiftStatus("", day);
            } else {
                if (0 == duty && 0 == rest) {
                    initArrangeShiftStatus("排", day);
                } else {
                    if (1 == duty) {
                        initArrangeShiftStatus("值", day);
                    }
                    if (1 == rest) {
                        initArrangeShiftStatus("休", day);
                    }
                }

            }

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

    }
}
