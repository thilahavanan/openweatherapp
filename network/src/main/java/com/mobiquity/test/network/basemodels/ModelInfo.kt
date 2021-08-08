package com.mobiquity.test.network.basemodels

import com.mobiquity.test.network.basenetwork.Result

class ModelInfo<T>(val result: Result<T>) {
    val responseBody: T? = when (result) {
        is Result.Success -> {
            if (result.body is BaseResponseDto) {
                if (result.body.cod.equals("200")) {
                    result.body
                } else {
                    null
                }
            } else {
                result.body
            }
        }
        is Result.Error -> null
    }

    val errorModel: ErrorModel? = when (result) {
        is Result.Success ->
            if (result.body is BaseResponseDto) {
                if (!result.body.cod.equals("200")) {
                    null
                } else {
                    result.body.errorMap?.let { ErrorModel(it) }
                }
            } else {
                null
            }

        is Result.Error ->
            if (result.errorResponse != null) {
                ErrorModel(result.errorResponse)
            } else {
                ErrorModel(
                    ErrorMap(
                        errorCode = result.code.toString(),
                        errorMessage = result.originalThrowable?.message,
                        errorTitle = "Error title"
                    )
                )
            }
    }

    val timeoutMins = when (result) {
        is Result.Success -> {
            2L
        }
        is Result.Error -> null
    }

    override fun toString(): String {
        return "ModelInfo(result=$result,timeoutMins=$timeoutMins,responseBody=$responseBody,errorModel=$errorModel)"
    }
}