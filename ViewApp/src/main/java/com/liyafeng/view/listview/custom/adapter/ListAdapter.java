package com.liyafeng.view.listview.custom.adapter;

/**
 * Created by liyafeng on 2018/3/28.
 */

public interface ListAdapter extends Adapter {

    /**
     * 判断是否是分割线
     * @param position
     * @return
     */
    boolean isEnabled(int position);


}
