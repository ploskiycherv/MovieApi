package com.example.movieapi.domain.interactor

import com.example.movieapi.domain.repo.MovieRepo
import com.example.movieapi.domain.entity.Description
import io.reactivex.Single

class MovieInteractorImpl(
        private val repo: MovieRepo
) : MovieInteractor {

    override fun getDescription(id: String): Single<Description> = repo.getDescription(id)

}