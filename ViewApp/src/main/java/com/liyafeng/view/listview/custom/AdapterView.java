package com.liyafeng.view.listview.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;

/**
 * Created by liyafeng on 2018/3/28.
 * 这个类定义了 这是一个 持有 适配器的View
 */

public abstract class AdapterView<T extends com.liyafeng.view.listview.custom.adapter.Adapter> extends ViewGroup {


    private int mLayoutHeight;

    public AdapterView(Context context) {
        super(context);
    }

    public AdapterView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AdapterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public abstract void setAdapter(T adapter);

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        mLayoutHeight = getHeight();
    }
}
