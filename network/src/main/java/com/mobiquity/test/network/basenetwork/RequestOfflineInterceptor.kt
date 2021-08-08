package com.mobiquity.test.network.basenetwork

import android.content.Context
import android.net.ConnectivityManager
import okhttp3.Interceptor
import okhttp3.Response

class RequestOfflineInterceptor(context: Context) : Interceptor {
    private val appContext = context.applicationContext
    override fun intercept(chain: Interceptor.Chain): Response {
        if (isOffline()) {
            throw OfflineExeception()
        }
        return chain.proceed(chain.request())
    }

    private fun isOffline(): Boolean {
        val connectivityManager =
            appContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        val result = networkInfo != null && networkInfo.isConnected
        return result
    }
}