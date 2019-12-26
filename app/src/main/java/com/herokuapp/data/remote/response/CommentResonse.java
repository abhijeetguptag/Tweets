package com.herokuapp.data.remote.response;

import com.herokuapp.data.entity.Comments;

import java.util.List;

public class CommentResonse {
    private List<Comments> comments;

    public List<Comments> getComments() {
        return comments;
    }

    @SuppressWarnings("unused")
    public void setComments(List<Comments> comments) {
        this.comments = comments;
    }
}
