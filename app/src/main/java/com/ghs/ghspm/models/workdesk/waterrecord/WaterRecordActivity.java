package com.ghs.ghspm.models.workdesk.waterrecord;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ghs.ghspm.R;
import com.ghs.ghspm.base.BaseActivity;
import com.ghs.ghspm.base.network.RequestStatus;
import com.ghs.ghspm.bean.CellBean;
import com.ghs.ghspm.bean.TowerBean;
import com.ghs.ghspm.bean.WaterRecordBean;
import com.ghs.ghspm.models.workdesk.publicfuction.universalkey.UniversialKeyContract;
import com.ghs.ghspm.models.workdesk.publicfuction.universalkey.UniversialKeyModel;
import com.ghs.ghspm.tools.ActivityResultManager;
import com.ghs.ghspm.tools.PickerManager;
import com.ghs.ghspm.tools.UserInfoUtil;

import java.util.ArrayList;
import java.util.List;

public class WaterRecordActivity extends BaseActivity<WaterRecordContract.IWaterRecordView, WaterRecordPresent> implements View.OnClickListener, WaterRecordContract.IWaterRecordView, RequestStatus {


    /**
     * 请选择
     */
    private TextView mWaterRecordSelectFloorTv;
    private TextView mTowerName;
    private LinearLayout mWaterRecordSelectTowerLl;
    /**
     * 请选择
     */
    private TextView mWaterRecordSelectCellTv;
    private LinearLayout mWaterRecordSelectCellLl;
    private RecyclerView mWaterRecordRv;
    private TowerBean.DataBean selectedTowerInfo = null;//已选中的楼信息
    private CellBean.DataBean selectedCellInfo = null;//已选中的单元信息
    private WaterRecordAdapter waterRecordAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void getDate() {
    }

    @Override
    public void initLayoutView() {
        initView();
        initActionBar("水电表抄录", null);

    }


    @Override
    public void setLayout() {
        setContentView(R.layout.activity_water_record);

    }

    @Override
    public void actionBarRightTvOnClick() {

    }

    @Override
    public WaterRecordPresent creatPresenter() {
        return new WaterRecordPresent();
    }

    private void initView() {
        mWaterRecordSelectFloorTv = (TextView) findViewById(R.id.water_record_select_floor_tv);
        mTowerName = (TextView) findViewById(R.id.water_record_tower_name_tv);
        mWaterRecordSelectTowerLl = (LinearLayout) findViewById(R.id.water_record_select_tower_ll);
        mWaterRecordSelectTowerLl.setOnClickListener(this);
        mWaterRecordSelectCellTv = (TextView) findViewById(R.id.water_record_select_cell_tv);
        mWaterRecordSelectCellLl = (LinearLayout) findViewById(R.id.water_record_select_cell_ll);
        mWaterRecordSelectCellLl.setOnClickListener(this);
        mWaterRecordRv = (RecyclerView) findViewById(R.id.water_record_rv);
        waterRecordAdapter = new WaterRecordAdapter(R.layout.water_record_item);
        initRecyclerview(mWaterRecordRv, waterRecordAdapter, LinearLayoutManager.VERTICAL, false);
        addDivider(true,mWaterRecordRv,false,true);
        waterRecordAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                WaterRecordBean.DataBean dataBean = (WaterRecordBean.DataBean) adapter.getData().get(position);
                Intent intent = new Intent(WaterRecordActivity.this,SaveWaterRecordActivity.class);
                intent.putExtra(ActivityResultManager.WATER_ROOM_ID,dataBean.getRoomId());
                startActivityForResult(intent, ActivityResultManager.SAVE_WATER_RECORD);
            }
        });
        waterRecordAdapter.setEmptyView(getAdapterEmptyView("选择楼号和单元号查看记录"));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode== ActivityResultManager.SAVE_WATER_RECORD) {
            getPresenter().getRoomRecordList(UserInfoUtil.getInstance().getPropertyId(), "water", selectedCellInfo.getId(), WaterRecordContract.GET_EOOM_RECORD_LIST);

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.water_record_select_tower_ll:
                    new UniversialKeyModel().getTowerNo(UserInfoUtil.getInstance().getVillageId(), this, UniversialKeyContract.GET_TOWER_NO);
                break;
            case R.id.water_record_select_cell_ll:
                String towerName = mWaterRecordSelectFloorTv.getText().toString().trim();
                if ("请选择".equals(towerName)) {
                    showNormalToast("请先选择楼号");
                } else {
                        new UniversialKeyModel().getCellNo(UserInfoUtil.getInstance().getVillageId(),selectedTowerInfo.getId(), this, UniversialKeyContract.GET_CELL_NO);
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
        WaterRecordBean waterRecordBean = (WaterRecordBean) o;
        if (waterRecordBean != null) {
            List<WaterRecordBean.DataBean> dataBeanList = waterRecordBean.getData();
            if (dataBeanList != null) {
                if (dataBeanList.size() > 0) {
                    waterRecordAdapter.setNewData(dataBeanList);
                }
            }

        }
    }

    @Override
    public void onStart(String tag) {

    }

    @Override
    public void onSuccess(Object o, String tag) {
        switch (tag) {
            case UniversialKeyContract.GET_TOWER_NO://获取楼号
                TowerBean towerBean = (TowerBean) o;
                if (towerBean != null) {
                    if (towerBean.getData() != null) {
                        final List<TowerBean.DataBean> towers = towerBean.getData();
                        if (towers.size() > 0) {
                            //条件选择器
                            PickerManager.getInstance().showOptionPicker(this, towers,new PickerManager.OnOptionPickerSelectedListener() {
                                @Override
                                public void onOptionsSelect(int options1, int option2, int options3, View v) {
                                    //返回的分别是三个级别的选中位置
                                    selectedTowerInfo = towers.get(options1);
                                    String tx = selectedTowerInfo.getPickerViewText();
                                    mWaterRecordSelectFloorTv.setText(tx.replace("号楼", ""));
                                    mTowerName.setText("号楼");
                                    mWaterRecordSelectCellTv.setText("请选择");
                                    waterRecordAdapter.setNewData(new ArrayList<WaterRecordBean.DataBean>());
                                }
                            });
                        }
                    }
                }
                break;
            case UniversialKeyContract.GET_CELL_NO://获单元号
                CellBean cellBean = (CellBean) o;
                if (cellBean != null) {
                    if (cellBean.getData() != null) {
                        final List<CellBean.DataBean> cellsList = cellBean.getData();
                        if (cellsList.size() > 0) {
                            //条件选择器
                            PickerManager.getInstance().showOptionPicker(this, cellsList,new PickerManager.OnOptionPickerSelectedListener() {
                                @Override
                                public void onOptionsSelect(int options1, int option2, int options3, View v) {
//返回的分别是三个级别的选中位置
                                    selectedCellInfo = cellsList.get(options1);
                                    String tx = selectedCellInfo.getPickerViewText();
                                    mWaterRecordSelectCellTv.setText(tx.replace("单元", ""));
                                    getPresenter().getRoomRecordList(UserInfoUtil.getInstance().getPropertyId(), "water", selectedCellInfo.getId(), WaterRecordContract.GET_EOOM_RECORD_LIST);
                                }
                            });
                        }
                    }
                }
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
