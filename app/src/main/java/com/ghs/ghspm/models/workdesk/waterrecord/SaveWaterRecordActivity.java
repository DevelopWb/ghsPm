package com.ghs.ghspm.models.workdesk.waterrecord;

import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.ghs.ghspm.R;
import com.ghs.ghspm.base.BaseActivity;
import com.ghs.ghspm.bean.WaterRecordBean;
import com.ghs.ghspm.tools.ActivityResultManager;
import com.ghs.ghspm.tools.StrUtils;
import com.ghs.ghspm.tools.UserInfoUtil;

public class SaveWaterRecordActivity extends BaseActivity<WaterRecordContract.IWaterRecordView, WaterRecordPresent> implements View.OnClickListener, WaterRecordContract.IWaterRecordView {

    /**
     * 请输入水表数
     */
    private EditText mSaveRecordValueEt;
    /**
     * 反馈内容...
     */
    private EditText mSaveRecordContentEt;
    /**
     * 确定
     */
    private TextView mSaveRecordConfirmTv;
    private TextView mSaveRecordTitleTv;
    private WaterRecordBean.DataBean dataBean;
    private int roomId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public WaterRecordPresent creatPresenter() {
        return new WaterRecordPresent();
    }

    @Override
    public void setLayout() {
        setContentView(R.layout.activity_save_water_record);

    }

    @Override
    public void initLayoutView() {
        initActionBar("水电表抄录", null);
        initView();

    }

    @Override
    public void getDate() {
        roomId = getIntent().getIntExtra(ActivityResultManager.WATER_ROOM_ID, 0);
        getPresenter().getRoomRecordDetail("water", roomId, WaterRecordContract.GET_EOOM_RECORD);
    }

    @Override
    public void actionBarRightTvOnClick() {

    }

    private void initView() {
        mSaveRecordValueEt = (EditText) findViewById(R.id.save_record_value_et);
        mSaveRecordContentEt = (EditText) findViewById(R.id.save_record_content_et);
        mSaveRecordConfirmTv = (TextView) findViewById(R.id.save_record_confirm_tv);
        mSaveRecordTitleTv = (TextView) findViewById(R.id.save_record_title_et);
        mSaveRecordConfirmTv.setOnClickListener(this);
        mSaveRecordValueEt.setFilters(new InputFilter[]{
                new InputFilter() {
                    @Override
                    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                        String lastInputContent = dest.toString();
                        if (lastInputContent.contains(".")) {//小数位 4位
                            int index = lastInputContent.indexOf(".");
                            if(dend - index >=5){
                                return "";
                            }
                        }else{//整数位8位
                            if(dend - start >=8){
                                return "";
                            }
                        }
                        return null;
                    }
                }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.save_record_confirm_tv:
                String value = mSaveRecordValueEt.getText().toString().trim();
                String content = mSaveRecordContentEt.getText().toString().trim();

                if (StrUtils.isStringValueOk(value) || StrUtils.isStringValueOk(content)) {
                    if (dataBean != null) {
                        String value_str = StrUtils.isStringValueOk(value) ? value : "0";
                        double value_double = Double.parseDouble(value_str);
                        getPresenter().saveWaterRecord(dataBean.getOrderId(), "water", UserInfoUtil.getInstance().getUserId(), roomId, value_double, content, WaterRecordContract.SAVE_WATER_RECORD);

                    }
                } else {
                    showNormalToast("水表数或者备注最少输入一项");
                    return;
                }


                break;
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
        switch (tag) {
            case WaterRecordContract.GET_EOOM_RECORD:
                dataBean = (WaterRecordBean.DataBean) o;
                if (dataBean != null) {
                    String watchNo = dataBean.getWatchNumber() > 0 ? String.valueOf(dataBean.getWatchNumber()) : "";
                    mSaveRecordValueEt.setText(watchNo);
                    mSaveRecordContentEt.setText(dataBean.getRemark());
                    mSaveRecordTitleTv.setText(dataBean.getRoomNumber());
                }
                break;
            case WaterRecordContract.SAVE_WATER_RECORD:
                showNormalToast("录入成功");
                setResult(ActivityResultManager.SAVE_WATER_RECORD);
                finish();
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
}
