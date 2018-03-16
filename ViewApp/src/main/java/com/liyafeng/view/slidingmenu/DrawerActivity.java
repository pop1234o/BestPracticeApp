package com.liyafeng.view.slidingmenu;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.liyafeng.view.R;

import java.util.ArrayList;

public class DrawerActivity extends AppCompatActivity {

    private FrameLayout mContentFrame;
    private ListView mLeftDrawer;
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);
        initView();
    }

    private void initView() {
        mContentFrame = (FrameLayout) findViewById(R.id.content_frame);
        mLeftDrawer = (ListView) findViewById(R.id.left_drawer);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        ArrayList<String> strings = new ArrayList<>();
        strings.add("t1");
        strings.add("t2");
        mLeftDrawer.setAdapter(new ArrayAdapter<String>(this,
                R.layout.drawer_list_item, R.id.tv_sliding_item, strings));
        // Set the list's click listener
        mLeftDrawer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(DrawerActivity.this, "" + position, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
