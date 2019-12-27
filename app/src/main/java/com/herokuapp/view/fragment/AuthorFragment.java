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
import com.herokuapp.data.entity.Author;
import com.herokuapp.data.remote.Status;
import com.herokuapp.databinding.AuthorListBinding;
import com.herokuapp.view.adapter.listevents.AuthorListCallBack;
import com.herokuapp.view.adapter.AuthorListAdapter;
import com.herokuapp.view.base.BaseFragment;
import com.herokuapp.view.base.EndlessRecyclerOnScrollListener;
import com.herokuapp.viewmodel.AuthorsViewModel;

public class AuthorFragment extends BaseFragment<AuthorsViewModel, AuthorListBinding> implements AuthorListCallBack {



    public static AuthorFragment newInstance() {
        Bundle args = new Bundle();
        AuthorFragment fragment = new AuthorFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Class<AuthorsViewModel> getViewModel() {
        return AuthorsViewModel.class;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.author_list;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        dataBinding.authorRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        dataBinding.authorRecyclerView.setAdapter(new AuthorListAdapter(this));
        return dataBinding.getRoot();
    }


    @Override
    public void onListItemClicked(Author author) {
        if (null != getActivity()) {
            Bundle args = new Bundle();
            args.putParcelable(FragmentUtils.AUTHOR_KEY, author);
            PostsFragment detailFragment = PostsFragment.newInstance();
            detailFragment.setArguments(args);
            FragmentUtils.replaceFragment((AppCompatActivity) getActivity(), detailFragment, R.id.fragContainer, true);
        }

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        fetchData(pageNo);
        dataBinding.authorRecyclerView.addOnScrollListener(new EndlessRecyclerOnScrollListener() {
            @Override
            public void onLoadMore() {
                int totalCount= dataBinding.authorRecyclerView.getAdapter().getItemCount();
                if(totalCount < maxItemCount)
                fetchData(++pageNo);
            }
        });

    }

    private void fetchData(int pageNo){
        viewModel.getAuthorList(pageNo)
                .observe(this, listResource -> {
                    if (null != listResource && (listResource.status == Status.ERROR || listResource.status == Status.SUCCESS)) {
                        maxItemCount =listResource.totalDataAvailable;
                        dataBinding.progressBarAuthor.setVisibility(View.GONE);
                        dataBinding.errorLayoutAuthor.setText(listResource.getMessage());
                    }
                    dataBinding.setResource(listResource);

                    // If the cached data is already showing then no need to show the error
                    if (null != dataBinding.authorRecyclerView.getAdapter() && dataBinding.authorRecyclerView.getAdapter().getItemCount() > 0) {
                        dataBinding.errorLayoutAuthor.setVisibility(View.GONE);
                    }

                    if(null != listResource && listResource.status == Status.ERROR)
                        showToastMessage(listResource.getMessage());
                });
    }
}
