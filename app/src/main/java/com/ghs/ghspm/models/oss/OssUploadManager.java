package com.ghs.ghspm.models.oss;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSStsTokenCredentialProvider;
import com.ghs.ghspm.base.network.RequestStatus;
import com.ghs.ghspm.bean.OssTokenBean;
import com.ghs.ghspm.tools.ConfigUtil;
import com.ghs.ghspm.tools.FileUtil;
import com.ghs.ghspm.tools.ImageUtil;
import com.ghs.ghspm.tools.PubUtil;
import com.ghs.ghspm.tools.StrUtils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Author:wang_sir
 * Time:2019/7/15 16:23
 * Description:This is OssUploadManager
 */
public class OssUploadManager implements RequestStatus {
    private OssService ossService;
    private Context mContext;

    public static OssUploadManager getInstance(Context mContext) {
        return UploadImageToOssManagerHolder.getInstance(mContext);
    }

    public OssUploadManager(Context mContext) {
        this.mContext = mContext.getApplicationContext();
        new OssTokenModel().getOssToken(this, "");
    }

    @Override
    public void onStart(String tag) {

    }

    @Override
    public void onSuccess(Object o, String tag) {
        OssTokenBean ossTokenBean = (OssTokenBean) o;
        if (ossTokenBean != null) {
            OssTokenBean.DataBean dataBean = ossTokenBean.getData();
            if (dataBean != null) {
                OSSCredentialProvider credentialProvider = new OSSStsTokenCredentialProvider(dataBean.getAccessKeyId(), dataBean.getAccessKeySecret(), dataBean.getSecurityToken());
                ossService = OssService.initOSS(credentialProvider, mContext, ConfigUtil.endpoint, ConfigUtil.bucket);
            }
        }
    }

    @Override
    public void onError(String tag) {

    }

    private static class UploadImageToOssManagerHolder {
        private static OssUploadManager getInstance(Context mContext) {

            return new OssUploadManager(mContext);
        }

        ;

    }

    /**
     * 上传图片  放到子线程中执行
     */
    public String uploadPicInfo(String picPath) {
        String iconServerPath = "";
        if (StrUtils.isStringValueOk(picPath)) {
            iconServerPath = getUploadImageNameServer();
            if (ossService != null) {
                ossService.asyncPutImage(ConfigUtil.IMAGE_PATH_SERVER + iconServerPath, picPath);
            }
        }
        return iconServerPath;
    }
    /**
     * 上传图片  放到子线程中执行
     */
    public void asyncPutImage(String serverPath, String localFile) {
        if (ossService != null) {
            ossService.asyncPutImage(serverPath, localFile);
        }
    }
    /**
     * 设置存储到服务端的图片名称
     *
     * @return
     */
    public String getUploadImageNameServer() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS", Locale.CHINA);
        return sdf.format(new Date()) + PubUtil.getRandomData() + ".jpeg";
    }
    /**
     * 获取上传到oss上图片的路径，多个图片用逗号分隔()
     * <p>
     * (最好在子线程中调用次方法，比较耗时)
     *
     * @return
     */
    public  String getPhotoPathOfUploadedToOssServer(Context  context, List<String> icons) {
        Bitmap bitmap = null;
        List<String> dealedPics = new ArrayList<>();
        for (String icon : icons) {
            if (!"-1".equals(icon)) {

                long size = 0;
                try {
                    size = FileUtil.getFileSize(new File(icon));
                    if (size > 500 * 1024) {
                        bitmap = ImageUtil.readPictureDegreeToForwordBitmap(icon);
                    } else {
                        bitmap = BitmapFactory.decodeFile(icon);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (bitmap != null) {
                    String userHeadPic = ImageUtil.saveCroppedImage(FileUtil.getHeadPicDir(context), bitmap);
                    dealedPics.add(userHeadPic);
                }

            }
        }
        StringBuilder icon_server_path = new StringBuilder();

        if (dealedPics.size() > 0) {
            for (int i = 0; i < dealedPics.size(); i++) {
                String dealedPic = dealedPics.get(i);
                String name = getUploadImageNameServer();
                Log.i("TGA", dealedPics.get(i));
                asyncPutImage(ConfigUtil.IMAGE_PATH_SERVER + name, dealedPic);
                if (i == dealedPics.size() - 1) {
                    if (dealedPic.contains("com.ghs.ghshome")) {
                        icon_server_path.append(name);
                    } else {
                        icon_server_path.append(dealedPic);
                    }

                } else {
                    if (dealedPic.contains("com.ghs.ghshome")) {
                        icon_server_path.append(name);
                        icon_server_path.append(",");
                    } else {

                        icon_server_path.append(dealedPic);
                        icon_server_path.append(",");
                    }

                }

            }

        }
        return icon_server_path.toString();
    }
}
