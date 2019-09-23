package com.ghs.ghspm.models.workdesk.publicfuction.notice.unnormalnotice;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.ghs.ghspm.R;
import com.ghs.ghspm.base.BaseActivity;
import com.ghs.ghspm.base.BasePresent;
import com.ghs.ghspm.tools.ActivityResultManager;
import com.ghs.ghspm.tools.Contract;
import com.ghs.ghspm.tools.PubUtil;
import com.ghs.ghspm.tools.StrUtils;

public class UnNormalNoticeDetailActivity extends BaseActivity {

    private WebView mWebview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void getDate() {
        String url = getIntent().getStringExtra(ActivityResultManager.INSTITUTION_URL);
        if (StrUtils.isStringValueOk(url)) {
            mWebview.loadUrl(Contract.INTITUTION_URL+url);
        }

    }

    @Override
    public void initLayoutView() {
        switch (PubUtil.NOTICE_TYPE) {
            case 0:
                initActionBar("制度",null);

                break;
            case 1:
                initActionBar("小区公告",null);

                break;
            default:
                break;
        }
        initView();

    }

    @Override
    public void setLayout() {
        setContentView(R.layout.activity_institution_detail);

    }

    @Override
    public void actionBarRightTvOnClick() {

    }

    @Override
    public BasePresent creatPresenter() {
        return null;
    }

    private void initView() {
        mWebview = (WebView) findViewById(R.id.webview);
        showMaterialProgressDialog(null,"正在加载，请稍后...");
        WebViewClient webViewClient = new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {//页面加载完成
                stopMaterialProgressDialog();
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {//页面开始加载

            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                if (url.equals("http://www.google.com/")) {
//                    Toast.makeText(MainActivity.this, "国内不能访问google,拦截该url", Toast.LENGTH_LONG).show();
//                    return true;//表示我已经处理过了
//                }
                return super.shouldOverrideUrlLoading(view, url);
            }

        };
        mWebview.setWebViewClient(webViewClient);
        mWebview.setVerticalScrollBarEnabled(false); //垂直不显示
    }

    @Override
    protected void onDestroy() {
        mWebview.destroy();
        mWebview=null;
        stopMaterialProgressDialog();

        super.onDestroy();
    }
}
