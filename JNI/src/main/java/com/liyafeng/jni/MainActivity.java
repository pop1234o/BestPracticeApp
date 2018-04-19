package com.liyafeng.jni;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    /**
     * ndk入门指南
     * https://developer.android.google.cn/ndk/guides/index.html?hl=zh-cn
     * jni介绍
     * https://developer.android.google.cn/training/articles/perf-jni.html
     *
     * hello-jni示例
     * https://github.com/googlesamples/android-ndk/tree/master/hello-jni
     *
     * 使用c/c++示例
     * https://developer.android.google.cn/studio/projects/add-native-code.html
     *
     * ndk下载
     * https://developer.android.google.cn/ndk/downloads/index.html
     *
     * application_mk
     * https://developer.android.google.cn/ndk/guides/application_mk.html
     * https://developer.android.google.cn/ndk/guides/android_mk.html
     * ============为什么用jni====================
     * 从设备获取卓越性能以用于计算密集型应用，例如游戏或物理模拟。
     *  重复使用您自己或其他开发者的 C 或 C++ 库。
     *
     *
     * ====================jni使用步骤===========================
     *
     *
     * 需要将jni.h（该文件可以在%JAVA_HOME%/include文件夹下面找到）文件引入，
     * 因为在程序中的JNIEnv、 jobject等类型都是在该头文件中定义的
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
