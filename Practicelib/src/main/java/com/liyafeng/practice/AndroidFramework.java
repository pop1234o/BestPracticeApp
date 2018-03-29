package com.liyafeng.practice;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.Handler;

import java.util.concurrent.Executors;

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
        * 最一开始ViewRootImpl 接收到触摸事件，然后会传递给DecorView
        * 的dispatchTouchEvent()，然后Decorview会将事件分发给子控件
        * 先判断当前View是否拦截事件，如果拦截就直接调用自己的ontouchevent
        * 如果没有拦截就依次分发给子view，直到最底层的view，在ontouchevent
        * 中返回是否消费，如果有消费下次就直接将事件传递给它，如果没有消费就依次
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
        *
        *============================View和ViewGroup分别有哪些事件分发相关的回调方法?===========================
        * onTouch ,onClick onLongClick
        *
        *
        * */

    }

    /**
     * View的渲染机制
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
        * 然后是performLayout，确定子view在父布局中的位置，left top right bottom 四个参数
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
        *  是调用了 onResume后，WindowManager.addView，将视图加入到界面上的时候，在WindowManagerGloble
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
        * https://developer.android.google.cn/topic/performance/graphics/manage-memory.html
        * 每个api版本不同，bitmap存放的位置也不同
        * 在3.0以前，bitmap的像素信息存储在native memory中，而bitmap对象存储在 Dalvik heap中
        * 所以我们必须要调用recycle（）方法来释放bitmap的像素数据（因为GC只会回收Heap）
        *
        * 3.0以后，pixel data 和 bitmap对象都存储在Dalvik heap中，这个时候我们就不必调用recycle
        * Gc会自动回收了。
        *
        * 8.0开始，pixel data存到了 native heap中
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
        *
        * =====================自定义View如何考虑机型适配?======================
        * 获取屏幕分辨率，从新进行onLayout，
        * ===========自定义View的事件如何处理？=========================
        * 重写onTouchEvent(),根据业务需求返回true，进行事件消费
        *
        */
    }


    /**
     * 计算一个view的嵌套层级?
     * */
    public void a1_9(){
        /*
        * 循环调用view.getParent
        */
    }
    //endregion

    //region Android 内存/虚拟机

    /**
     * =====================
     * ### Android 内存
     * =====================
     * */

    /**
     * 说说什么是内存泄漏，说一个典型的例子，怎么避免？
     */
    public void a2_1() {
        /*
        * 本该被回收的对象因为存在对他的强引用而没有被回收
        * Android中最典型的就是Activity对象的泄漏，比如用Handler发延时消息
        * 在Activity销毁后消息还存在队列中，但是此时Handler对象持有Activity的引用
        * 从而使Activity没有被回收，导致内存泄漏，解决方法就是用静态Handler或者在
        * onDestroy()中移除消息
        * -------
        * 还有一个例子是在Activity中使用AsyncTask，当Activity销毁时任务没有执行完
        * 因为AsyncTask持有Activity的引用，也会导致泄漏，解决方法是在onDestroy调用
        * 他的cancel方法来中断线程
        *
        */
    }

    /**
     * Android进程如何保活？系统杀掉后如何重启？为什么要保活？
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
        * =====================2==================
        * https://blog.csdn.net/xiaoli100861/article/details/50855152
        *  方向切换的时候我们会销毁后重建，当然onSaveInstanceState
        *  onRestoreInstanceState 可以用来恢复数据
        *  onPause ,onStop onDestroy onCreate onStart onResume
        * 我们在清单文件中配置不销毁
        * android:configChanges="orientation|keyboardHidden|screenSize"
        * （这个和操作系统(4.0)和targetApi(12)有关，但是最新的一般都是这样配置）
        * ================3========================
        * Dialog的出现不会调用Activity的任何生命周期，
        * 调用生命周期是ActivityManager，而dialog是通过WindowManager来管理的
        * （但是好像系统的Dialog会造成onPause???）
        *====================4=======================
        * 有无dialog，Activity的进入后台，切回前台生命周期都是这样的
        *   onPause:
        *   onSaveInstanceState:
        *   onStop:
        *   onRestart:
        *   onStart:
        *   onResume:
        *
        * ==================5=========================
        * A开启B, A-onPause B-onCreate\onStart\onResume A-onStop
        * B关闭，B-onPause A-onRestart\onStart\onResume  B-onStop\onDestroy
        * ================6================
        * 锁屏和前后台切换的生命周期相同
        * ===============7================
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
     * 进程的四种状态？内存低的时候Android系统是如何管理进程的？
     * https://developer.android.google.cn/guide/components/activities/process-lifecycle.html
     */
    public void a3_7() {
        /*
        * 1.前台进程，
        * 这个进程中有一个resume的Activity，
        * 或者一个在执行onReceive()的BroadCastReceiver ,
        * 或者有一个在执行onCreate onStart onDestroy 的Service
        *
        * 2. 可见进程，
        * 进程中有一个没有焦点的Activity，但是它可见，比如一个半透明的Activity 盖住了他
        * 有个前台的Service，通过Service.startForeground方法来显示一个Notification
        * 有系统用的独特服务，比如动态壁纸，输入法服务
        *
        * 3.服务进程
        * 进程中有startService 方式打开的Service，当前两种进程内存不够用时，将回收这个进程
        * 连续运行30分钟以上有可能会被降级，因为这有可能发生了内存泄漏而占用太多内存
        *
        * 4.缓存进程
        * 一般这种进程中有1个或者多个onStop的Activity，这个时候当内存不足时会优先回收
        * 一般优先回收的是最久没有用过的进程。
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
     */
    public void a3_10() {
        /*
        * 可以通过Activity来传递，也可以用EventBus的实现方式
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

    //endregion

    //region BroadCastReceiver

    /**
     * 广播有几种注册方式？各有什么优点？
     * https://developer.android.google.cn/guide/components/broadcasts.html#receiving_broadcasts
     */
    public void a4() {
        /*
        * AndroidManifest中静态注册，代码中动态注册
        * 优点，代码注册的优先级比较高，而且有些隐式广播只能代码中注册
        * 缺点，注册广播的Activity的页面关闭后，广播就失效了
        * 静态的优点，是随时都能接收到广播
        *
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
     */
    public void a4_2() {
        /*
        * 本地广播只能在本应用内传播，使用LocalBroadcastManager来注册和发送
        */
    }
    //endregion

    //region Service

    /**
     * 请描述一下Service 的生命周期?
     */
    public void a5_1() {
        /*
        * start方式，onCreate ,onStartCommand ,onDestroy
        * bind方式，onCreate,onBind  onUnBind ,
        * 多次start 会调用多次startCommand()
        * bind只能调用一次，否则抛异常
        * bind后调用stop无效果
        * start和bind可以不分顺序的调用
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
     * 如何通过广播拦截和abort一条短信？
     * 广播是否可以请求网络？
     * 广播引起anr的时间限制是多少？
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
     * Android  6.0的权限机制？6.0之前的权限机制？权限机制的原理是什么？
     */
    public void a8() {
        /*
         * 权限请求
         * https://developer.android.google.cn/training/permissions/requesting.html?hl=zh-cn
         * -----------------------
         * 6.0后要动态申请权限，6.0之前可以在xml中申请权限，用户在安装的时候同意所有
         * 原理是调用系统api的时候回去判断这个应用有没有被授权，如果有则执行，没有就
         * 返回null或者异常。
         */
    }

    /**
     * looper架构?
     */
    public void a8_1() {
        /*
        * Handler=》{Looper } Handler只能在有Looper的线程创建（主线程已经在初始化的时候就已经创建Looper了）
        * Looper=>{MessageQueue}
        * 在创建Handler是，获取 Looper.myLooper();里面用的ThreadLocal来保证每个线程取出各自的Looper
        *
        * Looper.prepare();//给这个线程创建一个Looper，然后放到ThreadLocal中
        * Looper.loop();//从MessageQueue中取Message，如果没有，就阻塞（用的native方法）
        * //取出来后调用 msg.target.dispatchMessage(msg);target就是Handler
        * dispatchMessage 内部调用handleMessage方法
        *
        * sendMessage();将msg加入到MessageQueue中（这是一个链表的形式存储）
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
        * 3.ActivityMangerService判断进程是否启动，没有启动则调用Process.start()来开启一个进程
        * 4.启动进程后dalvik会调用SystemServer.main()方法，这个方法中创建ActivityThread，继而创建ApplicationThread
        * 5,ApplicationThread绑定到ActivityManagerService中？？？
        * 6.ActivityManagerService执行创建Application对象,调用onCreate()，启动Activity，调用他的onCreate()
        */
        context.getResources().getDrawable(R.drawable.app_launch);
        context.getResources().getDrawable(R.drawable.app_launch_flow);
        context.getResources().getDrawable(R.drawable.app_launch_flow_2);
        context.getResources().getDrawable(R.drawable.app_launch_flow_3);
        context.getResources().getDrawable(R.drawable.app_launch_start_activity);
    }

    /**
     * 说说Android系统开机流程
     */
    public void a8_3() {
        /*
        *
        */
    }

    /**
     * Android源码下载，编译，导入Studio预览？
     * http://wl9739.github.io/2016/05/09/Android%E6%BA%90%E7%A0%81%E7%9A%84%E4%B8%8B%E8%BD%BD%E3%80%81%E7%BC%96%E8%AF%91%E4%B8%8E%E5%AF%BC%E5%85%A5%E5%88%B0Android-Studio/
     * <p>
     * http://kaedea.com/2016/02/09/android-about-source-code-how-to-read/
     */
    public void a8_4() {
        /*
        *
        */
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
        */
        new AsyncTask<Integer, Double, String>() {

            @Override
            protected void onPreExecute() {
            }

            @Override
            protected void onProgressUpdate(Double... values) {
                super.onProgressUpdate(values);
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

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {

            }
        });
    }


    /**
     * ThreadLocal作用？原理？
     */
    public void a8_6() {
        /*
        * ThreadLocal中有静态内部类，ThreadLocalMap
        * 不同ThreadLocal实例 对应不同的值。
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
     * SP是进程同步的吗?有什么方法做到同步？
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
        *
        * =================SP是进程同步的吗?有什么方法做到同步？============================
        * https://www.jianshu.com/p/875d13458538 使用ContentProvider代替
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
     * LruCache作用，原理？
     * DiskLruCache作用，原理？
     */
    public void a8_19() {
        /*
        * ===========LruCache作用，原理？=========================
        * Least Recent Used 最近最少使用，意思就是将最近使用的缓存起来（加入头部）
        * 最少使用的淘汰出去（从尾部去除）
        * 原理就是里面使用了LinkedHashMap，他里面有一个maxSize的设置，如果超过
        * 那么从队列尾出队列。
        *
        * =================DiskLruCache=====================
        * 他的原理和LruCache差不多，只是写入读取的时候是从磁盘中读取的
        * 他里面也用LinkedHashMap来保存缓存文件列表（里面持有文件的大小信息）
        * https://github.com/JakeWharton/DiskLruCache/blob/master/src/main/java/com/jakewharton/disklrucache/DiskLruCache.java
        */
    }

    
    /**
     * Android线程有没有上限？
     * 线程池有没有上限？
     * */
    public void a8_20(){
        /*
        * 有上限，如果过多会导致 StackOverflowError（这个是C层创建线程的时候会有判断？？）
        * 最多好像是1024个？？？
        * ====================线程池有没有上限？====================
        * 应该是和线程的数量一致？
        *
        */
//        Executors.newFixedThreadPool()
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
        * 差值器是重新计算这个fraction，
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

}
