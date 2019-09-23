package com.ghs.ghspm.models.workdesk.publicfuction.universalkey;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ghs.ghspm.R;
import com.ghs.ghspm.base.BaseActivity;
import com.ghs.ghspm.bean.CellBean;
import com.ghs.ghspm.bean.TowerBean;
import com.ghs.ghspm.bean.VilliageDoorBean;
import com.ghs.ghspm.models.workdesk.publicfuction.universalkey.adapter.RecentlyAdapter;
import com.ghs.ghspm.models.workdesk.publicfuction.universalkey.adapter.UniversalKeyAdapter;
import com.ghs.ghspm.tools.PickerManager;
import com.ghs.ghspm.tools.UserInfoUtil;
import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UniversalKeyActivity extends BaseActivity<UniversialKeyContract.IUniversialKeyView, UniversialKeyPresent> implements View.OnClickListener, UniversialKeyContract.IUniversialKeyView {

    public static String MAPKEY = "map_key";
    public static String LISTKEY = "list_key";
    /**
     * 请选择
     */
    private TextView mUniversalKeySelectFloorTv;
    private TextView mUniversalKeyTowerName;
    private LinearLayout mUniversalKeySelectFloorLl;
    /**
     * 请选择
     */
    private TextView mUniversalKeySelectCellTv;
    private LinearLayout mUniversalKeySelectCellLl;
    /**
     * 一键开门
     */
    private TextView mUniversalKeyOpenDoorTv;
    private RecyclerView mUniversalKeyRv;
    private UniversalKeyAdapter universalKeyAdapter;
    private TowerBean.DataBean selectedTowerDataBean = null;//当前选中的楼的实体类
    private CellBean.DataBean selectedCellDataBean = null;//当前选中的楼对应的单元的实体类
    private RecyclerView universal_recentlyRecylerView;
    private RecentlyAdapter recentlyAdapter;
    //key 是门牌号 viue cellid
    private Map<String, String> map = new HashMap<>();
    //存储门牌号
    private List<String> list = new ArrayList<>();
    //缓存的map
    private Map<String, String> lcackMap = new HashMap<>();
    //缓存的list
    private List<String> lcakList = new ArrayList<>();
    private List<String> doorNameList;
    private Map<String, String> cellidMap;
    private TextView tv_1;
    private LinearLayout tv_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public UniversialKeyPresent creatPresenter() {
        return new UniversialKeyPresent();
    }

    @Override
    public void setLayout() {
        setContentView(R.layout.activity_universal_key);
    }

    @Override
    public void initLayoutView() {
        initView();
        initActionBar("万能钥匙", null);
    }

    @Override
    public void getDate() {
        getPresenter().getDoorNo(String.valueOf(UserInfoUtil.getInstance().getVillageId()), UniversialKeyContract.GET_DOOR_NO);
        if (doorNameList.size() > 0) {
            recentlyAdapter.setNewData(doorNameList);
            tv_1.setVisibility(View.VISIBLE);
            tv_2.setVisibility(View.VISIBLE);
        } else {
            tv_1.setVisibility(View.GONE);
            tv_2.setVisibility(View.GONE);
        }


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        selectedTowerDataBean = null;
        selectedCellDataBean = null;
        stopMaterialProgressDialog();
    }

    @Override
    public void actionBarRightTvOnClick() {

    }

    private void initView() {
        if (!Hawk.contains(LISTKEY)) {
            Hawk.put(LISTKEY, list);
        }
        if (!Hawk.contains(MAPKEY)) {
            Hawk.put(MAPKEY, map);
        }
        doorNameList = Hawk.get(LISTKEY);
        cellidMap = Hawk.get(MAPKEY);
        mUniversalKeySelectFloorTv = (TextView) findViewById(R.id.universal_key_select_floor_tv);
        mUniversalKeyTowerName = (TextView) findViewById(R.id.universal_tower_name);
        mUniversalKeySelectFloorLl = (LinearLayout) findViewById(R.id.universal_key_select_floor_ll);
        mUniversalKeySelectFloorLl.setOnClickListener(this);
        mUniversalKeySelectCellTv = (TextView) findViewById(R.id.universal_key_select_cell_tv);
        mUniversalKeySelectCellLl = (LinearLayout) findViewById(R.id.universal_key_select_cell_ll);
        mUniversalKeySelectCellLl.setOnClickListener(this);
        mUniversalKeyOpenDoorTv = (TextView) findViewById(R.id.universal_key_open_door_tv);
        mUniversalKeyOpenDoorTv.setOnClickListener(this);
        mUniversalKeyRv = (RecyclerView) findViewById(R.id.universal_key_rv);
        tv_1 = findViewById(R.id.tv_1);
        tv_2 = findViewById(R.id.tv_2);

        universalKeyAdapter = new UniversalKeyAdapter(R.layout.universal_key_item);
        GridLayoutManager manager = new GridLayoutManager(this, 3);
        mUniversalKeyRv.setLayoutManager(manager);
        mUniversalKeyRv.setAdapter(universalKeyAdapter);
        universalKeyAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                showMaterialProgressDialog(null, "正在开门，请稍后...");
                VilliageDoorBean.DataBean dataBean = (VilliageDoorBean.DataBean) adapter.getData().get(position);
                getPresenter().openByDeviceId(String.valueOf(dataBean.getId()), String.valueOf(UserInfoUtil.getInstance().getUserId()), UniversialKeyContract.OPEN_BY_DEVICE_NO);
            }
        });
