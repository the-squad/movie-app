package com.example.android.movieapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by mohamedkamal on 5/5/2017.
 */

public class commentAdapter extends RecyclerView.Adapter<commentAdapter.commentViewHolder>{
    ArrayList<comment> comments;

    public commentAdapter(ArrayList<comment> comments) {
        this.comments = comments;
    }

    @Override
    public commentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_row,parent,false);
        commentViewHolder holder = new commentViewHolder(row);
        return holder;
    }

    @Override
    public void onBindViewHolder(commentViewHolder holder, int position) {
        comment comment = comments.get(position);
        holder.name.setText(comment.getUser());
        holder.comment.setText(comment.getComment());
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }
    class commentViewHolder extends RecyclerView.ViewHolder
    {
        TextView name,comment;
        LinearLayout containerlayout;
        public commentViewHolder(final View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.nameComment);
            comment = (TextView) itemView.findViewById(R.id.commentText);
            containerlayout = (LinearLayout) itemView.findViewById(R.id.containerlayout);
            containerlayout.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    Toast.makeText(itemView.getContext(),comment.getText().toString(),Toast.LENGTH_LONG).show();
                    return true;
                }
            });
        }
    }
}
