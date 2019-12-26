package com.herokuapp.viewmodel;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.herokuapp.data.entity.Comments;
import com.herokuapp.data.remote.Resource;
import com.herokuapp.data.remote.repository.CommentsRepository;

import java.util.List;

import javax.inject.Inject;


public class CommentsViewModel extends ViewModel {
    private LiveData<Resource<List<Comments>>> commentsList;
    private final CommentsRepository commentsRepository;

    @Inject
    public CommentsViewModel(CommentsRepository commentsRepository) {
        this.commentsRepository=commentsRepository;
    }

    public LiveData<Resource<List<Comments>>> getComments(String postId, String page) {

        return commentsRepository.loadCommentAssociatedWithPost(postId, 1);


    }

}
