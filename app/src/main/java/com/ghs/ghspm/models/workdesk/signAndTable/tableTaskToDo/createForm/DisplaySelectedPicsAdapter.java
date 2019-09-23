package com.ghs.ghspm.models.workdesk.signAndTable.tableTaskToDo.createForm;

import android.support.constraint.ConstraintLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ghs.ghspm.R;
import com.ghs.ghspm.bean.DynamicSelectedPicBean;
import com.ghs.ghspm.tools.Contract;
import com.ghs.ghspm.tools.PubUtil;

import jp.wasabeef.glide.transformations.CropSquareTransformation;

/**
 * Author:wang_sir
 * Time:2018/7/19 10:52
 * Description:This is MySuggestionAdapter
 */
public class DisplaySelectedPicsAdapter extends BaseQuickAdapter<DynamicSelectedPicBean, BaseViewHolder> {


    private int widthAndHeigh = 60;

    public void setWidthAndHeigh(int widthAndHeigh) {
    this.widthAndHeigh = widthAndHeigh;
    }

    public DisplaySelectedPicsAdapter(int layoutResId) {
        super(layoutResId);
    }


    @Override
    protected void convert(BaseViewHolder helper, DynamicSelectedPicBean dynamicSelectedPicBean) {
        if (dynamicSelectedPicBean != null) {
            String item = dynamicSelectedPicBean.getImagePath();
            if ("-1".equals(item)) {
                Glide.with(mContext).load(R.mipmap.mine_suggest_add)
                        .skipMemoryCache(false)
                        .bitmapTransform(new CropSquareTransformation(mContext))
                        .into((ImageView) helper.getView(R.id.mine_sugguest_icon_iv));

            } else {
                //Contract.ImageBasePath +
                if (item.contains("com.ghs.ghspm")||item.contains("storage")) {
                    Glide.with(mContext).load(item)
                            .skipMemoryCache(false)
                            .bitmapTransform(new CropSquareTransformation(mContext))
                            .into((ImageView) helper.getView(R.id.mine_sugguest_icon_iv));
                }else{
                    Glide.with(mContext).load(Contract.ImageBasePath +item)
                            .skipMemoryCache(false)
                            .bitmapTransform(new CropSquareTransformation(mContext))
                            .into((ImageView) helper.getView(R.id.mine_sugguest_icon_iv));
                }

            }
            helper.addOnClickListener(R.id.mine_sugguest_icon_iv);
            helper.addOnClickListener(R.id.mine_sugguest_delete_iv);
            ImageView imageView = helper.getView(R.id.mine_sugguest_icon_iv);
            ConstraintLayout.LayoutParams linearParams = (ConstraintLayout.LayoutParams) imageView.getLayoutParams(); //取控件textView当前的布局参数 linearParams.height = 20;// 控件的高强制设成20
            linearParams.width = PubUtil.dip2px(mContext, widthAndHeigh);// 控件的宽强制设成30
            linearParams.height = PubUtil.dip2px(mContext, widthAndHeigh);// 控件的高强制设成30

            imageView.setLayoutParams(linearParams); //使设置好的布局参数应用到控件
        }

    }
}