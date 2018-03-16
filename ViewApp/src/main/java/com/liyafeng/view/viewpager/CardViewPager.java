package com.liyafeng.view.viewpager;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by liyafeng on 2017/9/21.
 */

public class CardViewPager extends ViewPager {
    private final String TAG = this.getClass().getSimpleName();


    private float scale = 0.15f;
    private OnPageChangeListener listener;

    public CardViewPager(Context context) {
        super(context);
        init();
    }


    public CardViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        setOffscreenPageLimit(3);
        setPageMargin(-350);
        setClipToPadding(false);
        int padding = 240;
        setPadding(padding, padding, padding, padding);

        super.setOnPageChangeListener(innerlistener);
    }

    OnPageChangeListener innerlistener = new OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                Log.i(TAG, "onPageScrolled: " + positionOffset + "  " + positionOffsetPixels + "  " + position);
            //2 0最大
            //1，0.9 -1，0变小  //2，0.9最小
            View view = getChildAt(position);
            if (view != null) {
                float scaleX = 1 + (1 - positionOffset) * scale;
                view.setScaleX(scaleX);
                view.setScaleY(scaleX);

            }
            View childAt = getChildAt(position + 1);
            if (childAt != null) {
                float scaleX1 = 1 + (positionOffset) * scale;
                childAt.setScaleX(scaleX1);
                childAt.setScaleY(scaleX1);
            }
            if (CardViewPager.this.listener != null) {
                CardViewPager.this.listener.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }
        }

        @Override
        public void onPageSelected(int position) {

            if (CardViewPager.this.listener != null) {
                CardViewPager.this.listener.onPageSelected(position);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

            if (CardViewPager.this.listener != null) {
                CardViewPager.this.listener.onPageScrollStateChanged(state);
            }
        }
    };

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (innerlistener != null) {
            int currentItem = getCurrentItem();
            if(currentItem>=0){
                View view = getChildAt(currentItem);
                if(view!=null){
                    view.setScaleX(1+scale);
                    view.setScaleY(1+scale);
                }
            }
        }
    }

    @Override
    public void setOnPageChangeListener(OnPageChangeListener listener) {
        this.listener = listener;
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }
}
