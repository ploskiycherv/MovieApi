package com.example.movieapi.view;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.movieapi.R;
import com.example.movieapi.api.Description;
import com.example.movieapi.api.MovieList;
import com.example.movieapi.api.NetworkService;
import com.example.movieapi.api.Result;
import com.example.movieapi.data.MovieModel;
import com.example.movieapi.data.MovieRepo;
import com.example.movieapi.data.MovieRepoImpl;
import com.example.movieapi.model.MovieDescription;
import com.example.movieapi.model.MovieItem;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DescriptionMovieFragment extends Fragment {
//test
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_description_movie, container, false);

        Bundle bundle = this.getArguments();

        MovieRepo movieRepo = new MovieRepoImpl();

        String id = bundle.getString("ID");

        TextView ratingTextView, titleTextView, yearTextView, descriptionTextView, runtimeTextView;
        ImageView backdropPathImageView, posterImageView;

        titleTextView = view.findViewById(R.id.titleDescriptionTextView);
        ratingTextView = view.findViewById(R.id.ratingDescriptionTextView);
        yearTextView = view.findViewById(R.id.yearDescriptionTextView);
        descriptionTextView = view.findViewById(R.id.descriptionDescriptionTextView);
        backdropPathImageView = view.findViewById(R.id.backdropPathDescriptionImageView);
        posterImageView = view.findViewById(R.id.posterDescriptionImageView);
        runtimeTextView = view.findViewById(R.id.runtimeDescriptionTextView);

        final MovieModel viewModel = new ViewModelProvider(this).get(MovieModel.class);
        viewModel.descriptionLiveData().observe(getViewLifecycleOwner(), new Observer<List<MovieDescription>>() {
            @Override
            public void onChanged(@Nullable List<MovieDescription> data) {
                titleTextView.setText(data.get(0).getTitle());
                ratingTextView.setText(data.get(0).getRating());
                yearTextView.setText(data.get(0).getYear());
                descriptionTextView.setText(data.get(0).getDescription());
                runtimeTextView.setText(data.get(0).getRuntime());
                Picasso.get().load("https://image.tmdb.org/t/p/w500" + data.get(0).getPosterUrl()).into(posterImageView);
                Picasso.get().load("https://image.tmdb.org/t/p/original" + data.get(0).getBackdropPathUrl()).into(backdropPathImageView);
            }
        });

        viewModel.getDescriptions(id);

        return view;
    }
}
