package com.liyafeng.view;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.liyafeng.view.SimpleViewPager.ViewPagerActivity;
import com.liyafeng.view.drag.DragActivity;
import com.liyafeng.view.redpoint.RedPointActivity;
import com.liyafeng.view.slidingmenu.SlidingMenuActivity;

public class MainActivity extends Activity {
    public static String TAG = "test";


    String[] str = {"drag",
            "slidingmenu",
            "redpoint",
            "viewpager"

    };


    Class[] classes ={DragActivity.class,
            SlidingMenuActivity.class,
            RedPointActivity.class,
            ViewPagerActivity.class

            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ListView list = (ListView) findViewById(R.id.list);

        list.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return str.length;
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(final int position, View convertView, ViewGroup parent) {
                if(convertView ==null){
                    convertView =new TextView(MainActivity.this);
                    TextView textView = (TextView) convertView;
                    textView.setTextColor(Color.BLACK);
                    textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,100));
                }
                TextView textView = (TextView) convertView;
                textView.setText(str[position]);
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this, classes[position]);
                        startActivity(intent);
                    }
                });
                return convertView;
            }
        });
    }


}
