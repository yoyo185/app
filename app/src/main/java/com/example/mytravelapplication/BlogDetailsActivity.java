package com.example.mytravelapplication;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.example.mytravelapplication.http.Blog;
import com.example.mytravelapplication.http.BlogArticlesCallback;
import com.example.mytravelapplication.http.BlogHttpClient;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;


public class BlogDetailsActivity extends AppCompatActivity{
    private TextView textTitle;
    private TextView textDate;
    private TextView textAuthor;
    private TextView textRating;
    private TextView textDescription;
    private TextView textViews;
    private RatingBar ratingBar;
    private ImageView imageAvatar;
    private ImageView imageMain;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(@Nullable Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_main);
//        setContentView(R.layout.activity_blog_details);
//
//        imageMain = findViewById(R.id.imageMain);
//        imageAvatar = findViewById(R.id.imageAvatar);
//
//
//        ImageView imageBack = findViewById(R.id.imageBack);
//        imageBack.setOnClickListener(v -> finish());
//
//        textTitle = findViewById(R.id.textTitle);
//        textDate = findViewById(R.id.textDate);
//        textAuthor = findViewById(R.id.textAuthor);
//        textRating = findViewById(R.id.textRating);
//        textViews = findViewById(R.id.textViews);
//        textDescription = findViewById(R.id.textDescription);
//        ratingBar = findViewById(R.id.ratingBar);
//
//        progressBar = findViewById(R.id.progressBar);
        // start data loading
        loadData();
    }

    private void loadData() {
        BlogHttpClient.INSTANCE.loadBlogArticles(new BlogArticlesCallback(){
            @Override
            public void onSuccess(List<Blog> blogList){
                runOnUiThread(() -> showData(blogList.get(0)));

            }
            @Override
            public void onError(){
                runOnUiThread(() -> showErrorSnackbar());
            }
        });
    }

    private void showErrorSnackbar() {
        View rootView = findViewById(android.R.id.content);
        Snackbar snackbar = Snackbar.make(rootView,"Error during loading blog articles", Snackbar.LENGTH_INDEFINITE);
        snackbar.setActionTextColor(getResources().getColor(R.color.orange500));
        snackbar.setAction("Retry", v -> {
            loadData();
            snackbar.dismiss();
        });
        snackbar.show();
    }

    private void showData(Blog blog){
        progressBar.setVisibility(View.GONE);
        textTitle.setText(blog.getTitle());
        textDate.setText(blog.getDate());
        textAuthor.setText(blog.getAuthor().getName());
        textRating.setText(String.valueOf(blog.getRating()));
        textViews.setText(String.format("(%d views)",blog.getViews()));
        textDescription.setText(Html.fromHtml(blog.getDescription()));
        ratingBar.setRating(blog.getRating());

        Glide.with(this)
                .load(blog.getImage())
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageMain);
        Glide.with(this)
                .load(blog.getAuthor().getAvatar())
                .transform(new CircleCrop())
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageAvatar);
    }
}
