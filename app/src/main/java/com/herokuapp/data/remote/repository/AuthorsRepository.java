package com.herokuapp.data.remote.repository;

import android.support.annotation.NonNull;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.herokuapp.data.entity.Author;
import com.herokuapp.data.local.EntityDao;
import com.herokuapp.data.remote.ApiConstants;
import com.herokuapp.data.remote.ApiService;
import com.herokuapp.data.remote.Resource;
import com.herokuapp.data.remote.networkboundResources.NetworkBoundResource;
import com.herokuapp.data.remote.repository.irepository.IAuthorsRepository;

import javax.inject.Inject;

import retrofit2.Call;

public class AuthorsRepository implements IAuthorsRepository {
    private final EntityDao articleDao;
    private final ApiService apiService;


    @Inject
    AuthorsRepository(EntityDao dao, ApiService service) {
        this.articleDao = dao;
        this.apiService = service;
    }


    @Override
    public LiveData<Resource<PagedList<Author>>> loadAuthors(final int pageNo) {
        return new NetworkBoundResource<PagedList<Author>, PagedList<Author>>(pageNo) {

            @Override
            protected void saveCallResult(PagedList<Author> item) {
                if (null != item)
                    articleDao.saveAuthorList(item);
            }

            @NonNull
            @Override
            protected LiveData<PagedList<Author>> loadFromDb() {
                DataSource.Factory<Integer, Author> myConcertDataSource =
                        articleDao.fetchAuthors();

                return
                        new LivePagedListBuilder(myConcertDataSource, ApiConstants.AUTHOR_FETCH_LIMIT).build();

//                return articleDao.fetchAuthors();
            }

            @NonNull
            @Override
            protected Call<PagedList<Author>> createCall() {
                return apiService.fetchAuthor(pageNo);
            }

        }.getAsLiveData();
    }

}
