package com.liyafeng.view.viewpager;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.liyafeng.view.R;

public class CardPagerActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_pager);

        ViewPager viewpager = (ViewPager) findViewById(R.id.viewpager);

        viewpager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return 4;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {

                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                View inflate = LayoutInflater.from(CardPagerActivity.this).inflate(R.layout.custom_pager_item, null);
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
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }
        });
    }
}
