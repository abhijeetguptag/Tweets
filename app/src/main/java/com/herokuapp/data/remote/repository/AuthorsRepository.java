package com.herokuapp.data.remote.repository;


import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.herokuapp.data.entity.Author;
import com.herokuapp.data.local.EntityDao;
import com.herokuapp.data.remote.network.ApiConstants;
import com.herokuapp.data.remote.network.ApiService;
import com.herokuapp.data.Resource;
import com.herokuapp.data.remote.network.NetworkBoundResource;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;

public class AuthorsRepository {


    private final EntityDao articleDao;
    private final ApiService apiService;
    private final PagedList.Config myPagingConfig;
    private int pageNo=1;

    @Inject
    AuthorsRepository(EntityDao dao, ApiService service) {
        this.articleDao = dao;
        this.apiService = service;

        myPagingConfig= new PagedList.Config.Builder()
                .setPageSize(ApiConstants.AUTHOR_DB_FETCH_LIMIT)
                .setEnablePlaceholders(true)
                .build();
    }

    @SuppressWarnings("unchecked")
    private LiveData<PagedList<Author>>loadAuthorsFromDb() {
        DataSource.Factory<Integer, Author> authorDataSource =
                articleDao.fetchAuthors();

        return new LivePagedListBuilder(authorDataSource, myPagingConfig)
                .setBoundaryCallback(new AuthorCallBack())
                .build();
    }
    public LiveData<Resource<PagedList<Author>>> loadAuthors(){
       return loadAuthors(pageNo);
    }


    private LiveData<Resource<PagedList<Author>>> loadAuthors(int pageNo) {
        return new NetworkBoundResource<PagedList<Author>, List<Author>>() {

            @Override
            protected void saveCallResult(List<Author> item) {
                if (null != item)
                    articleDao.saveAuthorList(item);
            }

            @NonNull
            @Override
            protected LiveData<PagedList<Author>> loadFromDb() {
                return loadAuthorsFromDb();
            }

            @NonNull
            @Override
            protected Call<List<Author>> createCall() {
                return apiService.fetchAuthor(pageNo);
            }

        }.getAsLiveData();
    }

    @SuppressWarnings("unchecked")
    class AuthorCallBack extends PagedList.BoundaryCallback {
        @Override
        public void onZeroItemsLoaded() {
            super.onZeroItemsLoaded();
            loadAuthors(pageNo);
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
            loadAuthors(++pageNo);
        }
    }

}
