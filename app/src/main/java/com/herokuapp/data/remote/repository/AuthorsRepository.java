package com.herokuapp.data.remote.repository;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.herokuapp.data.entity.Author;
import com.herokuapp.data.local.EntityDao;
import com.herokuapp.data.remote.ApiConstants;
import com.herokuapp.data.remote.ApiService;
import com.herokuapp.data.remote.networkboundResources.NetworkBoundResource;
import com.herokuapp.data.remote.Resource;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;

public class AuthorsRepository {
    private final EntityDao articleDao;
    private final ApiService apiService;


    @Inject
    AuthorsRepository(EntityDao dao, ApiService service) {
        this.articleDao = dao;
        this.apiService = service;
    }


    public LiveData<Resource<List<Author>>> loadAuthors(int pageNo) {
        return new NetworkBoundResource<List<Author>, List<Author>>() {


            @Override
            protected void saveCallResult(List<Author> item) {
                if (null != item)
                    articleDao.saveAuthorList(item);
            }

            @NonNull
            @Override
            protected LiveData<List<Author>> loadFromDb() {
                return articleDao.fetchAuthors();
            }

            @NonNull
            @Override
            protected Call<List<Author>> createCall() {
                    return apiService.fetchAuthor(pageNo);
            }

        }.getAsLiveData();
    }
}
