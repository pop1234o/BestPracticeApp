package com.liyafeng.view.listview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Created by liyafeng on 07/10/2017.
 */

public class CustomListView extends LinearLayout {

    private float rawX;
    private float rawY;
    private int t;

    public CustomListView(Context context) {
        super(context);
    }

    public CustomListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        switch (event.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                rawX = event.getRawX();
//                rawY = event.getRawY();
//                break;
//            case MotionEvent.ACTION_MOVE:
//                float crawX = event.getRawX();
//                float crawY = event.getRawY();
//
//                float dy = crawY - rawY;
////                setTranslationY(getTranslationY()+dy);
//
////                scrollBy(0, (int) -dy);
////                overScrollBy(0, (int) dy, 0, t, 0, 0, 0, 0, true);
//
//                scrollTo(0, (int) (getScrollY()-dy));
//                int childCount = getChildCount();
////                for (int i = 0; i < childCount; i++) {
////                getChildAt(i).offsetTopAndBottom((int) dy);
////                }
//                rawX = event.getRawX();
//                rawY = event.getRawY();
//
//
//                break;
//            case MotionEvent.ACTION_UP:
//                break;
//        }
//
//        return true;
//    }

}
