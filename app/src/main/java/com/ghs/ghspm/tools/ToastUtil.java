package com.ghs.ghspm.tools;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ghs.ghspm.R;

/**
 * Author:wang_sir
 * Time:2018/8/10 15:00
 * Description:This is ToastUtil
 */
public class ToastUtil {
//    private Context context;
    private  static Toast toast;//必须设为静态的 要不实现不了效果

    /** 之前显示的内容 */
    private static String oldMsg ;
    /** Toast对象 */
    private static  Toast mToast = null ;
    /** 第一次时间 */
    private static long oneTime = 0 ;
    /** 第二次时间 */
    private static long twoTime = 0 ;



//    private ToastUtil(Context context) {
//        this.context = context.getApplicationContext();
//
//
//    }
//
//    public static ToastUtil getInstance(Context context) {
//        return ToastUtilHolder.getInstance(context);
//    }
//
//    private static class ToastUtilHolder {
//        private static ToastUtil getInstance(Context context) {
//            return new ToastUtil(context);
//        }
//    }

    /**
     * 将Toast封装在一个方法中，在其它地方使用时直接输入要弹出的内容即可
     */


    public static void showToast(Context context ,String messages) {
        context = context.getApplicationContext();
        View view = LayoutInflater.from(context).inflate(R.layout.custom_toast, null);
        TextView content = (TextView) view.findViewById(R.id.custom_toast_message_tv);
        if (toast == null) {
            toast = new Toast(context);
        }
        content.setText(messages);
        toast.setGravity(Gravity.CENTER, 12, 20);//setGravity用来设置Toast显示的位置，相当于xml中的android:gravity或android:layout_gravity
        toast.setDuration(Toast.LENGTH_SHORT);//setDuration方法：设置持续时间，以毫秒为单位。该方法是设置补间动画时间长度的主要方法
        toast.setView(view); //添加视图文件
        toast.show();
    }

    /**
     * 普通展示
     * @param msg
     */
    public static void showNormalToast(Context context,int duration,String msg){
        context = context.getApplicationContext();

        if (mToast == null) {
            mToast =  Toast.makeText(context,msg,duration);
        }else{
            mToast.setText(msg);
            mToast.setDuration(duration);
        }
        mToast.show();
    }


//    /**
//     * 显示Toast
//     * @param context
//     * @param message
//     */
//    public static void showToast(Context context,int duration,String message){
//        if(mToast == null){
//            mToast = Toast.makeText(context, message, duration);
//            mToast.show() ;
//            oneTime = System.currentTimeMillis() ;
//        }else{
//            twoTime = System.currentTimeMillis() ;
//            if(message.equals(oldMsg)){
//                if(twoTime - oneTime > duration){
//                    mToast.show() ;
//                }
//            }else{
//                oldMsg = message ;
//                mToast.setText(message) ;
//                mToast.show() ;
//            }
//        }
//        oneTime = twoTime ;
//    }
}


