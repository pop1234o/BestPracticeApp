package com.liyafeng.view.listview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import static com.liyafeng.view.MainActivity.TAG;

/**
 * Created by liyafeng on 07/10/2017.
 */

public class CustomListView extends ViewGroup {

    private float rawX;
    private float rawY;
    private int t;
    private ListAdapter adapter;
    private RecycleBin recycleBin;
    private int mfirstPosition;

    public CustomListView(Context context) {
        super(context);
        init();
    }

    public CustomListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        recycleBin = new RecycleBin();
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {


    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        layoutChildren();
    }

    private void layoutChildren() {


        fillFromTop();
    }

    private void fillFromTop() {
        fillDown(0, 0);
    }

    private void fillDown(int firstpos, int nextTop) {
        int count = adapter.getCount();

        int bottom = getBottom();

        while (nextTop < bottom && firstpos < count) {

            View child = makeAndAddView(firstpos, nextTop);

            Log.i(TAG, "fillDown: add" + firstpos);

            nextTop += child.getMeasuredHeight();
            firstpos++;

        }
    }

    private View makeAndAddView(int firstpos, int nextTop) {
        View scrapView = recycleBin.getScrapView();
        View view = adapter.getView(firstpos, scrapView, this);
        LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams == null) {

            layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
            view.setLayoutParams(layoutParams);
        }

        addViewInLayout(view, -1, layoutParams);

        int w = MeasureSpec.makeMeasureSpec(getMeasuredWidth(), MeasureSpec.AT_MOST);
        int h = MeasureSpec.makeMeasureSpec(getMeasuredHeight(), MeasureSpec.UNSPECIFIED);
        view.measure(w, h);

        view.layout(0, nextTop, getMeasuredWidth(), nextTop + view.getMeasuredHeight());

        return view;
    }


    public void setAdapter(ListAdapter adapter) {

        this.adapter = adapter;
    }

    public interface ListAdapter {
        int getCount();

        View getView(int position, View convertView, ViewGroup parent);
    }

    public class RecycleBin {
        //        public ArrayList<View> activeView;
        public ArrayList<View> scrapView;

        public RecycleBin() {
//            activeView=  new ArrayList<>();
            scrapView = new ArrayList<>();
        }

        public View getScrapView() {
            if (scrapView.size() > 0) {
                return scrapView.remove(0);
            }
            return null;
        }

        public void putScrapView(View view) {
            scrapView.add(view);
        }
    }


    /**
     * 触摸
     */

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                rawY = event.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                float crawY = event.getRawY();

                float dy = crawY - rawY;

                scrollBy(0, (int) -dy);
                rawX = event.getRawX();
                rawY = event.getRawY();


                trackMotionScroll();

                break;
            case MotionEvent.ACTION_UP:
                break;
        }

        return true;
    }

 private void trackMotionScroll() {
        //回收看不到的view
        int scrollY = getScrollY();

        View childAt = getChildAt(0);
        int bottom = childAt.getBottom();
        if (bottom < scrollY) {//回收
            recycleBin.putScrapView(childAt);
            mfirstPosition++;
            detachViewFromParent(childAt);
        }


        //填补空隙
        fillGap();
    }

    private void fillGap() {

        int bottom = getChildAt(getChildCount() - 1).getBottom();

        int lastIndex = mfirstPosition+getChildCount() - 1;

        int parentBottom = getScrollY() + getMeasuredHeight();

        int count = adapter.getCount();
        //最后一个到屏幕底部的空隙填满
        Log.i(TAG, "fillGap: " + bottom + "  " + parentBottom + "  " + lastIndex);
        while (bottom < parentBottom && lastIndex < count) {
            View scrapView = recycleBin.getScrapView();

            View view = adapter.getView(lastIndex + 1, scrapView, this);
            LayoutParams layoutParams = view.getLayoutParams();
            if (layoutParams == null) {

                layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
                view.setLayoutParams(layoutParams);
            }

            if (scrapView == null) {//没有缓存，添加

                addViewInLayout(view, -1, layoutParams);

            } else {
                Log.i(TAG, "fillGap: 复用");
                attachViewToParent(scrapView, -1 , layoutParams);
            }

            int w = MeasureSpec.makeMeasureSpec(getMeasuredWidth(), MeasureSpec.AT_MOST);
            int h = MeasureSpec.makeMeasureSpec(getMeasuredHeight(), MeasureSpec.UNSPECIFIED);
            view.measure(w, h);

            view.layout(0, parentBottom, getMeasuredWidth(), parentBottom + view.getMeasuredHeight());

            lastIndex++;

            bottom += view.getMeasuredHeight();

        }

    }
}
