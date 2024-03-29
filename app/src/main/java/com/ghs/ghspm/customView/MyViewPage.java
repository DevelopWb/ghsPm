package com.ghs.ghspm.customView;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class MyViewPage extends ViewPager {
	 private boolean noScroll = false;

	    public MyViewPage(Context context, AttributeSet attrs) {
	        super(context, attrs);
	    }

	    public MyViewPage(Context context) {
	        super(context);
	    }

	    public void setNoScroll(boolean noScroll) {
	        this.noScroll = noScroll;
	    }

	    @Override
	    public void scrollTo(int x, int y) {
	        super.scrollTo(x, y);
	    }

	    @Override
	    public boolean onTouchEvent(MotionEvent arg0) {
	        /* return false;//super.onTouchEvent(arg0); */

	        return !noScroll&&super.onTouchEvent(arg0);
	    }

	    @Override
	    public boolean onInterceptTouchEvent(MotionEvent arg0) {
			return !noScroll&&super.onInterceptTouchEvent(arg0);
	    }

	    @Override
	    public void setCurrentItem(int item, boolean smoothScroll) {
	        super.setCurrentItem(item, smoothScroll);
	    }

	    @Override
	    public void setCurrentItem(int item) {
	        super.setCurrentItem(item);
	    }
}