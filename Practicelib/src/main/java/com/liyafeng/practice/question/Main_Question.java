package com.liyafeng.practice.question;

public class Main_Question {

    /**
     * 比较排除法
     * 找一个可以工作的逻辑，一点点比较不同，注释掉一部分异常代码，如果可以，那么问题肯定出在被注释的代码中
     *
     * 时间点判断法
     * 如果之前可以，那么找出不可以的时间点，看执行了什么逻辑，就能定位到问题代码的范围
     * 就像webview宽度不变那次，就是一开始可以，后面就不行了，通过时间点判断问题所在
     *
     *
     * 查看源码法，debug，这个是正向推理
     *
     *
     * @param args
     */
    public static void main(String[] args) {

    }


    /**
     * java.lang.ExceptionInInitializerError
     * 这个异常是在静态代码块初始化的时候（包括静态变量）
     * 抛出了异常导致的，这个异常用可能是空指针或者其他异常
     */
    public static void a1() {
    }

    /**
     * Okhttp 的addInterceptor 和 addNetworkInterceptor 的区别？
     * https://github.com/AndroidPreView/AndroidNote/wiki/Okhttp-的addInterceptor-和-addNetworkInterceptor-的区别？
     * 响应有可能是缓存，应用拦截器会拦截，网络拦截器不会
     */
    public static void a2() {
    }


    /**
     * LinearLayout android:layout_gravity=right 无效问题
     * 当LinearLayout设置 android:orientation="vertical" 的时候，
     * <p>
     * 竖直即vertical方向上，android:layout_gravity="bottom" 是无效的，只有左右left和right有效果。
     * <p>
     * 同理，当设置 android:orientation="horizontal" ，水平方向的设置都是无效的，只有top和bottom设置有效果
     */
    public void a3() {

    }


    /**
     * android TextView setEms 方法名字
     * android TextView setEms() 作用是设置textview的字符宽度。但是名字很奇怪。
     * <p>
     * <p>
     * <p>
     * [java] view plain copy
     * /**
     * Makes the TextView exactly this many ems wide
     *
     * @attr ref android.R.styleable#TextView_ems
     * @android.view.RemotableViewMethod public void setEms(int ems) {
     * mMaxWidth = mMinWidth = ems;
     * mMaxWidthMode = mMinWidthMode = EMS;
     * <p>
     * requestLayout();
     * invalidate();
     * }
     * <p>
     * <p>
     * [html]
     * view plain
     * copy
     * An em
     * is a
     * unit in
     * the field
     * of typography
     * <p>
     * <p>
     * em是一个印刷排版的单位，表示字宽的单位。em字面意思为：
     * equal M   （和M字符一致的宽度为一个单位）简称em。
     * <p>
     * ems是em的复数表达。
     * <p>
     * <p>
     * em 的具体来历？
     * <p>
     * http://en.wikipedia.org/wiki/Em_%28typography%29
     */

    public void a4() {

    }

    /**
     * edittext不获取焦点
     * https://blog.csdn.net/WOSHICAIXIANFENG/article/details/7261718
     * android:focusable="true"
     * android:focusableInTouchMode="true"
     */
    void a5() {

    }


    /**
     * 上传头像图片过大导致 413错误 Request Entity Too Large
     * cropPhotoIntent.putExtra("crop", "true");
     * if(android.os.Build.MODEL.contains("HUAWEI"))
     * {//华为特殊处理 不然会显示圆
     * cropPhotoIntent.putExtra("aspectX", 9998);
     * cropPhotoIntent.putExtra("aspectY", 9999);
     * }
     * else
     * {
     * cropPhotoIntent.putExtra("aspectX", 1);
     * cropPhotoIntent.putExtra("aspectY", 1);
     * }
     * // outputX outputY 是裁剪图片宽高
     * cropPhotoIntent.putExtra("outputX", 300);
     * cropPhotoIntent.putExtra("outputY", 300);
     * <p>
     * 直接调用系统剪裁
     */
    void a6() {
    }


    /**
     * frecso的oom,新闻列表加载图片，都是大图，如何处理？
     * <p>
     * 其实就是加载控件大小的图片即可，或者用glide
     */
    void a7() {
    }


    /**
     * 接入tinker,测试没问题，到正式项目中
     * 出现  but we found loader classes are found in old secondary dex
     * 说明tinker的部分代码在class2.dex中，用 AS的build->Analyze apk来分析
     * 结果真在，网上找方法，说是加 multiDexKeepProguard 来将代码保持在主dex中
     * 但是试了不行。。。不知道哪出了问题。。。
     * <p>
     * 这个时候你只能从原理，分析，Android分包是因为65536，自动将启动需要的代码放入主dex
     * 然后.pro keep规则你也得了解一下。。。看是不是你这写错了，
     * gradle 的规则你也得了解一下 看看 multiDexKeepProguard 是不是写错位置了
     * <p>
     * -----
     * 妈的，气死我了，原来点击右边的 assembleDebug 生成的apk tinker就都在
     * 主dex中，直接run的不行。。。
     */
    void a8() {
    }


    /**
     * java.util.concurrent.TimeoutException: android.os.BinderProxy.finalize() timed out after 10 seconds
     * <p>
     * https://blog.csdn.net/jamin0107/article/details/78793021
     * https://www.jianshu.com/p/0119c682d2b8
     * <p>
     * 屏幕息屏，进程被中断，导致gc超时，抛出异常
     */
    void a9() {

    }


    /**
     * 很多jni的部分我们down下来代码，在sdkManager安装cmake和llbs，
     * 然后需要可能需要 invalidate cache restart ,才能正常
     */
    void a10() {
    }


    /**
     * This file can not be opened as a file descriptor; it is probably compressed
     * 这个问题是aapt打包的时候对资源压缩导致的
     * https://github.com/tensorflow/tensorflow/issues/22333
     * android{
     * aaptOptions {
     * noCompress "tflite"
     * noCompress "lite"
     * }
     */
    void a11() {
    }


    /**
     * Cause: org.jetbrains.plugins.gradle.tooling.util.ModuleComponentIdentifierImpl.getModuleIdentifier()Lorg/gradle/api/artifacts/ModuleIdentifier;
     * <p>
     * classpath 'com.android.tools.build:gradle:3.3.2'
     * distributionUrl=https\://services.gradle.org/distributions/gradle-5.2.1-all.zip
     * <p>
     * 这需要Android studio 版本在3.2以上
     */
    void a12() {
    }


    /**
     * 项目中有jni，这就需要配置ndk，还有tools 中的lldb 和cmake 工具
     * 设置中搜sdk，下载即可，或者直接启动sdk manager
     */
    void a13() {
    }


    /**
     * Could not determine artifacts for XXXX: Skipped due to earlier error
     * <p>
     * https://www.jianshu.com/p/cf2cbd4d005b
     * 仓库的地址请求不到，所以后面的同样的url都失败了
     * <p>
     * 官网上的解释是因为超时的原因，跳过了对同一仓库的请求，这里就比较明显，其实就是代理的问题。
     * 国内开发环境一直是一个比较大问题，开发得一直连着代理，但是由于可能公司有自己的内网maven，所以需要过滤掉内网的Host。但是我这个地方是过滤了的，但是不知道什么原因过滤失败了，而且是部分失败。所以我需要关闭掉代理再来尝试，但是gradle这里有有一些坑了，即使你在IDE中关闭了代理，但是gradle还是会缓存代理的设置，所以需要去Users/xxx/.gradle/gradle.properties中删除掉代理。
     * 然后再进行尝试就ok了。
     */
    void a14() {
    }


    /**
     * 适配的坑，通过头条的适配方案，很完美，但是就一个页面不行，有视频直播，有webview，
     * 检查原因是density被重置了，，然后就debug，找出哪里改变了，发现是setContentView后被重置了
     * 然后这可咋整。。。后来一想为啥之前没事，修改布局后就有事，后来发现原来布局在webview后的大小都错了
     * 那肯定是webview的原因，结果发现就是这个问题（肯定webview中new了一个新的出来），后来解决方案是
     * 动态加入webview，加入后重新设置 density的值，这样就不影响其他布局了
     */
    void a15() {
    }


