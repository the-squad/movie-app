package com.example.android.movieapp;

/**
 * Created by mohamed on 11/22/2016.
 */

//This Class represents Review's Fields
public class ReviewData {
    private String author;
    private String content;

    public ReviewData(String author, String content) {
        this.author = author;
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
