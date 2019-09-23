package com.ghs.ghspm.models.workdesk.organizeframe;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ghs.ghspm.R;
import com.ghs.ghspm.base.BaseActivity;
import com.ghs.ghspm.base.network.RequestStatus;
import com.ghs.ghspm.bean.OrganizeItem;
import com.ghs.ghspm.bean.RoleBean;
import com.ghs.ghspm.bean.UserInfoBean;
import com.ghs.ghspm.bean.UsersFromRoleBean;
import com.ghs.ghspm.bean.section.OrganizeMenuBean;
import com.ghs.ghspm.models.mine.personalInfo.PersonalInfoModel;
import com.ghs.ghspm.tools.PubUtil;
import com.ghs.ghspm.tools.UserInfoUtil;
import com.wyt.searchbox.SearchFragment;
import com.wyt.searchbox.custom.IOnSearchClickListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * created by 8级大的狂风
 * created date 2018/9/27 14:06.
 * application  通讯录
 */
public class OrganizeFrameActivity extends BaseActivity<OrganizeContact.IOrganizeView, OrganizePresent> implements OrganizeContact.IOrganizeView, IOnSearchClickListener, RequestStatus, OrganizeContentAdapter.OnHeadMsgCallBack {

    /**
     * 搜索
     */
    private EditText mOrgnizeFrameSearchEt;
    private RecyclerView mOrgnizeRv;
    private OrganizeMenuAdapter menuAdapter;
    private List<OrganizeMenuBean> organizeMenuBeans = new ArrayList<>();//横向菜单
    private SearchFragment searchFragment;
    private RecyclerView mOrgnizeContentRv;
    private OrganizeContentAdapter contentAdapter;
    private UserInfoBean.DataBean userInfoBean;

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
        setContentView(R.layout.activity_organize_frame);

    }

    @Override
    public void initLayoutView() {
        initView();
        initActionBar("通讯录", null);
        mHeaderLeftIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void getDate() {
        showMaterialProgressDialog();
        getPresenter().getRoleList(UserInfoUtil.getInstance().getVillageId(), OrganizeContact.GET_ROLE_LIST);
        OrganizeMenuBean selectedDepsBean = new OrganizeMenuBean(UserInfoUtil.getInstance().getVillageId(), UserInfoUtil.getInstance().getVillageName(), "village" + UserInfoUtil.getInstance().getVillageId());
        addToselectedDepsBeans(selectedDepsBean);
    }

    @Override
    public void actionBarRightTvOnClick() {

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    /**
     * 添加数据
     */
    private void addToselectedDepsBeans(OrganizeMenuBean selectedDepsBean) {
        organizeMenuBeans.add(selectedDepsBean);
        organizeMenuBeans = PubUtil.removeDuplicateDataOfList(organizeMenuBeans);
        menuAdapter.setNewData(organizeMenuBeans);

    }

    private void initView() {
        mOrgnizeFrameSearchEt = (EditText) findViewById(R.id.orgnize_frame_search_et);
        mOrgnizeFrameSearchEt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchFragment.show(getSupportFragmentManager(), SearchFragment.TAG);
            }
        });
        mOrgnizeRv = (RecyclerView) findViewById(R.id.orgnize_rv);
        menuAdapter = new OrganizeMenuAdapter(R.layout.organize_menu_item);
        initRecyclerview(mOrgnizeRv, menuAdapter, LinearLayoutManager.HORIZONTAL, false);
        menuAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                OrganizeMenuBean selectedDepsBean = (OrganizeMenuBean) adapter.getData().get(position);
                int depId = selectedDepsBean.getDepId();
                if (-1 == depId) {
                    return;
                }
                showMaterialProgressDialog();
                if (0 == position) {
                    organizeMenuBeans.clear();
                    getPresenter().getRoleList(selectedDepsBean.getDepId(), OrganizeContact.GET_ROLE_LIST);
                    OrganizeMenuBean bean = new OrganizeMenuBean(UserInfoUtil.getInstance().getVillageId(), UserInfoUtil.getInstance().getVillageName(), "village" + UserInfoUtil.getInstance().getVillageId());
                    addToselectedDepsBeans(bean);
                } else {
                    getPresenter().getUsersFromRoleList(selectedDepsBean.getDepId(), OrganizeContact.GET_USERS_FROM_ROLE_LIST);
                    for (int i = 0; i < organizeMenuBeans.size(); i++) {
                        OrganizeMenuBean bean = organizeMenuBeans.get(i);
                        if (i > position) {
                            organizeMenuBeans.remove(bean);
                        }
                    }
                }
                adapter.notifyDataSetChanged();
            }
        });
        searchFragment = SearchFragment.newInstance();
        searchFragment.setOnSearchClickListener(this);
        mOrgnizeContentRv = (RecyclerView) findViewById(R.id.orgnize_content_rv);
        contentAdapter = new OrganizeContentAdapter(null);
        contentAdapter.setOnHeadBgCallBack(this);
        contentAdapter.setEmptyView(getAdapterEmptyView(""));
        initRecyclerview(mOrgnizeContentRv, contentAdapter, LinearLayoutManager.VERTICAL, false);
        contentAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                OrganizeItem organizeItem = (OrganizeItem) adapter.getData().get(position);
                switch (organizeItem.getItemType()) {
                    case OrganizeItem.DEPS:
                        RoleBean.DataBean dataBean = (RoleBean.DataBean) organizeItem.getObject();
                        if (dataBean.getUserNum() > 0) {
                            showMaterialProgressDialog();
                            OrganizeMenuBean selectedDepsBean = new OrganizeMenuBean(dataBean.getRoleId(), dataBean.getRoleName(), dataBean.getRoleName() + dataBean.getRoleId());
                            addToselectedDepsBeans(selectedDepsBean);
                            getPresenter().getUsersFromRoleList(dataBean.getRoleId(), OrganizeContact.GET_USERS_FROM_ROLE_LIST);
                        }
                        break;
                    case OrganizeItem.USERS:
                        showMaterialProgressDialog();
                        UsersFromRoleBean.DataBean userBean = (UsersFromRoleBean.DataBean) organizeItem.getObject();
                        OrganizeMenuBean selectedDepsBean = new OrganizeMenuBean(-1, userBean.getName(), userBean.getName() + userBean.getUserId());
                        Iterator it = organizeMenuBeans.iterator();
                        while (it.hasNext()) {
                            OrganizeMenuBean selectedDepsBean1 = (OrganizeMenuBean) it.next();
                            if (-1 == selectedDepsBean1.getDepId()) {
                                it.remove();
                            }
                        }
                        addToselectedDepsBeans(selectedDepsBean);
                        new PersonalInfoModel().getUserInfoDetail(UserInfoUtil.getInstance().getPropertyId(), UserInfoUtil.getInstance().getVillageId(), userBean.getUserId(), "", OrganizeFrameActivity.this);

                        break;
                    case OrganizeItem.USER:
                        UserInfoBean.DataBean.UserConfigListBean user = (UserInfoBean.DataBean.UserConfigListBean) organizeItem.getObject();
                        if ("电话".equals(user.getTitle())) {
                            makeAPhoneCall(userInfoBean.getMobile());
                        }
                        break;
                    default:
                        break;
                }
            }
        });
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
                if (depsAdapterData != null && depsAdapterData.size() > 0) {
                    contentAdapter.setNewData(depsAdapterData);
                }
                break;
            case OrganizeContact.GET_USERS_FROM_ROLE_LIST:
                UsersFromRoleBean usersFromRoleBean = (UsersFromRoleBean) o;
                List<UsersFromRoleBean.DataBean> user_arrays = usersFromRoleBean.getData();
                if (user_arrays != null && user_arrays.size() > 0) {
                    for (UsersFromRoleBean.DataBean array : user_arrays) {
                        depsAdapterData.add(new OrganizeItem(OrganizeItem.USERS, array));
                    }
                    if (depsAdapterData != null && depsAdapterData.size() > 0) {
                        contentAdapter.setNewData(depsAdapterData);
                    }
                } else {
                    showToast("无法查到该用户，请确保信息输入正确");
                }

                break;
            default:
                break;
        }

    }

    @Override
    public void onStart(String tag) {

    }

    @Override
    public void onSuccess(Object o, String tag) {
        stopMaterialProgressDialog();
        List<OrganizeItem> depsAdapterData = new ArrayList<>();
        UserInfoBean userInfoBean = (UserInfoBean) o;
        if (userInfoBean != null) {
            this.userInfoBean = userInfoBean.getData();
            if (this.userInfoBean != null) {
                depsAdapterData.add(new OrganizeItem(OrganizeItem.USER, new UserInfoBean.DataBean.UserConfigListBean("头像", userInfoBean.getData().getName() + "," + userInfoBean.getData().getHeadImage() + "," + userInfoBean.getData().getHeadImageBackGroudColor())));
                depsAdapterData.add(new OrganizeItem(OrganizeItem.USER, new UserInfoBean.DataBean.UserConfigListBean("职称", this.userInfoBean.getPosition())));
                depsAdapterData.add(new OrganizeItem(OrganizeItem.USER, new UserInfoBean.DataBean.UserConfigListBean("状态", this.userInfoBean.getWorkState())));
                depsAdapterData.add(new OrganizeItem(OrganizeItem.USER, new UserInfoBean.DataBean.UserConfigListBean("电话", this.userInfoBean.getMobile())));
                List<UserInfoBean.DataBean.UserConfigListBean> userConfigList = this.userInfoBean.getUserConfigList();
                if (userConfigList != null && userConfigList.size() > 0) {
                    for (UserInfoBean.DataBean.UserConfigListBean userConfigListBean : userConfigList) {
                        depsAdapterData.add(new OrganizeItem(OrganizeItem.USER, new UserInfoBean.DataBean.UserConfigListBean(userConfigListBean.getTitle(), userConfigListBean.getValue())));
                    }
                }
                contentAdapter.setNewData(depsAdapterData);
            }
        }
    }

    @Override
    public void onError(String tag) {
        stopMaterialProgressDialog();
        showToast(tag);
    }


    @Override
    public void OnSearchClick(String keyword) {
        mOrgnizeFrameSearchEt.setText(keyword);
        showMaterialProgressDialog();
        getPresenter().searchUsersFromRoleList(UserInfoUtil.getInstance().getVillageId(), keyword, OrganizeContact.GET_USERS_FROM_ROLE_LIST);

    }

    @Override
    public void setHeadPicBg(ImageView imageView, TextView textView) {
        setHeadPicBgResource(imageView, textView, userInfoBean.getName(), userInfoBean.getHeadImage(), userInfoBean.getHeadImageBackGroudColor(), true);

    }

    @Override
    public void onBackPressed() {
        switch (organizeMenuBeans.size()) {
            case 1:
                super.onBackPressed();
                break;
            case 2:
                //返回到部门列表
                showMaterialProgressDialog();
                getPresenter().getRoleList(organizeMenuBeans.get(0).getDepId(), OrganizeContact.GET_ROLE_LIST);
                organizeMenuBeans.clear();
                OrganizeMenuBean bean = new OrganizeMenuBean(UserInfoUtil.getInstance().getVillageId(), UserInfoUtil.getInstance().getVillageName(), "village" + UserInfoUtil.getInstance().getVillageId());
                addToselectedDepsBeans(bean);
                break;
            case 3:
                //返回到用户列表
                showMaterialProgressDialog();
                getPresenter().getUsersFromRoleList(organizeMenuBeans.get(1).getDepId(), OrganizeContact.GET_USERS_FROM_ROLE_LIST);
                organizeMenuBeans.remove(2);
                menuAdapter.setNewData(organizeMenuBeans);
                break;
            default:
                break;
        }

    }
}
