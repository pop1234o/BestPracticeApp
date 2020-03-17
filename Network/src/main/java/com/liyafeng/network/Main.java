package com.liyafeng.network;

/**
 * Created by liyafeng on 2018/1/3.
 */

public class Main {

    /**
     * Android 6.0 版移除了对 Apache HTTP 客户端的支持。
     * 如果您的应用使用该客户端，并以 Android 2.3（API 级别 9）或更高版本为目标平台，
     * 请改用 HttpURLConnection 类。此 API 效率更高，
     * 因为它可以通过透明压缩和响应缓存减少网络使用，
     * 并可最大限度降低耗电量。
     * 要继续使用 Apache HTTP API，您必须先在 build.gradle 文件中声明以下编译时依赖项：
     * android {
     * useLibrary 'org.apache.http.legacy'
     * }
     */
    public static void main(String[] args) {

    }


    /**
     * ===========设置公共请求头/公共Header=========
     * timestamp 当前时间戳， 如果本地改时间，那么有本地时间和服务端时间校正逻辑
     * sign 签名，一般是所有参数加一起，然后加密，防止参数被修改
     * nonce 随机字符串，防止网络重放攻击 建议您每一次请求都使用不同的随机数
     *
     *
     */
    void a1() {
    }


    /**
     * ========android app 抓包===============
     * https://www.charlesproxy.com/ 官网
     * 真尼玛，下载速度慢的不行，还要收费。破解版也得费时间。。醉了
     * 一个要30美元醉了
     *
     *
     * =========Android 抓包配置==========
     * https://www.jianshu.com/p/59e9ef771ff2 （Android 7.0 以上 Charles 和 Fiddler 无法抓取 HTTPS 包的解决方式）
     * https://developer.android.google.cn/training/articles/security-config （官方文档）
     *
     * --------每个版本的默认配置
     * Android 6.0（API 23）及更低版本应用的默认网络安全性配置如下：
     * <!-- 默认允许所有明文通信 -->
     * <base-config cleartextTrafficPermitted="true">
     *     <trust-anchors>
     *         <!-- 信任系统预装 CA 证书 -->
     *         <certificates src="system" />
     *         <!-- 信任用户添加的 CA 证书，Charles 和 Fiddler 抓包工具安装的证书属于此类 -->
     *         <certificates src="user" />
     *     </trust-anchors>
     * </base-config>
     *
     * 而在 Android 7.0（API 24）到 Android 8.1（API 27）的默认网络安全性配置如下
     * <!-- 默认允许所有明文通信 -->
     * <base-config cleartextTrafficPermitted="true">
     *     <trust-anchors>
     *         <!-- 信任系统预装 CA 证书 -->
     *         <certificates src="system" />
     *     </trust-anchors>
     * </base-config>
     *
     *  Android 9.0（API 28）及更高版本的默认网络安全性配置如下
     *
     *  <!-- 默认禁止所有明文通信 -->
     * <base-config cleartextTrafficPermitted="false">
     *     <trust-anchors>
     *         <!-- 信任系统预装 CA 证书 -->
     *         <certificates src="system" />
     *     </trust-anchors>
     * </base-config>
     *
     * 9.0默认禁止明文就是http协议，如果你设置了target api 是28+
     *
     * ===========解决方案=====
     * <?xml version="1.0" encoding="utf-8"?>
     * <network-security-config>
     *     <base-config cleartextTrafficPermitted="true">
     *          <debug-overrides>
     *         <trust-anchors>
     *             <certificates src="system" />
     *             <certificates src="user" />
     *         </trust-anchors>
     *          <debug-overrides>
     *     </base-config>
     * </network-security-config>
     *
     *
     */
    void a2(){}
}
