package com.example.movieapi.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

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
import com.example.movieapi.data.MovieRepoImpl;
import com.example.movieapi.model.MovieDescription;
import com.example.movieapi.model.MovieItem;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DescriptionMovieFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_description_movie, container, false);

        Bundle bundle = this.getArguments();

        String id = bundle.getString("ID");

        TextView ratingTextView, titleTextView, yearTextView, descriptionTextView;
        ImageView backdropPathImageView, posterImageView;

        titleTextView = view.findViewById(R.id.titleDescriptionTextView);
        ratingTextView = view.findViewById(R.id.ratingDescriptionTextView);
        yearTextView = view.findViewById(R.id.yearDescriptionTextView);
        descriptionTextView = view.findViewById(R.id.descriptionDescriptionTextView);
        backdropPathImageView = view.findViewById(R.id.backdropPathDescriptionImageView);
        posterImageView = view.findViewById(R.id.posterDescriptionImageView);

        NetworkService.getInstance().getAPIService().getDescriptionWithId(id, "fd81cebf8e9f74660ab0ba29bfce449f", "en-US")
                .enqueue(new Callback<Description>() {
                    @Override
                    public void onResponse(Call<Description> call, Response<Description> response) {

                        Description description = response.body();

                       titleTextView.setText(description.getTitle());
                    ratingTextView.setText(description.getVoteAverage());
                        yearTextView.setText(description.getReleaseDate());
                       descriptionTextView.setText(description.getOverview());
                        Picasso.get().load("https://image.tmdb.org/t/p/original" + description.getBackdropPath()).into(backdropPathImageView);
                        Picasso.get().load("https://image.tmdb.org/t/p/w500" + description.getPosterPath()).into(posterImageView);

                    }

                    @Override
                    public void onFailure(Call<Description> call, Throwable t) {

                    }
                });

        return view;
    }
}
