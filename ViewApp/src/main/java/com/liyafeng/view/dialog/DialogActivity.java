package com.liyafeng.view.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.liyafeng.view.R;

public class DialogActivity extends AppCompatActivity implements View.OnClickListener {

    private final String TAG = getClass().getSimpleName();
    /**
     * Button
     */
    private Button mButton9;
    private ProgressDialog progress;

    /**
     * 一个全屏的dialog，想让dialog像Activity一样
     * 如果你设置dialog最外层布局为match_parent，那么他实际是没有效果的
     * 会自动变为warp_content
     * -----------------------------------
     * dialog本质上是一个新的PhoneWindow，只不过没有Activity那样的生命周期
     * ---------------------------------------------
     * <item name="android:windowFullscreen">true</item>
     * <item name="android:windowContentOverlay">@null</item>
     * 在style设置了全屏后，那么代码中就不用设置全屏，window背景默认白色
     * <p>
     * ----------------------------------------
     * 注意，你要的是window的大小还是，window内容的大小
     * <p>
     * -----------------------------------------
     * 设置一个空的style，然后将内容布局设置为match_parent，直接就是全屏
     * <p>
     * -----------------------------------------
     * AlertDialog是默认有几个按钮，也可以设置文案，也可以自定义布局
     * guide/topics/ui/dialogs.html
     * 但是注意，alertDialog只能用系统的几个主题，你用自己的主题不管用，里面有判断
     * resolveDialogTheme
     * -------------------------------------
     * overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
     * overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
     * <p>
     * ----------------显示原理---------------
     * 其实就是将你自定义的布局加入了DecorView，你自己布局最外层参数会变成最简单的warp_content
     * 外面的蒙版，边距，都是DecorView控制的，这个window指的是PhoneWindow.class，里面有DecorView
     * 最终是DecorView加入到WindowManager.addView中，这个DecorView实际上就是LinearLayout(title+FrameLayout)
     * 我们的自定义布局就是在FrameLayout中 id是content;
     * <p>
     * ======全屏的dialog===============
     * //https://blog.csdn.net/LonelyRoamer/article/details/77430041
     * <p>
     * <p>
     * ============support v7 alertdialog和原来的区别==========
     * <p>
     * <p>
     * 谷歌发布了Material Design设计之后，很多 Material 风格的控件也随之加入到了V7兼容包中。
     * Android Support Library v22.1 中开始提供了Material风格的Dialog控件 。这为开发者提供了很好的支持，
     * 省去了使用开源库或自己设计的烦恼。下面我们来看看如何使用 Material风格的 Dialog。
     *
     * 如果直接使用  android.app.AlertDialog 在 Android 5.0以下就是原始风格， 5.0 以上为 Material 风格。
     *
     * dependencies {
     *     compile 'com.android.support:appcompat-v7:23.1.1'
     * }
     *
     * 这个V7包中的AlertDialog在Android2.1以上可以提供兼容性的 Material 风格 Dialog 。
     * 也就是说，使用这个包中的 AlertDialog 的话，从2.1到7.0都是Material风格的Dialog 。当使用这个包中的AlertDialog 时:
     *
     * 而Activity主题也需要是 APPcompat的
     * Theme.AppCompat.Light.DarkActionBar
     * 否则会报异常，
     *
     * 使用方法
     * https://developer.android.google.cn/reference/android/support/v7/app/AlertDialog
     *
     * A subclass of Dialog that can display one, two or three buttons. If you only want to display a String in this dialog box, use the setMessage() method. If you want to display a more complex view, look up the FrameLayout called "custom" and add your view to it:
     *
     *  FrameLayout fl = findViewById(android.R.id.custom);
     *  fl.addView(myView, new LayoutParams(MATCH_PARENT, WRAP_CONTENT));
     *
     *
     * @param args
     */
    public static void main(String[] args) {


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        initView();

//        new CustomDialog(this).createDialog();

        Log.i(TAG, "onCreate: ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart: ");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart: ");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.i(TAG, "onRestoreInstanceState: ");
    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause: ");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG, "onSaveInstanceState: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy: ");
    }

