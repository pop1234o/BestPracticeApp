package com.liyafeng.view.recycleview;

/**
 * Created by liyafeng on 2018/1/8.
 */

public class Main {

    /**
     * 填充的item如果要用xml中的参数，就指定parent，但是不attachToRoot
     * inflater.inflate(R.layout.xxx, parent, false)
     *
     * ==============下拉刷新的listview===========
     * LRecyclerView
     *
     * <p>
     * ============Payload=========================
     * 使用 Payload 提高 RecyclerView 渲染效率
     * <p>
     * Read more: http://blog.chengyunfeng.com/?p=1007#ixzz5HABlg6BO
     * public void onBindViewHolder(VH holder, int position, List<Object> payloads)
     * public final void notifyItemChanged(int position, Object payload) {
     *
     * 这样我们就可以通过payload来知道哪里刷新了，我们不需要刷新整个item了，只需要刷新item的指定view就行
     *
     *
     *
     * =============setHasFixedSize=================
     *
     *
     * 当我们确定Item的改变不会影响RecyclerView的宽高的时候可以设置setHasFixedSize(true)，
     * 并通过Adapter的增删改插方法去刷新RecyclerView，而不是通过notifyDataSetChanged()。
     * （其实可以直接设置为true，当需要改变宽高的时候就用notifyDataSetChanged()去整体刷新一下）
     *
     *
     *
     * @param args
     */
    public static void main(String[] args) {

    }


    /**
     * 下拉阶段，松开加载阶段，惯性下滑阶段，手势下滑阶段，下拉阶段手势下滑惯性，下拉阶段手势手势上滑惯性
     * 上滑惯性阶段
     *
     * 看了其他app，在 下拉阶段和松开加载阶段，上下方向都不应该有惯性滑动，都视为松开手，忽略惯性
     *
     * 上滑惯性阶段，有的app都提供一个惯性回弹的效果，但有的也没有，
     *
     *
     */
    public void a1(){}
}
