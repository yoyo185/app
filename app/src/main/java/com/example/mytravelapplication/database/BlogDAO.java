package com.example.mytravelapplication.database;

import com.example.mytravelapplication.http.Blog;

import java.util.List;
@Dao
public interface BlogDAO {
    @Query("SELECT * FROM blog")
    List<Blog> getAll();
    @Insert
    void insertAll(List<Blog> blogList);
    @Query("DELETE FROM blog")
    void deleteAll();
}
