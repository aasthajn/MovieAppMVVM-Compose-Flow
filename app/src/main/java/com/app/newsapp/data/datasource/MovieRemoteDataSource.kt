package com.app.newsapp.data.datasource

import com.app.newsapp.data.model.PopularMovieResponse

interface MovieRemoteDataSource {

    suspend fun getPopularMovies(pageNumber: Int): PopularMovieResponse
}
