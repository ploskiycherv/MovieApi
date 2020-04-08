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
import com.squareup.picasso.Picasso

class DescriptionMovieFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_description_movie, container, false)

        val bundle = this.arguments

        val id = bundle!!.getString("ID")

        val titleTextView = view.findViewById<TextView>(R.id.titleDescriptionTextView)
        val ratingTextView = view.findViewById<TextView>(R.id.ratingDescriptionTextView)
        val yearTextView = view.findViewById<TextView>(R.id.yearDescriptionTextView)
        val descriptionTextView = view.findViewById<TextView>(R.id.descriptionDescriptionTextView)
        val backdropPathImageView = view.findViewById<ImageView>(R.id.backdropPathDescriptionImageView)
        val posterImageView = view.findViewById<ImageView>(R.id.posterDescriptionImageView)
        val runtimeTextView = view.findViewById<TextView>(R.id.runtimeDescriptionTextView)

        val viewModel = ViewModelProvider(this).get(MovieModel::class.java)

        viewModel.descriptionLiveData().observe(viewLifecycleOwner, Observer { data ->

            titleTextView.text = data[0].title
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