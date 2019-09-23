package com.ghs.ghspm.models.mine.personalInfo;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ghs.ghspm.R;
import com.ghs.ghspm.base.BaseActivity;
import com.ghs.ghspm.base.network.RequestStatus;
import com.ghs.ghspm.bean.UserInfoBean;
import com.ghs.ghspm.models.mine.personalInfo.modifymobile.ModifyMobileActivity;
import com.ghs.ghspm.models.oss.OssUploadManager;
import com.ghs.ghspm.tools.ActivityResultManager;
import com.ghs.ghspm.tools.FileUtil;
import com.ghs.ghspm.tools.ImageUtil;
import com.ghs.ghspm.tools.UserInfoUtil;
import com.ghs.ghspm.tools.glide.GlideCircleTransform;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * created by tobato
 * created date 2019/7/15 16:02.
 * application  个人资料
 */
public class PersonalInfoActivity extends BaseActivity<PersonalInfoContract.IUserInfoView, PersonalInfoPresent> implements PersonalInfoContract.IUserInfoView, View.OnClickListener, RequestStatus {

    private ImageView mUserHeadIv;
    private RelativeLayout mUserInfoHeadRl;
    /**
     * 123
     */
    private TextView mUserInfoMobileTv;
    private RelativeLayout mUserInfoMobileRl;
    private RecyclerView mUserInfoRv;
    private PersonalInfoAdapter userInfoAdapter;
    private BottomSheetDialog bottomSheetDialog;
    private OssUploadManager ossManager;
    private TextView mMineUserHeadTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public PersonalInfoPresent creatPresenter() {
        return new PersonalInfoPresent();
    }

    @Override
    public void setLayout() {
        setContentView(R.layout.activity_user_info);

    }

    @Override
    public void initLayoutView() {
        initView();
        initActionBar("个人资料", null);

    }

    @Override
    public void getDate() {
        getPresenter().getUserInfoDetail(UserInfoUtil.getInstance().getUserId(), PersonalInfoContract.GET_USER_INFO_DETAIL);
        ossManager = OssUploadManager.getInstance(this);
    }

    @Override
    public void actionBarRightTvOnClick() {

    }

    private void initView() {
        mUserHeadIv = (ImageView) findViewById(R.id.user_head_iv);
        mUserInfoHeadRl = (RelativeLayout) findViewById(R.id.user_info_head_rl);
        mUserInfoHeadRl.setOnClickListener(this);
        mUserInfoMobileTv = (TextView) findViewById(R.id.user_info_mobile_tv);
        mUserInfoMobileRl = (RelativeLayout) findViewById(R.id.user_info_mobile_rl);
        mUserInfoMobileRl.setOnClickListener(this);
        mUserInfoRv = (RecyclerView) findViewById(R.id.user_info_rv);
        userInfoAdapter = new PersonalInfoAdapter(R.layout.user_info_detail_item);
        initRecyclerview(mUserInfoRv, userInfoAdapter, LinearLayoutManager.VERTICAL, false);
        addDivider(true, mUserInfoRv, false, true);

        mMineUserHeadTv = (TextView) findViewById(R.id.persional_mag_head_tv);
    }

    @Override
    public void startLoading(String tag) {

    }

    @Override
    public void stopLoading(String tag) {

    }

    @Override
    public void updateView(Object o, String tag) {
        switch (tag) {
            case PersonalInfoContract.GET_USER_INFO_DETAIL:
                UserInfoBean userInfoBean = (UserInfoBean) o;
                if (userInfoBean != null) {
                    UserInfoBean.DataBean dataBean = userInfoBean.getData();
                    if (dataBean != null) {
                        List<UserInfoBean.DataBean.UserConfigListBean> arrays = new ArrayList<>();
                        arrays.add(new UserInfoBean.DataBean.UserConfigListBean("用户名", dataBean.getName()));
                        arrays.add(new UserInfoBean.DataBean.UserConfigListBean("职称", dataBean.getPosition()));
                        arrays.add(new UserInfoBean.DataBean.UserConfigListBean("工作状态", dataBean.getWorkState()));
                        List<UserInfoBean.DataBean.UserConfigListBean> userConfigList = dataBean.getUserConfigList();
                        if (userConfigList != null && userConfigList.size() > 0) {
                            for (UserInfoBean.DataBean.UserConfigListBean userConfigListBean : userConfigList) {
                                arrays.add(new UserInfoBean.DataBean.UserConfigListBean(userConfigListBean.getTitle(), userConfigListBean.getValue()));
                            }
                        }
                        userInfoAdapter.setNewData(arrays);
                        mUserInfoMobileTv.setText(userInfoBean.getData().getMobile());
                        setHeadPicBgResource(mUserHeadIv,mMineUserHeadTv , mUserInfoUtil.getUserName(), userInfoBean.getData().getHeadImage(), mUserInfoUtil.getHeadDefaultBgColor(), false);

                    }
                }
                break;
            case PersonalInfoContract.MODIFY_HEAD_PIC:
                String msg = (String) o;
                showNormalToast(msg);
                break;
            default:
                break;
        }

    }

