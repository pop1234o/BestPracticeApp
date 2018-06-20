package com.liyafeng.view.viewpager;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liyafeng on 2018/1/4.
 */

public class Main {

    /**
     * @param args 注意在Adapter中记得container.addView，否则显示不出来
     *             而且必须用addView，不能有inflate(R.layout.xxx,container)，否则显示不出来
     */
    public static void main(String[] args) {

    }

    public void indicator() {

//            ViewPager vp_banner = (ViewPager) header.findViewById(R.id.vp_banner);
//            final TextView tv_head_content = (TextView) header.findViewById(R.id.tv_head_content);
//            LinearLayout ll_point = (LinearLayout) header.findViewById(R.id.ll_point);
//            ll_point.removeAllViews();
//
//            vp_banner.setAdapter(new HeadPageAdapter(context, banner));
//            int width = AppUtil.dp2px(context, 10);
//            int height = AppUtil.dp2px(context, 3);
//            int left = AppUtil.dp2px(context, 7);
//            if (views == null) {
//                views = new ArrayList<>();
//            } else {
//                views.clear();
//            }
//            for (int i = 0; i < banner.size(); i++) {
//                View view = new View(context);
//                view.setBackgroundResource(R.drawable.selector_head_tab);
//                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(width, height);
//                if (i != 0) {
//                    layoutParams.leftMargin = left;
//                    view.setEnabled(false);
//                } else {
//                    view.setEnabled(true);
//                }
//                view.setLayoutParams(layoutParams);
//                ll_point.addView(view);
//                views.add(view);
//            }
//
//            vp_banner.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//                @Override
//                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//                }
//
//                @Override
//                public void onPageSelected(int position) {
//                    tv_head_content.setText(banner.get(position).getTitle());
//
//
//                    for (int i = 0; i < views.size(); i++) {
//                        View view = views.get(i);
//                        if (i == position) {
//                            view.setEnabled(true);
//                        } else {
//                            view.setEnabled(false);
//
//                        }
//                    }
//                }
//
//                @Override
//                public void onPageScrollStateChanged(int state) {
//
//                }
//            });
//
//            tv_head_content.setText(banner.get(0).getTitle());
    }
}
