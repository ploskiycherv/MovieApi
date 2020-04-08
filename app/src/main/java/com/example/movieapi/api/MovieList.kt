package com.example.movieapi.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MovieList {

    @SerializedName("results")
    @Expose
    var results: List<Result>? = null

//    @SerializedName("page")
//    @Expose
//    var page: Int? = null
//
//    @SerializedName("total_results")
//    @Expose
//    var totalResults: Int? = null
//
//    @SerializedName("total_pages")
//    @Expose
//    var totalPages: Int? = null

}