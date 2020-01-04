package com.herokuapp.data.local;


import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.herokuapp.data.entity.AddressConverter;
import com.herokuapp.data.entity.Author;
import com.herokuapp.data.entity.Comments;
import com.herokuapp.data.entity.Post;

@Database(entities = {Author.class, Comments.class, Post.class}, version = 1, exportSchema = false)
@TypeConverters({AddressConverter.class})
public abstract class HeroKuDataBase extends RoomDatabase {
    public abstract EntityDao entityDao();
}
