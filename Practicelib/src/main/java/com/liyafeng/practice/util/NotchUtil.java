package com.liyafeng.practice.util;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.view.View;
import android.view.WindowInsets;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 刘海屏适配
 * https://www.jianshu.com/p/f04f066a626d
 */
public class NotchUtil {


    /**
     * 判断是否是刘海屏
     *
     * @return
     */
    public static boolean hasNotchScreen(Activity activity) {
        try {
            if (getInt("ro.miui.notch", activity) == 1 || hasNotchAtHuawei(activity) || hasNotchAtOPPO(activity)
                    || hasNotchAtVivo(activity) || isAndroidP(activity) != null) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Android P 刘海屏判断
     *
     * @param activity
     * @return
     */
    private static DisplayCutout isAndroidP(Activity activity) {
        View decorView = activity.getWindow().getDecorView();
        if (decorView != null && android.os.Build.VERSION.SDK_INT >= 28) {
            WindowInsets windowInsets = decorView.getRootWindowInsets();
            if (windowInsets != null) {
                return windowInsets.getDisplayCutout();
            }
        }
        return null;
    }

    /**
     * 小米刘海屏判断.
     *
     * @return 0 if it is not notch ; return 1 means notch
     */
    private static int getInt(String key, Activity activity) {
        int result = 0;
        if (isXiaomi()) {
            try {
                ClassLoader classLoader = activity.getClassLoader();
                @SuppressWarnings("rawtypes")
                Class SystemProperties = classLoader.loadClass("android.os.SystemProperties");
                //参数类型
                @SuppressWarnings("rawtypes")
                Class[] paramTypes = new Class[2];
                paramTypes[0] = String.class;
                paramTypes[1] = int.class;
                Method getInt = SystemProperties.getMethod("getInt", paramTypes);
                //参数
                Object[] params = new Object[2];
                params[0] = key;
                params[1] = 0;
                result = (Integer) getInt.invoke(SystemProperties, params);

            } catch (ClassNotFoundException e) {
                Logger.e("Exception:" + e.getMessage());
            } catch (NoSuchMethodException e) {
                Logger.e("Exception:" + e.getMessage());
            } catch (IllegalAccessException e) {
                Logger.e("Exception:" + e.getMessage());
            } catch (IllegalArgumentException e) {
                Logger.e("Exception:" + e.getMessage());
            } catch (InvocationTargetException e) {
                Logger.e("Exception:" + e.getMessage());
            }
        }
        return result;
    }

    /**
     * 华为刘海屏判断
     *
     * @return
     */
    private static boolean hasNotchAtHuawei(Context context) {
        boolean ret = false;
        try {
            ClassLoader classLoader = context.getClassLoader();
            Class HwNotchSizeUtil = classLoader.loadClass("com.huawei.android.util.HwNotchSizeUtil");
            Method get = HwNotchSizeUtil.getMethod("hasNotchInScreen");
            ret = (boolean) get.invoke(HwNotchSizeUtil);
        } catch (ClassNotFoundException e) {
            Logger.e("Exception:" + e.getMessage());
        } catch (NoSuchMethodException e) {
            Logger.e("Exception:" + e.getMessage());
        } catch (Exception e) {
            Logger.e("Exception:" + e.getMessage());
        }
        return ret;
    }

    public static final int VIVO_NOTCH = 0x00000020;//是否有刘海
    public static final int VIVO_FILLET = 0x00000008;//是否有圆角

    /**
     * VIVO刘海屏判断
     *
     * @return
     */
    private static boolean hasNotchAtVivo(Context context) {
        boolean ret = false;
        try {
            ClassLoader classLoader = context.getClassLoader();
            Class FtFeature = classLoader.loadClass("android.util.FtFeature");
            Method method = FtFeature.getMethod("isFeatureSupport", int.class);
            ret = (boolean) method.invoke(FtFeature, VIVO_NOTCH);

        } catch (ClassNotFoundException e) {
            Logger.e("Exception:" + e.getMessage());
        } catch (NoSuchMethodException e) {
            Logger.e("Exception:" + e.getMessage());
        } catch (Exception e) {
            Logger.e("Exception:" + e.getMessage());
        }
        return ret;
    }

    /**
     * OPPO刘海屏判断
     *
     * @return
     */
    private static boolean hasNotchAtOPPO(Context context) {
        return context.getPackageManager().hasSystemFeature("com.oppo.feature.screen.heteromorphism");
    }

    // 是否是小米手机
    private static boolean isXiaomi() {
        return "Xiaomi".equals(Build.MANUFACTURER);
    }
}
