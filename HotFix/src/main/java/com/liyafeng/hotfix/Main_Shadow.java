package com.liyafeng.hotfix;

public class Main_Shadow {

    /**
     *
     *

     ===========插件化==============

     插件编译 成apk，上传

     host负责下载，加载

     =========要解决的问题====
     自己编译好的 shadow sdk是放哪了？自己搭建的的maven仓库么，还是集团的？

     业务插件依赖底层common库 ，中baseActivity ，basePresenter  还有share库等底层支持
     依赖底层的layout drawable  titlebar  这个是共用的（一般肯定是共用的，否则你改逻辑 多个插件都要改，不得疯）


     三个host manager plugin 是三个不同的代码库？
     如果是三个通用的部分怎么解决？比如通用loading，titlebar ，分享，网络请求等等

     如何调试插件中的代码？开发和测试和上线有什么区别么？

     登录态等一些共用的数据和状态？是用过进程通信来获取的么？跨进程sp问题？

     插件的崩溃上报问题？还是用bugly初始化一下就行么？


     =========目标=====
     一个module如何变为插件？
     测试把插件复制到assets中，然后复制到下载目录
     宿主如何加载？ 通过Arouter跳转页面，

     开发时候本地依赖，测试时候用assets插件形式，线上用下载插件形式
     可以通过配置环境变量来区分

     so加载问题

     ============qa===========
     1
     manager代码运行在哪个进程？插件代码呢？
     manager在主进程，插件代码运行在 com.tal.monkey:plugin

     2
     插件调用宿主中的类，资源可以共用，还是分开打包，分开打包会有冲突么？
     需要分开依赖打包，公共代码如果host和plugin必须都依赖，然后会有两份代码和资源打包到host和plugin中
     因为他们两个在不一样的进程，所以 图片 文字，类，等同名的不冲突

     3
     host和plugin的 sp，getfiles 等等目录是一个么？ 不是一个，sp不能共用

     /data/user/0/com.tal.monkey/files/ShadowPlugin_demo   会创建一个子文件夹
     /storage/emulated/0/Android/data/com.tal.monkey/cache/ShadowPlugin_demo

     4 Application是一个类么？
     不是
     androidx.multidex.MultiDexApplication@eaaeb76
     你也可以指定吧？对，也可以xml中指定，见source的代码


     5
     插件进程是在加载插件的时候创建的


     6
     插件的公共部分，比如网络等等，也需要初始化逻辑


     =========shadow=======
     sample-host 宿主app

     PluginLoadActivity 相当于加载界面

     sample-host-lib 是模拟 sample-host的依赖库


     sample-manager 插件管理 app，也是动态加载的，主要用来安装插件，然后加载
     里面有callback onShowLoadingView onCloseLoadingView



     sample-plugin中是各种类型插件的示例  ，
     sample-plugin/sample-app-lib 是示例中 sample-manager 跳转的插件 ,跳转是 SplashActivity，这个是在 宿主Activity指定的类名

     sample-app-lib 编译后生成的插件，在根目录build/plugin-debug.zip下，这个zip中有插件zpk和runtime 和loader
     这个生成zip的逻辑在 simple-plugin-app/build.gradle中
     apply plugin: 'com.tencent.shadow.plugin'


     sample-plugin/sample-runtime 定义host代理组件的实际类
     sample-plugin/sample-loader host代理组件 和 插件组件 的对应关系
     host预注册的组件 对应 插件中Activity的关系？？

     * `sample-plugin/sample-normal-app`是一个apk模块壳子，将`sample-app-lib`打包在其中，演示业务App是可以正常安装运行的。
     * `sample-plugin/sample-plugin-app`也是一个apk模块壳子，同样将`sample-app-lib`打包在其中，但是额外应用了Shadow插件，生成的apk不能正常安装运行。


     maven是远程二进制依赖的示例，需要用Android studio分别打开 三个工程运行
     plugin-project 需要手动编译，然后把  adb push build/plugin-debug.zip /data/local/tmp
     manager-project 也需要手动编译 adb push sample-manager/build/outputs/apk/debug/sample-manager-debug.apk /data/local/tmp
     这样是模拟了已经下载好的过程
     然后运行 host-project 即可


     projects/sample/sunflower 同maven项目的结构，只是 https://github.com/android/sunflower/ 改造成插件化



     //注意sample-host-lib要用compileOnly编译而不打包在插件中。在packagePlugin任务中配置hostWhiteList允许插件访问宿主的类



     宿主加载插件管理器apk，插件管理器安装插件zip，然后调用 startPluginActivity


     =========依赖
     sample中source是源码依赖，用的是maven.gradle中的脚本来实现
     里面定义了 coreGroupId = com.tencent.shadow.core
     dynamicGroupId = 'com.tencent.shadow.dynamic'



     maven是二进制依赖
     特别注意，这3个工程中以maven方式引用的SDK，都是需要自行发布到`mavenLocal()`才能使用的。


     shadow sdk没有发布二进制到maven，得自己编译发布。。。



     =========项目中应用
     配置好 manager工程，目标是可以单独 assemble 出一个manager的apk

     配置好 loader 和 runtime工程， 然后 配置plugin工程，配置shadow打包插件
     目标是可以生成plugin-debug.zip
     配置了插件 apply plugin: 'com.tencent.shadow.plugin'
     然后执行Task中的plugin/packageReleasePlugin即可生成zip插件


     有了这两个，宿主就能加载插件了

     注：assembleDebug在Task的other目录下

     FixedPathPmUpdater是manager.apk更新管理器

     =============流程=====
     PluginLoadActivity中
     HostApplication.loadPluginManager(PluginHelper.getInstance().pluginManagerFile);
     加载插件管理器.apk ，然后调用
     HostApplication.getPluginManager().enter()
     就能走入 加载插件管理器.apk 中的代码了

     PluginProcessPPS是在宿主中注册的  宿主中注册的PluginProcessService实现的类名


     =========配置白名单
     这样隔离的目的是让宿主apk中的类可以通过约定的接口使用插件apk中的实现
     宿主使用插件中的实现？？ApkClassLoader
     回去 apk中找 com.tencent.shadow.dynamic.impl.WhiteList

     示例见source的  SamplePluginLoader 的 LoadPluginCallback.getCallback().beforeLoadPlugin（）
     这个示例是loader中访问宿主的类。。

     LoadPluginCallback是宿主中的类，然而通过这个类也可以访问宿主中的静态成员变量了




     目地：插件访问宿主中的类
     步骤：
     需要在插件的loader中配置
     package com.tencent.shadow.dynamic.impl;

     /**
     * 此类包名及类名固定
     * classLoader的白名单
     * PluginLoader可以加载宿主中位于白名单内的类
     *
    public interface WhiteList {
        String[] sWhiteList = new String[]
                {
                        "com.tencent.shadow.sample.host.lib",
                };
    }


    manager也可以配置白名单，访问宿主中的类
    在 com.tencent.shadow.dynamic.host.ImplLoader 中读取apk对应目录下的白名单。。
    看代码白名单只限于manager和loader
    看 com.tencent.shadow.dynamic.host.ApkClassLoader 中处理白名单的逻辑，必须配置父包名在白名单中。。。



    Caused by: java.lang.ClassNotFoundException: Didn't find class "com.tal.utils.AppUtils" on path: DexPathList[[zip file "/data/user/0/com.tal.monkey/files/ShadowPluginManager/UnpackedPlugin/sample-manager/b9c7595644df1e0d44e02288bdbaf418/plugin-release.zip/module_plugin_runtime-release.apk"],nativeLibraryDirectories=[/data/user/0/com.tal.monkey/files/ShadowPluginManager/UnpackedPlugin/sample-manager/lib/0A040A6D-8463-4D50-A56A-0B2E2F8A21F9_lib, /system/lib, /hw_product/lib]]
    at dalvik.system.BaseDexClassLoader.findClass(BaseDexClassLoader.java:209)
    at java.lang.ClassLoader.loadClass(ClassLoader.java:379)
    at java.lang.ClassLoader.loadClass(ClassLoader.java:312)
    at com.tencent.shadow.core.loader.classloaders.PluginClassLoader.loadClass(Unknown Source:90)
    at java.lang.ClassLoader.loadClass(ClassLoader.java:312)
    at com.tal.module_plugin_test.MainActivity.onCreate(MainActivity.java:25) 
    at com.tencent.shadow.core.loader.delegates.ShadowActivityDelegate.onCreate(Unknown Source:371) 
    at com.tencent.shadow.core.runtime.container.PluginContainerActivity.onCreate(PluginContainerActivity.java:84) 
    at android.app.Activity.performCreate(Activity.java:8085) 
    at android.app.Activity.performCreate(Activity.java:8073) 
    at android.app.Instrumentation.callActivityOnCreate(Instrumentation.java:1320) 
    at android.app.ActivityThread.performLaunchActivity(ActivityThread.java:3868) 
    at android.app.ActivityThread.handleLaunchActivity(ActivityThread.java:4074) 
    at android.app.servertransaction.LaunchActivityItem.execute(LaunchActivityItem.java:91) 
    at android.app.servertransaction.TransactionExecutor.executeCallbacks(TransactionExecutor.java:149) 
    at android.app.servertransaction.TransactionExecutor.execute(TransactionExecutor.java:103) 
    at android.app.ActivityThread$H.handleMessage(ActivityThread.java:2473) 
    at android.os.Handler.dispatchMessage(Handler.java:110) 
    at android.os.Looper.loop(Looper.java:219) 
    at android.app.ActivityThread.main(ActivityThread.java:8347) 
    at java.lang.reflect.Method.invoke(Native Method) 
    at com.android.internal.os.RuntimeInit$MethodAndArgsCaller.run(RuntimeInit.java:513) 
    at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:1055) 


            -----白名单

            参考
    //注意sample-host-lib要用compileOnly编译而不打包在插件中。在packagePlugin任务中配置hostWhiteList允许插件访问宿主的类。

    com.tencent.shadow.sample.plugin.app.lib.usecases.host_communication.PluginUseHostClassActivity

    需要在插件的build.gradle脚本中配置
    hostWhiteList = ["com.tencent.shadow.sample.host.lib"]

    Caused by: java.lang.ClassNotFoundException: Didn't find class "com.tal.lib_common.entity.UserInfoEntity" on path: DexPathList[[zip file "/data/user/0/com.tal.monkey/files/ShadowPluginManager/UnpackedPlugin/sample-manager/c681576ff234082256d4f2bacf692949/plugin-release.zip/module_plugin_runtime-release.apk"],nativeLibraryDirectories=[/data/user/0/com.tal.monkey/files/ShadowPluginManager/UnpackedPlugin/sample-manager/lib/9EF92971-E6AC-4997-BE45-C6688C50334F_lib, /system/lib, /hw_product/lib]]
    at dalvik.system.BaseDexClassLoader.findClass(BaseDexClassLoader.java:209)
    at java.lang.ClassLoader.loadClass(ClassLoader.java:379)
    at java.lang.ClassLoader.loadClass(ClassLoader.java:312)
    at com.tencent.shadow.core.loader.classloaders.PluginClassLoader.loadClass(Unknown Source:90)
    at java.lang.ClassLoader.loadClass(ClassLoader.java:312)
    at com.tal.module_plugin_test.MainActivity.onCreate(MainActivity.java:36) 
    at com.tencent.shadow.core.loader.delegates.ShadowActivityDelegate.onCreate(Unknown Source:371) 
    at com.tencent.shadow.core.runtime.container.PluginContainerActivity.onCreate(PluginContainerActivity.java:84) 
    at android.app.Activity.performCreate(Activity.java:8085) 

    要把所有关联的类都加入白名单，而且是必须是完整包路径！！！

    而且获取的类加载的类对象都和宿主是共用的，说明是一个类文件对象！这就可以跨进程通信了

================插件和宿主通信=====================

    由于插件ClassLoader的parent是宿主的ClassLoader，所以插件的ClassLoader中的类型不能在宿主中直接使用。
    最好的方式还是通过依赖反转设计，让插件向宿主注入接口的实现。接口由宿主定义，并写到白名单中。


    插件调用宿主的类，可以通过插件的白名单配置，用complieOnly 方式引用宿主的类
    宿主用插件中的方法，可以通过宿主定义接口，然后插件注入接口的实现，宿主直接调用注入的实例即可

    图片资源和字符串，公共布局





=============问题
    adb: failed to install /Users/pop/Documents/mypro/monkey-homework-android/app/build/outputs/apk/monkey_oral_android/beta/app-monkey_oral_android-beta.apk: Failure [INSTALL_FAILED_CONFLICTING_PROVIDER: Scanning Failed.: Can't install because provider name com.tencent.shadow.contentprovider.authority.dynamic (in package com.tal.monkey) is already used by com.tencent.shadow.sample.host]

    已经有app在用这个了，换个名字

=============bug
2020-11-14 18:16:40.376 20858-22179/com.tal.monkey E/CrashReport: java.lang.ExceptionInInitializerError
    at com.tal.module_plugin.manager.Shadow.getPluginManager(Shadow.java:32)
    at com.tal.module_plugin.process.HostApplication.loadPluginManager(HostApplication.java:96)
    at com.tal.module_plugin.process.PluginLoadActivity$1.run(PluginLoadActivity.java:56)
    at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1167)
    at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:641)
    at java.lang.Thread.run(Thread.java:929)
    Caused by: java.lang.RuntimeException: 没有找到 ILoggerFactory 实现，请先调用setILoggerFactory
    at com.tencent.shadow.core.common.LoggerFactory.getILoggerFactory(LoggerFactory.java:39)
    at com.tencent.shadow.core.common.LoggerFactory.getLogger(LoggerFactory.java:33)
    at com.tencent.shadow.dynamic.host.DynamicPluginManager.<clinit>(DynamicPluginManager.java:34)
    at com.tal.module_plugin.manager.Shadow.getPluginManager(Shadow.java:32) 
    at com.tal.module_plugin.process.HostApplication.loadPluginManager(HostApplication.java:96) 
    at com.tal.module_plugin.process.PluginLoadActivity$1.run(PluginLoadActivity.java:56) 
    at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1167) 
    at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:641) 
    at java.lang.Thread.run(Thread.java:929) 

    要设置  setILoggerFactory

============bug
    Process: com.tal.monkey, PID: 22793
    java.lang.RuntimeException: Unable to create application com.tal.monkey.MonkeyApplication: java.lang.IllegalStateException: Can't set data directory suffix: WebView already initialized
    at android.app.ActivityThread.handleBindApplication(ActivityThread.java:7365)
    at android.app.ActivityThread.access$2400(ActivityThread.java:308)
    at android.app.ActivityThread$H.handleMessage(ActivityThread.java:2295)
    at android.os.Handler.dispatchMessage(Handler.java:110)
    at android.os.Looper.loop(Looper.java:219)
    at android.app.ActivityThread.main(ActivityThread.java:8347)
    at java.lang.reflect.Method.invoke(Native Method)
    at com.android.internal.os.RuntimeInit$MethodAndArgsCaller.run(RuntimeInit.java:513)
    at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:1055)
    Caused by: java.lang.IllegalStateException: Can't set data directory suffix: WebView already initialized
    at android.webkit.WebViewFactory.setDataDirectorySuffix(WebViewFactory.java:136)
    at android.webkit.WebView.setDataDirectorySuffix(WebView.java:2058)
    at com.tal.module_plugin.process.HostApplication.setWebViewDataDirectorySuffix(HostApplication.java:84)
    at com.tal.module_plugin.process.HostApplication.init(HostApplication.java:67)
    at com.tal.monkey.MonkeyApplication.onCreate(MonkeyApplication.java:41)
    at android.app.Instrumentation.callApplicationOnCreate(Instrumentation.java:1202)
    at android.app.ActivityThread.handleBindApplication(ActivityThread.java:7349)
    at android.app.ActivityThread.access$2400(ActivityThread.java:308) 
    at android.app.ActivityThread$H.handleMessage(ActivityThread.java:2295) 
    at android.os.Handler.dispatchMessage(Handler.java:110) 
    at android.os.Looper.loop(Looper.java:219) 
    at android.app.ActivityThread.main(ActivityThread.java:8347) 
    at java.lang.reflect.Method.invoke(Native Method) 
    at com.android.internal.os.RuntimeInit$MethodAndArgsCaller.run(RuntimeInit.java:513) 
    at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:1055) 

    多进程共享webview目录问题，只设置一次即可


========bug
    Process: com.tal.monkey, PID: 23146
    java.lang.RuntimeException: java.lang.RuntimeException: java.io.FileNotFoundException: /data/local/tmp/plugin-debug.zip: open failed: ENOENT (No such file or directory)
    at com.tal.module_plugin_manager.manager.SamplePluginManager$2.run(SamplePluginManager.java:161)
    at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1167)
    at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:641)
    at java.lang.Thread.run(Thread.java:929)
    Caused by: java.lang.RuntimeException: java.io.FileNotFoundException: /data/local/tmp/plugin-debug.zip: open failed: ENOENT (No such file or directory)
    at com.tencent.shadow.core.manager.installplugin.MinFileUtils.md5File(MinFileUtils.java:79)
    at com.tencent.shadow.core.manager.installplugin.UnpackManager.unpackPlugin(UnpackManager.java:104)
    at com.tencent.shadow.core.manager.BasePluginManager.installPluginFromZip(BasePluginManager.java:98)
    at com.tal.module_plugin_manager.manager.FastPluginManager.installPlugin(FastPluginManager.java:43)
    at com.tal.module_plugin_manager.manager.SamplePluginManager$2.run(SamplePluginManager.java:131)
    at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1167) 
    at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:641) 
    at java.lang.Thread.run(Thread.java:929) 
    Caused by: java.io.FileNotFoundException: /data/local/tmp/plugin-debug.zip: open failed: ENOENT (No such file or directory)
    at libcore.io.IoBridge.open(IoBridge.java:496)
    at java.io.FileInputStream.<init>(FileInputStream.java:159)
    at com.tencent.shadow.core.manager.installplugin.MinFileUtils.md5File(MinFileUtils.java:72)
    at com.tencent.shadow.core.manager.installplugin.UnpackManager.unpackPlugin(UnpackManager.java:104) 
    at com.tencent.shadow.core.manager.BasePluginManager.installPluginFromZip(BasePluginManager.java:98) 
    at com.tal.module_plugin_manager.manager.FastPluginManager.installPlugin(FastPluginManager.java:43) 
    at com.tal.module_plugin_manager.manager.SamplePluginManager$2.run(SamplePluginManager.java:131) 
    at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1167) 
    at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:641) 
    at java.lang.Thread.run(Thread.java:929) 
    Caused by: android.system.ErrnoException: open failed: ENOENT (No such file or directory)
    at libcore.io.Linux.open(Native Method)
    at libcore.io.ForwardingOs.open(ForwardingOs.java:167)
    at libcore.io.BlockGuardOs.open(BlockGuardOs.java:252)
    at libcore.io.ForwardingOs.open(ForwardingOs.java:167)
    at android.app.ActivityThread$AndroidOs.open(ActivityThread.java:8196)
    at libcore.io.IoBridge.open(IoBridge.java:482)
    at java.io.FileInputStream.<init>(FileInputStream.java:159) 
    at com.tencent.shadow.core.manager.installplugin.MinFileUtils.md5File(MinFileUtils.java:72) 
    at com.tencent.shadow.core.manager.installplugin.UnpackManager.unpackPlugin(UnpackManager.java:104) 
    at com.tencent.shadow.core.manager.BasePluginManager.installPluginFromZip(BasePluginManager.java:98) 
    at com.tal.module_plugin_manager.manager.FastPluginManager.installPlugin(FastPluginManager.java:43) 
    at com.tal.module_plugin_manager.manager.SamplePluginManager$2.run(SamplePluginManager.java:131) 
    at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1167) 
    at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:641) 
    at java.lang.Thread.run(Thread.java:929) 

    host 的constant要和plugin的一致，要不启动错误了。。。 而且插件的路径要写对。。。。

            =========bug

    java.lang.RuntimeException: java.lang.IllegalArgumentException: 无法绑定PPS:com.tencent.shadow.sample.introduce_shadow_lib.MainPluginProcessService
    at com.tal.module_plugin_manager.manager.SamplePluginManager$1.run(SamplePluginManager.java:99)
    at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1167)
    at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:641)
    at java.lang.Thread.run(Thread.java:929)
    Caused by: java.lang.IllegalArgumentException: 无法绑定PPS:com.tencent.shadow.sample.introduce_shadow_lib.MainPluginProcessService
    at com.tencent.shadow.dynamic.manager.BaseDynamicPluginManager.bindPluginProcessService(BaseDynamicPluginManager.java:109)
    at com.tal.module_plugin_manager.manager.FastPluginManager.loadPluginLoaderAndRuntime(FastPluginManager.java:113)
    at com.tal.module_plugin_manager.manager.FastPluginManager.loadPlugin(FastPluginManager.java:121)
    at com.tal.module_plugin_manager.manager.FastPluginManager.convertActivityIntent(FastPluginManager.java:107)
    at com.tal.module_plugin_manager.manager.FastPluginManager.startPluginActivity(FastPluginManager.java:99)
    at com.tal.module_plugin_manager.manager.SamplePluginManager$1.run(SamplePluginManager.java:97)
    at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1167) 
    at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:641) 
    at java.lang.Thread.run(Thread.java:929) 


    宿主中注册的PluginProcessService实现的类名 ,要在 SamplePluginManager 中写正确

==========bug
    Process: com.tal.monkey, PID: 26730
    java.lang.RuntimeException: java.lang.NullPointerException
    at com.tal.module_plugin_manager.manager.SamplePluginManager$1.run(SamplePluginManager.java:99)
    at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1167)
    at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:641)
    at java.lang.Thread.run(Thread.java:929)
    Caused by: java.lang.NullPointerException
    at android.os.Parcel.createException(Parcel.java:2077)
    at android.os.Parcel.readException(Parcel.java:2039)
    at android.os.Parcel.readException(Parcel.java:1987)
    at com.tencent.shadow.dynamic.manager.BinderPluginLoader.loadPlugin(BinderPluginLoader.java:47)
    at com.tal.module_plugin_manager.manager.FastPluginManager.loadPlugin(FastPluginManager.java:124)
    at com.tal.module_plugin_manager.manager.FastPluginManager.convertActivityIntent(FastPluginManager.java:107)
    at com.tal.module_plugin_manager.manager.FastPluginManager.startPluginActivity(FastPluginManager.java:99)
    at com.tal.module_plugin_manager.manager.SamplePluginManager$1.run(SamplePluginManager.java:97)
    at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1167) 
    at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:641) 
    at java.lang.Thread.run(Thread.java:929) 



    看样子manager中的代码还是在 Process: com.tal.monkey, 中执行？

    java.lang.NullPointerException
    at android.os.Parcel.createException(Parcel.java:2077)
    at android.os.Parcel.readException(Parcel.java:2039)
    at android.os.Parcel.readException(Parcel.java:1987)
    at com.tencent.shadow.dynamic.manager.BinderPluginLoader.loadPlugin(BinderPluginLoader.java:47)
    at com.tal.module_plugin_manager.manager.FastPluginManager.loadPlugin(FastPluginManager.java:124)
    at com.tal.module_plugin_manager.manager.FastPluginManager.convertActivityIntent(FastPluginManager.java:107)
    at com.tal.module_plugin_manager.manager.FastPluginManager.startPluginActivity(FastPluginManager.java:99)
    at com.tal.module_plugin_manager.manager.SamplePluginManager$1.run(SamplePluginManager.java:97)
    at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1167)
    at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:641)
    at java.lang.Thread.run(Thread.java:929)

    uuid  9CE7524B-6B93-4CC2-AB7D-1668A8DB69D4  partKey  null
    partkey没传。。。


            =============bug
    Process: com.tal.monkey, PID: 30791
    java.lang.RuntimeException: android.content.ActivityNotFoundException: Unable to find explicit activity class {com.tal.monkey/com.tencent.shadow.sample.runtime.PluginDefaultProxyActivity}; have you declared this activity in your AndroidManifest.xml?
    at com.tal.module_plugin_manager.manager.SamplePluginManager$1.run(SamplePluginManager.java:100)
    at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1167)
    at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:641)
    at java.lang.Thread.run(Thread.java:929)
    Caused by: android.content.ActivityNotFoundException: Unable to find explicit activity class {com.tal.monkey/com.tencent.shadow.sample.runtime.PluginDefaultProxyActivity}; have you declared this activity in your AndroidManifest.xml?
    at android.app.Instrumentation.checkStartActivityResult(Instrumentation.java:2118)
    at android.app.Instrumentation.execStartActivity(Instrumentation.java:1746)
    at android.app.Activity.startActivityForResult(Activity.java:5362)
    at android.app.Activity.startActivityForResult(Activity.java:5303)
    at android.app.Activity.startActivity(Activity.java:5733)
    at android.app.Activity.startActivity(Activity.java:5701)
    at com.tal.module_plugin_manager.manager.FastPluginManager.startPluginActivity(FastPluginManager.java:104)
    at com.tal.module_plugin_manager.manager.SamplePluginManager$1.run(SamplePluginManager.java:97)
    at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1167) 
    at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:641) 
    at java.lang.Thread.run(Thread.java:929) 

    注意在 插件 loader.apk 中 SampleComponentManager 会打开 PluginDefaultProxyActivity  （这个类在runtime.apk中定义）
    在宿主中提前注册好，各种启动模式和样式的Activity壳子


    SampleComponentManager 就是插件中的提前定义的壳子Activity（在host中注册过），然后代理 plugin中的Activity生命周期用的



===========依赖common后报错
            加入
    configurations {
        all {
            exclude module: 'httpclient'
            exclude module: 'commons-logging'
        }
    }



> Task :module_plugin_test:mergeDexRelease FAILED
    AGPBI: {"kind":"error","text":"Cannot fit requested classes in a single dex file (# methods: 78910 > 65536 ; # fields: 67565 > 65536)","sources":[{}],"tool":"D8"}
    com.android.builder.dexing.DexArchiveMergerException: Error while merging dex archives:
    The number of method references in a .dex file cannot exceed 64K.
    Learn how to resolve this issue at https://developer.android.com/tools/building/multidex.html
    at com.android.builder.dexing.D8DexArchiveMerger.getExceptionToRethrow(D8DexArchiveMerger.java:132)
    at com.android.builder.dexing.D8DexArchiveMerger.mergeDexArchives(D8DexArchiveMerger.java:119)
    at com.android.build.gradle.internal.transforms.DexMergerTransformCallable.call(DexMergerTransformCallable.java:102)
    at com.android.build.gradle.internal.tasks.DexMergingTaskRunnable.run(DexMergingTask.kt:445)
    at com.android.build.gradle.internal.tasks.Workers$ActionFacade.run(Workers.kt:348)
    at org.gradle.workers.internal.AdapterWorkAction.execute(AdapterWorkAction.java:50)
    at org.gradle.workers.internal.DefaultWorkerServer.execute(DefaultWorkerServer.java:47)
    at org.gradle.workers.internal.NoIsolationWorkerFactory$1$1$1.create(NoIsolationWorkerFactory.java:65)
    at org.gradle.workers.internal.NoIsolationWorkerFactory$1$1$1.create(NoIsolationWorkerFactory.java:61)
    at org.gradle.internal.classloader.ClassLoaderUtils.executeInClassloader(ClassLoaderUtils.java:98)
    at org.gradle.workers.internal.NoIsolationWorkerFactory$1$1.execute(NoIsolationWorkerFactory.java:61)
    at org.gradle.workers.internal.AbstractWorker$1.call(AbstractWorker.java:44)
    at org.gradle.workers.internal.AbstractWorker$1.call(AbstractWorker.java:41)
    at org.gradle.internal.operations.DefaultBuildOperationExecutor$CallableBuildOperationWorker.execute(DefaultBuildOperationExecutor.java:416)
    at org.gradle.internal.operations.DefaultBuildOperationExecutor$CallableBuildOperationWorker.execute(DefaultBuildOperationExecutor.java:406)
    at org.gradle.internal.operations.DefaultBuildOperationExecutor$1.execute(DefaultBuildOperationExecutor.java:165)
    at org.gradle.internal.operations.DefaultBuildOperationExecutor.execute(DefaultBuildOperationExecutor.java:250)
    at org.gradle.internal.operations.DefaultBuildOperationExecutor.execute(DefaultBuildOperationExecutor.java:158)
    at org.gradle.internal.operations.DefaultBuildOperationExecutor.call(DefaultBuildOperationExecutor.java:102)
    at org.gradle.internal.operations.DelegatingBuildOperationExecutor.call(DelegatingBuildOperationExecutor.java:36)
    at org.gradle.workers.internal.AbstractWorker.executeWrappedInBuildOperation(AbstractWorker.java:41)
    at org.gradle.workers.internal.NoIsolationWorkerFactory$1.execute(NoIsolationWorkerFactory.java:56)
    at org.gradle.workers.internal.DefaultWorkerExecutor$3.call(DefaultWorkerExecutor.java:215)
    at org.gradle.workers.internal.DefaultWorkerExecutor$3.call(DefaultWorkerExecutor.java:210)
    at java.util.concurrent.FutureTask.run(FutureTask.java:266)
    at org.gradle.internal.work.DefaultConditionalExecutionQueue$ExecutionRunner.runExecution(DefaultConditionalExecutionQueue.java:215)
    at org.gradle.internal.work.DefaultConditionalExecutionQueue$ExecutionRunner.runBatch(DefaultConditionalExecutionQueue.java:164)
    at org.gradle.internal.work.DefaultConditionalExecutionQueue$ExecutionRunner.run(DefaultConditionalExecutionQueue.java:131)
    at java.util.concurrent.Executors$RunnableAdapter.call(Executors.java:511)
    at java.util.concurrent.FutureTask.run(FutureTask.java:266)
    at org.gradle.internal.concurrent.ExecutorPolicy$CatchAndRecordFailures.onExecute(ExecutorPolicy.java:64)
    at org.gradle.internal.concurrent.ManagedExecutorImpl$1.run(ManagedExecutorImpl.java:48)
    at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149)
    at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
    at org.gradle.internal.concurrent.ThreadFactoryImpl$ManagedThreadRunnable.run(ThreadFactoryImpl.java:56)
    at java.lang.Thread.run(Thread.java:748)
    Caused by: com.android.tools.r8.CompilationFailedException: Compilation failed to complete
    at com.android.tools.r8.utils.O.a(:65)
    at com.android.tools.r8.D8.run(:11)
    at com.android.builder.dexing.D8DexArchiveMerger.mergeDexArchives(D8DexArchiveMerger.java:117)
            ... 34 more
    Caused by: com.android.tools.r8.utils.b: Error: null, Cannot fit requested classes in a single dex file (# methods: 78910 > 65536 ; # fields: 67565 > 65536)
    at com.android.tools.r8.utils.y0.a(:21)
    at com.android.tools.r8.dex.K.a(:56)
    at com.android.tools.r8.dex.K$h.a(:5)
    at com.android.tools.r8.dex.b.b(:15)
    at com.android.tools.r8.dex.b.a(:38)
    at com.android.tools.r8.D8.d(:87)
    at com.android.tools.r8.D8.b(:1)
    at com.android.tools.r8.utils.O.a(:30)
            ... 36 more

    加入         multiDexEnabled true 即可


========= 插件打不开了。。。卡在manager了

2020-11-14 19:54:33.321 7823-8546/com.tal.monkey W/com.tal.monkey: Unsupported class loader
2020-11-14 19:54:33.321 7823-7823/com.tal.monkey I/CommonDialogHelper: [MainActivity:onStop:679]: main onStop()
2020-11-14 19:54:33.321 7823-8550/com.tal.monkey W/com.tal.monkey: Unsupported class loader
2020-11-14 19:54:33.342 7823-8547/com.tal.monkey W/com.tal.monkey: Unsupported class loader
2020-11-14 19:54:33.371 7823-8547/com.tal.monkey W/com.tal.monkey: Failed execv(/system/bin/dex2oat --debuggable --instruction-set=arm --instruction-set-features=div,atomic_ldrd_strd,-armv8a --runtime-arg -Xhidden-api-policy:enabled --runtime-arg -Xrelocate --boot-image=/system/framework/boot.art --runtime-arg -Xms64m --runtime-arg -Xmx512m -j4 --instruction-set-variant=cortex-a15 --instruction-set-features=default --generate-mini-debug-info --debuggable --generate-mini-debug-info --dex-file=/data/user/0/com.tal.monkey/files/ShadowPluginManager/UnpackedPlugin/sample-manager/22ae68addde3fa80119cd991259f652/plugin-release.zip/module_plugin_loader-release.apk --output-vdex-fd=206 --oat-fd=208 --oat-location=/data/user/0/com.tal.monkey/files/ShadowPluginManager/UnpackedPlugin/sample-manager/22ae68addde3fa80119cd991259f652/plugin-release.zip/oat/arm/module_plugin_loader-release.odex --compiler-filter=quicken --class-loader-context=& --compilation-reason=dynamic-load) because non-0 exit status
2020

    重新创建一个plugin.zip就好了。。。这个不知道啥问题。。。。


            =============编译问题
    注意，packageRelease  需要你依赖的模块配置了对应的buildType 有release


=========bug

    Process: com.tal.monkey:plugin, PID: 31357
    java.lang.RuntimeException: Unable to start activity ComponentInfo{com.tal.monkey/com.tal.module_plugin_runtime.runtime.PluginDefaultProxyActivity}: java.lang.RuntimeException: java.lang.IllegalStateException: You need to use a Theme.AppCompat theme (or descendant) with this activity.
    at android.app.ActivityThread.performLaunchActivity(ActivityThread.java:3895)
    at android.app.ActivityThread.handleLaunchActivity(ActivityThread.java:4074)
    at android.app.servertransaction.LaunchActivityItem.execute(LaunchActivityItem.java:91)
    at android.app.servertransaction.TransactionExecutor.executeCallbacks(TransactionExecutor.java:149)
    at android.app.servertransaction.TransactionExecutor.execute(TransactionExecutor.java:103)
    at android.app.ActivityThread$H.handleMessage(ActivityThread.java:2473)
    at android.os.Handler.dispatchMessage(Handler.java:110)
    at android.os.Looper.loop(Looper.java:219)
    at android.app.ActivityThread.main(ActivityThread.java:8347)
    at java.lang.reflect.Method.invoke(Native Method)
    at com.android.internal.os.RuntimeInit$MethodAndArgsCaller.run(RuntimeInit.java:513)
    at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:1055)
    Caused by: java.lang.RuntimeException: java.lang.IllegalStateException: You need to use a Theme.AppCompat theme (or descendant) with this activity.
    at com.tencent.shadow.core.loader.delegates.ShadowActivityDelegate.onCreate(Unknown Source:380)
    at com.tencent.shadow.core.runtime.container.PluginContainerActivity.onCreate(PluginContainerActivity.java:84)
    at android.app.Activity.performCreate(Activity.java:8085)
    at android.app.Activity.performCreate(Activity.java:8073)
    at android.app.Instrumentation.callActivityOnCreate(Instrumentation.java:1320)
    at android.app.ActivityThread.performLaunchActivity(ActivityThread.java:3868)
    at android.app.ActivityThread.handleLaunchActivity(ActivityThread.java:4074) 
    at android.app.servertransaction.LaunchActivityItem.execute(LaunchActivityItem.java:91) 
    at android.app.servertransaction.TransactionExecutor.executeCallbacks(TransactionExecutor.java:149) 
    at android.app.servertransaction.TransactionExecutor.execute(TransactionExecutor.java:103) 
    at android.app.ActivityThread$H.handleMessage(ActivityThread.java:2473) 
    at android.os.Handler.dispatchMessage(Handler.java:110) 
    at android.os.Looper.loop(Looper.java:219) 
    at android.app.ActivityThread.main(ActivityThread.java:8347) 
    at java.lang.reflect.Method.invoke(Native Method) 
    at com.android.internal.os.RuntimeInit$MethodAndArgsCaller.run(RuntimeInit.java:513) 
    at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:1055) 
    Caused by: java.lang.IllegalStateException: You need to use a Theme.AppCompat theme (or descendant) with this activity.
    at androidx.appcompat.app.AppCompatDelegateImpl.x(Unknown Source:423)
    at androidx.appcompat.app.AppCompatDelegateImpl.y(Unknown Source:4)
    at androidx.appcompat.app.AppCompatDelegateImpl.c(Unknown Source:0)
    at androidx.appcompat.app.AppCompatActivity.setContentView(Unknown Source:4)
    at com.tal.lib_common.ui.activity.BaseActivity.onCreate(Unknown Source:16)
    at com.tencent.shadow.core.loader.delegates.ShadowActivityDelegate.onCreate(Unknown Source:371)
    at com.tencent.shadow.core.runtime.container.PluginContainerActivity.onCreate(PluginContainerActivity.java:84) 
    at android.app.Activity.performCreate(Activity.java:8085) 
    at android.app.Activity.performCreate(Activity.java:8073) 
    at android.app.Instrumentation.callActivityOnCreate(Instrumentation.java:1320) 
    at android.app.ActivityThread.performLaunchActivity(ActivityThread.java:3868) 
    at android.app.ActivityThread.handleLaunchActivity(ActivityThread.java:4074) 
    at android.app.servertransaction.LaunchActivityItem.execute(LaunchActivityItem.java:91) 
    at android.app.servertransaction.TransactionExecutor.executeCallbacks(TransactionExecutor.java:149) 
    at android.app.servertransaction.TransactionExecutor.execute(TransactionExecutor.java:103) 
    at android.app.ActivityThread$H.handleMessage(ActivityThread.java:2473) 
    at android.os.Handler.dispatchMessage(Handler.java:110) 
    at android.os.Looper.loop(Looper.java:219) 
    at android.app.ActivityThread.main(ActivityThread.java:8347) 
    at java.lang.reflect.Method.invoke(Native Method) 
    at com.android.internal.os.RuntimeInit$MethodAndArgsCaller.run(RuntimeInit.java:513) 
    at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:1055) 

    要添加主题
    android:theme="@style/Theme.AppCompat.Light.NoActionBar"


            ==========bug
    Caused by: d.a.a.a.c.b: ARouter::Init::Invoke init(context) first!
    at d.a.a.a.e.a.b(Unknown Source:34)
    at com.tal.lib_common.ui.activity.BaseActivity.onCreate(Unknown Source:19)

    没初始化的要初始化




========bug
    Process: com.tal.monkey:plugin, PID: 7328
    java.lang.NoClassDefFoundError: Failed resolution of: Lorg/apache/http/conn/scheme/SchemeRegistry;
    at com.tencent.open.utils.HttpUtils.getHttpClient(ProGuard:626)
    at com.tencent.open.utils.HttpUtils.openUrl2(ProGuard:506)
    at com.tencent.open.utils.f$1.run(ProGuard:197)
    Caused by: java.lang.ClassNotFoundException: Didn't find class "org.apache.http.conn.scheme.SchemeRegistry" on path: DexPathList[[zip file "/data/user/0/com.tal.monkey/files/ShadowPluginManager/UnpackedPlugin/sample-manager/4ff7737e5647fc0e3b9052567835a976/plugin-release.zip/module_plugin_runtime-release.apk"],nativeLibraryDirectories=[/data/user/0/com.tal.monkey/files/ShadowPluginManager/UnpackedPlugin/sample-manager/lib/AC1F624B-9493-481B-B81C-97B982CCBD82_lib, /system/lib, /hw_product/lib]]
    at dalvik.system.BaseDexClassLoader.findClass(BaseDexClassLoader.java:209)
    at java.lang.ClassLoader.loadClass(ClassLoader.java:379)
    at java.lang.ClassLoader.loadClass(ClassLoader.java:312)
    at com.tencent.shadow.core.loader.classloaders.PluginClassLoader.loadClass(Unknown Source:90)
    at java.lang.ClassLoader.loadClass(ClassLoader.java:312)
    at com.tencent.open.utils.HttpUtils.getHttpClient(ProGuard:626) 
    at com.tencent.open.utils.HttpUtils.openUrl2(ProGuard:506) 
    at com.tencent.open.utils.f$1.run(ProGuard:197) 
    Suppressed: java.lang.ClassNotFoundException: Didn't find class "org.apache.http.conn.scheme.SchemeRegistry" on path: DexPathList[[zip file "/data/user/0/com.tal.monkey/files/ShadowPluginManager/UnpackedPlugin/sample-manager/4ff7737e5647fc0e3b9052567835a976/plugin-release.zip/lib_printer-release.apk"],nativeLibraryDirectories=[/data/user/0/com.tal.monkey/files/ShadowPluginManager/UnpackedPlugin/sample-manager/lib/AC1F624B-9493-481B-B81C-97B982CCBD82_lib, /system/lib, /hw_product/lib]]
    at dalvik.system.BaseDexClassLoader.findClass(BaseDexClassLoader.java:209)
    at com.tencent.shadow.core.loader.classloaders.PluginClassLoader.loadClass(Unknown Source:75)
            ... 4 more

    分享需要用到httpclient ，先注调，可以用跨进程共享

==========bug

    Process: com.tal.monkey:plugin, PID: 11712
    java.lang.RuntimeException: An error occurred while executing doInBackground()
    at android.os.AsyncTask$AsyncFutureTask.done(AsyncTask.java:429)
    at java.util.concurrent.FutureTask.finishCompletion(FutureTask.java:383)
    at java.util.concurrent.FutureTask.setException(FutureTask.java:252)
    at java.util.concurrent.FutureTask.run(FutureTask.java:271)
    at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1167)
    at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:641)
    at java.lang.Thread.run(Thread.java:929)
    Caused by: java.lang.UnsatisfiedLinkError: No implementation found for long com.shockwave.pdfium.PdfiumCore.nativeOpenDocument(int, java.lang.String) (tried Java_com_shockwave_pdfium_PdfiumCore_nativeOpenDocument and Java_com_shockwave_pdfium_PdfiumCore_nativeOpenDocument__ILjava_lang_String_2)
    at com.shockwave.pdfium.PdfiumCore.nativeOpenDocument(Native Method)
    at com.shockwave.pdfium.PdfiumCore.newDocument(PdfiumCore.java:135)
    at com.tal.lib_printer.pdfviewer.source.FileSource.createDocument(FileSource.java:38)
    at com.tal.lib_printer.pdfviewer.DecodingAsyncTask.doInBackground(DecodingAsyncTask.java:53)
    at com.tal.lib_printer.pdfviewer.DecodingAsyncTask.doInBackground(DecodingAsyncTask.java:27)
    at android.os.AsyncTask$3.call(AsyncTask.java:389)
    at java.util.concurrent.FutureTask.run(FutureTask.java:266)
    at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1167) 
    at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:641) 
    at java.lang.Thread.run(Thread.java:929) 

    需要manager中写上对应的abi，这样manager才会复制插件中的so文件，而且好像必须删除app才能生效



===========源码分析======、、
    manager 中 com.tencent.shadow.sample.manager.SamplePluginManager
    解压plugin.zip，然后加载loader,runtime , plugin的dex，然后bind宿主中注册的
    com.tencent.shadow.sample.host.PluginProcessPPS
    这个服务是在plugin进程中的，

    创建服务后，调用 callApplicationOnCreate


     *
     */
    public static void main(String[] args) {

    }

