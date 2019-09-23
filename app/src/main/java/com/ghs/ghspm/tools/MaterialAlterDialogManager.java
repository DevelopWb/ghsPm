package com.ghs.ghspm.tools;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.WindowManager;

/**
 * Author:wang_sir
 * Time:2019/7/9 17:04
 * Description:This is MaterialAlterDialogManager
 */
public class MaterialAlterDialogManager {
    public static MaterialAlterDialogManager getInstance() {
        return MaterialAlterDialogHolder.materialAlterDialogManager;
    }

    private static class MaterialAlterDialogHolder {
        private static MaterialAlterDialogManager materialAlterDialogManager = new MaterialAlterDialogManager();
    }

    /**
     * 设置alertdialog的宽高
     *
     * @param dialog
     * @param width  -1代表屏幕宽度  0 代表 wrap_content  其他就是自定义值了
     * @param height
     */
    public void setAlertDialogHeightWidth(AlertDialog dialog, int width, int height) {
        // 设置dialog的宽度
        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
        if (-1 == width) {
            params.width = PubUtil.ScreenWidth;
        } else if (0 == width) {
            params.width = params.width;
        } else {
            params.width = width;
        }
        if (-1 == height) {
            params.height = PubUtil.ScreenHeight;
        } else if (0 == height) {
            params.height = params.height;
        } else {
            params.height = height;
        }
        dialog.getWindow().setAttributes(params);
    }

    /**
     * 只要一个按钮  知道了
     */
    public void showMaterialAlterdialogOnlyOneBtToClose(Context context, String msg, String btName) {

        AlertDialog alertDialog = new AlertDialog.Builder(context)
                .setMessage(msg)
                .setNegativeButton(btName, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();
        setAlertDialogHeightWidth(alertDialog, -1, 0);

    }

    /**
     * @param context
     * @param msg
     * @param leftBt
     * @param rightBt
     */
    public void showMaterialAlterDialog(Context context, String msg, String leftBt, String rightBt, final OnAlterDialogCallBack callBack) {
        AlertDialog alertDialog = new AlertDialog.Builder(context)
                .setMessage(msg)
                .setPositiveButton(rightBt, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        if (callBack != null) {
                            callBack.rightBtClicked();
                        }
                    }
                }).setNegativeButton(leftBt, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        if (callBack != null) {
                            callBack.leftBtClicked();
                        }
                    }
                }).show();
        setAlertDialogHeightWidth(alertDialog, -1, 0);
    }
    /**
     * @param context
     * @param msg
     * @param leftBt
     * @param rightBt
     */
    public void showMaterialAlterDialog(Context context, String msg, int leftBt, int rightBt, final OnAlterDialogCallBack callBack) {
        AlertDialog alertDialog = new AlertDialog.Builder(context)
                .setMessage(msg)
                .setPositiveButton(rightBt, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        if (callBack != null) {
                            callBack.rightBtClicked();
                        }
                    }
                }).setNegativeButton(leftBt, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        if (callBack != null) {
                            callBack.leftBtClicked();
                        }
                    }
                }).show();
        setAlertDialogHeightWidth(alertDialog, -1, 0);
    }
    /**
     * @param context
     * @param msg
     * @param leftBt
     * @param rightBt
     */
    public void showMaterialAlterDialog(Context context, String title,int  icon,String msg, String leftBt, String rightBt, final OnAlterDialogCallBack callBack) {
        AlertDialog alertDialog = new AlertDialog.Builder(context)
                .setTitle(title)
                .setIcon(icon)
                .setCancelable(false)
                .setMessage(msg)
                .setPositiveButton(rightBt, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        if (callBack != null) {
                            callBack.rightBtClicked();
                        }
                    }
                }).setNegativeButton(leftBt, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        if (callBack != null) {
                            callBack.leftBtClicked();
                        }
                    }
                }).show();
        setAlertDialogHeightWidth(alertDialog, -1, 0);
    }
    /**
     * @param context
     * @param msg
     * @param leftBt
     * @param rightBt
     */
    public void showMaterialAlterDialog(Context context, String title,String msg, String leftBt, String rightBt, final OnAlterDialogCallBack callBack) {
        AlertDialog alertDialog = new AlertDialog.Builder(context)
                .setTitle(title)
                .setCancelable(false)
                .setMessage(msg)
                .setPositiveButton(rightBt, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        if (callBack != null) {
                            callBack.rightBtClicked();
                        }
                    }
                }).setNegativeButton(leftBt, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        if (callBack != null) {
                            callBack.leftBtClicked();
                        }
                    }
                }).show();
        setAlertDialogHeightWidth(alertDialog, -1, 0);
    }

//    /**
//     *
//     * @param context
//     * @param layoutId
//     */
//    public void  showMaterialAlterDialogCustomView(Context context,int  layoutId){
//        View view = LayoutInflater.from(context).inflate(layoutId, null);
//        final AlertDialog alertDialog = new AlertDialog.Builder(context)
//                .setView(view)
//                .setCancelable(false)
//                .show();
//        setAlertDialogHeightWidth(alertDialog, -1, 0);
//
//    }
    /**
     * 弹框按钮的回调
     */
    public interface OnAlterDialogCallBack {
        void leftBtClicked();

        void rightBtClicked();
    }
//    /**
//     * 自定义布局弹框按钮的回调
//     */
//    public interface OnAlterDialogCustomViewCallBack {
//
//        void customView(View view);
//    }
}
