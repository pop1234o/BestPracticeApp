package com.liyafeng.view.viewpager;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.liyafeng.view.R;

import java.util.ArrayList;


public class ViewPagerActivity extends Activity {

    private ArrayList<View> views;
    private ArrayList<String> banner;
    private ViewPager viewpager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);

        viewpager = (ViewPager) findViewById(R.id.viewpager);
        final ArrayList<String> list = new ArrayList<>();
        list.add("");
        list.add("");
        banner = new ArrayList<String>();
        banner.add("");
        banner.add("");
        viewpager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return list == null ? 0 : Integer.MAX_VALUE;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                position = position % list.size();

                View inflate = LayoutInflater.from(ViewPagerActivity.this).inflate(R.layout.pager_item, null);
                TextView tv_pager = (TextView) inflate.findViewById(R.id.tv_pager);
                View ll_pager = inflate.findViewById(R.id.ll_pager);
                int color = Color.RED;
                if (position % 2 == 0) {
                    color = Color.YELLOW;
                }
                ll_pager.setBackgroundColor(color);
                tv_pager.setTextColor(Color.BLACK);
                tv_pager.setText("pager-" + position);
                container.addView(inflate);
                return inflate;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }
        });


        initPoint();


    }

    private void initPoint() {
        LinearLayout ll_point = (LinearLayout) findViewById(R.id.ll_point);

//        int width = AppUtil.dp2px(context, 10);
//        int height = AppUtil.dp2px(context, 3);
//        int left = AppUtil.dp2px(context, 7);

        int width = 20;
        int height = 20;
        int left = 14;
        if (views == null) {
            views = new ArrayList<>();
        } else {
            views.clear();
        }
        for (int i = 0; i < banner.size(); i++) {
            View view = new View(this);
            view.setBackgroundResource(R.drawable.drawable_circle);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(width, height);
            if (i != 0) {
                layoutParams.leftMargin = left;
                view.setEnabled(false);
            } else {
                view.setEnabled(true);
            }
            view.setLayoutParams(layoutParams);
            ll_point.addView(view);
            views.add(view);
        }

        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                position = position % banner.size();
//                tv_head_content.setText(banner.get(position).getTitle());


                for (int i = 0; i < views.size(); i++) {
                    View view = views.get(i);
                    if (i == position) {
                        view.setEnabled(true);
                    } else {
                        view.setEnabled(false);

                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewpager.setCurrentItem(banner.size() * 1000);

    }
}
