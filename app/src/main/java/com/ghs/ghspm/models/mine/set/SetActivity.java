package com.ghs.ghspm.models.mine.set;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.ghs.ghspm.R;
import com.ghs.ghspm.base.BaseActivity;
import com.ghs.ghspm.base.BasePresent;
import com.ghs.ghspm.customView.TextViewImageView;
import com.ghs.ghspm.customView.TwoTextView;
import com.ghs.ghspm.models.login.LoginActivity;
import com.ghs.ghspm.models.mine.set.useguide.UserGuideActivity;
import com.ghs.ghspm.models.push.AliPushManager;
import com.ghs.ghspm.models.workOrder.workOrderDetail.WriteProgressActivity;
import com.ghs.ghspm.models.workdesk.publicfuction.universalkey.UniversalKeyActivity;
import com.ghs.ghspm.tools.ActivityResultManager;
import com.ghs.ghspm.tools.FileUtil;
import com.ghs.ghspm.tools.HawkProperty;
import com.ghs.ghspm.tools.PubUtil;
import com.ghs.ghspm.tools.ToastUtil;
import com.orhanobut.hawk.Hawk;

/**
 * created by tobato
 * created date 2019/7/15 10:45.
 * application   设置
 */
public class SetActivity extends BaseActivity implements View.OnClickListener {

    private TextViewImageView mSetUseGuideMcv;
    private TwoTextView mSetUserVersionSiv;
    private TextViewImageView mSetUseSuggestionMcv;
    private TwoTextView mSetUserClearCacheSiv;
    private TextViewImageView mSetUseProtocolMcv;
    private TwoTextView mSetUserQuitSiv;
    private BottomSheetDialog bottomSheetDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void getDate() {
        initCacheTextValue();
    }

    @Override
    public void initLayoutView() {
        initView();
        initActionBar("设置", null);
    }

    @Override
    public void setLayout() {
        setContentView(R.layout.activity_set);

    }

    @Override
    public void actionBarRightTvOnClick() {

    }

    @Override
    public BasePresent creatPresenter() {
        return null;
    }

    private void initView() {
        mSetUseGuideMcv = (TextViewImageView) findViewById(R.id.set_use_guide_mcv);
        mSetUseGuideMcv.setOnClickListener(this);
        mSetUserVersionSiv = (TwoTextView) findViewById(R.id.set_user_version_siv);
        mSetUserVersionSiv.gettitleBarRightTv().setText(PubUtil.getAPPVersion(this));
        mSetUseSuggestionMcv = (TextViewImageView) findViewById(R.id.set_use_suggestion_mcv);
        mSetUseSuggestionMcv.setOnClickListener(this);
        mSetUserClearCacheSiv = (TwoTextView) findViewById(R.id.set_user_clear_cache_siv);
        mSetUserClearCacheSiv.setOnClickListener(this);
        mSetUseProtocolMcv = (TextViewImageView) findViewById(R.id.set_use_protocol_mcv);
        mSetUseProtocolMcv.setOnClickListener(this);
        mSetUserQuitSiv = (TwoTextView) findViewById(R.id.set_user_quit_siv);
        mSetUserQuitSiv.setOnClickListener(this);
    }

    /**
     * 初始化缓存的大小
     */
    private void initCacheTextValue() {
        String size = null;
        try {
            size = FileUtil.getTotalCacheSize(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!"0B".equals(size)) {
            mSetUserClearCacheSiv.gettitleBarRightTv().setText(size);
        } else {
            mSetUserClearCacheSiv.gettitleBarRightTv().setText("");
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.set_use_guide_mcv:
                startActivity(new Intent(this, UserGuideActivity.class));

                break;
            case R.id.set_use_suggestion_mcv:
                //投诉建议
                PubUtil.LAYOUT_TYPE = 0;
                startActivity(new Intent(this, WriteProgressActivity.class));

                break;
            case R.id.set_user_clear_cache_siv:
                if (!TextUtils.isEmpty(mSetUserClearCacheSiv.gettitleBarRightTv().getText().toString().trim())) {
                    showMaterialProgressDialog(null, "正在清理，请稍后...");
                    new ClearCacheThread(new ClearCacheListener() {
                        @Override
                        public void onClearCacheFinished() {
                            stopMaterialProgressDialog();
                            ToastUtil.showNormalToast(SetActivity.this, Toast.LENGTH_SHORT, "清理完成");
                            initCacheTextValue();
                        }

                    }).run();


                }

                break;
            case R.id.set_use_protocol_mcv:
                startActivity(new Intent(this, UserProtocalActivity.class));

                break;

            case R.id.set_user_quit_siv:
                if (!Hawk.contains(HawkProperty.LOGIN_BEAN)) {
                    showNormalToast("当前没有登录的账号");
                    return;
                }
                View view = LayoutInflater.from(this).inflate(R.layout.mine_set_quit_app, null);
                bottomSheetDialog = new BottomSheetDialog(this);
                bottomSheetDialog.setCancelable(false);
                bottomSheetDialog.setContentView(view);
                bottomSheetDialog.show();
                view.findViewById(R.id.mine_set_quit_app_tv).setOnClickListener(this);
                view.findViewById(R.id.mine_set_exit_cancel_tv).setOnClickListener(this);

                break;
            case R.id.mine_set_quit_app_tv:
                PubUtil.LOGIN_ENTER = 2;
                AliPushManager.getInstance().removeAllAlias();
                //删除最近开门记录
                Hawk.delete(UniversalKeyActivity.MAPKEY);
                Hawk.delete(UniversalKeyActivity.LISTKEY);
                Hawk.delete(HawkProperty.LOGIN_BEAN);
                setResult(ActivityResultManager.MINE_SET_QUIT_APP);
                startActivity(new Intent(this, LoginActivity.class));
                finish();
                break;
            case R.id.mine_set_exit_cancel_tv:
                if (bottomSheetDialog.isShowing()) {
                    bottomSheetDialog.dismiss();
                }

                break;
        }
    }

    public interface ClearCacheListener {
        void onClearCacheFinished();

    }

    public class ClearCacheThread implements Runnable {
        private ClearCacheListener mClearCacheListener;

        public ClearCacheThread(ClearCacheListener mClearCacheListener) {
            this.mClearCacheListener = mClearCacheListener;
        }

        @Override
        public void run() {
            if (FileUtil.clearCacheMemory(SetActivity.this)) {
                if (mClearCacheListener != null) {
                    mClearCacheListener.onClearCacheFinished();
                }
            }
        }


    }

}
