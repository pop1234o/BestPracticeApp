package com.liyafeng.practice;

/**
 * Created by liyafeng on 19/11/2017.
 */

public class Programming {


    /**
     * 1.什么是面向对象编程思想 OOP
     * 什么是面向过程编程
     */
    void a1() {

        /*
        * 万物皆对象
        * 比如java ，所有方法都属于一个对象，代表这个对象提供的能力
        * 而c是面向过程，函数是不属于某个对象，只是代表输入输出的执行过程
        *
        * */
    }

    /**
     * 2.什么是AOP（aspect-oriented programming）
     *
     *
     *
     */
    void a2() {

        /*
        宏观上说

        * 面向切面编程，它切的是业务逻辑，对多个不同业务逻辑中的相同处理逻辑提取出来
        *
        * 比如 日志统计，性能统计 ， 异常处理
        *
        * 这些处理逻辑在不同业务中，的相同逻辑
        *
        * 微观上说
        * 通过java代理，对指定的方法进行拦截
        * 从而实现对不同业务逻辑的切入
        *
        *
        * ===============Android AOP框架============
        * https://github.com/HujiangTechnology/gradle_plugin_android_aspectjx
        * 一个基于AspectJ并在此基础上扩展出来可应用于Android开发平台的AOP框架，可作用于java源码，class文件及jar包，同时支持kotlin的应用。
        *
        * https://github.com/JakeWharton/hugo
        * Annotation-triggered method call logging for your debug builds.
        *
        *
        *
        *
        * ============gradle_plugin_android_aspectjx==========
        * https://github.com/HujiangTechnology/gradle_plugin_android_aspectjx
        * https://www.jianshu.com/p/e152b34b785b （Android中使用AspectJ）
        * https://zhuanlan.zhihu.com/p/24876611 （看AspectJ在Android中的强势插入）
        * https://cloud.tencent.com/developer/article/1400317 (利用AOP对点击事件作防抖处理)
        *
        * A Android gradle plugin that effects AspectJ on Android project and can hook methods in Kotlin, aar and jar file.
        * 一个基于AspectJ并在此基础上扩展出来可应用于Android开发平台的AOP框架，可作用于java源码，class文件及jar包，同时支持kotlin的应用。
        *
        * --------------gradle_plugin_android_aspectjx 使用方法---------
        * 用的AspectJ语法
        * 有before ，after ，around注解，表示能注入方法的位置
        *
        *
        *
        * --------------AspectJ实现原理----------------
        * AspectJ的原理实际上是在编译的时候（遍历class文件），根据一定的规则解析，然后插入一些代码，通过aspectjx生成的代码，会在Build目录下：
        * transforms/AspectTransform/debug/jars/1/1f/aspects.jar
        *
        *
        *
        * */
    }

    /**
     * 什么是 OOD （面向对象设计）
     */
    void a2_1() {

    }

    /**
     * OOP 和 AOP的区别
     */
    void a2_2() {

    }

    /**
     * 3.你的开发流程？什么是TTD？Test-driven development
     *
     * 《测试驱动开发》
     */
    void a3() {

        /*
        * 先想好测试用例，再写逻辑代码
        *
        * 写测试用例的好处，比如出了问题，我可以快速通过测试来定位问题
        * 新添加的业务逻辑，将之前的测试用例完全跑一边，可以保证没有污染到之前版本的代码
        *
        *
        * */
    }

    /**
     * 你是如何重构代码的？
     */
    void a4() {

    }


    /**
     * 什么是编程思想
     * https://www.zhihu.com/question/35648714
     */
    void a5() {

    }

