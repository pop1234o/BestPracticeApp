package com.liyafeng.buildtool;

public class Gradle_Maven {
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
     * ---------Failed to resolve common open file gradle.build---------
     * https://stackmirror.com/questions/50786296
     * 主要是顺序不对  add google() before jcenter()
     * 好像是缓存问题。。
     *
     *
     * -------------------
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
     * -----------子模块引入aar包---------------
     * https://blog.csdn.net/zxc418983651/article/details/83030344  android子module中引入aar包
     * 子模块引入，依赖这个子模块的其他模块也需要引入，否则会报找不到aar包的编译报错
     * 解决方案： 在依赖这个子模块的其他模块的build.gradle下，和android{}并列加入下面代码
     * repositories {
     *         flatDir {
     *             dirs 'libs','../xx_module/libs' //这个目录是相对于当前build.gradle的目录
     *         }
     * }
     * 这样其他模块就能依赖到这个aar包了
     *
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

    /**
     * ============= mavenLocal() 使用=========
     * repositories {
     *     mavenLocal() //直接使用本地maven仓库
     *     maven { url "http://maven.aliyun.com/nexus/content/groups/public" }
     *     mavenCentral()
     *
     * }
     *
     * 发现使用mavenLocal() 时Gradle默认会按以下顺序去查找本地的maven仓库：
     * USER_HOME/.m2/settings.xml >> M2_HOME/conf/settings.xml >> USER_HOME/.m2/repository
     *
     * USER_HOME/.m2/settings.xml  找配置的路径，如果没有去下一个找
     *
     */
    void a7(){}

    /**
     * =========发布aar jar apk到maven仓库==============
     * Maven Publish Plugin
     * https://docs.gradle.org/current/userguide/publishing_maven.html
     *
     * 使用 Maven Publish 插件
     * https://developer.android.google.cn/studio/build/maven-publish-plugin
     *
     *
     *
     *
     */
    void a8(){}


    /**
     * 1. Snapshot版本代表不稳定、尚处于开发中的版本2. Release版本则代表稳定的版本
     * 3. 什么情况下该用SNAPSHOT?协同开发时，如果A依赖构件B，由于B会更新，B应该使用SNAPSHOT来标识自己。
     * 协同开发依赖的版本
     *
     * 作者：搁浅的双鱼
     * 链接：https://www.jianshu.com/p/559fa91ce176
     * 来源：简书
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     *
     */
    void a9(){}


    /**
     * =======安装maven===========
     * 可以下载，然后解压，配置环境变量
     * https://maven.apache.org/download.cgi
     *
     * 也可以用brew
     * brew search maven
     * brew info maven
     * brew install maven
     *
     * 在路径/usr/local/Cellar/maven/3.5.0/libexec/conf下找到setting.xml，设置
     * <localRepository>/Users/xxx/maven_repo</localRepository>
     *
     * 作者：perfect_jimmy
     * 链接：https://www.jianshu.com/p/02f17ab0fc74
     * 来源：简书
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * 可能需要翻墙，安装完成，
     * mvn -v 查看
     * Apache Maven 3.6.3 (cecedd343002696d0abb50b32b541b8a6ba2883f)
     * Maven home: /usr/local/Cellar/maven/3.6.3/libexec
     * Java version: 1.8.0_201, vendor: Oracle Corporation, runtime: /Library/Java/JavaVirtualMachines/jdk1.8.0_201.jdk/Contents/Home/jre
     * Default locale: zh_CN, platform encoding: UTF-8
     * OS name: "mac os x", version: "10.14.1", arch: "x86_64", family: "mac"
     *
     *
     * ============发布jar到maven===============
     * Maven使用deploy上传jar包到远程库
     * https://blog.csdn.net/Roy_70/article/details/75267831
     *
     * 在路径/usr/local/Cellar/maven/3.5.0/libexec/conf下找到setting.xml，设置
     * <servers>
     *     <server>
     *       <id>roy_privrepository_snapshots</id>
     *       <username>roy</username>
     *       <password>123456</password>
     *     </server>
     * </servers>
     *
     * 这个是配置仓库访问权限
     * repository 后的路径就是id， http://182.xx.xx.xx:xx/repository/xxx/
     *
     * 配置完成，然后执行
     * mvn deploy:deploy-file
     * -Dmaven.test.skip=true  //跳过编译、测试
     * -Dfile=D:\MvnProject\service-mvn-1.0.0.jar //要上传jar的路径
     * -DgroupId=pri.roy.mvn.test //上传仓库的目录，可以随意填写，一般是jar中的包名
     * -DartifactId=bugly //包的名称，
     * -Dversion=1.0.0-SNAPSHOT //版本名称
     * -Dpackaging=jar
     * -DrepositoryId=roy_privrepository_snapshots //仓库id，要和setting.xml配置的一致
     * -Durl=http://10.4.71.144:9090/repository/roy_privrepository_snapshots/  // 仓库的路径
     *
     *
     * mvn deploy:deploy-file -Dmaven.test.skip=true -Dfile=/Users/xxx/Downloads/bug/class/bugly-3.3.3.jar -DgroupId=com.tencent.bugly -DartifactId=crashreport -Dversion=3.3.3 -Dpackaging=jar -DrepositoryId=maven-releases -Durl=http://xxx:8081/repository/maven-releases/
     *
     *
     * ==========删除jar=======
     * 可以直接登录，然后选中删除
     *
     *
     *
     *
     * ============安装自定义jar包到本地Maven库
     * 将本地jar安装到maven本地仓库中
     *
     * 当出现下列情况时：
     * 1.要使用的 jar 不存在于 Maven 的中心储存库中。
     * 2.您创建了一个自定义的 jar ，而另一个 Maven 项目需要使用。
     * 需要手动将所需要的jar包存放至Maven本地资源库，可以再cmd中输入以下命令：
     *
     * mvn install:install-file -Dfile=c:\userdefined-1.0.jar -DgroupId=pers.test.code -DartifactId=userdefined -Dversion={1.0} -Dpackaging=jar
     * 1
     * 安装成功后，在pom.xml文件中可以使用了
     *
     * <dependency>
     *       <groupId>pers.test.code</groupId>
     *       <artifactId>userdefined </artifactId>
     *       <version>1.0</version>
     * </dependency>
     */
    void a10(){}


