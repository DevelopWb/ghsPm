package com.ghs.ghspm.models.workdesk.ownerinfo;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ghs.ghspm.R;
import com.ghs.ghspm.base.BaseActivity;
import com.ghs.ghspm.bean.CarMessageBean;
import com.ghs.ghspm.bean.CellBean;
import com.ghs.ghspm.bean.OwnerMessageBean;
import com.ghs.ghspm.bean.OwnerPropretyBean;
import com.ghs.ghspm.bean.OwnerWaterBillBean;
import com.ghs.ghspm.bean.RoomBean;
import com.ghs.ghspm.bean.TowerBean;
import com.ghs.ghspm.models.workdesk.ownerinfo.owneradapter.CarMessageAdapter;
import com.ghs.ghspm.models.workdesk.ownerinfo.owneradapter.OwnerCallerAdapter;
import com.ghs.ghspm.models.workdesk.ownerinfo.owneradapter.OwnerEmptyAdapter;
import com.ghs.ghspm.models.workdesk.ownerinfo.owneradapter.OwnerLeftNameAdapter;
import com.ghs.ghspm.models.workdesk.ownerinfo.owneradapter.OwnerPropretyAdapter;
import com.ghs.ghspm.models.workdesk.ownerinfo.owneradapter.OwnerRightAdapter;
import com.ghs.ghspm.models.workdesk.ownerinfo.owneradapter.WaterbillAdapter;
import com.ghs.ghspm.tools.PickerManager;
import com.ghs.ghspm.tools.UserInfoUtil;

import java.util.ArrayList;
import java.util.List;
/**
 * created by tobato
 * created date 2019/8/27 15:16.
 * application   业主信息
 */
public class OwnerInfoeActivity extends BaseActivity<OwnerInfoContract.OwnerInfoView, OwnerInfoPresenter> implements View.OnClickListener, OwnerInfoContract.OwnerInfoView, OwnerInfoContract.LeftOnClickListenter {

    private LinearLayout mOwnerInfoTower;
    private LinearLayout mOwnerInfoUnit;
    private LinearLayout mOwnerInfoRoom;
    private RecyclerView mOwnerInfoLeft;
    private RecyclerView mOwnerInfoRight;
    /**
     * 楼号
     */
    private TextView mOwnerInfoTowerName;
    /**
     * 单元
     */
    private TextView mOwnerInfoUnitName;
    /**
     * 房间
     */
    private TextView mOwnerInfoRoomName;
    private TowerBean.DataBean selectedTowerDataBean = null;//当前选中的楼的实体类
    private CellBean.DataBean selectedCellDataBean = null;//当前选中的楼对应的单元的实体类
    private String roomNumber; //房间id
    private OwnerRightAdapter ownerRightAdapter;
    private long roomid;
    private WaterbillAdapter waterbillAdapter;
    private OwnerLeftNameAdapter ownerLeftNameAdapter;
    private OwnerPropretyAdapter propretyAdapter;
    private List<OwnerMessageBean.DataBean> ownerList;
    private List<OwnerPropretyBean.DataBean> data;
    private OwnerEmptyAdapter ownerEmptyAdapter;
    private CarMessageAdapter messageAdapter;
    private LinearLayout left_ly;
    private List<OwnerWaterBillBean.DataBean> waterList;


    @Override
    public OwnerInfoPresenter creatPresenter() {
        return new OwnerInfoPresenter();
    }

    @Override
    public void setLayout() {
        setContentView(R.layout.activity_owner_infoe);

    }

    @Override
    public void initLayoutView() {
        initActionBar("业主信息", "");
        initView();
    }

