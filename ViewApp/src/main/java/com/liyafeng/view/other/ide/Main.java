package com.liyafeng.view.other.ide;

public class Main {

    public static void main(String[] args) {

    }

    /**
     * 引入aar包
     * 我们需要在build.gradle(module下的) 中最外层加入
     *
     * repositories {
     *     flatDir {
     *         dirs 'libs'
     *     }
     * }
     * 表示repositories 库的目录是 平行目录下的libs文件夹
     *
     * 然后在
     * dependencies {
     *     implementation fileTree(dir: 'libs', include: ['*.jar']) //这个是代表引入lib中的jar包
     *     implementation(name: 'testlibrary-release', ext: 'aar')
     *  }
     */
    public void f1(){}


    /**
     * jdk.table.xml 配置源码路径
     * mac
     * /Users/xxx/Library/Preferences/AndroidStudio2.x/options/jdk.table.xml
     * windows
     * 在 c://user/xxx/ AndroidStudio2.x/options/jdk.table.xml
     *
     *
     *
     */
    public void f2(){}

}
