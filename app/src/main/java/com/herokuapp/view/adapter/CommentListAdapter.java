package com.herokuapp.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;


import com.herokuapp.data.entity.Comments;
import com.herokuapp.databinding.CommentItemBinding;
import com.herokuapp.view.adapter.listevents.CommentListClickCallBack;
import com.herokuapp.view.base.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

public class CommentListAdapter extends BaseAdapter<CommentListAdapter.CommentViewHolder, Comments>
        implements Filterable {

    private final CommentListClickCallBack commentListClickCallback;
    private List<Comments> commentsList;

    public CommentListAdapter(@NonNull CommentListClickCallBack listClickCallBack) {
        commentsList = new ArrayList<>();
        this.commentListClickCallback = listClickCallBack;
    }

    @NonNull
    @Override
    public CommentListAdapter.CommentViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return CommentListAdapter.CommentViewHolder.create(LayoutInflater.from(viewGroup.getContext()), viewGroup, commentListClickCallback);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentListAdapter.CommentViewHolder viewHolder, int i) {
        viewHolder.onBind(commentsList.get(i));
    }

    @Override
    public int getItemCount() {
        return commentsList.size();
    }

    @Override
    public Filter getFilter() {
        return null;
    }

    @Override
    public void setData(List<Comments> data) {
        this.commentsList = data;
    }


    static class CommentViewHolder extends RecyclerView.ViewHolder {

        final CommentItemBinding binding;

        private CommentViewHolder(CommentItemBinding binding, CommentListClickCallBack callback) {
            super(binding.getRoot());
            this.binding = binding;
            binding.getRoot().setOnClickListener(v ->
                    callback.onListItemClicked(binding.getComment()));
        }

        private static CommentListAdapter.CommentViewHolder create(LayoutInflater inflater, ViewGroup parent, CommentListClickCallBack callback) {
            CommentItemBinding itemListBinding = CommentItemBinding.inflate(inflater, parent, false);
            return new CommentListAdapter.CommentViewHolder(itemListBinding, callback);
        }

        private void onBind(Comments commentEntity) {
            binding.setComment(commentEntity);
            binding.executePendingBindings();
        }
    }

}
