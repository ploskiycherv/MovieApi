package com.example.movieapi.api

import com.google.gson.annotations.SerializedName

data class Description(
        @SerializedName("backdrop_path") val backdropPath: String?,
        @SerializedName("id") val id: Int,
        @SerializedName("overview") val overview: String?,
        @SerializedName("poster_path") val posterPath: String?,
        @SerializedName("release_date") val releaseDate: String?,
        @SerializedName("runtime") val runtime: Int,
        @SerializedName("title") val title: String?,
        @SerializedName("vote_average") val voteAverage: String?,
        @SerializedName("belongs_to_collection") val belongsToCollection: Any?,
        @SerializedName("budget") val budget: Int,
        @SerializedName("homepage") val homepage: String?,
        @SerializedName("adult") val adult: Boolean?,
        @SerializedName("imdb_id") val imdbId: String?,
        @SerializedName("original_language") val originalLanguage: String?,
        @SerializedName("original_title") val originalTitle: String?,
        @SerializedName("popularity") val popularity: Double?,
        @SerializedName("revenue") val revenue: Int,
        @SerializedName("status") val status: String?,
        @SerializedName("tagline") val tagline: String?,
        @SerializedName("video") val video: Boolean,
        @SerializedName("vote_count") val voteCount: Int
)