package com.example.movieapi.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.movieapi.R
import com.example.movieapi.data.OnItemClick
import com.example.movieapi.model.MovieItem
import com.squareup.picasso.Picasso

class MovieItemAdapter(diffCallback: DiffUtil.ItemCallback<MovieItem>, var onClickListener: OnItemClick) : ListAdapter<MovieItem, MovieItemViewHolder>(diffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieItemViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        return MovieItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MovieItemViewHolder, position: Int) {
        val movieItem = getItem(position)
        holder.titleTextView.text = movieItem!!.title
        holder.yearTextView.text = movieItem.year
        holder.ratingTextView.text = movieItem.rating
        val posterUrl = "https://image.tmdb.org/t/p/w500" + movieItem.posterUrl
        Picasso.get().load(posterUrl).into(holder.posterImageView)
        holder.itemCardView.setOnClickListener { onClickListener.onClick(movieItem.id) }
    }

}