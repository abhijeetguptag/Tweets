package com.herokuapp.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.herokuapp.R;
import com.herokuapp.data.entity.Author;
import com.herokuapp.data.entity.Comments;
import com.herokuapp.data.entity.Post;
import com.herokuapp.data.remote.Status;
import com.herokuapp.databinding.CommentListBinding;
import com.herokuapp.view.CommentListClickCallBack;
import com.herokuapp.view.adapter.CommentListAdapter;
import com.herokuapp.view.base.BaseFragment;
import com.herokuapp.viewmodel.CommentsViewModel;

public class CommentFragment extends BaseFragment<CommentsViewModel, CommentListBinding> implements CommentListClickCallBack {

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
        if(bundle !=null) {
            Post post = bundle.getParcelable(FragmentUtils.POST_KEY);
            if (post != null) {
                viewModel.getComments(post.getId(),"1")
                        .observe(this, listResource -> {
                            if (null != listResource && (listResource.status == Status.ERROR || listResource.status == Status.SUCCESS)) {
                                dataBinding.progressBarComment.setVisibility(View.GONE);
                                dataBinding.errorLayoutComment.setText(listResource.getMessage());
                            }
                            dataBinding.setResource(listResource);

                            // If the cached data is already showing then no need to show the error
                            if (null != dataBinding.commentRecyclerView.getAdapter() && dataBinding.commentRecyclerView.getAdapter().getItemCount() > 0) {
                                dataBinding.errorLayoutComment.setVisibility(View.GONE);
                            }
                        });
            }
        }

    }

    @Override
    public void onListItemClicked(Comments comment) {

    }
}
