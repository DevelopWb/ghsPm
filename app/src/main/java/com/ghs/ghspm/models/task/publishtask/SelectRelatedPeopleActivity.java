package com.ghs.ghspm.models.task.publishtask;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ghs.ghspm.R;
import com.ghs.ghspm.base.BaseActivity;
import com.ghs.ghspm.bean.OrganizeItem;
import com.ghs.ghspm.bean.RoleBean;
import com.ghs.ghspm.bean.UsersFromRoleBean;
import com.ghs.ghspm.bean.section.OrganizeMenuBean;
import com.ghs.ghspm.models.workdesk.organizeframe.OrganizeContact;
import com.ghs.ghspm.models.workdesk.organizeframe.OrganizeMenuAdapter;
import com.ghs.ghspm.models.workdesk.organizeframe.OrganizePresent;
import com.ghs.ghspm.tools.ActivityResultManager;
import com.ghs.ghspm.tools.PubUtil;
import com.ghs.ghspm.tools.UserInfoUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * created by 8级大的狂风
 * created date 2018/10/16 15:55.
 * application  选择执行人 负责人 抄送人
 */
public class SelectRelatedPeopleActivity extends BaseActivity<OrganizeContact.IOrganizeView, OrganizePresent> implements OrganizeContact.IOrganizeView, View.OnClickListener {

    private RecyclerView mOrgnizeRv;
    private RecyclerView mDepsShowRv;
    private OrganizeMenuAdapter adapter;
    private List<OrganizeMenuBean> selectedDepsBeans = new ArrayList<>();

    /**
     * 已选择
     */
    private TextView mSelectedPeopleTv;
    /**
     * 确定
     */
    private TextView mSelectedPeopleConfirmTv;
    private LinearLayout mSelectPeopleConfirmLl;
    private SelectRelatedPeopleAdapter depsShowAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public OrganizePresent creatPresenter() {
        return new OrganizePresent();
    }

    @Override
    public void setLayout() {
        setContentView(R.layout.activity_select_related_people);

    }

    @Override
    public void initLayoutView() {
        initView();
        switch (PubUtil.publish_task_select_people) {
            case 0:
                initActionBar("选择执行人", null);
                break;
            case 1:
                initActionBar("选择负责人", null);
                break;
            case 2:
                initActionBar("选择抄送人", null);
                break;
            case 3:
                initActionBar("选择转发人", null);
                break;
            case 4:
                initActionBar("选择签批人", null);
                break;
            default:
                initActionBar("选择人员", null);

                break;
        }

    }

    @Override
    public void getDate() {
        getPresenter().getRoleList(UserInfoUtil.getInstance().getVillageId(), OrganizeContact.GET_ROLE_LIST);
        OrganizeMenuBean selectedDepsBean = new OrganizeMenuBean(UserInfoUtil.getInstance().getVillageId(), UserInfoUtil.getInstance().getVillageName(), "village" + UserInfoUtil.getInstance().getVillageId());
        addToselectedDepsBeans(selectedDepsBean);

    }

    @Override
    public void actionBarRightTvOnClick() {

    }

