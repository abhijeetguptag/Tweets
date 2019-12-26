package com.herokuapp.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.herokuapp.R;
import com.herokuapp.data.entity.Post;
import com.herokuapp.data.remote.Status;
import com.herokuapp.databinding.PostListBinding;
import com.herokuapp.view.PostsClickListener;
import com.herokuapp.view.adapter.PostListAdapter;
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
            CommentFragment detailFragment = new CommentFragment();
            detailFragment.setArguments(args);
            FragmentUtils.replaceFragment((AppCompatActivity) getActivity(), detailFragment, R.id.fragContainer, true);
        }

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        viewModel.getPost()
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
                });

    }
}
