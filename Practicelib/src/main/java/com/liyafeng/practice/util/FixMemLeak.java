package com.liyafeng.practice.util;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;

import java.lang.reflect.Field;

public class FixMemLeak {

    private static Field field;
    private static boolean hasField = true;

    /**
     * 解决华为泄漏问题 InputManager mLastSrvView
     * @param context
     */
    public static void fixLeak(Context context) {
        if (!hasField) {
            return;
        }
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm == null) {
            return;
        }

        String[] arr = new String[]{"mLastSrvView"};
        for (String param : arr) {
            try {
                if (field == null) {
                    field = imm.getClass().getDeclaredField(param);
                }
                if (field == null) {
                    hasField = false;
                }
                if (field != null) {
                    field.setAccessible(true);
                    field.set(imm, null);
                }
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }
    }
}
