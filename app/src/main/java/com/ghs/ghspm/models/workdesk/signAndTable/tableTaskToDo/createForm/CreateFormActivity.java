package com.ghs.ghspm.models.workdesk.signAndTable.tableTaskToDo.createForm;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ghs.ghspm.R;
import com.ghs.ghspm.base.BaseActivity;
import com.ghs.ghspm.base.network.RequestStatus;
import com.ghs.ghspm.base.promission.CheckPermListener;
import com.ghs.ghspm.bean.AutoFormBean;
import com.ghs.ghspm.bean.CellBean;
import com.ghs.ghspm.bean.DailyTaskJsonBean;
import com.ghs.ghspm.bean.DynamicLayoutBean;
import com.ghs.ghspm.bean.DynamicSelectedPicBean;
import com.ghs.ghspm.bean.PatrolFormTaskBean;
import com.ghs.ghspm.bean.RoomBean;
import com.ghs.ghspm.bean.TableMultipleItem;
import com.ghs.ghspm.bean.TableTaskDetailBean;
import com.ghs.ghspm.bean.ToolFormBean;
import com.ghs.ghspm.bean.TowerBean;
import com.ghs.ghspm.models.oss.OssUploadManager;
import com.ghs.ghspm.models.task.publishtask.SelectRelatedPeopleActivity;
import com.ghs.ghspm.models.workdesk.publicfuction.universalkey.UniversialKeyContract;
import com.ghs.ghspm.models.workdesk.publicfuction.universalkey.UniversialKeyModel;
import com.ghs.ghspm.models.workdesk.signAndTable.SignTableContrct;
import com.ghs.ghspm.models.workdesk.signAndTable.SignTablePresent;
import com.ghs.ghspm.tools.ActivityResultManager;
import com.ghs.ghspm.tools.CalendarUtil;
import com.ghs.ghspm.tools.FileUtil;
import com.ghs.ghspm.tools.GsonManager;
import com.ghs.ghspm.tools.ImageUtil;
import com.ghs.ghspm.tools.PickerManager;
import com.ghs.ghspm.tools.PubUtil;
import com.ghs.ghspm.tools.RxScheduler;
import com.ghs.ghspm.tools.StrUtils;
import com.ghs.ghspm.tools.UserInfoUtil;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import cn.com.mark.multiimage.core.ImageMainActivity;

/**
 * created by tobato
 * created date 2018/12/25 14:16.
 * application   需要填写的表单类  创建新的表单
 */
public class CreateFormActivity extends BaseActivity<SignTableContrct.ISignTableView, SignTablePresent> implements RequestStatus, View.OnClickListener, DynamicLayoutListener, SignTableContrct.ISignTableView {

    private RecyclerView mDetailRv;
    private TowerBean.DataBean selectTowerBean = null;//楼
    private CellBean.DataBean selectCellBean = null;//单元
    private RoomBean.DataBean selectRoomBean = null;//房间
    private TextView towerTv;
    private TextView cellTv;
    private TextView roomTv;
    private TextView mTableTaskDealTv;
    private CreateFormAdapter dailyRecordAdapter;
    private List<String> multiSelectList = new ArrayList<>();//多选列表
    private StringBuffer selectedUsersSbId = new StringBuffer();//多选
    private StringBuffer selectedUsersSbName = new StringBuffer();//多选
    private StringBuffer selectedUserSb = new StringBuffer();//单选
    private TextView selectUserTv;//选择人员
    private List<DynamicLayoutBean> dynamicLayoutArrays;
    private DynamicLayoutBean selectUserDynamicLayoutBean = null;//选择人员

    private DynamicLayoutBean TowerDynamicLayoutBean = null;//动态布局 选择楼号
    private DynamicLayoutBean CellDynamicLayoutBean = null;//动态布局 选择单元
    private DynamicLayoutBean RoomDynamicLayoutBean = null;//动态布局 选择房间号
    private TableTaskDetailBean.DataBean dataBean;
    private ToolFormBean.DataBean toolFormBean;
    private Map<String, DisplaySelectedPicsAdapter> adapterMap = new LinkedHashMap<>();//当有多个选择图片的控件的时候，用于存储对应的adapter
    private String currentAdapterMapKey = "";
    private int maxCount = 10;//图片总数
    private int spanCount = 5;//单行图片的个数
    private DynamicLayoutBean editTextDynamicLayoutBean = null;//当前点击的edittext对应的item
    private DynamicLayoutBean imageDynamicLayoutBean = null;//当前点击的image对应的item
    private DynamicLayoutBean dynamicLayoutBean = null;//当前点击的类似选择签批人 抄送人  楼号 单元等对应的item的实体类

