package com.liyafeng.view.recycleview.pullrefresh;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import static android.R.attr.top;

/**
 * Created by liyafeng on 10/10/2017.
 */

public class PullToRefreshRecyclerView extends RecyclerView {
    private final String TAG = getClass().getSimpleName();
    private float rawY;
    private Paint paint;

    public PullToRefreshRecyclerView(Context context) {
        super(context);
        init();
    }

    public PullToRefreshRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PullToRefreshRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(Color.RED);
    }

    private float lastX;
    private float lastY;

    @Override
    public void onDraw(Canvas c) {
        super.onDraw(c);
        int top = getChildAt(0).getTop();
        c.drawCircle(c.getWidth()/2,top,30,paint);
        Log.i(TAG, "onDraw: ");
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        float dy = 0;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastY = event.getRawY();
                break;
            case MotionEvent.ACTION_UP:
                break;
            case MotionEvent.ACTION_MOVE:
                dy = event.getRawY() - lastY;

                lastY = event.getRawY();

                break;

        }


        View childAt = getChildAt(0);
        int position = getChildAdapterPosition(childAt);
        if (position == 0) {

            int top = childAt.getTop();

//            switch (e.getAction()) {
//                case MotionEvent.ACTION_DOWN:
//                    float rawY = e.getRawY();
//                    break;
//            }

            int childCount = getChildCount();
            for (int i = 0; i < childCount; i++) {
                getChildAt(i).offsetTopAndBottom((int) dy);

            }
            invalidate();
            return true;
        }
        Log.i(TAG, "onTouchEvent: " + top);

        return super.onTouchEvent(event);

    }
}
