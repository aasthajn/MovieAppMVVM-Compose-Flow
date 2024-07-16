package com.app.newsapp.utils

interface Constants {
    companion object {
        const val BASE_URL = "https://api.themoviedb.org"
        const val IMAGE_URL_MOVIE = "https://image.tmdb.org/t/p/w500"
//        const val BASE_URL = "https://api.mocklets.com/p6796/"
        const val IMAGE_LOAD_DELAY = 0L //change to 2000L for cancel
        const val LOG_TAG = "picGallery"
        const val MOVIE_API_KEY = "8f3845576311ddddc2ae7f7801641fdb"
        const val MAX_PAGE_SIZE = 10
        const val FORMAT_DATE_MOVIE_RELEASE_PARSER = "yyyy-MM-dd"
        const val BASE_URL_IMAGE_MOVIE_POSTER = "https://image.tmdb.org/t/p/w500"
    }

}
