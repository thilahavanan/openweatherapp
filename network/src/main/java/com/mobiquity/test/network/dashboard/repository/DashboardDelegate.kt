package com.mobiquity.test.network.dashboard.repository

import androidx.lifecycle.LiveData
import com.mobiquity.test.network.dashboard.models.WeatherListModel

interface DashboardDelegate {
    fun getForeCastWeather(latitude: String, longitude: String): LiveData<WeatherListModel>
}