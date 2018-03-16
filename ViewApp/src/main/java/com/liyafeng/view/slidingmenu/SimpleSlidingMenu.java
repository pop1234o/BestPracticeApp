package com.liyafeng.view.slidingmenu;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

/**
 * Created by liyafeng on 2017/9/11.
 */

public class SimpleSlidingMenu extends RelativeLayout {
    String TAG = getClass().getSimpleName();
    private FrameLayout content;
    private FrameLayout menu;
    private float lastX;
    private int menuWidth;

    public static final int TYPE_ALL = 0;
    public static final int TYPE_CONTENT = 1;
    public static final int TYPE_MENU_SCALE = 2;
    private ScrollHelper scrollHelper;

    public SimpleSlidingMenu(Context context) {
        this(context, null);
    }

    public SimpleSlidingMenu(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SimpleSlidingMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);


    }

    private void init(Context context) {
        content = new FrameLayout(context);
        menu = new FrameLayout(context);
        addView(menu, new LayoutParams(MATCH_PARENT, MATCH_PARENT));
        addView(content, new ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT));
    }

    public void setContent(View content) {
        this.content.addView(content);
    }

    public void setMenu(View menu, int type, int width) {
        this.menu.addView(menu);
        menuWidth = menu.getLayoutParams().width = width;
        switch (type) {
            case TYPE_ALL:
                this.menu.setTranslationX(-width);
                scrollHelper = new AllScrollHelper();
                break;
            case TYPE_CONTENT:
                scrollHelper = new ContentScrollHelper();
                break;
            case TYPE_MENU_SCALE:
                scrollHelper = new MenuScaleScrollHelper();
                break;
            default:
                scrollHelper = new ContentScrollHelper();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastX = event.getRawX();
                break;
            case MotionEvent.ACTION_UP:
                scrollHelper.reset();
                break;
            case MotionEvent.ACTION_MOVE:
                float dx = event.getRawX() - lastX;
                scrollHelper.scroll((int) dx);
                lastX = event.getRawX();
                break;
        }
        return true;
    }


    /**
     * 辅助滑动的类，可以继承实现来自己的滑动效果
     */
    abstract class ScrollHelper {
        abstract void scroll(float dx);

        abstract  void reset();
    }

    class AllScrollHelper extends ScrollHelper {


        @Override
        void scroll(float dx) {
            float x = content.getTranslationX() + dx;//
            if (x < 0) {
                dx = -content.getTranslationX();
            } else if (x > menuWidth) {
                dx = menuWidth - content.getTranslationX();
            }
            menu.setTranslationX(menu.getTranslationX() + dx);

            content.setTranslationX(content.getTranslationX() + dx);
        }
        void reset() {
            float translationX = content.getTranslationX();
            float translationX1 = menu.getTranslationX();
            if (translationX > menuWidth / 2) {
                ObjectAnimator animator = ObjectAnimator.ofFloat(content, "translationX", translationX, menuWidth);
                animator.setDuration(100);
                animator.start();
                ObjectAnimator animator1 = ObjectAnimator.ofFloat(menu, "translationX", translationX1, 0);
                animator1.setDuration(100);
                animator1.start();
            } else {
                ObjectAnimator animator = ObjectAnimator.ofFloat(content, "translationX", translationX, 0);
                animator.setDuration(100);
                animator.start();
                ObjectAnimator animator1 = ObjectAnimator.ofFloat(menu, "translationX", translationX1, -menuWidth);
                animator1.setDuration(100);
                animator1.start();
            }
        }


    }

    class ContentScrollHelper extends ScrollHelper {


        @Override
        void scroll(float dx) {
            float x = content.getTranslationX() + dx;
            if (x < 0) {
                dx = -content.getTranslationX();
            } else if (x > menuWidth) {
                dx = menuWidth - content.getTranslationX();
            }
            content.setTranslationX(content.getTranslationX() + dx);
        }

        void reset() {
            float translationX = content.getTranslationX();
            if (translationX > menuWidth / 2) {
                ObjectAnimator animator = ObjectAnimator.ofFloat(content, "translationX", translationX, menuWidth);
                animator.setDuration(100);
                animator.start();
            } else {
                ObjectAnimator animator = ObjectAnimator.ofFloat(content, "translationX", translationX, 0);
                animator.setDuration(100);
                animator.start();
            }
        }

    }

    class MenuScaleScrollHelper extends ScrollHelper {

        private float t;
        private float mScale;

        public MenuScaleScrollHelper() {
            this(0.7f);
        }

        public MenuScaleScrollHelper(float scale) {
            this.mScale = scale;
        }

        @Override
        void scroll(float dx) {
            float translationX = content.getTranslationX();
            int width = content.getWidth();
            t = (width - width * mScale) / 2;
            float x = translationX + dx;
            if (x < 0) {
                dx = -translationX;
            } else if (x > menuWidth - t) {
                dx = menuWidth - t - translationX;
            }

            float v = translationX + dx;

            content.setTranslationX(v);

            float p = (menuWidth - t - v) / (menuWidth - t);//1-0
            float scale = mScale + (1 - mScale) * p;
            content.setScaleY(scale);//1-0.7
            content.setScaleX(scale);

            float scaleX = mScale + (1 - mScale) * (1 - p);
            menu.setScaleX(scaleX);//0.7-1
            menu.setScaleY(scaleX);
        }

        @Override
        void reset() {
            float translationX = content.getTranslationX();
            float scaleX = content.getScaleX();
            float scaleY = content.getScaleY();
            float scaleX1 = menu.getScaleX();
            float scaleY1 = menu.getScaleY();
            if (translationX > (menuWidth - t) / 2) {
                PropertyValuesHolder x = PropertyValuesHolder.ofFloat("translationX", translationX, menuWidth - t);
                PropertyValuesHolder y = PropertyValuesHolder.ofFloat("scaleX", scaleX, mScale);
                PropertyValuesHolder z = PropertyValuesHolder.ofFloat("scaleY", scaleY, mScale);

                PropertyValuesHolder y1 = PropertyValuesHolder.ofFloat("scaleX", scaleX1, 1);
                PropertyValuesHolder z1 = PropertyValuesHolder.ofFloat("scaleY", scaleY1, 1);


                ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(content, x, y, z);
                ObjectAnimator animator_menu = ObjectAnimator.ofPropertyValuesHolder(menu, y1, z1);

                animator.setDuration(100);
                animator.start();
                animator_menu.setDuration(100);
                animator_menu.start();
            } else {
                PropertyValuesHolder x = PropertyValuesHolder.ofFloat("translationX", translationX, 0);
                PropertyValuesHolder y = PropertyValuesHolder.ofFloat("scaleX", scaleX, 1);
                PropertyValuesHolder z = PropertyValuesHolder.ofFloat("scaleY", scaleY, 1);

                PropertyValuesHolder y1 = PropertyValuesHolder.ofFloat("scaleX", scaleX1, mScale);
                PropertyValuesHolder z1 = PropertyValuesHolder.ofFloat("scaleY", scaleY1, mScale);


                ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(content, x, y, z);
                ObjectAnimator animator_menu = ObjectAnimator.ofPropertyValuesHolder(menu, y1, z1);

                animator.setDuration(100);
                animator.start();
                animator_menu.setDuration(100);
                animator_menu.start();

            }
        }

    }
}
