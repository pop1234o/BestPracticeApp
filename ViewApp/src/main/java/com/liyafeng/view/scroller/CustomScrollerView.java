package com.liyafeng.view.scroller;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.Scroller;

import static com.liyafeng.view.MainActivity.TAG;

/**
 * Created by liyafeng on 07/10/2017.
 */

public class CustomScrollerView extends FrameLayout {
    private Scroller scroller;
    private float rawX;
    private float rawY;
    private VelocityTracker tracker;
    private int width;
    private int height;

    public CustomScrollerView(Context context) {
        super(context);
        init(context);
    }

    public CustomScrollerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CustomScrollerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {

        scroller = new Scroller(context);


        WindowManager systemService = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        width = systemService.getDefaultDisplay().getWidth();
        height = systemService.getDefaultDisplay().getHeight();
        Log.i(TAG, "init: " + height + "  w" + width);
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
                scroller.abortAnimation();
                break;
            case MotionEvent.ACTION_MOVE:
                float crawX = event.getRawX();
                float crawY = event.getRawY();

                float dy = crawY - rawY;
                float dx = crawX - rawX;

                scrollBy((int) -dx, (int) -dy);
                rawX = event.getRawX();
                rawY = event.getRawY();


                break;
            case MotionEvent.ACTION_UP:
                tracker.computeCurrentVelocity(1000);
                float xVelocity = tracker.getXVelocity();
                float yVelocity = tracker.getYVelocity();
                scroller.fling(-getScrollX(), -getScrollY(), (int) xVelocity * 2, (int) yVelocity * 2, 0, width - 500, 0, height - 500);

                invalidate();
                tracker.clear();
                tracker.recycle();
                tracker = null;
                break;
        }

        return true;
    }

    public void smoothScrollTo(int x, int y) {
        scroller.startScroll(0, 0, x, y, 1000);
        invalidate();
    }

    @Override
    public void computeScroll() {
        if (scroller.computeScrollOffset()) {
            scrollTo(-scroller.getCurrX(), -scroller.getCurrY());
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save()
    }
}
