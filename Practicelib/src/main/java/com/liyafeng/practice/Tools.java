package com.liyafeng.practice;

import android.content.Context;
import android.os.Build;
import android.os.Trace;

/**
 * Created by liyafeng on 16/11/2017.
 */

public class Tools {

    /**
     * 说说Android 编译打包的过程
     * <p>
     * http://blog.csdn.net/luoshengyang/article/details/8744683
     * http://mouxuejie.com/blog/2016-08-04/build-and-package-flow-introduction/
     * <p>
     * 说说zipalign?
     * https://www.jianshu.com/p/10bc0c632eda
     *
     * https://blog.csdn.net/jiangwei0910410003/article/details/50628894
     * (Android逆向之旅---解析编译之后的Resource.arsc文件格式)
     *
     * ===========加固流程======
     * https://www.jianshu.com/p/4ff48b761ff6 （Android应用加固原理）
     *
     */
    void a1(Context context) {
        context.getResources().getDrawable(R.drawable.build_simplified);
        context.getResources().getDrawable(R.drawable.build_apk);
        context.getResources().getDrawable(R.drawable.build_all);
        /*
         * 首先将java文件，R.java，编译成class（字节码）文件
         * 将工程的字节码文件，和library（依赖库）中的字节码文件合并成dex文件
         * 将values中的文件（strings.xml color.xml styles.xml）用aapt 打包到resources.arsc中
         * 将dex文件、resources.arsc、layout和drawable中的xml和png文件、lib中的so
         * assets中的js或者html，一起用apkbuilder，压缩到apk文件中
         * 然后后用jarsigner 对apk进行签名，防止apk被修改
         * 最后用zipalign ，将apk包中的内容对齐，这有利于资源的查找速度
         * 比如我们apk安装时home应用会读取其中的app名称和图标，读取应用的
         * 权限等，如果对齐有利于查找（就像代码格式化后有利于阅读一样）
         *
         *
         * ===============R文件有什么作用 R.java==========
         * R文件在 项目/build/generate/source/r/[build_type]/[包名]/R.java
         *
         * 默认有attr、drawable、layout、string等四个静态内部类
         * 他是资源的字典
         *
         * */
    }

    /**
     * android studio 目录下build/下的文件夹都是什么作用？
     */
    public void a1_1() {
        /*
         * The "generated" folder contains java code generated by Android Studio for the module. The primary file here is "R.java" which assigns symbolic names to each of the items in the "res" directory so they can be referenced in java source code.

         *The "intermediates" folder contains individual files that are created during the build process and which are eventually combined to produce the "apk" file.
         *
         *The output folder is missing because the module ".iml" file explicitly excludes it with the following statement:
         *
         *<excludeFolder url="file://$MODULE_DIR$/build/outputs" />
         *
         *Remove that line and the "output" directory will appear under build.
         */
    }

    /**
     * android-apt的使用?
     * https://joyrun.github.io/2016/07/19/AptHelloWorld/
     * https://www.jianshu.com/p/2494825183c5
     * https://juejin.im/entry/584a29a5128fe1006c7923a5
     */
    public void a1_2() {
        /*
         * 最早我们用android-apt 这个工具，但是现在已经不维护了
         * 因为Gradle推出了官方的处理工具 annotationProcessor
         *
         * Annotation Processing Tool 注解处理工具，用注解来生成代码
         */
        //这是以前的android-apt工具使用
//        buildscript {
//            repositories {
//                jcenter()
//            }
//            dependencies {
//                classpath 'com.neenbedankt.gradle.plugins:android-apt:1.8'
//            }
//        }
//        apply plugin: 'com.neenbedankt.android-apt'
//        dependencies {
//            compile 'org.greenrobot:eventbus:3.0.0'
//            apt'org.greenrobot:eventbus-annotation-processor:3.0.1'//apt
//        }

        //Gradle自带的  annotationProcessor
//        dependencies {
//            compile 'org.greenrobot:eventbus:3.0.0'
//            annotationProcessor  'org.greenrobot:eventbus-annotation-processor:3.0.1'
//        }
    }


