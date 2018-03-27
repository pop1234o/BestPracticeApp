package com.liyafeng.orm;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;

/**
 * Created by lenovo on 2017/12/18.
 */

public class MyApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }
}
