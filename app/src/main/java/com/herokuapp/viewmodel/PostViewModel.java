package com.herokuapp.viewmodel;



import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.herokuapp.data.entity.Post;
import com.herokuapp.data.remote.Resource;
import com.herokuapp.data.remote.repository.PostsRepository;

import java.util.List;

import javax.inject.Inject;

public class PostViewModel extends ViewModel {

    private LiveData<Resource<List<Post>>> fetchPostOfAuthor;
    private final PostsRepository postsRepository;

    @Inject
    public PostViewModel(PostsRepository postsRepository) {
        this.postsRepository = postsRepository;
    }

    public LiveData<Resource<List<Post>>> getPost(String authorId) {

        this.fetchPostOfAuthor = postsRepository.loadPostAssociatedWithAuthor(authorId, 1);

        return fetchPostOfAuthor;
    }
}
