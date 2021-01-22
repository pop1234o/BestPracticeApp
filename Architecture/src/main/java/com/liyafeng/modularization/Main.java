package com.liyafeng.modularization;

/**
 * Created by liyafeng on 2019/07/04.
 */

public class Main {

    /**
     *
     * 组件化
     * =================
     * https://zhuanlan.zhihu.com/p/45374964 （ 知乎 Android 客户端组件化实践）
     * https://www.infoq.cn/article/fS4WGGbAqYMOq-As6Eq3 （ 有赞微商城 Android 组件化方案）
     * https://www.jianshu.com/p/d0f5cf304fa4 （组件化系列文章）
     * =========为什么组件化==========
     * 1.可以快速复用。比如你再写一个app，之间把common接入，把基础组件合并一下， 然后根据业务写业务组件。
     *
     * 2.逻辑隔离，降低团队之间的代码冲突。
     *
     * 一般是一个项目有多个app，然后多个app有很多通用的部分，比如基础模块：网络库，分享，拍照，相册选择，图片加载，推送
     * app更新， 还有一些业务模块通用：视频模块，登录(验证码/微信登录)
     * 所以我们把通用的部分抽取出来，维护成一个组件，这样就能支持多个app，只需要维护一份代码即可
     *
     * 组件收集 （把组件实现类找到）
     * SPI Service Provider Interface  IOC设计思想（控制反转）
     * 依赖注入
     * 在运行时 ，加载 IComponent 接口的所有实现类，并且调用方法
     *
     *
     * ========组件通信
     * 直接依赖 ，耦合严重
     * 事件通知，不好维护和调试，事件混乱
     * 路由，需要信息共享？？
     * 公共模块，随着业务迭代，代码膨胀。开发边界不能收敛（希望开发组件人员只能修改组件范围内的代码）
     * 可以通过gradle脚本吧当前模块约定好的文件，复制到公共模块中
     *
     * 只能从上面四个选择几个
     *
     *
     * =======组件化架构分层========
     * 组件化4层分类
     * app 壳工程
     *   全局配置和主 Activity
     *
     * 业务组件 一条完整的业务线
     *   由基础组件组成的一个完整的业务
     *
     * 基础组件 支撑上层业务组件运行的基础业务服务 ，
     *   比如分享，评论，反馈，视频，浏览器 ，支付，图片选择，拍照，debugtool
     *   这些业务在任何一个app出现频率都很高，但是细微区别还是有的，比如布局，loading等样式，业务可能也有区分
     *   基本上ui和网络接口相关的组件都是基础组件
     *
     * 基础sdk（common）  （完全业务无关的基础代码）
     *   网络 ，router ,weight  , db/sp ，loading ，dialog，BaseActivity，utils ，eventbus，
     *   网络，loading 第三方控件等库 每个app都不一样，所以我们可以单独抽离sdk，然后封装成aar或者lib,让common依赖
     * ---------------------
     * 下层不依赖上层，组件之间通过router跳转
     *
     *
     * 就比如分享sdk实际是和业务无关了，但是分享的页面和业务有关，我们把他们写到一个module，组成分享的基础组件
     * 各个业务线通过router都能跳转
     *
     *
     *
     *
     */
    public static void main(String[] args) {

    }

    /**
     * 问题
     *
     * Resource ID In Android Library Project
     *
     * R.id.xxx是变量，所以不能在Library中用switch
     * 得用if
     * As others have pointed out, you need to change your switch() statement to if()/else if()/else statements. R.id.menu_search is not a constant (static final) and cannot be used in a case statement. That is because R.id.menu_search is coming from your Android library project. android.R.id.home is a constant, because that is part of the OS and is not changing.
     *
     *
     */
    void a1() {
    }


    /**
     * 设计思想
     * =======配置文件==========
     * 很多软件把很多参数写入配置文件，这样增加了软件的灵活性，比如ss的配置文件
     */
    void a2() {
    }


    /**
     * 全局配置抽取-gradle.properties
     * ========有什么用=====
     * https://blog.csdn.net/seafishyls/article/details/79968315
     * 根目录下可以新建gradle.properties文件， 用于存储一些全局性配置
     *  jvm 的配置等，除此之外，业务层面的配置也可以抽取出来放在此处，方便各个module调用
     *
     * ============书写格式=======
     * 内容是典型的 ini 格式的文件，配置项通过回车符来分割。
     *
     * org.gradle.jvmargs=-Xmx1536m
     *
     * 比如定义，默认都是字符串
     * AppID=com.gz.debugtool
     * AppVersionName=1.0
     * AppVersionCode=1
     * StartPage=index.html
     * BaiduKey=u91cPvSUevIzvOhBdBGU7iv5mSb6mHDs
     *
     * ===========使用场景=====
     *
     *
     * 为支持自动化编译android工程，我们需要把配置集中起来，方便动态替换，有如下要求需要满足：
     * 1. 应用Id 可以配置 （对应 build.gradle => applicationId）
     *
     *
     * 2. 应用版本 VersionName 和 VersionCode 可以配置 用project对象读取 （对应 build.gradle => versionCode | versionName）
     *    versionCode project.AppVersionCode as int  //读取版本号 转换成数字
     *    versionName project.AppVersionName //读取版本名称
     *
     *
     * 3. 应用名称 Name 可以配置 （对应 AndroidManifest.xml => android:label，这里有坑，请往下看）
     *
     *
     * 4. 可以根据debug 和 release（这个名字可以在buildTypes自己定义）不同的配置   百度定位Key配置 （对应 AndroidManifest.xml => meta-data）
     * buildTypes {
     *         release {
     *             manifestPlaceholders = [BAIDU_APPKEY_VALUE: BaiduKey]
     *             //...
     *         }
     *         debug {
     *             manifestPlaceholders = [BAIDU_APPKEY_VALUE: BaiduKey]
     *             //...
     *         }
     *     }
     * <meta-data
     *      android:name="com.baidu.lbsapi.API_KEY"
     *      android:value="${BAIDU_APPKEY_VALUE}" >
     *  </meta-data>
     *
     * manifestPlaceholders = [
     *      BAIDU_APPKEY_VALUE: BaiduKey,
     *      Key1: Value1,
     *      Key2: Value2
     *  ]
     *
     * 5. java代码中读取配置  启动页面 可以配置 ( 对应 MainActivity.java => 变量）
     * 其实是编译的时候，生成BuildConfig.java
     * buildTypes {
     *     release {
     *         buildConfigField "String", "StartPage", "${StartPage}"
     *         //...
     *     }
     *     debug {
     *         buildConfigField "String", "StartPage", "${StartPage}"
     *         //...
     *     }
     *
     *     xxx{
     *         initWith debug  // initWith允许你把其他type的配置复制过来，可以新增，也可以覆盖
     *     }
     * }
     *
     * public final class BuildConfig {
     *  public static final String StartPage = "index.html";
     * }
     *
     *
     * 将如上需要配置的地方抽取出来到统一的地方，那就是gradle.properties文件
     *
     *
     * 中文乱码问题
     * gradle.properties 中配置中文，例如AppName=测试应用， 无论哪种方式读取出来均是乱码，这是使用过程中遇到的巨大的坑。因此不建议在该文件中使用中文字符
     *
     *
     */
    void a3(){}
}
