package com.ghs.ghspm.models.workOrder.createOrder;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ghs.ghspm.R;
import com.ghs.ghspm.base.BaseActivity;
import com.ghs.ghspm.bean.CreateOrderBean;
import com.ghs.ghspm.bean.CreateOrderTimeBean;
import com.ghs.ghspm.bean.WorkOrderUnreadBean;
import com.ghs.ghspm.models.task.publishtask.SelectRelatedPeopleActivity;
import com.ghs.ghspm.models.workOrder.WorkOrderContract;
import com.ghs.ghspm.models.workOrder.WorkOrderPresent;
import com.ghs.ghspm.tools.ActivityResultManager;
import com.ghs.ghspm.tools.CalendarUtil;
import com.ghs.ghspm.tools.PickerManager;
import com.ghs.ghspm.tools.PubUtil;
import com.ghs.ghspm.tools.StrUtils;
import com.ghs.ghspm.tools.UserInfoUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * created by tobato
 * created date 2019/7/19 10:49.
 * application   创建工单  完成页面
 */
public class CreateOrderSubmitActivity extends BaseActivity<WorkOrderContract.IWorkOrderView, WorkOrderPresent> implements View.OnClickListener, WorkOrderContract.IWorkOrderView {

    private LinearLayout mCreateOrderSubmitFollowLy;
    private LinearLayout mCreateOrderSubmitCheckLy;
    private LinearLayout mCreateOrderSubmitCopyLy;
    private LinearLayout mCreateOrderSubmitTimeLy;
    private String selectedTime;
    private String selectedDate;

    //跟进人
    private StringBuffer excuterBuffer = new StringBuffer();
    //验收人
    private StringBuffer leaderBuffer = new StringBuffer();
    //抄送人
    private StringBuffer duplicateBuffer = new StringBuffer();
    /**
     * 跟进人
     */
    private TextView mCreateOrderSubmitFollowTv;
    /**
     * 验收人
     */
    private TextView mCreateOrderSubmitCheckTv;
    /**
     * 抄送人
     */
    private TextView mCreateOrderSubmitCopyTv;
    private CreateOrderBean createOrderBean;
    /**
     * 尽快上门
     */
    private TextView mCreateOrderSubmitTimeContentTv;
    /**
     * 上一步
     */
    private TextView mOrderHeaderLeftIv;
    /**
     * 设置
     */
    private TextView mOrderHeaderTitleTv;
    /**
     * 设置
     */
    private TextView mOrderHeaderRightTv;
    private String excuters;
    private String checkUser;
    private String duplicate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public WorkOrderPresent creatPresenter() {
        return new WorkOrderPresent();
    }

    @Override
    public void setLayout() {
        setContentView(R.layout.activity_create_order_submit);
        initView();
        Intent intent = getIntent();
        createOrderBean = (CreateOrderBean) intent.getSerializableExtra(CreateOrderActivity.CREATE_DATA);
        if (createOrderBean != null) {
            mOrderHeaderTitleTv.setText(createOrderBean.getOrderType());
            mOrderHeaderRightTv.setText("完成");
        }
        initView(createOrderBean);
    }


    public void initView(CreateOrderBean createOrderBean) {

        mCreateOrderSubmitFollowTv.setText(createOrderBean.getFollowPeople());
        mCreateOrderSubmitCheckTv.setText(createOrderBean.getCheckPeople());
        mCreateOrderSubmitCopyTv.setText(createOrderBean.getCopyPeople());
        excuters = createOrderBean.getFollowPeopleId();
        checkUser = createOrderBean.getCheckPeopleId();
        duplicate = createOrderBean.getCopyPeopleId();
        //家庭报修工单有期望上门时间
        String hopeTime = createOrderBean.getOrderHopeGotoTime();
        if (!"尽快上门".equals(hopeTime)) {
            hopeTime = hopeTime.substring(5,hopeTime.length());
        }
        mCreateOrderSubmitTimeContentTv.setText(hopeTime);

        if ("物业报修单".equals(createOrderBean.getOrderType())) {
            if ("家庭报修".equals(createOrderBean.getRepairsType())) {
                mCreateOrderSubmitTimeLy.setVisibility(View.VISIBLE);
                //家庭报修操作
            } else {
                mCreateOrderSubmitTimeLy.setVisibility(View.GONE);
            }

        } else {
            mCreateOrderSubmitTimeLy.setVisibility(View.GONE);

        }


    }


    @Override
    public void initLayoutView() {

    }

    @Override
    public void getDate() {


    }

    @Override
    public void actionBarRightTvOnClick() {

    }

