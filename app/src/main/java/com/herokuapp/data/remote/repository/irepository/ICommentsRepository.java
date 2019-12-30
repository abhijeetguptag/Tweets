package com.herokuapp.data.remote.repository.irepository;

import android.arch.lifecycle.LiveData;

import com.herokuapp.data.entity.Comments;
import com.herokuapp.data.remote.Resource;

import java.util.List;

public interface ICommentsRepository {
    public LiveData<Resource<List<Comments>>> loadCommentAssociatedWithPost(String postId, int howfarback);
}
