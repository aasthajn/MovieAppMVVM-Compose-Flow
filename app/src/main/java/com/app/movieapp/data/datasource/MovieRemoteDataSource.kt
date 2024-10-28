package com.app.movieapp.data.datasource

import com.app.movieapp.data.model.popular.PopularMovieResponse
import com.app.movieapp.data.model.trending.SearchMovieResponse

interface MovieRemoteDataSource {

    suspend fun getPopularMovies(pageNumber: Int): PopularMovieResponse

    suspend fun searchMovies(query:String) : SearchMovieResponse
}
