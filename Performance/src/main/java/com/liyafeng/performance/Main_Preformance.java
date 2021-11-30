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
     * 缩减应用大小
     * https://developer.android.google.cn/topic/performance/reduce-apk-size
     * 缩减资源
     * https://developer.android.google.cn/studio/build/shrink-code
     *
     * ==========了解包结构=======
     * APK 包含以下目录：
     *
     * META-INF/：包含 CERT.SF 和 CERT.RSA 签名文件，以及 MANIFEST.MF 清单文件。
     * assets/：包含应用的资源；应用可以使用 AssetManager 对象检索这些资源。
     * res/：包含未编译到 resources.arsc 中的资源。
     * lib/：包含特定于处理器软件层的已编译代码。此目录包含每种平台类型的子目录，如 armeabi、armeabi-v7a、arm64-v8a、x86、x86_64 和 mips。
     * APK 还包含以下文件。在这些文件中，只有 AndroidManifest.xml 是必需的。
     *
     * resources.arsc：包含已编译的资源。此文件包含 res/values/ 文件夹的所有配置中的 XML 内容。打包工具会提取此 XML 内容，将其编译为二进制文件形式，并压缩内容。此内容包括语言字符串和样式，以及未直接包含在 resources.arsc 文件中的内容（例如布局文件和图片）的路径。
     * classes.dex：包含以 Dalvik/ART 虚拟机可理解的 DEX 文件格式编译的类。
     * AndroidManifest.xml：包含核心 Android 清单文件。此文件列出了应用的名称、版本、访问权限和引用的库文件。该文件使用 Android 的二进制 XML 格式。
     *
     * ========包结构优化插件=======
     * studio 安装 Android Size Analyzer 插件，谷歌出品
     *
     *
     * =========示例==========
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
     * -------重写控件----
     * 某些库里你只用了其中一个控件，那么就可以把代码复制出来自己写一个
     *
     * ----------移除没有的code和资源
     * 其实依赖的很多库只是用到其中几个类，然而那个库大小却有几百k ，比如 androidx 2m
     * mertial 700k
     *
     * Code shrinking是一个Android Plugin for Gradle，从您的打包的应用程序中删除未使用的资源，
     * 包括代码库中的未使用的资源。它工作在与代码缩小，这样，一旦未使用的代码已被删除，任何资源不再引用可以安全地删除。
     *
     * gradle配置
     *    release {
     *             debuggable false
     *             // 是否进行混淆
     *             minifyEnabled true
     *             useProguard true
     *             // 是否zip对齐
     *             zipAlignEnabled true
     *             // 移除无用的resource文件
     *             shrinkResources true
     *             proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
     *         }
     *
     * ===========使用proguard=====
     * 那些你不知道的 APK 瘦身，让你的 APK 更小
     * https://blog.csdn.net/vfush/article/details/52266843
     *
     * ==========gradle插件移除指定类/包======
     * https://blog.csdn.net/ifmvo/article/details/85791614 Android aar或jar中删除某个包或类
     *
     *
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
     * 阿里云日志上报
     * 本地日志存储+日志回捞
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
     * =========黑科技============
     * 支付宝 App 构建优化解析：通过安装包重排布优化 Android 端启动性能
     * https://mp.weixin.qq.com/s/79tAFx6zi3JRG-ewoapIVQ
     *
     * 08 | 启动优化（下）：优化启动速度的进阶方法 - 张绍文
     * https://time.geekbang.org/column/article/74044
     *
     * 支付宝客户端架构解析：Android 客户端启动速度优化之「垃圾回收」 4.x系统用的，过时了
     * https://mp.weixin.qq.com/s/ePjxcyF3N1vLYvD5dPIjUw
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
     * =======启动时间监控方法==============
     * -----通过logcat查看-----
     * 在 Android 4.4（API 级别 19）及更高版本中，logcat 包含一个输出行，其中包含名为 Displayed 的值。
     *
     * 此值代表从启动进程到在屏幕上完成对应 Activity 的绘制所用的时间。
     *
     * （包括了application的oncreate ，到Activity的onResume 后，绘制完成的时间）
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
     * am start -S -W com.example.app/.MainActivity (先进入adb shell状态)
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
     *
     *
     * 正常情况下点击桌面图标只启动一个有界面的Activity，此时displayStartTime与mLaunchStartTime便指向同一时间点，
     * 此时ThisTime=TotalTime。另一种情况是点击桌面图标应用会先启动一个无界面的Activity做逻辑处理，
     * 接着又启动一个有界面的Activity，在这种启动一连串Activity的情况下（知乎的启动就是属于这种情况），
     * displayStartTime便指向最后一个Activity的开始启动时间点，mLaunchStartTime指向第一个无界面Activity的开始启动时间点
     * ，此时ThisTime！=TotalTime
     *
     *
     * WaitTime 就是总的耗时，包括前一个应用Activity pause的时间和新应用启动的时间；（桌面也是一个应用）
     * ThisTime 表示一连串启动Activity的最后一个Activity的启动耗时；
     * TotalTime 表示新应用启动的耗时，包括新进程的启动和Activity的启动，但不包括前一个应用Activity pause的耗时。也就是说，
     * 开发者一般只要关心TotalTime即可，这个时间才是自己应用真正启动的耗时。
     *
     * 作者：Groffa
     * 链接：https://www.zhihu.com/question/35487841/answer/63007551
     * 来源：知乎
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * TotalTime = 启动应用创建进程时间到首页渲染出来的时间
     * WaitTime = TotalTime + 上一个应用的pause时间
     *
     *
     *
     * --完全显示所用时间---------
     * 在有些情况下我们视图都加载出来了，但是有些数据或者图片是网络请求的，我们想看这个总时间
     * 在代码完成中调用 reportFullyDrawn() ，logcat就会打印时间
     * system_process I/ActivityManager: Fully drawn {package}/.MainActivity: +1s54ms
     * 过滤 Fully 即可
     * 这个就是从进程启动，到调用这个方法时候的时间
     *
     *
     * 总结：
     * 每个应用的启动结束点都不一样，基本不会是闪屏的时候。所以一般都是应用内部自己的启动统计框架
     * 但是可以用这个来定位闪屏页之前的耗时
     * ==========代码中启动时间获取==============
     *
     * 首先在 Application 的 attachBaseContext 方法记录开始时间
     *
     * 在业务的第一个 Activity 的 onWindowFocusChanged 方法记录结束时间
     * 或者 onResume中post
     *
     * onResume 330 此时没有算上draw时间?
     * onCreate - post 427
     * onResume - post 434
     * onWindowFocusChanged - 435
     *
     * onWindowFocusChanged onResume view被addView，通过 windowManagerService，然后添加到屏幕上
     * 添加成功windowManagerService回调onWindowFocusChanged，通过handler发送消息。所以消息是
     * onResume - post 后执行，所以onWindowFocusChanged在 onResume - post后执行
     *
     *
     *
     *
     * ==========识别瓶颈/定位耗时位置=========
     *
     *
     *
     * ==========启动时间标准========
     * Android Vitals 在您的应用出现以下情况时将其启动时间视为过长：
     * 冷启动用了 5 秒或更长时间。
     * 温启动用了 2 秒或更长时间。
     * 热启动用了 1.5 秒或更长时间。
     *
     * ==============通过视频关键帧统计启动时间============
     * 怎么计算apk的启动时间？ - 技术性调整的回答 - 知乎
     * https://www.zhihu.com/question/35487841/answer/1135191974
     * stagesepx
     *
     *
     * ===========工具=====
     * Profilo is an Android library for collecting performance traces from production builds of an app.
     * https://github.com/facebookincubator/profilo
     *
     *
     */
    void fun3(){}


    /**
     * 网络优化
     *
     *
     */
    void fun4(){}

    /**
     * 编译速度优化
     *
     */
    void fun5(){}


    /**
     * ============= ProGuard 简介 =============
     * https://tech.meituan.com/2018/04/27/mt-proguard.html 插件化、热补丁中绕不开的Proguard的坑
     *
     * https://www.jianshu.com/p/da5cf53735c9 浅谈Android混淆
     *
     *
     * ProGuard是2002年由比利时程序员Eric Lafortune发布的一款优秀的开源代码优化、混淆工具，
     * 适用于Java和Android应用，目标是让程序更小，运行更快，在Java界处于垄断地位。
     * 主要分为三个模块：Shrinker（压缩器）、Optimizer（优化器）、Obfuscator（混淆器）、Retrace（堆栈反混淆）。
     *
     * Shrinker 通过引用标记算法，将没用到的代码移除掉。
     * Optimizer 通过复杂的算法（Partial Evaluation &Peephole optimization，这部分算法我们不再展开介绍）
     * 对字节码进行优化，代码优化会使部分代码块的结构出现变动。
     * Obfuscator 通过一个混淆名称发生器产生a、b、c的毫无意义名称来替换原来正常的名称，增加逆向的难度。
     * Retrace 利用mapping还原堆栈信息
     *
     * ======= Entry Point===
     * 为了确定哪些代码应该被保留，哪些代码应该被移除或混淆，
     * 需要确定一个或多个Entry Point。
     * Entry Point 经常是带有main methods,applets,midlets的classes,它们在混淆过程中会被保留
     *
     * shrink： Proguard从上述EntryPoints开始遍历搜索哪些类和类成员被使用。其他没有被使用的类和类成员会移除。
     *
     * optimize: 优化代码，非EntryPoints类会加上private/static/final, 没有用到的参数会被删除，一些方法可能会变成内联代码。
     *
     * obfuscate: 使用短又没有语义的名字重命名非EntryPoints的类名，变量名，方法名。EntryPoints的名字保持不变。
     *
     * preverify: 预校验代码是否符合Java1.6或者更高的规范(唯一一个与入口类不相关的步骤)
     *
     *
     * =============手动反混淆代码的堆栈信息
     *
     * https://www.jianshu.com/p/69857a6cb956  （Android 混淆使用入门笔记）
     *
     * app构建后
     * 在 app/build/outputs/mapping/release/目录下生成一些信息日志文件
     * dump.txt：描述APK文件中所有类的内部结构
     * mapping.txt：提供混淆前后类、方法、类成员等的对照表
     * seeds.txt：列出没有被混淆的类和成员
     * usage.txt：列出被移除的代码
     * ----------反解工具 retrace ------
     * 在<SDK_rootDir>/tools/proguard/bin/目录下
     * 命令行输入./proguardgui.sh  出现一个弹窗，选择retrace
     * 把崩溃堆栈复制进去
     * java.lang.ArrayIndexOutOfBoundsException: length=0; index=0
     * at org.xxx.xxx.c.a.a.a(SourceFile:13)
     * at android.os.Handler.handleCallback(Handler.java:808)
     * at android.os.Handler.dispatchMessage(Handler.java:101)
     * at android.os.Looper.loop(Looper.java:166)
     * at android.app.ActivityThread.main(ActivityThread.java:7529)
     * at java.lang.reflect.Method.invoke(Native Method)
     * at com.android.internal.os.Zygote$MethodAndArgsCaller.run(Zygote.java:245)
     * at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:921)
     * 点击retrace就能反解代码了。。。
     *
     * =========mapping文件格式介绍=========
     *
     *
     * 类映射,特征：映射以:结束。
     * 字段映射，特征：映射中没有()。
     * 方法映射，特征：映射中有()，并且左侧的拥有两个数字，代表方法体的行号范围。
     * 内联，特征：与方法映射相比，多了两个行号范围，右侧的行号表示原始代码行，左侧表示新的行号。
     * 闭包，特征：只有三个行号，它与内联成对出现。
     * 注释，特征：以#开头，通常不会出现在mapping中
     *
     * //类名的映射
     * org.tensorflow.lite.examples.digitclassifier.Xxx -> org.tensorflow.lite.c.a.a:
     *     org.tensorflow.lite.Interpreter interpreter -> a
     *     int inputImageHeight -> d
     *     int modelInputSize -> e
     *     boolean isInitialized -> b
     *     int inputImageWidth -> c
     *     android.content.Context context -> f
     *     //上面是字段的映射
     *
     *
     *     1:1:void <init>(android.content.Context):14:14 -> <init>
     *     1:3:java.lang.String recognizeSync(java.lang.String,float[][]):26:28 -> a
     *     4:5:java.lang.String recognizeSync(java.lang.String,float[][]):31:32 -> a
     *     6:6:void init():39:39 -> a
     *     7:12:java.nio.ByteBuffer loadModelFile(android.content.res.AssetManager):66:71 -> a
     *     13:14:android.graphics.Bitmap getImgFromTrace(float[][]):109:110 -> a
     *     15:15:android.graphics.Bitmap getImgFromTrace(float[][]):114:114 -> a
     *     16:18:android.graphics.Bitmap getImgFromTrace(float[][]):116:118 -> a
     *     19:20:android.graphics.Bitmap getImgFromTrace(float[][]):167:168 -> a
     *     21:21:android.graphics.Bitmap getImgFromTrace(float[][]):171:171 -> a
     *      //这里是方法的映射，左侧行号范围是在混淆后的行号范围，右侧的在源文件中的行号范围
     *      最右侧的a，代表混淆后的方法，有可能源文件中的方法合并成一个方法了
     *
     * 比如13:14 对应 109:110 就是源文件的109-110行代码对应混淆后 13-14行代码
     *
     *
     * =============混淆规则=============
     * proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
     * proguard-android.txt 这个文件在  Android SDK/tools/proguard/ 中
     * 我们可以改成  proguard-android-optimize.txt  来开启
     *
     *
     * =========-assumenosideeffects========
     * https://cloud.tencent.com/developer/article/1396277 Android/Java 混淆中使用-assumenosideeffects删除日志代码遇到的问题
     * 全局删除调用的方法
     * 首先要 确保没有开启 --dontoptimize 选项
     * proguardFiles getDefaultProguardFile('proguard-android.txt') 改成 proguard-android-optimize.txt
     * 因为默认的 Android SDK/tools/proguard/proguard-android.txt中 配置了 --dontoptimize
     *
     * 然后在 app/proguard-rules.pro 中定义要去除的方法
     *
     * -assumenosideeffects class android.webkit.SslErrorHandler {
     *     public void proceed(...);
     * }
     *
     * -assumenosideeffects class android.util.Log {
     * public static *** d(...);
     * public static *** e(...);
     * public static *** i(...);
     * public static *** v(...);
     * public static *** println(...);
     * public static *** w(...);
     * public static *** wtf(...);
     * }
     *
     * 其实就是根据这个去匹配要移除的类的调用的方法
     *
     *
     *
     */
    void fun6(){}

    /**
     * ===========系统bug防止=======
     * https://booster.johnsonlee.io/preface/#%E6%9C%AC%E4%B9%A6%E9%80%82%E5%90%88%E4%BA%BA%E7%BE%A4
     * Booster 是一款专门为移动应用设计的易用、轻量级且可扩展的质量优化框架，
     * 其目标主要是为了解决随着 APP 复杂度的提升而带来的性能、稳定性、包体积等一系列质量问题。
     *
     * Booster 提供了性能检测、多线程优化、资源索引内联、资源去冗余、资源压缩、
     * 系统 Bug 修复等一系列功能模块，可以使得稳定性能够提升 15% ~ 25%，包体积可以减小 1MB ~ 10MB。
     *
     */
    void fun7(){}

    /**
     * ========= 查看 app内存 apu 占用情况
     *
     * ====== adb shell ps 查看所有进程信息
     * 原文链接：https://blog.csdn.net/zhangjg_blog/article/details/83537869
     * https://www.cnblogs.com/mlZhao/p/12435987.html
     *
     * USER           PID  PPID     VSZ    RSS WCHAN            ADDR S NAME
     * root             1     0   43860   3848 SyS_epoll_wait      0 S init
     * root             2     0       0      0 kthreadd            0 S [kthreadd]
     * root             4     2       0      0 worker_thread       0 I [kworker/0:0H]
     * system        6172  2702  964668 128444 SyS_epoll_wait      0 S com.xx.xx.rtc
     * system        6220  2702  970540 115628 SyS_epoll_wait      0 S com.xx.xx.rtc:core
     *
     * 1 . USER：进程uid
     * 2 . PID：进程pid
     * 3 . PPID：父进程pid。所有app进程的父进程都是zygote,　所以zygote进程pid肯定是356:
     * 4 . VSIZE : 进程虚拟地址空间的大小。man ps中对VSIZE的解释
     * virtual memory size of the process in KiB (1024-byte units).
     * Device mappings are currently excluded; this is subject to change.
     * ５．RSS：进程所占的物理内存大小。man ps中对RSS的解释：
     * resident set size, the non-swapped physical memory that a task has used (in kiloBytes).
     * ６．PC：program counter，程序计数器。
     *
     * ７．Name：进程名
     *
     * ８．S　进程状态，常见的状态如下：
     *
     * R　正在运行或在运行队列上等待调度
     * S 正在睡眠，该睡眠可被中断，如可以被信号唤醒
     * D　正在睡眠，该睡眠不可被中断，不接收信号
     * Z　zombie僵尸进程。进程死后没有被其父进程回收
     *
     * ９ . WCHAN ：当前线程在哪个内核函数上睡眠。man ps 对该字段的解释如下：
     * name of the kernel function in which the process is sleeping,
     * a “-” if the process is running, or a “*” if the process is multi-threaded and ps is not displaying threads.
     * SyS_epoll_ 说明进程的主线程正在消息队列上等待，比如我们分析Android ANR问题时，经常遇到如下日志，
     * 说明主线程正在内核的epoll上睡眠，也就是说主线程正在消息循环上等待，因为消息循环就是通过epoll实现的。
     *
     * adb shell ps | grep com.xxx.xx 过滤包名
     *
     * ========== adb shell top
     * adb shell top -m 5 该命令会打印当前使用 CPU 前5位的进程相关的信息，每隔1s更新一次
     *
     * adb shell top -h 查看帮助
     * Usage: top [ -m max_procs ] [ -n iterations ] [ -d delay ] [ -s sort_column ] [-t ] [ -h ]
     *     -m num  Maximum number of processes to display. 最多显示多少个进程
     *     -n num  Updates to show before exiting.  刷新次数
     *     -d num  Seconds to wait between updates. 刷新间隔时间（默认5秒）
     *     -s col  Column to sort by (cpu,vss,rss,thr). 按哪列排序
     *     -t      Show threads instead of processes. 显示线程信息而不是进程
     *     -h      Display this help screen.  显示帮助文档
     *
     *   top -d 1 | grep com.xx.xx 每一秒打印一次内存使用情况
     * ————————————————
     * 版权声明：本文为CSDN博主「明潮」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
     * 原文链接：https://blog.csdn.net/u010144805/article/details/79152837
     *
     *
     *  PID USER         PR  NI VIRT  RES  SHR S[%CPU] %MEM     TIME+ ARGS
     * 17326 root         20   0 6.6M 3.6M 2.9M R  2.6   0.1   0:02.75 top -m 5
     *  5911 system       20   0 936M 119M 104M S  1.6   6.1  28:14.44 com.tal.genie.p+
     *  2759 system       18  -2 1.3G 242M 211M S  1.3  12.4  19:15.42 system_server
     *   439 root         20   0    0    0    0 S  0.6   0.0   8:58.42 [f2fs_discard-2+
     *  6172 system       10 -10 1.1G 278M 217M S  117  14.3   5:27.22 com.xx.xx.r+
     *  2708 audioserver  20   0  45M  18M  12M S 46.0   0.9   2:18.29 android.hardwar+
     *   481 cameraserver 20   0 373M  60M  32M S 39.0   3.1   2:11.20 camerahalserver
     *
     * 一般情况：VSS>= RSS >= PSS >= USS。
     *
     * PID 进程在系统中的ID
     * USER 进程uid
     * PR    优先级
     * NI — nice值。负值表示高优先级，正值表示低优先级
     * VIRT — 进程使用的虚拟内存总量，单位kb。VIRT=SWAP+RES
     * RES — 进程使用的、未被换出的物理内存大小，单位kb。RES=CODE+DATA
     * SHR — 共享内存大小，单位kb
     * S     进程状态:D=不可中断的睡眠状态, R=运行, S=睡眠, T=跟踪/停止, Z=僵尸进程
     *
     * %CPU — 上次更新到现在的CPU时间占用百分比
     * %MEM — 进程使用的物理内存百分比
     * TIME+ — 进程使用的CPU时间总计，单位1/100秒
     * COMMAND — 进程名称（命令名/命令行）
     *
     * =========
     * VSS - Virtual Set Size 虚拟耗用内存（包含共享库占用的内存）是单个进程全部可访问的地址空间
     * RSS - Resident Set Size 实际使用物理内存（包含共享库占用的内存）是单个进程实际占用的内存大小，对于单个共享库， 尽管无论多少个进程使用，实际该共享库只会被装入内存一次。
     * PSS - Proportional Set Size 实际使用的物理内存（比例分配共享库占用的内存）
     * USS - Unique Set Size 进程独自占用的物理内存（不包含共享库占用的内存）USS 是一个非常非常有用的数字， 因为它揭示了运行一个特定进程的真实的内存增量大小。如果进程被终止， USS 就是实际被返还给系统的内存大小。
     * USS 是针对某个进程开始有可疑内存泄露的情况，进行检测的最佳数字。怀疑某个程序有内存泄露可以查看这个值是否一直有增加
     * ————————————————
     * 版权声明：本文为CSDN博主「明潮」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
     * 原文链接：https://blog.csdn.net/u010144805/article/details/79152837
     *
     *
     *
     * NativeHeap：native层的 so 中调用malloc或new创建的内存
     * Graphics：OpenGL和SurfaceFlinger相关内存
     * Stack：线程栈
     * Code：dex+so相关代码占用内存
     *
     *
     *
     *
     *
     *
     *
     */
    void fun8(){}

    /**
     * https://developer.android.google.cn/studio/profile/cpu-profiler
     * 查看app cpu消耗和 其他进程消耗
     * 可以record每个线程的cpu占用，消耗的时间。
     * 
     *
     */
    void fun9(){

    }
}
