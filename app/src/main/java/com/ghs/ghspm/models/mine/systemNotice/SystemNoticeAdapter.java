package com.ghs.ghspm.models.mine.systemNotice;

import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ghs.ghspm.R;
import com.ghs.ghspm.bean.SysNoticeBean;
import com.ghs.ghspm.tools.StrUtils;

/**
 * Author:wang_sir
 * Time:2019/2/1 14:34
 * Description:This is SystemNoticeAdapter
 */
public class SystemNoticeAdapter extends BaseQuickAdapter<SysNoticeBean.DataBean, BaseViewHolder> {
    public SystemNoticeAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, SysNoticeBean.DataBean item) {
        helper.setText(R.id.system_notice_item_title, item.getTitle());
        String content = item.getContent();
        if (StrUtils.isStringValueOk(content)) {
            if (content.contains("#")) {
                int startIndex = content.indexOf("#");
                int endIndex = content.lastIndexOf("#");
                TextView textView = helper.getView(R.id.system_notice_item_content);
                String str = content.replace("#","\"");
                StrUtils.changeTextColor(textView,str,startIndex+1,endIndex,"#2367C9");
            } else {
                helper.setText(R.id.system_notice_item_content, content);
            }
        }
    }
}