    /**
     * 主工程，新建一个模块module_plugin，吧shadow代码复制过来就行，专门用来加载manager的，里面有 PluginLoadActivity 加载插件的
     *
     *  implementation 'commons-io:commons-io:2.5'//sample-host从assets中复制插件用的
     *     implementation 'org.slf4j:slf4j-api:1.7.25'
     *
     *     implementation "com.tencent.shadow.dynamic:host:2.0.12-65b95750-SNAPSHOT"
     *     compileOnly "com.tencent.shadow.core:runtime:2.0.12-65b95750-SNAPSHOT"
     *
     *
     * //后两个是shaow打包上传到本地gradle的maven仓库就行
     * 根目录添加本地仓库地址
     * allprojects {
     *     repositories {
     *      mavenLocal()
     *     }
     * }
     *
     *
     *
     * ===========其他模块调用，开启PluginLoadActivity来加载插件
     *  Intent intent = new Intent(getActivity(), PluginLoadActivity.class);
     *  //插件中配置的partkey
     *  String partKey = "sample-plugin";
     *  intent.putExtra(Constant.KEY_PLUGIN_PART_KEY, partKey);
     * //            switch (partKey) {
     * //                //为了演示多进程多插件，其实两个插件内容完全一样，除了所在进程
     * //                case Constant.PART_KEY_PLUGIN_MAIN_APP:
     * //                case Constant.PART_KEY_PLUGIN_ANOTHER_APP:
     *             //要启动的插件中的Activity
     *                     intent.putExtra(Constant.KEY_ACTIVITY_CLASSNAME, "com.tal.module_plugin_test.MainActivity");
     * //                    intent.putExtra(Constant.KEY_ACTIVITY_CLASSNAME, "com.tal.lib_printer.ui.PrinterActivity");
     * //                    break;
     * //
     * //            }
     *             startActivity(intent);
     *
     * ========插件模块====
     * module_plugin_manager ，插件管理器，需要单独打包成manager.apk
     *
     * module_plugin_demo 插件apk，独立的业务
     *
     * module_plugin_loader
     * module_plugin_runtime
     * 这两个模块是和 demo.apk 压缩到 plugin.zip 中的
     *
     * 到时我们把 manager.apk 和  plugin.zip 下载后，通过PluginLoadActivity来加载就行
     *
     * ========= module_plugin_manager 代码
     *   implementation "com.tencent.shadow.dynamic:manager:2.0.12-65b95750-SNAPSHOT"
     *     compileOnly "com.tencent.shadow.core:common:2.0.12-65b95750-SNAPSHOT"
     *     compileOnly "com.tencent.shadow.dynamic:host:2.0.12-65b95750-SNAPSHOT"
     *
     * 修改SamplePluginManager
     * PluginManager实现的别名，用于区分不同PluginManager实现的数据存储路径
     *  protected String getName() {
     *         return "sample-manager";
     *     }
     * public String getAbi() {
     *         return "armeabi-v7a";
     *     }
     *     宿主中注册的PluginProcessService实现的类名
     * protected String getPluginProcessServiceName() {
     *         return "com.tal.module_plugin.process.PluginProcessPPS";
     *     }
     *
     * ======== runtime 有宿主中注册的Activity类，用来占位四大组件
     *  implementation "com.tencent.shadow.core:activity-container:2.0.12-65b95750-SNAPSHOT"
     *
     * ========loader 配置 宿主和 runtime中占位Activity的对应关系
     *   implementation "com.tencent.shadow.dynamic:loader-impl:2.0.12-65b95750-SNAPSHOT"
     *
     *     compileOnly "com.tencent.shadow.core:activity-container:2.0.12-65b95750-SNAPSHOT"
     *     compileOnly "com.tencent.shadow.core:common:2.0.12-65b95750-SNAPSHOT"
     *     //下面这行依赖是为了防止在proguard的时候找不到LoaderFactory接口
     *     compileOnly "com.tencent.shadow.dynamic:host:2.0.12-65b95750-SNAPSHOT"
     *
     * 模块中定义的壳子Activity，需要在宿主AndroidManifest.xml注册
     *
     *
     * =============demo=====
     *     //Shadow Transform后业务代码会有一部分实际引用runtime中的类
     *     //如果不以compileOnly方式依赖，会导致其他Transform或者Proguard找不到这些类
     *     compileOnly "com.tencent.shadow.core:runtime:2.0.12-65b95750-SNAPSHOT"
     *     api deps.support.multidex
     *     //宿主的依赖
     *     compileOnly project(':lib_common')
     *
     *  ======packagePlugin 任务配置，在demo的build.gradle中
     //这段buildscript配置的dependencies是为了apply plugin: 'com.tencent.shadow.plugin'能找到实现
     buildscript {
     repositories {
     google()
     jcenter()
     mavenLocal()
     }

     dependencies {
     classpath "com.tencent.shadow.core:gradle-plugin:2.0.12-65b95750-SNAPSHOT"
     }
     }

     apply plugin: 'com.tencent.shadow.plugin'

     //在这里可以配置多插件 pluginApk1 pluginApk2
     shadow {
     packagePlugin {
     pluginTypes {
     debug {
     //改成对应module的task   loader 和runtime指定的名字也要改
     loaderApkConfig = new Tuple2('module_plugin_loader-debug.apk', ':module_plugin_loader:assembleDebug')
     runtimeApkConfig = new Tuple2('module_plugin_runtime-debug.apk', ':module_plugin_runtime:assembleDebug')
     pluginApks {
     pluginApk1 {
     businessName = 'sample-plugin'//businessName相同的插件，context获取的Dir是相同的。businessName留空，表示和宿主相同业务，直接使用宿主的Dir。
     partKey = 'sample-plugin'
     buildTask = 'assembleDebug'
     //这里改成插件的模块的名字
     apkName = 'module_plugin_test-debug.apk'
     apkPath = 'module_plugin_test/build/outputs/apk/debug/pmodule_plugin_test-debug.apk'
     hostWhiteList = ["com.tal.utils","com.tal.lib_common.utils","com.tal.lib_common.entity"]
     }
     }
     }

     release {
     loaderApkConfig = new Tuple2('module_plugin_loader-release.apk', ':module_plugin_loader:assembleRelease')
     runtimeApkConfig = new Tuple2('module_plugin_runtime-release.apk', ':module_plugin_runtime:assembleRelease')
     pluginApks {
     pluginApk1 {
     businessName = 'demo'
     partKey = 'sample-plugin'
     buildTask = 'assembleRelease'
     apkName = 'module_plugin_test-release.apk'
     apkPath = 'module_plugin_test/build/outputs/apk/release/module_plugin_test-release.apk'
     //这样就可以访问宿主中的类了
     hostWhiteList = ["com.tal.utils","com.tal.lib_common.utils","com.tal.lib_common.entity"]
     }
     }
     }
     }

     //这里的路径是相对当前module对应project的根目录，写对应模块的名字
     loaderApkProjectPath = 'module_plugin_loader'

     runtimeApkProjectPath = 'module_plugin_runtime'

     archiveSuffix = System.getenv("PluginSuffix") ?: ""
     archivePrefix = 'plugin'
     destinationDir = "${getRootProject().getBuildDir()}"


     version = 4
     compactVersion = [1, 2, 3]
     uuidNickName = "1.1.5"
     }
     }
     configurations {
     all {
     exclude module: 'httpclient'
     exclude module: 'commons-logging'
     }
     }
     *
     */
    void a1(){}
}
