package com.liyafeng.view.drag;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by liyafeng on 2017/9/13.
 */

public class DragView extends View {
    String TAG = getClass().getSimpleName();
    private Paint paint;

    public DragView(Context context) {
        super(context);
    }

    public DragView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        paint.setColor(Color.GREEN);
    }

    public DragView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }
    private float lastX;
    private float lastY;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(10,10,10,paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastX = event.getRawX();
                lastY = event.getRawY();
                break;
            case MotionEvent.ACTION_UP:
                break;
            case MotionEvent.ACTION_MOVE:
                float dx = event.getRawX() - lastX;
                float dy = event.getRawY() - lastY;

                move(dx, dy);
                lastX = event.getRawX();
                lastY = event.getRawY();

                break;

        }

        return true;

    }

    private void move(float dx, float dy) {
        setTranslationX(getTranslationX()+dx);
        setTranslationY(getTranslationY()+dy);
    }

    /**
     * 判断是拦截垂直滑动，不拦截水平滑动
     * https://www.jianshu.com/p/15e12fde7d1d  Android-解决事件冲突和处理滑动事件
     *
     * ，第一次 onInterceptTouchEvent ACTION_DOWN 拦截  return super.onInterceptTouchEvent(ev); true
     * 然后 onTouchEvent ACTION_MOVE
     *
     * @param ev
     * @return
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

            switch (ev.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    startX = (int) ev.getX();
                    startY = (int) ev.getY();
                    Log.i("test","ACTION_DOWN "+startY);
                    break;
                case MotionEvent.ACTION_MOVE:

                    int dX = (int) (ev.getX() - startX);
                    int dY = (int) (ev.getY() - startY);
                    Log.i("test","ACTION_MOVE "+startY);
                    if (Math.abs(dX) > Math.abs(dY)) {//左右滑动 不拦截事件
                        return false;
                    } else {//上下滑动 拦截
                        return true;
                    }
                case MotionEvent.ACTION_UP:
                    break;
            }
        return super.onInterceptTouchEvent(ev);
    }


//    private void move(float dx, float dy) {
//        int x = (int) -dx;
//        int y = (int) -dy;
//        this.scrollBy(x, y);
//    }

//    private void move(float dx, float dy) {
//
//        setLeft((int) (getLeft()+dx));
//        setRight((int) (getRight()+dx));
//        setTop((int) (getTop()+dy));
//        setBottom((int) (getBottom()+dy));
//
//    }

//    private void move(float dx, float dy) {
//
//
//        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) getLayoutParams();
//        layoutParams.topMargin +=dy;
//        layoutParams.leftMargin+=dx;
//        setLayoutParams(layoutParams);
//    }
}
