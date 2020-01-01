package com.herokuapp.data.local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import androidx.paging.DataSource;
import androidx.paging.PagedList;

import com.herokuapp.data.entity.Author;
import com.herokuapp.data.entity.Comments;
import com.herokuapp.data.entity.Post;

@Dao
public interface EntityDao {

    @Query("SELECT * FROM authors ORDER BY id ASC")
    DataSource.Factory<Integer, Author> fetchAuthors();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveAuthorList(PagedList<Author> authors);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveComments(PagedList<Comments> comments);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void savePosts(PagedList<Post> post);

    @Query("SELECT * FROM posts where authorId Like :authorId ORDER BY date DESC")
    DataSource.Factory<Integer, Comments> loadPostsAssociatedWithAuthor(String authorId);

    @Query("SELECT * FROM comments where postId Like :postId ORDER BY date DESC ")
    DataSource.Factory<Integer, Comments> loadCommentsAssociatedWithPost(String postId);
}