    /**
     * 技术基础能力不太好、架构能力较弱；三方库 大部分限于会用，没有主动探究源码、实现原理；不了解高日活APP需要的能力，项目视野不够
     *
     * 一、专业技术能力：
     * HashMap
     * LinkeHashMap的实现原理
     * 介绍一下线程池（如何创建，几个参数
     * 内存回收机制
     * 内存泄露、内存溢出的场景和解决办法
     * 事件分发机制：
     * RxJava的原理，常用的修饰符等：了解一些，限于会用
     * okhttp设计模式 ？
     * Apk 瘦身手段：常规手段基本了解 ？ 仅限于压缩，其他不了解
     * App性能优化、启动优化 ？
     * 卡顿优化？
     * native crash如何解决定位？
     *
     * 音视频编解码原理：不太了解，限于会用声望
     * 热修复怎么做的，实现原理
     *
     * 二、架构能力
     *
     * 怎么理解组件化和插件化：组件化了解一般，插件化不了解。
     * 现有项目架构描述：基本没有组件化。
     * 如何接入一个三方（未来可能被替换的）sdk，如何设计，如何与业务隔离
     * 日志系统：只使用过firebase，自身app没有
     *
     *
     * 三、项目经验
     * 开发流程、灰度、上线流程：应用市场灰度，app内没有灰度机制
     * 一个成熟稳定的App 应该具备哪些非业务的基础能力，如何支撑高流量、高日活：主要是后端。app没有具体思路。
     *
     *
     *
     *
     *
     * 百万日活和初始项目
     * 小问题会被放大，卡顿，启动，包大小，crash
     * 业务代码激增，难拓展，维护
     *
     *
     * -----------------
     * 面试评价：技术基础能力较好、比较踏实；架构能力较弱；三方sdk/开源库 大部分限于会用，没有主动探究源码、实现原理；不关注行业前沿技术，项目中用不到便不会主动了解。
     *
     * 面试问题
     *
     * 一、专业技术能力：
     * 1. 内存泄露、内存溢出的场景和解决办法
     * 2. 内存回收机制
     * 3. 热修复的实现原理
     * 4. HashTable和HashMap的实现原理
     * 5. Apk 瘦身手段：常规手段基本了解
     * 6. View/ViewGroup 的事件分发机制
     * 7. RxJava的原理，常用的修饰符等
     * 8. App性能优化、布局优化
     * 9. 音视频编解码原理，
     * 10. 介绍一下线程池，有哪几种，分别适用于什么场景
     *
     *
     *
     *
     * 二、架构能力
     *
     * 1. 怎么理解组件化和插件化：组件化基本了解，插件化不了解。
     * 2. 现有项目架构描述：架构一般，不够清晰、无边界感。
     * 3. 如何接入一个三方（未来可能被替换的）sdk，比如IM ，如何设计，如何与业务隔离：没有思路，答不上来。
     * 4. 日志系统、APM：将日志写入本地文件，通过用户说动上报。
     *
     *
     * 三、技术前瞻性、主动学习能力
     * 1. flutter
     * 2. Rect Native
     * 3. Android 12最新系统版本的新特性
     * 4. avif
     *
     * 四、项目中遇到的问题
     * 1. 如何解决：短视频，高斯模糊问题
     * 2. 教训总结：多了解底层原理、开源库实现原理
     *
     * 五、项目经验
     * 1. 开发流程、灰度、上线流程：没有灰度机制、早期通过志愿者用户群进行灰度
     * 2. 从0 到1 开始开发app  如何起步、如何进行架构、框架的选型、考虑哪些方面：结合产品需求、基础库封装。描述不够全面。
     * 3. 一个成熟稳定的App 应该具备哪些非业务的基础能力，如何支撑高流量、高日活：仔细、认真、代码健壮性。眼界不够开阔，没有具体思路。
     * 4. 低端机音视频卡顿、网络质量差掉帧等如何优化：列表滑动加载时机优化
     *
     *
     */
    void a6(){}

    /**
     *  晋升-------
     *  讲的还行，有一些思考
     *  ppt一定要讲你的难点，亮点，让别人知道你为此付出的难度，得到的收益，不要讲一堆无关痛痒的，
     *  背景，痛点，解决方案/步骤，结果/收益/好处
     *  不是讲一堆你代码逻辑，业务逻辑，否则你有能力也不能体现出来
     *  不要讲太多逻辑，讲关键逻辑，然后你这样设计的好处
     *  表达的是你的视野，你的思考，行业内多高水平，你做了多少，差距多少，
     *
     *  更主动的去发现用技术解决的问题，比如有些业务有bug只能发版，我们需要动态下发。
     * ---自我介绍
     * 简短突出重点
     *  -----主要成果选型
     *  你讲的东西目的是要超出评委预期，讲的东西都是评委做过的，大家都说过的，没有超出预期，那很难给你高分
     * 不要写那些大家都知道的东西，要有新玩意，能和业内对齐甚至超过的。平时要多关注一些这种信息
     *
     * ------技术专长不足
     * ----技术贡献
     *
     *
     */
    void a7(){}

}
