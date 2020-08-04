package com.liyafeng.buildtool;

import android.app.Activity;
import android.os.Bundle;

/**
 * gradle 自动化构建工具
 * <p>
 * 用java写成，
 * <p>
 * 它提供了接口，是一些规范的行为，但他不提供build的实现，build实现需要指定构建脚本
 * <p>
 * buildscript{repositories{jcenter() }  dependenies{ classpath:'com.android.build.tools:gradle:2.3.1'} }
 * 这句话就是指定构建的插件（脚本）是什么，从哪里获取。那么gradle做的就是从jcenter这个网站下载指定的 groupid:arfricId:version
 * 下载的（gradle-2.3.1.jar包）存放目录在studio 下的 gradle/m2repository目录下
 * <p>
 * 我们就是依靠这个jar包（脚本/插件）来构建我们的Android工程的，它依靠调用Android sdk中的build-tools中的工具来完成构建
 * =======================
 * gradle有gradle build language 或者叫 dsl domain sepcifit language
 * <p>
 * gradle 的脚本 script 分三种（脚本类型） :
 * 一个是构建脚本 对应Project对象 build.gradle
 * 一个是配置脚本，对应Settings对象，对应settings.gradle
 * 一个是初始化脚本 对应Gradle对象，在~/.gradle 目录下配置 init.gradle ，一般是大企业用的，用于统一管理多个project
 * 当然在~/.gradle/init.d/ 目录下的*.gradle也是初始化用的
 * <p>
 * =============== build script=================
 * 下面主要讲的是 build script
 * 一个build script 包含0个或多个 statements(声明)和 script blocks(脚本块)
 * <p>
 * 声明对应的是：方法和成员变量的调用
 * 而代码块则是 对应的是 一特殊的方法，这个方法以closure（闭包）作为参数
 * 这个闭包（{}）会委托一个object作为他的执行者
 * <p>
 * 那么在Project对象中，也就是在build.gradle脚本文件中，常用的script block 有以下几种：
 * allprojects { } -配置这个project的所有子project
 * buildscript { } -配置这个project的 build script classpath （指定构建插件\jar包的位置）
 * dependencies { } -配置依赖库
 * repositories { } -配置这个project使用的仓库位置
 * 等，见https://docs.gradle.org/4.0/dsl/
 * <p>
 * ===============plugin==========
 * 一个jar包中用很多pluginID，比如com.android.application com.android.library com.android.test com.android.instantapp
 * 我们要指定使用一个插件 比如 apply plugin:'比如com.android.application'
 * apply这个方法参数是hashmap类型的
 * <p>
 * 然后我们就可以用这个插件中的android{} 这个script block了，里面是build apk的一些参数
 * <p>
 * <p>
 * ==========dsl==================
 * 下面说dsl ,它实际上就是一种关系映射 是基于Groovy dsl的
 * 比如调用方法 就可以写成
 * <p>
 * methodname 参数
 * 字段 值
 * <p>
 * 如果调用setxxx方法
 * <p>
 * xxx 值
 * <p>
 * <p>
 * <p>
 * =================task==============
 * 其实这个插件就是写了很多task，比如编译task ,合并Manifesttask等
 * 我们要查看所有task  : 在project目录下 gradle tasks --all
 * <p>
 * ==========gradle wrapper=========
 * gradle wrapper 是gradle的一个task  https://guides.gradle.org/creating-new-gradle-builds/
 * <p>
 * 我们来到一个空目录，创建空的build.gradle文件
 * 然后 gradle tasks ，就能看到这个task ，执行 gradle wrapper
 * 我们就会看到生成了 gradlew  / gradlew.sh / gradle/wrapper/中有gralde-wrapper.jar 和 gradle-wrapper.properties
 * <p>
 * 这些就是配置好这个工程的gradle了
 * <p>
 * wrapper存在的意义在于即使你的电脑中没有 gradle这个软件，那么你执行gradlew来构建这个项目的时候，
 * gralde会去gradle-wrapper.properties 中配置的网址下载gradle软件
 * <p>
 * 而且可以保证每个人用的gradle软件版本是一致的
 * <p>
 * <p>
 * <p>
 * =======自定义task===========
 * 当然我们还可以定义自己的task，关于更多自定义taks见https://guides.gradle.org/writing-gradle-tasks/
 * task 任务名{
 * task的方法{
 * 调用方法
 * }
 * }
 * <p>
 * task 任务名(type:调用的类名){
 * 调用类中的方法 参数
 * }
 * <p>
 * task clean(type: Delete) {
 * delete rootProject.buildDir
 * }
 * <p>
 * task hello {
 * doLast {
 * println 'Hello, World!'
 * }
 * }
 * <p>
 * <p>
 * <p>
 * 我们的Android plugin for gradle 正是实现了这个接口
 * <p>
 * <p>
 * 这个插件在 项目 的build.gradle下指定 ：
 * <p>
 * <p>
 * =========android plugin dsl（讲解里面可以配置的名称）===========
 * 所有的android plugin dsl 在http://google.github.io/android-gradle-dsl/current/index.html
 * dsl就自定义的，我们可以在里面使用我们定义好的方法，字段
 * 原理就是映射到java的对象中的方法和字段，而这种映射能力是Groovy dsl提供的
 * =========== 优化构建速度 =========
 * 优化构建速度  https://developer.android.com/studio/build/optimize-your-build.html
 * <p>
 * 1.优化构建配置
 * 更新最新的Android gradle插件
 * 配置productFlavors 来指定开发的时候需要编译哪些（开发时可能不需要全部编译）比如我们开发的时候只要xhdpi的
 * 禁用崩溃收集器
 * 使用静态值 在你debug build的时候，比如versioncode = 1
 * 使用静态依赖（硬编码依赖库的版本号）
 * 使用offine模式的gradle 这样配置的依赖库就只会在本地找~/.gralde
 * Enable configuration on demand 只编译当前的module (在preference中的compiler中)
 * 使用library module ,这样不用每次都依赖了
 * 使用webp格式的图形，它压缩率更高，省去了编译器压缩图片的过程
 * 禁用png压缩
 * 使用instant run
 * 2.profile your build (查看你的build信息)
 * gradlew clean
 * gradlew --profile --recompile-scripts --offline --rerun-tasks  //assembleFlavorDebug
 * 在build/reports下生成构建报告
 * <p>
 * 禁用一些没必要task ，在debug的时候
 * <p>
 * =========关于配置你的android project================
 * 关于配置你的android project 见https://developer.android.com/studio/build/index.html#build-process
 * <p>
 * <p>
 * ===================排查编译时的错误==============
 * Compilation error. See log for more details
 * 有的时候编译器直接给你这个错误，你也不知道去哪找错，所以你可以运行命令行
 * 来打印从错误
 * <p>
 * ./gradlew build --stacktrace   --info  --scan(高亮)
 * <p>
 * 这个时候你往下拉点就行，有的时候编译器根本找不到，就是编译文件错误，那肯定得到文件里找。。
 * 要么重启ide
 * <p>
 * <p>
 * <p>
 * ==============Android Studio之BuildConfig类=============
 * 在build目录下，generated->source->buildConfig->
 * 根据build.grade文件自动生成
 * <p>
 * ===============注意版本=================
 * 如果你用 last.xx这种，最新的版本，就有可能因为类的差异而不同而报错
 * <p>
 * ================MultiDex=============
 * http://www.thinkerzhangyan.com/2018/07/22/MultiDex/
 * <p>
 * <p>
 * ===============Gradle task===============
 * https://www.jianshu.com/p/02cb9a0eb2a0
 * 执行 ./gradlew tasks 列出所有的task
 * ./gradlew assembleDebug 执行task
 * <p>
 * <p>
 * <p>
 * ==============gradle配置文件内容解析============
 * project级别的 build.gradle
 * <p>
 * buildscript {
 * <p>
 * repositories {  //我们需要Android的构建工具，而这里配置构建工具的下载地址
 * google()
 * jcenter()  //这两个是内置的依赖库，因为最常用
 * <p>
 * //添加 Sensors Analytics maven 库地址
 * maven {
 * url 'https://dl.bintray.com/zouyuhan/maven'
 * }
 * }
 * dependencies {     //依赖的构建工具，
 * classpath 'com.android.tools.build:gradle:3.2.1'
 * <p>
 * <p>
 * //添加 Sensors Analytics android-gradle-plugin 依赖
 * classpath 'com.sensorsdata.analytics.android:android-gradle-plugin2:3.0.2'
 * <p>
 * // NOTE: Do not place your application dependencies here; they belong
 * // in the individual module build.gradle files
 * }
 * }
 * <p>
 * allprojects {  //所有项目的依赖库都可以从下面下载
 * repositories {
 * google()
 * jcenter()
 * <p>
 * //添加 Sensors Analytics maven 库地址
 * maven {
 * url 'https://dl.bintray.com/zouyuhan/maven'
 * }
 * }
 * }
 */
