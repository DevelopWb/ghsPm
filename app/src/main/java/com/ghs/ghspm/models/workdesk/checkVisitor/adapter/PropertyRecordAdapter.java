package com.ghs.ghspm.models.workdesk.checkVisitor.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ghs.ghspm.R;
import com.ghs.ghspm.bean.PropertyVisitorListBean;
import com.ghs.ghspm.tools.CalendarUtil;
import com.ghs.ghspm.tools.StrUtils;

import java.util.List;

/**
 * created by guohainan
 * created date 2019/8/13
 * application
 * package name com.ghs.ghspm.models.workdesk.propertyrecord.adapter
 */
public class PropertyRecordAdapter extends BaseQuickAdapter<PropertyVisitorListBean.DataBean,BaseViewHolder> {

    public PropertyRecordAdapter(int layoutResId, @Nullable List<PropertyVisitorListBean.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, PropertyVisitorListBean.DataBean item) {
        helper.setText(R.id.property_record_item_name,"访客姓名："+item.getVisitorName());
        helper.setText(R.id.property_record_item_mobile,"手机号码："+item.getVisitorMobile());
        String  arriveTime = item.getVisitDay();
        if (StrUtils.isStringValueOk(arriveTime)) {
            helper.setText(R.id.property_record_item_time, CalendarUtil.getTimeFromStringTime("yyyy-MM-dd HH:mm:ss","yyyy-MM-dd",arriveTime));
        }
    }
}
