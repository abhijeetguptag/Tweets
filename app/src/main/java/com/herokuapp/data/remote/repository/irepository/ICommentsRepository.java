package com.herokuapp.data.remote.repository.irepository;


import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;

import com.herokuapp.data.entity.Comments;
import com.herokuapp.data.remote.Resource;

public interface ICommentsRepository {
    LiveData<Resource<PagedList<Comments>>> loadCommentAssociatedWithPost(String postId, int pageNo);
}
