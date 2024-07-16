package com.app.newsapp.utils

interface Mapper<F, T> {
    suspend fun map(from: F): T
}
