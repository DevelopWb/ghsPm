package com.ghs.ghspm.models.workdesk.patrolForm.formStatusInfo;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.ghs.ghspm.R;
import com.ghs.ghspm.base.BaseActivity;
import com.ghs.ghspm.bean.DynamicLayoutBean;
import com.ghs.ghspm.bean.PatrolFormListBean;
import com.ghs.ghspm.models.workdesk.patrolForm.PatrolCheckUpdateContract;
import com.ghs.ghspm.models.workdesk.patrolForm.PatrolCheckUpdatePresent;
import com.ghs.ghspm.models.workdesk.patrolForm.fillingForms.FormFillingActivity;
import com.ghs.ghspm.models.workdesk.patrolForm.formcomplete.FormCompleteActivity;
import com.ghs.ghspm.tools.GsonManager;
import com.ghs.ghspm.tools.PubUtil;
import com.ghs.ghspm.tools.StrUtils;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

/**
 * created by tobato
 * created date 2019/4/23 15:21.
 * application   巡检表单模板预览
 */
public class PatrolFormPreviewActivity extends BaseActivity<PatrolCheckUpdateContract.IPatrolCheckUpdateView, PatrolCheckUpdatePresent> implements PatrolCheckUpdateContract.IPatrolCheckUpdateView, View.OnClickListener {

    private RecyclerView mFormStatusInfoRv;
    private ConstraintLayout mFillingFormFl;
    private ConstraintLayout mCommitedFormFl;
    /**
     * 确定
     */
    private TextView mPatrolFormConfirmTv;
    private OnlyOneTextAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public PatrolCheckUpdatePresent creatPresenter() {
        return new PatrolCheckUpdatePresent();
    }

    @Override
    public void setLayout() {
        setContentView(R.layout.activity_form_status_info);

    }

    @Override
    public void initLayoutView() {
        initView();

    }

    @Override
    public void getDate() {
            if (PubUtil.PATROL_FORM_LIST_DATABEAN != null) {
                String formTitle = PubUtil.PATROL_FORM_LIST_DATABEAN.getFormName();
                int id = PubUtil.PATROL_FORM_LIST_DATABEAN.getId();
                initActionBar(formTitle, null);
                List<String> data = getRvData(PubUtil.PATROL_FORM_LIST_DATABEAN);
                PubUtil.PATROL_FORM_TASK_ROW_TITLE = data;
                adapter.setNewData(data);
            }
    }

    /**
     * 获取rv的数据
     */
    private List<String> getRvData(PatrolFormListBean.DataBean dataBean) {
        List<String> arrays = new ArrayList<>();
        String fieldJson = dataBean.getFieldJson();
        if (StrUtils.isStringValueOk(fieldJson)) {
            List<DynamicLayoutBean> dynamicLayoutArrays = (List<DynamicLayoutBean>) GsonManager.getInstance().parseJsonToList(dataBean.getFieldJson(), new TypeToken<List<DynamicLayoutBean>>() {
            }.getType());
            if (dynamicLayoutArrays != null && dynamicLayoutArrays.size() > 0) {
                for (DynamicLayoutBean dynamicLayoutArray : dynamicLayoutArrays) {
                    arrays.add(dynamicLayoutArray.getTitle());
                }
            }
        }
        return arrays;

    }

    @Override
    public void actionBarRightTvOnClick() {

    }

    @Override
    public void startLoading(String tag) {

    }

    @Override
    public void stopLoading(String tag) {

    }

    @Override
    public void updateView(Object o, String tag) {

    }

    @Override
    public void onError(String tag) {

    }

    private void initView() {
        mFormStatusInfoRv = (RecyclerView) findViewById(R.id.form_status_info_rv);
        adapter = new OnlyOneTextAdapter(R.layout.text_gravity_left_item);
        initRecyclerviewGridLayout(mFormStatusInfoRv, adapter, 2, GridLayoutManager.VERTICAL, false, true);
        mFillingFormFl = (ConstraintLayout) findViewById(R.id.filling_form_fl);
        mFillingFormFl.setOnClickListener(this);
        mCommitedFormFl = (ConstraintLayout) findViewById(R.id.commited_form_fl);
        mCommitedFormFl.setOnClickListener(this);
        mPatrolFormConfirmTv = (TextView) findViewById(R.id.patrol_form_confirm_tv);
        mPatrolFormConfirmTv.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.filling_form_fl:
                //正在填写的表单
                startActivity(new Intent(PatrolFormPreviewActivity.this, FormFillingActivity.class));

                break;
            case R.id.commited_form_fl:
                startActivity( new Intent(PatrolFormPreviewActivity.this, FormCompleteActivity.class));
                break;
            case R.id.patrol_form_confirm_tv:
//                //提取新表单
                PubUtil.MODIFY_PATROL_FORM_INFO = 0;
                startActivity(new Intent(this, ModifyPatrolFormInfoActivity.class));
//                overridePendingTransition(R.anim.bottom_in, 0);

                break;
        }
    }


}
