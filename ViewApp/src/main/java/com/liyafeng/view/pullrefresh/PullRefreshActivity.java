package com.liyafeng.view.pullrefresh;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.liyafeng.view.R;


public class PullRefreshActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_refresh);


        RecyclerView recycer = (RecyclerView) findViewById(R.id.recycer);
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
                return 50;
            }
        };
        recycer.setAdapter(adapter);

    }

    public class ListHolder extends RecyclerView.ViewHolder{

        private TextView itemView;

        public ListHolder(View itemView) {
            super(itemView);
            this.itemView = (TextView) itemView.findViewById(R.id.item);
        }

        public void setData(int position){
            itemView.setText(""+position);
        }
    }
}
