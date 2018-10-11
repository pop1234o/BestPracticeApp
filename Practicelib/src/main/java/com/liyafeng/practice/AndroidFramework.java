package com.liyafeng.practice;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.util.Log;
import android.util.LruCache;

import com.liyafeng.practice.basic.Animal;

import java.io.File;

public class AndroidFramework {


    //region Android UI绘制

    /**
     * =====================
     * ### Android UI
     * =====================
     * */

    /**
     * 简述事件分发流程\事件分发机制？
     * ----------------------------------
     * 说说onTouch和onTouchEvent的区别？
     * ----------------------------------
     * View和ViewGroup分别有哪些事件分发相关的回调方法?
     * <p>
     * https://blog.csdn.net/u012203641/article/details/77624017
     */
    public void a1() {
        /*
         * 1.首先说明视图层级，Activity持有PhoneWindow（startActivity时attach()中new出来的）
         * 然后PhoneWindow持有DecorView（setContentView的时候new出来的，是FrameLayout的子类
         * 里面自带layout.xml，是一个LinearLayout,里面有id是content的一个FrameLayout,这个会在
         * PhoneWindow中持有，叫mContentParent)
         *
         * 在onResume后DecorView 被WindowManagerImpl.addView ,这时创建了ViewRootImpl
         * 然后ViewRoot就持有了DecorView，WindowManagerGlobal这个单例持有APP所有的ViewRoot对象（是个List）
         *
         * 最一开始ViewRootImpl 接收到触摸事件，然后会传递给DecorView
         * 的dispatchTouchEvent()，然后Decorview会将事件分发给子控件
         * 也就是ViewGroup的dispatchTouchEvent
         * 先是down事件传入
         * 判断当前ViewGroup是否拦截事件，调用onInterceptTouchEvent（）
         * 如果拦截就直接调用自己的ontouchevent（这个是从View中继承的，调用的是View的onTouchEvent）
         * super.dispatchTouchEvent-》mOnTouchListener.onTouch-》onTouchEvent（）
         * 如果没有拦截就依次分发给子控件（后添加的最先调用），直到最底层的view，
         * 在ontouchevent 中返回是否消费，如果有消费下次就直接将事件传递给它，如果没有消费就依次
         * 调用父控件的ontouchevent,直到事件被消费。
         *
         * 整个过程是一个递归调用，是类似于是反向的树的前序遍历
         * ----------------------------------
         * 关键点在于，ViewGroup中的dispatchTouchEvent()，最开始肯定是Down事件传递进去
         * 然后执行拦截，调用onInterceptTouchEvent ,判断是否拦截，如果拦截，那么直接调用dispatchTransformedTouchEvent方法
         * 然后调用super.dispatchTouchEvent(),就是View中的方法，这个方法中调用了onTouchEvent
         * 如果没有拦截，那么会逆序遍历子View，调用他们的dispatchTouchEvent()（遇到子控件是View就是调用onTouchEvent，遇到
         * ViewGroup就会遍历他的子View,直到视图树的子节点为止），如果遇到一个View消费了时间的，那么循环直接break，停止遍历
         * 然后标记已经遍历了alreadyDispatchedToNewTouchTarget = true，
         *
         * 接下来模块判断 mFirstTouchTarget 是否为null，如果为null，那么说明子视图没有消费事件，交给自己的onTouchEvent处理
         * 如果有消费，那么判断是否已经Dispatched过了，如果已经循环过了，那么直接标记handle为true，如果没有（则遍历调用
         * dispatchTransformedTouchEvent 调用他们的dispatch方法）
         *
         * ------------------------
         * 第二次，直接判断有没有target，如果没有，直接拦截事件（说明它肯定在down传入的时候消费了事件，否则第二次肯定不会调用了）
         *
         * ===========================说说onTouch和onTouchEvent的区别？=======================================
         * onTouch是OnTouchListener调用的，是先于onTouchEvent,只有onTouch没有消费事件，才会传入到onTouchEvent
         * 他们是在View中的dispatchTouchEvent方法中调用的
         *============================View和ViewGroup分别有哪些事件分发相关的回调方法?===========================
         * onTouch ,onClick onLongClick
         *
         *
         * */

    }

    /**
     * View的渲染机制
     * View/Activity是 如何显示在屏幕上的？
     */
    public void a1_1() {
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
         * ======View/Activity是 如何显示在屏幕上的？===========
         * 当Activity onResume后，DecorView被WindowMangerImpl.addView()
         * 里面new 了ViewRootImpl，setView（DecorView），
         * 然后里面调用了Session(IWindowSession).addToDisplay()
         * 然后WindowManagerService.addWindow ，从而分配surface显示？？？
         *
         * https://wizardforcel.gitbooks.io/deepin-android-vol3/content/4.html
         *
         * */
    }


    /**
     * View的绘制流程
     * http://www.liyafeng.com/c/Android_APIsetContentView流程分析
     */
    public void a1_2() {
        /*
         * 首先会将xml解析成对象，addview添加到decorview中
         * 然后执行requestLayout()，最终在ViewRootImpl中执行doTraversals
         * 进行view树的遍历，最先执行performMeasure()初步确定view的宽高,
         *
         * 然后是performLayout，确定子view在父布局中的位置，left top right bottom 四个参数
         *
         * 最后执行performDraw ,将canvas对象传入，子view根据自己的ondraw方法进行绘制
         */
    }

    /**
     * 说说onMeasure,onLayout，onDraw都发生了什么？
     * MeasureSpec三种模式的理解？UNSPECIFIED、EXACTLY、AT_MOST
     */
    public void a1_3() {
        /*
         * 最外层ViewRoot调用根布局的，measureChildren方法（ViewGroup的），
         * 然后遍历子View，调用他们的measure()方法，这个方法中调用onMeasure方法
         * 如果子View是ViewGroup的话，那么所有的ViewGroup都重写了onMeasure方法来定义自己如何测量自己的
         * 子View。。如果是View，那么直接调用指定View的onMeasure.一般是父布局，减去自己的padding，然后
         * 获取子View的layoutParam，然后计算出View应该有的大小，传到measure中，然后再调用onMeasure（如此循环）
         * ----------------------
         * onMeasure的目的就是计算出measureHeight和measureWidth
         *
         * MeasureSpec.EXACTLY：使用measureSpec中size的值作为宽高的精确值
         *  当我们将控件的layout_width或layout_height指定为具体数值时如andorid:layout_width="50dip"，
         *  或者为FILL_PARENT是，都是控件大小已经确定的情况，都是精确尺寸。
         * Match_parent也是这种模式，因为他直接将父布局的宽高传入，直接指定了宽高就是父布局的宽高
         *
         *
         *  MeasureSpec.AT_MOST：使用measureSpec中size的值作为最大值，采用不超过这个值的最大允许值
         *  当控件的layout_width或layout_height指定为WRAP_CONTENT时，控件大小一般随着控件的子空间或内容进行变化，此时控件尺寸只要不超过父控件允许的最大尺寸即可。因此，此时的mode是AT_MOST，size给出了父控件允许的最大尺寸。
         *
         *
         *
         *  MeasureSpec.UNSPECIFIED是未指定尺寸，这种情况不多 比如scrollView，中的LinearLayout
         *
         *
         * ===========================================
         * layout的目的就是计算出view相对于父布局左上角的left top right bottom
         *
         * ====================================================
         * 父布局根据子View的 位置，将画布剪裁，调用child.draw方法
         */
    }


    /**
     * invalidate和postInvalidate的区别及使用?
     * -------------------------
     * 如何刷新layout?
     * ------------------------
     * View刷新机制?
     * -------------------------
     * 一个Activity的ViewRootImpl是何时创建的？
     * ---------------------------
     * ViewRootImpl有什么作用？
     */
    public void a1_5() {
        /*
         * 他们都是用来发出信号来刷新UI的,只重写调用onDraw方法
         * 区别是后者可以在字线程中调用
         * 原理是调用了ViewRootImpl中的ViewRootHandler.post方法
         * =================如何刷新layout?========================
         * requestLayout()
         *====================View刷新机制================
         * 调用invalidate，最终调用ViewParent的invalidateChild方法，这是个接口，实现类是ViewRootImpl
         * 里面调用scheduleTraversals()-》performTraversals  来发送遍历视图树，从新调用他们的onDraw方法重新绘制
         *
         *=======================一个Activity的ViewRootImpl是何时创建的？=================================
         *  是调用了 onResume后，WindowManager.addView，将视图加入到界面上的时候，在WindowManagerGlobal中new出来的
         *  而且WindowManagerGlobal这个单例持有他
         *
         *===========================ViewRootImpl有什么作用？==========================================
         *  ViewRootImpl实现了ViewParent接口，他是DecorView和WindowManager之间的桥梁，比如我们有触摸事件
         *  ViewRootImpl是接收触摸事件，然后分发给真正的视图树。
         */
    }

    /**
     * Activity-Window-View三者的差别?
     */
    public void a1_6() {
        /*
         * Activity中持有Window对象，他的实现类是PhoneWindow
         * PhoneWindow中持有DecorView,DecorView是FrameLayout的子类
         * 是真正显示视图的
         */
    }

