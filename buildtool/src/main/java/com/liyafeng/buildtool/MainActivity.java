package com.liyafeng.buildtool;

import android.app.Activity;
import android.os.Bundle;

/**
 * gradle 自动化构建工具
 *
 * 用java写成，
 *
 * 它提供了接口，是一些规范的行为，但他不提供build的实现，build实现需要指定构建脚本
 *
 * buildscript{repositories{jcenter() }  dependenies{ classpath:'com.android.build.tools:gradle:2.3.1'} }
 * 这句话就是指定构建的插件（脚本）是什么，从哪里获取。那么gradle做的就是从jcenter这个网站下载指定的 groupid:arfricId:version
 * 下载的（gradle-2.3.1.jar包）存放目录在studio 下的 gradle/m2repository目录下
 *
 * 我们就是依靠这个jar包（脚本/插件）来构建我们的Android工程的，它依靠调用Android sdk中的build-tools中的工具来完成构建
 * =======================
 * gradle有gradle build language 或者叫 dsl domain sepcifit language
 *
 * gradle 的脚本 script 分三种（脚本类型） :
 * 一个是构建脚本 对应Project对象 build.gradle
 * 一个是配置脚本，对应Settings对象，对应settings.gradle
 * 一个是初始化脚本 对应Gradle对象，在~/.gradle 目录下配置 init.gradle ，一般是大企业用的，用于统一管理多个project
 * 当然在~/.gradle/init.d/ 目录下的*.gradle也是初始化用的
 *
 * ================================
 * 下面主要讲的是 build script
 * 一个build script 包含0个或多个 statements(声明)和 script blocks(脚本块)
 *
 * 声明对应的是：方法和成员变量的调用
 * 而代码块则是 对应的是 一特殊的方法，这个方法以closure（闭包）作为参数
 * 这个闭包（{}）会委托一个object作为他的执行者
 *
 * 那么在Project对象中，也就是在build.gradle脚本文件中，常用的script block 有以下几种：
 * allprojects { } -配置这个project的所有子project
 * buildscript { } -配置这个project的 build script classpath （指定构建插件\jar包的位置）
 * dependencies { } -配置依赖库
 * repositories { } -配置这个project使用的仓库位置
 * 等，见https://docs.gradle.org/4.0/dsl/
 *
 * =========================
 * 一个jar包中用很多pluginID，比如com.android.application com.android.library com.android.test com.android.instantapp
 * 我们要指定使用一个插件 比如 apply plugin:'比如com.android.application'
 * apply这个方法参数是hashmap类型的
 *
 * 然后我们就可以用这个插件中的android{} 这个script block了，里面是build apk的一些参数
 *
 *
 * ============================
 * 下面说dsl ,它实际上就是一种关系映射 是基于Groovy dsl的
 * 比如调用方法 就可以写成
 *
 * methodname 参数
 * 字段 值
 *
 * 如果调用setxxx方法
 *
 * xxx 值
 *
 *
 *
 * ===============================
 * 其实这个插件就是写了很多task，比如编译task ,合并Manifesttask等
 * 我们要查看所有task  : 在project目录下 gradle tasks --all
 *
 * ===================
 * gradle wrapper 是gradle的一个task  https://guides.gradle.org/creating-new-gradle-builds/
 *
 * 我们来到一个空目录，创建空的build.gradle文件
 * 然后 gradle tasks ，就能看到这个task ，执行 gradle wrapper
 * 我们就会看到生成了 gradlew  / gradlew.sh / gradle/wrapper/中有gralde-wrapper.jar 和 gradle-wrapper.properties
 *
 * 这些就是配置好这个工程的gradle了
 *
 * wrapper存在的意义在于即使你的电脑中没有 gradle这个软件，那么你执行gradlew来构建这个项目的时候，
 * gralde会去gradle-wrapper.properties 中配置的网址下载gradle软件
 *
 * 而且可以保证每个人用的gradle软件版本是一致的
 *
 *
 *
 * ==================
 * 当然我们还可以定义自己的task，关于更多自定义taks见https://guides.gradle.org/writing-gradle-tasks/
 * task 任务名{
 *     task的方法{
 *         调用方法
 *     }
 * }
 *
 * task 任务名(type:调用的类名){
 *     调用类中的方法 参数
 * }
 *
 * task clean(type: Delete) {
     delete rootProject.buildDir
    }

   task hello {
     doLast {
     println 'Hello, World!'
     }
   }
 *
 *
 *
 * 我们的Android plugin for gradle 正是实现了这个接口
 *
 *
 * 这个插件在 项目 的build.gradle下指定 ：
 * ====================
 * gradle来构建一个Android app https://guides.gradle.org/building-android-apps/
 *
 * 使用gradle3.0  https://developer.android.com/studio/build/gradle-plugin-3-0-0-migration.html
 *
 * gradle 和 Android plugin for gradle 版本对应  https://developer.android.com/studio/releases/gradle-plugin.html#updating-plugin
 *
 * plugin           gradle
 * 2.3.0+            3.3+
 * 2.1.3-2.2.3       2.14.1+
 *
 * ====================
 * 所有的android plugin dsl 在http://google.github.io/android-gradle-dsl/current/index.html
 * dsl就自定义的，我们可以在里面使用我们定义好的方法，字段
 * 原理就是映射到java的对象中的方法和字段，而这种映射能力是Groovy dsl提供的
 * ====================
 * 优化构建速度  https://developer.android.com/studio/build/optimize-your-build.html
 *
 * 1.优化构建配置
 *  更新最新的Android gradle插件
 *  配置productFlavors 来指定开发的时候需要编译哪些（开发时可能不需要全部编译）比如我们开发的时候只要xhdpi的
 *  禁用崩溃收集器
 *  使用静态值 在你debug build的时候，比如versioncode = 1
 *  使用静态依赖（硬编码依赖库的版本号）
 *  使用offine模式的gradle 这样配置的依赖库就只会在本地找~/.gralde
 *  Enable configuration on demand 只编译当前的module (在preference中的compiler中)
 *  使用library module ,这样不用每次都依赖了
 *  使用webp格式的图形，它压缩率更高，省去了编译器压缩图片的过程
 *  禁用png压缩
 *  使用instant run
 * 2.profile your build (查看你的build信息)
 *  gradlew clean
 *  gradlew --profile --recompile-scripts --offline --rerun-tasks  //assembleFlavorDebug
 *  在build/reports下生成构建报告
 *
 *  禁用一些没必要task ，在debug的时候
 *
 *
 *
 */
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
