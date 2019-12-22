package com.herokuapp.data.remote;

import com.herokuapp.data.remote.response.AuthorsResponse;
import com.herokuapp.data.remote.response.PostsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @GET("authors?_limit=" + ApiConstants.USER_FETCH_LIMIT + "&_page={pageNo}")
    Call<AuthorsResponse> fetchAuthor(@Query("_page") int pageNo);

    @GET("posts?authorId={authorId}&_sort=date&_order=desc&_page={pageNo}&_limit=" + ApiConstants.POSTS_FETCH_LIMIT)
    Call<PostsResponse> fetchPosts(@Query("authorId") String authorId,@Query("_page") int pageNo);

    @GET("posts?postId={postId}&_sort=date&_order=desc&_page={pageNo}&_limit=" + ApiConstants.POSTS_FETCH_LIMIT)
    Call<PostsResponse> fetchComments(@Query("postId") String postId, @Query("_page") int pageNo);
}
