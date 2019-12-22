package com.herokuapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.herokuapp.data.entity.Post;
import com.herokuapp.data.remote.Resource;
import com.herokuapp.data.remote.repository.PostsRepository;

import java.util.List;

import javax.inject.Inject;

public class PostViewModel extends ViewModel {

    private final LiveData<Resource<List<Post>>> fetchPostOfAuthor;

    @Inject
    public PostViewModel(PostsRepository postsRepository, String authorId, int pageNo) {
        fetchPostOfAuthor = postsRepository.loadPostAssociatedWithAuthor(authorId, pageNo);
    }

    public LiveData<Resource<List<Post>>> getPost() {
        return fetchPostOfAuthor;
    }
}
