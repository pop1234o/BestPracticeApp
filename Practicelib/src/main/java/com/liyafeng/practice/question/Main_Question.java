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
     * android.view.WindowLeaked: Activity com.tal.brandy.ui.activity.video.VideoActivity has leaked window DecorView@b38aa27[] that was originally added here
     *         at android.view.ViewRootImpl.<init>(ViewRootImpl.java:496)
     *         at android.view.WindowManagerGlobal.addView(WindowManagerGlobal.java:349)
     *         at android.view.WindowManagerImpl.addView(WindowManagerImpl.java:94)
     *         at android.app.Dialog.show(Dialog.java:332)
     *         at com.tal.brandy.ui.dialog.QuitDialog.showDialog
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
     *     Process: com.tal.brandy, PID: 4773
     *     java.lang.UnsupportedOperationException
     *         at java.util.AbstractList.add(AbstractList.java:148)
     *         at java.util.AbstractList.add(AbstractList.java:108)
     *         at java.util.AbstractCollection.addAll(AbstractCollection.java:344)
     *         at com.tal.brandy.ui.activity.video.BaseVideoActivity$11.onUserUpdate(BaseVideoActivity.java:593)
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
     *
     * x86_64
     *
     * x86
     *
     * mips64
     *
     * mips
     *
     * armeabi-v7a
     *
     * armeabi
     *
     * arm64-v8a
     *
     * 问题二、系统是怎么查找so库的呢？
     *
     * 查找so库规则：
     *
     *       运行的时候，系统会到Jnidirs目录里查找so库，会根据当前平台架构查找对应的目录。这里面有一个规则是这个问题的元凶。当你只提供了armeabi目录时，armeabi-v7a、arm64-v8a架构的程序都会去armeabi里寻找，而当你同时也提供了armeabi-v7a、armeabi-v8a目录，而里面又不存在对应的so库时，系统就不会再去armeabi里面寻找了，直接找不到报错。
     *
     *       而签名包里面这七个目录都有，这个问题后面讨论。
     *
     * 验证猜想：
     *
     *       将app-release.apk使用打包软件打开，删除其中的armeabi-v7a目录，发现联想手机可以正常运行了（去armeabi里面寻找了）。移除x86_64文件夹，模拟器可以正常运行（去x86里面寻找了）。
     *
     *  问题三、签名包为什么七个目录全都有？
     *
     *       对应Jnidirs目录中的七个目录。打开签名包，发现这七个目录都有。奇怪的是我的项目里只有x86、armeabi和arm64-v8a三个类型的so库。
     *       打开其他的目录发现都是只有一个so文件：libgenius_graphics.so。百思不得其解这个从何而来，查找build-gradle的时候发现：compile 'net.qiujuer.genius:graphics:2.0.0-beta8' 非常相似，这是一个开源的UI库。猜测是引入这个库在编译的时候自动生成各个平台下的libgenius_graphics.so文件。移除之，rebuild，确实如此，只剩下原本的三个文件夹
     *
     * 查阅许多文档都说x86是兼容armeabi的so文件的，验证时必须保证x86文件夹为空，或者根本没有x86文件夹)
     *
     */
    void a20(){}


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
     *     java.lang.SecurityException: Permission Denial: reading android.support.v4.content.FileProvider uri content://com.tal.brandy.fileProvider/download/appv1.0.0.apk from pid=9663, uid=10018 requires the provider be exported, or grantUriPermission()
     *         at android.content.ContentProvider.enforceReadPermissionInner(ContentProvider.java:777)
     *         at android.content.ContentProvider$Transport.enforceReadPermission(ContentProvider.java:540)
     *         at android.content.ContentProvider$Transport.enforceFilePermission(ContentProvider.java:530)
     *         at android.content.ContentProvider$Transport.openTypedAssetFile(ContentProvider.java:458)
     *         at android.content.ContentProviderNative.onTransact(ContentProviderNative.java:302)
     *         at android.os.Binder.execTransact(Binder.java:708)
     * 2019-05-24 20:39:14.517 9663-15818/? W/InstallStaging: Error staging apk from content URI
     *     java.lang.SecurityException: Permission Denial: reading android.support.v4.content.FileProvider uri content://com.tal.brandy.fileProvider/download/appv1.0.0.apk from pid=9663, uid=10018 requires the provider be exported, or grantUriPermission()
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
     *     java.io.FileNotFoundException: No content provider: content://com.tal.brandy.fileProvider/download/app1.0.3.apk
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
}
