package com.mobiquity.test.openweather.dashboard.usecase

import androidx.lifecycle.LiveData
import com.mobiquity.test.network.dashboard.models.WeatherListModel
import com.mobiquity.test.network.dashboard.repository.DashboardRepository

class DashboardUseCase(private val dashboardRepository: DashboardRepository) {
    fun getForeCastWeather(latitude: String, longitude: String): LiveData<WeatherListModel> {
        return dashboardRepository.getForeCastWeather(latitude, longitude)
    }
}