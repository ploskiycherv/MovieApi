package com.example.movieapi.presentation.list.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.movieapi.presentation.list.item.MovieItem

class MovieItemDiffUtil : DiffUtil.ItemCallback<MovieItem>() {

    override fun areItemsTheSame(oldItem: MovieItem, newItem: MovieItem): Boolean =
            oldItem == newItem

    override fun areContentsTheSame(oldItem: MovieItem, newItem: MovieItem): Boolean =
            true
}