package com.example.movieapi.data;

import com.example.movieapi.api.Description;
import com.example.movieapi.api.MovieList;

import retrofit2.Call;

public interface MovieRepo {

    Call<MovieList> getMovieWithId(
      String sorting
    );

    Call<Description> getDescriptionWithId(
      String id
    );

}
