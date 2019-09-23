package com.ghs.ghspm.models.workdesk.patrolForm.formStatusInfo;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.util.ArrayMap;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.ghs.ghspm.R;
import com.ghs.ghspm.base.BaseActivity;
import com.ghs.ghspm.bean.PatrolTaskFormListBean;
import com.ghs.ghspm.models.workdesk.patrolForm.PatrolCheckUpdateContract;
import com.ghs.ghspm.models.workdesk.patrolForm.PatrolCheckUpdatePresent;
import com.ghs.ghspm.models.workdesk.patrolForm.creatPatrolForm.PatrolTaskFormRecordActivity;
import com.ghs.ghspm.base.network.NetWorkUtil;
import com.ghs.ghspm.tools.PickerManager;
import com.ghs.ghspm.tools.PubUtil;
import com.ghs.ghspm.tools.StrUtils;
import com.ghs.ghspm.tools.UserInfoUtil;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

/**
 * created by tobato
 * created date 2019/4/24 15:17.
 * application  修改表单的基本信息 包括头信息 尾信息等。涉及提取新表单和编辑表单
 */
public class ModifyPatrolFormInfoActivity extends BaseActivity<PatrolCheckUpdateContract.IPatrolCheckUpdateView, PatrolCheckUpdatePresent> implements PatrolCheckUpdateContract.IPatrolCheckUpdateView, View.OnClickListener, View.OnFocusChangeListener {

    /**
     * 取消
     */
    private TextView mPickedFormCancelTv;
    /**
     * 确定
     */
    private TextView mPickedFormConfirmTv;
    /**
     * 请输入
     */
    private EditText mPickedFormContentEt;
    /**
     * 请选择
     */
    private TextView mPickedFormSelectTv;
    private ConstraintLayout mPickedFormTimeCl;
    /**
     * 请输入
     */
    private EditText mPickedFormHeadContent1Et;
    /**
     * 请输入
     */
    private EditText mPickedFormHeadContent2Et;
    /**
     * 请输入
     */
    private EditText mPickedFormHeadContent3Et;
    /**
     * 请输入
     */
    private EditText mPickedFormHeadContent4Et;
    /**
     * 请输入
     */
    private EditText mPickedFormTailContent1Et;
    /**
     * 请输入
     */
    private EditText mPickedFormTailContent2Et;
    /**
     * 请输入
     */
    private EditText mPickedFormTailContent3Et;
    /**
     * 请输入
     */
    private EditText mPickedFormTailContent4Et;
    private ConstraintLayout mPickedFormHead1Cl;
    private ConstraintLayout mPickedFormHead2Cl;
    private ConstraintLayout mPickedFormHead3Cl;
    private ConstraintLayout mPickedFormHead4Cl;
    /**
     * 请输入
     */
    private TextView mPickedFormDesContentTv;
    private ConstraintLayout mPickedFormDesCl;
    private ConstraintLayout mPickedFormTail1Cl;
    private ConstraintLayout mPickedFormTail2Cl;
    private ConstraintLayout mPickedFormTail3Cl;
    private ConstraintLayout mPickedFormTail4Cl;
    /**
     * 请输入
     */
    private TextView mPickedFormNumValueTv;
    private ConstraintLayout mPickedFormNumCl;
    /**
     * 表头1
     */
    private TextView mPickedFormHeadTitle1Tv;
    /**
     * 表头2
     */
    private TextView mPickedFormHeadTitle2Tv;
    /**
     * 表头3
     */
    private TextView mPickedFormHeadTitle3Tv;
    /**
     * 表头4
     */
    private TextView mPickedFormHeadTitle4Tv;
    /**
     * 表尾1
     */
    private TextView mPickedFormTailTitle1Tv;
    /**
     * 表尾2
     */
    private TextView mPickedFormTailTitle2Tv;
    /**
     * 表尾3
     */
    private TextView mPickedFormTailTitle3Tv;
    /**
     * 表尾4
     */
    private TextView mPickedFormTailTitle4Tv;
    /**
     * 表头信息
     */
    private TextView mPickedFormHeadGpTv;
    /**
     * 表尾信息
     */
    private TextView mPickedFormTailGpTv;
    private PatrolTaskFormListBean.DataBean patrolDealBean;
    private String time;
    public String showdata;
    /**
     * 提取新表单
     */
    private TextView mPatrolFormInfoTitleTv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public PatrolCheckUpdatePresent creatPresenter() {
        return new PatrolCheckUpdatePresent();
    }