    /**
     * https://www.jianshu.com/p/57047a84e559
     *
     * Android P(9.0) http网络请求的问题
     * okhttp 出现 CLEARTEXT communication to [ip]  not permitted by network security policy
     * HttpUrlConnection 出现 cleartext HTTP traffic to **** not permitted
     *
     * 在Android P系统的设备上，如果应用使用的是非加密的明文流量的http网络请求，
     * 则会导致该应用无法进行网络请求，https则不会受影响，同样地，如果应用嵌套了webview，webview也只能使用https请求。
     *
     * 有以下三种解决方案
     *
     * APP改用https请求
     *
     * targetSdkVersion 降到27以下
     *
     * 在 res 下新增一个 xml 目录，然后创建一个名为：network_security_config.xml 文件（名字自定） ，内容如下，大概意思就是允许开启http请求
     * <?xml version="1.0" encoding="utf-8"?>
     *
     * <network-security-config>
     *
     *  <base-config cleartextTrafficPermitted="true" />
     *
     * </network-security-config>
     *
     *
     * 然后在APP的AndroidManifest.xml文件下的application标签增加以下属性
     *  android:networkSecurityConfig="@xml/network_security_config"
     *
     */
    void a16(){}


    /**
     * android.view.WindowLeaked: Activity com.brandy.ui.activity.video.VideoActivity has leaked window DecorView@b38aa27[] that was originally added here
     *         at android.view.ViewRootImpl.<init>(ViewRootImpl.java:496)
     *         at android.view.WindowManagerGlobal.addView(WindowManagerGlobal.java:349)
     *         at android.view.WindowManagerImpl.addView(WindowManagerImpl.java:94)
     *         at android.app.Dialog.show(Dialog.java:332)
     *         at com.brandy.ui.dialog.QuitDialog.showDialog
     *  产生原因：
     * 我们知道Android的每一个Activity都有个WindowManager窗体管理器，同样，构建在某个Activity之上的对话框、PopupWindow也有相应的WindowManager窗体管理器。因为对话框、PopupWindown不能脱离Activity而单独存在着，所以当某个Dialog或者某个PopupWindow正在显示的时候我们去finish()了承载该Dialog(或PopupWindow)的Activity时，就会抛Window Leaked异常了，因为这个Dialog(或PopupWindow)的WindowManager已经没有谁可以附属了，所以它的窗体管理器已经泄漏了。
     *
     *
     * 解决方法：
     * 关闭(finish)某个Activity前，要确保附属在上面的Dialog或PopupWindow已经关闭(dismiss)了。
     *
     *
     * ---------------------
     * 作者：u_xtian
     * 来源：CSDN
     * 原文：https://blog.csdn.net/u_xtian/article/details/6123945
     * 版权声明：本文为博主原创文章，转载请附上博文链接！
     *
     *
     *
     */
    void a17(){}


    /**
     * fresco 内存占用问题
     * 是因为setImage 为 null 导致一直请求
     */
    void a18(){}


    /**
     *     Process: com.brandy, PID: 4773
     *     java.lang.UnsupportedOperationException
     *         at java.util.AbstractList.add(AbstractList.java:148)
     *         at java.util.AbstractList.add(AbstractList.java:108)
     *         at java.util.AbstractCollection.addAll(AbstractCollection.java:344)
     *         at com.brandy.ui.activity.video.BaseVideoActivity$11.onUserUpdate(BaseVideoActivity.java:593)
     *         at com.zego.zegoliveroom.ZegoLiveRoom$35.run(ZegoLiveRoom.java:3933)
     *
     */
    void a19(){}


    /**
     * java.lang.UnsatisfiedLinkError，ClassLoader找不到相关的so库
     * https://blog.51cto.com/weijiancheng/1891876
     * 问题一、Android支持哪些cpu架构？
     *
     * 答案很容易搜索到：android目前支持7种架构
     * x86_64
     * x86
     * mips64
     * mips
     * armeabi-v7a
     * armeabi
     * arm64-v8a
     * 问题二、系统是怎么查找so库的呢？
     * 查找so库规则：
     *       运行的时候，系统会到Jnidirs目录里查找so库，会根据当前平台架构查找对应的目录。这里面有一个规则是这个问题的元凶。当你只提供了armeabi目录时，armeabi-v7a、arm64-v8a架构的程序都会去armeabi里寻找，而当你同时也提供了armeabi-v7a、armeabi-v8a目录，而里面又不存在对应的so库时，系统就不会再去armeabi里面寻找了，直接找不到报错。
     *       而签名包里面这七个目录都有，这个问题后面讨论。
     *
     * 验证猜想：
     *       将app-release.apk使用打包软件打开，删除其中的armeabi-v7a目录，发现联想手机可以正常运行了（去armeabi里面寻找了）。移除x86_64文件夹，模拟器可以正常运行（去x86里面寻找了）。
     *  问题三、签名包为什么七个目录全都有？
     *
     *       对应Jnidirs目录中的七个目录。打开签名包，发现这七个目录都有。奇怪的是我的项目里只有x86、armeabi和arm64-v8a三个类型的so库。
     *       打开其他的目录发现都是只有一个so文件：libgenius_graphics.so。百思不得其解这个从何而来，查找build-gradle的时候发现：compile 'net.qiujuer.genius:graphics:2.0.0-beta8' 非常相似，这是一个开源的UI库。猜测是引入这个库在编译的时候自动生成各个平台下的libgenius_graphics.so文件。移除之，rebuild，确实如此，只剩下原本的三个文件夹
     *
     * 查阅许多文档都说x86是兼容armeabi的so文件的，验证时必须保证x86文件夹为空，或者根本没有x86文件夹)
     *
     *  ============配置===========
     *  需要写到app下，不能写在模块的gradle配置中，否则会全部abi都引用
     * ndk {
     * abiFilters “armeabi-v7a”
     * }
     *
     * =============abi兼容=============
     * https://blog.csdn.net/YoungHong1992/article/details/90083194
     * ABI目录（横向）和cpu（纵向）	armeabi	armeabi-v7a	arm64-v8a	mips	mips64	x86	x86_64
     * ARMv5	                支持
     * ARMv7	                支持	    支持
     * ARMv8	                支持	    支持	        支持
     * MIPS				        支持
     * MIPS64				                                    支持	    支持
     * x86	                    支持	    支持				                        支持
     * x86_64	                支持					                            支持	支持
     *
     */
    void a20(){}


