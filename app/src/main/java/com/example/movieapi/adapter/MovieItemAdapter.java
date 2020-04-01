package com.example.movieapi.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.movieapi.R;
import com.example.movieapi.model.MovieItem;
import com.squareup.picasso.Picasso;

public class MovieItemAdapter extends ListAdapter<MovieItem, MovieItemViewHolder> {

    View.OnClickListener onClickListener;

    public MovieItemAdapter(@NonNull DiffUtil.ItemCallback<MovieItem> diffCallback, View.OnClickListener onClickListener) {
        super(diffCallback);
        this.onClickListener = onClickListener;
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

        holder.titleTextView.setText(movieItem.getTitle());
        holder.yearTextView.setText(movieItem.getYear());
        holder.ratingTextView.setText(movieItem.getRating());

        String posterUrl = "https://image.tmdb.org/t/p/w500" + movieItem.getPosterUrl();
        Picasso.get().load(posterUrl).into(holder.posterImageView);

        holder.itemCardView.setOnClickListener(onClickListener);

    }
}
