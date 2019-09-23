package com.ghs.ghspm.models.task;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ghs.ghspm.R;
import com.ghs.ghspm.base.BaseActivity;
import com.ghs.ghspm.base.displayPhotos.DisplayPhotosActivity;
import com.ghs.ghspm.base.network.RequestStatus;
import com.ghs.ghspm.base.promission.CheckPermListener;
import com.ghs.ghspm.bean.TaskContentMapBean;
import com.ghs.ghspm.bean.TaskInfoBean;
import com.ghs.ghspm.bean.UsersFromRoleBean;
import com.ghs.ghspm.models.oss.OssUploadManager;
import com.ghs.ghspm.models.task.publishtask.SelectRelatedPeopleActivity;
import com.ghs.ghspm.models.task.waittodealtask.ModificationActivity;
import com.ghs.ghspm.models.task.waittodealtask.TaskContentAdapter;
import com.ghs.ghspm.models.workdesk.publicfuction.notice.normalnotice.NoticeDetailPicDisplayAdapter;
import com.ghs.ghspm.models.workdesk.publicfuction.notice.publishNotice.ShowSelectedPicsAdapter;
import com.ghs.ghspm.tools.ActivityResultManager;
import com.ghs.ghspm.tools.CalendarUtil;
import com.ghs.ghspm.tools.Contract;
import com.ghs.ghspm.tools.EventManager;
import com.ghs.ghspm.tools.FileUtil;
import com.ghs.ghspm.tools.ImageUtil;
import com.ghs.ghspm.tools.PubUtil;
import com.ghs.ghspm.tools.RxScheduler;
import com.ghs.ghspm.tools.StrUtils;
import com.ghs.ghspm.tools.UserInfoUtil;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import cn.com.mark.multiimage.core.ImageMainActivity;

public class TaskDetailInfoActivity extends BaseActivity<TaskContract.ITaskView, TaskPresent> implements View.OnClickListener, TaskContract.ITaskView, RequestStatus {

    /**
     * 通知：南门门禁
     */
    private TextView mTaskDetailContentTv;
    /**
     * 发起人
     */
    private TextView mTaskDetailCreaterTv;
    /**
     * 9:00
     */
    private TextView mTaskDetailTimeTv;
    private RecyclerView mTaskDetailPicsRv;
    private RecyclerView mTaskDetailStatusRv;
    /**
     * 转发
     */
    private TextView mTaskDetailRelayTv;
    /**
     * 完成
     */
    private TextView mTaskDetailFinishedTv;
    /**
     * 放弃
     */
    private TextView mTaskDetailGiveUpTv;
    private TaskDealStatusAdapter statusAdapter;
    private LinearLayout mTaskDetailDealExecutorActionLl;
    private TaskInfoBean.DataBean.TaskBean taskBean;
    private NoticeDetailPicDisplayAdapter adapter;
    private ImageView mHeadPicBgIv;
    private LinearLayout mTaskAddrLl;
    private LinearLayout mTaskTimeLl;
    private RelativeLayout mTaskDetailRv;
    /**
     * 内容
     */
    private TextView mTaskDetailControlerTv;
    /**
     * 内容
     */
    private TextView mTaskDetailExecuterTv;
    private TextView mTaskAddrTv;
    private TextView mTaskTimeTv;
    /**
     * 同意
     */
    private TextView mTaskDetailAgreeTv;
    /**
     * 驳回
     */
    private TextView mTaskDetailUnAgreeTv;
    private LinearLayout mTaskDetailDealControlerActionLl;
    private String imageUrl;
    private BottomSheetDialog selectDialog;
    private ShowSelectedPicsAdapter selectedPicsAdapter;

    private int spanCount = 3;//一行的个数，默认0
    private int maxCount = 7;//最大个数，默认9个
    private int widthAndHeigh = 85;//itemView的宽高
    private List<String> icons = new ArrayList<>();
    private BottomSheetDialog dealTaskDialog;
    private EditText dealTaskDesEt;
    private int userID = -1;
    private TextView commitTv;
    private RecyclerView task_detail_content_recylerview;
    private TaskContentAdapter taskContentAdapter;
    private LinearLayout task_detail_linearlayout;
    private OssUploadManager ossManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public TaskPresent creatPresenter() {
        return new TaskPresent();
    }

