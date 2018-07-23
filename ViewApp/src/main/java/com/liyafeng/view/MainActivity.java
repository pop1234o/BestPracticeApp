package com.liyafeng.view;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.liyafeng.view.bar.ToolBarActivity;
import com.liyafeng.view.camera.CameraActivity;
import com.liyafeng.view.camera.SystemCameraActivity;
import com.liyafeng.view.dialog.DialogActivity;
import com.liyafeng.view.dialog.MainPopUpWindowActivity;
import com.liyafeng.view.dialog.ToastActivity;
import com.liyafeng.view.drag.DragActivity;
import com.liyafeng.view.fragment.CustomFragmentActivity;
import com.liyafeng.view.listview.ListViewActivity;
import com.liyafeng.view.newlayout.CoordinatorLayoutActivity;
import com.liyafeng.view.newlayout.ScrollingActivity;
import com.liyafeng.view.recycleview.pullrefresh.PullRefreshActivity;
import com.liyafeng.view.recycleview.RecyclerViewActivity;
import com.liyafeng.view.redpoint.RedPointActivity;
import com.liyafeng.view.scroller.ScrollerActivity;
import com.liyafeng.view.scrollview.ChartActivity;
import com.liyafeng.view.scrollview.ScrollActivity;
import com.liyafeng.view.slidingmenu.DrawerActivity;
import com.liyafeng.view.slidingmenu.SlidingMenuActivity;
import com.liyafeng.view.swipecard.SwipeCardActivity;
import com.liyafeng.view.viewpager.CardPagerActivity;
import com.liyafeng.view.viewpager.RoundViewPagerActivity;
import com.liyafeng.view.viewpager.ViewPagerActivity;
import com.liyafeng.view.viewpagerindicator.ViewPagerIndicatorActivity;

public class MainActivity extends Activity {
    public static String TAG = "test";


    String[] str = {"drag",
            "slidingmenu",
            "redpoint",
            "viewpager",
            "cardpager",
            "swipecardlayout",
            "fragment",
            "scroller",
            "scrollview",
            "listview",
            "recyclerview",
            "pullrefresh",
            "coordinatorlayout",
            "scrolling",
            "popup",
            "dialog",
            "toast",
            "chartView",
            "drawerActivity",
            "camera",
            "systemCamera",
            "ViewPagerIndicator",
            "RoundViewPagerActivity",
            "toolbar",

    };


    Class[] classes ={DragActivity.class,
            SlidingMenuActivity.class,
            RedPointActivity.class,
            ViewPagerActivity.class,
            CardPagerActivity.class,
            SwipeCardActivity.class,
            CustomFragmentActivity.class,
            ScrollerActivity.class,
            ScrollActivity.class,
            ListViewActivity.class,
            RecyclerViewActivity.class,
            PullRefreshActivity.class,
            CoordinatorLayoutActivity.class,
            ScrollingActivity.class,
            MainPopUpWindowActivity.class,
            DialogActivity.class,
            ToastActivity.class,
            ChartActivity.class,
            DrawerActivity.class,
            CameraActivity.class,
            SystemCameraActivity.class,
            ViewPagerIndicatorActivity.class,
            RoundViewPagerActivity.class,
            ToolBarActivity.class,

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
//
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                new DialogActivity.FullDiolog(MainActivity.this).createDialog();
//            }
//        },5000);


    }


    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart: ");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy: ");
    }
}
