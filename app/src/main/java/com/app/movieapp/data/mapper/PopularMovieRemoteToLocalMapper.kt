package com.app.movieapp.data.mapper

import com.app.movieapp.data.model.popular.PopularMovie
import com.app.movieapp.data.model.popular.PopularMovieEntity
import com.app.movieapp.utils.Mapper
import javax.inject.Inject

class PopularMovieRemoteToLocalMapper @Inject constructor() :
    Mapper<PopularMovie, PopularMovieEntity> {
    override suspend fun map(from: PopularMovie): PopularMovieEntity = PopularMovieEntity(
        id = from.id,
        overview = from.overview,
        posterPath = from.poster,
        releaseDate = from.releaseDate,
        popularity = from.popularity,
        title = from.title,
        voteCount = from.voteCount1,
        voteAverage = from.voteAverage1
    )
}
