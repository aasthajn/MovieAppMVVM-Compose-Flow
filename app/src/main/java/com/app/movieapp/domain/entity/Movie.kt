package com.app.movieapp.domain.entity

data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val posterUrl: String?,
    val releaseDate: String?,
    val rating: String
)
