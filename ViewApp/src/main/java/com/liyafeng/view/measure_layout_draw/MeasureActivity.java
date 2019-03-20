package com.liyafeng.view.measure_layout_draw;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.liyafeng.view.R;

public class MeasureActivity extends AppCompatActivity {

    /**
     * http://stackmirror.caup.cn/page/rhala8qd0ieu
     * 当我们 clipChildren=false 的时候子布局的边界能超出父布局
     * 但是当父布局是Relativelayout的时候这个无效
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_measure);

    }
}
