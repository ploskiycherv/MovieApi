package com.example.movieapi.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.movieapi.R
import com.example.movieapi.data.OnItemClick
import com.example.movieapi.model.MovieItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.movie_item.view.*

class MovieItemAdapter(
        diffCallback: DiffUtil.ItemCallback<MovieItem>,
        private val onClickListener: OnItemClick
) : ListAdapter<MovieItem, MovieItemViewHolder>(diffCallback) {

    companion object {
        private const val BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w500"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieItemViewHolder =
            MovieItemViewHolder(
                    LayoutInflater.from(parent.context)
                            .inflate(R.layout.movie_item, parent, false))

    override fun onBindViewHolder(holder: MovieItemViewHolder, position: Int) {
        with(holder.itemView) {
            getItem(position)?.let { item ->
                titleTextView.text = item.title
                yearTextView.text = item.year
                ratingTextView.text = item.rating
                val posterUrl: String = BASE_IMAGE_URL + item.posterUrl
                Picasso.get().load(posterUrl).into(posterImageView)
                itemCardView.setOnClickListener { onClickListener.onClick(item.id) }
            }
        }
    }

}