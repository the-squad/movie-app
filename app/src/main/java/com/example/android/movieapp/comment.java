package com.example.android.movieapp;

import android.content.Context;

/**
 * Created by mohamedkamal on 5/5/2017.
 */

public class comment {
    private String comment_id;
    private String id;
    private String comment;
    private String user;
    private Context context;
    private boolean own;
    public comment(){}
    public comment(String moveid, String comment , String user) {
        this.id = moveid;
        this.comment = comment;
        this.user = user;
        own = false;
    }

    public boolean isOwn() {
        return own;
    }

    public void setOwn(boolean own) {
        this.own = own;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public String getComment_id() {
        return comment_id;
    }

    public void setComment_id(String comment_id) {
        this.comment_id = comment_id;
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
