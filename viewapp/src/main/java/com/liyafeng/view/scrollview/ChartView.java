package com.liyafeng.view.scrollview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Scroller;

/**
 * Created by liyafeng on 2018/1/22.
 */

public class ChartView extends RelativeLayout {
    public ChartView(Context context) {
        super(context);
        init(context);
    }

    public ChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ChartView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        InnerView innerView = new InnerView(context);
        addView(innerView);
    }

    class InnerView extends View {

        private Scroller scroller;
        private VelocityTracker tracker;
        private float rawX;
        private Paint paint;
        private int durationX = 100;//点之间的间距
        private int  pointNum = 100;

        public InnerView(Context context) {
            super(context);
            init(context);
        }

        public InnerView(Context context, @Nullable AttributeSet attrs) {
            super(context, attrs);
            init(context);
        }

        public InnerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
            init(context);
        }

        private void init(Context context) {
            scroller = new Scroller(context);
            paint = new Paint();
            paint.setColor(Color.parseColor("#000000"));
            paint.setTextSize(40);
        }

        /**
         * @param canvas
         * 可以根据间距 算出 startIndex ,endIndex
         * 然后只循环之个区间的数据
         * 他们的坐标就是 index*durationX ,
         */
        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            int scrollX = getScrollX();

            int width = canvas.getWidth();

            for (int i = 0; i < pointNum; i++) {

                int x = i * durationX;
                if (x >= scrollX &&x<=scrollX + width){
                    canvas.drawText(String.valueOf(i), x, 300, paint);
                }
            }
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
                    break;
                case MotionEvent.ACTION_MOVE:
                    float crawX = event.getRawX();

                    float dx = crawX - rawX;

                    scrollBy((int) -dx, 0);
                    rawX = event.getRawX();


                    break;
                case MotionEvent.ACTION_UP:
                    tracker.computeCurrentVelocity(1000);
                    float xVelocity = tracker.getXVelocity();
                    scroller.fling(getScrollX(), 0,  -(int)xVelocity, 0, 0, durationX*pointNum-getWidth(), 0, 0);
                    invalidate();
                    tracker.clear();
                    break;
            }

            return true;
        }


        @Override
        public void computeScroll() {
            if (scroller.computeScrollOffset()) {
                scrollTo(scroller.getCurrX(), 0);
            }
        }
    }
}
