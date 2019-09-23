package com.ghs.ghspm.models.mine.set;

import android.os.Bundle;
import android.webkit.WebView;

import com.ghs.ghspm.R;
import com.ghs.ghspm.base.BaseActivity;
import com.ghs.ghspm.base.BasePresent;
import com.ghs.ghspm.tools.Contract;


public class UserProtocalActivity extends BaseActivity {

    private WebView mUserProtocalWv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void getDate() {
        mUserProtocalWv.loadUrl(Contract.USER_PROTOCAL_URL);
    }

    @Override
    public void initLayoutView() {
        initView();
        initActionBar("用户协议",null);

    }

    @Override
    public void setLayout() {
        setContentView(R.layout.activity_user_protocal);

    }

    @Override
    public void actionBarRightTvOnClick() {

    }

    @Override
    public BasePresent creatPresenter() {
        return null;
    }

    private void initView() {
        mUserProtocalWv = (WebView) findViewById(R.id.user_protocal_wv);

        mUserProtocalWv.setVerticalScrollBarEnabled(false); //垂直不显示
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUserProtocalWv.destroy();
        mUserProtocalWv=null;
    }

}
