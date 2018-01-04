package com.liyafeng.network;

/**
 * Created by liyafeng on 2018/1/3.
 */

public class Main {

    /**
     *             Android 6.0 版移除了对 Apache HTTP 客户端的支持。
     *             如果您的应用使用该客户端，并以 Android 2.3（API 级别 9）或更高版本为目标平台，
     *             请改用 HttpURLConnection 类。此 API 效率更高，
     *             因为它可以通过透明压缩和响应缓存减少网络使用，
     *             并可最大限度降低耗电量。
     *             要继续使用 Apache HTTP API，您必须先在 build.gradle 文件中声明以下编译时依赖项：
     *             android {
     *             useLibrary 'org.apache.http.legacy'
     *             }
     */
    public static void main(String[] args) {

    }
}
