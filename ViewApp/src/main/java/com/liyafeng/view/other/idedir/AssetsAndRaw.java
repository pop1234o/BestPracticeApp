package com.liyafeng.view.other.idedir;

public class AssetsAndRaw {

    /**
     * assets 目录 和raw
     *
     * https://www.jianshu.com/p/c5d41272c7e9
     *
     * 他们都在apk中，不会被解压出来
     * apk安装之后会放在/data/app/**.apk目录下，以apk形式存在，asset/res和被绑定在apk里，
     * 并不会解压到/data/data/YourApp目录下去，所以我们无法直接获取到assets的绝对路径
     * ，因为它们根本就没有。
     *========================raw=================
     * InputStream is = getResources().openRawResource(R.id.filename);
     * ======================assets========
     * webView.loadUrl("file:///android_asset/win8_Demo/index.html");
     * Context.getAssets()来获取AssetManager实例来访问
     *
     * @param args
     */
    public static void main(String[] args) {

    }
}
