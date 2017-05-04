package com.example.android.movieapp;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


public class MainActivity extends AppCompatActivity {

    ConnectionChecker checker = new ConnectionChecker();
    public static boolean paneView = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (checker.checkConnection(this)) {
            setContentView(R.layout.activity_main);
        } else {
            Message.message(this, "Please check your internet connection");
            setContentView(R.layout.no_connection);
        }

        if (findViewById(R.id.twoPane) == null) {
            paneView = false;
        } else {
            paneView = true;
        }

    }
}
