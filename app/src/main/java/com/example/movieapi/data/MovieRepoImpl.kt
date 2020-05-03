package com.example.movieapi.data

import com.example.movieapi.api.Api
import com.example.movieapi.api.Description
import com.example.movieapi.api.MovieList
import io.reactivex.Single
import retrofit2.Call

class MovieRepoImpl(
        private val api: Api
) : MovieRepo {
    override fun getMovieWithId(sorting: String?): Single<MovieList> = api
            .getMovieWithId(
                    sorting = sorting,
                    apiKey = "fd81cebf8e9f74660ab0ba29bfce449f",
                    language = "en-US",
                    page = 1).map {
                if (it.isSuccessful && it.body() != null) {
                    it.body()!!
                } else {
                    error("Bad data")
                }
            }


    override fun getDescriptionWithId(id: String?): Single<Description> = api
            .getDescriptionWithId(
                    id = id,
                    apiKey = "fd81cebf8e9f74660ab0ba29bfce449f",
                    language = "en-US").map {
                if (it.isSuccessful && it.body() != null) {
                    it.body()!!
                } else {
                    error("Bad data")
                }
            }

}
