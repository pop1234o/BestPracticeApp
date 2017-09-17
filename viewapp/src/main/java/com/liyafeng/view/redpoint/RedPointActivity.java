package com.liyafeng.view.redpoint;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.liyafeng.view.R;


public class RedPointActivity extends Activity implements View.OnTouchListener {

    private BaseAdapter adapter1 = new BaseAdapter() {
        @Override
        public int getCount() {
            return 10;
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
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(RedPointActivity.this).inflate(R.layout.item_list, null);
            }
            View viewById = convertView.findViewById(R.id.view);

            viewById.setOnTouchListener(RedPointActivity.this);
            return convertView;
        }
    };
    private View inflate;
    private PointView pointview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_red_point);

                ListView listview = (ListView) findViewById(R.id.listview);

        listview.setAdapter(adapter1);

    }
    @Override
    public boolean onTouch(final View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                ViewParent parent = v.getParent();
                if (parent == null) {
                    return false;
                }
                parent.requestDisallowInterceptTouchEvent(true);

                inflate = LayoutInflater.from(this).inflate(R.layout.layout_drag, null);
                pointview = (PointView) inflate.findViewById(R.id.pointview);
                PointLayout pointlayout = (PointLayout) inflate.findViewById(R.id.pointlayout);
                pointlayout.setOnRemoveListenter(new PointLayout.OnRemoveListenter() {
                    @Override
                    public void onRemove() {
                        v.setVisibility(View.GONE);
                    }

                    @Override
                    public void onRestore() {
                        v.setVisibility(View.VISIBLE);
                    }
                });
                pointview.setView(v);
                ViewGroup viewGroup = (ViewGroup) v.getRootView();
                viewGroup.addView(inflate);
                v.setVisibility(View.INVISIBLE);

                break;
            case MotionEvent.ACTION_UP:


                break;
            case MotionEvent.ACTION_MOVE:

                break;

        }

        pointview.onTouchEvent(event);
        return true;
    }
}
