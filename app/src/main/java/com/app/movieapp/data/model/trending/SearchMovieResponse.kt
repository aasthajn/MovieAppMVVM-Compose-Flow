package com.app.movieapp.data.model.trending

import com.google.gson.annotations.SerializedName


data class SearchMovieResponse(
    val page: Int,
    val results: List<SearchMovie>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)
