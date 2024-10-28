package com.app.movieapp.di

import com.app.movieapp.data.datasource.MovieRemoteDataSource
import com.app.movieapp.data.datasource.MovieRemoteDataSourceImpl
import com.app.movieapp.data.datasource.PopularMovieLocalDataSource
import com.app.movieapp.data.datasource.PopularMovieLocalDataSourceImpl
import com.app.movieapp.data.mapper.PopularMovieRemoteToLocalMapper
import com.app.movieapp.data.mapper.PopularMoviesLocalMapper
import com.app.movieapp.data.model.popular.PopularMovie
import com.app.movieapp.data.model.popular.PopularMovieEntity
import com.app.movieapp.data.repository.MovieRepositoryImpl
import com.app.movieapp.domain.entity.Movie
import com.app.movieapp.domain.repository.MovieRepository
import com.app.movieapp.utils.Mapper
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
