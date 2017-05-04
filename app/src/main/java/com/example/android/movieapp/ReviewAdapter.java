package com.example.android.movieapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by mohamed on 11/22/2016.
 */

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewHolder> {
    private ArrayList<ReviewData> reviewsList;
    private Context context;

    ReviewAdapter(ArrayList<ReviewData> reviewsList, Context context) {
        this.reviewsList = reviewsList;
        this.context = context;
    }

    @Override
    public ReviewAdapter.ReviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.review_row, parent, false);
        ReviewAdapter.ReviewHolder reviewHolder = new ReviewAdapter.ReviewHolder(item);
        return reviewHolder;
    }

    @Override
    public void onBindViewHolder(ReviewAdapter.ReviewHolder holder, int position) {

        final ReviewData review = reviewsList.get(position);// review object
        holder.author.setText(review.getAuthor());
        holder.content.setText(review.getContent());

    }

    @Override
    public int getItemCount() {
        return reviewsList.size();
    }

    class ReviewHolder extends RecyclerView.ViewHolder {
        TextView author;
        TextView content;

        public ReviewHolder(View itemView) {
            super(itemView);
            author = (TextView) itemView.findViewById(R.id.author);
            content = (TextView) itemView.findViewById(R.id.content);
        }
    }
}
