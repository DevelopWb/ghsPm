package com.ghs.ghspm.models.task.publishtask;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ghs.ghspm.R;
import com.ghs.ghspm.base.BaseActivity;
import com.ghs.ghspm.base.network.RequestStatus;
import com.ghs.ghspm.base.promission.CheckPermListener;
import com.ghs.ghspm.bean.CellBean;
import com.ghs.ghspm.bean.TowerBean;
import com.ghs.ghspm.models.oss.OssUploadManager;
import com.ghs.ghspm.models.task.TaskContract;
import com.ghs.ghspm.models.task.TaskPresent;
import com.ghs.ghspm.models.workdesk.publicfuction.notice.publishNotice.ShowSelectedPicsAdapter;
import com.ghs.ghspm.models.workdesk.publicfuction.universalkey.UniversialKeyContract;
import com.ghs.ghspm.models.workdesk.publicfuction.universalkey.UniversialKeyModel;
import com.ghs.ghspm.tools.ActivityResultManager;
import com.ghs.ghspm.tools.CalendarUtil;
import com.ghs.ghspm.tools.FileUtil;
import com.ghs.ghspm.tools.ImageUtil;
import com.ghs.ghspm.tools.MaterialAlterDialogManager;
import com.ghs.ghspm.base.network.NetWorkUtil;
import com.ghs.ghspm.tools.PickerManager;
import com.ghs.ghspm.tools.PubUtil;
import com.ghs.ghspm.tools.RxScheduler;
import com.ghs.ghspm.tools.StrUtils;
import com.ghs.ghspm.tools.UserInfoUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.com.mark.multiimage.core.ImageMainActivity;

/**
 * created by tobato
 * created date 2019/6/3 17:58.
 * application   发布任务
 */
public class PublishTaskActivity extends BaseActivity<TaskContract.ITaskView, TaskPresent> implements View.OnClickListener, RequestStatus, TaskContract.ITaskView {

    Map<String, String> requestMap = new HashMap<>();//请求参数的map集合
    /**
     * 请输入内容...
     */
    private EditText mPublishTaskContentEt;
    private RecyclerView mPublishTaskRv;
    /**
     * 楼号
     */
    private TextView mPublishTaskAddrTowerTv;
    private LinearLayout mPublishTaskSelectTowerLl;
    /**
     * 单元
     */
    private TextView mPublishTaskAddrCellTv;
    private LinearLayout mPublishTaskSelectCellLl;
    /**
     * 开始时间
     */
    private TextView mPublishTaskStartTimeTv;
    private LinearLayout mPublishTaskSelectStartTimeLl;
    /**
     * 结束时间
     */
    private TextView mPublishTaskEndTimeTv;
    private LinearLayout mPublishTaskSelectEndTimeLl;
    /**
     * 确定
     */
    private TextView mPublishTaskConfirmTv;
    private FloatingActionButton mTaskExecutorFb;
    private FloatingActionButton mTaskLeaderFb;
    private FloatingActionButton mTaskDuplicateFb;
    private List<String> icons = new ArrayList<>();
    private ShowSelectedPicsAdapter adapter;
    private BottomSheetDialog bottomSheetDialog;
    private StringBuffer excuterBuffer = new StringBuffer();
    private StringBuffer leaderBuffer = new StringBuffer();
    private StringBuffer duplicateBuffer = new StringBuffer();
    /**
     * 任务人等2人
     */
    private TextView mTaskExecutorTv;
    /**
     * 任务人等2人
     */
    private TextView mTaskLeaderTv;
    /**
     * 任务人等2人
     */
    private TextView mTaskDuplicateTv;
    private TowerBean.DataBean selectedTowerInfo = null;//已选中的楼信息
    private CellBean.DataBean selectedCellInfo = null;//已选中的单元信息
    private OssUploadManager ossManager;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void getDate() {
        icons.add("-1");
        adapter.setNewData(icons);
        ossManager = OssUploadManager.getInstance(this);
    }


    @Override
    public void initLayoutView() {
        initView();
        initActionBar("发起任务", null);

    }

    @Override
    public void setLayout() {
        setContentView(R.layout.activity_publish_task);

    }

    @Override
    public void actionBarRightTvOnClick() {

    }

    @Override
    public TaskPresent creatPresenter() {
        return new TaskPresent();
    }

