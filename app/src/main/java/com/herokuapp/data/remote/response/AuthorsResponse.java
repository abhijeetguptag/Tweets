package com.herokuapp.data.remote.response;

import com.herokuapp.data.entity.Author;

import java.util.List;

public class AuthorsResponse {


    private List<Author> authors;

    public List<Author> getAuthors() {
        return authors;
    }

    @SuppressWarnings("unused")
    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }
}
