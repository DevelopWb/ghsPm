package com.ghs.ghspm.models.workOrder.createOrder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ghs.ghspm.R;
import com.ghs.ghspm.base.BaseActivity;
import com.ghs.ghspm.base.BasePresent;
import com.ghs.ghspm.base.displayPhotos.SelectPhotosToUploadFragment;
import com.ghs.ghspm.bean.CreateOrderBean;
import com.ghs.ghspm.bean.RoomSearchedBean;
import com.ghs.ghspm.models.task.waittodealtask.WaitForDealTaskFragment;
import com.ghs.ghspm.tools.ActivityResultManager;
import com.ghs.ghspm.tools.PickerManager;
import com.ghs.ghspm.tools.PubUtil;
import com.ghs.ghspm.tools.StrUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * created by tobato
 * created date 2019/7/17 18:08.
 * application  创建工单
 */
public class CreateOrderActivity extends BaseActivity implements View.OnClickListener {

    private SelectPhotosToUploadFragment selectPhotosFragment;
    /**
     * 报修类型
     */
    private TextView mCreateOrderRepairsTypeTv;
    /**
     * 请选择
     */
    private TextView mCreateOrderRepairsTypeContent;
    private LinearLayout mCreateOrderRepairsType;
    private LinearLayout mCreateOrderRepairsLabelLy;
    /**
     * 请输入
     */
    private EditText mCreateOrderName;
    /**
     * 请输入
     */
    private EditText mCreateOrderMobile;
    /**
     *
     */
    private TextView mCreateOrder1X;
    /**
     *
     */
    private TextView mCreateOrder2X;
    /**
     *
     */
    private TextView mCreateOrder3X;
    private String stringExtra;
    /**
     * 请选择
     */
    private TextView mCreateOrderRepairsLabelContent;
    private LinearLayout mCreateOrderSearch;
    /**
     * 请选择
     */
    private TextView mCreateOrderSearchContent;

    public static String CREATE_DATA = "create_data";
    private CreateOrderBean updataCreateOrderBean;
    private RoomSearchedBean.DataBean roomData;
    /**
     * 上一步
     */
    private TextView mOrderHeaderLeftTv;
    /**
     * 设置
     */
    private TextView mOrderHeaderTitleTv;
    /**
     * 设置
     */
    private TextView mOrderHeaderRightTv;
    private LinearLayout mTopLayoutLl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * 设置编辑内容默认数据
     *
     * @param createOrderBean
     */
    public void updataView(CreateOrderBean createOrderBean) {
        //编辑内容
        selectPhotosFragment.setContent(createOrderBean.getOrderContent());
        //编辑图片
        selectPhotosFragment.setAdapterData(createOrderBean.getOrderImage());
        //工单标签
        mCreateOrderRepairsLabelContent.setText(createOrderBean.getRepairsLabel());

        //联系人姓名
        mCreateOrderName.setText(createOrderBean.getOrderName());
        //联系人电话
        mCreateOrderMobile.setText(createOrderBean.getOrderMobile());
        //房间号
        mCreateOrderSearchContent.setText(createOrderBean.getOrderRoom());


        switch (createOrderBean.getOrderType()) {
            case "物业报修单":
                //报修类型
                mCreateOrderRepairsTypeContent.setText(createOrderBean.getRepairsType());

                stringExtra = WaitForDealTaskFragment.REPAIRS;
                if ("家庭报修".equals(createOrderBean.getRepairsType())) {
                    mCreateOrder1X.setVisibility(View.VISIBLE);
                    mCreateOrder2X.setVisibility(View.VISIBLE);
                    mCreateOrder3X.setVisibility(View.VISIBLE);

                } else {
                    mCreateOrder1X.setVisibility(View.INVISIBLE);
                    mCreateOrder2X.setVisibility(View.INVISIBLE);
                    mCreateOrder3X.setVisibility(View.INVISIBLE);
                }

                break;

            case "综合服务单":
                //服务单类型
                mCreateOrderRepairsTypeContent.setText(createOrderBean.getServeType());
                stringExtra = WaitForDealTaskFragment.SERVE;
                mCreateOrderRepairsTypeTv.setText("工单类型");
                mCreateOrderRepairsLabelLy.setVisibility(View.GONE);
                mCreateOrder3X.setVisibility(View.INVISIBLE);

                break;


        }

    }

