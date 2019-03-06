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
     *
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
     *
     *
     */
    void a1(){}


    /**
     * Timer的两种执行方式
     *
     * schedule - fixed-delay  固定延时，如果一次执行因为gc或者退到后台 而延时了，那么下一次执行也相对延时，
     * scheduleAtFixedRate - fixed-rate 固定速率，如果一次延时了，那么下一次也在这个绝对时间立即执行（所有执行都是相对第一次执行的时间，
     *              而不是相对前一次）
     *
     *================alarmManager方式=================
     *
     *
     *
     */
    void a2(Context context){

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent();
        intent.setClass(context, WebViewActivity.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_NO_CREATE);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            alarmManager.setExactAndAllowWhileIdle(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime(), pendingIntent);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            alarmManager.setExact(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime(), pendingIntent);
        } else {
            long TIME_INTERVAL=1000;
            alarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime(), TIME_INTERVAL, pendingIntent);
        }

    }


    /**
     * PendingIntent ==
     *
     * https://www.kancloud.cn/digest/androidframeworks/127784
     *
     *
     * PendingIntent 的使用场景有三个：
     *
     * 使用 AlarmManager 设定闹钟
     * 在系统状态栏显示 Notification
     * 在桌面显示 Widget
     *
     * ========获取PendingIntent对象========
     * // 获取 Broadcast 关联的 PendingIntent
     * PendingIntent.getBroadcast(Context context, int requestCode, Intent intent, int flags)
     *
     * // 获取 Activity 关联的 PendingIntent
     * PendingIntent.getActivity(Context context, int requestCode, Intent intent, int flags)
     * PendingIntent.getActivity(Context context, int requestCode, Intent intent, int flags, Bundle options)
     *
     * // 获取 Service 关联的 PendingIntent
     * PendingIntent.getService(Context context, int requestCode, Intent intent, int flags)
     *
     * =======================
     * //如果新请求的 PendingIntent 发现已经存在时，取消已存在的，用新的 PendingIntent 替换
     * int FLAG_CANCEL_CURRENT
     *
     * //如果新请求的 PendingIntent 发现已经存在时，忽略新请求的，继续使用已存在的。日常开发中很少使用
     * int FLAG_NO_CREATE
     *
     * //表示 PendingIntent 只能使用一次，如果已使用过，那么 getXXX(...) 将会返回 NULL
     * //也就是说同类的通知只能使用一次，后续的通知单击后无法打开。
     * int FLAG_ONE_SHOT
     *
     * //如果新请求的 PendingIntent 发现已经存在时, 如果 Intent 有字段改变了，这更新已存在的 PendingIntent
     * int FLAG_UPDATE_CURRENT
     *
     *
     */
    void a3(){

    }




}
