package com.example.movieapi.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkService private constructor() {
    private val baseUrl = "https://api.themoviedb.org"

    private val retrofit: Retrofit

    val aPIService: APIService
        get() = retrofit.create(APIService::class.java)

    companion object {
        var instance: NetworkService? = null
            get() {
                if (field == null) {
                    field = NetworkService()
                }
                return field
            }
            private set
    }

    init {
        retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }
}