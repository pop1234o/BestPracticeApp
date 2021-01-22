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
     * ======
     * 如果一个控件300px scaleX=2 了，那么getX 最大范围还是300px ，触摸范围大了，但是坐标范围没大
     * scaleX是以中心点向两侧对称放大/缩小
     *
     * translationX 后触摸范围也平移了 （和坐标范围没关系）
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drag);
    }
}
