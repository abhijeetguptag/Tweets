package com.herokuapp.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.herokuapp.R;
import com.herokuapp.data.entity.Comments;
import com.herokuapp.data.entity.Post;
import com.herokuapp.data.remote.Status;
import com.herokuapp.databinding.CommentListBinding;
import com.herokuapp.view.adapter.CommentListAdapter;
import com.herokuapp.view.adapter.listevents.CommentListClickCallBack;
import com.herokuapp.view.base.BaseFragment;
import com.herokuapp.view.base.EndlessRecyclerOnScrollListener;
import com.herokuapp.viewmodel.CommentsViewModel;

public class CommentFragment extends BaseFragment<CommentsViewModel, CommentListBinding> implements CommentListClickCallBack {


    private String postId;

    public static CommentFragment newInstance() {
        Bundle args = new Bundle();
        CommentFragment fragment = new CommentFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Class<CommentsViewModel> getViewModel() {
        return CommentsViewModel.class;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.comment_list;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        dataBinding.commentRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        dataBinding.commentRecyclerView.setAdapter(new CommentListAdapter(this));
        return dataBinding.getRoot();
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            Post post = bundle.getParcelable(FragmentUtils.POST_KEY);
            dataBinding.setPostComments(post);
            if (post != null) {
                postId = post.getId();
                fetchData(postId, pageNo);
            }
            dataBinding.commentRecyclerView.addOnScrollListener(new EndlessRecyclerOnScrollListener() {
                @Override
                public void onLoadMore() {
                    int totalCount = dataBinding.commentRecyclerView.getAdapter().getItemCount();
                    if (totalCount < maxItemCount)
                        fetchData(postId, ++pageNo);
                }
            });
        }

    }

    private void fetchData(String postId, int pageNo) {
        viewModel.getComments(postId, pageNo)
                .observe(this, listResource -> {
                    if (null != listResource && (listResource.status == Status.ERROR || listResource.status == Status.SUCCESS)) {
                        maxItemCount = listResource.totalDataAvailable;
                        dataBinding.progressBarComment.setVisibility(View.GONE);
                        dataBinding.errorLayoutComment.setText(listResource.getMessage());
                    }
                    dataBinding.setResource(listResource);

                    // If the cached data is already showing then no need to show the error
                    if (null != dataBinding.commentRecyclerView.getAdapter() && dataBinding.commentRecyclerView.getAdapter().getItemCount() > 0) {
                        dataBinding.errorLayoutComment.setVisibility(View.GONE);
                    }

                    if (null != listResource && listResource.status == Status.ERROR)
                        showToastMessage(listResource.getMessage());
                });
    }

    @Override
    public void onListItemClicked(Comments comment) {

    }
}
