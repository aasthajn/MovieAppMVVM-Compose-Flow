package com.app.newsapp.data.datasource

import com.app.newsapp.data.model.PopularMovieResponse
import com.app.newsapp.data.network.MovieAPIService
import com.app.newsapp.utils.Constants
import javax.inject.Inject
import javax.inject.Singleton

class MovieRemoteDataSourceImpl @Inject constructor(
    private val apiService: MovieAPIService,
) : MovieRemoteDataSource {

    override suspend fun getPopularMovies(pageNumber: Int): PopularMovieResponse {
        return apiService.getMovies(apiKey = Constants.MOVIE_API_KEY, pageNumber = pageNumber)
    }
}
