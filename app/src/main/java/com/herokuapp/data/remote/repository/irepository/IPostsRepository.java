package com.herokuapp.data.remote.repository.irepository;


import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;

import com.herokuapp.data.entity.Post;
import com.herokuapp.data.remote.Resource;

public interface IPostsRepository {
    LiveData<Resource<PagedList<Post>>> loadPostAssociatedWithAuthor(String authorId, int pageNo);
}
