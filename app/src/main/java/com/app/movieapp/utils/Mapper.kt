package com.app.movieapp.utils

interface Mapper<F, T> {
    suspend fun map(from: F): T
}
