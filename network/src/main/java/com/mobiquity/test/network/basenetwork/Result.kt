package com.mobiquity.test.network.basenetwork

import com.mobiquity.test.network.basemodels.ErrorResponse
import okhttp3.Headers

sealed class Result<out T> {
    data class Success<out T>(val body: T, val headers: Headers? = null) : Result<T>()
    data class Error(
        val code: Int? = null,
        val originalThrowable: Throwable? = null,
        val errorResponse: ErrorResponse?
    ) : Result<Nothing>()
}
