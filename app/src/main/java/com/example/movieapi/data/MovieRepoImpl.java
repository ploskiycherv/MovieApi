package com.example.movieapi.data;

import com.example.movieapi.api.MovieList;
import com.example.movieapi.api.NetworkService;

import retrofit2.Call;

public class MovieRepoImpl implements MovieRepo {
    @Override
    public Call<MovieList> getMovieWithId(String sorting) {
        return  NetworkService.getInstance()
                .getAPIService()
                .getMovieWithId(sorting, "fd81cebf8e9f74660ab0ba29bfce449f", "en-US", 1);
    }
}
