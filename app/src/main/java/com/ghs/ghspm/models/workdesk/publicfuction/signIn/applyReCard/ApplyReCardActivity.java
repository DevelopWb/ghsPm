package com.ghs.ghspm.models.workdesk.publicfuction.signIn.applyReCard;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ghs.ghspm.R;
import com.ghs.ghspm.base.BaseActivity;
import com.ghs.ghspm.bean.PrepareFillCardBean;
import com.ghs.ghspm.models.workdesk.publicfuction.signIn.SignInContract;
import com.ghs.ghspm.models.workdesk.publicfuction.signIn.SignInPresent;
import com.ghs.ghspm.tools.ActivityResultManager;
import com.ghs.ghspm.tools.CalendarUtil;
import com.ghs.ghspm.tools.PickerManager;
import com.ghs.ghspm.tools.StrUtils;
import com.ghs.ghspm.tools.UserInfoUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * created by tobato
 * created date 2019/1/29 16:34.
 * application   申请补卡
 */
public class ApplyReCardActivity extends BaseActivity<SignInContract.ISignInView, SignInPresent> implements View.OnClickListener, SignInContract.ISignInView {

    /**
     * 补卡日期
     */
    private TextView mRecardDateTv;
    /**
     * 班次
     */
    private TextView mRecardShiftTv;
    /**
     * 请选择
     */
    private TextView mDynamicLayoutSelectTv;
    private ConstraintLayout mDynamicLayoutCl;
    /**
     * 补卡日期
     */
    private TextView mRecardNumberTv;
    /**
     * 请输入
     */
    private EditText mLackCardReasonEt;
    /**
     * 提交
     */
    private TextView mRecardCommitTv;
    /**
     * 楼号
     */
    private TextView mDynamicLayoutTitleTv;
    private PrepareFillCardBean.DataBean prepareFillCardBean;
    private ArrayList<String> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();

    private  String  reCardYear = "";//补卡的年份


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public SignInPresent creatPresenter() {
        return new SignInPresent();
    }

    @Override
    public void setLayout() {
        setContentView(R.layout.activity_apply_re_card);

    }

    @Override
    public void initLayoutView() {
        initView();
        initActionBar("申请补卡", null);

    }

    @Override
    public void getDate() {
        Intent intent = getIntent();
        int clockId = intent.getIntExtra("clockId", -1);
        if (clockId != -1) {
            getPresenter().getReplaceClock(UserInfoUtil.getInstance().getPropertyId(), UserInfoUtil.getInstance().getUserId(), clockId, SignInContract.READlY_REPLACE_CLOCK);
        }
    }

    @Override
    public void actionBarRightTvOnClick() {

    }

