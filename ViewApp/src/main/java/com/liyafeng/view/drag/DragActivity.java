package com.liyafeng.view.drag;

import android.app.Activity;
import android.os.Bundle;

import com.liyafeng.view.R;

public class DragActivity extends Activity {

    /**
     * Android开发之getX，getRawX,getWidth,getTranslationX等的区别
     * https://blog.csdn.net/dmk877/article/details/51550031
     * <p>
     * getX是触摸点相对于整个屏幕的绝对距离
     * getRawX是触摸点相对于触摸控件的x y坐标，坐标原点是触摸控件的左上角
     *
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drag);
    }
}
