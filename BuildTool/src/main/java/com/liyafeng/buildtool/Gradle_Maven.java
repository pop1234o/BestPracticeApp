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
     * ===========什么是maven===========
     * https://maven.apache.org/
     * https://maven.apache.org/what-is-maven.html （什么是maven）
     * Apache Maven is a software project management and comprehension tool.
     * Based on the concept of a project object model (POM),
     * Maven can manage a project's build, reporting and documentation from a central piece of information.
     * maven是一个软件，是java写的，它用来构建，发布，工程 从maven仓库
     * Apache Maven是一个项目管理和理解工具，它基于项目对象模型(POM)的概念，它可以管理项目的构建、报告和文档
     *
     * 一个项目生命周期(Project Lifecycle)，一个依赖管理系统(Dependency Management System)，
     * 能帮你构建工程，管理jar包，编译代码，还能帮你自动运行单元测试，打包，生成报表，甚至能帮你部署项目，生成Web站点
     *
     * ================maven能做什么================
     * 1.项目自动化编译部署：
     * 2.项目jar包的依赖管理： 自动下载项目需要的jar，还可以自动上传项目构建好的jar
     * 3.企业开发分包分模块部署：
     *
     * ======================maven仓库===============
     * https://www.runoob.com/maven/maven-repositories.html
     *
     * 支持maven工具下载jar包的网站，叫maven仓库，他们目录中有 .pom文件，所以支持maven工具下载
     *
     * 本地的仓库地址 ~/.m2/repository   下载的依赖库都在这， ~代表用户目录
     * 默认的远程库 ()https://repo1.maven.org/maven2/
     * 阿里云镜像的maven仓库  http://maven.aliyun.com/nexus/content/groups/public/
     * google的仓库 as3.0之前
     *  maven {
     *             url "https://maven.google.com"
     *  }
     *  之后
     *  allprojects {
     *     repositories {
     *         google()  // add google() before jcenter()
     *         jcenter()
     *     }
     * }
     *
     *
     * 阿里云有很多国外仓库的镜像 https://maven.aliyun.com/mvn/view
     * jcenter (https://maven.aliyun.com/repository/jcenter)
     * maven central (https://maven.aliyun.com/repository/central)
     * Google的 （ https://maven.aliyun.com/repository/google ）
     *
     *
     *
     * （maven仓库列表）https://mvnrepository.com/repos
     *
     *  maven工具可以从下面的网站上下载jar
     *
     * http://jcenter.bintray.com/
     * http://central.maven.org/maven2/
     * jitpack.io
     * ==================mavenCenter仓库===============
     * maven中央仓库（http://repo1.maven.org/maven2/）是由Sonatype公司提供的服务，它是Apache Maven、SBT和其他构建系统的默认仓库，
     * 并能很容易被Apache Ant/Ivy、Gradle和其他工具所使用。
     * 开源组织例如Apache软件基金会、Eclipse基金会、JBoss和很多个人开源项目都将构件发布到中央仓库。
     * maven中央仓库已经将内容浏览功能禁掉了，可在http://search.maven.org/查询构件。
     *
     *
     * ================jcenter(仓库)是什么 =============
     * https://www.geekhub.cn/a/1295.html（JCenter是什么？）
     * 他是一个maven仓库
     * 它是由 JFrog 公司提供的 Bintray 中的 Java 仓库。它是当前世界上最大的 Java 和 Android 开源软件构件仓库。
     * 所有内容都通过内容分发网络（CDN）使用加密https连接获取。JCenter是Goovy Grape内的默认仓库，Gradle内建支持（jcenter()仓库），
     * 非常易于在（可能除了Maven之外的）其他构建工具内进行配置。
     * 与 Maven Central 相比，JCenter 的速度更快，包含的库更多，UI界面更友好，更容易使用，
     * 同时 Bintray 还支持将 JCenter 上传到 Maven Central 的功能。
     *
     *
     *
     *
     * ===========总结=========
     * maven是一个工具，可以构建和发布项目，和管理项目依赖的依赖库
     * maven仓库 是存jar/aar包的地方，构建工具(Ant、Gradle、maven)可以从这里下载jar包
     * jcenter和mavenCentral都是一种maven仓库
     *
     * 构建和依赖管理工具：Ant Gradle Maven
     * 构建工具下载jar/aar包的地方(maven仓库): jcenter ，mavenCentral
     *
     */
    void a0(){}


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
     * -----------------
     *  引入aar包
     *  我们需要在build.gradle(module下的) 中最外层加入
     *
     *  repositories {
     *      flatDir {
     *          dirs 'libs'
     *      }
     *  }
     *  表示repositories 库的目录是 平行目录下的libs文件夹
     *
     *  然后在
     *  dependencies {
     *      implementation fileTree(dir: 'libs', include: ['*.jar']) //这个是代表引入lib中的jar包
     *      implementation(name: 'testlibrary-release', ext: 'aar')
     *   }
     * ===============查看arr内容======
     * 其实想要查看AAR文件里面的内容很简单，只需要将文件名的后缀由".aar" 改为".zip", 然后再解压zip文件即可。
     * 或者用jd-gui也可以查看aar包
     * 内容：
     * aidl
     * assets  assets/fonts
     * jni
     * libs
     * res  (layout,values)
     * AndroidManifest.xml
     * R.txt
     * classes.jar
     *
     *
     */
    void a4(){}


    /**
     *
     *  ===============aar反编译&重新打包==============
     *  aar包就是zip文件，直接改后缀解压即可，或者用jd-gui也可以看
     *  里面有classes.jar
     *
     * ==============jar包改代码&重新打包==========
     * 用jd-gui打开，file->save all sources 导出反编译后的java源码
     * 修改
     *
     *
     *
     *
     * ===============jar命令用法========
     * 用法: jar {ctxui}[vfmn0PMe] [jar-file] [manifest-file] [entry-point] [-C dir] files ...
     *
     * {ctxu}代表只能选择一个，[是可选项]
     * 选项:
     *     -c  创建新档案
     *     -t  列出档案目录
     *     -x  从档案中提取指定的 (或所有) 文件
     *     -u  更新现有档案
     *     -v  在标准输出中生成详细输出
     *     -f  指定档案文件名
     *     -m  包含指定清单文件中的清单信息
     *     -n  创建新档案后执行 Pack200 规范化
     *     -e  为捆绑到可执行 jar 文件的独立应用程序
     *         指定应用程序入口点
     *     -0  仅存储; 不使用任何 ZIP 压缩
     *     -P  保留文件名中的前导 '/' (绝对路径) 和 ".." (父目录) 组件
     *     -M  不创建条目的清单文件
     *     -i  为指定的 jar 文件生成索引信息
     *     -C  更改为指定的目录并包含以下文件
     * 如果任何文件为目录, 则对其进行递归处理。
     * 清单文件名, 档案文件名和入口点名称的指定顺序
     * 与 'm', 'f' 和 'e' 标记的指定顺序相同。
     *
     * 示例 1: 将两个类文件归档到一个名为 classes.jar 的档案中:
     *        jar cvf classes.jar Foo.class Bar.class
     * 示例 2: 使用现有的清单文件 'mymanifest' 并
     *            将 foo/ 目录中的所有文件归档到 'classes.jar' 中:
     *        jar cvfm classes.jar mymanifest -C foo/ .
     *
     *  jar cvf classes.jar -C foo/ .
     *  foo/ 是.class开始的目录
     *  . 是输出到用户目录
     */
    void a4_1(){}


    /**
     * ==============java命令==============
     * 执行class或者jar
     *
     * java [-options] class [args...]
     *            (执行类)
     *    或  java [-options] -jar jarfile [args...]
     *            (执行 jar 文件)
     * 其中选项包括:
     *     -d32	  使用 32 位数据模型 (如果可用)
     *     -d64	  使用 64 位数据模型 (如果可用)
     *     -server	  选择 "server" VM
     *                   默认 VM 是 server,
     *                   因为您是在服务器类计算机上运行。
     *
     *
     *     -cp <目录和 zip/jar 文件的类搜索路径>
     *     -classpath <目录和 zip/jar 文件的类搜索路径>
     *                   用 : 分隔的目录, JAR 档案
     *                   和 ZIP 档案列表, 用于搜索类文件。
     *     -D<名称>=<值>
     *                   设置系统属性
     *     -verbose:[class|gc|jni]
     *                   启用详细输出
     *     -version      输出产品版本并退出
     *     -version:<值>
     *                   警告: 此功能已过时, 将在
     *                   未来发行版中删除。
     *                   需要指定的版本才能运行
     *     -showversion  输出产品版本并继续
     *     -jre-restrict-search | -no-jre-restrict-search
     *                   警告: 此功能已过时, 将在
     *                   未来发行版中删除。
     *                   在版本搜索中包括/排除用户专用 JRE
     *     -? -help      输出此帮助消息
     *     -X            输出非标准选项的帮助
     *     -ea[:<packagename>...|:<classname>]
     *     -enableassertions[:<packagename>...|:<classname>]
     *                   按指定的粒度启用断言
     *     -da[:<packagename>...|:<classname>]
     *     -disableassertions[:<packagename>...|:<classname>]
     *                   禁用具有指定粒度的断言
     *     -esa | -enablesystemassertions
     *                   启用系统断言
     *     -dsa | -disablesystemassertions
     *                   禁用系统断言
     *     -agentlib:<libname>[=<选项>]
     *                   加载本机代理库 <libname>, 例如 -agentlib:hprof
     *                   另请参阅 -agentlib:jdwp=help 和 -agentlib:hprof=help
     *     -agentpath:<pathname>[=<选项>]
     *                   按完整路径名加载本机代理库
     *     -javaagent:<jarpath>[=<选项>]
     *                   加载 Java 编程语言代理, 请参阅 java.lang.instrument
     *     -splash:<imagepath>
     *                   使用指定的图像显示启动屏幕
     * 有关详细信息, 请参阅 http://www.oracle.com/technetwork/java/javase/documentation/index.html
     */
    void a4_2(){}

    /**
     * ================javac命令================
     * 用来编译java文件为class文件
     *
     * 用法: javac <options> <source files>
     *     javac [ options ] [ sourcefiles ] [ @files ]
     *
     *     options
     *          命令行选项。
     *      sourcefiles
     *          一个或多个要编译的源文件（例如 MyClass.java）。
     *      @files
     *          一个或多个对源文件进行列表的文件。可以是txt文件，里面用java文件的列表
     *
     *
     * 其中, 可能的选项包括:
     *   -g                         生成所有调试信息
     *   -g:none                    不生成任何调试信息
     *   -g:{lines,vars,source}     只生成某些调试信息
     *   -nowarn                    不生成任何警告
     *   -verbose                   输出有关编译器正在执行的操作的消息
     *   -deprecation               输出使用已过时的 API 的源位置
     *   -classpath <路径>            指定查找用户类文件和注释处理程序的位置
     *   -cp <路径>                   指定查找用户类文件和注释处理程序的位置
     *   -sourcepath <路径>           指定查找输入源文件的位置
     *   -bootclasspath <路径>        覆盖引导类文件的位置
     *   -extdirs <目录>              覆盖所安装扩展的位置
     *   -endorseddirs <目录>         覆盖签名的标准路径的位置
     *   -proc:{none,only}          控制是否执行注释处理和/或编译。
     *   -processor <class1>[,<class2>,<class3>...] 要运行的注释处理程序的名称; 绕过默认的搜索进程
     *   -processorpath <路径>        指定查找注释处理程序的位置
     *   -parameters                生成元数据以用于方法参数的反射
     *   -d <目录>                    指定放置生成的类文件的位置
     *   -s <目录>                    指定放置生成的源文件的位置
     *   -h <目录>                    指定放置生成的本机标头文件的位置
     *   -implicit:{none,class}     指定是否为隐式引用文件生成类文件
     *   -encoding <编码>             指定源文件使用的字符编码
     *   -source <发行版>              提供与指定发行版的源兼容性
     *   -target <发行版>              生成特定 VM 版本的类文件
     *   -profile <配置文件>            请确保使用的 API 在指定的配置文件中可用
     *   -version                   版本信息
     *   -help                      输出标准选项的提要
     *   -A关键字[=值]                  传递给注释处理程序的选项
     *   -X                         输出非标准选项的提要
     *   -J<标记>                     直接将 <标记> 传递给运行时系统
     *   -Werror                    出现警告时终止编译
     *   @<文件名> 从文件读取选项和文件名
     */
    void a4_3(){}


    /**
     * ==========
     * https://zhuanlan.zhihu.com/p/29345229(利用原始的javac编译整个Java项目)
     *
     * 注意 MANIFEST.MF 文件 定义主函数 即程序入口;定义依赖库文件的路径 内容如下：
     * Manifest-Version: 1.0
     * Main-Class: com.travel.app.AppMainEntry
     * Class-Path: bin/fastjson-1.2.37.jar bin/json-20170516.jar bin/org.restlet.ext.json-2.3.10.jar bin/org.restlet-2.3.10.jar
     *
     *
     *
     */
    void a4_4(){}


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
     * ============api implement 下载的内容============
     * 里面有
     * [项目名]-[version].pom 代表这个项目所需的依赖库
     * [项目名]-[version]-sources.jar源码
     * [项目名]-[version].aar classes.jar 资源文件  AndroidManifest.xml等
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