    /**
     * Bitmap对象的理解?
     * 如何计算一张图片的大小？
     * 如何高效加载一张大图？
     * 如何高效加载多张张图片，比如在ListView或ViewPager中？
     * 图片放在不同dpi路径下的区别？
     * https://developer.android.google.cn/topic/performance/graphics/index.html
     */
    public void a1_7() {
        /*
         * =========Bitmap对象的理解?==============================
         * Bitmap，位图，存储图像像素点的信息（比如Argb的值，各个通道的值，组成像素点的颜色，像素点组成图片）
         * https://developer.android.google.cn/topic/performance/graphics/manage-memory.html
         * 每个api版本不同，bitmap存放的位置也不同
         * 在3.0以前，bitmap的像素信息存储在native memory中，而bitmap对象存储在 Dalvik heap中
         * 所以我们必须要调用recycle（）方法来释放bitmap的像素数据（因为GC只会回收Heap）
         *
         * 3.0以后，pixel data 和 bitmap对象都存储在Dalvik heap中，这个时候我们就不必调用recycle
         * Gc会自动回收了。
         *
         * 8.0开始，pixel data存到了 native heap中
         *
         * 而fresco的存储位置（https://www.fresco-cn.org/docs/caching.html）
         * 在5.0以下系统，Bitmap缓存位于ashmem，这样Bitmap对象的创建和释放将不会引发GC，更少的GC会使你的APP运行得更加流畅。
         *
         * 5.0及其以上系统，相比之下，内存管理有了很大改进，所以Bitmap缓存直接位于Java的heap上。
         * 当应用在后台运行时，该内存会被清空。
         * ------------重用bitmap-----------------
         * https://developer.android.google.cn/reference/android/graphics/BitmapFactory.Options.html#inBitmap
         * 每次都要为像素数据 申请内存空间，而被回收的bitmap的内存空间又要被回收，
         * 那么我们可以通过 BitmapFactory.Options.inBitmap 属性来设置重用的bitmap
         * 1.我们将LruCache中废弃的Bitamp对象存入一个Set<SoftReference<Bitmap>>，而且要是
         * 软引用的（以便于系统回收），然后我们下次加载图片的时候（用decodeXX方法），
         * 我们将要加载图片的大小获取到，然后从Set中找到合适的Bitmap
         * （在4.4以下，他们的宽高必须相同，4.4以上，要加载图片的bitmap大小要小于缓存的Bitmap的）
         *   options.inMutable = true;
         *   if (cache != null) {
         *     Bitmap inBitmap = cache.getBitmapFromReusableSet(options);
         *     if (inBitmap != null) {
         *         options.inBitmap = inBitmap;
         *     }
         *    }
         * 我们用这个options去decode Bitmap，那么系统就不会新申请内存了，就用以前的了。
         *
         * =============如何计算一张图片的大小？==================
         * 512x384分辨率的图片，如果按ARGB_8888 来加载，那么每个像素需要4个字节，
         * 因为每8位表示透明、red green blue ，所以一个像素需要32位表示，就是4个字节
         * 那么图片加入到内存总大小就是，512x384*4 byte = 0.75Mb
         *
         * ===========如何高效加载一张大图？=======================
         * https://developer.android.google.cn/topic/performance/graphics/load-bitmap.html
         * 做法就是设置只加载图片的大小
         * BitmapFactory.Options options = new BitmapFactory.Options();
         *   options.inJustDecodeBounds = true;
         *   BitmapFactory.decodeResource(getResources(), R.id.myimage, options);
         *   int imageHeight = options.outHeight;
         *   int imageWidth = options.outWidth;
         *   String imageType = options.outMimeType;
         *
         * 比如我们要加载到200*200像素的imageview中，那么图片像素是200*200就正好
         * 获取到原始宽高后，我们需要根据显示的分辨率来设置图片的加载大小
         * 设置正确的inSampleSize，就会加载指定大小的图片
         * 这样我们就能控制一张图片加载到内存中的大小了，就避免了OOM
         *
         * ==========================如何高效加载多张张图片，比如在ListView或ViewPager中？=====================================
         * 答：
         * 1.技术上优化，使用二级缓存（内存缓存和磁盘缓存），按照控件大小加载图片（缩放加载）主流的图片加载框架
         * Fresco，Gilded，Picasso都是这么做
         * 2.从业务上优化，在滑动停止的时候才进行加载，防止连续滑动带来大内存的开销
         * 缩小内存缓存大小，从而防止OOM，或者释放其他地方的缓存来腾出更多控件
         *
         * 在listview中使用LruCache（内存缓存），我们设置lruCache的大小原则，一般
         * 是jvm为我们可分配最大内存的1/8
         *
         *   final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
         *   final int cacheSize = maxMemory / 8;
         *   mMemoryCache = new LruCache<String, Bitmap>(cacheSize) {
         *       @Override
         *       protected int sizeOf(String key, Bitmap bitmap) {
         *           return bitmap.getByteCount() / 1024;//这里要返回kb
         *       }
         *   };
         * 使用LruCache可以使得ListView滑动加载图片更流畅
         * 但是只有内存缓存是不建议的，一个是如果我们又很多图片要缓存，那么可能会引起OOM
         * 而且此时来了一个电话，然后此时我们的app被kill掉了，那么内存缓存就没有了，
         * 我们又要从新decode图片了，所以，这个时候我们应该用DiskLruCache
         * 他比正常加载更快（但是比内存加载慢），
         *
         * 所以，多图加载的优化，对于大小我们还是用上面的inSampleSize来解决，
         * 加载完毕后，我们分别加入LruCache和DiskLruCache，然后取得时候我们
         * 依次从里面取，这种比从新将图片转化为bitmap要快。（这种算是显示速度
         * 的优化）
         *
         *
         * =======================图片放在不同dpi路径下的区别？=====================
         * 系统会比较 设备屏幕的dpi ，和图片所在的dpi，将图片进行缩放（加载到内存的
         * 大小会不同），尽量放在高dpi下，如果放在低dpi下，那么在高dpi屏幕下就会
         * 放大，导致内存占用增加
         *
         *
         */

        //获取采样率(缩放比例)
      /*  public static int calculateInSampleSize(
                BitmapFactory.Options options, int reqWidth, int reqHeight) {
            // Raw height and width of image
            final int height = options.outHeight;
            final int width = options.outWidth;
            int inSampleSize = 1;

            if (height > reqHeight || width > reqWidth) {

                final int halfHeight = height / 2;
                final int halfWidth = width / 2;

                // Calculate the largest inSampleSize value that is a power of 2 and keeps both
                // height and width larger than the requested height and width.
                while ((halfHeight / inSampleSize) >= reqHeight
                        && (halfWidth / inSampleSize) >= reqWidth) {
                    inSampleSize *= 2;
                }
            }

            return inSampleSize;
        }*/

        //加载指定宽高的图片
        /*public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
        int reqWidth, int reqHeight) {

            // First decode with inJustDecodeBounds=true to check dimensions
            final BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeResource(res, resId, options);

            // Calculate inSampleSize
            options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

            // Decode bitmap with inSampleSize set
            options.inJustDecodeBounds = false;
            return BitmapFactory.decodeResource(res, resId, options);
        }*/
    }


    /**
     * 1 如何自定义View?
     * 2 自定义View如何考虑机型适配?
     * 3 自定义View的事件如何处理？
     */
    public void a1_8() {
        /*
         * =================如何自定义View===================
         * 写控件，继承View，如果要支持warp_content，需要重写onMeasure
         * 然后重写onDraw，在canvas中绘制View，
         * 如果有交互，那么要重写onTouchEvent，根据xy坐标的变化，或者是相对于
         * down事件的xy坐标的相对变化，进行相应参数的修改，比如scrollX/Y ，然后invalidate进行刷新，进行重新绘制
         *
         * 如果是ViewGroup，还要重新onLayout,来确定子View的 上下左右的位置，确定在布局中的位置
         * 以便于在绘制的时候 将ViewGroup指定Canvas的位置传给子控件来进行绘制
         * =====================自定义View如何考虑机型适配?======================
         * 获取屏幕分辨率，从新进行onLayout，
         * 或者在onSizeChanged中获取控件宽高进行适配
         * ===========自定义View的事件如何处理？=========================
         * 重写onTouchEvent(),根据业务需求返回true，进行事件消费
         *
         */
    }


    /**
     * 计算一个view的嵌套层级?
     */
    public void a1_9() {
        /*
         * 循环调用view.getParent,直到parent是ViewRootImpl
         */
    }

    /**
     * 如何禁止父布局拦截事件？
     */
    public void a1_10() {
        /*
         * new ViewGroup().requestDisallowInterceptTouchEvent(true)
         * getParent().requestDisallowInterceptTouchEvent(true)来阻止父控件调用onInterceptEvent
         *
         * 这样父布局就不会调用onInterceptTouchEvent()来判断是否要拦截了
         * 这个只在Down事件的时候判断(或者是在Down的时候有控件消费了这个事件，导致mFirstTouchTarget不为null)
         */
//        new ViewGroup().requestDisallowInterceptTouchEvent(true);
    }
    //endregion

    //region Android 内存/虚拟机

    /**
     * =====================
     * ### Android 内存
     * =====================
     * */

    /**
     * 说说什么是内存泄漏？
     * 说典型的例子？怎么避免？
     * 如何检测内存泄露？
     */
    public void a2_1() {
        /*
         * 本该被回收的对象因为存在对他的强引用而没有被回收
         * java内存回收根据 可达性分析，即从gc root（比如静态变量作为gc ）
         * 到被回收的对象 是可达的，那么他就不会被回收
         * ===============说典型的例子？怎么避免？==============
         * Android中最典型的就是Activity对象的泄漏，
         * 1.比如用Handler发延时消息
         * 在Activity销毁后消息还存在队列中，但是此时非静态Handler对象持有Activity的引用
         * 从而使Activity没有被回收，导致内存泄漏
         *
         * 解决方法：（使用一个静态内部类继承Handler来使用
         * 或者在 onDestroy()中移除消息）
         * -------
         *  2.还有一个例子是在Activity中使用匿名内部类，匿名内部类默认持有外部类的引用
         *  比如AsyncTask，当Activity销毁时任务没有执行完
         * 因为AsyncTask持有Activity的引用，也会导致泄漏，解决方法是在onDestroy调用
         * 他的cancel方法来中断线程
         *
         * Activity中开启匿名线程，而页面关闭时未被销毁
         * 匿名TimerTask
         *
         * 解决方法：页面关闭时销毁或者使用静态内部类
         *
         * 4。Activity被单例或者静态常量引用
         *
         * 解决方法：避免这种情况，或者在页面关闭时变量置空
         *
         * 5.Android5.1 webview 引起的泄露，
         * https://coolpers.github.io/webview/memory/leak/2015/07/16/android-5.1-webview-memory-leak.html
         *
         * 解决方法：onDestroy中 webview.getParent().removeView(webview)
         *
         *
         * 6.网络请求在页面关闭时没有被取消
         * 解决方法：关闭时取消
         *
         * ==============如何检测内存泄露？==========
         * 1.使用Android Profiler中的Memory，打开多个页面，再关闭，Gc一下，然后dump head
         * 查看是否有Activity没有被回收
         * 2.使用MAT来分析dump 出的heap （prof） 文件，有个merge gc root 选项，就能查出是哪里持有的引用
         *
         * 3.使用Leak Canary ，如果泄露会有通知提示，我们可以直接查看
         *
         *
         */
    }

    /**
     * Android进程如何保活？
     * Android进程分为哪几种？
     * 系统杀掉后如何重启？
     * 为什么要保活？
     * http://blog.csdn.net/andrexpert/article/details/75045678
     */
    public void a2_2() {
        /*
         * 我们APP要及时接收到通知，那么就需要通知服务一直在后台运行
         * Android的进程回收机制是用Low Memory Killer
         *
         * 1.监听系统广播唤醒app
         * 2.启动前台service，在通知栏发个消息
         * 3.减少内存消耗，防止被杀死
         * 4.一像素保活（动态监听屏幕锁屏解锁广播，在锁屏时开启一个像素的Activity）
         *   在黑屏状态下保活
         * 5.循环播放一段无声的音频，用一键清理也保活
         * 6.双进程相互唤起
         *
         * linux会为每个进程分配一个优先级，叫oom_adj，数值越低优先级越高，越不容易被杀死
         * 普通app的值一般是大于0，系统进程一般是小于0
         * 用adb shell进入手机命令行模式，然后用 ps|grep com.xxx 来查看包下的所有进程
         * 然后用 cat /proc/进程id/oom_adj 来查看进程的优先级数值
         *
         * ===============Android进程分为哪几种？===========
         * 前台进程，可见进程，服务进程，后台进程，空进程查看（a2_6()）
         *
         *
         *
         */
    }


    /**
     * Android的类加载器？
     * 加载流程是什么？/加载dex源码分析？
     */
    public void a2_4() {
        /*
         * PathClassLoader：只能加载已经安装到Android系统中的apk文件（/data/app目录），
         * 是Android默认使用的类加载器。
         *  PathClassLoader加载系统的类，是在ClassLoader中创建的，因为jvm,dvm不一样，所以
         *  google修改了ClassLoader的代码
         *
         * DexClassLoader：可以加载任意目录下的dex/jar/apk/zip文件，也就是我们一开始提到的补丁。
         *
         * ====================加载流程是什么？/加载dex源码分析？===============
         * https://juejin.im/post/5a0ad2b551882531ba1077a2
         * 实际上就是将dex文件转换为Element对象，一个dex文件对应一个Element
         * 首先遍历Element[]，依次调用element.findClass(name)
         * 里面调用了DexFile.loadClassBinaryName(),里面最终还是调用native方法
         *
         */
    }


    /**
     * Android Dalvik虚拟机和JVM的区别？
     */
    public void a2_3() {
        /*
         * 1.Android Dalvik 运行的是.dex 即Dalvik Executable,
         * 他是.class文件的压缩，这样占用的内存更少
         * 2.dvm是基于寄存器的，而jvm是基于栈的
         * http://rednaxelafx.iteye.com/blog/492667
         */
    }

    /**
     * ART 和 Dalvik的区别？
     * https://www.zhihu.com/question/29406156
     */
    public void a2_5() {
        /*
         * 1.使用了AOT(Ahead-of-time)代替了JIT(Just-in-time)
         * 2.提高了gc的效率,改成并行执行gc，以前gc的时候程序都要中断
         * 3.提高了内存使用效率和减少了碎片化。
         *
         * 1   jit 是dex要在程序运行的时候才转化为可执行的机器代码，
         * 转化后的dex是oat文件，而AOT是在安装的时候就讲dex转化为oat文件
         * AOT优点是执行快，不用转化了。缺点是安装时间变长，oat占用多余的内存空间
         *
         * 2.使用了并发的gc
         *
         * 3.专门分配了large-object-space，用来存放大内存，这样就不用每次都回收碎片内存了
         *
         */
    }


