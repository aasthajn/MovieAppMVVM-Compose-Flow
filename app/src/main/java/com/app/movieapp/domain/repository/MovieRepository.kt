package com.app.movieapp.domain.repository

import androidx.paging.PagingData
import com.app.movieapp.core.NetworkResponseState
import com.app.movieapp.domain.entity.Movie
import kotlinx.coroutines.flow.Flow


interface MovieRepository {

    suspend fun getMovieList(): Flow<PagingData<Movie>>

    suspend fun searchMovie(search:String) : Flow<NetworkResponseState<List<Movie>>>

}
