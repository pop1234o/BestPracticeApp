package com.liyafeng.view.redpoint;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.RelativeLayout;

/**
 * Created by liyafeng on 2017/9/10.
 */

public class PointLayout extends RelativeLayout {
    String TAG = getClass().getSimpleName();
    private Paint paint;
    private Path path;
    private Paint paint1;
    private Paint paint2;
    private boolean isLeave;

    private static final int DISTANCE = 300;
    private OnRemoveListenter onRemoveListenter;
    private int RADIUS_MOVE= 25;
    private int OUTLENGTH  = 500;
    private int ORGINLENGTH  = 25;

    public PointLayout(Context context) {
        super(context);
        init();
    }

    public PointLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PointLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(Color.RED);
        setWillNotDraw(false);
        path = new Path();
        paint1 = new Paint();
        paint1.setColor(Color.RED);
        paint2 = new Paint();
        paint2.setColor(Color.BLUE);
    }


    public void pointUp() {
        isLeave = false;
        double distance = getDistance();
        if(distance>DISTANCE){//消失
            onRemoveListenter.onRemove();
            ViewGroup viewGroup1 = (ViewGroup) getRootView();
            viewGroup1.removeView(this);
        }else {//回到原点
            View view = getChildAt(0);
            PropertyValuesHolder x = PropertyValuesHolder.ofFloat("translationX", view.getTranslationX(), 0);
            PropertyValuesHolder y = PropertyValuesHolder.ofFloat("translationY", view.getTranslationY(), 0);
            ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(view, x, y);
            animator.setDuration(150);
            animator.setInterpolator(new OvershootInterpolator());
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    invalidate();
                }
            });
            animator.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {

                    onRemoveListenter.onRestore();
                    ViewGroup viewGroup1 = (ViewGroup) PointLayout.this.getRootView();
                    viewGroup1.removeView(PointLayout.this);
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });
            animator.start();

        }
    }

    public void pointDown(){
    }

    double getDistance() {
        View view = getChildAt(0);

        float left = view.getTranslationX();
        float top = view.getTranslationY();
        float width = view.getWidth();
        float height = view.getHeight();
        float w = width / 2;
        float h = height / 2;
        float x = left + w;
        float y = top + h;

        double sqrt = Math.sqrt((x - w) * (x - w) + (y - h) * (y - h));
        return sqrt;

    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

    }

    private void doLeave() {
        if (isLeave) {
            return;
        }

        isLeave = true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        View view = getChildAt(0);
        float width = view.getWidth();
        float height = view.getHeight();
        int left1 = view.getLeft();
        int top1 = view.getTop();
        float w = left1+width / 2;
        float h = top1+height / 2;
        float x = left1 +view.getTranslationX()+ width / 2;
        float y = top1 +view.getTranslationY()+ height / 2;
        path.reset();


        //移动点
        canvas.drawCircle(x, y, RADIUS_MOVE, paint);


        double sqrt = Math.sqrt((x - w) * (x - w) + (y - h) * (y - h));


        if (sqrt >= DISTANCE || isLeave) {
            doLeave();
            return;
        }
        float p = (float) ((OUTLENGTH - sqrt) / OUTLENGTH);


        float radius = ORGINLENGTH * p;
        canvas.drawCircle(w, h, radius, paint);
//        Log.i(TAG, "draw: " + sqrt);
        double cose = (x - w) / sqrt;
        double sine = (y - h) / sqrt;


        double dy = RADIUS_MOVE * cose;
        double dx = RADIUS_MOVE * sine;
        double dy1 = radius * cose;
        double dx1 = radius * sine;


        float x1 = (float) (w - dx1);
        float y1 = (float) (h + dy1);

        float x4 = (float) (w + dx1);
        float y4 = (float) (h - dy1);


        float x2 = (float) (x - dx);
        float y2 = (float) (y + dy);

        float x3 = (float) (x + dx);
        float y3 = (float) (y - dy);


        float xx = w + (x - w) / 2;
        float yy = h + (y - h) / 2;

        path.moveTo(x1, y1);
        path.quadTo(xx, yy, x2, y2);

        path.lineTo(x3, y3);
        path.quadTo(xx, yy, x4, y4);
        path.close();

        canvas.drawPath(path, paint);

//        canvas.drawCircle(x1, y1, 15, paint1);
//        canvas.drawCircle(x2, y2, 15, paint1);
//        canvas.drawCircle(x3, y3, 15, paint2);
//        canvas.drawCircle(x4, y4, 15, paint2);
//        canvas.drawCircle(xx, yy, 15, paint2);
    }

    public interface OnRemoveListenter{
        void onRemove();
        void onRestore();
    }
    public void setOnRemoveListenter(OnRemoveListenter onRemoveListenter) {

        this.onRemoveListenter = onRemoveListenter;
    }
}
