package com.app.movieapp.data.repository.paging

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.app.movieapp.data.datasource.MovieRemoteDataSource
import com.app.movieapp.data.datasource.PopularMovieLocalDataSource
import com.app.movieapp.data.mapper.PopularMovieRemoteToLocalMapper
import com.app.movieapp.data.model.popular.PopularMovieEntity
import com.app.movieapp.data.model.popular.PopularMovieRemoteKeyEntity

const val BASE_VALUE = 10

@OptIn(ExperimentalPagingApi::class)
class MovieRemoteMediator(
    private val popularRemoteDataSource: MovieRemoteDataSource,
    private val popularLocalDataSource: PopularMovieLocalDataSource,
    private val remoteToLocalMapper: PopularMovieRemoteToLocalMapper,
) : RemoteMediator<Int, PopularMovieEntity>() {

    @Suppress("ReturnCount")
    override suspend fun load(loadType: LoadType, state: PagingState<Int, PopularMovieEntity>): MediatorResult {
        val page = when (loadType) {
            LoadType.REFRESH -> null
            LoadType.PREPEND -> return MediatorResult.Success(
                endOfPaginationReached = true
            )
            LoadType.APPEND -> {
                val remoteKeys = getRemoteKeyForLastItem(state)
                remoteKeys?.nextKey ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
            }
        } ?: 1

        //Timber.i("Fetching trending movies on page $page from remote")
        try {
            val popularMovieResponse =  popularRemoteDataSource.getPopularMovies(
                pageNumber = page
            )

            Log.d("Aastha",popularMovieResponse.results.toString())
            val movies = popularMovieResponse.results.mapIndexed { index, movie ->
                /* It is required to keep order of items because it
                   shows trending movies and the order is important.
                   Otherwise, Room orders items by their ids */
                remoteToLocalMapper.map(movie).copy(order = page.minus(1) * BASE_VALUE + index)
            }
            popularLocalDataSource.refreshDataForPaging(loadType, page, movies)
            return MediatorResult.Success(endOfPaginationReached = movies.size < state.config.pageSize)

        }catch (exception: Exception) {
            return  MediatorResult.Error(exception)
        }
        //return MediatorResult.Error(Exception("Unexpected error"))
    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, PopularMovieEntity>
    ): PopularMovieRemoteKeyEntity? = state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
        ?.let { movie ->
            popularLocalDataSource.getRemoteKeyFromDb(movie.id)
        }
}