    /**
     * 进程的四种状态？内存低的时候Android系统是如何管理进程的？
     * https://developer.android.google.cn/guide/components/activities/process-lifecycle.html
     */
    public void a2_6() {
        /*
         * 1.前台进程，foreground process
         *
         * 这个进程中有一个resume的Activity，
         * 或者一个在执行onReceive()的BroadCastReceiver ,
         * 或者有一个在执行onCreate onStart onDestroy 的Service
         *
         * 2. 可见进程， visible process
         *
         * 进程中有一个没有焦点的Activity，但是它可见，比如一个半透明的Activity 盖住了他
         *
         * 有个前台的Service，通过Service.startForeground方法来显示一个Notification(比如音乐服务会开一个歌名显示
         * 在通知栏上)startForeground 将服务变为前台服务，使得它的优先级更高
         *
         * 有系统用的独特服务，比如动态壁纸，输入法服务
         *
         * 3.服务进程 service process
         * 进程中有startService 方式打开的Service，当前两种进程内存不够用时，将回收这个进程
         * 连续运行30分钟以上有可能会被降级，因为这有可能发生了内存泄漏而占用太多内存
         *
         * 4.缓存进程 cached process（）后台进程
         * 一般这种进程中有1个或者多个onStop的Activity，这个时候当内存不足时会优先回收
         * 一般优先回收的是最久没有用过的进程。
         *
         *
         * 5.空进程
         * 进程中没有Activity，没有Service，重启的时候不会执行Application的onCreate
         *
         */
    }

    /**
     * Android为每个应用程序分配的内存大小是多少？
     */
    public void a2_7() {
        /*
         * 初始内存分配的大小配置在 /system/build.prop
         * dalvik.vm.heapstartsize=8m   初始分配
         *   dalvik.vm.heapgrowthlimit=96m   增长的限制
         *   dalvik.vm.heapsize=384m 设置largeheap时的大小
         *
         */
        Runtime runtime = Runtime.getRuntime();
        long l = runtime.maxMemory();//bytes vm最大能申请的内存
        long l1 = runtime.totalMemory();// 当前分配的内存大小

        //mix2 的结果
        //268435456 256m  18250546  17.4m
    }
    //endregion

    //region Android 四大组件基本知识


    //region Activity/Fragment

    /**
     * 1.Activity 生命周期的理解？
     * 2.横竖屏切换时的生命周期？如何配置?
     * 3.显示dialog时 Activity的生命周期?
     * 4.Activity上有Dialog的时候按Home键（前后台切换）时的生命周期？
     * 5.跳转时的生命周期
     * 6.锁屏和解锁后生命周期
     * 7.永久性质的数据，应该在哪个生命周期方法中保存?
     * https://developer.android.google.cn/guide/components/activities.html
     * https://developer.android.google.cn/guide/components/activities/activity-lifecycle.html#java
     */
    public void a3_1() {
        /*
         * =============1==========================
         * onCreate() 设置布局，初始化变量，接收Bundle来恢复Activity
         * onStart() 这个方法不建议做耗时操作，可以进行注册广播接受者的操作
         * 这个时候Activity还不可见，
         *
         * onRestoreInstanceState 在onStart后调用， 在onPostCreate前调用
         * 一般我们在onCreate中就恢复了原有状态，但是在这调用时为了有些时候
         * 我们需要等所有资源初始化完毕后再调用。（这个只有在系统回收后，我们
         * 再次启动的时候调用,还有在屏蔽旋转销毁后）所以这里的Bundle一定不为null
         *
         *
         * onResume()后，Activity变得可见，一般在这个方法中恢复在onPause
         * 中释放的资源，或者初始化动画？这个时候Activity获取到焦点
         *
         * onPause()当Activity被遮罩的时候，失去焦点，用户不能再与之交互
         * 比如半透明的activity（作为dialog的Activity），这个Activity部分可见，就会
         * 失去焦点，调用onPause();注意Dialog出现不会调用任何方法
         *
         * onSaveInstanceState 永远在onPause后和onStop前调用，为什么这么设计？
         * 是因为我们如果onStop后调用，那么有可能被系统回收而得不到回调。
         * onPause之前调用又会效率太低。（用户按back键或者finish时这个方法不被调用
         * ，因为用户已经明确不需要这个Activity了，只有在Activity进入后台，有可能被
         * 回收的时候，才会调用（比如home键，锁屏，打开新的Activity））
         *
         *
         * onStop当Activity完全不可见的时候进入这个方法。这个方法中我们释放
         * 一些用户不用的资源，比如我们在onStart中注册的广播，可以在
         * onStop中取消注册。还可以释放一些资源以免内存泄漏，因为这个Activity
         * 有可能被系统终止而不调用onDestroy ,如果Activity重新回到前台，
         * 会调用onReStart()-OnStart
         *
         * onDestroy 释放所有资源
         *
         *
         *
         *
         * =====================2 横竖屏切换时的生命周期？如何配置?==================
         * https://blog.csdn.net/xiaoli100861/article/details/50855152
         *  方向切换的时候我们会销毁后重建，当然onSaveInstanceState
         *  onRestoreInstanceState 可以用来恢复数据
         *  onPause ,onStop onDestroy onCreate onStart onResume
         * 我们在清单文件中配置不销毁
         * android:configChanges="orientation|keyboardHidden|screenSize"
         * （这个和操作系统(4.0)和targetApi(12)有关，但是最新的一般都是这样配置）
         * ================3 显示dialog时 Activity的生命周期 ========================
         * Dialog的出现不会调用Activity的任何生命周期，
         * 调用生命周期是ActivityManager，而dialog是通过WindowManager来管理的
         * （但是好像系统的Dialog会造成onPause???）
         *====================4 Activity上有Dialog的时候按Home键（前后台切换）时的生命周期？=======================
         * 有无dialog，Activity的进入后台，切回前台生命周期都是这样的
         *   onPause:
         *   onSaveInstanceState:
         *   onStop:
         *   onRestart:
         *   onStart:
         *   onResume:
         * (只有被销毁再回来才调用 onRestoreInstanceState)
         *
         * ==================5 跳转时的生命周期=========================
         * A开启B, A-onPause B-onCreate\onStart\onResume A-onStop
         * B关闭，B-onPause A-onRestart\onStart\onResume  B-onStop\onDestroy
         * ================6 锁屏和解锁后生命周期 ================
         * 锁屏和前后台切换的生命周期相同
         *
         * ===============7 永久性质的数据，应该在哪个生命周期方法中保存?================
         * 永久性数据应该在onStop中存储，因为在后台有可能被系统kill掉不调用onDestroy
         * onPause中太频繁
         *
         */
    }


    /**
     * Activity与Fragment之间生命周期比较
     * https://developer.android.google.cn/guide/components/fragments.html
     *
     * @link com.liyafeng.view.fragment.Main}
     */
    public void a3_2(Context context) {
        /*
         * Fragment生命周期是FragmentManager来控制的
         * 他本质上还是inflate了布局，然后addView的方式加入到Activity的布局中
         * 他也有回退栈管理，按返回键和Activity效果一样
         *
         */
        context.getResources().getDrawable(R.drawable.fragment_lifecycle);
        context.getResources().getDrawable(R.drawable.activity_fragment_lifecycle);

    }

    /**
     * Activity的四种启动模式对比? 回退栈有什么用？
     * https://developer.android.google.cn/guide/components/activities/tasks-and-back-stack.html
     * https://blog.csdn.net/u012203641/article/details/77408342
     */
    public void a3_6() {
        /*
         * https://blog.csdn.net/u012203641/article/details/77408342
         */
    }


    /**
     * Fragment状态保存startActivityForResult是哪个类的方法，在什么情况下使用？
     */
    public void a3_8() {
        /*
         * 有个回调是在FragmentActivity中调用的，
         */
    }

    /**
     * 如何实现Fragment的滑动？
     */
    public void a3_9() {
        /*
         * 用ViewPager
         */
    }

    /**
     * fragment之间传递数据的方式？
     * Activity和Fragment之间数据传递方式？
     */
    public void a3_10() {
        /*
         *===========fragment之间传递数据的方式？============
         * 可以通过Activity来传递，也可以用EventBus的实现方式
         *
         * =========Activity和Fragment之间数据传递方式？======
         * Activity给Fragment传递数据，
         * 可以用intent传递
         * 可以调用Fragment对象传递（findFragmentById/Tag）
         *
         * Fragment给Activity传递数据，
         * 可以用getActivity 获取对象来传递
         * 可以用给Fragment设置一个Callback来传递
         *
         */
    }

    /**
     * Activity的加载流程
     * http://www.liyafeng.com/c/Android_APIstartActivity流程分析
     */
    public void a3_11() {
        /*
         * 首先用binder请求到ActivityManagerService ，然后会回调到本进程的
         * ActivityThread，在里面会通过反射方式new 出Activity的对象，然后会
         * 回调Activity的生命周期
         */
    }

    /**
     * ViewPager如何设置只初始化当前的Fragment？
     * https://blog.csdn.net/linglongxin24/article/details/53205878
     */
    public void a3_12() {
        /*
         * 1判断当前Fragment是否可见，如果可见才loadData() (onHiddenChanged())
         * 2.或者从低版本v4中拷贝一份，将里面的DEFAULT_OFFSCREEN_PAGES 改为0
         * 可以通过反射来改变DEFAULT_OFFSCREEN_PAGES的值，但是会不起作用
         * 因为对于int long String这些基本类型，java在编译的时候会用数值替换掉这个常量
         * 所以我们修改了常量值，但是代码中的值却已经写死了
         * http://www.barryzhang.com/archives/188
         *
         */
        Fragment fragment = new Fragment();
        fragment.setUserVisibleHint(true);
        boolean userVisibleHint = fragment.getUserVisibleHint();

    }

    /**
     * 说说fragment的设计原理？
     */
    public void a3_13() {
        /*
         * 拿v4包里的来说
         *
         * 有 FragmentActivity，里面主要是持有了一个 FragmentController
         * FragmentController又持有 FragmentActivity中的 HostCallbacks
         * HostCallbacks extends FragmentHostCallback<FragmentActivity>
         * FragmentHostCallback持有FragmentManager
         *
         * FragmentActivity的声明周期中调用 FragmentController中的方法
         *  FragmentController调用HostCallbacks 中的 FragmentManager方法
         *  然后调用对应的声明周期
         *  比如在Activity的onCreate中，最终调用到 FragmentManager的
         *  Fragment的onAttach onCreate实在 Fragment.INITIALIZING中调用的
         *  onCreateView onViewCreated onActivityCreated是在 Fragment.CREATED中调用的
         *
         *  也是在Fragment.CREATED中 将 Fragment.View加入到Activity的container中的 （addView）
         *
         *  如果是代码中创建的Fragment，那么他的生命周期是在Activity生命周期走完后调用的
         *
         * */
    }
    //endregion

    //region BroadCastReceiver

