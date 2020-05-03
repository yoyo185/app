package com.example.mytravelapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
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


public class BlogDetailsActivity extends AppCompatActivity{
    private static final String EXTRAS_BLOG = "EXTRAS_BLOG";
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

        showData(getIntent()
                    .getExtras()
                    .getParcelable(EXTRAS_BLOG));
    }

//    private void loadData() {
//        BlogHttpClient.INSTANCE.loadBlogArticles(new BlogArticlesCallback(){
//            @Override
//            public void onSuccess(List<Blog> blogList){
//                runOnUiThread(() -> showData(blogList.get(0)));
//
//            }
//            @Override
//            public void onError(){
//                runOnUiThread(() -> showErrorSnackbar());
//            }
//        });
//    }
//
//    private void showErrorSnackbar() {
//        View rootView = findViewById(android.R.id.content);
//        Snackbar snackbar = Snackbar.make(rootView,"Error during loading blog articles", Snackbar.LENGTH_INDEFINITE);
//        snackbar.setActionTextColor(getResources().getColor(R.color.orange500));
//        snackbar.setAction("Retry", v -> {
//            loadData();
//            snackbar.dismiss();
//        });
//        snackbar.show();
//    }
    public static void startBlogDetailsActivity(Activity activity, Blog blog) {
        Intent intent = new Intent(activity, BlogDetailsActivity.class);
        intent.putExtra(EXTRAS_BLOG, blog);
        activity.startActivity(intent);
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
