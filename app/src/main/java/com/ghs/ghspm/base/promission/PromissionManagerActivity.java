package com.ghs.ghspm.base.promission;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.ghs.ghspm.R;
import com.ghs.ghspm.tools.ActivityResultManager;
import com.ghs.ghspm.tools.FileUtil;
import com.ghs.ghspm.tools.PubUtil;

import java.io.File;
import java.util.List;

/**
 * created by tobato
 * created date 2019/7/23 14:53.
 * application   动态权限管理类
 */
public class PromissionManagerActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks {
    protected static final int RC_PERM = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }




    /**
     * 权限回调接口
     */
    private CheckPermListener mListener;


    public void checkAppPermissions(CheckPermListener listener, int resString, String... mPerms) {
        mListener = listener;
        if (EasyPermissions.hasPermissions(this, mPerms)) {
            if (mListener != null) {
                mListener.agreeAllPermission();
            }
        } else {
            EasyPermissions.requestPermissions(this, getString(resString),
                    RC_PERM, mPerms);
        }
    }


    /**
     * 用户权限处理,
     * 如果全部获取, 则直接过.
     * 如果权限缺失, 则提示Dialog.
     *
     * @param requestCode  请求码
     * @param permissions  权限
     * @param grantResults 结果
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == EasyPermissions.SETTINGS_REQ_CODE) {
            //配置权限，设置返回

        }
    }


    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        //同意了某些权限可能不是全部
    }

    @Override
    public void onRefuseGivePromission() {

    }

    @Override
    public void onPermissionsAllGranted() {
        if (mListener != null) {
            mListener.agreeAllPermission();//同意了全部权限的回调
        }
    }

    @Override
    public void onPermissionsAllSelected() {
        if (mListener != null) {
            mListener.selectedAllPermission();//选择完全部权限的回调
        }
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {

        EasyPermissions.checkDeniedPermissionsNeverAskAgain(this,
                getString(R.string.perm_tip),
                R.string.setting, R.string.cancel, null, perms);
    }


    /**
     * 从相册选择照片
     *
     * @param bottomSheetDialog
     */
    protected void selectPicturesFromSysPhoto(final BottomSheetDialog bottomSheetDialog) {
        checkAppPermissions(new CheckPermListener() {
            @Override
            public void agreeAllPermission() {
                if (bottomSheetDialog.isShowing()) {
                    bottomSheetDialog.dismiss();
                }
                Intent albumIntent = new Intent(Intent.ACTION_PICK, null);
                albumIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(albumIntent, ActivityResultManager.SYSTEM_PICTURE);

            }

            @Override
            public void selectedAllPermission() {

            }
        }, R.string.perm_camera_store, PubUtil.promissions[1], PubUtil.promissions[3]);
    }

    /**
     * 拍照
     * 从activity跳转
     *
     * @param bottomSheetDialog
     */
    public void takePicturesFromActivity(final BottomSheetDialog bottomSheetDialog) {
        checkAppPermissions(new CheckPermListener() {
            @Override
            public void agreeAllPermission() {
                if (bottomSheetDialog != null) {
                    if (bottomSheetDialog.isShowing()) {
                        bottomSheetDialog.dismiss();
                    }
                }


                File file = new File(FileUtil.getHeadPicRootPath(PromissionManagerActivity.this));
                FileUtil.openCamera(PromissionManagerActivity.this, file);
            }

            @Override
            public void selectedAllPermission() {

            }
        }, R.string.perm_camera_store, PubUtil.promissions[1], PubUtil.promissions[3]);
    }

    /**
     * 拍照
     * 从fragment跳转
     *
     * @param bottomSheetDialog
     */
    public void takePicturesFromFragment(final BottomSheetDialog bottomSheetDialog, final Fragment fragment) {
        checkAppPermissions(new CheckPermListener() {
            @Override
            public void agreeAllPermission() {
                if (bottomSheetDialog != null) {
                    if (bottomSheetDialog.isShowing()) {
                        bottomSheetDialog.dismiss();
                    }
                }


                File file = new File(FileUtil.getHeadPicRootPath(PromissionManagerActivity.this));
                FileUtil.openCamera(fragment, file);
            }

            @Override
            public void selectedAllPermission() {

            }
        }, R.string.perm_camera_store, PubUtil.promissions[1], PubUtil.promissions[3]);
    }


}