    /**
     * 广播有几种注册方式？区别？/各有什么优点？
     * 广播有哪几种？
     * 隐式广播是什么？
     * 新版本对广播的变化？如何监听8.0被移除了的广播？
     * AndroidManifest中四大组件的android:exported="true"什么意思？
     * 如何通过广播拦截和abort一条短信？
     * 广播引起anr的时间限制是多少？（10秒）
     * https://developer.android.google.cn/guide/components/broadcasts.html#receiving_broadcasts
     */
    public void a4() {
        /*
         *===========广播有几种注册方式？区别？=============
         * 1 AndroidManifest中静态注册 2 代码中动态注册
         *
         * 静态注册广播：
         * 当app安装的时候，这些静态广播由packageManager来管理，每当接收到广播的时候，系统会创建一个实例来处理这个广播
         * 执行完onReceive时，这个组件被销毁
         *
         * 动态注册广播：
         * 需要Context，广播的存在和Context的生命周期一致，比如Activity，只有Activity没有销毁的时候
         * 才能接收到广播，如果是Application context，则在app运行的时候才能接收到
         *
         *
         * 区别：
         * 1.生命周期不同，一个是packageManager管理App安装一直存在，一个和Context生命周期一样
         * 2.在Android 8.0以上（api26）只有动态注册才能监听到隐式广播
         *
         *
         * https://toutiao.io/posts/jb0dwz/preview
         *
         * ==========广播有哪几种？============
         * 普通广播和有序广播
         * 普通广播是并行的
         * 有序广播是顺序执行的，比如收到短信
         *
         * =========隐式广播是什么？==========
         * https://developer.android.google.cn/about/versions/oreo/background?hl=zh-cn
         * 隐式广播是一种不专门针对该应用的广播 ，系统广播都属于隐式广播
         * 例如，ACTION_PACKAGE_REPLACED 就是一种隐式广播，因为它将发送到注册的所有侦听器，让后者知道设备上的某些软件包已被替换。
         * 不过，ACTION_MY_PACKAGE_REPLACED 不是隐式广播，因为不管已为该广播注册侦听器的其他应用有多少，它都会只发送到软件包已被替换的应用。
         *
         * ===========新版本对广播的变化？===============
         * https://developer.android.google.cn/guide/components/broadcasts#receiving_broadcasts（每个版本的变化）
         *
         * 在Android8.0开始我们不能在xml中注册隐式广播了，因为那样会很耗电，但是有些广播除外（因为他们触发频率小）
         * https://developer.android.google.cn/about/versions/oreo/features/background-broadcasts?hl=zh-cn
         *
         * 7.0去除了几个广播
         * ACTION_NEW_PICTURE
         * ACTION_NEW_VIDEO
         *
         *  CONNECTIVITY_ACTION这个必须动态注册才有效
         *
         * -----------------
         * 我们可以用JobScheduler来代替去除的隐式广播
         * https://toutiao.io/posts/jb0dwz/preview
         *
         * ==========AndroidManifest中四大组件的android:exported="true"什么意思？==========
         * 如果为true，那么代表这个组件可以被其他进程调用（唤起）
         * 默认如果有intent-filter 的默认为true，否则为false
         */
    }

    /**
     * 有哪些常见的系统广播
     */
    public void a4_1() {
        /*
         * 系统开机
         * 飞行模式
         * 网络状态改变
         *
         */
    }

    /**
     * 本地广播和全局广播的差别?
     * \https://developer.android.google.cn/reference/android/support/v4/content/LocalBroadcastManager
     * <p>
     * http://www.cnblogs.com/trinea/archive/2012/11/09/2763182.html（广播详细介绍）
     */
    public void a4_2() {
        /*
         * 本地广播只能在本应用内传播，使用LocalBroadcastManager来注册和发送
         * 优点：
         * 1.不必担心隐私数据发送到其他进程
         * 2.不必担心接收到其他进程不安全的数据
         * 3.比全局广播更高效（全局广播用的binder，而且要遍历所有系统中注册的广播接受者）
         *
         *  LocalBroadcastManager.getInstance(this).registerReceiver();
         *  位于support-v4中
         *  原理：
         *  他其实就和观察者类似，里面用HashMap来存储，用handler来切换线程
         *
         *  =================全局广播的分类=======================
         *  1，黏性广播，在发送后注册的广播还能接受到消息
         *  （已经不建议使用，因为不安全，可以用别的方式来获取状态）
         *  需要权限
         *  <uses-permission android:name="android.permission.BROADCAST_STICKY"/>
         *  Context.sendStickyBroadcast(Intent)
         *  也可以移除黏性广播removeStickyBroadcast(Intent intent)
         *  2.有序广播，，通过Context的sendOrderedBroadcast接口发送
         *  广播接受者通过注册顺序一个个处理广播，
         */
//        new Context().sendOrderedBroadcast();

    }
    //endregion

    //region Service

    /**
     * 请描述一下Service 的生命周期?
     * start和bind的区别？
     * https://developer.android.google.cn/guide/components/services
     */
    public void a5_1() {
        /*
         * start方式，onCreate ,onStartCommand ,onDestroy
         * bind方式，onCreate,onBind  onUnBind ,
         *
         *  * 多次start 会调用多次startCommand()
         * bind只能调用一次，否则抛异常
         * bind后调用stop无效果
         * start和bind可以不分顺序的调用
         *
         * 一个服务同时允许这两种方式运行
         *
         * ==========start和bind的区别？===========
         * 注意start后服务一直运行，知道被系统回收或者stop
         * bind后一直运行，多个组件可以同时绑定到该服务，直到全部取消后，该服务会被销毁
         *

         *
         */
    }
    //endregion

    //region ContentProvider

    /**
     * 谈谈你对ContentProvider的理解?
     * https://developer.android.google.cn/guide/topics/providers/content-providers.html
     */
    public void a6_1() {
        /*
         * 我们可以用这个组件来提供自己app的数据（CRUD操作），比如系统提供的音频，视频
         * 相片，联系人，日历
         *
         */

        // Queries the user dictionary and returns results
//        mCursor = getContentResolver().query(
//                UserDictionary.Words.CONTENT_URI,   // The content URI of the words table
//                mProjection,                        // The columns to return for each row
//                mSelectionClause                    // Selection criteria
//                mSelectionArgs,                     // Selection criteria
//                mSortOrder);                        // The sort order for the returned rows
    }

    /**
     * ContentProvider的权限管理(解答：读写分离，权限控制-精确到表级，URL控制)
     */
    public void a6_2() {
        /*
         *
         */
    }
    //endregion


    //endregion


    //region Android 操作系统
    /**
     * =====================
     * ### Android 操作系统
     * =====================
     * */

    /**
     * Android  6.0的权限机制？6.0之前的权限机制？
     * 如何权限适配？
     * 权限机制的原理是什么？
     */
    public void a8() {
        /*
         * 权限请求
         * https://developer.android.google.cn/training/permissions/requesting.html?hl=zh-cn
         * https://blog.csdn.net/cadi2011/article/details/71642355(权限被拒)
         * ContextCompat.checkSelfPermission
         * ActivityCompat.shouldShowRequestPermissionRationale（是否应该显示解释的弹窗（解释为何需要这个权限））
         * （如果你之前请求过这个权限，但是用户拒绝了的时候，返回true
         * 如果拒绝了，而且Don’t ask again ，那么返回false）
         *
         * 如果被拒，而且Don’t ask again，那么checkSelfPermission为false，而且shouldShowRequestPermissionRationale
         * 也为false，这个时候要弹出框让用户去系统设置里打开（因为这时ActivityCompat.requestPermissions是无效的）
         *
         *   ActivityCompat.requestPermissions
         *
         *   onRequestPermissionsResult
         * ============如何权限适配？===========
         * target改到23以上
         * 然后用上面的方法请求权限即可
         *
         * -----------------------
         * 6.0后要动态申请权限，6.0之前可以在xml中申请权限，用户在安装的时候同意所有
         * 原理是调用系统api的时候回去判断这个应用有没有被授权，如果有则执行，没有就
         * 返回null或者异常。
         */
    }

    /**
     * looper架构?
     * {@link android.os.Handler}
     */
    public void a8_1() {
        /*
         * Handler=》{Looper } Handler只能在有Looper的线程创建（主线程已经在初始化的时候就已经创建Looper了）
         * Looper=>{MessageQueue}
         * 在创建Handler是，获取 Looper.myLooper();里面用的ThreadLocal来保证每个线程取出各自的Looper
         *
         * Looper.prepare();//给这个线程创建一个Looper，然后放到ThreadLocal中
         *
         * Looper.loop();//从MessageQueue中取Message，如果没有，就阻塞（用的native方法）
         *
         * //取出来后调用 msg.target.dispatchMessage(msg);target就是Handler
         * dispatchMessage 内部调用handleMessage方法
         *
         * sendMessage();将msg加入到MessageQueue中（这是一个链表的形式存储）,
         * 存储后调用nativeWake(如果Looper.loop中的queue.next()被阻塞的话)
         * ----------------------------------------
         * 这个Handler思想还是一个线程中的轮训器去取 消息队列中的Message
         * 没有就阻塞。Handler绑定这个Looper，然后向他的MessageQueue中插入消息
         *
         * 一个线程只能有一个Looper,否则抛出异常
         *
         */
    }


    /**
     * 说说App启动流程？
     * (点下App图标后到界面显示都发生了什么？)
     * 为什么点击home app退到后台，再次点击图标，不会再次启动app？
     * https://www.jianshu.com/p/a5532ecc8377
     * https://blog.csdn.net/luoshengyang/article/details/6689748
     */
    public void a8_2(Context context) {
        /*
         * 桌面也是一个应用，里面收集了所有应用的信息（可以用
         * PackageManager.qureyActivities 来搜索所有 launch页面）
         * 然后获取到包名,当我们点击的时候 桌面应用启动指定包名的启动页（指定intent的action和category）
         * 到此，后面就是我们开启一个Activity（startActivity方法）所发生的事情了
         * ----------------------
         * http://www.liyafeng.com/c/Android_APIstartActivity%E6%B5%81%E7%A8%8B%E5%88%86%E6%9E%90
         * 这个时候交给ActivityManagerService，然后判断这个进程是否存在，如果不存在
         * 则用ZygoteProcess,来start一个进程。 里面用的fork命令来 开启一个新的进程
         * 然后新进程的入口就是，com.android.server.SystemServer中的main()方法，
         * 里面调用了createSystemContext();方法中调用了ActivityThread.systemMain()
         * 这个方法中new ActivityThread(),这时被创建了，ApplicationThread是在ActivityThread创建的时候就被创建了（成员变量直接new的）
         * 紧接着调用attach方法，里面通过反射创建了Application对象，
         *
         * 总结一下
         * 1,launcher调用startActivity
         * 2,ActivityMangerService将launcher进入pause状态
         * 3.ActivityMangerService判断进程是否启动，没有启动则调用Process.start()来开启一个进程，其实是zygote进程fork出来的
         * 4.启动进程后dalvik会调用SystemServer.main()方法，这个方法中创建ActivityThread，继而创建ApplicationThread
         * 5,ApplicationThread绑定到ActivityManagerService中？？？
         * 6.ActivityManagerService发送通知让Activity执行创建Application对象,调用onCreate()，
         *  启动Activity，调用他的onCreate()
         */

        /*
         *    at com.android.nfc.NfcApplication.onCreate(NfcApplication.java:61)
         *   at android.app.Instrumentation.callApplicationOnCreate(Instrumentation.java:1014)
         *   at android.app.ActivityThread.handleBindApplication(ActivityThread.java:4707)
         *   at android.app.ActivityThread.access$1600(ActivityThread.java:150)
         *   at android.app.ActivityThread$H.handleMessage(ActivityThread.java:1405)
         *   at android.os.Handler.dispatchMessage(Handler.java:102)
         *   at android.os.Looper.loop(Looper.java:148)
         *   at android.app.ActivityThread.main(ActivityThread.java:5417)
         *   at java.lang.reflect.Method.invoke(Native Method)
         *   at com.android.internal.os.ZygoteInit$MethodAndArgsCaller.run(ZygoteInit.java:726)
         *   at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:616)
         *
         * */
        context.getResources().getDrawable(R.drawable.app_launch);
        context.getResources().getDrawable(R.drawable.app_launch_flow);
        context.getResources().getDrawable(R.drawable.app_launch_flow_2);
        context.getResources().getDrawable(R.drawable.app_launch_flow_3);
        context.getResources().getDrawable(R.drawable.app_launch_start_activity);
    }

