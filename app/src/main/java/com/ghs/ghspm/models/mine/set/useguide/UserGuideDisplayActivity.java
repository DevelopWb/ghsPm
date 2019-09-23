package com.ghs.ghspm.models.mine.set.useguide;

import android.os.Bundle;

import com.ghs.ghspm.R;
import com.ghs.ghspm.base.BaseActivity;
import com.ghs.ghspm.base.BasePresent;
import com.ghs.ghspm.customView.photoview.PhotoView;
import com.ghs.ghspm.tools.ActivityResultManager;


public class UserGuideDisplayActivity extends BaseActivity {


    private PhotoView mPhotoview;
    private PhotoView mPhotoview2;
    private PhotoView mPhotoview3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public void getDate() {

    }

    @Override
    public void initLayoutView() {
        initView();
        String tag = getIntent().getStringExtra(ActivityResultManager.USER_GUID_TAG);
        switch (tag) {
            case ActivityResultManager.USER_GUIDE_TASK:
                initActionBar("任务", "");
                mPhotoview.setImageResource(R.mipmap.user_guide_task1);
                mPhotoview2.setImageResource(R.mipmap.user_guide_task2);
                mPhotoview3.setImageResource(R.mipmap.user_guide_task3);
                break;
            case ActivityResultManager.USER_GUIDE_SIGN:
                initActionBar("签到", "");
                mPhotoview.setImageResource(R.mipmap.user_guide_sign);
                break;
            case ActivityResultManager.USER_GUIDE_KEY:
                initActionBar("万能钥匙", "");

                mPhotoview.setImageResource(R.mipmap.user_guide_open_door);
                break;
            case ActivityResultManager.USER_GUIDE_NOTICE:
                initActionBar("公告", "");

                mPhotoview.setImageResource(R.mipmap.user_guide_notice1);
                mPhotoview2.setImageResource(R.mipmap.user_guide_notice2);
                break;
            case ActivityResultManager.USER_GUIDE_ORGANIZE:
                initActionBar("通讯录", "");

                mPhotoview.setImageResource(R.mipmap.user_guide_orgnize);
                break;
            case ActivityResultManager.USER_GUIDE_WATER_RECORD:
                initActionBar("水电表抄录", "");

                mPhotoview.setImageResource(R.mipmap.user_guide_water_record);
                break;
            case ActivityResultManager.USER_GUIDE_CAR_INFO:
                initActionBar("车辆信息", "");

                mPhotoview.setImageResource(R.mipmap.user_guide_car_info);
                break;
            case ActivityResultManager.USER_GUIDE_MINE:
                initActionBar("我的", "");
                mPhotoview.setImageResource(R.mipmap.user_guide_mine);
                break;
            default:
                break;
        }

    }

    @Override
    public void setLayout() {
        setContentView(R.layout.mine_user_guide_display_layout);

    }

    @Override
    public void actionBarRightTvOnClick() {

    }

    @Override
    public BasePresent creatPresenter() {
        return null;
    }


    private void initView() {

        mPhotoview = (PhotoView) findViewById(R.id.photoview);
        mPhotoview2 = (PhotoView) findViewById(R.id.photoview2);
        mPhotoview3 = (PhotoView) findViewById(R.id.photoview3);
        mPhotoview.enable();
        mPhotoview2.enable();
        mPhotoview3.enable();
    }


}
