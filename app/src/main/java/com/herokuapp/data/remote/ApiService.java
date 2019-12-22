package com.herokuapp.data.remote;

import com.herokuapp.data.remote.response.AuthorsResponse;
import com.herokuapp.data.remote.response.PostsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @GET("authors?_limit=" + ApiConstants.USER_FETCH_LIMIT + "&_page=1")
    Call<AuthorsResponse> fetchAuthor(@Query("_page") int pageNo);

    @GET("posts?authorId={authorId}&_sort=date&_order=desc&_page={pageNo}&_limit=" + ApiConstants.POSTS_FETCH_LIMIT)
    Call<PostsResponse> fetchPosts(@Path("authorId") String authorId,@Path("_page") int pageNo);

    @GET("posts?postId={authorId}&_sort=date&_order=desc&_page=1&_limit=" + ApiConstants.POSTS_FETCH_LIMIT)
    Call<PostsResponse> fetchComments(@Path("postId") String postId, @Path("_page") int pageNo);
}
