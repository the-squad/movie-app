package com.example.android.movieapp;

/**
 * Created by mohamedkamal on 5/5/2017.
 */

public class user {
    String usid;
    String name;
    String password;
    String email;
    private static user userInstance;
    private user(){}
    public static user getInstance()
    {
        if(userInstance == null)
        {
            userInstance = new user();
        }
        return userInstance;
    }

    public String getUsid() {
        return usid;
    }

    public void setUsid(String usid) {
        this.usid = usid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
