package com.herokuapp.viewmodel;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.herokuapp.data.entity.Comments;
import com.herokuapp.data.remote.Resource;
import com.herokuapp.data.remote.repository.CommentsRepository;

import java.util.List;

import javax.inject.Inject;


public class CommentsViewModel extends ViewModel {
    private final LiveData<Resource<List<Comments>>> commentsList;

    @Inject
    public CommentsViewModel(CommentsRepository commentsRepository) {
        commentsList = commentsRepository.loadCommentAssociatedWithPost("authorId", 5);
    }

    public LiveData<Resource<List<Comments>>> getComments() {
        return commentsList;
    }

}
