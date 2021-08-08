package com.mobiquity.test.network.basenetwork

import okhttp3.Interceptor
import okhttp3.Response


class AppIdInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val originalHttpUrl = original.url
        val url = originalHttpUrl.newBuilder()
            .addQueryParameter(
                "appId",
                "fae7190d7e6433ec3a45285ffcf55c86"
            ).build()
        val requestBuilder = original.newBuilder()
            .url(url)

        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}