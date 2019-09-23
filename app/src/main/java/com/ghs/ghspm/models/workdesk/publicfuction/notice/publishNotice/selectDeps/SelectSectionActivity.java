package com.ghs.ghspm.models.workdesk.publicfuction.notice.publishNotice.selectDeps;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.ghs.ghspm.R;
import com.ghs.ghspm.base.BaseActivity;
import com.ghs.ghspm.bean.section.SectionBeanNew;
import com.ghs.ghspm.bean.section.SectionFive;
import com.ghs.ghspm.bean.section.SectionFour;
import com.ghs.ghspm.bean.section.SectionOne;
import com.ghs.ghspm.bean.section.SectionThree;
import com.ghs.ghspm.bean.section.SectionTwo;
import com.ghs.ghspm.models.workdesk.publicfuction.notice.NoticeContract;
import com.ghs.ghspm.models.workdesk.publicfuction.notice.NoticePresent;
import com.ghs.ghspm.tools.ActivityResultManager;

import java.util.ArrayList;
import java.util.List;

/**
 * created by 8级大的狂风
 * created date 2018/10/9 19:30.
 * application  选择角色
 */
public class SelectSectionActivity extends BaseActivity<NoticeContract.INoticeView, NoticePresent> implements NoticeContract.INoticeView, View.OnClickListener {


    private int times = 0;
    private RecyclerView mSelectSectionRv;
    private ExpandableItemAdapter expandableItemAdapter;
    /**
     * 确定
     */
    private TextView mSelectedPeopleConfirmTv;
    private List<SectionBeanNew> sectionBeans;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public NoticePresent creatPresenter() {
        return new NoticePresent();
    }

    @Override
    public void setLayout() {
        setContentView(R.layout.activity_select_section);


    }

    @Override
    public void initLayoutView() {
        initView();

        initActionBar("选择角色", null);
    }

    @Override
    public void getDate() {
        getPresenter().getRoleList("");
    }

    @Override
    public void actionBarRightTvOnClick() {

    }

    private void initView() {
        mSelectSectionRv = (RecyclerView) findViewById(R.id.select_section_rv);

        mSelectedPeopleConfirmTv = (TextView) findViewById(R.id.selected_people_confirm_tv);
        mSelectedPeopleConfirmTv.setOnClickListener(this);
    }

    @Override
    public void startLoading(String tag) {

    }

    @Override
    public void stopLoading(String tag) {

    }

