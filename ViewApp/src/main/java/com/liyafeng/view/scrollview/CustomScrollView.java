package com.liyafeng.view.scrollview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Scroller;

import static com.liyafeng.view.MainActivity.TAG;

/**
 * Created by liyafeng on 07/10/2017.
 */

public class CustomScrollView extends FrameLayout {
    private float rawX;
    private float rawY;
    private Scroller scroller;
    private VelocityTracker tracker;

    public CustomScrollView(Context context) {
        super(context);
        init(context);
    }

    public CustomScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CustomScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {

        scroller = new Scroller(context);

    }


    @Override
    protected void measureChildWithMargins(View child, int parentWidthMeasureSpec, int widthUsed, int parentHeightMeasureSpec, int heightUsed) {
        ViewGroup.LayoutParams lp = child.getLayoutParams();

        int childHeightMeasureSpec;


        childHeightMeasureSpec = MeasureSpec.makeMeasureSpec(
                MeasureSpec.getSize(parentHeightMeasureSpec), MeasureSpec.UNSPECIFIED);

        child.measure(parentWidthMeasureSpec, childHeightMeasureSpec);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (tracker == null) {

        tracker = VelocityTracker.obtain();
        }
        tracker.addMovement(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                rawX = event.getRawX();
                rawY = event.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                float crawX = event.getRawX();
                float crawY = event.getRawY();

                float dy = crawY - rawY;

                scrollBy(0, (int) -dy);
                rawX = event.getRawX();
                rawY = event.getRawY();


                break;
            case MotionEvent.ACTION_UP:
                tracker.computeCurrentVelocity(1000);
                float yVelocity = tracker.getYVelocity();
                Log.i(TAG, "onTouchEvent: "+getScrollY());
                scroller.fling(0,getScrollY(),0, -(int) yVelocity,0,0,0,5000);
                invalidate();
                tracker.clear();
                break;
        }

        return true;
    }


    @Override
    public void computeScroll() {
        if(scroller.computeScrollOffset()){
            scrollTo(0,scroller.getCurrY());
        }
    }
}
