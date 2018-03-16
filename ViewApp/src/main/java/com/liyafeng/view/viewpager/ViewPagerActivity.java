package com.liyafeng.view.viewpager;

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
                TextView tv_pager = (TextView) inflate.findViewById(R.id.tv_pager);
                View ll_pager = inflate.findViewById(R.id.ll_pager);
                int color = Color.RED;
                if(position%2==0){
                    color = Color.YELLOW;
                }
                ll_pager.setBackgroundColor(color);
                tv_pager.setTextColor(Color.BLACK);
                tv_pager.setText("pager-"+position);
                container.addView(inflate);
                return inflate;
            }
        });



    }
}
