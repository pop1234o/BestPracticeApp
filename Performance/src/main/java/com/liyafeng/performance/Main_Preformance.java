package com.liyafeng.performance;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;

public class Main_preformance {

    /**
     * 1,千万不要在循环中使用+操作字符串，会产生大量String对象，要用StringBuilder
     * 2.不要在循环中使用HasMap来存储或者读取，key是int ，long 的类型，否则大量的装箱
     * 会产生大量的Integer， Long类型，占用内存，要用SparseArray代替
     * 3.不要在循环中创建大量对象，我们可以用缓冲池的形式来处理
     * 4.尽量避免轮询（自旋），可以用观察者模式来解决
     * <p>
     * <p>
     *
     * https://www.jianshu.com/p/31b1a4aef550( Android App性能评测分析－cpu占用篇)
     * https://developer.android.google.cn/studio/profile/cpu-profiler?hl=zh-cn (使用 CPU Profiler 检查 CPU 活动)
     *
     * =================monkey 压测=============
     * https://developer.android.google.cn/studio/test/monkey.html
     * <p>
     * money有参数， 主要`用来约束包，页面，控制事件数量
     * <p>
     * adb shell ps | awk '/com\.android\.commands\.monkey/ { system("adb shell kill " $2) }'
     * <p>
     * //次数
     * adb shell monkey -p your.package.name -v 500
     * <p>
     * adb shell monkey -p com.qusukj.baoguan  -v 250000 --pct-trackball 100%
     * <p>
     * <p>
     * 停止monkey
     * adb shell ps|grep monkey返回的第一个数字就是monkey的进程号
     * 2. adb shell kill [进程号]
     * <p>
     * ========================anr=================
     * <p>
     * http://duanqz.github.io/2015-10-12-ANR-Analysis
     *
     *
     * =================查看cpu占用=========
     * adb shell top -d 1 | grep com.tal.monkey
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        StringBuilder

    }

    /**
     * 使用Android Profiler 进行内存占用分析，cpu耗时分析，网络流量分析
     * 使用MAT查看内存泄漏，还可以查看内存中大对象
     *
     *
     * */


    /**
     * GPU呈献模式分析
     * 横线代表16毫秒，（60帧每秒 ftps），一个直线代表一帧，高度代表时间
     * 一共8种颜色，从下到上
     * <p>
     * Misc Time/Vsync Delay 主线程执行的时间
     * Input Handling  ontouchEvent的执行时间
     * Animation 动画时间
     * Measure/Layout的时间
     * Draw时间
     * Sync & Upload带绘制图片的时间
     * Command Issue open gl的执行时间
     * Swap Buffers GPU处理的时间，  处理完成GPU就直接将像素块显示在屏幕上
     * <p>
     * =======================================
     * 到这我们能大概找出哪里耗时了
     * 然后我们用Android Monitor 的 cpu 中的 traceing 来查看具体的哪些方法耗时多少
     * 找出耗时来解决卡顿
     */

    void gpu() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getDrawable(R.drawable.gpu_line);
        }
    }


    /**
     *
     * resolveRtlPropertiesIfNeeded
     *
     * 如果支持
     *   val b = ((context?.applicationInfo?.flags)!! and FLAG_SUPPORTS_RTL) == FLAG_SUPPORTS_RTL;
     *
     * 默认不支持，但是引用库有一个支持那么就支持，如果我们要覆盖这个属性
     * 用这个来覆盖
     * tool:replace="android:label,android:icon"
     *
     * */
}
