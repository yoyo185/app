package com.example.mytravelapplication.repository;

import com.example.mytravelapplication.http.Blog;

import java.util.List;

public interface DataFromNetworkCallback {
    void onSuccess(List<Blog> blogList);
    void onError();
}