    /**
     * ==============native crash问题===========
     * https://cloud.tencent.com/developer/article/1192001 （ Android基础开发实践：如何分析Native Crash）
     *
     * （常见android-native崩溃及错误原因）
     * http://www.droidsec.cn/%E5%B8%B8%E8%A7%81android-native%E5%B4%A9%E6%BA%83%E5%8F%8A%E9%94%99%E8%AF%AF%E5%8E%9F%E5%9B%A0/
     *
     * https://www.jianshu.com/p/841c18c6e18d （一个关于Android支持64位CPU架构升级的“锅”）
     *
     *
     * Android的Zygote在Fork进程的时候，
     * 都会在InitNonZygoteOrPostFork时调用 StartSignalCatcher 创建一个新的SignalCatcher线程，这个线程的作用就是用来捕获Linux信号。
     * 这个线程也是通过pthread_create创建，运行起来之后，会一直等待信号的到来：
     *
     * StartSignalCatcher只处理两种类型的信号，一种是SIGQUIT，一种是SIGUSR1
     * 除了SignalCatcher，Runtime在启动的时候会创建一个FaultManager
     * FaultManager则会捕获更多真正意义上的信号(SIGABRT/SIGBUS/SIGFPE/SIGILL/SIGSEGV)
     *
     * Linux中对信号的定义在signum.h文件中
     * https://users.unvanquished.net/~modi/code/include/x86_64-linux-gnu/bits/signum.h.html
     *
     * define SIGILL		4	/* Illegal instruction (ANSI).  *
     * define SIGTRAP		5    /* Trace trap (POSIX).  *
     * define SIGABRT		6    /* Abort (ANSI).  *
     * define SIGIOT		6    /* IOT trap (4.2 BSD).  *
     * define SIGBUS		7    /* BUS error (4.2 BSD).  *
     * define SIGFPE		8    /* Floating-point exception (ANSI).  *
     * define SIGKILL		9    /* Kill, unblockable (POSIX).  *
     * define SIGUSR1		10    /* User-defined signal 1 (POSIX).  *
     * define SIGSEGV		11    /* Segmentation violation (ANSI).  *
     * define SIGUSR2		12    /* User-defined signal 2 (POSIX).  *
     * define SIGPIPE		13    /* Broken pipe (POSIX).  *
     * define SIGALRM		14    /* Alarm clock (POSIX).  *
     * define SIGTERM		15    /* Termination (ANSI).  *
     * define SIGSTKFLT	16    /* Stack fault.  *
     * define SIGCLD
     * SIGCHLD    /* Same as SIGCHLD (System V).  *
     * define SIGCHLD		17    /* Child status has changed (POSIX).  *
     * define SIGCONT		18    /* Continue (POSIX).  *
     * define SIGSTOP		19    /* Stop, unblockable (POSIX).  *
     * define SIGTSTP		20    /* Keyboard stop (POSIX).  *
     * define SIGTTIN		21    /* Background read from tty (POSIX).  *
     * define SIGTTOU		22    /* Background write to tty (POSIX).  *
     * define SIGURG		23    /* Urgent condition on socket (4.2 BSD).  *
     * define SIGXCPU		24    /* CPU limit exceeded (4.2 BSD).  *
     * define SIGXFSZ		25    /* File size limit exceeded (4.2 BSD).  *
     * define SIGVTALRM	26    /* Virtual alarm clock (4.2 BSD).  *
     * define SIGPROF		27    /* Profiling alarm clock (4.2 BSD).  *
     * define SIGWINCH	28    /* Window size change (4.3 BSD, Sun).  *
     * define SIGPOLL
     * SIGIO    /* Pollable event occurred (System V).  *
     * define SIGIO		29    /* I/O now possible (4.2 BSD).  *
     * define SIGPWR		30    /* Power failure restart (System V).  *
     * define SIGSYS		31    /* Bad system call.  *
     * define SIGUNUSED	31
     *
     * SIG是前缀 ，signal , 后面是名称
     * 1. SIGBUS：总线出错，比如数据对齐；
     * 2. SIGFPE：错误的运算操作，比如除零；
     * 3. SIGILL：出现了非法指令；
     * 4. SIGSEGV：访问了一个不合法内存地址，空指针或者内存越界导致的。
     *
     *
     * 错误信号：11是信号量sigNum，SIGSEGV是信号的名字，SEGV_MAPERR是SIGSEGV下的一种类型。
     * 寄存器快照：进程收到错误信号时保存下来的寄存器快照，其中PC寄存器存储的就是下个要运行的指令（出错的位置）。
     * 调用栈：#00是栈顶，#02是栈底，#02调用#01调用#00方法，#00的方法时libspirit.so中的Spirit类下的testCrash方法，出错的地方是testCrash方法内汇编偏移17（不是行号哦！）
     *
     * 比如bugly上的日志
     * #00 pc 0001aa2c /system/lib/libc.so (abort+63) [armeabi-v7a]
     * #00 pc 000000000001e158 /system/lib64/libc.so (abort+104) [arm64-v8a]
     * #01 pc 000000000044e750 /system/lib64/libart.so (art::Runtime::Abort(char const*)+552) [arm64-v8a]
     * #02 pc 00000000005445a8 /system/lib64/libart.so (android::base::LogMessage::~LogMessage()+996) [arm64-v8a]
     *
     *
     * ===============什么是错误信号=========
     * Android本质就是一个Linux，信号跟Linux信号是同一个东西，信号本身是用于进程间通信的没有正确错误之分，但官方给一些信号赋予了特定的含义及特定处理动作，
     * 通常我们说的错误信号有5个（Bugly全部都能上报），系统默认处理就是dump出堆栈，并退出进程：
     *
     * 1、硬件发生异常，即硬件(通常是CPU)检测到一个错误条件并通知Linux内核，内核处理该异常，给相应的进程发送信号。
     * 硬件异常的例子包括执行一条异常的机器语言指令，诸如，被0除，或者引用了无法访问的内存区域。大部分信号如果没有被进程处理，
     * 默认的操作就是杀死进程。在本文中，SIGSEGV(段错误)，SIGBUS(内存访问错误)，SIGFPE(算数异常)属于这种信号
     *
     * 2、进程调用的库发现错误，给自己发送中止信号，默认情况下，该信号会终止进程。在本文中，SIGABRT(中止进程)属于这种信号。
     *
     * 3、用户（手贱）或第三方App（恶意）通过kill-信号 pid的方式给错误进程发送，这时signal中的si_code会小于0。
     *
     * 比如：空指针，野指针，数组越界 都会发送内存段错误的信号 SIGSEGV
     *
     * int* p = 0; //空指针
     * *p = 1; //写空指针指向的内存，产生SIGSEGV信号，造成Crash
     *
     * int* p; //野指针，未初始化，其指向的地址通常是随机的
     * *p = 1; //写野指针指向的内存，有可能不会马上Crash，而是破坏了别处的内存
     *
     * int arr[10];
     * arr[10] = 1; //数组越界，有可能不会马上Crash，而是破坏了别处的内存
     *
     *
     * 缓冲区溢出
     * char szBuffer[10];
     * //由于函数栈是从高地址往低地址创建，而sprintf是从低地址往高地址打印字符，
     * //如果超出了缓冲区的大小，函数的栈帧会被破坏，在函数返回时会跳转到未知的地址上，
     * //基本上都会造成访问异常，从而产生SIGABRT或SIGSEGV，造成Crash
     * sprintf(szBuffer, "Stack Buffer Overrun!111111111111111"  "111111111111111111111");
     * 通过往程序的缓冲区写超出其长度的内容，造成缓冲区的溢出，从而破坏函数调用的堆栈，修改函数调用的返回地址。如果不是黑客故意攻击，
     * 那么最终函数调用很可能会跳转到无法读写的内存区域，产生段错误信号SIGSEGV或SIGABRT，造成程序崩溃，并生成core文件。
     *
     *
     * 主动抛出异常
     * if ((*env)->ExceptionOccurred(env) != 0) {
     *          //动态库在内部运行出现错误时，大都会主动abort，终止运行
     *          abort(); //给当前进程发送信号SIGABRT
     * }
     *
     * 解决方法
     * 查看堆栈找出abort的原因
     * 如果是程序主动abort的，通过堆栈加源码还是很好定位的，
     * 但往往abort的位置是在系统库中，就不好定位了，需要多查看系统API的使用方法，检查是否使用不当。
     *
     *
     * ==============发生错误后保存位置=======
     * E//system/bin/tombstoned: Tombstone written to: /data/tombstones/tombstone_05
     *
     *
     *
     *
     * ============SIGFPE===================
     * int a = 1;
     * int b = a / 0; //整数除以0，产生 SIGFPE 信号，导致Crash
     *
     *
     * ===========SIGSEGV 错误=============
     * https://blog.csdn.net/liangfeng093/article/details/79401351 （ 集成ndk导致的SIGSEGV(SEGV_MAPERR)）
     * https://stackmirror.com/questions/1000002 ( What is SEGV_MAPERR?)
     *
     * SEGV = segmentation violation（内存段异常）an error that results from an invalid memory access
     * 有两种 SEGV
     * SEGV_ACCERR : 内存段是只读的，但是你却写入
     * SEGV_MAPERR : 内存指向一个空指针或者一个错误的地址，这时我们向这个地址写入数据，会发生错误
     *
     * 原因：配置ndk后，jinlibs 文件夹中还存在其它架构的.so库。手机会优先加载自身架构的.so库
     * jinlibs和gradle中配置的要一致，最好只要 v7a 和 v8a ，如果只要v7a在有的oppo v8a手机上会有兼容性问题，就抛出这个错误
     *
     *
     * ==============webview闪退问题===========
     * https://www.jianshu.com/p/841c18c6e18d （一个关于Android支持64位CPU架构升级的“锅”）
     * https://blog.csdn.net/tomatomas/article/details/78624427 （WebView闪退）
     *
     * signal: 11 (SIGSEGV), code: 1 (SEGV_MAPERR) fault addr: 0xa08c45e000 发生在
     *
     * #00 pc 0000000000a20384 /vendor/lib64/libllvm-glnext.so [arm64-v8a]
     * #05 pc 00000000000c4404 /vendor/lib64/egl/libGLESv2_adreno.so (EsxContext::GlProgramBinary(unsigned int, unsigned int, void const*, int)+360) [arm64-v8a]
     * #07 pc 00000000023fe4b4 /system/app/webview/webview.apk
     * #26 pc 0000000000077980 /system/lib64/libc.so (__pthread_start(void*)+36) [arm64-v8a]
     *
     * 现象是打开webview就崩溃，而且在魅族手机上
     * 出现路径是在从1.2版本升级到1.3版本 后出现的，1.3版本新支持的arm64-v8a ，之前只支持 armeabi armeabi-v7a
     *
     * 解决方法是 删除 [your_package]/data/app_webview/GPUCache 下的所有文件，要递归删除，一般耗时十几毫秒
     * 这里存放GPU渲染的一些缓存？？
     *
     *
     *
     *
     */
    void a20_1(){


    }


