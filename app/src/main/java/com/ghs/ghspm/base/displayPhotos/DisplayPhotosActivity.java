package com.ghs.ghspm.base.displayPhotos;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.ghs.ghspm.R;
import com.ghs.ghspm.base.BaseActivity;
import com.ghs.ghspm.base.BasePresent;
import com.ghs.ghspm.customView.photoview.PhotoView;
import com.ghs.ghspm.tools.ActivityResultManager;
import com.ghs.ghspm.tools.Contract;
import com.ghs.ghspm.tools.StrUtils;

/**
 * created by tobato
 * created date 2018/12/27 14:45.
 * application 浏览图片
 */

public class DisplayPhotosActivity extends BaseActivity {

    private ViewPager mDisplayPhotoVp;
    private String[] pics = null;
    private TextView mDisplayPhotoTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setLayout() {
        setContentView(R.layout.activity_display_photos);
    }

    @Override
    public void initLayoutView() {
        getPhotoPaths();
        initView();

    }

    @Override
    public BasePresent creatPresenter() {
        return null;
    }

    @Override
    public void getDate() {


    }

    @Override
    public void actionBarRightTvOnClick() {

    }

    /**
     * 获取图片的路径
     */
    private void getPhotoPaths() {
        String photoPaths = "";
        if (getIntent() != null) {
            photoPaths = getIntent().getStringExtra(ActivityResultManager.DISPLAY_PHOTOS);
            if (StrUtils.isStringValueOk(photoPaths)) {
                if (photoPaths.contains(",")) {
                    pics = photoPaths.split(",");
                } else {
                    pics = new String[]{photoPaths};
                }

            }
        }


    }

    private void initView() {
        mDisplayPhotoVp = (ViewPager) findViewById(R.id.display_photo_vp);
        mDisplayPhotoTv = (TextView) findViewById(R.id.display_photo_tv);

        mDisplayPhotoVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                String text = position + 1 + "/" + pics.length;
                mDisplayPhotoTv.setText(text);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mDisplayPhotoVp.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return pics.length;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                PhotoView view = new PhotoView(DisplayPhotosActivity.this);

                Glide.with(DisplayPhotosActivity.this).load(Contract.ImageBasePath + pics[position])
                        .skipMemoryCache(false)
//                        .placeholder(R.mipmap.mine_user_icon)
//                        .error(R.mipmap.mine_user_icon)
                        .into(view);
                container.addView(view);
                view.enable();
                view.setScaleType(ImageView.ScaleType.FIT_XY);
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });
                return view;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }
        });
        if (getIntent() != null) {
            int position = getIntent().getIntExtra(ActivityResultManager.DISPLAY_PHOTO_POSITION, 0);
            mDisplayPhotoVp.setCurrentItem(position);
            String text = position + 1 + "/" + pics.length;
            mDisplayPhotoTv.setText(text);

        }


    }

    /**
     * 启动activity,将需要展示的photo路径传过来
     */
    public void startDisplayPhotosActivity(Context context, String photosPath, int startPosition) {

        if (!StrUtils.isStringValueOk(photosPath)) {
            Toast.makeText(context, "没有需要展示的图片", Toast.LENGTH_LONG).show();
            return;
        }
        Intent intent = new Intent(context, DisplayPhotosActivity.class);
        intent.putExtra(ActivityResultManager.DISPLAY_PHOTOS, photosPath);
        intent.putExtra(ActivityResultManager.DISPLAY_PHOTO_POSITION, startPosition);
        context.startActivity(intent);
    }
}
