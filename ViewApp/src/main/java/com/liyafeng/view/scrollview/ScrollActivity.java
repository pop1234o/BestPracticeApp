package com.liyafeng.view.scrollview;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.liyafeng.view.R;

public class ScrollActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll);
        LinearLayout clistview = (LinearLayout) findViewById(R.id.clistview);
        for (int i = 0; i < 100; i++) {
            TextView textView = new TextView(this);
            textView.setBackgroundColor(Color.YELLOW);
            textView.setTextColor(Color.BLACK);
            textView.setText("text"+i);
            clistview.addView(textView);
        }
    }
}
