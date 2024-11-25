package com.app.movieapp.core

import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.net.ssl.SSLHandshakeException

open class BaseAPIResponse{

    fun resolveError(e: Exception): NetworkResponseState.Error {
        var error = NetworkErrorException(errorMessage ="something went wrong")

        when (e) {
            is SocketTimeoutException -> {
                error = NetworkErrorException(errorMessage = "connection timeout!")
            }
            is ConnectException -> {
                error = NetworkErrorException(errorMessage = "no internet access!")
            }
            is UnknownHostException -> {
                error = NetworkErrorException(errorMessage = "no internet access!")
            }
            is SSLHandshakeException -> {
            error = NetworkErrorException(errorMessage = "Invalid request!")
            }

            is HttpException -> {
                when(e.code()){
                    502 -> {
                        error = NetworkErrorException(e.code(),  "internal error!")
                    }
                    401 -> {
                        throw AuthenticationException("authentication error!")
                    }
                    400 -> {
                        error = NetworkErrorException.parseException(e)
                    }
                }
            }
        }


        return NetworkResponseState.Error(error)
    }
}
