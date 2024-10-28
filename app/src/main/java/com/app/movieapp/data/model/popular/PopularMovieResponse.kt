package com.app.movieapp.data.model.popular

import com.google.gson.annotations.SerializedName


data class PopularMovieResponse(
    val page: Int,
    val results: List<PopularMovie>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)