    /**
     * 谈谈你对安卓签名的理解?
     * https://blog.csdn.net/jiangwei0910410003/article/details/50402000（signapk.jar源码分析）
     * https://juejin.im/entry/575ed0bb1532bc00609c3aa9
     * https://maoao530.github.io/2017/01/31/apk-sign/
     */
    public void a2(Context context) {
        /*
         * 整个过程用到了SHA-1 的hash算法，和RSA非对称加密
         * 首先签名过程就是在apk(压缩包)中生成一个META-INF文件夹，里面有三个文件
         * MANIFEST.MF  CERT.SF  CERT.RSA 文件，我们就是靠这三个文件来验证apk是没有
         * 被修改过的。
         *   而产生这三个文件的程序就是用 apksigner.jar用xxx.keystore来生成的，
         * xxx.keystore中存储了公钥和私钥 ，而生成xxx.keystore 需要用keytool.jar这个工具
         * keytool -genkeypair -v -keyalg DSA -keysize 1024 -sigalg SHA1withDSA -validity 20000 -keystore D:\xxx.keystore -alias 别名 -keypass 使用密码 -storepass 打包密码？
         * apksigner 签名时用的是pk8和x509.pem文件，xxx.keystore文件可以转化为他们
         * apksigner.jar 源码分析 源码位置：com/android/signapk/sign.java
         * 1，首先生成MANIFEST.MF 文件，apksigner.jar对工程中所有文件的内容做Sha-1计算，
         *   生成摘要信息，然后对摘要进行Base64编码，然后将 name:[文件名] sha1-digest:[摘要]
         *   这个键值对写入文件中。（这样能保证每个文件内容没有被修改过，因为修改了sha1值会变）
         * 2,生成CERT.SF，对MANIFEST.MF 文件做sha1生成摘要信息，然后base64编码
         *   将sha1-digest-manifest:[摘要]写入文件首部
         *   对MANIFEST.MF中的每个“键值对”做sha-1，然后base64编码，写入 name:[key] sha1-digest:[摘要]
         *  （这样就能保证MANIFEST.MF 中的“键值对”没有被修改过）
         * 3，生成CERT.RSA，里面存储了签名使用的算法，公钥信息，然后将CERT.SF和前面的信息用私钥加密
         *   然后写入文件结尾（这样就保证了前面所以文件都不能被修改，因为用公钥解密这个密文，得出
         *   的结果要和之前的信息一致）
         */

        context.getResources().getDrawable(R.drawable.cert_rsa);
    }

