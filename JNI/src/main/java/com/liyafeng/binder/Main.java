package com.liyafeng.binder;

/**
 * Created by liyafeng on 2018/4/21.
 */

public class Main {

    /**
     * 讲binder源码分析的文章
     * https://github.com/xdtianyu/SourceAnalysis/blob/master/Binder%E6%BA%90%E7%A0%81%E5%88%86%E6%9E%90.md
     * =================================
     *
     *
     * ===================================
     *
     * platform： frameworks\base\core\jni\android_util_Binder.cpp
     * 我们在github上framework\base是一个单独的库
     * https://github.com/aosp-mirror/platform_frameworks_base
     * -------------------------------
     * http://androidxref.com/8.0.0_r4/xref/
     * 这里是全部的asop镜像，文件夹也是一样的，github上的镜像是把一个个模块都
     * 新建一个仓库来管理
     * 但是代码还是不全，比如frameworks/native，里面有BpBinder.cpp，
     * 而BBinder在Binder.h中定义
     *
     *
     *
     * @param args
     */
    public static void main(String[] args) {

    }
}
