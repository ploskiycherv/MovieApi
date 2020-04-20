package com.example.movieapi.api

import com.google.gson.annotations.SerializedName

data class MovieList(
        @SerializedName("results") var results: List<Result>?,
        @SerializedName("page") var page: Int,
        @SerializedName("total_results") var totalResults: Int,
        @SerializedName("total_pages") var totalPages: Int
)