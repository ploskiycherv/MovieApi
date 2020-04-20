package com.example.movieapi.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.movieapi.R
import com.example.movieapi.data.MovieModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_description_movie.*
import org.koin.android.ext.android.inject

class DescriptionMovieFragment : Fragment() {

    private val viewModel: MovieModel by inject()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?):
            View = inflater.inflate(R.layout.fragment_description_movie, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle = this.arguments

        val id = bundle!!.getString("ID")

        viewModel.descriptionLiveData().observe(viewLifecycleOwner, Observer { data ->

            titleDescriptionTextView.text = data[0].title
            ratingDescriptionTextView.text = data[0].rating
            yearDescriptionTextView.text = data[0].year
            descriptionDescriptionTextView.text = data[0].description
            runtimeDescriptionTextView.text = data[0].runtime
            Picasso.get().load("https://image.tmdb.org/t/p/w500" + data[0].posterUrl).into(posterDescriptionImageView)
            Picasso.get().load("https://image.tmdb.org/t/p/original" + data[0].backdropPathUrl).into(backdropPathDescriptionImageView)

        })

        if (id != null) {
            viewModel.getDescriptions(id)
        }
    }
}
