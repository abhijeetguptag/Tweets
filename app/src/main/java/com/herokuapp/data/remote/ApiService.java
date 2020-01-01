package com.herokuapp.data.remote;

import androidx.paging.PagedList;

import com.herokuapp.data.entity.Author;
import com.herokuapp.data.entity.Comments;
import com.herokuapp.data.entity.Post;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("authors?_limit=" + ApiConstants.AUTHOR_FETCH_LIMIT)
    Call<PagedList<Author>> fetchAuthor(@Query("_page") int pageNo);

    @GET("posts?_sort=date&_order=desc&_limit=" + ApiConstants.POSTS_FETCH_LIMIT)
    Call<PagedList<Post>> fetchPosts(@Query("authorId") String authorId, @Query("_page") int pageNo);

    @GET("comments?_sort=date&_order=desc&_limit=" + ApiConstants.COMMENTS_FETCH_LIMIT)
    Call<PagedList<Comments>> fetchComments(@Query("postId") String postId, @Query("_page") int pageNo);
}
