package com.example.movieapi.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.movieapi.R;
import com.example.movieapi.model.MovieItem;
import com.example.movieapi.view.DescriptionFragment;
import com.squareup.picasso.Picasso;

public class MovieItemAdapter extends ListAdapter<MovieItem, MovieItemViewHolder> {

    public MovieItemAdapter(@NonNull DiffUtil.ItemCallback<MovieItem> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public MovieItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent, false);

        return new MovieItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieItemViewHolder holder, int position) {

        MovieItem movieItem = getItem(position);
        DescriptionFragment descriptionFragment = new DescriptionFragment();

        holder.titleTextView.setText(movieItem.getTitle());
        holder.yearTextView.setText(movieItem.getYear());
        holder.ratingTextView.setText(movieItem.getRating());

        String posterUrl = "https://image.tmdb.org/t/p/w500" + movieItem.getPosterUrl();
        Picasso.get().load(posterUrl).into(holder.posterImageView);

        holder.itemCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Bundle bundle = new Bundle();
                bundle.putString("ID", movieItem.getId());
                descriptionFragment.setArguments(bundle);
                FragmentTransaction fragmentTransaction = ((FragmentActivity) context).getSupportFragmentManager().beginTransaction();
                fragmentTransaction.add(R.id.description_fragment_container, descriptionFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

    }
}
