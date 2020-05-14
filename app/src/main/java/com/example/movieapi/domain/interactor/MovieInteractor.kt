package com.example.movieapi.domain.interactor

import com.example.movieapi.domain.entity.Description
import io.reactivex.Single

interface MovieInteractor {

    fun getDescription(id: String): Single<Description>

}