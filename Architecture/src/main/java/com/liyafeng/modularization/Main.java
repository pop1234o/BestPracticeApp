package com.liyafeng.architecture;

/**
 * Created by liyafeng on 2019/07/04.
 */

public class Main {

    /**
     *
     * 组件化
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
