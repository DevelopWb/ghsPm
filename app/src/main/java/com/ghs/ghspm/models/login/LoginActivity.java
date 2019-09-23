package com.ghs.ghspm.models.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ghs.ghspm.R;
import com.ghs.ghspm.base.BaseActivity;
import com.ghs.ghspm.bean.LoginBean;
import com.ghs.ghspm.bean.PropertiesBean;
import com.ghs.ghspm.models.login.selectProperty.SelectPropertyActivity;
import com.ghs.ghspm.models.main.MainActivity;
import com.ghs.ghspm.models.push.AliPushManager;
import com.ghs.ghspm.tools.ActivityManagerTool;
import com.ghs.ghspm.tools.ActivityResultManager;
import com.ghs.ghspm.tools.HawkProperty;
import com.ghs.ghspm.tools.PubUtil;
import com.ghs.ghspm.tools.UserInfoUtil;
import com.orhanobut.hawk.Hawk;

import java.util.List;

/**
 * created by tobato
 * created date 2019/7/15 15:05.
 * application   登录
 * 登录的逻辑
 * 登录--请求企业列表--无企业--提示无权限，联系管理员
 * 登录--请求企业列表--有企业--如果只有一个企业---直接请求选择企业的接口（一个企业的时候 默认选中）--进入主页
 * 登录--请求企业列表--有企业--如果有多个企业---如果本地保存了选择的企业---直接请求选择企业的接口（一个企业的时候 默认选中）--进入主页
 * 登录--请求企业列表--有企业--如果有多个企业---如果本地没有保存选择的企业（没选过企业）---直接跳转到选择企业的界面--进入主页
 */
public class LoginActivity extends BaseActivity<LoginContract.ILoginView, LoginPresent> implements LoginContract.ILoginView, View.OnClickListener {

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
    private LoginBean.DataBean loginDataBean = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void getDate() {
        if (Hawk.contains(HawkProperty.WORK_DESK_MENU)) {
            Hawk.delete(HawkProperty.WORK_DESK_MENU);
        }
    }

    @Override
    protected void onResume() {
        if (getIntent() != null) {
            String tag = getIntent().getStringExtra(ActivityResultManager.TOKEN_EXPIRED_ACTIVITY_TAG);
            //ActivityResultManager.MAINATIVITY_TAG.equals(tag) ||
            if (ActivityResultManager.EXCEPT_MAINATIVITY_TAG.equals(tag)) {
                showToast("登录已过期，请重新登录");
            }
        }
        super.onResume();

    }

    @Override
    public void initLayoutView() {
        initView();
        initActionBar("登录", null);

    }


    @Override
    public void setLayout() {
        setContentView(R.layout.activity_login);
    }

    @Override
    public void actionBarRightTvOnClick() {

    }

    @Override
    public LoginPresent creatPresenter() {
        return new LoginPresent();
    }


    @Override
    public void startLoading(String tag) {
        showMaterialProgressDialog("", "");
    }

    @Override
    public void stopLoading(String tag) {
        stopMaterialProgressDialog();
    }

    @Override
    public void updateView(Object o, String tag) {
        switch (tag) {
            case LoginContract.CHECK_CODE:
//                getPresenter().checkCodeReceived();
                break;
            case LoginContract.LOGIN_MOBILE:
                LoginBean loginBean = (LoginBean) o;
                loginSuccess(loginBean);
                break;
            case LoginContract.GET_PROPERTY_LIST://获取企业列表
                PropertiesBean bean = (PropertiesBean) o;
                if (bean != null) {
                    List<PropertiesBean.DataBean> arrays = bean.getData();
                    if (arrays != null && arrays.size() > 0) {
                        if (arrays.size() == 1) {
                            if (!PubUtil.CHANGE_PROPERTY) {
                                int propertyId = arrays.get(0).getId();
                                Hawk.put(HawkProperty.SELECTED_PROPERTY_ID + UserInfoUtil.getInstance().getUserId(), propertyId);
                                //调用选择物业的接口
                                getPresenter().selectProperty(loginDataBean.getUserId(), propertyId, LoginContract.UPLOAD_SELECTED_PROPERTY);
                            }
                        } else {//一个以上的企业  请求权限的接口
                            //如果保存了选择的物业 直接调用选择物业的接口
                            if (Hawk.contains(HawkProperty.SELECTED_PROPERTY_ID + UserInfoUtil.getInstance().getUserId())) {
                                int propertyId = Hawk.get(HawkProperty.SELECTED_PROPERTY_ID + UserInfoUtil.getInstance().getUserId());
                                //调用选择物业的接口
                                getPresenter().selectProperty(loginDataBean.getUserId(), propertyId, LoginContract.UPLOAD_SELECTED_PROPERTY);
                            } else {
                                startActivity(new Intent(this, SelectPropertyActivity.class));
                                finish();
                            }
                        }
                    } else {//没有企业
                        showToast("暂无系统权限，请联系管理员");
                        stopLoading("");
                    }
                }

                break;
            case LoginContract.UPLOAD_SELECTED_PROPERTY://已选择物业
                LoginBean loginBean_latest = (LoginBean) o;
                if (loginBean_latest != null) {
                    if (loginBean_latest.getData() != null) {

                        //如果保存了已选择的小区
                        if (Hawk.contains(HawkProperty.SELECTED_VILLAGE_ID + UserInfoUtil.getInstance().getUserId())) {
                            int villageId = Hawk.get(HawkProperty.SELECTED_VILLAGE_ID + UserInfoUtil.getInstance().getUserId());
                            String villageName = Hawk.get(HawkProperty.SELECTED_VILLAGE_NAME + UserInfoUtil.getInstance().getUserId());
                            loginBean_latest.getData().setVillageId(villageId);
                            loginBean_latest.getData().setVillageName(villageName);
                            Hawk.put(HawkProperty.LOGIN_BEAN , loginBean_latest);
                        } else {
                            Hawk.put(HawkProperty.LOGIN_BEAN , loginBean_latest);
                            //请求权限接口
                        }
                        startToMainActivity();


                    }
                }
                break;
            default:
                break;
        }

    }

