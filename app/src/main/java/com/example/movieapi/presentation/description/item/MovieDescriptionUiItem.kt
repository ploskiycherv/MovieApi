package com.example.movieapi.presentation.description.item

data class MovieDescriptionUiItem(val title: String,
                                  val year: String,
                                  val rating: String,
                                  val posterUrl: String,
                                  val backdropPathUrl: String,
                                  val id: String,
                                  val description: String,
                                  val runtime: String
)