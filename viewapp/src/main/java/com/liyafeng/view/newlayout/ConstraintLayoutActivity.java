package com.liyafeng.view.newlayout;

import android.app.Activity;
import android.os.Bundle;

import com.liyafeng.view.R;

public class ConstraintLayoutActivity extends Activity {

    /**
     * ConstraintLayout
     * 偏平布局，提高性能
     * 相对约束
     *
     *
     * 创造一个chain，就是相互toright toleft ,而且要和parent进行约束
     * 而且width要是0dp ,才能形成一个chain
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
