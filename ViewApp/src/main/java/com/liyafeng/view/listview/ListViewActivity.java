package com.liyafeng.view.listview;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.GridView;
import android.widget.TextView;

import com.liyafeng.view.R;

public class ListViewActivity extends Activity {

    int count = 0;

    /**
     * 获取高度
     *
     * @param adapter
     * @return
     */
    public static int getListViewHeight(Adapter adapter, int add, GridView gv_rule) {
        int totalHeight = 0;
        for (int i = 0; i < adapter.getCount(); i += add) {
            View listItem = adapter.getView(i, null, gv_rule);
            listItem.measure(
                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
            totalHeight += listItem.getMeasuredHeight();
        }
        return totalHeight;
    }
    /**
     * 如果ScrollView包裹了listview，那么listview的高度就变为一个item的高度
     * 这个时候就必须手动测量出item的高度和，然后给listview设置高度
     * mGv.setLayoutParams(new LinearLayout.LayoutParams(mGv.getLayoutParams().width, totalHeight));
     * @param savedInstanceState
     */
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
