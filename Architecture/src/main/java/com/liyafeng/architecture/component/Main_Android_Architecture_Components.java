package com.liyafeng.architecture.component;

public class Main_Android_Architecture_Components {


    /**
     * https://developer.android.google.cn/topic/libraries/architecture
     *
     * Android architecture components are a collection of libraries
     * that help you design robust, testable, and maintainable apps
     *
     * acc是一个  帮助你设计出一个健壮的，课测试的，可维护的app 的库的集合
     * 所以acc是一套开源库的集合，并不是一种设计模式，一般用这套开源库我们用mvvm模式
     *
     *
     * ========================
     * lifecycle-aware components 帮助你管理生命周期，避免内存泄漏，使得加载数据到ui更容易
     * LiveData  构建一个数据对象，当数据发生改变，通知view
     * ViewModel 存储ui相关的数据，屏幕旋转不销毁
     * Room  一个orm 框架（数据库框架） 支持返回  RxJava, Flowable and LiveData observables.
     *
     *
     *
     *
     *
     * @param args
     */
    public static void main(String[] args) {

    }


    /**
     * AndroidX===============
     *
     *
     * 新库老库对照表
     * https://developer.android.com/jetpack/androidx/migrate
     * 以前老库的版本号一般是跟着编译的版本号走，这几个库一般版本一样 比如 27.0.2
     * （除了constraint-layout）
     *
     *
     * com.android.support.constraint:constraint-layout
     * androidx.constraintlayout:constraintlayout:1.1.2
     *
     * com.android.support:cardview-v7
     * androidx.cardview:cardview:1.0.0
     *
     * com.android.support:recyclerview-v7
     * androidx.recyclerview:recyclerview:1.0.0
     *
     * com.android.support:coordinatorlayout
     * androidx.coordinatorlayout:coordinatorlayout:1.0.0
     *
     * com.android.support:design
     * com.google.android.material:material:1.0.0-rc01
     *
     * com.android.support:appcompat-v7
     * androidx.appcompat:appcompat:1.0.0
     *
     * com.android.support:support-v4
     * androidx.legacy:legacy-support-v4:1.0.0
     *
     * com.android.support:support-v13
     * androidx.legacy:legacy-support-v13:1.0.0
     *
     *
     *
     *
     */
    void a1(){}
}
