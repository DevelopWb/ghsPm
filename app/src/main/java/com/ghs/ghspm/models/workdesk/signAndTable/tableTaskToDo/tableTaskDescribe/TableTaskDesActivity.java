package com.ghs.ghspm.models.workdesk.signAndTable.tableTaskToDo.tableTaskDescribe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.ghs.ghspm.R;
import com.ghs.ghspm.base.BaseActivity;
import com.ghs.ghspm.bean.KeyValueBean;
import com.ghs.ghspm.bean.TableTaskDetailBean;
import com.ghs.ghspm.models.workdesk.signAndTable.SignTableContrct;
import com.ghs.ghspm.models.workdesk.signAndTable.SignTablePresent;
import com.ghs.ghspm.models.workdesk.signAndTable.tableTaskToDo.createForm.CreateFormActivity;
import com.ghs.ghspm.models.workdesk.signAndTable.tableTaskToDo.formTaskSign.autoForm.AutoFormActivity;
import com.ghs.ghspm.models.workdesk.signAndTable.tableTaskToDo.formTaskSign.commonSign.CommonSignActivity;
import com.ghs.ghspm.tools.ActivityResultManager;
import com.ghs.ghspm.tools.CalendarUtil;
import com.ghs.ghspm.tools.PubUtil;
import com.ghs.ghspm.tools.StrUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * created by tobato
 * created date 2018/12/25 14:40.
 * application   表单任务详情
 */
public class TableTaskDesActivity extends BaseActivity<SignTableContrct.ISignTableView, SignTablePresent> implements View.OnClickListener, SignTableContrct.ISignTableView {

    private RecyclerView mDetailRv;
    private TextView mTableTaskDealTv;
    private TableTaskDesAdapter tableTaskDetailAdapter;
    private TableTaskDetailBean.DataBean dataBean;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public SignTablePresent creatPresenter() {
        return new SignTablePresent();
    }

    @Override
    public void setLayout() {
        setContentView(R.layout.activity_form_task_info);

    }

    @Override
    public void initLayoutView() {
        initView();
        initActionBar("表单任务", null);

    }

    @Override
    public void getDate() {
        getTableTaskInfo();
    }

    @Override
    public void actionBarRightTvOnClick() {

//        startActivity(new Intent(TableTaskDesActivity.this,ModificationActivity.class));

    }

    /**
     * 获取表单信息
     */
    private void getTableTaskInfo() {
        Intent intent = getIntent();
        if (intent != null) {
            int taskId = intent.getIntExtra(ActivityResultManager.TABLE_TASK_DETAIL_ID, -1);
            int taskType = intent.getIntExtra(ActivityResultManager.TABLE_TASK_DETAIL_TYPE, -1);
            int taskStatus = intent.getIntExtra(ActivityResultManager.TABLE_TASK_DETAIL_STATUS, -1);
            if (getPresenter() != null) {
                getPresenter().getTableTaskDetail(taskId, taskType, taskStatus, SignTableContrct.GET_TABLE_TASK_DETAIL);
            }
        }

    }

