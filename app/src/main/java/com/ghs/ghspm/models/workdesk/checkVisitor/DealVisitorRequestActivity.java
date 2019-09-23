package com.ghs.ghspm.models.workdesk.checkVisitor;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.allen.library.SuperTextView;
import com.ghs.ghspm.R;
import com.ghs.ghspm.base.BaseActivity;
import com.ghs.ghspm.bean.PropertyVisitorListBean;
import com.ghs.ghspm.tools.IntentUtil;
import com.ghs.ghspm.tools.StrUtils;
import com.ghs.ghspm.tools.UserInfoUtil;

/**
 * created by
 * created date 2019/8/26 11:26.
 * application  物业访客详情 处理或者显示
 */
public class DealVisitorRequestActivity extends BaseActivity<PropertyVisitorContract.PropertyRecordView, PropertyVisitorPresent> implements PropertyVisitorContract.PropertyRecordView {


    /**
     * 到访地址：
     */
    private TextView mPropertyDeailsAddress;
    /**
     * 访客姓名：
     */
    private TextView mPropertyDeailsName;
    /**
     * 手机号码：
     */
    private TextView mPropertyDeailsMobile;
    /**
     * 到访时间：
     */
    private TextView mPropertyDeailsTime;
    /**
     * 到访原因：
     */
    private TextView mPropertyDeailsCause;
    private SuperTextView mWorkOrderRejectStv;
    private SuperTextView mWorkOrderWriteProgressStv;
    /**
     * 是否驾车：
     */
    private TextView mPropertyDeailsCarState;
    /**
     * 车牌号码：
     */
    private TextView mPropertyDeailsCarNumber;
    /**
     * 审核状态：
     */
    private TextView mPropertyDeailsState;
    /**
     * 临时密码：
     */
    private TextView mPropertyDeailsPassword;
    private PropertyVisitorListBean.DataBean dataBean;
    private LinearLayout mAccessVisitorRequestWarnLl;

    @Override
    public PropertyVisitorPresent creatPresenter() {
        return new PropertyVisitorPresent();
    }

    @Override
    public void setLayout() {
        setContentView(R.layout.activity_property_details);
        initView();
    }


    @Override
    public void initLayoutView() {
        initActionBar("访客请求审核", "", R.drawable.bg_gray_only_bottom_deeper_gray_shape_1px);

    }

    @Override
    public void getDate() {
        dataBean = (PropertyVisitorListBean.DataBean) getIntentParcelableData();
        initVisitorMsg(dataBean);

    }

    /**
     * 初始化物业访客的信息
     *
     * @param dataBean
     */
    private void initVisitorMsg(PropertyVisitorListBean.DataBean dataBean) {
        if (dataBean != null) {
            //状态 1:审核中 2:通过 3：驳回
            if (1 == dataBean.getState()) {
                setViewsVisible(mWorkOrderRejectStv, mWorkOrderWriteProgressStv);
                setViewsInvisible(true, mPropertyDeailsPassword, mAccessVisitorRequestWarnLl);
            } else {
                setViewsVisible(mPropertyDeailsPassword, mAccessVisitorRequestWarnLl);
                setViewsInvisible(true, mWorkOrderRejectStv, mWorkOrderWriteProgressStv);
            }
            mPropertyDeailsAddress.setText("到访地址：" + dataBean.getVillageName());
            mPropertyDeailsName.setText("访客姓名：" + dataBean.getVisitorName());
            mPropertyDeailsMobile.setText("手机号码：" + dataBean.getVisitorMobile());
            String arriveTime = dataBean.getVisitDay();
            if (StrUtils.isStringValueOk(arriveTime)) {
                mPropertyDeailsTime.setText("到访时间：" + arriveTime);
            }
            mPropertyDeailsCause.setText("到访原因：" + dataBean.getReason());
            String carnum = StrUtils.isStringValueOk(dataBean.getCarNum()) ? "是" : "无";
            mPropertyDeailsCarState.setText("是否驾车：" + carnum);
            if (!"无".equals(carnum)) {
                mPropertyDeailsCarNumber.setVisibility(View.VISIBLE);
                mPropertyDeailsCarNumber.setText("车牌号码：" + dataBean.getCarNum());
            } else {
                mPropertyDeailsCarNumber.setVisibility(View.GONE);
            }
            mPropertyDeailsState.setText("审核状态：" + getState(dataBean.getState()));
            if (2 == dataBean.getState()) {
                mPropertyDeailsPassword.setVisibility(View.VISIBLE);
                mPropertyDeailsPassword.setText("临时密码：" + dataBean.getLockPassword());
            } else {
                mPropertyDeailsPassword.setVisibility(View.GONE);
            }
        }
    }

