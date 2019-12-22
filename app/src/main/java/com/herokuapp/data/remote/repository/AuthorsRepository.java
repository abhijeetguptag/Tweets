package com.herokuapp.data.remote.repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.herokuapp.data.entity.Author;
import com.herokuapp.data.local.dao.EntityDao;
import com.herokuapp.data.remote.ApiService;
import com.herokuapp.data.remote.NetworkBoundResource;
import com.herokuapp.data.remote.Resource;
import com.herokuapp.data.remote.response.AuthorsResponse;

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


    public LiveData<Resource<List<Author>>> loadAuthors(int howfarback) {
        return new NetworkBoundResource<List<Author>, AuthorsResponse>() {

            @Override
            protected void saveCallResult(AuthorsResponse item) {
                if (null != item)
                    articleDao.saveAuthorList(item.getAuthors());
            }

            @NonNull
            @Override
            protected LiveData<List<Author>> loadFromDb() {
                return articleDao.fetchAuthors();
            }

            @NonNull
            @Override
            protected Call<AuthorsResponse> createCall() {
                return apiService.fetchAuthor(howfarback);
            }
        }.getAsLiveData();
    }


//        @SuppressLint("CheckResult")
//        public void loadAuthorDetail(String url, AuthorClickListener responseListener) {
//            Author author = new Author();
//            Observable.fromCallable(() -> {
//                author.setId();
//                return false;
//            }).subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribe(result -> responseListener.onSuccess(author),
//                            (error -> responseListener.onFailure(error.getMessage())));
//
//        }

}
