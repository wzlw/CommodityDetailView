package com.zl.detaillib.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.zl.detaillib.utils.SizeUtils;
import com.zl.pullimagedemo.detaillib.R;

/**
 * Created by zl on 2016/11/1.
 */

public class DetailView extends ScrollView {

    private int mHeight;
    private boolean isSetted = false;
    private PageOneView mPageOne;
    private DetailWebView mDetailView;
    private boolean isPageOne = true;
    private OnLoadUrlListener listener;
    private Context context;
    private boolean isLoad = true;

    public DetailView(Context context) {
        this(context, null);
    }

    public DetailView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DetailView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public DetailView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        this.context = context;
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.WapperPadding);
        float dimension = a.getDimension(R.styleable.WapperPadding_padding, 0);
        mHeight = SizeUtils.getScreenHeight(context) - SizeUtils.getStatusBarHeight(context) - SizeUtils.dp2px(context, dimension);
        a.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (!isSetted) {
            LinearLayout parent = (LinearLayout) getChildAt(0);
            mPageOne = (PageOneView) parent.getChildAt(0);
            mDetailView = (DetailWebView) parent.getChildAt(1);
            mPageOne.getLayoutParams().height = mHeight;
            mDetailView.getLayoutParams().height = mHeight;
            if (listener != null){
                listener.initWebView(mDetailView);
            }
            isSetted = true;
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (changed) {
            this.scrollTo(0, 0);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        if (mDetailView.getContentHeight() * mDetailView.getScale() == (mDetailView.getHeight() + mDetailView.getScrollY())) {
            return true;
        }
        switch (action) {
            case MotionEvent.ACTION_UP:
                int scrollY = getScrollY();
                int creteria = mHeight / 5;
                if (isPageOne) {
                    if (scrollY <= creteria) {
                        this.smoothScrollTo(0, 0);
                    } else {
                        this.smoothScrollTo(0, mHeight);
                        if (listener != null && isLoad){
                            mDetailView.loadUrl(listener.loadUrl());
                            isLoad = false;
                        }
                        this.setFocusable(false);
                        isPageOne = false;
                    }
                } else {
                    int scrollPadding = mHeight - getScrollY();
                    if (scrollPadding > creteria) {
                        this.smoothScrollTo(0, 0);
                        isPageOne = true;
                    } else {
                        this.smoothScrollTo(0, mHeight);
                        if (listener != null && isLoad){
                            mDetailView.loadUrl(listener.loadUrl());
                            isLoad = false;
                        }
                    }
                }

                return true;
        }
        return super.onTouchEvent(ev);
    }

    public interface OnLoadUrlListener{
        String loadUrl();
        void initWebView(WebView view);
    }
    
    public void setOnLoadUrlListener(OnLoadUrlListener listener){
        this.listener = listener;
    }

    @Override
    protected boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY, int scrollRangeX, int scrollRangeY, int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {
//        Log.e("","====overScrollBy===DetailView==="+deltaX+"========"+deltaY);
        return super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX, scrollRangeY, maxOverScrollX, maxOverScrollY, isTouchEvent);
    }
}
