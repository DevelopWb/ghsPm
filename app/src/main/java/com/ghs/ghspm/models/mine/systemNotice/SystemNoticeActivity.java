package com.ghs.ghspm.models.mine.systemNotice;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ghs.ghspm.R;
import com.ghs.ghspm.base.BaseActivity;
import com.ghs.ghspm.base.network.RequestStatus;
import com.ghs.ghspm.bean.SysNoticeBean;
import com.ghs.ghspm.models.unread.UnReadTagModel;
import com.ghs.ghspm.tools.ActivityResultManager;
import com.ghs.ghspm.tools.UserInfoUtil;

import java.util.List;
/**
 * created by tobato
 * created date 2019/7/17 15:31.
 * application  系统消息
 */
public class SystemNoticeActivity extends BaseActivity<SystemNoticeContract.ISystemNoticeView, SystemNoticePresent> implements SystemNoticeContract.ISystemNoticeView, RequestStatus {

    private RecyclerView mSystemNoticeRv;
    private SystemNoticeAdapter systemNoticeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public SystemNoticePresent creatPresenter() {
        return new SystemNoticePresent();
    }

    @Override
    public void setLayout() {
        setContentView(R.layout.activity_system_notice);

    }

    @Override
    public void initLayoutView() {
        initView();
        initActionBar("系统消息", null);
    }

    @Override
    public void getDate() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        getPresenter().getNoticeList(UserInfoUtil.getInstance().getUserId(), SystemNoticeContract.GET_SYS_NOTICE);
        new UnReadTagModel().rvUnreadSysNoticeAmount(UserInfoUtil.getInstance().getUserId(),this,SystemNoticeContract.RV_SYS_NOTICE_UNREAD);


    }

    @Override
    public void actionBarRightTvOnClick() {

    }


    private void initView() {
        mSystemNoticeRv = (RecyclerView) findViewById(R.id.system_notice_rv);
        systemNoticeAdapter = new SystemNoticeAdapter(R.layout.system_notice_item);
        initRecyclerview(mSystemNoticeRv, systemNoticeAdapter, LinearLayoutManager.VERTICAL, false);
        addDivider(true,mSystemNoticeRv,true,false);
        systemNoticeAdapter.setEmptyView(getAdapterEmptyView("很干净,一条消息也没有"));
    }

    @Override
    public void onBackPressed() {
        setResult(ActivityResultManager.MINE_SYSTEM_NOTICE_BACK);
        super.onBackPressed();

    }

    @Override
    public void startLoading(String tag) {

    }

    @Override
    public void stopLoading(String tag) {

    }

    @Override
    public void updateView(Object o, String tag) {
        SysNoticeBean sysNoticeBean = (SysNoticeBean) o;
        if (sysNoticeBean != null) {
            List<SysNoticeBean.DataBean> dataBean = sysNoticeBean.getData();
            if (dataBean != null&&dataBean.size()>0) {
                systemNoticeAdapter.setNewData(dataBean);
            }
        }
    }

    @Override
    public void onError(String tag) {

    }

    @Override
    public void onStart(String tag) {

    }

    @Override
    public void onSuccess(Object o, String tag) {

    }
}
