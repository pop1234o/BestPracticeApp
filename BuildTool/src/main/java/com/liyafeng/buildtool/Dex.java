package com.liyafeng.buildtool;

public class Dex {


    /**
     * 分包
     *
     * //官方文档
     * https://developer.android.google.cn/studio/build/multidex?hl=zh-cn
     *
     * //这种是为了兼容5.0以下的
     * implementation 'com.android.support:multidex:1.0.3'
     * <p>
     * android{
     * defaultConfig{
     * multiDexEnabled true
     * }
     * }
     * <p>
     * 在application中 attachBaseContext -》  MultiDex.install(base)
     * <p>
     * <p>
     * https://segmentfault.com/a/1190000004053072 （讲解分包的原理）
     *
     *
     *
     * ===========java.lang.NoClassDefFoundError===========
     * https://developer.android.google.cn/studio/build/multidex?hl=zh-cn
     *
     *
     *
     * @param args
     */
    public static void main(String[] args) {

    }
}
