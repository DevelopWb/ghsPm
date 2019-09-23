package com.ghs.ghspm.models.workdesk.ownerinfo.illegally;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ghs.ghspm.R;
import com.ghs.ghspm.base.displayPhotos.DisplayPhotosActivity;
import com.ghs.ghspm.bean.IllegallyMessageBean;
import com.ghs.ghspm.tools.Contract;
import com.ghs.ghspm.tools.StrUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jp.wasabeef.glide.transformations.CropSquareTransformation;

public class IlleagllyAdapter extends BaseQuickAdapter<IllegallyMessageBean.DataBean, BaseViewHolder> {
    private Context context;
    private IlleagllyAdapter.imageAdapters imageAdapters;


    public IlleagllyAdapter(int layoutResId, Context context) {
        super(layoutResId);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, IllegallyMessageBean.DataBean item) {

        helper.setText(R.id.illegally_people, item.getCreateUserName() == null ? "无" : item.getCreateUserName());
        helper.setText(R.id.illegally_content, item.getContent() == null ? "无" : item.getContent());
        helper.setText(R.id.illegally_time, item.getCreateTime() == null ? "无" : item.getCreateTime());
        Log.i(TAG, "convert: " + item.getImageUrl());
        helper.setText(R.id.image_wu, item.getImageUrl() == null ? "无" : "");
        RecyclerView illegally_image_RecylerView = helper.getView(R.id.illegally_image_RecylerView);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        illegally_image_RecylerView.setLayoutManager(layoutManager);
        imageAdapters = new imageAdapters(R.layout.illegally_item_image);
        illegally_image_RecylerView.setAdapter(imageAdapters);
        final String imageUrl = item.getImageUrl();
        if (StrUtils.isStringValueOk(imageUrl)) {
            if (imageUrl.contains(",")) {
                String[] split = imageUrl.split(",");
                imageAdapters.setNewData(Arrays.asList(split));
            } else {
                List<String> imageList = new ArrayList<>();
                imageList.add(imageUrl);
                imageAdapters.setNewData(imageList);
            }
            imageAdapters.setOnItemChildClickListener(new OnItemChildClickListener() {
                @Override
                public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                    new DisplayPhotosActivity().startDisplayPhotosActivity(mContext, imageUrl, position);
                }
            });
        } else {
            helper.setText(R.id.image_wu, "无");
        }


    }

    //图片适配器
    public class imageAdapters extends BaseQuickAdapter<String, BaseViewHolder> {


        public imageAdapters(int layoutResId) {
            super(layoutResId);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
            ImageView illegally_item_image = helper.getView(R.id.illegally_item_image);

            Glide.with(mContext).load(Contract.ImageBasePath + item)
                    .skipMemoryCache(false)
                    // 设置错误图片
                    .crossFade()
                    // 设置淡入淡出效果，默认300ms，可以传参
                    .placeholder(R.mipmap.splash_shadow)
                    .bitmapTransform(new CropSquareTransformation(mContext))
                    .into(illegally_item_image);

            helper.addOnClickListener(R.id.illegally_item_image);

        }
    }


}
