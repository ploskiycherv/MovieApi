package com.example.movieapi.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Description {

    @SerializedName("backdrop_path")
    @Expose
    var backdropPath: String? = null

    @SerializedName("id")
    @Expose
    var id: Int? = null

    @SerializedName("overview")
    @Expose
    var overview: String? = null

    @SerializedName("poster_path")
    @Expose
    var posterPath: String? = null

    @SerializedName("release_date")
    @Expose
    var releaseDate: String? = null

    @SerializedName("runtime")
    @Expose
    var runtime = 0

    @SerializedName("title")
    @Expose
    var title: String? = null

    @SerializedName("vote_average")
    @Expose
    var voteAverage: String? = null

//    @SerializedName("belongs_to_collection")
//    @Expose
//    var belongsToCollection: Any? = null
//
//    @SerializedName("budget")
//    @Expose
//    var budget: Int? = null
//
//    @SerializedName("homepage")
//    @Expose
//    var homepage: String? = null
//    @SerializedName("adult")
//    @Expose
//    var adult: Boolean? = null
//
//    @SerializedName("imdb_id")
//    @Expose
//    var imdbId: String? = null
//
//    @SerializedName("original_language")
//    @Expose
//    var originalLanguage: String? = null
//
//    @SerializedName("original_title")
//    @Expose
//    var originalTitle: String? = null
//
//    @SerializedName("popularity")
//    @Expose
//    var popularity: Double? = null
//
//    @SerializedName("revenue")
//    @Expose
//    var revenue: Int? = null
//
//    @SerializedName("status")
//    @Expose
//    var status: String? = null
//
//    @SerializedName("tagline")
//    @Expose
//    var tagline: String? = null
//
//    @SerializedName("video")
//    @Expose
//    var video: Boolean? = null
//
//    @SerializedName("vote_count")
//    @Expose
//    var voteCount: Int? = null

}