package com.herokuapp.data.remote.repository;


import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.herokuapp.data.entity.Post;
import com.herokuapp.data.local.EntityDao;
import com.herokuapp.data.remote.ApiService;
import com.herokuapp.data.remote.NetworkBoundResource;
import com.herokuapp.data.remote.Resource;
import com.herokuapp.data.remote.response.PostsResponse;

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


    public LiveData<Resource<List<Post>>> loadPostAssociatedWithAuthor(String authorId, int howfarback) {
        return new NetworkBoundResource<List<Post>, PostsResponse>() {

            @Override
            protected void saveCallResult(PostsResponse posts) {
                if (null != posts)
                    articleDao.savePosts(posts.getPosts());
            }

            @NonNull
            @Override
            protected LiveData<List<Post>> loadFromDb() {
                return articleDao.loadPosts();
            }

            @NonNull
            @Override
            protected Call<PostsResponse> createCall() {
                return apiService.fetchPosts(authorId, howfarback);
            }
        }.getAsLiveData();
    }

}
