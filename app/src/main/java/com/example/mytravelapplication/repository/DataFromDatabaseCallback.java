package com.example.mytravelapplication.repository;

import com.example.mytravelapplication.http.Blog;

import java.util.List;

public interface DataFromDatabaseCallback {
    void onSuccess(List<Blog> blogList);
}
