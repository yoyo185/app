package com.example.mytravelapplication.repository;

import android.content.Context;

import com.example.mytravelapplication.database.AppDatabase;
import com.example.mytravelapplication.database.BlogDAO;
import com.example.mytravelapplication.database.DatabaseProvider;
import com.example.mytravelapplication.http.Blog;
import com.example.mytravelapplication.http.BlogHttpClient;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class BlogRepository {
    private BlogHttpClient httpClient;
    private AppDatabase database;
    private Executor executor;

    public BlogRepository(Context context){
        httpClient = BlogHttpClient.INSTANCE;
        database = DatabaseProvider.getInstance(context.getApplicationContext());
        executor = Executors.newSingleThreadExecutor();
    }

    public void loadDataFromDatabase(DataFromDatabaseCallback callback){
        executor.execute(() -> callback.onSuccess(database.blogDao().getAll()));
    }
    public void loadDataFromNetwork(DataFromNetworkCallback callback){
        executor.execute(()->{
            List<Blog> blogList = httpClient.loadBlogArticles();
            if (blogList == null){
                callback.onError();
            } else {
                BlogDAO blogDAO = database.blogDao();
                blogDAO.deleteAll();
                blogDAO.insertAll(blogList);
                callback.onSuccess(blogList);
            }
        });
    }
}
