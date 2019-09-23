package com.ghs.ghspm.models.workdesk.innerrepair;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ghs.ghspm.R;
import com.ghs.ghspm.base.BaseActivity;
import com.ghs.ghspm.base.displayPhotos.SelectPhotosToUploadFragment;
import com.ghs.ghspm.base.network.RequestStatus;
import com.ghs.ghspm.bean.CellBean;
import com.ghs.ghspm.bean.InnerRepairBean;
import com.ghs.ghspm.bean.InnerRepairTagBean;
import com.ghs.ghspm.bean.TowerBean;
import com.ghs.ghspm.models.workdesk.publicfuction.universalkey.UniversialKeyContract;
import com.ghs.ghspm.models.workdesk.publicfuction.universalkey.UniversialKeyModel;
import com.ghs.ghspm.base.network.NetWorkUtil;
import com.ghs.ghspm.tools.PickerManager;
import com.ghs.ghspm.tools.RxScheduler;
import com.ghs.ghspm.tools.StrUtils;
import com.ghs.ghspm.tools.UserInfoUtil;

import java.util.ArrayList;
import java.util.List;

public class InnerRepairActivity extends BaseActivity<InnerRepairContract.IInnerRepairView, InnerRepairPresent> implements View.OnClickListener, RequestStatus, InnerRepairContract.IInnerRepairView {


    private SelectPhotosToUploadFragment selectPhotosFragment;
    private RecyclerView mQuesMenuRv;
    private RecyclerView mQuesTagsRv;
    /**
     * 请选择
     */
    private TextView mSelectTowerTv;
    private LinearLayout mSelectTowerLl;
    /**
     * 楼号
     */
    private TextView mTowerNameTv;
    /**
     * 请选择
     */
    private TextView mSelectCellTv;
    private TextView mTagTitleTv;
    private LinearLayout mSelectCellLl;
    /**
     * 提交
     */
    private TextView mCommitTv;
    private TowerBean.DataBean selectedTowerInfo = null;//已选中的楼信息
    private CellBean.DataBean selectedCellInfo = null;//已选中的单元信息
    private InnerRepairMenuAdapter menuAdapter;
    private InnerRepairTagsAdapter tagsAdapter;

    private String selectedTags = "";
    private String selectedTower = "";
    private String selectedCell = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public InnerRepairPresent creatPresenter() {
        return new InnerRepairPresent();
    }

    @Override
    public void setLayout() {
        setContentView(R.layout.activity_inner_repair);

    }

    @Override
    public void initLayoutView() {
        initView();
        selectPhotosFragment = (SelectPhotosToUploadFragment) getSupportFragmentManager().findFragmentById(R.id.select_photos_fg);
        selectPhotosFragment.setSpanCount(3, 6, 85,false);
        initActionBar("内部报修", null);
    }

    @Override
    public void getDate() {
        getPresenter().getInnerRepairMenu(UserInfoUtil.getInstance().getPropertyId(), InnerRepairContract.REPAIR_MENU);
    }

    @Override
    public void actionBarRightTvOnClick() {

    }

