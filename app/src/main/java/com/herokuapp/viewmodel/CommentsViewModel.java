package com.herokuapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.herokuapp.data.entity.Comments;
import com.herokuapp.data.remote.Resource;
import com.herokuapp.data.remote.repository.CommentsRepository;

import java.util.List;

import javax.inject.Inject;

public class CommentsViewModel extends ViewModel {
    private final LiveData<Resource<List<Comments>>> commentsList;

    @Inject
    public CommentsViewModel(CommentsRepository commentsRepository, String authorId, int pageNo) {
        commentsList = commentsRepository.loadCommentAssociatedWithPost(authorId, pageNo);
    }

    public LiveData<Resource<List<Comments>>> getComments() {
        return commentsList;
    }


}