    /**
     * Android 上传aar到maven仓库
     *
     * 在要上传的模块build.gradle中最下面加入
     *
     * apply plugin: 'maven'
     *
     * //上传源码
     * task androidSourcesJar(type: Jar) {
     *     classifier = 'sources'
     *     from android.sourceSets.main.java.sourceFiles
     * }
     * artifacts {
     *     archives androidSourcesJar
     * }
     * //上传配置
     * uploadArchives {
     *     repositories {
     *         mavenDeployer {
     *             pom.project {
     *                 groupId "xx.xxx"
     *                 artifactId "xxSdk"
     *                 version  "0.0.4"
     *                 packaging 'aar'
     *             }
     *
     *             repository(url: "http://xxx/repository/maven-releases/") {
     *                 authentication(userName: getRepositoryUsername(), password: getRepositoryPassword())
     *             }
     *             snapshotRepository(url: "http://xxx/repository/maven-releases/") {
     *                 authentication(userName: getRepositoryUsername(), password: getRepositoryPassword())
     *             }
     *         }
     *     }
     * }
     *
     * 然后执行gradle uploadArchives 任务就能上传了，里面pom.xml中的dependencies都有。
     *
     * ==========依赖
     * 模块build.gradle 中
     *
     * configurations.all {
     *     // check for updates every build
     *     resolutionStrategy.cacheChangingModulesFor 0, 'seconds'
     * }
     *
     * dependencies {
     *      //可以写   changing = true ，也可以加 -SNAPSHOT后缀，
     *      //默认缓存24小时，上面也可以配置每次都更新。
     *      //传统的依赖是本地有就不会去更新了，除非变更版本号
     *      implementation ('com.xx.xx.xxx:xxSdk:0.0.3') { changing = true }
     *
     * }
     *
     *
     *
     *
     */
    void a11(){}


    /**
     * gradle 依赖库 版本冲突问题
     * https://www.ucloud.cn/yun/14338.html
     * 高版本会覆盖低版本，
     * 查看依赖树 ./gradlew app:dependencies --configuration releaseRuntimeClasspath
     * 符号的含义：
     * x.x.x (*) 该依赖已经有了，将不再重复依赖。
     * x.x.x -> x.x.x 该依赖的版本被箭头所指的版本代替。
     * x.x.x -> x.x.x(*) 该依赖的版本被箭头所指的版本代替，并且该依赖已经有了，不再重复依赖。
     *
     * =====解决方法====
     * // 在build.gradle 中添加下面节点 。
     * 排除所有
     * 在 android{}下
     * configuration{
     *     all*.exclude module: "support-fragment"
     * }
     *
     * 排除单个
     * implementation ('com.github.bumptech.glide:glide:4.7.1'){
     *      exclude module:"support-fragment"
     *      或者 exclude group:'com.android.support'
     *  }
     *
     * 强制使用
     * configurations.all {
     *    resolutionStrategy {
     *        force 'com.android.support:support-fragment:26.1.0'
     *    }
     * }
     *
     */
    void a12(){}

}