    private void initView() {
        mRecardDateTv = (TextView) findViewById(R.id.attendance_date_tv);
        mRecardShiftTv = (TextView) findViewById(R.id.recard_shift_tv);
        mDynamicLayoutSelectTv = (TextView) findViewById(R.id.dynamic_layout_select_tv);
        mDynamicLayoutSelectTv.setText("请选择补卡时间");
        mDynamicLayoutCl = (ConstraintLayout) findViewById(R.id.dynamic_layout_cl);
        mDynamicLayoutCl.setOnClickListener(this);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(mDynamicLayoutCl.getLayoutParams());
        layoutParams.setMargins(0, 0, 0, 0);
        mDynamicLayoutCl.setLayoutParams(layoutParams);
        mRecardNumberTv = (TextView) findViewById(R.id.recard_number_tv);
        mLackCardReasonEt = (EditText) findViewById(R.id.lack_card_reason_et);
        mRecardCommitTv = (TextView) findViewById(R.id.recard_commit_tv);
        mRecardCommitTv.setOnClickListener(this);
        mDynamicLayoutTitleTv = (TextView) findViewById(R.id.reason_of_abcence_title_tv);
        mDynamicLayoutTitleTv.setText("补卡时间");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.dynamic_layout_cl:
                if (prepareFillCardBean != null) {
                    final  List<String>  dates = getRecardDate(prepareFillCardBean.getReplaceTimeRange());
                    PickerManager.getInstance().showOptionPicker(ApplyReCardActivity.this, dates, new PickerManager.OnOptionPickerSelectedListener() {
                        @Override
                        public void onOptionsSelect(int options1, int option2, int options3, View v) {
                            //返回的分别是三个级别的选中位置
                            reCardYear = dates.get(options1);
                            showHourAndMinuData(prepareFillCardBean.getReplaceTimeRange(),reCardYear );
                        }
                    });
                }else{
                    showToast("无法获取补卡时间段");
                }


                break;
            case R.id.recard_commit_tv:
                String recardTime = mDynamicLayoutSelectTv.getText().toString().trim();
                String recardReason = mLackCardReasonEt.getText().toString().trim();
                    if (!StrUtils.isStringValueOk(recardTime) || "请选择补卡时间".equals(recardTime)) {
                        showToast("补卡时间未选定");
                        return;
                    }
                    if (!StrUtils.isStringValueOk(recardReason)) {
                        showToast("缺卡原因未填写");
                        return;
                    }
                    if (prepareFillCardBean != null) {
                            getPresenter().getReplaceClocked(UserInfoUtil.getInstance().getPropertyId(), UserInfoUtil.getInstance().getUserId(), prepareFillCardBean.getClockId(), recardTime, recardReason, SignInContract.REPLACE_CLOCK);
                    }
                break;
        }
    }

    /**
     * 展示小时和分钟的弹框
     */
    private void showHourAndMinuData(String range, String time) {
        getRecardHourAndMinuData(range, time);
        //条件选择器
        PickerManager.getInstance().showOptionPicker(this, options1Items,options2Items,new PickerManager.OnOptionPickerSelectedListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String tx = options1Items.get(options1)+":"
                        + options2Items.get(options1).get(option2);
                mDynamicLayoutSelectTv.setText(reCardYear+" "+tx);
            }
        });

    }

    /**
     * 获取补卡的时间和分钟
     */
    private void getRecardHourAndMinuData(String range, String time) {

        if (StrUtils.isStringValueOk(range)) {
            if (range.contains("#")) {
                String[] times = range.split("#");
                String startDate = CalendarUtil.getTimeFromStringTime("yyyy-MM-dd", "yyyy-MM-dd HH:mm", times[0]);
                String endDate = CalendarUtil.getTimeFromStringTime("yyyy-MM-dd", "yyyy-MM-dd HH:mm", times[1]);
                if (startDate.equals(endDate)) {//不跨天
                    int startHour = Integer.parseInt(CalendarUtil.getTimeFromStringTime("HH", "yyyy-MM-dd HH:mm", times[0]));
                    int endHour = Integer.parseInt(CalendarUtil.getTimeFromStringTime("HH", "yyyy-MM-dd HH:mm", times[1]));
                    int startMinu = Integer.parseInt(CalendarUtil.getTimeFromStringTime("mm", "yyyy-MM-dd HH:mm", times[0]));
                    int endMinu = Integer.parseInt(CalendarUtil.getTimeFromStringTime("mm", "yyyy-MM-dd HH:mm", times[1]));
                    getOption2Data(startHour, endHour, startMinu, endMinu);

                } else {//时间段跨天
                    if (time.equals(startDate)) {
                        //当天
                        int startHour = Integer.parseInt(CalendarUtil.getTimeFromStringTime("HH", "yyyy-MM-dd HH:mm", times[0]));
                        int startMinu = Integer.parseInt(CalendarUtil.getTimeFromStringTime("mm", "yyyy-MM-dd HH:mm", times[0]));
                        int endHour = 23;
                        int endMinu = 59;
                        getOption2Data(startHour, endHour, startMinu, endMinu);
                    } else if (time.equals(endDate)) {
                        //次日
                        int startHour = 0;
                        int startMinu = 0;
                        int endHour = Integer.parseInt(CalendarUtil.getTimeFromStringTime("HH", "yyyy-MM-dd HH:mm", times[1]));
                        int endMinu = Integer.parseInt(CalendarUtil.getTimeFromStringTime("mm", "yyyy-MM-dd HH:mm", times[1]));
                        getOption2Data(startHour, endHour, startMinu, endMinu);
                    }
                }
            }
        }


    }

    /**
     * 获取分钟的数据
     *
     * @param startHour
     * @param endHour
     * @param startMinu
     * @param endMinu
     */
    private void getOption2Data(int startHour, int endHour, int startMinu, int endMinu) {
        options1Items.clear();
        options2Items.clear();
        for (int i = startHour; i < endHour + 1; i++) {
            Log.d(TAG, i + "-------------");
            if (i<10) {
                options1Items.add("0"+String.valueOf(i));
            }else{
                options1Items.add(String.valueOf(i));
            }
            if (startHour == i) {//第一个时间
                ArrayList<String> option_start = new ArrayList<>();
                for (int i1 = startMinu; i1 < 60; i1++) {
                    if (i1<10) {
                        option_start.add("0"+String.valueOf(i1));
                    }else{
                        option_start.add(String.valueOf(i1));
                    }
                }
                options2Items.add(option_start);
            } else if (endHour == i) {//最后一个时间
                ArrayList<String> option_end = new ArrayList<>();
                for (int i1 = 0; i1 < endMinu + 1; i1++) {
                    if (i1<10) {
                        option_end.add("0"+String.valueOf(i1));
                    }else{
                        option_end.add(String.valueOf(i1));
                    }
                }
                options2Items.add(option_end);
            } else {
                ArrayList<String> option = new ArrayList<>();
                for (int i1 = 0; i1 < 60; i1++) {
                    if (i1<10) {
                        option.add("0"+String.valueOf(i1));
                    }else{
                        option.add(String.valueOf(i1));
                    }
                }
                options2Items.add(option);
            }
        }
    }

    /**
     * 获取补卡的年月日
     */
    private List<String> getRecardDate(String range) {
        List<String> arrays = new ArrayList<>(2);
        if (StrUtils.isStringValueOk(range)) {
            if (range.contains("#")) {
                String[] times = range.split("#");
                String startDate = CalendarUtil.getTimeFromStringTime("yyyy-MM-dd", "yyyy-MM-dd HH:mm", times[0]);
                String endDate = CalendarUtil.getTimeFromStringTime("yyyy-MM-dd", "yyyy-MM-dd HH:mm", times[1]);
                if (startDate.equals(endDate)) {//不跨天
                    arrays.add(startDate);
                } else {//时间段跨天
                    arrays.add(startDate);
                    arrays.add(endDate);
                }
            }
        }
        return arrays;
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
            case SignInContract.READlY_REPLACE_CLOCK:
                PrepareFillCardBean  bean = (PrepareFillCardBean) o;
                if (bean != null) {
                    prepareFillCardBean = bean.getData();
                    if (prepareFillCardBean != null) {
                        mRecardDateTv.setText("补卡日期：" + getCurrentYear() + "." + CalendarUtil.getCurrentMonth() + "." + CalendarUtil.getCurrentDay());
                        mRecardShiftTv.setText("班次：" + prepareFillCardBean.getShiftTime() == null ? "暂无" : "班次：" + prepareFillCardBean.getShiftTime());
                        mRecardNumberTv.setText("本月已申请补卡次数 " + prepareFillCardBean.getAlreadyReplaceNum() + " 次  剩余 " + prepareFillCardBean.getLeaveReplaceNum() + " 次");
                    }
                }
                break;
            case SignInContract.REPLACE_CLOCK:
                showToast("已提交");
                setResult(ActivityResultManager.RECARD_SUCCESS);
                finish();
                break;

        }


    }

    @Override
    public void onError(String tag) {
        showToast(tag);
    }
}
