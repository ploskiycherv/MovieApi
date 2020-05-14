package com.example.movieapi.domain.repo

import com.example.movieapi.data.dto.MovieListResp
import com.example.movieapi.domain.entity.Description
import io.reactivex.Single

interface MovieRepo {
    fun getMovieWithId(
            sorting: String?
    ): Single<MovieListResp>

    fun getDescription(
            id: String?
    ): Single<Description>
}