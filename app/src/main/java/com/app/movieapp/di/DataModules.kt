package com.app.movieapp.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

class DataModules {

    @Module
    @InstallIn(SingletonComponent::class)
    abstract class RepositoryModule {

       /* @Singleton
        @Binds
        abstract fun bindMovieRepository(repository: MovieRepositoryImpl): MovieRepository*/
    }
}