    /**
     * apk反编译的流程？
     * x.509是什么？
     */
    public void a3(Context context) {
        /*
         * ===================apk中的内容=====================
         * 将apk后缀改为zip，解压即可
         * apk中有
         * class.dex这个是所有java文件编译成class后合并为一个或多个dex文件
         * resource.arsc 这个是所有res文件和id的映射
         * lib 一般是so库
         * assets 不会编译这个，是存放静态资源，比如html js文件
         * AndroidManifest.xml 里面声名权限，四大组件等信息
         * MATA_INF 签名信息，保证apk内容不被篡改，保证是指定私钥签名的
         *  而不是他人伪造的apk
         *
         * 这里我们只能看到资源文件，而代码在dex中看不到，必须要反编译
         *
         * apktool反编译后再values下有个public.xml
         *  type：类型名
         *  name：资源名
         *  id：资源的id
         *  类型的话有这么几种：
         *  drawable，menu，layout，string，attr，color，style等
         *
         *
         * ===================反编译查看内容=============================
         * apktool 下载 https://ibotpeaches.github.io/Apktool/install/
         *   反编译出的资源，能查看出Manifest的内容，还有string ,value，layout中的文本
         *   正常解压出来查看是乱码!!!
         *   apk d[ecode] filename   注意不是 -d
         * 这个需要jdk，配置环境变量
         * https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html
         *
         * apktool使用方法
         * https://ibotpeaches.github.io/Apktool/documentation/
         *
         * dex2jar 下载 https://sourceforge.net/projects/dex2jar/files/
         *    d2j-dex2jar.bat filename.dex 转化为jar包
         *
         * 如果执行命令需要权限，那么手动修改权限（MAC）
         *   chmod -R +x [dir]
         *  解压后的目录默认在用户根目录下： ~/[name]-dex2jar.jar
         *
         * -------查看jar中的内容-------------
         * jd-gui http://jd.benow.ca/
         *    点击jd-gui.exe 开启程序，打开上面转化后的jar包，然后就可以查看代码了
         *
         * 或者直接复制到Android studio中的lib中，把jar包添加依赖，然后就自动反编译了
         * ===========================从新打包=============================
         * 使用 apktool b[uild] -o new.apk <反编译后的文件夹名称>
         * 这样就生成了 new.apk，但是这种没有 META-INF文件夹，安装会提示失败
         * 因为这个没有签名。
         * 所以我们对它签名，先要生成签名文件，xx.keystore,用keytool这个工具，这个是jdk中的
         * keytool -genkey -alias demo.keystore -keyalg RSA -validity 40000 -keystore demo.keystore
         * 生成了demo.keystore ，然后我们要用jarsigner.exe(也是jdk中的)对apk进行签名
         * jarsigner -verbose -keystore demo.keystore new.apk demo.keystore
         * 这样就在apk中生成了MATE-INF文件夹
         * 然后我们就可以安装了，但是要把之前安装的程序删除，因为我们的签名文件不一样
         * 否则会提示更新冲突，和之前的签名文件不一致，导致安装失败
         *
         * ====================x.509是什么？======================
         * 是一种证书的格式，规定了公钥name:value的一些格式
         */
        context.getResources().getDrawable(R.drawable.cert_rsa);
    }

    /**
     * 如何自定义Gradle插件?
     * https://kotlintc.com/articles/3075
     */
    public void a4() {
        /*
         * 具体可以看项目中的Module：hotfixcustomgradplugin
         * 一个gradle插件就是一个groovy项目
         * 具体就是mian/groovy/包名/MyPlugin.groovy
         * 和resources/META-INF/gradle-plugins/插件名称.properties
         * 里面指定插件实现的类，implementation-class=com.liyafeng.plugin.MyPlugin
         * 这两个文件有了后就可以生成插件了
         * 当然我们要使用groovy的插件 apply plugin: 'groovy'
         * 依赖产生插件的包
         *    compile gradleApi()
         *   compile localGroovy()
         *
         * 然后我们要用maven的插件来将jar包（插件）来发布到maven仓库
         *
         * 在另一个项目中添加meven库的路径，用classpath指定使用哪个库
         * 然后apply plugin '插件名'来使用插件
         *
         * 这样我们点击run的时候就会执行我们的插件代码，我们能获取到Project对象
         * 就能做一些操作，比如动态生成代码
         *====================================================
         * 在build.gradle 中apply plugin'插件名' 会执行插件中的apply(Project p)方法
         * Project代表整个工程的信息。在里面我们可以做自己的自定义操作
         * 我们可以自定义 自己的 Extension ,(一个Extension代表一个 花括号name{})
         *
         * 这样我们就可以读取用户在build.gradle中的name{ }中用户设置的值
         */
    }

    /**
     * 如何使用Gradle的transform处理字节码？
     * <p>
     * https://bintray.com/android/android-tools/com.android.tools.build.transform-api(官方库)
     * <p>
     * javassist 官方库
     * https://mvnrepository.com/artifact/org.javassist/javassist
     */
    public void a5() {
        /*
         * 其实这就是自定义一个Gradle插件（需要新建一个工程并且发布到仓库）
         * 在这个插件中我们自定义Transform类，来对class文件进行处理
         * 然后我们在要处理字节码的工程中引用这个插件。
         * 然后make module ，我们的插件就能执行了。
         *
         * 所以说要使用这个类（api），就得先学会如何自定义gradle插件并使用
         * 然后我们再在插件中加入自定义的Transform
         *
         */
    }