    /**
     * Android之安装报错INSTALL_PARSE_FAILED_NO_CERTIFICATES
     * Android 7.0 引入一项新的应用签名方案 APK Signature Scheme v2，它能提供更快的应用安装时间和更多针对未授权 APK 文件更改的保护。在默认情况下，Android Studio 2.2 和 Android Plugin for Gradle 2.2 会使用 APK Signature Scheme v2 和传统签名方案来签署您的应用。
     *
     * 同时勾选Signature Versions V1（兼容低版本） 和 V2（支持高版本）
     *
     * 开发工具版本Android Studio 2.3.1 在Generate signed apk 后，在Android 7.0以下版本的手机安装报错：INSTALL_PARSE_FAILED_NO_CERTIFICATES
     */
    void a21(){}


    /**
     *
     * app内安装apk报错 FileProvider 没有授权
     *     java.lang.SecurityException: Permission Denial: reading android.support.v4.content.FileProvider uri content://com.brandy.fileProvider/download/appv1.0.0.apk from pid=9663, uid=10018 requires the provider be exported, or grantUriPermission()
     *         at android.content.ContentProvider.enforceReadPermissionInner(ContentProvider.java:777)
     *         at android.content.ContentProvider$Transport.enforceReadPermission(ContentProvider.java:540)
     *         at android.content.ContentProvider$Transport.enforceFilePermission(ContentProvider.java:530)
     *         at android.content.ContentProvider$Transport.openTypedAssetFile(ContentProvider.java:458)
     *         at android.content.ContentProviderNative.onTransact(ContentProviderNative.java:302)
     *         at android.os.Binder.execTransact(Binder.java:708)
     * 2019-05-24 20:39:14.517 9663-15818/? W/InstallStaging: Error staging apk from content URI
     *     java.lang.SecurityException: Permission Denial: reading android.support.v4.content.FileProvider uri content://com.brandy.fileProvider/download/appv1.0.0.apk from pid=9663, uid=10018 requires the provider be exported, or grantUriPermission()
     *         at android.os.Parcel.readException(Parcel.java:2013)
     *         at android.database.DatabaseUtils.readExceptionFromParcel(DatabaseUtils.java:183)
     *         at android.database.DatabaseUtils.readExceptionWithFileNotFoundExceptionFromParcel(DatabaseUtils.java:146)
     *         at android.content.ContentProviderProxy.openTypedAssetFile(ContentProviderNative.java:698)
     *         at android.content.ContentResolver.openTypedAssetFileDescriptor(ContentResolver.java:1520)
     *         at android.content.ContentResolver.openAssetFileDescriptor(ContentResolver.java:1357)
     *         at android.content.ContentResolver.openInputStream(ContentResolver.java:1071)
     *         at com.android.packageinstaller.ll1l1l.l11(InstallStaging.java:234)
     *         at com.android.packageinstaller.ll1l1l.doInBackground(InstallStaging.java:211)
     *         at android.os.AsyncTask$2.call(AsyncTask.java:333)
     *         at java.util.concurrent.FutureTask.run(FutureTask.java:266)
     *         at android.os.AsyncTask$SerialExecutor$1.run(AsyncTask.java:245)
     *         at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1162)
     *         at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:636)
     *         at java.lang.Thread.run(Thread.java:764)
     * ============解决方法==========
     * Stack Overflow的解决方案
     *  List<ResolveInfo> resInfoList = BrandyApplication.getInstance().getPackageManager().queryIntentActivities(install, PackageManager.MATCH_DEFAULT_ONLY);
     *                 for (ResolveInfo resolveInfo : resInfoList) {
     *                     String packageName = resolveInfo.activityInfo.packageName;
     *                     BrandyApplication.getInstance().grantUriPermission(packageName, contentUri, Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
     *                 }
     *
     *
     * ====================
     *  W/InstallStaging: Error staging apk from content URI
     *     java.io.FileNotFoundException: No content provider: content://com.brandy.fileProvider/download/app1.0.3.apk
     *         at android.content.ContentResolver.openTypedAssetFileDescriptor(ContentResolver.java:1489)
     *         at android.content.ContentResolver.openAssetFileDescriptor(ContentResolver.java:1340)
     *         at android.content.ContentResolver.openInputStream(ContentResolver.java:1060)
     *         at com.android.packageinstaller.InstallStaging$StagingAsyncTask.doInBackground(InstallStaging.java:167)
     *         at com.android.packageinstaller.InstallStaging$StagingAsyncTask.doInBackground(InstallStaging.java:160)
     *         at android.os.AsyncTask$2.call(AsyncTask.java:333)
     *         at java.util.concurrent.FutureTask.run(FutureTask.java:266)
     *         at android.os.AsyncTask$SerialExecutor$1.run(AsyncTask.java:245)
     *         at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1167)
     *         at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:641)
     *         at java.lang.Thread.run(Thread.java:764)
     */
    void a22(){}


    /**
     * setMargin突然无效，
     * jni线程中 更新ui，
     * ViewRootImpl 中，
     *   public void invalidateChild(View child, Rect dirty) 有checkThread()方法
     *
     *  throw new CalledFromWrongThreadException(
     * "Only the original thread that created a view hierarchy can touch its views.");
     *
     * jni线程中报出异常不崩溃，但是ui渲染器就停止了。
     *
     *
     */
    void a23(){}


    /**
     * 如果控件在RelativeLayout是居中的，那么padding对他无效，而且会被剪裁
     * padding top的部分在居中时完全没有用，坑爹了word RelativeLayout
     */
    void a24(){}


    /**
     * ARouter::ARouter init logistics center exception
     * 在我将一个有@Route 的Activity移动到新的module的时候，在ARouter.init的时候崩溃
     * 之前是好的，看新module配置也没问题，而且我主App也还没依赖那个module
     * 网上搜，都不是一个问题。。。
     * 没办法，只能一点点比较排除了，先新建module，没问题，一点点添加逻辑，都没问题，
     * 最后删除主App中的 有@Route 的Activity 时候就有问题了。。。
     * 所以这是什么鬼。。
     * 最后删除App，手动删除build下的文件，重装，搞定！ 原来是有缓存
     *
     * 期间还看了Arouter源码，看源码理解为什么崩溃是有效的。。。但是成本太高
     * 你都读懂需要很长时间，而且你还不一定短时间能读懂。。。
     * 这个问题搞了一个下午。。
     *
     * 还有
     * dont matched 问题，也是要清空缓存
     *
     */
    void a25(){}


