package com.app.movieapp.data.repository.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.app.movieapp.data.datasource.MovieRemoteDataSource
import com.app.movieapp.data.mapper.PopularMovieRemoteToLocalMapper
import com.app.movieapp.data.model.popular.PopularMovieEntity

import retrofit2.HttpException
import java.io.IOException

class MoviePagingSource(
    private val remoteDataSource: MovieRemoteDataSource,
    private val mapper: PopularMovieRemoteToLocalMapper
) : PagingSource<Int, PopularMovieEntity>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PopularMovieEntity> {
        return try {
            val currentPage = params.key ?: 1
            val movies = remoteDataSource.getPopularMovies(
                pageNumber = currentPage
            )
            LoadResult.Page(
                data = movies.results.map { mapper.map(it)},
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = if (movies.results.isEmpty()) null else movies.page!! + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, PopularMovieEntity>): Int? {
        return state.anchorPosition
    }

}
