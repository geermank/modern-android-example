package com.geermank.data.repository

import com.geermank.data.api.models.ErrorResponse
import retrofit2.HttpException
import java.io.IOException

abstract class Repository {

    fun isNetworkError(throwable: Throwable): Boolean {
        return throwable is IOException
    }

    fun isResponseError(throwable: Throwable): Boolean {
        return throwable is HttpException
    }

    fun getErrorResponse(throwable: Throwable): ErrorResponse? {
        return (throwable as? HttpException)?.let {
            ErrorResponse(it.code(), it.message())
        }
    }
}
