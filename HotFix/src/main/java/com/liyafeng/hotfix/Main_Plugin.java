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
























}
