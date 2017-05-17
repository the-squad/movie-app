package com.example.android.movieapp;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;


class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieHolder> {

    private ArrayList<MovieData> moviesList;
    private Context context;

    MovieAdapter(ArrayList<MovieData> moviesList, Context context) {
        this.moviesList = moviesList;
        this.context = context;
    }

    @Override
    public MovieHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent, false);
        MovieHolder movieHolder = new MovieHolder(item);
        return movieHolder;
    }

    @Override
    public void onBindViewHolder(MovieHolder holder, int position) {

        final MovieData movie = moviesList.get(position);// movie object

        context = holder.itemView.getContext();//getting context
        holder.poster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = ((Activity) context).getFragmentManager();
                DetailsFragment detailsFragment = DetailsFragment.getInstance(movie);
                FragmentTransaction fragmentTransaction =
                        fragmentManager.beginTransaction();

                //handeling twoPane
                if (MainActivity.paneView == true) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("movie", movie);
                    detailsFragment.setArguments(bundle);

                    fragmentTransaction.replace(R.id.detailPane, detailsFragment).commit();
                } else {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("movie", movie);
                    detailsFragment.setArguments(bundle);

                    fragmentManager.beginTransaction()
                            .replace(R.id.activity_main, detailsFragment).addToBackStack("fragment1").commit();
                }

            }
        });

        Picasso.with(context).load("http://image.tmdb.org/t/p/w342/" + movie.getPoster()).placeholder(R.drawable.loading).into(holder.poster);
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }

    class MovieHolder extends RecyclerView.ViewHolder {
        ImageView poster;

        MovieHolder(View itemView) {
            super(itemView);
            poster = (ImageView) itemView.findViewById(R.id.moviePoster);
        }
    }
}
