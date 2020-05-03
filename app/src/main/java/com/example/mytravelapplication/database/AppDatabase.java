package com.example.mytravelapplication.database;

import com.example.mytravelapplication.http.Blog;

@Database(entities = {Blog.class},version = 1)
public abstract class AppDatabase extends RoomDatabase{
    public abstract BlogDAO blogDao();
}
