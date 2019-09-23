package com.ghs.ghspm.models.workdesk.signAndTable.tableTaskToDo.formTaskSign.commonSign;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ghs.ghspm.R;
import com.ghs.ghspm.base.BaseActivity;
import com.ghs.ghspm.base.displayPhotos.DisplayPhotosActivity;
import com.ghs.ghspm.bean.DailyTaskJsonBean;
import com.ghs.ghspm.bean.TableTaskDetailBean;
import com.ghs.ghspm.models.workdesk.signAndTable.SignTableContrct;
import com.ghs.ghspm.models.workdesk.signAndTable.SignTablePresent;
import com.ghs.ghspm.tools.ActivityResultManager;
import com.ghs.ghspm.tools.GsonManager;
import com.ghs.ghspm.tools.PubUtil;
import com.ghs.ghspm.tools.UserInfoUtil;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * created by tobato
 * created date 2019/1/7 17:04.
 * application   签批  日常记录类 工具类
 */
public class CommonSignActivity extends BaseActivity<SignTableContrct.ISignTableView, SignTablePresent> implements View.OnClickListener, SignTableContrct.ISignTableView {

    private RecyclerView mDetailRv;
    private TextView mTableTaskDealTv;
    private CommonSignAdapter commonSignAdapter;
    private TableTaskDetailBean.DataBean dataBean;
    private List<DailyTaskJsonBean> dailyTaskJsonBeanList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public SignTablePresent creatPresenter() {
        return new SignTablePresent();
    }

    @Override
    public void setLayout() {
        setContentView(R.layout.activity_form_task_info);

    }

    @Override
    public void initLayoutView() {
        initView();
    }

    @Override
    public void getDate() {
        dataBean = getIntent().getParcelableExtra(ActivityResultManager.COMMON_SIGN_ACTIVITY);
        if (dataBean != null) {
            initTitleAndSIgnButtomStatus();
            dailyTaskJsonBeanList = (List<DailyTaskJsonBean>) GsonManager.getInstance().parseJsonToList(dataBean.getContentJson(), new TypeToken<List<DailyTaskJsonBean>>() {
            }.getType());
            commonSignAdapter.setNewData(dailyTaskJsonBeanList);

        }

    }

    /**
     * 初始化标题和签批按钮的状态
     */
    private void initTitleAndSIgnButtomStatus() {
        if (3 == dataBean.getStatus()) {
            mTableTaskDealTv.setVisibility(View.GONE);
            initActionBar("表单详情", null);

        } else {
            if (0 == PubUtil.TableTaskDesActivityEntry) {
                mTableTaskDealTv.setVisibility(View.GONE);
                initActionBar("表单详情", null);

            } else {
                initActionBar("签批表单", null);

            }
        }
    }

    @Override
    public void actionBarRightTvOnClick() {

    }

    private void initView() {
        mDetailRv = (RecyclerView) findViewById(R.id.detail_rv);
        mTableTaskDealTv = (TextView) findViewById(R.id.table_task_deal_tv);
        mTableTaskDealTv.setOnClickListener(this);
        mTableTaskDealTv.setVisibility(View.VISIBLE);
        mTableTaskDealTv.setText("签字/审批");
        commonSignAdapter = new CommonSignAdapter(R.layout.sign_or_deal_item);
        initRecyclerview(mDetailRv, commonSignAdapter, LinearLayoutManager.VERTICAL, false);
        commonSignAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                DailyTaskJsonBean dailyTaskJsonBean = (DailyTaskJsonBean) adapter.getData().get(position);
                String cssClass = dailyTaskJsonBean.getCssClass();
                if ("image".equals(cssClass)) {
                    String photoPath = dailyTaskJsonBean.getPrevalue();
                    new DisplayPhotosActivity().startDisplayPhotosActivity(CommonSignActivity.this, photoPath, 0);

                }
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.table_task_deal_tv:
                    if (dataBean != null) {
                        getPresenter().signCommonFormTask(dataBean.getId(), UserInfoUtil.getInstance().getUserId(), "");
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
        showToast("已签批");
        setResult(ActivityResultManager.FINISH_FORM_TASK_DETAIL_ACTIVITY);
        finish();
    }

    @Override
    public void onError(String tag) {
        showToast(tag);
    }
}
