package com.app.newsapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.ExperimentalPagingApi
import androidx.paging.map
import com.app.newsapp.domain.repository.MovieRepository
import com.app.newsapp.data.datasource.MovieRemoteDataSource
import com.app.newsapp.data.datasource.PopularMovieLocalDataSource
import com.app.newsapp.data.mapper.PopularMovieRemoteToLocalMapper
import com.app.newsapp.data.mapper.PopularMoviesLocalMapper
import com.app.newsapp.data.repository.paging.MovieRemoteMediator
import javax.inject.Inject
import kotlinx.coroutines.flow.map


private const val NETWORK_PAGE_SIZE = 20
private const val INITIAL_LOAD_SIZE = 40
private const val PREFETCH_DISTANCE = 6

class MovieRepositoryImpl @Inject constructor(
    private val remoteDataSource: MovieRemoteDataSource,
    private val localDataSource: PopularMovieLocalDataSource,
    private val remoteToLocalMapper: PopularMovieRemoteToLocalMapper,
    private val localMapper: PopularMoviesLocalMapper,
) : MovieRepository {

    @OptIn(ExperimentalPagingApi::class)
    override suspend fun getMovieList() = Pager(
        config = PagingConfig(
            pageSize = NETWORK_PAGE_SIZE,
            prefetchDistance = PREFETCH_DISTANCE,
            initialLoadSize = INITIAL_LOAD_SIZE,
        ),
        remoteMediator = MovieRemoteMediator(remoteDataSource, localDataSource, remoteToLocalMapper),
        pagingSourceFactory = { localDataSource.getPagingSourceFromDb() }
    ).flow.map { pagingData ->
        pagingData.map {
            localMapper.map(it)
        }
    }
}
