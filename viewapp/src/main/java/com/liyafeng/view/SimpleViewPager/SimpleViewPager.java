package com.liyafeng.view.SimpleViewPager;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by liyafeng on 2017/9/20.
 */

public class SimpleViewPager extends ViewGroup {
    private SimplePagerAdapter adapter;
    private float rawX;

    public SimpleViewPager(Context context) {
        super(context);
        init();
    }


    public SimpleViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SimpleViewPager(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
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
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                rawX = event.getRawX();
                break;
            case MotionEvent.ACTION_MOVE:
                float dx = event.getRawX() - rawX;

                scrollBy((int) -dx,0);

                rawX = event.getRawX();
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return true;
    }



    public abstract static class SimplePagerAdapter {
        public abstract int getCount();

        public abstract Object instantiateItem(ViewGroup container, int position);
    }
}