    public String getState(int state) {
        String date = "";
        switch (state) {
            case 1:
                date = "审核中";
                break;
            case 2:
                date = "通过";
                break;
            case 3:
                date = "驳回";
                break;

        }

        return date;

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

        switch (tag) {
            case PropertyVisitorContract.PROPERTY_RECORD_LIST:

                break;
            case PropertyVisitorContract.PROPERTY_RECORD_CHECKAGREE:
                showToast("已通过");
                IntentUtil.getInstance().startActivityWithParcelableData((PropertyVisitorListBean.DataBean) o, DealVisitorRequestActivity.this, PropertyVisitorDetailActivity.class);
                finish();

                break;
            case PropertyVisitorContract.PROPERTY_RECORD_CHECKREJECT:
                showToast("已拒绝");
                IntentUtil.getInstance().startActivityWithParcelableData((PropertyVisitorListBean.DataBean) o, DealVisitorRequestActivity.this, PropertyVisitorDetailActivity.class);
                finish();
                break;


        }


    }

    @Override
    public void onError(String tag) {

    }


    public void initView() {
        mPropertyDeailsAddress = (TextView) findViewById(R.id.property_deails_address);
        mPropertyDeailsName = (TextView) findViewById(R.id.property_deails_name);
        mPropertyDeailsMobile = (TextView) findViewById(R.id.property_deails_mobile);
        mPropertyDeailsTime = (TextView) findViewById(R.id.property_deails_time);
        mPropertyDeailsCause = (TextView) findViewById(R.id.property_deails_cause);
        mWorkOrderRejectStv = (SuperTextView) findViewById(R.id.work_order_reject_stv);
        mWorkOrderWriteProgressStv = (SuperTextView) findViewById(R.id.work_order_write_progress_stv);
        mPropertyDeailsCarState = (TextView) findViewById(R.id.property_deails_car_state);
        mPropertyDeailsCarNumber = (TextView) findViewById(R.id.property_deails_car_number);
        mPropertyDeailsState = (TextView) findViewById(R.id.property_deails_state);
        mPropertyDeailsPassword = (TextView) findViewById(R.id.property_deails_password);
        mWorkOrderRejectStv.setOnSuperTextViewClickListener(new SuperTextView.OnSuperTextViewClickListener() {
            @Override
            public void onClickListener(SuperTextView superTextView) {

                getPresenter().getCheckReject(dataBean.getId(), UserInfoUtil.getInstance().getUserId(), PropertyVisitorContract.PROPERTY_RECORD_CHECKREJECT);

            }
        });

        mWorkOrderWriteProgressStv.setOnSuperTextViewClickListener(new SuperTextView.OnSuperTextViewClickListener() {
            @Override
            public void onClickListener(SuperTextView superTextView) {
                //通过
                if (dataBean != null) {
                    getPresenter().getCheckAgree(dataBean.getId(), UserInfoUtil.getInstance().getUserId(), PropertyVisitorContract.PROPERTY_RECORD_CHECKAGREE);

                }

            }
        });
        mAccessVisitorRequestWarnLl = (LinearLayout) findViewById(R.id.access_visitor_request_warn_ll);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
