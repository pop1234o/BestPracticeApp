package com.liyafeng.view.drawable;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.liyafeng.view.R;

public class DrawableActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawable);
        //一个边框的背景
        getResources().getDrawable(R.drawable.bg_frame);
        //一个横线的背景
        getResources().getDrawable(R.drawable.bg_bottom_line);
    }
}
