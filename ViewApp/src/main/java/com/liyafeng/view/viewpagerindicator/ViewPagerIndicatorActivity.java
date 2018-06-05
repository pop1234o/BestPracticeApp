package com.liyafeng.view.viewpagerindicator;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.liyafeng.view.R;

public class ViewPagerIndicatorActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_view_pager_indicator);

        TabLayout tl_view = (TabLayout) findViewById(R.id.tl_view);
        TabLayout.Tab tab = tl_view.newTab();
        tab.setTag("1");
        tab.setContentDescription("111");
        tl_view.addTab(tab.setText("Tab 1"));
        tl_view.addTab(tl_view.newTab().setText("Tab 2"));
        tl_view.addTab(tl_view.newTab().setText("Tab 3"));

        tl_view.setTabTextColors(Color.BLACK,Color.RED);
        tl_view.setSelectedTabIndicatorColor(Color.BLUE);
        tl_view.setTabMode(TabLayout.MODE_FIXED);//这种是一屏显示所有，均分

        final ViewPager viewpager = (ViewPager) findViewById(R.id.viewpager);
        viewpager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return 3;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view==object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                TextView textView = new TextView(ViewPagerIndicatorActivity.this);
                textView.setText(""+position);
                textView.setTextColor(Color.BLACK);
                container.addView(textView);
                return textView;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return "tabbb"+position;
            }
        });




        //关联
//        tl_view.setupWithViewPager(viewpager);

        //监听
        tl_view.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Log.i("test", "onTabSelected: "+tab.toString());
                viewpager.setCurrentItem(2,false);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                Log.i("test", "onTabUnselected: "+tab.toString());
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                Log.i("test", "onTabReselected: "+tab.toString());
            }
        });

        //viewpager禁止滑动
        viewpager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });
    }
}
