package com.example.android.movieapp;

/**
 * Created by mohamed on 11/22/2016.
 */
//This Class Represents Trailer's Fields
public class TrailerData {
    private String videoName;
    private String videoLink;

    public TrailerData(String videoName, String videoLink) {
        this.videoName = videoName;
        this.videoLink = videoLink;
    }

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public String getVideoLink() {
        return videoLink;
    }

    public void setVideoLink(String videoLink) {
        this.videoLink = videoLink;
    }
}
