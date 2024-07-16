package com.app.newsapp.di

import com.app.newsapp.data.repository.MovieRepositoryImpl
import com.app.newsapp.domain.repository.MovieRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
class DataModules {

    @Module
    @InstallIn(SingletonComponent::class)
    abstract class RepositoryModule {

       /* @Singleton
        @Binds
        abstract fun bindMovieRepository(repository: MovieRepositoryImpl): MovieRepository*/
    }
}
