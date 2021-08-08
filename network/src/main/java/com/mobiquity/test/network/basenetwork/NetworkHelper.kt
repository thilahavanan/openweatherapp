package com.mobiquity.test.network.basenetwork

import com.mobiquity.test.network.basemodels.ErrorResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import timber.log.Timber

const val RESPONSE_STATUS_SUCCESS = "200"
const val ERROR_CODE_COMMON = "100"
const val ERROR_TITLE = "Error"
suspend fun <T> networkApiCall(
    dispatcher: CoroutineDispatcher = Dispatchers.IO,
    apiCall: suspend () -> Call<T>
): Result<T> {
    return withContext(dispatcher) {
        try {
            val timeStart = System.currentTimeMillis()
            val response = apiCall().execute()
            Timber.tag("HTTP").d("Request took ${System.currentTimeMillis() - timeStart} ms")
            val headers = response.headers()
            val body = response.body()
            val errorBody = response.errorBody()
            Timber.d("Response:$body,Error=$errorBody")
            if (body != null) {
                Result.Success(body = body, headers = headers)
            } else {
                Result.Error(errorResponse = ErrorResponse())
            }
        } catch (throwable: Throwable) {
            Timber.tag("HTTP Exeception").d("$throwable")
            getResultError(throwable)
        }
    }
}

fun getResultError(throwable: Throwable): Result.Error = when (throwable) {
    is OfflineExeception -> Result.Error(
        errorResponse = ErrorResponse(
            errorCode = ERROR_CODE_COMMON,
            errorMessage = "Device Offline",
            errorTitle = ERROR_TITLE
        )
    )
    else -> {
        Result.Error(
            errorResponse = ErrorResponse(
                errorTitle = ERROR_TITLE,
                errorMessage = throwable.message,
                errorCode = ERROR_CODE_COMMON
            )
        )
    }
}
