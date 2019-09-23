package com.ghs.ghspm.tools;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;

/**
 * Author:wang_sir
 * Time:2018/7/19 9:52
 * Description:This is FileUtil
 */
public class FileUtil {


    public static final int SIZETYPE_B = 1;//获取文件大小单位为B的double值
    public static final int SIZETYPE_KB = 2;//获取文件大小单位为KB的double值
    public static final int SIZETYPE_MB = 3;//获取文件大小单位为MB的double值
    public static final int SIZETYPE_GB = 4;//获取文件大小单位为GB的double值
    public static String SUGGESTION_SAVED_PICTURE_NAME = "suggestion_user_head_uncrop.jpg";//建议
    public static String SAVED_PICTURE_NAME = "user_head_uncrop.jpg";
    public static String SIGN_PIC_NAME = "signature.png";//签名文件
    public static String HEAD_PIC_TO_SHOW = "head_pic_to_show.jpg";
    public static String SAVED_PICTURE_HEARD = "saved_head_pic.jpg";

    /**
     * 获取头像图片的根目录
     *
     * @return
     */
    public static String getHeadPicDir(Context context) {
        String path = context
                .getExternalCacheDir().getAbsolutePath() + "/userPic/";
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        return path;
    }
    /**
     * 获取头像图片的根路径
     *
     * @return
     */
    public static String getHeadPicRootPath(Context context) {
        return getHeadPicDir(context)+SAVED_PICTURE_NAME;
    }