public class Main_Gradle extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    /**
     * * ==================多渠道打包================
     * android{
     * <p>
     * flavorDimensions "default"
     * productFlavors {
     * xiaomi {
     * manifestPlaceholders = [UMENG_CHANNEL_VALUE: "xiaomi"]
     * }
     * _360 {
     * manifestPlaceholders = [UMENG_CHANNEL_VALUE: "360"]
     * }
     * <p>
     * }
     * }
     * <p>
     * androidManifest.xml中
     * <p>
     * <application>
     * <p>
     * <p>
     * <meta-data
     * android:name="UMENG_APPKEY"
     * android:value="5aa8e90af29d980a0e000087" />
     * <meta-data
     * android:name="UMENG_CHANNEL"
     * android:value="${UMENG_CHANNEL_VALUE}" />
     * <p>
     * </application>
     */
    void dabao() {

    }

    /**
     * 命令行构建项目
     * https://developer.android.com/studio/build/building-cmdline?hl=zh-CN
     * <p>
     * ./gradlew task-name
     * <p>
     * 查看所有task
     * ./gradlew tasks
     * <p>
     * ./gradlew assemble 打包apk 在build/outputs/apk 中
     * <p>
     * gradlew assembleDebug
     * 或者直接安装
     * gradlew installDebug
     */
    void a1() {
    }


    /**
     * 从命令行对apk进行签名
     * https://developer.android.com/studio/publish/app-signing.html?hl=zh-CN
     */
    void a2() {
    }


    /**
     * ============gradle版本对应========
     * gradle来构建一个Android app https://guides.gradle.org/building-android-apps/
     * <p>
     * 使用gradle3.0  https://developer.android.com/studio/build/gradle-plugin-3-0-0-migration.html
     * <p>
     * <p>
     * gradle对应的Android插件版本  classpath 'com.android.tools.build:gradle:3.4.0'
     * gradle版本在gradle/wrapper/gradle-wrapper.properties中配置
     * <p>
     * gradle 和 Android plugin for gradle 版本对应  https://developer.android.com/studio/releases/gradle-plugin.html#updating-plugin
     * <p>
     * plugin           gradle
     * 1.0.0 - 1.1.3	2.2.1 - 2.3
     * 1.2.0 - 1.3.1	2.2.1 - 2.9
     * 1.5.0	        2.2.1 - 2.13
     * 2.0.0 - 2.1.2	2.10 - 2.13
     * 2.1.3 - 2.2.3	2.14.1+
     * 2.3.0+	        3.3+
     * 3.0.0+	        4.1+
     * 3.1.0+	        4.4+
     * 3.2.0 - 3.2.1	4.6+
     * 3.3.0 - 3.3.2	4.10.1+
     * 3.4.0+	        5.1.1+
     * <p>
     *
     * classpath 'com.android.tools.build:gradle:3.4.0'
     *
     * gradle-wrapper.properties
     * distributionUrl=https\://services.gradle.org/distributions/gradle-5.1.1-all.zip
     *
     * ===========Android gradle插件版本和 build tools版本对应 ====================
     * To suppress this warning, remove "buildToolsVersion '27.0.2'" from your build.gradle file,
     * as each version of the Android Gradle Plugin now has a default version of the build tools.
     * 我们可以移除这个配置，使用gradle插件中默认的build tools 版本
     *
     */
    void a3() {
    }


    /**
     * Android 7.0	24	N	平台亮点
     * Android 6.0	23	M	平台亮点
     * Android 5.1	22	LOLLIPOP_MR1	平台亮点
     * Android 5.0	21	LOLLIPOP
     * Android 4.4W	20	KITKAT_WATCH	仅限 KitKat for Wearables
     * Android 4.4	19	KITKAT	平台亮点
     * Android 4.3	18	JELLY_BEAN_MR2	平台亮点
     * Android 4.2、4.2.2	17	JELLY_BEAN_MR1	平台亮点
     * Android 4.1、4.1.1	16	JELLY_BEAN	平台亮点
     * Android 4.0.3、4.0.4	15	ICE_CREAM_SANDWICH_MR1	平台亮点
     * Android 4.0、4.0.1、4.0.2	14	ICE_CREAM_SANDWICH
     * Android 3.2	13	HONEYCOMB_MR2	 
     * Android 3.1.x	12	HONEYCOMB_MR1	平台亮点
     * Android 3.0.x	11	HONEYCOMB	平台亮点
     * Android 2.3.4
     * Android 2.3.3	10	GINGERBREAD_MR1	平台亮点
     * Android 2.3.2
     * Android 2.3.1
     * Android 2.3	9	GINGERBREAD
     * Android 2.2.x	8	FROYO	平台亮点
     * Android 2.1.x	7	ECLAIR_MR1	平台亮点
     * Android 2.0.1	6	ECLAIR_0_1
     * Android 2.0	5	ECLAIR
     * Android 1.6	4	DONUT	平台亮点
     * Android 1.5	3	CUPCAKE	平台亮点
     * Android 1.1	2	BASE_1_1	 
     * Android 1.0	1	BASE	 
     * ---------------------
     * 作者：HawkJony
     * 来源：CSDN
     * 原文：https://blog.csdn.net/qiaoquan3/article/details/70185550
     * 版权声明：本文为博主原创文章，转载请附上博文链接！
     */
    void a4(){}


    /**
     * //这里设置构建工具的依赖和下载依赖的仓库
     * buildscript {
     *     apply from: 'versions.gradle'
     *
     *     repositories {
     *
     *
     *         google()
     *         jcenter() {
     *             url "http://jcenter.bintray.com/"
     *
     *         }
     *         mavenCentral()
     *         maven { url "https://jitpack.io" }
     *
     *     }
     *     dependencies {
     *         classpath deps.android_gradle_plugin
     *         classpath deps.buggly_mapping_uploader
     *     }
     * }
     *
     * //这里是项目中的依赖
     * allprojects {
     *
     *     repositories {
     *         google()
     *         jcenter() {
     *             url "http://jcenter.bintray.com/"
     *         }
     *         mavenCentral()
     *         maven { url "https://jitpack.io" }
     *         maven {
     *             url "http://maven.aliyun.com/nexus/content/repositories/releases"
     *         }
     *     }
     * }
     */
    void a5(){}


    /**
     *
     * gradle 指定的maven仓库
     * 我们通过implmentments 依赖的仓库，会自动下载aar所需要的依赖库
     * 原理是通过 仓库中的 . pom文件来指定的
     * <dependencies>
     * <dependency>
     * <groupId>com.alibaba</groupId>
     * <artifactId>fastjson</artifactId>
     * <version>1.2.60</version>
     * <scope>compile</scope>
     * </dependency>
     * </dependencies>
     *
     * 常见的 maven 仓库
     * http://jcenter.bintray.com/
     *
     *
     */
    void a6(){

    }


    /**
     * ==============混淆=============
     * https://www.jianshu.com/p/9dacabd351e3 （Android混淆快速配置之@Keep）
     * @Keep
     * 在使用@Keep注解之前我们需要先导入
     * compile 'com.android.support:support-annotations:25.1.1'类库
     *
     * @Keep 只是保持类名，里面的变量还是被混淆
     *
     *
     */
    void a7(){}


    /**
     * 2020-04-07 gradle技术分享
     * 闭包 groovy
     * android 工程各个 gradle文件的执行顺序
     * setting.gradle(读取工程结构) -> build.gradle -> 子模块的 build.gradle ->各种task
     *
     * -----监听gradlew 生命周期 来做一些操作
     * 1.
     * （通过System.getEnv）
     * 配置构建apk的输出目录
     * 2.配置统一的version
     * 3.
     *
     * ---------各种task 自定义task
     * 参考tinker task
     *
     *
     * -------自定义 plugin
     * 用groovy编写，
     *
     * ------组件化配置
     * 各个项目可以aar包依赖，
     * 如果没有aar引用，可以动态include 引用 进来
     * ----多渠道打包
     * 美团-瓦利
     *
     *
     */
    void a8(){}


    /**
     * https://source.android.google.cn/security/apksigning?hl=zh-cn （应用签名）
     * ========v1 v2 v3签名=============
     * V1(Jar Signature)  V2(Full APK Signature)
     *
     * v1是对jar签名，但是不对元数据进行签名，例如 ZIP 元数据
     * v2+ 方案会将 APK 文件视为 Blob，并对整个文件进行签名检查。对 APK 进行的任何修改（包括对 ZIP 元数据进行的修改）
     * 都会使 APK 签名作废。这种形式的 APK 验证不仅速度要快得多，而且能够发现更多种未经授权的修改
     *
     * 7.0以下Android系统的手机只验证v1 (源码中只有v1验证)
     * 7.0以上先验证 apk 中是否有 v2的签名信息在 apk的签名中，如果有就验证，没有就说明apk没有用v2签名，所以Android系统就只会验证
     * v1签名
     * （所以v1是必选的，因为在搭载7.0以下手机的Android系统只会验证v1签名， 7.0以上才会优先验证v2）
     *
     * 在 Android P 中，v2 方案已更新为 v3 方案，以便在签名分块中包含其他信息，但在其他方面保持相同的工作方式
     *
     * ===============apksigner 和 jarsigner=========
     *  https://blog.csdn.net/qq_32115439/article/details/55520012  Android-APK签名工具-jarsigner和apksigner
     * jarsigner 是JDK提供的针对jar包签名的通用工具, 是对每个class文件(内容)进行hash计算，然后存入到MATA_INF中
     * JDK/bin/jarsigner.exe
     *
     * V1签名: 来自JDK(jarsigner), 对zip压缩包的每个文件进行验证, 签名后还能对压缩包修改(移动/重新压缩文件)
     * 对V1签名的apk/jar解压,在META-INF存放签名文件(MANIFEST.MF, CERT.SF, CERT.RSA),
     * 其中MANIFEST.MF文件保存所有文件的SHA1指纹(除了META-INF文件), 由此可知: V1签名是对压缩包中单个文件签名验证
     *
     * V2签名:
     * 来自Google(apksigner), 对zip压缩包的整个文件验证, 签名后不能修改压缩包(包括zipalign),
     * 对V2签名的apk解压,没有发现签名文件,重新压缩后V2签名就失效, 由此可知: V2签名是对整个APK签名验证
     * （V2签名实际上就是将构建后的内容(dex,res 等)压缩成 apk （就是zip压缩包），然后对zip内容进行签名）
     *
     * apksigner是Google官方提供的针对Android apk签名及验证的专用工具,
     * 位于Android SDK/build-tools/SDK版本/apksigner.bat
     *
     *
     *
     *
     * 注意: apksigner 工具默认同时使用V1和V2签名,以兼容Android 7.0以下版本
     *
     *
     * =========== zipalign =====
     * 对齐使用的是android-sdk/tools目录下的 zipalign 工具，
     * 主要工作是将apk包中所有的资源文件起始偏移为4字节的整数倍，这样通过内存映射访问apk时的速度会更快(比如home访问应用图标)
     *
     * zipalign是在应用签名之后(如果v2签名后就不能用zipalign了，因为这样改动了zip包内容导致签名会验证失败)
     *
     * 位于Android SDK/build-tools/SDK版本/zipalign
     * zipalign 是对zip包对齐的工具,使APK包内未压缩的数据有序排列对齐,从而减少APP运行时内存消耗
     * zipalign -v 4 in.apk out.apk   //4字节对齐优化
     * zipalign -c -v 4 in.apk        //检查APK是否对齐
     *
     * zipalign可以在V1签名后执行
     * 但zipalign不能在V2签名后执行,只能在V2签名之前执行！！！
     *
     *
     * =========360加固后用walle打包==============
     * https://jiagu.360.cn/#/global/download 下载mac 版，如果10.15以下版本打不开就复制一下里面的app覆盖外面的
     *
     * studio 打包出来的 是签名包
     * 然后加固（把dex加密后重新打包成 加固包）
     * 因为改动的apk，然后需要重新签名
     * 签名后用walle 来重新写入 （写入到 APK Signing Block）
     *
     *  failed to install xx.apk: Failure [INSTALL_PARSE_FAILED_NO_CERTIFICATES:
     *  Failed to collect certificates from /data/app/vmdl417198627.tmp/base.apk using APK Signature Scheme v2:
     *  Size of APK Signing Block is not a multiple of 4096: 4140]
     *
     * 意思是加固后重新签名，然后写入渠道信息，就废了。。。
     *
     *
     *
     *
     *
     */
    void a9(){}
}
