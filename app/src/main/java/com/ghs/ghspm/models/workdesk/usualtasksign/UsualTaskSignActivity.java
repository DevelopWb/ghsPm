package com.ghs.ghspm.models.workdesk.usualtasksign;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ghs.ghspm.R;
import com.ghs.ghspm.base.BaseActivity;
import com.ghs.ghspm.base.BasePresent;
import com.ghs.ghspm.models.workdesk.usualtasksign.patrolwork.PatrolWorkActivity;
import com.ghs.ghspm.models.workdesk.usualtasksign.shiftwork.ShiftWorkActivity;

public class UsualTaskSignActivity extends BaseActivity implements View.OnClickListener {

    /**
     * 任务一：轮岗
     */
    private TextView mUsualTaskSignShiftTv;
    /**
     * 任务二：巡岗
     */
    private TextView mUsualTaskSignPatrolTv;

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
        initActionBar("任务打卡", null);
    }

    @Override
    public void setLayout() {
        setContentView(R.layout.activity_usual_task_sign);

    }

    @Override
    public void actionBarRightTvOnClick() {

    }

    @Override
    public BasePresent creatPresenter() {
        return null;
    }

    private void initView() {
        mUsualTaskSignShiftTv = (TextView) findViewById(R.id.usual_task_sign_shift_tv);
        mUsualTaskSignShiftTv.setOnClickListener(this);
        mUsualTaskSignPatrolTv = (TextView) findViewById(R.id.usual_task_sign_patrol_tv);
        mUsualTaskSignPatrolTv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.usual_task_sign_shift_tv:
                startActivity(new Intent(this, ShiftWorkActivity.class));
                break;
            case R.id.usual_task_sign_patrol_tv:
                startActivity(new Intent(this, PatrolWorkActivity.class));

                break;
        }
    }
}
