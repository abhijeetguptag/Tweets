package com.herokuapp.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.herokuapp.R;
import com.herokuapp.data.entity.Author;
import com.herokuapp.data.entity.Post;
import com.herokuapp.data.Status;
import com.herokuapp.databinding.PostListBinding;
import com.herokuapp.view.adapter.PostListAdapter;
import com.herokuapp.view.adapter.listevents.PostsClickListener;
import com.herokuapp.view.base.BaseFragment;
import com.herokuapp.viewmodel.PostViewModel;

public class PostsFragment extends BaseFragment<PostViewModel, PostListBinding> implements PostsClickListener {

    public static PostsFragment newInstance() {
        Bundle args = new Bundle();
        PostsFragment fragment = new PostsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Class<PostViewModel> getViewModel() {
        return PostViewModel.class;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.post_list;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        dataBinding.postRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        dataBinding.postRecyclerView.setAdapter(new PostListAdapter(this));
        return dataBinding.getRoot();
    }


    @Override
    public void onListItemClicked(Post post) {
        if (null != getActivity()) {
            Bundle args = new Bundle();
            args.putParcelable(FragmentUtils.POST_KEY, post);
            CommentFragment detailFragment = CommentFragment.newInstance();
            detailFragment.setArguments(args);
            FragmentUtils.replaceFragment((AppCompatActivity) getActivity(), detailFragment, R.id.fragContainer, true);
        }

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        Bundle bundle = getArguments();
        if (bundle != null) {
            Author author = bundle.getParcelable(FragmentUtils.AUTHOR_KEY);
            if (author != null) {
                dataBinding.setPostAuthor(author);
                fetchData("" + author.getId());
            }
        }
    }

    private void fetchData(String authorId) {
        viewModel.getPost(authorId)
                .observe(this, listResource -> {
                    if (null != listResource && (listResource.status == Status.ERROR || listResource.status == Status.SUCCESS)) {
                        dataBinding.progressBarPost.setVisibility(View.GONE);
                        dataBinding.errorLayoutPost.setText(listResource.getMessage());
                    }
                    dataBinding.setResource(listResource);

                    // If the cached data is already showing then no need to show the error
                    if (null != dataBinding.postRecyclerView.getAdapter() && dataBinding.postRecyclerView.getAdapter().getItemCount() > 0) {
                        dataBinding.errorLayoutPost.setVisibility(View.GONE);
                    }

                    if (null != listResource && listResource.status == Status.ERROR)
                        showToastMessage(listResource.getMessage());
                });
    }
}
