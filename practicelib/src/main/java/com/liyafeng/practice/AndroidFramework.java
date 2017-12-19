package com.liyafeng.practice;

public class AndroidFramework {

    /**
     * 简述事件分发流程
     * 事件分发机制
     */
    public void a1() {
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
     * View的渲染机制
     */
    public void a2() {
        /*
        *
        * Android的图形都是在canvas对象中绘制的，一个canvas持有一个bitmap对象
        * 然后用openGl es将多维图形通过gpu来渲染，进行光栅化，就是将矢量图转化为
        * 像素点，然后通过硬件时钟将像素点投影到屏幕上
        *
        * 其中16ms同步一次，帧率就是60fps ，所以我们完成一次绘制要在16ms内
        * 否则就会出现掉帧的情况，因为绘制时间超过16ms，就算绘制完成也不会同步，
        * 只能等待下一次同步，所以这一帧就没有被渲染，我们管他叫掉帧
        *
        * 我们滑动卡顿优化的原理也是根据这个来的
        *
        * */
    }


    /**
     * View的绘制流程
     * http://www.liyafeng.com/c/Android_APIsetContentView流程分析
     */
    public void a2_1() {
        /*
        * 首先会将xml解析成对象，addview添加到decorview中
        * 然后执行requestLayout()，最终在ViewRootImpl中执行doTraversals
        * 进行view树的遍历，最先执行performMeasure()初步确定view的宽高,
        * 然后是performLayout，确定子view在父布局中的位置，left top right bottom 四个参数
        * 最后执行performDraw ,将canvas对象传入，子view根据自己的ondraw方法进行绘制
        */
    }

    /**
     * Android 动画原理 、底层如何给上层信号？
     */
    public void a3() {
        /*
        * 分为 1.补间动画（tween 屯，两者之间）2.属性动画(attribute) 3.帧动画 frame
        *
        * 补间动画实际上操作的是canvas的matrix ，属性动画操作view的属性，有get set方法的属性
        * 帧动画就是一帧帧图片播放
        *
        * 他们都原理都是记录动画的执行时间，判断当前时间动画有没有结束，如果没有结束
        * 就调用invalidate方法进行重绘，一次次的重绘，改变位置，就会形成动画效果
        *
        * 给上层信号调用自身的的invalidate方法，里面调用父布局的invalidateChildInParent
        * 这里有一个while循环，会一直取父布局（的引用），直到调用viewrootimpl的invalidateChildInParent
        * 里面会调用scheduleTraversals()执行遍历，遍历调用view树的ondraw，这样就会刷新view的视图
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