    /**
     * 对Gradle的理解？
     * <p>
     * https://segmentfault.com/a/1190000004229002 (专门讲gradle的一个系列)
     */
    public void a6() {
        /*
         * Gradle是一个软件，他帮我们构建一个Module，打包成apk
         * 但是真正打包的执行者是我们在 build.gradle中设置的插件
         * apply plugin: 'com.android.application'
         * 而这个插件是在Project的build.gradle中配置的
         * buildscript { //这里要指定我们具体的构建脚本
         *    repositories {
         *      //指定构建插件所在的库的地址
         *      //（jcenter就代表会去https://bintray.com/bintray/jcenter 这个网站上下载对应的jar包）
         *        jcenter()
         *    }
         *    dependencies {
         *          //指定插件 组名，项目名，版本号，然后下载里面的jar包
         *        classpath 'com.android.tools.build:gradle:3.0.1'
         *        //下载的位置就是 Android Studio安装目录下
         *        //xxx\Android Studio\gradle\m2repository\com\android\tools\build\gradle\[版本号]\gradle-[版本号].jar
         *
         *    }
         * }
         *
         * 在apply plugin: 'com.android.application' 应用了这个插件后
         * 我们就能调用里面的方法，去做一些编译时的配置
         * 我们看到google为android打包写的gradle插件 gradle-3.0.1.jar中
         * 也有很多自定义的Plugin，这些都是编译的时候进行一些处理
         *
         *
         *
         */
    }

    /**
     * 描述清点击 Android Studio 的 build 按钮后发生了什么?
     */
    public void a7() {
        /*
         * 点击build，gradle会执行 google为android写的插件
         * com.android.application
         *
         * 这个插件的下载位置在Android Studio目录下
         * \gradle\m2repository\com\android\tools\build\gradle\3.0.1\gradle-3.0.1.jar
         * 里面的META-INF\gradle-plugin\com.android.application.properties
         * 中指定了要执行插件的类
         * implementation-class=com.android.build.gradle.AppPlugin
         *
         * 然后会执行AppPlugin类中的apply(Project p)方法
         * 然后所有的调用都在这里执行各种task
         *
         * 具体的task我们可以在Android Studio 右侧的Gradle栏看到
         * 接下来这些task做的就是编译打包的流程
         */
    }


    //region git

    /**
     * git 流是怎样的
     */
    public void a8(Context context) {
        /*
         *
         */
        context.getResources().getDrawable(R.drawable.git);
    }
    //endregion

    //region systrace

    /**
     * 如何使用systrace
     * https://developer.android.google.cn/studio/command-line/systrace（官方介绍）
     * https://zhuanlan.zhihu.com/p/27331842 （田维术的讲解）
     */
    public void a9() {
        /*
         * systrace 在sdk的platform-tools/systrace中
         * 用来分析app运行的时间信息，方法的耗时
         * 它是用Python写的，最终生成html的结果
         *
         *
         */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            Trace.beginSection("lll");
            Trace.endSection();
        }
    }
    //endregion


    /**
     * 查找依赖库的指定版本
     * 去jcenter这个网站输入包名即可
     * <p>
     * https://bintray.com/bintray/jcenter
     * <p>
     * http://jcenter.bintray.com/包名
     */
    public void a10() {

    }


    /**
     * 图片压缩
     * https://pngquant.org
     * tinypng.com
     * 我们对图片压缩很有必要
     * 用ps给出的图是 32-bit color 的
     * 我们压缩后变为 8bit-color ,Android加载后足足小了三倍！！
     * 效果还是很明显的
     */
    public void a11() {

    }
}
