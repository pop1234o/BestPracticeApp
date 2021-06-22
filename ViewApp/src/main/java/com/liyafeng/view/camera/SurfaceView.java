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
     *
     * ===================SurfaceView 双缓冲机制===============
     * https://blog.csdn.net/rabbit_in_android/article/details/50807765
     *
     * 双缓冲技术是把要处理的图片在内存中处理好之后，再将其显示在屏幕上。双缓冲主要是为了解决 反复局部刷屏带来的闪烁。
     * 把要画的东西先画到一个内存区域里，然后整体的一次性画出来
     *
     * 这种双缓冲技术需要两个图形缓冲区，其中一个称为前端缓冲区，另外一个称为后端缓冲区。
     * 前端缓冲区是正在渲染的图形缓冲区，而后端缓冲区是接下来要渲染的图形缓冲区，
     * 它们分别通过Surface类的成员变量mPostedBuffer和mLockedBuffer所指向的两个GraphicBuffer对象来描述
     *
     *
     *
     * ==============SurfaceHolder========
     * https://www.cnblogs.com/plokmju/p/android_surfaceview.html Android--SurfaceView播放视频
     * SurfaceView 内部实现了双缓冲的机制，但是实现这个功能是非常消耗系统内存的
     * SurfaceHolder可以理解为SurfaceView装载需要显示的一帧帧图像的容器，它可以通过 SurfaceView.getHolder()方法获得。
     *
     * SurfaceView如果为用户可见的时候，创建SurfaceView的SurfaceHolder用于显示视频流解析的帧图片，
     * 如果发现SurfaceView变为用户不可见的时候，则立即销毁SurfaceView的SurfaceHolder，以达到节约系统资源的目的。
     *
     * 如果开发人员不对 SurfaceHolder 进行维护，会出现最小化程序后，再打开应用的时候，视频的声音在继续播放，但是不显示画面了的情况，
     * 这就是因为当SurfaceView不被用户可见的时候，之前的SurfaceHolder已经被销毁了，再次进入的时候，界面上的SurfaceHolder已经是新的SurfaceHolder了。
     * 所以SurfaceHolder需要我们开发人员去编码维护，维护SurfaceHolder需要用到它的一个回调，SurfaceHolder.Callback()，它需要实现三个如下三个方法
     *
     *
     * ==============SurfaceView 生命周期变化========
     * SurfaceView在布局中.
     *  打开页面生命周期
     *  onCraete
     *  onStart
     *  onResume
     *  surfaceCreated=======
     *  surfaceChanged=======
     *
     * 退到后台生命周期
     * onPause
     * surfaceDestroyed=======
     * onStop
     *
     * 返回前台生命周期
     *  onStart
     *  onResume
     *  surfaceCreated=======
     *  surfaceChanged=======
     *
     * 页面展示，Surface创建，不展示就销毁
     *
     *
     * ===============场景问题=============
     * https://blog.csdn.net/junzia/article/details/52704129  Android MediaPlayer+SurfaceView播放视频
     * 1. 有声音没有图像
     * 视频播放有声音没图像也许是在使用MediaPlayer最容易出现的问题，几乎所有使用MediaPlayer的新手都会遇到。视频播放的图像呈现需要一个载体，需要利用MediaPlayer.setDisplay设置一个展示视频画面的SurfaceHolder，最终视频的每一帧图像是要绘制在Surface上面的。通常，设置给MediaPlayer的SurfaceHolder未被创建，视频播放就注定没有图像。
     * * 比如你先调用了setDisplay，但是这个时候holder是没有被创建的。视频就没有图像了。
     * * 或者你在setDisplay的时候holder确保了holder是被创建了，但是当因为一些原因holder被销毁了，视频也就没有图像了。
     * * 内存占用过高，创建后没有及时展示在屏幕上（比如recycleview 不要在item中加入Surfaceview，否则会被回收？？），然后被回收了
     * * 再者，你没有给展示视频的view设置合适的大小，比如都设置wrap_content，或者都设置0，也会导致SurfaceHolder不能被创建，视频也就没有图像了。
     * * 还有滑动的时候播放错乱问题，可能是1视频surfaceCreate了，然后setVideoPath，prepareAsync了，然后没好的时候有切换到下一个
     * 然后用新的surface，这个时候pause,stop setVideoPath(新url)，prepareAsync无效加载的还是上个url数据，但是上个的surface已经销毁了
     * 所以写不了数据，黑屏。
     * 因为必须是先setdisplay，然后数据才能prepareAsync写入数据，后面你再setDisplay也无效了？？？
     *   globalPlayer.setDisplay(holder);
     *   globalPlayer.setDataSource(videoUrl);
     *   globalPlayer.prepareAsync();
     *
     *
     *
     *
     * 2. 视频图像变形
     * Surface展示视频图像的时候，是不会去主动保证和呈现出来的图像和原始图像的宽高比例是一致的，所以我们需要自己去设置展示视频的View的宽高，以保证视频图像展示出来的时候不会变形。我认为比较合适的做法就是利用FrameLayout嵌套一个SurfaceView或者其他拥有Surface的View来作为视频图像播放的载体View，然后再OnVideoSizeChangeListener的监听回调中，对载体View的大小做更改。
     * 3. 切入后台后声音还在继续播放
     * 这个问题只需要在onPause中暂停播放即可
     * 4. 切入后台再切回来，视频黑屏
     * 诸如此类的黑屏问题，多是因为surfaceholder被销毁了，再切回来时，需要重新给MediaPlayer设置holder。
     * 5. 播放时会有一小段时间的黑屏
     * 视频准备完成后，调用play进行播放视频，承载视频播放的View会是黑屏状态，我们只需要在播放前，给对应的Surface绘制一张图即可。
     * 6. 多个SurfaceView用来播放视频，滑动切换时会有上个视频的残影
     * 当视频切换出界面，设置surfaceView的visiable状态为Gone，界面切回来时再设置为visiable即可。
     * ————————————————
     * 版权声明：本文为CSDN博主「湖广午王」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
     * 原文链接：https://blog.csdn.net/junzia/article/details/52704129
     *
     *
     * ============== lockCanvas  unlockCanvasAndPost====
     * >SurfaceHolder.lockCanvas()获得Canvas对象并锁定画布----> Canvas绘画 ---->SurfaceHolder.unlockCanvasAndPost(Canvas canvas)结束锁定画图
     * 锁定back buffer 缓冲，让 front buffer 不要读取，然后画内容到back buffer中。unlockCanvasAndPost 解锁，然后，post到front buffer展示
     *
     * (2)、abstract Canvas lockCanvas();
     * // 锁定画布，一般在锁定后就可以通过其返回的画布对象Canvas，在其上面画图等操作了。
     * (3)、abstract Canvas lockCanvas(Rect dirty);
     * // 锁定画布的某个区域进行画图等..因为画完图后，会调用下面的unlockCanvasAndPost来改变显示内容。
     * // 相对部分内存要求比较高的游戏来说，可以不用重画dirty外的其它区域的像素，可以提高速度。
     * (4)、abstract void unlockCanvasAndPost(Canvas canvas);
     * // 结束锁定画图，并提交改变。
     *
     *
     * @param args
     */
    public static void main(String[] args) {

//         surfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {
//            @Override
//            public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
//                Log.i("test","surfaceCreated=======");
//            }
//
//            @Override
//            public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int format, int width, int height) {
//                Log.i("test","surfaceChanged=======");
//            }
//
//            @Override
//            public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {
//                Log.i("test","surfaceDestroyed=======");
//            }
//        });

        //
        //   OnVideoSizeChangedListener
        //  if (width != 0 && height != 0) {
        //                    float ratioW = (float) width / (float) mSurfaceWidth;//原本的宽高，一般是屏幕宽高
        //                    float ratioH = (float) height / (float) mSurfaceHeight;
        //                    float ratio = Math.max(ratioW, ratioH);
        //                    width = (int) Math.ceil((float) width / ratio);
        //                    height = (int) Math.ceil((float) height / ratio);
        //                    FrameLayout.LayoutParams layout = new FrameLayout.LayoutParams(width, height);
        //                    layout.gravity = Gravity.CENTER;
        //                    surfacePlayer.setLayoutParams(layout);
        //
        //                    if (surfacePlayer.isAttachedToWindow()) {
        //                        globalPlayer.setSurface(surfacePlayer.getHolder().getSurface());
        //                        Log.e(TAG, "isAttachedToWindow setSurface ===" + position);
        //                        imgThumb.animate().alpha(0).setDuration(100).start();
        //                    } else {
        //                        Log.e(TAG, "show ERROR!!! " + position);
        //                    }
        //                }
    }


    /**
     * ==========MediaPlayer============
     *
     *
     *
     *
     */
    void a1(){}
}
