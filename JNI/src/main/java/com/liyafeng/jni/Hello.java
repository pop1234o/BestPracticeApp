package com.liyafeng.jni;

/**
 * Created by liyafeng on 2018/4/18.
 */

public class Hello {
    static {
        System.loadLibrary("custom_jni");
    }


    public static native int doSomething(String s);
}
