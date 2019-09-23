package com.ghs.ghspm.models.workOrder.workOrderDetail;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.ghs.ghspm.R;
import com.ghs.ghspm.base.BaseActivity;
import com.ghs.ghspm.base.displayPhotos.SelectPhotosToUploadFragment;
import com.ghs.ghspm.base.network.RequestStatus;
import com.ghs.ghspm.bean.WorkOrderDetailBean;
import com.ghs.ghspm.models.workOrder.WorkOrderContract;
import com.ghs.ghspm.models.workOrder.WorkOrderPresent;
import com.ghs.ghspm.tools.PubUtil;
import com.ghs.ghspm.tools.RxScheduler;
import com.ghs.ghspm.tools.StrUtils;
import com.ghs.ghspm.tools.UserInfoUtil;

/**
 * created by tobato
 * created date 2019/7/11 15:04.
 * application  写进展  和投诉建议共用一个布局
 */
public class WriteProgressActivity extends BaseActivity<WorkOrderContract.IWorkOrderView, WorkOrderPresent> implements WorkOrderContract.IWorkOrderView, View.OnClickListener {

    /**
     * 请描述您对产品的使用建议
     */
    private EditText mWriteProgressContentEt;
    private SelectPhotosToUploadFragment mWriteProgressFragment;
    /**
     * 提交
     */
    private TextView mWriteProgressConfirmTv;
    private WorkOrderDetailBean.DataBean.ServiceWorkDOBean dataBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public WorkOrderPresent creatPresenter() {
        return new WorkOrderPresent();
    }

    @Override
    public void setLayout() {

    }

    @Override
    public void initLayoutView() {
        setContentView(R.layout.activity_write_progress);
        initView();
        if (0 == PubUtil.LAYOUT_TYPE) {
            initActionBar("投诉建议", null,R.drawable.bg_gray_only_bottom_deeper_gray_shape_1px);
            mWriteProgressContentEt.setHint("请填写问题描述...");
            mWriteProgressConfirmTv.setText("提交");
        } else if (1 == PubUtil.LAYOUT_TYPE) {
            initActionBar("写进展", null,R.drawable.bg_gray_only_bottom_deeper_gray_shape_1px);
            mWriteProgressContentEt.setHint("请输入进展详情...");
            mWriteProgressConfirmTv.setText("发送");

        }
    }

    @Override
    public void getDate() {
        dataBean = (WorkOrderDetailBean.DataBean.ServiceWorkDOBean) getIntentParcelableData();
    }

    @Override
    public void actionBarRightTvOnClick() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.write_progress_confirm_tv:
                if (!isNetWorkConnected()) {
                    return;
                }
                final String content = mWriteProgressContentEt.getText().toString().trim();
                if (!StrUtils.isStringValueOk(content)) {
                    if (0 == PubUtil.LAYOUT_TYPE) {
                        showToast("请填写问题描述...");
                    } else if (1 == PubUtil.LAYOUT_TYPE) {
                        showToast("请输入进展详情...");
                    }
                    return;
                }


                showMaterialProgressDialog(null, "正在提交，请稍后...");
                RxScheduler.doOnIoThread(new RxScheduler.IOTask<Void>() {
                    @Override
                    public void doOnIOThread() {
                        String iconServerPath1 = "";
                        String iconServerPath2 = "";
                        String iconServerPath3 = "";
                        String icons = mWriteProgressFragment.uploadPhotosToOssForPath();
                        if (StrUtils.isStringValueOk(icons)) {
                            if (icons.contains(",")) {
                                String[] iconArrays = icons.split(",");
                                int lengh = iconArrays.length;
                                iconServerPath1 = iconArrays[0];
                                iconServerPath2 = iconArrays[1];
                                if (3 == lengh) {
                                    iconServerPath3 = iconArrays[2];
                                }
                            } else {
                                iconServerPath1 = icons;
                            }
                        }
                            if (0 == PubUtil.LAYOUT_TYPE) {
                                getPresenter().uploadSuggestion(UserInfoUtil.getInstance().getUserId(), UserInfoUtil.getInstance().getVillageId(), content, iconServerPath1, iconServerPath2, iconServerPath3, RequestStatus.UPDATE);
                            } else if (1 == PubUtil.LAYOUT_TYPE) {
                                //  调用写进展的接口
                                if (dataBean != null) {
                                    getPresenter().writeProgress(mUserInfoUtil.getUserId(), mUserInfoUtil.getUserName(), dataBean.getId(), content, icons, WorkOrderContract.WORK_ORDER_WRITE_PROGRESS);
                                }
                            }
                    }
                });
                break;
            default:
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
        stopMaterialProgressDialog();
        showToast("已提交");
        finish();
    }

    @Override
    public void onError(String tag) {
        stopMaterialProgressDialog();
        showToast(tag);
    }

    private void initView() {
        mWriteProgressContentEt = (EditText) findViewById(R.id.write_progress_content_et);
        mWriteProgressFragment = (SelectPhotosToUploadFragment) getSupportFragmentManager().findFragmentById(R.id.write_progress_fragment);
        mWriteProgressFragment.setSpanCount(4, 9, 20, true);
        mWriteProgressConfirmTv = (TextView) findViewById(R.id.write_progress_confirm_tv);
        mWriteProgressConfirmTv.setOnClickListener(this);
    }
}
