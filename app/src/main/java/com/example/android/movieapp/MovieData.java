package com.example.android.movieapp;

import java.io.Serializable;

/**
 * Created by mohamed on 11/18/2016.
 */

//This Class represents a Movie
public class MovieData implements Serializable {
    private String poster;
    private String overview;
    private String release_date;
    private String id;
    private String title;
    private String rate;
    private String backdropPath;

    public MovieData(String poster, String overview, String release_date, String id, String title, String rate, String backdropPath) {
        this.poster = poster;
        this.overview = overview;
        this.release_date = release_date;
        this.id = id;
        this.title = title;
        this.rate = rate;
        this.backdropPath = backdropPath;
    }

    public MovieData(){

    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }
}
