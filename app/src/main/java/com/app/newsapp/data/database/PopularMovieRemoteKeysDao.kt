package com.app.newsapp.data.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.app.newsapp.data.model.PopularMovieRemoteKeyEntity

@Dao
interface PopularMovieRemoteKeysDao {

    @Upsert
    suspend fun upsertAll(remoteKey: List<PopularMovieRemoteKeyEntity>)

    @Query("SELECT * FROM trending_movie_remote_keys WHERE id = :movieId")
    suspend fun getRemoteKey(movieId: Int): PopularMovieRemoteKeyEntity?

    @Query("DELETE FROM trending_movie_remote_keys")
    suspend fun clearAll()
}
