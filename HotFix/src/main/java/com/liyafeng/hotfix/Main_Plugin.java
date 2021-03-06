package com.liyafeng.hotfix;

public class Main_Plugin {

    /**
     *
     * 【Android 修炼手册】常用技术篇 -- Android 插件化解析 （好文！）
     * https://juejin.im/post/6844903885476233229
     *
     * Shadow的缺点介绍  原作者
     * https://juejin.im/post/6844903875682500621
     *
     * ========hook系统api=========
     * 目的是让 系统原本执行的代码，变成执行我们想要的代码
     * 方法是，创建一个执行调用代码对象的子类，重新方法，通过反射来替换系统调用的对象（重新赋值）
     * 比如 ActivityThread 中有个调用 a.do()  我们就创建一个A的子类，然后创建实例
     * 然后通过反射把 ActivityThread 中的对象替换成我们自己的实例，这样执行的代码就是我们写的代码了
     *
     * 示例
     *  // hook 系统，替换 Instrumentation 为我们自己的 AppInstrumentation，Reflect 是从 VirtualApp 里拷贝的反射工具类，使用很流畅~
     *             var reflect = Reflect.on(activity)
     *             var activityThread = reflect.get<Any>("mMainThread")
     *             var base = Reflect.on(activityThread).get<Instrumentation>("mInstrumentation")
     *             var appInstrumentation = AppInstrumentation(activity, base, pluginContext)
     *             Reflect.on(activityThread).set("mInstrumentation", appInstrumentation)
     *             Reflect.on(activity).set("mInstrumentation", appInstrumentation)
     *
     * 作者：ZYLAB
     * 链接：https://juejin.im/post/6844903885476233229
     * 来源：掘金
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     *
     * =========插件化要解决的问题====
     * 一个apk要加载网络下载的apk
     * 要解决
     * 类加载问题  用 dexClassloader解决
     * 资源加载问题 （独立式，共享式）
     * 系统加载资源，都是通过filename 用assetManager 来加载，返回drawable
     * 通过addAssetPath方式加入资源的目录
     *
     * 生命周期管理（因为新的Activity在原来的apk manifest中没有定义，所以系统不会走生命周期）
     *
     *
     *
     *
     * @param args
     */
    public static void main(String[] args) {

    }

    /**
     *
     * ==========插件化和热修复的区别===========
     * 其实在我看来，插件化和热修复的本质是一样的，都是动态加载 dex 以及资源，区别在于两者的侧重点不同，
     * 插件化重点在加载宿主里不存在的内容，热修复重在修复问题，因为重点不同，所以两者的实现方式上又会有区别，
     * 插件化实现重点在四大组件的调用，所以就有了 hook，代理等等方法。热修复实现重点在加载补丁替换有问题的方法／类，
     * 所以方式有插桩，native 方法替换，dex 替换等等。在很多 app 里两者其实是共存的，功能不同，互不冲突
     *
     * 热修复偏向于动态加载dex，替换原有的dex中的逻辑，插件化偏向于加载新的dex，和动态调用其中四大组件的生命周期
     *
     *
     */
    void a1(){}


