package com.app.movieapp.ui.home

import com.app.movieapp.domain.entity.Movie

sealed interface MovieUiState {

    data class Success(
        val movie: Movie
    ) : MovieUiState

    object Empty : MovieUiState
}
