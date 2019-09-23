package com.ghs.ghspm.models.task.waittodealtask;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ghs.ghspm.R;
import com.ghs.ghspm.base.BaseActivity;
import com.ghs.ghspm.base.network.RequestStatus;
import com.ghs.ghspm.bean.TaskContentMapBean;
import com.ghs.ghspm.bean.TaskInfoBean;
import com.ghs.ghspm.models.task.TaskContract;
import com.ghs.ghspm.models.task.TaskPresent;
import com.ghs.ghspm.models.task.publishtask.SelectRelatedPeopleActivity;
import com.ghs.ghspm.tools.ActivityResultManager;
import com.ghs.ghspm.tools.PubUtil;
import com.ghs.ghspm.tools.StrUtils;
import com.ghs.ghspm.tools.UserInfoUtil;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModificationActivity extends BaseActivity<TaskContract.ITaskView, TaskPresent> implements View.OnClickListener, TaskContract.ITaskView, RequestStatus {



    private ImageView mHeaderLeftIv;
    /**
     * 设置
     */
    private TextView mHeaderTitleTv;
    /**
     * 设置
     */
    private TextView mHeaderRightTv;
    private FloatingActionButton mTaskExecutorFb;
    private TextView mTaskExecutorTv;
    private FloatingActionButton mTaskLeaderFb;
    private TextView mTaskLeaderTv;
    private FloatingActionButton mTaskDuplicateFb;
    private TextView mTaskDuplicateTv;
    /**
     * 确定
     */
    private TextView mPublishTaskConfirmTv;
    //执行人
    private StringBuffer excuterBuffer = new StringBuffer();
    //负责人
    private StringBuffer leaderBuffer = new StringBuffer();
    //抄送人呢
    private StringBuffer duplicateBuffer = new StringBuffer();

    private TaskInfoBean.DataBean.TaskBean taskBean;
    private TextView updata_task_content;
    private Map<String, String> cacheMap = new HashMap<>();
    private static String UODATA_TASK_CHECKUSER = "updata_task_checkuser";
    private static String UODATA_TASK_HANDLERSERS = "updata_task_handlerusers";
    private static String UODATA_TASK_CCUSERS = "updata_tasl_ccusers";
    private RecyclerView task_detail_content_recylerview;
    private TaskContentAdapter taskContentAdapter;
    private String checkUser;
    private String excuters;
    private String duplicate;
    private boolean button_state = false;


    @Override
    public TaskPresent creatPresenter() {
        return new TaskPresent();
    }

    @Override
    public void setLayout() {
        setContentView(R.layout.activity_modification);

    }

    @Override
    public void initLayoutView() {
        initActionBar("修改任务", "");
        initView();
    }

    @Override
    public void getDate() {
        Intent intent = getIntent();
        taskBean = (TaskInfoBean.DataBean.TaskBean) intent.getSerializableExtra(TaskContract.UPDATA_TASK);
        if (taskBean != null) {
            cacheMap.put(taskBean.getCheckUserName(), taskBean.getCheckUserId() + "");
            cacheMap.put(taskBean.getHandlerUsersName(), taskBean.getHandlerUsersId());
            cacheMap.put(taskBean.getCcUsersName(), taskBean.getCcUsersId());

            excuters = taskBean.getHandlerUsersId();
            checkUser = taskBean.getCheckUserId();
            duplicate = taskBean.getCcUsersId();

            updata_task_content.setText(taskBean.getContent());
            List<TaskContentMapBean> contentlist = parseJSONWithJSONObject(taskBean.getContent());
            if (contentlist.size() != 0) {

                taskContentAdapter.setNewData(contentlist);
                task_detail_content_recylerview.setVisibility(View.VISIBLE);
            }else {

                updata_task_content.setText(taskBean.getContent());
                task_detail_content_recylerview.setVisibility(View.GONE);
                updata_task_content.setVisibility(View.VISIBLE);

            }

            String handleNames = nameTools(taskBean.getHandlerUsersName());
            String CcUsersNames = nameTools(taskBean.getCcUsersName());
            mTaskExecutorTv.setText(handleNames);
            mTaskLeaderTv.setText(taskBean.getCheckUserName());
            mTaskDuplicateTv.setText(CcUsersNames);

        }


    }

    public String nameTools(String names){
        String name = "";
        try {
            String[] split = names.split(",");
            if(split.length>1){
                name = split[0]+"等"+split.length+"人";
            }else {
                name = names;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  name;

    }

    private List<TaskContentMapBean> parseJSONWithJSONObject(String JsonData) {
        List<TaskContentMapBean> list = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(JsonData);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String title = jsonObject.getString("title");
                String value = jsonObject.getString("value");
                list.add(new TaskContentMapBean(title, value));
            }

            return list;
        } catch (Exception e) {

            e.printStackTrace();

            return list;
        }


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
        stopMaterialProgressDialog();
        switch (tag) {
            case TaskContract.UPDATA_TASK:
                showToast("修改成功");
                setResult(ActivityResultManager.COMPILE_SUCCESS);
                finish();
                break;

        }

    }

    @Override
    public void onStart(String tag) {

    }

    @Override
    public void onSuccess(Object o, String tag) {

    }

    @Override
    public void onError(String tag) {
        stopMaterialProgressDialog();
        showToast(tag);

    }


    public void initView() {
        mHeaderLeftIv = (ImageView) findViewById(R.id.header_left_iv);
        mHeaderTitleTv = (TextView) findViewById(R.id.header_title_tv);
        mHeaderRightTv = (TextView) findViewById(R.id.header_right_tv);
        mHeaderRightTv.setOnClickListener(this);
        //执行人
        mTaskExecutorFb = (FloatingActionButton) findViewById(R.id.task_executor_fb);
        mTaskExecutorFb.setOnClickListener(this);
        mTaskExecutorTv = (TextView) findViewById(R.id.task_executor_tv);

        //负责人
        mTaskLeaderFb = (FloatingActionButton) findViewById(R.id.task_leader_fb);
        mTaskLeaderFb.setOnClickListener(this);
        mTaskLeaderTv = (TextView) findViewById(R.id.task_leader_tv);
        //抄送人
        mTaskDuplicateFb = (FloatingActionButton) findViewById(R.id.task_duplicate_fb);
        mTaskDuplicateFb.setOnClickListener(this);
        mTaskDuplicateTv = (TextView) findViewById(R.id.task_duplicate_tv);

        //确定按钮
        mPublishTaskConfirmTv = (TextView) findViewById(R.id.publish_task_confirm_tv);

        mPublishTaskConfirmTv.setOnClickListener(this);

        updata_task_content = findViewById(R.id.updata_task_content);
        task_detail_content_recylerview = findViewById(R.id.task_detail_content_recylerview);
        task_detail_content_recylerview.setLayoutManager(new LinearLayoutManager(this));
        taskContentAdapter = new TaskContentAdapter(R.layout.task_content_layout, null);
        task_detail_content_recylerview.setAdapter(taskContentAdapter);
    }

    @Override
    public void onClick(View v) {


        switch (v.getId()) {
            case R.id.task_executor_fb://执行人
                //执行人
                initSelectedUserData(0, excuterBuffer);
                break;
            case R.id.task_leader_fb://负责人
                //负责人
                initSelectedUserData(1, leaderBuffer);
                break;
            case R.id.task_duplicate_fb://抄送人
                //抄送人
                initSelectedUserData(2, duplicateBuffer);
                break;

            case R.id.publish_task_confirm_tv:

                if (!StrUtils.isStringValueOk(excuters)) {
                    showNormalToast("请选择执行人");
                    return;
                }
                if (taskBean != null) {
                    showMaterialProgressDialog();
                    Log.i("TTT", excuters + "--" + checkUser + "---" + duplicate);
                    Long l = null;
                    try {
                        l = null;
                        if( checkUser != null && !checkUser.equals("")){
                            l =  Long.valueOf(checkUser);
                        }
                    } catch (NumberFormatException e) {
                        e.printStackTrace();

                    }
                    getPresenter().updataTask(taskBean.getId(), UserInfoUtil.getInstance().getUserId(),l, excuters, duplicate, TaskContract.UPDATA_TASK);

                }

                break;

            case R.id.header_right_tv:


                break;


        }


    }

    @NonNull
    private String getIds(StringBuffer sb) {
        String checkUser = sb.toString().trim();
        if (checkUser.contains(",")) {
            return checkUser.substring(0, checkUser.length() - 1);
        } else {
            return checkUser;
        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (ActivityResultManager.PUBLISH_TASK_SELECT_PEOPLE == resultCode) {
            switch (PubUtil.publish_task_select_people) {
                case 0:
                    initSelectedPeoples(excuterBuffer, mTaskExecutorTv);
                    excuters = getIds(excuterBuffer);
                    break;
                case 1:
                    initSelectedPeoples(leaderBuffer, mTaskLeaderTv);
                    checkUser = getIds(leaderBuffer);
                    break;
                case 2:
                    initSelectedPeoples(duplicateBuffer, mTaskDuplicateTv);
                    duplicate = getIds(duplicateBuffer);
                default:
                    break;
            }
        }
    }

    /**
     * 初始化选择user的状态
     */
    private void initSelectedUserData(int type, StringBuffer stringBuffer) {
        PubUtil.publish_task_select_people = type;
        PubUtil.selectedUsersMap.clear();
        stringBuffer.delete(0, stringBuffer.length());
        startActivityForResult(new Intent(this, SelectRelatedPeopleActivity.class), ActivityResultManager.PUBLISH_TASK_SELECT_PEOPLE);
    }


}
