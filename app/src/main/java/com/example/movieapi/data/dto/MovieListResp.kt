package com.example.movieapi.data.dto

import com.google.gson.annotations.SerializedName

data class MovieListResp(
        @SerializedName("results") val movieDtos: List<MovieDto>?,
        @SerializedName("page") val page: Int,
        @SerializedName("total_results") val totalResults: Int,
        @SerializedName("total_pages") val totalPages: Int
)