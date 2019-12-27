package com.herokuapp.data.remote.repository;


import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.herokuapp.data.entity.Post;
import com.herokuapp.data.local.EntityDao;
import com.herokuapp.data.remote.ApiService;
import com.herokuapp.data.remote.networkboundResources.NetworkBoundResource;
import com.herokuapp.data.remote.Resource;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;

public class PostsRepository {

    private final EntityDao articleDao;
    private final ApiService apiService;

    @Inject
    PostsRepository(EntityDao dao, ApiService service) {
        this.articleDao = dao;
        this.apiService = service;
    }


    public LiveData<Resource<List<Post>>> loadPostAssociatedWithAuthor(String authorId, int pageNo) {
        return new NetworkBoundResource<List<Post>, List<Post>>() {

            @Override
            protected void saveCallResult(List<Post> posts) {
                if (null != posts)
                    articleDao.savePosts(posts);
            }

            @NonNull
            @Override
            protected LiveData<List<Post>> loadFromDb() {
                return articleDao.loadPostsAssociatedWithAuthor(authorId);
            }

            @NonNull
            @Override
            protected Call<List<Post>> createCall() {
                return apiService.fetchPosts(authorId, pageNo);
            }
        }.getAsLiveData();
    }

}
