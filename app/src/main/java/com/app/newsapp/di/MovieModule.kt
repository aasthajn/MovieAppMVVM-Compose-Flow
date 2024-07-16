package com.app.newsapp.di

import com.app.newsapp.data.datasource.MovieRemoteDataSource
import com.app.newsapp.data.datasource.MovieRemoteDataSourceImpl
import com.app.newsapp.data.datasource.PopularMovieLocalDataSource
import com.app.newsapp.data.datasource.PopularMovieLocalDataSourceImpl
import com.app.newsapp.data.mapper.PopularMovieRemoteToLocalMapper
import com.app.newsapp.data.mapper.PopularMoviesLocalMapper
import com.app.newsapp.data.model.PopularMovie
import com.app.newsapp.data.model.PopularMovieEntity
import com.app.newsapp.data.repository.MovieRepositoryImpl
import com.app.newsapp.domain.entity.Movie
import com.app.newsapp.domain.repository.MovieRepository
import com.app.newsapp.utils.Mapper
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
interface MovieModule {


    @ViewModelScoped
    @Binds
    fun bindMovieRepository(repository: MovieRepositoryImpl): MovieRepository

    @ViewModelScoped
    @Binds
    fun bindRemoteDataSource(impl: MovieRemoteDataSourceImpl): MovieRemoteDataSource

    @ViewModelScoped
    @Binds
    fun bindLocalDataSource(impl: PopularMovieLocalDataSourceImpl): PopularMovieLocalDataSource

    @ViewModelScoped
    @Binds
    fun bindLocalMapper(impl: PopularMoviesLocalMapper): Mapper<PopularMovieEntity, Movie>

    @ViewModelScoped
    @Binds
    fun bindRemoteToLocalMapper(impl: PopularMovieRemoteToLocalMapper): Mapper<PopularMovie, PopularMovieEntity>
}
