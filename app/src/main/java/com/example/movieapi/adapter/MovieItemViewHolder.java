package com.example.movieapi.adapter;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieapi.R;
import com.example.movieapi.view.MainActivity;
import com.example.movieapi.view.MainMovieListFragment;

public class MovieItemViewHolder extends RecyclerView.ViewHolder {

    TextView titleTextView, yearTextView, ratingTextView;
    ImageView posterImageView;
    CardView itemCardView;

    public MovieItemViewHolder(@NonNull View itemView) {
        super(itemView);

        titleTextView = itemView.findViewById(R.id.titleTextView);
        yearTextView = itemView.findViewById(R.id.yearTextView);
        ratingTextView = itemView.findViewById(R.id.ratingTextView);
        posterImageView = itemView.findViewById(R.id.posterImageView);
        itemCardView = itemView.findViewById(R.id.itemCardView);

    }
}
