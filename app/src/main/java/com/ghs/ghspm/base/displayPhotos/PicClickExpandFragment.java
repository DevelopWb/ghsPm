package com.ghs.ghspm.base.displayPhotos;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.ghs.ghspm.R;
import com.ghs.ghspm.customView.photoview.Info;
import com.ghs.ghspm.customView.photoview.PhotoView;

/**
 * Author:wang_sir
 * Time:2018/11/14 13:57
 * Description:This is PicClickExpandFragment  点击图片放大的fragment
 */
public class PicClickExpandFragment extends Fragment implements View.OnClickListener {


    private View view;
    private ImageView mHeadPicBgIv;
    private PhotoView mHeadPicPv;
    private FrameLayout mHeadPicFl;
    AlphaAnimation in = new AlphaAnimation(0, 1);
    AlphaAnimation out = new AlphaAnimation(1, 0);
    private Info mInfo;
    private CancelDisplayPhotoCallBack cancelDisplayPhotoCallBack;

    /**
     * 传入回调
     * @param cancelDisplayPhotoCallBack
     */
    public void setCancelDisplayPhotoCallBack(CancelDisplayPhotoCallBack cancelDisplayPhotoCallBack){
        this.cancelDisplayPhotoCallBack = cancelDisplayPhotoCallBack;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.pic_show_layout, null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mHeadPicBgIv = (ImageView) view.findViewById(R.id.head_pic_bg_iv);
        mHeadPicPv = (PhotoView) view.findViewById(R.id.head_pic_pv);
        mHeadPicPv.setOnClickListener(this);
        mHeadPicPv.enable();
        mHeadPicFl = (FrameLayout) view.findViewById(R.id.head_pic_fl);
        initAnimation();
    }

    /**
     * 初始化动画
     */
    private void initAnimation(){
        in.setDuration(300);
        out.setDuration(300);
        out.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mHeadPicBgIv.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.head_pic_pv:
                mHeadPicBgIv.startAnimation(out);
                mHeadPicPv.animaTo(mInfo, new Runnable() {
                    @Override
                    public void run() {
                        mHeadPicFl.setVisibility(View.GONE);
                        if (cancelDisplayPhotoCallBack != null) {
                            cancelDisplayPhotoCallBack.showActivityLayout();
                        }
                    }
                });
                break;
        }
    }


    @Override
    public void onDestroy() {
        if (mHeadPicFl.getVisibility() == View.VISIBLE) {
            mHeadPicBgIv.startAnimation(out);
            mHeadPicPv.animaTo(mInfo, new Runnable() {
                @Override
                public void run() {
                    mHeadPicFl.setVisibility(View.GONE);
                    if (cancelDisplayPhotoCallBack != null) {
                        cancelDisplayPhotoCallBack.showActivityLayout();
                    }
                }
            });
        }
        super.onDestroy();
    }

    /**
     * 展示大图
     */
    public void showPic(final ImageView imageView,String  picPath){
        mHeadPicFl.setVisibility(View.VISIBLE);
        Glide.with(getContext()).load(picPath)
                .placeholder(R.mipmap.mine_user_icon)
                .error(R.mipmap.mine_user_icon)
                .into(new SimpleTarget<GlideDrawable>() {
                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                        mHeadPicPv.setImageDrawable(resource);
                        mInfo = PhotoView.getImageViewInfo(imageView);
                        mHeadPicBgIv.startAnimation(in);
                        mHeadPicBgIv.setVisibility(View.VISIBLE);
                        mHeadPicFl.setVisibility(View.VISIBLE);
                        mHeadPicPv.animaFrom(mInfo);
                        if (cancelDisplayPhotoCallBack != null) {
                            cancelDisplayPhotoCallBack.hideActivityLayout();
                        }

                    }
                });
    }



  public   interface CancelDisplayPhotoCallBack{
        void  showActivityLayout();//取消展示后的逻辑
        void hideActivityLayout();//展示图片
    }




}
