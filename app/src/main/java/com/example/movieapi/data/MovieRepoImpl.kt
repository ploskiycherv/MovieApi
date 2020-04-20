package com.example.movieapi.data

import com.example.movieapi.api.Api
import com.example.movieapi.api.Description
import com.example.movieapi.api.MovieList
import retrofit2.Call

class MovieRepoImpl(
        private val api: Api
) : MovieRepo {
    override fun getMovieWithId(sorting: String?): Call<MovieList?> = api
            .getMovieWithId(
                    sorting = sorting,
                    apiKey = "fd81cebf8e9f74660ab0ba29bfce449f",
                    language = "en-US",
                    page = 1)


    override fun getDescriptionWithId(id: String?): Call<Description?> = api
            .getDescriptionWithId(
                    id = id,
                    apiKey = "fd81cebf8e9f74660ab0ba29bfce449f",
                    language = "en-US")

}
