package com.liyafeng.view.other;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.SystemClock;

import com.liyafeng.view.webview.WebViewActivity;

public class Other {


    public static void main(String[] args) {

    }


    /**
     * android studio 工程目录
     * <p>
     * .gradle ——>gradle 运行以后生成的缓存文件夹
     * .idea ——>是android studio 工程打开以后生成的工作环境配置文件夹。
     * app 文件夹是application module，其中包含你的源码src、资源文件res、Assets 等必须的文件。
     * build 文件夹为编译时的缓存文件夹，你在运行了Build——>clear project后它会被删除清理掉，但是当你再次运行工程的时候它又会自动生成。
     * gradle 文件夹中包含的是gradle-wrapper.jar 文件，通过配置其中的gradle-wrapper.properties 中的distributionUrl 可以给你的项目指定需要使用的gradle 版本。
     * .gitignore 文件为git 版本控制的忽略清单（要完成标题所示的任务，就考它了）。
     * build.gradle 为project 全局的配置。里面有你gradle的Android插件版本
     * gradle.properties 为 gradle 的参数配置。
     * *.iml 文件为Android Studio / Intellij IDEA 为每一个module 生成的配置文件
     * gradlew gradlew.bat 是gradle 任务的脚本命令。
     * local.properties 是个人电脑中的环境配置，这个不要同步到代码库中给别人使用。
     * settings.gradle 文件中可指定project 目录中的文件夹为gradle的module
     * ---------------------
     * 作者：张庚
     * 来源：CSDN
     * 原文：https://blog.csdn.net/watermusicyes/article/details/50348967
     * 版权声明：本文为博主原创文章，转载请附上博文链接！
     */
    void a1() {
    }