    /**
     * 2019-7-16
     *
     * 今天用retrofit写获取验证码的接口，post请求，然后回调onError
     * 查看错误
     * java.lang.ClassCastException: okhttp3.RequestBody$2 cannot be cast to okhttp3.FormBody
     * 这是什么鬼。。。
     * 网上查也没有。。。
     * 还以为自己post写错了。。
     * 后来e.printStackTrace()才发现是自己的拦截器里报的异常。。醉了
     *
     */
    void a26(){}


    /**
     * 为了加快冷启动速度，所以设置splash为透明
     * Only fullscreen opaque activities can request orientation
     * https://blog.csdn.net/starry_eve/article/details/82777160
     *
     * 安卓8.0版本时为了支持全面屏，增加了一个限制：如果是透明的Activity，则不能固定它的方向，因为它的方向其实是依赖其父Activity的（因为透明）。然而这个bug只有在8.0中有，8.1中已经修复。具体crash有两种：
     * 1.Activity的风格为透明，在manifest文件中指定了一个方向，则在onCreate中crash
     * 2.Activity的风格为透明，如果调用setRequestedOrientation方法固定方向，则crash
     *
     *
     * 解决方法为：
     * MainActivity设置android:windowIsTranslucent=false，然后指定屏幕方向，而其他activity则可使用android:windowIsTranslucent=true，然后设置android:screenOrientation=”behind”，这样就可以保持屏幕方向统一了。
     * ---------------------
     * 作者：Samlss
     * 来源：CSDN
     * 原文：https://blog.csdn.net/Samlss/article/details/80791042
     * 版权声明：本文为博主原创文章，转载请附上博文链接！
     */
    void a27(){}


    /**
     * Gradle's dependency cache may be corrupt (this sometimes occurs after a network connection timeout.)
     *
     * gradle版本要和配置版本一致，而且要和studio版本对应
     *
     *
     */
    void a28(){}


    /**
     * webview加载不了 cocosjs ,webview开启硬件加速就可以了
     *
     * webView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
     *
     * <application ...
     *  android:hardwareAccelerated="true"
     */
    void a29(){}


    /**
     * 遇到一个问题，在给一个宽度是match—parent高度是wrap_content的imageView设置bitmap的时候
     * imageview的高度不会随着图片宽度变小而变化，导致view的高度还是缩放前的bitmap的高度
     *
     * 加入下面两句即可，保持imageview宽高比，同时要fitxy
     *   android:scaleType="fitXY"
     *   android:adjustViewBounds="true"
     */
    void a30(){}


