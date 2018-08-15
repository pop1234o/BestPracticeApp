package com.liyafeng.view.listview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class BaseListAdapter<H extends BaseListAdapter.BaseHolder, T> extends RecyclerView.Adapter<H> {




    protected List<T> mDataList;


    private final LayoutInflater inflater;

    public BaseListAdapter(Context context, List<T> list) {
        this.mDataList = list;
        inflater = LayoutInflater.from(context);
    }

    public BaseListAdapter(Context context) {
        this(context, null);
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
        holder.refresh(position, mDataList.get(position));
    }

    @Override
    public int getItemCount() {
        return mDataList == null ? 0 : mDataList.size();
    }

    public abstract static class BaseHolder<T> extends RecyclerView.ViewHolder {

        public BaseHolder(View itemView) {
            super(itemView);
        }


        public abstract void refresh(int position, T t);
    }


    public void setList(List<T> list) {
        if (list == null) {
            return;
        }
        if (mDataList != null) {
            mDataList.clear();
            mDataList = null;
        }
        this.mDataList = list;
        notifyDataSetChanged();
    }

    public void addAll(Collection<T> list) {
        if (list == null) {
            return;
        }
        if (mDataList == null) {
            mDataList = new ArrayList<>(list.size());
        }
        int lastIndex = this.mDataList.size();
        if (this.mDataList.addAll(list)) {
            notifyItemRangeInserted(lastIndex, list.size());
        }
    }

    public void addAllFirst(Collection<T> list) {
        if (list == null) {
            return;
        }
        if (mDataList == null) {
            mDataList = new ArrayList<>(list.size());
        }
        if (mDataList.addAll(0, list)) {
            notifyItemRangeInserted(0, list.size());
        }
    }

    public List<T> getmDataList() {
        return mDataList;
    }

}
