package com.herokuapp.view.adapter;


import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.herokuapp.data.entity.Author;
import com.herokuapp.databinding.AuthorItemBinding;
import com.herokuapp.view.adapter.listevents.AuthorListCallBack;
import com.herokuapp.view.base.BaseAdapter;

@SuppressWarnings("unchecked")
public class AuthorListAdapter extends BaseAdapter<Author, AuthorListAdapter.AuthorViewHolder> {

    private static final DiffUtil.ItemCallback<Author> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Author>() {
                // Concert details may have changed if reloaded from the database,
                // but ID is fixed.
                @Override
                public boolean areItemsTheSame(Author oldConcert, Author newConcert) {
                    return oldConcert.getId() == newConcert.getId();
                }

                @SuppressLint("DiffUtilEquals")
                @Override
                public boolean areContentsTheSame(Author oldConcert,
                                                  Author newConcert) {
                    return oldConcert.equals(newConcert);
                }
            };
    private final AuthorListCallBack articleListCallback;


    public AuthorListAdapter(@NonNull AuthorListCallBack articleListCallback) {
        super(DIFF_CALLBACK);
        this.articleListCallback = articleListCallback;

    }

    @NonNull
    @Override
    public AuthorViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return AuthorViewHolder.create(LayoutInflater.from(viewGroup.getContext()), viewGroup, articleListCallback);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Author repoItem = this.getItem(position);
        if (repoItem != null) {
            ((AuthorViewHolder) holder).onBind(repoItem);
        }
    }

    @Override
    public void setData(PagedList data) {
        this.submitList(data);
        notifyDataSetChanged();
    }

    static class AuthorViewHolder extends RecyclerView.ViewHolder {

        final AuthorItemBinding binding;

        private AuthorViewHolder(AuthorItemBinding binding, AuthorListCallBack callback) {
            super(binding.getRoot());
            this.binding = binding;
            binding.getRoot().setOnClickListener(v ->
                    callback.onListItemClicked(binding.getAuthor()));
        }

        private static AuthorViewHolder create(LayoutInflater inflater, ViewGroup parent, AuthorListCallBack callback) {
            AuthorItemBinding itemListBinding = AuthorItemBinding.inflate(inflater, parent, false);
            return new AuthorViewHolder(itemListBinding, callback);
        }

        private void onBind(Author author) {
            if (author != null) {
                binding.setAuthor(author);
                binding.executePendingBindings();
            }
        }
    }
}
