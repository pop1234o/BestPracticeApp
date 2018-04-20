package com.liyafeng.aidl;

/**
 * Created by liyafeng on 2018/4/20.
 */

public class Main {


    /**
     * ======================
     * 当我们服务是同一个进程中，直接bindservice返回Service中的Binder对象
     *
     * 在不同进程中会报错
     * java.lang.ClassCastException: android.os.BinderProxy cannot be cast to com.liyafeng.aidl.LocalService$MyBinder
     * at com.liyafeng.jni.MainActivity$1.onServiceConnected(MainActivity.java:109)
     * at android.app.LoadedApk$ServiceDispatcher.doConnected(LoadedApk.java:1208)
     * at android.app.LoadedApk$ServiceDispatcher$RunConnection.run(LoadedApk.java:1225)
     * at android.os.Handler.handleCallback(Handler.java:739)
     * at android.os.Handler.dispatchMessage(Handler.java:95)
     * at android.os.Looper.loop(Looper.java:135)
     * at android.app.ActivityThread.main(ActivityThread.java:5271)
     * at java.lang.reflect.Method.invoke(Native Method)
     * at java.lang.reflect.Method.invoke(Method.java:372)
     * at com.android.internal.os.ZygoteInit$MethodAndArgsCaller.run(ZygoteInit.java:902)
     * at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:697)
     *
     *=============================================
     *   android:process=":remote" 带冒号是表示这个进程是应用私有的，别的应用不能访问
     *
     * ===================================
     * 通过Messenger传递的obj要实现Parcelable
     *
     *
     *
     * @param args
     */
    public static void main(String[] args) {

    }
}
