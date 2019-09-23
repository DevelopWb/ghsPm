package com.ghs.ghspm.models.workdesk.publicfuction.notice.normalnotice;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ghs.ghspm.R;
import com.ghs.ghspm.tools.Contract;

import jp.wasabeef.glide.transformations.CropSquareTransformation;

/**
 * Author:wang_sir
 * Time:2018/7/19 10:52
 * Description:This is MySuggestionAdapter
 */
public class NoticeDetailPicDisplayAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public NoticeDetailPicDisplayAdapter(int layoutResId) {
        super(layoutResId);
    }


    @Override
    protected void convert(BaseViewHolder helper, String item) {
        Glide.with(mContext).load(Contract.ImageBasePath+item)
                .skipMemoryCache(false)
                .bitmapTransform(new CropSquareTransformation(mContext))
                .into((ImageView) helper.getView(R.id.notice_detatil_pic_display_iv));
        helper.addOnClickListener(R.id.notice_detatil_pic_display_iv);

    }
}