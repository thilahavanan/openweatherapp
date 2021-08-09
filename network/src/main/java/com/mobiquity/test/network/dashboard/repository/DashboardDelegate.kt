package com.mobiquity.test.network.dashboard.repository

import androidx.lifecycle.LiveData
import com.mobiquity.test.network.dashboard.models.CurrentWeatherModel
import com.mobiquity.test.network.dashboard.models.WeatherListModel

interface DashboardDelegate {
    fun getForeCastWeather(
        latitude: String,
        longitude: String,
        tempratureUnits: String
    ): LiveData<WeatherListModel>

    fun getCurrentWeather(
        latitude: String,
        longitude: String,
        tempratureUnits: String
    ): LiveData<CurrentWeatherModel>
}