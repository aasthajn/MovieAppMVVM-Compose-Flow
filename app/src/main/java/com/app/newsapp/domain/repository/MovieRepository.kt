package com.app.newsapp.domain.repository

import androidx.paging.PagingData
import com.app.newsapp.core.NetworkResponseState
import com.app.newsapp.domain.entity.Movie
import kotlinx.coroutines.flow.Flow


interface MovieRepository {

    suspend fun getMovieList(): Flow<PagingData<Movie>>
}
