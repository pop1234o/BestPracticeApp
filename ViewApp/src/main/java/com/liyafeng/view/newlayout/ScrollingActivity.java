package com.liyafeng.view.newlayout;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.liyafeng.view.R;

public class ScrollingActivity extends AppCompatActivity {

    /**
     * AppCompatActivity 是默认有actionbar的
     *
     * ConstraintLayout 约束布局（偏平化）
     *
     * DrawerArrowDrawable （drawer的联动icon）
     * DrawerLayout  v4
     * SwipeRefreshLayout v4（默认下拉刷新效果）
     *
     * NestedScrollView  取代recyclerview的   v4
     *
     * Toolbar 取代action bar 因为它拓展性低
     * AppbarLayout，在CoordinatorLayout中使用Toolbar要嵌套这个
     * (AppbarLayout是为了关联滑动控件和toolbar而诞生的)
     *
     * CoordinatorLayout（协调布局，子控件之前联动）
     *
     * FloatingActionButton
     *
     * CollapsingToolbarLayout
     *
     * SnapBar 一个toast
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        toolbar.setTitle("title");

        setSupportActionBar(toolbar);

        initRecyclerView();
    }

    private void initRecyclerView() {
        final RecyclerView recycer = (RecyclerView) findViewById(R.id.recycer);
        recycer.setLayoutManager(new LinearLayoutManager(this));
        RecyclerView.Adapter adapter = new RecyclerView.Adapter<ListHolder>() {
            @Override
            public ListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.list_recycle, null);

                view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

                ListHolder listHolder = new ListHolder(view);

                return listHolder;
            }

            @Override
            public void onBindViewHolder(ListHolder holder, int position) {
                holder.setData(position);
            }

            @Override
            public int getItemCount() {
                return 150;
            }
        };
        recycer.setAdapter(adapter);
    }


    public class ListHolder extends RecyclerView.ViewHolder {

        private TextView itemView;

        public ListHolder(View itemView) {
            super(itemView);
            this.itemView = (TextView) itemView.findViewById(R.id.item);
        }

        public void setData(int position) {
            itemView.setText("" + position);
        }
    }

}
