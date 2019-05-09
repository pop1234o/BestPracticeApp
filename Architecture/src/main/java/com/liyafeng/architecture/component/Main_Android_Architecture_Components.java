package com.liyafeng.architecture.component;

public class Main_Android_Architecture_Components {


    /**
     * =======Android architecture components==============
     * https://developer.android.google.cn/topic/libraries/architecture
     *
     * Android architecture components are a collection of libraries
     * that help you design robust, testable, and maintainable apps
     *
     * acc是一个  帮助你设计出一个健壮的，课测试的，可维护的app 的库的集合
     * 所以acc是一套开源库的集合，并不是一种设计模式，一般用这套开源库我们用mvvm模式
     *
     *
     * ============acc包含的组件============
     * lifecycle-aware components 帮助你管理生命周期，避免内存泄漏，使得加载数据到ui更容易
     * LiveData  构建一个数据对象，当数据发生改变，通知view
     * ViewModel 存储ui相关的数据，屏幕旋转不销毁
     * Room  一个orm 框架（数据库框架） 支持返回  RxJava, Flowable and LiveData observables.
     *
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
     * =========Android Jetpack============
     *
     * Jetpack 是 Android 软件组件的集合，使您可以更轻松地开发出色的 Android 应用。
     * 这些组件可帮助您遵循最佳做法、让您摆脱编写样板代码的工作并简化复杂任务，以便您将精力集中放在所需的代码上。
     *
     * Jetpack 包含与平台 API 解除捆绑的 androidx.* 软件包库，这意味着，它可以提供向后兼容性，
     * 且比 Android 平台的更新频率更高，以此确保您始终可以获取最新且最好的 Jetpack 组件版本。
     *
     * ===========为什么用Jetpack========
     * 加速开发
     *  组件可以单独采用（不过这些组件是为协同工作而构建的），同时利用 Kotlin 语言功能帮助您提高工作效率
     * 消除样板代码
     *  Android Jetpack 可管理繁琐的 Activity（如后台任务、导航和生命周期管理），以便您可以专注于如何让自己的应用出类拔萃。
     * 构建高质量的强大应用
     *  Android Jetpack 组件围绕现代化设计实践构建而成，具有向后兼容性，可以减少崩溃和内存泄漏。
     *
     *
     *
     */
    public void a0(){}

    /**
     * ==========AndroidX===============
     * https://developer.android.com/jetpack/androidx
     * AndroidX is the open-source project that the Android team uses to develop,
     * test, package, version and release libraries within Jetpack.
     * AndroidX is a major improvement to the original Android Support Library.
     *
     * AndroidX是一个开源项目，主要用来代替之前的support库
     *
     * ===========与support库的区别？===========
     * Unlike the Support Library, AndroidX packages are separately maintained and updated.
     *
     * 与平台 API 解除捆绑，这意味着，它可以提供向后兼容性，
     * 且比 Android 平台的更新频率更高，以此确保您始终可以获取最新且最好的 Jetpack 组件版本
     *
     *
     *
     *
     * ==========新库老库对照表==============
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
