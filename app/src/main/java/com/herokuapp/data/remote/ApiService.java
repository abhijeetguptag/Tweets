package com.herokuapp.data.remote;

import com.herokuapp.data.entity.Author;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {

    @GET("authors?_limit=40&_page={page}")
    Call<Author> fetchAuthor(@Path("page") int page);
}