    /**
     * 获取输入流的大小
     *
     * @param inputStream
     * @return
     */
    public static byte[] readInputStream(InputStream inputStream) {


        // 1.建立通道对象
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        // 2.定义存储空间
        byte[] buffer = new byte[1024];
        // 3.开始读文件
        int len = -1;
        try {
            if (inputStream != null) {
                while ((len = inputStream.read(buffer)) != -1) {
                    // 将Buffer中的数据写到outputStream对象中
                    outputStream.write(buffer, 0, len);
                }
            }
            // 4.关闭流
            outputStream.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return outputStream.toByteArray();
    }

//    /**
//     * 调用此方法自动计算指定文件或指定文件夹的大小
//     *
//     * @param filePath 文件路径
//     * @return 计算好的带B、KB、MB、GB的字符串
//     */
//    public static String getAutoFileOrFilesSize(String filePath) {
//        File file = new File(filePath);
//        long blockSize = 0;
//        try {
//            if (file.isDirectory()) {
//                blockSize = getFileSizes(file);
//            } else {
//                blockSize = getFileSize(file);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return FormetFileSize(blockSize);
//    }

    /**
     * 创建文件
     *
     * @param filePath
     */
    private static File creatFile(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }

    /**
     * 删除文件
     *
     * @param path
     * @return
     */
    public static boolean deleteFile(String path) {
        File file = new File(path);
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File file1 : files) {
                if (file1.isDirectory()) {
                    deleteFile(file1.getAbsolutePath());
                } else {
                    file1.delete();
                }
            }
//            file.delete();//如要保留文件夹，只删除文件，请注释这行
        } else if (file.exists()) {
            file.delete();
        }
        return true;
    }

    /**
     * 清除缓存
     *
     * @return
     */
    public static boolean clearCacheMemory(Context context) {
        //清除缓存
        deleteFile(context.getCacheDir().getAbsolutePath());
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            deleteFile(context.getExternalCacheDir().getAbsolutePath());
        }
        return true;
    }


    /**
     * 获取缓存大小
     *
     * @param context
     * @return
     * @throws Exception
     */
    public static String getTotalCacheSize(Context context) throws Exception {
        //缓存
        long cacheSize = 0;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            cacheSize += getFileSizes(context.getExternalCacheDir());
        }
        return formetFileSize(cacheSize);
    }

    /**
     * 获取指定文件夹
     *
     * @param f
     * @return
     * @throws Exception
     */
    private static long getFileSizes(File f) throws Exception {
        long size = 0;
        File flist[] = f.listFiles();
        for (File file : flist) {
            if (file.isDirectory()) {
                size = size + getFileSizes(file);
            } else {
                size += getFileSize(file);
            }
        }
        return size;
    }

    /**
     * 获取指定文件大小
     *
     * @param file
     * @return
     * @throws Exception
     */
    public static long getFileSize(File file) throws Exception {
        long size = 0;
        if (file.exists()) {
            FileInputStream fis = null;
            fis = new FileInputStream(file);
            size = fis.available();
        } else {
            file.createNewFile();
        }
        return size;
    }


    /**
     * 转换文件大小
     *
     * @param fileS
     * @return
     */
    private static String formetFileSize(long fileS) {
        DecimalFormat df = new DecimalFormat("#.00");
        String fileSizeString = "";
        String wrongSize = "0B";
        if (fileS == 0) {
            return wrongSize;
        }
        if (fileS < 1024) {
            fileSizeString = df.format((double) fileS) + "B";
        } else if (fileS < 1048576) {
            fileSizeString = df.format((double) fileS / 1024) + "KB";
        } else if (fileS < 1073741824) {
            fileSizeString = df.format((double) fileS / 1048576) + "MB";
        } else {
            fileSizeString = df.format((double) fileS / 1073741824) + "GB";
        }
        return fileSizeString;
    }

    /**
     * 转换文件大小,指定转换的类型
     *
     * @param fileS
     * @param sizeType
     * @return
     */
    private static double FormetFileSize(long fileS, int sizeType) {
        DecimalFormat df = new DecimalFormat("#.00");
        double fileSizeLong = 0;
        switch (sizeType) {
            case SIZETYPE_B:
                fileSizeLong = Double.valueOf(df.format((double) fileS));
                break;
            case SIZETYPE_KB:
                fileSizeLong = Double.valueOf(df.format((double) fileS / 1024));
                break;
            case SIZETYPE_MB:
                fileSizeLong = Double.valueOf(df.format((double) fileS / 1048576));
                break;
            case SIZETYPE_GB:
                fileSizeLong = Double.valueOf(df.format((double) fileS / 1073741824));
                break;
            default:
                break;
        }
        return fileSizeLong;
    }

    /**
     * 打开文件
     * 兼容7.0
     *
     * @param context     activity
     * @param file        File
     * @param contentType 文件类型如：文本（text/html）
     *                    当手机中没有一个app可以打开file时会抛ActivityNotFoundException
     */
    public static void startActionFile(Context context, File file, String contentType) throws ActivityNotFoundException {
        if (context == null) {
            return;
        }
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.setFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);//增加读写权限
        intent.setDataAndType(getUriFromFile(context, file), contentType);
        if (!(context instanceof Activity)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        context.startActivity(intent);
    }

    /**
     * 打开相机
     * 兼容7.0
     *
     * @param activity    Activity
     * @param file        File
     */
    public static void openCamera(Activity activity, File file) {
        if (activity == null) {
            return;
        }
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, getUriFromFile(activity, file));
        activity.startActivityForResult(intent, ActivityResultManager.TAKE_PICTURE);
    }
    /**
     * 打开相机
     * 兼容7.0
     *
     * @param file        File
     */
    public static void openCamera(Fragment fragment, File file) {
        if (fragment == null) {
            return;
        }
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, getUriFromFile(fragment.getContext(), file));
        fragment.startActivityForResult(intent, ActivityResultManager.TAKE_PICTURE);
    }

    /**
     * 获取对应的URI
     * @param context
     * @param file
     * @return
     */
    public static Uri getUriFromFile(Context context, File file) {
        if (context == null || file == null) {
            throw new NullPointerException();
        }
        Uri uri;
        if (Build.VERSION.SDK_INT >= 24) {
            uri = FileProvider.getUriForFile(context.getApplicationContext(), "com.ghs.ghspm.fileprovider", file);
        } else {
            uri = Uri.fromFile(file);
        }
        return uri;
    }



    //判断文件是否存在
    public  static boolean fileIsExists(String strFile) {
        try {
            File f = new File(strFile);
            if (!f.exists()) {
                return false;
            }

        } catch (Exception e) {
            return false;
        }

        return true;
    }
}

