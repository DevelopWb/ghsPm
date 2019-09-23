package com.ghs.ghspm.models.mine.personalInfo.modifymobile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ghs.ghspm.R;
import com.ghs.ghspm.base.BaseActivity;
import com.ghs.ghspm.models.login.LoginContract;
import com.ghs.ghspm.tools.ActivityResultManager;
import com.ghs.ghspm.tools.UserInfoUtil;


public class ModifyMobileActivity extends BaseActivity<ModifyMobileContract.IModifyMobileView, ModifyMobilePresent> implements ModifyMobileContract.IModifyMobileView, View.OnClickListener {

    /**
     * 13578790511
     */
    private EditText mLoginMobileEt;
    /**
     * 发送验证码
     */
    private TextView mLoginSendCheckCodeTv;
    private EditText mLoginCheckCodeEt;
    /**
     * 登 录
     */
    private TextView mLoginConfirmTv;
    private ImageView mHeaderLeftIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public ModifyMobilePresent creatPresenter() {
        return new ModifyMobilePresent();
    }

    @Override
    public void setLayout() {
        setContentView(R.layout.activity_modify_mobile);

    }

    @Override
    public void initLayoutView() {
        initView();

        initActionBar("修改手机号", "取消");

    }

    @Override
    public void getDate() {

    }

    @Override
    public void actionBarRightTvOnClick() {
        finish();
    }

    private void initView() {
        mLoginMobileEt = (EditText) findViewById(R.id.login_mobile_et);
        mLoginSendCheckCodeTv = (TextView) findViewById(R.id.login_send_check_code_tv);
        mLoginSendCheckCodeTv.setOnClickListener(this);
        mLoginCheckCodeEt = (EditText) findViewById(R.id.login_check_code_et);
        mLoginConfirmTv = (TextView) findViewById(R.id.login_confirm_tv);
        mLoginConfirmTv.setOnClickListener(this);
        mLoginConfirmTv.setText("提交");
        mHeaderLeftIv = (ImageView) findViewById(R.id.header_left_iv);
        mHeaderLeftIv.setVisibility(View.INVISIBLE);

    }


    @Override
    public void updateSendCheckCodeViewStatus(long second) {
        if (second > 0) {
            mLoginSendCheckCodeTv.setText("重新发送 " + second + "s");
            mLoginSendCheckCodeTv.setClickable(false);
            mLoginSendCheckCodeTv.setTextColor(ContextCompat.getColor(this,R.color.unclick_gray_deep));
        } else {
            mLoginSendCheckCodeTv.setText("发送验证码");
            mLoginSendCheckCodeTv.setClickable(true);
            mLoginSendCheckCodeTv.setTextColor(ContextCompat.getColor(this,R.color.app_black));

        }
    }

    @Override
    public void checkFormatError(String error) {
        showToast(error);
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
            case LoginContract.CHECK_CODE:
//                getPresenter().checkCodeReceived();
//                mLoginCheckCodeEt.setText((String) o);
                break;
            case ModifyMobileContract.MODIFY_MOBILE:
                showToast("修改成功");
                Intent intent = new Intent();
                intent.putExtra("MODIFYED_MOBILE", mLoginMobileEt.getText().toString().trim());
                setResult(ActivityResultManager.EDIT_USER_MODIFY_MOBILE, intent);
                finish();
                break;
            case ModifyMobileContract.MODIFY_MOBILE_FAILED:
                showToast((String) o);
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
    public void onBackPressed() {
        getPresenter().checkCodeReceived();
        super.onBackPressed();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            default:
                break;
            case R.id.login_send_check_code_tv:
                if (isNetWorkConnected()) {
                    getPresenter().sendCheckCode(mLoginMobileEt.getText().toString().trim());
                }
                break;
            case R.id.login_confirm_tv:
                getPresenter().commitModify(UserInfoUtil.getInstance().getUserId(), mLoginMobileEt.getText().toString().trim(), mLoginCheckCodeEt.getText().toString().trim());
                break;
        }
    }
}
