package com.herokuapp.data.remote.repository;


import android.support.annotation.NonNull;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.herokuapp.data.entity.Comments;
import com.herokuapp.data.local.EntityDao;
import com.herokuapp.data.remote.ApiConstants;
import com.herokuapp.data.remote.ApiService;
import com.herokuapp.data.remote.Resource;
import com.herokuapp.data.remote.networkboundResources.NetworkBoundResource;
import com.herokuapp.data.remote.repository.irepository.ICommentsRepository;

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
    public LiveData<Resource<PagedList<Comments>>> loadCommentAssociatedWithPost(String postId, int pageNo) {
        return new NetworkBoundResource<PagedList<Comments>, PagedList<Comments>>(pageNo) {

            @Override
            protected void saveCallResult(PagedList<Comments> comments) {
                if (null != comments)
                    articleDao.saveComments(comments);
            }

            @NonNull
            @Override
            protected LiveData<PagedList<Comments>> loadFromDb() {

                DataSource.Factory<Integer, Comments> myConcertDataSource =
                        articleDao.loadCommentsAssociatedWithPost(postId);

                return
                        new LivePagedListBuilder(myConcertDataSource, ApiConstants.COMMENTS_FETCH_LIMIT).build();

//                return articleDao.loadCommentsAssociatedWithPost(postId);
            }

            @NonNull
            @Override
            protected Call<PagedList<Comments>> createCall() {
                return apiService.fetchComments(postId, pageNo);
            }
        }.getAsLiveData();
    }
}
