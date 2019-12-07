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

}
