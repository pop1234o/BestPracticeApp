package com.liyafeng.practice;

public class MyClass {

    /**
     * 1.简述事件分发流程
     */
    public void a1(){
        /*
        * 最一开始ViewRootImpl 接收到触摸事件，然后会传递给DecorView
        * 的dispatchTouchEvent()，然后Decorview会将事件分发给子控件
        * 先判断当前View是否拦截事件，如果拦截就直接调用自己的ontouchevent
        * 如果没有拦截就依次分发给子view，直到最底层的view，在ontouchevent
        * 中返回是否消费，如果有消费下次就直接将事件传递给它，如果没有消费就依次
        * 调用父控件的ontouchevent,直到事件被消费。
        *
        * 整个过程是一个递归调用，是类似于是反向的树的前序遍历
        *
        * */

    }
    /**
     * 2.View的渲染机制
     */
    public void a2(){
        /*
        *
        * Android的图形都是在canvas对象中绘制的，
        *
        * */
    }


}
/**
 *
 *
 *
 *
 * */