    private void initView() {
        mCreateOrderSubmitFollowLy = (LinearLayout) findViewById(R.id.create_order_submit_follow_ly);
        mCreateOrderSubmitFollowLy.setOnClickListener(this);
        mCreateOrderSubmitCheckLy = (LinearLayout) findViewById(R.id.create_order_submit_check_ly);
        mCreateOrderSubmitCheckLy.setOnClickListener(this);
        mCreateOrderSubmitCopyLy = (LinearLayout) findViewById(R.id.create_order_submit_copy_ly);
        mCreateOrderSubmitCopyLy.setOnClickListener(this);
        mCreateOrderSubmitTimeLy = (LinearLayout) findViewById(R.id.create_order_submit_time_ly);
        mCreateOrderSubmitTimeLy.setOnClickListener(this);
        mCreateOrderSubmitFollowTv = (TextView) findViewById(R.id.create_order_submit_follow_tv);
        mCreateOrderSubmitCheckTv = (TextView) findViewById(R.id.create_order_submit_check_tv);
        mCreateOrderSubmitCopyTv = (TextView) findViewById(R.id.create_order_submit_copy_tv);
        mCreateOrderSubmitTimeContentTv = (TextView) findViewById(R.id.create_order_submit_time_content_tv);

        mOrderHeaderLeftIv = (TextView) findViewById(R.id.order_header_left_iv);
        mOrderHeaderLeftIv.setOnClickListener(this);
        mOrderHeaderTitleTv = (TextView) findViewById(R.id.order_header_title_tv);
        mOrderHeaderRightTv = (TextView) findViewById(R.id.order_header_right_tv);
        mOrderHeaderRightTv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.create_order_submit_follow_ly:
                initSelectedUserData(0, excuterBuffer);
                break;
            case R.id.create_order_submit_check_ly:
                initSelectedUserData(1, leaderBuffer);
                break;
            case R.id.create_order_submit_copy_ly:
                initSelectedUserData(2, duplicateBuffer);
                break;
            case R.id.create_order_submit_time_ly:
                showtime();
                break;
            case R.id.order_header_left_iv:
                onBackPressed();
                break;
            case R.id.order_header_right_tv:
//完成
                if (createOrderBean != null) {
                    switch (createOrderBean.getOrderType()) {
                        case "物业报修单":

                            dateOnJson("1", createOrderBean);

                            break;

                        case "综合服务单":
                            dateOnJson("2", createOrderBean);

                            break;


                    }

                }

                break;
        }
    }

    /**
     * 参数上传
     *
     * @param type            1:物业保修单 2:综合服务单     工单类型
     * @param createOrderBean
     */
    public void dateOnJson(String type, CreateOrderBean createOrderBean) {

        Map<String, String> map = new HashMap<>();
        //操作人姓名
        map.put("userName", UserInfoUtil.getInstance().getUserName());
        //工单种类
        map.put("serviceClass", type);
        //工单内容
        map.put("content", createOrderBean.getOrderContent());
        //图片
        map.put("imageUrl", createOrderBean.getOrderImage());
        //报修类型
        map.put("serviceType", selectType("1".equals(type) ? createOrderBean.getRepairsType() : createOrderBean.getServeType()));
        //报修标签
        map.put("serviceLabel", createOrderBean.getRepairsLabel());
        //联系人姓名
        if (StrUtils.isStringValueOk(createOrderBean.getOrderName())) {
            map.put("ownerName", createOrderBean.getOrderName());
        }
        //联系人电话
        if (StrUtils.isStringValueOk(createOrderBean.getOrderMobile())) {
            map.put("mobile", createOrderBean.getOrderMobile());
        }
        //房间号
        if (StrUtils.isStringValueOk(createOrderBean.getOrderRoomId())) {
            map.put("roomId", createOrderBean.getOrderRoomId());
        }
        //跟进人
        if (StrUtils.isStringValueOk(excuters)) {
            map.put("repairUserId", excuters);
        }
        //检查人
        if (StrUtils.isStringValueOk(checkUser)) {
            map.put("checkUserId", checkUser);
        }
        //抄送人
        if (StrUtils.isStringValueOk(duplicate)) {
            map.put("ccUserId", duplicate);
        }
        //期望上门时间
        String  hopeTime = mCreateOrderSubmitTimeContentTv.getText().toString().trim();

        if (StrUtils.isStringValueOk(hopeTime) && type.equals("1") && "家庭报修".equals(createOrderBean.getRepairsType())) {
            if ("尽快上门".equals(hopeTime)) {
                map.put("hopeGotoTime", hopeTime);
            }else{
                map.put("hopeGotoTime", selectedDate+selectedTime);

            }
        }
        showMaterialProgressDialog();

        if (createOrderBean.isUpdateOrderState()) {
            //编辑
            getPresenter().updateOrder(Integer.parseInt(createOrderBean.getOrderId()), UserInfoUtil.getInstance().getUserId(), map, WorkOrderContract.UPDATE_ORDER_CONTENT);

        } else {
            getPresenter().createOrder(UserInfoUtil.getInstance().getVillageId(), UserInfoUtil.getInstance().getUserId(), map, WorkOrderContract.CREATE_ORDER);

        }


    }

    /**
     * 报修类型 1:家庭报修、4:公共报修、2:投诉、3:建议、5:报事、6:其他
     *
     * @param type
     * @return
     */
    public String selectType(String type) {
        String i = "";

        switch (type) {
            case "家庭报修":
                i = "1";
                break;
            case "公共报修":
                i = "4";
                break;

            case "投诉":
                i = "2";
                break;
            case "建议":
                i = "3";
                break;

            case "报事":
                i = "5";
                break;

            case "其他":
                i = "6";
                break;

        }

        return i;

    }

    private void showtime() {

        final ArrayList<CreateOrderTimeBean> timeList = new ArrayList<>();
        final ArrayList<ArrayList<String>> timeList1 = new ArrayList<>();
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("7:00-12:00");
        arrayList.add("12:00-18:00");
        arrayList.add("18:00-22:00");

        for (int i = 1; i < 30; i++) {
            CreateOrderTimeBean createOrderTimeBean = new CreateOrderTimeBean();
            createOrderTimeBean.setName(CalendarUtil.getNextDayTime(i, "yyyy年MM月dd日 "));
            createOrderTimeBean.setTime(arrayList);
            timeList1.add(arrayList);
            timeList.add(createOrderTimeBean);
        }

        PickerManager.getInstance().showOptionPicker(this, timeList, timeList1, new PickerManager.OnOptionPickerSelectedListener() {



            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                selectedDate = timeList.get(options1).getName();
                selectedTime = timeList1.get(options1).get(option2);
                if (selectedDate != null) {
                    mCreateOrderSubmitTimeContentTv.setText( CalendarUtil.getTimeFromStringTime("MM月dd日","yyyy年MM月dd日",selectedDate)+ selectedTime);

                }
            }
        });


    }


    private void initSelectedUserData(int type, StringBuffer stringBuffer) {
        PubUtil.publish_task_select_people = type;
        PubUtil.selectedUsersMap.clear();
        stringBuffer.delete(0, stringBuffer.length());
        startActivityForResult(new Intent(this, SelectRelatedPeopleActivity.class), ActivityResultManager.PUBLISH_TASK_SELECT_PEOPLE);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (ActivityResultManager.PUBLISH_TASK_SELECT_PEOPLE == resultCode) {
            switch (PubUtil.publish_task_select_people) {
                case 0:
                    initSelectedPeoples(excuterBuffer, mCreateOrderSubmitFollowTv);
                    excuters = getIds(excuterBuffer);

                    break;
                case 1:
                    initSelectedPeoples(leaderBuffer, mCreateOrderSubmitCheckTv);
                    checkUser = getIds(leaderBuffer);
                    break;
                case 2:
                    initSelectedPeoples(duplicateBuffer, mCreateOrderSubmitCopyTv);
                    duplicate = getIds(duplicateBuffer);
                default:
                    break;
            }
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
    public void startLoading(String tag) {

    }

    @Override
    public void stopLoading(String tag) {

    }

    @Override
    public void updateView(Object o, String tag) {
        stopMaterialProgressDialog();
        switch (tag) {
            case WorkOrderContract.CREATE_ORDER:
                showToast("创建成功");
                onActivityFinish();

                break;

            case WorkOrderContract.UPDATE_ORDER_CONTENT:
                WorkOrderUnreadBean workOrderUnreadBean = (WorkOrderUnreadBean) o;
                if (workOrderUnreadBean != null) {
                    showToast(workOrderUnreadBean.getMessage());
                    onActivityFinish();

                }

                break;


        }

    }

    public void onActivityFinish() {

        if (createOrderBean != null) {
            if (createOrderBean.isUpdateOrderState()) {
                Intent intent = new Intent();
                intent.putExtra("UPDATE_ORDER", "update_order");
                setResult(ActivityResultManager.CREATE_ORDER_COMPLETE, intent);

            } else {
                setResult(ActivityResultManager.CREATE_ORDER_COMPLETE);
            }
            onBackPressed();
        }
    }

    @Override
    public void onError(String tag) {
        stopMaterialProgressDialog();
        showToast(tag);

    }
}
