package com.liyafeng.view.listview.custom.adapter;

import android.view.View;
import android.view.ViewGroup;

/**
 * Created by liyafeng on 2018/3/28.
 * 适配器，将 数据转换为View对象，具体如何转换由子类实现
 */

public interface Adapter {

    int getCount();//list元素的数量

    View getView(int position, View convertView, ViewGroup parent);

    /**
     * 指定位置上的view的type
     * @param position
     * @return 返回的必须是 0到getViewTypeCount()-1
     */
    int getItemViewType(int position);

    int getViewTypeCount();
}
