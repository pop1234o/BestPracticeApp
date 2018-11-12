package com.liyafeng.view.camera;

public class SurfaceView {

    /**
     * https://developer.android.google.cn/reference/android/view/SurfaceView
     * 16ms发送vsync信号从显卡同步数据到显示器
     * 如果我们不能及时将新的ui数据发送的显卡，那么久会产生卡顿
     * 如果停止canvas绘制屏幕也不会没有内容，因为他只是没有刷新
     * 没有写入新的数据，所以还是显示之前的内容
     *
     * ===============View和SurfaceView的区别:============
     * 1 . View适用于主动更新的情况，而SurfaceView则适用于被动更新的情况，比如频繁刷新界面。
     * 2 . View在主线程中对页面进行刷新，而SurfaceView则开启一个子线程来对页面进行刷新。
     * 3 . View在绘图时没有实现双缓冲机制，SurfaceView在底层机制中就实现了双缓冲机制。
     * 双缓冲技术是把要处理的图片在内存中处理好之后，再将其显示在屏幕上。
     * 是为了解决 反复局部刷屏带来的闪烁
     *
     * ======================为什么使用surfaceview================
     * 当需要视图频繁刷新，而且需要做耗时操作的时候
     * surfaceview的刷新在子线程，所以不会阻塞主线程
     *
     * ===================TextureView===============
     * TextureView可用于显示内容流。内容流可以是视频或者OpenGL的场景。内容流可来自应用进程或是远程其它进程。
     * Textureview必须在硬件加速开启的窗口中使用。若是软解，TextureView不会显示东西。
     * 不同于SurfaceView，TextureView不会建立一个单独的窗口(单独的surface)，而是像一个常规的View一样
     *
     * @param args
     */
    public static void main(String[] args) {

    }
}
