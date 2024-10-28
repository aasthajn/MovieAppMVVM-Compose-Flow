package com.app.movieapp.data.database

import androidx.paging.LoadType
import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.app.movieapp.data.model.popular.PopularMovieEntity
import com.app.movieapp.data.model.popular.PopularMovieRemoteKeyEntity

const val TMDB_FIRST_PAGE_INDEX = 1
const val LAST_PAGE = 1000

@Dao
interface PopularMoviesDao {
    @Upsert
    suspend fun upsertAll(list: List<PopularMovieEntity>)

    @Query("SELECT * FROM trending_movies ORDER BY `order` ASC")
    fun getPagingSource(): PagingSource<Int, PopularMovieEntity>

    @Query("DELETE FROM trending_movies")
    suspend fun clearAll()

    @Transaction
    suspend fun deleteAndInsertTransactionForPaging(
        loadType: LoadType,
        page: Int,
        movies: List<PopularMovieEntity>,
        popularMovieRemoteKeysDao: PopularMovieRemoteKeysDao
    ) {
        val endOfPaginationReached = page == LAST_PAGE
        if (loadType == LoadType.REFRESH) {
            popularMovieRemoteKeysDao.clearAll()
            clearAll()
        }
        val prevKey = page.minus(1).takeUnless { page == TMDB_FIRST_PAGE_INDEX }
        val nextKey = page.plus(1).takeUnless { endOfPaginationReached }
        val keys = movies.map {
            PopularMovieRemoteKeyEntity(id = it.id, prevKey = prevKey, nextKey = nextKey)
        }
        popularMovieRemoteKeysDao.upsertAll(keys)
        upsertAll(movies)
    }
}
