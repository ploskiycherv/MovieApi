package com.example.movieapi.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Result {
    @SerializedName("poster_path") var posterPath: String? = null
    @SerializedName("id") var id: String? = null
    @SerializedName("title") var title: String? = null
    @SerializedName("vote_average") var voteAverage: String? = null
    @SerializedName("release_date") var releaseDate: String? = null
    @SerializedName("overview") var overview: String? = null
    @SerializedName("adult") var adult: Boolean? = null
    @SerializedName("backdrop_path") var backdropPath: String? = null
    @SerializedName("original_language") var originalLanguage: String? = null
    @SerializedName("original_title") var originalTitle: String? = null
    @SerializedName("genre_ids") var genreIds: List<Int>? = null
    @SerializedName("popularity") var popularity: Double? = null
    @SerializedName("vote_count") var voteCount: Int? = null
    @SerializedName("video") var video: Boolean? = null

}