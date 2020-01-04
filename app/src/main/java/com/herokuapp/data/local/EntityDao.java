package com.herokuapp.data.local;

import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.herokuapp.data.entity.Author;
import com.herokuapp.data.entity.Comments;
import com.herokuapp.data.entity.Post;

import java.util.List;

@Dao
public interface EntityDao {

    @Query("SELECT * FROM authors ORDER BY id ASC")
    DataSource.Factory<Integer, Author> fetchAuthors();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveAuthorList(List<Author> authors);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveComments(List<Comments> comments);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void savePosts(List<Post> post);

    @Query("SELECT * FROM posts where authorId Like :authorId ORDER BY date DESC")
    DataSource.Factory<Integer, Post> loadPostsAssociatedWithAuthor(String authorId);

    @Query("SELECT * FROM comments where postId Like :postId ORDER BY date DESC ")
    DataSource.Factory<Integer, Comments> loadCommentsAssociatedWithPost(String postId);
}
