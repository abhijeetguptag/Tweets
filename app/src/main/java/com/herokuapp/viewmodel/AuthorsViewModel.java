package com.herokuapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;

import com.herokuapp.data.entity.Author;
import com.herokuapp.data.remote.Resource;
import com.herokuapp.data.remote.repository.AuthorsRepository;

import javax.inject.Inject;

public class AuthorsViewModel extends ViewModel {

    private final AuthorsRepository authorsRepository;
    private LiveData<Resource<PagedList<Author>>> resourceLiveData;

    @Inject
    public AuthorsViewModel(AuthorsRepository authorsRepository) {
        this.authorsRepository = authorsRepository;
    }

    public LiveData<Resource<PagedList<Author>>> getAuthorList() {
        return authorsRepository.loadAuthors();
    }

}
