package com.example.android.movieapp;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import static android.R.attr.x;
import static android.provider.ContactsContract.CommonDataKinds.Website.URL;


public abstract class MovieParser extends AsyncTask<String, Void, ArrayList<MovieData>> {

    public ArrayList<MovieData> moviesList = new ArrayList<>();

    @Override
    protected ArrayList<MovieData> doInBackground(String... params) {

        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String param_type = params[0];
        final String BASE_URL = "http://api.themoviedb.org/3/movie/" + param_type +
                "?api_key=f0d7c86f6eeb4a09849c830fb0d27a46";
        try {

            URL url = new URL(BASE_URL);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.connect();

            InputStreamReader stream = new InputStreamReader(urlConnection.getInputStream());
            reader = new BufferedReader(stream);


            final StringBuilder textBuilder = new StringBuilder();
            String line;
            String finalResult;
            while ((line = reader.readLine()) != null) {
                textBuilder.append(line);
            }
            finalResult = textBuilder.toString();

            JSONObject jsonObject = new JSONObject(finalResult);
            JSONArray jsonArray = jsonObject.getJSONArray("results");

            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject currentMovie = jsonArray.getJSONObject(i);
                String id = currentMovie.getString("id");
                String poster = currentMovie.getString("poster_path");
                String overview = currentMovie.getString("overview");
                String release = currentMovie.getString("release_date");
                String title = currentMovie.getString("title");
                String rate = currentMovie.getString("vote_average");
                String backdropPath = currentMovie.getString("backdrop_path");

                MovieData movieData = new MovieData(poster, overview, release, id, title, rate, backdropPath);
                moviesList.add(movieData);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return moviesList;
    }

    @Override
    protected abstract void onPostExecute(ArrayList<MovieData> movieData);
}
