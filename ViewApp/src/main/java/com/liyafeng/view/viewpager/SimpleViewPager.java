package com.liyafeng.view.viewpager;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

import static com.liyafeng.view.MainActivity.TAG;

/**
 * Created by liyafeng on 2017/9/20.
 */

public class SimpleViewPager extends ViewGroup {
    private SimplePagerAdapter adapter;
    private float rawX;
    private Scroller scroller;
    private VelocityTracker velocityTracker;

    public SimpleViewPager(Context context) {
        super(context);
        init(context);
    }


    public SimpleViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public SimpleViewPager(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        scroller = new Scroller(context);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        int childCount = getChildCount();
        int width = r - l;
        for (int i = 0; i < childCount; i++) {

            View childAt = getChildAt(i);

            int left = l + width * i;

            childAt.layout(left, t, left + childAt.getMeasuredWidth(), t + childAt.getMeasuredHeight());
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = View.getDefaultSize(0, widthMeasureSpec);
        int height = View.getDefaultSize(0, heightMeasureSpec);
        setMeasuredDimension(width, height);

        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            int spec_w = MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY);
            int spec_h = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);
            childAt.measure(spec_w, spec_h);
        }

    }

    public void setAdapter(SimplePagerAdapter adapter) {

        this.adapter = adapter;
        populate();
    }

    public void populate() {
        //添加子布局
        int count = adapter.getCount();
        for (int i = 0; i < count; i++) {
            adapter.instantiateItem(this, i);
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();


        if (velocityTracker == null) {
            velocityTracker = VelocityTracker.obtain();
        }
        velocityTracker.addMovement(event);
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                rawX = event.getRawX();
                if (!scroller.isFinished()) {
                    scroller.abortAnimation();
                }

                break;
            case MotionEvent.ACTION_MOVE:
                int dx = (int) (event.getRawX() - rawX);

                int x = getScrollX() - dx;
                if (x < 0) {
                    x = 0;
                } else if (x > (adapter.getCount() - 1) * getWidth()) {
                    x = (adapter.getCount() - 1) * getWidth();
                }

                scrollTo(x, 0);

                rawX = event.getRawX();
                break;
            case MotionEvent.ACTION_UP:

                int scrollX = getScrollX();
                int halfwidth = getWidth() / 2;
                int index = (scrollX + halfwidth) / getWidth();
                velocityTracker.computeCurrentVelocity(1000);
                float xVelocity = velocityTracker.getXVelocity();
//                Log.i(TAG, "onTouchEvent: "+index);
                smoothScrollTo(index, xVelocity);
                break;
        }
        return true;
    }


    private void smoothScrollTo(int itemIndex, float xVelocity) {

        if (itemIndex >= 0 && itemIndex < adapter.getCount()) {
            int scrollX = getScrollX();
//            Log.i(TAG, "onTouchEvent: "+scrollX);
            int dx = (itemIndex * getWidth()) - scrollX;
            xVelocity = Math.abs(xVelocity);
            int duration = 600;
            if (xVelocity > 0) {
//                duration = (int) Math.abs(dx / xVelocity) ;
                Log.i(TAG, "smoothScrollTo: " + dx + "  " + xVelocity+ " "+(dx / xVelocity)*1000);
            }
            scroller.startScroll(scrollX, getScrollY(), dx, getScrollY(), duration);
            invalidate();
        }
    }

    @Override
    public void computeScroll() {
        boolean b = scroller.computeScrollOffset();
        if (b) {
            scrollTo(scroller.getCurrX(), scroller.getCurrY());
            invalidate();
        }

    }

    public abstract static class SimplePagerAdapter {
        public abstract int getCount();

        public abstract Object instantiateItem(ViewGroup container, int position);
    }
}

