package com.example.android.movieapp;

/**
 * Created by mohamedkamal on 5/14/2017.
 */

public class messengerData {
    private String Message;
    private String user;

    public messengerData(String message, String user) {
        Message = message;
        this.user = user;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }
}