    @Override
    public void onError(String tag) {
        stopMaterialProgressDialog();
        showToast(tag);
    }

    @Override
    public void onStart(String tag) {

    }

    @Override
    public void onSuccess(Object o, String tag) {
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == ActivityResultManager.TAKE_PICTURE) {
                Bitmap bitmap = ImageUtil.readPictureDegreeToForwordBitmap(FileUtil.getHeadPicRootPath(this));
                String userHeadPic = ImageUtil.saveCroppedImage(FileUtil.getHeadPicDir(this), bitmap);
                Glide.with(this).load(userHeadPic)
                        .skipMemoryCache(false)
                        // 加载图片
//                        .error(errorimg)
//                        // 设置错误图片
//                        .crossFade()
//                        // 设置淡入淡出效果，默认300ms，可以传参
                        .placeholder(R.mipmap.default_user_head_icon)
                        // 设置占位图
                        .transform(new GlideCircleTransform(this))//圆角
                        .into(mUserHeadIv);
                uploadPicInfo(userHeadPic);
            } else if (requestCode == ActivityResultManager.SYSTEM_PICTURE) {
                if (data != null) {
                    Bitmap bitmap = null;
                    ContentResolver resolver = getContentResolver();
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inSampleSize = 4;
                    try {
                        Uri uri = data.getData();
                        if (uri != null) {
                            InputStream inputStream = resolver.openInputStream(uri);
                            int length = 0;
                            try {
                                length = inputStream.available();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            if (length > 500 * 1000) {
                                bitmap = BitmapFactory.decodeStream(inputStream, null, options);
                            } else {
                                bitmap = BitmapFactory.decodeStream(inputStream);
                            }
                        }
                        String userHeadPic = ImageUtil.saveCroppedImage(FileUtil.getHeadPicDir(this), bitmap);
                        Glide.with(this).load(userHeadPic)
                                .skipMemoryCache(false)
                                // 加载图片
//                        .error(errorimg)
//                        // 设置错误图片
//                        .crossFade()
//                        // 设置淡入淡出效果，默认300ms，可以传参
                                .placeholder(R.mipmap.default_user_head_icon)
                                // 设置占位图
                                .transform(new GlideCircleTransform(this))//圆角
                                .into(mUserHeadIv);
                        uploadPicInfo(userHeadPic);

                    } catch (FileNotFoundException e) {


                        e.printStackTrace();
                    }
                }

            }
        } else if (ActivityResultManager.EDIT_USER_MODIFY_MOBILE == resultCode) {
            mUserInfoMobileTv.setText(data.getStringExtra("MODIFYED_MOBILE"));
        }


    }

    /**
     * 上传图片
     */
    private void uploadPicInfo(String picPath) {
        String path = ossManager.uploadPicInfo(picPath);
        getPresenter().modifyUserHeadPic(UserInfoUtil.getInstance().getUserId(), path, PersonalInfoContract.MODIFY_HEAD_PIC);
    }

    @Override
    public void onBackPressed() {
        setResult(ActivityResultManager.MODIFY_USER_INFO);
        super.onBackPressed();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.user_info_head_rl:
                if (isNetWorkConnected()) {
                    View view = LayoutInflater.from(this).inflate(R.layout.select_pic_menue, null);
                    bottomSheetDialog = new BottomSheetDialog(this);
                    bottomSheetDialog.setCancelable(true);
                    bottomSheetDialog.setContentView(view);
                    bottomSheetDialog.show();
                    view.findViewById(R.id.mine_edit_cancel_pic_tv).setOnClickListener(this);
                    view.findViewById(R.id.mine_edit_take_pic_tv).setOnClickListener(this);
                    view.findViewById(R.id.mine_edit_select_pic_tv).setOnClickListener(this);
                }

                break;
            case R.id.user_info_mobile_rl:
                startActivityForResult(new Intent(this, ModifyMobileActivity.class), ActivityResultManager.EDIT_USER_MODIFY_MOBILE);
                break;
            case R.id.mine_edit_cancel_pic_tv://替换头像中的取消按钮
                if (bottomSheetDialog.isShowing()) {
                    bottomSheetDialog.dismiss();
                }
                break;
            case R.id.mine_edit_take_pic_tv://替换头像中的拍照按钮
                takePicturesFromActivity(bottomSheetDialog);

                break;
            case R.id.mine_edit_select_pic_tv:////替换头像中的从相册中选取按钮

                selectPicturesFromSysPhoto(bottomSheetDialog);


                break;

        }
    }


}
