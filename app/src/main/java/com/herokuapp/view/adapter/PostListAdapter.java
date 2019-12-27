package com.herokuapp.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import com.herokuapp.data.entity.Post;
import com.herokuapp.databinding.PostItemBinding;
import com.herokuapp.view.adapter.listevents.PostsClickListener;
import com.herokuapp.view.base.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

public class PostListAdapter extends BaseAdapter<PostListAdapter.PostViewHolder, Post>
        implements Filterable {

    private final PostsClickListener postsClickListener;
    private List<Post> postList;

    public PostListAdapter(@NonNull PostsClickListener postsClickListener) {
        postList = new ArrayList<>();
        this.postsClickListener = postsClickListener;
    }

    @NonNull
    @Override
    public PostListAdapter.PostViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return PostListAdapter.PostViewHolder.create(LayoutInflater.from(viewGroup.getContext()), viewGroup, postsClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull PostListAdapter.PostViewHolder viewHolder, int i) {
        viewHolder.onBind(postList.get(i));
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    @Override
    public Filter getFilter() {
        return null;
    }

    @Override
    public void setData(List<Post> data) {
        this.postList = data;
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