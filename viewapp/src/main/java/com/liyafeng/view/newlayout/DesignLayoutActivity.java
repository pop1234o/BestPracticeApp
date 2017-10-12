package com.liyafeng.view.newlayout;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.liyafeng.view.R;

public class DesignLayoutActivity extends Activity {

    /**
     * As a top-level application decor or chrome layout
     * As a container for a specific interaction with one or more child views
     * <p>
     * 这个其实就是来协调滚动的
     * <p>
     * UI变换会更连贯
     *
     * 每个sdk版本下自带的res中的Style也不一样，它里面自定义了一些基本的属性
     * 如果我们用低版本的sdk，这些属性就没有，可能会报错
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_design_layout);

        final RecyclerView recycer = (RecyclerView) findViewById(R.id.recycer);
        recycer.setLayoutManager(new LinearLayoutManager(this));
        RecyclerView.Adapter adapter = new RecyclerView.Adapter<DesignLayoutActivity.ListHolder>() {
            @Override
            public DesignLayoutActivity.ListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.list_recycle, null);

                view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

                DesignLayoutActivity.ListHolder listHolder = new DesignLayoutActivity.ListHolder(view);

                return listHolder;
            }

            @Override
            public void onBindViewHolder(DesignLayoutActivity.ListHolder holder, int position) {
                holder.setData(position);
            }

            @Override
            public int getItemCount() {
                return 50;
            }
        };
        recycer.setAdapter(adapter);

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Snackbar.make(recycer, "aaa", 100).show();
            }
        });
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
