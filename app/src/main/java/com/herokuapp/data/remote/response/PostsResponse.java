package com.herokuapp.data.remote.response;

import com.herokuapp.data.entity.Post;

import java.util.List;

public class PostsResponse {
    private List<Post> posts;

    public List<Post> getPosts() {
        return posts;
    }

    @SuppressWarnings("unused")
    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}