    /**
     * 说说Android系统开机流程
     * （很详细的流程)
     * https://blog.csdn.net/itachi85/article/details/54783506（init）
     * https://blog.csdn.net/itachi85/article/details/55047104 (zygote)
     * https://blog.csdn.net/itachi85/article/details/55053356 (systemServer)
     */
    public void a8_3() {
        /*
         * ======================1 启动init进程========================
         * android系统基于Linux，所以点击开机按钮后，先启动linux内核
         * 然后启动init进程，system/core/init/init.cpp ，调用里面的main函数
         * 里面加载了init.rc文件（位置/system/core/rootdir/init.rc），
         * 在7.0后，将zygote启动的.rc文件单独分离出来了 /system/core/rootdir/init.zygote64.rc
         * 部分内容：service zygote /system/bin/app_process64 -Xzygote /system/bin --zygote --start-system-server
         * 看到指定用app_process这来启动zygote，这是个cmd命令，是由app_main.cpp编译成的可执行文件。
         * /frameworks/base/cmds/app_process/Android.mk中会看到app_main.cpp变编译成名为app_process的库
         * 调用后会执行 /frameworks/base/cmds/app_process/app_main.cpp中的main
         * 在main中调用 runtime.start("com.android.internal.os.ZygoteInit", args, zygote);
         * 来启动zygote进程
         *
         * 所以init进程主要做了三件事
         * 1,创建文件夹并挂载设备
         * 2.初始化和启动属性服务（这个用来存储系统的一些属性信息，每次启动要加载，类似于windows的注册表）
         * 3.解析init.rc配置文件并启动dvm ，绑定c和java的函数，启动zygote进程
         *
         * ==========================启动Zygote进程===================
         * 上面知道app_main.cpp中通过runtime.start("com.android.internal.os.ZygoteInit", args, zygote);
         * 这个runtime是frameworks/base/core/jni/AndroidRuntime.cpp
         *
         * 来启动java的Zygote进程，start方法中，通过Jni_invocation api来启动虚拟机
         * 启动后调用startReg来动态注册jni函数（使c++函数和java函数手动关联）
         * 所以我们看native函数的映射关系可以在这里看到。
         *
         * 然后通过JNIEnv* env 中的函数来调用 ZygoteInit.java的main函数
         *
         * 然后zygote进程就算启动了，这是运行在dvm中的一个进程
         * /frameworks/base/core/java/com/android/internal/os/ZygoteInit.java
         *
         * 接下来是ZygoteInit.java中的main函数
         * 1，注册zygote用的socket
         * 2.预加载系统的class文件（Class.forName()）和资源文件 android.R.xxx
         * 3.启动SystemServer进程（Zygote.forkSystemServer（）+handleSystemServerProcess()）
         * 4.socket进入无限循环等待，等待ActivityManager中发来的消息
         * 5.从AM接收到ZygoteConnection，执行ZygoteConnection.runOnce()方法
         *    里面调用了Zygote.forkAndSpecialize，来创建新的进程（这些进程都共享了预加载的资源和dvm）
         *
         * 总结一下Zygote进程启动后做的事情：
         * 1,注册socket来接收消息
         * 2.预加载类文件和资源
         * 3.用fork启动SystemServer进程
         * 4.循环等待消息来fork出新的进程
         *
         * ===================启动SystemServer进程========================
         * /frameworks/base/core/java/com/android/internal/os/ZygoteInit.java
         * ZygoteInit的main中启动SystemServer
         * zygote进程fork出SystemServer进程后，调用了handleSystemServerProcess()
         *     其实调用了fork后，那么接下来的代码就在新fork的进程中执行了，fork操作实际上就是将
         * 上下文代码和内存空间拷贝了一份。
         *       handleSystemServerProcess()先创建ClassLoader，PathClassLoader在启动SystemServer后创建
         * cl = createPathClassLoader(systemServerClasspath, parsedArgs.targetSdkVersion);
         * 里面调用了 ZygoteInit.zygoteInit（）
         *   里面调用ZygoteInit.nativeZygoteInit();这个方法是本地方法，调用的是
         * frameworks/base/core/jni/AndroidRuntime.cpp中的com_android_internal_os_RuntimeInit_nativeZygoteInit
         * 这个方法中主要是启动了binder线程池
         *   ZygoteInit.zygoteInit（）中接下来调用
         *   RuntimeInit.applicationInit(targetSdkVersion, argv, classLoader);
         * /frameworks/base/core/java/com/android/internal/os/RuntimeInit.java
         * applicationInit（）中调用了invokeStaticMain（）
         * 里面通过反射获取到com.android.server.SystemServer 的main方法Method对象
         * 然后抛出一个异常throw new ZygoteInit.MethodAndArgsCaller(m, argv)
         * ZygoteInit.java的main方法会捕获到这个异常，然后调用caller.run();
         * run（）中执行了反射的方法 mMethod.invoke(null, new Object[] { mArgs });
         * 即调用了SystemServer.main()
         * ZygoteInit.main()->ZygoteInit.startSystemServer()->ZygoteInit.handleSystemServerProcess()
         * ->ZygoteInit.zygoteInit（）/RuntimeInit.applicationInit（）
         * ->反射获取SystemServer.main()抛出异常->ZygoteInit.main()调用了caller.run()->SystemServer.main()
         *
         * frameworks/base/services/java/com/android/server/SystemServer.java
         * SystemServer.main()中调用new SystemServer().run();
         * run()方法中
         *  Looper.prepareMainLooper();//创建MainLooper，这是在SystemServer的进程中
         *  System.loadLibrary("android_servers");//加载so库
         *  createSystemContext();-》
         *        ActivityThread activityThread = ActivityThread.systemMain();
         *        mSystemContext = activityThread.getSystemContext();
         *
         *  mSystemServiceManager = new SystemServiceManager(mSystemContext);// Create the system service manager.
         *       ArrayList<SystemService> mServices = new ArrayList<SystemService>()
         *       //SystemServiceManager中有个List，存储它开启的服务
         *  LocalServices.addService(SystemServiceManager.class, mSystemServiceManager);
         *  startBootstrapServices();//创建引导服务
         *       mSystemServiceManager.startService(Installer.class);
         *       mActivityManagerService = mSystemServiceManager.startService(ActivityManagerService.Lifecycle.class).getService();
         *       mActivityManagerService.setSystemServiceManager(mSystemServiceManager);
         *       mActivityManagerService.setInstaller(installer);
         *       mPowerManagerService = mSystemServiceManager.startService(PowerManagerService.class);
         *       mPackageManagerService = PackageManagerService.main(mSystemContext, installer,mFactoryTestMode != FactoryTest.FACTORY_TEST_OFF, mOnlyCore);
         *       //AMS,PMS都是在SystemServer进程启动的时候创建的，所以他们在SystemServer进程
         *       //startService都是根据反射来创建对象。
         *  startCoreServices();
         *         mSystemServiceManager.startService(BatteryService.class);
         *         mSystemServiceManager.startService(UsageStatsService.class);
         *  startOtherServices();
         *       inputManager = new InputManagerService(context);
         *       wm = WindowManagerService.main(context, inputManager,mFactoryTestMode != FactoryTest.FACTORY_TEST_LOW_LEVEL,!mFirstBoot, mOnlyCore, new PhoneWindowManager());
         *       ServiceManager.addService(Context.WINDOW_SERVICE, wm);
         *       ServiceManager.addService(Context.INPUT_SERVICE, inputManager);
         *       //main中都是new出来的对象并返回
         *       Watchdog.getInstance().start();
         *  Looper.loop();//开始接受消息
         *
         * SystemServer进程启动后做的事：
         * 1.创建binder线程池
         * 2.调用自己的main方法，run方法，然后创建Looper，创建ActivityThread，创建各种服务对象
         *
         * =========================Launcher的启动=================
         * 在startOtherServices()中的最后mActivityManagerService.systemReady
         * 里面经过层层调用，最终调用了ActivityStarter.startActivity（）
         * 而intent是Launcher应用的过滤条件
         * <action android:name="android.intent.action.MAIN" />
         * <category android:name="android.intent.category.HOME" />
         * <category android:name="android.intent.category.DEFAULT" />
         * /packages/apps/Launcher3/
         * 看Launcher源码，里面加载了所有app的信息，显示在一个自定义的RecyclerView上
         *
         */
    }

    /**
     * Android源码下载，编译，导入Studio预览？
     * http://wl9739.github.io/2016/05/09/Android%E6%BA%90%E7%A0%81%E7%9A%84%E4%B8%8B%E8%BD%BD%E3%80%81%E7%BC%96%E8%AF%91%E4%B8%8E%E5%AF%BC%E5%85%A5%E5%88%B0Android-Studio/
     * <p>
     * http://kaedea.com/2016/02/09/android-about-source-code-how-to-read/
     */
    public void a8_4(Context context) {
        /*
         * https://github.com/aosp-mirror
         * 这个是android open system project 在github上的镜像，但是代码不全，
         * =========================
         * https://source.android.com/setup/downloading
         * 这个是官方的源码下载教程
         * ===============================
         * http://androidxref.com/
         * 这个网站不用翻墙也能看，能看platform下的代码，但也不是全部的
         * https://android.googlesource.com/?format=HTML
         * 这个是全部的代码了，包括一些虚拟机和一些工具比如ndk的源代码
         * 但是关于android整个四层架构的代码都在platform/下了，所以我们只看
         * 这个目录下的代码就足够了。
         * ===================================================
         * 如果我们要下载aosp,但是不想翻墙，那么可以使用清华的镜像网站
         * https://mirror.tuna.tsinghua.edu.cn/help/AOSP/
         * ================android的platform下目录结构=================
         * 里面有
         * /frameworks 是framework层的代码，
         * /dalvik 虚拟机代码
         * /frameworks/native 里面有Binder的native代码
         *
         *
         */
        context.getResources().getDrawable(R.drawable.android_architecture);
    }

    /**
     * AsyncTask 如何使用?原理？
     * -------------------
     * 如何取消AsyncTask？
     */
    @SuppressLint("StaticFieldLeak")
    public void a8_5() {
        /*
         * AsyncTask 持有 static的 线程池，和 static的 Handler(Looper是主线程的)
         *  还有非静态的 WorkerRunnable（是个Callable），一个FutureTask（持有WorkerRunnable）
         * WorkerRunnable中的call()是真正执行doBackground的地方，随后将结果用Handler
         * 发到主线程，执行onPostExecute(Result s);
         *
         * 执行了execute后，将我们传入的参数 赋给 WorkerRunnable中的Param[] ,然后
         * WorkerRunnable中调用doBackground(params) ，
         * --------------------
         * 我们需要在doBackground中调用publishProgress(Progress... p)方法，来回调onProgressUpdate
         * 这个也是通过Handler发送到主线程的
         *
         * ======================================
         * 总结，一个AsyncTask对应一个FutureTask+Callable， 执行，传入参数
         * 用静态的线程池执行FutureTask，调用Callable中的call，调用doBackground
         * 返回结果后，用静态的Handler发送结果到主线程，执行onPostExecute
         *
         * ======================如何取消AsyncTask？==============
         * 调用他的cancel方法，里面调用的futureTask，的cancel方法，因为futureTask.get()方法阻塞，
         * 等待call执行完会将他唤醒，那么现在直接调用interrupt方法将线程中断阻塞。
         *
         * ===================AsyncTask在执行多个任务时是串行还是并行？==================
         * 模式是串行的，利用了ArrayDeque<Runnable> 来存储任务队列，一个任务的run方法
         * 里面是静态的线程池，handler
         * new 一个AsyncTask只能执行一次，因为他代表一个任务对象，就算你new多个同时执行
         * 他还是串行执行的。因为里面的线程池是静态的，多个task对象共用一个线程池
         *
         */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.CUPCAKE) {
            new AsyncTask<Integer, Double, String>() {

                @Override
                protected void onPreExecute() {
                }

                @Override
                protected void onProgressUpdate(Double... values) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.CUPCAKE) {
                        super.onProgressUpdate(values);
                    }
                }

