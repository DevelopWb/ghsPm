package com.ghs.ghspm.customView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import com.ghs.ghspm.R;


public class HorizontalProgressBar extends View {

    /**
     * 渐变颜色组
     */
    private int[] GRADIENT_COLORS = {Color.parseColor("#FFFAEDCC"), Color.parseColor("#FFF9CC4F"),};
    /**
     * 最大进度
     */
    private float max = 100;
    /**
     * 当前进度
     */
    private float mProgress;
    /**
     * 画笔
     */
    private Paint mPaint;
    /**
     * 外描边的宽度
     */
    private float BORDER_STROCK;
    //进度条的宽高
    private int mWidth, mHeight;
    private int progressBarWidth;//宽度
    /**
     * 画进度条的矩形
     */
    private RectF mRectF;


    private int topTextColor;//顶部text的颜色
    private int bottomTextColor;//顶部text的颜色
    private float topTextSize;//顶部text的大小
    private float progressBarHeight;//prgressBar的高度
    private float bottomTextSizer;//顶部text的大小
    private Bitmap mTopBackgroundBitmap;//顶部text的背景
    private int mTopBackgroundBitmapWidth;//顶部text的背景的宽度
    private int mTopBackgroundBitmapHeight;//顶部text的背景的高度
    private String topText = "";//顶部text内容
    private float topTextWidth;//顶部text内容的宽度
    /**
     * 顶部文字基线Y坐标
     */
    private float mTopTextBaseLineY;
    private int paddingLeft;
    private int paddingRight;
    private int paddingTop;
    private int paddingBottom;
    private Bitmap newBmp;
    private LinearGradient shader;
    private float section;


