package com.example.android.movieapp;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


/**
 * Created by mohamed on 11/29/2016.
 */

public class DetailsFragment extends Fragment {
    MovieData model = new MovieData();
    MovieDbAdapter movieDbAdapterObj;
    DatabaseReference db;
    commentAdapter commentAdapter;
    ArrayList<comment> comments = new ArrayList<>();
    private ImageView posterView;
    private TextView titleView;
    private TextView yearView;
    private TextView rateView;
    private TextView storyView;
    private ImageView coverView;
    public RecyclerView videosView;
    public RecyclerView reviewsView;
    public RecyclerView commentsView;
    private TrailerAdapter videosAdapter;
    private ReviewAdapter reviewAdapter;
    private ImageButton favorite;
    private ImageButton delete;

    public static DetailsFragment getInstance(MovieData movie)
    {
        DetailsFragment detailsFragment = new DetailsFragment();
        Bundle args = new Bundle();
        args.putSerializable("movie",movie);
        detailsFragment.setArguments(args);
        return detailsFragment;
    }

    public DetailsFragment()
    {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_details, container, false);

//initialize views
        coverView = (ImageView) rootView.findViewById(R.id.cover);
        posterView = (ImageView) rootView.findViewById(R.id.img2);
        titleView = (TextView) rootView.findViewById(R.id.title);
        yearView = (TextView) rootView.findViewById(R.id.year);
        rateView = (TextView) rootView.findViewById(R.id.rate);
        storyView = (TextView) rootView.findViewById(R.id.story);
        videosView = (RecyclerView) rootView.findViewById(R.id.vidRecycler);
        reviewsView = (RecyclerView) rootView.findViewById(R.id.revRecycler);
        commentsView = (RecyclerView) rootView.findViewById(R.id.commentrec);
        commentsView.setLayoutManager(new LinearLayoutManager(getActivity()));
        videosView.setLayoutManager(new LinearLayoutManager(getActivity()));
        reviewsView.setLayoutManager(new LinearLayoutManager(getActivity()));
        favorite = (ImageButton) rootView.findViewById(R.id.favBtn);
        delete = (ImageButton) rootView.findViewById(R.id.delete);

        model = (MovieData) getArguments().getSerializable("movie");
        commentAdapter = new commentAdapter(comments);
        commentsView.setAdapter(commentAdapter);
        db = FirebaseDatabase.getInstance().getReference();
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot d : dataSnapshot.child("comments").getChildren())
                {
                    Log.d("blaaaaaaaa",(String) dataSnapshot.child("users").child((String) d.child("name").getValue()).child("name").getValue());
                    //String name = (String) dataSnapshot.child("users").child().getValue();
                    comments.add(new comment("blaaa",(String) d.child("comment").getValue()));
                    commentAdapter.notifyDataSetChanged();
                    //Log.d("bbblaaaaaaaaaa",(String) d.child("name").getValue());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        //setting data into views
        titleView.setText(model.getTitle());
        rateView.setText(model.getRate());
        yearView.setText(model.getRelease_date());
        storyView.setText(model.getOverview());
        Picasso.with(getActivity()).load("http://image.tmdb.org/t/p/w342/" + model.getPoster()).placeholder(R.drawable.loading).into(posterView);
        Picasso.with(getActivity()).load("http://image.tmdb.org/t/p/w780/" + model.getBackdropPath()).placeholder(R.drawable.loading).into(coverView);


        //SQLite
        movieDbAdapterObj = new MovieDbAdapter(getActivity());
        //check if movies is a favorite movie
        if (movieDbAdapterObj.checkFav(model.getId()) == true) {
            favorite.setVisibility(View.GONE);
            delete.setVisibility(View.VISIBLE);
        } else {
            favorite.setVisibility(View.VISIBLE);
            delete.setVisibility(View.GONE);
        }

        //when favorite is clicked
        favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                movieDbAdapterObj.insertFavMovie(model);
                Message.message(getActivity(), model.getTitle() + " has been added to favorites");
                favorite.setVisibility(View.GONE);
                delete.setVisibility(View.VISIBLE);
            }
        });
        //when delete is clicked
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                movieDbAdapterObj.deleteFavMovie(model);
                Message.message(getActivity(), model.getTitle() + " has been removed from favorites");
                delete.setVisibility(View.GONE);
                favorite.setVisibility(View.VISIBLE);
            }
        });

        TrailerParser trailerParser = new TrailerParser() {
            @Override
            protected void onPostExecute(ArrayList<TrailerData> trailersList) {
                videosAdapter = new TrailerAdapter(trailersList, getActivity());
                videosView.setAdapter(videosAdapter);
            }
        };
        trailerParser.execute(model.getId());

        ReviewParser reviewParser = new ReviewParser() {
            @Override
            protected void onPostExecute(ArrayList<ReviewData> reviewsList) {
                reviewAdapter = new ReviewAdapter(reviewsList, getActivity());
                reviewsView.setAdapter(reviewAdapter);
            }
        };
        reviewParser.execute(model.getId());

        setHasOptionsMenu(true);
        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.details_menu,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id==R.id.home)
        {
            Intent intent = new Intent(getActivity(),MainActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

