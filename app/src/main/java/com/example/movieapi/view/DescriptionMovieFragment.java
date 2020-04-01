package com.example.movieapi.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.movieapi.R;
import com.example.movieapi.api.MovieList;
import com.example.movieapi.api.NetworkService;
import com.example.movieapi.model.MovieItem;

public class DescriptionMovieFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_description_movie, container, false);

        String id;
        TextView ratingTextView, titleTextView, yearTextView, descriptionTextView;
        ImageView backdropPathImageView, posterImageView;

        titleTextView = view.findViewById(R.id.titleTextView);
        ratingTextView = view.findViewById(R.id.ratingTextView);
        yearTextView = view.findViewById(R.id.yearTextView);
        descriptionTextView = view.findViewById(R.id.descriptionTextView);
        backdropPathImageView = view.findViewById(R.id.backdropPathImageView);
        posterImageView = view.findViewById(R.id.posterImageView);

        NetworkService.getInstance().getAPIService().getDescriptionWithId(); //как передать сюда айдиху ептааааа

        return view;
    }
}