    private boolean hasSelectImageView = false;//是否存在选择图片的控件
    private OssUploadManager ossManager;
    private BottomSheetDialog bottomSheetDialog;
    private DisplaySelectedPicsAdapter adapter;

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
        setContentView(R.layout.activity_table_task_detial);

    }

    @Override
    public void initLayoutView() {
        initView();

    }

    @Override
    public void getDate() {
        switch (PubUtil.DailyRecordActivityEntry) {
            case 0:
                toolFormBean = getIntent().getParcelableExtra(ActivityResultManager.TABLE_TASK_DAILY_RECORD);
                if (toolFormBean != null) {
                    initActionBar(toolFormBean.getFormName(), null);
                    String fieldJson = toolFormBean.getFieldJson();
                    if (StrUtils.isStringValueOk(fieldJson)) {
                        dynamicLayoutArrays = (List<DynamicLayoutBean>) GsonManager.getInstance().parseJsonToList(toolFormBean.getFieldJson(), new TypeToken<List<DynamicLayoutBean>>() {
                        }.getType());
                        dynamicLayoutArrays.add(new DynamicLayoutBean("check_user", "请选择对应的签批人", "签批人", null, ""));
                        dynamicLayoutArrays.add(new DynamicLayoutBean("copy_user", "请选择对应的抄送人", "抄送人", null, ""));
                    }
                    dailyRecordAdapter.setNewData(getPresenter().loadDynamicLayoutData(dynamicLayoutArrays, toolFormBean.getUsage()));
                }
                break;
            default:
                unToolForm();
                break;
        }
        if (dynamicLayoutArrays != null) {
            if (dynamicLayoutArrays.size() > 0) {
                for (DynamicLayoutBean dynamicLayoutArray : dynamicLayoutArrays) {
                    String cssClass = dynamicLayoutArray.getCssClass();
                    if ("image".equals(cssClass)) {
                        if (ossManager == null) {
                            ossManager = OssUploadManager.getInstance(this);
                        }
                        break;
                    }
                }
            }
        }
    }

    /**
     * 非工具类表单的逻辑
     */
    private void unToolForm() {
        dataBean = getIntent().getParcelableExtra(ActivityResultManager.TABLE_TASK_DAILY_RECORD);
        if (dataBean != null) {
            initActionBar(dataBean.getFormName(), null);
            dynamicLayoutArrays = (List<DynamicLayoutBean>) GsonManager.getInstance().parseJsonToList(dataBean.getFieldJson(), new TypeToken<List<DynamicLayoutBean>>() {
            }.getType());
            if (3 == PubUtil.DailyRecordActivityEntry) {//修改
                if (PubUtil.CURRENT_SELECTED_VALUE_LIST.size() > 0) {
                    for (int i = 0; i < dynamicLayoutArrays.size(); i++) {
                        DynamicLayoutBean dynamicLayoutBean = dynamicLayoutArrays.get(i);
                        String cssName = dynamicLayoutBean.getCssClass();

                        String value = PubUtil.CURRENT_SELECTED_VALUE_LIST.get(i);
                        if (StrUtils.isStringValueOk(value)) {
                            dynamicLayoutBean.setValue(value);
                        }
                        if ("cell".equals(cssName)) {///单元对应的房间id
                            dynamicLayoutBean.setPreId(PubUtil.patrolFormSelectedTowerId);
                        }
                        if ("room".equals(cssName)) {//房间对应的单元id
                            dynamicLayoutBean.setPreId(PubUtil.patrolFormSelectedCellId);

                        }

                    }
                }
            }

            dailyRecordAdapter.setNewData(getPresenter().loadDynamicLayoutData(dynamicLayoutArrays, dataBean.getUsage()));
        }
    }


    @Override
    public void actionBarRightTvOnClick() {

    }

    private void initView() {
        mDetailRv = (RecyclerView) findViewById(R.id.detail_rv);
        mTableTaskDealTv = (TextView) findViewById(R.id.table_task_deal_tv);
        mTableTaskDealTv.setText("保存");
        mTableTaskDealTv.setOnClickListener(this);
        dailyRecordAdapter = new CreateFormAdapter(null, this);
        initRecyclerview(mDetailRv, dailyRecordAdapter, LinearLayoutManager.VERTICAL, false);
        dailyRecordAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {


            @Override
            public void onItemClick(final BaseQuickAdapter adapter, View view, final int position) {
                DynamicLayoutBean bean = null;
                selectUserTv = (TextView) adapter.getViewByPosition(mDetailRv, position, R.id.dynamic_layout_select_tv);
                TableMultipleItem tableMultipleItem = (TableMultipleItem) adapter.getItem(position);
                bean = (DynamicLayoutBean) tableMultipleItem.getObject();
                dynamicLayoutBean = bean;
                if (dynamicLayoutBean != null) {
                    int type = tableMultipleItem.getItemType();
                    switch (type) {
                        case TableMultipleItem.TABLE_TEXT://单行文本
                            Log.d("", "");
                            break;
                        case TableMultipleItem.TABLE_AREA://多行文本

                            break;
                        case TableMultipleItem.TABLE_RADIO://单项选择框
                            singleSelectLogic(adapter, position, dynamicLayoutBean);


                            break;
                        case TableMultipleItem.TABLE_CHECKBOX://多项选择框
                            final TextView multiText = (TextView) adapter.getViewByPosition(mDetailRv, position, R.id.dynamic_layout_select_tv);
                            multiSelectList.clear();
                            final List<String> list_more = dynamicLayoutBean.getChildren();
                            final BottomSheetDialog multiSelectBd = new BottomSheetDialog(CreateFormActivity.this);
                            multiSelectBd.setCanceledOnTouchOutside(true);
                            View mView = LayoutInflater.from(CreateFormActivity.this).inflate(R.layout.multi_select_layout, null);
                            TextView cancelTv = mView.findViewById(R.id.multi_select_cancel_tv);
                            TextView confirmTv = mView.findViewById(R.id.multi_select_confirm_tv);
                            final RecyclerView multiRv = mView.findViewById(R.id.multi_select_rv);
                            final MultiSelectAdapter multiSelectAdapter = new MultiSelectAdapter(R.layout.multi_select_layout_item);
                            initRecyclerview(multiRv, multiSelectAdapter, LinearLayoutManager.VERTICAL, false);
                            multiSelectAdapter.setNewData(list_more);
                            cancelTv.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    dismissDialog(multiSelectBd);
                                }
                            });
                            confirmTv.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if (multiSelectList.size() > 0) {
                                        StringBuilder sb = new StringBuilder(multiSelectList.size() * 2);
                                        for (int i = 0; i < multiSelectList.size(); i++) {
                                            String s = multiSelectList.get(i);
                                            if (i == multiSelectList.size() - 1) {
                                                sb.append(s);
                                            } else {
                                                sb.append(s);
                                                sb.append(",");
                                            }
                                        }
                                        dynamicLayoutBean.setValue(sb.toString().trim());
                                        adapter.notifyItemChanged(position);

                                    }
                                    dismissDialog(multiSelectBd);
                                }
                            });
                            multiSelectBd.setContentView(mView);
                            multiSelectBd.show();
                            multiSelectAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                    CheckBox checkBox = (CheckBox) adapter.getViewByPosition(multiRv, position, R.id.multi_select_cb);
                                    String content = (String) adapter.getData().get(position);
                                    if (checkBox.isChecked()) {
                                        if (multiSelectList.contains(content)) {
                                            multiSelectList.remove(content);
                                        }
                                        checkBox.setChecked(false);
                                    } else {
                                        checkBox.setChecked(true);
                                        if (!multiSelectList.contains(content)) {
                                            multiSelectList.add(content);
                                        }
                                    }
                                }
                            });
                            break;
                        case TableMultipleItem.TABLE_NUMBER://数字输入框

                            break;
                        case TableMultipleItem.TABLE_MONEY://金额输入框

                            break;
                        case TableMultipleItem.TABLE_USER://选择人员，可多选
                            selectUserDynamicLayoutBean = dynamicLayoutBean;
                            initSelectUserStatus(2, selectedUsersSbId);

                            break;
                        case TableMultipleItem.TABLE_COPY_USER://选择人员，抄送人
                            selectUserDynamicLayoutBean = dynamicLayoutBean;

                            initSelectUserStatus(2, selectedUsersSbId);

                            break;
                        case TableMultipleItem.TABLE_CHECK_USER://选择人员，签批人
                            selectUserDynamicLayoutBean = dynamicLayoutBean;

                            initSelectUserStatus(4, selectedUserSb);

                            break;
                        case TableMultipleItem.TABLE_IMAGE://图片

                            break;
                        case TableMultipleItem.TABLE_DATE://日期
                            //时间选择器

                            final TextView tv = (TextView) adapter.getViewByPosition(mDetailRv, position, R.id.dynamic_layout_select_tv);
                            PickerManager.getInstance().showTimePickerView(CreateFormActivity.this, null, null, new PickerManager.OnTimePickerTimeSelectedListener() {
                                @Override
                                public void onTimeSelect(Date date, View v) {
                                    PubUtil.getViewFocus(tv);
                                    tv.setText(CalendarUtil.getTimeFromDate("yyyy-MM-dd", date));
                                    dynamicLayoutBean.setValue(CalendarUtil.getTimeFromDate("yyyy-MM-dd", date));
                                    adapter.notifyItemChanged(position);
                                }
                            });
                            break;
                        case TableMultipleItem.TABLE_DEVICE://设备单选框
                            singleSelectLogic(adapter, position, dynamicLayoutBean);

                            break;
                        case TableMultipleItem.TABLE_TOWER://楼栋单选框
                            TowerDynamicLayoutBean = dynamicLayoutBean;
                            showMaterialProgressDialog("", "");
                            new UniversialKeyModel().getTowerNo(UserInfoUtil.getInstance().getVillageId(), CreateFormActivity.this, UniversialKeyContract.GET_TOWER_NO);
                            break;
                        case TableMultipleItem.TABLE_CELL://单元号单选框
                            CellDynamicLayoutBean = dynamicLayoutBean;
                            showMaterialProgressDialog("", "");
                            String towerId = dynamicLayoutBean.getPreId();
                            if (StrUtils.isStringValueOk(towerId)) {
                                if (selectTowerBean == null) {
                                    new UniversialKeyModel().getCellNo(UserInfoUtil.getInstance().getVillageId(), Integer.parseInt(towerId), CreateFormActivity.this, UniversialKeyContract.GET_CELL_NO);
                                } else {
                                    if (towerId.equals(String.valueOf(selectTowerBean.getId()))) {
                                        //楼号没有改动
                                        new UniversialKeyModel().getCellNo(UserInfoUtil.getInstance().getVillageId(), Integer.parseInt(towerId), CreateFormActivity.this, UniversialKeyContract.GET_CELL_NO);
                                    } else {
                                        new UniversialKeyModel().getCellNo(UserInfoUtil.getInstance().getVillageId(), selectTowerBean.getId(), CreateFormActivity.this, UniversialKeyContract.GET_CELL_NO);
                                    }
                                }
                            } else {
                                if (selectTowerBean == null) {
                                    showNormalToast("请先选择楼号");
                                    stopMaterialProgressDialog();
                                    return;
                                }
                                new UniversialKeyModel().getCellNo(UserInfoUtil.getInstance().getVillageId(), selectTowerBean.getId(), CreateFormActivity.this, UniversialKeyContract.GET_CELL_NO);
                            }
                            break;
                        case TableMultipleItem.TABLE_ROOM://房间号单选框
                            RoomDynamicLayoutBean = dynamicLayoutBean;
                            if (cellTv != null) {
                                String cell = getTextViewValue(cellTv);
                                if ("请选择".equals(cell)) {
                                    showNormalToast("请先选择单元号");
                                    return;
                                }
                            }

                            showMaterialProgressDialog("", "");
                            String cellId = dynamicLayoutBean.getPreId();
                            if (StrUtils.isStringValueOk(cellId)) {
                                if (selectCellBean == null) {
                                    new UniversialKeyModel().getRoomNo(UserInfoUtil.getInstance().getVillageId(), Integer.parseInt(cellId), CreateFormActivity.this, UniversialKeyContract.GET_ROOM_NO);
                                } else {
                                    if (cellId.equals(String.valueOf(selectCellBean.getId()))) {
                                        //单元号没有改动
                                        new UniversialKeyModel().getRoomNo(UserInfoUtil.getInstance().getVillageId(), Integer.parseInt(cellId), CreateFormActivity.this, UniversialKeyContract.GET_ROOM_NO);
                                    } else {
                                        new UniversialKeyModel().getRoomNo(UserInfoUtil.getInstance().getVillageId(), selectCellBean.getId(), CreateFormActivity.this, UniversialKeyContract.GET_ROOM_NO);
                                    }
                                }
                            } else {
                                if (selectCellBean == null) {
                                    showNormalToast("请先选择单元号");
                                    stopMaterialProgressDialog();
                                    return;
                                }
                                new UniversialKeyModel().getRoomNo(UserInfoUtil.getInstance().getVillageId(), selectCellBean.getId(), CreateFormActivity.this, UniversialKeyContract.GET_ROOM_NO);
                            }
                            break;
                        default:
                            break;
                    }
                }
            }
        });

    }

    /**
     * 单项选择框逻辑
     *
     * @param adapter
     * @param position
     * @param dynamicLayoutBean
     */
    private void singleSelectLogic(final BaseQuickAdapter adapter, final int position, final DynamicLayoutBean dynamicLayoutBean) {

        final List<String> list_single = dynamicLayoutBean.getChildren();
        //条件选择器
        PickerManager.getInstance().showOptionPicker(this, list_single, new PickerManager.OnOptionPickerSelectedListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                dynamicLayoutBean.setValue(list_single.get(options1));
                adapter.notifyItemChanged(position);
            }
        });

    }

    /**
     * 初始化选择人物之前的状态
     *
     * @param selectType 0多选 1 单选
     */
    private void initSelectUserStatus(int selectType, StringBuffer sb) {
        PubUtil.publish_task_select_people = selectType;
        PubUtil.selectedUsersMap.clear();
        sb.delete(0, sb.length());
        startActivityForResult(new Intent(CreateFormActivity.this, SelectRelatedPeopleActivity.class), ActivityResultManager.PUBLISH_TASK_SELECT_PEOPLE);
    }

    @Override
    public void onStart(String tag) {

    }

    @Override
    public void onSuccess(Object o, String tag) {
        stopMaterialProgressDialog();
        switch (tag) {
            case UniversialKeyContract.GET_TOWER_NO:
                TowerBean towerBean = (TowerBean) o;
                if (towerBean != null) {
                    if (towerBean.getData() != null) {
                        final List<TowerBean.DataBean> towers = towerBean.getData();
                        if (towers.size() > 0) {
                            //条件选择器
                            PickerManager.getInstance().showOptionPicker(this, towers, new PickerManager.OnOptionPickerSelectedListener() {
                                @Override
                                public void onOptionsSelect(int options1, int option2, int options3, View v) {
                                    //返回的分别是三个级别的选中位置
                                    selectTowerBean = towers.get(options1);
                                    String tx = selectTowerBean.getPickerViewText();
                                    PubUtil.getViewFocus(towerTv);
                                    towerTv.setText(tx);
                                    dynamicLayoutBean.setValue(tx);
                                    dynamicLayoutBean.setPreId(selectTowerBean.getId() + "");
                                    if (cellTv != null) {
                                        cellTv.setText("请选择");
                                    }
                                    selectCellBean = null;
                                    if (roomTv != null) {
                                        roomTv.setText("请选择");
                                    }
                                    selectRoomBean = null;
                                }
                            });
                        }
                    }
                }
                break;
            case UniversialKeyContract.GET_CELL_NO:
                CellBean cellBean = (CellBean) o;
                if (cellBean != null) {
                    if (cellBean.getData() != null) {
                        final List<CellBean.DataBean> cellsList = cellBean.getData();
                        if (cellsList.size() > 0) {
                            //条件选择器
                            PickerManager.getInstance().showOptionPicker(this, cellsList, new PickerManager.OnOptionPickerSelectedListener() {
                                @Override
                                public void onOptionsSelect(int options1, int option2, int options3, View v) {
                                    //返回的分别是三个级别的选中位置
                                    selectCellBean = cellsList.get(options1);
                                    String tx = selectCellBean.getPickerViewText();
                                    PubUtil.getViewFocus(cellTv);

                                    cellTv.setText(tx);
                                    dynamicLayoutBean.setValue(tx);
                                    dynamicLayoutBean.setPreId(selectCellBean.getTowerId() + "");

                                    if (roomTv != null) {
                                        roomTv.setText("请选择");
                                    }
                                    selectRoomBean = null;
                                }
                            });
                        } else {
                            showNormalToast("该楼栋没有单元");
                        }
                    }
                }
                break;
            case UniversialKeyContract.GET_ROOM_NO:
                RoomBean roomBean = (RoomBean) o;
                if (roomBean != null) {
                    if (roomBean.getData() != null) {
                        final List<RoomBean.DataBean> roomList = roomBean.getData();
                        if (roomList.size() > 0) {
                            //条件选择器
                            PickerManager.getInstance().showOptionPicker(this, roomList, new PickerManager.OnOptionPickerSelectedListener() {
                                @Override
                                public void onOptionsSelect(int options1, int option2, int options3, View v) {
                                    //返回的分别是三个级别的选中位置
                                    selectRoomBean = roomList.get(options1);
                                    String tx = selectRoomBean.getRoomNumber();
                                    PubUtil.getViewFocus(roomTv);
                                    roomTv.setText(tx + "室");
                                    dynamicLayoutBean.setValue(tx);
                                    dynamicLayoutBean.setPreId(selectRoomBean.getCellId() + "");

                                }
                            });

                        } else {
                            showNormalToast("该单元没有房间");
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
        showToast("提交成功");

        switch (tag) {
            case SignTableContrct.SAVE_PATROL_FORM_RECORD:
                //编辑或者保存
                PatrolFormTaskBean.DataBean patrolFormTaskBean = (PatrolFormTaskBean.DataBean) o;
                if (patrolFormTaskBean != null) {
                    int recordId = patrolFormTaskBean.getId();
                    String contentJson = patrolFormTaskBean.getContentJson();
                    ArrayList<AutoFormBean.DataBean> arrays = (ArrayList<AutoFormBean.DataBean>) GsonManager.getInstance().parseJsonToList(contentJson, new TypeToken<ArrayList<AutoFormBean.DataBean>>() {
                    }.getType());
                    for (AutoFormBean.DataBean array : arrays) {
                        array.setRecordId(recordId);
                    }
                    arrays.size();
                    Intent intent = new Intent();
                    intent.putParcelableArrayListExtra(ActivityResultManager.PATROL_FORM_TASK_RECORD_ACTIVITY, arrays);
                    setResult(ActivityResultManager.PATROL_FORM, intent);
                }

                break;
            default:
                setResult(ActivityResultManager.FINISH_FORM_TASK_DETAIL_ACTIVITY);
                break;
        }
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (ActivityResultManager.PUBLISH_TASK_SELECT_PEOPLE == resultCode) {
            if (2 == PubUtil.publish_task_select_people) {//抄送人
                clearStringBuffer(selectedUsersSbId);
                clearStringBuffer(selectedUsersSbName);
                initSelectedPeoples(selectedUsersSbId, selectedUsersSbName, selectUserTv);
                String names = selectedUsersSbName.toString().trim();
                if (StrUtils.isStringValueOk(names)) {
                    if (names.contains(",")) {
                        names = names.substring(0, names.length() - 1);
                    }
                }
                if (0 != PubUtil.DailyRecordActivityEntry) {
                    dynamicLayoutBean.setValue(names);
                } else {
                    if (selectUserDynamicLayoutBean != null) {
                        dynamicLayoutBean.setValue(names);
                    }
                }

            } else if (4 == PubUtil.publish_task_select_people) {//签批人
                initSelectedPeoples(selectedUserSb, selectUserTv);
                if (0 != PubUtil.DailyRecordActivityEntry) {
                    dynamicLayoutBean.setValue(selectUserTv.getText().toString().trim());
                } else {
                    if (selectUserDynamicLayoutBean != null) {
                        dynamicLayoutBean.setValue(selectUserTv.getText().toString().trim());

                    }
                }
            }
        } else if (-1 == resultCode) {
            if (requestCode == ActivityResultManager.TAKE_PICTURE) {
                Bitmap bitmap = ImageUtil.readPictureDegreeToForwordBitmap(FileUtil.getHeadPicRootPath(this));
                String userHeadPic = ImageUtil.saveCroppedImage(FileUtil.getHeadPicDir(this), bitmap);
                Log.d(TAG, currentAdapterMapKey + "-----开始存储拍的图片--未处理--------" + userHeadPic);

                if (adapterMap.size() > 0) {
                    DisplaySelectedPicsAdapter adapter = getCurrentImageAdapter();
                    if (adapter != null) {
                        List<DynamicSelectedPicBean> arrays = adapter.getData();
                        arrays.add(new DynamicSelectedPicBean(currentAdapterMapKey, userHeadPic));
                        adapter.setNewData(reSortIconList(currentAdapterMapKey, arrays));
                        //给选择图片的控件赋值
                        if (imageDynamicLayoutBean != null) {
                            String value = getDealedPhotosLocal(arrays);
                            Log.d(TAG, currentAdapterMapKey + "-----开始存储拍的图片--------" + value);
                            imageDynamicLayoutBean.setValue(value);
                        }
                    }
                }
            } else if (requestCode == ActivityResultManager.SYSTEM_PICTURE) {
                String imagePath = "";
                ArrayList<Uri> images = data.getParcelableArrayListExtra("result");
                if (adapterMap.size() > 0) {
                    DisplaySelectedPicsAdapter adapter = getCurrentImageAdapter();
                    if (adapter != null) {
                        List<DynamicSelectedPicBean> arrays = adapter.getData();
                        for (int i = 0; i < images.size(); i++) {
                            imagePath = images.get(i).getPath();
                            arrays.add(new DynamicSelectedPicBean(currentAdapterMapKey, imagePath));
                        }
                        adapter.setNewData(reSortIconList(currentAdapterMapKey, arrays));
                        //给选择图片的控件赋值
                        if (imageDynamicLayoutBean != null) {
                            String value = getDealedPhotosLocal(arrays);
                            Log.d(TAG, currentAdapterMapKey + "-----开始存储拍的图片--------" + value);
                            imageDynamicLayoutBean.setValue(value);
                        }
                    }
                }
            }

        } else {
            super.onActivityResult(requestCode, resultCode, data);

        }
    }

    /**
     * 获取当前图片选择器的adapter
     *
     * @return
     */
    private DisplaySelectedPicsAdapter getCurrentImageAdapter() {
        DisplaySelectedPicsAdapter adapter = null;
        for (Map.Entry<String, DisplaySelectedPicsAdapter> entry : adapterMap.entrySet()) {
            String key = entry.getKey();
            if (key.equals(currentAdapterMapKey)) {
                adapter = entry.getValue();
                break;
            }
        }
        return adapter;
    }

    /**
     * 对icons集合处理
     *
     * @return
     */
    private List<DynamicSelectedPicBean> reSortIconList(String key, List<DynamicSelectedPicBean> icons) {
        List<DynamicSelectedPicBean> icons_new = new ArrayList<>();
        if (icons != null) {
            for (DynamicSelectedPicBean icon : icons) {
                if (!"-1".equals(icon.getImagePath())) {
                    icons_new.add(icon);
                }
            }
        }

        if (icons == null || icons.size() < maxCount) {
            icons_new.add(new DynamicSelectedPicBean(key, "-1"));
        }
        return icons_new;
    }

    /**
     * @return
     */
    private String getDealedPhotosLocal(List<DynamicSelectedPicBean> arrays) {
        StringBuilder sb = new StringBuilder();
        if (arrays.size() > 0) {
            Bitmap bitmap = null;
            for (int i = 0; i < arrays.size(); i++) {
                DynamicSelectedPicBean bean = arrays.get(i);
                String icon = bean.getImagePath();
                if (!"-1".equals(icon)) {
                    if (icon.contains("com.ghs.ghspm")) {
                        long size = 0;
                        try {
                            size = FileUtil.getFileSize(new File(icon));
                            if (size > 700 * 1024) {
                                bitmap = ImageUtil.readPictureDegreeToForwordBitmap(icon);
                            } else {
                                bitmap = BitmapFactory.decodeFile(icon);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        String userHeadPic = ImageUtil.saveCroppedImage(FileUtil.getHeadPicDir(this), bitmap);
                        if (i == arrays.size() - 1) {
                            sb.append(userHeadPic);
                        } else {
                            sb.append(userHeadPic);
                            sb.append(",");
                        }
                    } else {
                        if (i == arrays.size() - 1) {
                            sb.append(icon);
                        } else {
                            sb.append(icon);
                            sb.append(",");
                        }
                    }


                }
            }
        }
        return sb.toString();
    }


    /**
     * Edittext 内容变化时 保存 或者删除数据
     */

    @Override
    public void getSelectPhotosData(String value, RecyclerView recyclerView) {
        final DynamicLayoutBean dynamicLayoutBean = (DynamicLayoutBean) recyclerView.getTag();
        adapter = new DisplaySelectedPicsAdapter(R.layout.show_selected_pic_item);
        GridLayoutManager managere = new GridLayoutManager(this, spanCount);
        recyclerView.setLayoutManager(managere);
        recyclerView.setAdapter(adapter);
        String title = dynamicLayoutBean.getTitle();
        adapterMap.put(title, adapter);

        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {

            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                List<DynamicSelectedPicBean> data = adapter.getData();
                DynamicSelectedPicBean dynamicSelectedPicBean = (DynamicSelectedPicBean) data.get(position);
                currentAdapterMapKey = dynamicSelectedPicBean.getDynamicLayoutTitle();
                imageDynamicLayoutBean = getCurrentImageLayout(currentAdapterMapKey);
                List<DynamicSelectedPicBean> arrays = reSortIconList(currentAdapterMapKey, data);
                String icon_path = dynamicSelectedPicBean.getImagePath();
                switch (view.getId()) {
                    case R.id.mine_sugguest_icon_iv:
                        if ("-1".equals(icon_path)) {
                            View bottomView = LayoutInflater.from(CreateFormActivity.this).inflate(R.layout.select_pic_menue, null);
                            bottomSheetDialog = new BottomSheetDialog(CreateFormActivity.this);
                            bottomSheetDialog.setCancelable(true);
                            bottomSheetDialog.setContentView(bottomView);
                            bottomSheetDialog.show();
                            bottomView.findViewById(R.id.mine_edit_cancel_pic_tv).setOnClickListener(CreateFormActivity.this);
                            bottomView.findViewById(R.id.mine_edit_take_pic_tv).setOnClickListener(CreateFormActivity.this);
                            bottomView.findViewById(R.id.mine_edit_select_pic_tv).setOnClickListener(CreateFormActivity.this);
                            bottomView.findViewById(R.id.mine_edit_title_tv).setVisibility(View.GONE);

                        }
                        break;
                    case R.id.mine_sugguest_delete_iv:
                        arrays.remove(position);
                        if (!checkImageAdapterData(arrays)) {
                            arrays.add(new DynamicSelectedPicBean(currentAdapterMapKey, "-1"));
                        }
                        adapter.setNewData(arrays);
                        imageDynamicLayoutBeanSetValue(arrays);
                        if (arrays.size() < 2) {
//                            removeOfDailyTaskJsonBeanMap(currentAdapterMapKey);
                        }
                        break;
                    default:
                        break;
                }
            }
        });
        if (!StrUtils.isStringValueOk(value)) {//没有选择图片
            List<DynamicSelectedPicBean> arrays = reSortIconList(title, null);
            adapter.setNewData(arrays);
        } else {
            List<DynamicSelectedPicBean> icons = new ArrayList<>();
            Log.d(TAG, title + "-----存储图片的路径￥￥￥￥￥￥￥￥￥￥" + value);
            if (value.contains(",")) {
                String[] arrs = value.split(",");//注意 这个地方有个空格
                for (String arr : arrs) {
                    icons.add(new DynamicSelectedPicBean(title, arr));
                }
            } else {
                icons.add(new DynamicSelectedPicBean(title, value));
            }
            List<DynamicSelectedPicBean> arrays = reSortIconList(title, icons);
            adapter.setNewData(arrays);
        }

    }

    @Override
    public void edittextOnTouchListen(DynamicLayoutBean dynamicLayoutBean) {
        editTextDynamicLayoutBean = dynamicLayoutBean;
        Log.d(TAG, editTextDynamicLayoutBean.getCssClass() + "点击edittext了++++++");
    }

    @Override
    public void edittextFocusChangedListen(EditText editText, boolean hasFocus) {
        String tv = editText.getText().toString().trim();
        Log.d(TAG, "edittext 输入的值为" + tv);

        if (!hasFocus) {
            //隐藏软键盘
            hideKeyboard(editText);
        }
    }

    @Override
    public void getTowerView(TextView textView) {
        towerTv = textView;
    }

    @Override
    public void getCellView(TextView textView) {
        cellTv = textView;
    }

    @Override
    public void getRoomView(TextView textView) {
        roomTv = textView;
    }

    /**
     * 获取当前选择图片控件对应的item实体类
     *
     * @param key
     * @return
     */
    private DynamicLayoutBean getCurrentImageLayout(String key) {
        DynamicLayoutBean bean = null;
        List<TableMultipleItem> adapterData = dailyRecordAdapter.getData();
        for (TableMultipleItem adapterDatum : adapterData) {
            int dataType = adapterDatum.getItemType();
            if (TableMultipleItem.TABLE_IMAGE == dataType) {//选择图片控件
                DynamicLayoutBean dynamicLayoutBean = (DynamicLayoutBean) adapterDatum.getObject();
                String title = dynamicLayoutBean.getTitle();
                if (key.equals(title)) {
                    bean = dynamicLayoutBean;
                    break;
                }
            }
        }
        return bean;
    }


    /**
     * 检查图片适配器中 是否有 添加图片的按钮
     *
     * @param arrays
     * @return
     */
    private boolean checkImageAdapterData(List<DynamicSelectedPicBean> arrays) {
        boolean hasPlus = false;
        if (arrays.size() < maxCount) {
            for (DynamicSelectedPicBean array : arrays) {
                String imagePath = array.getImagePath();
                if ("-1".equals(imagePath)) {
                    hasPlus = true;
                    break;
                }
            }
        }
        return hasPlus;
    }

    /**
     * @param icons
     */
    private void imageDynamicLayoutBeanSetValue(List<DynamicSelectedPicBean> icons) {
        if (icons != null && icons.size() > 0) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < icons.size(); i++) {
                DynamicSelectedPicBean icon = icons.get(i);
                if (!"-1".equals(icon.getImagePath())) {
                    if (i == icons.size() - 1) {
                        sb.append(icon.getImagePath());
                    } else {
                        sb.append(icon.getImagePath());
                        sb.append(",");
                    }
                }
            }
            if (sb.length() > 0) {
                imageDynamicLayoutBean.setValue(sb.toString());
            } else {
                imageDynamicLayoutBean.setValue("");
            }
        }
    }


    /**
     * 获取选择人的id
     *
     * @param sb
     * @return
     */
    private String getIds(StringBuffer sb) {
        String checkUser = sb.toString().trim();
        if (checkUser.contains(",")) {
            return checkUser.substring(0, checkUser.length() - 1);
        } else {
            return checkUser;
        }

    }


    /**
     * 获取需要上传的json字符串
     *
     * @return
     */
    private String getUploadedJson() {
        List<DailyTaskJsonBean> arrays = new ArrayList<>();
        List<TableMultipleItem> adapterData = dailyRecordAdapter.getData();
        for (TableMultipleItem adapterDatum : adapterData) {
            int dataType = adapterDatum.getItemType();
            if (TableMultipleItem.TABLE_TEXT_SHOW != dataType) {
                DynamicLayoutBean dynamicLayoutBean = (DynamicLayoutBean) adapterDatum.getObject();
                String value = dynamicLayoutBean.getValue();
                String title = dynamicLayoutBean.getTitle();
                String cssClass = dynamicLayoutBean.getCssClass();
                if (StrUtils.isStringValueOk(value)) {
                    arrays.add(new DailyTaskJsonBean(title, cssClass, value, dynamicLayoutBean.getPreId()));
                } else {
                    arrays.add(new DailyTaskJsonBean(title, cssClass, "", dynamicLayoutBean.getPreId()));
                }
            }
        }
        return GsonManager.getInstance().parseListToJson(arrays);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.table_task_deal_tv:

                if (dynamicLayoutArrays.size() > 0) {
                    if (checkValues()) {
                        return;
                    }
                    showMaterialProgressDialog(null, null);
                        if (hasSelectImageView) {//存在选择图片的控件
                            RxScheduler.doOnIoThread(new RxScheduler.IOTask<Void>() {
                                @Override
                                public void doOnIOThread() {

                                    List<TableMultipleItem> adapterData = dailyRecordAdapter.getData();

                                    for (TableMultipleItem adapterDatum : adapterData) {
                                        int dataType = adapterDatum.getItemType();
                                        if (TableMultipleItem.TABLE_IMAGE == dataType) {

                                            DynamicLayoutBean dynamicLayoutBean = (DynamicLayoutBean) adapterDatum.getObject();

                                            String value = dynamicLayoutBean.getValue();

                                            if (StrUtils.isStringValueOk(value)) {
                                                List<String> icons = new ArrayList<>();
                                                if (value.contains(",")) {
                                                    String[] arrs = value.split(",");//
                                                    for (String arr : arrs) {
                                                        icons.add(arr);
                                                    }
                                                } else {
                                                    icons.add(value);
                                                }

                                                String path = ossManager.getPhotoPathOfUploadedToOssServer(CreateFormActivity.this, icons);
                                                Log.i("TAG", "path-----------" + path);
                                                dynamicLayoutBean.setValue(path);
                                            }
                                        }
                                    }


                                    //请求网络  上传json
                                    Log.d(TAG, getUploadedJson());
                                    commitFormTask();
                                }
                            });
                        } else {
                            //  请求网络，上传json
                            Log.d(TAG, getUploadedJson());

                            commitFormTask();
                        }

                    }


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
                        Intent intent = new Intent(CreateFormActivity.this, ImageMainActivity.class);
                        intent.putExtra("action-original", true);
                        DisplaySelectedPicsAdapter adapter = getCurrentImageAdapter();
                        intent.putExtra("MAX_SEND", maxCount - adapter.getData().size());
                        startActivityForResult(intent, ActivityResultManager.SYSTEM_PICTURE);
                    }

                    @Override
                    public void selectedAllPermission() {

                    }
                }, R.string.perm_camera_store, PubUtil.promissions[1], PubUtil.promissions[3]);
                break;
        }
    }

    /**
     * 提交表单任务
     */
    private void commitFormTask() {
        if (0 == PubUtil.DailyRecordActivityEntry) {
            if (toolFormBean != null) {
                int checkerId = 0;
                String checkerStr = getIds(selectedUserSb);
                if (StrUtils.isStringValueOk(checkerStr)) {
                    checkerId = Integer.parseInt(checkerStr);
                }
                getPresenter().saveToolFormTask(UserInfoUtil.getInstance().getVillageId(), toolFormBean.getId(), UserInfoUtil.getInstance().getUserId(), checkerId, getIds(selectedUsersSbId), getUploadedJson(), "");
            }

        } else if (1 == PubUtil.DailyRecordActivityEntry) {
            if (dataBean != null) {
                getPresenter().saveCommonFormTask(dataBean.getId(), getUploadedJson(), UserInfoUtil.getInstance().getUserId(), SignTableContrct.SAVE_DAILY_FORM_TASK);
            }
        } else if (2 == PubUtil.DailyRecordActivityEntry) {
            if (dataBean != null) {
                //保存巡检表单的记录
                getPresenter().savePatrolFormTaskRecord(UserInfoUtil.getInstance().getVillageId(), dataBean.getId(), dataBean.getTaskId(), getUploadedJson(), UserInfoUtil.getInstance().getUserId(), SignTableContrct.SAVE_PATROL_FORM_RECORD);
            }
        } else if (3 == PubUtil.DailyRecordActivityEntry) {
            if (dataBean != null) {
                //修改巡检表单的记录  保存
                getPresenter().editPatrolFormTaskRecord(dataBean.getRecordId(), getUploadedJson(), UserInfoUtil.getInstance().getUserId(), SignTableContrct.SAVE_PATROL_FORM_RECORD);
            }

        }
    }


    /**
     * 检查填入的值
     */
    private boolean checkValues() {
        boolean isOk = false;
        List<TableMultipleItem> adapterData = dailyRecordAdapter.getData();
        for (TableMultipleItem adapterDatum : adapterData) {
            int dataType = adapterDatum.getItemType();
            if (TableMultipleItem.TABLE_TEXT_SHOW != dataType) {//非纯文本
                DynamicLayoutBean dynamicLayoutBean = (DynamicLayoutBean) adapterDatum.getObject();
                String title = dynamicLayoutBean.getTitle();
                String value = dynamicLayoutBean.getValue();
                hasSelectImageView = true;
                boolean required = StrUtils.isStringValueOk(dynamicLayoutBean.getRequired()) ? true : false;
                if (required) {

                    if (TableMultipleItem.TABLE_IMAGE == dataType) {
                        hasSelectImageView = true;
                    }
                    if (!StrUtils.isStringValueOk(value)) {
                        Toast.makeText(getApplicationContext(), title + "为必填项", Toast.LENGTH_LONG).show();
                        isOk = true;
                        break;
                    }
                }
            }
        }
        return isOk;

    }


    @Override
    public void onBackPressed() {

        hideKeyboard(mHeaderLeftIv);

        super.onBackPressed();
    }
}
