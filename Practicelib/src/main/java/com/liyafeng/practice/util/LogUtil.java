package com.liyafeng.practice.util;

import android.util.Log;

public class LogUtil {

   static boolean isDebug=true;
    public static void i(String tag, String s) {
        if (isDebug) {
            if (s.length() > 4000) {
                printLong(tag, s);
            } else {
                Log.i(tag, s);
            }
        }
    }


    public static void w(String tag, String s) {
        if (isDebug) {
            Log.w(tag, s);
        }
    }

    public static void e(String tag, String s) {
        if (isDebug) {
            Log.e(tag, s);
        }
    }

    public static void d(String tag, String s) {
        if (isDebug) {
            Log.d(tag, s);
        }
    }


    private static void printLong(String tag, String s) {
        for (int i = 0; i < s.length(); i += 4000) {
            if (i + 4000 < s.length())
                Log.i(tag + i, s.substring(i, i + 4000));
            else
                Log.i(tag + i, s.substring(i, s.length()));
        }
    }
}
