package com.ghs.ghspm.tools.glide;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import jp.wasabeef.glide.transformations.CropSquareTransformation;

/**
 * Author:wang_sir
 * Time:2019/7/31 14:12
 * Description:This is GlideManagerUtil
 */
public class GlideManagerUtil {
    private Context mContext;

    public GlideManagerUtil(Context mContext) {
        this.mContext = mContext;
    }

    public static GlideManagerUtil getInstance(Context context) {
        return GlideManagerUtilHolder.instance(context);
    }

    private static class GlideManagerUtilHolder {
        private static GlideManagerUtil instance(Context context) {
            return new GlideManagerUtil(context);
        }
    }

    /**
     * 加载圆形图片
     *
     * @param picPath     图片路径
     * @param placeholder 占位图
     * @param mImageView
     */
    public void loadCirclePic(String picPath, int placeholder, ImageView mImageView) {
        if (!isValidContextForGlide()) {
            return;
        }
        Glide.with(mContext).load(picPath)
                .placeholder(placeholder)
                .skipMemoryCache(false)
                .error(placeholder)
                .transform(new GlideCircleTransform(mContext)).into(mImageView);
    }
    /**
     * 加载方形图片
     *
     * @param picPath     图片路径
     * @param placeholder 占位图
     * @param mImageView
     */
    public void loadSquarePic(String picPath, int placeholder, ImageView mImageView) {
        if (!isValidContextForGlide()) {
            return;
        }
        Glide.with(mContext).load(picPath)
                .placeholder(placeholder)
                .skipMemoryCache(false)
                .error(placeholder)
                .bitmapTransform(new CropSquareTransformation(mContext))
                .into(mImageView);
    }
    /**
     * 加载方形图片
     *
     * @param picPathRes     图片路径
     * @param placeholder 占位图
     * @param mImageView
     */
    public void loadSquarePic(int picPathRes, int placeholder, ImageView mImageView) {
        if (!isValidContextForGlide()) {
            return;
        }
        Glide.with(mContext).load(picPathRes)
                .placeholder(placeholder)
                .skipMemoryCache(false)
                .error(placeholder)
                .bitmapTransform(new CropSquareTransformation(mContext))
                .into(mImageView);
    }

    /**
     * 正常加载图片
     * @param picPath
     * @param mImageView
     */
    public void loadNormalPic(String picPath,  int placeholder,ImageView mImageView) {
        if (!isValidContextForGlide()) {
            return;
        }
        Glide.with(mContext).load(picPath)
                .placeholder(placeholder)
                .skipMemoryCache(false)
                .error(placeholder)
                .into(mImageView);
    }

    /**
     * 检测glide加载环境是否ok
     *
     * @return
     */
    public  boolean isValidContextForGlide() {
        if (mContext == null) {
            return false;
        }
        if (mContext instanceof Activity) {
            final Activity activity = (Activity) mContext;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                if (activity.isDestroyed() || activity.isFinishing()) {
                    return false;
                }
            } else {
                if (activity.isFinishing()) {
                    return false;
                }
            }
        }
        return true;
    }
}
