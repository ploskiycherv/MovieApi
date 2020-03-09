package com.example.movieapi.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.example.movieapi.model.MovieItem;

public class MovieItemDiffUtil extends DiffUtil.ItemCallback<MovieItem> {
    @Override
    public boolean areItemsTheSame(@NonNull MovieItem oldItem, @NonNull MovieItem newItem) {
        return false;
    }

    @Override
    public boolean areContentsTheSame(@NonNull MovieItem oldItem, @NonNull MovieItem newItem) {
        return false;
    }
}
