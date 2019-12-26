package com.herokuapp.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;



import com.herokuapp.data.entity.Author;
import com.herokuapp.databinding.AuthorItemBinding;
import com.herokuapp.view.AuthorListCallBack;
import com.herokuapp.view.base.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

public class AuthorListAdapter extends BaseAdapter<AuthorListAdapter.AuthorViewHolder, Author> {

    private final AuthorListCallBack articleListCallback;
    private List authorList;

    public AuthorListAdapter(@NonNull AuthorListCallBack articleListCallback) {
        authorList = new ArrayList<>();
        this.articleListCallback = articleListCallback;
    }

    @NonNull
    @Override
    public AuthorViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return AuthorViewHolder.create(LayoutInflater.from(viewGroup.getContext()), viewGroup, articleListCallback);
    }

    @Override
    public void onBindViewHolder(@NonNull AuthorViewHolder viewHolder, int i) {
        viewHolder.onBind((Author) authorList.get(i));
    }

    @Override
    public int getItemCount() {
        return authorList.size();
    }

    @Override
    public void setData(List data) {
        this.authorList = data;
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

        private void onBind(Author authorEntity) {
            binding.setAuthor(authorEntity);
            binding.executePendingBindings();
        }
    }
}
