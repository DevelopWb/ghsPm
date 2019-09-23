package com.ghs.ghspm.bean.section;

import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.ghs.ghspm.models.workdesk.publicfuction.notice.publishNotice.selectDeps.ExpandableItemAdapter;

/**
 * Author:wang_sir
 * Time:2018/10/10 14:00
 * Description:This is SectionOne
 */
public class SectionFive extends AbstractExpandableItem<SectionBeanNew> implements MultiItemEntity {
    @Override
    public int getItemType() {
        return ExpandableItemAdapter.TYPE_LEVEL_4;
    }

    @Override
    public int getLevel() {
        return 4;
    }
}
