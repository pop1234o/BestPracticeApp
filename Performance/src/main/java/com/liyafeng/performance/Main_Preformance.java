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


    /**
     * ===============Android 瘦身 /包体积优化===================
     * 瘦身前  21.6M
     * 1.删除无用资源后  20.9M
     * 2.删除中文外的其他系统语言资源后     20.2M
     * 3.图片格式用webp代替png lottie中图片也可以用webp 19M
     * 4.压缩音频和gif图片  18.5M
     * 5.覆盖第三库里的大图（本地放一套文件名一样的图片会覆盖掉arr中的图片）18.3M
     * 6.优化asset里的动画图片  17.9M
     *
     * -----图片xml代替方案------
     * xml占位远程加载，按需加载
     *
     * ------插件化 ----------
     *
     *
    * */
    void fun1(){}


    /**
     * ============ app稳定性保障 / 降低崩溃率 /降级方案=============
     *
     * ------降级方案-----------
     *
     * ----------灰度接口------
     * 当 App 启动或从后台切换到前台时
     * 客户端询问后端是否有可用的灰度版本，若有可用的灰度版本，则客户端以弹窗的方式提示用户可以更新。
     * 灰度用户量可以通过后端开关开启的时间长度来控制，应用内升级的速度很快，很容易就能获得大量灰度用户。
     * 后端开关开启时，用户也可以通过点击「设置 -> 检查更新」升级到灰度版本。
     *
     * -----内侧和灰度机制-------
     * https://zhuanlan.zhihu.com/p/39155476  知乎客户端内测和灰度方案演进
     * 邮件邀请内部员工 + 私信邀请外部用户
     * App 内邀请内部员工 + 线下活动邀请外部用户
     *
     * 如何收集反馈？
     * 用户开始使用灰度版之后，我们会通过 Fabric 收集用户遇到崩溃，用户也可以通过摇一摇将自己遇到的问题提交到我们的反馈收集平台。
     * 我们也做了一些周边工具，如崩溃监控和报警、一键提交反馈到 jira 等功能，帮助我们提高收集用户反馈的效率。
     *
     * 更高质量：提供更高质量的灰度包给到用户，把灰度当做全量发布前的一种验证手段，而不是寄希望于让用户在灰度期间发现前期没有发现的问题
     * 更少次数：减少灰度发布的次数，减少用户流失
     * 更多维度：支持给特定的机型、系统、地区、特征的用户下发灰度包，使得灰度更加精确
     *
     * 收集灰度用户意见反馈，形成改进需求方案
     *
     * --------告警机制----
     *
     * ----日志埋点------
     *
     *
     * ---- 热修复 -----
     *
     * -------异常捕获---------
     * https://github.com/android-notes/Cockroach
     * 防止主线程
     *
     *
     */
    void fun2(){}


    /**
     *
     * -----oom优化----
     *
     *
     * ------ 卡顿优化 ------
     *
     */
    void fun2_1(){}

    /**
     * ========app启动时间优化===========
     * （历时1年，上百万行代码！首次揭秘手淘全链路性能优化（上））
     * https://mp.weixin.qq.com/s?__biz=MzAxNDEwNjk5OQ==&mid=2650403370&idx=1&sn=b4297b138eb7f73c95a6279c3458f025&chksm=83953a32b4e2b3247fc18cbee08a2682d8b09720a1c5fef0c36257ae92b1e201cb1ad3125455&mpshare=1&scene=1&srcid=#
     *
     *
     * 常规手段
     * 只加载必要的模块，延迟加载等
     *
     * 通过减少执行代码从而减少执行时间的方式，叫着软优化。
     * 相对的，对于提升系统的吞吐效率，对于相同的代码用更少的执行时间完成，叫着硬优化。
     * 硬优化是面向硬件资源，包括CPU，内存，网络，磁盘 IO等的调度，减少等待时间，最大化利用硬件资源，保持系统负载在合理范围内。
     *
     * 优化我们有一个大的原则，要求基本不能影响业务需求，也就是要在不减任何业务代码的情况下进行优化。
     *
     * 降低 IO 的并发，整体的执行时间大幅降低。（防止多个任务同时并发io）
     *
     *
     *
     */
    void fun3(){}


}
