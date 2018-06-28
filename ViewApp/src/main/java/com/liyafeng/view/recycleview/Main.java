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
     * 有两个bug，一个是下拉中向上惯性滑动，这个时候head的height有时候不为0，是因为ontouch事件
     * 在head>0的时候不应该交给rv来处理，
     * 一个是在done的过程中，就是head高度变为0的过程中手指下滑（此时head还在显示）
     * 因为head此时接收了滑动事件，到时height变化，当done事件完成变为normal时，
     * 画面会向下跳动一大段距离。解决方法是在done的时候将标记还设置为刷新状态
     * 使得head不接收事件即可。
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
     * 加载动画过程中，手指滑动，此时加载完成（这个时候最顶层是head变为0的动画）
     *
     * 看了其他app，在 下拉阶段和松开加载阶段，上下方向都不应该有惯性滑动，都视为松开手，忽略惯性
     *
     * 上滑惯性阶段，有的app都提供一个惯性回弹的效果，但有的也没有，
     *
     * ================下拉刷新原理==============
     * 头部添加一个item，然后重写ontouchevent
     * 如果顶部item的parent不为null，说明在顶部，而且没有在刷新状态
     * 这个时候将移动的垂直距离传给head，然后他来变化高度，然后再根据高度的阈值来确定是
     * 上拉刷新的状态还是释放刷新的状态
     * 然后判断如果head的height>0，那么处于下拉阶段，然后就直接返回true，不传给recyclerview来处理
     * 这样就是禁止了，在上拉阶段的惯性滑动，手指松开就是松开了，不用考虑惯性滑动的问题
     * 然后是松开阶段，如果高度大于阈值，那么用ValueAnimator将高度设置为阈值的height，有一个动画效果
     * 显示加载动画，调用刷新回调
     * 如果head高度小于阈值大于等于0，那么就用ValueAnimator设置为0，就结束了
     *
     * 加载数据完成，通知head显示刷新完成，然后做一个head高度为0的动画，动画完成后，
     * 将head状态设置为"空闲"，
     * 先是文字变化（状态设置为done状态），然后等待200毫秒进行动画，
     * 动画时间是300ms，然后500毫秒后将状态设置为"空闲"
     *
     * 所以在done状态中head不能接受滑动事件，只能交给rv来处理，如果滑动的时候head显示
     * 那么在head变为0的过程中，页面向下展示滑动（手指向上），这个时候就会出现head移出rv
     * 的效果，但是这可以接受，不会出现较大的跳动
     *
     *
     *
     *
     */
    public void a1(){}
}
