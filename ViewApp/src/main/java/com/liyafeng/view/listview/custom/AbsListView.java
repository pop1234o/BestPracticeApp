package com.liyafeng.view.listview.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.liyafeng.view.listview.custom.adapter.ListAdapter;

import java.util.ArrayList;

/**
 * Created by liyafeng on 2018/3/28.
 */

public abstract class AbsListView extends AdapterView<ListAdapter> {

    ListAdapter mAdapter;

    /**
     * 很明显，这种一对一的对象可以在类加载的时候就创建了
     */
    RecycleBin mRecycleBin = new RecycleBin();


    public AbsListView(Context context) {
        super(context);
    }

    public AbsListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AbsListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        layoutChildren();
    }


    protected abstract void layoutChildren();


    /**
     * 只负责返回指定位置的View
     *
     * @param position
     * @return
     */
    View obtainView(int position) {
        //这里想获取缓存
        View scrapView = mRecycleBin.getScrapView(position);
        //然后真正的去发挥适配器的作用
        View view = mAdapter.getView(position, scrapView, this);
        //这里说明没有使用缓存的view，那么要将他回收
        if (view != scrapView) {
            mRecycleBin.addScrapView(scrapView, position);
        }

        //如果没有布局,就给他一个默认的（所以我们在inflate的时候要指定parent，但是attach是false，这样
        // inflate出来的view会有layoutParams
        // ）
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams == null) {
            view.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
        }
        return view;
    }


    /**
     * 每个ListView都得有个回收对象，管理回收
     */
    class RecycleBin {
        private ArrayList<View>[] mScrapViews;//管理不同type的view(废弃view的缓存)


        /**
         * 这个是初始化 废弃View缓存的地方，这个是setAdapter后调用的
         *
         * @param viewTypeCount
         */
        public void setViewTypeCount(int viewTypeCount) {
            if (viewTypeCount < 1) {
                throw new IllegalArgumentException("Can't have a viewTypeCount < 1");
            }
            ArrayList<View>[] scrapViews = new ArrayList[viewTypeCount];
            for (int i = 0; i < viewTypeCount; i++) {
                scrapViews[i] = new ArrayList<View>();
            }
            mScrapViews = scrapViews;
        }


        /**
         * 获取
         *
         * @param position
         * @return
         */
        View getScrapView(int position) {
            int viewType = mAdapter.getItemViewType(position);
            ArrayList<View> mScrapView = mScrapViews[viewType];

            View view = mScrapView.remove(mScrapView.size() - 1);
            return view;

        }

        void addScrapView(View view, int position) {
            int viewType = mAdapter.getItemViewType(position);
            ArrayList<View> mScrapView = mScrapViews[viewType];
            mScrapView.add(view);
        }


    }


    /**
     * 这里要继承的作用是可以在里面加一些附加的信息，比如position
     */
    public static class LayoutParams extends ViewGroup.LayoutParams {

        int postion;

        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
        }

        public LayoutParams(int width, int height) {
            super(width, height);
        }

        public LayoutParams(ViewGroup.LayoutParams source) {
            super(source);
        }
    }
}
