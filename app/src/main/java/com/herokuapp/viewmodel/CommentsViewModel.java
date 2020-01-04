package com.herokuapp.viewmodel;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;

import com.herokuapp.data.entity.Comments;
import com.herokuapp.data.remote.Resource;
import com.herokuapp.data.remote.repository.CommentsRepository;

import javax.inject.Inject;


public class CommentsViewModel extends ViewModel {
    private final CommentsRepository commentsRepository;

    @Inject
    public CommentsViewModel(CommentsRepository commentsRepository) {
        this.commentsRepository = commentsRepository;
    }

    public LiveData<Resource<PagedList<Comments>>> getComments(String postId) {
        return commentsRepository.loadCommentAssociatedWithPost(postId);
    }

}
