package com.example.movieapi.data.repo

import com.example.movieapi.data.api.Api
import com.example.movieapi.data.dto.DescriptionResp
import com.example.movieapi.data.dto.MovieListResp
import com.example.movieapi.domain.entity.Description
import com.example.movieapi.domain.repo.MovieRepo
import io.reactivex.Single

class MovieRepoImpl(
        private val api: Api
) : MovieRepo {
    override fun getMovieWithId(sorting: String?): Single<MovieListResp> = api
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


    override fun getDescription(id: String?): Single<Description> = api
            .getDescriptionWithId(
                    id = id,
                    apiKey = "fd81cebf8e9f74660ab0ba29bfce449f",
                    language = "en-US").map {
                val body:DescriptionResp? = it.body()
                if (it.isSuccessful && body != null) {
                    body
                } else {
                    error("Bad data")
                }
            }.map {
                Description(
                        id = it.id.orEmpty(),
                        backdropPath = it.backdropPath.orEmpty(),
                        overview = it.overview.orEmpty(),
                        posterPath = it.posterPath.orEmpty(),
                        releaseDate = it.releaseDate.orEmpty(),
                        runtime = it.runtime,
                        title = it.title.orEmpty(),
                        voteAverage = it.voteAverage.orEmpty(),
                        originalTitle = it.originalTitle.orEmpty()
                )
            }

}
