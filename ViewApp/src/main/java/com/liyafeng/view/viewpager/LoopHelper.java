package com.liyafeng.view.viewpager;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;


public class LoopHelper {


    private static final int LOOP_TIME = 5000;
    private boolean isPause = true;

    private Handler handler = new Myhandler();
    private ViewPager viewPager;

    private boolean isRelease;
    private int count;

    public class Myhandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
//            LogUtil.i("test", "轮讯 isRelease=" + isRelease + "  isPause=" + isPause);
            if (isRelease) {
                return;
            }
            if (!isPause && viewPager != null) {
                int currentItem = viewPager.getCurrentItem();
                if (currentItem + 1 >= count) {
                    currentItem = -1;
                }
                viewPager.setCurrentItem(currentItem + 1, true);
            }
            sendEmptyMessageDelayed(0, LOOP_TIME);
        }
    }

    public LoopHelper(Activity activity) {
        activity.getFragmentManager().beginTransaction().add(new LifeFragemnt(new LifeListener() {
            @Override
            public void onPause() {
                pause();
//                LogUtil.i("test", "暂停");
            }

            @Override
            public void onResume() {
//                LogUtil.i("test", "开始");
                start();
            }

            @Override
            public void onDestroy() {
//                LogUtil.i("test", "销毁");
                release();

            }
        }), "").commit();
    }

    public void setViewPager(ViewPager viewPager) {
        this.viewPager = viewPager;
        PagerAdapter adapter = viewPager.getAdapter();
        if (adapter == null) {
            throw new NullPointerException("adapter is null");
        }
        count = adapter.getCount();

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

                switch (state) {
                    // 闲置中

                    case ViewPager.SCROLL_STATE_IDLE:
                        isPause = false;
                        break;
                    // 拖动中
                    case ViewPager.SCROLL_STATE_DRAGGING:
                        isPause = true;
                        break;
                }

            }
        });

//        handler.sendEmptyMessageDelayed(0, LOOP_TIME);
    }

    public void pause() {
        isPause = true;
        handler.removeMessages(0);
    }

    public void start() {
        if (isPause) {
            handler.sendEmptyMessageDelayed(0, LOOP_TIME);
            isPause = false;
        }
    }


    public void release() {
        isRelease = true;
        handler.removeMessages(0);

    }

    public static class LifeFragemnt extends Fragment {
        LifeListener lifeListener;

        public LifeFragemnt() {
        }

        @SuppressLint("ValidFragment")
        public LifeFragemnt(LifeListener lifeListener) {
            this.lifeListener = lifeListener;
        }

        @Override
        public void onPause() {
            super.onPause();
            if (lifeListener != null) {
                lifeListener.onPause();
            }
        }

        @Override
        public void onResume() {
            super.onResume();
            if (lifeListener != null) {
                lifeListener.onResume();
            }
        }

        @Override
        public void onDestroy() {
            super.onDestroy();
            if (lifeListener != null) {
                lifeListener.onDestroy();
            }
        }
    }

    public interface LifeListener {

        void onPause();

        void onResume();

        void onDestroy();
    }

}
