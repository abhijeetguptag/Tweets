package com.herokuapp.viewmodel;



import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.herokuapp.data.entity.Post;
import com.herokuapp.data.remote.Resource;
import com.herokuapp.data.remote.repository.PostsRepository;

import java.util.List;

import javax.inject.Inject;

public class PostViewModel extends ViewModel {

    private final PostsRepository postsRepository;

    @Inject
    public PostViewModel(PostsRepository postsRepository) {
        this.postsRepository = postsRepository;
    }

    public LiveData<Resource<List<Post>>> getPost(String authorId, int pageNo) {
        return postsRepository.loadPostAssociatedWithAuthor(authorId, pageNo);
    }
}
