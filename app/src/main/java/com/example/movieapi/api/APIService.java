package com.example.movieapi.api;

import com.example.movieapi.model.MovieDescription;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIService {

    @GET("/3/movie/{sorting}/")
    Call<MovieList> getMovieWithId(
            @Path("sorting") String sorting,
            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("page") int page
    );

    @GET("/3/movie/{id}")
    Call<Description> getDescriptionWithId(
            @Path("id") String id,
            @Query("api_key") String apiKey,
            @Query("language") String language
    );
}