    @Override
    public BasePresent creatPresenter() {
        return null;
    }

    @Override
    public void setLayout() {
        setContentView(R.layout.activity_create_order);
        initView();
        setResult(ActivityResultManager.UPDATE_ORDER_COMPLETE);
        Intent intent = getIntent();
        stringExtra = intent.getStringExtra(ActivityResultManager.CREATE_ORDER);
        if (StrUtils.isStringValueOk(stringExtra)) {
            if (stringExtra.equals(WaitForDealTaskFragment.REPAIRS)) {
                initTopLayout("物业报修单", "下一步", R.drawable.bg_gray_only_bottom_deeper_gray_shape_1px);

            } else if (stringExtra.equals(WaitForDealTaskFragment.SERVE)) {
                initTopLayout("综合服务单", "下一步", R.drawable.bg_gray_only_bottom_deeper_gray_shape_1px);
                mCreateOrderRepairsTypeTv.setText("工单类型");
                mCreateOrderRepairsLabelLy.setVisibility(View.GONE);
                mCreateOrder3X.setVisibility(View.INVISIBLE);
            }
        } else {
            initTopLayout("编辑工单", "下一步", R.drawable.bg_gray_only_bottom_deeper_gray_shape_1px);
            updataCreateOrderBean = (CreateOrderBean) intent.getSerializableExtra(PubUtil.UPADAT_ORDER);
            updataView(updataCreateOrderBean);
        }

    }

