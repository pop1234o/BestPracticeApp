package com.liyafeng.view.listview.recycleview;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
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
//        recycer.setLayoutManager(new LinearLayoutManager(this));
//        recycer.setLayoutManager(new GridLayoutManager(this,3));


        //设置item的动画，只有用notifyinsert的时候，才起作用，但是有一个问题，就是在屏幕外面的item不执行
        //动画，导致向下滑动时，只执行第一个item的动画，剩下添加的item因为在屏幕外，所以没有动画
        DefaultItemAnimator animator = new DefaultItemAnimator();
        animator.setAddDuration(100);
        recycer.setItemAnimator(animator);
        recycer.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        adapter = new RecyclerView.Adapter<ListHolder>() {
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

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ListCallback());
        itemTouchHelper.attachToRecyclerView(recycer);
    }

    public class ListHolder extends RecyclerView.ViewHolder{

        private TextView itemView;

        public ListHolder(View itemView) {
            super(itemView);
            this.itemView = (TextView) itemView.findViewById(R.id.item);
        }

        public void setData(int position){
            itemView.setText(""+position);
            itemView.setHeight(position*50);
        }
    }


    public class ListCallback extends ItemTouchHelper.Callback{

        @Override
        public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            int dragFlags = ItemTouchHelper.UP|ItemTouchHelper.DOWN|ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT;
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
            return false;
        }



//        @Override
//        public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
//            if (dX > -200) {
//
////                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
//                viewHolder.itemView.scrollTo((int) -dX,0);
//            } else {
//
//            }
//
//
//        }
    }
}
