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
     *
     */
    void a2(){}
}
