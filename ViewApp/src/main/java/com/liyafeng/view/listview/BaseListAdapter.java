package com.liyafeng.view.listview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public abstract class BaseListAdapter<H extends BaseListAdapter.BaseHolder, T> extends RecyclerView.Adapter<H> {


    private List<T> list;

    public void setList(List<T> list) {
        this.list = list;
    }

    private final LayoutInflater inflater;

    public BaseListAdapter(Context context, List<T> list) {
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public H onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(getLayoutId(), parent, false);
        H h = getHolder(view);
        return h;
    }

    protected abstract H getHolder(View view);

    protected abstract int getLayoutId();

    @Override
    public void onBindViewHolder(H holder, int position) {
        holder.refresh(position, list.get(position));
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public abstract static class BaseHolder<T> extends RecyclerView.ViewHolder {

        public BaseHolder(View itemView) {
            super(itemView);
        }


        public abstract  void refresh(int position, T t);
    }
}
