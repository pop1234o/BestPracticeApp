package com.liyafeng.view.bar;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.liyafeng.view.R;

public class ToolBarActivity extends Activity {


    private Toolbar toolbar;

    /**
     * ActionBar和ToolBar
     * https://developer.android.google.cn/reference/android/app/ActionBar.html
     * https://developer.android.google.cn/reference/android/widget/Toolbar.html
     * -----------------------
     * ActionBar是api 11的时候，也就是3.0的时候加入的
     * ToolBar是api 21的时候，就是5.0的时候加入的
     * ----------------
     * ActionBar是Activity的Window中最顶层的视图
     * 而ToolBar可以嵌套在视图中的任意层级
     * <p>
     * ===============================
     * A Toolbar is a generalization of action bars for use within application layouts.
     * While an action bar is traditionally part of an Activity's opaque window decor
     * controlled by the framework, a Toolbar may be placed at any arbitrary level of
     * nesting within a view hierarchy. An application may choose to designate a Toolbar
     * as the action bar for an Activity using the setActionBar() method.
     * 我们可以用setActionBar来将一个我们自定义的toolbar交给系统控制
     * <p>
     * =============================
     * Toolbar就相当于一个LinearLayout，只不过里面都是针对bar的特性来封装的
     *
     *
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tool_bar);
        initView();


    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);

    }
}