    private void initView() {
        mQuesMenuRv = (RecyclerView) findViewById(R.id.ques_menu_rv);
        mQuesTagsRv = (RecyclerView) findViewById(R.id.ques_tags_rv);
        mSelectTowerTv = (TextView) findViewById(R.id.select_tower_tv);
        mSelectTowerLl = (LinearLayout) findViewById(R.id.select_tower_ll);
        mSelectTowerLl.setOnClickListener(this);
        mTowerNameTv = (TextView) findViewById(R.id.tower_name_tv);
        mSelectCellTv = (TextView) findViewById(R.id.select_cell_tv);
        mTagTitleTv = (TextView) findViewById(R.id.tag_title_tv);
        mSelectCellLl = (LinearLayout) findViewById(R.id.select_cell_ll);
        mSelectCellLl.setOnClickListener(this);
        mCommitTv = (TextView) findViewById(R.id.commit_tv);
        mCommitTv.setOnClickListener(this);
        menuAdapter = new InnerRepairMenuAdapter(R.layout.repair_menu_item);
        menuAdapter.setEmptyView(getAdapterEmptyView(""));
        initRecyclerview(mQuesMenuRv, menuAdapter, LinearLayoutManager.VERTICAL, false);
        addDivider(true,mQuesMenuRv, false, false);

        tagsAdapter = new InnerRepairTagsAdapter(R.layout.repair_tags_item);
        GridLayoutManager manager = new GridLayoutManager(this, 2);
        mQuesTagsRv.setAdapter(tagsAdapter);
        mQuesTagsRv.setLayoutManager(manager);
        menuAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                initData();
                List<InnerRepairBean.DataBean> datas = adapter.getData();
                InnerRepairBean.DataBean bean = (InnerRepairBean.DataBean) adapter.getData().get(position);
                for (InnerRepairBean.DataBean data : datas) {
                    data.setSelected(false);
                }
                bean.setSelected(true);
                menuAdapter.notifyDataSetChanged();
                initTagAdapterData(bean);

            }
        });
        tagsAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                InnerRepairTagBean tagBean = (InnerRepairTagBean) adapter.getData().get(position);
                if (tagBean.isSelected()) {
                    tagBean.setSelected(false);
                } else {
                    tagBean.setSelected(true);
                }
                adapter.notifyItemChanged(position);
            }
        });

    }

    /**
     * 初始化数据  清空
     */
    private void initData() {
        selectPhotosFragment.initContentAndIcons();
        mSelectTowerTv.setText("请选择");
        mSelectCellTv.setText("请选择");
    }

    /**
     * 问题标签数据
     *
     * @param bean
     */
    private void initTagAdapterData(InnerRepairBean.DataBean bean) {
        String lable = bean.getLabel();
        if (StrUtils.isStringValueOk(lable)) {
            mTagTitleTv.setVisibility(View.VISIBLE);
            if (lable.contains(",")) {
                String[] arrs = lable.split(",");
                List<InnerRepairTagBean> list = new ArrayList<>(arrs.length);
                for (String arr : arrs) {
                    list.add(new InnerRepairTagBean(arr, false));
                }
                tagsAdapter.setNewData(list);

            } else {
                List<InnerRepairTagBean> lists = new ArrayList<>(1);
                lists.add(new InnerRepairTagBean(lable, false));
                tagsAdapter.setNewData(lists);

            }

        } else {
            mTagTitleTv.setVisibility(View.GONE);
            tagsAdapter.setNewData(null);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.select_tower_ll:
                    new UniversialKeyModel().getTowerNo(UserInfoUtil.getInstance().getVillageId(), this, UniversialKeyContract.GET_TOWER_NO);

                break;
            case R.id.select_cell_ll:
                String towerName = mSelectTowerTv.getText().toString().trim();
                if ("请选择".equals(towerName)) {
                    showNormalToast("请先选择楼号");
                } else {
                    new UniversialKeyModel().getCellNo(UserInfoUtil.getInstance().getVillageId(), selectedTowerInfo.getId(), this, UniversialKeyContract.GET_CELL_NO);

                }

                break;
            case R.id.commit_tv:

//                String a = "12";
//               a.substring(0,10);

                //查看标签是否选择
                selectedTags = getQueTags();
                if (!StrUtils.isStringValueOk(selectedTags)) {
                    showNormalToast("请选择问题标签");
                    return;
                } else {
                    selectedTags = selectedTags.substring(0, selectedTags.length() - 1);
                }
                final String content = selectPhotosFragment.getContent();
                if (!StrUtils.isStringValueOk(content)) {
                    showNormalToast("请填写问题描述");
                    return;
                }
                final String kind = getKind();
                selectedTower = mSelectTowerTv.getText().toString().trim();
                selectedTower = "请选择".equals(selectedTower) ? "" : selectedTower + "号楼";
                if (!StrUtils.isStringValueOk(selectedTower)) {
                    showNormalToast("请选择位置");
                    return;
                }
                selectedCell = mSelectCellTv.getText().toString().trim();
                selectedCell = "请选择".equals(selectedCell) ? "" : selectedCell + "单元";
                showMaterialProgressDialog(null, "正在提交，请稍后...");
                RxScheduler.doOnIoThread(new RxScheduler.IOTask<Void>() {
                    @Override
                    public void doOnIOThread() {
                        String icons = selectPhotosFragment.uploadPhotosToOssForPath();
                            getPresenter().saveInnerRepairInfo(UserInfoUtil.getInstance().getPropertyId(), UserInfoUtil.getInstance().getUserId(), kind, selectedTags, selectedTower + selectedCell, content, icons, InnerRepairContract.SAVE_REPAIR);
                    }
                });
                break;
        }
    }

    @NonNull
    private String getQueTags() {
        List<InnerRepairTagBean> allTags = tagsAdapter.getData();
        StringBuilder sb = new StringBuilder(allTags.size());

        if (allTags.size() > 0) {
            for (InnerRepairTagBean tag : allTags) {
                if (tag.isSelected()) {
                    sb.append(tag.getName() );
                    sb.append(",");

                }
            }
        }
        return sb.toString();
    }

    /**
     * 获取种类
     *
     * @return
     */
    private String getKind() {
        String kind = "";
        List<InnerRepairBean.DataBean> menuData = menuAdapter.getData();
        for (InnerRepairBean.DataBean menuDatum : menuData) {
            if (menuDatum.isSelected()) {
                kind = menuDatum.getKind();
            }
        }
        return kind;
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
                            PickerManager.getInstance().showOptionPicker(this, towers, new PickerManager.OnOptionPickerSelectedListener() {
                                @Override
                                public void onOptionsSelect(int options1, int option2, int options3, View v) {
                                    //返回的分别是三个级别的选中位置
                                    selectedTowerInfo = towers.get(options1);
                                    String tx = selectedTowerInfo.getPickerViewText();
                                    mSelectTowerTv.setText(tx.replace("号楼", ""));
                                    mTowerNameTv.setText("号楼");
                                    mSelectCellTv.setText("请选择");
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
                            PickerManager.getInstance().showOptionPicker(this, cellsList, new PickerManager.OnOptionPickerSelectedListener() {
                                @Override
                                public void onOptionsSelect(int options1, int option2, int options3, View v) {
//返回的分别是三个级别的选中位置
                                    selectedCellInfo = cellsList.get(options1);
                                    String tx = selectedCellInfo.getPickerViewText();
                                    mSelectCellTv.setText(tx.replace("单元", ""));

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

        showNormalToast(tag);
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
            case InnerRepairContract.REPAIR_MENU:
                InnerRepairBean innerRepairBean = (InnerRepairBean) o;
                if (innerRepairBean != null) {
                    List<InnerRepairBean.DataBean> dataBean = innerRepairBean.getData();
                    if (dataBean != null) {
                        if (dataBean.size() > 0) {
                            for (int i = 0; i < dataBean.size(); i++) {
                                InnerRepairBean.DataBean bean = dataBean.get(i);
                                if (0 == i) {
                                    bean.setSelected(true);
                                    initTagAdapterData(bean);
                                } else {
                                    bean.setSelected(false);
                                }
                            }
                            menuAdapter.setNewData(dataBean);
                        }
                    }
                }

                break;
            case InnerRepairContract.SAVE_REPAIR:
                List<InnerRepairTagBean> allTags = tagsAdapter.getData();
                for (InnerRepairTagBean allTag : allTags) {
                    allTag.setSelected(false);
                }
                tagsAdapter.notifyDataSetChanged();
                stopMaterialProgressDialog();
                initData();
                showNormalToast("提交成功");
                break;
            default:
                break;
        }
    }
}
