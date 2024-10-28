package com.app.movieapp.data.network

import com.app.movieapp.data.model.popular.PopularMovieResponse
import com.app.movieapp.data.model.trending.SearchMovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieAPIService {

    @GET("/3/movie/popular")
    suspend fun getMovies(
        @Query("api_key") apiKey: String,
        @Query("page") pageNumber: Int
    ): PopularMovieResponse

    @GET("3/search/movie")
    suspend fun searchMovie(
        @Query("api_key") apiKey: String,
        @Query("page") pageNumber: Int,
        @Query("query") query:String
    ): SearchMovieResponse
}
