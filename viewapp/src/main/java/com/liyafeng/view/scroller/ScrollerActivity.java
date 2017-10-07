package com.liyafeng.view.scroller;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.liyafeng.view.R;

public class ScrollerActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroller);
        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomScrollerView scroller = (CustomScrollerView) findViewById(R.id.scroller);
                scroller.smoothScrollTo(200,200);
            }
        });
    }
}