    /**
     * 点击run
     * Compilation failed; see the compiler error output for details.
     *
     * 看到
     * org.gradle.api.internal.tasks.compile.CompilationFailedException: Compilation failed; see the compiler error output for details.
     * 	at org.gradle.api.internal.tasks.compile.JdkJavaCompiler.execute(JdkJavaCompiler.java:52)
     * 	at org.gradle.api.internal.tasks.compile.JdkJavaCompiler.execute(JdkJavaCompiler.java:37)
     * 	at org.gradle.api.internal.tasks.compile.NormalizingJavaCompiler.delegateAndHandleErrors(NormalizingJavaCompiler.java:98)
     * 	at org.gradle.api.internal.tasks.compile.NormalizingJavaCompiler.execute(NormalizingJavaCompiler.java:51)
     * 	at org.gradle.api.internal.tasks.compile.NormalizingJavaCompiler.execute(NormalizingJavaCompiler.java:37)
     * 	at org.gradle.api.internal.tasks.compile.CleaningJavaCompilerSupport.execute(CleaningJavaCompilerSupport.java:35)
     * 	at org.gradle.api.internal.tasks.compile.CleaningJavaCompilerSupport.execute(CleaningJavaCompilerSupport.java:25)
     * 	at org.gradle.api.internal.tasks.compile.incremental.IncrementalResultStoringDecorator.execute(IncrementalResultStoringDecorator.java:41)
     * 	at org.gradle.api.internal.tasks.compile.incremental.IncrementalResultStoringDecorator.execute(IncrementalResultStoringDecorator.java:27)
     * 	at org.gradle.api.internal.tasks.compile.incremental.IncrementalAnnotationProcessingCompiler.execute(IncrementalAnnotationProcessingCompiler.java:50)
     * 	at org.gradle.api.internal.tasks.compile.incremental.IncrementalAnnotationProcessingCompiler.execute(IncrementalAnnotationProcessingCompiler.java:36)
     * 	at org.gradle.api.tasks.compile.JavaCompile.performCompilation(JavaCompile.java:156)
     * 	at org.gradle.api.tasks.compile.JavaCompile.compile(JavaCompile.java:126)
     * 	at com.android.build.gradle.tasks.factory.AndroidJavaCompile.compile(AndroidJavaCompile.java:125)
     *
     * 	什么鬼。。。
     *
     * 	原来具体错误要点击左侧 toggle view来查看 ,切换到Messages View
     * 	错误: ARouter::Compiler An exception is encountered, [The inject fields CAN NOT BE 'private'!!! please check field [correctNum] in class [com.module_oral.ui.activity.share.ResultShareActivity]]
     *       at com.alibaba.android.arouter.compiler.processor.AutowiredProcessor.categories(AutowiredProcessor.java:266)
     *       at com.alibaba.android.arouter.compiler.processor.AutowiredProcessor.process(AutowiredProcessor.java:94)
     *       at com.sun.tools.javac.processing.JavacProcessingEnvironment.callProcessor(JavacProcessingEnvironment.java:794)
     *       at com.sun.tools.javac.processing.JavacProcessingEnvironment.discoverAndRunProcs(JavacProcessingEnvironment.java:705)
     *       at com.sun.tools.javac.processing.JavacProcessingEnvironment.access$1800(JavacProcessingEnvironment.java:91)
     *       at com.sun.tools.javac.processing.JavacProcessingEnvironment$Round.run(JavacProcessingEnvironment.java:1035)
     *       at com.sun.tools.javac.processing.JavacProcessingEnvironment.doProcessing(JavacProcessingEnvironment.java:1176)
     *       at com.sun.tools.javac.main.JavaCompiler.processAnnotations(JavaCompiler.java:1170)
     *       at com.sun.tools.javac.main.JavaCompiler.compile(JavaCompiler.java:856)
     *       at com.sun.tools.javac.main.Main.compile(Main.java:523)
     *       at com.sun.tools.javac.api.JavacTaskImpl.doCall(JavacTaskImpl.java:129)
     *       at com.sun.tools.javac.api.JavacTaskImpl.call(JavacTaskImpl.java:138)
     *       at org.gradle.api.internal.tasks.compile.IncrementalAnnotationProcessingCompileTask.call(IncrementalAnnotationProcessingCompileTask.java:73)
     *       at org.gradle.api.internal.tasks.compile.JdkJavaCompiler.execute(JdkJavaCompiler.java:50)
     *       at org.gradle.api.internal.tasks.compile.JdkJavaCompiler.execute(JdkJavaCompiler.java:37)
     *       at org.gradle.api.internal.tasks.compile.NormalizingJavaCompiler.delegateAndHandleErrors(NormalizingJavaCompiler.java:98)
     *       at org.gradle.api.internal.tasks.compile.NormalizingJavaCompiler.execute(NormalizingJavaCompiler.java:51)
     *       at org.gradle.api.internal.tasks.compile.NormalizingJavaCompiler.execute(NormalizingJavaCompiler.java:37)
     *       at org.gradle.api.internal.tasks.compile.CleaningJavaCompilerSupport.execute(CleaningJavaCompilerSupport.java:35)
     *       at org.gradle.api.internal.tasks.compile.CleaningJavaCompilerSupport.execute(CleaningJavaCompilerSupport.java:25)
     *       at org.gradle.api.internal.tasks.compile.incremental.IncrementalResultStoringDecorator.execute(IncrementalResultStoringDecorator.java:41)
     *       at org.gradle.api.internal.tasks.compile.incremental.IncrementalResultStoringDecorator.execute(IncrementalResultStoringDecorator.java:27)
     *       at org.gradle.api.internal.tasks.compile.incremental.IncrementalAnnotationProcessingCompiler.execute(IncrementalAnnotationProcessingCompiler.java:50)
     *       at org.gradle.api.internal.tasks.compile.incremental.IncrementalAnnotationProcessingCompiler.execute(IncrementalAnnotationProcessingCompiler.java:36)
     *       at org.gradle.api.tasks.compile.JavaCompile.performCompilation(JavaCompile.java:156)
     *       at org.gradle.api.tasks.compile.JavaCompile.compile(JavaCompile.java:126)
     *       at com.android.build.gradle.tasks.factory.AndroidJavaCompile.compile(AndroidJavaCompile.java:125)
     *       at sun.reflect.GeneratedMethodAccessor491.invoke(Unknown Source)
     *       at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
     *       at java.lang.reflect.Method.invoke(Method.java:498)
     *       at org.gradle.internal.reflect.JavaMethod.invoke(JavaMethod.java:73)
     *       at org.gradle.api.internal.project.taskfactory.IncrementalTaskAction.doExecute(IncrementalTaskAction.java:50)
     *       at org.gradle.api.internal.project.taskfactory.StandardTaskAction.execute(StandardTaskAction.java:39)
     *       at org.gradle.api.internal.project.taskfactory.StandardTaskAction.execute(StandardTaskAction.java:26)
     *       at org.gradle.api.internal.tasks.execution.ExecuteActionsTaskExecuter$1.run(ExecuteActionsTaskExecuter.java:124)
     *       at org.gradle.internal.progress.DefaultBuildOperationExecutor$RunnableBuildOperationWorker.execute(DefaultBuildOperationExecutor.java:336)
     *       at org.gradle.internal.progress.DefaultBuildOperationExecutor$RunnableBuildOperationWorker.execute(DefaultBuildOperationExecutor.java:328)
     *       at org.gradle.internal.progress.DefaultBuildOperationExecutor.execute(DefaultBuildOperationExecutor.java:199)
     *       at org.gradle.internal.progress.DefaultBuildOperationExecutor.run(DefaultBuildOperationExecutor.java:110)
     *       at org.gradle.api.internal.tasks.execution.ExecuteActionsTaskExecuter.executeAction(ExecuteActionsTaskExecuter.java:113)
     *       at org.gradle.api.internal.tasks.execution.ExecuteActionsTaskExecuter.executeActions(ExecuteActionsTaskExecuter.java:95)
     *       at org.gradle.api.internal.tasks.execution.ExecuteActionsTaskExecuter.execute(ExecuteActionsTaskExecuter.java:73)
     *       at org.gradle.api.internal.tasks.execution.OutputDirectoryCreatingTaskExecuter.execute(OutputDirectoryCreatingTaskExecuter.java:51)
     *       at org.gradle.api.internal.tasks.execution.SkipUpToDateTaskExecuter.execute(SkipUpToDateTaskExecuter.java:59)
     *       at org.gradle.api.internal.tasks.execution.ResolveTaskOutputCachingStateExecuter.execute(ResolveTaskOutputCachingStateExecuter.java:54)
     *       at org.gradle.api.internal.tasks.execution.ValidatingTaskExecuter.execute(ValidatingTaskExecuter.java:59)
     *       at org.gradle.api.internal.tasks.execution.SkipEmptySourceFilesTaskExecuter.execute(SkipEmptySourceFilesTaskExecuter.java:101)
     *       at org.gradle.api.internal.tasks.execution.FinalizeInputFilePropertiesTaskExecuter.execute(FinalizeInputFilePropertiesTaskExecuter.java:44)
     *       at org.gradle.api.internal.tasks.execution.CleanupStaleOutputsExecuter.execute(CleanupStaleOutputsExecuter.java:91)
     *       at org.gradle.api.internal.tasks.execution.ResolveTaskArtifactStateTaskExecuter.execute(ResolveTaskArtifactStateTaskExecuter.java:62)
     *       at org.gradle.api.internal.tasks.execution.SkipTaskWithNoActionsExecuter.execute(SkipTaskWithNoActionsExecuter.java:59)
     *       at org.gradle.api.internal.tasks.execution.SkipOnlyIfTaskExecuter.execute(SkipOnlyIfTaskExecuter.java:54)
     *       at org.gradle.api.internal.tasks.execution.ExecuteAtMostOnceTaskExecuter.execute(ExecuteAtMostOnceTaskExecuter.java:43)
     *       at org.gradle.api.internal.tasks.execution.CatchExceptionTaskExecuter.execute(CatchExceptionTaskExecuter.java:34)
     *       at org.gradle.execution.taskgraph.DefaultTaskGraphExecuter$EventFiringTaskWorker$1.run(DefaultTaskGraphExecuter.java:256)
     *       at org.gradle.internal.progress.DefaultBuildOperationExecutor$RunnableBuildOperationWorker.execute(DefaultBuildOperationExecutor.java:336)
     *       at org.gradle.internal.progress.DefaultBuildOperationExecutor$RunnableBuildOperationWorker.execute(DefaultBuildOperationExecutor.java:328)
     *       at org.gradle.internal.progress.DefaultBuildOperationExecutor.execute(DefaultBuildOperationExecutor.java:199)
     *       at org.gradle.internal.progress.DefaultBuildOperationExecutor.run(DefaultBuildOperationExecutor.java:110)
     *       at org.gradle.execution.taskgraph.DefaultTaskGraphExecuter$EventFiringTaskWorker.execute(DefaultTaskGraphExecuter.java:249)
     *       at org.gradle.execution.taskgraph.DefaultTaskGraphExecuter$EventFiringTaskWorker.execute(DefaultTaskGraphExecuter.java:238)
     *       at org.gradle.execution.taskgraph.DefaultTaskPlanExecutor$TaskExecutorWorker.processTask(DefaultTaskPlanExecutor.java:123)
     *       at org.gradle.execution.taskgraph.DefaultTaskPlanExecutor$TaskExecutorWorker.access$200(DefaultTaskPlanExecutor.java:79)
     *       at org.gradle.execution.taskgraph.DefaultTaskPlanExecutor$TaskExecutorWorker$1.execute(DefaultTaskPlanExecutor.java:104)
     *       at org.gradle.execution.taskgraph.DefaultTaskPlanExecutor$TaskExecutorWorker$1.execute(DefaultTaskPlanExecutor.java:98)
     *       at org.gradle.execution.taskgraph.DefaultTaskExecutionPlan.execute(DefaultTaskExecutionPlan.java:663)
     *       at org.gradle.execution.taskgraph.DefaultTaskExecutionPlan.executeWithTask(DefaultTaskExecutionPlan.java:597)
     *       at org.gradle.execution.taskgraph.DefaultTaskPlanExecutor$TaskExecutorWorker.run(DefaultTaskPlanExecutor.java:98)
     *       at org.gradle.internal.concurrent.ExecutorPolicy$CatchAndRecordFailures.onExecute(ExecutorPolicy.java:63)
     *       at org.gradle.internal.concurrent.ManagedExecutorImpl$1.run(ManagedExecutorImpl.java:46)
     *       at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1142)
     *       at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:617)
     *       at org.gradle.internal.concurrent.ThreadFactoryImpl$ManagedThreadRunnable.run(ThreadFactoryImpl.java:55)
     *       at java.lang.Thread.run(Thread.java:745)
     *
     * 原来Arouter的注解的变量不能是private的，f**k!
     *
     */
    void a31(){}


