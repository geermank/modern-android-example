package com.geermank.rickandmorty.errors

import com.geermank.common.presentation.error.ErrorModel
import com.geermank.data.repository.Repository
import com.geermank.rickandmorty.R

class DataErrorMapper(private val repository: Repository) {

    fun getErrorModel(throwable: Throwable): ErrorModel {
        return when {
            repository.isNetworkError(throwable) -> {
                buildErrorModelOnNetworkFail()
            }
            repository.isResponseError(throwable) -> {
                buildErrorModelOnApiFail(throwable)
            }
            else -> {
                buildGenericErrorModel()
            }
        }
    }

    private fun buildErrorModelOnNetworkFail(): ErrorModel {
        return ErrorModel(R.string.error_no_connection_title, R.string.error_no_connection_subtitle, true)
    }

    private fun buildErrorModelOnApiFail(throwable: Throwable): ErrorModel {
        val errorResponse = repository.getErrorResponse(throwable)!!
        return if (errorResponse.code >= 500) {
            ErrorModel(R.string.error_server_side_title, R.string.error_server_side_subtitle, true)
        } else {
            ErrorModel(R.string.error_client_side_title, R.string.error_client_side_subtitle, false)
        }
    }

    private fun buildGenericErrorModel(): ErrorModel {
        return ErrorModel(R.string.error_generic_title, R.string.error_generic_subtitle, false)
    }
}
