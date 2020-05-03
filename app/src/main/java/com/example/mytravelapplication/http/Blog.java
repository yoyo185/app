package com.example.mytravelapplication.http;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

@Entity
public class Blog {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM dd, yyyy");
    @PrimaryKey
    private String id;
    @Embedded
    private Author author;
    private String title;
    private String date;
    private String image;
    private String description;
    private int views;
    private float rating;

    public Blog(int id, Author author, String title, String date, String image, String description,
                int views, float rating){
        this.id = id;
        this.author = author;
        this.title = title;
        this.date = date;
        this.image = image;
        this.description = description;
        this.views = views;
        this.rating = rating;
    }
    public String getTitle(){
        return title;
    }
    public String getDate(){
        return date;
    }
    public Long getDateMillis(){
        try{
            Date date = dateFormat.parse(getDate());
            return date !=null?date.getTime():null;
        }catch(ParseException e){
            e.printStackTrace();
        }
        return null;
    }
    public String getImage(){
        return image;
    }
    public String getImageURL(){
        return BlogHttpClient.BASE_URL+BlogHttpClient.PATH+getImage();
    }
    public String getDescription(){
        return description;
    }
    public int getViews(){
        return views;
    }
    public float getRating(){
        return rating;
    }
    public Author getAuthor(){
        return author;
    }
    public void setAuthor(Author author){
        this.author = author;
    }
    public String getId(){
        return id;
    }
    @Override
    public boolean equals(Object o){
        if (this==o) return true;
        if (o==null || getClass() !=o.getClass()) return false;
        Blog blog = (Blog) o;
        return views == blog.views &&
                Float.compare(blog.rating,rating)==0 &&
                Objects.equals(id,blog.id) &&
                Objects.equals(author,blog.author) &&
                Objects.equals(title, blog.title) &&
                Objects.equals(date, blog.date) &&
                Objects.equals(image, blog.image) &&
                Objects.equals(description, blog.description);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, author, title, date, image, description, views, rating);
    }
}