    /**
     * 使用jsbridge webview报错
     * java.lang.NoClassDefFoundError: Failed resolution of: Landroid/webkit/SafeBrowsingResponse
     *
     *  if (Build.VERSION.SDK_INT >= 26) {
     *             webSettings.setSafeBrowsingEnabled(false);
     *         }
     */
    void a32(){}


    /**
     * android studio  点击rebuild project 或者 clean project 无反应
     * 或者 android studio 报错（代码报错） 但是可以正常运行的问题
     *
     * https://blog.csdn.net/hx7013/article/details/79971980 （AndroidStudio 代码报错（引入红色），但可正常运行解决方法（4种方式））
     *
     * 这种一般就是有缓存
     *
     * 1.点击AndroidStudio菜单File -> Invalidate Caches/Restar
     *
     * 2.关闭AndroidStudio，进入工程根目录，删除.gradle、.idea两个文件夹。重新打开AndroidStudio会自动Sync，等待同步完成。
     *
     * 3.关闭AndroidStudio，删除C:\Users\xUser\.gradle。重新打开AndroidStudio会自动Sync，等待同步完成。
     * 注意:删除该文件夹所有的classpath、gradle、compile、implementation等，都需要重新从网上同步。
     *
     * 4.关闭AndroidStudio，进入C:\Users\xUser.AndroidStudio3.1(3.1为当前AS版本号)，删除除了config以外的文件。重新打开AndroidStudio会自动Sync，等待同步完成。
     *
     *
     *
     */
    void a33(){}


    /**
     * 今天遇到一个问题，注册push成功将regid 写入日志，但是到主界面查看log发现没有。。。而且log文件也没创建
     * 打断点debug一下，又发现可以。。没问题。。
     *
     * 后来想肯定是线程的问题，可能是吧文件给删了，然后看代码才发现在闪屏页上传日志成功后会删掉日志文件
     * 而我写入文件是在闪屏页启动前，所以你看不到日志。。。
     *
     */
    void a34(){}


    /**
     * dialog设置 dismissListener 内存泄漏，因为设置了这个 ，在dismiss的时候回发送一个handler的msg
     * 这个时候msg在handler中阻塞，然后Activity被finish了，就会内存泄漏。
     * Thread → Message → Listener → Dialog → Activity
     *
     * 我们不用 dismissListener就好了
     *
     *
     */
    void a35(){}


    /**
     * https://juejin.im/post/5847f1c4128fe1006c60f355 (时间校准解决方案)
     * 客户端在header 传timestamp给服务端，服务端校验时间不准，返回http code是200或者4xx都行
     * 我们拿到body，获取到errorcode和timestamp(这个是服务端接口的返回时间) ,然后和本地时间做差
     * 然后记录这个差值，在将当前时间加上差值时间去再次请求。
     * 如果并发也没关系，顶多每次回来都更新时间差，而不用管其他接口，只管自己接口重新请求即可
     *
     */
    void a36(){}

    /**
     * Rxjava 请求的问题，一个请求已经dispose() ，如果再发生异常，比如IO异常等
     * 那么Rxjava是不会捕获这个异常的，所以我们要设置一个全局捕获rxjava异常的方法
     * RxJavaPlugins.setErrorHandler()
     *
     */
    void a37(){}


    /**
     * ======== java.util.concurrent.TimeoutException :xxx.finalize timed out after 10 seconds==========
     * https://blog.csdn.net/a_Chaon/article/details/71908481 ( 从Daemons到finalize timed out after 10 seconds)
     * https://yq.aliyun.com/articles/225755 （ 再谈Finalizer对象--大型App中内存与性能的隐性杀手）
     *
     * java.lang.Daemons  Daemons开始于Zygote进程创建子进程.
     * 这个进程是负责GC回收的操作，它有四个线程
     * ReferenceQueueDaemon，对象被GC时，负责将被回收的对象加入回收队列
     * FinalizerDaemon : 对于重写了成员函数finalize的对象，它们被GC决定回收时，并没有马上被回收，而是被放入到一个队列中，等待FinalizerDaemon守护线程去调用它们的成员函数finalize，然后再被回收。
     * FinalizerWatchdogDaemon : 调用对象的 finalize时超出一定时间，那么就会退出VM。
     * HeapTaskDaemon : 堆裁剪守护线程。用来执行裁剪堆的操作，也就是用来将那些空闲的堆内存归还给系统
     *
     *
     * 这个bug就来自于  FinalizerWatchdogDaemon
     * 如果超过10秒调用
     *  System.exit(2);
     *  //0表示正常退出,0以上表示非正常退出.
     *  private static final int RUNNING = 0;
     *  private static final int HOOKS = 1;
     *  private static final int FINALIZERS = 2;
     *
     *
     * ==========java.util.concurrent.TimeoutException: android.view.ThreadedRenderer.finalize() timed out after 10 seconds =======
     * 网上说api19 以后把webview硬件加速关了就行，但是我发生这个bug的页面根本没有webview
     *
     * http://androidxref.com/7.0.0_r1/xref/frameworks/base/core/java/android/view/ThreadedRenderer.java
     * 他的finalize() 调用了native方法：  private static native void nDeleteProxy(long nativeProxy);
     * 然后阻塞超过了10秒。。。
     *
     * 这个类的作用
     * Hardware renderer that proxies the rendering to a render thread. Most calls
     * are currently synchronous.  一个硬件加速的渲染线程。。
     *
     * 这个bug也不是必现的，所以解决方法就是尽量优化view布局把，让视图释放的更快些。。
     */
    void a38(){}


    /**
     * 今天工程添加一个依赖，添加了maven{url 'xxx'}
     * 然后工程中添加依赖，aar包死活下载不下来，最后还得用比较法解决问题，
     * demo能下载，而我的不能下载，找差别，发现 allProject中没添加仓库地址。。
     */
    void a39(){}


    /**
     * Program type already present: com.alibaba.android.arouter.routes.ARouter$$Group$$correction$1
     *
     * 编译的时候报错，Arouter的group只能有一个，这个错说明，你项目中有多个重复的group
     * 最后看了一眼是 依赖了project 又依赖了那个project的aar。。。失误
     *
     *
     **/
    void a40(){}

    /**
     * Couldn't desugar invokedynamic for xxx
     * 报错在一个lambda表达式中 post(()->{})
     * 意思应该是不能拖语法糖
     * Android studio 3.0以上内置支持java8了 （17年10月）
     *
     * https://developer.android.google.cn/studio/write/java8-support.html （使用 Java 8 语言功能）
     * http://www.guodongkeji.com/newsshow-24-2484-1.html （AndroidStudio3 支持 Java8 了，就问你敢用吗）
     *
     * 后来解决方法还是换成 new runnable了，但是别的lambda表达式没事，不知道为啥。。。
     *
     **/
    void a41(){}


    /**
     * ========属性动画卡顿优化问题========
     * 有一个结果页展示 放大的属性动画，然后并行加载帧动画播放
     * 但是放大过程中有卡顿。。一开始认为帧动画加载耗时，放到子线程并行，
     * 但是还是卡一下。连接profiler看哪卡顿，结果一连接调试
     * app的接口就报错，到不了结果页。。。于是暂时放弃这个
     *
     * 按理来说属性动画不应该卡，所以注释了渐变效果，帧动画加载等逻辑，还是卡
     * 然后我post一下动画执行，想让他在别的都绘制完成后再执行，结果还是卡
     * postdelay 3秒就不卡了。看代码发现上面居然还有一个post，而且里面是
     * 调用invalidate()重绘，而且重绘的控件比较复杂，耗时。
     *
     * 于是我把这个动画放到invalidate（）后面 delay了300ms执行，就不卡了！！！！！
     *
     * -----总结----
     * 还是排除法，单独拿出来没事，在你这就不行了，肯定是哪写错了。注释掉，一点点排除
     *
     */
    void a42(){}


