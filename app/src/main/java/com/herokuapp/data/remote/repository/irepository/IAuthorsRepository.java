package com.herokuapp.data.remote.repository.irepository;

import android.arch.lifecycle.LiveData;

import com.herokuapp.data.entity.Author;
import com.herokuapp.data.remote.Resource;

import java.util.List;

public interface IAuthorsRepository {
     LiveData<Resource<List<Author>>> loadAuthors(int pageNo);
}
