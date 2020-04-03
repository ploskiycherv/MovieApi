package com.example.movieapi.data

import com.example.movieapi.api.Description
import com.example.movieapi.api.MovieList
import com.example.movieapi.api.NetworkService
import retrofit2.Call

class MovieRepoImpl : MovieRepo {
    override fun getMovieWithId(sorting: String?): Call<MovieList?>? {
        return NetworkService.instance
                ?.aPIService
                ?.getMovieWithId(sorting, "fd81cebf8e9f74660ab0ba29bfce449f", "en-US", 1)
    }

    override fun getDescriptionWithId(id: String?): Call<Description?>? {
        return NetworkService.instance
                ?.aPIService
                ?.getDescriptionWithId(id, "fd81cebf8e9f74660ab0ba29bfce449f", "en-US")
    }
}