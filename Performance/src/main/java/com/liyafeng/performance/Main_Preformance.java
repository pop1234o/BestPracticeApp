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
     * adb shell top -d 1 | grep com.xxx.xxx
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
     * 瘦身前  21.6
     * 1.删除无用资源  20.9
     * 2.删除中文外的其他系统语言资源   20.2
     * 3.图片格式用webp代替png lottie中图片也可以用webp 19
     * 4.压缩音频和gif图片  18.5
     * 5.覆盖第三库的大图（本地放一套文件名一样的图片会覆盖掉arr中的图片）18.3
     * 6.优化asset里的动画图片  17.9
     * 7.AndResGuard进行混淆资源 16.4
     *
     *
     * -----图片xml代替方案------
     * xml占位远程加载，按需加载
     *
     * ------插件化 ----------
     *
     * ----so远程加载------
     *
    * */
    void fun1(){}


    /**
     * ============ app稳定性保障 / 降低崩溃率 /降级方案=============
     *
     * ----------线上apm---------------
     * APM 全称 Application Performance Management & Monitoring (应用性能管理/监控)
     * 线上apm目的就是发现用户的使用体验问题，便于及时修改
     * 一、监控线上APP的质量，对崩溃和异常数据进行分析，这是APM系统最大的作用。通过这些数据来了解APP线上用户在使用时是否存在问题。
     * APM系统性能监控的几个维度
     * 内存 CPU APP启动时间 UI性能  耗电量 网络性能  用户的行为路径
     * 关键业务的耗时
     *
     *
     * ------降级方案-----------
     *
     * ----------灰度接口------
     * 当 App 启动或从后台切换到前台时
     * 客户端询问后端是否有可用的灰度版本，若有可用的灰度版本，则客户端以弹窗的方式提示用户可以更新。
     * 灰度用户量可以通过后端开关开启的时间长度来控制，应用内升级的速度很快，很容易就能获得大量灰度用户。
     * 后端开关开启时，用户也可以通过点击「设置 -> 检查更新」升级到灰度版本。
     *
     * 灰度版本号可以是10 ，没问题发版后改成 15，这样版本名称一样，应用市场也会显示更新
     *
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
     * ---日志回捞------
     * 日志可以是业务日志，也可以是性能日志
     * 利用服务端主动搜索信息或打开开关做监控，解到更详细的性能信息来排查问题
     *
     * ---- 热修复 -----
     *
     * -------异常捕获---------
     * https://github.com/android-notes/Cockroach
     * 防止主线程
     *
     * -------自动化测试平台对接-------
     *
     *
     *
     */
    void fun2(){}


    /**
     * ======app问题排查---app关键路径日志埋点========
     * 目的是为了线上用户出了问题，比如登录失败，方便开发人员通过日志快速定位问题
     * 要不你本地也复现不了，用户怎么操作的你也不知道，只能通过关键路径日志来排查问题
     *
     * 所以埋点的地方很关键，通过日志你必须能定位到，用户的代码走了哪些逻辑，
     * 用户可能的操作是一个树状结构，你需要在关键的分叉点埋日志
     *
     *
     */
    void fun2_0(){}


    /**
     * 谷歌性能优化文档
     * https://developer.android.google.cn/topic/performance
     *
     * -----oom优化----
     *
     *
     * ------ 卡顿优化/掉帧现象 ------
     * 系统内置的GPU profiling工具来发现
     *
     *
     *
     */
    void fun2_1(){}

    /**
     * ========app启动时间优化===========
     * （历时1年，上百万行代码！首次揭秘手淘全链路性能优化（上））
     * https://mp.weixin.qq.com/s?__biz=MzAxNDEwNjk5OQ==&mid=2650403370&idx=1&sn=b4297b138eb7f73c95a6279c3458f025&chksm=83953a32b4e2b3247fc18cbee08a2682d8b09720a1c5fef0c36257ae92b1e201cb1ad3125455&mpshare=1&scene=1&srcid=#
     *
     * Android App 启动优化全记录
     * https://androidperformance.com/2019/11/18/Android-App-Lunch-Optimize/
     *
     * 都9102年了，Android 冷启动优化除了老三样还有哪些新招？
     * https://mp.weixin.qq.com/s?__biz=MzAxMTI4MTkwNQ==&mid=2650829097&idx=2&sn=e59841d4b1ed7e12a30e29ec51072d70&chksm=80b7a5b7b7c02ca184e0c06289d90823d589e738c55712318875f51e4aeb8646294b8d426299&mpshare=1&scene=1&srcid=&sharer_sharetime=1571275213308&sharer_shareid=60bd7acea7881a97fbf9a6126d3e88d3#rd
     *
     * 应用启动时间 - 谷歌官方建议
     * https://developer.android.google.cn/topic/performance/vitals/launch-time
     *
     *
     * ====== 启动优化老三样 ======
     * 1. 将启动页主题背景设置成闪屏页图片   视觉优化，消除启动黑白屏
     * 2. 主页面布局优化
     * 1）通过减少冗余或者嵌套布局来降低视图层次结构
     * 2）用 ViewStub 替代在启动过程中不需要显示的 UI 控件
     *
     * 3. Application 和 主 Activity 的 onCreate 中异步初始化某些代码
     * 因为在主线程上进行资源初始化会降低启动速度，所以可以将不必要的资源初始化延迟，达到优化的效果。
     * 但是这里要注意懒加载集中化的问题，别用户启动时间快了，但是无法在界面上操作就尴尬了。
     *
     *
     *
     *
     * ======常规手段 ========
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
     * ===================
     * 控制线程数量 – 线程池
     * 检查线程间的锁 ，防止依赖等待
     * 使用合理的启动架构
     * 微信内部使用的 mmkernel
     * 阿里 Alpha
     * ======启动的内部机制========
     * https://developer.android.google.cn/topic/performance/vitals/launch-time
     * 三种启动状态
     * 冷启动 ：app第一次安装，或者应用进程被系统清理后，需要重新创建进程，叫冷启动
     * 温启动 ：用户back退出app，此时所有Activity被销毁，但是进程还在，这时启动叫温启动
     *          还有一种app后台被回收了，此时恢复app ，进程和 Activity 需要重启，但传递到 onCreate() 的已保存的实例 state bundle 对于完成此任务有一定助益。
     * 热启动 ：用户home键将app退到后台，此时Activity对象还都存在，在热启动中，系统的所有工作就是将您的 Activity 带到前台
     *
     * -----冷启动流程-------
     *
     * 1.加载并启动应用。
     * 2.在启动后立即显示应用的空白启动窗口。
     * 3.创建应用进程。
     *
     * 上面流程是系统进程负责的流程，系统一创建应用进程，应用进程就负责后续阶段：
     *
     * 创建应用对象。 Application对象
     * 启动主线程。 MainThread线程
     * 创建主 Activity。
     * 扩充视图。 onCreate中 inflate
     * 布局屏幕。 布局 依附到windowManager上  onResume
     * 执行初始绘制。measure layout draw 完成第一次绘制，内容展示给用户
     *
     *
     *
     *
     * =======启动时间监控方法==============
     * -----通过logcat查看-----
     * 在 Android 4.4（API 级别 19）及更高版本中，logcat 包含一个输出行，其中包含名为 Displayed 的值。
     * 此值代表从启动进程到在屏幕上完成对应 Activity 的绘制所用的时间。
     * ActivityManager: Displayed com.android.myexample/.StartupTiming: +3s534ms
     * //前面是包名，后面是启动的第一个Activity的名称
     * 关闭过滤器，在logcat过滤 Displayed 即可
     *
     * 这个是系统进程来统计的启动时长
     *
     * -----通过adb命令查看--------
     * 您也可以使用 ADB Shell Activity Manager 命令运行应用来测量初步显示所用时间。示例如下：
     * adb [-d|-e|-s <serialNumber>] shell am start -S -W
     *     com.example.app/.MainActivity
     *     -c android.intent.category.LAUNCHER
     *     -a android.intent.action.MAIN
     *
     *  adb shell am start -S -W com.example.app/.MainActivity -c android.intent.category.LAUNCHER -a android.intent.action.MAIN
     *
     *  -W: wait for launch to complete
     *  -S: force stop the target app before starting the activity
     *
     *   [-a <ACTION>] [-d <DATA_URI>] [-t <MIME_TYPE>] [-i <IDENTIFIER>]
     *   [-c <CATEGORY> [-c <CATEGORY>] ...]
     *
     * -c 和 -a 为可选参数，可让您为 intent 指定 <category> 和 <action>。
     *
     * am start -S -W com.example.app/.MainActivity
     *
     * Displayed 指标和以前一样出现在 logcat 输出中
     *
     * Stopping: com.xxxx
     * Starting: Intent { act=android.intent.action.MAIN cat=[android.intent.category.LAUNCHER] cmp=com.xxx.xxx/.ui.activity.xxx }
     * Status: ok
     * LaunchState: COLD
     * Activity: com.xxx.xxx/.ui.activity.xxx
     * TotalTime: 622  这个就是上面 Displayed的值
     * WaitTime: 624
     * Complete
     * 这个时间其实和上面时间一样，
     *
     * ---------完全显示所用时间---------
     * 在有些情况下我们视图都加载出来了，但是有些数据或者图片是网络请求的，我们想看这个总时间
     * 在代码完成中调用 reportFullyDrawn() ，logcat就会打印时间
     * system_process I/ActivityManager: Fully drawn {package}/.MainActivity: +1s54ms
     * 过滤 Fully 即可
     * 这个就是从进程启动，到调用这个方法时候的时间
     *
     *
     *
     * ==========启动时间标准========
     * Android Vitals 在您的应用出现以下情况时将其启动时间视为过长：
     * 冷启动用了 5 秒或更长时间。
     * 温启动用了 2 秒或更长时间。
     * 热启动用了 1.5 秒或更长时间。
     *
     */
    void fun3(){}


    /**
     * 网络优化
     *
     *
     */
    void fun4(){}

}
