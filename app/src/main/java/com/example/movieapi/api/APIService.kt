package com.example.movieapi.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface APIService {
    @GET("/3/movie/{sorting}/")
    fun getMovieWithId(
            @Path("sorting") sorting: String?,
            @Query("api_key") apiKey: String?,
            @Query("language") language: String?,
            @Query("page") page: Int
    ): Call<MovieList?>?

    @GET("/3/movie/{id}")
    fun getDescriptionWithId(
            @Path("id") id: String?,
            @Query("api_key") apiKey: String?,
            @Query("language") language: String?
    ): Call<Description?>?
}