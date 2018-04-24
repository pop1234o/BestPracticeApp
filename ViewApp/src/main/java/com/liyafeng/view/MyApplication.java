package com.liyafeng.view;

import android.app.Application;

/**
 * Created by liyafeng on 2018/4/24.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        MyCrashHandler crashHandler = new MyCrashHandler();
        //设置未捕获的异常
        Thread.setDefaultUncaughtExceptionHandler(crashHandler);
    }
}
