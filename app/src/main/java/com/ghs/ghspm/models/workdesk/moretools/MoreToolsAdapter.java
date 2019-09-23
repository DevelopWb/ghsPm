package com.ghs.ghspm.models.workdesk.moretools;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ghs.ghspm.R;
import com.ghs.ghspm.bean.MultiWorkDeskMenuBean;
import com.ghs.ghspm.bean.ToolFormBean;
import com.ghs.ghspm.tools.Contract;
import com.ghs.ghspm.tools.StrUtils;

import java.util.List;

public class MoreToolsAdapter extends BaseQuickAdapter<MultiWorkDeskMenuBean, BaseViewHolder> {


    public MoreToolsAdapter(int layoutResId, @Nullable List<MultiWorkDeskMenuBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MultiWorkDeskMenuBean item) {
        ToolFormBean.DataBean dataBean = (ToolFormBean.DataBean) item.getObject();
        helper.setText(R.id.work_item_content_tv, dataBean.getFormName());
        LinearLayout moretools_linearlayout = helper.getView(R.id.moretools_linearlayout);
        WindowManager wm = (WindowManager) mContext
                .getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        ViewGroup.LayoutParams params = moretools_linearlayout.getLayoutParams();
        //设置宽度值
        params.width =  width/3;
        moretools_linearlayout.setLayoutParams(params);

        ImageView imageView = helper.getView(R.id.work_item_content_iv);
        String imagePath = dataBean.getIcon();
        if (StrUtils.isStringValueOk(imagePath)) {
            Glide.with(mContext).load(Contract.ImageBasePath + dataBean.getIcon())
                    .skipMemoryCache(false)
                    // 加载图片
//                        .error(errorimg)
//                        // 设置错误图片
//                        .crossFade()
//                        // 设置淡入淡出效果，默认300ms，可以传参
//                            .placeholder(R.mipmap.default_user_head_icon)
                    // 设置占位图
//                            .transform(new GlideCircleTransform(context))//圆角
                    .into(imageView);

        }
    }
}