    @Override
    public void getDate() {
        List<String> leftName = new ArrayList<>();
        leftName.add("基本信息");
        leftName.add("家人信息");
        leftName.add("租客信息");
        leftName.add("车辆信息");
        leftName.add("水费");
        leftName.add("物业费");
        leftName.add("停车费");
        WindowManager wm = (WindowManager) this
                .getSystemService(Context.WINDOW_SERVICE);
        int height = (int) (wm.getDefaultDisplay().getHeight() * 0.8);
        ownerLeftNameAdapter = new OwnerLeftNameAdapter(this, height);
        ownerLeftNameAdapter.setThisPosition(100);
        ownerLeftNameAdapter.notifyDataSetChanged();

        ownerLeftNameAdapter.setData(leftName);
        ownerLeftNameAdapter.setLeftOnClickListenter(this);
        mOwnerInfoLeft.setLayoutManager(new LinearLayoutManager(this));


        mOwnerInfoLeft.setAdapter(ownerLeftNameAdapter);

        ownerRightAdapter = new OwnerRightAdapter(this);
        mOwnerInfoRight.setLayoutManager(new LinearLayoutManager(this));

        waterbillAdapter = new WaterbillAdapter(R.layout.owner_waterbill_item1_layout);
        waterbillAdapter.setEmptyView(getAdapterEmptyView("很干净，一条数据都没有"));

        propretyAdapter = new OwnerPropretyAdapter(R.layout.owner_proprety_item_layout);
        propretyAdapter.setEmptyView(getAdapterEmptyView("很干净，一条数据都没有"));

        ownerEmptyAdapter = new OwnerEmptyAdapter(R.layout.empty_view_list_layout);
        ownerEmptyAdapter.setEmptyView(getAdapterEmptyView("很干净，一条数据都没有"));

        //车辆信息
        messageAdapter = new CarMessageAdapter(R.layout.car_message_layout, this);
        messageAdapter.setEmptyView(getAdapterEmptyView("很干净，一条数据都没有"));
        showFristEmpty();


    }

    @Override
    public void actionBarRightTvOnClick() {

    }

    public void showFristEmpty() {
        List<String> list = new ArrayList<>();
        OwnerCallerAdapter adapter = new OwnerCallerAdapter(R.layout.empty_view, list);
        adapter.setEmptyView(getAdapterEmptyView("选择楼号、单元号及房间号查询信息"));
        mOwnerInfoRight.setAdapter(adapter);
    }

