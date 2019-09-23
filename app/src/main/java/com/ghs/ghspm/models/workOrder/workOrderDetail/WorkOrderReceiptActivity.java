package com.ghs.ghspm.models.workOrder.workOrderDetail;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.allen.library.SuperTextView;
import com.bumptech.glide.Glide;
import com.ghs.ghspm.R;
import com.ghs.ghspm.base.BaseActivity;
import com.ghs.ghspm.base.network.RequestStatus;
import com.ghs.ghspm.bean.WorkOrderDetailBean;
import com.ghs.ghspm.customView.GestureSignatureView;
import com.ghs.ghspm.models.oss.OssService;
import com.ghs.ghspm.models.oss.OssUploadManager;
import com.ghs.ghspm.models.workOrder.WorkOrderContract;
import com.ghs.ghspm.models.workOrder.WorkOrderPresent;
import com.ghs.ghspm.tools.CalendarUtil;
import com.ghs.ghspm.tools.Contract;
import com.ghs.ghspm.tools.FileUtil;
import com.ghs.ghspm.base.network.NetWorkUtil;
import com.ghs.ghspm.tools.PickerManager;
import com.ghs.ghspm.tools.StrUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * created by tobato
 * created date 2019/7/9 10:23.
 * application   工单回执
 */
public class WorkOrderReceiptActivity extends BaseActivity<WorkOrderContract.IWorkOrderView, WorkOrderPresent> implements WorkOrderContract.IWorkOrderView, View.OnClickListener, RequestStatus {

    //签名图片存储路径
//    public static String signaturePath = FileUtil.getHeadPicDir(WorkOrderReceiptActivity.this) + "signature.png";