    private void initView() {
        mOrgnizeRv = (RecyclerView) findViewById(R.id.orgnize_rv);
        mDepsShowRv = (RecyclerView) findViewById(R.id.deps_show_rv);
        adapter = new OrganizeMenuAdapter(R.layout.organize_menu_item);
        initRecyclerview(mOrgnizeRv, adapter, LinearLayoutManager.HORIZONTAL, false);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                OrganizeMenuBean selectedDepsBean = (OrganizeMenuBean) adapter.getData().get(position);
                if (0 == position) {
                    selectedDepsBeans.clear();
                    getPresenter().getRoleList(selectedDepsBean.getDepId(), OrganizeContact.GET_ROLE_LIST);
                    OrganizeMenuBean bean = new OrganizeMenuBean(UserInfoUtil.getInstance().getVillageId(), UserInfoUtil.getInstance().getVillageName(), "village" + UserInfoUtil.getInstance().getVillageId());
                    addToselectedDepsBeans(bean);
                }
                adapter.notifyDataSetChanged();

            }
        });


        mSelectedPeopleTv = (TextView) findViewById(R.id.selected_people_tv);
        mSelectedPeopleConfirmTv = (TextView) findViewById(R.id.selected_people_confirm_tv);
        mSelectedPeopleConfirmTv.setOnClickListener(this);
        mSelectPeopleConfirmLl = (LinearLayout) findViewById(R.id.select_people_confirm_ll);

        depsShowAdapter = new SelectRelatedPeopleAdapter(null);
        depsShowAdapter.setEmptyView(getAdapterEmptyView(""));
        initRecyclerview(mDepsShowRv, depsShowAdapter, LinearLayoutManager.VERTICAL, false);
        depsShowAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                OrganizeItem organizeItem = (OrganizeItem) adapter.getData().get(position);
                if (OrganizeItem.DEPS == organizeItem.getItemType()) {
                    RoleBean.DataBean dataBean = (RoleBean.DataBean) organizeItem.getObject();
                    int num = dataBean.getUserNum();
                    if (num > 0) {
                        OrganizeMenuBean selectedDepsBean = new OrganizeMenuBean(dataBean.getRoleId(), dataBean.getRoleName(), dataBean.getRoleName() + dataBean.getRoleId());
                        addToselectedDepsBeans(selectedDepsBean);
                        getPresenter().getUsersFromRoleList(dataBean.getRoleId(), OrganizeContact.GET_USERS_FROM_ROLE_LIST);
                    }
                } else if (OrganizeItem.USERS == organizeItem.getItemType()) {
                    if (PubUtil.publish_task_select_people == 1 || PubUtil.publish_task_select_people == 3 || PubUtil.publish_task_select_people == 4) {
                        List<OrganizeItem> arrays = adapter.getData();
                        if (arrays.size() > 0) {
                            for (OrganizeItem array : arrays) {
                                if (OrganizeItem.USERS == array.getItemType()) {
                                    UsersFromRoleBean.DataBean userListBean = (UsersFromRoleBean.DataBean) array.getObject();
                                    userListBean.setSelected(false);
                                }
                            }
                            depsShowAdapter.setNewData(arrays);
                        }
                        initSelectStatus(position, organizeItem, true);
                    } else {
                        initSelectStatus(position, organizeItem, false);
                    }
                    initSelectedPeopleTvValue();
                }
            }
        });

    }

    /**
     * 添加数据
     */
    private void addToselectedDepsBeans(OrganizeMenuBean selectedDepsBean) {
        selectedDepsBeans.add(selectedDepsBean);
        List<OrganizeMenuBean> arrays = PubUtil.removeDuplicateDataOfList(selectedDepsBeans);
        adapter.setNewData(arrays);

    }

    /**
     * 初始化选择状态
     *
     * @param position
     * @param organizeItem
     * @param clear
     */
    private void initSelectStatus(int position, OrganizeItem organizeItem, boolean clear) {
        UsersFromRoleBean.DataBean userListBean = (UsersFromRoleBean.DataBean) organizeItem.getObject();
        if (PubUtil.selectedUsersMap.containsKey(userListBean.getUserId())) {
            PubUtil.selectedUsersMap.remove(userListBean.getUserId());
            userListBean.setSelected(false);
        } else {
            if (clear) {
                PubUtil.selectedUsersMap.clear();
            }
            PubUtil.selectedUsersMap.put(userListBean.getUserId(), userListBean);
            userListBean.setSelected(true);
        }
        depsShowAdapter.notifyItemChanged(position);
    }

    /**
     * 初始化已选择文本控件的值
     */
    private void initSelectedPeopleTvValue() {
        if (PubUtil.selectedUsersMap.size() > 0) {
            StringBuilder sb = new StringBuilder();
            for (Map.Entry<Integer, UsersFromRoleBean.DataBean> entry : PubUtil.selectedUsersMap.entrySet()) {
                entry.getKey();
                UsersFromRoleBean.DataBean userListBean1 = entry.getValue();
                sb.append(userListBean1.getName());
                sb.append(",");

            }
            mSelectedPeopleTv.setText("已选择：" + sb.toString().substring(0, sb.toString().length() - 1));
        } else {
            mSelectedPeopleTv.setText("");
        }
    }

    @Override
    public void startLoading(String tag) {
        showMaterialProgressDialog("", "");

    }

    @Override
    public void stopLoading(String tag) {
        stopMaterialProgressDialog();
    }

    @Override
    public void updateView(Object o, String tag) {

        List<OrganizeItem> depsAdapterData = new ArrayList<>();

        switch (tag) {
            case OrganizeContact.GET_ROLE_LIST:
                RoleBean roleBean = (RoleBean) o;
                List<RoleBean.DataBean> arrays = roleBean.getData();
                if (arrays != null && arrays.size() > 0) {
                    for (RoleBean.DataBean array : arrays) {
                        depsAdapterData.add(new OrganizeItem(OrganizeItem.DEPS, array));
                    }
                }
                break;
            case OrganizeContact.GET_USERS_FROM_ROLE_LIST:
                UsersFromRoleBean usersFromRoleBean = (UsersFromRoleBean) o;
                List<UsersFromRoleBean.DataBean> user_arrays = usersFromRoleBean.getData();
                if (user_arrays != null && user_arrays.size() > 0) {
                    for (UsersFromRoleBean.DataBean array : user_arrays) {
                        depsAdapterData.add(new OrganizeItem(OrganizeItem.USERS, array));
                    }
                }

                break;
            default:
                break;
        }
        if (depsAdapterData != null && depsAdapterData.size() > 0) {
            depsShowAdapter.setNewData(getVerRvData(depsAdapterData));
        }
    }

    /**
     * 获取竖直方向rv的数据
     *
     * @return
     */
    private List<OrganizeItem> getVerRvData(List<OrganizeItem> depsAdapterData) {

        for (OrganizeItem depsAdapterDatum : depsAdapterData) {
            int type = depsAdapterDatum.getItemType();
            if (OrganizeItem.USERS == type) {
                UsersFromRoleBean.DataBean userListBean = (UsersFromRoleBean.DataBean) depsAdapterDatum.getObject();
                if (PubUtil.selectedUsersMap.containsKey(userListBean.getUserId())) {
                    userListBean.setSelected(true);
                } else {
                    userListBean.setSelected(false);
                }
            }
        }
        return depsAdapterData;
    }

    @Override
    public void onError(String tag) {
        stopMaterialProgressDialog();
        showToast(tag);
    }

    @Override
    public void onBackPressed() {
        PubUtil.selectedUsersMap.clear();
        setResult(ActivityResultManager.PUBLISH_TASK_SELECT_PEOPLE);
        super.onBackPressed();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.selected_people_confirm_tv:
                setResult(ActivityResultManager.PUBLISH_TASK_SELECT_PEOPLE);
                finish();
                break;
        }
    }


}
