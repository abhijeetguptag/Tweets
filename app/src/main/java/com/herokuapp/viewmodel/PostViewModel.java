package com.herokuapp.viewmodel;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;

import com.herokuapp.data.entity.Post;
import com.herokuapp.data.Resource;
import com.herokuapp.data.remote.repository.PostsRepository;

import javax.inject.Inject;

public class PostViewModel extends ViewModel {

    private final PostsRepository postsRepository;

    @Inject
    public PostViewModel(PostsRepository postsRepository) {
        this.postsRepository = postsRepository;
    }

    public LiveData<Resource<PagedList<Post>>> getPost(String authorId) {
        return postsRepository.loadPostAssociatedWithAuthor(authorId);
    }
}
