package com.liyafeng.view.SimpleViewPager;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.liyafeng.view.R;


public class ViewPagerActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);

        SimpleViewPager viewpager = (SimpleViewPager) findViewById(R.id.viewpager);
        viewpager.setAdapter(new SimpleViewPager.SimplePagerAdapter() {
            @Override
            public int getCount() {
                return 4;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                View inflate = LayoutInflater.from(ViewPagerActivity.this).inflate(R.layout.pager_item, null);
//                TextView textView = new TextView(ViewPagerActivity.this);
//                textView.setText("view-"+position);
//                textView.setBackgroundColor(Color.RED);
                TextView tv_pager = (TextView) inflate.findViewById(R.id.tv_pager);
                tv_pager.setTextColor(Color.BLACK);
                tv_pager.setText("pager-"+position);
                container.addView(inflate);
                return inflate;
            }
        });
//        ViewPager viewpager = (ViewPager) findViewById(R.id.viewpager);
//        viewpager.setAdapter(new PagerAdapter() {
//            @Override
//            public int getCount() {
//                return 4;
//            }
//
//            @Override
//            public boolean isViewFromObject(View view, Object object) {
//
//                return view==object;
//            }
//
//            @Override
//            public Object instantiateItem(ViewGroup container, int position) {
//                TextView textView = new TextView(ViewPagerActivity.this);
//                textView.setText("view-"+position);
//                textView.setTextColor(Color.BLACK);
//                container.addView(textView);
//                return textView;
//            }
//
//            @Override
//            public void destroyItem(ViewGroup container, int position, Object object) {
//                container.removeView((View) object);
//            }
//        });
    }
}