    public HorizontalProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs, defStyleAttr);
        paddingLeft = getPaddingLeft();
        paddingRight = getPaddingRight();
        paddingTop = getPaddingTop();
        paddingBottom = getPaddingBottom();
    }

    public HorizontalProgressBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HorizontalProgressBar(Context context) {
        this(context, null);
    }

    private void initView(Context context, AttributeSet attrs, int defStyleAttr) {
        section = mProgress / max;

        //创建线性颜色渐变器
        shader = new LinearGradient( paddingLeft,  mTopBackgroundBitmapHeight + paddingTop,
                progressBarWidth * section +paddingRight, progressBarHeight + mTopBackgroundBitmapHeight + paddingTop, GRADIENT_COLORS, null, Shader.TileMode.MIRROR);

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mRectF = new RectF();
        TypedArray ta = context.getTheme().obtainStyledAttributes(attrs, R.styleable.MProgressBar, defStyleAttr, 0);
        int count = ta.getIndexCount();
        for (int i = 0; i < count; i++) {
            int index = ta.getIndex(i);
            switch (index) {
                case R.styleable.MProgressBar_top_textColor:
                    topTextColor = ta.getColor(index, Color.BLACK);
                    break;
                case R.styleable.MProgressBar_bottom_textColor:
                    bottomTextColor = ta.getColor(index, Color.BLACK);
                    break;
                case R.styleable.MProgressBar_progressBarHeight:
                    progressBarHeight = ta.getDimension(index, 10);
                    break;
                case R.styleable.MProgressBar_top_textSize:
                    topTextSize = ta.getDimension(index, 24f);
                    break;
                case R.styleable.MProgressBar_bottom_textSize:
                    bottomTextSizer = ta.getDimension(index, 24f);
                    break;

                case R.styleable.MProgressBar_top_textBackground:
                    int bgResId = ta.getResourceId(index, R.mipmap.custom_progressbar_text_bg);
                    //获取文字背景图片的宽高
                    mTopBackgroundBitmap = BitmapFactory.decodeResource(getResources(), bgResId);
                    mTopBackgroundBitmapWidth = mTopBackgroundBitmap.getWidth();
                    mTopBackgroundBitmapHeight = mTopBackgroundBitmap.getHeight();
                    break;

            }
        }
        ta.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        progressBarWidth = mWidth-paddingLeft-paddingRight;
        int round = Math.round(progressBarHeight / 2);//弧度为高度的一半
        if (mProgress == 0)//进度为 0不画进度
            return;
        //第一层矩形(进度层底部背景层)
        mRectF.set( paddingLeft, mTopBackgroundBitmapHeight + paddingTop, progressBarWidth+paddingRight, progressBarHeight + mTopBackgroundBitmapHeight + paddingTop);
        mPaint.setColor(Color.parseColor("#FFCCCCCC"));//
        canvas.drawRoundRect(mRectF, round, round, mPaint);//
        //第二层矩形(进度层)
        mRectF.set(paddingLeft, mTopBackgroundBitmapHeight + paddingTop, progressBarWidth * section+paddingRight, progressBarHeight + mTopBackgroundBitmapHeight + paddingTop);
        canvas.drawRoundRect(mRectF, round, round, mPaint);//画第三层(进度层)圆角矩形(盖在背景层之上)
        mPaint.setShader(shader);//第三层矩形颜色(进度渐变色)
        canvas.drawRoundRect(mRectF, round, round, mPaint);//画第三层(进度层)圆角矩形(盖在背景层之上)
        mPaint.setShader(null);//清除之前传递的shader

        float textX = progressBarWidth-30;
        float textY = progressBarHeight + 40 + mTopBackgroundBitmapHeight + paddingTop;
        mPaint.setColor(Color.parseColor("#000000"));//
        mPaint.setTextSize(32);
        //绘制文字
        canvas.drawText(String.valueOf(Math.round(max)), textX, textY, mPaint);

        getTopTextLocation();
        newBmp = Bitmap.createScaledBitmap(mTopBackgroundBitmap, Math.round(getTextWidth(topText))+26, mTopBackgroundBitmap.getHeight(), true);
        mPaint.setColor(Color.parseColor("#FF15A270"));//
        //计算数值背景X坐标
        float bgX = progressBarWidth * section+paddingLeft-newBmp.getWidth()/2;
        //计算数值背景Y坐标
        float bgY = -10 + paddingTop;
        //计算数值文字X坐标
        float top_textX = bgX+(newBmp.getWidth()-getTextWidth(topText))/2;
        float top_textY = bgY + mTopBackgroundBitmapHeight / 2+8;
        //绘制文字和背景
        canvas.drawBitmap(newBmp, bgX, bgY, mPaint);
        canvas.drawText(topText, top_textX, top_textY, mPaint);

    }

    /**
     * 计算顶部文字的显示位置
     */
    private void getTopTextLocation() {
        Paint.FontMetrics fm = mPaint.getFontMetrics();
        topText = String.valueOf(Math.round(mProgress));
        //测量文字宽度
        topTextWidth = mPaint.measureText(topText);
        //计算文字基线Y坐标
        mTopTextBaseLineY = mTopBackgroundBitmapHeight / 2 - fm.descent + (fm.descent - fm.ascent) / 2;
    }

    /***
     * 设置最大进度
     *
     * @param maxCount
     */
    public void setMax(float maxCount) {
        this.max = maxCount;
    }

    /***
     * 设置当前进度
     *
     * @param currentCount
     */
    public void setProgress(float currentCount) {
        this.mProgress = currentCount > max ? max : currentCount;
        postInvalidate();
    }

    public float getMax() {
        return max;
    }


    /**
     * 根据文本的
     *
     * @param text
     * @return
     */
    public float getTextWidth(String text) {

        Rect rect = new Rect();
        mPaint.getTextBounds(text, 0, text.length(), rect);
        return rect.width();//文字宽

    }

    /**
     * 根据文本的
     *
     * @param text
     * @return
     */
    public float getTextHeight(String text) {

        Rect rect = new Rect();
        mPaint.getTextBounds(text, 0, text.length(), rect);
        return rect.height();//文字高

    }
    /**
     * 测量得到进度条的宽高
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);
        if (widthSpecMode == MeasureSpec.EXACTLY || widthSpecMode == MeasureSpec.AT_MOST) {
            mWidth = widthSpecSize;
        } else {
            mWidth = 0;
        }
        if (heightSpecMode == MeasureSpec.AT_MOST || heightSpecMode == MeasureSpec.UNSPECIFIED) {
            mHeight = (int) getResources().getDimension(R.dimen.x20);
        } else {
            mHeight = heightSpecSize;
        }
        setMeasuredDimension(mWidth, mHeight);

    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if(newBmp != null && !newBmp.isRecycled()){
            newBmp.recycle();
            newBmp = null;
        }
        if(mTopBackgroundBitmap != null && !mTopBackgroundBitmap.isRecycled()){
            mTopBackgroundBitmap.recycle();
            mTopBackgroundBitmap = null;
        }
        System.gc();
    }
}
