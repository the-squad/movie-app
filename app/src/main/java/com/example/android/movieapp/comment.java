package com.example.android.movieapp;

/**
 * Created by mohamedkamal on 5/5/2017.
 */

public class comment {
    private String id;
    private String comment;
    private String user;

    public comment(){}
    public comment(String moveid, String comment , String user) {
        this.id = moveid;
        this.comment = comment;
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public comment setId(String name) {
        this.id = name;
        return this;
    }

    public comment setComment(String comment) {
        this.comment = comment;
        return this;
    }

    public String getComment() {
        return comment;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
