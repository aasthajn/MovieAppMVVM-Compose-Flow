package com.app.movieapp.data.datasource

import androidx.paging.LoadType
import androidx.paging.PagingSource
import com.app.movieapp.data.model.popular.PopularMovieEntity
import com.app.movieapp.data.model.popular.PopularMovieRemoteKeyEntity


interface PopularMovieLocalDataSource {
    fun getPagingSourceFromDb(): PagingSource<Int, PopularMovieEntity>
    suspend fun insertAllMoviesToDb(list: List<PopularMovieEntity>)
    suspend fun clearAllMoviesFromDb()
    suspend fun refreshDataForPaging(loadType: LoadType, page: Int, movies: List<PopularMovieEntity>)
    suspend fun insertAllRemoteKeysToDb(list: List<PopularMovieRemoteKeyEntity>)
    suspend fun getRemoteKeyFromDb(movieId: Int): PopularMovieRemoteKeyEntity?
    suspend fun clearAllRemoteKeysFromDb()
}
