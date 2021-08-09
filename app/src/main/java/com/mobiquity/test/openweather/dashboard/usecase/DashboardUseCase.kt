package com.mobiquity.test.openweather.dashboard.usecase

import androidx.lifecycle.LiveData
import com.mobiquity.test.network.dashboard.models.CurrentWeatherModel
import com.mobiquity.test.network.dashboard.models.WeatherListModel
import com.mobiquity.test.network.dashboard.repository.DashboardRepository

class DashboardUseCase(private val dashboardRepository: DashboardRepository) {
    fun getForeCastWeather(
        latitude: String,
        longitude: String,
        tempratureUnits: String
    ): LiveData<WeatherListModel> {
        return dashboardRepository.getForeCastWeather(
            latitude = latitude,
            longitude = longitude,
            tempratureUnits = tempratureUnits
        )
    }

    fun getCurrentWeatherData(
        latitude: String,
        longitude: String,
        tempratureUnits: String
    ): LiveData<CurrentWeatherModel> {
        return dashboardRepository.getCurrentWeather(
            latitude = latitude,
            longitude = longitude,
            tempratureUnits = tempratureUnits
        )
    }
}