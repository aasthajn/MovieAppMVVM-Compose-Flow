package com.app.movieapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.app.movieapp.data.model.popular.PopularMovieEntity
import com.app.movieapp.data.model.popular.PopularMovieRemoteKeyEntity

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