    @Override
    public void setLayout() {
        setContentView(R.layout.pick_up_form);

    }

    @Override
    public void initLayoutView() {
        initView();

    }

    @Override
    public void getDate() {

        switch (PubUtil.MODIFY_PATROL_FORM_INFO) {
            case 0:
                //从模板信息预览那个界面跳转过来的
                if (PubUtil.PATROL_FORM_LIST_DATABEAN != null) {
                    mPatrolFormInfoTitleTv.setText("提取新表单");
                    String showDate = PubUtil.PATROL_FORM_LIST_DATABEAN.getShowDate();
                    String head1 = PubUtil.PATROL_FORM_LIST_DATABEAN.getHeaderTitle1();
                    String head2 = PubUtil.PATROL_FORM_LIST_DATABEAN.getHeaderTitle2();
                    String head3 = PubUtil.PATROL_FORM_LIST_DATABEAN.getHeaderTitle3();
                    String head4 = PubUtil.PATROL_FORM_LIST_DATABEAN.getHeaderTitle4();
                    initViewStatus(mPickedFormTimeCl, null, showDate, null, null);
                    initViewStatus(mPickedFormHead1Cl, mPickedFormHeadTitle1Tv, head1, null, null);
                    initViewStatus(mPickedFormHead2Cl, mPickedFormHeadTitle2Tv, head2, null, null);
                    initViewStatus(mPickedFormHead3Cl, mPickedFormHeadTitle3Tv, head3, null, null);
                    initViewStatus(mPickedFormHead4Cl, mPickedFormHeadTitle4Tv, head4, null, null);
                    if (!StrUtils.isStringValueOk(showDate) && !StrUtils.isStringValueOk(head1) && !StrUtils.isStringValueOk(head2)
                            && !StrUtils.isStringValueOk(head3) && !StrUtils.isStringValueOk(head4)) {
                        mPickedFormHeadGpTv.setVisibility(View.GONE);
                    } else {
                        mPickedFormHeadGpTv.setVisibility(View.VISIBLE);
                    }
                    String des = PubUtil.PATROL_FORM_LIST_DATABEAN.getUsage();
                    String tail1 = PubUtil.PATROL_FORM_LIST_DATABEAN.getFooterTitle1();
                    String tail2 = PubUtil.PATROL_FORM_LIST_DATABEAN.getFooterTitle2();
                    String tail3 = PubUtil.PATROL_FORM_LIST_DATABEAN.getFooterTitle3();
                    String tail4 = PubUtil.PATROL_FORM_LIST_DATABEAN.getFooterTitle4();
                    String formNum = PubUtil.PATROL_FORM_LIST_DATABEAN.getShowCode();

                    initViewStatus(mPickedFormDesCl, mPickedFormDesContentTv, des, null, null);
                    initViewStatus(mPickedFormTail1Cl, mPickedFormTailTitle1Tv, tail1, null, null);
                    initViewStatus(mPickedFormTail2Cl, mPickedFormTailTitle2Tv, tail2, null, null);
                    initViewStatus(mPickedFormTail3Cl, mPickedFormTailTitle3Tv, tail3, null, null);
                    initViewStatus(mPickedFormTail4Cl, mPickedFormTailTitle4Tv, tail4, null, null);
                    initViewStatus(mPickedFormNumCl, mPickedFormNumValueTv, formNum, null, null);
                    if (!StrUtils.isStringValueOk(des) && !StrUtils.isStringValueOk(tail1) && !StrUtils.isStringValueOk(tail2)
                            && !StrUtils.isStringValueOk(tail3) && !StrUtils.isStringValueOk(tail4) && !StrUtils.isStringValueOk(formNum)) {
                        mPickedFormTailGpTv.setVisibility(View.GONE);
                    } else {
                        mPickedFormTailGpTv.setVisibility(View.VISIBLE);
                    }
                }
                break;
            case 1:
                Intent intent = getIntent();
                if (intent != null) {
                    //从表单任务记录那个类跳转过来的
                    mPatrolFormInfoTitleTv.setText("编辑表单信息");

                    patrolDealBean = intent.getParcelableExtra(PatrolCheckUpdateContract.PATROL_FORM_TASK_DEAL_BEAN);
                    if (patrolDealBean != null) {
                        String formName = patrolDealBean.getTaskName();
                        mPickedFormContentEt.setText(formName);
                        String showDate = PubUtil.PATROL_FORM_LIST_DATABEAN.getShowDate();
                        String head1 = PubUtil.PATROL_FORM_LIST_DATABEAN.getHeaderTitle1();
                        String head2 = PubUtil.PATROL_FORM_LIST_DATABEAN.getHeaderTitle2();
                        String head3 = PubUtil.PATROL_FORM_LIST_DATABEAN.getHeaderTitle3();
                        String head4 = PubUtil.PATROL_FORM_LIST_DATABEAN.getHeaderTitle4();
                        time = patrolDealBean.getShowDateValue();
                        String headValue1 = patrolDealBean.getHeaderTitle1Value();
                        String headValue2 = patrolDealBean.getHeaderTitle2Value();
                        String headValue3 = patrolDealBean.getHeaderTitle3Value();
                        String headValue4 = patrolDealBean.getHeaderTitle4Value();
                        initViewStatus(mPickedFormTimeCl, null, showDate, mPickedFormSelectTv, time);
                        initViewStatus(mPickedFormHead1Cl, mPickedFormHeadTitle1Tv, head1, mPickedFormHeadContent1Et, headValue1);
                        initViewStatus(mPickedFormHead2Cl, mPickedFormHeadTitle2Tv, head2, mPickedFormHeadContent2Et, headValue2);
                        initViewStatus(mPickedFormHead3Cl, mPickedFormHeadTitle3Tv, head3, mPickedFormHeadContent3Et, headValue3);
                        initViewStatus(mPickedFormHead4Cl, mPickedFormHeadTitle4Tv, head4, mPickedFormHeadContent4Et, headValue4);
                        if (!StrUtils.isStringValueOk(showDate) && !StrUtils.isStringValueOk(head1) && !StrUtils.isStringValueOk(head2)
                                && !StrUtils.isStringValueOk(head3) && !StrUtils.isStringValueOk(head4)) {
                            mPickedFormHeadGpTv.setVisibility(View.GONE);
                        } else {
                            mPickedFormHeadGpTv.setVisibility(View.VISIBLE);
                        }
                        String des = PubUtil.PATROL_FORM_LIST_DATABEAN.getUsage();
                        String tail1 = PubUtil.PATROL_FORM_LIST_DATABEAN.getFooterTitle1();
                        String tail2 = PubUtil.PATROL_FORM_LIST_DATABEAN.getFooterTitle2();
                        String tail3 = PubUtil.PATROL_FORM_LIST_DATABEAN.getFooterTitle3();
                        String tail4 = PubUtil.PATROL_FORM_LIST_DATABEAN.getFooterTitle4();
                        String formNum = PubUtil.PATROL_FORM_LIST_DATABEAN.getShowCode();
                        String tailValue1 = patrolDealBean.getFooterTitle1Value();
                        String tailValue2 = patrolDealBean.getFooterTitle2Value();
                        String tailValue3 = patrolDealBean.getFooterTitle3Value();
                        String tailValue4 = patrolDealBean.getFooterTitle4Value();
                        initViewStatus(mPickedFormDesCl, mPickedFormDesContentTv, des, null, null);
                        initViewStatus(mPickedFormTail1Cl, mPickedFormTailTitle1Tv, tail1, mPickedFormTailContent1Et, tailValue1);
                        initViewStatus(mPickedFormTail2Cl, mPickedFormTailTitle2Tv, tail2, mPickedFormTailContent2Et, tailValue2);
                        initViewStatus(mPickedFormTail3Cl, mPickedFormTailTitle3Tv, tail3, mPickedFormTailContent3Et, tailValue3);
                        initViewStatus(mPickedFormTail4Cl, mPickedFormTailTitle4Tv, tail4, mPickedFormTailContent4Et, tailValue4);
                        initViewStatus(mPickedFormNumCl, mPickedFormNumValueTv, formNum, null, null);
                        if (!StrUtils.isStringValueOk(des) && !StrUtils.isStringValueOk(tail1) && !StrUtils.isStringValueOk(tail2)
                                && !StrUtils.isStringValueOk(tail3) && !StrUtils.isStringValueOk(tail4) && !StrUtils.isStringValueOk(formNum)) {
                            mPickedFormTailGpTv.setVisibility(View.GONE);
                        } else {
                            mPickedFormTailGpTv.setVisibility(View.VISIBLE);
                        }
                    }
                    break;
                }

            default:
                break;
        }
    }