    @Override
    public void setLayout() {
        setContentView(R.layout.activity_wait_to_deal);

    }

    @Override
    public void initLayoutView() {
        initView();
        switch (PubUtil.clickedTaskType) {
            case 0:
                initActionBar("待处理工作", "");
                break;
            case 1:
                initActionBar("我发起的工作", "");
                break;
            case 2:
                initActionBar("与我相关工作", "");
                break;
            case 3:
                initActionBar("已完成工作", "");
                break;
            default:
                break;
        }

    }

    //判断userid是否相同 任务状态是否是待处理
    public boolean isCreateUseridSame() {
        if (taskBean != null) {
            Log.i("TAG", taskBean.getCreateUserId() + "----" + UserInfoUtil.getInstance().getUserId());
            if (taskBean.getCreateUserId() == UserInfoUtil.getInstance().getUserId()) {
                Log.i("TAG", taskBean.getStatus() + "===" + 2);
                if (taskBean.getStatus() == 2) {
                    return true;
                }
            }

        }
        return false;
    }

    @Override
    public void getDate() {
        getPresenter().getTaskDetailInfo(PubUtil.clickedTaskID, UserInfoUtil.getInstance().getUserId(), TaskContract.TASK_DETAIL_INFO);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void actionBarRightTvOnClick() {
        Intent intent = new Intent(TaskDetailInfoActivity.this, ModificationActivity.class);
        intent.putExtra(TaskContract.UPDATA_TASK, taskBean);
        startActivityForResult(intent, ActivityResultManager.COMPILE_SUCCESS);

    }

    private void initView() {
        task_detail_content_recylerview = findViewById(R.id.task_detail_content_recylerview);
        task_detail_content_recylerview.setLayoutManager(new LinearLayoutManager(this));
        taskContentAdapter = new TaskContentAdapter(R.layout.task_content_layout, null);
        task_detail_content_recylerview.setAdapter(taskContentAdapter);

        mTaskDetailContentTv = (TextView) findViewById(R.id.task_detail_content_tv);
        mTaskDetailCreaterTv = (TextView) findViewById(R.id.task_detail_creater_tv);
        mTaskDetailTimeTv = (TextView) findViewById(R.id.task_detail_time_tv);
        mTaskDetailPicsRv = (RecyclerView) findViewById(R.id.task_detail_pics_rv);
        adapter = new NoticeDetailPicDisplayAdapter(R.layout.notice_detail_pics_display_item);
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                new DisplayPhotosActivity().startDisplayPhotosActivity(TaskDetailInfoActivity.this, imageUrl, position);

            }
        });
        GridLayoutManager managere = new GridLayoutManager(this, 3) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        mTaskDetailPicsRv.setLayoutManager(managere);
        mTaskDetailPicsRv.setAdapter(adapter);
        mTaskDetailStatusRv = (RecyclerView) findViewById(R.id.task_detail_status_rv);
        statusAdapter = new TaskDealStatusAdapter(R.layout.task_deal_status_item);
        initRecyclerview(mTaskDetailStatusRv, statusAdapter, LinearLayoutManager.VERTICAL, false);
        mTaskDetailRelayTv = (TextView) findViewById(R.id.task_detail_relay_tv);
        mTaskDetailRelayTv.setOnClickListener(this);
        mTaskDetailFinishedTv = (TextView) findViewById(R.id.task_detail_finished_tv);
        mTaskDetailFinishedTv.setOnClickListener(this);
        mTaskDetailGiveUpTv = (TextView) findViewById(R.id.task_detail_give_up_tv);
        mTaskDetailGiveUpTv.setOnClickListener(this);
        mTaskDetailDealExecutorActionLl = (LinearLayout) findViewById(R.id.task_detail_deal_executor_action_ll);
        mHeadPicBgIv = (ImageView) findViewById(R.id.head_pic_bg_iv);
        mTaskAddrLl = (LinearLayout) findViewById(R.id.task_addr_ll);
        mTaskTimeLl = (LinearLayout) findViewById(R.id.task_time_ll);
        mTaskDetailRv = (RelativeLayout) findViewById(R.id.task_detail_rv);
        mTaskDetailControlerTv = (TextView) findViewById(R.id.task_detail_controler_tv);
        mTaskDetailExecuterTv = (TextView) findViewById(R.id.task_detail_executer_tv);
        mTaskAddrTv = (TextView) findViewById(R.id.task_addr_tv);
        mTaskTimeTv = (TextView) findViewById(R.id.task_time_tv);
        mTaskDetailAgreeTv = (TextView) findViewById(R.id.task_detail_agree_tv);
        mTaskDetailAgreeTv.setOnClickListener(this);
        mTaskDetailUnAgreeTv = (TextView) findViewById(R.id.task_detail_unAgree_tv);
        mTaskDetailUnAgreeTv.setOnClickListener(this);
        mTaskDetailDealControlerActionLl = (LinearLayout) findViewById(R.id.task_detail_deal_controler_action_ll);
        task_detail_linearlayout = findViewById(R.id.task_detail_linearlayout);
    }

    @Override
    public void onStart(String tag) {

    }

    @Override
    public void onSuccess(Object o, String tag) {
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (ActivityResultManager.PUBLISH_TASK_SELECT_PEOPLE == resultCode) {
            if (PubUtil.publish_task_select_people == 3) {
                if (PubUtil.selectedUsersMap.size() < 1) {
                    showToast("请选择转发接受人");
                } else {

                    for (Map.Entry<Integer, UsersFromRoleBean.DataBean> entry : PubUtil.selectedUsersMap.entrySet()) {
                        userID = entry.getKey();
                    }
                    commitTaskResult(Contract.TASK_REJECT, TaskContract.REJECT_TASK, 0);
                }


            }
        } else if (-1 == resultCode) {
            if (requestCode == ActivityResultManager.TAKE_PICTURE) {
                Bitmap bitmap = ImageUtil.readPictureDegreeToForwordBitmap(FileUtil.getHeadPicRootPath(this));
                String userHeadPic = ImageUtil.saveCroppedImage(FileUtil.getHeadPicDir(this), bitmap);
                icons.add(userHeadPic);
                selectedPicsAdapter.setNewData(reSortIconList());

            } else if (requestCode == ActivityResultManager.SYSTEM_PICTURE) {
                String imagePath = "";
                ArrayList<Uri> images = data.getParcelableArrayListExtra("result");
                for (int i = 0; i < images.size(); i++) {
                    imagePath = images.get(i).getPath();
                    icons.add(imagePath);
//                    Bitmap bitmap = ImageUtil.readPictureDegreeToForwordBitmap(imagePath);
//                    String userHeadPic = ImageUtil.saveCroppedImage(FileUtil.getHeadPicDir(PublishNoticeActivity.this), bitmap);
//                    icons.add(userHeadPic);
                }
                selectedPicsAdapter.setNewData(reSortIconList());

            }
        } else if (requestCode == ActivityResultManager.COMPILE_SUCCESS) {
//            getPresenter().getTaskDetailInfo(PubUtil.clickedTaskID, UserInfoUtil.getInstance().getUserId(), TaskContract.TASK_DETAIL_INFO);
            EventManager.sendStringMsg(ActivityResultManager.TASK_FRAGMENT_REFRESH);
            finish();
        }

    }

    @Override
    public void onBackPressed() {
        setResult(ActivityResultManager.DEAL_TASK_DETAIL);
        super.onBackPressed();
    }

    /**
     * 提交处理任务的结果
     *
     * @param completeTask
     * @param tag
     * @param type         -1,非转发的接口 0 ，转发的接口
     */
    private void commitTaskResult(final String completeTask, final String tag, final int type) {
        commitTv.setText("正在提交...");
        commitTv.setClickable(false);
        RxScheduler.doOnIoThread(new RxScheduler.IOTask<Void>() {
            @Override
            public void doOnIOThread() {

                if (taskBean != null) {
                    String content = dealTaskDesEt.getText().toString().trim();
                    String picPath = ossManager.getPhotoPathOfUploadedToOssServer(TaskDetailInfoActivity.this,icons);
                    if (-1 == type) {
                        getPresenter().dealTask(completeTask, taskBean.getId(), UserInfoUtil.getInstance().getUserId(), content, picPath, tag);
                    } else {
                        getPresenter().transferTask(taskBean.getId(), UserInfoUtil.getInstance().getUserId(), userID, content, picPath, TaskContract.TRANSFER_TASK);

                    }
                }

            }
        });
    }

    /**
     * 对icons集合处理
     *
     * @return
     */
    private List<String> reSortIconList() {
        List<String> icons_new = new ArrayList<>();
        for (String icon : icons) {
            if (!"-1".equals(icon)) {
                icons_new.add(icon);
            }
        }
        if (icons.size() < maxCount) {
            icons_new.add("-1");
        }
        return icons_new;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.task_detail_relay_tv://转发
                dealTask("转发");
                break;
            case R.id.task_detail_finished_tv://完成
                dealTask("完成");
                break;
            case R.id.task_detail_give_up_tv://放弃
                dealTask("放弃");
                break;
            case R.id.task_detail_agree_tv://同意
                dealTask("同意");
                break;
            case R.id.task_detail_unAgree_tv://驳回
                dealTask("驳回");
                break;
            case R.id.mine_edit_cancel_pic_tv://替换头像中的取消按钮
                dismissSelectPicDialog();

                break;
            case R.id.mine_edit_take_pic_tv://替换头像中的拍照按钮
                takePicturesFromActivity(selectDialog);


                break;
            case R.id.mine_edit_select_pic_tv:////替换头像中的从相册中选取按钮
                checkAppPermissions(new CheckPermListener() {
                    @Override
                    public void agreeAllPermission() {
                        dismissSelectPicDialog();
                        Intent intent = new Intent(TaskDetailInfoActivity.this, ImageMainActivity.class);
                        intent.putExtra("action-original", true);
                        intent.putExtra("MAX_SEND", maxCount - icons.size());
                        startActivityForResult(intent, ActivityResultManager.SYSTEM_PICTURE);

                    }

                    @Override
                    public void selectedAllPermission() {

                    }
                }, R.string.perm_camera_store, PubUtil.promissions[1],PubUtil.promissions[3]);


                break;


        }
    }

    /**
     * 处理任务
     */
    private void dealTask(final String typeName) {
        dealTaskDialog = new BottomSheetDialog(this);
        dealTaskDialog.setCanceledOnTouchOutside(true);

        View view = LayoutInflater.from(this).inflate(R.layout.deal_task_dialog_view, null);
        TextView title = view.findViewById(R.id.deal_task_title_tv);
        title.setText(typeName + "详情（选填）");
        commitTv = view.findViewById(R.id.deal_task_commit_tv);
        commitTv.setText("确定");
        dealTaskDesEt = view.findViewById(R.id.deal_task_content_et);

        commitTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (typeName) {
                    case "转发":
                        PubUtil.publish_task_select_people = 3;
                        PubUtil.selectedUsersMap.clear();
                        startActivityForResult(new Intent(TaskDetailInfoActivity.this, SelectRelatedPeopleActivity.class), ActivityResultManager.PUBLISH_TASK_SELECT_PEOPLE);

                        break;
                    case "完成":
                        commitTaskResult(Contract.COMPLETE_TASK, TaskContract.COMPLATE_TASK, -1);
                        break;
                    case "放弃":
                        commitTaskResult(Contract.GIVE_UP_TASK, TaskContract.GIVE_UP_TASK, -1);

                        break;
                    case "同意":
                        commitTaskResult(Contract.TASK_AGREE, TaskContract.AGREE_TASK, -1);
                        break;
                    case "驳回":
                        commitTaskResult(Contract.TASK_REJECT, TaskContract.REJECT_TASK, -1);
                        break;
                    default:
                        break;
                }
            }
        });
        dealTaskDialog.setContentView(view);
        dealTaskDialog.show();
        if (ossManager == null) {
            ossManager = OssUploadManager.getInstance(this);
        }
        RecyclerView recyclerView = view.findViewById(R.id.deal_task_rv);
        if (selectedPicsAdapter == null) {
            selectedPicsAdapter = new ShowSelectedPicsAdapter(R.layout.show_selected_pic_item);
            selectedPicsAdapter.setWidthAndHeigh(widthAndHeigh);

        }
        selectedPicsAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {


            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
//                imageView = (ImageView) selectedPicsAdapter.getViewByPosition(mPublishNoticeRv, position, R.id.mine_sugguest_icon_iv);
                List<String> arrays = reSortIconList();
                String icon_path = arrays.get(position);
                switch (view.getId()) {
                    case R.id.mine_sugguest_icon_iv:

                        if ("-1".equals(icon_path)) {
                            showSelectDialog();
                        }
                        break;
                    case R.id.mine_sugguest_delete_iv:
                        arrays.remove(position);
                        icons.clear();
                        if (arrays.size() < maxCount) {
                            if (!arrays.contains("-1")) {
                                arrays.add("-1");
                            }
                        }
                        icons = arrays;
                        adapter.setNewData(arrays);
                        break;
                    default:
                        break;
                }
            }
        });
        icons.clear();
        icons.add("-1");
        selectedPicsAdapter.setNewData(icons);
        initWidget(recyclerView);

    }

    /**
     * 关闭dialog
     */
    private void dismissSelectPicDialog() {
        if (selectDialog != null) {
            if (selectDialog.isShowing()) {
                selectDialog.dismiss();
            }
        }

    }


    /**
     * 展示选择图片的入口
     */
    private void showSelectDialog() {
        View bottomView = LayoutInflater.from(this).inflate(R.layout.select_pic_menue, null);
        selectDialog = new BottomSheetDialog(this);
        selectDialog.setCanceledOnTouchOutside(true);
        selectDialog.setContentView(bottomView);
        selectDialog.show();
        bottomView.findViewById(R.id.mine_edit_cancel_pic_tv).setOnClickListener(this);
        bottomView.findViewById(R.id.mine_edit_take_pic_tv).setOnClickListener(this);
        bottomView.findViewById(R.id.mine_edit_select_pic_tv).setOnClickListener(this);
        bottomView.findViewById(R.id.mine_edit_title_tv).setVisibility(View.GONE);
    }

    private void initWidget(RecyclerView recyclerView) {

        GridLayoutManager managere = new GridLayoutManager(this, spanCount);
        recyclerView.setLayoutManager(managere);
        recyclerView.setAdapter(selectedPicsAdapter);
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
            case TaskContract.TASK_DETAIL_INFO:
                TaskInfoBean taskInfoBean = (TaskInfoBean) o;

                if (taskInfoBean != null) {
                    taskBean = taskInfoBean.getData().getTask();

                    if (isCreateUseridSame()) {
                        if (PubUtil.clickedTaskType == 0) {
                            mHeaderRightTv.setText("编辑");
                            mHeaderRightTv.setTextColor(ContextCompat.getColor(this,R.color.app_default_blue));
                            mHeaderRightTv.setVisibility(View.VISIBLE);
                        }
                    }else {
                        mHeaderRightTv.setEnabled(false);
                    }


                    String content = taskBean.getContent();
                    Log.i("TAG", content);

                    List<TaskContentMapBean> contentlist = parseJSONWithJSONObject(content);
                     if(contentlist.size()!= 0){
                         taskContentAdapter.setNewData(contentlist);
                         task_detail_content_recylerview.setVisibility(View.VISIBLE);
                         task_detail_linearlayout.setVisibility(View.GONE);

                     }else {
                         mTaskDetailContentTv.setText(taskBean.getContent());
                         task_detail_content_recylerview.setVisibility(View.GONE);
                         mTaskDetailContentTv.setVisibility(View.VISIBLE);

                     }

                    mTaskDetailCreaterTv.setText("发起人：" + taskBean.getCreateUserName());
                    mTaskDetailTimeTv.setText(CalendarUtil.getTimeFromStringTime("MM-dd HH:mm", "yyyy-MM-dd HH:mm:ss", taskBean.getCreateTime()));
                    mTaskDetailControlerTv.setText("负责人：" + taskBean.getCheckUserName());
                    mTaskDetailExecuterTv.setText("执行人：" + taskBean.getHandlerUsersName());
                    String startTime = taskBean.getStartTime();
                    String towerName = taskBean.getTowerNumber();
                    if (StrUtils.isStringValueOk(startTime)) {
                        mTaskTimeLl.setVisibility(View.VISIBLE);
                        String endTime = StrUtils.isStringValueOk(taskBean.getEndTime()) ? "-" + taskBean.getEndTime() : "";
                        mTaskTimeTv.setText(taskBean.getStartTime() + endTime);
                    } else {
                        mTaskTimeLl.setVisibility(View.GONE);
                    }
                    if (StrUtils.isStringValueOk(towerName)) {
                        mTaskAddrLl.setVisibility(View.VISIBLE);
                        String cell = StrUtils.isStringValueOk(taskBean.getCellName()) ? taskBean.getCellName() : "";

                        mTaskAddrTv.setText(taskBean.getPortionName() + taskBean.getTowerNumber() + cell);
                    } else {
                        mTaskAddrLl.setVisibility(View.GONE);

                    }
                    imageUrl = taskBean.getPicture();
                    if (StrUtils.isStringValueOk(imageUrl)) {
                        if (imageUrl.contains(",")) {
                            String[] pics = imageUrl.split(",");
                            adapter.setNewData(Arrays.asList(pics));
                        } else {
                            List<String> arrays = new ArrayList<>();
                            arrays.add(imageUrl);
                            adapter.setNewData(arrays);
                        }

                    }
                    if (0 == PubUtil.clickedTaskType) {//待处理工作
                        int operateType = taskBean.getOpertionType();

                        if (1 == operateType) {//执行人
                            mTaskDetailDealControlerActionLl.setVisibility(View.GONE);
                            mTaskDetailDealExecutorActionLl.setVisibility(View.VISIBLE);
                        } else if (2 == operateType) {//负责人
                            mTaskDetailDealControlerActionLl.setVisibility(View.VISIBLE);
                            mTaskDetailDealExecutorActionLl.setVisibility(View.GONE);
                        } else {
                            mTaskDetailDealExecutorActionLl.setVisibility(View.GONE);
                            mTaskDetailDealControlerActionLl.setVisibility(View.GONE);

                        }
                    }

                    if (taskInfoBean.getData() != null) {
                        TaskInfoBean.DataBean dataBean = taskInfoBean.getData();
                        List<TaskInfoBean.DataBean.RecordListBean> listBeanList = dataBean.getRecordList();
                        Collections.reverse(listBeanList);
                        statusAdapter.setNewData(listBeanList);
                    }
                }
                break;
            case TaskContract.TRANSFER_TASK:
                dismissDealTaskBottomDialog();
                showNormalToast("转发成功");
                if (taskBean != null) {
                    getPresenter().getTaskDetailInfo(taskBean.getId(), UserInfoUtil.getInstance().getUserId(), TaskContract.TASK_DETAIL_INFO);

                }
                break;
            case TaskContract.COMPLATE_TASK:
                dismissDealTaskBottomDialog();
                showNormalToast("已完成,等待审核");
                getPresenter().getTaskDetailInfo(PubUtil.clickedTaskID, UserInfoUtil.getInstance().getUserId(), TaskContract.TASK_DETAIL_INFO);

                break;
            case TaskContract.GIVE_UP_TASK:
                dismissDealTaskBottomDialog();
                showNormalToast("已放弃,等待审核");
                getPresenter().getTaskDetailInfo(PubUtil.clickedTaskID, UserInfoUtil.getInstance().getUserId(), TaskContract.TASK_DETAIL_INFO);

                break;
            case TaskContract.AGREE_TASK:
                dismissDealTaskBottomDialog();
                showNormalToast("已同意");
                getPresenter().getTaskDetailInfo(PubUtil.clickedTaskID, UserInfoUtil.getInstance().getUserId(), TaskContract.TASK_DETAIL_INFO);

                break;
            case TaskContract.REJECT_TASK:
                dismissDealTaskBottomDialog();
                showNormalToast("已驳回");
                getPresenter().getTaskDetailInfo(PubUtil.clickedTaskID, UserInfoUtil.getInstance().getUserId(), TaskContract.TASK_DETAIL_INFO);

                break;

            default:
                break;
        }

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

    /**
     * 关闭dialog
     */
    private void dismissDealTaskBottomDialog() {
        if (dealTaskDialog != null) {
            if (dealTaskDialog.isShowing()) {
                dealTaskDialog.dismiss();
            }
        }

    }

    @Override
    public void onError(String tag) {
        commitTv.setText("再次提交");
        commitTv.setClickable(true);
        stopMaterialProgressDialog();
        showToast(tag);
    }
}
