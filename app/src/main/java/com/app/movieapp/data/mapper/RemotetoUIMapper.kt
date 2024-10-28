package com.app.movieapp.data.mapper

import com.app.movieapp.data.model.trending.SearchMovie
import com.app.movieapp.domain.entity.Movie
import com.app.movieapp.utils.Constants
import com.app.movieapp.utils.Mapper
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject


private const val FORMAT_DATE_TRENDING_MOVIE_RELEASE_FORMATTER = "dd MMM yyyy"
private const val CHAR_STAR = '★'
class RemotetoUIMapper @Inject constructor() :
    Mapper<SearchMovie, Movie> {
    override suspend fun map(from: SearchMovie) :Movie{
        val parser = SimpleDateFormat(Constants.FORMAT_DATE_MOVIE_RELEASE_PARSER, Locale.ENGLISH)
        val formatter = SimpleDateFormat(FORMAT_DATE_TRENDING_MOVIE_RELEASE_FORMATTER, Locale.ENGLISH)
        return Movie(
            id = from.id,
            overview = from.overview.orEmpty(),
            posterUrl = Constants.BASE_URL_IMAGE_MOVIE_POSTER + from.poster,
            releaseDate = runCatching {
                parser.parse(from.releaseDate.orEmpty())
            }.getOrNull()?.let { releaseDate ->
                formatter.format(releaseDate)
            }.orEmpty(),
            title = from.title.orEmpty(),
            rating = from.voteAverage.let { voteAverage ->
                String.format(Locale.ENGLISH, "%.1f", voteAverage).plus(CHAR_STAR)
            }.orEmpty(),
        )
    }
}
