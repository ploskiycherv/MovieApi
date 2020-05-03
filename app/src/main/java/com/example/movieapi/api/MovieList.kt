package com.example.movieapi.api

import com.google.gson.annotations.SerializedName

data class MovieList(
        @SerializedName("results") val results: List<Result>?,
        @SerializedName("page") val page: Int,
        @SerializedName("total_results") val totalResults: Int,
        @SerializedName("total_pages") val totalPages: Int
)