    /**
     * 登录成功
     *
     * @param loginBean
     */
    private void loginSuccess(LoginBean loginBean) {
        getPresenter().checkCodeReceived();
        if (loginBean != null) {
            if (loginBean.getData() != null) {
                loginDataBean = loginBean.getData();
                int userId = loginDataBean.getUserId();
                Hawk.put(HawkProperty.LOGIN_BEAN, loginBean);
                Hawk.put(HawkProperty.USERID, userId);
                Hawk.put(HawkProperty.TOKEN, loginDataBean.getToken());
                AliPushManager.getInstance().bindAll(userId);
            }
        }
//请求企业列表
        getPresenter().getPropertyList(loginDataBean.getUserId(), LoginContract.GET_PROPERTY_LIST);


    }


    private void startToMainActivity() {
        switch (PubUtil.LOGIN_ENTER) {
            case 1:
                setResult(ActivityResultManager.LOGIN_MOBILE_SUCESSED);
                break;
            case 2:
                setResult(ActivityResultManager.QUIT_CURRENT_ACCOUNT_FOR_LOGIN);
                break;
            case 4:
                setResult(ActivityResultManager.QUIT_CURRENT_ACCOUNT_FOR_LOGIN_OTHER);
                break;
            case 3:
                PubUtil.tokenExpiredFirstNotice = true;
                String flog = getIntent().getStringExtra(ActivityResultManager.TOKEN_EXPIRED_ACTIVITY_TAG);
//                if (ActivityResultManager.MAINATIVITY_TAG.equals(flog)) {
//                    setResult(ActivityResultManager.QUIT_CURRENT_ACCOUNT_FOR_TOKEN_MAINACTIVITY);
//                }
                break;
            default:
                break;
        }
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    public void onError(String tag) {
        stopLoading("");
        showToast(tag);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == ActivityResultManager.BOUND_MOBILE) {
            setResult(ActivityResultManager.LOGIN_MOBILE_SUCESSED);
            finish();
        }

    }

    private void initView() {
        mLoginMobileEt = (EditText) findViewById(R.id.login_mobile_et);
        if (Hawk.contains(HawkProperty.LOGIN_BEAN )) {
            LoginBean loginBean = Hawk.get(HawkProperty.LOGIN_BEAN );
            if (loginBean != null) {
                if (loginBean.getData() != null) {
                    mLoginMobileEt.setText(loginBean.getData().getMobile());

                }
            }
        }
        mLoginSendCheckCodeTv = (TextView) findViewById(R.id.login_send_check_code_tv);
        mLoginSendCheckCodeTv.setOnClickListener(this);
        mLoginCheckCodeEt = (EditText) findViewById(R.id.login_check_code_et);
        mLoginConfirmTv = (TextView) findViewById(R.id.login_confirm_tv);
        mLoginConfirmTv.setOnClickListener(this);
        mHeaderLeftIv = (ImageView) findViewById(R.id.header_left_iv);
        mHeaderLeftIv.setVisibility(View.INVISIBLE);
    }


    @Override
    public void updateSendCheckCodeViewStatus(long second) {
        if (second > 0) {
            mLoginSendCheckCodeTv.setText("重新发送 " + second + "s");
            mLoginSendCheckCodeTv.setClickable(false);
            mLoginSendCheckCodeTv.setTextColor(ContextCompat.getColor(this, R.color.app_gray));
        } else {
            mLoginSendCheckCodeTv.setText("发送验证码");
            mLoginSendCheckCodeTv.setClickable(true);
            mLoginSendCheckCodeTv.setTextColor(ContextCompat.getColor(this, R.color.app_black));

        }
    }

    @Override
    public void checkFormatError(String error) {
        showToast(error);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void onBackPressed() {
        getPresenter().checkCodeReceived();
        ActivityManagerTool.getInstance().finishApp();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.login_send_check_code_tv:
                if (isNetWorkConnected()) {
                    getPresenter().sendCheckCode(mLoginMobileEt.getText().toString().trim());
                }
                break;
            case R.id.login_confirm_tv:
                    getPresenter().loginByTelNo(mLoginMobileEt.getText().toString().trim(), mLoginCheckCodeEt.getText().toString().trim());
                break;
        }
    }
}