    /**
     * ==========java classloader==============
     * java文件编译成class字节码文件，  这种字节码就是Java虚拟机可执行的“机器语言”。　
     * 磁盘上的class文件需要通过jvm 的classloader 加载到内存中，才能通过jvm的解释器进行执行
     *
     * classloader是jvm的一部分
     *
     * JVM预定义的三种类型类加载器：
     * 启动（Bootstrap）类加载器：加载jdk中的代码 <Java_Runtime_Home>/lib （即所有java.*开头的类）
     * 标准扩展（Extension）类加载器 ExtClassLoader  它负责将< Java_Runtime_Home >/lib/ext 中的类加载到内存中
     *
     * 系统（System）类加载器：AppClassLoader  它负责将系统类路径（CLASSPATH）中指定的类库加载到内存中。开发者可以直接使用系统类加载器。
     *
     *
     * -------------双亲委派机制描述
     * 某个特定的类加载器在接到加载类的请求时，首先将加载任务委托给父类加载器，依次递归，
     * 如果父类加载器可以完成类加载任务，就成功返回；只有父类加载器无法完成此加载任务时，才自己去加载。
     *
     * 委托机制的意义 — 防止内存中出现多份同样的字节码
     * 如果不用委托而是自己加载自己的，那么类A就会加载一份System字节码，然后类B又会加载一份System字节码，这样内存中就出现了两份System字节码。
     *
     *
     *
     * ------------- BootstrapClassLoader
     * Java虚拟机的第一个类加载器是 Bootstrap，这个加载器很特殊，
     * 它不是Java类，因此它不需要被别人加载，它嵌套在Java虚拟机内核里面，
     * 也就是JVM启动的时候Bootstrap就已经启动，它是用C++写的二进制代码（不是字节码），它可以去加载别的类。
     *
     * 这也是我们在测试时为什么发现System.class.getClassLoader()结果为null的原因，
     * 这并不表示System这个类没有类加载器，而是它的加载器比较特殊，是BootstrapClassLoader，
     * 由于它不是Java类，因此获得它的引用肯定返回null。
     *
     * ---------能不能自己写个类叫java.lang.System
     * 能不能自己写个类叫java.lang.System？
     *
     * 答案：通常不可以，但可以采取另类方法达到这个需求。
     * 解释：为了不让我们写System类，类加载采用委托机制，这样可以保证爸爸们优先，爸爸们能找到的类，
     * 儿子就没有机会加载。而System类是Bootstrap加载器加载的，
     * 就算自己重写，也总是使用Java系统提供的System，自己写的System类根本没有机会得到加载。
     *
     * -------------一个程序最基本的加载流程
     * 当运行一个程序的时候，JVM启动，运行bootstrapclassloader，
     * 该ClassLoader加载java核心API（ExtClassLoader和AppClassLoader也在此时被加载），
     * 然后调用ExtClassLoader加载扩展API，最后AppClassLoader加载CLASSPATH目录下定义的Class，
     * 这就是一个程序最基本的加载流程。
     *
     *
     *
     *
     */
    void a2(){}


    /**
     * Android App 启动时的操作之 ClassLoader 和 Application 初始化
     * https://www.jianshu.com/p/7c59391f0658
     *
     * App's ClassLoader的来源
     * https://linjiang.tech/2016/10/31/App's%20ClassLoader%E7%9A%84%E6%9D%A5%E6%BA%90/
     *
     *
     *
     * ==========android classloader========
     * BootClassLoader 是Android平台上所有ClassLoader的最终parent,这个内部类是包内可见,所以我们没法使用。
     * URLClassLoader  只能用于加载jar文件，但是由于 dalvik 不能直接识别jar，所以在 Android 中无法使用这个加载器。
     *
     * PathClassLoader 和 DexClassLoader 都继承自BaseDexClassLoader,其中的主要逻辑都是在BaseDexClassLoader完成的
     *
     * PathClassLoader 只能加载已经安装的apk的dex
     * DexClassLoader 能加载apk  dex jar 中的类到内存
     *
     *
     * =======BootClassLoader======
     * Android启动的时候创建，加载framework中的Android sdk中的类 是个单例
     * app启动时候回把它传入，
     *
     * ====== PathClassLoader==============
     * 加载 application 的类加载器为 dalvik.system.PathClassLoader, 并且它与包名 com.example.chenzhao.thememaintest-1/base.apk 是相关的，也就是一对一对应的
     *
     * ========PathClassLoader 是什么时候初始化的呢？
     * https://www.jianshu.com/p/7c59391f0658
     *
     * 当一个进程被创建成功后，会走到这个进程的 ActivityThread.main(...),
     *
     * 里面创建application对象之前会创建 ContextImpl  ，
     * java.lang.ClassLoader cl = getClassLoader();
     *         if (!mPackageName.equals("android")) {
     *             Trace.traceBegin(Trace.TRACE_TAG_ACTIVITY_MANAGER,
     *                     "initializeJavaContextClassLoader");
     *             initializeJavaContextClassLoader();
     *             Trace.traceEnd(Trace.TRACE_TAG_ACTIVITY_MANAGER);
     *         }
     *         ContextImpl appContext = ContextImpl.createAppContext(mActivityThread, this);
     *         app = mActivityThread.mInstrumentation.newApplication(cl, appClass, appContext);
     *
     * getClassLoader就是获取类加载器，用来加载apk中的类
     * 在 ApplicationLoaders
     * PathClassLoader pathClassloader = PathClassLoaderFactory.createClassLoader(...);
     *
     * 然后就用这个 PathClassLoader 去加载apk中的类，因为这个loader已经加入到loaders中了，
     *
     * 当一个 Application 建立起时， 会首先调用 attachBaseContext() 这个方法。
     * 然后才会调用 onCreate()
     *
     * 每个App进程都有唯一的ApplicationLoaders实例，一个LoadedApk的ClassLoader一但创建，
     * 便会保存在ApplicationLoaders的mLoaders实例中，后续则通过apk路径查询返回。同时还可以得出一个结论，
     * 一个进程可以对应多个apk。
     *
     * ===========四大组件 和 其他类的类加载器===========
     *
     *
     * 普通类的加载是通过调用该类的caller类的类加载器来完成的。
     * 由于整个应用默认的初始类加载器是PathClassLoader，
     * 那么通过Application和四大组件引用到的其它普通类的类加载器也都是PathClassLoader。
     *
     * private Activity performLaunchActivity(ActivityClientRecord r, Intent customIntent) {
     *         //...
     *         Activity activity = null;
     *         try {
     *             java.lang.ClassLoader cl = r.packageInfo.getClassLoader();
     *             activity = mInstrumentation.newActivity(cl, component.getClassName(), r.intent);
     *             //...
     *         } catch (Exception e) {
     *             //...
     *         }
     *         //...
     * }
     *
     * =========结论==========
     * 每个app进程都会创建一个对应apk路径 的 PathClassLoader
     * 用这个loader加载类到当前进程的内存中。
     *
     */
    void a3(){}


