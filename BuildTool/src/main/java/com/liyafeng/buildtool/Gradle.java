package com.liyafeng.buildtool;

public class Gradle {
    /**
     * 2017 年google 后，Android studio 版本更新至3.0，更新中，
     * 、连带着com.android.tools.build:gradle 工具也升级到了3.0.0，
     * 在3.0.0中使用了最新的Gralde 4.0 里程碑版本作为gradle 的编译版本，
     * 该版本gradle编译速度有所加速，更加欣喜的是，完全支持Java8。当然，对于Kotlin的支持，
     * 在这个版本也有所体现，Kotlin插件默认是安装的。
     * ---------------------
     * 作者：迦蓝叶
     * 来源：CSDN
     * 原文：https://blog.csdn.net/soslinken/article/details/73114637
     * 版权声明：本文为博主原创文章，转载请附上博文链接！
     *
     *
     * =========api 和 implementation 区别==========
     * api和implement区别
     * api完全等于compile，implement依赖的包只能在本模块中引用到
     * 而api可以任何模块中使用
     *
     * @param args
     */
    public static void main(String[] args) {

    }



    /**
     * 项目打包成aar文件
     * ===============
     * 1.打包之后生成的文件地址：
     *
     * *.jar：库/build/intermediates/bundles/debug(release)/classes.jar
     * *.aar：库/build/outputs/aar/libraryname.aar
     * ---修改编译配置--
     * 1.将apply plugin: ‘com.android.application’改为apply plugin: ‘com.android.library’
     * 2.去掉applicationId
     *-----修改清单文件---
     * 3.清单文件AndroidManifest.xml
     * 将application的name icon lable theme roundIcon等属性去掉
     * .去掉软件的入口（AndroidManifest中的启动intent），如果不去掉，引用此aar文件后，运行时软件有两个图标
     * -----------
     * 运行右侧 gradle ->[module]->build->assemble 就能在目录中找到aar包了
     *
     *
     * =======集成aar包==========
     * android {
     * repositories {
     *     flatDir {
     *         dirs 'libs'
     *     }
     * }
     * }
     * compile(name: 'app-debug', ext: 'aar')
     *
     *
     *
     */
    void a4(){}




    /**
     * Gradle下载的 jar或aar在哪？
     * https://www.zhihu.com/question/40900206
     *
     * Mac系统默认下载到：/Users/(用户名)/.gradle/caches/modules-2/files-2.1
     * Windows系统默认下载到：C:\Users\(用户名)\.gradle\caches\modules-2\files-2.1
     *
     * 或者
     * 视图切换到Project，最下面有External Libraries，里面是这个项目依赖的库，
     * 右键-》Library Properties 就能查看到目录了，如果是在工程中直接依赖的库，就不会显示路径
     *
     *
     * =======gradle的下载位置========
     * ~/.gradle/wrapper/dists/gradle-2.14.1-all/...
     * ~代表用户跟目录，
     * Mac:  /Users/(用户名)/
     * Windows: C:\Users\(用户名)\
     *
     *
     */
    void a5(){}


    /**
     * =====什么是aapt==========
     * https://developer.android.google.cn/studio/command-line/aapt2
     *
     * aapt(Android Asset Packaging Tool)
     * 编译和打包资源文件成一个二进制文件用的
     *  compile and package your app’s resources.
     *  AAPT2 parses, indexes, and compiles the resources into a binary format
     *  that is optimized for the Android platform.
     *
     *
     *
     *
     */
    void a6(){}
}
