package com.herokuapp.data.remote.repository.irepository;


import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;

import com.herokuapp.data.entity.Author;
import com.herokuapp.data.remote.Resource;


public interface IAuthorsRepository {
    LiveData<Resource<PagedList<Author>>> loadAuthors(int pageNo);
}
