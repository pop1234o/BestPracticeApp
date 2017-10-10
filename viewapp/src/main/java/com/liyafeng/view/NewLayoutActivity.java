package com.liyafeng.view;

import android.app.Activity;
import android.os.Bundle;

public class NewLayoutActivity extends Activity {

    /**
     * ConstraintLayout
     * 偏平布局，提高性能
     * 相对约束
     *
     *
     * 创造一个chain，就是相互toright toleft
     *
     * 我们之前用LinearLayout的权重，然后再用RelaiveLayout的相对约束
     *
     * 但是这个布局就集合在一起了
     *
     * 还有GuideLine来引导
     *
     *
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_layout);


    }
}
