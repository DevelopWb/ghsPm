package com.ghs.ghspm.models.workdesk.publicfuction.signIn.attendanceRule;

import android.widget.TextView;

import com.ghs.ghspm.R;
import com.ghs.ghspm.base.BaseActivity;
import com.ghs.ghspm.bean.AttendanceRuleBean;
import com.ghs.ghspm.models.workdesk.publicfuction.signIn.SignInContract;
import com.ghs.ghspm.models.workdesk.publicfuction.signIn.SignInPresent;
import com.ghs.ghspm.tools.CalendarUtil;
import com.ghs.ghspm.tools.UserInfoUtil;

/**
 * created by tobato
 * created date 2019/1/29 18:12.
 * application  考勤规则
 */
public class AttendanceRuleActivity extends BaseActivity<SignInContract.ISignInView, SignInPresent> implements SignInContract.ISignInView {

    /**
     * 考勤组
     */
    private TextView mAttendanceGroupTv;
    /**
     * 排班
     */
    private TextView mAttendanceShiftTv;
    /**
     * 白班-----------   早班--------------
     */
    private TextView mAttendanceAttendanceTime;
    /**
     * -------------------------
     */
    private TextView mAttendanceBelate;

    /**
     * 允许补卡，每月最多补卡8次
     */
    private TextView mAttendanceReplaceDescription;


    @Override
    public SignInPresent creatPresenter() {
        return new SignInPresent();
    }

    @Override
    public void setLayout() {
        setContentView(R.layout.activity_attendance_rule);

    }

    @Override
    public void initLayoutView() {
        initActionBar("考勤规则", null);
        initView();
    }

    @Override
    public void getDate() {
        getPresenter().getAttendanceRules(UserInfoUtil.getInstance().getPropertyId(), UserInfoUtil.getInstance().getUserId(), SignInContract.RULE);
    }

    @Override
    public void actionBarRightTvOnClick() {

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
            case SignInContract.RULE:

                AttendanceRuleBean attendanceRuleBean = (AttendanceRuleBean) o;
                if (attendanceRuleBean != null) {
                    AttendanceRuleBean.DataBean data = attendanceRuleBean.getData();
                    if (data != null) {

                        int type = data.getType();
                        if (type == 1) {
                            mAttendanceAttendanceTime.setText(data.getWeekDay() == null && data.getAttendanceTime() == null ? "暂无" :data.getAttendanceTime() +"\n"+data.getWeekDay());
                        } else if (type == 2) {
                            mAttendanceAttendanceTime.setText(data.getWeekDay() == null ? "暂无" : data.getWeekDay());
                        } else {
                            mAttendanceAttendanceTime.setText(data.getAttendanceTime() == null ? "暂无" : data.getAttendanceTime());

                        }
                        mAttendanceReplaceDescription.setText(data.getReplaceDescription() == null ? "暂无" : data.getReplaceDescription());
                        mAttendanceGroupTv.setText(data.getRulesName() == null ? "暂无" : "考勤组：" + data.getRulesName());
                        mAttendanceBelate.setText("迟到：" + data.getLateDescription() == null ? "暂无" : "迟到：" + data.getLateDescription());
                        mAttendanceShiftTv.setText("排班：" + getCurrentYear() + "." + CalendarUtil.getCurrentMonth() + "." + CalendarUtil.getCurrentDay());
                    }
                }

                break;
        }


    }

    @Override
    public void onError(String tag) {

    }

    private void initView() {
        mAttendanceGroupTv = (TextView) findViewById(R.id.attendance_group_tv);
        mAttendanceShiftTv = (TextView) findViewById(R.id.attendance_shift_tv);
        mAttendanceAttendanceTime = (TextView) findViewById(R.id.attendance_attendanceTime);
        mAttendanceBelate = (TextView) findViewById(R.id.attendance_belate);
        mAttendanceReplaceDescription = (TextView) findViewById(R.id.attendance_replaceDescription);

    }
}
