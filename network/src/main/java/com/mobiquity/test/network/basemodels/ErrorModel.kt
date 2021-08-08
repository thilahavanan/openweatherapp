package com.mobiquity.test.network.basemodels

class ErrorModel(
    val errorCode: String = "",
    val errorTitle: String = "",
    val errorMessage: String = ""
) {
    constructor(errorMap: ErrorMap) : this(
        errorCode = errorMap.errorCode ?: "",
        errorMessage = errorMap.errorMessage ?: "",
        errorTitle = errorMap.errorTitle ?: ""
    )

    constructor(errorResponse: ErrorResponse) : this(
        errorCode = errorResponse.errorCode ?: "",
        errorMessage = errorResponse.errorMessage ?: "",
        errorTitle = errorResponse.errorTitle ?: ""
    )
}