    @Override
    public void updateView(Object o, String tag) {
        sectionBeans = (List<SectionBeanNew>) o;
        ArrayList<MultiItemEntity> lists = generateData(sectionBeans);
        expandableItemAdapter = new ExpandableItemAdapter(lists);
        initRecyclerview(mSelectSectionRv, expandableItemAdapter, LinearLayoutManager.VERTICAL, false);
        expandableItemAdapter.expand(0);
        expandableItemAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                ArrayList<MultiItemEntity> lists = (ArrayList<MultiItemEntity>) adapter.getData();
                MultiItemEntity multiItemEntity = lists.get(position);
                SectionBeanNew sectionBean = null;
                switch (multiItemEntity.getItemType()) {
                    case 0:
                        SectionOne sectionOne = (SectionOne) multiItemEntity;
                        sectionBean = sectionOne.getSectionBean();
                        break;
                    case 1:
                        SectionTwo sectionTwo = (SectionTwo) multiItemEntity;
                        sectionBean = sectionTwo.getSectionBean();

                        break;
                    case 2:
                        SectionThree sectionThree = (SectionThree) multiItemEntity;
                        sectionBean = sectionThree.getSectionBean();

                        break;
                    case 3:
                        SectionFour sectionFour = (SectionFour) multiItemEntity;
                        sectionBean = sectionFour.getSectionBean();

                        break;
                    case 4:
                        break;
                    default:
                        break;
                }
                if (sectionBean != null) {
                    if (sectionBean.isSelected()) {
                        unSelect(sectionBean);
                    } else {
                        select(sectionBean);
                    }
                }

            }
        });
    }

    @Override
    public void onError(String tag) {
        stopMaterialProgressDialog();
        showToast(tag);
    }

    /**
     * 重新整理数据 得到适配器的数据
     *
     * @param sectionBeans
     * @return
     */
    private ArrayList<MultiItemEntity> generateData(List<SectionBeanNew> sectionBeans) {
        ArrayList<MultiItemEntity> res = new ArrayList<>();
        if (sectionBeans.size() > 0) {
            for (SectionBeanNew bean : sectionBeans) {
                bean.setSelected(false);
                SectionOne sectionOne = new SectionOne();
                sectionOne.setSectionBean(bean);
                if (bean.getChildren().size() > 0) {
                    for (SectionBeanNew sectionBean : bean.getChildren()) {
                        sectionBean.setSelected(false);
                        SectionTwo sectionTwo = new SectionTwo();
                        sectionTwo.setSectionBean(sectionBean);
                        if (sectionBean.getChildren().size() > 0) {
                            for (SectionBeanNew sectionBean1 : sectionBean.getChildren()) {
                                sectionBean1.setSelected(false);
                                SectionThree sectionThree = new SectionThree();
                                sectionThree.setSectionBean(sectionBean1);
                                if (sectionBean1.getChildren().size() > 0) {
                                    for (SectionBeanNew sectionBean2 : sectionBean1.getChildren()) {
                                        sectionBean2.setSelected(false);
                                        SectionFour sectionFour = new SectionFour();
                                        sectionFour.setSectionBean(sectionBean2);
                                        if (sectionBean2.getChildren().size() > 0) {
                                            for (SectionBeanNew sectionBean3 : sectionBean2.getChildren()) {
                                                sectionBean3.setSelected(false);
                                                SectionFive sectionFive = new SectionFive();
                                                sectionFour.addSubItem(sectionFive);
                                            }
                                        }
                                        sectionThree.addSubItem(sectionFour);
                                    }
                                }
                                sectionTwo.addSubItem(sectionThree);
                            }
                        }
                        sectionOne.addSubItem(sectionTwo);
                    }
                }
                res.add(sectionOne);
            }
        }
        return res;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.selected_people_confirm_tv:
                Intent intent = new Intent();
                intent.putExtra(ActivityResultManager.SELECTED_DEPS_ID, getSelectedDepsInfo(0));
                intent.putExtra(ActivityResultManager.SELECTED_DEPS_NAME, getSelectedDepsInfo(1));
                setResult(ActivityResultManager.PUBLISH_NOTICE_SELECT_DEP, intent);
                finish();

        }
    }

    /**
     * 获取选中的部门id或者名称
     * type 0 代表id  1代表名称
     */
    private String getSelectedDepsInfo(int type) {
        List<SectionBeanNew> sectionBeanList = new ArrayList<>();
        List<SectionBeanNew> allSelectedDeps = getSelectedSectionBeans(sectionBeanList, sectionBeans);
        boolean isId = type == 0 ? true : false;
        StringBuilder sb = new StringBuilder();
        if (allSelectedDeps != null) {
            if (allSelectedDeps.size() > 0) {
                for (SectionBeanNew allSelectedDep : allSelectedDeps) {
                    if (isId) {
                        sb.append(allSelectedDep.getRoleId());
                        sb.append(",");
                    } else {
                        sb.append(allSelectedDep.getRoleName());
                        sb.append(",");
                    }
                }
            }
        }
        String deps = sb.toString();
        if (deps.contains(",")) {
            deps = deps.substring(0, deps.length() - 1);
        }
        return deps;
    }


    /**
     * 从所有数据中遍历查找指定sectionBean的父Sectionbean
     *
     * @param sectionBean
     * @return
     */

    private List<SectionBeanNew> getPresentSectionBean2(List<SectionBeanNew> arraysreturn, List<SectionBeanNew> arrays, SectionBeanNew sectionBean) {
        if (sectionBean == null) {
            return arraysreturn;
        }
        for (SectionBeanNew bean : arrays) {
            if (bean.getRoleId() == sectionBean.getParentId()) {
                arraysreturn.add(bean);
                break;
            } else {
                if (bean.getChildren() != null && bean.getChildren().size() > 0) {
                    getPresentSectionBean2(arraysreturn, bean.getChildren(), sectionBean);
                }
            }
        }
        return arraysreturn;
    }

    /**
     * 从所有数据中遍历查找选中的sectionbean
     *
     * @return
     */

    private List<SectionBeanNew> getSelectedSectionBeans(List<SectionBeanNew> arraysreturn, List<SectionBeanNew> arrays) {
        for (SectionBeanNew bean : arrays) {
            if (bean.isSelected()) {
                arraysreturn.add(bean);
            }
            if (bean.getChildren() != null && bean.getChildren().size() > 0) {
                getSelectedSectionBeans(arraysreturn, bean.getChildren());
            }
        }
        return arraysreturn;
    }

    /**
     * 从所有数据中遍历查找指定sectionBean的父Sectionbean
     *
     * @param sectionBean
     * @return
     */
    private SectionBeanNew getPresentSectionBean(List<SectionBeanNew> arrays, SectionBeanNew sectionBean) {
        List<SectionBeanNew> sectionBeanList = new ArrayList<>();
        List<SectionBeanNew> presentSections = getPresentSectionBean2(sectionBeanList, arrays, sectionBean);
        if (presentSections.size() == 0) {
            return null;
        } else {
            return presentSections.get(0);
        }

    }


    //刷新下级选中状态
    private void selectLow(SectionBeanNew bean) {
        for (SectionBeanNew selectionBean : bean.getChildren()) {
            if (selectionBean.getParentId() == bean.getRoleId()) {
                selectionBean.setSelected(true);
                selectLow(selectionBean);
            }
        }
    }

    //刷新下级未选中状态
    private void unSelectLow(SectionBeanNew bean) {
        for (SectionBeanNew selectionBean : bean.getChildren()) {
            if (selectionBean.getParentId() == bean.getRoleId()) {
                selectionBean.setSelected(false);
                unSelectLow(selectionBean);
            }
        }
    }

    //刷新父级选中状态
    private void selectPrent(SectionBeanNew bean) {
        SectionBeanNew pBean = getPresentSectionBean(sectionBeans, bean);
        if (pBean != null && pBean.getChildren() != null && pBean.getChildren().size() > 0) {
            int count = 0;
            for (SectionBeanNew selectionBean : pBean.getChildren()) {
                if (selectionBean.getParentId() == bean.getParentId() && selectionBean.isSelected()) {
                    count++;
                }
            }
            if (count == pBean.getChildren().size()) {
                pBean.setSelected(true);
            }
            selectPrent(pBean);
        }

    }

    //刷新父级取消选中状态
    private void unSelectPrent(SectionBeanNew bean) {
        SectionBeanNew presentBean = getPresentSectionBean(sectionBeans, bean);
        if (presentBean != null) {
            presentBean.setSelected(false);
            unSelectPrent(presentBean);
        }
    }

    /**
     * 选中
     *
     * @param bean
     */
    private void select(SectionBeanNew bean) {
        bean.setSelected(true);
        selectPrent(bean);
        selectLow(bean);
        //记得notify列表
        expandableItemAdapter.notifyDataSetChanged();

    }

    /**
     * 取消选中
     *
     * @param bean
     */
    private void unSelect(SectionBeanNew bean) {
        bean.setSelected(false);
        unSelectPrent(bean);
        unSelectLow(bean);
        //记得notify列表
        expandableItemAdapter.notifyDataSetChanged();
    }

}