    private void initView() {
        mButton9 = (Button) findViewById(R.id.button9);
        mButton9.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.button9:
//                new BottomDialog(this).createDialog();

                new FullDiolog(this).createDialog();
                break;
        }
    }


    /**
     * 如果直接style设置为空的，
     * 而且window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
     * 这样就是全屏的dialog，而且背景是白色的
     *
     * 注意，show一定要在前调用
     *   window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
     *
     * 如果要设置背景是半透明黑色
     *   <item name="android:windowBackground">@color/transparent</item>
     *         <item name="android:windowFrame">@null</item>
     *         <item name="android:windowNoTitle">true</item>
     *         <item name="android:windowIsFloating">true</item>
     *         <item name="android:windowIsTranslucent">true</item>
     *         <item name="android:windowAnimationStyle">@style/Animation_Translucent</item>
     *
     *    如果背景完全透明
     *
     * 去掉 <item name="android:windowIsFloating">true</item>
     *
     *
     *
     */
    class CustomDialog extends android.app.Dialog {

        public CustomDialog(@NonNull Context context) {
            super(context, R.style.popupDialog);
        }

        public void createDialog() {
            setContentView(R.layout.dialog_custom);
            Window window = getWindow();
            show();

            //这2种方式都可以改变宽高，但是周围会有留白，留白是因为使用了系统的theme，换成一个空theme即可
//       WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
//        window.getAttributes().height = ViewGroup.LayoutParams.MATCH_PARENT;
//        window.getAttributes().width = ViewGroup.LayoutParams.MATCH_PARENT;
//        window.setAttributes(window.getAttributes());

            //这个设置的是内容布局根部局的大小
            window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        }
    }

    class BottomDialog extends android.app.Dialog {

        public BottomDialog(@NonNull Context context) {
            super(context, R.style.bottomDialog);
        }

        public void createDialog() {
            setContentView(R.layout.dialog_bottom);
            show();
            Window window = getWindow();

            //布局高度是包裹内容的
            window.setGravity(Gravity.BOTTOM);

            //取消边距
            WindowManager.LayoutParams lp = window.getAttributes();
            //设置宽
            lp.width = WindowManager.LayoutParams.MATCH_PARENT;
            //设置高
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;

            window.setAttributes(lp);


        }
    }

    class CenterDialog {
        public CenterDialog(Context context) {
            View view = LayoutInflater.from(context).inflate(R.layout.dialog_center, null);
            AlertDialog alertDialog = new AlertDialog.Builder(context).setView(view).create();
            //这样是在中央显示，但是会变成warp_content，所以最好不要把宽高写在最外层
            //让内部布局都有高度
            alertDialog.show();

            //加入到window中最外层布局，参数变成warp_content
        }
    }

    class CenterDialog1 extends Dialog {

        public CenterDialog1(Context context) {
            //Base.Theme.AppCompat.Dialog 设置这个主题，然后显示居中
            super(context, android.R.style.Theme_DeviceDefault_Light_Dialog);
//            setContentView();
        }
    }

    /**
     * ============全屏的dialog=====
     * 如果只继承 android.app.Dialog  ，setContentView后，宽度两边有留边，高度变成warp_content了
     *
     * -----1
     *  getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
     *  如果设置了上面这个，那么window就会撑大，但是还是有边框
     *
     * ----2 这样还是有边框，因为会用默认的style，默认style就是有边框的，所以要指定自己的
     *     <style name="style_full">
     *
     *     </style>
     *    设置了这个style我们设置的layout就能全屏展示了
     *
     * ---3 这个时候背景是白的。。
     *   getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
     *   去除白色，可以给我们自己的布局背景加个mask
     *
     * ---4 这个时候其实背景默认是半透明的黑 （如果你加了 floating）
     * 如果你设置
     *  <item name="android:windowIsFloating">true</item>
     *  那么就会有系统的黑色蒙层，还有动画淡入淡出的效果
     *
     *  如果要设置透明背景
     *
     *  你可以试试
     *  <!--        <item name="android:windowBackground">@color/transparent</item>-->
     * <!--        <item name="android:windowIsTranslucent">true</item>-->
     * <!--        <item name="android:windowAnimationStyle">@style/Animation_Translucent</item>-->
     *
     *
     *
     */
    public static class FullDiolog extends android.app.Dialog {

        public FullDiolog(@NonNull Context context) {
            super(context, R.style.style_full);//这里不指定主题，就用默认的主题
        }

        public void createDialog() {
            //这个最外层布局不会变成warp_content,还是原来的布局（如果没有设置windowIsFloating）
            setContentView(R.layout.dialog_full);//这里不能用 LayoutInflater ，否则最外层参数无效会变成wrap_content

            getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

            //这个居然变成match_content了
//            setContentView(LayoutInflater.from(getContext()).inflate(R.layout.dialog_full,null));
            show();

            //有的window背景是白色的
            getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
    }


    public static class InfoDialog extends Dialog {


        public InfoDialog(Context context) {
            super(context);
        }

        public static void createInfoDialog(Activity activity) {
            InfoDialog dialog = new InfoDialog(activity);
            // 这个布局用match_parent也行
//            View view = LayoutInflater.from(activity).inflate(R.layout.dialog_info, null);
//            dialog.setContentView(view);

            //设置window背景，默认的背景会有Padding值，不能全屏。当然不一定要是透明，你可以设置其他背景，替换默认的背景即可。
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            //一定要在setContentView之后调用，否则无效
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

            dialog.show();
        }
    }


    public void showLoading() {
        if (progress == null) {
            progress = new ProgressDialog(this);
            progress.setMessage("Wait while loading...");
            progress.setCancelable(false); // disable dismiss by tapping outside of the dialog

        }
        progress.show();

    }

    public void hideLoading() {

        if (progress != null) {
            progress.dismiss();
        }
    }
}
