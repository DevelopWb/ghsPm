package com.ghs.ghspm.models.workdesk.patrolForm.fillingForms;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ghs.ghspm.R;
import com.ghs.ghspm.base.BaseActivity;
import com.ghs.ghspm.bean.PatrolTaskFormListBean;
import com.ghs.ghspm.models.workdesk.patrolForm.PatrolCheckUpdateContract;
import com.ghs.ghspm.models.workdesk.patrolForm.PatrolCheckUpdatePresent;
import com.ghs.ghspm.models.workdesk.patrolForm.creatPatrolForm.PatrolTaskFormRecordActivity;
import com.ghs.ghspm.tools.ActivityResultManager;
import com.ghs.ghspm.tools.PubUtil;
import com.ghs.ghspm.tools.UserInfoUtil;

import java.util.List;
/**
 * created by tobato
 * created date 2019/5/5 10:32.
 * application   正在填写的表单
 */
public class FormFillingActivity extends BaseActivity<PatrolCheckUpdateContract.IPatrolCheckUpdateView, PatrolCheckUpdatePresent> implements PatrolCheckUpdateContract.IPatrolCheckUpdateView {


    private RecyclerView mFormFillingRecylerview;
    private FormFillingAdapter formFillingAdapter;
    private SwipeRefreshLayout form_filling_refresh;


    @Override
    public PatrolCheckUpdatePresent creatPresenter() {
        return new PatrolCheckUpdatePresent();
    }

    @Override
    public void setLayout() {
        setContentView(R.layout.activity_from_filling);

    }

    @Override
    public void initLayoutView() {
        initActionBar("正在填写的表单", "");
        initView();
    }

    @Override
    public void getDate() {
        if (PubUtil.PATROL_FORM_LIST_DATABEAN != null) {
            getPresenter().getFormFillingList(UserInfoUtil.getInstance().getVillageId(), PubUtil.PATROL_FORM_LIST_DATABEAN .getId(), PatrolCheckUpdateContract.FORMFILLING);

        }

    }

    @Override
    public void actionBarRightTvOnClick() {

    }


    public void initView() {
        mFormFillingRecylerview = (RecyclerView) findViewById(R.id.form_filling_recylerview);
        mFormFillingRecylerview.setLayoutManager(new LinearLayoutManager(this));
        formFillingAdapter = new FormFillingAdapter(R.layout.form_fiilling_item_layout, null);
        formFillingAdapter.setEmptyView(getAdapterEmptyView("很干净，一条数据都没有"));
        addDivider(true, mFormFillingRecylerview, true, true);

        mFormFillingRecylerview.setAdapter(formFillingAdapter);
        formFillingAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                PatrolTaskFormListBean.DataBean dataBean = formFillingAdapter.getData().get(position);
                Intent intent = new Intent(FormFillingActivity.this, PatrolTaskFormRecordActivity.class);
                intent.putExtra(PatrolCheckUpdateContract.PATROL_FORM_TASK_DEAL_BEAN, dataBean);
                startActivityForResult(intent, ActivityResultManager.TABLE_FORM_CONTINUE);
            }
        });

        form_filling_refresh = findViewById(R.id.form_filling_refresh);
        form_filling_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                if (PubUtil.PATROL_FORM_LIST_DATABEAN  != null) {
                    getPresenter().getFormFillingList(UserInfoUtil.getInstance().getVillageId(), PubUtil.PATROL_FORM_LIST_DATABEAN .getId(), PatrolCheckUpdateContract.FORMFILLING);
                }

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ActivityResultManager.TABLE_FORM_CONTINUE) {
            if (PubUtil.PATROL_FORM_LIST_DATABEAN  != null) {
                getPresenter().getFormFillingList(UserInfoUtil.getInstance().getVillageId(), PubUtil.PATROL_FORM_LIST_DATABEAN .getId(), PatrolCheckUpdateContract.FORMFILLING);
            }
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
            case PatrolCheckUpdateContract.FORMFILLING:
                PatrolTaskFormListBean formFillingListBean = (PatrolTaskFormListBean) o;
                if (formFillingListBean != null) {
                    List<PatrolTaskFormListBean.DataBean> data = formFillingListBean.getData();
                    formFillingAdapter.setNewData(data);
                }

                break;
        }

        form_filling_refresh.setRefreshing(false);


    }

    @Override
    public void onError(String tag) {

    }
}