    public void initView() {

        mOwnerInfoTower = (LinearLayout) findViewById(R.id.ownerInfo_tower);
        mOwnerInfoUnit = (LinearLayout) findViewById(R.id.ownerInfo_unit);
        mOwnerInfoRoom = (LinearLayout) findViewById(R.id.ownerInfo_room);
        mOwnerInfoLeft = (RecyclerView) findViewById(R.id.ownerInfo_left);
        mOwnerInfoRight = (RecyclerView) findViewById(R.id.ownerInfo_right);
        mOwnerInfoTower.setOnClickListener(this);
        mOwnerInfoUnit.setOnClickListener(this);
        mOwnerInfoRoom.setOnClickListener(this);

        mOwnerInfoTowerName = (TextView) findViewById(R.id.ownerInfo_tower_name);
        mOwnerInfoUnitName = (TextView) findViewById(R.id.ownerInfo_unit_name);
        mOwnerInfoRoomName = (TextView) findViewById(R.id.ownerInfo_room_name);

        left_ly = findViewById(R.id.left_ly);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.ownerInfo_tower:

                    getPresenter().getTower("" + UserInfoUtil.getInstance().getVillageId(), OwnerInfoContract.GET_TOWER_NAME);

                break;
            case R.id.ownerInfo_unit:
                if (selectedTowerDataBean == null) {
                    showNormalToast("请先选择楼号");
                    return;
                }
                String towerId = String.valueOf(selectedTowerDataBean.getId());
                if (selectedCellDataBean == null) {
                    mOwnerInfoRoomName.setText("房间");
                }
                getPresenter().getRation(String.valueOf(UserInfoUtil.getInstance().getVillageId()), towerId, OwnerInfoContract.GET_UNIT_NAME);
                break;
            case R.id.ownerInfo_room:

                if (selectedTowerDataBean == null) {
                    showNormalToast("请先选择楼号");
                    return;
                } else {
                    if (selectedCellDataBean == null) {
                        showNormalToast("请先选择单元号");
                        return;
                    }
                    showMaterialProgressDialog();
                    getPresenter().getRoom(String.valueOf(UserInfoUtil.getInstance().getVillageId()), String.valueOf(selectedCellDataBean.getId()), OwnerInfoContract.GET_ROOM_NAME);
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
        boolean waterFlag = false;
        String TAG = "";
        if (tag.length() == 15) {
            String substring = tag.substring(0, tag.indexOf("#"));
            TAG = substring;
        } else {
            TAG = tag;
        }
        switch (TAG) {

            case OwnerInfoContract.GET_TOWER_NAME:

                TowerBean towerBean = (TowerBean) o;
                if (towerBean != null) {
                    if (towerBean.getData() != null) {
                        final List<TowerBean.DataBean> towers = towerBean.getData();
                        if (towers.size() > 0) {
                            PickerManager.getInstance().showOptionPicker(OwnerInfoeActivity.this, towers, new PickerManager.OnOptionPickerSelectedListener() {
                                @Override
                                public void onOptionsSelect(int options1, int option2, int options3, View v) {
                                    //返回的分别是三个级别的选中位置
                                    mOwnerInfoUnitName.setText("单元");
                                    mOwnerInfoRoomName.setText("房间");
                                    selectedTowerDataBean = towers.get(options1);
                                    String tx = selectedTowerDataBean.getPickerViewText();
                                    mOwnerInfoTowerName.setText(tx);
                                    selectedCellDataBean = null;
                                    roomNumber = null;
                                    pushData();
                                }
                            });
                        }
                    }
                }


                break;


            case OwnerInfoContract.GET_UNIT_NAME:
                CellBean cellBean = (CellBean) o;
                if (cellBean != null) {
                    if (cellBean.getData() != null) {
                        final List<CellBean.DataBean> cellsList = cellBean.getData();
                        if (cellsList.size() > 0) {

                            PickerManager.getInstance().showOptionPicker(OwnerInfoeActivity.this, cellsList, new PickerManager.OnOptionPickerSelectedListener() {
                                @Override
                                public void onOptionsSelect(int options1, int option2, int options3, View v) {
                                    //返回的分别是三个级别的选中位置
                                    mOwnerInfoRoomName.setText("房间");
                                    selectedCellDataBean = cellsList.get(options1);
                                    String tx = selectedCellDataBean.getPickerViewText();
                                    mOwnerInfoUnitName.setText(tx);
                                    roomNumber = null;
                                    pushData();
                                }
                            });
                        }
                    }
                }

                break;
            case OwnerInfoContract.GET_ROOM_NAME:
                stopMaterialProgressDialog();
                RoomBean getRoomBean = (RoomBean) o;
                if (getRoomBean.getData() != null) {
                    final List<RoomBean.DataBean> roomlist = getRoomBean.getData();
                    if (roomlist.size() > 0) {
                        PickerManager.getInstance().showOptionPicker(OwnerInfoeActivity.this, roomlist, new PickerManager.OnOptionPickerSelectedListener() {
                            @Override
                            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                                //返回的分别是三个级别的选中位置
                                roomNumber = roomlist.get(options1).getRoomNumber();
                                mOwnerInfoRoomName.setText(roomNumber + "室");
                                roomid = roomlist.get(options1).getId();
                                pushData();

                            }
                        });

                    }
                }

                break;

            case OwnerInfoContract.OWNER_MESSAGE:

                OwnerMessageBean ownerMessageBean = (OwnerMessageBean) o;
                if (ownerMessageBean != null) {
                    ownerList = ownerMessageBean.getData();

                    if (ownerList.size() > 0) {
                        String userIdJiequ = tag.substring(tag.indexOf("#"));

                        if (userIdJiequ.equals(OwnerInfoContract.OWNET_TYPE_1)) {

                            ownerRightAdapter.setOwnerList(ownerList, OwnerInfoContract.OWNER_USER);
                            mOwnerInfoRight.setAdapter(ownerRightAdapter);

                        } else if (userIdJiequ.equals(OwnerInfoContract.OWNET_TYPE_2)) {

                            ownerRightAdapter.setOwnerList(ownerList, OwnerInfoContract.OWNER_HOME);
                            mOwnerInfoRight.setAdapter(ownerRightAdapter);

                        } else if (userIdJiequ.equals(OwnerInfoContract.OWNET_TYPE_3)) {

                            ownerRightAdapter.setOwnerList(ownerList, OwnerInfoContract.OWNER_TENANT);
                            mOwnerInfoRight.setAdapter(ownerRightAdapter);

                        }

                    } else {


                        mOwnerInfoRight.setAdapter(ownerEmptyAdapter);
                        ownerEmptyAdapter.setNewData(ownerList);


                    }

                }


                break;

            case OwnerInfoContract.GET_WATER_BILL:

                OwnerWaterBillBean waterBillBean = (OwnerWaterBillBean) o;
                if (waterBillBean != null) {
                    waterList = waterBillBean.getData();
                    if (waterList.size() > 0) {

                        for (int i = 0; i < waterList.size(); i++) {
                            List<OwnerWaterBillBean.DataBean.BillDOListBean> billDOList = waterList.get(i).getBillDOList();
                            if (billDOList != null) {
                                if (billDOList.size() > 0) {
                                    waterFlag = true;
                                }
                            }

                        }

                    }

                    if (waterFlag) {
                        mOwnerInfoRight.setAdapter(waterbillAdapter);
                        waterbillAdapter.setNewData(waterList);
                    } else {
                        showEmpty();
                    }

                } else {
                    showEmpty();
                }
                break;

            case OwnerInfoContract.GET_PROPRETY:
                OwnerPropretyBean ownerPropretyBean = (OwnerPropretyBean) o;
                if (ownerPropretyBean != null) {
                    data = ownerPropretyBean.getData();
                    propretyAdapter.setNewData(data);
                    mOwnerInfoRight.setAdapter(propretyAdapter);
                }

                break;

            case OwnerInfoContract.OWNER_CAR:

                CarMessageBean carMessageBean = (CarMessageBean) o;
                if (carMessageBean != null) {
                    List<CarMessageBean.DataBean> carMessageBeanDataList = carMessageBean.getData();
                    if (messageAdapter != null) {

                        mOwnerInfoRight.setAdapter(messageAdapter);
                        messageAdapter.setNewData(carMessageBeanDataList);

                    }
                }


                break;


        }


    }

