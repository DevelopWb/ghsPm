package com.ghs.ghspm.models.workdesk.carnum.uploadwrongofcar;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ghs.ghspm.R;
import com.ghs.ghspm.base.BaseActivity;
import com.ghs.ghspm.base.displayPhotos.SelectPhotosToUploadFragment;
import com.ghs.ghspm.models.workdesk.carnum.CarContract;
import com.ghs.ghspm.models.workdesk.carnum.CarPresent;
import com.ghs.ghspm.tools.ActivityResultManager;
import com.ghs.ghspm.base.network.NetWorkUtil;
import com.ghs.ghspm.tools.RxScheduler;
import com.ghs.ghspm.tools.StrUtils;
import com.ghs.ghspm.tools.UserInfoUtil;
/**
 * created by tobato
 * created date 2019/7/22 13:47.
 * application   上报违停
 */
public class UploadCarMsgActivity extends BaseActivity<CarContract.ICarView, CarPresent> implements View.OnClickListener, CarContract.ICarView {

    /**
     * 京N 123456
     */
    private TextView mCarMsgUploadTitleTv;
    /**
     * 确定
     */
    private TextView mCarMsgUploadComfirmTv;
    /**
     * 取消
     */
    private TextView mCarMsgUploadCancelTv;
    private String carNo;
    private SelectPhotosToUploadFragment selectPhotosFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void getDate() {
        carNo = getIntent().getStringExtra(ActivityResultManager.CAR_NO);
        String carNo_1 = carNo.substring(0, 2);
        String carNo_2 = carNo.substring(2, carNo.length());
        mCarMsgUploadTitleTv.setText(carNo_1 + " " + carNo_2);
    }

    @Override
    public void initLayoutView() {
        initView();
        initActionBar("违停描述", null);

    }

    @Override
    public void setLayout() {
        setContentView(R.layout.activity_upload_car_msg);

    }

    @Override
    public void actionBarRightTvOnClick() {

    }

    @Override
    public CarPresent creatPresenter() {
        return new CarPresent();
    }

    private void initView() {
        mCarMsgUploadTitleTv = (TextView) findViewById(R.id.car_msg_upload_title_tv);
        mCarMsgUploadComfirmTv = (TextView) findViewById(R.id.car_msg_upload_comfirm_tv);
        mCarMsgUploadComfirmTv.setOnClickListener(this);
        mCarMsgUploadCancelTv = (TextView) findViewById(R.id.car_msg_upload_cancel_tv);
        mCarMsgUploadCancelTv.setOnClickListener(this);

        selectPhotosFragment = (SelectPhotosToUploadFragment) getSupportFragmentManager().findFragmentById(R.id.select_photos_fg);
        selectPhotosFragment.setSpanCount(4, 6, 40,false).setEtMinHeightAndHint(160,"请输入违停描述");

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.car_msg_upload_comfirm_tv:
                final String content = selectPhotosFragment.getContent();
                if (!StrUtils.isStringValueOk(content)) {
                    showNormalToast("请填写问题描述");
                    return;
                }
                showMaterialProgressDialog(null, "正在提交，请稍后...");
                RxScheduler.doOnIoThread(new RxScheduler.IOTask<Void>() {
                    @Override
                    public void doOnIOThread() {
                        String icons = selectPhotosFragment.uploadPhotosToOssForPath();
                            getPresenter().saveCarErrorInfo(UserInfoUtil.getInstance().getVillageId(),UserInfoUtil.getInstance().getPropertyId(), UserInfoUtil.getInstance().getUserId(), carNo, content, icons, "");
                    }

                });

                break;
            case R.id.car_msg_upload_cancel_tv:
                finish();
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
        String  content = (String) o;
        Toast.makeText(getApplicationContext(), content, Toast.LENGTH_LONG).show();
        finish();
    }



    @Override
    public void onError(String tag) {
        stopMaterialProgressDialog();
        showToast(tag);
    }

}
