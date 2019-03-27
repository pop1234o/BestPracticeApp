package com.liyafeng.practice.util;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Point;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import java.lang.ref.WeakReference;

public class ScreenUtil {


    public static float sNoncompatDensity;//原始的Density
    public static float sNoncompatScaledDensity;

    /**
     * 屏幕适配
     *
     * @param activity
     * @param application
     */
    public static void setCustomDensity(final Activity activity, final Application application) {
        if (activity == null) {
            return;
        }
        DisplayMetrics displayMetrics = application.getResources().getDisplayMetrics();
        if (sNoncompatDensity == 0) {
            sNoncompatDensity = displayMetrics.density;
            sNoncompatScaledDensity = displayMetrics.scaledDensity;
            application.registerComponentCallbacks(new ComponentCallbacks() {

                @Override
                public void onConfigurationChanged(Configuration newConfig) {
                    if (newConfig != null && newConfig.fontScale > 0) {
                        sNoncompatScaledDensity = application.getResources().getDisplayMetrics().scaledDensity;
                    }

                    if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
//                        Toast.makeText(BrandyApplication.getInstance(), "现在是竖屏==", Toast.LENGTH_SHORT).show();
                    }
                    if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
//                        Toast.makeText(BrandyApplication.getInstance(), "现在是横屏==", Toast.LENGTH_SHORT).show();
                        WeakReference<Activity> reference = new WeakReference<>(activity);
                        ScreenUtil.setCustomDensity(reference.get(), application);

                    }
                }

                @Override
                public void onLowMemory() {

                }
            });
        }

        int screenWidth = getScreenWidth(application);
        int screenHeight = getScreenHeight(application);

        int width = screenWidth > screenHeight ? screenHeight : screenWidth;
        float targetDensity = width / 360.0f;
        float targetScaledDensity = targetDensity * (sNoncompatScaledDensity / sNoncompatDensity);
        int targetDensityDpi = (int) (160 * targetDensity);

        displayMetrics.density = targetDensity;
        displayMetrics.scaledDensity = targetScaledDensity;
        displayMetrics.densityDpi = targetDensityDpi;

        DisplayMetrics activityDisplayMetrics = activity.getResources().getDisplayMetrics();

        activityDisplayMetrics.density = targetDensity;
        activityDisplayMetrics.scaledDensity = targetScaledDensity;
        activityDisplayMetrics.densityDpi = targetDensityDpi;


    }



    /**
     * 获取屏幕宽度
     *
     * @return
     */
    public static int getScreenWidth(Context context) {
        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Point point = new Point();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            manager.getDefaultDisplay().getRealSize(point);
        }

        return point.x;
    }

    /**
     * 获取屏幕高度  这个是真正的大小，不包括底部导航bar 和顶部状态栏
     *
     * @return
     */
    public static int getScreenHeight(Context context) {
        WindowManager manager = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
        Point point = new Point();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            manager.getDefaultDisplay().getRealSize(point);
        }

        return point.y;
//        return BrandyApplication.getInstance().getResources().getDisplayMetrics().heightPixels;
    }
    /**
     * 隐藏虚拟栏 ，显示的时候再隐藏掉
     *
     * @param window
     */
    public static void hideNavigationBar(final Window window) {
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        window.getDecorView().setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
            @Override
            public void onSystemUiVisibilityChange(int visibility) {
                int uiOptions = View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                        //布局位于状态栏下方
                        View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
                        //全屏
                        View.SYSTEM_UI_FLAG_FULLSCREEN |
                        //隐藏导航栏
                        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
                if (Build.VERSION.SDK_INT >= 19) {
                    uiOptions |= View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
                } else {
                    uiOptions |= View.SYSTEM_UI_FLAG_LOW_PROFILE;
                }
                window.getDecorView().setSystemUiVisibility(uiOptions);
            }
        });
    }
}
