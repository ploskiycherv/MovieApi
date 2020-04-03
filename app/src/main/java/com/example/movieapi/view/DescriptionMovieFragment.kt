package com.example.movieapi.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.movieapi.R
import com.example.movieapi.data.MovieModel
import com.example.movieapi.data.MovieRepo
import com.example.movieapi.data.MovieRepoImpl
import com.squareup.picasso.Picasso

class DescriptionMovieFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_description_movie, container, false)

        val bundle = this.arguments

        val movieRepo: MovieRepo = MovieRepoImpl()

        val id = bundle!!.getString("ID")

        val ratingTextView: TextView
        val titleTextView: TextView
        val yearTextView: TextView
        val descriptionTextView: TextView
        val runtimeTextView: TextView
        val backdropPathImageView: ImageView
        val posterImageView: ImageView

        titleTextView = view.findViewById(R.id.titleDescriptionTextView)
        ratingTextView = view.findViewById(R.id.ratingDescriptionTextView)
        yearTextView = view.findViewById(R.id.yearDescriptionTextView)
        descriptionTextView = view.findViewById(R.id.descriptionDescriptionTextView)
        backdropPathImageView = view.findViewById(R.id.backdropPathDescriptionImageView)
        posterImageView = view.findViewById(R.id.posterDescriptionImageView)
        runtimeTextView = view.findViewById(R.id.runtimeDescriptionTextView)

        val viewModel = ViewModelProvider(this).get(MovieModel::class.java)

        viewModel.descriptionLiveData().observe(viewLifecycleOwner, Observer { data ->

            titleTextView.text = data!![0].title
            ratingTextView.text = data[0].rating
            yearTextView.text = data[0].year
            descriptionTextView.text = data[0].description
            runtimeTextView.text = data[0].runtime
            Picasso.get().load("https://image.tmdb.org/t/p/w500" + data[0].posterUrl).into(posterImageView)
            Picasso.get().load("https://image.tmdb.org/t/p/original" + data[0].backdropPathUrl).into(backdropPathImageView)

        })

        if (id != null) {
            viewModel.getDescriptions(id)
        }
        return view

    }
}