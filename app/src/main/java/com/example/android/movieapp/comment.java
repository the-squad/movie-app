package com.example.android.movieapp;

import java.util.ArrayList;

/**
 * Created by mohamedkamal on 5/5/2017.
 */

public class comment {
    private String name;
    private String comment;

    /*public comment(String name, String comment) {
        this.name = name;
        this.comment = comment;
    }*/

    public static ArrayList<comment> commentArrayList()
    {
        ArrayList<comment> x = new ArrayList<>();
        x.add(new comment().setName("mohamed kamal1").setComment("blaaaaaaaaaaa1"));
        x.add(new comment().setName("mohamed kamal2").setComment("blaaaaaaaaaaa2"));
        x.add(new comment().setName("mohamed kamal3").setComment("blaaaaaaaaaaa3"));
        x.add(new comment().setName("mohamed kamal4").setComment("blaaaaaaaaaaa4"));
        return x;
    }
    public String getName() {
        return name;
    }

    public comment setName(String name) {
        this.name = name;
        return this;
    }

    public comment setComment(String comment) {
        this.comment = comment;
        return this;
    }

    public String getComment() {
        return comment;
    }
}