    /**
     * ======进程间的内存空间========
     * 不同进程间的内存空间是隔离，是相互看不见，摸不着，访问不了的。
     * （这样设计是为了使用相对虚拟内存的位置，而不需要记录绝对内存位置）
     * 有时候多个进程之间需要共享，OS就划一块物理内存出来，让两个进程将物理内存映射到两个进程各自的虚拟内容内，这种方案称为共享内存
     *
     */
    void a3_1(){}


    /**
     * ====Dalvik Art区别
     * ART能够把应用程序的字节码转换为机器码
     * Dalvik采用的是JIT技术，字节码都需要通过即时编译器（just in time ，JIT）转换为机器码，这会拖慢应用的运行效率，
     * 而ART采用Ahead-of-time（AOT）技术，应用在第一次安装的时候，字节码就会预先编译成机器码，
     * 这个过程叫做预编译。ART同时也改善了性能、垃圾回收（Garbage Collection）、应用程序除错以及性能分析。
     *
     * 但是art 运行时内存占用空间较少同样意味着编译二进制需要更高的存储。
     *
     * ART模式相比原来的Dalvik，会在安装APK的时候，使用Android系统自带的dex2oat工具把APK里面的.dex文件转化成OAT文件，OAT文件是一种Android私有ELF文件格式，它不仅包含有从DEX文件翻译而来的本地机器指令，还包含有原来的DEX文件内容
     *
     * 、
     * 作者：SilenceDut
     * 链接：https://www.jianshu.com/p/a620e368389a
     * 来源：简书
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     *
     */
    void a4(){}


    /**
     * =======插件化应用======
     * 请求接口，获取插件list。后台定义的插件列表，name ,下载地址 , md5
     * 后台接口下发入口配置，plugin://插件名
     * 插件名就是manager中定义的，必须对应。
     * 下载插件的zip路径，然后插件名称，就能开启插件了
     *
     * ----
     * shadow相当于把一个能独立运行的apk打包到zip中，zip中还有 manager，loader，runtime
     * manager是主进程加载的代码，另外是子进程加载的
     *
     * ----
     * 每次插件加载的时候，遍历apk中的AndroidManifest然后绑定对应的Activity。
     *
     *
     * ----- 插件包的内容
     * 相当于把测试app依赖的库，当成 plugin_build 模块的依赖，然后打包成apk
     * 测试app 模块的代码并不会打包到插件中。。
     * 所以你要定义application就要在子模块(非测试app模块)进行一个打包。
     * shadow会读取apk中的manifest文件，读取出application的类名，进行反射创建。
     * plugin_build 包名必须和宿主app包名一致，否则抛出异常。
     *
     * ModuleInfo business = new ModuleInfo()
     * business.apkName = "plugin-build-type.apk"
     * business.buildTask = ":plugin_build:plugin-build:assembletype".toString()
     * business.projectPath = "lugin_build/plugin-build".toString()
     * business.apkPath = "plugin_build/plugin-build/build/outputs/apk/type/plugin-build-type.apk"
     *
     * apply from: path_apk_gradle//测试app模块的gradle相当于把同步了一下配置，然后改了一下包名
     * android {
     *     defaultConfig {
     *         applicationId "com.主包名"
     *     }
     * }
     *
     * -----原理
     * 原理就是打包成 ,plugin.apk，manager.apk,runtime.apk,loader.apk
     * 主app加载manager.apk，然后根据loader中代码启动子进程service进行插件页面绑定
     * 然后打开的时候调用 manager中的代码enter即可。
     *
     *
     *
     */
    void a5(){}











}
