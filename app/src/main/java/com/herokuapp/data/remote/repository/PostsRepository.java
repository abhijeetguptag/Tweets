package com.herokuapp.data.remote.repository;


import android.support.annotation.NonNull;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.herokuapp.data.entity.Comments;
import com.herokuapp.data.entity.Post;
import com.herokuapp.data.local.EntityDao;
import com.herokuapp.data.remote.ApiConstants;
import com.herokuapp.data.remote.ApiService;
import com.herokuapp.data.remote.Resource;
import com.herokuapp.data.remote.networkboundResources.NetworkBoundResource;
import com.herokuapp.data.remote.repository.irepository.IPostsRepository;

import javax.inject.Inject;

import retrofit2.Call;

public class PostsRepository implements IPostsRepository {

    private final EntityDao entityDao;
    private final ApiService apiService;

    @Inject
    PostsRepository(EntityDao dao, ApiService service) {
        this.entityDao = dao;
        this.apiService = service;
    }


    @Override
    public LiveData<Resource<PagedList<Post>>> loadPostAssociatedWithAuthor(String authorId, int pageNo) {
        return new NetworkBoundResource<PagedList<Post>, PagedList<Post>>(pageNo) {

            @Override
            protected void saveCallResult(PagedList<Post> posts) {
                if (null != posts)
                    entityDao.savePosts(posts);
            }

            @NonNull
            @Override
            protected LiveData<PagedList<Post>> loadFromDb() {

                DataSource.Factory<Integer, Comments> myConcertDataSource =
                        entityDao.loadPostsAssociatedWithAuthor(authorId);

                return
                        new LivePagedListBuilder(myConcertDataSource, ApiConstants.POSTS_FETCH_LIMIT).build();

//                return entityDao.loadPostsAssociatedWithAuthor(authorId);
            }

            @NonNull
            @Override
            protected Call<PagedList<Post>> createCall() {
                return apiService.fetchPosts(authorId, pageNo);
            }
        }.getAsLiveData();
    }

}
