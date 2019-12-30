package com.herokuapp.data.remote.repository.irepository;

import android.arch.lifecycle.LiveData;

import com.herokuapp.data.entity.Post;
import com.herokuapp.data.remote.Resource;

import java.util.List;

public interface IPostsRepository {
    LiveData<Resource<List<Post>>> loadPostAssociatedWithAuthor(String authorId, int pageNo);
}
