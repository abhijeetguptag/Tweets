package com.herokuapp.view.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.herokuapp.data.entity.Comments;
import com.herokuapp.databinding.CommentItemBinding;
import com.herokuapp.view.adapter.listevents.CommentListClickCallBack;
import com.herokuapp.view.base.BaseAdapter;


public class CommentListAdapter extends BaseAdapter<Comments, CommentListAdapter.CommentViewHolder> {

    private static final DiffUtil.ItemCallback<Comments> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Comments>() {
                // Concert details may have changed if reloaded from the database,
                // but ID is fixed.
                @Override
                public boolean areItemsTheSame(Comments oldConcert, Comments newConcert) {
                    return oldConcert.getId().equals(newConcert.getId());
                }

                @SuppressLint("DiffUtilEquals")
                @Override
                public boolean areContentsTheSame(Comments oldConcert,
                                                  Comments newConcert) {
                    return oldConcert.equals(newConcert);
                }
            };
    private final CommentListClickCallBack commentListClickCallback;
//    public CommentListAdapter(@NonNull CommentListClickCallBack listClickCallBack) {
//        commentsList = new ArrayList<>();
//        this.commentListClickCallback = listClickCallBack;
//    }

    public CommentListAdapter(@NonNull CommentListClickCallBack articleListCallback) {
        super(DIFF_CALLBACK);
        this.commentListClickCallback = articleListCallback;

    }

    @NonNull
    @Override
    public CommentListAdapter.CommentViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return CommentListAdapter.CommentViewHolder.create(LayoutInflater.from(viewGroup.getContext()), viewGroup, commentListClickCallback);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Comments repoItem = this.getItem(position);
        if (repoItem != null) {
            ((CommentListAdapter.CommentViewHolder) holder).onBind(repoItem);
        }
    }

    @Override
    public void setData(PagedList<Comments> data) {
        this.submitList(data);
        notifyDataSetChanged();
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
