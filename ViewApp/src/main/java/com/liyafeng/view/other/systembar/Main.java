package com.liyafeng.view.other.systembar;

public class Main {

    /**
     * 设置状态栏颜色
     * <p>
     * if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
     * var window = getWindow();
     * //取消状态栏透明
     * window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
     * //添加Flag把状态栏设为可绘制模式
     * window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
     * //设置状态栏颜色
     * window.setStatusBarColor(Color.WHITE)
     * <p>
     * //设置系统状态栏处于可见状态
     * window.getDecorView().setSystemUiVisibility(SYSTEM_UI_FLAG_VISIBLE);
     * //让view不根据系统窗口来调整自己的布局
     * var mContentView = window.findViewById(Window.ID_ANDROID_CONTENT) as ViewGroup
     * var mChildView = mContentView.getChildAt(0);
     * if (mChildView != null) {
     * ViewCompat.setFitsSystemWindows(mChildView, false);
     * ViewCompat.requestApplyInsets(mChildView);
     * }
     * <p>
     *
     *
     * }
     *
     * @param args
     */
    public static void main(String[] args) {

    }
}
