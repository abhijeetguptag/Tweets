package com.herokuapp.data.remote;

import com.herokuapp.data.entity.Author;
import com.herokuapp.data.entity.Comments;
import com.herokuapp.data.entity.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("authors?_limit=" + ApiConstants.AUTHOR_NETWORK_FETCH_LIMIT)
    Call<List<Author>> fetchAuthor(@Query("_page") int pageNo);

    @GET("posts?_sort=date&_order=desc&_limit=" + ApiConstants.POSTS_NETWORK_FETCH_LIMIT)
    Call<List<Post>> fetchPosts(@Query("authorId") String authorId, @Query("_page") int pageNo);

    @GET("comments?_sort=date&_order=desc&_limit=" + ApiConstants.COMMENTS_NETWORK_FETCH_LIMIT)
    Call<List<Comments>> fetchComments(@Query("postId") String postId, @Query("_page") int pageNo);
}
