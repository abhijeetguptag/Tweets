package com.herokuapp.data.remote.repository;


import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.herokuapp.data.entity.Comments;
import com.herokuapp.data.local.EntityDao;
import com.herokuapp.data.remote.network.ApiConstants;
import com.herokuapp.data.remote.network.ApiService;
import com.herokuapp.data.Resource;
import com.herokuapp.data.remote.network.NetworkBoundResource;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;

public class CommentsRepository {
    private final EntityDao articleDao;
    private final ApiService apiService;
    private final PagedList.Config myPagingConfig;
    private String postId;
    private int pageNo=1;


    @Inject
    CommentsRepository(EntityDao dao, ApiService service) {
        this.articleDao = dao;
        this.apiService = service;
        myPagingConfig= new PagedList.Config.Builder()
                .setPageSize(ApiConstants.COMMENTS_DB_FETCH_LIMIT)
                .build();
    }

    public LiveData<Resource<PagedList<Comments>>> loadCommentAssociatedWithPost(String postId){
        this.postId=postId;
        return loadCommentAssociatedWithPost(postId,pageNo);
    }

    private LiveData<Resource<PagedList<Comments>>> loadCommentAssociatedWithPost(String postId, int pageNo) {
        return new NetworkBoundResource<PagedList<Comments>, List<Comments>>() {

            @Override
            protected void saveCallResult(List<Comments> comments) {
                if (null != comments)
                    articleDao.saveComments(comments);
            }

            @SuppressWarnings("unchecked")
            @NonNull
            @Override
            protected LiveData<PagedList<Comments>> loadFromDb() {
                DataSource.Factory<Integer, Comments> myConcertDataSource =
                        articleDao.loadCommentsAssociatedWithPost(postId);

                return
                        new LivePagedListBuilder(myConcertDataSource,myPagingConfig)
                                .setBoundaryCallback(new CommentsCallBack())
                                .build();
            }

            @NonNull
            @Override
            protected Call<List<Comments>> createCall() {
                return apiService.fetchComments(postId, pageNo);
            }
        }.getAsLiveData();
    }

    private class CommentsCallBack extends PagedList.BoundaryCallback {
        @Override
        public void onZeroItemsLoaded() {
            super.onZeroItemsLoaded();
            loadCommentAssociatedWithPost(postId,pageNo);
        }

        @SuppressWarnings("unchecked")
        @Override
        public void onItemAtFrontLoaded(@NonNull Object itemAtFront) {
            super.onItemAtFrontLoaded(itemAtFront);
        }

        @SuppressWarnings("unchecked")
        @Override
        public void onItemAtEndLoaded(@NonNull Object itemAtEnd) {
            super.onItemAtEndLoaded(itemAtEnd);
            loadCommentAssociatedWithPost(postId,++pageNo);
        }
    }
}
