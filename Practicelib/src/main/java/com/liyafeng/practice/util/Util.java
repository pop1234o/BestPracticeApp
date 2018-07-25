package com.liyafeng.practice.util;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap.Config;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Process;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebSettings;
import android.widget.Adapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * Created by liyafeng on 2018/1/8.
 */

public class Util {


    public static void refresh() {
//        MediaStore.Images.Media.insertImage(getContentResolver(), data.uri, "title", "description");
        //这个保存在sd卡的Picture文件夹中
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            final Intent scanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
//            final Uri contentUri = Uri.fromFile(outputFile);
//            scanIntent.setData(contentUri);
//            sendBroadcast(scanIntent);
//        } else {
//            final Intent intent = new Intent(Intent.ACTION_MEDIA_MOUNTED, Uri.parse("file://" + Environment.getExternalStorageDirectory()));
//            sendBroadcast(intent);
//        }
    }

    /**
     * 获取状态栏高度
     *
     * @param context
     * @return
     */
    public static int getStatusBarHeight(Context context) {
        Resources resources = context.getResources();
        int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        int height = resources.getDimensionPixelSize(resourceId);
        return height;
    }


    /**
     * 底部栏高度
     *
     * @param context
     * @return
     */
    public static int getNavigationBarHeight(Context context) {
        Resources resources = context.getResources();
        int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
        int height = resources.getDimensionPixelSize(resourceId);
        return height;
    }

    public static int px2dp(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static int dp2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }


