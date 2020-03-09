package com.liyafeng.jni;

/**
 * Created by liyafeng on 2018/4/24.
 */

public class Main_jni {

    /**
     * * ndk入门指南
     * https://developer.android.google.cn/ndk/guides/index.html?hl=zh-cn
     * jni介绍
     * https://developer.android.google.cn/training/articles/perf-jni.html
     * <p>
     * hello-jni示例
     * https://github.com/googlesamples/android-ndk/tree/master/hello-jni
     * <p>
     * 使用c/c++示例
     * https://developer.android.google.cn/studio/projects/add-native-code.html
     * <p>
     * ndk下载
     * https://developer.android.google.cn/ndk/downloads/index.html
     * <p>
     * application_mk
     * https://developer.android.google.cn/ndk/guides/application_mk.html
     * https://developer.android.google.cn/ndk/guides/android_mk.html
     * <p>
     * jni官方介绍（包括原理）
     * https://docs.oracle.com/javase/7/docs/technotes/guides/jni/spec/jniTOC.html
     * ============为什么用jni====================
     * 从设备获取卓越性能以用于计算密集型应用，例如游戏或物理模拟。
     * 重复使用您自己或其他开发者的 C 或 C++ 库。
     * <p>
     * <p>
     * ====================jni使用步骤===========================
     * 首先我们要下载ndk，我们生成so文件就是靠里面的 ndk-build.cmd
     * 在Tools=>Android=>SDK Manager中安装Cmake，和NDK
     * 1，我们要在java文件中生命native方法
     * 2.使用javac 编译java文件为class文件（或者点AS的 Build->Make Module "xxx",
     * 在Module的build/intermediates/classes/包名/ 下有自动编译的class文件）
     * 3.用javah命令生成xx.h头文件，javah com/liyafeng/jni/xxx  在com的同级目录生成
     * 头文件，我们将它改成.c文件，然后实现里面的方法，注意方法参数要补全（或者自己定义
     * 一个.c文件，把生成的头文件include进来）
     * 4.生成Android.mk，这个文件时是帮助ndk-build命令来生成so文件的，一般我们
     * 在项目的src/main/jni中创建了c/c++文件，然后build一下，就在build/intermediates/ndk
     * 中生成了Android.mk
     * 5.此时需要把Android.mk拷贝到src/main/jni中，然后再这个目录运行 ndk-build命令
     * 然后就会生成对应的so文件，然后将这些so文件加入，src/main/jniLibs/xxabi中
     * 当然我们可以指定so文件的位置 jniLibs.srcDirs = ['libs']
     * 然后我们在java文件中，我们将so文件的lib前缀去掉
     * static {
     * System.loadLibrary("custom_jni");
     * }
     * 最后我们就可以用native方法了
     * ---------------------------
     * window中用dll文件，linux中用so
     * 加载后，jvm就会生成so中定义的方法和java native方法的映射
     * 这样就能对应调用了，如果没有这个方法，就会报UnsatisfiedLinkException
     * ====================================================
     * 需要将jni.h（该文件可以在%JAVA_HOME%/include文件夹下面找到）文件引入，
     * 因为在程序中的JNIEnv、 jobject等类型都是在该头文件中定义的
     * <p>
     * =================================================
     * 1写java native方法
     * 2.生成class，生成.h
     * 3.编写Android.mk，使用NDK的ndk-build命令来生成so
     * 4.将so加入工程，用静态代码块加载，运行，即可调用native方法了
     *
     * ========================c/c++调用java方法=================
     * 从参数列表中获取 jobject 或者jclass ，我们要用*env 来调用jni.h中的函数
     * *env->getMethodID(jobject,"方法名","方法签名);
     * 方法签名可以用javap命令来查询  javap -s -p class文件
     *
     * ===================jni中类型对应-================
     * java中类型在不同平台中所占用的位数是相同的
     * 而c在不同平台中类型的位数不同。所以我们要将java中类型映射为c中的类型
     * jint jfloat jstring jdouble等
     * ======================jni运行java程序================
     * 我们知道java调用c的时候，jvm已经启动了，那么能不能再c中启动jvm呢？
     * 比如我们写好了c，编译成exe文件，在windows中执行，他要调用java的class文件
     * 那么就先要启动java虚拟机，这时候就要用到jni中的invokcation API，
     * 这个api可以让我们启动jvm，然后findclass函数来找到对应的类，（根据classpath来查找）
     * 然后会加载class到jvm中执行，最后提供了销毁jvm的方法
     *
     *
     * =============== lldb ==============
     * https://lldb.llvm.org/use/remote.html
     * https://developer.android.google.cn/studio/debug
     *
     * lldb 一个调试native代码的工具
     *
     *
     *
     * =====================ndk=====================
     * NDK 工具包中提供了完整的一套将 c/c++ 代码编译成静态/动态库的工具
     * 而 Android.mk 和 Application.mk 你可以认为是描述编译参数和一些配置的文件
     *
     * ndk-build 文件是 Android NDK r4 中引入的一个 shell 脚本。
     * 其用途是调用正确的 NDK 构建脚本。其实最终还是会去调用 NDK 自己的编译工具
     *
     * ================abi和so文件兼容====================
     * http://blog.coderclock.com/2017/05/07/android/Android-so-files-compatibility-and-adaptation/
     *
     *  ABI（Application binary interface）应用程序二进制接口
     *
     * armeabi设备只兼容armeabi（指令集）写成的程序
     * armeabi-v7a设备兼容armeabi-v7a、armeabi；
     * arm64-v8a设备兼容arm64-v8a、armeabi-v7a、armeabi；
     * X86设备兼容X86、armeabi；
     * X86_64设备兼容X86_64、X86、armeabi；
     * mips64设备兼容mips64、mips；
     * mips只兼容mips；
     * --------------------------------
     * 尽管armeabi可以兼容多种平台，仍有些运算在armeabi-v7a、arm64-v8a去使用armeabi的SO文件时
     * ，性能会非常差强人意，所以还是应该用其对应平台架构的SO文件进行运算
     * ----------------------------
     *
     *
     *
     * @param args
     */
    public static void main(String[] args) {

    }


    /**
     * C语言的写法
     * JNIEXPORT jstring JNICALL
     * Java_com_liyafeng_cproject_MainActivity_intFromJNI(
     *         JNIEnv *env,
     *         jobject pthis) {
     *     jstring jstr;
     *     char cstr[]="你好吗?";
     *     jstr=(*env)->NewStringUTF(env,cstr);
     *     return jstr;
     * //    return env->NewStringUTF(hello.c_str())
     * }
     */
    void a1(){}


    /**
     * ============android依赖so文件
     * 1.
     * 在src/main中新建jniLibs文件夹 ，把.so复制进去即可
     * 比如 jniLibs/armabi/xx.so 这样gradle自动依赖，约定好的文件名
     *
     * 2.在app/中新建libs文件夹，把.so复制进去
     * gradle中添加
     * sourceSets {
     *     main {
     *         jniLibs.srcDirs = ['libs']
     *     }
     * }
     *
     */
    void a2(){}
}