    private SuperTextView mArriveTimeStv;//到达时间
    private SuperTextView mServiceCompleteTimeStv;
    private SuperTextView mRepairUsedTimeStv;
    private EditText mServiceDesEt;
    private EditText mServiceMaterialEt;
    private EditText mServiceNoteEt;
    /**
     * 请输入服务的金额
     */
    private EditText mServiceAmountEt;
    private ImageView mSignNameTagIv;
    /**
     * 轻触签名
     */
    private TextView mSignNameNoticeTv;
    private LinearLayout mSignNameLl;
    /**
     * 保存
     */
    private TextView mReceiptSaveTv;
    private SuperTextView mServiceTypeStv;
    private LinearLayout mServiceMoneyLy;
    private BottomSheetDialog bottomSheetDialog;
    private GestureSignatureView gsv_signature;
    private ImageView mSignRedactImg;
    private SuperTextView mSignResign;
    private OssService ossService;
    private OssUploadManager ossUploadManager;
    private WorkOrderDetailBean.DataBean.ServiceWorkDOBean serviceWorkDOBean;
    private View repairUsedTimeDivider;
    private View mServiceTypeDividerV;
    private boolean SINGE_STATE = false;

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
        setContentView(R.layout.activity_work_order_receipt);
        initView();
        initActionBar("工单回执", null, R.drawable.bg_gray_only_bottom_deeper_gray_shape_1px);
    }

    @Override
    public void initLayoutView() {

    }

    @Override
    public void getDate() {
        ossUploadManager = OssUploadManager.getInstance(this);
        serviceWorkDOBean = (WorkOrderDetailBean.DataBean.ServiceWorkDOBean) getIntentParcelableData();
        if (serviceWorkDOBean != null) {
            String arriveTime = serviceWorkDOBean.getArriveTime();
            if (StrUtils.isStringValueOk(arriveTime)) {
                arriveTime = CalendarUtil.getTimeFromStringTime("yy年MM月dd日 HH时mm分", "yyyy-MM-dd HH:mm:ss", arriveTime);
                mArriveTimeStv.setRightString(arriveTime).setRightTextColor(ContextCompat.getColor(this, R.color.app_gray));
            }
            String finishTime = serviceWorkDOBean.getFinishJobTime();
            if (StrUtils.isStringValueOk(finishTime)) {
                finishTime = CalendarUtil.getTimeFromStringTime("yy年MM月dd日 HH时mm分", "yyyy-MM-dd HH:mm:ss", finishTime);
                mServiceCompleteTimeStv.setRightString(finishTime).setRightTextColor(ContextCompat.getColor(this, R.color.app_gray));
            }
            if (StrUtils.isStringValueOk(arriveTime) && StrUtils.isStringValueOk(finishTime)) {
                setViewsVisible(mRepairUsedTimeStv, repairUsedTimeDivider);
                String usedTime = serviceWorkDOBean.getRepairUseTime();
                if (StrUtils.isStringValueOk(usedTime)) {
                    mRepairUsedTimeStv.setRightString(usedTime).setRightTextColor(ContextCompat.getColor(this, R.color.app_gray));
                }
            } else {
                setViewsInvisible(true, mRepairUsedTimeStv, repairUsedTimeDivider);

            }

            String repairDes = serviceWorkDOBean.getRepairMsg();
            if (StrUtils.isStringValueOk(repairDes)) {
                mServiceDesEt.setText(repairDes);
            }
            String usedMaterial = serviceWorkDOBean.getUseMaterial();
            if (StrUtils.isStringValueOk(usedMaterial)) {
                mServiceMaterialEt.setText(usedMaterial);
            }
            String repairRemark = serviceWorkDOBean.getRemark();
            if (StrUtils.isStringValueOk(repairRemark)) {
                mServiceNoteEt.setText(repairRemark);
            }
            String serviceType = 0 == serviceWorkDOBean.getPaid() ? "免费" : "有偿服务";
            if (StrUtils.isStringValueOk(serviceType)) {
                if ("有偿服务".equals(serviceType)) {
                    setViewsVisible(mServiceMoneyLy, mServiceTypeDividerV);
                    mServiceAmountEt.setText(String.valueOf(serviceWorkDOBean.getRepairMoney()));
                } else if ("免费".equals(serviceType)) {
                    setViewsInvisible(true, mServiceMoneyLy, mServiceTypeDividerV);
                }
            }
            String signPic = serviceWorkDOBean.getOwnerSignUrl();
            if (StrUtils.isStringValueOk(signPic)) {
                setViewsVisible(mSignRedactImg);
                mSignResign.getRightTextView().setVisibility(View.VISIBLE);
                setViewsInvisible(true, mSignNameTagIv, mSignNameNoticeTv);
                Glide.with(this).load(Contract.ImageBasePath + signPic)
                        .skipMemoryCache(false)
                        .into(mSignRedactImg);
            } else {
                mSignResign.getRightTextView().setVisibility(View.GONE);
                setViewsVisible(mSignNameTagIv, mSignNameNoticeTv);
                setViewsInvisible(true, mSignRedactImg);

            }

        }


    }

    @Override
    public void actionBarRightTvOnClick() {

    }


    /**
     * true到场时间
     * fase完成时间
     *
     * @param type
     */
    public void showSerViceOutTime(final boolean type) {
        PickerManager.getInstance().showTimePickerView(this, new boolean[]{true, true, true, true, true, false}, "", new PickerManager.OnTimePickerTimeSelectedListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onTimeSelect(Date date, View v) {
                if (type) {
                    mArriveTimeStv.getRightTextView().setText(CalendarUtil.getTimeFromDate("yy年MM月dd日 HH时mm分", date));
                    mServiceCompleteTimeStv.getRightTextView().setText("请选择");
                    setViewsInvisible(true, mRepairUsedTimeStv, repairUsedTimeDivider);
                } else {

                    if (CalendarUtil.compareDateSize("yy年MM月dd日 HH时mm分", CalendarUtil.getTimeFromDate("yy年MM月dd日 HH时mm分", date), mArriveTimeStv.getRightTextView().getText().toString())) {
                        mServiceCompleteTimeStv.getRightTextView().setText(CalendarUtil.getTimeFromDate("yy年MM月dd日 HH时mm分", date));
                        setViewsVisible(mRepairUsedTimeStv, repairUsedTimeDivider);
                        String dd = CalendarUtil.dateDiff(mArriveTimeStv.getRightTextView().getText().toString(), mServiceCompleteTimeStv.getRightTextView().getText().toString(), "yy年MM月dd日 HH时mm分");
                        mRepairUsedTimeStv.setRightString(dd);
                    } else {
                        setViewsInvisible(true, mRepairUsedTimeStv, repairUsedTimeDivider);
                        mServiceCompleteTimeStv.getRightTextView().setText("请选择");
                        showToast("完工时间小于到场时间！");

                    }
                }

            }
        });


    }

    @Override
    public void startLoading(String tag) {

    }

    @Override
    public void stopLoading(String tag) {

    }

    @Override
    public void updateView(Object o, String tag) {
        showToast("保存成功");
        finish();
    }

    @Override
    public void onStart(String tag) {

    }

    @Override
    public void onSuccess(Object o, String tag) {


    }

    @Override
    public void onError(String tag) {

    }

    private void initView() {
        mArriveTimeStv = (SuperTextView) findViewById(R.id.service_arrive_time);
        mArriveTimeStv.setOnClickListener(this);
        mServiceCompleteTimeStv = (SuperTextView) findViewById(R.id.service_complete_time);
        mServiceCompleteTimeStv.setOnClickListener(this);
        mRepairUsedTimeStv = (SuperTextView) findViewById(R.id.repair_used_time);
        mRepairUsedTimeStv.setOnClickListener(this);
        repairUsedTimeDivider = findViewById(R.id.service_complete_time_divider_v);
        mServiceDesEt = (EditText) findViewById(R.id.service_des_et);
        mServiceMaterialEt = (EditText) findViewById(R.id.service_material_et);
        mServiceNoteEt = (EditText) findViewById(R.id.service_note_et);
        mServiceAmountEt = (EditText) findViewById(R.id.service_amount_et);
        mSignNameTagIv = (ImageView) findViewById(R.id.sign_name_tag_iv);
        mSignNameNoticeTv = (TextView) findViewById(R.id.sign_name_notice_tv);
        mSignNameLl = (LinearLayout) findViewById(R.id.sign_name_ll);
        mSignNameLl.setOnClickListener(this);
        mReceiptSaveTv = (TextView) findViewById(R.id.receipt_save_tv);
        mReceiptSaveTv.setOnClickListener(this);

        mServiceTypeStv = (SuperTextView) findViewById(R.id.service_type_stv);
        mServiceTypeStv.setOnClickListener(this);
        mServiceMoneyLy = (LinearLayout) findViewById(R.id.service_money_ly);


        mSignRedactImg = (ImageView) findViewById(R.id.sign_redact_img);
        mSignResign = (SuperTextView) findViewById(R.id.sign_resign);
        mSignResign.setRightTvClickListener(new SuperTextView.OnRightTvClickListener() {
            @Override
            public void onClickListener() {
                //重签
                showSignatureView();

            }
        });
        mServiceTypeDividerV = (View) findViewById(R.id.service_type_divider_v);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.service_arrive_time:
                showSerViceOutTime(true);
                break;
            case R.id.service_complete_time:
                showSerViceOutTime(false);
                break;
            case R.id.receipt_save_tv:
                    String uploadStr = ossUploadManager.uploadPicInfo(FileUtil.getHeadPicDir(this) + FileUtil.SIGN_PIC_NAME);
                    Map<String, String> map = getUploadOptions();
                    if (SINGE_STATE) {
                        map.put("ownerSignUrl", uploadStr);
                    }
                    getPresenter().updateReceipt(mUserInfoUtil.getUserId(), serviceWorkDOBean.getId(), map, "");


                break;
            case R.id.service_type_stv:
                //服务类型
                showServiceType();

                break;
            //签名
            case R.id.sign_name_ll:
                showSignatureView();

                break;

            case R.id.signature_view_save:


                if (gsv_signature.getTouched()) {

                    try {
                        //保存到本地
                        gsv_signature.save(FileUtil.getHeadPicDir(WorkOrderReceiptActivity.this) + FileUtil.SIGN_PIC_NAME);
                        Bitmap bitmap1 = getLoacalBitmap(FileUtil.getHeadPicDir(WorkOrderReceiptActivity.this) + FileUtil.SIGN_PIC_NAME); //从本地取图片(在cdcard中获取)  //
                        mSignRedactImg.setImageBitmap(bitmap1); //设置Bitmap
                        SINGE_STATE = true;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    mSignNameTagIv.setVisibility(View.GONE);
                    mSignNameNoticeTv.setVisibility(View.GONE);
                    mSignRedactImg.setVisibility(View.VISIBLE);
                    bottomSheetDialog.dismiss();
                    mSignResign.getRightTextView().setVisibility(View.VISIBLE);
                } else {
                    showToast("请签名！");
                }

                break;

            case R.id.signature_view_rewrite:
                gsv_signature.clear();
                break;
            case R.id.signature_view_cancel:
                gsv_signature.clear();
                bottomSheetDialog.dismiss();
                break;


        }
    }


    /**
     * 获取bitmap
     *
     * @param url
     * @return
     */
    private Bitmap getLoacalBitmap(String url) {
        try {
            FileInputStream fis = new FileInputStream(url);
            return BitmapFactory.decodeStream(fis);  ///把流转化为Bitmap图片

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * paid   服务类别(1:有偿 0：无偿)
     *
     * @return
     */
    private Map<String, String> getUploadOptions() {

        Map<String, String> map = new HashMap<>();
        //操作人姓名
        map.put("userName", mUserInfoUtil.getUserName());
        //到达时间
        String arriveTime = mArriveTimeStv.getRightString();
        if (!"请选择".equals(arriveTime)) {
            arriveTime = CalendarUtil.getTimeFromStringTime("yyyy-MM-dd HH:mm", "yy年MM月dd日 HH时mm分", arriveTime);
        }
        map.put("arriveTime", arriveTime);
        //完工时间
        String finishTime = mServiceCompleteTimeStv.getRightString();
        if (!"请选择".equals(finishTime)) {
            finishTime = CalendarUtil.getTimeFromStringTime("yyyy-MM-dd HH:mm", "yy年MM月dd日 HH时mm分", finishTime);
        }
        map.put("finishJobTime", finishTime);
        //维修大概耗时
        map.put("repairUseTime", mRepairUsedTimeStv.getRightString());
        //维修情况
        map.put("repairMsg", mServiceDesEt.getText().toString().trim());
        //使用材料
        map.put("useMaterial", mServiceMaterialEt.getText().toString().trim());
        //备注
        map.put("remark", mServiceNoteEt.getText().toString().trim());
        if ("有偿服务".equals(mServiceTypeStv.getRightString())) {
            //服务类别
            map.put("paid", "1");
            //服务金额
            map.put("repairMoney", mServiceAmountEt.getText().toString().trim());
        } else if ("免费".equals(mServiceTypeStv.getRightString())) {
            //服务类别
            map.put("paid", "0");
        }
        return map;
    }

    /**
     * 展示签名的画板
     */
    private void showSignatureView() {

        bottomSheetDialog = new BottomSheetDialog(this);
        View view = LayoutInflater.from(this).inflate(R.layout.signature_view_layout, null);
        view.findViewById(R.id.signature_view_save).setOnClickListener(this);
        view.findViewById(R.id.signature_view_rewrite).setOnClickListener(this);
        view.findViewById(R.id.signature_view_cancel).setOnClickListener(this);
        //签名画板
        gsv_signature = view.findViewById(R.id.gsv_signature);

        bottomSheetDialog.setContentView(view);
        bottomSheetDialog.setCanceledOnTouchOutside(false);
        bottomSheetDialog.setCancelable(false);
        bottomSheetDialog.show();


    }


    /**
     * 服务类型
     */
    public void showServiceType() {
        final String[] typeList = {"有偿服务", "免费"};
        PickerManager.getInstance().showOptionPicker(this, Arrays.asList(typeList), new PickerManager.OnOptionPickerSelectedListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                mServiceTypeStv.setRightString(typeList[options1]);
                if ("有偿服务".equals(mServiceTypeStv.getRightString())) {
                    setViewsVisible(mServiceMoneyLy, mServiceTypeDividerV);
                } else if ("免费".equals(mServiceTypeStv.getRightString())) {
                    setViewsInvisible(true, mServiceMoneyLy, mServiceTypeDividerV);
                }

            }
        });

    }

}