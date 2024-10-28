package com.app.movieapp.data.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.app.movieapp.data.model.popular.PopularMovieRemoteKeyEntity

@Dao
interface PopularMovieRemoteKeysDao {

    @Upsert
    suspend fun upsertAll(remoteKey: List<PopularMovieRemoteKeyEntity>)

    @Query("SELECT * FROM trending_movie_remote_keys WHERE id = :movieId")
    suspend fun getRemoteKey(movieId: Int): PopularMovieRemoteKeyEntity?

    @Query("DELETE FROM trending_movie_remote_keys")
    suspend fun clearAll()
}
