package com.app.movieapp.data.datasource

import androidx.paging.LoadType
import androidx.paging.PagingSource
import com.app.movieapp.data.database.PopularMovieRemoteKeysDao
import com.app.movieapp.data.database.PopularMoviesDao
import com.app.movieapp.data.model.popular.PopularMovieEntity
import com.app.movieapp.data.model.popular.PopularMovieRemoteKeyEntity

import javax.inject.Inject

class PopularMovieLocalDataSourceImpl @Inject constructor(
    private val popularMoviesDao: PopularMoviesDao,
    private val popularMovieRemoteKeysDao: PopularMovieRemoteKeysDao,
) : PopularMovieLocalDataSource {

    override fun getPagingSourceFromDb(): PagingSource<Int, PopularMovieEntity> =
        popularMoviesDao.getPagingSource()

    override suspend fun insertAllMoviesToDb(list: List<PopularMovieEntity>) {
        popularMoviesDao.upsertAll(list)
    }

    override suspend fun clearAllMoviesFromDb() {
        popularMoviesDao.clearAll()
    }

    override suspend fun refreshDataForPaging(
        loadType: LoadType,
        page: Int,
        movies: List<PopularMovieEntity>,
    ) {
        popularMoviesDao.deleteAndInsertTransactionForPaging(loadType, page, movies, popularMovieRemoteKeysDao)
    }

    override suspend fun insertAllRemoteKeysToDb(list: List<PopularMovieRemoteKeyEntity>) {
        popularMovieRemoteKeysDao.upsertAll(list)
    }

    override suspend fun getRemoteKeyFromDb(movieId: Int): PopularMovieRemoteKeyEntity? =
        popularMovieRemoteKeysDao.getRemoteKey(movieId)

    override suspend fun clearAllRemoteKeysFromDb() {
        popularMovieRemoteKeysDao.clearAll()
    }
}
