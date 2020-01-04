package com.herokuapp.view.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.herokuapp.data.entity.Post;
import com.herokuapp.databinding.PostItemBinding;
import com.herokuapp.view.adapter.listevents.PostsClickListener;
import com.herokuapp.view.base.BaseAdapter;

public class PostListAdapter extends BaseAdapter<Post, PostListAdapter.PostViewHolder> {

    private static final DiffUtil.ItemCallback<Post> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Post>() {
                // Concert details may have changed if reloaded from the database,
                // but ID is fixed.
                @Override
                public boolean areItemsTheSame(Post oldConcert, Post newConcert) {
                    return oldConcert.getId().equals(newConcert.getId());
                }

                @SuppressLint("DiffUtilEquals")
                @Override
                public boolean areContentsTheSame(Post oldConcert,
                                                  @NonNull Post newConcert) {
                    return oldConcert.equals(newConcert);
                }
            };
    private final PostsClickListener postsClickListener;


    public PostListAdapter(@NonNull PostsClickListener postsClickListener) {
        super(DIFF_CALLBACK);
        this.postsClickListener = postsClickListener;
    }

    @NonNull
    @Override
    public PostListAdapter.PostViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return PostListAdapter.PostViewHolder.create(LayoutInflater.from(viewGroup.getContext()), viewGroup, postsClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Post postItem = this.getItem(position);
        if (postItem != null) {
            ((PostListAdapter.PostViewHolder) holder).onBind(postItem);
        }
    }

    @Override
    public void setData(PagedList<Post> data) {
        this.submitList(data);
        notifyDataSetChanged();
    }

    static class PostViewHolder extends RecyclerView.ViewHolder {

        final PostItemBinding binding;

        private PostViewHolder(PostItemBinding binding, PostsClickListener callback) {
            super(binding.getRoot());
            this.binding = binding;
            binding.getRoot().setOnClickListener(v ->
                    callback.onListItemClicked(binding.getPost()));
        }

        private static PostViewHolder create(LayoutInflater inflater, ViewGroup parent, PostsClickListener callback) {
            PostItemBinding itemListBinding = PostItemBinding.inflate(inflater, parent, false);
            return new PostListAdapter.PostViewHolder(itemListBinding, callback);
        }

        private void onBind(Post postEntity) {
            binding.setPost(postEntity);
            binding.executePendingBindings();
        }
    }
}