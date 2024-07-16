package com.app.newsapp.data.datasource

import androidx.paging.LoadType
import androidx.paging.PagingSource
import com.app.newsapp.data.database.PopularMovieRemoteKeysDao
import com.app.newsapp.data.database.PopularMoviesDao
import com.app.newsapp.data.model.PopularMovieEntity
import com.app.newsapp.data.model.PopularMovieRemoteKeyEntity

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
