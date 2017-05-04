package com.example.android.movieapp;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by mohamed on 11/29/2016.
 */

public class ConnectionChecker {
    //checks whether  If there is an Internet Connection or no
    public static boolean checkConnection(Context context)
    {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnectedOrConnecting();

    }

}
