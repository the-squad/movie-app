package com.example.android.movieapp;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class DetailsActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        //adding details fragment dynamically
        DetailsFragment detailsFragment = new DetailsFragment();
        getFragmentManager().beginTransaction()
                .add(R.id.details_container, detailsFragment);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
