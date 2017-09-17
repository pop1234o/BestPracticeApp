package com.liyafeng.view.redpoint;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import android.widget.RelativeLayout;

import java.lang.reflect.Field;

import static com.liyafeng.view.MainActivity.TAG;


/**
 * Created by liyafeng on 2017/9/10.
 */

public class PointView extends View {

    private Paint paint;
    private float x;
    private float y;
    private View v;
    private Bitmap drawingCache;

    public PointView(Context context) {
        super(context);
        init();
    }

    public PointView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PointView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();
//        paint.setColor(Color.RED);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawBitmap(drawingCache,0,0,paint);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x = event.getRawX();
                y = event.getRawY();
                PointLayout layout = (PointLayout) getParent();
                layout.pointDown();
                break;
            case MotionEvent.ACTION_UP:
                ViewParent parent = getParent();
                PointLayout layout1 = (PointLayout) parent;
                layout1.pointUp();
                break;
            case MotionEvent.ACTION_MOVE:
                float dx = event.getRawX() - x;
                float dy = event.getRawY() - y;

                setTranslationX(getTranslationX() + dx);
                setTranslationY(getTranslationY() + dy);

                x = event.getRawX();
                y = event.getRawY();
                requestLayout();
                break;

        }

        return true;

    }

    public void setView(View v) {


        this.v = v;

        v.setDrawingCacheEnabled(true);
        drawingCache = v.getDrawingCache();
        int[] ints = new int[2];
        v.getLocationInWindow(ints);
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) getLayoutParams();
        layoutParams.leftMargin = ints[0];
        layoutParams.topMargin = ints[1];
        Log.i(TAG, "setView: " + ints[0] + " " + ints[1]);
        layoutParams.width = v.getWidth();
        layoutParams.height=v.getHeight();
        setLayoutParams(layoutParams);
    }

    public static int getStatusBarHeight(Context context) {
        Class<?> c = null;
        Object obj = null;
        Field field = null;
        int x = 0, statusBarHeight = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            statusBarHeight = context.getResources().getDimensionPixelSize(x);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return statusBarHeight;
    }
}
