package com.app.movieapp.data.repository

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.ExperimentalPagingApi
import androidx.paging.map
import com.app.movieapp.core.BaseAPIResponse
import com.app.movieapp.core.NetworkResponseState
import com.app.movieapp.domain.repository.MovieRepository
import com.app.movieapp.data.datasource.MovieRemoteDataSource
import com.app.movieapp.data.datasource.PopularMovieLocalDataSource
import com.app.movieapp.data.mapper.PopularMovieRemoteToLocalMapper
import com.app.movieapp.data.mapper.PopularMoviesLocalMapper
import com.app.movieapp.data.mapper.RemotetoUIMapper
import com.app.movieapp.data.repository.paging.MovieRemoteMediator
import com.app.movieapp.di.DefaultDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn


private const val NETWORK_PAGE_SIZE = 20
private const val INITIAL_LOAD_SIZE = 40
private const val PREFETCH_DISTANCE = 6

class MovieRepositoryImpl @Inject constructor(
    private val remoteDataSource: MovieRemoteDataSource,
    private val localDataSource: PopularMovieLocalDataSource,
    private val remoteToLocalMapper: PopularMovieRemoteToLocalMapper,
    private val localMapper: PopularMoviesLocalMapper,
    private val remoteToUiMapper: RemotetoUIMapper,
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher
) : MovieRepository,BaseAPIResponse() {

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

    override suspend fun searchMovie(search: String) =flow {
        try {
            val result = remoteDataSource.searchMovies(search)
            Log.d("Aastha success",result.toString())
            emit(NetworkResponseState.Success((result.results).map {remoteToUiMapper.map(it)}))
        } catch (exception: Exception) {
            Log.d("Aastha error",exception.toString())
            emit(resolveError(exception))
        }
    }.flowOn(dispatcher)

}