    /**
     * Timer的两种执行方式
     * <p>
     * schedule - fixed-delay  固定延时，如果一次执行因为gc或者退到后台 而延时了，那么下一次执行也相对延时，
     * scheduleAtFixedRate - fixed-rate 固定速率，如果一次延时了，那么下一次也在这个绝对时间立即执行（所有执行都是相对第一次执行的时间，
     * 而不是相对前一次）
     * <p>
     * ================alarmManager方式=================
     */
    void a2(Context context) {

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent();
        intent.setClass(context, WebViewActivity.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_NO_CREATE);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            alarmManager.setExactAndAllowWhileIdle(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime(), pendingIntent);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            alarmManager.setExact(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime(), pendingIntent);
        } else {
            long TIME_INTERVAL = 1000;
            alarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime(), TIME_INTERVAL, pendingIntent);
        }

    }


    /**
     * PendingIntent ==
     * <p>
     * https://www.kancloud.cn/digest/androidframeworks/127784
     * <p>
     * <p>
     * PendingIntent 的使用场景有三个：
     * <p>
     * 使用 AlarmManager 设定闹钟
     * 在系统状态栏显示 Notification
     * 在桌面显示 Widget
     * <p>
     * ========获取PendingIntent对象========
     * // 获取 Broadcast 关联的 PendingIntent
     * PendingIntent.getBroadcast(Context context, int requestCode, Intent intent, int flags)
     * <p>
     * // 获取 Activity 关联的 PendingIntent
     * PendingIntent.getActivity(Context context, int requestCode, Intent intent, int flags)
     * PendingIntent.getActivity(Context context, int requestCode, Intent intent, int flags, Bundle options)
     * <p>
     * // 获取 Service 关联的 PendingIntent
     * PendingIntent.getService(Context context, int requestCode, Intent intent, int flags)
     * <p>
     * =======================
     * //如果新请求的 PendingIntent 发现已经存在时，取消已存在的，用新的 PendingIntent 替换
     * int FLAG_CANCEL_CURRENT
     * <p>
     * //如果新请求的 PendingIntent 发现已经存在时，忽略新请求的，继续使用已存在的。日常开发中很少使用
     * int FLAG_NO_CREATE
     * <p>
     * //表示 PendingIntent 只能使用一次，如果已使用过，那么 getXXX(...) 将会返回 NULL
     * //也就是说同类的通知只能使用一次，后续的通知单击后无法打开。
     * int FLAG_ONE_SHOT
     * <p>
     * //如果新请求的 PendingIntent 发现已经存在时, 如果 Intent 有字段改变了，这更新已存在的 PendingIntent
     * int FLAG_UPDATE_CURRENT
     */
    void a3() {

    }


    /**
     * android查看apk的内容，直接后缀名改为.zip 解压即可
     */
    void a4() {
    }


    /**
     * Android 实用插件
     * GsonFormat 自动根据json生成entity
     * findviewbyid
     */
    void a5() {
    }


    /**
     * vivo 解析软件包时出现问题
     * 设置里吧instant run 关闭
     */
    void a6() {
    }


    /**
     * https://developer.android.google.cn/studio/build/manifest-merge
     * tools:replace="android:supportsRtl,android:allowBackup"
     * 设置高优先级
     */
    void a7() {
    }


    /**
     * ADB 安装 INSTALL_FAILED_TEST_ONLY 问题探究 （vivo手机上）
     * <p>
     * https://zhuanlan.zhihu.com/p/32347983
     * <p>
     * as运行的安装命令
     * <p>
     * // 把当前的APK推送到手机的 /data/local/tmp/文件夹下面
     * $ adb push E:\asworkspace\ArpTets\app\build\outputs\apk\debug\app-debug.apk /data/local/tmp/com.example.wule.arptets
     * // 通过使用pm进行apk安装，注意添加参数 -t
     * $ adb shell pm install -t -r "/data/local/tmp/com.example.wule.arptets"
     * <p>
     * -t代表允许安装测试
     * <p>
     * https://developer.android.google.cn/studio/command-line/adb.html
     * <p>
     * <p>
     * Run 按钮生成apk带有testOnly属性，意味着apk只能通过as调试安装。
     * 如果想生成adb install 安装的apk，选择Build > Build APK.
     * 生成的apk 在build/output/debug 中，然后adb install xx.apk即可
     * <p>
     * 问题的根源在于manifest中有 testOnly=true 属性，而点击run按钮生成的apk都是true,即使你设置的false
     * <p>
     * =========adb shell pm=======
     * https://blog.csdn.net/mmk1992/article/details/56482610
     * —pm（Package Manager），这个命令主要用于获取和安装在 Android 设备上的应用信息
     * <p>
     * 命令行下输入adb shell pm即可获得关于pm的用法帮助
     *
     *
     * ========================
     * run构建出来的包（output/apk/xx.apk）testOnly=true 如果你直接传到手机安装是安装不上的
     * Android studio 通过上传安装包，设置 adb shell pm install -t -r 来安装的，所以能安装上
     *
     * 或者我们选择Build > Build APK. 这样构建出来的包就能安装了 （目录也是 output/apk/xx.apk）
     *
     *
     *
     */
    void a8() {
    }


    /**
     * https://blog.csdn.net/dangnianmingyue_gg/article/details/72723053
     * scrollbar样式设置
     * android:fadeScrollbars="false"
     * android:scrollbarStyle="insideInset"
     * android:scrollbarThumbVertical="@drawable/shape_bar_inner"
     * android:scrollbarTrackVertical="@drawable/shape_bar_outer"
     * android:scrollbars="vertical"
     * <p>
     * <!-- 2.定义滚动条的大小，垂直时指宽度，水平时指高度 -->
     * android:scrollbarSize="4dp"
     * <p>
     * 其中，scrollbaTrackxxx、scrollbarThumbxxx可以使用：
     * <p>
     * Shape自定义 Drawable
     * 图片
     * .9.png
     *
     * @color/xxx的方式使用颜色值 不可以直接使用#xxxxxx颜色值
     * <p>
     * android:scrollbarStyle
     * 可以定义滚动条的样式和位置，可选值有insideOverlay、insideInset、outsideOverlay、outsideInset四种
     * <p>
     * insideOverlay	默认值，表示在padding区域内并且覆盖在view上
     * insideInset	表示在padding区域内并且插入在view后面
     * outsideOverlay	表示在padding区域外并且覆盖在view上
     * outsideInset	表示在padding区域外并且插入在view后面
     */
    void a9() {
    }


    /**
     * =======SeekBar========
     * http://www.runoob.com/w3cnote/android-tutorial-seekbar.html
     *
     * <p>
     * <SeekBar
     * android:id="@+id/video_progress"
     * android:layout_width="253dp"
     * android:layout_height="15dp"
     * android:layout_marginLeft="5dp"
     * android:layout_marginRight="5dp"
     * android:max="0"
     * android:progress="0"
     * android:progressDrawable="@drawable/bg_progress_bar"  这个可以是selector
     * android:thumb="@drawable/shape_round_white" />
     * <p>
     * android:splitTrack="false"
     * 这个属性 是去掉分割的背景 让 thumb空白区变透明，否则默认是主题色
     * <p>
     * android:layout_height="15dp"
     * 这个height是 bar 和thumb的最大高度，否则会被切
     * <p>
     * android:maxHeight="11dp"这个设置的是bar的高度
     *
     */
    void a10() {
    }


    /**
     * https://www.kancloud.cn/kancloud/android-tutorial/87166
     *
     * android:numStars：显示多少个星星，必须为整数
     * android:rating：默认评分值，必须为浮点数
     * android:stepSize： 评分每次增加的值，必须为浮点数
     *
     * 除了上面这些，还有两种样式供我们选择咧，但是不建议使用，因为这两种样式都好丑... 他们分别是：
     * style="?android:attr/ratingBarStyleSmall"
     * style="?android:attr/ratingBarStyleIndicator"
     *
     * ——事件处理： 只需为RatingBar设置OnRatingBarChangeListener事件，然后重写下**onRatingChanged()**方法即可！
     *
     * ratingbar_full.xml:
     *
     * <?xml version="1.0" encoding="utf-8"?>
     * <layer-list xmlns:android="http://schemas.android.com/apk/res/android">
     *     <item android:id="@android:id/background"
     *         android:drawable="@mipmap/ic_rating_off1" />
     *     <item android:id="@android:id/secondaryProgress"
     *         android:drawable="@mipmap/ic_rating_off1" />
     *     <item android:id="@android:id/progress"
     *         android:drawable="@mipmap/ic_rating_on1" />
     * </layer-list>
     *
     *
     * 设置样式
     *  <item name="android:progressDrawable">@drawable/ratingbar_full</item>
     *         <item name="android:minHeight">24dip</item>
     *         <item name="android:maxHeight">24dip</item>
     *
     *
     */
    void a11(){}


    /**
     * ========EditText maxEms和 maxLength ============
     * http://blog.csdn.net/JavaLive09/article/details/38661773
     * https://blog.csdn.net/beiminglei/article/details/9317997
     *
     * maxLength是字符串长度
     *
     * An em is a unit in the field of typography
     * em是一个印刷排版的单位，表示字宽的单位。 em字面意思为：equal M   （和M字符一致的宽度为一个单位）简称em。
     *  ems是em的复数表达。
     *
     *
     *
     */
    void a12(){}
}
