package com.example.movieapi.data.dto

import com.google.gson.annotations.SerializedName

data class MovieDto(
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("id") val id: String?,
    @SerializedName("title") val title: String?,
    @SerializedName("vote_average") val voteAverage: String?,
    @SerializedName("release_date") val releaseDate: String?,
    @SerializedName("overview") val overview: String?,
    @SerializedName("adult") val adult: Boolean?,
    @SerializedName("backdrop_path") val backdropPath: String?,
    @SerializedName("original_language") val originalLanguage: String?,
    @SerializedName("original_title") val originalTitle: String?,
    @SerializedName("genre_ids") val genreIds: List<Int>?,
    @SerializedName("popularity") val popularity: Double?,
    @SerializedName("vote_count") val voteCount: Int?,
    @SerializedName("video") val video: Boolean?
)