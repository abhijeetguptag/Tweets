package com.herokuapp.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.herokuapp.data.entity.Author;
import com.herokuapp.data.remote.Resource;
import com.herokuapp.data.remote.repository.AuthorsRepository;

import java.util.List;

import javax.inject.Inject;

public class AuthorsViewModel extends ViewModel {

    private final LiveData<Resource<List<Author>>> authorList;

    @Inject
    public AuthorsViewModel(AuthorsRepository authorsRepository) {
        authorList = authorsRepository.loadAuthors(1);
    }

    public LiveData<Resource<List<Author>>> getAuthorList() {
        return authorList;
    }

}
