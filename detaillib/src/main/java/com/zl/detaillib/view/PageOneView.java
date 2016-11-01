package com.zl.detaillib.view;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ScrollView;

import com.zl.detaillib.utils.SizeUtils;

/**
 * Created by zl on 2016/11/1.
 */

public class PageOneView extends ScrollView {

    private float mOldY;
    private float mOldX;
    private int t;
    private int mHeight;

    public PageOneView(Context context) {
        this(context, null);
    }

    public PageOneView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PageOneView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public PageOneView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context){
        mHeight = SizeUtils.getScreenHeight(context) - SizeUtils.getStatusBarHeight(context) - SizeUtils.dp2px(context, 20);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action){
            case MotionEvent.ACTION_DOWN:
                getParent().getParent().requestDisallowInterceptTouchEvent(true);
                mOldY = ev.getY();
                mOldX = ev.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                float y = ev.getY();
                float gap = y - mOldY;
                float x = ev.getX();
                float gapHorizontal = x - mOldX;
                if (Math.abs(gapHorizontal) > 120) {
                    getParent().getParent().requestDisallowInterceptTouchEvent(false);
                }
                int measuredHeight = this.getChildAt(0).getMeasuredHeight();
                int padding = measuredHeight - t;
                if (gap < 0 && padding <= mHeight){
                    getParent().getParent().requestDisallowInterceptTouchEvent(false);
                }
                break;
            case  MotionEvent.ACTION_UP:
                getParent().getParent().requestDisallowInterceptTouchEvent(false);
                break;
        }
        return super.onTouchEvent(ev);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        this.t = t;
        Log.e("", "===onScrollChanged==PageOne="+l+"========"+t+"======="+oldl+"=========="+oldt);
        super.onScrollChanged(l, t, oldl, oldt);
    }

    @Override
    protected boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY, int scrollRangeX, int scrollRangeY, int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {
        Log.e("","====overScrollBy===PageOneView==="+deltaX+"========"+deltaY);
        return super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX, scrollRangeY, maxOverScrollX, maxOverScrollY, isTouchEvent);
    }
}
