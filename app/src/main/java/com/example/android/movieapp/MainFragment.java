package com.example.android.movieapp;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by mohamed on 11/29/2016.
 */

public class MainFragment extends Fragment {
    RecyclerView recyclerView;
    MovieAdapter movieAdapter;
    MovieDbAdapter movieDbAdapterObj;
    SharedPreferences sharedPreferences ;
    private void jsonParsing(String x)
    {
        MovieParser movieParser =  new MovieParser() {
            @Override
            protected void onPostExecute(ArrayList<MovieData> movieData) {
                movieAdapter = new MovieAdapter(movieData,getActivity());
                recyclerView.setAdapter(movieAdapter);
            }
        };
        movieParser.execute(x);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main,container,false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        movieDbAdapterObj = new MovieDbAdapter(getActivity());

        sharedPreferences = getActivity().getSharedPreferences("state", Context.MODE_APPEND);
        String type = sharedPreferences.getString("key","popular");
        if(type.equals("fav")&&movieDbAdapterObj.checkMovies()) {
            MovieAdapter dbAdapter = new MovieAdapter(movieDbAdapterObj.getAllFavMovies(), getActivity());
            recyclerView.setAdapter(dbAdapter);
        }
        else
        {jsonParsing(type);}
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        setHasOptionsMenu(true);
        return rootView;
    }



    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
        inflater.inflate(R.menu.menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        sharedPreferences = getActivity().getSharedPreferences("state",Context.MODE_APPEND);
        SharedPreferences.Editor editor = sharedPreferences.edit();


        if(id==R.id.popular)
        {
            editor.putString("key","popular");
            editor.commit();
            jsonParsing("popular");
            return true;
        }

        if(id==R.id.most_rated)
        {
            editor.putString("key","top_rated");
            editor.commit();
            jsonParsing("top_rated");
            return true;
        }
        if(id==R.id.favorite)
        {
            if(movieDbAdapterObj.checkMovies()==false)
                Message.message(getActivity(),"You have no favorite movies");
            else
            {
                editor.putString("key","fav");
                editor.commit();
                MovieAdapter dbAdapter = new MovieAdapter(movieDbAdapterObj.getAllFavMovies(),getActivity());
                recyclerView.setAdapter(dbAdapter);
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
