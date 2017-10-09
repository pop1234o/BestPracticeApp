package com.liyafeng.view.recycleview;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.liyafeng.view.R;

import static com.liyafeng.view.MainActivity.TAG;

public class RecyclerViewActivity extends Activity {

    private RecyclerView.Adapter<ListHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        RecyclerView recycer = (RecyclerView) findViewById(R.id.recycer);
        recycer.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecyclerView.Adapter<ListHolder>() {
            @Override
            public ListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                TextView textView = new TextView(RecyclerViewActivity.this);
                textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                textView.setBackgroundColor(Color.RED);
                textView.setTextColor(Color.BLACK);
                ListHolder listHolder = new ListHolder(textView);

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

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ListCallback());
        itemTouchHelper.attachToRecyclerView(recycer);
    }

    public class ListHolder extends RecyclerView.ViewHolder{

        private TextView itemView;

        public ListHolder(View itemView) {
            super(itemView);
            this.itemView = (TextView) itemView;
        }

        public void setData(int position){
            itemView.setText(""+position);
        }
    }


    public class ListCallback extends ItemTouchHelper.Callback{

        @Override
        public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            int dragFlags = ItemTouchHelper.UP|ItemTouchHelper.DOWN;
            int swipeFlags  =ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT;
            int i = makeMovementFlags(dragFlags, swipeFlags);
            return i;
        }

        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {

            Log.i(TAG, "onMove: ");
            adapter.notifyItemMoved(viewHolder.getAdapterPosition(),target.getAdapterPosition());
            return false;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
            adapter.notifyItemRemoved(viewHolder.getAdapterPosition());
            Log.i(TAG, "onSwiped: ");
        }

        @Override
        public boolean isLongPressDragEnabled() {
            return super.isLongPressDragEnabled();
        }

        @Override
        public boolean isItemViewSwipeEnabled() {
            return super.isItemViewSwipeEnabled();
        }

    }
}
