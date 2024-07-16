package com.app.newsapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.app.newsapp.data.model.PopularMovieEntity
import com.app.newsapp.data.model.PopularMovieRemoteKeyEntity

@Database(
    entities = [
        PopularMovieEntity::class,
        PopularMovieRemoteKeyEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class MainDatabase : RoomDatabase() {
    abstract fun popularMoviesDao(): PopularMoviesDao
    abstract fun popularMoviesRemoteKeysDao(): PopularMovieRemoteKeysDao
}

