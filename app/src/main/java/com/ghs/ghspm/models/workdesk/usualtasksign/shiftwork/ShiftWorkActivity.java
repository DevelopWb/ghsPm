package com.ghs.ghspm.models.workdesk.usualtasksign.shiftwork;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ghs.ghspm.R;
import com.ghs.ghspm.base.BaseActivity;
import com.ghs.ghspm.base.BasePresent;
/**
 * created by 8级大的狂风
 * created date 2018/9/25 13:34.
 * application  轮岗打卡
 */
public class ShiftWorkActivity extends BaseActivity implements View.OnClickListener {

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
    /**
     * 早班：8:00-18:00(前后30分钟有效)
     */
    private TextView mShiftWorkTypeTv;
    /**
     * 负责人：李四
     */
    private TextView mShiftWorkLeaderTv;
    /**
     * 创建人：李四
     */
    private TextView mShiftWorkCreaterTv;
    /**
     * 已打卡
     */
    private TextView mShiftWorkOnWorkTv;
    /**
     * 已打卡
     */
    private TextView mShiftWorkOffWorkTv;

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
        initActionBar("轮岗打卡",null);

    }

    @Override
    public void setLayout() {
        setContentView(R.layout.activity_shift_work);

    }

    @Override
    public void actionBarRightTvOnClick() {

    }

    @Override
    public BasePresent creatPresenter() {
        return null;
    }

    private void initView() {
        mSignUserHeadIv = (ImageView) findViewById(R.id.sign_user_head_iv);
        mSignUserNameTv = (TextView) findViewById(R.id.sign_user_name_tv);
        mSignTypeTv = (TextView) findViewById(R.id.sign_type_tv);
        mSignMonthDetailLl = (LinearLayout) findViewById(R.id.sign_month_detail_ll);
        mSignMonthDetailLl.setOnClickListener(this);
        mShiftWorkTypeTv = (TextView) findViewById(R.id.shift_work_type_tv);
        mShiftWorkLeaderTv = (TextView) findViewById(R.id.shift_work_leader_tv);
        mShiftWorkCreaterTv = (TextView) findViewById(R.id.shift_work_creater_tv);
        mShiftWorkOnWorkTv = (TextView) findViewById(R.id.shift_work_on_work_tv);
        mShiftWorkOnWorkTv.setOnClickListener(this);
        mShiftWorkOffWorkTv = (TextView) findViewById(R.id.shift_work_off_work_tv);
        mShiftWorkOffWorkTv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.sign_month_detail_ll:
                break;
            case R.id.shift_work_on_work_tv:
                break;
            case R.id.shift_work_off_work_tv:
                break;
        }
    }
}
