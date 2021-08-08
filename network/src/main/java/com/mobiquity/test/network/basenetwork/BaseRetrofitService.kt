package com.mobiquity.test.network.basenetwork

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Interceptor
import org.koin.core.KoinComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

open class BaseRetrofitService<T>(
    private val api: Class<T>,
    private val interceptorList: List<Interceptor>? = null,
    private val connectTimeoutSeconds: Long = 30L,
    private val readTimeoutSeconds: Long = 30L
) : KoinComponent {
    val apiInstance: T

    init {
        val retrofitBuilder = Retrofit.Builder()
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
        val newInterceptorList: ArrayList<Interceptor> = ArrayList(interceptorList ?: emptyList())

        val networkClient = NetworkClient.ApiClient(
            interceptorList = interceptorList,
            connectTimeoutSeconds = connectTimeoutSeconds,
            readTimeoutSeconds = readTimeoutSeconds
        )

        apiInstance = retrofitBuilder.baseUrl("http://api.openweathermap.org/data/2.5/")
            .client(networkClient.getOkhttpClient()).build().create(api)
    }
}