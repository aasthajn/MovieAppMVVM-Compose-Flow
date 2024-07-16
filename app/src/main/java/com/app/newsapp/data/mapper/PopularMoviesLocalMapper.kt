package com.app.newsapp.data.mapper

import com.app.newsapp.data.model.PopularMovieEntity
import com.app.newsapp.domain.entity.Movie
import com.app.newsapp.utils.Constants.Companion.BASE_URL_IMAGE_MOVIE_POSTER
import com.app.newsapp.utils.Constants.Companion.FORMAT_DATE_MOVIE_RELEASE_PARSER
import com.app.newsapp.utils.Mapper
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject

private const val FORMAT_DATE_TRENDING_MOVIE_RELEASE_FORMATTER = "dd MMM yyyy"
private const val CHAR_STAR = '★'
class PopularMoviesLocalMapper @Inject constructor() : Mapper<PopularMovieEntity, Movie> {
    override suspend fun map(from: PopularMovieEntity): Movie {
        val parser = SimpleDateFormat(FORMAT_DATE_MOVIE_RELEASE_PARSER, Locale.ENGLISH)
        val formatter = SimpleDateFormat(FORMAT_DATE_TRENDING_MOVIE_RELEASE_FORMATTER, Locale.ENGLISH)
        return Movie(
            id = from.id,
            overview = from.overview.orEmpty(),
            posterUrl = BASE_URL_IMAGE_MOVIE_POSTER + from.posterPath,
            releaseDate = runCatching {
                parser.parse(from.releaseDate.orEmpty())
            }.getOrNull()?.let { releaseDate ->
                formatter.format(releaseDate)
            }.orEmpty(),
            title = from.title.orEmpty(),
            rating = from.voteAverage?.let { voteAverage ->
                String.format(Locale.ENGLISH, "%.1f", voteAverage).plus(CHAR_STAR)
            }.orEmpty(),
        )
    }
}
