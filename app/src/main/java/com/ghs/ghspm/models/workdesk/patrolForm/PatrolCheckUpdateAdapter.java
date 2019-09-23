package com.ghs.ghspm.models.workdesk.patrolForm;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ghs.ghspm.R;
import com.ghs.ghspm.bean.PatrolFormListBean;
import com.ghs.ghspm.tools.Contract;

/**
 * Author:wang_sir
 * Time:2019/4/23 14:31
 * Description:This is PatrolCheckUpdateAdapter
 */
public class PatrolCheckUpdateAdapter extends BaseQuickAdapter<PatrolFormListBean.DataBean, BaseViewHolder> {
    public PatrolCheckUpdateAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, PatrolFormListBean.DataBean item) {
        String  formName =item.getFormName();
        helper.setText(R.id.patrol_title_tv,formName);
        helper.setImageResource(R.id.patrol_check_icon_iv,R.mipmap.statement);
//        Glide.with(mContext).load(Contract.ImageBasePath + item.getIcon())
//                .skipMemoryCache(false)
//                // 加载图片
////                        .error(errorimg)
////                        // 设置错误图片
////                        .crossFade()
////                        // 设置淡入淡出效果，默认300ms，可以传参
//                            .placeholder(R.mipmap.ic_launcher)
//                .into((ImageView) helper.getView(R.id.patrol_check_icon_iv));
    }
}