    /**
     * 初始化actionBar
     *
     * @param titleContent
     * @param rightTvContent
     * @param bgRes          背景色
     */
    public void initTopLayout(String titleContent, String rightTvContent, int bgRes) {
        mTopLayoutLl.setBackgroundResource(bgRes);
        mOrderHeaderTitleTv.setText(titleContent);
        mOrderHeaderRightTv.setText(rightTvContent);
        mOrderHeaderLeftTv.setText("取消");
        mOrderHeaderLeftTv.setTextColor(ContextCompat.getColor(this, R.color.text_cancel));
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

    public void submitOrder() {

        //下一步按钮点击事件
        Intent intent = new Intent(CreateOrderActivity.this, CreateOrderSubmitActivity.class);
        CreateOrderBean createOrderBean = new CreateOrderBean();
        if (updataCreateOrderBean != null) {
            //跟进人
            createOrderBean.setFollowPeople(updataCreateOrderBean.getFollowPeople());
            createOrderBean.setFollowPeopleId(updataCreateOrderBean.getFollowPeopleId());
            //验收人
            createOrderBean.setCheckPeople(updataCreateOrderBean.getCheckPeople());
            createOrderBean.setCheckPeopleId(updataCreateOrderBean.getCheckPeopleId());
            //抄送人
            createOrderBean.setCopyPeople(updataCreateOrderBean.getCopyPeople());
            createOrderBean.setCopyPeopleId(updataCreateOrderBean.getCopyPeopleId());
            //房间号
            createOrderBean.setOrderRoom(updataCreateOrderBean.getOrderRoom());
            createOrderBean.setOrderRoomId(updataCreateOrderBean.getOrderRoomId());
            //工单id
            createOrderBean.setOrderId(updataCreateOrderBean.getOrderId());
            //状态
            createOrderBean.setUpdateOrderState(updataCreateOrderBean.isUpdateOrderState());
            //上门时间
            createOrderBean.setOrderHopeGotoTime(updataCreateOrderBean.getOrderHopeGotoTime());
            //内容
            createOrderBean.setOrderContent(selectPhotosFragment.getContent());
            //图片
            createOrderBean.setOrderImage(selectPhotosFragment.getNetWorkImage() + selectPhotosFragment.uploadPhotosToOssForPath());

        }
        if (stringExtra.equals(WaitForDealTaskFragment.REPAIRS)) {
            createOrderBean.setOrderType("物业报修单");
            //物业报修单
            if (!StrUtils.isStringValueOk(selectPhotosFragment.getContent())) {
                showToast("请填写详细报修内容！");
                return;
            }
            createOrderBean.setOrderContent(selectPhotosFragment.getContent());
            //图片
            createOrderBean.setOrderImage(selectPhotosFragment.getNetWorkImage() + selectPhotosFragment.uploadPhotosToOssForPath());


            if ("请选择".equals(mCreateOrderRepairsTypeContent.getText().toString())) {
                showToast("请选择报修类型！");
                return;
            } else if ("家庭报修".equals(mCreateOrderRepairsTypeContent.getText().toString())) {

                createOrderBean.setRepairsType(mCreateOrderRepairsTypeContent.getText().toString());
                //标签
                if ("请选择".equals(mCreateOrderRepairsLabelContent.getText().toString())) {
                    showToast("请选择报修标签！");
                    return;
                }
                createOrderBean.setRepairsLabel(mCreateOrderRepairsLabelContent.getText().toString());
                //联系人
                if (!StrUtils.isStringValueOk(mCreateOrderName.getText().toString().trim())) {
                    showToast("请输入联系人姓名！");
                    return;
                }
                createOrderBean.setOrderName(mCreateOrderName.getText().toString().trim());
                //联系电话
                if (!StrUtils.isStringValueOk(mCreateOrderMobile.getText().toString().trim())) {
                    showToast("请输入联系人电话！");
                    return;
                }
                //开放手机号验证
//                if (!PubUtil.isMobileNO(mCreateOrderMobile.getText().toString().trim())) {
//                    showToast("手机号格式不正确！");
//                    return;
//                }
                createOrderBean.setOrderMobile(mCreateOrderMobile.getText().toString().trim());

                if ("请选择".equals(mCreateOrderSearchContent.getText().toString())) {
                    showToast("请选择房间号！");
                    return;
                }
                createOrderBean.setOrderRoom(mCreateOrderSearchContent.getText().toString().trim());
                createOrderBean.setOrderRoomId(roomData == null ? updataCreateOrderBean.getOrderRoomId() : roomData.getId() + "");

                intent.putExtra(CREATE_DATA, createOrderBean);
                startActivityForResult(intent, ActivityResultManager.CREATE_ORDER_COMPLETE);


            } else if ("公共报修".equals(mCreateOrderRepairsTypeContent.getText().toString())) {
                //报修类型
                createOrderBean.setRepairsType(mCreateOrderRepairsTypeContent.getText().toString());
                //标签
                if ("请选择".equals(mCreateOrderRepairsLabelContent.getText().toString())) {
                    showToast("请选择报修标签！");
                    return;
                }
                //报修标签
                createOrderBean.setRepairsLabel(mCreateOrderRepairsLabelContent.getText().toString());
                //联系人姓名
                createOrderBean.setOrderName(mCreateOrderName.getText().toString().trim());
                //联系人电话
                createOrderBean.setOrderMobile(mCreateOrderMobile.getText().toString().trim());
                //房间ID
                if (!"请选择".equals(mCreateOrderSearchContent.getText().toString())) {
                    createOrderBean.setOrderRoom(mCreateOrderSearchContent.getText().toString());
                    createOrderBean.setOrderRoomId(roomData == null ? updataCreateOrderBean.getOrderRoomId() : roomData.getId() + "");
                }

                intent.putExtra(CREATE_DATA, createOrderBean);
                startActivityForResult(intent, ActivityResultManager.CREATE_ORDER_COMPLETE);

            }

        } else {
            //综合服务单
            createOrderBean.setOrderType("综合服务单");

            if (!StrUtils.isStringValueOk(selectPhotosFragment.getContent())) {
                showToast("请填写详细工单内容！");
                return;
            }
            createOrderBean.setOrderContent(selectPhotosFragment.getContent());
            //图片
            createOrderBean.setOrderImage(selectPhotosFragment.getNetWorkImage() + selectPhotosFragment.uploadPhotosToOssForPath());


            if ("请选择".equals(mCreateOrderRepairsTypeContent.getText().toString())) {
                showToast("请选择工单类型！");
                return;
            }
            //工单类型
            createOrderBean.setServeType(mCreateOrderRepairsTypeContent.getText().toString());
            //联系人
            if (!StrUtils.isStringValueOk(mCreateOrderName.getText().toString().trim())) {
                showToast("请输入联系人姓名！");
                return;
            }
            createOrderBean.setOrderName(mCreateOrderName.getText().toString().trim());
            //联系电话
            if (!StrUtils.isStringValueOk(mCreateOrderMobile.getText().toString().trim())) {
                showToast("请输入联系人电话！");
                return;
            }
            createOrderBean.setOrderMobile(mCreateOrderMobile.getText().toString().trim());
            //房间ID
            if (!"请选择".equals(mCreateOrderSearchContent.getText().toString())) {
                createOrderBean.setOrderRoom(mCreateOrderSearchContent.getText().toString());
                createOrderBean.setOrderRoomId(roomData == null ? updataCreateOrderBean.getOrderRoomId() : roomData.getId() + "");
            }
            intent.putExtra(CREATE_DATA, createOrderBean);
            startActivityForResult(intent, ActivityResultManager.CREATE_ORDER_COMPLETE);

        }

    }

    private void initView() {
        selectPhotosFragment = (SelectPhotosToUploadFragment) getSupportFragmentManager().findFragmentById(R.id.create_order_photos_fg);
        selectPhotosFragment.setSpanCount(5, 6, 20, false);
        selectPhotosFragment.setBackGround(R.drawable.bg_white_only_bottom_gray_shape_1px);
        mCreateOrderRepairsTypeTv = (TextView) findViewById(R.id.create_order_repairs_type_tv);
        mCreateOrderRepairsTypeContent = (TextView) findViewById(R.id.create_order_repairs_type_content);
        mCreateOrderRepairsType = (LinearLayout) findViewById(R.id.create_order_repairs_type);
        mCreateOrderRepairsType.setOnClickListener(this);
        mCreateOrderRepairsLabelLy = (LinearLayout) findViewById(R.id.create_order_repairs_label);
        mCreateOrderRepairsLabelLy.setOnClickListener(this);
        mCreateOrderName = (EditText) findViewById(R.id.create_order_name);
        mCreateOrderMobile = (EditText) findViewById(R.id.service_amount_et);
        mCreateOrder1X = (TextView) findViewById(R.id.create_order_1_x);
        mCreateOrder2X = (TextView) findViewById(R.id.create_order_2_x);
        mCreateOrder3X = (TextView) findViewById(R.id.create_order_3_x);
        mCreateOrderRepairsLabelContent = (TextView) findViewById(R.id.create_order_repairs_label_content);
        mCreateOrderSearch = (LinearLayout) findViewById(R.id.create_order_search);
        mCreateOrderSearch.setOnClickListener(this);
        mCreateOrderSearchContent = (TextView) findViewById(R.id.create_order_search_content);
        mOrderHeaderLeftTv = (TextView) findViewById(R.id.order_header_left_iv);
        mOrderHeaderLeftTv.setOnClickListener(this);
        mOrderHeaderTitleTv = (TextView) findViewById(R.id.order_header_title_tv);
        mOrderHeaderRightTv = (TextView) findViewById(R.id.order_header_right_tv);
        mOrderHeaderRightTv.setOnClickListener(this);
        mTopLayoutLl = (LinearLayout) findViewById(R.id.creat_order_actionbar_top_ll);
    }

    //展示报修类型
    public void selectCreateType(final List<String> dataList) {

        PickerManager.getInstance().showOptionPicker(CreateOrderActivity.this, dataList, new PickerManager.OnOptionPickerSelectedListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                mCreateOrderRepairsTypeContent.setText(dataList.get(options1));
                if ("公共报修".equals(dataList.get(options1))) {
                    mCreateOrder1X.setVisibility(View.INVISIBLE);
                    mCreateOrder2X.setVisibility(View.INVISIBLE);
                    mCreateOrder3X.setVisibility(View.INVISIBLE);
                } else if (("家庭报修").equals(dataList.get(options1))) {
                    mCreateOrder1X.setVisibility(View.VISIBLE);
                    mCreateOrder2X.setVisibility(View.VISIBLE);
                    mCreateOrder3X.setVisibility(View.VISIBLE);
                }
                mCreateOrderRepairsLabelContent.setText("请选择");
            }
        });

    }

    //标签展示
    public void selectCreateLabel(final List<String> dataList) {

        PickerManager.getInstance().showOptionPicker(CreateOrderActivity.this, dataList, new PickerManager.OnOptionPickerSelectedListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                mCreateOrderRepairsLabelContent.setText(dataList.get(options1));
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.create_order_repairs_type:
                hideKeyboard(selectPhotosFragment.getView().findViewById(R.id.select_photos_content_et));
                //报修类型
                List<String> typeList = new ArrayList<>();
                if (stringExtra.equals(WaitForDealTaskFragment.REPAIRS)) {
                    typeList.add("家庭报修");
                    typeList.add("公共报修");
                } else {
                    typeList.add("报事");
                    typeList.add("建议");
                    typeList.add("投诉");
                    typeList.add("其他");
                }
                selectCreateType(typeList);

                break;
            //公共照明维修、给水设施维修、电梯维修、消防设施维修、
            // 弱电设施维修、健身设施维修、绿化设施维修、其他问题
            case R.id.create_order_repairs_label:
                //选择标签
                selectlabel();
                break;
            case R.id.create_order_search:
                //搜索房间
                Intent intent = new Intent(CreateOrderActivity.this, SearchRoomActivity.class);
                startActivityForResult(intent, ActivityResultManager.CREATE_ORDER_SEACHROOM);

                break;
            case R.id.order_header_left_iv:
                onBackPressed();
                break;
            case R.id.order_header_right_tv:
                //下一步
                submitOrder();
                break;
        }
    }

    public void selectlabel() {
        //报修标签
        List<String> labelList = new ArrayList<>();

        if ("家庭报修".equals(mCreateOrderRepairsTypeContent.getText().toString())) {
            labelList.add("空调维修");
            labelList.add("热水器维修");
            labelList.add("网络维修");
            labelList.add("门窗维修");
            labelList.add("电路&灯具维修");
            labelList.add("马桶&地漏堵塞");
            labelList.add("墙面渗漏&脱落");
            labelList.add("其他问题");

        } else if ("公共报修".equals(mCreateOrderRepairsTypeContent.getText().toString())) {
            labelList.add("公共照明维修");
            labelList.add("给水设施维修");
            labelList.add("电梯维修");
            labelList.add("消防设施维修");
            labelList.add("弱电设施维修");
            labelList.add("健身设施维修");
            labelList.add("绿化设施维修");
            labelList.add("其他问题");

        } else {
            showToast("请选择报修类型");
            return;
        }
        selectCreateLabel(labelList);


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ActivityResultManager.CREATE_ORDER_SEACHROOM) {
            if (data != null) {
                roomData = data.getParcelableExtra(INTENT_KEY);
                if (roomData != null && roomData.getRoomNumber() != null) {
                    mCreateOrderSearchContent.setText(roomData.getPortionName()+"-"+roomData.getTowerNumber()+"-"+roomData.getCellName()+"-"+roomData.getRoomNumber());
                }
            }
        } else if (resultCode == ActivityResultManager.CREATE_ORDER_COMPLETE) {
            onBackPressed();
        }

    }


    @Override
    public void onBackPressed() {
        if (mOrderHeaderTitleTv != null) {
            hideKeyboard(mOrderHeaderTitleTv);
        }
        super.onBackPressed();
    }
}
