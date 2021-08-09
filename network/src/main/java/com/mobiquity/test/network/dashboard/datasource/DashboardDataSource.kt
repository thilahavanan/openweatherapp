package com.mobiquity.test.network.dashboard.datasource

import com.mobiquity.test.network.dashboard.models.CurrentWeatherModel
import com.mobiquity.test.network.dashboard.models.WeatherListModel

interface DashboardDataSource {

    suspend fun getForeCastWeather(
        latitude: String,
        longitude: String,
        tempratureUnits: String
    ): WeatherListModel

    suspend fun getCurrentWeather(
        latitude: String,
        longitude: String,
        tempratureUnits: String
    ): CurrentWeatherModel
}