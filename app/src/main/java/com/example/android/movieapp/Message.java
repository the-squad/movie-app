package com.example.android.movieapp;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by mohamed on 11/27/2016.
 */

public class Message {
    //responsible for displaying toasts to the user
    public static void message(Context context , String message)
    {
        Toast.makeText(context,message,Toast.LENGTH_LONG).show();
    }
}
