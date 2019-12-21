package com.herokuapp.data.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.herokuapp.data.entity.Author;
import com.herokuapp.data.entity.Comments;
import com.herokuapp.data.entity.Post;
import com.herokuapp.data.local.dao.EntityDao;

@Database(entities = {Author.class, Comments.class, Post.class}, version = 1)
public abstract class HeroKuDataBase extends RoomDatabase {
    public abstract EntityDao entityDao();
}
