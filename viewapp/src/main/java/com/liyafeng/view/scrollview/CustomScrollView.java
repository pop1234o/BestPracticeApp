package com.liyafeng.view.scrollview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/**
 * Created by liyafeng on 07/10/2017.
 */

public class CustomScrollView extends FrameLayout {
    private float rawX;
    private float rawY;
    private int scrollY;

    public CustomScrollView(Context context) {
        super(context);
        init();
    }

    public CustomScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

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
                break;
        }

        return true;
    }

}
