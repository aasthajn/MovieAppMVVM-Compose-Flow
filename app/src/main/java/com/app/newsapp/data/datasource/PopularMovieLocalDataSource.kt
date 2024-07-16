package com.app.newsapp.data.datasource

import androidx.paging.LoadType
import androidx.paging.PagingSource
import com.app.newsapp.data.model.PopularMovieEntity
import com.app.newsapp.data.model.PopularMovieRemoteKeyEntity


interface PopularMovieLocalDataSource {
    fun getPagingSourceFromDb(): PagingSource<Int, PopularMovieEntity>
    suspend fun insertAllMoviesToDb(list: List<PopularMovieEntity>)
    suspend fun clearAllMoviesFromDb()
    suspend fun refreshDataForPaging(loadType: LoadType, page: Int, movies: List<PopularMovieEntity>)
    suspend fun insertAllRemoteKeysToDb(list: List<PopularMovieRemoteKeyEntity>)
    suspend fun getRemoteKeyFromDb(movieId: Int): PopularMovieRemoteKeyEntity?
    suspend fun clearAllRemoteKeysFromDb()
}
