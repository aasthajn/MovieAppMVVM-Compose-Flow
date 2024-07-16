package com.app.newsapp.data.network

import com.app.newsapp.data.model.PopularMovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieAPIService {
    @GET("/3/trending/movie/day")
    suspend fun getMovies(
        @Query("api_key") apiKey: String,
        @Query("page") pageNumber: Int
    ): PopularMovieResponse
}
