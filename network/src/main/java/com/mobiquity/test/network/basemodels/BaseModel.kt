package com.mobiquity.test.network.basemodels

open class BaseModel(
    val timeoutMins: Long?,
    val errorModel: ErrorModel? = null
) {
    fun isErrorModel(): Boolean {
        if (errorModel != null) {
            if (errorModel.errorMessage.isNotEmpty() && errorModel.errorTitle.isNotEmpty()) {
                return true
            }
        }
        return false
    }
}