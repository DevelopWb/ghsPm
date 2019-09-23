package com.ghs.ghspm.models.login.selectProperty;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ghs.ghspm.R;
import com.ghs.ghspm.base.BaseActivity;
import com.ghs.ghspm.bean.LoginBean;
import com.ghs.ghspm.bean.PropertiesBean;
import com.ghs.ghspm.models.login.LoginContract;
import com.ghs.ghspm.models.login.LoginPresent;
import com.ghs.ghspm.models.main.MainActivity;
import com.ghs.ghspm.models.main.MainContact;
import com.ghs.ghspm.tools.EventManager;
import com.ghs.ghspm.tools.HawkProperty;
import com.ghs.ghspm.tools.PubUtil;
import com.ghs.ghspm.tools.UserInfoUtil;
import com.orhanobut.hawk.Hawk;

import java.util.List;


public class SelectPropertyActivity extends BaseActivity<LoginContract.ILoginView, LoginPresent> implements View.OnClickListener, LoginContract.ILoginView {

    /**
     * 请选择进入的空间
     */
    private RecyclerView mSplashOrganizesRv;
    private SelectPropertyAdapter adapter;
    /**
     * 取消
     */
    private TextView mSelectPropertyCancelTv;
    private LinearLayout mSelectPropertyEmptyLl;
    private ConstraintLayout mSelectPropertyMorePropertiesCl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public LoginPresent creatPresenter() {
        return new LoginPresent();
    }

    @Override
    public void setLayout() {
        setContentView(R.layout.activity_splash_jump);

    }

    @Override
    public void initLayoutView() {
        initView();

    }

    @Override
    public void getDate() {
        getPresenter().getPropertyList(UserInfoUtil.getInstance().getUserId(), LoginContract.GET_PROPERTY_LIST);
    }

    @Override
    public void actionBarRightTvOnClick() {

    }

    private void initView() {
        mSplashOrganizesRv = (RecyclerView) findViewById(R.id.splash_organizes_rv);
        adapter = new SelectPropertyAdapter(R.layout.property_item);
        initRecyclerview(mSplashOrganizesRv, adapter, LinearLayoutManager.VERTICAL, false);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                PropertiesBean.DataBean dataBean = (PropertiesBean.DataBean) adapter.getData().get(position);
                getPresenter().selectProperty(UserInfoUtil.getInstance().getUserId(), dataBean.getId(), LoginContract.UPLOAD_SELECTED_PROPERTY);

            }
        });

        mSelectPropertyCancelTv = (TextView) findViewById(R.id.select_property_cancel_tv);
        mSelectPropertyCancelTv.setOnClickListener(this);
        if (PubUtil.CHANGE_PROPERTY) {
            mSelectPropertyCancelTv.setVisibility(View.VISIBLE);
        } else {
            mSelectPropertyCancelTv.setVisibility(View.GONE);

        }
        mSelectPropertyEmptyLl = (LinearLayout) findViewById(R.id.select_property_empty_ll);
        mSelectPropertyMorePropertiesCl = (ConstraintLayout) findViewById(R.id.select_property_more_properties_cl);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.select_property_cancel_tv:
                finish();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        PubUtil.CHANGE_PROPERTY = false;
        super.onDestroy();
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
            case LoginContract.GET_PROPERTY_LIST:
                PropertiesBean bean = (PropertiesBean) o;
                if (bean != null) {
                    List<PropertiesBean.DataBean> arrays = bean.getData();
                    if (arrays != null && arrays.size() > 0) {
                        mSelectPropertyMorePropertiesCl.setVisibility(View.VISIBLE);
                        mSelectPropertyEmptyLl.setVisibility(View.GONE);
                        adapter.setNewData(arrays);
                    } else {
                        mSelectPropertyMorePropertiesCl.setVisibility(View.GONE);
                        mSelectPropertyEmptyLl.setVisibility(View.VISIBLE);
                    }
                }

                break;
            case LoginContract.UPLOAD_SELECTED_PROPERTY:
                LoginBean loginBean = (LoginBean) o;
                if (loginBean != null) {
                    if (loginBean.getData() != null) {
                        Hawk.put(HawkProperty.LOGIN_BEAN, loginBean);
                        int  propertyId = loginBean.getData().getPropertyId();
                        int  villageId = loginBean.getData().getVillageId();
                        String villageName = loginBean.getData().getVillageName();

                        Hawk.put(HawkProperty.SELECTED_PROPERTY_ID+UserInfoUtil.getInstance().getUserId(),propertyId);
                        Hawk.put(HawkProperty.SELECTED_VILLAGE_ID+UserInfoUtil.getInstance().getUserId(),villageId);
                        Hawk.put(HawkProperty.SELECTED_VILLAGE_NAME+UserInfoUtil.getInstance().getUserId(),villageName);

                        startActivity(new Intent(SelectPropertyActivity.this, MainActivity.class));
                        if (PubUtil.CHANGE_PROPERTY) {
                            //通知相关接口 重新刷新
                            EventManager.sendStringMsg(MainContact.CHANGE_VILLAGE);
                        }
                        finish();
                    }
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onError(String tag) {

    }

    @Override
    public void onBackPressed() {
    }

    @Override
    public void updateSendCheckCodeViewStatus(long second) {
        
    }

    @Override
    public void checkFormatError(String error) {

    }
}
