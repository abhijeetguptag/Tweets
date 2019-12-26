package com.herokuapp.data.local;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.herokuapp.data.entity.Author;
import com.herokuapp.data.entity.Comments;
import com.herokuapp.data.entity.Post;

import java.util.List;

@Dao
public interface EntityDao {

    @Query("SELECT * FROM authors")
    LiveData<List<Author>> fetchAuthors();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveAuthorList(List<Author> authors);

    @Query("SELECT * FROM comments ")
    LiveData<List<Comments>> fetchComments();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveComments(List<Comments> comments);

    @Query("SELECT * FROM posts")
    LiveData<List<Post>> loadPosts();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void savePosts(List<Post> post);
}