    private void initView() {
        mPublishTaskContentEt =  findViewById(R.id.publish_task_content_et);
        mPublishTaskContentEt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });
        mPublishTaskRv = (RecyclerView) findViewById(R.id.publish_task_rv);
        mPublishTaskAddrTowerTv = (TextView) findViewById(R.id.publish_task_addr_tower_tv);
        mPublishTaskSelectTowerLl = (LinearLayout) findViewById(R.id.publish_task_select_tower_ll);
        mPublishTaskSelectTowerLl.setOnClickListener(this);
        mPublishTaskAddrCellTv = (TextView) findViewById(R.id.publish_task_addr_cell_tv);
        mPublishTaskSelectCellLl = (LinearLayout) findViewById(R.id.publish_task_select_cell_ll);
        mPublishTaskSelectCellLl.setOnClickListener(this);
        mPublishTaskStartTimeTv = (TextView) findViewById(R.id.publish_task_start_time_tv);
        mPublishTaskSelectStartTimeLl = (LinearLayout) findViewById(R.id.publish_task_select_start_time_ll);
        mPublishTaskSelectStartTimeLl.setOnClickListener(this);
        mPublishTaskEndTimeTv = (TextView) findViewById(R.id.publish_task_end_time_tv);
        mPublishTaskSelectEndTimeLl = (LinearLayout) findViewById(R.id.publish_task_select_end_time_ll);
        mPublishTaskSelectEndTimeLl.setOnClickListener(this);
        mPublishTaskConfirmTv = (TextView) findViewById(R.id.publish_task_confirm_tv);
        mPublishTaskConfirmTv.setOnClickListener(this);
        mTaskExecutorFb = (FloatingActionButton) findViewById(R.id.task_executor_fb);
        mTaskExecutorFb.setOnClickListener(this);
        mTaskLeaderFb = (FloatingActionButton) findViewById(R.id.task_leader_fb);
        mTaskLeaderFb.setOnClickListener(this);
        mTaskDuplicateFb = (FloatingActionButton) findViewById(R.id.task_duplicate_fb);
        mTaskDuplicateFb.setOnClickListener(this);
        adapter = new ShowSelectedPicsAdapter(R.layout.show_selected_pic_item);
        GridLayoutManager managere = new GridLayoutManager(this, 4);
        mPublishTaskRv.setLayoutManager(managere);
        mPublishTaskRv.setAdapter(adapter);
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {


            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
//                imageView = (ImageView) adapter.getViewByPosition(mPublishNoticeRv, position, R.id.mine_sugguest_icon_iv);
                List<String> arrays = reSortIconList();
                String icon_path = arrays.get(position);
                switch (view.getId()) {
                    case R.id.mine_sugguest_icon_iv:
                        if ("-1".equals(icon_path)) {
                            View bottomView = LayoutInflater.from(PublishTaskActivity.this).inflate(R.layout.select_pic_menue, null);
                            bottomSheetDialog = new BottomSheetDialog(PublishTaskActivity.this);
                            bottomSheetDialog.setContentView(bottomView);
                            bottomSheetDialog.show();
                            bottomView.findViewById(R.id.mine_edit_cancel_pic_tv).setOnClickListener(PublishTaskActivity.this);
                            bottomView.findViewById(R.id.mine_edit_take_pic_tv).setOnClickListener(PublishTaskActivity.this);
                            bottomView.findViewById(R.id.mine_edit_select_pic_tv).setOnClickListener(PublishTaskActivity.this);
                            bottomView.findViewById(R.id.mine_edit_title_tv).setVisibility(View.GONE);
                        }
                        break;
                    case R.id.mine_sugguest_delete_iv:
                        arrays.remove(position);
                        icons.clear();
                        if (arrays.size() < 7) {
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
        mTaskExecutorTv = (TextView) findViewById(R.id.task_executor_tv);
        mTaskLeaderTv = (TextView) findViewById(R.id.task_leader_tv);
        mTaskDuplicateTv = (TextView) findViewById(R.id.task_duplicate_tv);
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
        if (icons.size() < 7) {
            icons_new.add("-1");
        }
        return icons_new;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, final Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == ActivityResultManager.TAKE_PICTURE) {
                Bitmap bitmap = ImageUtil.readPictureDegreeToForwordBitmap(FileUtil.getHeadPicRootPath(this));
                String userHeadPic = ImageUtil.saveCroppedImage(FileUtil.getHeadPicDir(this), bitmap);
                icons.add(userHeadPic);
                adapter.setNewData(reSortIconList());

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
                adapter.setNewData(reSortIconList());

            }
        } else if (ActivityResultManager.PUBLISH_TASK_SELECT_PEOPLE == resultCode) {
            switch (PubUtil.publish_task_select_people) {
                case 0:
                    initSelectedPeoples(excuterBuffer, mTaskExecutorTv);

                    break;
                case 1:
                    initSelectedPeoples(leaderBuffer, mTaskLeaderTv);

                    break;
                case 2:
                    initSelectedPeoples(duplicateBuffer, mTaskDuplicateTv);

                default:
                    break;
            }
        }


    }


    @Override
    public void onBackPressed() {
        String content = mPublishTaskContentEt.getText().toString().trim();
        String checkUser = getIds(leaderBuffer);
        String excuters = getIds(excuterBuffer);
        String duplicate = getIds(duplicateBuffer);
        String startTime = mPublishTaskStartTimeTv.getText().toString().trim();

        if (StrUtils.isStringValueOk(content) || StrUtils.isStringValueOk(checkUser)
                || StrUtils.isStringValueOk(excuters) || StrUtils.isStringValueOk(duplicate) || icons.size() > 1
                || !"开始时间".equals(startTime) || selectedTowerInfo != null) {

            MaterialAlterDialogManager.getInstance().showMaterialAlterDialog(this, "提示", "您有未发布的任务，是否放弃？", "是", "否", new MaterialAlterDialogManager.OnAlterDialogCallBack() {
                @Override
                public void leftBtClicked() {
                    setResult(ActivityResultManager.PUBLISH_TASK);
                    finish();
                }

                @Override
                public void rightBtClicked() {

                }
            });
        } else {
            setResult(ActivityResultManager.PUBLISH_TASK);
            finish();


        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.publish_task_select_tower_ll:
                    new UniversialKeyModel().getTowerNo(UserInfoUtil.getInstance().getVillageId(), this, UniversialKeyContract.GET_TOWER_NO);
                break;
            case R.id.publish_task_select_cell_ll:
                String towerName = mPublishTaskAddrTowerTv.getText().toString().trim();
                if ("楼号".equals(towerName)) {
                    showNormalToast("请先选择楼号");
                } else {
                        new UniversialKeyModel().getCellNo(UserInfoUtil.getInstance().getVillageId(), selectedTowerInfo.getId(), this, UniversialKeyContract.GET_CELL_NO);
                }
                break;
            case R.id.publish_task_select_start_time_ll:
                selectTime(mPublishTaskStartTimeTv, "开始时间");
                break;
            case R.id.publish_task_select_end_time_ll:
                String startText = mPublishTaskStartTimeTv.getText().toString().trim();
                if ("开始时间".equals(startText)) {
                    showNormalToast("请先选择开始时间");
                } else {
                    selectTime(mPublishTaskEndTimeTv, "结束时间");

                }
                break;
            case R.id.publish_task_confirm_tv:
                if (!isNetWorkConnected()) {
                    return;
                }
                final String content = mPublishTaskContentEt.getText().toString().trim();
                final String checkUser = getIds(leaderBuffer);
                final String excuters = getIds(excuterBuffer);
                final String duplicate = getIds(duplicateBuffer);
                if (!StrUtils.isStringValueOk(content)) {
                    showNormalToast("请输入任务的内容");
                    return;
                }
                if (!StrUtils.isStringValueOk(excuters)) {
                    showNormalToast("请选择执行人");
                    return;
                }
                getTowerAndCellRequestMap();
                getStartTimeAndEndTimeRequestMap();
                showMaterialProgressDialog(null, "正在发起任务，请稍后...");
                RxScheduler.doOnIoThread(new RxScheduler.IOTask<Void>() {
                    @Override
                    public void doOnIOThread() {
                        if (icons.size() > 0) {
                            String path = ossManager.getPhotoPathOfUploadedToOssServer(PublishTaskActivity.this, icons);

                            requestMap.put("picture", path);
                        }
                        getPresenter().publishTask(content, checkUser, UserInfoUtil.getInstance().getPropertyId(), UserInfoUtil.getInstance().getUserId(), UserInfoUtil.getInstance().getUserName(), excuters, duplicate, requestMap, "");

                    }
                });


                break;
            case R.id.task_executor_fb://执行人
                initSelectedUserData(0, excuterBuffer);
                break;
            case R.id.task_leader_fb://负责人
                initSelectedUserData(1, leaderBuffer);

                break;
            case R.id.task_duplicate_fb://抄送人
                initSelectedUserData(2, duplicateBuffer);

                break;
            case R.id.mine_edit_cancel_pic_tv://替换头像中的取消按钮
                if (bottomSheetDialog.isShowing()) {
                    bottomSheetDialog.dismiss();
                }
                break;
            case R.id.mine_edit_take_pic_tv://替换头像中的拍照按钮
                takePicturesFromActivity(bottomSheetDialog);

                break;
            case R.id.mine_edit_select_pic_tv:////替换头像中的从相册中选取按钮
                checkAppPermissions(new CheckPermListener() {
                    @Override
                    public void agreeAllPermission() {
                        if (bottomSheetDialog != null) {
                            if (bottomSheetDialog.isShowing()) {
                                bottomSheetDialog.dismiss();
                            }
                        }
                        Intent intent = new Intent(PublishTaskActivity.this, ImageMainActivity.class);
                        intent.putExtra("action-original", true);
                        intent.putExtra("MAX_SEND", 7 - icons.size());
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
     * 初始化选择user的状态
     */
    private void initSelectedUserData(int type, StringBuffer stringBuffer) {
        PubUtil.publish_task_select_people = type;
        PubUtil.selectedUsersMap.clear();
        stringBuffer.delete(0, stringBuffer.length());
        startActivityForResult(new Intent(this, SelectRelatedPeopleActivity.class), ActivityResultManager.PUBLISH_TASK_SELECT_PEOPLE);
    }

    /**
     * 选择时间
     *
     * @param textView
     * @param title
     */
    private void selectTime(final TextView textView, final String title) {
        PickerManager.getInstance().showTimePickerView(this, new boolean[]{false, false, false, true, true, false}, title, new PickerManager.OnTimePickerTimeSelectedListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                textView.setText(CalendarUtil.getTimeFromDate("HH:mm", date));
                if ("开始时间".equals(title)) {
                    mPublishTaskEndTimeTv.setText("结束时间");
                } else {
                    //比较两个时间戳的大小
//                    CalendarUtil.compareTimes()
                }
            }
        });
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

    /**
     * 获取楼号和单元号对应的请求信息
     */
    private void getTowerAndCellRequestMap() {
        if (selectedTowerInfo != null) {
//            requestMap.put("towerNumber", selectedTowerInfo.getTowerNumber());
            requestMap.put("towerId", String.valueOf(selectedTowerInfo.getId()));
            if (selectedCellInfo != null) {
//                requestMap.put("towerNumber", selectedCellInfo.getCellName());
                requestMap.put("cellId", String.valueOf(selectedCellInfo.getId()));
            }
        }
    }

    /**
     * 获得开始时间和结束时间的请求参数
     */
    private void getStartTimeAndEndTimeRequestMap() {
        String startTime = mPublishTaskStartTimeTv.getText().toString().trim();
        if (!"开始时间".equals(startTime)) {//已选中开始时间
            requestMap.put("startTime", startTime);
            String endTime = mPublishTaskEndTimeTv.getText().toString().trim();
            if (!"结束时间".equals(endTime)) {
                requestMap.put("endTime", endTime);
            }
        }
    }




    @Override
    public void onStart(String tag) {

    }

    @Override
    public void onSuccess(Object o, String tag) {
        switch (tag) {
            case UniversialKeyContract.GET_TOWER_NO://获取楼号
                TowerBean towerBean = (TowerBean) o;
                if (towerBean != null) {
                    if (towerBean.getData() != null) {
                        final List<TowerBean.DataBean> towers = towerBean.getData();
                        if (towers.size() > 0) {
                            //条件选择器
                            PickerManager.getInstance().showOptionPicker(this, towers,new PickerManager.OnOptionPickerSelectedListener() {
                                @Override
                                public void onOptionsSelect(int options1, int option2, int options3, View v) {
//返回的分别是三个级别的选中位置
                                    selectedTowerInfo = towers.get(options1);
                                    String tx = selectedTowerInfo.getPickerViewText();
                                    mPublishTaskAddrTowerTv.setText(tx);
                                    mPublishTaskAddrCellTv.setText("单元");
                                }
                            });
                        }
                    }
                }
                break;
            case UniversialKeyContract.GET_CELL_NO://获单元号
                CellBean cellBean = (CellBean) o;
                if (cellBean != null) {
                    if (cellBean.getData() != null) {
                        final List<CellBean.DataBean> cellsList = cellBean.getData();
                        if (cellsList.size() > 0) {
                            //条件选择器
                            PickerManager.getInstance().showOptionPicker(this, cellsList,new PickerManager.OnOptionPickerSelectedListener() {
                                @Override
                                public void onOptionsSelect(int options1, int option2, int options3, View v) {
//返回的分别是三个级别的选中位置
                                    selectedCellInfo = cellsList.get(options1);
                                    String tx = selectedCellInfo.getPickerViewText();
                                    mPublishTaskAddrCellTv.setText(tx);
                                }
                            });
                        }
                    }
                }
                break;
            default:
                break;
        }

    }

    @Override
    public void onError(String tag) {
        stopMaterialProgressDialog();
        showToast(tag);

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
        showNormalToast("发起任务成功");
        setResult(ActivityResultManager.PUBLISH_TASK);
        finish();
    }


}
