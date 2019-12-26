package com.herokuapp.data.remote.repository;


import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.herokuapp.data.entity.Comments;
import com.herokuapp.data.local.EntityDao;
import com.herokuapp.data.remote.ApiService;
import com.herokuapp.data.remote.NetworkBoundResource;
import com.herokuapp.data.remote.Resource;

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


    public LiveData<Resource<List<Comments>>> loadCommentAssociatedWithPost(String postId, int howfarback) {
        return new NetworkBoundResource<List<Comments>, List<Comments>>() {

            @Override
            protected void saveCallResult(List<Comments> comments) {
                if (null != comments)
                    articleDao.saveComments(comments);
            }

            @NonNull
            @Override
            protected LiveData<List<Comments>> loadFromDb() {
                return articleDao.fetchComments();
            }

            @NonNull
            @Override
            protected Call <List<Comments> >createCall() {
                return apiService.fetchComments(postId, howfarback);
            }


            @Override
            protected void nextPageURL(String nextPageURL) {

            }

            @Override
            protected void previousPageURL(String previousPageURL) {

            }
        }.getAsLiveData();
    }
}
