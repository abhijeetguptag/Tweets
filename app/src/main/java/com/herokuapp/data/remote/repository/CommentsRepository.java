package com.herokuapp.data.remote.repository;


import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.herokuapp.data.entity.Comments;
import com.herokuapp.data.local.EntityDao;
import com.herokuapp.data.remote.ApiService;
import com.herokuapp.data.remote.NetworkBoundResource;
import com.herokuapp.data.remote.Resource;
import com.herokuapp.data.remote.response.PostsResponse;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;

public class CommentsRepository {
    private final EntityDao articleDao;
    private final ApiService apiService;

    @Inject
    CommentsRepository(EntityDao dao, ApiService service) {
        this.articleDao = dao;
        this.apiService = service;
    }


    public LiveData<Resource<List<Comments>>> loadCommentAssociatedWithPost(String authorId, int howfarback) {
        return new NetworkBoundResource<List<Comments>, PostsResponse>() {

            @Override
            protected void saveCallResult(PostsResponse posts) {
                if (null != posts)
                    articleDao.savePosts(posts.getPosts());
            }

            @NonNull
            @Override
            protected LiveData<List<Comments>> loadFromDb() {
                return articleDao.fetchComments();
            }

            @NonNull
            @Override
            protected Call<PostsResponse> createCall() {
                return apiService.fetchPosts(authorId, howfarback);
            }
        }.getAsLiveData();
    }
}