    private void initView() {


        mDetailRv = (RecyclerView) findViewById(R.id.detail_rv);
        mTableTaskDealTv = (TextView) findViewById(R.id.table_task_deal_tv);
        mTableTaskDealTv.setOnClickListener(this);
        tableTaskDetailAdapter = new TableTaskDesAdapter(R.layout.sign_or_deal_item);
        tableTaskDetailAdapter.setEmptyView(getAdapterEmptyView("暂无数据"));
        initRecyclerview(mDetailRv, tableTaskDetailAdapter, LinearLayoutManager.VERTICAL, false);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.table_task_deal_tv:
                if (dataBean != null) {
                    int taskStatus = dataBean.getStatus();
                    switch (dataBean.getFormType()) {
                        case 1://日常记录类表单
                            if (1 == taskStatus) {
                                PubUtil.DailyRecordActivityEntry = 1;

                                //待处理
                                Intent intent = new Intent(this, CreateFormActivity.class);
                                intent.putExtra(ActivityResultManager.TABLE_TASK_DAILY_RECORD, dataBean);
                                startActivityForResult(intent, ActivityResultManager.FINISH_FORM_TASK_DETAIL_ACTIVITY);

                            } else if (2 == taskStatus) {
                                //带签批
                                Intent intent = new Intent(this, CommonSignActivity.class);
                                intent.putExtra(ActivityResultManager.COMMON_SIGN_ACTIVITY, dataBean);
                                startActivityForResult(intent, ActivityResultManager.FINISH_FORM_TASK_DETAIL_ACTIVITY);

                            } else if (3 == taskStatus) {
                                //已完成
                                Intent intent = new Intent(this, CommonSignActivity.class);
                                intent.putExtra(ActivityResultManager.COMMON_SIGN_ACTIVITY, dataBean);
                                startActivity(intent);
                            }
                            break;

                        case 2://工具类表单
                            //带签批
                            Intent toolIntent = new Intent(this, CommonSignActivity.class);
                            toolIntent.putExtra(ActivityResultManager.COMMON_SIGN_ACTIVITY, dataBean);
                            startActivityForResult(toolIntent, ActivityResultManager.FINISH_FORM_TASK_DETAIL_ACTIVITY);
                            break;
                        case 3://自动报表
                            Intent intent = new Intent(this, AutoFormActivity.class);
                            intent.putExtra(ActivityResultManager.AUTO_FORM_SIGN_ACTIVITY, dataBean);
                            startActivityForResult(intent, ActivityResultManager.FINISH_FORM_TASK_DETAIL_ACTIVITY);


                            break;

                        default:
                            break;
                    }
                }

                break;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == ActivityResultManager.FINISH_FORM_TASK_DETAIL_ACTIVITY) {
            onBackPressed();
        } else {
            super.onActivityResult(requestCode, resultCode, data);

        }
    }

    @Override
    public void onBackPressed() {
        setResult(ActivityResultManager.TABLE_TASK_DETAIL);
        finish();


    }

    @Override
    public void startLoading(String tag) {

    }

    @Override
    public void stopLoading(String tag) {

    }

    @Override
    public void updateView(Object o, String tag) {
        TableTaskDetailBean tableTaskDetailBean = (TableTaskDetailBean) o;

        if (tableTaskDetailBean != null) {
            dataBean = tableTaskDetailBean.getData();
            if (dataBean != null) {
                int formType = dataBean.getFormType();
                int status = dataBean.getStatus();
                String statusValue = "";
                mTableTaskDealTv.setVisibility(View.VISIBLE);
                switch (status) {
                    case 1:
                        statusValue = "待处理";
                        mTableTaskDealTv.setText("去完成");
                        break;
                    case 2:
                        statusValue = "待签批";
                        mTableTaskDealTv.setText("去签批");
                        break;
                    case 3:
                        statusValue = "已完成";
                        mTableTaskDealTv.setText("查看详情");

                        break;
                    default:
                        break;
                }
                if (0 == PubUtil.TableTaskDesActivityEntry) {
                    mTableTaskDealTv.setText("查看详情");
                }
                List<KeyValueBean> keyValueBeanList = new ArrayList<>();
                switch (formType) {
                    case 1://日常记录类

//                        keyValueBeanList.add(new KeyValueBean("表单类型", "日常记录类"));
                        keyValueBeanList.add(new KeyValueBean("任务标题", dataBean.getTaskName()));
                        keyValueBeanList.add(new KeyValueBean("发起人", dataBean.getTaskUpdateUserName()));
                        keyValueBeanList.add(new KeyValueBean("执行人", dataBean.getHandlerName()));
                        keyValueBeanList.add(new KeyValueBean("签批人", dataBean.getCheckerName()));
                        keyValueBeanList.add(new KeyValueBean("表单名称", dataBean.getFormName()));
                        keyValueBeanList.add(new KeyValueBean("描述", dataBean.getDescription()));


                        break;
                    case 2://工作工具类

//                        keyValueBeanList.add(new KeyValueBean("表单类型", "工作工具类"));
                        keyValueBeanList.add(new KeyValueBean("表单名称", dataBean.getFormName()));
                        keyValueBeanList.add(new KeyValueBean("提交人", dataBean.getHandlerName()));
                        keyValueBeanList.add(new KeyValueBean("提交时间", dataBean.getHandleTime()));
                        keyValueBeanList.add(new KeyValueBean("签批人", dataBean.getCheckerName()));
                        keyValueBeanList.add(new KeyValueBean("抄送人", dataBean.getCcName()));
                        keyValueBeanList.add(new KeyValueBean("状态", statusValue));
                        break;
                    case 3://自动报表
//                        keyValueBeanList.add(new KeyValueBean("表单类型", "自动报表类"));
                        keyValueBeanList.add(new KeyValueBean("表单名称", dataBean.getFormName()));
                        keyValueBeanList.add(new KeyValueBean("任务名称", dataBean.getTaskName()));
                        keyValueBeanList.add(new KeyValueBean("签批人", dataBean.getCheckerName()));
                        keyValueBeanList.add(new KeyValueBean("状态", statusValue));
                        String startTime = StrUtils.isStringValueOk(dataBean.getTaskStartTime()) ? CalendarUtil.getTimeFromStringTime("yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", dataBean.getTaskStartTime()) : "";
                        String endTime = StrUtils.isStringValueOk(dataBean.getTaskEndTime()) ? CalendarUtil.getTimeFromStringTime("yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", dataBean.getTaskEndTime()) : "";
                        keyValueBeanList.add(new KeyValueBean("报表日期", startTime + "至" + endTime));
                        break;
                    default:
                        break;
                }
                tableTaskDetailAdapter.setNewData(keyValueBeanList);
            }
        }
    }

    @Override
    public void onError(String tag) {
        showToast(tag);

    }
}
