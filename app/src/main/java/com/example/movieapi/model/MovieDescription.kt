package com.example.movieapi.model

data class MovieDescription(val title: String,
                       val year: String,
                       val rating: String,
                       val posterUrl: String,
                       val backdropPathUrl: String,
                       val id: String,
                       val description: String,
                       val runtime: String
)