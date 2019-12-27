package com.herokuapp.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.herokuapp.data.entity.Author;
import com.herokuapp.data.remote.Resource;
import com.herokuapp.data.remote.repository.AuthorsRepository;

import java.util.List;

import javax.inject.Inject;

public class AuthorsViewModel extends ViewModel {

    private final AuthorsRepository authorsRepository;

    @Inject
    public AuthorsViewModel(AuthorsRepository authorsRepository) {
        this.authorsRepository = authorsRepository;
    }

    public LiveData<Resource<List<Author>>> getAuthorList(int totalCount) {
        return authorsRepository.loadAuthors(totalCount);
    }

}
