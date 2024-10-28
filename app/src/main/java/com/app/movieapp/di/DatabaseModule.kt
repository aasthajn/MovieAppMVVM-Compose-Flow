package com.app.movieapp.di

import android.content.Context
import androidx.room.Room
import com.app.movieapp.data.database.MainDatabase
import com.app.movieapp.data.database.PopularMovieRemoteKeysDao
import com.app.movieapp.data.database.PopularMoviesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

private const val DB_NAME = "main_db"

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {
    @Singleton
    @Provides
    fun provideMainDataBase(@ApplicationContext appContext: Context): MainDatabase =
        Room.databaseBuilder(appContext, MainDatabase::class.java, DB_NAME)
            .fallbackToDestructiveMigration()
            .build()

    @Singleton
    @Provides
    fun providePopularMoviesDao(database: MainDatabase): PopularMoviesDao = database.popularMoviesDao()

    @Singleton
    @Provides
    fun providePopularMovieRemoteKeysDao(database: MainDatabase): PopularMovieRemoteKeysDao =
        database.popularMoviesRemoteKeysDao()
}
