package com.herokuapp.data.remote.repository;


import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.herokuapp.data.entity.Post;
import com.herokuapp.data.local.EntityDao;
import com.herokuapp.data.remote.network.ApiConstants;
import com.herokuapp.data.remote.network.ApiService;
import com.herokuapp.data.Resource;
import com.herokuapp.data.remote.network.NetworkBoundResource;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;

public class PostsRepository {

    private final EntityDao entityDao;
    private final ApiService apiService;
    private final PagedList.Config myPagingConfig;
    private String authorId;
    private int pageNo=1;

    @Inject
    PostsRepository(EntityDao dao, ApiService service) {
        this.entityDao = dao;
        this.apiService = service;
        myPagingConfig= new PagedList.Config.Builder()
                .setPageSize(ApiConstants.POSTS_DB_LIMIT)
                .build();
    }

    public LiveData<Resource<PagedList<Post>>> loadPostAssociatedWithAuthor(String authorId){
        this.authorId=authorId;
        return loadPostAssociatedWithAuthor(authorId,pageNo);
    }


    private LiveData<Resource<PagedList<Post>>> loadPostAssociatedWithAuthor(String authorId, int pageNo) {
        return new NetworkBoundResource<PagedList<Post>, List<Post>>() {

            @Override
            protected void saveCallResult(List<Post> posts) {
                if (null != posts)
                    entityDao.savePosts(posts);
            }

            @SuppressWarnings("unchecked")
            @NonNull
            @Override
            protected LiveData<PagedList<Post>> loadFromDb() {
                DataSource.Factory<Integer, Post> myConcertDataSource =
                        entityDao.loadPostsAssociatedWithAuthor(authorId);

                return
                        new LivePagedListBuilder(myConcertDataSource, myPagingConfig)
                                .setBoundaryCallback(new PostsCallBack())
                                .build();

            }

            @NonNull
            @Override
            protected Call<List<Post>> createCall() {
                return apiService.fetchPosts(authorId, pageNo);
            }
        }.getAsLiveData();
    }

    private class PostsCallBack extends PagedList.BoundaryCallback {
        @Override
        public void onZeroItemsLoaded() {
            super.onZeroItemsLoaded();
            loadPostAssociatedWithAuthor(authorId,pageNo);
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
            loadPostAssociatedWithAuthor(authorId,++pageNo);
        }
    }

}
