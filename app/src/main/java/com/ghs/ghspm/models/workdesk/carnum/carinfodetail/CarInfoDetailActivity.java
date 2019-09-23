package com.ghs.ghspm.models.workdesk.carnum.carinfodetail;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ghs.ghspm.R;
import com.ghs.ghspm.base.BaseActivity;
import com.ghs.ghspm.bean.CarInfoBean;
import com.ghs.ghspm.models.workdesk.carnum.CarContract;
import com.ghs.ghspm.models.workdesk.carnum.CarPresent;
import com.ghs.ghspm.models.workdesk.carnum.uploadwrongofcar.UploadCarMsgActivity;
import com.ghs.ghspm.tools.ActivityResultManager;
import com.ghs.ghspm.tools.StrUtils;
import com.ghs.ghspm.tools.UserInfoUtil;

import java.util.ArrayList;
import java.util.List;
/**
 * created by tobato
 * created date 2019/7/22 13:44.
 * application   车辆信息详情
 */
public class CarInfoDetailActivity extends BaseActivity<CarContract.ICarView, CarPresent> implements View.OnClickListener, CarContract.ICarView {

    /**
     * 京N 123456
     */
    private TextView mCarInfoDetailTitleTv;
    private RecyclerView mCarInfoDetailRv;
    /**
     * 上报违停
     */
    private TextView mCarInfoDetailUploadTv;
    /**
     * 取消
     */
    private TextView mCarInfoDetailCancelTv;
    private CarInfoDetailAdapter carInfoDetailAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public CarPresent creatPresenter() {
        return new CarPresent();
    }

    @Override
    public void setLayout() {
        setContentView(R.layout.activity_car_info_detail);

    }

    @Override
    public void initLayoutView() {
        initView();
        initActionBar("车辆信息", null);
    }

    @Override
    public void getDate() {
        String carNo = getIntent().getStringExtra(ActivityResultManager.CAR_NO);
        String carNo_1 = carNo.substring(0, 2);
        String carNo_2 = carNo.substring(2, carNo.length());
        mCarInfoDetailTitleTv.setText(carNo_1 + " " + carNo_2);
        getPresenter().getCarInfo(UserInfoUtil.getInstance().getPropertyId(), UserInfoUtil.getInstance().getVillageId(), carNo, CarContract.CAR_INFO);
    }

    @Override
    public void actionBarRightTvOnClick() {

    }

    private void initView() {
        mCarInfoDetailTitleTv = (TextView) findViewById(R.id.car_info_detail_title_tv);
        mCarInfoDetailRv = (RecyclerView) findViewById(R.id.car_info_detail_rv);
        carInfoDetailAdapter = new CarInfoDetailAdapter(R.layout.car_info_detail_item);
        initRecyclerview(mCarInfoDetailRv, carInfoDetailAdapter, LinearLayoutManager.VERTICAL, false);
        mCarInfoDetailUploadTv = (TextView) findViewById(R.id.car_info_detail_upload_tv);
        mCarInfoDetailUploadTv.setOnClickListener(this);
        mCarInfoDetailCancelTv = (TextView) findViewById(R.id.car_info_detail_cancel_tv);
        mCarInfoDetailCancelTv.setOnClickListener(this);
        carInfoDetailAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (1 == position) {
                    TextView textView = (TextView) adapter.getViewByPosition(mCarInfoDetailRv, position, R.id.car_info_detail_item_value_tv);
                    if (textView != null) {
                        final String tv = textView.getText().toString().trim();
                        makeAPhoneCall(tv);
                    }

                }
            }
        });
    }

    @Override
    public void startLoading(String tag) {

    }    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.car_info_detail_upload_tv:
                String carNo = getIntent().getStringExtra(ActivityResultManager.CAR_NO);
                Intent intent = new Intent(this, UploadCarMsgActivity.class);
                intent.putExtra(ActivityResultManager.CAR_NO, carNo);
                startActivity(intent);
                break;
            case R.id.car_info_detail_cancel_tv:
                finish();
                break;
        }
    }

    @Override
    public void stopLoading(String tag) {

    }

    @Override
    public void updateView(Object o, String tag) {

        switch (tag) {
            case CarContract.CAR_INFO:
                CarInfoBean carInfoBean = (CarInfoBean) o;
                if (carInfoBean != null) {
                    CarInfoBean.DataBean dataBean = carInfoBean.getData();
                    if (dataBean != null) {
                        List<String> arrays = new ArrayList<>();
                        arrays.add(StrUtils.isStringValueOk(dataBean.getFullName()) ? dataBean.getFullName() : "暂无信息");
                        arrays.add(StrUtils.isStringValueOk(dataBean.getMobile()) ? dataBean.getMobile() : "暂无信息");
                        arrays.add(StrUtils.isStringValueOk(dataBean.getEnterTime()) ? dataBean.getEnterTime() : "暂无信息");
                        arrays.add(StrUtils.isStringValueOk(dataBean.getOutTime()) ? dataBean.getOutTime() : "暂无信息");
                        arrays.add(StrUtils.isStringValueOk(dataBean.getStayTime()) ? dataBean.getStayTime() : "暂无信息");
                        arrays.add(StrUtils.isStringValueOk(dataBean.getCarFee()) ? dataBean.getCarFee() : "暂无信息");
                        carInfoDetailAdapter.setNewData(arrays);
                    }
                }
                break;
            case CarContract.SAVE_CAR_ERRO_INFO:
                break;
            default:
                break;
        }

    }

    @Override
    public void onError(String tag) {
        stopMaterialProgressDialog();
        showToast(tag);
    }


}
