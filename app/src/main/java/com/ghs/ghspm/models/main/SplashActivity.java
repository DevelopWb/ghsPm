package com.ghs.ghspm.models.main;

import android.content.Intent;
import android.os.Bundle;

import com.ghs.ghspm.base.BaseActivity;
import com.ghs.ghspm.base.BasePresent;
import com.ghs.ghspm.models.login.LoginActivity;
import com.ghs.ghspm.models.login.selectProperty.SelectPropertyActivity;
import com.ghs.ghspm.models.push.AliPushManager;
import com.ghs.ghspm.tools.HawkProperty;
import com.ghs.ghspm.tools.StrUtils;
import com.ghs.ghspm.tools.UserInfoUtil;
import com.orhanobut.hawk.Hawk;

import java.util.List;

/**
 * created by tobato
 * created date 2019/7/23 11:41.
 * application   启动页
 */
public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initSplashActivity();
    }

    @Override
    public BasePresent creatPresenter() {
        return null;
    }

    @Override
    public void setLayout() {

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

    private void initSplashActivity() {

            if (Hawk.contains(HawkProperty.LOGIN_BEAN)) {
                String propertyName = UserInfoUtil.getInstance().getPropertyName();
                if (StrUtils.isStringValueOk(propertyName)) {
                    if (getIntent() != null) {
                        Intent intent = new Intent(this, MainActivity.class);
                        String noticeType = getIntent().getStringExtra(AliPushManager.PUSH_NOTICE_TYPE);
                        intent.putExtra(AliPushManager.PUSH_NOTICE_TYPE, noticeType);
                        startActivity(intent);
                    } else {
                        startActivity(new Intent(this, MainActivity.class));
                    }
                } else {
                    startActivity(new Intent(this, SelectPropertyActivity.class));
                }
            }else{
                startActivity(new Intent(this, LoginActivity.class));
            }

        finish();
    }


    @Override
    public void onRefuseGivePromission() {
//        initSplashActivity();
    }

    @Override
    public void onPermissionsDenied(int requestCode, List perms) {

    }
}