package com.example.movieapi.domain.entity

data class Description(
        val backdropPath: String,
        val id: String,
        val overview: String,
        val posterPath: String,
        val releaseDate: String,
        val runtime: Int,
        val title: String,
        val voteAverage: String,
        val originalTitle: String
)