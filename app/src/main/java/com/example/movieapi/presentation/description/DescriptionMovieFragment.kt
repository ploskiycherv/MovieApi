package com.example.movieapi.presentation.description

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.movieapi.R
import com.example.movieapi.presentation.description.DescriptionModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_description_movie.*
import org.koin.android.ext.android.inject

class DescriptionMovieFragment : Fragment() {

    private val viewModel: DescriptionModel by inject()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?):
            View = inflater.inflate(R.layout.fragment_description_movie, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle = this.arguments

        val id = bundle!!.getString("ID")

        viewModel.descriptionLiveData().observe(viewLifecycleOwner, Observer { movieDescription ->
            with(movieDescription) {
                titleDescriptionTextView.text = title
                ratingDescriptionTextView.text = rating
                yearDescriptionTextView.text = year
                descriptionDescriptionTextView.text = description
                runtimeDescriptionTextView.text = runtime
                Picasso.get().load("https://image.tmdb.org/t/p/w500$posterUrl")
                        .into(posterDescriptionImageView)
                Picasso.get().load("https://image.tmdb.org/t/p/original$backdropPathUrl")
                        .into(backdropPathDescriptionImageView)
            }
        })

        if (id != null) {
            viewModel.getDescription(id)
        }
    }
}
