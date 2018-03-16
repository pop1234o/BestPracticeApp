package com.liyafeng.view.slidingmenu;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import com.liyafeng.view.R;


public class SlidingMenuActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sliding_menu);

        SimpleSlidingMenu menu = (SimpleSlidingMenu) findViewById(R.id.menu);
        TextView textView1 = new TextView(this);
        textView1.setBackgroundColor(Color.WHITE);
        textView1.setTextColor(Color.BLACK);
        textView1.setText("this is content");
        TextView textView2 = new TextView(this);
        textView2.setBackgroundColor(Color.GRAY);
        textView2.setTextColor(Color.BLACK);
        textView2.setText("this is menu");
        menu.setContent(textView1);
        menu.setMenu(textView2,SimpleSlidingMenu.TYPE_MENU_SCALE,800);

    }
}
