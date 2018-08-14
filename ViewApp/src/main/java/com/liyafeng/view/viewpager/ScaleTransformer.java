package com.liyafeng.view.viewpager;

import android.support.v4.view.ViewPager;
import android.view.View;


public class ScaleTransformer implements ViewPager.PageTransformer {
    private static final float MIN_SCALE = 0.9f;
//    private static final float MIN_ALPHA = 0.5f;

    @Override
    public void transformPage(View page, float position) {//position 当前位置是0，前一个是-1，后一个是1
        if (position < -1 || position > 1) {
//            page.setAlpha(MIN_ALPHA);
            page.setScaleX(MIN_SCALE);
            page.setScaleY(MIN_SCALE);
        } else if (position <= 1) { // [-1,1]
//            float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
            if (position < 0) {
                float scaleX = 1 + (1 - MIN_SCALE) * position;
//                Log.d("google_lenve_fb", "transformPage: scaleX:" + scaleX);
                page.setScaleX(scaleX);
                page.setScaleY(scaleX);
            } else {
                float scaleX = 1 - (1 - MIN_SCALE) * position;
                page.setScaleX(scaleX);
                page.setScaleY(scaleX);
            }
//            page.setAlpha(MIN_ALPHA + (scaleFactor - MIN_SCALE) / (1 - MIN_SCALE) * (1 - MIN_ALPHA));
        }
    }
}
