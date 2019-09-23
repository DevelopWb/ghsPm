package com.ghs.ghspm.customView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ghs.ghspm.R;


public class OrganizeUserView extends RelativeLayout {
    private TextView titleBarLeftTv;
    private TextView titleBarRightTv;

    public OrganizeUserView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.mine_edit_custom_view, this, true);
        titleBarLeftTv = (TextView) findViewById(R.id.mine_edit_left);
        titleBarRightTv = (TextView) findViewById(R.id.mine_edit_right);
        initViewStatus(context, attrs);
    }

    private void initViewStatus(Context context, AttributeSet attrs) {
        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.OrganizeUserView);
        if (attributes != null) {
            //处理titleBar背景色
//            int titleBarBackGround = attributes.getResourceId(R.styleable.MineEditCustomView_title_background_color,R.color.navigition_press);
//            setBackgroundResource(titleBarBackGround);
            //先处理左边按钮
            //获取是否要显示左边按钮
            boolean leftButtonVisible = attributes.getBoolean(R.styleable.OrganizeUserView_left_button_visible, true);
            if (leftButtonVisible) {
                titleBarLeftTv.setVisibility(View.VISIBLE);
            } else {
                titleBarLeftTv.setVisibility(View.INVISIBLE);
            }
            //设置左边按钮的文字
            String leftButtonText = attributes.getString(R.styleable.OrganizeUserView_left_button_text);
            if (!TextUtils.isEmpty(leftButtonText)) {
                titleBarLeftTv.setText(leftButtonText);
//                //设置左边按钮文字颜色
                int leftButtonTextColor = attributes.getColor(R.styleable.OrganizeUserView_left_button_text_color, Color.RED);
                titleBarLeftTv.setTextColor(leftButtonTextColor);
            }


            //先处理右边按钮
            //获取是否要显示右边按钮
            boolean rightButtonVisible = attributes.getBoolean(R.styleable.OrganizeUserView_right_button_visible, true);
            if (rightButtonVisible) {
                titleBarRightTv.setVisibility(View.VISIBLE);
            } else {
                titleBarRightTv.setVisibility(View.INVISIBLE);
            }
            //设置右边按钮的文字
            String rightButtonText = attributes.getString(R.styleable.OrganizeUserView_right_button_text);
            if (!TextUtils.isEmpty(rightButtonText)) {
                titleBarRightTv.setText(rightButtonText);
                //设置右边按钮文字颜色
                int rightButtonTextColor = attributes.getColor(R.styleable.OrganizeUserView_right_button_text_color, Color.WHITE);
                titleBarRightTv.setTextColor(rightButtonTextColor);
            }
            attributes.recycle();
        }
    }


    /**
     * 获取左控件实例
     * @return
     */
    public TextView gettitleBarLeftTv() {
        return titleBarLeftTv;
    }
    /**
     * 获取右控件实例
     * @return
     */
    public TextView gettitleBarRightTv() {
        return titleBarRightTv;
    }


    /**
     *设置左控件图标的大小
     */
    public void settitleBarLeftTvIconSize(int width,int height){
        ViewGroup.LayoutParams params =  titleBarLeftTv.getLayoutParams();
        params.width= width;
        params.height=height;
        titleBarLeftTv.setLayoutParams(params);
    }

    /**
     *设置左控件图标的大小
     */
    public void settitleBarRightTvIconSize(int width,int height){
        ViewGroup.LayoutParams params =  titleBarRightTv.getLayoutParams();
        params.width= width;
        params.height=height;
        titleBarRightTv.setLayoutParams(params);
    }
}