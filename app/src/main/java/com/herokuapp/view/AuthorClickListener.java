package com.herokuapp.view;

import com.herokuapp.data.entity.Author;

public interface AuthorClickListener {
    void onSuccess(Author data);

    void onFailure(String message);
}
