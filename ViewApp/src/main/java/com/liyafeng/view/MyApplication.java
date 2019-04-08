package com.liyafeng.view;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

/**
 * Created by liyafeng on 2018/4/24.
 */

public class MyApplication extends Application {


    private int activityAount = 0;

    @Override
    public void onCreate() {
        super.onCreate();
        MyCrashHandler crashHandler = new MyCrashHandler();
        //设置未捕获的异常
        Thread.setDefaultUncaughtExceptionHandler(crashHandler);


        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

            }

            @Override
            public void onActivityStarted(Activity activity) {
                if (activityAount == 0) {
                    //app进入前台
                }
                activityAount++;
            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {
                activityAount--;
                if (activityAount == 0) {
                    //app进入后台
                }
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {

            }
        });
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        if(level==TRIM_MEMORY_UI_HIDDEN){//app进入后台

        }
    }
}