    /**
     * 禁用EditText的长按事件（粘贴）
     *
     * @param editText
     */
    public static void disableEditTextLongClick(EditText editText) {
        editText.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return true;
            }
        });
    }

    /**
     * EditText隐藏键盘
     *
     * @param editText
     */
    public static void disableShowSoftInput(EditText editText) {
        if (android.os.Build.VERSION.SDK_INT <= 10) {
            editText.setInputType(InputType.TYPE_NULL);
        } else {
            Class<EditText> cls = EditText.class;
            Method method;
            try {
                method = cls.getMethod("setShowSoftInputOnFocus", boolean.class);
                method.setAccessible(true);
                method.invoke(editText, false);
            } catch (Exception e) {
                // TODO: handle exception
            }

            try {
                method = cls.getMethod("setSoftInputShownOnFocus", boolean.class);
                method.setAccessible(true);
                method.invoke(editText, false);
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
    }


    /**
     * 获取屏幕宽度
     *
     * @param context
     * @return
     */
    public static int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    /**
     * 获取屏幕高度
     *
     * @param context
     * @return
     */
    public static int getScreenHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }


    /**
     * 获取高度
     *
     * @param adapter
     * @return
     */
    public static int getListViewHeight(Adapter adapter, int add, GridView gv_rule) {
        int totalHeight = 0;
        for (int i = 0; i < adapter.getCount(); i += add) {
            View listItem = adapter.getView(i, null, gv_rule);
            listItem.measure(
                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
            totalHeight += listItem.getMeasuredHeight();
        }
        return totalHeight;
    }


    /**
     * 获取一个像素多少字节
     *
     * @param config
     * @return
     */
    static int getBytesPerPixel(Config config) {
        if (config == Config.ARGB_8888) {
            return 4;
        } else if (config == Config.RGB_565) {
            return 2;
        } else if (config == Config.ARGB_4444) {
            return 2;
        } else if (config == Config.ALPHA_8) {
            return 1;
        }
        return 1;
    }


    public static boolean isNetworkAvailable(Context context) {

        ConnectivityManager manager = (ConnectivityManager) context
                .getApplicationContext().getSystemService(
                        Context.CONNECTIVITY_SERVICE);

        if (manager == null) {
            return false;
        }

        //需要权限
        NetworkInfo networkinfo = manager.getActiveNetworkInfo();

        if (networkinfo == null || !networkinfo.isAvailable()) {
            return false;
        }

        return true;
    }


    /**
     * 版本升级
     */
    public void checkVersion() {
        //https://blog.csdn.net/qq_34161388/article/details/73248703


        //https://blog.csdn.net/u013278099/article/details/52692008

        //https://segmentfault.com/a/1190000010172260

        //一般强制更新得`我们自己实现，下载时显示dialog不可关闭
    }


    public static int getVerCode(Context context) {
        int verCode = -1;
        try {
            verCode = context.getPackageManager().getPackageInfo(
                    "com.demo", 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
        }
        return verCode;
    }

    public static String getVerName(Context context) {
        String verName = "";
        try {
            verName = context.getPackageManager().getPackageInfo(
                    "com.demo", 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
        }
        return verName;
    }


    /**
     * 大陆号码或香港号码均可
     */
    public static boolean isPhoneLegal(String str) throws PatternSyntaxException {
        return isChinaPhoneLegal(str) || isHKPhoneLegal(str);
    }

    /**
     * 大陆手机号码11位数，匹配格式：前三位固定格式+后8位任意数
     * 此方法中前三位格式有：
     * 13+任意数
     * 15+除4的任意数
     * 18+除1和4的任意数
     * 17+除9的任意数
     * 147
     */
    public static boolean isChinaPhoneLegal(String str) throws PatternSyntaxException {
        String regExp = "^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }

    /**
     * 香港手机号码8位数，5|6|8|9开头+7位任意数
     */
    public static boolean isHKPhoneLegal(String str) throws PatternSyntaxException {
        String regExp = "^(5|6|8|9)\\d{7}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }


    public static void dismiss(Context context) {
//        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
////隐藏软键盘 //
//        imm.hideSoftInputFromWindow(et_edit.getWindowToken(), 0);
////显示软键盘
//        imm.showSoftInputFromInputMethod(tv.getWindowToken(), 0);
//        //切换软键盘的显示与隐藏
//        imm.toggleSoftInputFromWindow(tv.getWindowToken(), 0, InputMethodManager.HIDE_NOT_ALWAYS);
//
//
//        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
////隐藏软键盘 //
//        imm.hideSoftInputFromWindow(context.getWindow().getDecorView().getWindowToken(), 0);
    }

    /**
     * 返回当前程序版本名
     */
    public static String getAppVersionName(Context context) {
        String versionName = "";
        try {
            // ---get the package info---
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            versionName = pi.versionName;
            if (versionName == null || versionName.length() <= 0) {
                return "";
            }
        } catch (Exception e) {
        }
        return versionName;
    }

    public static void showSoftInput(Context context, View view) {


        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.showSoftInput(view, InputMethodManager.SHOW_FORCED);
        }
    }

    public static void hideSoftInput(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0); //强制隐藏键盘
        }
    }


    public static boolean isShowSoftInput(Context context) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        boolean isOpen = imm.isActive();
        return isOpen;
    }

    /**
     * 禁用回车
     * @param editText
     */
    public static void disableEnter(EditText editText) {
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                return (event.getKeyCode() == KeyEvent.KEYCODE_ENTER);
            }
        });
    }


    /**
     * 保留两位小数
     * @param v
     * @return
     */
    public static double round2(double v) {
        BigDecimal bg = new BigDecimal(v);
        return bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();


//        String.format("%.2f", f)

//        DecimalFormat df = new DecimalFormat("#.00");
    }


    /**
     * 判断新版本要比老的大
     *
     * @param newVersion
     * @return
     */
    public static boolean isUpdate(Context context,String newVersion) {
        try {
            String currentVersion = getAppVersionName(context);
            if (TextUtils.isEmpty(newVersion) || TextUtils.isEmpty(currentVersion)) {
                return false;
            }
            if (newVersion.equals(currentVersion)) {
                return false;
            }

            String[] split = newVersion.split("[.]");
            //必须有一个从左起对应位置比老版本大才行
            String[] split_old = currentVersion.split("[.]");

            for (int i = 0; i < split.length; i++) {
                String newstr = split[i];
                if (i < split_old.length) {
                    String current = split_old[i];
                    int newNum = Integer.parseInt(newstr);
                    int currentNum = Integer.parseInt(current);
                    if (newNum > currentNum) {
                        return true;//新的大，要更新
                    } else if (newNum < currentNum) {
                        return false;//服务端返回的小，不用更新
                    }
                } else {
                    break;
                }
            }

            //说明两个 版本交集部分相等,而且现在两个不相等
            if (newVersion.length() > currentVersion.length()) {//如果新的版本长，需要更新
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;//说明新的版本 短或者等于当前版本，说明比当前版本低

    }



    public static boolean isAlive(Context context) {
        ActivityManager am = ((ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE));
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            List<ActivityManager.AppTask> appTasks = am.getAppTasks();//这个返回当前应用的task，
            for (int i = 0; i < appTasks.size(); i++) {
                ComponentName baseActivity = appTasks.get(i).getTaskInfo().baseActivity;
                if (context.getPackageName().equals(baseActivity.getPackageName())) {
                    Log.i("test", "找到了当前应用" + appTasks.get(i).getTaskInfo().topActivity);
                    break;
                }
//                int baseActivity = appTasks.get(i).getTaskInfo().numActivities;
            }
        } else {//这种是返回当前app的task和 launcher的task
            List<ActivityManager.RunningTaskInfo> runningTasks = am.getRunningTasks(50);
            for (int i = 0; i < runningTasks.size(); i++) {
                ActivityManager.RunningTaskInfo runningTaskInfo = runningTasks.get(i);
                if (runningTaskInfo.topActivity.getPackageName().equals(context.getPackageName())) {
                    int numActivities = runningTaskInfo.numActivities;
                    Log.i("test", "当前应用页面" + numActivities);

                    break;
                }
            }
        }

        return false;
    }


    /**
     * @return
     */
    public static String getOkUserAgent(Context context) {
        try {
            String userAgent = "";
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                try {
                    userAgent = WebSettings.getDefaultUserAgent(context);
                } catch (Exception e) {
                    userAgent = System.getProperty("http.agent");
                }
            } else {
                userAgent = System.getProperty("http.agent");
            }
            StringBuffer sb = new StringBuffer();
            for (int i = 0, length = userAgent.length(); i < length; i++) {
                char c = userAgent.charAt(i);
                if (c <= '\u001f' || c >= '\u007f') {
                    sb.append(String.format("\\u%04x", (int) c));
                } else {
                    sb.append(c);
                }
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "okhttp/" + getAppVersionName(context);
    }



    /**
     * 获取当前进程名
     * @return
     */
    public static String getProcessName(Context context) {
        try {
            int i = Process.myPid();
            ActivityManager service = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            if (service != null) {
                List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = service.getRunningAppProcesses();
                for (ActivityManager.RunningAppProcessInfo procInfo : runningAppProcesses) {
                    if (procInfo.pid == i) {
                        return procInfo.processName;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

}
