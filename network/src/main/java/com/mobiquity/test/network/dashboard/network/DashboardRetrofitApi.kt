package com.mobiquity.test.network.dashboard.network

import com.mobiquity.test.network.dashboard.models.WeatherListDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface DashboardRetrofitApi {
    @GET("forecast")
    fun getForeCastWeather(
        @Query("lat") latitude: String,
        @Query("lon") longitude: String
    ): Call<WeatherListDto>
}