package com.herokuapp.viewmodel;

import android.arch.lifecycle.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;

import com.herokuapp.data.entity.Author;
import com.herokuapp.data.remote.Resource;
import com.herokuapp.data.remote.repository.AuthorsRepository;

import javax.inject.Inject;

public class AuthorsViewModel extends ViewModel {

    private final AuthorsRepository authorsRepository;

    @Inject
    public AuthorsViewModel(AuthorsRepository authorsRepository) {
        this.authorsRepository = authorsRepository;
    }

    public LiveData<Resource<PagedList<Author>>> getAuthorList(int pageNo) {
        return authorsRepository.loadAuthors(pageNo);
    }

}