//   最近开门
        universal_recentlyRecylerView = findViewById(R.id.universal_recentlyRecylerView);
        GridLayoutManager manager_recently = new GridLayoutManager(this, 3);
        universal_recentlyRecylerView.setLayoutManager(manager_recently);
        recentlyAdapter = new RecentlyAdapter(R.layout.recently_item_layout);
        recentlyAdapter.setEmptyView(getAdapterEmptyView("暂时没有记录"));
        universal_recentlyRecylerView.setAdapter(recentlyAdapter);
        //最近开门点击事件
        recentlyAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (cellidMap.size() != 0 && doorNameList.size() != 0) {
                    String cellid = cellidMap.get(doorNameList.get(position));
                    Log.i("TAG", cellid + "-----cellid");
                    getPresenter().openByCell(cellid, String.valueOf(UserInfoUtil.getInstance().getUserId()), UniversialKeyContract.OPEN_BY_CELL);
                }

            }
        });


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.universal_key_select_floor_ll:
                    getPresenter().getTowerNo(UserInfoUtil.getInstance().getVillageId(), UniversialKeyContract.GET_TOWER_NO);
                break;
            case R.id.universal_key_select_cell_ll:
                if (selectedTowerDataBean == null) {
                    showNormalToast("请先选择楼号");
                    return;
                }
                getPresenter().getCellNo(UserInfoUtil.getInstance().getVillageId(), selectedTowerDataBean.getId(), UniversialKeyContract.GET_CELL_NO);
                break;
            case R.id.universal_key_open_door_tv:
                if (selectedTowerDataBean == null) {
                    showNormalToast("请选择楼号");
                } else {
                    if (selectedCellDataBean == null) {
                        showNormalToast("请选择单元");
                    } else {
                            showMaterialProgressDialog(null, "正在开门，请稍后...");

                            String cellId = String.valueOf(selectedCellDataBean.getId());
                                getPresenter().openByCell(cellId, String.valueOf(UserInfoUtil.getInstance().getUserId()), UniversialKeyContract.OPEN_BY_CELL);
                    }
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
        stopMaterialProgressDialog();

        switch (tag) {
            case UniversialKeyContract.GET_DOOR_NO:
                VilliageDoorBean villiageDoorBean = (VilliageDoorBean) o;
                if (villiageDoorBean != null) {
                    if (villiageDoorBean.getData() != null) {
                        universalKeyAdapter.setNewData(villiageDoorBean.getData());
                    }
                }
                break;
            case UniversialKeyContract.GET_TOWER_NO:
                TowerBean towerBean = (TowerBean) o;
                if (towerBean != null) {
                    if (towerBean.getData() != null) {
                        final List<TowerBean.DataBean> towers = towerBean.getData();
                        if (towers.size() > 0) {

                            PickerManager.getInstance().showOptionPicker(UniversalKeyActivity.this, towers, new PickerManager.OnOptionPickerSelectedListener() {
                                @Override
                                public void onOptionsSelect(int options1, int option2, int options3, View v) {
                                    //返回的分别是三个级别的选中位置
                                    selectedTowerDataBean = towers.get(options1);
                                    String tx = selectedTowerDataBean.getPickerViewText();
                                    mUniversalKeySelectFloorTv.setText(tx.replace("号楼", ""));
                                    mUniversalKeyTowerName.setText("号楼");
                                    mUniversalKeySelectCellTv.setText("请选择");
                                    selectedCellDataBean = null;
                                }
                            });

                        }
                    }
                }
                break;
            case UniversialKeyContract.GET_CELL_NO:
                CellBean cellBean = (CellBean) o;
                if (cellBean != null) {
                    if (cellBean.getData() != null) {
                        final List<CellBean.DataBean> cellsList = cellBean.getData();
                        if (cellsList.size() > 0) {

                            PickerManager.getInstance().showOptionPicker(UniversalKeyActivity.this, cellsList, new PickerManager.OnOptionPickerSelectedListener() {
                                @Override
                                public void onOptionsSelect(int options1, int option2, int options3, View v) {
                                    //返回的分别是三个级别的选中位置
                                    selectedCellDataBean = cellsList.get(options1);
                                    String tx = selectedCellDataBean.getPickerViewText();
                                    mUniversalKeySelectCellTv.setText(tx.replace("单元", ""));
                                }
                            });
                        }
                    }
                }
                break;

            case UniversialKeyContract.OPEN_BY_CELL:
                int code = (Integer) o;
                if (1000 == code) {
                    showNormalToast("开门成功");
                    if (selectedTowerDataBean != null && selectedCellDataBean != null) {
                        if (!doorNameList.contains(selectedTowerDataBean.getTowerNumber() + "\n" + selectedCellDataBean.getCellName())) {
                            if (doorNameList.size() >= 6) {
                                doorNameList.add(0, selectedTowerDataBean.getTowerNumber() + "\n" + selectedCellDataBean.getCellName());
                                cellidMap.remove(doorNameList.get(0));
                                cellidMap.put(doorNameList.get(0), selectedCellDataBean.getId() + "");
                                doorNameList.remove(5);
                            } else {
                                doorNameList.add(selectedTowerDataBean.getTowerNumber() + "\n" + selectedCellDataBean.getCellName());
                                cellidMap.put(selectedTowerDataBean.getTowerNumber() + "\n" + selectedCellDataBean.getCellName(), selectedCellDataBean.getId() + "");
                            }
//
                            lcakList.addAll(doorNameList);
                            lcackMap.putAll(cellidMap);
                            Hawk.put(LISTKEY, lcakList);
                            Hawk.put(MAPKEY, lcackMap);
                            lcakList.clear();
                            lcackMap.clear();

                        }
                    }
                } else {
                    showNormalToast("开门失败");
                }
                break;
            case UniversialKeyContract.OPEN_BY_DEVICE_NO:
                int code2 = (Integer) o;
                if (1000 == code2) {
                    showNormalToast("开门成功");

                } else {
                    showNormalToast("开门失败");
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onError(String tag) {
        showNormalToast(tag);
        stopMaterialProgressDialog();

    }
}
