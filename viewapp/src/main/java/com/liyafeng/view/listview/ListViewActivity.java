package com.liyafeng.view.listview;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.liyafeng.view.R;

public class ListViewActivity extends Activity {

    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        CustomListView listview = (CustomListView) findViewById(R.id.listview);
        if(listview==null){
            return;
        }

        listview.setAdapter(new CustomListView.ListAdapter() {
            @Override
            public int getCount() {
                return 50;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if(convertView==null){
                    convertView =new TextView(ListViewActivity.this);
//                    Log.i(TAG, "getView: 创建"+ ++count);
                }
                TextView tv= (TextView)convertView;
                tv.setText(position+"");
                return tv;
            }
        });

//        ListView listview = (ListView) findViewById(R.id.listview);
//
//        listview.setAdapter(new BaseAdapter() {
//            @Override
//            public int getCount() {
//                return 50;
//            }
//
//            @Override
//            public Object getItem(int position) {
//                return null;
//            }
//
//            @Override
//            public long getItemId(int position) {
//                return 0;
//            }
//
//            @Override
//            public View getView(int position, View convertView, ViewGroup parent) {
//                if (convertView == null) {
//                    convertView = new TextView(ListViewActivity.this);
////                    Log.i(TAG, "getView: 创建"+ ++count);
//                }
//                TextView tv = (TextView) convertView;
//                tv.setText(position + "");
//                return tv;
//            }
//        });

    }
}
