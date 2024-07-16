package com.app.newsapp.data.mapper

import com.app.newsapp.data.model.PopularMovie
import com.app.newsapp.data.model.PopularMovieEntity
import com.app.newsapp.utils.Mapper
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
