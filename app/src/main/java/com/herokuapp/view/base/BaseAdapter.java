package com.herokuapp.view.base;


import androidx.annotation.NonNull;
import androidx.paging.PagedList;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

public abstract class BaseAdapter<D, T extends RecyclerView.ViewHolder> extends PagedListAdapter<D, RecyclerView.ViewHolder> {

    @SuppressWarnings("unchecked")
    protected BaseAdapter(@NonNull DiffUtil.ItemCallback diffCallback) {
        super(diffCallback);
    }

    public abstract void setData(PagedList<D> data);

}