    /**
     * 初始化控件的状态
     */
    private void initViewStatus(ConstraintLayout constraintLayout, TextView titleTv, String titleV, TextView ValueTv, String ValueV) {
        if (!StrUtils.isStringValueOk(titleV)) {
            constraintLayout.setVisibility(View.GONE);
        } else {
            constraintLayout.setVisibility(View.VISIBLE);
            if (titleTv != null) {
                titleTv.setText(titleV);
            }
            if (StrUtils.isStringValueOk(ValueV)) {
                if (ValueTv != null) {
                    ValueTv.setText(ValueV);
                }
            }
        }
    }

    @Override
    public void actionBarRightTvOnClick() {

    }



    /**
     * 单项选择框逻辑
     */
    private void singleSelectLogic() {

        if (PubUtil.PATROL_FORM_LIST_DATABEAN != null) {
            showdata = PubUtil.PATROL_FORM_LIST_DATABEAN.getShowDate();
        }

        Calendar startDate = Calendar.getInstance();
        int currentYear = getCurrentYear();
        startDate.set(currentYear, 1, 0);
        Calendar endDate = Calendar.getInstance();
        endDate.set(currentYear + 1, 12, 0);
        PickerManager.getInstance().showTimePickerViewIncludeRangDate(this, PickerManager.getInstance().getTimeType(showdata), "", new PickerManager.OnTimePickerTimeSelectedListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                mPickedFormSelectTv.setText(getTime(date, showdata));
            }
        },startDate,endDate);
    }

    private String getTime(Date date, String time) {//可根据需要自行截取数据显示
        Log.d("getTime()", "choice date millis: " + date.getTime());
        SimpleDateFormat formats = null;
        switch (time) {

            case "year":
                formats = new SimpleDateFormat("yyyy年");
                break;
            case "month":
                formats = new SimpleDateFormat("yyyy年MM月");

                break;

            case "day":
                formats = new SimpleDateFormat("yyyy年MM月dd日");

                break;

            default:
                break;


        }


        return formats.format(date);
    }

    @Override
    public void startLoading(String tag) {

    }

    @Override
    public void stopLoading(String tag) {

    }

    @Override
    public void updateView(Object o, String tag) {

        //修改返回数据
        PatrolTaskFormListBean.DataBean patrolTaskBean = (PatrolTaskFormListBean.DataBean) o;
        //将表头表尾等信息传递给巡检任务表单记录那个类

        Intent intent = new Intent(this, PatrolTaskFormRecordActivity.class);
        intent.putExtra(PatrolCheckUpdateContract.PATROL_FORM_TASK_DEAL_BEAN, patrolTaskBean);
        startActivity(intent);
        finish();

    }

    @Override
    public void onError(String tag) {
        showToast(tag);
    }

    private void initView() {
        mPickedFormCancelTv = (TextView) findViewById(R.id.picked_form_cancel_tv);
        mPickedFormCancelTv.setOnClickListener(this);
        mPickedFormConfirmTv = (TextView) findViewById(R.id.picked_form_confirm_tv);
        mPickedFormConfirmTv.setOnClickListener(this);
        mPickedFormContentEt = (EditText) findViewById(R.id.picked_form_content_et);
        mPickedFormContentEt.setOnFocusChangeListener(this);
        mPickedFormSelectTv = (TextView) findViewById(R.id.picked_form_select_tv);
        mPickedFormTimeCl = (ConstraintLayout) findViewById(R.id.picked_form_time_cl);
        mPickedFormTimeCl.setOnClickListener(this);
        mPickedFormHeadContent1Et = (EditText) findViewById(R.id.picked_form_head_content1_et);
        mPickedFormHeadContent2Et = (EditText) findViewById(R.id.picked_form_head_content2_et);
        mPickedFormHeadContent3Et = (EditText) findViewById(R.id.picked_form_head_content3_et);
        mPickedFormHeadContent4Et = (EditText) findViewById(R.id.picked_form_head_content4_et);
        mPickedFormTailContent1Et = (EditText) findViewById(R.id.picked_form_tail_content1_et);
        mPickedFormTailContent2Et = (EditText) findViewById(R.id.picked_form_tail_content2_et);
        mPickedFormTailContent3Et = (EditText) findViewById(R.id.picked_form_tail_content3_et);
        mPickedFormTailContent4Et = (EditText) findViewById(R.id.picked_form_tail_content4_et);
        mPickedFormHeadContent1Et.setOnFocusChangeListener(this);
        mPickedFormHeadContent2Et.setOnFocusChangeListener(this);
        mPickedFormHeadContent3Et.setOnFocusChangeListener(this);
        mPickedFormHeadContent4Et.setOnFocusChangeListener(this);
        mPickedFormTailContent1Et.setOnFocusChangeListener(this);
        mPickedFormTailContent2Et.setOnFocusChangeListener(this);
        mPickedFormTailContent3Et.setOnFocusChangeListener(this);
        mPickedFormTailContent4Et.setOnFocusChangeListener(this);

        mPickedFormHead1Cl = (ConstraintLayout) findViewById(R.id.picked_form_head1_cl);
        mPickedFormHead2Cl = (ConstraintLayout) findViewById(R.id.picked_form_head2_cl);
        mPickedFormHead3Cl = (ConstraintLayout) findViewById(R.id.picked_form_head3_cl);
        mPickedFormHead4Cl = (ConstraintLayout) findViewById(R.id.picked_form_head4_cl);
        mPickedFormDesContentTv = (TextView) findViewById(R.id.picked_form_des_content_tv);
        mPickedFormDesCl = (ConstraintLayout) findViewById(R.id.picked_form_des_cl);
        mPickedFormTail1Cl = (ConstraintLayout) findViewById(R.id.picked_form_tail1_cl);
        mPickedFormTail2Cl = (ConstraintLayout) findViewById(R.id.picked_form_tail2_cl);
        mPickedFormTail3Cl = (ConstraintLayout) findViewById(R.id.picked_form_tail3_cl);
        mPickedFormTail4Cl = (ConstraintLayout) findViewById(R.id.picked_form_tail4_cl);
        mPickedFormNumValueTv = (TextView) findViewById(R.id.picked_form_num_value_tv);
        mPickedFormNumCl = (ConstraintLayout) findViewById(R.id.picked_form_num_cl);
        mPickedFormHeadTitle1Tv = (TextView) findViewById(R.id.picked_form_head_title1_tv);
        mPickedFormHeadTitle2Tv = (TextView) findViewById(R.id.picked_form_head_title2_tv);
        mPickedFormHeadTitle3Tv = (TextView) findViewById(R.id.picked_form_head_title3_tv);
        mPickedFormHeadTitle4Tv = (TextView) findViewById(R.id.picked_form_head_title4_tv);
        mPickedFormTailTitle1Tv = (TextView) findViewById(R.id.picked_form_tail_title1_tv);
        mPickedFormTailTitle2Tv = (TextView) findViewById(R.id.picked_form_tail_title2_tv);
        mPickedFormTailTitle3Tv = (TextView) findViewById(R.id.picked_form_tail_title3_tv);
        mPickedFormTailTitle4Tv = (TextView) findViewById(R.id.picked_form_tail_title4_tv);
        mPickedFormHeadGpTv = (TextView) findViewById(R.id.picked_form_head_gp_tv);
        mPickedFormTailGpTv = (TextView) findViewById(R.id.picked_form_tail_gp_tv);
        mPatrolFormInfoTitleTv = (TextView) findViewById(R.id.patrol_form_info_title_tv);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.picked_form_cancel_tv:
                hideKeyboard(v);
                finish();
                break;
            case R.id.picked_form_confirm_tv:
                //新建表单

                switch (PubUtil.MODIFY_PATROL_FORM_INFO) {
                    case 0:
                        if (PubUtil.PATROL_FORM_LIST_DATABEAN != null) {
                            Map<String, String> map = new ArrayMap<>();
                            String formTitle = getTextViewValue(mPickedFormContentEt);
                            if (!StrUtils.isStringValueOk(formTitle)) {
                                showToast("请填写标题");
                                return;
                            }
                            map.put("villageId", String.valueOf(UserInfoUtil.getInstance().getVillageId()));
                            map.put("taskName", formTitle);
                            if (PubUtil.PATROL_FORM_LIST_DATABEAN != null) {
                                map.put("formId", String.valueOf(PubUtil.PATROL_FORM_LIST_DATABEAN.getId()));
                            }
                            if (patrolDealBean != null) {
                                map.put("formId", String.valueOf(patrolDealBean.getFormId()));

                            }
                            setRequestmap("showDateValue", mPickedFormSelectTv, map);
                            setRequestmap("headerTitle1Value", mPickedFormHeadContent1Et, map);
                            setRequestmap("headerTitle2Value", mPickedFormHeadContent2Et, map);
                            setRequestmap("headerTitle3Value", mPickedFormHeadContent3Et, map);
                            setRequestmap("headerTitle4Value", mPickedFormHeadContent4Et, map);
                            setRequestmap("footerTitle1Value", mPickedFormTailContent1Et, map);
                            setRequestmap("footerTitle2Value", mPickedFormTailContent2Et, map);
                            setRequestmap("footerTitle3Value", mPickedFormTailContent3Et, map);
                            setRequestmap("footerTitle4Value", mPickedFormTailContent4Et, map);
                            map.put("createUserId", String.valueOf(UserInfoUtil.getInstance().getUserId()));

                                getPresenter().savePatrolFormTask(map, "");

                        }
                        break;
                    case 1:
                        //修改表单
                        if (patrolDealBean != null) {

                            getPresenter().updataForm(patrolDealBean.getId(), mPickedFormContentEt.getText().toString()
                                    , patrolDealBean.getFormId()
                                    , getTextViewValue(mPickedFormSelectTv)
                                    , getTextViewValue(mPickedFormHeadContent1Et)
                                    , getTextViewValue(mPickedFormHeadContent2Et)
                                    , getTextViewValue(mPickedFormHeadContent3Et)
                                    , getTextViewValue(mPickedFormHeadContent4Et)
                                    , getTextViewValue(mPickedFormTailContent1Et)
                                    , getTextViewValue(mPickedFormTailContent2Et)
                                    , getTextViewValue(mPickedFormTailContent3Et)
                                    , getTextViewValue(mPickedFormTailContent4Et)
                                    , UserInfoUtil.getInstance().getUserId() + "", PatrolCheckUpdateContract.FORM_UPDATA
                            );

                        }
                        break;
                    default:
                        break;
                }


                break;
            case R.id.picked_form_time_cl:

                singleSelectLogic();

                break;
        }
    }

    /**
     * 网络请求的参数配置
     *
     * @param key
     * @param textView
     * @param map
     */
    private void setRequestmap(String key, TextView textView, Map<String, String> map) {
        if (textView != null) {
            String value = getTextViewValue(textView);
            if (StrUtils.isStringValueOk(value)) {
                map.put(key, value);
            }
        }

    }

    @Override
    public void finish() {
        super.finish();
//        overridePendingTransition(0, R.anim.bottom_out);
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        switch (v.getId()) {
            case R.id.picked_form_content_et:
                //标题
                hideKeyboard(v);
                break;
            case R.id.picked_form_head_content1_et:
                hideKeyboard(v);
                break;
            case R.id.picked_form_head_content2_et:
                hideKeyboard(v);
                break;
            case R.id.picked_form_head_content3_et:
                hideKeyboard(v);
                break;
            case R.id.picked_form_head_content4_et:
                hideKeyboard(v);
                break;
            case R.id.picked_form_tail_content1_et:
                //标题
                hideKeyboard(v);
                break;
            case R.id.picked_form_tail_content2_et:
                hideKeyboard(v);
                break;
            case R.id.picked_form_tail_content3_et:
                hideKeyboard(v);
                break;
            case R.id.picked_form_tail_content4_et:
                hideKeyboard(v);
                break;
            default:
                break;
        }
    }
}
