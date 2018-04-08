package com.liyafeng.hotfix;

import android.util.Log;

/**
 * Created by liyafeng on 2018/4/8.
 */

public class BugClass {

    static int i;

    static {
        i = 1;
        Log.i("test", "after fix init" + i);
    }

    public static void doSomething() {
        i++;
        Log.i("test", "after fix result" + i);
    }
}