    @Override
    public void onError(String tag) {
        stopMaterialProgressDialog();
        showToast(tag);

    }

    //清空适配器
    public void pushData() {

        mOwnerInfoRight.removeAllViews();
        ownerList = null;
        waterList = null;
        data = null;
        ownerRightAdapter.setOwnerList(ownerList, OwnerInfoContract.OWNER_EMPTY);
        waterbillAdapter.setNewData(waterList);
        propretyAdapter.setNewData(data);
        ownerRightAdapter.notifyDataSetChanged();
        waterbillAdapter.notifyDataSetChanged();
        propretyAdapter.notifyDataSetChanged();
        ownerLeftNameAdapter.setThisPosition(100);
        ownerLeftNameAdapter.notifyDataSetChanged();

        //展示空页面

    }

    //展示空页面
    public void showEmpty() {
        List<String> list = new ArrayList<>();
        OwnerCallerAdapter adapter = new OwnerCallerAdapter(R.layout.empty_view, list);
        adapter.setEmptyView(getAdapterEmptyView("很干净，一条数据都没有"));
        mOwnerInfoRight.setAdapter(adapter);
    }

    @Override
    public void OnClick(View view, int prostion) {
        ownerLeftNameAdapter.setThisPosition(prostion);
        ownerLeftNameAdapter.notifyDataSetChanged();
        switch (prostion) {

            case 0:

                if (isTable()) {
                    getPresenter().getOwnerMessage(UserInfoUtil.getInstance().getVillageId() + "", roomid + "", 1 + "", OwnerInfoContract.OWNER_USER);
                }
                break;

            case 1:
                //家人信息

                if (isTable()) {
                    getPresenter().getOwnerMessage(UserInfoUtil.getInstance().getVillageId() + "", roomid + "", 2 + "", OwnerInfoContract.OWNER_HOME);

                }

                break;

            case 2:
                //租客信息

                if (isTable()) {
                    getPresenter().getOwnerMessage(UserInfoUtil.getInstance().getVillageId() + "", roomid + "", 3 + "", OwnerInfoContract.OWNER_TENANT);

                }
                break;

            case 3:

                //车辆信息
                if (isTable()) {
                    getPresenter().getCarMessage(UserInfoUtil.getInstance().getVillageId() + "", roomid + "", OwnerInfoContract.OWNER_CAR);
                }

                break;


            case 4:
                //水电费

                if (isTable()) {
                    getPresenter().getWaterBill(UserInfoUtil.getInstance().getVillageId() + "", roomid + "", "water", "2018-01", "2018-12", OwnerInfoContract.GET_WATER_BILL);
                }


                break;

            case 5:
                //物业费

                if (isTable()) {

                    getPresenter().getProPrety(UserInfoUtil.getInstance().getVillageId() + "", roomid + "", OwnerInfoContract.GET_PROPRETY);

                }

                break;

            case 6:
                //停车费
                if (isTable()) {
                    showEmpty();
                }

                break;

        }


    }

    //  判断是否选择三级联动
    public boolean isTable() {
        if (selectedTowerDataBean != null) {
            if (selectedCellDataBean != null) {

                if (roomNumber != null) {
                    return true;
                } else {
                    showToast("请选择房间");
                    return false;

                }
            } else {
                showToast("请选择单元");
                return false;
            }

        } else {
            showToast("请选择楼号");
            return false;
        }

    }

}