    /**
     * 子布局被剪裁问题
     *
     * 设置 clipChildren=false 无效。。。
     * 结果发现android:clipToPadding="false"也要加上
     * 父布局和祖父布局加上就行
     *
     */
    void a43(){}


    /**
     * 今天在Android 6.0到8.0上请求报错。。醉了
     * java.lang.IllegalArgumentException: Unable to create converter for XXXEntity
     * java.lang.SecurityException: Can not make a java.lang.reflect.Method constructor accessible
     *
     * 开始还以为 entity是kotlin写的原因。。。后来换成java的也不行
     * 后来发现是  XXXEntity里有个View字段，View 里面有Method类型的属性，所以就抛出异常了
     * 详细见
     *
     * 11:09:58.71  15854  WARN  System.err  1  Caused by: java.lang.SecurityException: Can not make a java.lang.reflect.Method constructor accessible
     * 11:09:58.71  15854  WARN  System.err  1  at java.lang.reflect.AccessibleObject.setAccessible0(AccessibleObject.java:131)
     * 11:09:58.71  15854  WARN  System.err  1  at java.lang.reflect.AccessibleObject.setAccessible(AccessibleObject.java:115)
     * 11:09:58.71  15854  WARN  System.err  1  at com.google.gson.internal.reflect.PreJava9ReflectionAccessor.makeAccessible(PreJava9ReflectionAccessor.java:31)
     * 11:09:58.71  15854  WARN  System.err  1  at com.google.gson.internal.ConstructorConstructor.newDefaultConstructor(ConstructorConstructor.java:103)
     * 11:09:58.71  15854  WARN  System.err  1  at com.google.gson.internal.ConstructorConstructor.get(ConstructorConstructor.java:85)
     * 11:09:58.71  15854  WARN  System.err  1  at com.google.gson.internal.bind.ReflectiveTypeAdapterFactory.create(ReflectiveTypeAdapterFactory.java:101)
     * 11:09:58.71  15854  WARN  System.err  1  at com.google.gson.Gson.getAdapter(Gson.java:458)
     * 11:09:58.71  15854  WARN  System.err  1  at com.google.gson.internal.bind.ReflectiveTypeAdapterFactory.createBoundField(ReflectiveTypeAdapterFactory.java:117)
     * 11:09:58.71  15854  WARN  System.err  1  at com.google.gson.internal.bind.ReflectiveTypeAdapterFactory.getBoundFields(ReflectiveTypeAdapterFactory.java:166)
     * 11:09:58.71  15854  WARN  System.err  1  at com.google.gson.internal.bind.ReflectiveTypeAdapterFactory.create(ReflectiveTypeAdapterFactory.java:102)
     * 11:09:58.71  15854  WARN  System.err  1  at com.google.gson.Gson.getAdapter(Gson.java:458)
     * 11:09:58.71  15854  WARN  System.err  1  at com.google.gson.internal.bind.ArrayTypeAdapter$1.create(ArrayTypeAdapter.java:48)
     * 11:09:58.71  15854  WARN  System.err  1  at com.google.gson.Gson.getAdapter(Gson.java:458)
     * 11:09:58.71  15854  WARN  System.err  1  at com.google.gson.internal.bind.ReflectiveTypeAdapterFactory.createBoundField(ReflectiveTypeAdapterFactory.java:117)
     * 11:09:58.71  15854  WARN  System.err  1  at com.google.gson.internal.bind.ReflectiveTypeAdapterFactory.getBoundFields(ReflectiveTypeAdapterFactory.java:166)
     * 11:09:58.71  15854  WARN  System.err  1  at com.google.gson.internal.bind.ReflectiveTypeAdapterFactory.create(ReflectiveTypeAdapterFactory.java:102)
     * 11:09:58.71  15854  WARN  System.err  1  at com.google.gson.Gson.getAdapter(Gson.java:458)
     * 11:09:58.71  15854  WARN  System.err  1  at com.google.gson.internal.bind.ReflectiveTypeAdapterFactory.createBoundField(ReflectiveTypeAdapterFactory.java:117)
     * 11:09:58.71  15854  WARN  System.err  1  at com.google.gson.internal.bind.ReflectiveTypeAdapterFactory.getBoundFields(ReflectiveTypeAdapterFactory.java:166)
     * 11:09:58.71  15854  WARN  System.err  1  at com.google.gson.internal.bind.ReflectiveTypeAdapterFactory.create(ReflectiveTypeAdapterFactory.java:102)
     * 11:09:58.71  15854  WARN  System.err  1  at com.google.gson.Gson.getAdapter(Gson.java:458)
     * 11:09:58.71  15854  WARN  System.err  1  at com.google.gson.internal.bind.ReflectiveTypeAdapterFactory.createBoundField(ReflectiveTypeAdapterFactory.java:117)
     * 11:09:58.71  15854  WARN  System.err  1  at com.google.gson.internal.bind.ReflectiveTypeAdapterFactory.getBoundFields(ReflectiveTypeAdapterFactory.java:166)
     * 11:09:58.71  15854  WARN  System.err  1  at com.google.gson.internal.bind.ReflectiveTypeAdapterFactory.create(ReflectiveTypeAdapterFactory.java:102)
     * 11:09:58.71  15854  WARN  System.err  1  at com.google.gson.Gson.getAdapter(Gson.java:458)
     * 11:09:58.71  15854  WARN  System.err  1  at com.google.gson.internal.bind.ReflectiveTypeAdapterFactory.createBoundField(ReflectiveTypeAdapterFactory.java:117)
     * 11:09:58.71  15854  WARN  System.err  1  at com.google.gson.internal.bind.ReflectiveTypeAdapterFactory.getBoundFields(ReflectiveTypeAdapterFactory.java:166)
     * 11:09:58.71  15854  WARN  System.err  1  at com.google.gson.internal.bind.ReflectiveTypeAdapterFactory.create(ReflectiveTypeAdapterFactory.java:102)
     * 11:09:58.71  15854  WARN  System.err  1  at com.google.gson.Gson.getAdapter(Gson.java:458)
     * 11:09:58.71  15854  WARN  System.err  1  at com.google.gson.internal.bind.CollectionTypeAdapterFactory.create(CollectionTypeAdapterFactory.java:53)
     * 11:09:58.71  15854  WARN  System.err  1  at com.google.gson.Gson.getAdapter(Gson.java:458)
     * 11:09:58.71  15854  WARN  System.err  1  at com.google.gson.internal.bind.ReflectiveTypeAdapterFactory.createBoundField(ReflectiveTypeAdapterFactory.java:117)
     * 11:09:58.71  15854  WARN  System.err  1  at com.google.gson.internal.bind.ReflectiveTypeAdapterFactory.getBoundFields(ReflectiveTypeAdapterFactory.java:166)
     * 11:09:58.71  15854  WARN  System.err  1  at com.google.gson.internal.bind.ReflectiveTypeAdapterFactory.create(ReflectiveTypeAdapterFactory.java:102)
     * 11:09:58.71  15854  WARN  System.err  1  at com.google.gson.Gson.getAdapter(Gson.java:458)
     * 11:09:58.71  15854  WARN  System.err  1  at retrofit2.converter.gson.GsonConverterFactory.responseBodyConverter(GsonConverterFactory.java:64)
     * 11:09:58.71  15854  WARN  System.err  1  at retrofit2.Retrofit.nextResponseBodyConverter(Retrofit.java:330)
     * 11:09:58.71  15854  WARN  System.err  1  at retrofit2.Retrofit.responseBodyConverter(Retrofit.java:313)
     * 11:09:58.71  15854  WARN  System.err  1  at retrofit2.HttpServiceMethod.createResponseConverter(HttpServiceMethod.java:113)
     * 11:09:58.71  15854  WARN  System.err  1  ... 29 more
     *
     */
    void a44(){}
}
