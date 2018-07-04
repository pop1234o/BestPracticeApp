package com.liyafeng.view.viewpager;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import java.util.List;

public abstract class BasePagerAdapter<T> extends PagerAdapter {


    List<T> list;
    protected LayoutInflater inflater;


    public void setList(List<T> list) {
        this.list = list;
    }

    public BasePagerAdapter(Context context, List<T> list) {
        this(context);
        this.list = list;
    }


    public BasePagerAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        View view = getView(position);
        container.addView(view);
        return view;
    }

    public abstract View getView(int position);

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
