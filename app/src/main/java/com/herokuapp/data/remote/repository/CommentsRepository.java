package com.herokuapp.data.remote.repository;


import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.herokuapp.data.entity.Comments;
import com.herokuapp.data.local.EntityDao;
import com.herokuapp.data.remote.ApiService;
import com.herokuapp.data.remote.Resource;
import com.herokuapp.data.remote.networkboundResources.NetworkBoundResource;
import com.herokuapp.data.remote.repository.irepository.ICommentsRepository;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;

public class CommentsRepository implements ICommentsRepository {
    private final EntityDao articleDao;
    private final ApiService apiService;

    @Inject
    CommentsRepository(EntityDao dao, ApiService service) {
        this.articleDao = dao;
        this.apiService = service;
    }


    @Override
    public LiveData<Resource<List<Comments>>> loadCommentAssociatedWithPost(String postId, int pageNo) {
        return new NetworkBoundResource<List<Comments>, List<Comments>>(pageNo) {

            @Override
            protected void saveCallResult(List<Comments> comments) {
                if (null != comments)
                    articleDao.saveComments(comments);
            }

            @NonNull
            @Override
            protected LiveData<List<Comments>> loadFromDb() {
                return articleDao.loadCommentsAssociatedWithPost(postId);
            }

            @NonNull
            @Override
            protected Call<List<Comments>> createCall() {
                return apiService.fetchComments(postId, pageNo);
            }
        }.getAsLiveData();
    }
}
