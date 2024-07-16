package com.app.newsapp.data.model

import com.google.gson.annotations.SerializedName


data class PopularMovie(
    @SerializedName("id")
    val id: Int,
    @SerializedName("original_title")
    val title: String,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("popularity")
    val popularity: Double,
    @SerializedName("poster_path")
    val poster: String,
    @SerializedName("release_date")
    val releaseDate: String?,
    val video: Boolean,
    @SerializedName("vote_average")
    val voteAverage1: Double,
    @SerializedName("vote_count")
    val voteCount1: Int
)
