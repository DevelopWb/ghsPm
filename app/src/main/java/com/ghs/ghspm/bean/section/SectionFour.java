package com.ghs.ghspm.bean.section;

import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.ghs.ghspm.models.workdesk.publicfuction.notice.publishNotice.selectDeps.ExpandableItemAdapter;

/**
 * Author:wang_sir
 * Time:2018/10/10 14:00
 * Description:This is SectionOne
 */
public class SectionFour extends AbstractExpandableItem<SectionFive> implements MultiItemEntity {
    private SectionBeanNew sectionBean;

    public SectionBeanNew getSectionBean() {
        return sectionBean;
    }

    public void setSectionBean(SectionBeanNew sectionBean) {
        this.sectionBean = sectionBean;
    }

    @Override
    public int getItemType() {
        return ExpandableItemAdapter.TYPE_LEVEL_3;
    }

    @Override
    public int getLevel() {
        return 3;
    }
}
