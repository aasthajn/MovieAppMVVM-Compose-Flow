package com.app.movieapp.data.datasource

import com.app.movieapp.data.model.popular.PopularMovieResponse
import com.app.movieapp.data.model.trending.SearchMovieResponse
import com.app.movieapp.data.network.MovieAPIService
import com.app.movieapp.utils.Constants
import javax.inject.Inject

class MovieRemoteDataSourceImpl @Inject constructor(
    private val apiService: MovieAPIService,
) : MovieRemoteDataSource {

    override suspend fun getPopularMovies(pageNumber: Int): PopularMovieResponse {
        return apiService.getMovies(apiKey = Constants.MOVIE_API_KEY, pageNumber = pageNumber)
    }

    override suspend fun searchMovies(query: String): SearchMovieResponse {
        return apiService.searchMovie(apiKey = Constants.MOVIE_API_KEY, pageNumber = 1, query = query)
    }
}
