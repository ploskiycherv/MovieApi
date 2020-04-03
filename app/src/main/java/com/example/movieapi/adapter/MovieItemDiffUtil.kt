package com.example.movieapi.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.movieapi.model.MovieItem

class MovieItemDiffUtil : DiffUtil.ItemCallback<MovieItem>() {
    override fun areItemsTheSame(oldItem: MovieItem, newItem: MovieItem): Boolean {
        return false
    }

    override fun areContentsTheSame(oldItem: MovieItem, newItem: MovieItem): Boolean {
        return false
    }
}