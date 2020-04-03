package com.example.movieapi.data

import com.example.movieapi.api.Description
import com.example.movieapi.api.MovieList
import retrofit2.Call

interface MovieRepo {
    fun getMovieWithId(
            sorting: String?
    ): Call<MovieList?>?

    fun getDescriptionWithId(
            id: String?
    ): Call<Description?>?
}