                @Override
                protected String doInBackground(Integer... integers) {
                    //后台处理
                    String s = "";
                    for (Integer integer : integers) {
                        s += integer;
                    }
                    return s;
                }

                @Override
                protected void onPostExecute(String s) {
                    System.out.println(s);
                }
            }.execute(1, 2, 3).cancel(true);//传入元素数据
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            AsyncTask.execute(new Runnable() {
                @Override
                public void run() {

                }
            });
        }
    }


    /**
     * ThreadLocal作用？原理？
     * {@link java.lang.ThreadLocal}
     */
    public void a8_6() {
        /*
         * Thread中有静态内部类，ThreadLocalMap key是ThreadLocal，value是对应值，因为一个Thread能
         * 对应多个ThreadLocal实例。
         * 不同ThreadLocal实例 对应不同的值。(一个Thread中可以有多个ThreadLocal变量)
         * 所以在Thread中用一个ThreadLocalMap对象来存储，这个ThreadLocalMap自己实现了散列表
         * ThreadLocalMap key是ThreadLocal对象，value就是泛型的值
         * ============作用===========
         * 创建 线程唯一 的变量，就是一个线程对应一个变量，相互不冲突
         * ============原理============
         * 我们调用set方法为线程设置 变量值，
         * Thread中有成员变量，ThreadLocalMap，ThreadLocalMap里面有个一个Entry[]
         * 里面存放着每个ThreadLocal实例对应的Entry，Entry中有value值。
         * ------
         * 我们set(value)的时候，判断当前线程有没有ThreadLocalMap，如果没有，那么new一个
         * ，将value和ThreadLocal实例放入构造方法
         * 如果Thread中存在ThreadLocalMap对象，那么直接调用ThreadLocalMap对象的set(ThreadLocal,value)
         * 方法。
         *
         * get()方法，获取当前Thread的ThreadLocalMap对象，如果存在，调用map.getEntry(TheadLocal this)
         * 方法，那么entry.value就是要取出的值了
         *    如果不存在，那么调用setInitialValue()，里面调用initialValue()来获取默认值（我们可以重写这个方法）
         *    这个方法默认返回null。然后同上面一样，new 一个ThreadLocalMap（ThreadLocal,value），
         *    然后赋值给Thread的变量。
         * ================通过ThreadLocal这个key用的什么算法找到对应的Entry？===============================
         * 用的是线性探测法的散列表形式来找到对应的Entry.
         * 计算hash值得方法就是 threadLocal.threadLocalHashCode & (len-1)
         * threadLocalHashCode是用ThreadLocal中的一个静态的AtomicInteger.getAndAdd(HASH_INCREMENT)
         * 每次创建都赋给TheadLoacl对象不同的 hashcode
         *
         */
    }

    /**
     * HandlerThread 作用？原理？
     */
    public void a8_7() {
        /*
         * HandlerThread是Thread的一个子类，也是一个线程，
         * 我们开启这个线程，调用getThreadHandler(),用这个Handler发送的消息
         *  getThreadHandler().post(new Runnable() {
         *   @Override
         *   public void run() {
         *       //在子线程中执行
         *   }
         * })
         *
         * 或者
         * new Handler(handlerThread.getLooper())这个handler发送的消息就是子线程中执行的
         * handleMessage()
         * =====================原理===================
         * 就是在子线程中Looper.prepare(), Looper.loop(),这样从
         * MessageQueue中取出的消息就在当前线程中执行了
         */

    }

    /**
     * SpareArray作用？原理?
     */
    public void a8_8() {
        /*
        ===============作用=================
        * 他是用来代替HashMap的，他的Key是int ,value是T类型
        * 因为我们用HashMap.put(1,object),那么这个1就要转化为Integer对象
        * 如果频繁调用就会产生大量的 Integer对象，会造成频繁的gc，降低效率
        *
        * ==============原理==============
        * 他有两个数组，key[] value[] ,我们用二分法查找key，
        * put的时候，查找key存不存在，如果存在index>=0  ,
        * 那么就替换value[index] = newObject;
        * 如果不存在就插入到 ~index处。
        *
        * 当然空间不足的时候，会分配一个当前size 2倍的int[]空间，然后将原来的数据copy过去
        *
        */
    }

    /**
     * IntentService作用？原理？
     */
    public void a8_9() {
        /*
         * ===============作用====================
         * 作用是开启一个带子线程的Service，我们重写onHandleIntent方法在子线程中执行我们的任务
         * ==================原理===============
         * 里面使用的HandlerThread，然后new 了一个Handler 使用了HandlerThread的Looper
         */
    }


    /**
     * Application的Context 和 Activity的Context 的区别?
     */
    public void a8_10() {
        /*
         * Application的的生命周期和应用相同，Activity的生命周期和Activity相同
         * ==============
         * Activity的Context是在Activity开启的时候创建的。是在ActivityThread
         * 的handleLaunchActivity()中，context = new ContextImpl()，然后创建Activity
         * activity.attach(context ...) 然后这个是ContextWrapper中的真正的Context
         * ==================
         * Application的创建是在ActivityThread的handleLaunchActivity中，里面有获取
         * application，如果为null,先new ContextImpl()，然后获取 Application的的类名
         * 默认是“android.app,Application”，通过反射创建实例，然后app.attach(context)
         * =================
         * 所以我们看出，这个Context是随着Activity对象，或者Application对象有着同样的生命周期
         *
         */
    }


    /**
     * Android中存储数据的几种方式？
     * SharedPreference中apply和commit的区别？
     * SP（SharedPreference）是进程同步的吗?有什么方法做到同步？
     * 使用共享文件进行线程间通讯如何保证同步？{@link}
     * https://developer.android.google.cn/guide/topics/data/data-storage.html
     */
    public void a8_11() {
        /*
         * SharedPreferences
         * 使用file，（我们可以获取到getCacheDir内部的目录） Environment.getDataDirectory()（外部目录）
         *                   getExternalFilesDir()
         *                   区别 https://developer.android.google.cn/training/data-storage/files.html
         * 数据库
         * 网络
         * ContentProvider
         *
         * FileProvider共享文件 https://developer.android.google.cn/training/secure-file-sharing/index.html
         * ===============SharedPreference中apply和commit的区别？================
         * apply是异步的，commit是同步的
         *
         * =================SharedPreference是进程同步的吗?有什么方法做到同步？============================
         * https://www.jianshu.com/p/875d13458538 使用ContentProvider代替
         *
         * ===================使用共享文件进行线程间通讯如何保证同步？========================
         * 使用FileProvider
         *
         *
         *
         */

    }

    /**
     * 说说WindowManager?PackManager?LayoutInflater?
     * --------------------------------
     * 说说WMS AMS?
     */
    public void a8_12() {
        /*
         * =================说说WindowManager?PackManager?LayoutInflater?============================
         * 很多服务都是在ContextImpl中的静态方法块，中初始化的，是直接new出来的服务
         * 存入到静态的hashMap中，所以我们context.getSystemService(key)就是取出服务实例
         * 这些服务都是每个app都有一个的，然后所有app的服务都是与系统中WMS AMS建立联系
         * =============================
         * 最新的sdk已经放到了android/app/SystemServiceRegistry.java中,但是代码都是一样
         * 在静态代码块中注册（向hashmap加入）各个服务（基本都是new出来的，然后内部用binder
         * 和系统的服务建立联系）
         *
         *
         */
    }

    /**
     * ListView原理？RecycleView原理?
     */
    public void a8_13() {
        /*
         * 本质上就是在layout的时候，遍历子view，然后子view.measure,layout,
         * 第一个view从顶部开始，第二个就是第一个的height开始layout，
         * 然后滑动的时候判断当前滑动的距离，判断出第一个view是否滚出屏幕
         * 如果滚出屏幕，那么根据类型回收到对应的列表中，下面判断是否有gap(空隙)
         * 如果有空隙，那么obtainView，先从缓存中取，如果没有就新创建一个
         *========================================
         * RecyclerView自动实现了使用缓存，他是如果存在缓存，就调用onBindViewHolder(Holder holder, int position)
         * 如果不存在就调用 Holder onCreateViewHolder(ViewGroup parent, int viewType)
         * ==========================
         * 他们都使用了适配器模式，数据转换为View返回
         * 和观察者模式 BaseAdapter中持有被观察者，当数据发生改变，调用notify的时候，
         * 通知观察者，这个观察者就是AdapterView中持有的 AdapterDataSetObserver对象(在ListView中初始化的)
         * AdapterDataSetObserver这个类是AdapterView的内部类。
         * 然后里面调用了requestLayout()
         * ===============================
         * 其实listview主要的作用就是在 layoutChildren中，里面调用fillUp fillDown
         * 然后开始layout 一个个item，其实原理有点像LinearLayout，确定了layout后left top...等
         * 我们ListView的画布就会剪裁那段画布给他
         *
         */
    }


    /**
     * AndroidManifest的作用与理解?
     */
    public void a8_14() {
        /*
         * 1.主要是在app启动的时候，PMS会遍历解析这个文件，然后将组件注册到PMS中，当我们开启Activity或者发送广播
         * 都会去这里面查找符合Intent规则的组件并启动
         * 2.安装app声明所需要的权限
         * 3.定义应用名称，图标
         */
    }

    /**
     * 为什么子线程不能更新UI？在什么地方子线程能更新UI?
     * https://blog.csdn.net/xyh269/article/details/52728861
     */
    public void a8_15() {
        /*
         * 因为更新UI后再刷新的时候(调用view.requestLayout=>viewRoot.requestLayout->checkThread())，
         * 会判断这个方法是不是和ViewRootImpl中的thread一样，这个thread是
         * 在ViewRootImpl的构造方法被赋值的thread= Thread.currentThread() 所以更新UI的线程要和
         * 创建ViewRootImpl的线程一致，否则抛出异常。
         * 而我们知道ViewRootImpl,是在onResume之后再WindowManager将视图添加到WMS中的时候创建的。
         * 所以在onResume之前我们getViewRoot()的时候为null,所以不会执行viewRoot.requestLayout
         * 所以就不会抛出异常
         *
         */
    }

    /**
     * ANR产生的原因是什么？
     * ------------------------
     * ANR定位和修正
     * <p>
     * 基础：https://developer.android.google.cn/topic/performance/vitals/anr.html#detect_and_diagnose_problems
     * 深入源码：http://www.bijishequ.com/detail/569457?p=
     * 深入源码 http://maoao530.github.io/2017/02/21/anr-analyse/
     */
    public void a8_16() {
        /*
         ** Activity在生命周期中阻塞超过5秒就会提示anr，broadcastReceiver 是10秒，service是20秒
         * ActivityManagerService中定义了 Activity和broadcastReceiver的超时时间
         * ActiveServices中定义了服务的超时时间
         *
         * 触发anr的原理就是在执行Activity的生命周期之前，AMS会发送一个Handler,延时5秒
         * 然后执行Activity的生命周期的方法，执行完成后，取消Handler中的超时消息
         * 如果超过5秒，回执行相应的超时处理方法，比如Activity超时会弹出弹窗
         * ==================ANR定位和修正------------------------------
         * 然后将堆栈信息记录在data/anr/trace.txt中
         */
    }


    /**
     * oom是什么？
     * 什么情况导致oom？
     * android 每个应用能申请多少内存？ https://zhuanlan.zhihu.com/p/27269803
     * 有什么解决方法可以避免OOM？
     * Oom 是否可以try catch？为什么？
     */
    public void a8_17() {
        /*===============oom是什么？什么情况导致oom？==============
         * 内存溢出，是因为我们申请的内存超过jvm可分配内存的最大值，
         * 我们申请内存前会判断当前内存够不够，如果不够，那么触发gc，
         * gc后依然不够，那么抛出oom
         * =====================android 每个应用能申请多少内存？=======
         * Runtime.getRuntime().maxMemory() 获取app能申请的最大内存
         * 一般初始化的时候分配16m内存，一般最多是100m+ ，如果在AndroidManifest.xml
         * 配置android:largeHeap="true" 可能能分配到512m内存
         * 每个手机的这个配置在/system/build.prop 文件中
         * dalvik.vm.heapsize=36m
         *   dalvik.vm.heapstartsize=8m    ----起始分配内存
         *   dalvik.vm.heapgrowthlimit=192m ---- 一般情况app申请的最大内存 dalvik.vm.heapsize=512m   ---- 设置largeheap时，App可用的最大内存dalvik.vm.heaptargetutilization=0.75  ---- GC相关
         *   dalvik.vm.heapminfree=512k
         *   dalvik.vm.heapmaxfree=8m     ----- GC机制相关
         * ======================有什么解决方法可以避免OOM？=============
         * 预防，我们提前对app做性能测试，观察app内存变化情况，做出优化
         * 加载大图可能导致oom，所以要缩放
         * ==================Oom 是否可以try catch？为什么？===========
         * 不可以，因为这是jvm终止进程，他是一个Error类型的错误，是不可修复的
         */
    }


    /**
     * 内存泄漏是什么？
     * 什么情况导致内存泄漏？
     * 内存泄露的解决方法?
     * 如何防止线程的内存泄漏？
     */
    public void a8_18() {
        /*
        =============内存泄漏是什么？===============
        * 当我们要回收的对象无法进行回收的时候，这种叫内存泄漏
        * ================什么情况导致内存泄漏？======================
        * 静态变量持有要回收对象的引用
        * 内部类持有要回收对象的引用（因为内部类默认持有外部类的引用）
        * 非静态Handler发送延时消息，因为非静态Handler持有外部类（Activity）引用，而msg持有handler，MessageQueue持有msg
        * ==============内存泄露的解决方法?=========================
        * 编码：养成良好的编码习惯，我们编码可以用静态的Handler来发送消息，发送的msg要在页面退出时清空
        * 检测，我们可以用Android自带的内存检测，开启多个页面然后关闭，点击强制GC，然后看哪个页面还存留（1个或者多个）
        *       那么这个时候发生了内存泄漏
        * 定位：我们可以用观察代码的形式来判断，比如退出页面，线程没有关闭，msg没有清空，或者有静态变量引用Activity
        *       还可以用mat，dump heap，然后转化一下文件，然后mat打开，找到对应的类，然后找到最短gc路径，看哪个变量持有Activity，
        *       这里就是泄漏的地方了
        *
        * ==============================如何防止线程的内存泄漏？=============================
        * 1.及时关闭线程
        * 2.使得线程持有Activity的弱引用
        *
        */
    }

    /**
     * LruCache作用，原理？{@link android.util.LruCache}
     * DiskLruCache作用，原理？
     */
    public void a8_19() {
        /*
         * ===========LruCache作用，原理？=========================
         * Least Recent Used 最近最少使用，意思就是将最近使用的缓存起来（加入头部）
         * 最少使用的淘汰出去（从尾部去除）
         * 原理就是里面使用了LinkedHashMap，且设置为accessOrder=true,表示按访问顺序访问
         * 他里面有一个maxSize的设置，如果put时超过那么从队列尾出队列。
         *
         * 每新加入一个元素，会获取他的大小，和之前的大小相加，
         * 判断是否超过最大值，如果超过，那么从header移出LinkedHashMap
         *
         * 获取一个元素，从map中获取，如果没有命中，那么调用create方法来创建
         * 默认实现是返回null;
         * =================DiskLruCache=====================
         * 他的原理和LruCache差不多，只是写入读取的时候是从磁盘中读取的
         * 他里面也用LinkedHashMap来保存缓存文件列表（里面持有文件的大小信息）
         * https://github.com/JakeWharton/DiskLruCache/blob/master/src/main/java/com/jakewharton/disklrucache/DiskLruCache.java
         */

        //缓存总长度最大为30的字符串
        LruCache<Long, String> lruCache = new LruCache<Long, String>(30) {
            @Override
            protected int sizeOf(Long key, String value) {
                return value.length();
//                return super.sizeOf(key, value);
            }

            @Override
            protected void entryRemoved(boolean evicted, Long key, String oldValue, String newValue) {
                super.entryRemoved(evicted, key, oldValue, newValue);
                //这个是cache满了（或者key替换了新的entry） 移除的回调
            }

            @Override
            protected String create(Long key) {
                //当从缓存中没有获取到，则调用这个方法创建Entry，默认返回null
                return super.create(key);
            }
        };
        String aa = lruCache.put(1L, "aa");//返回被替换的entry
        String s = lruCache.get(1L);
    }


    /**
     * Android线程有没有上限？
     * 线程池有没有上限？
     */
    public void a8_20() {
        /*
         * 有上限，如果过多会导致 StackOverflowError（这个是C层创建线程的时候会有判断？？）
         * 最多好像是1024个？？？实践的到1976
         * ====================线程池有没有上限？====================
         * 应该是和线程的数量一致？
         *
         */
//        Executors.newFixedThreadPool()
    }


    /**
     * App 是如何沙箱化，为什么要这么做?
     */
    public void a8_21() {
        /*
         *
         *
         * */
    }

    /**
     * 说说Android能获取到那些存储目录？
     */
    public void a8_22(Context context) {
        /*
         * Context的方法
         * getExternalCacheDir    /storage/emulated/0/Android/data/com.liyafeng.hotfix/cache
         * getExternalFilesDir    /storage/emulated/0/Android/data/com.liyafeng.hotfix/files
         * getFilesDir           /data/data/com.liyafeng.hotfix/files
         * getCacheDir           /data/data/com.liyafeng.hotfix/cache
         *
         * Environment的方法
         * getDataDirectory                      /data
         * getDownloadCacheDirectory             /cache
         * getExternalStorageDirectory           /storage/emulated/0  （sd卡的根目录）
         * getRootDirectory                      /system
         */

        File externalCacheDir = context.getExternalCacheDir();
        Log.i("test", "getExternalCacheDir" + externalCacheDir.getAbsolutePath());
        File externalFilesDir = context.getExternalFilesDir(null);
        Log.i("test", "getExternalFilesDir" + externalFilesDir.getAbsolutePath());
        File filesDir = context.getFilesDir();
        Log.i("test", "getFilesDir" + filesDir.getAbsolutePath());
        File cacheDir = context.getCacheDir();
        Log.i("test", "getCacheDir" + cacheDir.getAbsolutePath());

        File dataDirectory = Environment.getDataDirectory();
        Log.i("test", "getDataDirectory" + dataDirectory.getAbsolutePath());
        File downloadCacheDirectory = Environment.getDownloadCacheDirectory();
        Log.i("test", "getDownloadCacheDirectory" + downloadCacheDirectory.getAbsolutePath());
        File externalStorageDirectory = Environment.getExternalStorageDirectory();
        Log.i("test", "getExternalStorageDirectory" + externalStorageDirectory.getAbsolutePath());
        File rootDirectory = Environment.getRootDirectory();
        Log.i("test", "getRootDirectory" + rootDirectory.getAbsolutePath());


    }


    /**
     * 系统启动流程是什么？（提示：Zygote进程 –> SystemServer进程 –> 各种系统服务 –> 应用进程）
     */
    public void a8_23() {
        /*
         *
         */
    }

    /**
     * 大体说清一个应用程序安装到手机上时发生了什么?/apk安装流程
     */
    public void a8_24() {
        /*
         *
         */
    }

    /**
     * 说说android枚举？为什么说它占用内存
     */
    public void a8_25() {
        /*
         * android中不建议使用枚举，因为他占用内存，应该使用@XXXDef注解来代替
         * ==============为什么说它占用内存======================
         * 我们定义一个枚举
         * public enum Animal{DOG ,CAT}
         * 经过javac编译，然后javap反编译，看结果
         * 看到Animal extends java.lang.Enum<Animal>
         *  {
         *   public static final Animal CAT;
         *   public static final Animal DOG;
         *   ...
         *  }
         * 所以一个枚举的元素实际上就是一个类
         * 所以一个类占用的空间肯定比int占用的空间大的多
         *
         */
        Animal cat = Animal.CAT;
    }
    //endregion

    //region Android 架构模式
    /**
     * =====================
     * ### Android 架构模式
     * =====================
     * */

    /**
     * 说说Android最新架构 Architecture Component
     * <p>
     * https://developer.android.google.cn/topic/libraries/architecture/guide.html#recommended_app_architecture
     * 源码地址
     * https://github.com/googlesamples/android-architecture-components
     */
    public void a9() {
        /*
         * 为了更好的管理生命周期，比如横竖屏切换，数据要重新加载的问题
         * 数据加载完成后 Activity 已经销毁导致内存泄漏的问题
         * 可以不让UI Controller （Activity Fragment）不那么臃肿，这样代码可维护
         * LifecycleOwner 持有Activity或者Fragment的生命周期
         * LiveData 负责当ViewModel获取了数据后，通知UI
         * ViewModel 为指定的UI提供数据
         * Repository 真正获取数据的仓库，里面获取MutableLiveData;
         * -------------
         * 在LiveData，setValue的时候，就会通知Activity中的观察者，然后更新UI
         * ---------
         * 其实也是MVVM的一种实现，但是加入了生命周期的管理，基于观察者模式的mvvm
         *
         */
    }


    /**
     * 说说MVC MVP MVVM 和Clean架构各自优点和区别？
     */
    public void a10() {
        /*
         *
         */
    }

    //endregion

    //region Android动画

    /**
     * 估值器和差值器的区别
     * https://blog.csdn.net/u012203641/article/details/77823949
     */
    public void a11() {
        /*
         * 一个动画 过程是从0-1（100%） 匀速完成的，这个进度定义为 fraction（百分比）
         * 差值器是重新计算这个fraction=差值器(fraction)，
         * 而估值器是计算当前百分比时，动画的属性值是多少
         *
         */
    }

    /**
     * Android动画框架实现原理?
     * https://blog.csdn.net/u012203641/article/details/77823949
     */
    public void a11_1() {
        /*
         *
         * 视图动画
         * 原理就是调用invalidate（）方法，然后在View.draw的时候getAnimation
         * 判断是否为null，如果不为null，则获取View的Transformation对象(转化对象)，
         * 这个对象持有View画布的Matrix对象，最终在Animation中的applyTransformation()
         * 方法中完成矩阵的转化，不同子类调用不同的Matrix方法。比如Rotate调用的是
         * Matrix的旋转方法，这个方法是native的。然后如果动画没完成，会接着触发invalidate
         * 就在draw中，有个more标记，如果为true，那么继续调用invalidate
         *
         * 属性动画，
         * 从start()后，里面 Choreographer.getInstance();
         * Choreographer = Coordinates the timing of animations, input and drawing.
         * 我们将属性动画的回调加入到Choreographer中，然后post一个消息，
         * 然后先调用我们属性动画的回调，调用View的setXXX来设置View的属性
         * 然后Choreographer中还添加了draw的回调，所以就会刷新UI。
         * 依然是判断时间有没有执行完，没有执行完就循环调用
         *
         */

//        ObjectAnimator.ofFloat().start();
    }

    /**
     * Android 动画原理 、底层如何给上层信号？
     */
    public void a11_2() {
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
    //endregion

    //region Android性能优化


    /**
     * 深入说说ANR
     * <p>
     * 基础：https://developer.android.google.cn/topic/performance/vitals/anr.html#detect_and_diagnose_problems
     * <p>
     * 深入源码：http://www.bijishequ.com/detail/569457?p=
     */
    public void a12() {
        /*
         * Activity在生命周期中阻塞超过5秒就会提示anr，broadcastReceiver 是10秒，service是20秒
         * ActivityManagerService中定义了 Activity和broadcastReceiver的超时时间
         * ActiveServices中定义了服务的超时时间
         *
         * 触发anr的原理就是在执行Activity的生命周期之前，AMS会发送一个Handler,延时5秒
         * 然后执行Activity的生命周期的方法，执行完成后，取消Handler中的超时消息
         * 如果超过5秒，回执行相应的超时处理方法，比如Activity超时会弹出弹窗
         * 然后将堆栈信息记录在data/anr/trace.txt中
         *
         */
    }

    /**
     * Android网络优化方案？
     */
    public void a12_1() {
        /*
         * 目的是减少传输时长
         * 1.ip直连
         * 2.实现SPDY协议（http2.0），减少tcp握手次数，
         * 3.域名收敛，将请求集中在几个域名，提高长连接的复用率
         *
         *
         */
    }

    /**
     * 说说Android内存优化？
     */
    public void a12_2() {
        /*
         * 目的就是降低内存占用率，三个方向，一个是降低正常内存使用，二是防止内存泄漏，三是使用多进程
         * ------------------降低内存使用---------------------
         * 1.问题定位，找出哪些地方申请内存过多，我们用AS自带的Android Profiler(分析器)
         * 我们先强制GC一下，然后操作app，在内存占用突然过高或者持续增长的地方，record
         * 然后生成分析报告，会显示出哪个方法中申请内存的数量，我们找到过高的地方，进行优化
         * 2.问题解决，优化的手段有：改变数据结构，使用缓存池，改变业务逻辑（比如使用观察者代替轮询）
         *
         * ------------------降低内存使用---------------------
         */
    }

    /**
     * 说说CPU优化？（如何使程序更流畅）
     */
    public void a12_3() {
        /*
         * 我们用Android Monitor可以捕获cpu执行的时间耗时（精确到方法）
         * 找出耗时的那个方法，加以优化
         *
         * 当然我们可以用sdk中的SysTrace工具来检测，这样更灵活，我们可以指定它
         * 来统计某个方法的耗时，只需要在代码中添加
         *  Trace.beginSection("lll");
         *  Trace.endSection();
         *
         * 我们可以通过修改数据结构和算法来提升计算效率，减少cpu占用时间，从而减少耗电
         * 比如我们用散列表代替数组存储数据，或者用二叉树代替链表，提升查找效率
         * 比如用SparseArray代替Map，从而减少装箱拆箱所申请的内存，减少gc次数
         *
         * */
    }


    //endregion

    //region Android 热修复/插件化

    /**
     * 说说Android 热修复
     * https://juejin.im/post/5a0ad2b551882531ba1077a2 (Dex插庄源码实现原理)
     * https://www.jianshu.com/p/704cac3eb13d（热修复、插件化）
     * https://yq.aliyun.com/articles/231111?utm_content=m_34179 (阿里的文章，各种热修复原理介绍)
     * DexPathList.java
     * http://androidxref.com/8.0.0_r4/xref/libcore/dalvik/src/main/java/dalvik/system/DexPathList.java
     * <p>
     * QQ空间15年写的热修复原理文章
     * https://mp.weixin.qq.com/s?__biz=MzI1MTA1MzM2Nw==&mid=400118620&idx=1&sn=b4fdd5055731290eef12ad0d17f39d4a&scene=1&srcid=1106Imu9ZgwybID13e7y2nEi#wechat_redirect%20%20%20da
     * <p>
     * 各个热修复方案比较
     * https://www.jianshu.com/p/eec0ab6800a4
     */
    public void a13() {
        /**
         *=====================美团 robust (2016年)====================
         * https://tech.meituan.com/android_robust.html
         * 方案就是在每个方法前插入代码，判断是否修复，如果修复就执行最新的代码
         *
         *==================阿里 Andfix (2015)======================
         * 是jni的native指针替换，完成方法调用的替换
         *===================个人 Nuwa (2015)==========================
         * dex插桩的方式，将补丁dex插入得到ClassLoader中的DexPathList中的Element[]前面
         *
         * =================微信 Tinker(2016)=========================
         * 原理是patch.dex和base.dex进行二路归并，形成新的dex，App启动后加载新的dex
         *
         *
         *
         *
         *
         */

    }


    /**
     * 说说插件化
     * http://www.infoq.com/cn/articles/android-plug-ins-from-entry-to-give-up( infoq的文章，插件化历史介绍)
     * https://zhuanlan.zhihu.com/p/33017826（插件化原理介绍）
     */
    public void a13_1(Context context) {
        /*
         *
         */

        context.getResources().getDrawable(R.drawable.plugin);
    }

    /**
     * 什么是hook？
     * 什么是插桩?
     */
    public void a13_2() {
        /*
         * =================hook==============
         * 中文翻译，钩子，其实就是重写某个方法，运行的时候执行的是重写后的方法
         *
         * ================什么是插桩=========================
         * 就是aop，面向切面编程，将某段逻辑插入到一个方法的前面
         * （将自定义代码插入到原有代码的前面）
         * 比如字节码插桩，就是在编译的时候，修改.class文件，对其中的指定方法
         * 中插入代码逻辑，
         * ---------------插桩的应用--------------------------------
         * 1。无埋点数据采集，不在代码中写埋点逻辑，在编译的时候讲埋点代码插入到指定方法中
         * （比如点击事件，activity的生命周期）
         *
         * 2。在热修复中解决预校验问题（如果一个类引用的类都在同一个dex下，那么这个类就会
         * 被打上预校验标记，这是Android优化为odex中做的，热修复中将他引用的类可能是patch.dex中的类，
         * 所以系统就会报错，
         * 导致热修复无效），解决方法就是创建一个新的dex，将原有dex中所有类的构造函数中
         * 加入对这个类的引用，那么原有dex中的类就不会被打上预校验的标记。
         *
         * 3.aop的一种实现方式，通过编译器批量对方法中插入相同的自定义代码
         *
         * -------------------如何插桩------------------------
         * 第三方库实现对class文件进行插桩，https://github.com/BryanSharp/hibeaver
         * （使用asm库插桩）
         * http://iceanson.github.io/Android-ASM%E6%8F%92%E6%A1%A9%E5%88%9D%E6%AD%A5%E5%AE%9E%E7%8E%B0
         *javassit和asm都是对字节码文件进行操作的类库
         *
         * （插桩方法讲解，很详细）
         * https://mp.weixin.qq.com/s?__biz=MzUxMzcxMzE5Ng==&mid=2247488304&amp;idx=1&amp;sn=6ab9a2cccbf3653d97e3ac1bde3a3794&source=41#wechat_redirect
         *
         */
    }


    //endregion


    //region jni/ndk/Binder机制


    /**
     * binder是什么？
     * Linux进程间通讯机制有哪些？Android为什么用binder?
     * aidl是是什么？如何使用？原理是什么？
     */
    public void a14(Context context) {
        /*
         * ==================binder是什么？===============
         * https://github.com/xdtianyu/SourceAnalysis/blob/master/Binder源码分析.md
         * Binder机制是Android系统进程间通讯的基础
         * 他采用C、S架构，客户端bindService，获取到远程服务的代理类
         * 然后客户端调用binder的一个代理类，里面封装好数据，调用native
         * android_util_Binder.cpp的transact()来处理数据，调用BpBinder,cpp的transact()
         * 数据通过kernel层/dev/binder来通知服务端的BBinder，然后调用onTransact()
         * 来通知java层的服务，根据封装的数据来调用服务端响应的方法。这样就完成的进程间的
         * 一次通信
         *   那么我们如何识别要调用那个服务，就是通过ServiceManager来进行判断，
         * 它相当于一个路由，当有一个远程服务启动的时候，服务会在SystemServer中进行注册
         * 然后我们通过intent中的标识，在ServiceManager中来查找注册的服务，找到相应的服务
         * 通过BBinder来调用他的方法。
         *
         * 我们和AMS通信 也是通过 ServiceManager 中注册的AMS来进行跨进程通信
         *
         * AMS等一些其他系统服务都在Zygote进程中运行
         * 在我们系统启动的时候 创建，然后在ServiceManager中注册
         * =====================？？？==============
         * SystemService是每个进程都有的吗？
         * ServiceManager和 SystemService有什么关系？
         *
         * =====================Linux进程间通讯机制有哪些？Android为什么用binder?======================
         * https://www.ibm.com/developerworks/cn/linux/l-ipc/index.html（linux进程通信介绍）
         * https://www.zhihu.com/question/39440766?sort=created（Android为什么使用binder）
         *
         * socket通信，管道，共享内存
         * 但是 socket通信，管道 要将数据拷贝2次，共享内存不安全
         *
         * 而binder是安全（他使用Uid来标识进程，这个uid是android在，使得服务端可以判断请求是否安全），
         * 高效的（只拷贝数据一次）
         *
         *
         */

        context.getResources().getDrawable(R.drawable.binder_native_stack);
    }

    //endregion

    //region Android 音视频

    /**
     * 说说SurfaceView
     * {@link android.view.SurfaceView}
     */
    public void a15() {
        /*
         * 他继承自View,有自己专有的Surface对象，在子线程中渲染，可以执行
         * 频繁的绘制操作
         *
         */
    }
    //endregion


    //region Android6.0/7.0/8.0/9.0新特性
    //https://developer.android.google.cn/about/versions/oreo/ 这里是每个版本的变化

    /**
     * 最新的JetPack使用
     * https://developer.android.google.cn/jetpack/docs/
     */
    public void a16() {
        /*
         *
         */
    }

    /**
     * android 9.0新特性
     * https://developer.android.google.cn/preview/features
     */
    public void a16_0() {
        /*
         * 1.支持 Wi-Fi Round-Trip-Time (RTT)协议，从而支持室内定位
         * 2.全新的 DisplayCutout 类支持刘海屏
         * 3.新的旋转模式，为了防止误旋转，当旋转的时候，用户可以选择系统栏
         * 上的旋转按钮来进行旋转，而不是自动旋转
         *
         *
         */
    }

    /**
     * 说说android8.0新特性(o-奥利奥)
     * https://developer.android.google.cn/about/versions/oreo/android-8.0
     */
    public void a16_1() {
        /*
         * 1.添加了画中画模式，picture in picture
         * android:supportsPictureInPicture="true"来使得页面支持画中画
         * 通过Activity.enterPictureInPictureMode(PictureInPictureParams args) 开启画中画页面
         * arg接收配置参数
         * 2.可下载的字体，support包中提供一个下载字体的框架，我们只需要配置好即可从
         * 网络下载字体，从而减少apk的体积
         * 3.多屏显示支持，我们可以指定页面显示在哪个屏幕上
         * 4.使用 SYSTEM_ALERT_WINDOW 权限的应用 无法在其他应用上弹窗
         * 除非是有 TYPE_APPLICATION_OVERLAY 的新window类型
         * 5.权限，8.0前，如果我们请求一个权限，那么权限组中其他注册的权限也一同授予
         * 8.0后则不会，会等到下次使用到组中其他权限的时候才去请求，只不过不会给用户提示
         *
         */
    }

    /**
     * 说说android7.0新特性  (n-牛轧糖)
     * https://developer.android.google.cn/about/versions/nougat/android-7.0
     */
    public void a16_2() {
        /*
         * 1.多窗口支持
         * 2.改进的低耗电模式，6.0中是当手机静止的时候延迟app的cpu使用和网络使用
         * 现在是在运动的情况下也会有限制
         * 3.改进的SurfaceView，SurfaceView 类可减少屏幕合成对电池的消耗，因为它是在专用硬件中合成，
         * 与应用窗口内容分隔开。因此，它产生的中间副本少于 TextureView。
         *
         */
    }
    //endregion
}
